package uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

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
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime("now");
        now.addValue(HQDM.ENTITY_NAME, LocalDateTime.now().toString());

        // Find the required Things by sign in a transaction.
        db.begin();
        final List<? extends Thing> things = service.findByTypeKindAndSignPattern(
                HQDM.PERSON,
                SignPatternTestData.kindOfPersonIri,
                new IRI(SignPatternTestData.pattern1.getId()),
                now);

        db.commit();

        // Assert the results are correct.
        assertNotNull(things);
        assertEquals(1, things.size());
        assertEquals(new IRI(SignPatternTestData.TEST_BASE, "person1").getIri(), things.get(0).getId());
    }
}
