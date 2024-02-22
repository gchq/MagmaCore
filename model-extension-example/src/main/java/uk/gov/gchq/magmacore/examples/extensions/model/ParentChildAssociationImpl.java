package uk.gov.gchq.magmacore.examples.extensions.model;

import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.*;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the ParentChildAssociation interface.
 */
public class ParentChildAssociationImpl extends HqdmObject implements ParentChildAssociation {

    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public ParentChildAssociationImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, PARENT_CHILD_ASSOCIATION_IRI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRI getParentIri() {
        return oneValue(PARENT_ROLE_IRI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRI getChildIri() {
        return oneValue(CHILD_ROLE_IRI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentIri(final IRI iri) {
        addValue(PARENT_ROLE_IRI, iri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChildIri(final IRI iri) {
        addValue(CHILD_ROLE_IRI, iri);
    }

}
