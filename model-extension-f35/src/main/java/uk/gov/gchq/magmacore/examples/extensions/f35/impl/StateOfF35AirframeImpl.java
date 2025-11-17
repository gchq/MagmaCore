package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF35Airframe;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF35AirframeImpl.
 */
public class StateOfF35AirframeImpl extends HqdmObject implements StateOfF35Airframe {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF35AirframeImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F35_AIRFRAME_TYPE_IRI);
    }
}
