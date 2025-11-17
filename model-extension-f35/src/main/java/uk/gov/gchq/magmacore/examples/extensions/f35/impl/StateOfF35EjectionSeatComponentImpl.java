package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF35EjectionSeatComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF35EjectionSeatComponentImpl.
 */
public class StateOfF35EjectionSeatComponentImpl extends HqdmObject implements StateOfF35EjectionSeatComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF35EjectionSeatComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F35_EJECTION_SEAT_COMPONENT_TYPE_IRI);
    }
}
