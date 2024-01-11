package uk.gov.gchq.magmacore.hqdm.extensions;

import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An SPI interface for extending HQDM.
 */
public interface ExtensionService {

    Thing createEntity(final String typeName, final IRI iri);

}
