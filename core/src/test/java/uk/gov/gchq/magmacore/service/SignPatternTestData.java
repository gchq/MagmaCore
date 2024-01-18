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

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.StateOfPerson;
import uk.gov.gchq.magmacore.hqdm.model.StateOfSign;
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

    static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    static RecognizingLanguageCommunity community1;
    static RecognizingLanguageCommunity community2;
    static Pattern pattern1;
    static Pattern pattern2;
    static Person person1;
    static Person person2;
    static StateOfPerson stateOfPerson1;
    static StateOfPerson stateOfPerson2;
    static IRI kindOfPersonIri;
    static IRI classOfPersonIri;

    /**
     * Populate a {@link MagmaCoreDatabase} with an instance of the sign pattern.
     *
     * <p>
     * This will create two {@link RepresentationBySign} associations that each use a String to
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

        final IRI pattern2Iri = new IRI(TEST_BASE, "pattern2");
        pattern2 = ClassServices.createPattern(pattern2Iri);
        pattern2.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);

        // Create RepresentationByPatterns
        final RepresentationByPattern repByPattern1 = ClassServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern1"));
        repByPattern1.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        final RepresentationByPattern repByPattern2 = ClassServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern2"));
        repByPattern2.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        // Add the relationships for the patterns and communities.

        repByPattern1.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, community1.getId());
        repByPattern2.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, community2.getId());

        repByPattern1.addValue(HQDM.CONSISTS_OF_BY_CLASS, pattern1.getId());
        repByPattern2.addValue(HQDM.CONSISTS_OF_BY_CLASS, pattern2.getId());

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

        // Create States for the People
        stateOfPerson1 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson1"));
        stateOfPerson1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson1.addValue(HQDM.TEMPORAL_PART_OF, person1.getId());

        stateOfPerson2 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson2"));
        stateOfPerson2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson2.addValue(HQDM.TEMPORAL_PART_OF, person2.getId());

        // Create signs
        final Sign sign1 = SpatioTemporalExtentServices.createSign(new IRI(TEST_BASE, "sign1"));
        sign1.addValue(RDFS.RDF_TYPE, HQDM.SIGN);
        sign1.addValue(HQDM.MEMBER_OF_, pattern1Iri);
        sign1.addValue(HQDM.VALUE_, "person1");

        final Sign sign2 = SpatioTemporalExtentServices.createSign(new IRI(TEST_BASE, "sign2"));
        sign2.addValue(RDFS.RDF_TYPE, HQDM.SIGN);
        sign2.addValue(HQDM.MEMBER_OF_, pattern2Iri);
        sign2.addValue(HQDM.VALUE_, "person2");

        // Create states for the Signs
        final StateOfSign stateOfSign1 = SpatioTemporalExtentServices
                .createStateOfSign(new IRI(TEST_BASE, "stateOfSign1"));
        stateOfSign1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN);
        stateOfSign1.addValue(HQDM.TEMPORAL_PART_OF, sign1.getId());

        final StateOfSign stateOfSign2 = SpatioTemporalExtentServices
                .createStateOfSign(new IRI(TEST_BASE, "stateOfSign2"));
        stateOfSign2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN);
        stateOfSign2.addValue(HQDM.TEMPORAL_PART_OF, sign2.getId());

        // Create Events for the BEGINNING and ENDING of the RepresentationBySigns
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(TEST_BASE, "begin"));
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(TEST_BASE, "end"));

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);

        begin.addStringValue(HQDM.ENTITY_NAME, LocalDateTime.now().minusDays(1L).toInstant(ZoneOffset.UTC).toString());
        end.addStringValue(HQDM.ENTITY_NAME, LocalDateTime.now().plusDays(1L).toInstant(ZoneOffset.UTC).toString());

        final IRI objectId = begin.getId();
        final IRI objectId2 = end.getId();

        // Create RepresentationBySigns
        final RepresentationBySign repBySign1 = SpatioTemporalExtentServices
                .createRepresentationBySign(new IRI(TEST_BASE, "repBySign1"));
        repBySign1.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN);
        repBySign1.addValue(HQDM.REPRESENTS, stateOfPerson1.getId());
        repBySign1.addValue(HQDM.MEMBER_OF_, repByPattern1.getId());
        repBySign1.addValue(HQDM.BEGINNING, objectId);
        repBySign1.addValue(HQDM.ENDING, objectId2);
        community1.addValue(HQDM.PARTICIPANT_IN, repBySign1.getId());
        stateOfSign1.addValue(HQDM.PARTICIPANT_IN, repBySign1.getId());

        final RepresentationBySign repBySign2 = SpatioTemporalExtentServices
                .createRepresentationBySign(new IRI(TEST_BASE, "repBySign2"));
        repBySign2.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN);
        repBySign2.addValue(HQDM.REPRESENTS, stateOfPerson2.getId());
        repBySign2.addValue(HQDM.MEMBER_OF_, repByPattern2.getId());
        repBySign2.addValue(HQDM.BEGINNING, objectId);
        repBySign2.addValue(HQDM.ENDING, objectId2);
        community2.addValue(HQDM.PARTICIPANT_IN, repBySign2.getId());
        stateOfSign2.addValue(HQDM.PARTICIPANT_IN, repBySign2.getId());

        // Persist all objects
        db.beginWrite();

        db.create(community1);
        db.create(community2);
        db.create(pattern1);
        db.create(pattern2);
        db.create(repByPattern1);
        db.create(repByPattern2);
        db.create(person1);
        db.create(person2);
        db.create(stateOfPerson1);
        db.create(stateOfPerson2);
        db.create(sign1);
        db.create(sign2);
        db.create(stateOfSign1);
        db.create(stateOfSign2);
        db.create(begin);
        db.create(end);
        db.create(repBySign1);
        db.create(repBySign2);

        db.commit();
    }
}
