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

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.system.FusekiLogging;

import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * Example use-case scenario for hosting {@link MagmaCoreJenaDatabase} on a Fuseki server.
 *
 * <p>
 * The FusekiService class can be used to host in-memory or persistent Magma Core Jena Datasets over
 * HTTP using a Fuseki server.
 * </p>
 * <p>
 * By default, the Fuseki server is configured to run on localhost:3330, however this can be change
 * by setting the {@code port(int)}.
 * </p>
 * <p>
 * The Fuseki server can host either in-memory Datasets, or connected TDB stores. Datasets can be
 * added to the server using the {@code add(name, dataset)} method. Datasets are hosted at
 * {@code localhost:<port>/<name>}.
 * </p>
 */
public final class FusekiService {

    /**
     * Run the example Fuseki server.
     */
    public void run(final boolean populate) {
        // Create/connect to persistent TDB.
        final MagmaCoreJenaDatabase tdb = new MagmaCoreJenaDatabase("tdb");

        // If TDB is not already populated create set of example data objects to
        // store in TDB.
        tdb.begin();
        if (tdb.getDataset().isEmpty() && populate) {
            // Build example data objects Dataset.
            ExampleDataObjects.populateExampleData(new MagmaCoreService(tdb));

            tdb.commit();
        } else {
            tdb.abort();
        }
        // Build and start Fuseki server.
        FusekiLogging.setLogging();
        FusekiServer
            .create()
            .port(3330)
            .add("/tdb", tdb.getDataset(), true)
            .start();
    }
}
