package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.pojo.HqdmObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * An HQDM Extension class to add a new entity type to MagmaCore.
 */
public class UkLimitedCompanyImpl extends HqdmObject implements UkLimitedCompany {

    /**
     * {@code UkLimitedCompany} constructor.
     *
     * @param id ID for the UkLimitedCompany.
     */
    public UkLimitedCompanyImpl(final IRI id) {
        super(id);
        addValue(RDFS.RDF_TYPE, Constants.UK_LIMITED_COMPANY_IRI);
    }
}
