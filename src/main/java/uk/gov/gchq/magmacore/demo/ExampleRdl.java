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

import static uk.gov.gchq.magmacore.demo.DemoUtils.REF_BASE;
import static uk.gov.gchq.magmacore.util.UID.uid;

import java.util.List;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.transformation.DbChangeSet;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;

/**
 * Example reference data.
 */
public class ExampleRdl {

    /**
     * Create a DbChangeSet that adds the RDL.
     *
     * @return {@link DbChangeSet}.
     */
    public static DbChangeSet createRefDataObjects() {

        // Create new unique IRIs for all the objects we need to create.
        final IRI viewable = new IRI(REF_BASE, uid());
        final IRI viewableObject = new IRI(REF_BASE, uid());
        final IRI viewableAssociation = new IRI(REF_BASE, uid());
        final IRI kindOfBiologicalSystemHumanComponent = new IRI(REF_BASE, uid());
        final IRI kindOfPerson = new IRI(REF_BASE, uid());
        final IRI classOfStateOfPerson = new IRI(REF_BASE, uid());
        final IRI kindOfFunctionalSystemBuilding = new IRI(REF_BASE, uid());
        final IRI kindOfFunctionalSystemDomesticPropertyComponent = new IRI(REF_BASE,
                "kindOfFunctionalSystemDomesticPropertyComponent");
        final IRI kindOfFunctionalSystemDomesticProperty = new IRI(REF_BASE, uid());
        final IRI classOfStateOfFunctionalSystemDomesticProperty = new IRI(REF_BASE,
                "classOfStateOfFunctionalSystemDomesticProperty");
        final IRI naturalMemberOfSocietyRole = new IRI(REF_BASE, uid());
        final IRI domesticPropertyRole = new IRI(REF_BASE, uid());
        final IRI domesticOccupantInPropertyRole = new IRI(REF_BASE, uid());
        final IRI occupierOfPropertyRole = new IRI(REF_BASE, "occupierOfPropertyRole ");
        final IRI occupantInPropertyKindOfAssociation = new IRI(REF_BASE, uid());

        // Add DbCreateOperations to create the objects and their properties.
        final List<DbCreateOperation> creates = List.of(
                new DbCreateOperation(viewable, RDFS.RDF_TYPE, HQDM.CLASS.getIri()),
                new DbCreateOperation(viewable, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(viewable, HQDM.ENTITY_NAME, "VIEWABLE"),

                new DbCreateOperation(viewableObject, RDFS.RDF_TYPE, HQDM.CLASS.getIri()),
                new DbCreateOperation(viewableObject, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(viewableObject, HQDM.ENTITY_NAME, "VIEWABLE_OBJECT"),

                new DbCreateOperation(viewableAssociation, RDFS.RDF_TYPE, HQDM.CLASS.getIri()),
                new DbCreateOperation(viewableAssociation, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(viewableAssociation, HQDM.ENTITY_NAME, "VIEWABLE_ASSOCIATION"),

                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, RDFS.RDF_TYPE,
                        HQDM.KIND_OF_BIOLOGICAL_SYSTEM_COMPONENT.getIri()),
                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, HQDM.ENTITY_NAME,
                        "KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT"),

                new DbCreateOperation(kindOfPerson, RDFS.RDF_TYPE, HQDM.KIND_OF_PERSON.getIri()),
                new DbCreateOperation(kindOfPerson, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfPerson, HQDM.ENTITY_NAME, "KIND_OF_PERSON"),

                new DbCreateOperation(classOfStateOfPerson, RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_PERSON.getIri()),
                new DbCreateOperation(classOfStateOfPerson, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(classOfStateOfPerson, HQDM.ENTITY_NAME, "CLASS_OF_STATE_OF_PERSON"),

                new DbCreateOperation(kindOfFunctionalSystemBuilding, RDFS.RDF_TYPE,
                        HQDM.KIND_OF_FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemBuilding, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemBuilding, HQDM.ENTITY_NAME,
                        "KIND_OF_FUNCTIONAL_SYSTEM_BUILDING"),

                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, RDFS.RDF_TYPE,
                        HQDM.KIND_OF_FUNCTIONAL_SYSTEM_COMPONENT.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, RDFS.RDF_TYPE,
                        RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, HQDM.ENTITY_NAME,
                        "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT"),

                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE,
                        HQDM.KIND_OF_FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, HQDM.ENTITY_NAME,
                        "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY"),

                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE,
                        HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE,
                        RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, HQDM.ENTITY_NAME,
                        "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY"),

                new DbCreateOperation(naturalMemberOfSocietyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(naturalMemberOfSocietyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(naturalMemberOfSocietyRole, HQDM.ENTITY_NAME, "NATURAL_MEMBER_OF_SOCIETY_ROLE"),

                new DbCreateOperation(domesticPropertyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(domesticPropertyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(domesticPropertyRole, HQDM.ENTITY_NAME,
                        "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE"),

                new DbCreateOperation(domesticOccupantInPropertyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(domesticOccupantInPropertyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(domesticOccupantInPropertyRole, HQDM.ENTITY_NAME,
                        "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE"),

                new DbCreateOperation(occupierOfPropertyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, HQDM.ENTITY_NAME, "OCCUPIER_LOCATED_IN_PROPERTY_ROLE"),

                new DbCreateOperation(occupantInPropertyKindOfAssociation, RDFS.RDF_TYPE,
                        HQDM.KIND_OF_ASSOCIATION.getIri()),
                new DbCreateOperation(occupantInPropertyKindOfAssociation, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(occupantInPropertyKindOfAssociation, HQDM.ENTITY_NAME,
                        "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION"),

                // Create the class hierarchy.
                new DbCreateOperation(viewableObject, HQDM.HAS_SUPERCLASS, viewable.getIri()),
                new DbCreateOperation(viewableObject, RDFS.RDFS_SUB_CLASS_OF, viewable.getIri()),

                new DbCreateOperation(viewableAssociation, HQDM.HAS_SUPERCLASS, viewable.getIri()),
                new DbCreateOperation(viewableAssociation, RDFS.RDFS_SUB_CLASS_OF, viewable.getIri()),

                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, HQDM.HAS_SUPERCLASS,
                        kindOfFunctionalSystemBuilding.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, RDFS.RDFS_SUB_CLASS_OF,
                        kindOfFunctionalSystemBuilding.getIri()),

                new DbCreateOperation(domesticOccupantInPropertyRole, HQDM.HAS_SUPERCLASS,
                        domesticPropertyRole.getIri()),
                new DbCreateOperation(domesticOccupantInPropertyRole, RDFS.RDFS_SUB_CLASS_OF,
                        domesticPropertyRole.getIri()),

                new DbCreateOperation(occupierOfPropertyRole, HQDM.HAS_SUPERCLASS, classOfStateOfPerson.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, RDFS.RDFS_SUB_CLASS_OF, classOfStateOfPerson.getIri()),

                // Set class memberships.
                new DbCreateOperation(kindOfPerson, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(classOfStateOfPerson, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, HQDM.MEMBER_OF,
                        viewableObject.getIri()),
                new DbCreateOperation(occupantInPropertyKindOfAssociation, HQDM.MEMBER_OF,
                        viewableAssociation.getIri()),

                // Set the has component by class predicates.
                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, HQDM.HAS_COMPONENT_BY_CLASS,
                        kindOfPerson.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, HQDM.HAS_COMPONENT_BY_CLASS,
                        kindOfFunctionalSystemDomesticProperty.getIri()),

                // Set the consists of by class predicates.
                new DbCreateOperation(domesticOccupantInPropertyRole, HQDM.CONSISTS_OF_BY_CLASS,
                        occupantInPropertyKindOfAssociation.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, HQDM.CONSISTS_OF_BY_CLASS,
                        occupantInPropertyKindOfAssociation.getIri()));

        // Put the operations in a change set and return it.
        return new DbChangeSet(List.of(), creates);
    }
}
