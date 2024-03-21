package uk.gov.gchq.magmacore.database.query;

/**
 * An RDF Literal Node.
 */
public class Literal extends RdfNode {

    /**
     * Constructor.
     *
     * @param value {@link String}
     */
    public Literal(final String value) {
        super(value);
    }
    
}
