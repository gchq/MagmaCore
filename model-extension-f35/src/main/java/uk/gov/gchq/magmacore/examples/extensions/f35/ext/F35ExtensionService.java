package uk.gov.gchq.magmacore.examples.extensions.f35.ext;

import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_ENGINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_ENGINE_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_ENGINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_TURBINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_TURBINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_AIRCRAFT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_AIRFRAME_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_AIRFRAME_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_EJECTION_SEAT_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_ENGINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_TURBINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F135_TURBINE_IN_F35_AIRCRAFT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F135_VLS_IN_F135_ENGINE_SYSTEM_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F135_ENGINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F135_ENGINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F135_TURBINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F135_TURBINE_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F135_VLS_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F135_VLS_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F35_EJECTION_SEAT_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F35_ENGINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F35_TURBINE_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_F35_VLS_COMPONENT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.STATE_OF_US16E_EJECTION_SEAT_TYPE_NAME;
import static uk.gov.gchq.magmacore.examples.extensions.f35.model.Constants.US16E_EJECTION_SEAT_TYPE_NAME;

import java.io.InputStream;

import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135EngineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135EngineImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135EngineSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135TurbineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135TurbineImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135VerticalLiftSystemComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F135VerticalLiftSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35AirframeComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35AirframeImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35EjectionSeatComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35EngineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35TurbineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.F35VerticalLiftSystemComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF135EngineInF135EngineSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF135EngineInF35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF135TurbineInF135EngineSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF135TurbineInF35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF135VLSInF135EngineSystemImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF135VLSInF35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledF35AirframeInF35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.InstalledUS16EEjectionSeatInF35AircraftImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF135EngineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF135EngineImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF135TurbineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF135TurbineImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF135VLSComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF135VLSImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF35EjectionSeatComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF35EngineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF35TurbineComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfF35VerticalLiftSystemComponentImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.StateOfUS16EEjectionSeatImpl;
import uk.gov.gchq.magmacore.examples.extensions.f35.impl.US16EEjectionSeatImpl;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;


/**
 * An example HQDM ExtensionService for adding some extension classes to
 * MagmaCore.
 */
public class F35ExtensionService implements ExtensionService {

    private static InputStream ttl = null;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Thing> T createEntity(final String typeName, final IRI iri) {

        switch (typeName) {
            case F135_ENGINE_COMPONENT_TYPE_NAME:
                return (T) new F135EngineComponentImpl(iri);

            case F135_ENGINE_SYSTEM_TYPE_NAME:
                return (T) new F135EngineSystemImpl(iri);

            case F135_ENGINE_TYPE_NAME:
                return (T) new F135EngineImpl(iri);

            case F135_TURBINE_COMPONENT_TYPE_NAME:
                return (T) new F135TurbineComponentImpl(iri);

            case F135_TURBINE_TYPE_NAME:
                return (T) new F135TurbineImpl(iri);

            case F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME:
                return (T) new F135VerticalLiftSystemComponentImpl(iri);

            case F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME:
                return (T) new F135VerticalLiftSystemImpl(iri);

            case F35_AIRFRAME_COMPONENT_TYPE_NAME:
                return (T) new F35AirframeComponentImpl(iri);

            case F35_AIRCRAFT_TYPE_NAME:
                return (T) new F35AircraftImpl(iri);

            case F35_AIRFRAME_TYPE_NAME:
                return (T) new F35AirframeImpl(iri);

            case F35_EJECTION_SEAT_COMPONENT_TYPE_NAME:
                return (T) new F35EjectionSeatComponentImpl(iri);

            case F35_ENGINE_COMPONENT_TYPE_NAME:
                return (T) new F35EngineComponentImpl(iri);

            case F35_TURBINE_COMPONENT_TYPE_NAME:
                return (T) new F35TurbineComponentImpl(iri);

            case F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME:
                return (T) new F35VerticalLiftSystemComponentImpl(iri);

            case INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME:
                return (T) new InstalledF135EngineInF135EngineSystemImpl(iri);

            case INSTALLED_F135_TURBINE_IN_F35_AIRCRAFT_TYPE_NAME:
                return (T) new InstalledF135TurbineInF35AircraftImpl(iri);

            case INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME:
                return (T) new InstalledF135TurbineInF135EngineSystemImpl(iri);

            case INSTALLED_F135_VLS_IN_F135_ENGINE_SYSTEM_TYPE_NAME:
                return (T) new InstalledF135VLSInF135EngineSystemImpl(iri);

            case INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_NAME:
                return (T) new InstalledF35AirframeInF35AircraftImpl(iri);

            case INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_NAME:
                return (T) new InstalledF135EngineInF35AircraftImpl(iri);

            case INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_NAME:
                return (T) new InstalledF135VLSInF35AircraftImpl(iri);

            case INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_NAME:
                return (T) new InstalledUS16EEjectionSeatInF35AircraftImpl(iri);

            case US16E_EJECTION_SEAT_TYPE_NAME:
                return (T) new US16EEjectionSeatImpl(iri);

            case STATE_OF_F135_ENGINE_COMPONENT_TYPE_NAME:
                return (T) new StateOfF135EngineComponentImpl(iri);

            case STATE_OF_F135_ENGINE_TYPE_NAME:
                return (T) new StateOfF135EngineImpl(iri);

            case STATE_OF_F135_TURBINE_COMPONENT_TYPE_NAME:
                return (T) new StateOfF135TurbineComponentImpl(iri);

            case STATE_OF_F135_TURBINE_TYPE_NAME:
                return (T) new StateOfF135TurbineImpl(iri);

            case STATE_OF_F135_VLS_COMPONENT_TYPE_NAME:
                return (T) new StateOfF135VLSComponentImpl(iri);

            case STATE_OF_F135_VLS_TYPE_NAME:
                return (T) new StateOfF135VLSImpl(iri);

            case STATE_OF_F35_EJECTION_SEAT_COMPONENT_TYPE_NAME:
                return (T) new StateOfF35EjectionSeatComponentImpl(iri);

            case STATE_OF_F35_ENGINE_COMPONENT_TYPE_NAME:
                return (T) new StateOfF35EngineComponentImpl(iri);

            case STATE_OF_F35_TURBINE_COMPONENT_TYPE_NAME:
                return (T) new StateOfF35TurbineComponentImpl(iri);

            case STATE_OF_F35_VLS_COMPONENT_TYPE_NAME:
                return (T) new StateOfF35VerticalLiftSystemComponentImpl(iri);

            case STATE_OF_US16E_EJECTION_SEAT_TYPE_NAME:
                return (T) new StateOfUS16EEjectionSeatImpl(iri);

            default:
                // Or null if it's not a class from this extension package.
                return null;
        }
    }

    @Override
    public InputStream getDomainTtl() {
        if (ttl == null) {
            ttl = F35ExtensionService.class.getResourceAsStream("/f35-model.ttl");
        }
        return ttl;
    }
}
