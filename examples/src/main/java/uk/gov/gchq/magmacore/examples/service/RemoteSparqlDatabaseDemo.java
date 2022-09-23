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

package uk.gov.gchq.magmacore.examples.service;

import static uk.gov.gchq.magmacore.examples.util.DemoUtils.populateExampleData;

import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Example use-case scenario for using Magma Core with a remote SPARQL database connection.
 */
public final class RemoteSparqlDatabaseDemo {

    /**
     * Constructs a RemoteSparqlDatabaseDemo with a remote SPARQL server connection.
     *
     * @param args Application arguments.
     */
    public static void main(final String[] args) {
        final MagmaCoreService mcService = MagmaCoreServiceFactory
                .attachRemoteSparqlEndpoint("http://localhost:3330/tdb");
        populateExampleData(mcService);
    }
}
