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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.StateOfPerson;
import uk.gov.gchq.magmacore.hqdm.model.StateOfSign;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class MagmaCoreServiceTest {

    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    private static RecognizingLanguageCommunity community1;
    private static RecognizingLanguageCommunity community2;
    private static Pattern pattern1;
    private static Pattern pattern2;
    private static Person person1;
    private static Person person2;
    private static StateOfPerson stateOfPerson1;
    private static StateOfPerson stateOfPerson2;

    /**
     * Test that triples can be deleted.
     */
    @Test
    public void test() {
        final MagmaCoreJenaDatabase database = new MagmaCoreJenaDatabase();

        final IRI individualIri = new IRI(TEST_BASE, "individual");
        final Individual individual = SpatioTemporalExtentServices.createIndividual(individualIri.getIri());

        individual.addValue(HQDM.MEMBER_OF, "classOfIndividual");

        database.begin();
        database.create(individual);
        database.commit();

        individual.removeValue(HQDM.MEMBER_OF, "classOfIndividual");

        database.begin();
        database.update(individual);
        database.commit();

        database.begin();
        final Thing individualFromDb = database.get(individualIri);
        database.commit();

        assertNull(individualFromDb);
    }

    /**
     * Test that findBySignValue can be used to find the right Things represented by a sign value for
     * the given {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} at the given
     * {@link uk.gov.gchq.magmacore.hqdm.model.PointInTime}.
     */
    @Test
    public void testFindBySignSuccess() throws MagmaCoreException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        createSignPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime("now");
        now.addValue(HQDM.ENTITY_NAME, LocalDateTime.now().toString());

        // Find the required Things by sign in a transaction.
        db.begin();
        final List<? extends Thing> found1 = service.findBySignValue(community1, pattern1, "person1", now);
        final List<? extends Thing> found2 = service.findBySignValue(community2, pattern2, "person2", now);
        db.commit();

        // Assert the results are correct.
        assertNotNull(found1);
        assertNotNull(found2);
        assertFalse(found1.isEmpty());
        assertFalse(found2.isEmpty());

        final StateOfPerson personState1 = (StateOfPerson) found1.iterator().next();
        final StateOfPerson personState2 = (StateOfPerson) found2.iterator().next();

        assertEquals(stateOfPerson1.getId(), personState1.getId());
        assertEquals(stateOfPerson2.getId(), personState2.getId());

        final Set<Object> parent1 = personState1.value(HQDM.TEMPORAL_PART_OF);
        final Set<Object> parent2 = personState2.value(HQDM.TEMPORAL_PART_OF);

        // Check that the `temporal_part_of` relationship is correct.
        assertEquals(1, parent1.size());
        assertEquals(1, parent2.size());

        assertEquals(person1.getId(), ((IRI) parent1.iterator().next()).getIri());
        assertEquals(person2.getId(), ((IRI) parent2.iterator().next()).getIri());
    }

    /**
     * Check that we get an empty result if the sign value is null.
     */
    @Test
    public void testFindBySignWithNullSignValue() throws MagmaCoreException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        createSignPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime("now");
        now.addValue(HQDM.ENTITY_NAME, LocalDateTime.now().toString());

        // Find the required Things by sign in a transaction.
        db.begin();
        final List<? extends Thing> found = service.findBySignValue(community1, pattern1, null, now);
        db.commit();

        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    /**
     * Check that we get an empty result if the pointInTime does not have an ENTITY_NAME.
     */
    @Test
    public void testFindBySignWithBadPointInTime() throws MagmaCoreException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        createSignPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime("now");

        // Find the required Things by sign in a transaction.
        db.begin();
        final List<? extends Thing> found = service.findBySignValue(community1, pattern1, "person1", now);
        db.commit();

        assertNotNull(found);
        assertTrue(found.isEmpty());
    }

    /**
     * Populate a {@link MagmaCoreDatabase} with an instance of the sign pattern.
     *
     * <p>
     * This will create two {@link RepresentationBySign} associations that each use a String to
     * represent a {@link StateOfPerson}, but for different {@link Pattern} and
     * {@link RecognizingLanguageCommunity} objects.
     * </p>
     *
     * @param db a {@link MagmaCoreDatabase}
     */
    private void createSignPattern(final MagmaCoreDatabase db) {

        // Create RecognizingLanguageCommunities
        final IRI community1Iri = new IRI(TEST_BASE, "community1");

        community1 = SpatioTemporalExtentServices
                .createRecognizingLanguageCommunity(community1Iri.getIri());
        community1.addValue(RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY);

        final IRI community2Iri = new IRI(TEST_BASE, "community2");
        community2 = SpatioTemporalExtentServices
                .createRecognizingLanguageCommunity(community2Iri.getIri());
        community2.addValue(RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY);

        // Create Patterns
        final IRI pattern1Iri = new IRI(TEST_BASE, "pattern1");
        pattern1 = SpatioTemporalExtentServices.createPattern(pattern1Iri.getIri());
        pattern1.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);

        final IRI pattern2Iri = new IRI(TEST_BASE, "pattern2");
        pattern2 = SpatioTemporalExtentServices.createPattern(pattern2Iri.getIri());
        pattern2.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);

        // Create RepresentationByPatterns
        final RepresentationByPattern repByPattern1 = SpatioTemporalExtentServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern1").getIri());
        repByPattern1.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        final RepresentationByPattern repByPattern2 = SpatioTemporalExtentServices
                .createRepresentationByPattern(new IRI(TEST_BASE, "repByPattern2").getIri());
        repByPattern2.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);

        // Add the relationships for the patterns and communities.

        repByPattern1.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, new IRI(community1.getId()));
        repByPattern2.addValue(HQDM.CONSISTS_OF_IN_MEMBERS, new IRI(community2.getId()));

        repByPattern1.addValue(HQDM.CONSISTS_OF_BY_CLASS, new IRI(pattern1.getId()));
        repByPattern2.addValue(HQDM.CONSISTS_OF_BY_CLASS, new IRI(pattern2.getId()));

        // Create People
        person1 = SpatioTemporalExtentServices.createPerson(new IRI(TEST_BASE, "person1").getIri());
        person1.addValue(RDFS.RDF_TYPE, HQDM.PERSON);

        person2 = SpatioTemporalExtentServices.createPerson(new IRI(TEST_BASE, "person2").getIri());
        person2.addValue(RDFS.RDF_TYPE, HQDM.PERSON);

        // Create States for the People
        stateOfPerson1 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson1").getIri());
        stateOfPerson1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson1.addValue(HQDM.TEMPORAL_PART_OF, new IRI(person1.getId()));

        stateOfPerson2 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "stateOfPerson2").getIri());
        stateOfPerson2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        stateOfPerson2.addValue(HQDM.TEMPORAL_PART_OF, new IRI(person2.getId()));

        // Create signs
        final Sign sign1 = SpatioTemporalExtentServices.createSign(new IRI(TEST_BASE, "sign1").getIri());
        sign1.addValue(RDFS.RDF_TYPE, HQDM.SIGN);
        sign1.addValue(HQDM.MEMBER_OF_, pattern1Iri);
        sign1.addValue(HQDM.VALUE_, "person1");

        final Sign sign2 = SpatioTemporalExtentServices.createSign(new IRI(TEST_BASE, "sign2").getIri());
        sign2.addValue(RDFS.RDF_TYPE, HQDM.SIGN);
        sign2.addValue(HQDM.MEMBER_OF_, pattern2Iri);
        sign2.addValue(HQDM.VALUE_, "person2");

        // Create states for the Signs
        final StateOfSign stateOfSign1 = SpatioTemporalExtentServices
                .createStateOfSign(new IRI(TEST_BASE, "stateOfSign1").getIri());
        stateOfSign1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN);
        stateOfSign1.addValue(HQDM.TEMPORAL_PART_OF, new IRI(sign1.getId()));

        final StateOfSign stateOfSign2 = SpatioTemporalExtentServices
                .createStateOfSign(new IRI(TEST_BASE, "stateOfSign2").getIri());
        stateOfSign2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN);
        stateOfSign2.addValue(HQDM.TEMPORAL_PART_OF, new IRI(sign2.getId()));

        // Create Events for the BEGINNING and ENDING of the RepresentationBySigns
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(TEST_BASE, "begin").getIri());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(TEST_BASE, "end").getIri());

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);

        begin.addStringValue(HQDM.ENTITY_NAME, LocalDateTime.now().minusDays(1L).toString());
        end.addStringValue(HQDM.ENTITY_NAME, LocalDateTime.now().plusDays(1L).toString());

        final IRI objectId = new IRI(begin.getId());
        final IRI objectId2 = new IRI(end.getId());

        // Create RepresentationBySigns
        final RepresentationBySign repBySign1 = SpatioTemporalExtentServices
                .createRepresentationBySign(new IRI(TEST_BASE, "repBySign1").getIri());
        repBySign1.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN);
        repBySign1.addValue(HQDM.REPRESENTS, new IRI(stateOfPerson1.getId()));
        repBySign1.addValue(HQDM.MEMBER_OF_, new IRI(repByPattern1.getId()));
        repBySign1.addValue(HQDM.BEGINNING, objectId);
        repBySign1.addValue(HQDM.ENDING, objectId2);
        community1.addValue(HQDM.PARTICIPANT_IN, new IRI(repBySign1.getId()));
        stateOfSign1.addValue(HQDM.PARTICIPANT_IN, new IRI(repBySign1.getId()));

        final RepresentationBySign repBySign2 = SpatioTemporalExtentServices
                .createRepresentationBySign(new IRI(TEST_BASE, "repBySign2").getIri());
        repBySign2.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN);
        repBySign2.addValue(HQDM.REPRESENTS, new IRI(stateOfPerson2.getId()));
        repBySign2.addValue(HQDM.MEMBER_OF_, new IRI(repByPattern2.getId()));
        repBySign2.addValue(HQDM.BEGINNING, objectId);
        repBySign2.addValue(HQDM.ENDING, objectId2);
        community2.addValue(HQDM.PARTICIPANT_IN, new IRI(repBySign2.getId()));
        stateOfSign2.addValue(HQDM.PARTICIPANT_IN, new IRI(repBySign2.getId()));

        // Persist all objects
        db.begin();

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
