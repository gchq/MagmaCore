package uk.gov.gchq.magmacore.examples.extensions;

import java.util.List;

import uk.gov.gchq.magmacore.examples.extensions.f35.ext.F35ExtensionService;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135Engine;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135EngineSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135Turbine;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135TurbineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F135VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35Airframe;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35AirframeComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EjectionSeatComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35EngineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35TurbineComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.F35VerticalLiftSystemComponent;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135EngineInF135EngineSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135EngineInF35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135TurbineInF135EngineSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135TurbineInF35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135VLSInF135EngineSystem;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF135VLSInF35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledF35AirframeInF35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.InstalledUS16EEjectionSeatInF35Aircraft;
import uk.gov.gchq.magmacore.examples.extensions.f35.model.US16EEjectionSeat;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * Test the model extension provided by the model-extension-f35 module.
 *
 * <p>
 * Run this using mvn exec:java
 * -Dexec.mainClass=uk.gov.gchq.magmacore.examples.extensions.F35ModelExtensionTest
 * </p>
 */
public class F35ModelExtensionTest {

    // Declare an IRI base for the data to be created.
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");

    private static final F35ExtensionService f35 = new F35ExtensionService();

    /**
     * Main entry point.
     *
     * @param args
     *            a String array
     */
    public static void main(final String[] args) {
        // Create a MagmaCoreService with an in-memory Apache Jena database.
        final var mcs = MagmaCoreServiceFactory.createWithJenaDatabase();
        mcs.loadTtl(f35.getDomainTtl());

        // Persist the entity in the database.
        mcs.runInWriteTransaction(svc -> {
            final var entities = run();
            entities.stream().forEach(e -> {
                svc.create(e);
            });
            return svc;
        });

        mcs.exportTtl(System.out);
    }

    /**
     * Build an F35.
     *
     * @return A List of FunctionalObject.
     */
    private static List<Thing> run() {
        // Create a PossibleWorld.
        final var possibleWorldIri = iri();
        final var possibleWorld = SpatioTemporalExtentServices.createPossibleWorld(possibleWorldIri);

        // ------------------------------------------------------------------------------------------------
        // Create the OrdinaryFunctionalObjects that will be installed in an aircraft
        // ------------------------------------------------------------------------------------------------

        // Manufacture a VLS
        final var vlsIri = iri();
        final F135VerticalLiftSystem vls = f35.createEntity(Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME, vlsIri);
        vls.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // Manufacture a Turbine
        final var turbineIri = iri();
        final F135Turbine turbine = f35.createEntity(Constants.F135_TURBINE_TYPE_NAME, turbineIri);
        turbine.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // Manufacture an Engine
        final var engineIri = iri();
        final F135Engine engine = f35.createEntity(Constants.F135_ENGINE_TYPE_NAME, engineIri);
        engine.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // Manufacture an Ejection Seat
        final var ejectionSeatIri = iri();
        final US16EEjectionSeat ejectionSeat = f35.createEntity(Constants.US16E_EJECTION_SEAT_TYPE_NAME, ejectionSeatIri);
        ejectionSeat.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // Manufacture an Airframe
        final var airframeIri = iri();
        final F35Airframe airframe = f35.createEntity(Constants.F35_AIRFRAME_TYPE_NAME, airframeIri);
        airframe.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // ------------------------------------------------------------------------------------------------
        // Create the engine Functional System.
        // ------------------------------------------------------------------------------------------------

        // Manufacture an Engine System
        final var engineSystem1Iri = iri();
        final var engineComponentIri = iri();
        final var turbineComponentIri = iri();
        final var vlsComponentIri = iri();

        final F135EngineComponent engineComponent = f35.createEntity(Constants.F135_ENGINE_COMPONENT_TYPE_NAME, engineComponentIri);
        final F135TurbineComponent turbineComponent = f35.createEntity(Constants.F135_TURBINE_COMPONENT_TYPE_NAME, turbineComponentIri);
        final F135VerticalLiftSystemComponent vlsComponent = f35.createEntity(Constants.F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME, vlsComponentIri);

        final F135EngineSystem engineSystem1 = f35.createEntity(Constants.F135_ENGINE_SYSTEM_TYPE_NAME, engineSystem1Iri);
        engineSystem1.setEngine(engineComponent);
        engineSystem1.setTurbine(turbineComponent);
        engineSystem1.setVerticalLiftSystem(vlsComponent);

        engineSystem1.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        engineComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        turbineComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        vlsComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        // ------------------------------------------------------------------------------------------------
        // Create the Aircraft Functional System
        // ------------------------------------------------------------------------------------------------

        // Manufacture an Aircraft
        final var f35AircraftSystemIri = iri();
        final var f35AirframeComponentIri = iri();
        final var f35EngineComponentIri = iri();
        final var f35EjectionSeatComponentIri = iri();
        final var f35VerticalLiftSystemComponentIri = iri();
        final var f35TurbineComponentIri = iri();

        final F35Aircraft aircraft2101 = f35.createEntity(Constants.F35_AIRCRAFT_TYPE_NAME, f35AircraftSystemIri);
        final F35AirframeComponent f35AirframeComponent = f35.createEntity(Constants.F35_AIRFRAME_COMPONENT_TYPE_NAME, f35AirframeComponentIri);
        final F35EngineComponent f35EngineComponent = f35.createEntity(Constants.F35_ENGINE_COMPONENT_TYPE_NAME, f35EngineComponentIri);
        final F35EjectionSeatComponent f35EjectionSeatComponent = f35.createEntity(Constants.F35_EJECTION_SEAT_COMPONENT_TYPE_NAME,
            f35EjectionSeatComponentIri);
        final F35VerticalLiftSystemComponent f35VerticalLiftSystemComponent = f35.createEntity(Constants.F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME,
            f35VerticalLiftSystemComponentIri);
        final F35TurbineComponent f35TurbineComponent = f35.createEntity(Constants.F35_TURBINE_COMPONENT_TYPE_NAME, f35TurbineComponentIri);

        aircraft2101.setEngine(f35EngineComponent);
        aircraft2101.setTurbine(f35TurbineComponent);
        aircraft2101.setAirframe(f35AirframeComponent);
        aircraft2101.setEjectionSeat(f35EjectionSeatComponent);
        aircraft2101.setVerticalLiftSystem(f35VerticalLiftSystemComponent);

        aircraft2101.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        f35AirframeComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        f35EngineComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        f35EjectionSeatComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        f35VerticalLiftSystemComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        f35TurbineComponent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);

        // ------------------------------------------------------------------------------------------------
        // Install the parts of the engine system.
        // ------------------------------------------------------------------------------------------------

        // Install the engine in the engine system
        final var engineInstalledEngineSystemIri = iri();
        final InstalledF135EngineInF135EngineSystem engineInstalledInEngineSystem = 
            f35.createEntity(Constants.INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME, engineInstalledEngineSystemIri);

        final var engineInstalledEngineSystemBeginningIri = iri();
        final Event engineInstalledEngineSystemBeginningEvent = SpatioTemporalExtentServices.createEvent(engineInstalledEngineSystemBeginningIri);
        engineInstalledEngineSystemBeginningEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        engineInstalledInEngineSystem.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        engineInstalledInEngineSystem.addValue(HQDM.BEGINNING, engineInstalledEngineSystemBeginningIri);
        engineInstalledInEngineSystem.addValue(HQDM.TEMPORAL_PART_OF, engineIri);
        engineInstalledInEngineSystem.addValue(HQDM.TEMPORAL_PART_OF, engineComponentIri);

        // Install the turbine in the engine system.
        final var turbineInstalledInEngineSystemIri = iri();
        final InstalledF135TurbineInF135EngineSystem turbineInF135EngineSystem = 
            f35.createEntity(Constants.INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME, turbineInstalledInEngineSystemIri);

        final var turbineInstalledInEngineSystemBeginningIri = iri();
        final Event turbineInstalledInEngineSystemEvent = SpatioTemporalExtentServices.createEvent(turbineInstalledInEngineSystemBeginningIri);
        turbineInstalledInEngineSystemEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        turbineInF135EngineSystem.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        turbineInF135EngineSystem.addValue(HQDM.BEGINNING, turbineInstalledInEngineSystemBeginningIri);
        turbineInF135EngineSystem.addValue(HQDM.TEMPORAL_PART_OF, turbineIri);
        turbineInF135EngineSystem.addValue(HQDM.TEMPORAL_PART_OF, turbineComponentIri);

        // Install the vls in the engine system.
        final var vlsInstalledInEngineSystemIri = iri();
        final InstalledF135VLSInF135EngineSystem vlsInstalledInEngineSystem =
            f35.createEntity(Constants.INSTALLED_F135_VLS_IN_F135_ENGINE_SYSTEM_TYPE_NAME, vlsInstalledInEngineSystemIri);

        final var vlsInstalledInEngineSystemBeginningIri = iri();
        final Event vlsInstalledInEngineSystemEvent = SpatioTemporalExtentServices.createEvent(vlsInstalledInEngineSystemBeginningIri);
        vlsInstalledInEngineSystemEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        vlsInstalledInEngineSystem.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        vlsInstalledInEngineSystem.addValue(HQDM.BEGINNING, vlsInstalledInEngineSystemBeginningIri);
        vlsInstalledInEngineSystem.addValue(HQDM.TEMPORAL_PART_OF, vlsIri);
        vlsInstalledInEngineSystem.addValue(HQDM.TEMPORAL_PART_OF, vlsComponentIri);

        // ------------------------------------------------------------------------------------------------
        // Install the parts of the Aircraft System.
        // ------------------------------------------------------------------------------------------------
        
        // Install the airframe in the aircraft
        final var airframeInstalledInF35AircraftIri = iri();
        final InstalledF35AirframeInF35Aircraft airframeInstalledInF35Aircraft = 
            f35.createEntity(Constants.INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_NAME, airframeInstalledInF35AircraftIri);

        final var airframeInstalledInF35AircraftBeginningIri = iri();
        final var airframeInstalledInF35AircraftBeginningEvent = SpatioTemporalExtentServices.createEvent(airframeInstalledInF35AircraftBeginningIri);
        airframeInstalledInF35AircraftBeginningEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        airframeInstalledInF35Aircraft.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        airframeInstalledInF35Aircraft.addValue(HQDM.BEGINNING, airframeInstalledInF35AircraftBeginningIri);
        airframeInstalledInF35Aircraft.addValue(HQDM.TEMPORAL_PART_OF, airframeIri);
        airframeInstalledInF35Aircraft.addValue(HQDM.TEMPORAL_PART_OF, f35AirframeComponentIri);

        // Install the engine in the aircraft.
        final var engineInAircraftIri = iri();
        final InstalledF135EngineInF35Aircraft engineInAircraft = f35.createEntity(Constants.INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_NAME, engineInAircraftIri);
        
        final var engineInAircraftBeginningIri = iri();
        final var engineInAircraftBeginningEvent = SpatioTemporalExtentServices.createEvent(engineInAircraftBeginningIri);
        engineInAircraftBeginningEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        engineInAircraft.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        engineInAircraft.addValue(HQDM.BEGINNING, engineInAircraftBeginningIri);
        engineInAircraft.addValue(HQDM.TEMPORAL_PART_OF, engineIri);
        engineInAircraft.addValue(HQDM.TEMPORAL_PART_OF, f35EngineComponentIri);

        // Install the ejection seat in the aircraft.
        final var ejectionSeatInAircraftIri = iri();
        final InstalledUS16EEjectionSeatInF35Aircraft ejectionSeatInAircraft = 
            f35.createEntity(Constants.INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_NAME, ejectionSeatInAircraftIri);

        final var ejectionSeatInAircraftBeginningIri = iri();
        final var ejectionSeatInAircraftBeginningEvent = SpatioTemporalExtentServices.createEvent(ejectionSeatInAircraftBeginningIri);
        ejectionSeatInAircraftBeginningEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        ejectionSeatInAircraft.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        ejectionSeatInAircraft.addValue(HQDM.BEGINNING, ejectionSeatInAircraftBeginningIri);
        ejectionSeatInAircraft.addValue(HQDM.TEMPORAL_PART_OF, ejectionSeatIri);
        ejectionSeatInAircraft.addValue(HQDM.TEMPORAL_PART_OF, f35EjectionSeatComponentIri);

        // Install the VLS in the aircraft.
        final var vlsInAircraftIri = iri();
        final InstalledF135VLSInF35Aircraft vlsInAircraft = f35.createEntity(Constants.INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_NAME, vlsInAircraftIri);

        final var vlsInAircraftBeginningIri = iri();
        final var vlsInAircraftBeginningEvent = SpatioTemporalExtentServices.createEvent(vlsInAircraftBeginningIri);
        vlsInAircraftBeginningEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        vlsInAircraft.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        vlsInAircraft.addValue(HQDM.BEGINNING, vlsInAircraftBeginningIri);
        vlsInAircraft.addValue(HQDM.TEMPORAL_PART_OF, vlsIri);
        vlsInAircraft.addValue(HQDM.TEMPORAL_PART_OF, f35VerticalLiftSystemComponentIri);

        // Install the turbine in the aircraft.
        final var turbineInAircraftIri = iri();
        final InstalledF135TurbineInF35Aircraft turbineInAircraft = f35.createEntity(Constants.INSTALLED_F135_TURBINE_IN_F35_AIRCRAFT_TYPE_NAME, turbineInAircraftIri);

        final var turbineInAircraftBeginningIri = iri();
        final var turbineInAircraftBeginningEvent = SpatioTemporalExtentServices.createEvent(turbineInAircraftBeginningIri);
        turbineInAircraftBeginningEvent.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        turbineInAircraft.addValue(HQDM.PART_OF_POSSIBLE_WORLD, possibleWorldIri);
        turbineInAircraft.addValue(HQDM.BEGINNING, turbineInAircraftBeginningIri);
        turbineInAircraft.addValue(HQDM.TEMPORAL_PART_OF, turbineIri);
        turbineInAircraft.addValue(HQDM.TEMPORAL_PART_OF, f35TurbineComponentIri);
        // ------------------------------------------------------------------------------------------------
        // return all the entities.
        // ------------------------------------------------------------------------------------------------
        
        return List.of(
                possibleWorld,
                vls,
                turbine,
                engine,
                ejectionSeat,
                airframe,
                engineComponent,
                turbineComponent,
                vlsComponent,
                engineSystem1,
                aircraft2101,
                f35AirframeComponent,
                f35EngineComponent,
                f35EjectionSeatComponent,
                f35VerticalLiftSystemComponent,
                f35TurbineComponent,
                engineInstalledInEngineSystem,
                engineInstalledEngineSystemBeginningEvent,
                turbineInF135EngineSystem,
                turbineInstalledInEngineSystemEvent,
                vlsInstalledInEngineSystem,
                vlsInstalledInEngineSystemEvent,
                airframeInstalledInF35Aircraft,
                airframeInstalledInF35AircraftBeginningEvent,
                engineInAircraft,
                engineInAircraftBeginningEvent,
                ejectionSeatInAircraft,
                ejectionSeatInAircraftBeginningEvent,
                vlsInAircraft,
                vlsInAircraftBeginningEvent,
                turbineInAircraft,
                turbineInAircraftBeginningEvent
                );
    }

    private static long id = 0;

    private static IRI iri() {
        return new IRI(TEST_BASE, Long.toString(id++));
    }
}
