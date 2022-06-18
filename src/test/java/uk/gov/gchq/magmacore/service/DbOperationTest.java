package  uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.exception.DbTransformationException;

/**
 * Check that {@link DbCreateOperation} and {@link DbDeleteOperation} work correctly.
 *
 * */
public class DbOperationTest {

    /**
     * Test that DbCreateOperations can be applied to a database and can also be
     * inverted and used to undo the {@link DbCreateOperation}.
     * */
    @Test
    public void testSingleCreateAndDelete() {

        // Create an operation to add an object with dummy values.
        final var op = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.ABSTRACT_OBJECT, "value");

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithObjectDatabase();

        // Apply the operation.
        op.apply(mcService);

        // Find the thing we just created and assert it's presence.
        final var thing = mcService.get(HQDM.ABSTRACT_OBJECT);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));

        // Invert the operation and assert that it is no longer present.
        DbCreateOperation.invert(op).apply(mcService);

        assertFalse(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
    }

    /**
     * Test that multiple DbCreateOperations can be applied to a database and can also be
     * inverted and used to undo the {@link DbCreateOperation}.
     * */
    @Test
    public void testMultipleCreateAndDelete() {

        // Create operations to add an object with dummy values.
        final var op1 = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.ABSTRACT_OBJECT, "value");
        final var op2 = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1");
        final var op3 = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.PART_OF_POSSIBLE_WORLD, "a world");

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithObjectDatabase();

        // Apply the operations.
        op1.apply(mcService);
        op2.apply(mcService);
        op3.apply(mcService);

        // Find the thing we just created and assert values are present.
        final var thing = mcService.get(HQDM.ABSTRACT_OBJECT);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertTrue(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertTrue(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        // Invert the operations, apply them in reverse order and assert they are no longer present.
        DbCreateOperation.invert(op3).apply(mcService);
        DbCreateOperation.invert(op2).apply(mcService);
        DbCreateOperation.invert(op1).apply(mcService);

        assertFalse(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertFalse(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertFalse(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));
    }

    /**
     * Test that we get an exception when trying to create something that already exists.
     *
     * */
    @Test(expected = DbTransformationException.class)
    public void testCreateWhenAlreadyPresent() {

        // Create an operation to add an object with dummy values.
        final var op = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.ABSTRACT_OBJECT, "value");

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithObjectDatabase();

        // Apply the operation twice, the second should throw an exception.
        op.apply(mcService);
        op.apply(mcService);
    }

    /**
     * Test that we get an exception when trying to delete something that does not exist.
     *
     * */
    @Test(expected = DbTransformationException.class)
    public void testDeleteWhenNotPresent() {

        // Create an operation to add an object with dummy values.
        final var op = new DbDeleteOperation(HQDM.ABSTRACT_OBJECT, HQDM.ABSTRACT_OBJECT, "value");

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithObjectDatabase();

        // Apply the operation, it should throw an exception.
        op.apply(mcService);
    }

    /**
     * Test the equals method for {@link DbCreateOperation}.
     *
     * */
    @Test
    public void testDbCreateEquals() {

        final var op1 = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1");
        final var op2 = new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1");

        assertTrue(op1.equals(op2));
        assertEquals(op1.hashCode(), op2.hashCode());
    }

    /**
     * Test the equals method for {@link DbDeleteOperation}.
     *
     * */
    @Test
    public void testDbDeleteEquals() {

        final var op1 = new DbDeleteOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1");
        final var op2 = new DbDeleteOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1");

        assertTrue(op1.equals(op2));
        assertEquals(op1.hashCode(), op2.hashCode());
    }
}
