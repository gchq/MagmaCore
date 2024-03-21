package uk.gov.gchq.magmacore.database.query;

/**
 * An RDF Resource, likely an IRI String.
 */
public class Resource extends RdfNode {

    /**
     * Constructor.
     *
     * @param value {@link String}
     */
    public Resource(final String value) {
        super(value);
    }
    
}
