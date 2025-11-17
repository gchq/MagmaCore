package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135 Vertical Lift System Component.
 */
public interface F135VerticalLiftSystemComponent extends FunctionalSystemComponent, StateOfF135VLSComponent {

    static Role intendedRole = IntendedRoles.F135VerticalLiftSystemIntendedRole;

}
