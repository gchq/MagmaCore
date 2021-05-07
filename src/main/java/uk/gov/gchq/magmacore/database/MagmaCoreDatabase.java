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

package uk.gov.gchq.magmacore.database;

import java.io.PrintStream;
import java.util.List;

import uk.gov.gchq.hqdm.iri.HqdmIri;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Thing;

/**
 * Interface defining CRUD operations and generic queries for Magma Core data collections.
 */
public interface MagmaCoreDatabase {

    // Data operations
    /**
     * Get an object from the collection.
     *
     * @param iri IRI of the object to get.
     * @return The fetched HQDM object.
     */
    Thing get(IRI iri);

    /**
     * Add an entity to the collection.
     *
     * @param object the HQDM-defined object to add.
     */
    void create(Thing object);

    /**
     * Update an existing entity within the collection
     *
     * @param object the HQDM object being updated.
     */
    void update(Thing object);

    /**
     * Delete an entity from the collection.
     *
     * @param object
     */
    void delete(Thing object);

    // Queries
    /**
     * Find an object by its predicate IRI.
     *
     * @param iri
     * @param object
     * @return the object(s)
     */
    List<Thing> findByPredicateIri(IRI iri, IRI object);

    /**
     *
     * @param predicateIri
     * @return
     */
    List<Thing> findByPredicateIriOnly(HqdmIri predicateIri);

    /**
     *
     * @param predicateIri
     * @param value
     * @return
     */
    List<Thing> findByPredicateIriAndStringValue(IRI predicateIri, String value);

    /**
     *
     * @param predicateIri
     * @param value
     * @return
     */
    List<Thing> findByPredicateIriAndStringCaseInsensitive(IRI predicateIri, String value);

    // Utilities
    /**
     * Dump the contents of the collection as text.
     *
     * @param out Output stream to dump data to.
     */
    void dump(PrintStream out);
}
