package uk.gov.gchq.magmacore.examples.plans;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import uk.gov.gchq.magmacore.hqdm.model.ClassOfState;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Plan;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Requirement;
import uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.ClassOfStateBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.ClassOfStateOfFunctionalObjectBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.ClassOfStateOfFunctionalSystemBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.PlanBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.PointInTimeBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.RequirementBuilder;
import uk.gov.gchq.magmacore.hqdm.rdfbuilders.RequirementSpecificationBuilder;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * An example of plans and requirements.
 */
public class PlanAndRequirementsExample {

    private static final String PREFIXES = """
        @prefix tst: <https://example.com/test#> .
        @prefix date: <https://example.com/date#> .
        @prefix h: <https://hqdmtop.github.io/hqdmOntology/1.0.0#> .
        @prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
        @prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
        @prefix skos: <http://www.w3.org/2004/02/skos/core#> .
                """;

    private static final IriBase BASE = new IriBase("tst", "https://example.com/test#");
    private static final IriBase DATE_BASE = new IriBase("date", "https://example.com/date#");
    private static final Instant START = Instant.now();
    private static long nextIri = 0L;

    private static String nextIri() {
        return Long.toString(nextIri++);
    }

    @Test
    public void testPlans() {
        final var objects = new HashMap<IRI, Thing>();

        //
        // Create the plan in the real world.
        //
        final var realWorld = getTheRealWorld();
        objects.put(realWorld.getId(), realWorld);

        final var planStartEvent = getCarServicePlanStartEvent(realWorld);
        objects.put(planStartEvent.getId(), planStartEvent);

        final var planEndEvent = getCarServicePlanEndEvent(realWorld);
        objects.put(planEndEvent.getId(), planEndEvent);

        final var carServicePlan = getCarServicePlan(realWorld, planStartEvent, planEndEvent);
        objects.put(carServicePlan.getId(), carServicePlan);

        //
        // Create the serviced car requirement.
        //
        final var servicedCarClassOfState = getServicedCarClassOfState();
        Arrays.stream(servicedCarClassOfState).forEach(cos -> {
            objects.put(cos.getId(), cos);
        });

        final var servicedCarRequirementSpecification = getServicedCarRequirementSpecification(servicedCarClassOfState);
        objects.put(servicedCarRequirementSpecification.getId(), servicedCarRequirementSpecification);

        final var servicedCarRequirementStartEvent = getServicedCarRequirementStartEvent(realWorld);
        objects.put(servicedCarRequirementStartEvent.getId(), servicedCarRequirementStartEvent);

        final var servicedCarRequirementEndEvent = getServicedCarRequirementEndEvent(realWorld);
        objects.put(servicedCarRequirementEndEvent.getId(), servicedCarRequirementEndEvent);

        final var servicedCarRequirement = getServicedCarRequirement(realWorld, carServicePlan, servicedCarRequirementSpecification,
            servicedCarRequirementStartEvent, servicedCarRequirementEndEvent);
        objects.put(servicedCarRequirement.getId(), servicedCarRequirement);

        //
        // Create the car service activity requirement.
        //
        final var serviceActivityClassOfState = getCarServiceActivityClassOfState();
        Arrays.stream(serviceActivityClassOfState).forEach(cos -> {
            objects.put(cos.getId(), cos);
        });

        final var serviceActivityRequirementsSpec = getServiceActivityRequirementsSpec(serviceActivityClassOfState);
        objects.put(serviceActivityRequirementsSpec.getId(), serviceActivityRequirementsSpec);

        final var serviceActivityStartEvent = getServiceActivityStartEvent(realWorld);
        objects.put(serviceActivityStartEvent.getId(), serviceActivityStartEvent);

        final var serviceActivityEndEvent = getServiceActivityEndEvent(realWorld);
        objects.put(serviceActivityEndEvent.getId(), serviceActivityEndEvent);

        final var serviceActivityRequirement = getServiceActivityRequirement(realWorld, carServicePlan, serviceActivityRequirementsSpec, 
                serviceActivityStartEvent, serviceActivityEndEvent, servicedCarRequirementStartEvent);
        objects.put(serviceActivityRequirement.getId(), serviceActivityRequirement);
        //
        // Create evertying in the database.
        //
        final var mcs = MagmaCoreServiceFactory.createWithJenaDatabase();
        mcs.runInWriteTransaction(svc -> {
            objects.values().stream().forEach(svc::create);
            return svc;
        });

        System.out.println();
        System.out.println(PREFIXES);
        mcs.exportTtl(System.out);
        System.out.println();
    }

    private Requirement getServiceActivityRequirement(final PossibleWorld realWorld, final Plan plan, final RequirementSpecification serviceActivityRequirementsSpec,
        final Event beginning, final Event ending, final Event servicedCarStartEvent) {

        final var iri = new IRI(BASE, nextIri());
        final var req = new RequirementBuilder(iri)
            .part_Of_Plan_M(plan)
            .part_Of_Possible_World_M(realWorld)
            .defined_By_M(serviceActivityRequirementsSpec)
            .beginning(beginning)
            .ending(ending)
            .build();
        req.addStringValue(HQDM.SKOS_DEFINITION, "Car 50k mile service activity");
        req.addValue(HQDM.CAUSES, servicedCarStartEvent.getId());

        return req;
    }

    private Event getServiceActivityEndEvent(final PossibleWorld realWorld) {
        final var date = START.plus(300, ChronoUnit.DAYS).plus(4, ChronoUnit.HOURS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(realWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private Event getServiceActivityStartEvent(final PossibleWorld realWorld) {
        final var date = START.plus(300, ChronoUnit.DAYS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(realWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private RequirementSpecification getServiceActivityRequirementsSpec(final ClassOfState[] classOfState) {
        if (classOfState.length == 0) {
            throw new InvalidParameterException("Need at  least one classOfState");
        }
        final var iri = new IRI(BASE, nextIri());
        final var spec = new RequirementSpecificationBuilder(iri)
            .intersection_Of_M(classOfState[0])
            .build();
        spec.addStringValue(HQDM.SKOS_DEFINITION, "50k mile car service activity specification");
        if (classOfState.length > 1) {
            for (int i = 1; i < classOfState.length; i++) {
                spec.addValue(HQDM.INTERSECTION_OF, classOfState[i].getId());
            }
        }
        return spec;
    }

    private ClassOfState[] getCarServiceActivityClassOfState() {
        final var iri = new IRI(BASE, nextIri());
        final var classOfState = new ClassOfStateBuilder(iri).build();
        classOfState.addStringValue(HQDM.SKOS_DEFINITION, "Car 50K mile service activity class of state");
        return new ClassOfState[] {
            classOfState
        };
    }

    private Event getServicedCarRequirementEndEvent(final PossibleWorld realWorld) {
        final var date = START.plus(365, ChronoUnit.DAYS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(realWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private Event getServicedCarRequirementStartEvent(final PossibleWorld realWorld) {
        final var date = START.plus(300, ChronoUnit.DAYS).plus(4, ChronoUnit.HOURS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(realWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private ClassOfState[] getServicedCarClassOfState() {
        final var carStateIri = new IRI(BASE, nextIri());
        final var serviceStateIri = new IRI(BASE, nextIri());
        final var workingStateIri = new IRI(BASE, nextIri());
        final var carState = new ClassOfStateOfFunctionalSystemBuilder(carStateIri).build();
        final var serviceState = new ClassOfStateOfFunctionalObjectBuilder(serviceStateIri).build();
        final var workingState = new ClassOfStateBuilder(workingStateIri).build();
        carState.addStringValue(HQDM.SKOS_DEFINITION, "State Of Car");
        serviceState.addStringValue(HQDM.SKOS_DEFINITION, "Car with 50k mile service");
        workingState.addStringValue(HQDM.SKOS_DEFINITION, "Functioning Car");
        return new ClassOfState[] {
            carState,
            serviceState,
            workingState
        };
    }

    private Requirement getServicedCarRequirement(
        final PossibleWorld realWorld,
        final Plan plan,
        final RequirementSpecification servicedCarRequirementSpecification,
        final Event beginning,
        final Event ending) {

        final var iri = new IRI(BASE, nextIri());
        final var req = new RequirementBuilder(iri)
            .part_Of_Plan_M(plan)
            .part_Of_Possible_World_M(realWorld)
            .defined_By_M(servicedCarRequirementSpecification)
            .beginning(beginning)
            .ending(ending)
            .build();
        req.addStringValue(HQDM.SKOS_DEFINITION, "Car with 50k mile service");

        return req;
    }

    private RequirementSpecification getServicedCarRequirementSpecification(final ClassOfState... classOfState) {
        if (classOfState.length == 0) {
            throw new InvalidParameterException("Need at  least one classOfState");
        }
        final var iri = new IRI(BASE, nextIri());
        final var spec = new RequirementSpecificationBuilder(iri)
            .intersection_Of_M(classOfState[0])
            .build();
        spec.addStringValue(HQDM.SKOS_DEFINITION, "Car with 50k mile service");
        if (classOfState.length > 1) {
            for (int i = 1; i < classOfState.length; i++) {
                spec.addValue(HQDM.INTERSECTION_OF, classOfState[i].getId());
            }
        }
        return spec;
    }

    private Plan getCarServicePlan(final PossibleWorld realWorld, final Event planStartEvent, final Event planEndEvent) {

        final var planIri = new IRI(BASE, nextIri());
        final var plan = new PlanBuilder(planIri)
            .part_Of_Possible_World_M(realWorld)
            .beginning(planStartEvent)
            .ending(planEndEvent)
            .build();

        plan.addStringValue(HQDM.SKOS_DEFINITION, "Car Servicing Plan");
        return plan;
    }

    private Event getCarServicePlanEndEvent(final PossibleWorld realWorld) {
        final var date = START.plus(365, ChronoUnit.DAYS);
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(realWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private Event getCarServicePlanStartEvent(final PossibleWorld realWorld) {
        final var date = START;
        final var eventIri = new IRI(DATE_BASE, instantToString(date));
        final var event = new PointInTimeBuilder(eventIri)
            .part_Of_Possible_World_M(realWorld)
            .build();
        event.addStringValue(HQDM.SKOS_DEFINITION, date.toString());
        return event;
    }

    private PossibleWorld getTheRealWorld() {
        final var possibleWorldIri = new IRI(BASE, nextIri());
        final var realWorld = SpatioTemporalExtentServices.createPossibleWorld(possibleWorldIri);
        realWorld.addStringValue(HQDM.SKOS_DEFINITION, "The Real World");
        return realWorld;
    }

    private static String instantToString(final Instant when) {
        return Long.toString(when.getEpochSecond()) + Long.toString(when.getNano());
    }
}
