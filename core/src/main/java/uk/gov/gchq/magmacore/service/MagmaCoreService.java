/*
 * Copyright 2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package uk.gov.gchq.magmacore.service;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.Lang;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.query.QueryResult;
import uk.gov.gchq.magmacore.database.query.QueryResultList;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfAssociation;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.service.dto.ParticipantDetails;
import uk.gov.gchq.magmacore.service.sparql.MagmaCoreServiceQueries;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbDeleteOperation;

/**
 * Service for interacting with a {@link MagmaCoreDatabase}.
 */
public class MagmaCoreService {

    private final MagmaCoreDatabase database;

    /**
     * Constructs a MagmaCoreService for a {@link MagmaCoreDatabase}.
     *
     * @param database {@link MagmaCoreDatabase} to build the service for.
     */
    MagmaCoreService(final MagmaCoreDatabase database) {
        this.database = database;
    }

    /**
     * Find the details of participants in associations of a specific kind between two
     * {@link Individuals} objects at a point in time.
     *
     * @param individual1 the first {@link Individual}
     * @param individual2 the second {@link Individual}
     * @param kind        the {@link KindOfAssociation}
     * @param pointInTime the {@link PointInTime} that the associations should exist.
     * @return a {@link Set} of {@link AssociationDetails}
     */
    public Set<ParticipantDetails> findParticipantDetails(final Individual individual1, final Individual individual2,
            final KindOfAssociation kind, final PointInTime pointInTime) {

        final LocalDateTime when = LocalDateTime
                .parse(pointInTime.value(HQDM.ENTITY_NAME).iterator().next().toString());

        final QueryResultList queryResultList = database
                .executeQuery(String.format(MagmaCoreServiceQueries.FIND_PARTICIPANT_DETAILS_QUERY, individual1.getId(),
                        individual2.getId(),
                        kind.getId(), individual1.getId(), individual2.getId(), kind.getId()));

        // Filter by the pointInTime
        final QueryResultList queryResults = filterByPointInTime(when, queryResultList);

        // Process all of the participants.
        return database.toTopObjects(queryResults)
                .stream()
                // Map them to ParticipantDetails objects.
                .map(p -> {
                    // Get the Roles of the Participant.
                    final Set<Role> roles = p.value(HQDM.MEMBER_OF_KIND)
                            .stream()
                            .map(o -> (IRI) o)
                            .map(roleIri -> database.get(roleIri))
                            .map(role -> (Role) role)
                            .collect(Collectors.toSet());
                    return new ParticipantDetails((Participant) p, roles);
                })
                .collect(Collectors.toSet());

    }

    /**
     * Filter a {@link QueryResultList} by a {@link PointInTime}. The {@link QueryResultList} should
     * have `start` and `finish` columns to allow filtering.
     *
     * @param when            {@link LocalDateTime}
     * @param queryResultList {@link QueryResultList}
     * @return {@link QueryResultList}
     */
    private QueryResultList filterByPointInTime(final LocalDateTime when, final QueryResultList queryResultList) {
        final List<QueryResult> queryResults = queryResultList.getQueryResults()
                .stream()
                .filter(qr -> {
                    final RDFNode start = qr.get("start");
                    final RDFNode finish = qr.get("finish");
                    final LocalDateTime from = (start != null) ? LocalDateTime.parse(start.toString())
                            : LocalDateTime.MIN;
                    final LocalDateTime to = (finish != null) ? LocalDateTime.parse(finish.toString())
                            : LocalDateTime.MAX;

                    return (when.equals(from) || when.isAfter(from))
                            && (when.equals(to) || when.isBefore(to));
                })
                .collect(Collectors.toList());
        return new QueryResultList(queryResultList.getVarNames(), queryResults);
    }

    /**
     * Find the Set of {@link Thing} represented by the given sign value.
     *
     * <p>
     * This could probably be replaced with a (rather complex) SPARQL query if {@link MagmaCoreDatabase}
     * allowed the execution of such queries.
     * </p>
     *
     * @param community   the {@link RecognizingLanguageCommunity} that recognises the sign value.
     * @param pattern     the {@link Pattern} the sign conforms to.
     * @param value       {@link String} the sign value to look for.
     * @param pointInTime {@link PointInTime} the point in time we are interested in.
     * @return {@link List} of {@link Thing} represented by the value.
     * @throws MagmaCoreException if the number of {@link RepresentationByPattern} found is not 1
     */
    public List<? extends Thing> findBySignValue(
            final RecognizingLanguageCommunity community,
            final Pattern pattern,
            final String value,
            final PointInTime pointInTime) throws MagmaCoreException {

        final Set<Object> pointInTimeValues = pointInTime.value(HQDM.ENTITY_NAME);
        if (pointInTimeValues == null || pointInTimeValues.isEmpty()) {
            return List.of();
        }

        final LocalDateTime when = LocalDateTime.parse(pointInTimeValues.iterator().next().toString());

        final QueryResultList queryResultList = database
                .executeQuery(String.format(MagmaCoreServiceQueries.FIND_BY_SIGN_VALUE_QUERY,
                        value,
                        community.getId(),
                        pattern.getId()));

        // Filter by the pointInTime
        final QueryResultList queryResults = filterByPointInTime(when, queryResultList);

        return database.toTopObjects(queryResults);
    }

    /**
     * Find Things of a giver rdf:type and Kind and their signs that are of a particular pattern.
     *
     * @param type        IRI
     * @param kind        IRI
     * @param pattern     IRI
     * @param pointInTime {@link PointInTime}
     * @return a {@link List} of {@link Thing}
     */
    public List<? extends Thing> findByTypeKindAndSignPattern(
            final IRI type,
            final IRI kind,
            final IRI pattern,
            final PointInTime pointInTime) {

        final Set<Object> pointInTimeValues = pointInTime.value(HQDM.ENTITY_NAME);
        if (pointInTimeValues == null || pointInTimeValues.isEmpty()) {
            return List.of();
        }

        final LocalDateTime when = LocalDateTime.parse(pointInTimeValues.iterator().next().toString());

        final QueryResultList queryResultList = database
                .executeQuery(String.format(MagmaCoreServiceQueries.FIND_OBJECTS_BY_TYPE_AND_SIGN_PATTERN,
                        type, kind, pattern, type, kind, pattern));

        // Filter by the pointInTime
        final QueryResultList queryResults = filterByPointInTime(when, queryResultList);

        return database.toTopObjects(queryResults);
    }

    /**
     * Find Individuals with states participting in associations of a specified kind, their roles and
     * signs.
     *
     * @param kindOfAssociation {@link IRI}
     * @param pointInTime       {@link PointInTime}
     * @return {@link List} of {@link Thing}
     */
    public List<? extends Thing> findByKindOfAssociation(final IRI kindOfAssociation, final PointInTime pointInTime) {

        final Set<Object> pointInTimeValues = pointInTime.value(HQDM.ENTITY_NAME);
        if (pointInTimeValues == null || pointInTimeValues.isEmpty()) {
            return List.of();
        }

        final LocalDateTime when = LocalDateTime.parse(pointInTimeValues.iterator().next().toString());

        final QueryResultList queryResultList = database
                .executeQuery(String.format(MagmaCoreServiceQueries.FIND_BY_KIND_OF_ASSOCIATION,
                        kindOfAssociation, kindOfAssociation, kindOfAssociation));

        // Filter by the pointInTime
        final QueryResultList queryResults = filterByPointInTime(when, queryResultList);

        return database.toTopObjects(queryResults);

    }

    /**
     * Find an object by its {@link HQDM#ENTITY_NAME}.
     *
     * @param <T>        HQDM entity type.
     * @param entityName Entity name value to search for.
     * @return {@link Thing} that was found.
     * @throws RuntimeException If no or multiple results were found.
     */
    public <T extends Thing> T findByEntityName(final String entityName) {
        final List<Thing> searchResult = database.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, entityName);

        if (searchResult.size() == 1) {
            return (T) searchResult.get(0);
        } else if (searchResult.isEmpty()) {
            throw new RuntimeException("No entity found with name: " + entityName);
        } else {
            throw new RuntimeException("Multiple entities found with name: " + entityName);
        }
    }

    /**
     * Create a new {@link Thing} in the database.
     *
     * @param thing {@link Thing} to create.
     */
    public void create(final Thing thing) {
        database.create(thing);
    }

    /**
     * Update an existing {@link Thing} in the database.
     *
     * @param thing {@link Thing} to update.
     */
    public void update(final Thing thing) {
        database.update(thing);
    }

    /**
     * Apply a set of deletes then a set of creates to the database.
     *
     * @param deletes a {@link List} of {@link DbDeleteOperation}
     * @param creates a {@link List} of {@link DbCreateOperation}
     */
    public void update(final List<DbDeleteOperation> deletes, final List<DbCreateOperation> creates) {
        database.delete(deletes);
        database.create(creates);
    }

    /**
     * Get a {@link Thing} by its IRI.
     *
     * @param iri IRI of the thing.
     * @return {@link Thing} to get.
     */
    public Thing get(final IRI iri) {
        return database.get(iri);
    }

    /**
     * Get a {@link Thing} by its {@link IRI} in a transactional database.
     *
     * @param iri {@link IRI} of the {@link Thing}.
     * @return {@link Thing} to get.
     */
    public Thing getInTransaction(final IRI iri) {
        try {
            database.begin();
            final Thing result = database.get(iri);
            database.commit();
            return result;
        } catch (final Exception e) {
            database.abort();
            throw e;
        }
    }

    /**
     * Run a {@link Function} in a transaction.
     *
     * @param func {@link Function} to run.
     */
    public void runInTransaction(final Function<MagmaCoreService, MagmaCoreService> func) {
        try {
            database.begin();
            func.apply(this);
            database.commit();
        } catch (final Exception e) {
            database.abort();
            throw e;
        }
    }

    /**
     * Find entities by their names.
     *
     * @param entityNames {@link List} of entity names.
     * @return {@link Map} of {@link String} to {@link Thing}.
     */
    public Map<String, Thing> findByEntityNameInTransaction(final List<String> entityNames) {
        try {
            database.begin();
            final HashMap<String, Thing> result = new HashMap<String, Thing>();

            for (final String name : entityNames) {
                result.put(name, findByEntityName(name));
            }

            database.commit();

            return result;
        } catch (final Exception e) {
            database.abort();
            throw e;
        }
    }

    /**
     * Dump the database to TTL format.
     *
     * @param out a {@link PrintStream}
     */
    public void exportTtl(final PrintStream out) {
        database.dump(out, Lang.TTL);
    }

}
