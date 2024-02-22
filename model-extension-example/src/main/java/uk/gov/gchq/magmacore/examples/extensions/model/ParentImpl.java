package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the Parent interface.
 */
public class ParentImpl extends HqdmObject implements Parent {

    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public ParentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.PARENT_IRI);
    }

}
