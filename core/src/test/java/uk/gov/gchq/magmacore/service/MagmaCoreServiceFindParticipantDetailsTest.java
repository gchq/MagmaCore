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

import java.time.Instant;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.dto.ParticipantDetails;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class MagmaCoreServiceFindParticipantDetailsTest {

    /**
     * Check that the findParticipantDetails method works correctly.
     *
     * @throws MagmaCoreException on error
     */
    @Test
    public void testFindParticipantDetailsSuccess() throws MagmaCoreException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        AssociationPatternTestData.createAssociationPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Create the PointInTime we're looking for
        final PointInTime now = SpatioTemporalExtentServices.createPointInTime(new IRI("http://example.com/entity#now"));
        now.addStringValue(HQDM.ENTITY_NAME, Instant.now().toString());

        // Find the required Things by sign in a transaction.
        db.beginRead();
        final Set<ParticipantDetails> found1 = service.findParticipantDetails(
                AssociationPatternTestData.person1,
                AssociationPatternTestData.system1,
                AssociationPatternTestData.userAssociationKind,
                now);

        final Set<ParticipantDetails> found2 = service.findParticipantDetails(
                AssociationPatternTestData.person2,
                AssociationPatternTestData.system2,
                AssociationPatternTestData.userAssociationKind,
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

        assertTrue(AssociationPatternTestData.person1State1.getId().equals(found1Details1.participant.getId())
                || AssociationPatternTestData.system1State1.getId().equals(found1Details1.participant.getId()));
        assertTrue(AssociationPatternTestData.person1State1.getId().equals(found1Details2.participant.getId())
                || AssociationPatternTestData.system1State1.getId().equals(found1Details2.participant.getId()));

        // Check that the Roles are correct (no assumptions about the order of ParticipantDetails
        // in the Set).

        assertTrue(found1Details1.roles.contains(AssociationPatternTestData.userRole)
                || found1Details1.roles.contains(AssociationPatternTestData.systemRole));
        assertTrue(found1Details2.roles.contains(AssociationPatternTestData.userRole)
                || found1Details2.roles.contains(AssociationPatternTestData.systemRole));

        // Check the results for found2
        final Iterator<ParticipantDetails> found2Iter = found2.iterator();

        final ParticipantDetails found2Details1 = found2Iter.next();
        final ParticipantDetails found2Details2 = found2Iter.next();

        // Check that the participant IDs are correct (no assumptions about the order of ParticipantDetails
        // in the Set).
        assertTrue(AssociationPatternTestData.person2State1.getId().equals(found2Details1.participant.getId())
                || AssociationPatternTestData.system2State1.getId().equals(found2Details1.participant.getId()));
        assertTrue(AssociationPatternTestData.person2State1.getId().equals(found2Details2.participant.getId())
                || AssociationPatternTestData.system2State1.getId().equals(found2Details2.participant.getId()));

        // Check that the Roles are correct (no assumptions about the order of ParticipantDetails
        // in the Set).

        assertTrue(found2Details1.roles.contains(AssociationPatternTestData.userRole)
                || found2Details1.roles.contains(AssociationPatternTestData.systemRole));
        assertTrue(found2Details2.roles.contains(AssociationPatternTestData.userRole)
                || found2Details2.roles.contains(AssociationPatternTestData.systemRole));

        // Confirm that we can select the Person participant and get the correct Role.
        final Optional<ParticipantDetails> maybeDetails = found1
                .stream()
                .filter(participantDetails -> participantDetails.participant.hasThisValue(RDFS.RDF_TYPE,
                        HQDM.STATE_OF_PERSON))
                .findAny();
        assertTrue(maybeDetails.isPresent());
        maybeDetails.ifPresent(details -> {
            assertEquals(1, details.roles.size());
            assertTrue(details.roles.contains(AssociationPatternTestData.userRole));
        });
    }
}
