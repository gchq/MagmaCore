package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F35 Airframe Component.
 */
public interface F35AirframeComponent extends FunctionalSystemComponent, StateOfF35AirframeComponent {

    static Role intendedRole = IntendedRoles.F35AirframeIntendedRole;

}
