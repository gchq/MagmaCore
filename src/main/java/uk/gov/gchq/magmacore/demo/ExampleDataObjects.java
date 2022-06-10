package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.magmacore.util.DataObjectUtils.uid;

import java.util.Set;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.IriBase;
import uk.gov.gchq.hqdm.iri.RDFS;
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
import uk.gov.gchq.magmacore.database.DbChangeSet;
import uk.gov.gchq.magmacore.database.DbCreateOperation;
import uk.gov.gchq.magmacore.database.DbTransformation;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.util.ComposableBiFunction;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleDataObjects {

    /** IriBase for Reference Data Library. */
    private static final IriBase REF_BASE =
        new IriBase("rdl", "http://www.semanticweb.org/magma-core/rdl#");

    /** IriBase for User data. */
    private static final IriBase USER_BASE =
        new IriBase("user", "http://www.semanticweb.org/magma-core/user#");

    /**
     * Create a new IRI in the REF_BASE namespace.
     *
     * @return {@link IRI}
     */
    private static IRI mkRefBaseIri() {
        return new IRI(REF_BASE, uid());
    }

    /**
     * Create a new IRI in the USER_BASE namespace.
     *
     * @return {@link IRI}
     */
    private static IRI mkUserBaseIri() {
        return new IRI(USER_BASE, uid());
    }

    /**
     * Create a DbChangeSet that adds the RDL.
     *
     * @return {@link DbChangeSet}
     */
    private static DbChangeSet createRefDataObjects() {

        final var viewable = mkRefBaseIri();
        final var viewableObject = mkRefBaseIri();
        final var viewableAssociation = mkRefBaseIri();
        final var kindOfBiologicalSystemHumanComponent = mkRefBaseIri();
        final var kindOfPerson = mkRefBaseIri();
        final var classOfStateOfPerson = mkRefBaseIri();
        final var kindOfFunctionalSystemBuilding = mkRefBaseIri();
        final var kindOfFunctionalSystemDomesticPropertyComponent = mkRefBaseIri();
        final var kindOfFunctionalSystemDomesticProperty = mkRefBaseIri();
        final var classOfStateOfFunctionalSystemDomesticProperty = mkRefBaseIri();
        final var naturalMemberOfSocietyRole = mkRefBaseIri();
        final var domesticPropertyRole = mkRefBaseIri();
        final var domesticOccupantInPropertyRole = mkRefBaseIri();
        final var occupierOfPropertyRole = mkRefBaseIri();
        final var occupantInPropertyKindOfAssociation = mkRefBaseIri();

        final var creates = Set.of(
                new DbCreateOperation(viewable, RDFS.RDF_TYPE, HQDM.CLASS.getIri()),
                new DbCreateOperation(viewable, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(viewable, HQDM.ENTITY_NAME, "VIEWABLE"),

                new DbCreateOperation(viewableObject, RDFS.RDF_TYPE, HQDM.CLASS.getIri()),
                new DbCreateOperation(viewableObject, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(viewableObject, HQDM.ENTITY_NAME, "VIEWABLE_OBJECT"),

                new DbCreateOperation(viewableAssociation, RDFS.RDF_TYPE, HQDM.CLASS.getIri()),
                new DbCreateOperation(viewableAssociation, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(viewableAssociation, HQDM.ENTITY_NAME, "VIEWABLE_ASSOCIATION"),

                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, RDFS.RDF_TYPE, HQDM.KIND_OF_BIOLOGICAL_SYSTEM_COMPONENT.getIri()),
                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, HQDM.ENTITY_NAME, "KIND_OF_BIOLOGICAL_SYSTEM_HUMAN_COMPONENT"),

                new DbCreateOperation(kindOfPerson, RDFS.RDF_TYPE, HQDM.KIND_OF_PERSON.getIri()),
                new DbCreateOperation(kindOfPerson, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfPerson, HQDM.ENTITY_NAME,  "KIND_OF_PERSON"),

                new DbCreateOperation(classOfStateOfPerson, RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_PERSON.getIri()),
                new DbCreateOperation(classOfStateOfPerson, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(classOfStateOfPerson, HQDM.ENTITY_NAME, "CLASS_OF_STATE_OF_PERSON"),

                new DbCreateOperation(kindOfFunctionalSystemBuilding, RDFS.RDF_TYPE, HQDM.KIND_OF_FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemBuilding, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemBuilding, HQDM.ENTITY_NAME, "KIND_OF_FUNCTIONAL_SYSTEM_BUILDING"),

                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, RDFS.RDF_TYPE, HQDM.KIND_OF_FUNCTIONAL_SYSTEM_COMPONENT.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, HQDM.ENTITY_NAME, "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY_COMPONENT"),

                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE, HQDM.KIND_OF_FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, HQDM.ENTITY_NAME, "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY"),

                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM.getIri()),
                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, HQDM.ENTITY_NAME, "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY"),

                new DbCreateOperation(naturalMemberOfSocietyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(naturalMemberOfSocietyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(naturalMemberOfSocietyRole, HQDM.ENTITY_NAME, "NATURAL_MEMBER_OF_SOCIETY_ROLE"),

                new DbCreateOperation(domesticPropertyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(domesticPropertyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(domesticPropertyRole, HQDM.ENTITY_NAME, "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE"),

                new DbCreateOperation(domesticOccupantInPropertyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(domesticOccupantInPropertyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(domesticOccupantInPropertyRole, HQDM.ENTITY_NAME, "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE"),

                new DbCreateOperation(occupierOfPropertyRole, RDFS.RDF_TYPE, HQDM.ROLE.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, HQDM.ENTITY_NAME, "OCCUPIER_LOCATED_IN_PROPERTY_ROLE"),

                new DbCreateOperation(occupantInPropertyKindOfAssociation, RDFS.RDF_TYPE, HQDM.KIND_OF_ASSOCIATION.getIri()),
                new DbCreateOperation(occupantInPropertyKindOfAssociation, RDFS.RDF_TYPE, RDFS.RDFS_CLASS.getIri()),
                new DbCreateOperation(occupantInPropertyKindOfAssociation, HQDM.ENTITY_NAME, "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION"),

                // Create the class hierarchy
                new DbCreateOperation(viewableObject, HQDM.HAS_SUPERCLASS, viewable.getIri()),
                new DbCreateOperation(viewableObject, RDFS.RDFS_SUB_CLASS_OF, viewable.getIri()),

                new DbCreateOperation(viewableAssociation, HQDM.HAS_SUPERCLASS, viewable.getIri()),
                new DbCreateOperation(viewableAssociation, RDFS.RDFS_SUB_CLASS_OF, viewable.getIri()),

                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, HQDM.HAS_SUPERCLASS, kindOfFunctionalSystemBuilding.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, RDFS.RDFS_SUB_CLASS_OF, kindOfFunctionalSystemBuilding.getIri()),

                new DbCreateOperation(domesticOccupantInPropertyRole, HQDM.HAS_SUPERCLASS, domesticPropertyRole.getIri()),
                new DbCreateOperation(domesticOccupantInPropertyRole, RDFS.RDFS_SUB_CLASS_OF, domesticPropertyRole.getIri()),

                new DbCreateOperation(occupierOfPropertyRole, HQDM.HAS_SUPERCLASS, classOfStateOfPerson.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, RDFS.RDFS_SUB_CLASS_OF, classOfStateOfPerson.getIri()),


                // Set class memberships
                new DbCreateOperation(kindOfPerson, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(classOfStateOfPerson, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticProperty, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(classOfStateOfFunctionalSystemDomesticProperty, HQDM.MEMBER_OF, viewableObject.getIri()),
                new DbCreateOperation(occupantInPropertyKindOfAssociation, HQDM.MEMBER_OF, viewableAssociation.getIri()),

                // Set the has component by class predicates
                new DbCreateOperation(kindOfBiologicalSystemHumanComponent, HQDM.HAS_COMPONENT_BY_CLASS, kindOfPerson.getIri()),
                new DbCreateOperation(kindOfFunctionalSystemDomesticPropertyComponent, HQDM.HAS_COMPONENT_BY_CLASS, kindOfFunctionalSystemDomesticProperty.getIri()),

                // Set the consists of by class predicates
                new DbCreateOperation(domesticOccupantInPropertyRole, HQDM.CONSISTS_OF_BY_CLASS, occupantInPropertyKindOfAssociation.getIri()),
                new DbCreateOperation(occupierOfPropertyRole, HQDM.CONSISTS_OF_BY_CLASS, occupantInPropertyKindOfAssociation.getIri())
                    );

        return new DbChangeSet(Set.of(), creates);
    }

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

    /**
     * Create a DbChangeSet that adds the whole life individuals.
     *
     * @return {@link DbChangeSet}
     */
    private static DbChangeSet addWholeLifeIndividuals(final MagmaCoreDatabase db) {
        //
        // Find the required classes, kinds, and roles.
        final KindOfPerson kindOfPerson = findByEntityName(db, "KIND_OF_PERSON");
        final Role personRole = findByEntityName(db, "NATURAL_MEMBER_OF_SOCIETY_ROLE");
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
            findByEntityName(db, "KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role domesticPropertyRole =
            findByEntityName(db, "ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");

        final var possibleWorld = mkUserBaseIri();
        final var e1 = mkUserBaseIri();
        final var e2 = mkUserBaseIri();
        final var person = mkUserBaseIri();
        final var house = mkUserBaseIri();

        final var creates = Set.of(
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

        return new DbChangeSet(Set.of(), creates);
    }

    /**
     * Create a person-occupies-house association.
     *
     * @param db a {@link MagmaCoreDatabase}
     * @param creates {@link Set} of {@link DbCreateOperation}
     * @param possibleWorld a {@link PossibleWorld}
     * @param person the {@link Person} occupying the house.
     * @param house the house as a {@link FunctionalSystem} that is occupied.
     * @param beginning {@link Event}
     * @param ending {@link Event}
     */
    private static void occupyHouse(
            final MagmaCoreDatabase db,
            final Set<DbCreateOperation> creates,
            final PossibleWorld possibleWorld,
            final Person person,
            final FunctionalSystem house,
            final IRI beginning,
            final IRI ending) {

        // Find the required classes, kinds, and roles.
        final ClassOfStateOfPerson classOfStateOfPerson = findByEntityName(db, "CLASS_OF_STATE_OF_PERSON");
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
            findByEntityName(db, "STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role occupierOfPropertyRole = findByEntityName(db, "OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        final Role domesticOccupantInPropertyRole = findByEntityName(db, "DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        final KindOfAssociation occupantInPropertyKindOfAssociation =
            findByEntityName(db, "OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        final var personState = mkUserBaseIri();
        final var houseState = mkUserBaseIri();
        final var personParticipant = mkUserBaseIri();
        final var houseParticipant = mkUserBaseIri();
        final var houseOccupiedAssociation = mkUserBaseIri();

        creates.add(new DbCreateOperation(personState, RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON.getIri()));
        creates.add(new DbCreateOperation(personState, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(personState, HQDM.MEMBER_OF, classOfStateOfPerson.getId()));
        creates.add(new DbCreateOperation(personState, HQDM.TEMPORAL_PART_OF, person.getId()));
        creates.add(new DbCreateOperation(personState, HQDM.BEGINNING, beginning.getIri()));
        creates.add(new DbCreateOperation(personState, HQDM.ENDING, ending.getIri()));

        creates.add(new DbCreateOperation(houseState, RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM.getIri()));
        creates.add(new DbCreateOperation(houseState, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(houseState, HQDM.MEMBER_OF, classOfStateOfFunctionalSystemDomesticProperty.getId()));
        creates.add(new DbCreateOperation(houseState, HQDM.TEMPORAL_PART_OF, house.getId()));
        creates.add(new DbCreateOperation(houseState, HQDM.BEGINNING, beginning.getIri()));
        creates.add(new DbCreateOperation(houseState, HQDM.ENDING, ending.getIri()));

        creates.add(new DbCreateOperation(personParticipant, RDFS.RDF_TYPE, HQDM.PARTICIPANT.getIri()));
        creates.add(new DbCreateOperation(personParticipant, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(personParticipant, HQDM.MEMBER_OF_KIND, occupierOfPropertyRole.getId()));
        creates.add(new DbCreateOperation(personParticipant, HQDM.TEMPORAL_PART_OF, personState.getIri()));
        creates.add(new DbCreateOperation(personParticipant, HQDM.BEGINNING, beginning.getIri()));
        creates.add(new DbCreateOperation(personParticipant, HQDM.ENDING, ending.getIri()));

        creates.add(new DbCreateOperation(houseParticipant, RDFS.RDF_TYPE, HQDM.PARTICIPANT.getIri()));
        creates.add(new DbCreateOperation(houseParticipant, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(houseParticipant, HQDM.MEMBER_OF_KIND, domesticOccupantInPropertyRole.getId()));
        creates.add(new DbCreateOperation(houseParticipant, HQDM.TEMPORAL_PART_OF, houseState.getIri()));
        creates.add(new DbCreateOperation(houseParticipant, HQDM.BEGINNING, beginning.getIri()));
        creates.add(new DbCreateOperation(houseParticipant, HQDM.ENDING, ending.getIri()));

        creates.add(new DbCreateOperation(houseOccupiedAssociation, RDFS.RDF_TYPE, HQDM.ASSOCIATION.getIri()));
        creates.add(new DbCreateOperation(houseOccupiedAssociation, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(houseOccupiedAssociation, HQDM.MEMBER_OF_KIND, occupantInPropertyKindOfAssociation.getId()));
        creates.add(new DbCreateOperation(houseOccupiedAssociation, HQDM.CONSISTS_OF_PARTICIPANT, houseParticipant.getIri()));
        creates.add(new DbCreateOperation(houseOccupiedAssociation, HQDM.CONSISTS_OF_PARTICIPANT, personParticipant.getIri()));
        creates.add(new DbCreateOperation(houseOccupiedAssociation, HQDM.BEGINNING, beginning.getIri()));
        creates.add(new DbCreateOperation(houseOccupiedAssociation, HQDM.ENDING, ending.getIri()));
    }

    /**
     * Add occupancy predicates.
     *
     * @param db {@link MagmaCoreDatabase}
     * @return {@link MagmaCoreDatabase}
    */
    private static DbChangeSet addHouseOccupancies(final MagmaCoreDatabase db) {
        // Use an existing PossibleWorld
        final PossibleWorld possibleWorld = findByEntityName(db, "Example1_World");

        // The person occupies the house twice at different times.
        final Person person = findByEntityName(db, "PersonB1_Bob");
        final FunctionalSystem house = findByEntityName(db, "HouseB");

        // Create the bounding events for the associations
        final var e2 = mkUserBaseIri();
        final var e3 = mkUserBaseIri();
        final var e4 = mkUserBaseIri();
        final var e5 = mkUserBaseIri();

        final var creates = Set.of(
                new DbCreateOperation(e2, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e2, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()),
                new DbCreateOperation(e2, HQDM.ENTITY_NAME, "2020-08-15T17:50:00"),

                new DbCreateOperation(e3, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e3, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()),
                new DbCreateOperation(e3, HQDM.ENTITY_NAME, "2020-08-15T19:21:00"),

                new DbCreateOperation(e4, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e4, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()),
                new DbCreateOperation(e4, HQDM.ENTITY_NAME, "2020-08-16T22:33:00"),

                new DbCreateOperation(e5, RDFS.RDF_TYPE, HQDM.EVENT.getIri()),
                new DbCreateOperation(e5, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()),
                new DbCreateOperation(e5, HQDM.ENTITY_NAME, "2020-08-17T10:46:00")
        );

        occupyHouse(db, creates, possibleWorld, person, house, e2, e3);
        occupyHouse(db, creates, possibleWorld, person, house, e4, e5);

        return new DbChangeSet(Set.of(), creates);
    }

    // A BiFunction to create the RDL
    private static ComposableBiFunction<DbTransformation, MagmaCoreDatabase, MagmaCoreDatabase> createRefDataObjects = (t, d) -> {
        final var changeSet = createRefDataObjects();
        t.add(changeSet);
        return changeSet.apply(d);
    };

    // A BiFunction to add the individuals
    private static ComposableBiFunction<DbTransformation, MagmaCoreDatabase, MagmaCoreDatabase> addWholeLifeIndividuals = (t, d) -> {
        final var changeSet = addWholeLifeIndividuals(d);
        t.add(changeSet);
        return changeSet.apply(d);
    };

    // A BiFunction to create the occupancy associations
    private static ComposableBiFunction<DbTransformation, MagmaCoreDatabase, MagmaCoreDatabase> addHouseOccupancies = (t, d) -> {
        final var changeSet = addHouseOccupancies(d);
        t.add(changeSet);
        return changeSet.apply(d);
    };

    /**
     * A function that populates a database.
     *
     * @param db a {@link MagmaCoreDatabase}
     * @return {@link DbTransformation}
     */
    public static DbTransformation populateExampleData(final MagmaCoreDatabase db) {

        // Apply the transformation to the database. There are dependencies between these change sets 
        // since they both depend on RDL being present, but also the occupancies depend on the 
        // individuals being present, so each change set needs to be applied before the next one 
        // can be created.
        final var transform = 
            createRefDataObjects
            .andThen(addWholeLifeIndividuals)
            .andThen(addHouseOccupancies);

        // This will be updated to record the transformation that took place.
        final var result = new DbTransformation();

        // Apply the transform to the database
        transform.apply(result, db);

        // Return the DbTransformation that took place.
        return result;
    }

}
