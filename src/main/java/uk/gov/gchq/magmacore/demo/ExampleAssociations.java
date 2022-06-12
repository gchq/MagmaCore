package uk.gov.gchq.magmacore.demo;

import java.util.HashSet;
import java.util.Set;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.RDFS;
import uk.gov.gchq.hqdm.model.ClassOfStateOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.ClassOfStateOfPerson;
import uk.gov.gchq.hqdm.model.Event;
import uk.gov.gchq.hqdm.model.FunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfAssociation;
import uk.gov.gchq.hqdm.model.Person;
import uk.gov.gchq.hqdm.model.PossibleWorld;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.service.DbChangeSet;
import uk.gov.gchq.magmacore.service.DbCreateOperation;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleAssociations {

    /**
     * Create a person-occupies-house association.
     *
     * @param mcService a {@link MagmaCoreDatabase}
     * @param creates {@link Set} of {@link DbCreateOperation}
     * @param possibleWorld a {@link PossibleWorld}
     * @param person the {@link Person} occupying the house.
     * @param house the house as a {@link FunctionalSystem} that is occupied.
     * @param beginning {@link Event}
     * @param ending {@link Event}
     */
    private static void occupyHouse(
            final MagmaCoreService mcService,
            final Set<DbCreateOperation> creates,
            final PossibleWorld possibleWorld,
            final Person person,
            final FunctionalSystem house,
            final IRI beginning,
            final IRI ending) {

        // Find the required classes, kinds, and roles.
        final ClassOfStateOfPerson classOfStateOfPerson = mcService.findByEntityName("CLASS_OF_STATE_OF_PERSON");
        final ClassOfStateOfFunctionalSystem classOfStateOfFunctionalSystemDomesticProperty =
            mcService.findByEntityName("STATE_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role occupierOfPropertyRole = mcService.findByEntityName("OCCUPIER_LOCATED_IN_PROPERTY_ROLE");
        final Role domesticOccupantInPropertyRole = mcService.findByEntityName("DOMESTIC_PROPERTY_THAT_IS_OCCUPIED_ROLE");
        final KindOfAssociation occupantInPropertyKindOfAssociation =
            mcService.findByEntityName("OCCUPANT_LOCATED_IN_VOLUME_ENCLOSED_BY_PROPERTY_ASSOCIATION");

        // Create DbCreateOperations to create the objects and their properties.
        final var personState = ExampleCommonUtils.mkUserBaseIri();
        final var houseState = ExampleCommonUtils.mkUserBaseIri();
        final var personParticipant = ExampleCommonUtils.mkUserBaseIri();
        final var houseParticipant = ExampleCommonUtils.mkUserBaseIri();
        final var houseOccupiedAssociation = ExampleCommonUtils.mkUserBaseIri();

        // Create DbCreateOperations to create the objects and their properties.
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
     * @param mcService {@link MagmaCoreDatabase}
     * @return {@link MagmaCoreDatabase}
    */
    public static DbChangeSet addHouseOccupancies(final MagmaCoreService mcService) {
        // Use an existing PossibleWorld
        final PossibleWorld possibleWorld = mcService.findByEntityName("Example1_World");

        // The person occupies the house twice at different times.
        final Person person = mcService.findByEntityName("PersonB1_Bob");
        final FunctionalSystem house = mcService.findByEntityName("HouseB");

        // Create IRIs for the objects we want to create.
        final var e2 = ExampleCommonUtils.mkUserBaseIri();
        final var e3 = ExampleCommonUtils.mkUserBaseIri();
        final var e4 = ExampleCommonUtils.mkUserBaseIri();
        final var e5 = ExampleCommonUtils.mkUserBaseIri();

        // Create DbCreateOperations to create the objects and their properties.
        final var creates = new HashSet<DbCreateOperation>();
        creates.add(new DbCreateOperation(e2, RDFS.RDF_TYPE, HQDM.EVENT.getIri()));
        creates.add(new DbCreateOperation(e2, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(e2, HQDM.ENTITY_NAME, "2020-08-15T17:50:00"));

        creates.add(new DbCreateOperation(e3, RDFS.RDF_TYPE, HQDM.EVENT.getIri()));
        creates.add(new DbCreateOperation(e3, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(e3, HQDM.ENTITY_NAME, "2020-08-15T19:21:00"));

        creates.add(new DbCreateOperation(e4, RDFS.RDF_TYPE, HQDM.EVENT.getIri()));
        creates.add(new DbCreateOperation(e4, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(e4, HQDM.ENTITY_NAME, "2020-08-16T22:33:00"));

        creates.add(new DbCreateOperation(e5, RDFS.RDF_TYPE, HQDM.EVENT.getIri()));
        creates.add(new DbCreateOperation(e5, HQDM.PART_OF_POSSIBLE_WORLD, possibleWorld.getId()));
        creates.add(new DbCreateOperation(e5, HQDM.ENTITY_NAME, "2020-08-17T10:46:00"));

        // Add more DbCreateOperations to create the required associations.
        occupyHouse(mcService, creates, possibleWorld, person, house, e2, e3);
        occupyHouse(mcService, creates, possibleWorld, person, house, e4, e5);

        // Create and return a new change set.
        return new DbChangeSet(Set.of(), creates);
    }
}
