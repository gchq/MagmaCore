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
 * Factory for creating MagmaCoreService instances. This removes the need to expose
 * MagmaCoreDatabase interface to clients of the library.
 */
public class MagmaCoreServiceFactory {

    /**
     * Create a {@link MagmaCoreService} for a new {@link MagmaCoreJenaDatabase}.
     *
     * @return {@link MagmaCoreService}.
     */
    public static MagmaCoreService createWithJenaDatabase() {
        return new MagmaCoreService(new MagmaCoreJenaDatabase());
    }

    /**
     * Create a {@link MagmaCoreService} for a new {@link MagmaCoreJenaDatabase} with a remote Jena
     * server.
     *
     * @param location URL of the database.
     * @return {@link MagmaCoreService}.
     */
    public static MagmaCoreService createWithJenaDatabase(final String location) {
        return new MagmaCoreService(new MagmaCoreJenaDatabase(location));
    }

    /**
     * Create a {@link MagmaCoreService} for an existing {@link MagmaCoreJenaDatabase}.
     *
     * @param database A {@link MagmaCoreJenaDatabase}.
     * @return {@link MagmaCoreService}.
     */
    public static MagmaCoreService createWithJenaDatabase(final MagmaCoreJenaDatabase database) {
        return new MagmaCoreService(database);
    }

    /**
     * Create a {@link MagmaCoreService} for a new {@link MagmaCoreRemoteSparqlDatabase} with a SPARQL
     * server connection.
     *
     * @param serviceUrl URL of the SPARQL server.
     * @return {@link MagmaCoreService}.
     */
    public static MagmaCoreService attachRemoteSparqlEndpoint(final String serviceUrl) {
        return new MagmaCoreService(new MagmaCoreRemoteSparqlDatabase(serviceUrl));
    }
}
