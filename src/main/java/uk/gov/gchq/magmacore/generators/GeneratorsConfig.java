package uk.gov.gchq.magmacore.generators;

import lombok.experimental.UtilityClass;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Thing;

/**
 * Configuration values.
 */
@UtilityClass
public class GeneratorsConfig {
    public static final Class<?> NEIGHBOUR_INTERFACE_CLASS = Thing.class;
    public static final Class<?> IRI_CLASS = IRI.class;
    public static final String BUILDER_CLASS_SHORT_NAME = "Builder";
    public static final String TARGET_CLASSES_DIR = "./target/classes/";
    public static final String BUILDER_METHOD_NAME = "build";
    public static final String MODEL_PACKAGE = "uk.gov.gchq.hqdm.model.";
    public static final String IMPL_SUFFIX = "Impl";
    public static final String CLASS_NAME_CONJUNCTION = "And";
    public static final String IMPL_PACKAGE = "uk.gov.gchq.hqdm.model.impl.";
    public static final String IMPL_PATH = "uk/gov/gchq/hqdm/model/impl/";
    public static final String MODEL_PATH = "uk/gov/gchq/hqdm/model/";
}
