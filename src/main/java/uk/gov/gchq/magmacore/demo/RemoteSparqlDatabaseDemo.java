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

import uk.gov.gchq.magmacore.database.MagmaCoreRemoteSparqlDatabase;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Example use-case scenario for using a {@link MagmaCoreRemoteSparqlDatabase} with a remote service.
 */
public final class RemoteSparqlDatabaseDemo {

    private final String url;

    public RemoteSparqlDatabaseDemo(final String url) {
      this.url = url;
    }

    /**
     * Run the demo.
     */
    public void run(final boolean populate) {
        final var mcService = MagmaCoreServiceFactory.attachRemoteSparqlEndpoint(url);

        if (populate) {
            ExampleDataObjects.populateExampleData(mcService);
        } 
    }

}
