package uk.gov.gchq.magmacore.examples.extensions;

import java.util.UUID;

import uk.gov.gchq.magmacore.examples.extensions.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.model.UkLimitedCompany;
import uk.gov.gchq.magmacore.examples.extensions.model.UkLimitedCompanyImpl;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Test the model extension provided by the model-extension-example module.
 *
 * <p>
 * Run this using mvn exec:java -Dexec.mainClass=uk.gov.gchq.magmacore.examples.extensions.ModelExtensionTest
 * </p>
 */
public class ModelExtensionTest {

    // Declare an IRI base for the data to be created.
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    /**
     * Main entry point.
     *
     * @param args a String array
     */
    public static void main(final String[] args) {
        //
        // Create a MagmaCoreService with an in-memory Apache Jena database.
        //
        final var mcs = MagmaCoreServiceFactory.createWithJenaDatabase();

        //
        // The entity will be a part of a dummy possible_world, we just use the
        // IRI rather than creating the possible_world for this example.
        //
        final var possibleWorldIri = new IRI(TEST_BASE, UUID.randomUUID().toString());

        //
        // Create an IRI for the entity we want to create, then create the entity.
        //
        final var entityIri = new IRI(TEST_BASE, UUID.randomUUID().toString());
        final Thing entity = new UkLimitedCompanyImpl(entityIri);

        //
        // Set the RDF_TYPE and add the entity as a `part_of_possible_world`.
        //
        entity.addValue(RDFS.RDF_TYPE, Constants.UK_LIMITED_COMPANY_IRI);
        entity.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        //
        // Persist the entity in the database.
        //
        mcs.runInWriteTransaction(svc -> {
            svc.create(entity);
            return svc;
        });

        //
        // Read the entity back and assert that it matches the original.
        //
        mcs.runInReadTransaction(svc -> {
            final var restoredEntity = svc.get(entityIri);

            if (restoredEntity == null) {
                System.err.println("restoredEntity should not be null.");
            } else if (!(restoredEntity instanceof UkLimitedCompany)) {
                System.err.println("restoredEntity should be an instanceof UkLimitedCompany.");
            } else if (!entity.equals(restoredEntity)) {
                System.err.println("restoredEntity should be equal to the original entity.");
            } else {
                System.out.println("Success.");
            }
            return svc;
        });
    }
}
