package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F35 Aircraft System.
 */
public interface F35Aircraft extends FunctionalSystem {

    static Role intendedRole = IntendedRoles.F35AircraftIntendedRole;

    public F35AirframeComponent getAirframe();

    public F35EjectionSeatComponent getEjectionSeat();

    public F35EngineComponent getEngine();

    public F35TurbineComponent getTurbine();

    public F35VerticalLiftSystemComponent getVls();

    void setAirframe(final F35AirframeComponent airframe);

    void setEjectionSeat(final F35EjectionSeatComponent ejectionSeat);

    void setEngine(final F35EngineComponent engine);

    void setTurbine(final F35TurbineComponent turbine);

    void setVerticalLiftSystem(final F35VerticalLiftSystemComponent vls);
}
