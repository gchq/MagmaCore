package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import java.util.function.UnaryOperator;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.model.ClassOfStateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.ClassOfStateOfPerson;
import uk.gov.gchq.hqdm.model.Event;
import uk.gov.gchq.hqdm.model.FunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfAssociation;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfPerson;
import uk.gov.gchq.hqdm.model.Person;
import uk.gov.gchq.hqdm.model.PossibleWorld;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleDataObjects {

    // A DB transformer that adds the RDL entities.
    private static UnaryOperator<MagmaCoreDatabase> createRefDataObjects = (db) -> {
        final var builder = new ExampleModelBuilder();

        // RDL CLASSES - Can be created, stored and queried separately.

        // Viewable is a class to assign other data objects to, to indicate that they are likely to
        // be of direct interest to a system user.
        final var viewable = builder.createClass("VIEWABLE");

        // A sub-set of the Viewable class.
        final var viewableObject = builder.createClass("VIEWABLE_OBJECT");

        // A sub-set of the Viewable Class for viewable Associations.
        final var viewableAssociation = builder.createClass("VIEWABLE_ASSOCIATION");

        // A system is composed of components so this is the class of components that a whole-life
        // person can have.
        final var kindOfBiologicalSystemHumanComponent =
            builder.createKindOfBiologicalSystemComponent("KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT");

        // A class of whole-life person (re-)created as Reference Data.
        final var kindOfPerson = builder.createKindOfPerson("KIND_OF_PERSON");

        // A class of temporal part (state) of a (whole-life) person.
        final var classOfStateOfPerson =
            builder.createClassOfStateOfPerson("CLASS_OF_STATE_OF_PERSON");

        // A class of whole-life system that is a Building.
        final var kindOfFunctionalSystemBuilding =
            builder.createKindOfFunctionalSystem("KIND_OF_FUNCTIONAL_SYSTEM_BUILDING");

        // A Domestic Property is a system composed of components (e.g. walls, floors, roof, front
        // door, etc). This is the class of those whole-life system components.
        final var kindOfFunctionalSystemDomesticPropertyComponent =
            builder.createKindOfFunctionalSystemComponent("KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT");

        // The class of whole-life system that is domestic property.
        final var kindOfFunctionalSystemDomesticProperty =
            builder.createKindOfFunctionalSystem("KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");

        // The class of state of system whose members are temporal parts of domestic properties.
        final var classOfStateOfFunctionalSystemDomesticProperty =
            builder.createClassOfStateOfFunctionalSystem("STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");

        // The class of role that every member of class of person plays.
        builder.createRole("NATURAL_MEMBER_OF_SOCIETY_ROLE");

        // The class of role that every member of class of domestic property plays.
        final var domesticPropertyRole = builder.createRole("ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");

        final var domesticOccupantInPropertyRole = builder.createRole("DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.

        final var occupierOfPropertyRole = builder.createRole("OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        // Would be good to add part_of_by_class_(occupantInPropertyKindOfAssociation) but can't
        // neatly do that in the class as it can only be added after
        // occupantInPropertyKindOfAssociation is created. This can be added later for completeness.

        // Add the Association Types (Participants and Associations).
        final var occupantInPropertyKindOfAssociation =
            builder.createKindOfAssociation("OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        builder
            // Create the class hierarchy
            .addSubclass(viewable, viewableObject)
            .addSubclass(viewable, viewableAssociation)
            .addSubclass(kindOfFunctionalSystemBuilding, kindOfFunctionalSystemDomesticProperty)
            .addSubclass(domesticPropertyRole, domesticOccupantInPropertyRole)
            .addSubclass(classOfStateOfPerson, occupierOfPropertyRole)
            // Set class memberships
            .addClassMember(viewableObject, kindOfPerson)
            .addClassMember(viewableObject, classOfStateOfPerson)
            .addClassMember(viewableObject, kindOfFunctionalSystemDomesticProperty)
            .addClassMember(viewableObject, classOfStateOfFunctionalSystemDomesticProperty)
            .addClassMember(viewableAssociation, occupantInPropertyKindOfAssociation)
            // Set the has component by class predicates
            .addHasComponentByClass(kindOfPerson, kindOfBiologicalSystemHumanComponent)
            .addHasComponentByClass(kindOfFunctionalSystemDomesticProperty,
                    kindOfFunctionalSystemDomesticPropertyComponent)
            // Set the consists of by class predicates
            .addConsistsOfByClass(occupantInPropertyKindOfAssociation, domesticOccupantInPropertyRole)
            .addConsistsOfByClass(occupantInPropertyKindOfAssociation, occupierOfPropertyRole)
            // store the objects in the database
            .getObjects().forEach(object -> {
                db.create(object);
            });

        return db;
    };


    /**
     * Find an object by its ENTITY_NAME.
     *
     * @param db the {@link MagmaCoreDatabase} to seaerch.
     * @param name the name {@link String} to seaerch for.
     * @return the {@link Thing}that was found.
     * @throws RuntimeException if no or multiple results found.
     */
    private static <T> T findByEntityName(final MagmaCoreDatabase db, final String name) {
        final var searchResult = db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, name);
        if (searchResult.size() == 1) {
            return (T) searchResult.get(0);
        } else if (searchResult.isEmpty()) {
            throw new RuntimeException("No entity found with name: " + name);
        } else {
            throw new RuntimeException("Multiple entities found with name: " + name);
        }
    }

    // A DB transformer that adds whole life individuals.
    private static UnaryOperator<MagmaCoreDatabase> addWholeLifeIndividuals = (db) -> {

        // Find the required classes, kinds, and roles.
        final KindOfPerson kindOfPerson = findByEntityName(db, "KIND_OF_PERSON");
        final Role personRole = findByEntityName(db, "NATURAL_MEMBER_OF_SOCIETY_ROLE");
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
            findByEntityName(db, "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role domesticPropertyRole =
            findByEntityName(db, "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final var pwContext = new PossibleWorldContext("Example1_World");

        // Create the beginning events for the person and house
        final var e1 = pwContext.createPointInTime("1991-02-18T00:00:00");
        final var e6 = pwContext.createPointInTime("1972-06-01T00:00:00");

        // Person B Whole Life Object.
        final var personB1 = pwContext.createPerson("PersonB1_Bob");

        // House B Whole Life Object.
        final var houseB = pwContext.createFunctionalSystem("HouseB");

        pwContext
            // Add the person predicates
            .addMemberOfKind(personB1, kindOfPerson)
            .addNaturalRole(personB1, personRole)
            .addBeginningEvent(personB1, e1)
            // Add the house predicates
            .addMemberOfKind(houseB, kindOfFunctionalSystemDomesticProperty)
            .addIntendedRole(houseB, domesticPropertyRole)
            .addBeginningEvent(houseB, e6)
            // Store the objects in the database
            .getObjects().forEach(object -> {
                db.create(object);
            });

        return db;
    };

    /**
     * Create a person-occupies-house association.
     *
     * @param db a {@link MagmaCoreDatabase}
     * @param possibleWorld a {@link PossibleWorld}
     * @param person the {@link Person} occupying the house.
     * @param house the house as a {@link FunctionalSystem} that is occupied.
     * @param beginning {@link Event}
     * @param ending {@link Event}
     */
    private static void occupyHouse(
            final MagmaCoreDatabase db,
            final PossibleWorld possibleWorld,
            final Person person,
            final FunctionalSystem house,
            final Event beginning,
            final Event ending) {

        // Find the required classes, kinds, and roles.
        final ClassOfStateOfPerson classOfStateOfPerson = findByEntityName(db, "CLASS_OF_STATE_OF_PERSON");
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
            findByEntityName(db, "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role occupierOfPropertyRole = findByEntityName(db, "OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        final Role domesticOccupantInPropertyRole = findByEntityName(db, "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        final KindOfAssociation occupantInPropertyKindOfAssociation =
            findByEntityName(db, "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        // STATES

        // The main state: This is a mandatory component of all datasets if we are to stick to the
        // commitments in HQDM. This is the least strict treatment, the creation of a single
        // possible world.
        final var pwContext = new PossibleWorldContext(possibleWorld);

        // Person state.
        final var personState = pwContext.createStateOfPerson(uid());

        // States of house when Occupant person is present.
        final var houseState = pwContext.createStateOfFunctionalSystem(uid());

        // Create Participants to link the states to their roles in the association
        final var personParticipant = pwContext.createParticipant(uid());
        final var houseParticipant = pwContext.createParticipant(uid());

        // Create the association
        final var houseOccupiedAssociation = pwContext.createAssociation(uid());

        // Build the full structure of the association
        pwContext
            // personState predicates
            .addMemberOf(personState, classOfStateOfPerson)
            .addTemporalPartOf(personState, person)
            .addBeginningEvent(personState, beginning)
            .addEndingEvent(personState, ending)
            // houseState predicates
            .addMemberOf(houseState, classOfStateOfFunctionalSystemDomesticProperty)
            .addTemporalPartOf(house, houseState)
            .addBeginningEvent(houseState, beginning)
            .addEndingEvent(houseState, ending)
            // personParticipant predicates
            .addMemberOfKind(personParticipant, occupierOfPropertyRole)
            .addTemporalPartOf(personParticipant, personState)
            .addBeginningEvent(personParticipant, beginning)
            .addEndingEvent(personParticipant, ending)
            // houseParticipant predicates
            .addMemberOfKind(houseParticipant, domesticOccupantInPropertyRole)
            .addTemporalPartOf(houseParticipant, houseState)
            .addBeginningEvent(houseParticipant, beginning)
            .addEndingEvent(houseParticipant, ending)
            // houseOccupiedAssociation predicates
            .addMemberOfKind(houseOccupiedAssociation, occupantInPropertyKindOfAssociation)
            .addConsistsOfParticipant(houseOccupiedAssociation, houseParticipant)
            .addConsistsOfParticipant(houseOccupiedAssociation, personParticipant)
            .addBeginningEvent(houseOccupiedAssociation, beginning)
            .addEndingEvent(houseOccupiedAssociation, ending)
            // Store the objects in the database
            .getObjects().forEach(object -> {
                db.create(object);
            });
            }

    // A DB transformer that adds house occupancy associations.
    private static UnaryOperator<MagmaCoreDatabase> addHouseOccupancies = (db) -> {

        // Use an existing PossibleWorld
        final PossibleWorld possibleWorld = findByEntityName(db, "Example1_World");
        final var pwContext = new PossibleWorldContext(possibleWorld);

        // Create the bounding events for the associations
        final var e2 = pwContext.createPointInTime("2020-08-15T17:50:00");
        final var e3 = pwContext.createPointInTime("2020-08-15T19:21:00");
        final var e4 = pwContext.createPointInTime("2020-08-16T22:33:00");
        final var e5 = pwContext.createPointInTime("2020-08-17T10:46:00");

        // Persist the events
        pwContext.getObjects().forEach(object -> {
            db.create(object);
        });

        // The person occupies the house twice at different times.
        final Person person = findByEntityName(db, "PersonB1_Bob");
        final FunctionalSystem house = findByEntityName(db, "HouseB");

        // This will create and persist the associations.
        occupyHouse(db, possibleWorld, person, house, e2, e3);
        occupyHouse(db, possibleWorld, person, house, e4, e5);

        return db;
    };

    /**
     * A function that populates a database.
     *
     * @param db a {@link MagmaCoreDatabase}
     */
    public static void populateExampleData(final MagmaCoreDatabase db) {

        // Compose 3 functions
        final var runDbOperations =
            createRefDataObjects
            .andThen(addWholeLifeIndividuals)
            .andThen(addHouseOccupancies);

        // Apply the composed function to the database.
        runDbOperations.apply(db);
    }

}
