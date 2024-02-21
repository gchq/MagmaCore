package uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Test the FindByTypeKindAndSignPattern service.
 */
public class MagmaCoreServiceFindByTypeKindAndSignPatternTest {

    @Test
    public void testFindSuccess() {
        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();

        SignPatternTestData.createSignPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime(new IRI("http://example.com/entity#now"));
        now.addStringValue(HQDM.ENTITY_NAME, Instant.now().toString());

        // Find the required Things by sign in a transaction.
        db.beginRead();
        final List<? extends Thing> things = service.findByTypeKindAndSignPattern(
                HQDM.PERSON,
                SignPatternTestData.kindOfPersonIri,
                SignPatternTestData.pattern1.getId(),
                now);

        db.commit();

        // Assert the results are correct.
        assertNotNull(things);
        assertEquals(1, things.size());

        final Thing person = things.get(0);
        final IRI personIri = new IRI(SignPatternTestData.TEST_BASE, "person1");
        assertEquals(personIri, person.getId());

        // This query augments its object with HQDM.VALUE predicates for the current Sign values for the
        // object.
        final Set<Object> values = person.values(HQDM.VALUE_);
        assertNotNull(values);
        assertEquals(1, values.size());
        assertEquals("person1", values.iterator().next().toString());
    }
}
