package uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Test the FindByKindOfAssociation service.
 */
public class MagmaCoreServiceFindByKindOfAssociationTest {

    @Test
    public void testFindSuccess() {
        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        AssociationPatternTestData.createAssociationPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime(new IRI("http://example.com/entity#now"));
        now.addStringValue(HQDM.ENTITY_NAME, Instant.now().toString());

        // Find the required Things by sign in a transaction.
        db.beginRead();
        final List<? extends Thing> participants = service
                .findByKindOfAssociation(AssociationPatternTestData.userAssociationKindIri, now);

        db.commit();

        // Assert the results are correct.
        assertNotNull(participants);
        assertEquals(4, participants.size());

        // Filter for person Things
        final List<? extends Thing> people = participants
                .stream()
                .filter(p -> p.hasThisValue(RDFS.RDF_TYPE, HQDM.PERSON))
                .toList();

        assertEquals(2, people.size());

        final IRI person1Iri = new IRI(AssociationPatternTestData.TEST_BASE, "person1");
        final IRI person2Iri = new IRI(AssociationPatternTestData.TEST_BASE, "person2");

        people.forEach(person -> {
            assertTrue(person1Iri.equals(person.getId()) || person2Iri.equals(person.getId()));

            // This query augments its object with HQDM.VALUE predicates for the current Sign values for the
            // object.

            final Set<Object> values = person.value(HQDM.VALUE_);

            assertNotNull(values);
            assertEquals(1, values.size());

            final Set<Object> names = person.value(HQDM.ENTITY_NAME);
            assertNotNull(names);
            assertEquals(1, names.size());
        });
    }

}
