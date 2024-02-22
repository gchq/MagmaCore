package uk.gov.gchq.magmacore.examples.extensions.ext;

import uk.gov.gchq.magmacore.examples.extensions.model.ChildImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.model.ParentChildAssociationImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.ParentImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.UkLimitedCompanyImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.UkSoftwareDevelopmentCompanyImpl;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An example HQDM ExtensionService for adding some extension classes to MagmaCore.
 */
public class HqdmExtensionService implements ExtensionService {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Thing> T createEntity(final String typeName, final IRI iri) {

        // Check the type name and return an appropriate instance.
        if (Constants.UK_LIMITED_COMPANY_TYPE_NAME.equals(typeName)) {
            return (T) new UkLimitedCompanyImpl(iri);
        } else if (Constants.UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME.equals(typeName)) {
            return (T) new UkSoftwareDevelopmentCompanyImpl(iri);
        } else if (Constants.PARENT_TYPE_NAME.equals(typeName)) {
            return (T) new ParentImpl(iri);
        } else if (Constants.CHILD_TYPE_NAME.equals(typeName)) {
            return (T) new ChildImpl(iri);
        } else if (Constants.PARENT_CHILD_ASSOCIATION_TYPE_NAME.equals(typeName)) {
            return (T) new ParentChildAssociationImpl(iri);
        }

        // Or null if it's not a class from this extension package.
        return null;
    }
}
