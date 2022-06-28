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

package  uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;

/**
 * Check that {@link DbChangeSet} works correctly.
 */
public class DbChangeSetTest {

    // Dummy IRI for testing.
    private static final String TEST_IRI = "http://example.com/test#test";

    /**
     * Test that {@link DbChangeSet} can be applied, inverted, and undone.
     *
     * */
    @Test
    public void testApplyAndInvert() {

        final var iri = new IRI(TEST_IRI);

        // Create operations to add an object with dummy values.
        final var changes = new DbChangeSet(List.of(), List.of(
            new DbCreateOperation(iri, RDFS.RDF_TYPE, HQDM.INDIVIDUAL.getIri()),
            new DbCreateOperation(iri, HQDM.MEMBER_OF, "class1"),
            new DbCreateOperation(iri, HQDM.PART_OF_POSSIBLE_WORLD, "a world")
        ));

        // Create a database to be updated.
        final var mcService = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Apply the operations.
        mcService.runInTransaction(changes);

        // Find the thing we just created and assert values are present.
        final var thing = mcService.getInTransaction(iri);

        assertNotNull(thing);
        assertTrue(thing.hasThisValue(RDFS.RDF_TYPE.getIri(), HQDM.INDIVIDUAL.getIri()));
        assertTrue(thing.hasThisValue(HQDM.MEMBER_OF.getIri(), "class1"));
        assertTrue(thing.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD.getIri(), "a world"));

        // Invert the operations, apply them in reverse order and assert they are no longer present.
        mcService.runInTransaction(DbChangeSet.invert(changes));

        final var thingFromDb = mcService.getInTransaction(iri);
        assertNull(thingFromDb);
    }
}
