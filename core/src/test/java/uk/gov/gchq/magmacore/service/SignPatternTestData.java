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

package uk.gov.gchq.magmacore.service;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.StateOfPerson;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class SignPatternTestData {

    public static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    static RecognizingLanguageCommunity community1;
    static RecognizingLanguageCommunity community2;
    static Pattern pattern1;
    static Pattern pattern2;
    static Pattern pattern3;
    static Person person1;
    static Person person2;
    static Person person3;
    static StateOfPerson stateOfPerson1;
    static StateOfPerson stateOfPerson2;
    static StateOfPerson stateOfPerson3;
    static IRI kindOfPersonIri;
    static IRI classOfPersonIri;

    /**
     * Populate a {@link MagmaCoreDatabase} with an instance of the sign pattern.
     *
     * <p>
     * This will create two {@link RepresentationBySign} associations that each use
     * a String to
     * represent a {@link StateOfPerson}, but for different {@link Pattern} and
     * {@link RecognizingLanguageCommunity} objects.
     * </p>
     *
     * @param db A {@link MagmaCoreDatabase}.
     */
    static void createSignPattern(final MagmaCoreDatabase db) {

        // Create RecognizingLanguageCommunities
        final IRI community1Iri = new IRI(TEST_BASE, "community1");

        community1 = SpatioTemporalExtentServices.createRecognizingLanguageCommunity(community1Iri);
        community1.addValue(RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY);

        final IRI community2Iri = new IRI(TEST_BASE, "community2");
        community2 = SpatioTemporalExtentServices.createRecognizingLanguageCommunity(community2Iri);
        community2.addValue(RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY);

        // Create Patterns
        final IRI pattern1Iri = new IRI(TEST_BASE, "pattern1");
        pattern1 = ClassServices.createPattern(pattern1Iri);
        pattern1.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);
        pattern1.addStringValue(HQDM.SKOS_DEFINITION, "person1");

        final IRI pattern2Iri = new IRI(TEST_BASE, "pattern2");
        pattern2 = ClassServices.createPattern(pattern2Iri);
        pattern2.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);
        pattern2.addStringValue(HQDM.SKOS_DEFINITION, "person2");

        final IRI pattern3Iri = new IRI(TEST_BASE, "pattern3");
        pattern3 = ClassServices.createPattern(pattern3Iri);
        pattern3.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);
        pattern3.addStringValue(HQDM.SKOS_DEFINITION, "person3");

        // Create RepresentationByPatterns
        final RepresentationByPattern repByPattern1 = ClassServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern1"));
        repByPattern1.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        final RepresentationByPattern repByPattern2 = ClassServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern2"));
        repByPattern2.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        final RepresentationByPattern repByPattern3 = ClassServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern3"));
        repByPattern3.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        // Add the relationships for the patterns and communities.
        repByPattern1.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, community1.getId());
        repByPattern2.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, community2.getId());
        repByPattern3.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, community2.getId());

        repByPattern1.addValue(HQDM.CONSISTS_OF_BY_CLASS, pattern1.getId());
        repByPattern2.addValue(HQDM.CONSISTS_OF_BY_CLASS, pattern2.getId());
        repByPattern3.addValue(HQDM.CONSISTS_OF_BY_CLASS, pattern3.getId());

        // Create KindOfPerson
        kindOfPersonIri = new IRI(TEST_BASE, "kindOfPerson1");

        // Create a classOfPerson.
        classOfPersonIri = new IRI(TEST_BASE, "class1OfPerson");

        // Create People
        person1 = SpatioTemporalExtentServices.createPerson(new IRI(TEST_BASE, "person1"));
        person1.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person1.addValue(HQDM.MEMBER_OF_KIND, kindOfPersonIri);
        person1.addValue(HQDM.MEMBER_OF, classOfPersonIri);

        person2 = SpatioTemporalExtentServices.createPerson(new IRI(TEST_BASE, "person2"));
        person2.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person2.addValue(HQDM.MEMBER_OF_KIND, kindOfPersonIri);
        person2.addValue(HQDM.MEMBER_OF, classOfPersonIri);

        person3 = SpatioTemporalExtentServices.createPerson(new IRI(TEST_BASE, "person3"));
        person3.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        person3.addValue(HQDM.MEMBER_OF_KIND, kindOfPersonIri);
        person3.addValue(HQDM.MEMBER_OF, classOfPersonIri);

        // Create States for the People
        stateOfPerson1 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson1"));
        stateOfPerson1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson1.addValue(HQDM.TEMPORAL_PART_OF, person1.getId());

        stateOfPerson2 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson2"));
        stateOfPerson2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson2.addValue(HQDM.TEMPORAL_PART_OF, person2.getId());

        stateOfPerson3 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson3"));
        stateOfPerson3.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson3.addValue(HQDM.TEMPORAL_PART_OF, person3.getId());

        repByPattern1.addValue(HQDM.REPRESENTED, stateOfPerson1.getId());
        repByPattern2.addValue(HQDM.REPRESENTED, stateOfPerson2.getId());
        repByPattern3.addValue(HQDM.REPRESENTED, stateOfPerson3.getId());
        // Persist all objects
        db.beginWrite();

        db.create(community1);
        db.create(community2);
        db.create(pattern1);
        db.create(pattern2);
        db.create(pattern3);
        db.create(repByPattern1);
        db.create(repByPattern2);
        db.create(repByPattern3);
        db.create(person1);
        db.create(person2);
        db.create(person3);
        db.create(stateOfPerson1);
        db.create(stateOfPerson2);
        db.create(stateOfPerson3);

        db.commit();
    }
}
