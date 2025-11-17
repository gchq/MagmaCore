package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF135VLSInF135EngineSystem.
 */
public interface InstalledF135VLSInF135EngineSystem extends InstalledFunctionalSystemComponent, StateOfF135VLS, StateOfF135VLSComponent {

    static Role intendedRole = IntendedRoles.F135VerticalLiftSystemIntendedRole;

}

