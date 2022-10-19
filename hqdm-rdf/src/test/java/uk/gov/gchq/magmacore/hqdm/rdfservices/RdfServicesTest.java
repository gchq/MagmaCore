package uk.gov.gchq.magmacore.hqdm.rdfservices;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Check that all Rdf*Service methods return a {@link Thing} with an RDFS.RDF_TYPE predicate.
 */
public class RdfServicesTest {

    /**
     * Test all of the RDF*Services classes.
     */
    @Test
    public void testRdfClassServices() {
        testMethods(RdfClassServices.class.getDeclaredMethods());
        testMethods(RdfSpatioTemporalExtentServices.class.getDeclaredMethods());
        testMethods(RdfRelationshipServices.class.getDeclaredMethods());
    }

    /**
     * Test the array of methods.
     *
     * @param methods an array of {@link Method}
     */
    private void testMethods(final Method[] methods) {

        final List<Thing> thingsWithoutRdfType = Arrays.stream(methods)
                .filter(method -> (method.getModifiers() & Modifier.STATIC) > 0)
                .filter(method -> method.getName().startsWith("create"))
                .map(method -> {
                    try {
                        final var result = method.invoke(null, "id");
                        assertNotNull(result);
                        return (Thing) result;
                    } catch (final Exception ex) {
                        fail(ex.getMessage());
                    }
                    return SpatioTemporalExtentServices.createThing("id");
                })
                .filter(thing -> !thing.hasValue(RDFS.RDF_TYPE))
                .toList();

        assertTrue(thingsWithoutRdfType.isEmpty());
        if (!thingsWithoutRdfType.isEmpty()) {
            System.out.println(thingsWithoutRdfType);
        }
    }
}
