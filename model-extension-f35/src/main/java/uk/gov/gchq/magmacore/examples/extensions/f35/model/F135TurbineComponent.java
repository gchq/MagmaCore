package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135 Turbine Component.
 */
public interface F135TurbineComponent extends FunctionalSystemComponent, StateOfF135TurbineComponent {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}
