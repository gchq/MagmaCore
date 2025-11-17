package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.StateOfF135EngineComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An StateOfF135EngineComponentImpl.
 */
public class StateOfF135EngineComponentImpl extends HqdmObject implements StateOfF135EngineComponent {

    /**
     * Constructor.
     *
     * @param id An IRI.
     */
    public StateOfF135EngineComponentImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.STATE_OF_F135_ENGINE_COMPONENT_TYPE_IRI);
    }
}
