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

package uk.gov.gchq.magmacore.examples.service;

import static uk.gov.gchq.magmacore.util.UID.uid;

import java.util.List;

import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.StateOfOrganization;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.HqdmObjectFactory;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.rdf.util.Pair;
import uk.gov.gchq.magmacore.hqdm.rdf.util.Triples;

/**
 * Demonstrate how to create a new class dynamically.
 */
public class McAssistMultInheritFromDataApp {

    /**
     * Example program for creating classes dynamically.
     *
     * @param args An array of Strings.
     */
    public static void main(final String[] args) {

        // Create a new type specification.
        final List<Pair<String, String>> newTypeSpecification = List.of(
                new Pair<>(RDFS.RDF_TYPE.getIri(), HQDM.STATE_OF_ORGANIZATION.getIri()),
                new Pair<>(RDFS.RDF_TYPE.getIri(), HQDM.PARTICIPANT.getIri()));

        // Create a new object using the type specification.
        final Thing orgState = HqdmObjectFactory.create(uid(), newTypeSpecification);

        // Check that it implements the two interfaces.
        if (orgState instanceof Participant && orgState instanceof StateOfOrganization) {
            System.out.println("Successfully implemented the Participant and StateOfOrganization interfaces.");
        } else {
            System.err.println("Failed to implement the Participant and StateOfOrganization interfaces.");
        }

        // DIsplay the object as triples.
        System.out.println("Result as TTL triples:");
        System.out.println(Triples.toTriples(orgState));
    }
}
