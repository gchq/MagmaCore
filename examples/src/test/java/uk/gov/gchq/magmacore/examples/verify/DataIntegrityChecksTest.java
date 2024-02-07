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
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
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
            final Thing kind = ClassServices.createKindOfOrganization(new IRI(TEST_BASE, UUID.randomUUID().toString()));
            svc.create(kind);
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
        assertEquals(1, validationResult.size());
    }

}
