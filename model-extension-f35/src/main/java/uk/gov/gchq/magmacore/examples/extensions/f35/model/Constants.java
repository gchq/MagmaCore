package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HQDM;

import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * Define some useful Constants.
 */
public interface Constants {

    public static final String F135_ENGINE_COMPONENT_TYPE_NAME = "F135EngineComponent";
    public static final String F135_ENGINE_SYSTEM_TYPE_NAME = "F135EngineSystem";
    public static final String F135_ENGINE_TYPE_NAME = "F135Engine";
    public static final String F135_TURBINE_COMPONENT_TYPE_NAME = "F135TurbineComponent";
    public static final String F135_TURBINE_TYPE_NAME = "F135Turbine";
    public static final String F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME = "F135VerticalLiftSystemComponent";
    public static final String F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME = "F135VerticalLiftSystem";
    public static final String F35_AIRCRAFT_TYPE_NAME = "F35Aircraft";
    public static final String F35_AIRFRAME_COMPONENT_TYPE_NAME = "F35AirframeComponent";
    public static final String F35_AIRFRAME_TYPE_NAME = "F35Airframe";
    public static final String F35_EJECTION_SEAT_COMPONENT_TYPE_NAME = "F35EjectionSeatComponent";
    public static final String F35_ENGINE_COMPONENT_TYPE_NAME = "F35EngineComponent";
    public static final String F35_TURBINE_COMPONENT_TYPE_NAME = "F35TurbineComponent";
    public static final String F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME = "F35VerticalLiftSystemComponent";
    public static final String INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME = "InstalledF135EngineInF135EngineSystem";
    public static final String INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_NAME = "InstalledF135EngineInF35Aircraft";
    public static final String INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME = "InstalledF135TurbineInF135EngineSystem";
    public static final String INSTALLED_F135_TURBINE_IN_F35_AIRCRAFT_TYPE_NAME = "InstalledF135TurbineInF35Aircraft";
    public static final String INSTALLED_F135_VLS_IN_F135_ENGINE_SYSTEM_TYPE_NAME = "InstalledF135VLSInF135EngineSystem";
    public static final String INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_NAME = "InstalledF135VLSInF35Aircraft";
    public static final String INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_NAME = "InstalledF35AirframeInF35Aircraft";
    public static final String INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_NAME = "InstalledUS16EEjectionSeatInF35Aircraft";
    public static final String STATE_OF_F135_ENGINE_COMPONENT_TYPE_NAME = "StateOfF135EngineComponent";
    public static final String STATE_OF_F135_ENGINE_TYPE_NAME = "StateOfF135Engine";
    public static final String STATE_OF_F135_TURBINE_COMPONENT_TYPE_NAME = "StateOfF135TurbineComponent";
    public static final String STATE_OF_F135_TURBINE_TYPE_NAME = "StateOfF135Turbine";
    public static final String STATE_OF_F135_VLS_COMPONENT_TYPE_NAME = "StateOfF135VLSComponent";
    public static final String STATE_OF_F135_VLS_TYPE_NAME = "StateOfF135VLS";
    public static final String STATE_OF_F35_AIRFRAME_COMPONENT_TYPE_NAME = "StateOfF35AirframeComponent";
    public static final String STATE_OF_F35_AIRFRAME_TYPE_NAME = "StateOfF35Airframe";
    public static final String STATE_OF_F35_EJECTION_SEAT_COMPONENT_TYPE_NAME = "StateOfF35EjectionSeatComponent";
    public static final String STATE_OF_F35_ENGINE_COMPONENT_TYPE_NAME = "StateOfF35EngineComponent";
    public static final String STATE_OF_F35_TURBINE_COMPONENT_TYPE_NAME = "StateOfF35TurbineComponent";
    public static final String STATE_OF_F35_VLS_COMPONENT_TYPE_NAME = "StateOfF35VLSComponent";
    public static final String STATE_OF_US16E_EJECTION_SEAT_TYPE_NAME = "StateOfUS16EEjectionSeat";
    public static final String US16E_EJECTION_SEAT_TYPE_NAME = "US16EEjectionSeat";

    public static final IRI F135_ENGINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F135_ENGINE_COMPONENT_TYPE_NAME);
    public static final IRI F135_ENGINE_SYSTEM_TYPE_IRI = new HqdmIri(HQDM, F135_ENGINE_SYSTEM_TYPE_NAME);
    public static final IRI F135_ENGINE_TYPE_IRI = new HqdmIri(HQDM, F135_ENGINE_TYPE_NAME);
    public static final IRI F135_TURBINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F135_TURBINE_COMPONENT_TYPE_NAME);
    public static final IRI F135_TURBINE_TYPE_IRI = new HqdmIri(HQDM, F135_TURBINE_TYPE_NAME);
    public static final IRI F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME);
    public static final IRI F135_VERTICAL_LIFT_SYSTEM_TYPE_IRI = new HqdmIri(HQDM, F135_VERTICAL_LIFT_SYSTEM_TYPE_NAME);
    public static final IRI F35_AIRCRAFT_TYPE_IRI = new HqdmIri(HQDM, F35_AIRCRAFT_TYPE_NAME);
    public static final IRI F35_AIRFRAME_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F35_AIRFRAME_COMPONENT_TYPE_NAME);
    public static final IRI F35_AIRFRAME_TYPE_IRI = new HqdmIri(HQDM, F35_AIRFRAME_TYPE_NAME);
    public static final IRI F35_EJECTION_SEAT_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F35_EJECTION_SEAT_COMPONENT_TYPE_NAME);
    public static final IRI F35_ENGINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F35_ENGINE_COMPONENT_TYPE_NAME);
    public static final IRI F35_TURBINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F35_TURBINE_COMPONENT_TYPE_NAME);
    public static final IRI F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_NAME);
    public static final IRI INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_IRI = new HqdmIri(HQDM, INSTALLED_F135_ENGINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME);
    public static final IRI INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_IRI = new HqdmIri(HQDM, INSTALLED_F135_ENGINE_IN_F35_AIRCRAFT_NAME);
    public static final IRI INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_IRI = new HqdmIri(HQDM, INSTALLED_F135_TURBINE_IN_F135_ENGINE_SYSTEM_TYPE_NAME);
    public static final IRI INSTALLED_F135_VLS_IN_F135_ENGINE_SYSTEM_TYPE_IRI = new HqdmIri(HQDM, INSTALLED_F135_VLS_IN_F135_ENGINE_SYSTEM_TYPE_NAME);
    public static final IRI INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_IRI = new HqdmIri(HQDM, INSTALLED_F135_VLS_IN_F35_AIRCRAFT_TYPE_NAME);
    public static final IRI INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_IRI = new HqdmIri(HQDM, INSTALLED_F35_AIRFRAME_IN_F35_AIRCRAFT_NAME);
    public static final IRI INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_IRI = new HqdmIri(HQDM, INSTALLED_US16E_EJECTION_SEAT_IN_F35_AIRCRAFT_NAME);
    public static final IRI STATE_OF_F135_ENGINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F135_ENGINE_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_F135_ENGINE_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F135_ENGINE_TYPE_NAME);
    public static final IRI STATE_OF_F135_TURBINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F135_TURBINE_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_F135_TURBINE_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F135_TURBINE_TYPE_NAME);
    public static final IRI STATE_OF_F135_VLS_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F135_VLS_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_F135_VLS_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F135_VLS_TYPE_NAME);
    public static final IRI STATE_OF_F35_AIRFRAME_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F35_AIRFRAME_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_F35_AIRFRAME_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F35_AIRFRAME_TYPE_NAME);
    public static final IRI STATE_OF_F35_EJECTION_SEAT_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F35_EJECTION_SEAT_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_F35_ENGINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F35_ENGINE_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_F35_VLS_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F35_VLS_COMPONENT_TYPE_NAME);
    public static final IRI STATE_OF_US16E_EJECTION_SEAT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_US16E_EJECTION_SEAT_TYPE_NAME);
    public static final IRI US16E_EJECTION_SEAT_TYPE_IRI = new HqdmIri(HQDM, US16E_EJECTION_SEAT_TYPE_NAME);
    public static final IRI INSTALLED_F135_TURBINE_IN_F35_AIRCRAFT_TYPE_IRI = new HqdmIri(HQDM, INSTALLED_F135_TURBINE_IN_F35_AIRCRAFT_TYPE_NAME);
    public static final IRI STATE_OF_F35_TURBINE_COMPONENT_TYPE_IRI = new HqdmIri(HQDM, STATE_OF_F35_TURBINE_COMPONENT_TYPE_NAME);

}
