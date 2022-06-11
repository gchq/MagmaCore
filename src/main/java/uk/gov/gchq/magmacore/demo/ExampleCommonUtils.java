package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.IriBase;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;

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

    /**
     * Find an object by its ENTITY_NAME.
     *
     * @param db the {@link MagmaCoreDatabase} to seaerch.
     * @param name the name {@link String} to seaerch for.
     * @return the {@link Thing}that was found.
     * @throws RuntimeException if no or multiple results found.
     */
    public static <T> T findByEntityName(final MagmaCoreDatabase db, final String name) {
        final var searchResult = db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, name);
        if (searchResult.size() == 1) {
            return (T) searchResult.get(0);
        } else if (searchResult.isEmpty()) {
            throw new RuntimeException("No entity found with name: " + name);
        } else {
            throw new RuntimeException("Multiple entities found with name: " + name);
        }
    }
}
