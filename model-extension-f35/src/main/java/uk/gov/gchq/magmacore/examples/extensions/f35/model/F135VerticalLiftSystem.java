package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135VerticalLiftSystem.
 */
public interface F135VerticalLiftSystem extends OrdinaryFunctionalObject, StateOfF135VLS {

    static Role intendedRole = IntendedRoles.F135VerticalLiftSystemIntendedRole;

}
