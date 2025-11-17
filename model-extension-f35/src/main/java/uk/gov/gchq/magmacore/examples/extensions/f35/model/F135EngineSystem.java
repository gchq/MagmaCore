package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135 Engine System.
 */
public interface F135EngineSystem extends FunctionalSystem {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

    public F135EngineComponent getEngine();

    public F135TurbineComponent getTurbine();

    public F135VerticalLiftSystemComponent getVls();

    void setEngine(final F135EngineComponent engine);

    void setTurbine(final F135TurbineComponent turbine);

    void setVerticalLiftSystem(final F135VerticalLiftSystemComponent vls);
}
