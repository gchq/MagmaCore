package uk.gov.gchq.magmacore.database.query;

/**
 * An RDF node.
 */
public class RdfNode {

    private final String value;

    /**
     * Constructor.
     *
     * @param value {@link String}
     */
    public RdfNode(final String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        if (other != null && other instanceof RdfNode node) {
            return value.equals(node.value);
        }
        return false;
    }
    
}
