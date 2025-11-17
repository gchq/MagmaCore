package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135TurbineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F135EngineSystem interface.
 */
public class F135EngineSystemImpl extends HqdmObject implements F135EngineSystem {
    private F135EngineComponent engine;
    private F135TurbineComponent turbine;
    private F135VerticalLiftSystemComponent vls;

    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F135EngineSystemImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F135_ENGINE_SYSTEM_TYPE_IRI);
    }

    @Override
    public F135EngineComponent getEngine() {
        return engine;
    }

    @Override
    public F135TurbineComponent getTurbine() {
        return turbine;
    }

    @Override
    public F135VerticalLiftSystemComponent getVls() {
        return vls;
    }

    @Override
    public void setEngine(final F135EngineComponent engine) {
        this.engine = engine;
        engine.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setTurbine(final F135TurbineComponent turbine) {
        this.turbine = turbine;
        turbine.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setVerticalLiftSystem(final F135VerticalLiftSystemComponent vls) {
        this.vls = vls;
        vls.addValue(HQDM.COMPONENT_OF, this.getId());
    }
}
