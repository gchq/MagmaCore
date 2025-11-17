package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF135EngineInF35Aircraft.
 */
public interface InstalledF135EngineInF35Aircraft extends InstalledFunctionalSystemComponent, StateOfF135Engine, StateOfF35EngineComponent {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

}

