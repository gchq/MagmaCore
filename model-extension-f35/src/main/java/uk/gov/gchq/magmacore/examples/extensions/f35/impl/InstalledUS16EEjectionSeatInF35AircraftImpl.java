package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledUS16EEjectionSeatInF35Aircraft;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledUS16EEjectionSeatInF35AircraftImpl.
 */
public class InstalledUS16EEjectionSeatInF35AircraftImpl extends HqdmObject implements InstalledUS16EEjectionSeatInF35Aircraft {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledUS16EEjectionSeatInF35AircraftImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_IRI);
    }
}
