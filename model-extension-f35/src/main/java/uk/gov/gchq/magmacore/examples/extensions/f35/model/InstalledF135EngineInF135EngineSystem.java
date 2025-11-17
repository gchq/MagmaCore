package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF135EngineInF135EngineSystem.
 */
public interface InstalledF135EngineInF135EngineSystem extends InstalledFunctionalSystemComponent, StateOfF135Engine, StateOfF135EngineComponent {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

}

