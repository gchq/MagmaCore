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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.internal.util.Predicates;

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
        final List<Thing> repByPatternThings = database.findByPredicateIriAndStringValue(HQDM.CONSISTS_OF_BY_CLASS,
                pattern.getId());
        if (repByPatternThings.size() != 1) {
            throw new MagmaCoreException(
                    String.format("Expected 1 RepresentationByPattern for Pattern %s, but found %s", pattern.getId(),
                            repByPatternThings.size()));
        }

        final String repByPatternId = repByPatternThings.get(0).getId();

        // Find the Things with the given value and filter out anything that isn't a sign.
        final Stream<Thing> signs = database.findByPredicateIriAndStringValue(HQDM.VALUE_, value)
                .stream()
                .filter(Predicates.isSign);

        // Find the states of these signs
        final List<Thing> signStates = signs
                .map(thing -> database.findByPredicateIriAndStringValue(HQDM.TEMPORAL_PART_OF, thing.getId()))
                .reduce(new ArrayList<>(), (accumulator, things) -> {
                    accumulator.addAll(things);
                    return accumulator;
                });

        // Map the states to RepresentationBySign objects
        final Stream<Thing> repBySignThings = signStates
                .stream()
                .map(signState -> signState.value(HQDM.PARTICIPANT_IN.getIri()))
                .reduce(new HashSet<Object>(), (accumulator, things) -> {
                    accumulator.addAll(things);
                    return accumulator;
                })
                .stream()
                .map(obj -> obj.toString())
                .map(repBySignId -> database.get(new IRI(repBySignId)))

                // Filter to those with the correct RepresentationByPattern and RecognizingLanguageCommunity
                .filter(repBySign -> community.hasThisValue(HQDM.PARTICIPANT_IN.getIri(),
                        repBySign.getId()) && repBySign.hasThisValue(HQDM.MEMBER_OF_.getIri(), repByPatternId))

                // Filter to those valid at the specified PointInTime.
                .filter(Predicates.isValidAtPointInTime(database, pointInTime));

        // Map the RepresentationBySign Things to the Things they represent.
        return repBySignThings.map(repBySign -> repBySign.value(HQDM.REPRESENTS.getIri()))
                .reduce(new HashSet<Object>(), (accumulator, things) -> {
                    accumulator.addAll(things);
                    return accumulator;
                })
                .stream()
                .map(obj -> obj.toString())
                .map(thingId -> database.get(new IRI(thingId)))
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
}
