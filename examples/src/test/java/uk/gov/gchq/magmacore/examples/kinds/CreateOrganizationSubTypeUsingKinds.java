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
 * Create a KindOfOrganization and make a Thing a member_of_kind of it,
 * then persist the Thing and make sure it is read back as an Organization.
 */
public class CreateOrganizationSubTypeUsingKinds {
    private static IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    
    @Test
    public void test() {
        final PossibleWorld possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(randomIri());
        final KindOfOrganizationComponent componentKind = new KindOfOrganizationComponentBuilder(randomIri())
            .build();

        final KindOfOrganization companies = new KindOfOrganizationBuilder(randomIri())
            .has_Component_By_Class_M(componentKind)
            .build(); 

        final Individual acmeLtd = new IndividualBuilder(randomIri())
            .member_Of_Kind(companies)
            .part_Of_Possible_World_M(possibleWorld)
            .build();

        final MagmaCoreService service = MagmaCoreServiceFactory.createWithJenaDatabase();
        service.beginWrite();
        service.create(companies);
        service.create(acmeLtd);
        service.commit();

        service.beginRead();
        final Thing acmeFromDatabase = service.get(acmeLtd.getId());
        service.commit();

        assertTrue(acmeFromDatabase instanceof Organization);
    }

    private IRI randomIri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }
}
