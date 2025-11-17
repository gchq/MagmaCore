package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * US16E Ejection Seat.
 */
public interface US16EEjectionSeat extends OrdinaryFunctionalObject, StateOfUS16EEjectionSeat {

    static Role intendedRole = IntendedRoles.US16EEjectionSeatIntendedRole;

}
