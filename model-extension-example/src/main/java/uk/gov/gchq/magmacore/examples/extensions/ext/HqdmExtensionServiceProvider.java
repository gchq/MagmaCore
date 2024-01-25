package uk.gov.gchq.magmacore.examples.extensions.ext;

import java.util.Map;

import uk.gov.gchq.magmacore.examples.extensions.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.model.UkLimitedCompany;
import uk.gov.gchq.magmacore.examples.extensions.model.UkSoftwareDevelopmentCompany;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionServiceProvider;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An example ExtensionServiceProvider for adding some extension classes to MagmaCore.
 */
public class HqdmExtensionServiceProvider implements ExtensionServiceProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtensionService createService(final Map<IRI, Class<? extends Thing>> map) {

        // Register our extension IRIs and the corresponding interfaces.
        //
        map.put(Constants.UK_LIMITED_COMPANY_IRI, UkLimitedCompany.class);
        map.put(Constants.UK_SOFTWARE_DEVELOPMENT_COMPANY_IRI, UkSoftwareDevelopmentCompany.class);

        // Create ane return the extension service.
        return new HqdmExtensionService();
    }

}
