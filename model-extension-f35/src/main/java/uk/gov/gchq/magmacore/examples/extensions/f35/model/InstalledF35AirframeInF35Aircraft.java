package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF35AirframeInF35Aircraft.
 */
public interface InstalledF35AirframeInF35Aircraft extends InstalledFunctionalSystemComponent, StateOfF35Airframe, StateOfF35AirframeComponent {

    static Role intendedRole = IntendedRoles.F35AirframeIntendedRole;

}

