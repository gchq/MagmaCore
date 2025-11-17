package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F35 Ejection Seat Component.
 */
public interface F35EjectionSeatComponent extends FunctionalSystemComponent, StateOfF35EjectionSeatComponent {

    static Role intendedRole = IntendedRoles.US16EEjectionSeatIntendedRole;

}
