package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF135VLSComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF135VLSComponentImpl.
 */
public class StateOfF135VLSComponentImpl extends HqdmObject implements StateOfF135VLSComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF135VLSComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F135_VLS_COMPONENT_TYPE_IRI);
    }
}
