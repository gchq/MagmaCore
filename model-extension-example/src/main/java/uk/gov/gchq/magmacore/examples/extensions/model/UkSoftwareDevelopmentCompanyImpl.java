package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An HQDM Extension class to add a new entity type to MagmaCore.
 */
public class UkSoftwareDevelopmentCompanyImpl extends HqdmObject implements UkLimitedCompany {

    /**
     * {@code UkSoftwareDevelopmentCompanyImpl} constructor.
     *
     * @param id ID for the {@code UkSoftwareDevelopmentCompany}.
     */
    public UkSoftwareDevelopmentCompanyImpl(final IRI id) {
        super(id);
    }
}
