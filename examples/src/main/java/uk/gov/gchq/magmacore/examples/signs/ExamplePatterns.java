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
 * <p>
 * This actually uses patterns to represent a person.
 * </p>
 */
public class ExamplePatterns {

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
        mcService.runInWriteTransaction(rdlChangeSet);

        // mcService now contains the RDL needed for the next DbChangeSet
        final DbChangeSet signsChangeSet = addPatterns(mcService);

        // Apply the DbChangeSet.
        mcService.runInWriteTransaction(signsChangeSet);
        
        // Combine the DbChangeSets into a DbTransformation and return it as a record of the changes.
        return new DbTransformation(List.of(rdlChangeSet, signsChangeSet));
    }

    /**
     * Create a {@link DbChangeSet} to add the representation by sign.
     *
     * @param mcService {@link MagmaCoreService}.
     * @return {@link DbChangeSet}.
     */
    private static DbChangeSet addPatterns(final MagmaCoreService mcService) {
        final Map<String, Thing> entities = mcService
                .findByEntityNameInTransaction(List.of("English Speakers"));

        // Find the required classes, kinds, and roles.
        final RecognizingLanguageCommunity englishSpeakers = (RecognizingLanguageCommunity) entities
                .get("English Speakers");
        final IRI englishSpeakersIri = englishSpeakers.getId();

        // Create IRIs for the new entities.
        final IRI possibleWorld = new IRI(USER_BASE, uid());
        final IRI person = new IRI(USER_BASE, uid());
        final IRI wikipediaPattern = new IRI(USER_BASE, uid());
        final IRI britannicaPattern = new IRI(USER_BASE, uid());
        final IRI biographyPattern = new IRI(USER_BASE, uid());
        final IRI stanfordPattern = new IRI(USER_BASE, uid());
        final IRI nationalGeographicPattern = new IRI(USER_BASE, uid());
        final IRI startEvent = new IRI(USER_BASE, uid());
        final IRI endEvent = new IRI(USER_BASE, uid());

        final IRI representationByWikipediaPattern = new IRI(USER_BASE, uid());
        final IRI representationByBritannicaPattern = new IRI(USER_BASE, uid());
        final IRI representationByBiographyPattern = new IRI(USER_BASE, uid());
        final IRI representationByStanfordPattern = new IRI(USER_BASE, uid());
        final IRI representationByNationalGeographicPattern = new IRI(USER_BASE, uid());

        // Create the set of DbCreateOperations.
        final List<DbCreateOperation> creates = List.of(

                // Create the possible world that we are working in.
                new DbCreateOperation(possibleWorld, RDFS.RDF_TYPE, HQDM.POSSIBLE_WORLD),
                new DbCreateOperation(possibleWorld, HQDM.ENTITY_NAME, "Example Signs World"),

                // Create the thing represented.
                new DbCreateOperation(person, RDFS.RDF_TYPE, HQDM.PERSON),
                new DbCreateOperation(person, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),
                new DbCreateOperation(person, HQDM.BEGINNING, startEvent),
                new DbCreateOperation(person, HQDM.ENDING, endEvent),

                // Create the signs that represent the thing.
                new DbCreateOperation(wikipediaPattern, RDFS.RDF_TYPE, HQDM.PATTERN),
                new DbCreateOperation(wikipediaPattern, HQDM.SKOS_DEFINITION, "https://en.wikipedia.org/wiki/Socrates"),

                new DbCreateOperation(britannicaPattern, RDFS.RDF_TYPE, HQDM.PATTERN),
                new DbCreateOperation(britannicaPattern, HQDM.SKOS_DEFINITION, "https://www.britannica.com/biography/Socrates"),

                new DbCreateOperation(biographyPattern, RDFS.RDF_TYPE, HQDM.PATTERN),
                new DbCreateOperation(biographyPattern, HQDM.SKOS_DEFINITION, "https://www.biography.com/scholar/socrates"),

                new DbCreateOperation(stanfordPattern, RDFS.RDF_TYPE, HQDM.PATTERN),
                new DbCreateOperation(stanfordPattern, HQDM.SKOS_DEFINITION, "https://plato.stanford.edu/entries/socrates/"),

                new DbCreateOperation(nationalGeographicPattern, RDFS.RDF_TYPE, HQDM.PATTERN),
                new DbCreateOperation(nationalGeographicPattern, HQDM.SKOS_DEFINITION,
                        "https://www.nationalgeographic.com/culture/article/socrates"),

                // Create the representation by patterns.
                new DbCreateOperation(representationByWikipediaPattern, RDFS.RDF_TYPE, HQDM.IDENTIFICATION),
                new DbCreateOperation(representationByWikipediaPattern, HQDM.CONSISTS_OF_IN_MEMBERS, 
                        englishSpeakersIri),
                new DbCreateOperation(representationByWikipediaPattern, HQDM.CONSISTS_OF_BY_CLASS, wikipediaPattern),
                new DbCreateOperation(representationByWikipediaPattern, HQDM.REPRESENTED, person),

                new DbCreateOperation(representationByBritannicaPattern, RDFS.RDF_TYPE, HQDM.IDENTIFICATION),
                new DbCreateOperation(representationByBritannicaPattern, HQDM.CONSISTS_OF_IN_MEMBERS, 
                        englishSpeakersIri),
                new DbCreateOperation(representationByBritannicaPattern, HQDM.CONSISTS_OF_BY_CLASS, britannicaPattern),
                new DbCreateOperation(representationByBritannicaPattern, HQDM.REPRESENTED, person),

                new DbCreateOperation(representationByBiographyPattern, RDFS.RDF_TYPE, HQDM.IDENTIFICATION),
                new DbCreateOperation(representationByBiographyPattern, HQDM.CONSISTS_OF_IN_MEMBERS, 
                        englishSpeakersIri),
                new DbCreateOperation(representationByBiographyPattern, HQDM.CONSISTS_OF_BY_CLASS, biographyPattern),
                new DbCreateOperation(representationByBiographyPattern, HQDM.REPRESENTED, person),

                new DbCreateOperation(representationByStanfordPattern, RDFS.RDF_TYPE, HQDM.IDENTIFICATION),
                new DbCreateOperation(representationByStanfordPattern, HQDM.CONSISTS_OF_IN_MEMBERS, 
                        englishSpeakersIri),
                new DbCreateOperation(representationByStanfordPattern, HQDM.CONSISTS_OF_BY_CLASS, stanfordPattern),
                new DbCreateOperation(representationByStanfordPattern, HQDM.REPRESENTED, person),

                new DbCreateOperation(representationByNationalGeographicPattern, RDFS.RDF_TYPE, HQDM.IDENTIFICATION),
                new DbCreateOperation(representationByNationalGeographicPattern, HQDM.CONSISTS_OF_IN_MEMBERS, 
                        englishSpeakersIri),
                new DbCreateOperation(representationByNationalGeographicPattern, HQDM.CONSISTS_OF_BY_CLASS, 
                        nationalGeographicPattern),
                new DbCreateOperation(representationByNationalGeographicPattern, HQDM.REPRESENTED, person),

                // Add beginning, ending, etc. from `association`.
                new DbCreateOperation(startEvent, RDFS.RDF_TYPE, HQDM.EVENT),
                new DbCreateOperation(startEvent, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),
                new DbCreateOperation(startEvent, HQDM.SKOS_DEFINITION, "2020-01-01T00:00:00"),

                new DbCreateOperation(endEvent, RDFS.RDF_TYPE, HQDM.EVENT),
                new DbCreateOperation(endEvent, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld),
                new DbCreateOperation(endEvent, HQDM.SKOS_DEFINITION, "2022-12-01T00:00:00")
                );

        // Create a change set and return it.
        return new DbChangeSet(List.of(), creates);
    }
}
