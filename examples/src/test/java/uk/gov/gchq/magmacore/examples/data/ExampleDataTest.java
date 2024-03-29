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

package uk.gov.gchq.magmacore.examples.data;

import static org.junit.Assert.assertNotNull;
import static uk.gov.gchq.magmacore.examples.util.DemoUtils.populateExampleData;

import org.junit.Test;

import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;
import uk.gov.gchq.magmacore.service.transformation.DbTransformation;

/**
 * Exercise the {@link ExampleDataObjects} code during the build.
 */
public class ExampleDataTest {

    /**
     * Test the {@link ExampleDataObjects} code.
     */
    @Test
    public void testWithJenaDatabase() {
        final MagmaCoreService service = MagmaCoreServiceFactory.createWithJenaDatabase();
        final DbTransformation transformation = populateExampleData(service);

        assertNotNull(transformation);
    }
}
