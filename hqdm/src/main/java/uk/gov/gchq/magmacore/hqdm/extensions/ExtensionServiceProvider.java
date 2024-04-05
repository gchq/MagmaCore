package uk.gov.gchq.magmacore.hqdm.extensions;

import java.util.Map;

import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An SPI interface for extending HQDM.
 *
 * <p>
 * See: <a href="https://github.com/twalmsley/hqdm_model_extension_example">HQDM Model Extension
 * Example</a> for an example of how to write an extension module.
 * </p>
 */
public interface ExtensionServiceProvider {
    /**
     * Create an instance of the extension service and ask it to register any new HQDM types in the Map.
     *
     * @param map a Map of IRI to Class so that MagmaCore can dynamically create Entities when found in
     *            a database.
     * @return ExtensionService instance.
     */
    ExtensionService createService(final Map<IRI, Class<? extends Thing>> map);
}
