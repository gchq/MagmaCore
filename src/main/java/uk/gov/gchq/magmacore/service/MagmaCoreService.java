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
 * Services exported by the MagmaCore module.
 */
public class MagmaCoreService {

    // The service operates on a database.
    private final MagmaCoreDatabase db;

    /**
     * Constructor that requires a {@link MagmaCoreDatabase}.
     *
     * @param db {@link MagmaCoreDatabase}
     */
    MagmaCoreService(final MagmaCoreDatabase db) {
        this.db = db;
    }

    /**
     * Find an object by its ENTITY_NAME.
     *
     * @param <T> the return type.
     * @param name the name {@link String} to search for.
     * @return the {@link Thing}that was found.
     * @throws RuntimeException if no or multiple results found.
     */
    public <T> T findByEntityName(final String name) {
        final var searchResult = db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, name);

        if (searchResult.size() == 1) {
            return (T) searchResult.get(0);
        } else if (searchResult.isEmpty()) {
            throw new RuntimeException("No entity found with name: " + name);
        } else {
            throw new RuntimeException("Multiple entities found with name: " + name);
        }
    }

    /**
     * Create a new Thing.
     *
     * @param thing {@link Thing}
     */
    public void create(final Thing thing) {
        db.create(thing);
    }

    /**
     * Update an existing {@link Thing}.
     *
     * @param thing {@link Thing}
     */
    public void update(final Thing thing) {
        db.update(thing);
    }

    /**
     * Get a {@link Thing} with the given {@link IRI}.
     *
     * @param iri {@link IRI}
     * @return {@link Thing}
     */
    public Thing get(final IRI iri) {
        return db.get(iri);
    }

    /**
     * Get a {@link Thing} with the given {@link IRI}.
     *
     * @param iri {@link IRI}
     * @return {@link Thing}
     */
    public Thing getInTransaction(final IRI iri) {
        try {
            db.begin();
            final var result = db.get(iri);
            db.commit();
            return result;
        } catch (final Exception e) {
            db.abort();
            throw e;
        }
    }

    /**
     * Run a function in a transaction.
     *
     * @param f the {@link Function} to run.
     */
    public void runInTransaction(final Function<MagmaCoreService, MagmaCoreService> f) {
        try {
            db.begin();
            f.apply(this);
            db.commit();
        } catch (final Exception e) {
            db.abort();
            throw e;
        }
    }

    /**
     * Find many entities by name.
     *
     * @param names a {@link List} of {@link String}
     * @return a {@link Map} of {@link String} to {@link Thing}
     */
    public Map<String, Thing> findByEntityNameInTransaction(final List<String> names) {
        try {
            db.begin();
            final var result = new HashMap<String, Thing>();

            for (final String name : names) {
                result.put(name, findByEntityName(name));
            }

            db.commit();

            return result;
        } catch (final Exception e) {
            db.abort();
            throw e;
        }
    }
}
