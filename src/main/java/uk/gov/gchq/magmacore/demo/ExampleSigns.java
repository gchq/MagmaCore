package uk.gov.gchq.magmacore.demo;

import java.util.List;
import java.util.Set;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.RDFS;
import uk.gov.gchq.hqdm.model.Description;
import uk.gov.gchq.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.service.DbChangeSet;
import uk.gov.gchq.magmacore.service.DbCreateOperation;
import uk.gov.gchq.magmacore.service.DbTransformation;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleSigns {

    /**
     * A function that populates a database.
     *
     * @param mcService a {@link MagmaCoreService}
     * @return {@link DbTransformation}
     */
    public static DbTransformation populateExampleData(final MagmaCoreService mcService) {

        // Apply the transformation to the database. There are dependencies between these change sets 
        // since they both depend on RDL being present, but also the occupancies depend on the 
        // individuals being present, so each change set needs to be applied before the next one 
        // can be created.
        final var rdlChangeSet = ExampleSignsRdl.createRefDataObjects();

        // Apply the DbChangeSet
        rdlChangeSet.apply(mcService);

        // mcService now contains the RDL needed for the next DbChangeSet
        final var signsChangeSet = addSigns(mcService);

        // Apply the DbChangeSet
        signsChangeSet.apply(mcService);
        //
        // Combine the DbChangeSets into a DbTransformation and return it as a record of the changes.
        return new DbTransformation(List.of(rdlChangeSet, signsChangeSet));
    }

    /**
     * Create a {@link DbChangeSet} to add the representation by sign.
     *
     * @param mcService {@link MagmaCoreService}
     * @return {@link DbChangeSet}
    */
    private static DbChangeSet addSigns(final MagmaCoreService mcService) {
        final var entities = mcService.findByEntityNameInTransaction(List.of(
                    "URL Pattern",
                    "Description By URL"
                    ));

        // Find the required classes, kinds, and roles.
        final var urlPattern = (Pattern) entities.get("URL Pattern");
        final var descriptionByUrl = (Description) entities.get("Description By URL");
        final var descriptionByUrlIri = new IRI(descriptionByUrl.getId());

        // Create IRIs for the new entities.
        final var person = ExampleCommonUtils.mkUserBaseIri();
        final var englishSpeakers = ExampleCommonUtils.mkUserBaseIri();
        final var wikipediaSign = ExampleCommonUtils.mkUserBaseIri();
        final var brittanica = ExampleCommonUtils.mkUserBaseIri();
        final var biography = ExampleCommonUtils.mkUserBaseIri();
        final var stanford = ExampleCommonUtils.mkUserBaseIri();
        final var nationalGeographic = ExampleCommonUtils.mkUserBaseIri();
        final var repBySign = ExampleCommonUtils.mkUserBaseIri();

        // Create the set of DbCreateOperations
        final var creates = Set.of(
                // Create the thing represented
                new DbCreateOperation(person, RDFS.RDF_TYPE, HQDM.PERSON.getIri()),

                // Create the community that recognizes the signs
                new DbCreateOperation(englishSpeakers, RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY.getIri()),
                new DbCreateOperation(englishSpeakers, HQDM.ENTITY_NAME, "English Speakers"),
                new DbCreateOperation(englishSpeakers, HQDM.PARTICIPANT_IN, repBySign.getIri()),

                // Create the signs that represent the thing
                new DbCreateOperation(wikipediaSign, RDFS.RDF_TYPE, HQDM.SIGN.getIri()),
                new DbCreateOperation(wikipediaSign, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(wikipediaSign, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(wikipediaSign, HQDM.VALUE_, "https://en.wikipedia.org/wiki/Socrates"),
               
                new DbCreateOperation(brittanica, RDFS.RDF_TYPE, HQDM.SIGN.getIri()),
                new DbCreateOperation(brittanica, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(brittanica, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(brittanica, HQDM.VALUE_, "https://www.britannica.com/biography/Socrates"),
               
                new DbCreateOperation(biography, RDFS.RDF_TYPE, HQDM.SIGN.getIri()),
                new DbCreateOperation(biography, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(biography, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(biography, HQDM.VALUE_, "https://www.biography.com/scholar/socrates"),
               
                new DbCreateOperation(stanford, RDFS.RDF_TYPE, HQDM.SIGN.getIri()),
                new DbCreateOperation(stanford, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(stanford, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(stanford, HQDM.VALUE_, "https://plato.stanford.edu/entries/socrates/"),
               
                new DbCreateOperation(nationalGeographic, RDFS.RDF_TYPE, HQDM.SIGN.getIri()),
                new DbCreateOperation(nationalGeographic, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(nationalGeographic, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(nationalGeographic, HQDM.VALUE_, "https://www.nationalgeographic.com/culture/article/socrates"),
               
                // Create the representation by signs
                new DbCreateOperation(repBySign, RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN.getIri()),
                new DbCreateOperation(repBySign, HQDM.MEMBER_OF_, descriptionByUrl.getId()),
                new DbCreateOperation(repBySign, HQDM.REPRESENTS, person.getIri()),

                // Link the description to the Pattern
                new DbCreateOperation(descriptionByUrlIri, HQDM.CONSISTS_OF_BY_CLASS, urlPattern.getId())

                );

        // Create a change set and return it.
        return new DbChangeSet(Set.of(), creates);
    }
}
