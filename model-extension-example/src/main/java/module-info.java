/**
 * Example HQDM model extension and ExtensionServiceProvider implementation.
 */
module uk.gov.gchq.magmacore.examples.extensions {
    exports uk.gov.gchq.magmacore.examples.extensions.ext;
    exports uk.gov.gchq.magmacore.examples.extensions.model;

    requires transitive uk.gov.gchq.magmacore.hqdm;

    provides uk.gov.gchq.magmacore.hqdm.extensions.ExtensionServiceProvider
            with uk.gov.gchq.magmacore.examples.extensions.ext.HqdmExtensionServiceProvider;
}
