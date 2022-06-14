package uk.gov.gchq.magmacore;

import java.util.List;
import java.util.UUID;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.RDFS;
import uk.gov.gchq.hqdm.model.Participant;
import uk.gov.gchq.hqdm.model.StateOfOrganization;
import uk.gov.gchq.hqdm.rdf.HqdmObjectFactory;
import uk.gov.gchq.hqdm.rdf.Pair;

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

        // Create a new type specification.
        final List<Pair<String, String>> newTypeSpecification = List.of(
            new Pair<>(RDFS.RDF_TYPE.getIri(), HQDM.STATE_OF_ORGANIZATION.getIri()),
            new Pair<>(RDFS.RDF_TYPE.getIri(), HQDM.PARTICIPANT.getIri())
        );

        // Create a new object using the type specification.
        final var orgState = HqdmObjectFactory.create(UUID.randomUUID().toString(), newTypeSpecification);

        // Check that it implements the two interfaces.
        if (orgState instanceof Participant && orgState instanceof StateOfOrganization) {
            System.out.println("Successfully implemented the Participant and StateOfOrganization interfaces.");
        } else {
            System.err.println("Faile to implement the Participant and StateOfOrganization interfaces.");
        }
    }
}
