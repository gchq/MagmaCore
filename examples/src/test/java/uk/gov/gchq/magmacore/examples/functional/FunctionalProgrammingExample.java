package uk.gov.gchq.magmacore.examples.functional;

import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.util.UUID;
import java.util.function.Function;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.Activity;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfPerson;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.Person;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.StateOfPerson;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * A Functional Programming example for using MagmaCore.
 *
 * <p>
 * The purpose of this example is to try to write a program that is more readable than the other examples.
 * The {@link #test() test} method shows a clear sequence of steps carried out by the use case, and it 
 * should be possible to write the steps as reusable and composable functions. 
 * </p>
 */
public class FunctionalProgrammingExample {

    /**
     * A unit test showing how to use functional programming with MagmaCore.
     */
    @Test
    public void test() {

        /*
         * Use function composition to build up a program that we will run later.
         * The program will use a Context object to keep track of entities that 
         * are created. All functions in the processing chain accept a Context,
         * mutate it, then return it. Ideally a `record` would be used instead
         * and Lombok could be used to add 'wither' methods so that the Context
         * could be immutable.
         */
        final Function<Context, Context> program = 

            /*
             * First create a MagmaCoreService in the Context.
             */
            createMagmaCoreService

            /*
             * The first transaction will populate the Reference Data needed by this test.
             * Normally such data will already exist in the database and this step will not 
             * be necessary.
             */
            .andThen(beginWriteTransaction)
            .andThen(populateRefData)
            .andThen(commitTransaction)

            /*
             * Often a program will need to look up some existing entities for Reference
             * Data needed by the use case. In this case they are stored in the Context.
             */
            .andThen(beginReadTransaction)
            .andThen(findRefData)
            .andThen(commitTransaction)

            /*
             * New entities can be created making use of the Reference Data. No transaction
             * is needed since the entities will be persisted at the end.
             */
            .andThen(createPerson)
            .andThen(createResearchActivity)
            .andThen(createPersonAsParticipantInActivity)
            
            /*
             * The last transaction persists all of the new entities.
             */
            .andThen(beginWriteTransaction)
            .andThen(creatEntities)
            .andThen(commitTransaction);

        /*
         * Now execute the program created above.
         */
        final Context ctx = program.apply(new Context());

        /*
         * Check that the results are as expected.
         */
        assertNotNull(ctx.magmaCore.get(ctx.person.getId()));
        assertNotNull(ctx.magmaCore.get(ctx.researchActivity.getId()));
        assertNotNull(ctx.magmaCore.get(ctx.startOfResearch.getId()));
        assertNotNull(ctx.magmaCore.get(ctx.stateOfPerson.getId()));
    }

    /**
     * A class to hold the entities created and referenced by the use case.
     *
     * <p>
     * All fields are public for ease of access and to reduce clutter from 
     * adding getters and setters. Lombok could be used to generate them
     * without adding clutter.
     * </p>
     */
    private static class Context {
        public MagmaCoreService magmaCore;

        // New entities to be created.
        public Activity researchActivity;
        public Person person;
        public PointInTime startOfResearch;
        public StateOfPerson stateOfPerson;

        // Ref data items.
        public ClassOfPerson researchersClass;
        public KindOfActivity researchActivityKind;
        public Role researchRole;
    }

    /**
     * An IRI prefix for the test.
     */
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    /**
     * A class name for collecting together persons who are reseaechers.
     */
    private static final String RESEARCHERS_CLASS_ENTITY_NAME = "Researchers";

    /**
     * The name of a kind of activity.
     */
    private static final String RESEARCH_ACTIVITY_KIND_ENTITY_NAME = "Research Activities";

    /**
     * The name of a role for participants of research activities.
     */
    private static final String RESEARCHER_ROLE_ENTITY_NAME = "Researcher Role";

    /**
     * A function to create the MagmaCoreService. In this case it is an in-memory
     * database for the unit test.
     */
    private static final Function<Context, Context> createMagmaCoreService = ctx -> {
        ctx.magmaCore = MagmaCoreServiceFactory.createWithJenaDatabase();
        return ctx;
    };

    /**
     * A function to persist the new entities in the database.
     */
    private static final Function<Context, Context> creatEntities = ctx -> {
        ctx.magmaCore.create(ctx.person);
        ctx.magmaCore.create(ctx.researchActivity);
        ctx.magmaCore.create(ctx.startOfResearch);
        ctx.magmaCore.create(ctx.stateOfPerson);
        return ctx;
    };

    /**
     * A function to find the Reference Data required by this use case.
     */
    private static final Function<Context, Context> findRefData = ctx -> {
        ctx.researchActivityKind = ctx.magmaCore.findByEntityName(RESEARCH_ACTIVITY_KIND_ENTITY_NAME);
        ctx.researchRole = ctx.magmaCore.findByEntityName(RESEARCHER_ROLE_ENTITY_NAME);
        ctx.researchersClass = ctx.magmaCore.findByEntityName(RESEARCHERS_CLASS_ENTITY_NAME);
        return ctx;
    };

    /**
     * A function to create a Person.
     */
    private static final Function<Context, Context> createPerson = ctx -> {

        /*
         * Create a Person.
         */
        ctx.person = SpatioTemporalExtentServices
            .createPerson(randomIri());
        ctx.person.addValue(HQDM.MEMBER_OF, ctx.researchersClass.getId());
        return ctx;
    };

    /**
     * A function to create an Activity.
     */
    private static final Function<Context, Context> createResearchActivity = ctx -> {
        /*
         * Create a timestamp for use as the beginning of the axctivity.
         */
        final String now = Instant.now().toString();

        /*
         * Create a PointInTime event.
         */
        ctx.startOfResearch = SpatioTemporalExtentServices
            .createPointInTime(randomIri());
        ctx.startOfResearch.addValue(HQDM.VALUE_, now);

        /*
         * Create the Activity.
         */
        ctx.researchActivity = SpatioTemporalExtentServices
            .createActivity(randomIri());
        ctx.researchActivity.addValue(HQDM.BEGINNING, ctx.startOfResearch.getId());
        ctx.researchActivity.addValue(HQDM.MEMBER_OF_KIND, ctx.researchActivityKind.getId());
        return ctx;
    };

    /**
     * A function to add a state of person as a participant in the research activity.
     */
    private static final Function<Context, Context> createPersonAsParticipantInActivity = ctx -> {
        /*
         * The state of person will be a participant in the research activity.
         */
        ctx.stateOfPerson = SpatioTemporalExtentServices
            .createStateOfPerson(randomIri());
        ctx.stateOfPerson.addValue(HQDM.BEGINNING, ctx.startOfResearch.getId());
        ctx.stateOfPerson.addValue(HQDM.MEMBER_OF_KIND, ctx.researchRole.getId());
        ctx.stateOfPerson.addValue(HQDM.PARTICIPANT_IN, ctx.researchActivity.getId());
        ctx.stateOfPerson.addValue(HQDM.TEMPORAL_PART_OF, ctx.person.getId());
        ctx.stateOfPerson.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);

        return ctx;
    };

    /**
     * A utility function to generate random IRI values.
     */
    private static final IRI randomIri() {
        return new IRI(TEST_BASE, UUID.randomUUID().toString());
    }

    /**
     * A function to populate Reference Data for the test.
     */
    private Function<Context, Context> populateRefData = ctx -> {

        /*
         * Create a Class of Person.
         */
        final ClassOfPerson cop = ClassServices.createClassOfPerson(randomIri());
        cop.addValue(HQDM.ENTITY_NAME, RESEARCHERS_CLASS_ENTITY_NAME);
        ctx.magmaCore.create(cop);

        /*
         * Create a Kind of Activity.
         */
        final KindOfActivity koa = ClassServices.createKindOfActivity(randomIri());
        koa.addValue(HQDM.ENTITY_NAME, RESEARCH_ACTIVITY_KIND_ENTITY_NAME);
        ctx.magmaCore.create(koa);

        /*
         * Create a Role.
         */
        final Role role = ClassServices.createRole(randomIri());
        role.addValue(HQDM.ENTITY_NAME, RESEARCHER_ROLE_ENTITY_NAME);
        ctx.magmaCore.create(role);

        return ctx;
    };

    /**
     * A function to begin a read transaction.
     */
    private final Function<Context, Context> beginReadTransaction = magmaCore -> {
        magmaCore.magmaCore.beginWrite();
        return magmaCore;
    };

    /**
     * A function to begin a write transaction.
     */
    private final Function<Context, Context> beginWriteTransaction = magmaCore -> {
        magmaCore.magmaCore.beginWrite();
        return magmaCore;
    };

    /**
     * A function to commit a transaction.
     */
    private final Function<Context, Context> commitTransaction = magmaCore -> {
        magmaCore.magmaCore.commit();
        return magmaCore;
    };

}

