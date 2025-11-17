package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject;

/**
 * A StateOfF35TurbineComponent.
 */
public interface StateOfF35TurbineComponent extends StateOfOrdinaryFunctionalObject {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}

