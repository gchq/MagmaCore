package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent;

/**
 * A StateOfF135EngineComponent.
 */
public interface StateOfF135EngineComponent extends StateOfFunctionalSystemComponent {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

}

