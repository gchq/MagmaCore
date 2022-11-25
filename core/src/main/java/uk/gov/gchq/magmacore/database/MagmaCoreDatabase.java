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

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import org.apache.jena.riot.Lang;

import uk.gov.gchq.magmacore.database.query.QueryResultList;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbDeleteOperation;

/**
 * Interface defining CRUD operations and generic queries for Magma Core data collections.
 */
public interface MagmaCoreDatabase {

    /**
     * Start a transaction in READ mode and which will switch to WRITE if an update is attempted but
     * only if no intermediate transaction has performed an update.
     */
    void begin();

    /**
     * Commit a transaction - Finish the current transaction and make any changes permanent (if a
     * "write" transaction).
     */
    void commit();

    /**
     * Abort a transaction - Finish the transaction and undo any changes (if a "write" transaction).
     */
    void abort();

    /**
     * Drop all data from the dataset.
     */
    void drop();

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
     * Apply a set of creates to the database.
     *
     * @param creates a {@link List} of {@link DbDeleteOperation}
     */
    void create(List<DbCreateOperation> creates);

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
     * Apply a set of deletes to the database.
     *
     * @param deletes a {@link List} of {@link DbDeleteOperation}
     */
    void delete(List<DbDeleteOperation> deletes);

    /**
     * Find object(s) that have a specific object associated with them.
     *
     * @param predicateIri IRI of the predicate being queried.
     * @param objectIri    IRI of the object to match.
     * @return The {@link Thing}(s) found.
     */
    List<Thing> findByPredicateIri(IRI predicateIri, IRI objectIri);

    /**
     * Find object(s) that have a specific HQDM-defined predication.
     *
     * @param predicateIri IRI of the HQDM relationship type being queried.
     * @return The {@link Thing}(s) found.
     */
    List<Thing> findByPredicateIriOnly(IRI predicateIri);

    /**
     * Find object(s) that have a specific value attribute associated with them.
     *
     * @param predicateIri IRI of the predicate being queried.
     * @param value        Object to match.
     * @return The {@link Thing}(s) found.
     */
    List<Thing> findByPredicateIriAndValue(IRI predicateIri, Object value);

    /**
     * Find object(s) that have a specific string-value attribute associated with them.
     *
     * @param predicateIri IRI of the predicate being queried.
     * @param value        Case-insensitive string to match.
     * @return The {@link Thing}(s).
     */
    List<Thing> findByPredicateIriAndStringCaseInsensitive(IRI predicateIri, String value);

    /**
     * Dump the contents of the collection as text.
     *
     * @param out Output stream to dump to.
     */
    void dump(PrintStream out);

    /**
     * Write the database as TTL using the {@link PrintStream} and {@link org.apache.jena.riot.Lang}.
     *
     * @param out      a {@link PrintStream}
     * @param language a {@link Lang}
     */
    void dump(final PrintStream out, final Lang language);

    /**
     * Import data into the model.
     *
     * @param in       {@link InputStream} to read from.
     * @param language RDF language syntax to output data as.
     */
    void load(final InputStream in, final Lang language);

    /**
     * Perform a SPARQL query on the dataset.
     *
     * @param sparqlQueryString SPARQL query to execute.
     * @return Results of the query.
     */
    QueryResultList executeQuery(final String sparqlQueryString);

    /**
     * Convert a {@link QueryResultList} to a {@link List} of {@link Thing}.
     *
     * @param queryResultsList {@link QueryResultList}
     * @return a {@link List} of {@link Thing}
     */
    List<Thing> toTopObjects(final QueryResultList queryResultsList);

    /**
     * Execute a CONSTRUCT query.
     *
     * @param query a CONSTRUCT query {@link String}
     * @return a {@link List} of {@link Thing}
     */
    List<Thing> executeConstruct(final String query);
}
