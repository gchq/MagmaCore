package  uk.gov.gchq.magmacore.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Exercise the {@link ExampleSigns} code during the build.
 *
 * */
public class ExampleiSignsTest {

    /**
     * Test the {@link ExampleSigns} code.
     *
     * */
    @Test
    public void testApplyAndInvert() {
        final var service = MagmaCoreServiceFactory.createWithJenaDatabase();
        
        final var transformation = ExampleSigns.populateExampleData(service);

        assertNotNull(transformation);
    }
}
