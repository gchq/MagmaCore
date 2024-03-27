package uk.gov.gchq.magmacore.database;

import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;
import uk.gov.gchq.magmacore.service.SignPatternTestData;

/**
 * Unit test for the MagmaCoreSailInMemoryDatabase.
 */
public class MagmaCoreSailDatabaseTest {
    static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    
    /**
     * Test that triples can be created and deleted.
     */
    @Test
    public void testInMemoryDatabase() {

        final MagmaCoreService database = MagmaCoreServiceFactory.createWithSailInMemoryDatabase();

        final IRI individualIri = new IRI(SignPatternTestData.TEST_BASE, "individual");
        final Individual individual = SpatioTemporalExtentServices.createIndividual(individualIri);

        individual.addValue(HQDM.MEMBER_OF, new IRI(TEST_BASE, "classOfIndividual"));

        database.beginWrite();
        database.create(individual);
        database.commit();

        individual.removeValue(HQDM.MEMBER_OF, new IRI(TEST_BASE, "classOfIndividual"));
        individual.removeValue(RDFS.RDF_TYPE, HQDM.INDIVIDUAL);

        database.beginWrite();
        database.update(individual);
        database.commit();

        database.beginRead();
        final Thing individualFromDb = database.get(individualIri);
        database.commit();

        assertNull(individualFromDb);
    }

    /**
     * Test that triples can be created and deleted.
     */
    @Test
    public void testNativeDatabase() throws IOException {

        final Path path = Files.createTempDirectory("magmacore_unit_test");
        final File dataDir = path.toFile();
        final MagmaCoreService database = MagmaCoreServiceFactory.createWithSailNativeDatabase(dataDir);

        final IRI individualIri = new IRI(SignPatternTestData.TEST_BASE, "individual");
        final Individual individual = SpatioTemporalExtentServices.createIndividual(individualIri);

        individual.addValue(HQDM.MEMBER_OF, new IRI(TEST_BASE, "classOfIndividual"));

        database.beginWrite();
        database.create(individual);
        database.commit();

        individual.removeValue(HQDM.MEMBER_OF, new IRI(TEST_BASE, "classOfIndividual"));
        individual.removeValue(RDFS.RDF_TYPE, HQDM.INDIVIDUAL);

        database.beginWrite();
        database.update(individual);
        database.commit();

        database.beginRead();
        final Thing individualFromDb = database.get(individualIri);
        database.commit();

        assertNull(individualFromDb);

        // Delete the temporary directory.
        Files.walk(path)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }

}
