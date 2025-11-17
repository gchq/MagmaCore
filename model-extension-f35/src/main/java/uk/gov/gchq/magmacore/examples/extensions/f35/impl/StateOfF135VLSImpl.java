package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF135VLS;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF135VLSImpl.
 */
public class StateOfF135VLSImpl extends HqdmObject implements StateOfF135VLS {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF135VLSImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F135_VLS_TYPE_IRI);
    }
}
