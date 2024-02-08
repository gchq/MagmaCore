package uk.gov.gchq.magmacore.examples.verify;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import uk.gov.gchq.magmacore.database.validation.ValidationReportEntry;
import uk.gov.gchq.magmacore.hqdm.model.Organization;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * An example of how to use Jena rules for Data Integrity Checking.
 */
public class DataIntegrityChecksTest {

    private static final boolean INCLUDE_RDFS = true;
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    /**
     * Run some data integrity checks.
     *
     * @throws URISyntaxException if the URI is invalid.
     * @throws IOException if the rules file can't be read.
     */
    @Test
    public void test() throws IOException, URISyntaxException {

        // Create the in-memory MagmaCoreService object.
        final MagmaCoreService service = MagmaCoreServiceFactory.createWithJenaDatabase();

        // Load the HQDM data model.
        service.loadTtl(getClass().getResourceAsStream("/hqdm-0.0.1-alpha.ttl"));

        // Populate some data to prove that the rules are applied.
        service.runInWriteTransaction(svc -> {
            // Create a kind without an ENTITY_NAME.
            final Thing kind = ClassServices.createKindOfOrganization(randomIri());
            svc.create(kind);

            // Create a participant that isn't a member_of_kind of a role.
            // Also, as a SpatioTemporalExtent it should have a part_of_possible_world predicate.
            final Thing participant = SpatioTemporalExtentServices.createParticipant(randomIri());
            svc.create(participant);

            // Create an entity with invalid temporal parthood.
            final PossibleWorld possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(randomIri());
            possibleWorld.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());

            final Person person = SpatioTemporalExtentServices.createPerson(randomIri());
            person.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());

            final Organization organization = SpatioTemporalExtentServices.createOrganization(randomIri());
            organization.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());

            person.addValue(HQDM.TEMPORAL_PART_OF, organization.getId());

            svc.create(possibleWorld);
            svc.create(person);
            svc.create(organization);

            // Create a sign without a value_ predicate.
            final Sign sign = SpatioTemporalExtentServices.createSign(randomIri());
            sign.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
            final Role signRole = ClassServices.createRole(randomIri());
            signRole.addValue(HQDM.ENTITY_NAME, "A role for a sign");
            sign.addValue(HQDM.MEMBER_OF_KIND, signRole.getId());
            svc.create(sign);
            svc.create(signRole);
            return svc;
        });

        // Create the construct query and load the validation rules.
        final String query = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o}";
        final String rules  = Files.readString(Paths.get(getClass().getResource("/validation.rules").toURI()));

        // Validate the model.
        final List<ValidationReportEntry> validationResult = service.validate(query, rules, INCLUDE_RDFS);

        if (validationResult.size() > 0) {
            System.out.println(validationResult);
        }
        assertEquals(5, validationResult.size());
    }

    /**
     * Make random IRIs.
     *
     * @return a random {@link IRI}
     */
    private static IRI randomIri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }
}
