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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;

import uk.gov.gchq.magmacore.hqdm.model.Thing;

/**
 * Check that {@link MagmaCoreService} can run a Data Integrity Check against a remote database.
 */
public class MagmaCoreServiceRemoteSparqlDataIntegrityCheckTest {

    /**
     * Test Data Integrity Checks for a remote sparql service.
     */
    @Ignore
    public void test() {
        final MagmaCoreService service = MagmaCoreServiceFactory
                .attachRemoteSparqlEndpoint("http://localhost:3030/testdb");
        final List<Thing> errors = service.verifyModel();

        if (errors.size() > 0) {
            System.out.println(errors);
        }
        assertTrue(errors.isEmpty());
    }

}
