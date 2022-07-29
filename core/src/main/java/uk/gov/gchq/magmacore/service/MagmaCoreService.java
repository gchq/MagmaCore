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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.jena.riot.Lang;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.model.Association;
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
import uk.gov.gchq.magmacore.internal.util.Predicates;
import uk.gov.gchq.magmacore.service.dto.ParticipantDetails;
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

        final IRI kindIri = new IRI(kind.getId());

        // Find the states of individual1 that are PARTICIPANTs in something.
        final List<Thing> statesOfIndividual1 = database
                .findByPredicateIri(HQDM.TEMPORAL_PART_OF, new IRI(individual1.getId())).stream()
                .filter(state -> state.hasValue(HQDM.PARTICIPANT_IN))
                .collect(Collectors.toList());

        // Find the states of individual2 that are PARTICIPANTs in something.
        final List<Thing> statesOfIndividual2 = database
                .findByPredicateIri(HQDM.TEMPORAL_PART_OF, new IRI(individual2.getId())).stream()
                .filter(state -> state.hasValue(HQDM.PARTICIPANT_IN))
                .collect(Collectors.toList());

        // Get the associations that each of the states participates in.
        final List<Thing> associations1 = getAssociationsOfKindForIndividual(statesOfIndividual1, kindIri);
        final List<Thing> associations2 = getAssociationsOfKindForIndividual(statesOfIndividual2, kindIri);

        // Find the associations that are common between the two lists.
        final List<String> intersection = associations1.stream()
                .distinct()
                .filter(associations2::contains)
                // Make sure the associations are valid at the requested PointInTime.
                .filter(Predicates.isValidAtPointInTime(database, pointInTime))
                // Get the Association IDs and collect to a List
                .map(a -> a.getId())
                .collect(Collectors.toList());

        // Process all of the participants.
        final List<Thing> allParticipants = new ArrayList<>();
        allParticipants.addAll(statesOfIndividual1);
        allParticipants.addAll(statesOfIndividual2);

        return allParticipants
                .stream()
                // Filter to only those in the common associations.
                .filter(p -> {
                    final String id = ((IRI) p.value(HQDM.PARTICIPANT_IN).iterator().next()).getIri();
                    return intersection.contains(id);
                })
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
     * Find the {@link Association} of the specified {@link KindOfAssociation} from the {@link Thing}
     * provided.
     *
     * @param statesOfIndividual {@link List} of {@link Thing}
     * @param kindIri            the {@link KindOfAssociation}
     * @return a {@link List} of {@link Thing}
     */
    private List<Thing> getAssociationsOfKindForIndividual(final List<Thing> statesOfIndividual, final IRI kindIri) {
        // Find the associations for the states that are of the right kind.
        return statesOfIndividual
                .stream()
                // Get the association IDs and gather them to a single list
                .map(state -> state.value(HQDM.PARTICIPANT_IN))
                .reduce(new HashSet<Object>(), (acc, iris) -> {
                    acc.addAll(iris);
                    return acc;
                })
                .stream()
                // Get the Associations using the IRIs and filter for the required KindOfAssociation
                .map(iri -> database.get((IRI) iri))
                .filter(association -> association.hasThisValue(HQDM.MEMBER_OF_KIND, kindIri))
                .collect(Collectors.toList());
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
     * @return {@link Set} of {@link Thing} represented by the value.
     * @throws MagmaCoreException if the number of {@link RepresentationByPattern} found is not 1
     */
    public Set<? extends Thing> findBySignValue(
            final RecognizingLanguageCommunity community,
            final Pattern pattern,
            final String value,
            final PointInTime pointInTime) throws MagmaCoreException {

        // Find the RepresentationByPattern
        final List<Thing> repByPatternThings = database.findByPredicateIri(HQDM.CONSISTS_OF_BY_CLASS,
                new IRI(pattern.getId()));
        if (repByPatternThings.size() != 1) {
            throw new MagmaCoreException(
                    String.format("Expected 1 RepresentationByPattern for Pattern %s, but found %s", pattern.getId(),
                            repByPatternThings.size()));
        }

        final IRI repByPatternIri = new IRI(repByPatternThings.get(0).getId());

        // Find the Things with the given value and filter out anything that isn't a sign.
        final Stream<Thing> signs = database.findByPredicateIriAndStringValue(HQDM.VALUE_, value)
                .stream()
                .filter(Predicates.isSign);

        // Find the states of these signs
        final List<Thing> signStates = signs
                .map(sign -> database.findByPredicateIri(HQDM.TEMPORAL_PART_OF, new IRI(sign.getId())))
                .reduce(new ArrayList<>(), (accumulator, things) -> {
                    accumulator.addAll(things);
                    return accumulator;
                });

        // Map the states to RepresentationBySign objects
        final Stream<Thing> repBySignThings = signStates
                .stream()
                .map(signState -> signState.value(HQDM.PARTICIPANT_IN))
                .reduce(new HashSet<Object>(), (accumulator, things) -> {
                    accumulator.addAll(things);
                    return accumulator;
                })
                .stream()
                .map(repBySignIri -> database.get((IRI) repBySignIri))

                // Filter to those with the correct RepresentationByPattern and RecognizingLanguageCommunity
                .filter(repBySign -> community.hasThisValue(HQDM.PARTICIPANT_IN,
                        new IRI(repBySign.getId())) && repBySign.hasThisValue(HQDM.MEMBER_OF_, repByPatternIri))

                // Filter to those valid at the specified PointInTime.
                .filter(Predicates.isValidAtPointInTime(database, pointInTime));

        // Map the RepresentationBySign Things to the Things they represent.
        return repBySignThings.map(repBySign -> repBySign.value(HQDM.REPRESENTS))
                .reduce(new HashSet<Object>(), (accumulator, things) -> {
                    accumulator.addAll(things);
                    return accumulator;
                })
                .stream()
                .map(thingId -> database.get((IRI) thingId))
                .collect(Collectors.toSet());

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
