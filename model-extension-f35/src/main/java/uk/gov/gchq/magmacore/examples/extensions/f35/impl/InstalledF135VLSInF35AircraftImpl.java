package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135VLSInF35Aircraft;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledF135VLSInF35AircraftImpl.
 */
public class InstalledF135VLSInF35AircraftImpl extends HqdmObject implements InstalledF135VLSInF35Aircraft {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledF135VLSInF35AircraftImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_IRI);
    }
}
