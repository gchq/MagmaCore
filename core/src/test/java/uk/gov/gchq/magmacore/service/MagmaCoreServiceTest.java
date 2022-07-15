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

import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class MagmaCoreServiceTest {

    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test");

    /**
     * Test that triples can be deleted.
     */
    @Test
    public void test() {
        final MagmaCoreJenaDatabase database = new MagmaCoreJenaDatabase();

        final IRI individualIri = new IRI(TEST_BASE, "individual");
        final Individual individual = SpatioTemporalExtentServices.createIndividual(individualIri.getIri());

        individual.addValue(HQDM.MEMBER_OF.getIri(), "classOfIndividual");

        database.begin();
        database.create(individual);
        database.commit();

        individual.removeValue(HQDM.MEMBER_OF.getIri(), "classOfIndividual");

        database.begin();
        database.update(individual);
        database.commit();

        database.begin();
        final Thing individualFromDb = database.get(individualIri);
        database.commit();

        assertNull(individualFromDb);
    }
}
