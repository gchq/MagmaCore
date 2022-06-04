package uk.gov.gchq.magmacore.demo;

import java.util.function.Function;
import java.util.function.Supplier;

import uk.gov.gchq.hqdm.iri.HQDM;
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
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.model.StateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.StateOfPerson;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreObjectDatabase;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class SystemFunctions {

    //  A Supplier of a MagmaCoreDatabase.
    private static Supplier<MagmaCoreDatabase> createDatabase = () -> new MagmaCoreObjectDatabase();

    // A Supplier of a ModelBuilder.
    private static Supplier<ModelBuilder> createRefDataObjects = () -> {
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
        final ClassOfStateOfPerson classOfStateOfPerson = 
            builder.createClassOfStateOfPerson("CLASS_OF_STATE_OF_PERSON");

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
        builder.addHasComponentByClass(kindOfFunctionalSystemDomesticProperty, 
                kindOfFunctionalSystemDomesticPropertyComponent);

        builder.addConsistsOfByClass(occupantInPropertyKindOfAssociation, domesticOccupantInPropertyRole);
        builder.addConsistsOfByClass(occupantInPropertyKindOfAssociation, occupierOfPropertyRole);

        return builder;
    };

    // A DB transformer that adds example entity instances.
    private static Function<MagmaCoreDatabase, MagmaCoreDatabase> addExampleEntities = (db) -> {
        final var kindOfPerson = (KindOfPerson) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, "KIND_OF_PERSON").get(0);
        final var personRole = (Role) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, "NATURAL_MEMBER_OF_SOCIETY_ROLE").get(0);
        final var classOfStateOfPerson = (ClassOfStateOfPerson) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, "CLASS_OF_STATE_OF_PERSON").get(0);
        final var kindOfFunctionalSystemDomesticProperty = (KindOfFunctionalSystem) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY").get(0);
        final var domesticPropertyRole = (Role) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, 
                    "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE").get(0);
        final var classOfStateOfFunctionalSystemDomesticProperty = (ClassOfStateOfFunctionalSystem) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME,
                    "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY").get(0);
        final var occupierOfPropertyRole = (Role) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME,
                    "OCCUPIER_LOCATED_IN_PROPERTY_ROLE").get(0);
        final var domesticOccupantInPropertyRole = (Role) 
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME,
                    "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE").get(0);
        final var occupantInPropertyKindOfAssociation = (KindOfAssociation)
            db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME,
                    "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION").get(0);

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final PossibleWorldContext pwContext = new PossibleWorldContext("Example1_World");

        // Person B Whole Life Object.
        final Event e1 = pwContext.createPointInTime("1991-02-18T00:00:00");

        final Person personB1 = pwContext.createPerson("PersonB1_Bob");

        pwContext.addMemberOfKind(personB1, kindOfPerson);
        pwContext.addNaturalRole(personB1, personRole);
        pwContext.addBeginningEvent(personB1, e1);

        // Person B states.
        final Event e2 = pwContext.createPointInTime("2020-08-15T17:50:00");
        final Event e3 = pwContext.createPointInTime("2020-08-15T19:21:00");


        final StateOfPerson personBs1 = pwContext.createStateOfPerson("");

        pwContext.addMemberOf(personBs1, classOfStateOfPerson);
        pwContext.addTemporalPartOf(personBs1, personB1);
        pwContext.addBeginningEvent(personBs1, e2);
        pwContext.addEndingEvent(personBs1, e3);

        final Event e4 = pwContext.createPointInTime("2020-08-16T22:33:00");
        final Event e5 = pwContext.createPointInTime("2020-08-17T10:46:00");


        final StateOfPerson personBs2 = pwContext.createStateOfPerson("");

        pwContext.addMemberOf(personBs2, classOfStateOfPerson);
        pwContext.addTemporalPartOf(personBs2, personB1);
        pwContext.addBeginningEvent(personBs2, e4);
        pwContext.addEndingEvent(personBs2, e5);


        // House B Whole Life Object.
        final Event e6 = pwContext.createPointInTime("1972-06-01T00:00:00");
        final FunctionalSystem houseB = pwContext.createFunctionalSystem("");

        pwContext.addMemberOfKind(houseB, kindOfFunctionalSystemDomesticProperty);
        pwContext.addIntendedRole(houseB, domesticPropertyRole);
        pwContext.addBeginningEvent(houseB, e6);

        // States of house when Occupant personBs1 is present.
        final StateOfFunctionalSystem houseBs1 = pwContext.createStateOfFunctionalSystem("");

        pwContext.addMemberOf(houseBs1, classOfStateOfFunctionalSystemDomesticProperty);
        pwContext.addTemporalPartOf(houseB, houseBs1);
        pwContext.addBeginningEvent(houseBs1, e2);
        pwContext.addEndingEvent(houseBs1, e3);

        final StateOfFunctionalSystem houseBs2 = pwContext.createStateOfFunctionalSystem("");;

        pwContext.addMemberOf(houseBs2, classOfStateOfFunctionalSystemDomesticProperty);
        pwContext.addTemporalPartOf(houseB, houseBs2);
        pwContext.addBeginningEvent(houseBs2, e4);
        pwContext.addEndingEvent(houseBs2, e5);

        // Add the Associations and map the states above to the appropriate participant objects.
        // If we had full has_superClass resolving in HQDM classes then this participant object
        // wouldn't be needed as the class occupierOfPropertyRole is also a sub-type of
        // state_of_person (see issues list).
        final Participant pPersonBs1 =
            pwContext.createParticipant("Note this is the state of person Bs1 that is participating the association");

        pwContext.addMemberOfKind(pPersonBs1, occupierOfPropertyRole);
        pwContext.addTemporalPartOf(pPersonBs1, personBs1);
        pwContext.addBeginningEvent(pPersonBs1, e2);
        pwContext.addEndingEvent(pPersonBs1, e3);

        final Participant pHouseBs1 =
            pwContext.createParticipant("Note this is the state of houseBs1 that is participating in the association");

        pwContext.addMemberOfKind(pHouseBs1, domesticOccupantInPropertyRole);
        pwContext.addTemporalPartOf(pHouseBs1, houseBs1);
        pwContext.addBeginningEvent(pHouseBs1, e2);
        pwContext.addEndingEvent(pHouseBs1, e3);

        final Participant pPersonBs2 =
          pwContext.createParticipant("Note this is the state of person Bs2 that is participating in the association");

        pwContext.addMemberOfKind(pPersonBs2, occupierOfPropertyRole);
        pwContext.addTemporalPartOf(pPersonBs2, personBs2);
        pwContext.addBeginningEvent(pPersonBs2, e4);
        pwContext.addEndingEvent(pPersonBs2, e5);

        final Participant pHouseBs2 =
            pwContext.createParticipant("Note this is the state of houseBs2 that is participating in the association");

        pwContext.addMemberOfKind(pHouseBs2, domesticOccupantInPropertyRole);
        pwContext.addTemporalPartOf(pHouseBs2, houseBs2);
        pwContext.addBeginningEvent(pHouseBs2, e4);
        pwContext.addEndingEvent(pHouseBs2, e5);

        final Association houseOccupantPresentState1 = pwContext.createAssociation("HouseOccupantPresent1");

        pwContext.addMemberOfKind(houseOccupantPresentState1, occupantInPropertyKindOfAssociation);
        pwContext.addConsistsOfParticipant(houseOccupantPresentState1, pHouseBs1);
        pwContext.addConsistsOfParticipant(houseOccupantPresentState1, pPersonBs1);
        pwContext.addBeginningEvent(houseOccupantPresentState1, e2);
        pwContext.addEndingEvent(houseOccupantPresentState1, e3);

        final Association houseOccupantPresentState2 = pwContext.createAssociation("HouseOccupantPresent2");

        pwContext.addMemberOfKind(houseOccupantPresentState2, occupantInPropertyKindOfAssociation);
        pwContext.addConsistsOfParticipant(houseOccupantPresentState2, pHouseBs2);
        pwContext.addConsistsOfParticipant(houseOccupantPresentState2, pPersonBs2);
        pwContext.addBeginningEvent(houseOccupantPresentState2, e4);
        pwContext.addEndingEvent(houseOccupantPresentState2, e5);

        return db;
    };

    // A DB transformer that adds Reference Data entities.
    private static Function<MagmaCoreDatabase, MagmaCoreDatabase> addReferenceDataLibrary = (db) -> {

        createRefDataObjects.get().getObjects().forEach(object -> {
            db.create(object);
        });

        return db;
    };

    // A Runnable that creates a database and populates it.
    public static Runnable runner = () -> {

        final Function<MagmaCoreDatabase, MagmaCoreDatabase> runDbOperations =
            addReferenceDataLibrary
            .andThen(addExampleEntities);

        runDbOperations.apply(createDatabase.get());
    };

}
