package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent;

/**
 * A StateOfF35AirframeComponent.
 */
public interface StateOfF35AirframeComponent extends StateOfFunctionalSystemComponent {

    static Role intendedRole = IntendedRoles.F35AirframeIntendedRole;

}

