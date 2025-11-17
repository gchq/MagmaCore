package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135 Engine.
 */
public interface F135Engine extends OrdinaryFunctionalObject, StateOfF135Engine {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

}
