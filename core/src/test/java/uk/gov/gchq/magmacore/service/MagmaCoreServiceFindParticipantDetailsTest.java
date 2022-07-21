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
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.model.Association;
import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.KindOfAssociation;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.StateOfPerson;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.dto.ParticipantDetails;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class MagmaCoreServiceFindParticipantDetailsTest {

    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    private static KindOfAssociation userAssociationKind;
    private static IRI userAssociationKindIri;
    private static Person person1;
    private static Person person2;
    private static FunctionalSystem system1;
    private static FunctionalSystem system2;
    private static IRI userRoleIri;
    private static Role userRole;
    private static IRI systemRoleIri;
    private static Role systemRole;
    private static StateOfPerson person1State1;
    private static StateOfFunctionalSystem system1State1;
    private static StateOfPerson person2State1;
    private static StateOfFunctionalSystem system2State1;
    private static StateOfPerson person1State2;
    private static StateOfPerson person2State2;
    private static StateOfPerson person2State3;
    private static StateOfPerson person3State1;

    /**
     * Check that the findParticipantDetails method works correctly.
     *
     * @throws MagmaCoreException on error
     */
    @Test
    public void testFindParticipantDetailsSuccess() throws MagmaCoreException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        createAssociationPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime("now");
        now.addValue(HQDM.ENTITY_NAME, LocalDateTime.now().toString());

        // Find the required Things by sign in a transaction.
        db.begin();
        final Set<ParticipantDetails> found1 = service.findParticipantDetails(person1, system1, userAssociationKind,
                now);
        final Set<ParticipantDetails> found2 = service.findParticipantDetails(person2, system2, userAssociationKind,
                now);
        db.commit();

        // Assert the results are correct.
        assertNotNull(found1);
        assertNotNull(found2);
        assertFalse(found1.isEmpty());
        assertFalse(found2.isEmpty());

        // Check the results for found1
        final Iterator<ParticipantDetails> found1Iter = found1.iterator();

        final ParticipantDetails found1Details1 = found1Iter.next();
        final ParticipantDetails found1Details2 = found1Iter.next();

        // Check that the participant IDs are correct (no assumptions about the order of ParticipantDetails
        // in the Set).
        assertTrue(person1State1.getId().equals(found1Details1.participant.getId())
                || system1State1.getId().equals(found1Details1.participant.getId()));
        assertTrue(person1State1.getId().equals(found1Details2.participant.getId())
                || system1State1.getId().equals(found1Details2.participant.getId()));

        // Check that the Roles are correct (no assumptions about the order of ParticipantDetails
        // in the Set).

        assertTrue(found1Details1.roles.contains(userRole) || found1Details1.roles.contains(systemRole));
        assertTrue(found1Details2.roles.contains(userRole) || found1Details2.roles.contains(systemRole));

        // Check the results for found2
        final Iterator<ParticipantDetails> found2Iter = found2.iterator();

        final ParticipantDetails found2Details1 = found2Iter.next();
        final ParticipantDetails found2Details2 = found2Iter.next();

        // Check that the participant IDs are correct (no assumptions about the order of ParticipantDetails
        // in the Set).
        assertTrue(person2State1.getId().equals(found2Details1.participant.getId())
                || system2State1.getId().equals(found2Details1.participant.getId()));
        assertTrue(person2State1.getId().equals(found2Details2.participant.getId())
                || system2State1.getId().equals(found2Details2.participant.getId()));

        // Check that the Roles are correct (no assumptions about the order of ParticipantDetails
        // in the Set).

        assertTrue(found2Details1.roles.contains(userRole) || found2Details1.roles.contains(systemRole));
        assertTrue(found2Details2.roles.contains(userRole) || found2Details2.roles.contains(systemRole));

        // Confirm that we can select the Person participant and get the correct Role.
        final Optional<ParticipantDetails> maybeDetails = found1
                .stream()
                .filter(participantDetails -> participantDetails.participant.hasThisValue(RDFS.RDF_TYPE,
                        HQDM.STATE_OF_PERSON))
                .findAny();
        assertTrue(maybeDetails.isPresent());
        maybeDetails.ifPresent(details -> {
            assertEquals(1, details.roles.size());
            assertTrue(details.roles.contains(userRole));
        });
    }

    /**
     * Create some Associations for the test.
     *
     * @param db a {@link MagmaCoreDatabase}
     */
    private void createAssociationPattern(final MagmaCoreDatabase db) {
        // Create KindOfAssociation objects
        userAssociationKindIri = new IRI(TEST_BASE, "userOfSystemKindOfAssociation");
        userAssociationKind = ClassServices.createKindOfAssociation(userAssociationKindIri.getIri());
        userAssociationKind.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ASSOCIATION);

        final IRI managerKindIri = new IRI(TEST_BASE, "managerKindOfAssociation");
        final KindOfAssociation managerKind = ClassServices
                .createKindOfAssociation(managerKindIri.getIri());
        managerKind.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ASSOCIATION);

        // Create Roles
        userRoleIri = new IRI(TEST_BASE, "userRole");
        userRole = ClassServices.createRole(userRoleIri.getIri());
        userRole.addValue(RDFS.RDF_TYPE, HQDM.ROLE);

        systemRoleIri = new IRI(TEST_BASE, "systemRole");
        systemRole = ClassServices.createRole(systemRoleIri.getIri());
        systemRole.addValue(RDFS.RDF_TYPE, HQDM.ROLE);

        final IRI managerRoleIri = new IRI(TEST_BASE, "managerRole");
        final Role managerRole = ClassServices.createRole(managerRoleIri.getIri());
        managerRole.addValue(RDFS.RDF_TYPE, HQDM.ROLE);

        final IRI workerRoleIri = new IRI(TEST_BASE, "workerRole");
        final Role workerRole = ClassServices.createRole(workerRoleIri.getIri());
        workerRole.addValue(RDFS.RDF_TYPE, HQDM.ROLE);

        // Create People
        final IRI person1Iri = new IRI(TEST_BASE, "person1");
        person1 = SpatioTemporalExtentServices.createPerson(person1Iri.getIri());
        person1.addValue(RDFS.RDF_TYPE, HQDM.PERSON);

        final IRI person2Iri = new IRI(TEST_BASE, "person2");
        person2 = SpatioTemporalExtentServices.createPerson(person2Iri.getIri());
        person2.addValue(RDFS.RDF_TYPE, HQDM.PERSON);

        final IRI person3Iri = new IRI(TEST_BASE, "person3");
        final Person person3 = SpatioTemporalExtentServices.createPerson(person3Iri.getIri());
        person3.addValue(RDFS.RDF_TYPE, HQDM.PERSON);

        // Create Systems
        final IRI system1Iri = new IRI(TEST_BASE, "system1");
        final IRI system2Iri = new IRI(TEST_BASE, "system2");
        system1 = SpatioTemporalExtentServices.createFunctionalSystem(system1Iri.getIri());
        system2 = SpatioTemporalExtentServices.createFunctionalSystem(system2Iri.getIri());

        // Create Events for the BEGINNING and ENDING of the Associations
        final PointInTime begin = SpatioTemporalExtentServices.createPointInTime(new IRI(TEST_BASE, "begin").getIri());
        final PointInTime end = SpatioTemporalExtentServices.createPointInTime(new IRI(TEST_BASE, "end").getIri());

        begin.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        end.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);

        begin.addStringValue(HQDM.ENTITY_NAME, LocalDateTime.now().minusDays(1L).toString());
        end.addStringValue(HQDM.ENTITY_NAME, LocalDateTime.now().plusDays(1L).toString());

        final IRI beginIri = new IRI(begin.getId());
        final IRI endIri = new IRI(end.getId());

        // Create associations of the right Kinds.
        final IRI person1UserOfSystem1Iri = new IRI(TEST_BASE, "person1UserOfSystem1");
        final IRI person2UserOfSystem2Iri = new IRI(TEST_BASE, "person2UserOfSystem2");
        final IRI person1WorkerForPerson2Iri = new IRI(TEST_BASE, "person1WorkerForPerson2");
        final IRI person2WorkerForPerson3Iri = new IRI(TEST_BASE, "person2WorkerForPerson3");

        final Association person1UserOfSystem1 = SpatioTemporalExtentServices
                .createAssociation(person1UserOfSystem1Iri.getIri());
        final Association person2UserOfSystem2 = SpatioTemporalExtentServices
                .createAssociation(person2UserOfSystem2Iri.getIri());
        final Association person1WorkerForPerson2 = SpatioTemporalExtentServices
                .createAssociation(person1WorkerForPerson2Iri.getIri());
        final Association person2WorkerForPerson3 = SpatioTemporalExtentServices
                .createAssociation(person2WorkerForPerson3Iri.getIri());

        person1UserOfSystem1.addValue(RDFS.RDF_TYPE, HQDM.ASSOCIATION);
        person2UserOfSystem2.addValue(RDFS.RDF_TYPE, HQDM.ASSOCIATION);
        person1WorkerForPerson2.addValue(RDFS.RDF_TYPE, HQDM.ASSOCIATION);
        person2WorkerForPerson3.addValue(RDFS.RDF_TYPE, HQDM.ASSOCIATION);

        person1UserOfSystem1.addValue(HQDM.MEMBER_OF_KIND, userAssociationKindIri);
        person2UserOfSystem2.addValue(HQDM.MEMBER_OF_KIND, userAssociationKindIri);
        person1WorkerForPerson2.addValue(HQDM.MEMBER_OF_KIND, managerKindIri);
        person2WorkerForPerson3.addValue(HQDM.MEMBER_OF_KIND, managerKindIri);

        person1UserOfSystem1.addValue(HQDM.BEGINNING, beginIri);
        person2UserOfSystem2.addValue(HQDM.BEGINNING, beginIri);
        person1WorkerForPerson2.addValue(HQDM.BEGINNING, beginIri);
        person2WorkerForPerson3.addValue(HQDM.BEGINNING, beginIri);

        person1UserOfSystem1.addValue(HQDM.ENDING, endIri);
        person2UserOfSystem2.addValue(HQDM.ENDING, endIri);
        person1WorkerForPerson2.addValue(HQDM.ENDING, endIri);
        person2WorkerForPerson3.addValue(HQDM.ENDING, endIri);

        person1State1 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "person1State1").getIri());
        person1State1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        person1State1.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        person1State1.addValue(HQDM.TEMPORAL_PART_OF, person1Iri);
        person1State1.addValue(HQDM.MEMBER_OF_KIND, userRoleIri);
        person1State1.addValue(HQDM.PARTICIPANT_IN, person1UserOfSystem1Iri);

        system1State1 = SpatioTemporalExtentServices
                .createStateOfFunctionalSystem(new IRI(TEST_BASE, "system1State1").getIri());
        system1State1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM);
        system1State1.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        system1State1.addValue(HQDM.TEMPORAL_PART_OF, system1Iri);
        system1State1.addValue(HQDM.MEMBER_OF_KIND, systemRoleIri);
        system1State1.addValue(HQDM.PARTICIPANT_IN, person1UserOfSystem1Iri);

        person2State1 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "person2State1").getIri());
        person2State1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        person2State1.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        person2State1.addValue(HQDM.TEMPORAL_PART_OF, person2Iri);
        person2State1.addValue(HQDM.MEMBER_OF_KIND, userRoleIri);
        person2State1.addValue(HQDM.PARTICIPANT_IN, person2UserOfSystem2Iri);

        system2State1 = SpatioTemporalExtentServices
                .createStateOfFunctionalSystem(new IRI(TEST_BASE, "system2State1").getIri());
        system2State1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM);
        system2State1.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        system2State1.addValue(HQDM.TEMPORAL_PART_OF, system2Iri);
        system2State1.addValue(HQDM.MEMBER_OF_KIND, systemRoleIri);
        system2State1.addValue(HQDM.PARTICIPANT_IN, person2UserOfSystem2Iri);

        person1State2 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "person1State2").getIri());
        person1State2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        person1State2.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        person1State2.addValue(HQDM.TEMPORAL_PART_OF, person1Iri);
        person1State2.addValue(HQDM.MEMBER_OF_KIND, workerRoleIri);
        person1State2.addValue(HQDM.PARTICIPANT_IN, person1WorkerForPerson2Iri);

        person2State2 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "person2State2").getIri());
        person2State2.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        person2State2.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        person2State2.addValue(HQDM.TEMPORAL_PART_OF, person2Iri);
        person2State2.addValue(HQDM.MEMBER_OF_KIND, managerRoleIri);
        person2State2.addValue(HQDM.PARTICIPANT_IN, person1WorkerForPerson2Iri);

        person2State3 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "person2State3").getIri());
        person2State3.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        person2State3.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        person2State3.addValue(HQDM.TEMPORAL_PART_OF, person2Iri);
        person2State3.addValue(HQDM.MEMBER_OF_KIND, workerRoleIri);
        person2State3.addValue(HQDM.PARTICIPANT_IN, person2WorkerForPerson3Iri);

        person3State1 = SpatioTemporalExtentServices
                .createStateOfPerson(new IRI(TEST_BASE, "person3State1").getIri());
        person3State1.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        person3State1.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        person3State1.addValue(HQDM.TEMPORAL_PART_OF, person3Iri);
        person3State1.addValue(HQDM.MEMBER_OF_KIND, managerRoleIri);
        person3State1.addValue(HQDM.PARTICIPANT_IN, person2WorkerForPerson3Iri);

        // Persist all objects
        db.begin();

        db.create(userAssociationKind);
        db.create(managerKind);
        db.create(userRole);
        db.create(systemRole);
        db.create(managerRole);
        db.create(workerRole);
        db.create(person1);
        db.create(person2);
        db.create(person3);
        db.create(system1);
        db.create(system2);
        db.create(begin);
        db.create(end);
        db.create(person1UserOfSystem1);
        db.create(person2UserOfSystem2);
        db.create(person1WorkerForPerson2);
        db.create(person2WorkerForPerson3);
        db.create(person1State1);
        db.create(person1State2);
        db.create(person2State1);
        db.create(person2State2);
        db.create(person2State3);
        db.create(person3State1);
        db.create(system1State1);
        db.create(system2State1);

        db.commit();
    }
}
