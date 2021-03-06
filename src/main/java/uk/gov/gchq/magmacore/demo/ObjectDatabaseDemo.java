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

import java.util.List;

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreObjectDatabase;

/**
 * Example use-case scenario for {@link MagmaCoreObjectDatabase}.
 *
 * <p>
 * This example demo creates an in-memory {@link MagmaCoreObjectDatabase} populated with the
 * {@link ExampleDataObjects} as HQDM Java objects.
 * </p>
 * <p>
 * {@code PersonB1_Bob} can be queried for using the
 * {@link MagmaCoreObjectDatabase#findByPredicateIriAndStringValue(uk.gov.gchq.hqdm.iri.IRI, String)}
 * method. The resulting object(s) of this query are output to the command-line as RDF triples.
 * </p>
 */
public final class ObjectDatabaseDemo {

    /**
     * Run the in-memory Object Database example.
     */
    public void run() {
        final MagmaCoreObjectDatabase objectDatabase = new MagmaCoreObjectDatabase();

        // Create set of example data objects.
        final List<Thing> objects = ExampleDataObjects.createDataObjects();

        // Add example data objects to dataset.
        objects.forEach(objectDatabase::create);

        // Query database to check it's populated.
        final List<Thing> queryResults =
                objectDatabase.findByPredicateIriAndStringValue(ENTITY_NAME, "PersonB1_Bob");

        // Output results of query to console.
        queryResults.forEach(object -> System.out.println(object.toString()));

        System.out.println("\n--- Object Example End ---");
    }
}
