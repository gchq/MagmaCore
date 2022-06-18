package  uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;

/**
 * Check that {@link DbTransformation} works correctly.
 *
 * */
public class DbTransformationTest {

    /**
     * Test that multiple DbChangeSets can be applied as a DbTransformation, inverted, and undone.
     *
     * */
    @Test
    public void testApplyAndInvert() {

        // Create operations to add an object with dummy values.
        final var changes1 = new DbChangeSet(Set.of(), Set.of(
            new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.ABSTRACT_OBJECT, "value"),
            new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.MEMBER_OF, "class1"),
            new DbCreateOperation(HQDM.ABSTRACT_OBJECT, HQDM.PART_OF_POSSIBLE_WORLD, "a world")
        ));

        final var changes2 = new DbChangeSet(Set.of(), Set.of(
            new DbCreateOperation(HQDM.PERSON, HQDM.ENTITY_NAME, "Trillian"),
            new DbCreateOperation(HQDM.PERSON, HQDM.MEMBER_OF, "class2"),
            new DbCreateOperation(HQDM.PERSON, HQDM.PART_OF_POSSIBLE_WORLD, "another world")
        ));

        final var transformation = new DbTransformation(List.of(
            changes1,
            changes2
        ));

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithObjectDatabase();

        // Apply the operations.
        transformation.apply(mcService);

        // Find the thing we just created and assert values are present.
        final var thing1 = mcService.get(HQDM.ABSTRACT_OBJECT);

        assertNotNull(thing1);
        assertTrue(thing1.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertTrue(thing1.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertTrue(thing1.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        final var thing2 = mcService.get(HQDM.PERSON);

        assertNotNull(thing2);
        assertTrue(thing2.hasThisValue(HQDM.ENTITY_NAME.getIri(), "Trillian"));
        assertTrue(thing2.hasThisValue(HQDM.MEMBER_OF.getIri(), "class2"));
        assertTrue(thing2.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "another world"));

        // Invert the operations, apply them in reverse order and assert they are no longer present.
        transformation.invert().apply(mcService);

        assertFalse(thing1.hasThisValue(HQDM.ABSTRACT_OBJECT.getIri(), "value"));
        assertFalse(thing1.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertFalse(thing1.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        assertFalse(thing2.hasThisValue(HQDM.PERSON.getIri(), "Trillian"));
        assertFalse(thing2.hasThisValue(HQDM.MEMBER_OF.getIri(), "class2"));
        assertFalse(thing2.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "another world"));
    }
}
