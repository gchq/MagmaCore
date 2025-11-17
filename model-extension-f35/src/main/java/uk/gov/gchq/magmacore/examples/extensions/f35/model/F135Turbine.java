package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135 Turbine.
 */
public interface F135Turbine extends OrdinaryFunctionalObject, StateOfF135Turbine {

    static Role intendedRole = IntendedRoles.F135TurbineIntendedRole;

}
