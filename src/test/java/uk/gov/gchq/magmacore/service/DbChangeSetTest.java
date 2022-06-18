package  uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;

/**
 * Check that {@link DbChangeSet} works correctly.
 *
 * */
public class DbChangeSetTest {

    /**
     * Test that {@link DbChangeSet} can be applied, inverted, and undone.
     *
     * */
    @Test
    public void testApplyAndInvert() {

        // Create operations to add an object with dummy values.
        final var changes = new DbChangeSet(Set.of(), Set.of(
            new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.ABSTRACT_OBJECT, "value"),
            new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1"),
            new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.PART_OF_POSSIBLE_WORLD, "a world")
        ));

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithObjectDatabase();

        // Apply the operations.
        changes.apply(mcService);

        // Find the thing we just created and assert values are present.
        final var thing = mcService.get(HQDM.ABSTRACT_OBJECT);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertTrue(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertTrue(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        // Invert the operations, apply them in reverse order and assert they are no longer present.
        DbChangeSet.invert(changes).apply(mcService);

        assertFalse(thing.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertFalse(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertFalse(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));
    }
}
