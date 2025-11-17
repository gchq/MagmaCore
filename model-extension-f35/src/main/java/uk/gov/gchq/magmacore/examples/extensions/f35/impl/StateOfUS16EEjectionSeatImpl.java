package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfUS16EEjectionSeat;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfUS16EEjectionSeatImpl.
 */
public class StateOfUS16EEjectionSeatImpl extends HqdmObject implements StateOfUS16EEjectionSeat {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfUS16EEjectionSeatImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_US16E_EJECTION_SEAT_TYPE_IRI);
    }
}
