package  uk.gov.gchq.magmacore.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uk.gov.gchq.hqdm.iri.HQDM;
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
        final var db = new MagmaCoreObjectDatabase();

        // Apply the operation.
        op.apply(db);

        // Find the thing we just created and assert it's presence.
        final var thing = db.get(HQDM.ABSTRACT_OBJECT);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));

        // Invert the operation and assert that it is no longer present.
        DbCreateOperation.invert(op).apply(db);

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
        final var db = new MagmaCoreObjectDatabase();

        // Apply the operations.
        op1.apply(db);
        op2.apply(db);
        op3.apply(db);

        // Find the thing we just created and assert values are present.
        final var thing = db.get(HQDM.ABSTRACT_OBJECT);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertTrue(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertTrue(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        // Invert the operations, apply them in reverse order and assert they are no longer present.
        DbCreateOperation.invert(op3).apply(db);
        DbCreateOperation.invert(op2).apply(db);
        DbCreateOperation.invert(op1).apply(db);

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
        final var db = new MagmaCoreObjectDatabase();

        // Apply the operation twice, the second should throw an exception.
        op.apply(db);
        op.apply(db);
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
        final var db = new MagmaCoreObjectDatabase();

        // Apply the operation, it should throw an exception.
        op.apply(db);
    }
}
