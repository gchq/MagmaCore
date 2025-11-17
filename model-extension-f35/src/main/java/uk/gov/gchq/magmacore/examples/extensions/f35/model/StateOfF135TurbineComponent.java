package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent;

/**
 * A StateOfF135TurbineComponent.
 */
public interface StateOfF135TurbineComponent extends StateOfFunctionalSystemComponent {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}

