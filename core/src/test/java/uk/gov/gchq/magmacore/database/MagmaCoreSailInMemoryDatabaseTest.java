package uk.gov.gchq.magmacore.database;

import static org.junit.Assert.assertNull;

import org.junit.Before;
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
public class MagmaCoreSailInMemoryDatabaseTest {
    static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    
    private MagmaCoreService database;

    @Before
    public void before() {
        database = MagmaCoreServiceFactory.createWithSailInMemoryDatabase();
    }

    /**
     * Test that triples can be deleted.
     */
    @Test
    public void test() {

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

}
