/*
 * Copyright 2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package uk.gov.gchq.magmacore.demo;

import java.util.List;

import uk.gov.gchq.hqdm.model.Description;
import uk.gov.gchq.hqdm.model.Pattern;
import uk.gov.gchq.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.transformation.DbChangeSet;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbTransformation;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 */
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
                    "Description By URL",
                    "English Speakers"
                    ));

        // Find the required classes, kinds, and roles.
        final var urlPattern = (Pattern) entities.get("URL Pattern");
        final var descriptionByUrl = (Description) entities.get("Description By URL");
        final var englishSpeakers = (RecognizingLanguageCommunity) entities.get("English Speakers");
        final var englishSpeakersIri = new IRI(englishSpeakers.getId());

        // Create IRIs for the new entities.
        final var possibleWorld = ExampleCommonUtils.mkUserBaseIri();
        final var person = ExampleCommonUtils.mkUserBaseIri();
        final var wikipediaSign = ExampleCommonUtils.mkUserBaseIri();
        final var brittanica = ExampleCommonUtils.mkUserBaseIri();
        final var biography = ExampleCommonUtils.mkUserBaseIri();
        final var stanford = ExampleCommonUtils.mkUserBaseIri();
        final var nationalGeographic = ExampleCommonUtils.mkUserBaseIri();
        final var repBySign = ExampleCommonUtils.mkUserBaseIri();
        final var e1 = ExampleCommonUtils.mkUserBaseIri();
        final var e2 = ExampleCommonUtils.mkUserBaseIri();

        // Create the set of DbCreateOperations
        final var creates = List.of(

                // Create the possible world that we are working in.
                new DbCreateOperation(possibleWorld, RDFS.RDF_TYPE, HQDM.POSSIBLE_WORLD.getIri()),
                new DbCreateOperation(possibleWorld, HQDM.ENTITY_NAME, "Example Signs World"),

                // Create the thing represented
                new DbCreateOperation(person, RDFS.RDF_TYPE, HQDM.PERSON.getIri()),
                new DbCreateOperation(person, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                // Create the signs that represent the thing
                new DbCreateOperation(wikipediaSign, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN.getIri()),
                new DbCreateOperation(wikipediaSign, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(wikipediaSign, HQDM.VALUE_, "https://en.wikipedia.org/wiki/Socrates"),
                new DbCreateOperation(wikipediaSign, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                new DbCreateOperation(brittanica, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN.getIri()),
                new DbCreateOperation(brittanica, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(brittanica, HQDM.VALUE_, "https://www.britannica.com/biography/Socrates"),
                new DbCreateOperation(brittanica, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                new DbCreateOperation(biography, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN.getIri()),
                new DbCreateOperation(biography, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(biography, HQDM.VALUE_, "https://www.biography.com/scholar/socrates"),
                new DbCreateOperation(biography, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                new DbCreateOperation(stanford, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN.getIri()),
                new DbCreateOperation(stanford, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(stanford, HQDM.VALUE_, "https://plato.stanford.edu/entries/socrates/"),
                new DbCreateOperation(stanford, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                new DbCreateOperation(nationalGeographic, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN.getIri()),
                new DbCreateOperation(nationalGeographic, HQDM.MEMBER_OF_, urlPattern.getId()),
                new DbCreateOperation(nationalGeographic, HQDM.VALUE_, "https://www.nationalgeographic.com/culture/article/socrates"),
                new DbCreateOperation(nationalGeographic, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                // Create the representation by signs
                new DbCreateOperation(repBySign, RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN.getIri()),
                new DbCreateOperation(repBySign, HQDM.MEMBER_OF_, descriptionByUrl.getId()),
                new DbCreateOperation(repBySign, HQDM.REPRESENTS, person.getIri()),
                new DbCreateOperation(repBySign, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),

                // Add beginning, ending, etc. from `association`
                new DbCreateOperation(e1, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e1, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),
                new DbCreateOperation(e1, HQDM.ENTITY_NAME, "2020-01-01T00:00:00"),

                new DbCreateOperation(e2, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e2, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),
                new DbCreateOperation(e2, HQDM.ENTITY_NAME, "2022-12-01T00:00:00"),

                new DbCreateOperation(repBySign, HQDM.BEGINNING, e1.getIri()),
                new DbCreateOperation(repBySign, HQDM.ENDING, e2.getIri()),

                // Add the participants
                new DbCreateOperation(englishSpeakersIri, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(wikipediaSign, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(brittanica, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(biography, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(stanford, HQDM.PARTICIPANT_IN, repBySign.getIri()),
                new DbCreateOperation(nationalGeographic, HQDM.PARTICIPANT_IN, repBySign.getIri())
                    );

        // Create a change set and return it.
        return new DbChangeSet(List.of(), creates);
    }
}
