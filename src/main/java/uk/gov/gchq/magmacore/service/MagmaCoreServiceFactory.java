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

package uk.gov.gchq.magmacore.service;

import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreRemoteSparqlDatabase;

/**
 * Factory for creating MagmaCoreService instances. This 
 * removes the need to expose MagmaCoreDatabase interface to
 * clients of the library.
 */
public class MagmaCoreServiceFactory {

    /**
     * Create a Jena database.
     *
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService createWithJenaDatabase() {
        return new MagmaCoreService(new MagmaCoreJenaDatabase());
    }

    /**
     * Create a Jena database.
     *
     * @param name a database name String
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService createWithJenaDatabase(final String name) {
        return new MagmaCoreService(new MagmaCoreJenaDatabase(name));
    }

    /**
     * Create a Jena database.
     *
     * @param db a {@link MagmaCoreJenaDatabase}
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService createWithJenaDatabase(final MagmaCoreJenaDatabase db) {
        return new MagmaCoreService(db);
    }

    /**
     * Attach to a remote SPARQL Endpoint.
     *
     * @param url the url {@link String}
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService attachRemoteSparqlEndpoint(final String url) {
        return new MagmaCoreService(new MagmaCoreRemoteSparqlDatabase(url));
    }
}
