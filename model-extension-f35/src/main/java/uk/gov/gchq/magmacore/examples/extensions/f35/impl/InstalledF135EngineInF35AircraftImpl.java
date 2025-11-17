package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135EngineInF35Aircraft;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledF135EngineInF35AircraftImpl.
 */
public class InstalledF135EngineInF35AircraftImpl extends HqdmObject implements InstalledF135EngineInF35Aircraft {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledF135EngineInF35AircraftImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_IRI);
    }
}
