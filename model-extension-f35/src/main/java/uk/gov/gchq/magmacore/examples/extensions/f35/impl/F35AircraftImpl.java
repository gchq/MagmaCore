package uk.gov.gchq.magmacore.examples.extensions.f35.impl;

import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35AirframeComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EjectionSeatComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EngineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35TurbineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An implementation of the F35Aircraft interface.
 */
public class F35AircraftImpl extends HqdmObject implements F35Aircraft {

    private F35AirframeComponent airframe;

    private F35EjectionSeatComponent ejectionSeat;

    private F35EngineComponent engine;

    private F35TurbineComponent turbine;

    private F35VerticalLiftSystemComponent vls;

    /**
     * Constructor.
     *
     * @param id {@link IRI}
     */
    public F35AircraftImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.F35_AIRCRAFT_TYPE_IRI);
    }

    public F35AirframeComponent getAirframe() {
        return airframe;
    }

    public F35EjectionSeatComponent getEjectionSeat() {
        return ejectionSeat;
    }

    public F35EngineComponent getEngine() {
        return engine;
    }

    public F35TurbineComponent getTurbine() {
        return turbine;
    }

    public F35VerticalLiftSystemComponent getVls() {
        return vls;
    }

    @Override
    public void setAirframe(final F35AirframeComponent airframe) {
        this.airframe = airframe;
        airframe.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setEjectionSeat(final F35EjectionSeatComponent ejectionSeat) {
        this.ejectionSeat = ejectionSeat;
        ejectionSeat.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setEngine(final F35EngineComponent engine) {
        this.engine = engine;
        engine.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setTurbine(final F35TurbineComponent turbine) {
        this.turbine = turbine;
        turbine.addValue(HQDM.COMPONENT_OF, this.getId());
    }

    @Override
    public void setVerticalLiftSystem(final F35VerticalLiftSystemComponent vls) {
        this.vls = vls;
        vls.addValue(HQDM.COMPONENT_OF, this.getId());
    }
}
