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

package uk.gov.gchq.magmacore.demo;

import java.util.List;

import uk.gov.gchq.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfPerson;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.transformation.DbChangeSet;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 */
public class ExampleIndividuals {

    /**
     * Create a DbChangeSet that adds the whole life individuals.
     *
     * @param mcService {@link MagmaCoreService}
     * @return {@link DbChangeSet}
     */
    public static DbChangeSet addWholeLifeIndividuals(final MagmaCoreService mcService) {

        final var entities = mcService.findByEntityNameInTransaction(List.of(
                    "KIND_OF_PERSON",
                    "NATURAL_MEMBER_OF_SOCIETY_ROLE",
                    "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY",
                    "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE"
                    ));

        // Find the required classes, kinds, and roles.
        final KindOfPerson kindOfPerson = (KindOfPerson) entities.get("KIND_OF_PERSON");
        final Role personRole = (Role) entities.get("NATURAL_MEMBER_OF_SOCIETY_ROLE");
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
            (KindOfFunctionalSystem) entities.get("KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role domesticPropertyRole =
            (Role) entities.get("ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");

        // Create IRIs for the objects we want to create.
        final var possibleWorld = ExampleCommonUtils.mkUserBaseIri();
        final var e1 = ExampleCommonUtils.mkUserBaseIri();
        final var e2 = ExampleCommonUtils.mkUserBaseIri();
        final var person = ExampleCommonUtils.mkUserBaseIri();
        final var house = ExampleCommonUtils.mkUserBaseIri();

        // Create DbCreateOperations to create the objects and their properties.
        final var creates = List.of(
                new DbCreateOperation(possibleWorld, RDFS.RDF_TYPE, HQDM.POSSIBLE_WORLD.getIri()),
                new DbCreateOperation(possibleWorld, HQDM.ENTITY_NAME, "Example1_World"),

                new DbCreateOperation(e1, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e1, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),
                new DbCreateOperation(e1, HQDM.ENTITY_NAME, "1991-02-18T00:00:00"),

                new DbCreateOperation(e2, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e2, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),
                new DbCreateOperation(e2, HQDM.ENTITY_NAME, "1972-06-01T00:00:00"),

                new DbCreateOperation(person, RDFS.RDF_TYPE, HQDM.PERSON.getIri()),
                new DbCreateOperation(person, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),
                new DbCreateOperation(person, HQDM.ENTITY_NAME, "PersonB1_Bob"),
                new DbCreateOperation(person, HQDM.MEMBER_OF_KIND, kindOfPerson.getId()),
                new DbCreateOperation(person, HQDM.NATURAL_ROLE, personRole.getId()),
                new DbCreateOperation(person, HQDM.BEGINNING, e1.getIri()),

                new DbCreateOperation(house, RDFS.RDF_TYPE, HQDM.FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(house, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri()),
                new DbCreateOperation(house, HQDM.ENTITY_NAME, "HouseB"),
                new DbCreateOperation(house, HQDM.MEMBER_OF_KIND, kindOfFunctionalSystemDomesticProperty.getId()),
                new DbCreateOperation(house, HQDM.INTENDED_ROLE, domesticPropertyRole.getId()),
                new DbCreateOperation(house, HQDM.BEGINNING, e2.getIri())
                    );

        // Create a change set and return it.
        return new DbChangeSet(List.of(), creates);
    }

    public ExampleIndividuals() {
    }
}
