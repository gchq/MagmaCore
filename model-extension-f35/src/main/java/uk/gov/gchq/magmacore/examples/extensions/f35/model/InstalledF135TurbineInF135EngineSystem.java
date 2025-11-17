package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF135TurbineInF135EngineSystem.
 */
public interface InstalledF135TurbineInF135EngineSystem extends InstalledFunctionalSystemComponent, StateOfF135Turbine, StateOfF135TurbineComponent {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}

