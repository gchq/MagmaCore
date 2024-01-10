package uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.transformation.DbTransformation;

/**
 * Test the CreateDbTransformation service.
 */
public class MagmaCoreServiceCreateDbTransformationTest {

    private static final String TEST_IRI = "http://example.com/test#newThing";
    private static final String TEST_ENTITY_NAME = "TEST ENTITY";

    /**
     * Create a {@link Thing}, convert it to a {@link DbTransformation} and use it to persist the
     * {@link Thing}. Then find the {@link Thing} and check that it matches the original.
     */
    @Test
    public void testCreateSuccess() {
        // Create an in-memory database.
        final MagmaCoreService svc = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Create a new Thing
        final IRI iri = new IRI(TEST_IRI);
        final Thing newThing = SpatioTemporalExtentServices.createThing(iri);
        newThing.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        newThing.addValue(HQDM.ENTITY_NAME, TEST_ENTITY_NAME);

        // Convert the new Thing to a DbTransformation and use it to persist the Thing.
        final DbTransformation transformation = svc.createDbTransformation(List.of(newThing));
        svc.runInTransaction(transformation);

        // Retrieve the Thing and make sure it matches the original.
        final Map<String, Thing> foundThings = svc.findByEntityNameInTransaction(List.of(TEST_ENTITY_NAME));

        assertTrue(foundThings.containsKey(TEST_ENTITY_NAME));
        final Thing found = foundThings.get(TEST_ENTITY_NAME);
        assertNotNull(found);
        assertEquals(TEST_IRI, found.getId().getIri());
    }

}
