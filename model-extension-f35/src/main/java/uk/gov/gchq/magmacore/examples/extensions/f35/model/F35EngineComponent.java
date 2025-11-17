package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * F35 Engine Component.
 */
public interface F35EngineComponent extends FunctionalSystemComponent, StateOfF35EngineComponent {

    static Role intendedRole = IntendedRoles.F135EngineIntendedRole;

}
