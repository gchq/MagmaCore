package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135EngineInF135EngineSystem;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An InstalledF135EngineInF135EngineSystemImpl.
 */
public class InstalledF135EngineInF135EngineSystemImpl extends HqdmObject implements InstalledF135EngineInF135EngineSystem {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public InstalledF135EngineInF135EngineSystemImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_IRI);
    }
}
