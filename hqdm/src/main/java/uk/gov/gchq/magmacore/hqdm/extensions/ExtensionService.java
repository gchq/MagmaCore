package uk.gov.gchq.magmacore.hqdm.extensions;

import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An SPI interface for extending HQDM.
 *
 * <p>
 * See: <a href="https://github.com/twalmsley/hqdm_model_extension_example">HQDM Model Extension Example</a> for
 * an example of how to write an extension module.
 * </p>
 */
public interface ExtensionService {

    /**
     * Create and entity with the given typeName, or return null if the typeName is not recognised
     * by the ExtensionService.
     *
     * @param typeName A String with the required type to be created.
     * @param iri The IRI to use when creating the instance.
     * @return A Thing if the typeName is recognised, null otherwise.
     */
    Thing createEntity(final String typeName, final IRI iri);

}
