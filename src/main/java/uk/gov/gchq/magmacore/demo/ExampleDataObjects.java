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

import static uk.gov.gchq.magmacore.util.DataObjectUtils.REF_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.USER_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.event;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Association;
import uk.gov.gchq.hqdm.model.ClassOfStateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.ClassOfStateOfPerson;
import uk.gov.gchq.hqdm.model.Event;
import uk.gov.gchq.hqdm.model.FunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfAssociation;
import uk.gov.gchq.hqdm.model.KindOfBiologicalSystemComponent;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystemComponent;
import uk.gov.gchq.hqdm.model.KindOfPerson;
import uk.gov.gchq.hqdm.model.Participant;
import uk.gov.gchq.hqdm.model.Person;
import uk.gov.gchq.hqdm.model.PossibleWorld;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.model.StateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.StateOfPerson;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.model.impl.AssociationImpl;
import uk.gov.gchq.hqdm.model.impl.ClassOfStateOfFunctionalSystemImpl;
import uk.gov.gchq.hqdm.model.impl.ClassOfStateOfPersonImpl;
import uk.gov.gchq.hqdm.model.impl.FunctionalSystemImpl;
import uk.gov.gchq.hqdm.model.impl.KindOfAssociationImpl;
import uk.gov.gchq.hqdm.model.impl.KindOfBiologicalSystemComponentImpl;
import uk.gov.gchq.hqdm.model.impl.KindOfFunctionalSystemComponentImpl;
import uk.gov.gchq.hqdm.model.impl.KindOfFunctionalSystemImpl;
import uk.gov.gchq.hqdm.model.impl.KindOfPersonImpl;
import uk.gov.gchq.hqdm.model.impl.ParticipantImpl;
import uk.gov.gchq.hqdm.model.impl.PersonImpl;
import uk.gov.gchq.hqdm.model.impl.PossibleWorldImpl;
import uk.gov.gchq.hqdm.model.impl.RoleImpl;
import uk.gov.gchq.hqdm.model.impl.StateOfFunctionalSystemImpl;
import uk.gov.gchq.hqdm.model.impl.StateOfPersonImpl;

/**
 *
 */
public final class ExampleDataObjects {

    private ExampleDataObjects() {}

    /**
     *
     * @return
     */
    public static final Dataset buildDataset() {
        final Model model = ModelFactory.createDefaultModel();

        createDataObjects().forEach(object -> {
            final Resource resource = model.createResource(object.getIri().toString());
            object.getPredicates()
                    .forEach((iri, predicates) -> predicates.forEach(predicate -> resource
                            .addProperty(model.createProperty(iri.toString()),
                                    predicate.toString())));
        });
        return DatasetFactory.create(model);
    }

    /**
     *
     * @return
     */
    public static List<Thing> createDataObjects() {
        final List<Thing> objects = new ArrayList<Thing>();

        // RDL CLASSES - Can be created, stored and queried separately.

        // Viewable is a class to assign other data objects to, to indicate that they are likely to
        // be of direct interest to a system user.
        final uk.gov.gchq.hqdm.model.Class viewable =
                new uk.gov.gchq.hqdm.model.impl.ClassImpl.Builder(
                        new IRI(REF_BASE, uid())).build();
        viewable.addStringValue(HQDM.ENTITY_NAME, "VIEWABLE");
        objects.add(viewable);

        // A sub-set of the Viewable class.
        final uk.gov.gchq.hqdm.model.Class viewableObject =
                new uk.gov.gchq.hqdm.model.impl.ClassImpl.Builder(
                        new IRI(REF_BASE, uid())).has_Superclass(viewable).build();
        viewableObject.addStringValue(HQDM.ENTITY_NAME, "VIEWABLE_OBJECT");
        objects.add(viewableObject);

        // A sub-set of the Viewable Class for viewable Associations.
        final uk.gov.gchq.hqdm.model.Class viewableAssociation =
                new uk.gov.gchq.hqdm.model.impl.ClassImpl.Builder(
                        new IRI(REF_BASE, uid())).has_Superclass(viewable).build();
        viewableAssociation.addStringValue(HQDM.ENTITY_NAME, "VIEWABLE_ASSOCIATION");
        objects.add(viewableAssociation);

        // An system is composed of components so this is the class of components that a whole-life
        // person can have.
        final KindOfBiologicalSystemComponent kindOfBiologicalSystemHumanComponent =
                new KindOfBiologicalSystemComponentImpl.Builder(
                        new IRI(REF_BASE, uid())).build();
        kindOfBiologicalSystemHumanComponent.addStringValue(HQDM.ENTITY_NAME,
                "KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT");
        objects.add(kindOfBiologicalSystemHumanComponent);

        // A class of whole-life person (re-)created as Reference Data.
        final KindOfPerson kindOfPerson = new KindOfPersonImpl.Builder(new IRI(REF_BASE, uid()))
                .member__Of(viewableObject)
                .has_Component_By_Class_M(kindOfBiologicalSystemHumanComponent).build();
        kindOfPerson.addStringValue(HQDM.ENTITY_NAME, "KIND_OF_PERSON");
        objects.add(kindOfPerson);

        // A class of temporal part (state) of a (whole-life) person.
        final ClassOfStateOfPerson classOfStateOfPerson = new ClassOfStateOfPersonImpl.Builder(
                new IRI(REF_BASE, uid())).member__Of(viewableObject).build();
        classOfStateOfPerson.addStringValue(HQDM.ENTITY_NAME, "CLASS_OF_STATE_OF_PERSON");
        objects.add(classOfStateOfPerson);

        // A class of whole-life system that is a Building.
        final KindOfFunctionalSystem kindOfFunctionalSystemBuilding =
                new KindOfFunctionalSystemImpl(
                        new IRI(REF_BASE, uid()));
        kindOfFunctionalSystemBuilding.addStringValue(HQDM.ENTITY_NAME,
                "KIND_OF_FUNCTIONAL_SYSTEM_BUILDING");
        objects.add(kindOfFunctionalSystemBuilding);

        // A Domestic Property is a system composed of components (e.g. walls, floors, roof, front
        // door, etc). This is the class of those whole-life system components.
        final KindOfFunctionalSystemComponent kindOfFunctionalSystemDomesticPropertyComponent =
                new KindOfFunctionalSystemComponentImpl.Builder(
                        new IRI(REF_BASE, uid())).build();
        kindOfFunctionalSystemDomesticPropertyComponent.addStringValue(HQDM.ENTITY_NAME,
                "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT");
        objects.add(kindOfFunctionalSystemDomesticPropertyComponent);

        // The class of whole-life system that is domestic property.
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
                new KindOfFunctionalSystemImpl.Builder(
                        new IRI(REF_BASE, uid()))
                                .has_Superclass(kindOfFunctionalSystemBuilding)
                                .member__Of(viewableObject)
                                .has_Component_By_Class_M(
                                        kindOfFunctionalSystemDomesticPropertyComponent)
                                .build();
        kindOfFunctionalSystemDomesticProperty.addStringValue(HQDM.ENTITY_NAME,
                "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        objects.add(kindOfFunctionalSystemDomesticProperty);

        // The class of state of system whose members are temporal parts of domestic properties.
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
                new ClassOfStateOfFunctionalSystemImpl.Builder(
                        new IRI(REF_BASE, uid()))
                                .member__Of(viewableObject)
                                .build();
        classOfStateOfFunctionalSystemDomesticProperty.addStringValue(HQDM.ENTITY_NAME,
                "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        objects.add(classOfStateOfFunctionalSystemDomesticProperty);

        // The class of role that every member of class of person plays.
        final Role personRole = new RoleImpl.Builder(new IRI(REF_BASE, uid())).build();
        personRole.addStringValue(HQDM.ENTITY_NAME, "NATURAL_MEMBER_OF_SOCIETY_ROLE");
        objects.add(personRole);

        // The class of role that every member of class of domestic property plays.
        final Role domesticPropertyRole = new RoleImpl.Builder(new IRI(REF_BASE, uid())).build();
        domesticPropertyRole.addStringValue(HQDM.ENTITY_NAME,
                "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");
        objects.add(domesticPropertyRole);

        final Role domesticOccupantInPropertyRole = new RoleImpl.Builder(new IRI(REF_BASE, uid()))
                .has_Superclass(domesticPropertyRole).build();
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.
        domesticOccupantInPropertyRole.addStringValue(HQDM.ENTITY_NAME,
                "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        objects.add(domesticOccupantInPropertyRole);

        final Role occupierOfPropertyRole = new RoleImpl.Builder(new IRI(REF_BASE, uid()))
                .has_Superclass(classOfStateOfPerson).build();

        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.
        occupierOfPropertyRole.addStringValue(HQDM.ENTITY_NAME,
                "OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        objects.add(occupierOfPropertyRole);

        // Add the Association Types (Participants and Associations).
        final KindOfAssociation occupantInPropertyKindOfAssociation =
                new KindOfAssociationImpl.Builder(
                        new IRI(REF_BASE, uid()))
                                .member__Of(viewableAssociation)
                                .consists_Of_By_Class(domesticOccupantInPropertyRole)
                                .consists_Of_By_Class(occupierOfPropertyRole).build();
        occupantInPropertyKindOfAssociation.addStringValue(HQDM.ENTITY_NAME,
                "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final PossibleWorld possibleWorld = new PossibleWorldImpl(new IRI(USER_BASE, uid()));
        possibleWorld.addStringValue(HQDM.ENTITY_NAME, "Example1_World");
        objects.add(possibleWorld);

        // Person B Whole Life Object.
        final Event e1 = event("1991-02-18T00:00:00", possibleWorld, USER_BASE);
        final Person personB1 = new PersonImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of_Kind(kindOfPerson)
                .natural_Role_M(personRole)
                .part_Of_Possible_World_M(possibleWorld)
                .beginning(e1)
                .build();
        personB1.addStringValue(HQDM.ENTITY_NAME, "PersonB1_Bob");
        objects.add(e1);
        objects.add(personB1);

        // Person B states.
        final Event e2 = event("2020-08-15T17:50:00", possibleWorld, USER_BASE);
        final Event e3 = event("2020-08-15T19:21:00", possibleWorld, USER_BASE);
        final StateOfPerson personBs1 = new StateOfPersonImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of(classOfStateOfPerson)
                .part_Of_Possible_World_M(possibleWorld)
                .temporal_Part_Of(personB1)
                .beginning(e2)
                .ending(e3)
                .build();
        objects.add(e2);
        objects.add(e3);
        objects.add(personBs1);

        final Event e4 = event("2020-08-16T22:33:00", possibleWorld, USER_BASE);
        final Event e5 = event("2020-08-17T10:46:00", possibleWorld, USER_BASE);
        final StateOfPerson personBs2 = new StateOfPersonImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of(classOfStateOfPerson)
                .part_Of_Possible_World_M(possibleWorld)
                .temporal_Part_Of(personB1)
                .beginning(e4)
                .ending(e5)
                .build();
        objects.add(e4);
        objects.add(e5);
        objects.add(personBs2);

        // House B Whole Life Object.
        final Event e6 = event("1972-06-01T00:00:00", possibleWorld, USER_BASE);
        final FunctionalSystem houseB = new FunctionalSystemImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of_Kind(kindOfFunctionalSystemDomesticProperty)
                .part_Of_Possible_World_M(possibleWorld)
                .intended_Role_M(domesticPropertyRole)
                .beginning(e6)
                .build();
        objects.add(e6);
        objects.add(houseB);

        // States of house when Occupant personBs1 is present.
        final StateOfFunctionalSystem houseBs1 = new StateOfFunctionalSystemImpl.Builder(
                new IRI(USER_BASE, uid())).member_Of(classOfStateOfFunctionalSystemDomesticProperty)
                        .temporal_Part_Of(houseB)
                        .part_Of_Possible_World_M(possibleWorld)
                        .beginning(e2)
                        .ending(e3)
                        .build();
        objects.add(houseBs1);

        final StateOfFunctionalSystem houseBs2 = new StateOfFunctionalSystemImpl.Builder(
                new IRI(USER_BASE, uid())).member_Of(classOfStateOfFunctionalSystemDomesticProperty)
                        .temporal_Part_Of(houseB)
                        .part_Of_Possible_World_M(possibleWorld)
                        .beginning(e4)
                        .ending(e5)
                        .build();
        objects.add(houseBs2);

        // Add the Associations and map the states above to the appropriate participant objects.
        // If we had full has_superClass resolving in HQDM classes then this participant object
        // wouldn't be needed as the class occupierOfPropertyRole is also a sub-type of
        // state_of_person (see issues list).
        final Participant pPersonBs1 = new ParticipantImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of_Kind_M(occupierOfPropertyRole)
                .part_Of_Possible_World_M(possibleWorld)
                .temporal__Part_Of(personBs1)
                .beginning(e2)
                .ending(e3)
                .build();
        pPersonBs1.addStringValue(HQDM.ENTITY_NAME,
                "Note this is the state of person Bs1 that is participating the association");
        objects.add(pPersonBs1);

        final Participant pHouseBs1 = new ParticipantImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of_Kind_M(domesticOccupantInPropertyRole)
                .part_Of_Possible_World_M(possibleWorld)
                .temporal__Part_Of(houseBs1)
                .beginning(e2)
                .ending(e3).build();
        pHouseBs1.addStringValue(HQDM.ENTITY_NAME,
                "Note this is the state of houseBs1 that is participating in the association");
        objects.add(pHouseBs1);

        final Participant pPersonBs2 = new ParticipantImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of_Kind_M(occupierOfPropertyRole)
                .part_Of_Possible_World_M(possibleWorld)
                .temporal__Part_Of(personBs2)
                .beginning(e4)
                .ending(e5)
                .build();
        pPersonBs2.addStringValue(HQDM.ENTITY_NAME,
                "Note this is the state of person Bs2 that is participating in the association");
        objects.add(pPersonBs2);

        final Participant pHouseBs2 = new ParticipantImpl.Builder(new IRI(USER_BASE, uid()))
                .member_Of_Kind_M(domesticOccupantInPropertyRole)
                .part_Of_Possible_World_M(possibleWorld)
                .temporal__Part_Of(houseBs2)
                .beginning(e4)
                .ending(e5)
                .build();
        pHouseBs2.addStringValue(HQDM.ENTITY_NAME,
                "Note this is the state of houseBs2 that is participating in the association");
        objects.add(pHouseBs2);

        final Association houseOccupantPresentState1 =
                new AssociationImpl.Builder(new IRI(USER_BASE, uid()))
                        .member_Of_Kind_M(occupantInPropertyKindOfAssociation)
                        .consists_Of_Participant(pHouseBs1)
                        .consists_Of_Participant(pPersonBs1)
                        .part_Of_Possible_World_M(possibleWorld)
                        .beginning(e2)
                        .ending(e3)
                        .build();
        // Abbreviated to allow a string to be displayed against this class of 'relationship'.
        houseOccupantPresentState1.addStringValue(HQDM.ENTITY_NAME, "HouseOccupantPresent1");
        objects.add(houseOccupantPresentState1);

        final Association houseOccupantPresentState2 =
                new AssociationImpl.Builder(new IRI(USER_BASE, uid()))
                        .member_Of_Kind_M(occupantInPropertyKindOfAssociation)
                        .consists_Of_Participant(pHouseBs2)
                        .consists_Of_Participant(pPersonBs2)
                        .part_Of_Possible_World_M(possibleWorld)
                        .beginning(e4)
                        .ending(e5)
                        .build();
        // Abbreviated to allow a string to be displayed against this class of 'relationship'.
        houseOccupantPresentState2.addStringValue(HQDM.ENTITY_NAME, "HouseOccupantPresent2");
        objects.add(houseOccupantPresentState2);

        return objects;
    }
}
