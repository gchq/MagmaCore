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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.exception.DbTransformationException;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Check that {@link DbCreateOperation} and {@link DbDeleteOperation} work correctly.
 */
public class DbOperationTest {

    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test");

    /**
     * Test that DbCreateOperations can be applied to a database and can also be inverted and used to
     * undo the {@link DbCreateOperation}.
     */
    @Test
    public void testCreateAndDelete() {
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Create an operation to add an object with dummy values.
        final IRI individualIri = new IRI(TEST_BASE, "individual");

        final DbCreateOperation createIndividual = new DbCreateOperation(individualIri, RDFS.RDF_TYPE,
                HQDM.INDIVIDUAL.getIri());
        final DbCreateOperation createIndividualMemberOf = new DbCreateOperation(individualIri, HQDM.MEMBER_OF,
                "classOfIndividual");

        // Apply the operations.
        mcService.runInTransaction(createIndividual);
        mcService.runInTransaction(createIndividualMemberOf);

        // Find the individual and assert it's presence.
        final Thing individual = mcService.getInTransaction(individualIri);

        assertNotNull(individual);
        assertTrue(individual.hasThisValue(RDFS.RDF_TYPE.getIri(), HQDM.INDIVIDUAL.getIri()));

        // Invert the operation and assert that it is no longer present.
        mcService.runInTransaction(DbCreateOperation.invert(createIndividualMemberOf));

        final Thing individualFromDb = mcService.getInTransaction(individualIri);
        assertFalse(individualFromDb.hasThisValue(HQDM.MEMBER_OF.getIri(), "classOfIndividual"));
    }

    /**
     * Test that multiple DbCreateOperations can be applied to a database and can also be inverted and
     * used to undo the {@link DbCreateOperation}.
     */
    @Test
    public void testMultipleCreateAndDelete() {
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        final IRI individualIri = new IRI(TEST_BASE, "individual");

        // Create operations to create an object.
        final DbCreateOperation createIndividual = new DbCreateOperation(individualIri, RDFS.RDF_TYPE,
                HQDM.INDIVIDUAL.getIri());
        final DbCreateOperation createIndividualMemberOf = new DbCreateOperation(individualIri, HQDM.MEMBER_OF,
                "classOfIndividual");
        final DbCreateOperation createIndividualPartOfPossibleWorld = new DbCreateOperation(individualIri,
                HQDM.PART_OF_POSSIBLE_WORLD, "possible world");

        // Apply the operations to the dataset.
        mcService.runInTransaction(createIndividual);
        mcService.runInTransaction(createIndividualMemberOf);
        mcService.runInTransaction(createIndividualPartOfPossibleWorld);

        // Find the thing we just created and assert values are present.
        final Thing individual = mcService.getInTransaction(individualIri);

        assertNotNull(individual);
        assertTrue(individual.hasThisValue(RDFS.RDF_TYPE.getIri(), HQDM.INDIVIDUAL.getIri()));
        assertTrue(individual.hasThisValue(HQDM.MEMBER_OF.getIri(), "classOfIndividual"));
        assertTrue(individual.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "possible world"));

        // Invert two of the operations, apply them in reverse order and assert they are no longer present.
        mcService.runInTransaction(DbCreateOperation.invert(createIndividualPartOfPossibleWorld));
        mcService.runInTransaction(DbCreateOperation.invert(createIndividualMemberOf));
        mcService.runInTransaction(DbCreateOperation.invert(createIndividual));

        final Thing individualFromDb = mcService.getInTransaction(individualIri);
        assertNull(individualFromDb);
    }

    /**
     * Test that we get an exception when trying to create something that already exists.
     */
    @Test(expected = DbTransformationException.class)
    public void testCreateWhenAlreadyPresent() {
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        final IRI individualIri = new IRI(TEST_BASE, "individual");

        // Create an operation to add an object with dummy values.
        final DbCreateOperation createIndividual = new DbCreateOperation(individualIri, RDFS.RDF_TYPE,
                HQDM.INDIVIDUAL.getIri());

        // Apply the operation twice, the second should throw an exception.
        mcService.runInTransaction(createIndividual);
        mcService.runInTransaction(createIndividual);
    }

    /**
     * Test that we get an exception when trying to delete something that does not exist.
     */
    @Test(expected = DbTransformationException.class)
    public void testDeleteWhenNotPresent() {
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        final IRI individualIri = new IRI(TEST_BASE, "individual");

        // Create an operation to add an object with dummy values.
        final DbDeleteOperation deleteIndividual = new DbDeleteOperation(individualIri, HQDM.INDIVIDUAL,
                "value not present");

        // Apply the operation, it should throw an exception.
        mcService.runInTransaction(deleteIndividual);
    }

    /**
     * Test the equals method for {@link DbCreateOperation}.
     */
    @Test
    public void testDbCreateEquals() {
        final IRI individualIri = new IRI(TEST_BASE, "individual");

        final DbCreateOperation createIndividual = new DbCreateOperation(individualIri, HQDM.MEMBER_OF,
                "classOfIndividual");
        final DbCreateOperation deleteIndividual = new DbCreateOperation(individualIri, HQDM.MEMBER_OF,
                "classOfIndividual");

        assertTrue(createIndividual.equals(deleteIndividual));
        assertEquals(createIndividual.hashCode(), deleteIndividual.hashCode());
    }

    /**
     * Test the equals method for {@link DbDeleteOperation}.
     */
    @Test
    public void testDbDeleteEquals() {
        final IRI individualIri = new IRI(TEST_BASE, "individual");

        final DbDeleteOperation deleteIndividual = new DbDeleteOperation(individualIri, HQDM.MEMBER_OF,
                "classOfIndividual");
        final DbDeleteOperation deleteAbsentIndividual = new DbDeleteOperation(individualIri, HQDM.MEMBER_OF,
                "classOfIndividual");

        assertTrue(deleteIndividual.equals(deleteAbsentIndividual));
        assertEquals(deleteIndividual.hashCode(), deleteAbsentIndividual.hashCode());
    }
}
