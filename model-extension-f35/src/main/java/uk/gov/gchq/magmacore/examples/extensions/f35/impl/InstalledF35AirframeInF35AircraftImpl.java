package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF35AirframeInF35Aircraft;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledF35AirframeInF35AircraftImpl.
 */
public class InstalledF35AirframeInF35AircraftImpl extends HqdmObject implements InstalledF35AirframeInF35Aircraft {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledF35AirframeInF35AircraftImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_IRI);
    }
}
