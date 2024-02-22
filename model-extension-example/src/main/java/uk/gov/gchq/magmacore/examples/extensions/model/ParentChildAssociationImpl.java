package uk.gov.gchq.magmacore.examples.extensions.model;

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
        addValue(RDFS.RDF_TYPE, Constants.PARENT_CHILD_ASSOCIATION_IRI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRI getParentIri() {
        return oneValue(Constants.PARENT_ROLE_IRI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IRI getChildIri() {
        return oneValue(Constants.CHILD_ROLE_IRI);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParentIri(final IRI iri) {
        addValue(Constants.PARENT_ROLE_IRI, iri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChildIri(final IRI iri) {
        addValue(Constants.CHILD_ROLE_IRI, iri);
    }

}
