package uk.gov.gchq.magmacore.examples.kinds;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfOrganization;
import uk.gov.gchq.magmacore.hqdm.model.KindOfOrganizationComponent;
import uk.gov.gchq.magmacore.hqdm.model.Organization;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.IndividualBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.KindOfOrganizationBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.KindOfOrganizationComponentBuilder;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Create a KindOfOrganization and make a Thing a member_of_kind of it, then persist the Thing and
 * make sure it is read back as an Organization.
 *
 * <p>
 * This demonstrates the implementation of instances of 'kinds' as new entity types.
 * </p>
 */
public class CreateOrganizationSubTypeUsingKinds {
    /*
     * A Base URL for the tests.
     */
    private static IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    /**
     * Unit test.
     */
    @Test
    public void test() {
        // ACME Ltd will be a part of this possibleWorld.
        final PossibleWorld possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(randomIri());

        // We need a kind of organization component for the kind of organization.
        final KindOfOrganizationComponent componentKind = new KindOfOrganizationComponentBuilder(randomIri())
                .build();

        // Create a new kind of organization to represent companies.
        // This will also make the new kind into a sub-type of organization.
        final KindOfOrganization companies = new KindOfOrganizationBuilder(randomIri())
                .has_Component_By_Class_M(componentKind)
                .build();

        // Create an Individual and make it a member_of_kind of the companies kind.
        // This will also mean that the individual is of type organization, via its kind.
        final Individual acmeLtd = new IndividualBuilder(randomIri())
                .member_Of_Kind(companies)
                .part_Of_Possible_World_M(possibleWorld)
                .build();

        // Persist everything.
        final MagmaCoreService service = MagmaCoreServiceFactory.createWithJenaDatabase();
        service.beginWrite();
        service.create(companies);
        service.create(acmeLtd);
        service.commit();

        // Read back the organization.
        service.beginRead();
        final Thing acmeFromDatabase = service.get(acmeLtd.getId());
        service.commit();

        // Confirm that the entity created as an individual is now actually an organization as well.
        assertTrue(acmeFromDatabase instanceof Organization);
    }

    /**
     * Utility method.
     *
     * @return IRI
     */
    private static IRI randomIri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }
}
