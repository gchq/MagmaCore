package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F135 Engine Component.
 */
public interface F135EngineComponent extends FunctionalSystemComponent, StateOfF135EngineComponent {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

}
