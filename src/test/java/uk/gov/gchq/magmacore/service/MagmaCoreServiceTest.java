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

import static org.junit.Assert.assertNull;

import org.junit.Test;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class MagmaCoreServiceTest {

    // Dummy IRI for testing.
    private static final String TEST_IRI = "http://example.com/test#test";

    /**
     * Test that triples can be deleted.
     */
    @Test
    public void test() {
        final var iri = new IRI(TEST_IRI);

        final var db = new MagmaCoreJenaDatabase();

        final var thing = SpatioTemporalExtentServices.createIndividual(TEST_IRI);

        thing.addValue(HQDM.MEMBER_OF.getIri(), "class1");

        db.begin();
        db.create(thing);
        db.commit();

        thing.removeValue(HQDM.MEMBER_OF.getIri(), "class1");

        db.begin();
        db.update(thing);
        db.commit();

        db.begin();
        final var thingFromDb = db.get(iri);
        db.commit();

        assertNull(thingFromDb);
    }
}
