package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF35EngineComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF35EngineComponentImpl.
 */
public class StateOfF35EngineComponentImpl extends HqdmObject implements StateOfF35EngineComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF35EngineComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F35_ENGINE_COMPONENT_TYPE_IRI);
    }
}
