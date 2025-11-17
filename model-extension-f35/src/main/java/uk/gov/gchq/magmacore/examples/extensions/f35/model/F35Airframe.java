package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An F35 Air Frame.
 */
public interface F35Airframe extends OrdinaryFunctionalObject, StateOfF35Airframe {

    static Role intendedRole = IntendedRoles.F35AirframeIntendedRole;

}
