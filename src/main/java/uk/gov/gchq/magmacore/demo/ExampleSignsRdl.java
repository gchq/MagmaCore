package uk.gov.gchq.magmacore.demo;

import java.util.Set;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.RDFS;
import uk.gov.gchq.magmacore.service.DbChangeSet;
import uk.gov.gchq.magmacore.service.DbCreateOperation;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleSignsRdl {

    /**
     * Create a DbChangeSet that adds the RDL.
     *
     * @return {@link DbChangeSet}
     */
    public static DbChangeSet createRefDataObjects() {

        // Create new unique IRIs for all the objects we need to create.
        final var urlPattern = ExampleCommonUtils.mkRefBaseIri();
        final var description = ExampleCommonUtils.mkRefBaseIri();

        // Add DbCreateOperations to create the objects and their properties.
        final var creates = Set.of(
                new DbCreateOperation(urlPattern, RDFS.RDF_TYPE, HQDM.PATTERN.getIri()),
                new DbCreateOperation(urlPattern, HQDM.ENTITY_NAME, "URL Pattern"),

                new DbCreateOperation(description, RDFS.RDF_TYPE, HQDM.DESCRIPTION.getIri()),
                new DbCreateOperation(description, HQDM.ENTITY_NAME, "Description By URL")
        );

        // Put the operations in a change set and return it.
        return new DbChangeSet(Set.of(), creates);
    }
}
