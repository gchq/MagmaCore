package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F35 Turbine Component.
 */
public interface F35TurbineComponent extends FunctionalSystemComponent, StateOfF35TurbineComponent {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}
