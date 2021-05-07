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

package uk.gov.gchq.magmacore;

import org.apache.jena.fuseki.main.FusekiServer;
import org.apache.jena.fuseki.system.FusekiLogging;
import org.apache.jena.query.Dataset;

import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;

/**
 * 
 */
public final class FusekiService {

    /**
     * 
     */
    public void run() {
        // Create/connect to persistant TDB.
        final MagmaCoreJenaDatabase tdb = new MagmaCoreJenaDatabase("tdb");

        // If TDB is not already populated create set of example data objects to
        // store in TDB.
        tdb.begin();
        if (tdb.getDataset().isEmpty()) {
            // Build example data objects Dataset.
            final Dataset objects = ExampleDataObjects.buildDataset();

            // Add example objects to default model in persistent dataset.
            tdb.getDataset().getDefaultModel().add(objects.getDefaultModel());
            tdb.commit();
        } else {
            tdb.abort();
        }

        // Build and start Fuseki server.
        final FusekiServer server = FusekiServer
                .create()
                .port(3332)
                .add("/tdb", tdb.getDataset(), true).build();
        FusekiLogging.setLogging();
        server.start();
    }
}
