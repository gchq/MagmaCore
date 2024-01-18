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
 * Check that {@link DbTransformation} works correctly.
 */
public class DbTransformationTest {

    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test");

    /**
     * Test that multiple DbChangeSets can be applied as a DbTransformation, inverted, and undone.
     */
    @Test
    public void testApplyAndInvert() {
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        final IRI individualIri = new IRI(TEST_BASE, "individual");
        final IRI personIri = new IRI(TEST_BASE, "person");
        final IRI classOfIndividualIri = new IRI(TEST_BASE, "classOfIndividual");
        final IRI classOfPersonIri = new IRI(TEST_BASE, "classOfPerson");
        final IRI possibleWorldIri = new IRI(TEST_BASE, "possible_world");

        // Create operations to add an object with dummy values.
        final DbChangeSet createIndividual = new DbChangeSet(List.of(),
                List.of(new DbCreateOperation(individualIri, RDFS.RDF_TYPE, HQDM.INDIVIDUAL),
                        new DbCreateOperation(individualIri, HQDM.MEMBER_OF, classOfIndividualIri),
                        new DbCreateOperation(individualIri, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri)));

        final DbChangeSet createPerson = new DbChangeSet(List.of(),
                List.of(new DbCreateOperation(personIri, RDFS.RDF_TYPE, HQDM.PERSON),
                        new DbCreateOperation(personIri, HQDM.MEMBER_OF, classOfPersonIri),
                        new DbCreateOperation(personIri, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri)));

        final DbTransformation transformation = new DbTransformation(List.of(createIndividual, createPerson));

        // Apply the operations.
        mcService.runInWriteTransaction(transformation);

        // Find the individual we just created and assert values are present.
        final Thing individual = mcService.getInTransaction(individualIri);

        assertNotNull(individual);
        assertTrue(individual.hasThisValue(RDFS.RDF_TYPE, HQDM.INDIVIDUAL));
        assertTrue(individual.hasThisValue(HQDM.MEMBER_OF, classOfIndividualIri));
        assertTrue(individual.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri));

        final Thing person = mcService.getInTransaction(personIri);

        assertNotNull(person);
        assertTrue(person.hasThisValue(RDFS.RDF_TYPE, HQDM.PERSON));
        assertTrue(person.hasThisValue(HQDM.MEMBER_OF, classOfPersonIri));
        assertTrue(person.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri));

        // Invert the operations, apply them in reverse order and assert they are no longer present.
        mcService.runInWriteTransaction(transformation.invert());

        assertNull(mcService.getInTransaction(individualIri));
        assertNull(mcService.getInTransaction(personIri));
    }
}
