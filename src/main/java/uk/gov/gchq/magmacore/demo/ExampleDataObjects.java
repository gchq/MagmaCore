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

import static uk.gov.gchq.hqdm.iri.HQDM.ENTITY_NAME;
import static uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices.event;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.REF_BASE;
import static uk.gov.gchq.magmacore.util.DataObjectUtils.USER_BASE;
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
import uk.gov.gchq.hqdm.services.ClassServices;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;

/**
 * Constructs a set of example HQDM objects for demonstrating Magma Core.
 */
public final class ExampleDataObjects {

    private ExampleDataObjects() {}

    /**
     * Creates and populates a Jena dataset with the example data objects.
     *
     * @return The populated Jena dataset.
     */
    public static final Dataset buildDataset() {
        final Model model = ModelFactory.createDefaultModel();

        createDataObjects().forEach(object -> {
            final Resource resource = model.createResource(object.getId());
            object.getPredicates()
                    .forEach((iri, predicates) -> predicates.forEach(predicate -> resource
                            .addProperty(model.createProperty(iri.toString()),
                                    predicate.toString())));
        });
        return DatasetFactory.create(model);
    }

    /**
     * Generates a set of example data objects using HQDM.
     *
     * @return A list of HQDM objects.
     */
    public static List<Thing> createDataObjects() {
        final List<Thing> objects = new ArrayList<>();

        // RDL CLASSES - Can be created, stored and queried separately.

        // Viewable is a class to assign other data objects to, to indicate that they are likely to
        // be of direct interest to a system user.
        final uk.gov.gchq.hqdm.model.Class viewable = ClassServices.createClass(new IRI(REF_BASE, uid()).toString());
        viewable.addStringValue(ENTITY_NAME.toString(), "VIEWABLE");
        objects.add(viewable);

        // A sub-set of the Viewable class.
        final uk.gov.gchq.hqdm.model.Class viewableObject = ClassServices.createClass(new IRI(REF_BASE, uid()).toString());
        viewableObject.addValue(HQDM.HAS_SUPERCLASS.toString(), viewable.getId());
        viewableObject.addStringValue(ENTITY_NAME.toString(), "VIEWABLE_OBJECT");
        objects.add(viewableObject);

        // A sub-set of the Viewable Class for viewable Associations.
        final uk.gov.gchq.hqdm.model.Class viewableAssociation =
                ClassServices.createClass(new IRI(REF_BASE, uid()).toString());
        viewableAssociation.addValue(HQDM.HAS_SUPERCLASS.toString(), viewable.getId());
        viewableAssociation.addStringValue(ENTITY_NAME.toString(), "VIEWABLE_ASSOCIATION");
        objects.add(viewableAssociation);

        // An system is composed of components so this is the class of components that a whole-life
        // person can have.
        final KindOfBiologicalSystemComponent kindOfBiologicalSystemHumanComponent =
                ClassServices.createKindOfBiologicalSystemComponent(new IRI(REF_BASE, uid()).toString());

        kindOfBiologicalSystemHumanComponent.addStringValue(ENTITY_NAME.toString(),
                "KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT");
        objects.add(kindOfBiologicalSystemHumanComponent);

        // A class of whole-life person (re-)created as Reference Data.
        final KindOfPerson kindOfPerson = ClassServices.createKindOfPerson(new IRI(REF_BASE, uid()).toString());

        kindOfPerson.addValue(HQDM.MEMBER__OF.toString(), viewableObject.getId());
        kindOfPerson.addValue(HQDM.HAS_COMPONENT_BY_CLASS.toString(), kindOfBiologicalSystemHumanComponent.getId());
        kindOfPerson.addStringValue(ENTITY_NAME.toString(), "KIND_OF_PERSON");
        objects.add(kindOfPerson);

        // A class of temporal part (state) of a (whole-life) person.
        final ClassOfStateOfPerson classOfStateOfPerson = ClassServices.createClassOfStateOfPerson(new IRI(REF_BASE, uid()).toString());

        classOfStateOfPerson.addValue(HQDM.MEMBER__OF.toString(), viewableObject.getId());
        classOfStateOfPerson.addStringValue(ENTITY_NAME.toString(), "CLASS_OF_STATE_OF_PERSON");
        objects.add(classOfStateOfPerson);

        // A class of whole-life system that is a Building.
        final KindOfFunctionalSystem kindOfFunctionalSystemBuilding =
                ClassServices.createKindOfFunctionalSystem(new IRI(REF_BASE, uid()).toString());

        kindOfFunctionalSystemBuilding.addStringValue(ENTITY_NAME.toString(),
                "KIND_OF_FUNCTIONAL_SYSTEM_BUILDING");
        objects.add(kindOfFunctionalSystemBuilding);

        // A Domestic Property is a system composed of components (e.g. walls, floors, roof, front
        // door, etc). This is the class of those whole-life system components.
        final KindOfFunctionalSystemComponent kindOfFunctionalSystemDomesticPropertyComponent =
                ClassServices.createKindOfFunctionalSystemComponent(new IRI(REF_BASE, uid()).toString());

        kindOfFunctionalSystemDomesticPropertyComponent.addStringValue(ENTITY_NAME.toString(),
                "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT");
        objects.add(kindOfFunctionalSystemDomesticPropertyComponent);

        // The class of whole-life system that is domestic property.
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
                ClassServices.createKindOfFunctionalSystem(new IRI(REF_BASE, uid()).toString());

        kindOfFunctionalSystemDomesticProperty.addValue(HQDM.HAS_SUPERCLASS.toString(), kindOfFunctionalSystemBuilding.getId());
        kindOfFunctionalSystemDomesticProperty.addValue(HQDM.MEMBER__OF.toString(), viewableObject.getId());
        kindOfFunctionalSystemDomesticProperty.addValue(HQDM.HAS_COMPONENT_BY_CLASS.toString(), kindOfFunctionalSystemDomesticPropertyComponent.getId());
        kindOfFunctionalSystemDomesticProperty.addStringValue(ENTITY_NAME.toString(),
                "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        objects.add(kindOfFunctionalSystemDomesticProperty);

        // The class of state of system whose members are temporal parts of domestic properties.
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
                ClassServices.createClassOfStateOfFunctionalSystem(new IRI(REF_BASE, uid()).toString());

        classOfStateOfFunctionalSystemDomesticProperty.addValue(HQDM.MEMBER__OF.toString(), viewableObject.getId());
        classOfStateOfFunctionalSystemDomesticProperty.addStringValue(ENTITY_NAME.toString(),
                "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        objects.add(classOfStateOfFunctionalSystemDomesticProperty);

        // The class of role that every member of class of person plays.
        final Role personRole = ClassServices.createRole(new IRI(REF_BASE, uid()).toString());
        personRole.addStringValue(ENTITY_NAME.toString(), "NATURAL_MEMBER_OF_SOCIETY_ROLE");
        objects.add(personRole);

        // The class of role that every member of class of domestic property plays.
        final Role domesticPropertyRole = ClassServices.createRole(new IRI(REF_BASE, uid()).toString());
        domesticPropertyRole.addStringValue(ENTITY_NAME.toString(),
                "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");
        objects.add(domesticPropertyRole);

        final Role domesticOccupantInPropertyRole = ClassServices.createRole(new IRI(REF_BASE, uid()).toString());
        domesticOccupantInPropertyRole.addValue(HQDM.HAS_SUPERCLASS.toString(), domesticPropertyRole.getId());
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.
        domesticOccupantInPropertyRole.addStringValue(ENTITY_NAME.toString(),
                "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        objects.add(domesticOccupantInPropertyRole);

        final Role occupierOfPropertyRole = ClassServices.createRole(new IRI(REF_BASE, uid()).toString());
        occupierOfPropertyRole.addValue(HQDM.HAS_SUPERCLASS.toString(), classOfStateOfPerson.getId());

        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.
        occupierOfPropertyRole.addStringValue(ENTITY_NAME.toString(),
                "OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        objects.add(occupierOfPropertyRole);

        // Add the Association Types (Participants and Associations).
        final KindOfAssociation occupantInPropertyKindOfAssociation =
                ClassServices.createKindOfAssociation(new IRI(REF_BASE, uid()).toString());

        occupantInPropertyKindOfAssociation.addValue(HQDM.MEMBER__OF.toString(), viewableAssociation.getId());
        occupantInPropertyKindOfAssociation.addValue(HQDM.CONSISTS_OF_BY_CLASS.toString(), domesticOccupantInPropertyRole.getId());
        occupantInPropertyKindOfAssociation.addValue(HQDM.CONSISTS_OF_BY_CLASS.toString(), occupierOfPropertyRole.getId());
        occupantInPropertyKindOfAssociation.addStringValue(ENTITY_NAME.toString(),
                "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final PossibleWorld possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(new IRI(USER_BASE, uid()).toString());
        possibleWorld.addStringValue(ENTITY_NAME.toString(), "Example1_World");
        objects.add(possibleWorld);

        // Person B Whole Life Object.
        final Event e1 = event(new IRI(USER_BASE, "1991-02-18T00:00:00").toString());
        e1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        final Person personB1 = SpatioTemporalExtentServices.createPerson(new IRI(USER_BASE, uid()).toString());

        personB1.addValue(HQDM.MEMBER_OF_KIND.toString(), kindOfPerson.getId());
        personB1.addValue(HQDM.NATURAL_ROLE.toString(), personRole.getId());
        personB1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        personB1.addValue(HQDM.BEGINNING.toString(), e1.getId());
        personB1.addStringValue(ENTITY_NAME.toString(), "PersonB1_Bob");
        objects.add(e1);
        objects.add(personB1);

        // Person B states.
        final Event e2 = event(new IRI(USER_BASE, "2020-08-15T17:50:00").toString());
        e2.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        final Event e3 = event(new IRI(USER_BASE, "2020-08-15T19:21:00").toString());
        e3.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        final StateOfPerson personBs1 = SpatioTemporalExtentServices.createStateOfPerson(new IRI(USER_BASE, uid()).toString());

        personBs1.addValue(HQDM.MEMBER_OF.toString(), classOfStateOfPerson.getId());
        personBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        personBs1.addValue(HQDM.TEMPORAL_PART_OF.toString(), personB1.getId());
        personBs1.addValue(HQDM.BEGINNING.toString(), e2.getId());
        personBs1.addValue(HQDM.ENDING.toString(), e3.getId());
        objects.add(e2);
        objects.add(e3);
        objects.add(personBs1);

        final Event e4 = event(new IRI(USER_BASE, "2020-08-16T22:33:00").toString());
        e4.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        final Event e5 = event(new IRI(USER_BASE, "2020-08-17T10:46:00").toString());
        e5.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        final StateOfPerson personBs2 = SpatioTemporalExtentServices.createStateOfPerson(new IRI(USER_BASE, uid()).toString());

        personBs2.addValue(HQDM.MEMBER_OF.toString(), classOfStateOfPerson.getId());
        personBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        personBs2.addValue(HQDM.TEMPORAL_PART_OF.toString(), personB1.getId());
        personBs2.addValue(HQDM.BEGINNING.toString(), e4.getId());
        personBs2.addValue(HQDM.ENDING.toString(), e5.getId());
        objects.add(e4);
        objects.add(e5);
        objects.add(personBs2);

        // House B Whole Life Object.
        final Event e6 = event(new IRI(USER_BASE, "1972-06-01T00:00:00").toString());
        e6.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        final FunctionalSystem houseB = SpatioTemporalExtentServices.createFunctionalSystem(new IRI(USER_BASE, uid()).toString());

        houseB.addValue(HQDM.MEMBER_OF_KIND.toString(), kindOfFunctionalSystemDomesticProperty.getId());
        houseB.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        houseB.addValue(HQDM.INTENDED_ROLE.toString(), domesticPropertyRole.getId());
        houseB.addValue(HQDM.BEGINNING.toString(), e6.getId());
        objects.add(e6);
        objects.add(houseB);

        // States of house when Occupant personBs1 is present.
        final StateOfFunctionalSystem houseBs1 = SpatioTemporalExtentServices.createStateOfFunctionalSystem(
                new IRI(USER_BASE, uid()).toString());

        houseBs1.addValue(HQDM.MEMBER_OF.toString(), classOfStateOfFunctionalSystemDomesticProperty.getId());
        houseBs1.addValue(HQDM.TEMPORAL_PART_OF.toString(), houseB.getId());
        houseBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        houseBs1.addValue(HQDM.BEGINNING.toString(), e2.getId());
        houseBs1.addValue(HQDM.ENDING.toString(), e3.getId());
        objects.add(houseBs1);

        final StateOfFunctionalSystem houseBs2 = SpatioTemporalExtentServices.createStateOfFunctionalSystem(new IRI(USER_BASE, uid()).toString());

        houseBs2.addValue(HQDM.MEMBER_OF.toString(), classOfStateOfFunctionalSystemDomesticProperty.getId());
        houseBs2.addValue(HQDM.TEMPORAL_PART_OF.toString(), houseB.getId());
        houseBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        houseBs2.addValue(HQDM.BEGINNING.toString(), e4.getId());
        houseBs2.addValue(HQDM.ENDING.toString(), e5.getId());
        objects.add(houseBs2);

        // Add the Associations and map the states above to the appropriate participant objects.
        // If we had full has_superClass resolving in HQDM classes then this participant object
        // wouldn't be needed as the class occupierOfPropertyRole is also a sub-type of
        // state_of_person (see issues list).
        final Participant pPersonBs1 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()).toString());

        pPersonBs1.addValue(HQDM.MEMBER_OF_KIND.toString(), occupierOfPropertyRole.getId());
        pPersonBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        pPersonBs1.addValue(HQDM.TEMPORAL__PART_OF.toString(), personBs1.getId());
        pPersonBs1.addValue(HQDM.BEGINNING.toString(), e2.getId());
        pPersonBs1.addValue(HQDM.ENDING.toString(), e3.getId());
        pPersonBs1.addStringValue(ENTITY_NAME.toString(),
                "Note this is the state of person Bs1 that is participating the association");
        objects.add(pPersonBs1);

        final Participant pHouseBs1 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()).toString());

        pHouseBs1.addValue(HQDM.MEMBER_OF_KIND.toString(), domesticOccupantInPropertyRole.getId());
        pHouseBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        pHouseBs1.addValue(HQDM.TEMPORAL__PART_OF.toString(), houseBs1.getId());
        pHouseBs1.addValue(HQDM.BEGINNING.toString(), e2.getId());
        pHouseBs1.addValue(HQDM.ENDING.toString(), e3.getId());
        pHouseBs1.addStringValue(ENTITY_NAME.toString(),
                "Note this is the state of houseBs1 that is participating in the association");
        objects.add(pHouseBs1);

        final Participant pPersonBs2 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()).toString());

        pPersonBs2.addValue(HQDM.MEMBER_OF_KIND.toString(), occupierOfPropertyRole.getId());
        pPersonBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        pPersonBs2.addValue(HQDM.TEMPORAL__PART_OF.toString(), personBs2.getId());
        pPersonBs2.addValue(HQDM.BEGINNING.toString(), e4.getId());
        pPersonBs2.addValue(HQDM.ENDING.toString(), e5.getId());
        pPersonBs2.addStringValue(ENTITY_NAME.toString(),
                "Note this is the state of person Bs2 that is participating in the association");
        objects.add(pPersonBs2);

        final Participant pHouseBs2 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()).toString());

        pHouseBs2.addValue(HQDM.MEMBER_OF_KIND.toString(), domesticOccupantInPropertyRole.getId());
        pHouseBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        pHouseBs2.addValue(HQDM.TEMPORAL__PART_OF.toString(), houseBs2.getId());
        pHouseBs2.addValue(HQDM.BEGINNING.toString(), e4.getId());
        pHouseBs2.addValue(HQDM.ENDING.toString(), e5.getId());
        pHouseBs2.addStringValue(ENTITY_NAME.toString(),
                "Note this is the state of houseBs2 that is participating in the association");
        objects.add(pHouseBs2);

        final Association houseOccupantPresentState1 =
                SpatioTemporalExtentServices.createAssociation(new IRI(USER_BASE, uid()).toString());

        houseOccupantPresentState1.addValue(HQDM.MEMBER_OF_KIND.toString(), occupantInPropertyKindOfAssociation.getId());
        houseOccupantPresentState1.addValue(HQDM.CONSISTS_OF_PARTICIPANT.toString(), pHouseBs1.getId());
        houseOccupantPresentState1.addValue(HQDM.CONSISTS_OF_PARTICIPANT.toString(), pPersonBs1.getId());
        houseOccupantPresentState1.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        houseOccupantPresentState1.addValue(HQDM.BEGINNING.toString(), e2.getId());
        houseOccupantPresentState1.addValue(HQDM.ENDING.toString(), e3.getId());
        // Abbreviated to allow a string to be displayed against this class of 'relationship'.
        houseOccupantPresentState1.addStringValue(ENTITY_NAME.toString(), "HouseOccupantPresent1");
        objects.add(houseOccupantPresentState1);

        final Association houseOccupantPresentState2 =
                SpatioTemporalExtentServices.createAssociation(new IRI(USER_BASE, uid()).toString());

        houseOccupantPresentState2.addValue(HQDM.MEMBER_OF_KIND.toString(), occupantInPropertyKindOfAssociation.getId());
        houseOccupantPresentState2.addValue(HQDM.CONSISTS_OF_PARTICIPANT.toString(), pHouseBs2.getId());
        houseOccupantPresentState2.addValue(HQDM.CONSISTS_OF_PARTICIPANT.toString(), pPersonBs2.getId());
        houseOccupantPresentState2.addValue(HQDM.PART_OF_POSSIBLE_WORLD.toString(), possibleWorld.getId());
        houseOccupantPresentState2.addValue(HQDM.BEGINNING.toString(), e4.getId());
        houseOccupantPresentState2.addValue(HQDM.ENDING.toString(), e5.getId());
        // Abbreviated to allow a string to be displayed against this class of 'relationship'.
        houseOccupantPresentState2.addStringValue(ENTITY_NAME.toString(), "HouseOccupantPresent2");
        objects.add(houseOccupantPresentState2);

        return objects;
    }
}
