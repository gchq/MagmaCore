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

package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.hqdm.iri.HQDM.ENTITY_NAME;
import static uk.gov.gchq.hqdm.iri.HQDM.HQDM;
import static uk.gov.gchq.hqdm.iri.RDFS.RDFS;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.REF_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.USER_BASE;

import java.util.List;

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;

/**
 * Example use-case scenario for {@link MagmaCoreJenaDatabase}.
 *
 * <p>
 * This example demo creates an in-memory {@link MagmaCoreJenaDatabase} populated with the
 * {@link ExampleDataObjects} as RDF triples.
 * </p>
 * <p>
 * The Jena dataset is transactional, so {@link MagmaCoreJenaDatabase#begin()} must be called before
 * any operations can be performed on the dataset, including queries. Once complete,
 * {@link MagmaCoreJenaDatabase#commit()} or {@link MagmaCoreJenaDatabase#abort()} should be called
 * to finish a transaction.
 * </p>
 * <p>
 * {@code PersonB1_Bob} can be queried for using the
 * {@link MagmaCoreJenaDatabase#findByPredicateIriAndStringValue(uk.gov.gchq.hqdm.iri.IRI, String)}
 * method. The resulting object(s) of this query are output to the command-line as RDF triples.
 * </p>
 *
 */
public final class JenaDatabaseDemo {

    /**
     * Run the in-memory Jena database example.
     */
    public void run() {
        // Instantiate new in-memory Jena database.
        final MagmaCoreJenaDatabase jenaDatabase = new MagmaCoreJenaDatabase();
        jenaDatabase.register(HQDM);
        jenaDatabase.register(RDFS);
        jenaDatabase.register(REF_BASE);
        jenaDatabase.register(USER_BASE);

        // Create set of example data objects.
        final List<Thing> objects = ExampleDataObjects.createDataObjects();

        // Add example data objects to dataset.
        jenaDatabase.begin();
        objects.forEach(jenaDatabase::create);
        jenaDatabase.commit();

        // Query database to check its populated.
        jenaDatabase.begin();
        final List<Thing> queryResults =
                jenaDatabase.findByPredicateIriAndStringValue(ENTITY_NAME, "PersonB1_Bob");
        jenaDatabase.abort();

        // Output results of query to console.
        queryResults.forEach(object -> System.out.println(object.toString()));

        jenaDatabase.begin();
        jenaDatabase.drop();
        jenaDatabase.commit();
        System.out.println("\n--- Jena Example End ---");
    }
}
