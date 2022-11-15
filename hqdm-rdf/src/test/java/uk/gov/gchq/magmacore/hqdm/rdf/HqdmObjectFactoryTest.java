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

package uk.gov.gchq.magmacore.hqdm.rdf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.rdf.util.Pair;

/**
 * Tests for the {@link HqdmObjectFactory}.
 */
public class HqdmObjectFactoryTest {

    /**
     * Test creating a new HQDM Object successfully.
     */
    @Test
    public void testCreateNewObjectSuccess() {
        final var personId = "person1";
        final var personIri = new IRI(HQDM.HQDM, personId);

        // Create a new person with an ENTITY_NAME.
        final var person = HqdmObjectFactory.create(HQDM.PERSON, personIri);
        person.addValue(HQDM.ENTITY_NAME, personId);

        // Assert the ENTITY_NAME and person IRI are as expected.
        assertNotNull(person);
        assertEquals(Set.of(personId), person.value(HQDM.ENTITY_NAME));
        assertEquals(personIri.getIri(), person.getId());
    }

    /**
     * Test creating a new HQDM Object that fails.
     */
    @Test(expected = HqdmException.class)
    public void testCreateNewObjectFail() {
        final var personId = "person1";
        final var personIri = new IRI(HQDM.HQDM, personId);

        // Create a new person.
        HqdmObjectFactory.create(new HqdmIri("http://bad/base#bad"), personIri);
    }

    /**
     * Test successfully creating an object with a list of predicates.
     */
    @Test
    public void testCreateObjectWithPredicatesSuccess() {
        final var personId = "person1";
        final var personIri = new IRI(HQDM.HQDM, personId);

        final var person = HqdmObjectFactory.create(personIri,
                List.of(new Pair<>(RDFS.RDF_TYPE, HQDM.PERSON),
                        new Pair<>(HQDM.ENTITY_NAME, personId),
                        new Pair<>(HQDM.MEMBER_OF_KIND, "PERSON_KIND")));

        // Assert the values are correct.
        assertNotNull(person);
        assertEquals(Set.of(personId), person.value(HQDM.ENTITY_NAME));
        assertEquals(Set.of("PERSON_KIND"), person.value(HQDM.MEMBER_OF_KIND));
        assertEquals(personIri.getIri(), person.getId());
    }

    /**
     * Test successfully creating an object with a list of predicates and multiple
     * HQDM interfaces as
     * rdf:type.
     */
    @Test
    public void testCreateObjectWithMultipleInterfacesSuccess() {
        final var personId = "person1";
        final var personIri = new IRI(HQDM.HQDM, personId);

        final var person = HqdmObjectFactory.create(personIri,
                List.of(new Pair<>(RDFS.RDF_TYPE, HQDM.PERSON),
                        new Pair<>(RDFS.RDF_TYPE, HQDM.PARTICIPANT),
                        new Pair<>(HQDM.ENTITY_NAME, personId)));

        // Assert the values are correct.
        assertNotNull(person);
        assertTrue(person instanceof Person);
        assertTrue(person instanceof Participant);
        assertEquals(Set.of(personId), person.value(HQDM.ENTITY_NAME));
        assertEquals(personIri.getIri(), person.getId());
    }

    /**
     * Test creating the enumerated_class entity type.
     */
    @Test
    public void testCreatEnumeratedClass() {
        final var id = "enum_class";
        final var iri = new IRI(HQDM.HQDM, id);
        final var clss = HqdmObjectFactory.create(HQDM.ENUMERATED_CLASS, iri);
        assertEquals(iri.getIri(), clss.getId());
    }
}
