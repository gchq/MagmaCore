package uk.gov.gchq.magmacore.examples.extensions.model;

import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;

/**
 * Define some useful Constants.
 */
public interface Constants {
    /** UK limited company type name. */
    public static final String UK_LIMITED_COMPANY_TYPE_NAME = "uk_limited_company";

    /** UK limited company IRI. */
    public static final HqdmIri UK_LIMITED_COMPANY_IRI = new HqdmIri(HQDM.HQDM, UK_LIMITED_COMPANY_TYPE_NAME);

    /** UK software development company type name. */
    public static final String UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME = "uk_software_development_company";
    
    /** UK software development company IRI. */
    public static final HqdmIri UK_SOFTWARE_DEVELOPMENT_COMPANY_IRI = 
        new HqdmIri(HQDM.HQDM, UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME);
}
