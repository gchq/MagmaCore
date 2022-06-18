package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.IriBase;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleCommonUtils {

    /** IriBase for User data. */
    public static final IriBase USER_BASE =
        new IriBase("user", "http://www.semanticweb.org/magma-core/user#");

    /**
     * Create a new IRI in the USER_BASE namespace.
     *
     * @return {@link IRI}
     */
    public static IRI mkUserBaseIri() {
        return new IRI(USER_BASE, uid());
    }

    /** IriBase for Reference Data Library. */
    public static final IriBase REF_BASE =
        new IriBase("rdl", "http://www.semanticweb.org/magma-core/rdl#");

    /**
     * Create a new IRI in the REF_BASE namespace.
     *
     * @return {@link IRI}
     */
    public static IRI mkRefBaseIri() {
        return new IRI(REF_BASE, uid());
    }

}
