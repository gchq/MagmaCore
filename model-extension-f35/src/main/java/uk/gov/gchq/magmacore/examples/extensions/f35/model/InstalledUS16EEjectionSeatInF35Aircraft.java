package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledUS16EEjectionSeatInF35Aircraft.
 */
public interface InstalledUS16EEjectionSeatInF35Aircraft extends InstalledFunctionalSystemComponent, StateOfUS16EEjectionSeat, StateOfF35EjectionSeatComponent {

    static Role intendedRole = IntendedRoles.US16EEjectionSeatIntendedRole;

}

