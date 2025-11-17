package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * An InstalledF135VLSInF35Aircraft.
 */
public interface InstalledF135VLSInF35Aircraft extends InstalledFunctionalSystemComponent, StateOfF135VLS, StateOfF35VerticalLiftSystemComponent {

    static Role intendedRole = IntendedRoles.F135VerticalLiftSystemIntendedRole;

}

