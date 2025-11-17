package uk.gov.gchq.magmacore.examples.extensions.f35.ext;

import java.util.Map;

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
import uk.gov.gchq.magmacore.examples.extensions.f35.model.US16EEjectionSeat;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionService;
import uk.gov.gchq.magmacore.hqdm.extensions.ExtensionServiceProvider;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * An example ExtensionServiceProvider for adding some extension classes to
 * MagmaCore.
 */
public class F35ExtensionServiceProvider implements ExtensionServiceProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public ExtensionService createService(final Map<IRI, Class<? extends Thing>> map) {

        // Register our extension IRIs and the corresponding interfaces.

        map.put(Constants.F135_ENGINE_COMPONENT_TYPE_IRI, F135EngineComponent.class);
        map.put(Constants.F135_ENGINE_SYSTEM_TYPE_IRI, F135EngineSystem.class);
        map.put(Constants.F135_ENGINE_TYPE_IRI, F135Engine.class);
        map.put(Constants.F135_TURBINE_COMPONENT_TYPE_IRI, F135TurbineComponent.class);
        map.put(Constants.F135_TURBINE_TYPE_IRI, F135Turbine.class);
        map.put(Constants.F135_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_IRI, F135VerticalLiftSystemComponent.class);
        map.put(Constants.F135_VERTICAL_LIFT_SYSTEM_TYPE_IRI, F135VerticalLiftSystem.class);
        map.put(Constants.F35_AIRCRAFT_TYPE_IRI, F35Aircraft.class);
        map.put(Constants.F35_AIRFRAME_COMPONENT_TYPE_IRI, F35AirframeComponent.class);
        map.put(Constants.F35_AIRFRAME_TYPE_IRI, F35Airframe.class);
        map.put(Constants.F35_EJECTION_SEAT_COMPONENT_TYPE_IRI, F35EjectionSeatComponent.class);
        map.put(Constants.F35_ENGINE_COMPONENT_TYPE_IRI, F35EngineComponent.class);
        map.put(Constants.F35_TURBINE_COMPONENT_TYPE_IRI, F35TurbineComponent.class);
        map.put(Constants.F35_VERTICAL_LIFT_SYSTEM_COMPONENT_TYPE_IRI, F35VerticalLiftSystemComponent.class);
        map.put(Constants.US16E_EJECTION_SEAT_TYPE_IRI, US16EEjectionSeat.class);
        // Create ane return the extension service.
        return new F35ExtensionService();
    }
}
