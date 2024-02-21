package uk.gov.gchq.magmacore.examples.verify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import uk.gov.gchq.magmacore.database.validation.ValidationReportEntry;
import uk.gov.gchq.magmacore.hqdm.model.Organization;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.StateOfSign;
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

    private static List<String> expectedErrors = null;

    /**
     * Get all the errors we expect to see.
     *
     * @throws IOException on error.
     * @throws URISyntaxException on error.
     */
    @BeforeClass
    public static void beforeClass() throws IOException, URISyntaxException {
        final var rulesUri = DataIntegrityChecksTest.class.getResource("/validation.rules").toURI();
        expectedErrors = Files.readAllLines(Paths.get(rulesUri))
            .stream()
            .filter(line -> line.contains("error"))
            .map(line -> line.split("'")[1])
            .map(s -> "\"" + s + "\"")
            .toList();
        final Set<String> deduped = Set.copyOf(expectedErrors);
        assertEquals("Posible duplicate errors in validation rules file.", deduped.size(), expectedErrors.size());
    }

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
            // Also tests the member_of_ for pattern since that is also missing.
            //
            final Sign sign = SpatioTemporalExtentServices.createSign(randomIri());
            sign.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
            final Role signRole = ClassServices.createRole(randomIri());
            signRole.addValue(HQDM.ENTITY_NAME, "A role for a sign");
            sign.addValue(HQDM.MEMBER_OF_KIND, signRole.getId());
            svc.create(sign);
            svc.create(signRole);

            // Create a RepresentationByPattern without a pattern and a pattern without a 
            // RepresentationByPattern. The RepresentationByPattern also has no community,
            // and no sign.
            //
            final RepresentationByPattern rbp = ClassServices.createRepresentationByPattern(randomIri());
            rbp.addValue(HQDM.ENTITY_NAME, "A RepresentationByPattern with no pattern");
            svc.create(rbp);

            final Pattern pattern = ClassServices.createPattern(randomIri());
            pattern.addValue(HQDM.ENTITY_NAME, "A Pattern with no RepresentationByPattern");
            svc.create(pattern);

            // Create a RepresentationBySign with no community, and no sign, and does not
            // represent a thing.
            //
            final RepresentationBySign rbs = SpatioTemporalExtentServices.createRepresentationBySign(randomIri());
            rbs.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
            rbs.addValue(HQDM.ENTITY_NAME, "A RepresentationBySign with no community and no sign and no thing");
            svc.create(rbs);

            // Create a state_of_sign with no participant_in relation.
            final StateOfSign stateOfSign = SpatioTemporalExtentServices.createStateOfSign(randomIri());
            stateOfSign.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
            svc.create(stateOfSign);

            return svc;
        });

        // Create the construct query and load the validation rules.
        final String query = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o}";
        final String rules  = Files.readString(Paths.get(getClass().getResource("/validation.rules").toURI()));

        // Validate the model.
        final List<ValidationReportEntry> validationResult = service.validate(query, rules, INCLUDE_RDFS);

        final Set<String> actualErrors = new HashSet<>();

        validationResult.forEach(result -> {
            actualErrors.add(result.type());
        });

        // Check that each rule has fired.
        final List<String> missing = expectedErrors.stream()
            .filter(e -> !actualErrors.contains(e))
            .map(e -> e + " was expected but not present.")
            .toList();

        missing.forEach(System.err::println);
        assertTrue("Not all rules fired - see log for details", missing.isEmpty());
        assertEquals(expectedErrors.size(), actualErrors.size());
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
