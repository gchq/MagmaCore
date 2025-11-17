package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject;

/**
 * A StateOfF35Airframe.
 */
public interface StateOfF35Airframe extends StateOfOrdinaryFunctionalObject {

    static Role intendedRole = IntendedRoles.F35AirframeIntendedRole;

}

