package uk.gov.gchq.magmacore.examples.extensions.model;

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HQDM;

import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;

/**
 * Define some useful Constants.
 */
public interface Constants {
    /** UK limited company type name. */
    public static final String UK_LIMITED_COMPANY_TYPE_NAME = "uk_limited_company";

    /** UK limited company IRI. */
    public static final HqdmIri UK_LIMITED_COMPANY_IRI = new HqdmIri(HQDM, UK_LIMITED_COMPANY_TYPE_NAME);

    /** UK software development company type name. */
    public static final String UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME = "uk_software_development_company";
    
    /** UK software development company IRI. */
    public static final HqdmIri UK_SOFTWARE_DEVELOPMENT_COMPANY_IRI = 
        new HqdmIri(HQDM, UK_SOFTWARE_DEVELOPMENT_COMPANY_TYPE_NAME);

    /** Parent/Child Association type name. */
    public static final String PARENT_CHILD_ASSOCIATION_TYPE_NAME = "parent_child_association";

    /** Parent/Child Association IRI. */
    public static final HqdmIri PARENT_CHILD_ASSOCIATION_IRI 
        = new HqdmIri(HQDM, PARENT_CHILD_ASSOCIATION_TYPE_NAME);

    /** Parent role type name. */
    public static final String PARENT_ROLE_NAME = "parent_role";
    
    /** Parent role IRI. */
    public static final HqdmIri PARENT_ROLE_IRI = new HqdmIri(HQDM, PARENT_ROLE_NAME);

    /** Child role name. */
    public static final String CHILD_ROLE_NAME = "child_role";

    /** Child role IRI. */
    public static final HqdmIri CHILD_ROLE_IRI = new HqdmIri(HQDM, CHILD_ROLE_NAME);

    /** Parent type name. */
    public static final String PARENT_TYPE_NAME = "parent";

    /** Parent type IRI. */
    public static final HqdmIri PARENT_IRI = new HqdmIri(HQDM, PARENT_TYPE_NAME);

    /** Child type name. */
    public static final String CHILD_TYPE_NAME = "child";

    /** Child type IRI. */
    public static final HqdmIri CHILD_IRI = new HqdmIri(HQDM, CHILD_TYPE_NAME);
}
