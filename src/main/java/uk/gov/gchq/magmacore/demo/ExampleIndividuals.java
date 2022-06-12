package uk.gov.gchq.magmacore.demo;

import java.util.Set;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.RDFS;
import uk.gov.gchq.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.hqdm.model.KindOfPerson;
import uk.gov.gchq.hqdm.model.Role;
import uk.gov.gchq.magmacore.service.DbChangeSet;
import uk.gov.gchq.magmacore.service.DbCreateOperation;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleIndividuals {

    /**
     * Create a DbChangeSet that adds the whole life individuals.
     *
     * @return {@link DbChangeSet}
     */
    public static DbChangeSet addWholeLifeIndividuals(final MagmaCoreService mcService) {

        // Find the required classes, kinds, and roles.
        final KindOfPerson kindOfPerson = mcService.findByEntityName("KIND_OF_PERSON");
        final Role personRole = mcService.findByEntityName("NATURAL_MEMBER_OF_SOCIETY_ROLE");
        final KindOfFunctionalSystem kindOfFunctionalSystemDomesticProperty =
            mcService.findByEntityName("KIND_OF_FUNCTIONAL_SYSTEM_DOMESTIC_PROPERTY");
        final Role domesticPropertyRole =
            mcService.findByEntityName("ACCEPTED_PLACE_OF_SEMI_PERMANENT_HABITATION_ROLE");

        // Create IRIs for the objects we want to create.
        final var possibleWorld = ExampleCommonUtils.mkUserBaseIri();
        final var e1 = ExampleCommonUtils.mkUserBaseIri();
        final var e2 = ExampleCommonUtils.mkUserBaseIri();
        final var person = ExampleCommonUtils.mkUserBaseIri();
        final var house = ExampleCommonUtils.mkUserBaseIri();

        // Create DbCreateOperations to create the objects and their properties.
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

        // Create a change set and return it.
        return new DbChangeSet(Set.of(), creates);
    }
}
