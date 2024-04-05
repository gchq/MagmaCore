package uk.gov.gchq.magmacore.examples.extensions.ext;

import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.CHILD_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.PARENT_CHILD_ASSOCIATION_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.PARENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.UK_LIMITED_COMPANY_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.model.Constants.UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME;

import uk.gov.gchq.magmacore.examples.extensions.model.ChildImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.ParentChildAssociationImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.ParentImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.UkLimitedCompanyImpl;
import uk.gov.gchq.magmacore.examples.extensions.model.UkSoftwareDevelopmentCompanyImpl;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An example HQDM ExtensionService for adding some extension classes to
 * MagmaCore.
 */
public class HqdmExtensionService implements ExtensionService {
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Thing> T createEntity(final String typeName, final IRI iri) {

        switch (typeName) {
            case UK_LIMITED_COMPANY_TYPE_NAME:
                return (T) new UkLimitedCompanyImpl(iri);

            case UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME:
                return (T) new UkSoftwareDevelopmentCompanyImpl(iri);

            case PARENT_TYPE_NAME:
                return (T) new ParentImpl(iri);

            case CHILD_TYPE_NAME:
                return (T) new ChildImpl(iri);

            case PARENT_CHILD_ASSOCIATION_TYPE_NAME:
                return (T) new ParentChildAssociationImpl(iri);

            default:
                // Or null if it's not a class from this extension package.
                return null;
        }
    }
}
