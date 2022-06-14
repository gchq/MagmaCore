package uk.gov.gchq.magmacore;

import java.util.UUID;

import uk.gov.gchq.hqdm.model.Participant;
import uk.gov.gchq.hqdm.model.StateOfOrganization;
import uk.gov.gchq.hqdm.services.DynamicObjects;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;

/**
 * Demonstrate how to create a new class dynamically.
 */
public class McAssistMultInheritFromDataApp {

    /**
     * Example program for creating classes dynamically.
     *
     * @param args an array of Strings.
     */
    public static void main(final String[] args) {

        // Create a StateOfOrganization.
        final var orgState = SpatioTemporalExtentServices.createStateOfOrganization(UUID.randomUUID().toString());

        // Add the Participant interface and return it as a Participant.
        final Participant orgStateAsParticipant = 
            DynamicObjects.implementInterfaces(orgState, Participant.class, new java.lang.Class[]{
                Participant.class,
                StateOfOrganization.class
            });

        // Check that it was successful.
        if (orgStateAsParticipant instanceof Participant && orgStateAsParticipant instanceof StateOfOrganization) {
            System.out.println("Successfully implemented the Participant and StateOfOrganization interfaces.");
        } else {
            System.err.println("Faile to implement the Participant and StateOfOrganization interfaces.");
        }
    }
}
