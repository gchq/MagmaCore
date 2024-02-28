package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.model.Association;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * A specialized Association representing human parent/child relationships.
 * This association has relations to it's participants for convenience.
 */
public interface ParentChildAssociation extends Association {
    
    /**
     * Mutator method.
     *
     * @param iri {@link IRI}
     */
    void setParentIri(final IRI iri);

    IRI getParentIri();

    /**
     * Mutator method.
     *
     * @param iri {@link IRI}
     */
    void setChildIri(final IRI iri);

    IRI getChildIri();
}
