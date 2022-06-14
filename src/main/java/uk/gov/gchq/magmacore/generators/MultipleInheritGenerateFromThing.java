package uk.gov.gchq.magmacore.generators;

import static io.vavr.API.Option;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.CLASS_NAME_CONJUNCTION;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.IMPL_PACKAGE;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.IMPL_SUFFIX;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.MODEL_PACKAGE;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.SUPER_IMPL_CLASS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.vavr.Function1;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;

/**
 * Class generation functions.
 */
@UtilityClass
public class MultipleInheritGenerateFromThing {

    /**
     * Create a function to find a Thing by IRI in a jenaDatabase.
     *
     * @param iri the IRI.
     * @return a Function.
     */
    private static Function1<MagmaCoreDatabase, Thing> findByIri(@NonNull final IRI iri) {
        return magmaCoreDatabase -> {
            try {
                magmaCoreDatabase.begin();
                return magmaCoreDatabase.get(iri);
            } finally {
                magmaCoreDatabase.abort();
            }
        };
    }

    /**
     * Generate classes dynamically.
     *
     * @param newTypeSpecificationObject a Thing specifying what needs to be created.
     * @param magmaCoreDatabase          a MagmaCoreJenaDatabase.
     * @return a Class.
     */
    public static Class<?> generateNewTypeClasses(
            @NonNull final Thing newTypeSpecificationObject,
            @NonNull final MagmaCoreDatabase magmaCoreDatabase
    ) {
        final var nameComponents = new ArrayList<String>();
        final var classSuperTypeInterfaceNames = new ArrayList<String>();
        final var classSuperTypeImplNames = new ArrayList<String>();
        final var typeExtractor = createTypeExtractor(magmaCoreDatabase, nameComponents, classSuperTypeInterfaceNames,
                classSuperTypeImplNames);

        // Get the type names from the MC database
        final var iris = newTypeSpecificationObject.getPredicates()
                .get(HQDM.HAS_SUPERTYPE)
                .stream()
                .map(iri -> typeExtractor.apply((IRI) iri))
                .collect(Collectors.toList());

        // There is a hidden dependency (due to side effects) which means that the typeExtractor needs to be called
        // before the following code so that the `nameComponents` list is populated. The check on the `iris` list
        // enforces that ordering.
        if (!iris.isEmpty()) {
            // Compose the new Interface name
            final var rootName = nameComponents
                    .stream()
                    .filter(Objects::nonNull)
                    .sorted()
                    .collect(Collectors.joining(CLASS_NAME_CONJUNCTION));

            final var classClazzName = IMPL_PACKAGE + rootName + IMPL_SUFFIX;

            // Test to see if Classfile exists already, return if so
            try {
                return Class.forName(classClazzName);
            } catch (final ClassNotFoundException ex) {
                return createClass(classSuperTypeInterfaceNames, classSuperTypeImplNames, rootName, classClazzName);
            }
        } else {
            throw new RuntimeException("No supertypes found.");
        }
    }

    public static Class<?> createClass(@NonNull final Collection<String> classSuperTypeInterfaceNames,
            @NonNull final Collection<String> classSuperTypeImplNames, @NonNull final String rootName,
            @NonNull final String classClazzName) {
        // Class does not exist so create it.
        final var intfzz = GenerateHqdmInterface.generateInterface(MODEL_PACKAGE + rootName,
                classSuperTypeInterfaceNames);

        // Generate class with method
        return GenerateHqdmClass.generateClass(
                rootName + IMPL_SUFFIX,
                classClazzName,
                SUPER_IMPL_CLASS,
                intfzz,
                classSuperTypeImplNames);
    }

    private static Function1<IRI, IRI> createTypeExtractor(@NonNull final MagmaCoreDatabase magmaCoreDatabase,
            @NonNull final Collection<String> nameComponents,
            @NonNull final Collection<String> classSuperTypeInterfaceNames,
            @NonNull final Collection<String> classSuperTypeImplNames) {
        return iri -> {
            final var stNameString = Option(magmaCoreDatabase)
                    .map(findByIri(iri))
                    .map(MultipleInheritGenerateFromThing::getEntityClassNamePredicates)
                    .getOrElseThrow(RuntimeException::new);

            final var parentInterfaceShort = StringUtils.substringBefore(
                    StringUtils.substringAfterLast(stNameString, "."), IMPL_SUFFIX);

            nameComponents.add(parentInterfaceShort);
            classSuperTypeInterfaceNames.add(MODEL_PACKAGE + parentInterfaceShort);
            classSuperTypeImplNames.add(stNameString);
            return iri;
        };
    }

    private static String getEntityClassNamePredicates(@NonNull final Thing t) {
        return (String) t.getPredicates()
                .get(HQDM.ENTITY_CLASS_NAME)
                .stream()
                .findFirst()
                .orElseThrow();
    }
}
