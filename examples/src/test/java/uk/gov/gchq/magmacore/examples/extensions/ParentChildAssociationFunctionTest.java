package uk.gov.gchq.magmacore.examples.extensions;

import java.util.UUID;

import org.junit.Test;

import uk.gov.gchq.magmacore.examples.extensions.ext.HqdmExtensionService;
import uk.gov.gchq.magmacore.examples.extensions.model.Child;
import uk.gov.gchq.magmacore.examples.extensions.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.model.Parent;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Tests for the ParentChildAssociationFunction class.
 */
public class ParentChildAssociationFunctionTest {
    
    // An IRI Base for the test entities.
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    // The ParentChildAssociation is in this extension module.
    private static final HqdmExtensionService ext = new HqdmExtensionService();

    // The persistence service.
    private static final MagmaCoreService svc = MagmaCoreServiceFactory.createWithJenaDatabase();

    /**
     * Demonstrate how to use the ParentChildAssociationFunction to create a 
     * fully populated and persisted ParentChildAssociation.
     */
    @Test
    public void testSuccessCase() {

        // Create a beginning event for the association and its participants.
        final PointInTime beginning = SpatioTemporalExtentServices.createPointInTime(randomIri());
        beginning.addStringValue(HQDM.VALUE_, "1967-04-04T10:15:30.00Z");

        // Create an ending event for the association and its participants.
        final PointInTime ending = SpatioTemporalExtentServices.createPointInTime(randomIri());
        ending.addStringValue(HQDM.VALUE_, "2040-12-03T10:15:30.00Z");

        // The entities will all be added to this PossibleWorld.
        final PossibleWorld possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(randomIri());

        // Create the Parent.
        final Parent parent = ext.createEntity(Constants.PARENT_TYPE_NAME, randomIri());
        
        // Create the Child.
        final Child child = ext.createEntity(Constants.CHILD_TYPE_NAME, randomIri());

        // Instantiate a ParentChildAssociationFunction and execute it n a write transaction.
        svc.runInWriteTransaction(new ParentChildAssociationFunction(
                possibleWorld,
                parent,
                child,
                beginning,
                ending));

        // Export the TTL for inspection.
        svc.exportTtl(System.out);

    }

    /**
     * Create a random IRI.
     *
     * @return {@link IRI}
     */
    private IRI randomIri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }
}
