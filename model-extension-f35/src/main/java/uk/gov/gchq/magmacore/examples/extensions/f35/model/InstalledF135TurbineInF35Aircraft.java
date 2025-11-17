package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF135TurbineInF35Aircraft.
 */
public interface InstalledF135TurbineInF35Aircraft extends InstalledFunctionalSystemComponent, StateOfF135Turbine, StateOfF35TurbineComponent {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}

