package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF35AirframeComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF35AirframeComponentImpl.
 */
public class StateOfF35AirframeComponentImpl extends HqdmObject implements StateOfF35AirframeComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF35AirframeComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F35_AIRFRAME_COMPONENT_TYPE_IRI);
    }
}
