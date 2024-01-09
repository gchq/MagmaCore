package uk.gov.gchq.magmacore.hqdm.rdfbuilders;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.ClassOfPerson;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.KindOfPerson;
import uk.gov.gchq.magmacore.hqdm.model.OrdinaryBiologicalObject;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfClassServices;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Test that the PersonBuild works as expected.
 */
public class PersonBuilderTest {

    @Test
    public void testBuilder() {
        // Use the simple RdfServices for these test entities.
        final SpatioTemporalExtent spatioTemporalExtent = RdfSpatioTemporalExtentServices
                .createSpatioTemporalExtent(randomIri());
        final Event event = RdfSpatioTemporalExtentServices.createEvent(randomIri());
        final ClassOfPerson classOfPerson = RdfClassServices.createClassOfPerson(randomIri());
        final KindOfPerson kindOfPerson = RdfClassServices.createKindOfPerson(randomIri());
        final Role role = RdfClassServices.createRole(randomIri());
        final PossibleWorld possibleWorld = RdfSpatioTemporalExtentServices.createPossibleWorld(randomIri());
        final OrdinaryBiologicalObject ordinaryBiologicalObject = RdfSpatioTemporalExtentServices
                .createOrdinaryBiologicalObject(randomIri());

        // Use PersonBuilder for this entity.
        final Person person = new PersonBuilder(new IRI(HQDM.HQDM, UUID.randomUUID().toString()))
                .aggregated_Into(spatioTemporalExtent)
                .beginning(event)
                .consists__Of(spatioTemporalExtent)
                .ending(event)
                .member_Of(classOfPerson)
                .member_Of_Kind(kindOfPerson)
                .member__Of(classOfPerson)
                .natural_Role_M(role)
                .part_Of_Possible_World_M(possibleWorld)
                .part__Of(spatioTemporalExtent)
                .temporal_Part_Of(ordinaryBiologicalObject)
                .temporal__Part_Of(spatioTemporalExtent)
                .build();

        assertNotNull(person);
        assertTrue(person.hasThisValue(RDFS.RDF_TYPE, HQDM.PERSON));
        assertTrue(person.hasThisValue(HQDM.AGGREGATED_INTO, iriFromThing(spatioTemporalExtent)));
        assertTrue(person.hasThisValue(HQDM.BEGINNING, iriFromThing(event)));
        assertTrue(person.hasThisValue(HQDM.CONSISTS__OF, iriFromThing(spatioTemporalExtent)));
        assertTrue(person.hasThisValue(HQDM.ENDING, iriFromThing(event)));
        assertTrue(person.hasThisValue(HQDM.MEMBER_OF, iriFromThing(classOfPerson)));
        assertTrue(person.hasThisValue(HQDM.MEMBER_OF_KIND, iriFromThing(kindOfPerson)));
        assertTrue(person.hasThisValue(HQDM.MEMBER__OF, iriFromThing(classOfPerson)));
        assertTrue(person.hasThisValue(HQDM.NATURAL_ROLE, iriFromThing(role)));
        assertTrue(person.hasThisValue(HQDM.PART_OF_POSSIBLE_WORLD, iriFromThing(possibleWorld)));
        assertTrue(person.hasThisValue(HQDM.PART__OF, iriFromThing(spatioTemporalExtent)));
        assertTrue(person.hasThisValue(HQDM.TEMPORAL_PART_OF, iriFromThing(ordinaryBiologicalObject)));
        assertTrue(person.hasThisValue(HQDM.TEMPORAL__PART_OF, iriFromThing(spatioTemporalExtent)));
    }

    private IRI iriFromThing(final Thing thing) {
        return new IRI(thing.getId());
    }

    private IRI randomIri() {
        return new IRI(HQDM.HQDM, UUID.randomUUID().toString());
    }
}
