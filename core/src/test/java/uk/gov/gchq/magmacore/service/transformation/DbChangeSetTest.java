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

package uk.gov.gchq.magmacore.service.transformation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Check that {@link DbChangeSet} works correctly.
 */
public class DbChangeSetTest {

    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    /**
     * Test that {@link DbChangeSet} can be applied, inverted, and undone.
     */
    @Test
    public void testApplyAndInvert() {
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Create operations to add an object with dummy values.
        final IRI individualIri = new IRI(TEST_BASE, "individual");
        final IRI classOfIndividualIri = new IRI(TEST_BASE, "classOfIndividual");
        final IRI possibleWorldIri = new IRI(TEST_BASE, "possible_world");

        final DbChangeSet createIndividual = new DbChangeSet(List.of(),
                List.of(new DbCreateOperation(individualIri, RDFS.RDF_TYPE, HQDM.INDIVIDUAL),
                        new DbCreateOperation(individualIri, HQDM.MEMBER_OF, classOfIndividualIri),
                        new DbCreateOperation(individualIri, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri)));

        // Apply the operations to the dataset.
        mcService.runInTransaction(createIndividual);

        // Find the individual and assert values are present.
        final Thing individual = mcService.getInTransaction(individualIri);

        assertNotNull(individual);
        assertTrue(individual.hasThisValue(RDFS.RDF_TYPE, HQDM.INDIVIDUAL));
        assertTrue(individual.hasThisValue(HQDM.MEMBER_OF, classOfIndividualIri));
        assertTrue(individual.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri));

        // Invert the operations and apply them in reverse order.
        mcService.runInTransaction(DbChangeSet.invert(createIndividual));

        assertNull(mcService.getInTransaction(individualIri));
    }
}
