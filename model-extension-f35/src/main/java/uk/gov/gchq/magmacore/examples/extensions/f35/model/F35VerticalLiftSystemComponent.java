package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F35 Vertical Lift System Component.
 */
public interface F35VerticalLiftSystemComponent extends FunctionalSystemComponent, StateOfF35VerticalLiftSystemComponent {

    static Role intendedRole = IntendedRoles.F135VerticalLiftSystemIntendedRole;

}
