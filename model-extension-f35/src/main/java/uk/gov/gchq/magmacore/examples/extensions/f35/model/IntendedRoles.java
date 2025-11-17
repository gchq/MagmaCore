package uk.gov.gchq.magmacore.examples.extensions.f35.model;

import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * IntendedRoles for the domain FunctionalObjects.
 */
public class IntendedRoles {

    public static final Role F35AircraftIntendedRole = ClassServices.createRole(new HqdmIri(HQDM.HQDM, "cc342252-08f0-4f81-a71b-723794f9a7af"));

    public static final Role F35AirframeIntendedRole = ClassServices.createRole(new HqdmIri(HQDM.HQDM, "3c85a699-71a5-4158-8404-0a39dff7df2c"));

    public static final Role F135EngineIntendedRole = ClassServices.createRole(new HqdmIri(HQDM.HQDM, "84cb77e9-409e-4f54-a7df-f50561f3c930"));

    public static final Role F135TurbineIntendedRole = ClassServices.createRole(new HqdmIri(HQDM.HQDM, "8a1fcd10-6db4-48c1-86b9-3e9c63f70b41"));

    public static final Role F135VerticalLiftSystemIntendedRole = ClassServices.createRole(new HqdmIri(HQDM.HQDM, "7be97087-1f41-4108-8294-23d699d753e9"));

    public static final Role US16EEjectionSeatIntendedRole = ClassServices.createRole(new HqdmIri(HQDM.HQDM, "79760082-e77a-43d1-bf13-0ef212f2f2f7"));

    static {
        F35AircraftIntendedRole.addStringValue(HQDM.SKOS_DEFINITION, "A Lockheed Martin F-35 Lightning II aircraft");
        F35AirframeIntendedRole.addStringValue(HQDM.SKOS_DEFINITION, "An airframe for a Lockheed Martin F-35 Lightning II aircraft");
        F135EngineIntendedRole.addStringValue(HQDM.SKOS_DEFINITION, "An afterburning turbofan for the Lockheed Martin F-35 Lightning II aircraft.");
        F135TurbineIntendedRole.addStringValue(HQDM.SKOS_DEFINITION, "A turbine for an F135 turbofan engine for the Lockheed Martin F-35 Lightning II aircraft");
        F135VerticalLiftSystemIntendedRole.addStringValue(HQDM.SKOS_DEFINITION, 
                "A Rolls-Royce LiftSystem for the F135 turbofan engine for the Lockheed Martin F-35 Lightning II aircraft");
        US16EEjectionSeatIntendedRole.addStringValue(HQDM.SKOS_DEFINITION, "A US16E Ejection Seat for a Lockheed Martin F-35 Lightning II aircraft");
    }
}

