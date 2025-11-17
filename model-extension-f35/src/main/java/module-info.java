/**
 * Example module for extension of MagmaCore HQDM classes.
 */

import uk.gov.gchq.magmacore.examples.extensions.f35.ext.F35ExtensionServiceProvider;

module uk.gov.gchq.magmacore.examples.extensions.f35 {
    exports uk.gov.gchq.magmacore.examples.extensions.f35.ext;
    exports uk.gov.gchq.magmacore.examples.extensions.f35.model;

    requires transitive uk.gov.gchq.magmacore.hqdm;

    provides uk.gov.gchq.magmacore.hqdm.extensions.ExtensionServiceProvider
            with F35ExtensionServiceProvider;
}
