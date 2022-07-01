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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;

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
     * Create a new Thing in the database.
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
     * Get a {@link Thing} by its IRI {@link IRI} in a transactional database.
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
