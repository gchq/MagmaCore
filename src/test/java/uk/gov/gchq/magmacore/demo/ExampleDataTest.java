package  uk.gov.gchq.magmacore.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Exercise the {@link ExampleDataObjects} code during the build.
 *
 * */
public class ExampleDataTest {

    /**
     * Test the {@link ExampleDataObjects} code.
     *
     * */
    @Test
    public void testApplyAndInvert() {
        final var service = MagmaCoreServiceFactory.createWithJenaDatabase();
        
        final var transformation = ExampleDataObjects.populateExampleData(service);

        assertNotNull(transformation);
    }
}
