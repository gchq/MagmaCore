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
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.exception.DbTransformationException;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Check that {@link DbCreateOperation} and {@link DbDeleteOperation} work correctly.
 */
public class DbOperationTest {

    // Dummy IRI for testing.
    private static final String TEST_IRI = "http://example.com/test#test";

    /**
     * Test that DbCreateOperations can be applied to a database and can also be inverted and used to
     * undo the {@link DbCreateOperation}.
     */
    @Test
    public void testCreateAndDelete() {

        // Create an operation to add an object with dummy values.
        final IRI iri = new IRI(TEST_IRI);
        final DbCreateOperation op1 = new DbCreateOperation(iri, RDFS.RDF_TYPE, HQDM.INDIVIDUAL.getIri());
        final DbCreateOperation op2 = new DbCreateOperation(iri, HQDM.MEMBER_OF, "class1");

        // Create a database to be updated.
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Apply the operation.
        mcService.runInTransaction(op1);
        mcService.runInTransaction(op2);

        // Find the thing we just created and assert it's presence.
        final Thing thing = mcService.getInTransaction(iri);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(RDFS.RDF_TYPE.getIri(), HQDM.INDIVIDUAL.getIri()));

        // Invert the operation and assert that it is no longer present.
        mcService.runInTransaction(DbCreateOperation.invert(op2));

        final Thing thingFromDb = mcService.getInTransaction(iri);
        assertFalse(thingFromDb.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
    }

    /**
     * Test that multiple DbCreateOperations can be applied to a database and can also be inverted and
     * used to undo the {@link DbCreateOperation}.
     */
    @Test
    public void testMultipleCreateAndDelete() {

        final IRI iri = new IRI(TEST_IRI);

        // Create operations to add an object with dummy values.
        final DbCreateOperation op1 = new DbCreateOperation(iri, RDFS.RDF_TYPE, HQDM.INDIVIDUAL.getIri());
        final DbCreateOperation op2 = new DbCreateOperation(iri, HQDM.MEMBER_OF, "class1");
        final DbCreateOperation op3 = new DbCreateOperation(iri, HQDM.PART_OF_POSSIBLE_WORLD, "a world");

        // Create a database to be updated.
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Apply the operations.
        mcService.runInTransaction(op1);
        mcService.runInTransaction(op2);
        mcService.runInTransaction(op3);

        // Find the thing we just created and assert values are present.
        final Thing thing = mcService.getInTransaction(iri);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(RDFS.RDF_TYPE.getIri(), HQDM.INDIVIDUAL.getIri()));
        assertTrue(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertTrue(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        // Invert two of the operations, apply them in reverse order and assert they are no longer present.
        mcService.runInTransaction(DbCreateOperation.invert(op3));
        mcService.runInTransaction(DbCreateOperation.invert(op2));
        mcService.runInTransaction(DbCreateOperation.invert(op1));

        final Thing thingFromDb = mcService.getInTransaction(iri);
        assertNull(thingFromDb);
    }

    /**
     * Test that we get an exception when trying to create something that already exists.
     */
    @Test(expected = DbTransformationException.class)
    public void testCreateWhenAlreadyPresent() {

        final IRI iri = new IRI(TEST_IRI);

        // Create an operation to add an object with dummy values.
        final DbCreateOperation op = new DbCreateOperation(iri, RDFS.RDF_TYPE, HQDM.INDIVIDUAL.getIri());

        // Create a database to be updated.
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Apply the operation twice, the second should throw an exception.
        mcService.runInTransaction(op);
        mcService.runInTransaction(op);
    }

    /**
     * Test that we get an exception when trying to delete something that does not exist.
     */
    @Test(expected = DbTransformationException.class)
    public void testDeleteWhenNotPresent() {

        final IRI iri = new IRI(TEST_IRI);

        // Create an operation to add an object with dummy values.
        final DbDeleteOperation op = new DbDeleteOperation(iri, HQDM.INDIVIDUAL, "value");

        // Create a database to be updated.
        final MagmaCoreService mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Apply the operation, it should throw an exception.
        mcService.runInTransaction(op);
    }

    /**
     * Test the equals method for {@link DbCreateOperation}.
     */
    @Test
    public void testDbCreateEquals() {

        final IRI iri = new IRI(TEST_IRI);

        final DbCreateOperation op1 = new DbCreateOperation(iri, HQDM.MEMBER_OF, "class1");
        final DbCreateOperation op2 = new DbCreateOperation(iri, HQDM.MEMBER_OF, "class1");

        assertTrue(op1.equals(op2));
        assertEquals(op1.hashCode(), op2.hashCode());
    }

    /**
     * Test the equals method for {@link DbDeleteOperation}.
     */
    @Test
    public void testDbDeleteEquals() {

        final IRI iri = new IRI(TEST_IRI);

        final DbDeleteOperation op1 = new DbDeleteOperation(iri, HQDM.MEMBER_OF, "class1");
        final DbDeleteOperation op2 = new DbDeleteOperation(iri, HQDM.MEMBER_OF, "class1");

        assertTrue(op1.equals(op2));
        assertEquals(op1.hashCode(), op2.hashCode());
    }
}
