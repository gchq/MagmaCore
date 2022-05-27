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
            final Resource resource = model.createResource(object.getIri().toString());
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
        final uk.gov.gchq.hqdm.model.Class viewable = ClassServices.createClass(new IRI(REF_BASE, uid()));
        viewable.addStringValue(ENTITY_NAME, "VIEWABLE");
        objects.add(viewable);

        // A sub-set of the Viewable class.
        final uk.gov.gchq.hqdm.model.Class viewableObject = ClassServices.createClass(new IRI(REF_BASE, uid()));
        viewableObject.addValue(HQDM.HAS_SUPERCLASS, viewable.getIri());
        viewableObject.addStringValue(ENTITY_NAME, "VIEWABLE_OBJECT");
        objects.add(viewableObject);

        // A sub-set of the Viewable Class for viewable Associations.
        final uk.gov.gchq.hqdm.model.Class viewableAssociation =
                ClassServices.createClass(new IRI(REF_BASE, uid()));
        viewableAssociation.addValue(HQDM.HAS_SUPERCLASS, viewable.getIri());
        viewableAssociation.addStringValue(ENTITY_NAME, "VIEWABLE_ASSOCIATION");
        objects.add(viewableAssociation);

        // An system is composed of components so this is the class of components that a whole-life
        // person can have.
        final KindOfBiologicalSystemComponent kindOfBiologicalSystemHumanComponent =
                ClassServices.createKindOfBiologicalSystemComponent(new IRI(REF_BASE, uid()));

        kindOfBiologicalSystemHumanComponent.addStringValue(ENTITY_NAME,
                "KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT");
        objects.add(kindOfBiologicalSystemHumanComponent);

        // A class of whole-life person (re-)created as Reference Data.
        final KindOfPerson kindOfPerson = ClassServices.createKindOfPerson(new IRI(REF_BASE, uid()));

        kindOfPerson.addValue(HQDM.MEMBER__OF, viewableObject.getIri());
        kindOfPerson.addValue(HQDM.HAS_COMPONENT_BY_CLASS, kindOfBiologicalSystemHumanComponent.getIri());
        kindOfPerson.addStringValue(ENTITY_NAME, "KIND_OF_PERSON");
        objects.add(kindOfPerson);

        // A class of temporal part (state) of a (whole-life) person.
        final ClassOfStateOfPerson classOfStateOfPerson = ClassServices.createClassOfStateOfPerson(new IRI(REF_BASE, uid()));

        classOfStateOfPerson.addValue(HQDM.MEMBER__OF, viewableObject.getIri());
        classOfStateOfPerson.addStringValue(ENTITY_NAME, "CLASS_OF_STATE_OF_PERSON");
        objects.add(classOfStateOfPerson);

        // A class of whole-life system that is a Building.
        final KindOfFunctionalSystem kindOfFunctionalSystemBuilding =
                ClassServices.createKindOfFunctionalSystem(new IRI(REF_BASE, uid()));

        kindOfFunctionalSystemBuilding.addStringValue(ENTITY_NAME,
                "KIND_OF_FUNCTIONAL_SYSTEM_BUILDING");
        objects.add(kindOfFunctionalSystemBuilding);

        // A Domestic Property is a system composed of components (e.g. walls, floors, roof, front
        // door, etc). This is the class of those whole-life system components.
        final KindOfFunctionalSystemComponent kindOfFunctionalSystemDomesticPropertyComponent =
                ClassServices.createKindOfFunctionalSystemComponent(new IRI(REF_BASE, uid()));

        kindOfFunctionalSystemDomesticPropertyComponent.addStringValue(ENTITY_NAME,
                "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT");
        objects.add(kindOfFunctionalSystemDomesticPropertyComponent);

        // The class of whole-life system that is domestic property.
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
                ClassServices.createKindOfFunctionalSystem(new IRI(REF_BASE, uid()));

        kindOfFunctionalSystemDomesticProperty.addValue(HQDM.HAS_SUPERCLASS, kindOfFunctionalSystemBuilding.getIri());
        kindOfFunctionalSystemDomesticProperty.addValue(HQDM.MEMBER__OF, viewableObject.getIri());
        kindOfFunctionalSystemDomesticProperty.addValue(HQDM.HAS_COMPONENT_BY_CLASS, kindOfFunctionalSystemDomesticPropertyComponent.getIri());
        kindOfFunctionalSystemDomesticProperty.addStringValue(ENTITY_NAME,
                "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        objects.add(kindOfFunctionalSystemDomesticProperty);

        // The class of state of system whose members are temporal parts of domestic properties.
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
                ClassServices.createClassOfStateOfFunctionalSystem(new IRI(REF_BASE, uid()));

        classOfStateOfFunctionalSystemDomesticProperty.addValue(HQDM.MEMBER__OF, viewableObject.getIri());
        classOfStateOfFunctionalSystemDomesticProperty.addStringValue(ENTITY_NAME,
                "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        objects.add(classOfStateOfFunctionalSystemDomesticProperty);

        // The class of role that every member of class of person plays.
        final Role personRole = ClassServices.createRole(new IRI(REF_BASE, uid()));
        personRole.addStringValue(ENTITY_NAME, "NATURAL_MEMBER_OF_SOCIETY_ROLE");
        objects.add(personRole);

        // The class of role that every member of class of domestic property plays.
        final Role domesticPropertyRole = ClassServices.createRole(new IRI(REF_BASE, uid()));
        domesticPropertyRole.addStringValue(ENTITY_NAME,
                "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");
        objects.add(domesticPropertyRole);

        final Role domesticOccupantInPropertyRole = ClassServices.createRole(new IRI(REF_BASE, uid()));
        domesticOccupantInPropertyRole.addValue(HQDM.HAS_SUPERCLASS, domesticPropertyRole.getIri());
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.
        domesticOccupantInPropertyRole.addStringValue(ENTITY_NAME,
                "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        objects.add(domesticOccupantInPropertyRole);

        final Role occupierOfPropertyRole = ClassServices.createRole(new IRI(REF_BASE, uid()));
        occupierOfPropertyRole.addValue(HQDM.HAS_SUPERCLASS, classOfStateOfPerson.getIri());

        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.
        occupierOfPropertyRole.addStringValue(ENTITY_NAME,
                "OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        objects.add(occupierOfPropertyRole);

        // Add the Association Types (Participants and Associations).
        final KindOfAssociation occupantInPropertyKindOfAssociation =
                ClassServices.createKindOfAssociation(new IRI(REF_BASE, uid()));

        occupantInPropertyKindOfAssociation.addValue(HQDM.MEMBER__OF, viewableAssociation.getIri());
        occupantInPropertyKindOfAssociation.addValue(HQDM.CONSISTS_OF_BY_CLASS, domesticOccupantInPropertyRole.getIri());
        occupantInPropertyKindOfAssociation.addValue(HQDM.CONSISTS_OF_BY_CLASS, occupierOfPropertyRole.getIri());
        occupantInPropertyKindOfAssociation.addStringValue(ENTITY_NAME,
                "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final PossibleWorld possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(new IRI(USER_BASE, uid()));
        possibleWorld.addStringValue(ENTITY_NAME, "Example1_World");
        objects.add(possibleWorld);

        // Person B Whole Life Object.
        final Event e1 = event("1991-02-18T00:00:00", possibleWorld, USER_BASE);
        final Person personB1 = SpatioTemporalExtentServices.createPerson(new IRI(USER_BASE, uid()));

        personB1.addValue(HQDM.MEMBER_OF_KIND, kindOfPerson.getIri());
        personB1.addValue(HQDM.NATURAL_ROLE, personRole.getIri());
        personB1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        personB1.addValue(HQDM.BEGINNING, e1.getIri());
        personB1.addStringValue(ENTITY_NAME, "PersonB1_Bob");
        objects.add(e1);
        objects.add(personB1);

        // Person B states.
        final Event e2 = event("2020-08-15T17:50:00", possibleWorld, USER_BASE);
        final Event e3 = event("2020-08-15T19:21:00", possibleWorld, USER_BASE);
        final StateOfPerson personBs1 = SpatioTemporalExtentServices.createStateOfPerson(new IRI(USER_BASE, uid()));

        personBs1.addValue(HQDM.MEMBER_OF, classOfStateOfPerson.getIri());
        personBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        personBs1.addValue(HQDM.TEMPORAL_PART_OF, personB1.getIri());
        personBs1.addValue(HQDM.BEGINNING, e2.getIri());
        personBs1.addValue(HQDM.ENDING, e3.getIri());
        objects.add(e2);
        objects.add(e3);
        objects.add(personBs1);

        final Event e4 = event("2020-08-16T22:33:00", possibleWorld, USER_BASE);
        final Event e5 = event("2020-08-17T10:46:00", possibleWorld, USER_BASE);
        final StateOfPerson personBs2 = SpatioTemporalExtentServices.createStateOfPerson(new IRI(USER_BASE, uid()));

        personBs2.addValue(HQDM.MEMBER_OF, classOfStateOfPerson.getIri());
        personBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        personBs2.addValue(HQDM.TEMPORAL_PART_OF, personB1.getIri());
        personBs2.addValue(HQDM.BEGINNING, e4.getIri());
        personBs2.addValue(HQDM.ENDING, e5.getIri());
        objects.add(e4);
        objects.add(e5);
        objects.add(personBs2);

        // House B Whole Life Object.
        final Event e6 = event("1972-06-01T00:00:00", possibleWorld, USER_BASE);
        final FunctionalSystem houseB = SpatioTemporalExtentServices.createFunctionalSystem(new IRI(USER_BASE, uid()));

        houseB.addValue(HQDM.MEMBER_OF_KIND, kindOfFunctionalSystemDomesticProperty.getIri());
        houseB.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        houseB.addValue(HQDM.INTENDED_ROLE, domesticPropertyRole.getIri());
        houseB.addValue(HQDM.BEGINNING, e6.getIri());
        objects.add(e6);
        objects.add(houseB);

        // States of house when Occupant personBs1 is present.
        final StateOfFunctionalSystem houseBs1 = SpatioTemporalExtentServices.createStateOfFunctionalSystem(
                new IRI(USER_BASE, uid()));

        houseBs1.addValue(HQDM.MEMBER_OF, classOfStateOfFunctionalSystemDomesticProperty.getIri());
        houseBs1.addValue(HQDM.TEMPORAL_PART_OF, houseB.getIri());
        houseBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        houseBs1.addValue(HQDM.BEGINNING, e2.getIri());
        houseBs1.addValue(HQDM.ENDING, e3.getIri());
        objects.add(houseBs1);

        final StateOfFunctionalSystem houseBs2 = SpatioTemporalExtentServices.createStateOfFunctionalSystem(new IRI(USER_BASE, uid()));

        houseBs2.addValue(HQDM.MEMBER_OF, classOfStateOfFunctionalSystemDomesticProperty.getIri());
        houseBs2.addValue(HQDM.TEMPORAL_PART_OF, houseB.getIri());
        houseBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        houseBs2.addValue(HQDM.BEGINNING, e4.getIri());
        houseBs2.addValue(HQDM.ENDING, e5.getIri());
        objects.add(houseBs2);

        // Add the Associations and map the states above to the appropriate participant objects.
        // If we had full has_superClass resolving in HQDM classes then this participant object
        // wouldn't be needed as the class occupierOfPropertyRole is also a sub-type of
        // state_of_person (see issues list).
        final Participant pPersonBs1 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()));

        pPersonBs1.addValue(HQDM.MEMBER_OF_KIND, occupierOfPropertyRole.getIri());
        pPersonBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        pPersonBs1.addValue(HQDM.TEMPORAL__PART_OF, personBs1.getIri());
        pPersonBs1.addValue(HQDM.BEGINNING, e2.getIri());
        pPersonBs1.addValue(HQDM.ENDING, e3.getIri());
        pPersonBs1.addStringValue(ENTITY_NAME,
                "Note this is the state of person Bs1 that is participating the association");
        objects.add(pPersonBs1);

        final Participant pHouseBs1 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()));

        pHouseBs1.addValue(HQDM.MEMBER_OF_KIND, domesticOccupantInPropertyRole.getIri());
        pHouseBs1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        pHouseBs1.addValue(HQDM.TEMPORAL__PART_OF, houseBs1.getIri());
        pHouseBs1.addValue(HQDM.BEGINNING, e2.getIri());
        pHouseBs1.addValue(HQDM.ENDING, e3.getIri());
        pHouseBs1.addStringValue(ENTITY_NAME,
                "Note this is the state of houseBs1 that is participating in the association");
        objects.add(pHouseBs1);

        final Participant pPersonBs2 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()));

        pPersonBs2.addValue(HQDM.MEMBER_OF_KIND, occupierOfPropertyRole.getIri());
        pPersonBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        pPersonBs2.addValue(HQDM.TEMPORAL__PART_OF, personBs2.getIri());
        pPersonBs2.addValue(HQDM.BEGINNING, e4.getIri());
        pPersonBs2.addValue(HQDM.ENDING, e5.getIri());
        pPersonBs2.addStringValue(ENTITY_NAME,
                "Note this is the state of person Bs2 that is participating in the association");
        objects.add(pPersonBs2);

        final Participant pHouseBs2 = SpatioTemporalExtentServices.createParticipant(new IRI(USER_BASE, uid()));

        pHouseBs2.addValue(HQDM.MEMBER_OF_KIND, domesticOccupantInPropertyRole.getIri());
        pHouseBs2.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        pHouseBs2.addValue(HQDM.TEMPORAL__PART_OF, houseBs2.getIri());
        pHouseBs2.addValue(HQDM.BEGINNING, e4.getIri());
        pHouseBs2.addValue(HQDM.ENDING, e5.getIri());
        pHouseBs2.addStringValue(ENTITY_NAME,
                "Note this is the state of houseBs2 that is participating in the association");
        objects.add(pHouseBs2);

        final Association houseOccupantPresentState1 =
                SpatioTemporalExtentServices.createAssociation(new IRI(USER_BASE, uid()));

        houseOccupantPresentState1.addValue(HQDM.MEMBER_OF_KIND, occupantInPropertyKindOfAssociation.getIri());
        houseOccupantPresentState1.addValue(HQDM.CONSISTS_OF_PARTICIPANT, pHouseBs1.getIri());
        houseOccupantPresentState1.addValue(HQDM.CONSISTS_OF_PARTICIPANT, pPersonBs1.getIri());
        houseOccupantPresentState1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        houseOccupantPresentState1.addValue(HQDM.BEGINNING, e2.getIri());
        houseOccupantPresentState1.addValue(HQDM.ENDING, e3.getIri());
        // Abbreviated to allow a string to be displayed against this class of 'relationship'.
        houseOccupantPresentState1.addStringValue(ENTITY_NAME, "HouseOccupantPresent1");
        objects.add(houseOccupantPresentState1);

        final Association houseOccupantPresentState2 =
                SpatioTemporalExtentServices.createAssociation(new IRI(USER_BASE, uid()));

        houseOccupantPresentState2.addValue(HQDM.MEMBER_OF_KIND, occupantInPropertyKindOfAssociation.getIri());
        houseOccupantPresentState2.addValue(HQDM.CONSISTS_OF_PARTICIPANT, pHouseBs2.getIri());
        houseOccupantPresentState2.addValue(HQDM.CONSISTS_OF_PARTICIPANT, pPersonBs2.getIri());
        houseOccupantPresentState2.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getIri());
        houseOccupantPresentState2.addValue(HQDM.BEGINNING, e4.getIri());
        houseOccupantPresentState2.addValue(HQDM.ENDING, e5.getIri());
        // Abbreviated to allow a string to be displayed against this class of 'relationship'.
        houseOccupantPresentState2.addStringValue(ENTITY_NAME, "HouseOccupantPresent2");
        objects.add(houseOccupantPresentState2);

        return objects;
    }
}
