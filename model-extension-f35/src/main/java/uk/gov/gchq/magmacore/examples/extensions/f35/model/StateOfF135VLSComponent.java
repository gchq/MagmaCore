package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent;

/**
 * A StateOfF135VLSComponent.
 */
public interface StateOfF135VLSComponent extends StateOfFunctionalSystemComponent {

    static Role intendedRole = IntendedRoles.F135VerticalLiftSystemIntendedRole;

}

