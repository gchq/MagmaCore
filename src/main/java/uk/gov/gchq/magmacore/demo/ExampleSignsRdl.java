package uk.gov.gchq.magmacore.demo;

import java.util.Set;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
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
        final var englishSpeakers = ExampleCommonUtils.mkUserBaseIri();

        // Add DbCreateOperations to create the objects and their properties.
        final var creates = Set.of(
                new DbCreateOperation(urlPattern, RDFS.RDF_TYPE, HQDM.PATTERN.getIri()),
                new DbCreateOperation(urlPattern, HQDM.ENTITY_NAME, "URL Pattern"),

                new DbCreateOperation(description, RDFS.RDF_TYPE, HQDM.DESCRIPTION.getIri()),
                new DbCreateOperation(description, HQDM.ENTITY_NAME, "Description By URL"),

                // Create the community that recognizes the signs
                new DbCreateOperation(englishSpeakers, RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY.getIri()),
                new DbCreateOperation(englishSpeakers, HQDM.ENTITY_NAME, "English Speakers"),

                // Link the description to the Pattern
                new DbCreateOperation(description, HQDM.CONSISTS_OF_BY_CLASS, urlPattern.getIri())

        );

        // Put the operations in a change set and return it.
        return new DbChangeSet(Set.of(), creates);
    }
}
