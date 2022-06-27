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

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.hqdm.rdf.iri.IRI;

/**
 * Interface defining CRUD operations and generic queries for Magma Core data collections.
 */
public interface MagmaCoreDatabase {

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
     * @param object The HQDM-defined object to add.
     */
    void create(Thing object);

    /**
     * Update an existing entity within the collection.
     *
     * @param object The HQDM object being updated.
     */
    void update(Thing object);

    /**
     * Delete an entity from the collection.
     *
     * @param object Entity to delete.
     */
    void delete(Thing object);

    /**
     * Find object(s) that have a specific object associated with them.
     *
     * @param predicateIri IRI of the predicate being queried.
     * @param objectIri IRI of the object to match.
     * @return The object(s).
     */
    List<Thing> findByPredicateIri(IRI predicateIri, IRI objectIri);

    /**
     * Find object(s) that have a specific HQDM-defined predication.
     *
     * @param predicateIri IRI of the HQDM relationship type being queried.
     * @return The object(s).
     */
    List<Thing> findByPredicateIriOnly(HqdmIri predicateIri);

    /**
     * Find object(s) that have a specific case-sensitive string-value attribute associated with
     * them.
     *
     * @param predicateIri IRI of the predicate being queried.
     * @param value Case-sensitive string to match.
     * @return The object(s).
     */
    List<Thing> findByPredicateIriAndStringValue(IRI predicateIri, String value);

    /**
     * Find object(s) that have a specific string-value attribute associated with them.
     *
     * @param predicateIri IRI of the predicate being queried.
     * @param value Case-insensitive string to match.
     * @return The object(s).
     */
    List<Thing> findByPredicateIriAndStringCaseInsensitive(IRI predicateIri, String value);

    /**
     * Dump the contents of the collection as text.
     *
     * @param out Output stream to dump to.
     */
    void dump(PrintStream out);

    /**
     * Begin a writeable transaction initially in READ mode,
     * but in Jena it will switch to WRITE mode if updates are made.
    */
    void begin();

    /**
     * Abort the current transaction.
    */
    void abort();

    /**
     * Drop the entire database.
    */
    void drop();

    /**
     * Commit the current transaction.
    */
    void commit();

}
