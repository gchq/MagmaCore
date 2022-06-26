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

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Example use-case scenario for the {@link uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase}
 *
 * <p>
 * This example demo creates an in-memory {@link MagmaCoreJenaDatabase} populated with the
 * {@link ExampleDataObjects} as RDF triples.
 * </p>
 * <p>
 * {@code PersonB1_Bob} can be queried for using the `findByEntityName` method.
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
        final var mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Add example data objects to dataset.
        ExampleDataObjects.populateExampleData(mcService);

        // Query database to check its populated.
        final Thing queryResults = mcService.findByEntityName("PersonB1_Bob");

        // Output results of query to console.
        System.out.println(queryResults);

        System.out.println("\n--- Jena Example End ---");
    }
}
