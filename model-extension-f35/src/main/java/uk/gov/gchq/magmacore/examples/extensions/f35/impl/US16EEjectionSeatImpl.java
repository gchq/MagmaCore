package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.US16EEjectionSeat;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the US16EEjectionSeat interface.
 */
public class US16EEjectionSeatImpl extends HqdmObject implements US16EEjectionSeat {
    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public US16EEjectionSeatImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.US16E_EJECTION_SEAT_TYPE_IRI);
    }
}
