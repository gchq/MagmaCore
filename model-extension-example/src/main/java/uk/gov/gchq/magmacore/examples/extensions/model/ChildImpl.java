package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the Child interface.
 */
public class ChildImpl extends HqdmObject implements Child {

    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public ChildImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.CHILD_IRI);
    }

}
