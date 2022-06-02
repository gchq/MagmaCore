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

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;

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
        final ModelBuilder builder = new ModelBuilder();

        // RDL CLASSES - Can be created, stored and queried separately.

        // Viewable is a class to assign other data objects to, to indicate that they are likely to
        // be of direct interest to a system user.
        final uk.gov.gchq.hqdm.model.Class viewable = builder.createClass("VIEWABLE");

        // A sub-set of the Viewable class.
        final uk.gov.gchq.hqdm.model.Class viewableObject = builder.createClass("VIEWABLE_OBJECT");

        // A sub-set of the Viewable Class for viewable Associations.
        final uk.gov.gchq.hqdm.model.Class viewableAssociation = builder.createClass("VIEWABLE_ASSOCIATION");

        // A system is composed of components so this is the class of components that a whole-life
        // person can have.
        final KindOfBiologicalSystemComponent kindOfBiologicalSystemHumanComponent =
                builder.createKindOfBiologicalSystemComponent("KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT");

        // A class of whole-life person (re-)created as Reference Data.
        final KindOfPerson kindOfPerson = builder.createKindOfPerson("KIND_OF_PERSON");

        // A class of temporal part (state) of a (whole-life) person.
        final ClassOfStateOfPerson classOfStateOfPerson = builder.createClassOfStateOfPerson("CLASS_OF_STATE_OF_PERSON");

        // A class of whole-life system that is a Building.
        final KindOfFunctionalSystem kindOfFunctionalSystemBuilding =
                builder.createKindOfFunctionalSystem("KIND_OF_FUNCTIONAL_SYSTEM_BUILDING");

        // A Domestic Property is a system composed of components (e.g. walls, floors, roof, front
        // door, etc). This is the class of those whole-life system components.
        final KindOfFunctionalSystemComponent kindOfFunctionalSystemDomesticPropertyComponent =
                builder.createKindOfFunctionalSystemComponent("KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT");

        // The class of whole-life system that is domestic property.
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
                builder.createKindOfFunctionalSystem("KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");

        // The class of state of system whose members are temporal parts of domestic properties.
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
                builder.createClassOfStateOfFunctionalSystem("STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");

        // The class of role that every member of class of person plays.
        final Role personRole = builder.createRole("NATURAL_MEMBER_OF_SOCIETY_ROLE");

        // The class of role that every member of class of domestic property plays.
        final Role domesticPropertyRole = builder.createRole("ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");

        final Role domesticOccupantInPropertyRole = builder.createRole("DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.

        final Role occupierOfPropertyRole = builder.createRole("OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.

        // Add the Association Types (Participants and Associations).
        final KindOfAssociation occupantInPropertyKindOfAssociation =
                builder.createKindOfAssociation("OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        builder.addSubclass(viewable, viewableObject);
        builder.addSubclass(viewable, viewableAssociation);
        builder.addSubclass(kindOfFunctionalSystemBuilding, kindOfFunctionalSystemDomesticProperty);
        builder.addSubclass(domesticPropertyRole, domesticOccupantInPropertyRole);
        builder.addSubclass(classOfStateOfPerson, occupierOfPropertyRole);

        builder.addClassMember(viewableObject, kindOfPerson);
        builder.addClassMember(viewableObject, classOfStateOfPerson);
        builder.addClassMember(viewableObject, kindOfFunctionalSystemDomesticProperty);
        builder.addClassMember(viewableObject, classOfStateOfFunctionalSystemDomesticProperty);
        builder.addClassMember(viewableAssociation, occupantInPropertyKindOfAssociation);

        builder.addHasComponentByClass(kindOfPerson, kindOfBiologicalSystemHumanComponent);
        builder.addHasComponentByClass(kindOfFunctionalSystemDomesticProperty, kindOfFunctionalSystemDomesticPropertyComponent);

        builder.addConsistsOfByClass(occupantInPropertyKindOfAssociation, domesticOccupantInPropertyRole);
        builder.addConsistsOfByClass(occupantInPropertyKindOfAssociation, occupierOfPropertyRole);

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final PossibleWorld possibleWorld = builder.createPossibleWorld("Example1_World");

        // Person B Whole Life Object.
        final Event e1 = builder.createPointInTime("1991-02-18T00:00:00");
        builder.addToPossibleWorld(e1, possibleWorld);

        final Person personB1 = builder.createPerson("PersonB1_Bob");

        builder.addMemberOfKind(personB1, kindOfPerson);
        builder.addNaturalRole(personB1, personRole);
        builder.addToPossibleWorld(personB1, possibleWorld);
        builder.addBeginningEvent(personB1, e1);





        // Person B states.
        final Event e2 = builder.createPointInTime("2020-08-15T17:50:00");
        final Event e3 = builder.createPointInTime("2020-08-15T19:21:00");

        builder.addToPossibleWorld(e2, possibleWorld);
        builder.addToPossibleWorld(e3, possibleWorld);

        final StateOfPerson personBs1 = builder.createStateOfPerson("");

        builder.addMemberOf(personBs1, classOfStateOfPerson);
        builder.addToPossibleWorld(personBs1, possibleWorld);
        builder.addTemporalPartOf(personBs1, personB1);
        builder.addBeginningEvent(personBs1, e2);
        builder.addEndingEvent(personBs1, e3);

        final Event e4 = builder.createPointInTime("2020-08-16T22:33:00");
        final Event e5 = builder.createPointInTime("2020-08-17T10:46:00");

        builder.addToPossibleWorld(e4, possibleWorld);
        builder.addToPossibleWorld(e5, possibleWorld);

        final StateOfPerson personBs2 = builder.createStateOfPerson("");

        builder.addMemberOf(personBs2, classOfStateOfPerson);
        builder.addToPossibleWorld(personBs2, possibleWorld);
        builder.addTemporalPartOf(personBs2, personB1);
        builder.addBeginningEvent(personBs2, e4);
        builder.addEndingEvent(personBs2, e5);


        // House B Whole Life Object.
        final Event e6 = builder.createPointInTime("1972-06-01T00:00:00");
        builder.addToPossibleWorld(e6, possibleWorld);
        final FunctionalSystem houseB = builder.createFunctionalSystem("");

        builder.addMemberOfKind(houseB, kindOfFunctionalSystemDomesticProperty);
        builder.addToPossibleWorld(houseB, possibleWorld);
        builder.addIntendedRole(houseB, domesticPropertyRole);
        builder.addBeginningEvent(houseB, e6);

        // States of house when Occupant personBs1 is present.
        final StateOfFunctionalSystem houseBs1 = builder.createStateOfFunctionalSystem("");

        builder.addMemberOf(houseBs1, classOfStateOfFunctionalSystemDomesticProperty);
        builder.addTemporalPartOf(houseB, houseBs1);
        builder.addToPossibleWorld(houseBs1, possibleWorld);
        builder.addBeginningEvent(houseBs1, e2);
        builder.addEndingEvent(houseBs1, e3);

        final StateOfFunctionalSystem houseBs2 = builder.createStateOfFunctionalSystem("");;

        builder.addMemberOf(houseBs2, classOfStateOfFunctionalSystemDomesticProperty);
        builder.addTemporalPartOf(houseB, houseBs2);
        builder.addToPossibleWorld(houseBs2, possibleWorld);
        builder.addBeginningEvent(houseBs2, e4);
        builder.addEndingEvent(houseBs2, e5);

        // Add the Associations and map the states above to the appropriate participant objects.
        // If we had full has_superClass resolving in HQDM classes then this participant object
        // wouldn't be needed as the class occupierOfPropertyRole is also a sub-type of
        // state_of_person (see issues list).
        final Participant pPersonBs1 = 
            builder.createParticipant("Note this is the state of person Bs1 that is participating the association");

        builder.addMemberOfKind(pPersonBs1, occupierOfPropertyRole);
        builder.addToPossibleWorld(pPersonBs1, possibleWorld);
        builder.addTemporalPartOf(pPersonBs1, personBs1);
        builder.addBeginningEvent(pPersonBs1, e2);
        builder.addEndingEvent(pPersonBs1, e3);

        final Participant pHouseBs1 = 
            builder.createParticipant("Note this is the state of houseBs1 that is participating in the association");

        builder.addMemberOfKind(pHouseBs1, domesticOccupantInPropertyRole);
        builder.addToPossibleWorld(pHouseBs1, possibleWorld);
        builder.addTemporalPartOf(pHouseBs1, houseBs1);
        builder.addBeginningEvent(pHouseBs1, e2);
        builder.addEndingEvent(pHouseBs1, e3);

        final Participant pPersonBs2 = 
            builder.createParticipant("Note this is the state of person Bs2 that is participating in the association");

        builder.addMemberOfKind(pPersonBs2, occupierOfPropertyRole);
        builder.addToPossibleWorld(pPersonBs2, possibleWorld);
        builder.addTemporalPartOf(pPersonBs2, personBs2);
        builder.addBeginningEvent(pPersonBs2, e4);
        builder.addEndingEvent(pPersonBs2, e5);

        final Participant pHouseBs2 = 
            builder.createParticipant("Note this is the state of houseBs2 that is participating in the association");

        builder.addMemberOfKind(pHouseBs2, domesticOccupantInPropertyRole);
        builder.addToPossibleWorld(pHouseBs2, possibleWorld);
        builder.addTemporalPartOf(pHouseBs2, houseBs2);
        builder.addBeginningEvent(pHouseBs2, e4);
        builder.addEndingEvent(pHouseBs2, e5);

        final Association houseOccupantPresentState1 = builder.createAssociation("HouseOccupantPresent1");

        builder.addMemberOfKind(houseOccupantPresentState1, occupantInPropertyKindOfAssociation);
        builder.addConsistsOfParticipant(houseOccupantPresentState1, pHouseBs1);
        builder.addConsistsOfParticipant(houseOccupantPresentState1, pPersonBs1);
        builder.addToPossibleWorld(houseOccupantPresentState1, possibleWorld);
        builder.addBeginningEvent(houseOccupantPresentState1, e2);
        builder.addEndingEvent(houseOccupantPresentState1, e3);

        final Association houseOccupantPresentState2 = builder.createAssociation("HouseOccupantPresent2");

        builder.addMemberOfKind(houseOccupantPresentState2, occupantInPropertyKindOfAssociation);
        builder.addConsistsOfParticipant(houseOccupantPresentState2, pHouseBs2);
        builder.addConsistsOfParticipant(houseOccupantPresentState2, pPersonBs2);
        builder.addToPossibleWorld(houseOccupantPresentState2, possibleWorld);
        builder.addBeginningEvent(houseOccupantPresentState2, e4);
        builder.addEndingEvent(houseOccupantPresentState2, e5);

        return builder.getObjects();
    }
}
