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

package uk.gov.gchq.magmacore.examples.signs;

import static uk.gov.gchq.magmacore.examples.util.DemoUtils.USER_BASE;
import static uk.gov.gchq.magmacore.util.UID.uid;

import java.util.List;
import java.util.Map;

import uk.gov.gchq.magmacore.hqdm.model.Description;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.transformation.DbChangeSet;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbTransformation;

/**
 * Example signs.
 */
public class ExampleSigns {

    /**
     * A function that populates a database.
     *
     * @param mcService A {@link MagmaCoreService}.
     * @return {@link DbTransformation}.
     */
    public static DbTransformation populateExampleData(final MagmaCoreService mcService) {

        // Apply the transformation to the database. There are dependencies between these change sets
        // since they both depend on RDL being present, but also the occupancies depend on the
        // individuals being present, so each change set needs to be applied before the next one
        // can be created.
        final DbChangeSet rdlChangeSet = ExampleSignsRdl.createRefDataObjects();

        // Apply the DbChangeSet.
        mcService.runInTransaction(rdlChangeSet);

        // mcService now contains the RDL needed for the next DbChangeSet
        final DbChangeSet signsChangeSet = addSigns(mcService);

        // Apply the DbChangeSet.
        mcService.runInTransaction(signsChangeSet);
        //
        // Combine the DbChangeSets into a DbTransformation and return it as a record of the changes.
        return new DbTransformation(List.of(rdlChangeSet, signsChangeSet));
    }

    /**
     * Create a {@link DbChangeSet} to add the representation by sign.
     *
     * @param mcService {@link MagmaCoreService}.
     * @return {@link DbChangeSet}.
     */
    private static DbChangeSet addSigns(final MagmaCoreService mcService) {
        final Map<String, Thing> entities = mcService
                .findByEntityNameInTransaction(List.of("URL Pattern", "Description By URL", "English Speakers"));

        // Find the required classes, kinds, and roles.
        final Pattern urlPattern = (Pattern) entities.get("URL Pattern");
        final Description descriptionByUrl = (Description) entities.get("Description By URL");
        final RecognizingLanguageCommunity englishSpeakers = (RecognizingLanguageCommunity) entities
                .get("English Speakers");
        final IRI englishSpeakersIri = new IRI(englishSpeakers.getId());

        // Create IRIs for the new entities.
        final IRI possibleWorld = new IRI(USER_BASE, uid());
        final IRI person = new IRI(USER_BASE, uid());
        final IRI wikipediaSign = new IRI(USER_BASE, uid());
        final IRI britannica = new IRI(USER_BASE, uid());
        final IRI biography = new IRI(USER_BASE, uid());
        final IRI stanford = new IRI(USER_BASE, uid());
        final IRI nationalGeographic = new IRI(USER_BASE, uid());
        final IRI representationBySign = new IRI(USER_BASE, uid());
        final IRI startEvent = new IRI(USER_BASE, uid());
        final IRI endEvent = new IRI(USER_BASE, uid());

        // Create the set of DbCreateOperations.
        final List<DbCreateOperation> creates = List.of(

                // Create the possible world that we are working in.
                new DbCreateOperation(possibleWorld, RDFS.RDF_TYPE, HQDM.POSSIBLE_WORLD),
                new DbCreateOperation(possibleWorld, HQDM.ENTITY_NAME, "Example Signs World"),

                // Create the thing represented.
                new DbCreateOperation(person, RDFS.RDF_TYPE, HQDM.PERSON),
                new DbCreateOperation(person, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                // Create the signs that represent the thing.
                new DbCreateOperation(wikipediaSign, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN),
                new DbCreateOperation(wikipediaSign, HQDM.MEMBER_OF_, new IRI(urlPattern.getId())),
                new DbCreateOperation(wikipediaSign, HQDM.VALUE_, "https://en.wikipedia.org/wiki/Socrates"),
                new DbCreateOperation(wikipediaSign, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                new DbCreateOperation(britannica, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN),
                new DbCreateOperation(britannica, HQDM.MEMBER_OF_, new IRI(urlPattern.getId())),
                new DbCreateOperation(britannica, HQDM.VALUE_, "https://www.britannica.com/biography/Socrates"),
                new DbCreateOperation(britannica, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                new DbCreateOperation(biography, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN),
                new DbCreateOperation(biography, HQDM.MEMBER_OF_, new IRI(urlPattern.getId())),
                new DbCreateOperation(biography, HQDM.VALUE_, "https://www.biography.com/scholar/socrates"),
                new DbCreateOperation(biography, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                new DbCreateOperation(stanford, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN),
                new DbCreateOperation(stanford, HQDM.MEMBER_OF_, new IRI(urlPattern.getId())),
                new DbCreateOperation(stanford, HQDM.VALUE_, "https://plato.stanford.edu/entries/socrates/"),
                new DbCreateOperation(stanford, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                new DbCreateOperation(nationalGeographic, RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN),
                new DbCreateOperation(nationalGeographic, HQDM.MEMBER_OF_, new IRI(urlPattern.getId())),
                new DbCreateOperation(nationalGeographic, HQDM.VALUE_,
                        "https://www.nationalgeographic.com/culture/article/socrates"),
                new DbCreateOperation(nationalGeographic, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                // Create the representation by signs.
                new DbCreateOperation(representationBySign, RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN),
                new DbCreateOperation(representationBySign, HQDM.MEMBER_OF_, new IRI(descriptionByUrl.getId())),
                new DbCreateOperation(representationBySign, HQDM.REPRESENTS, person),
                new DbCreateOperation(representationBySign, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),

                // Add beginning, ending, etc. from `association`.
                new DbCreateOperation(startEvent, RDFS.RDF_TYPE, HQDM.EVENT),
                new DbCreateOperation(startEvent, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),
                new DbCreateOperation(startEvent, HQDM.ENTITY_NAME, "2020-01-01T00:00:00"),

                new DbCreateOperation(endEvent, RDFS.RDF_TYPE, HQDM.EVENT),
                new DbCreateOperation(endEvent, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),
                new DbCreateOperation(endEvent, HQDM.ENTITY_NAME, "2022-12-01T00:00:00"),

                new DbCreateOperation(representationBySign, HQDM.BEGINNING, startEvent),
                new DbCreateOperation(representationBySign, HQDM.ENDING, endEvent),

                // Add the participants.
                new DbCreateOperation(englishSpeakersIri, HQDM.PARTICIPANT_IN, representationBySign),
                new DbCreateOperation(wikipediaSign, HQDM.PARTICIPANT_IN, representationBySign),
                new DbCreateOperation(britannica, HQDM.PARTICIPANT_IN, representationBySign),
                new DbCreateOperation(biography, HQDM.PARTICIPANT_IN, representationBySign),
                new DbCreateOperation(stanford, HQDM.PARTICIPANT_IN, representationBySign),
                new DbCreateOperation(nationalGeographic, HQDM.PARTICIPANT_IN, representationBySign));

        // Create a change set and return it.
        return new DbChangeSet(List.of(), creates);
    }
}
