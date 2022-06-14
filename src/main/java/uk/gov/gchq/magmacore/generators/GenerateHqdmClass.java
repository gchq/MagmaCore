package uk.gov.gchq.magmacore.generators;

import static io.vavr.API.Option;
import static io.vavr.API.Tuple;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.BUILDER_CLASS_SHORT_NAME;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.BUILDER_METHOD_NAME;
import static uk.gov.gchq.magmacore.generators.GeneratorsConfig.IRI_CLASS;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.addConstructor;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.addMethodWithBody;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.addNestedBuilder;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.setSuperInterface;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.setSuperclass;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.toJavaImplClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.vavr.Tuple2;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * Functions for generating new classes.
 */
@Log4j2
@UtilityClass
public class GenerateHqdmClass {

    public static final CtClass[] NO_PARAMETERS = null;
    private static final ClassPool pool = ClassPool.getDefault();

    /**
     * Generate a new class.
     *
     * @param classNameShort           a String.
     * @param className                a String.
     * @param superImplClazz           a Class.
     * @param intface                  a Class.
     * @param superClassesWithBuilders a List of String
     * @return a Class
     */
    public static Class<?> generateClass(
            @NonNull final String classNameShort,
            @NonNull final String className,
            @NonNull final Class<?> superImplClazz,
            @NonNull final Class<?> intface,
            @NonNull final Collection<String> superClassesWithBuilders
    ) {
        final var dedupedMethodPairs = new HashMap<String, Tuple2<String, CtClass>>();

        final var cc = Option(className)
                .map(pool::makeClass)
                .map(setSuperclass(superImplClazz))
                .map(setSuperInterface(intface))
                .map(addConstructor(classNameShort))
                .getOrElseThrow(RuntimeException::new);

        final var ccNestedClass = addNestedBuilder.apply(cc);
        final var fullClass = toJavaImplClass.apply(cc);

        // Add forEach to extract Builder Method pairs and de-dupe (may need to id preferred Class in case of clash)

        superClassesWithBuilders.forEach(
                item -> addClassBuildMethodsFromSuperclass(dedupedMethodPairs, item + "$" + BUILDER_CLASS_SHORT_NAME));

        generateBuilder(ccNestedClass, className, dedupedMethodPairs.values(), cc);

        return fullClass;
    }

    @SneakyThrows
    private static void addClassBuildMethodsFromSuperclass(
            @NonNull final HashMap<String, Tuple2<String, CtClass>> methodPairs,
            @NonNull final String builderClassName) {
        final var superClazzBuilderMethods = pool.get(builderClassName)
                .getDeclaredMethods();

        Arrays.stream(superClazzBuilderMethods)
                .forEach(superMethod -> extractSingleParameterMethodFromSuperclass(methodPairs, superMethod));
    }

    @SneakyThrows
    private static void extractSingleParameterMethodFromSuperclass(
            @NonNull final Map<String, Tuple2<String, CtClass>> methodPairs, @NonNull final CtMethod superMethod) {
        final var superMethodParameterTypes = superMethod.getParameterTypes();
        if (superMethodParameterTypes.length > 0) {
            final var superMethodName = superMethod.getName();

            methodPairs.putIfAbsent(superMethodName, Tuple(superMethodName, superMethodParameterTypes[0]));

        }
    }

    @SneakyThrows
    private static void generateBuilder(
            @NonNull final CtClass ccNestedClass,
            @NonNull final String parentClassName,
            @NonNull final Collection<Tuple2<String, CtClass>> methodList,
            @NonNull final CtClass parentCc) {

        // Create a field like: private final OrganizationImpl organizationImpl;
        final var fieldName = StringUtils.uncapitalize(StringUtils.substringAfterLast(parentClassName, "."));

        final var ctOnlyField = new CtField(parentCc, fieldName, ccNestedClass);
        ctOnlyField.setModifiers(Modifier.PRIVATE);
        ccNestedClass.addField(ctOnlyField);

        // Add the Builder Object constructor call
        final var ccNestedConstructor =
                new CtConstructor(new CtClass[]{pool.get(IRI_CLASS.getName())}, ccNestedClass);
        ccNestedConstructor.setBody(fieldName + " = new " + parentClassName + "($1);");
        ccNestedClass.addConstructor(ccNestedConstructor);

        // Add the builder predicate methods
        methodList.forEach(methodPair -> {

            final var newMethod = methodPair.apply((s, c) -> new CtMethod(
                    ccNestedClass,
                    s,
                    new CtClass[]{c},
                    ccNestedClass));

            final var predicateName = StringUtils.removeEnd(methodPair._1()
                    .toUpperCase(), "_M");

            addMethodWithBody(newMethod,
                    "{" + fieldName + ".addValue(uk.gov.gchq.hqdm.iri.HQDM." + predicateName
                            + ", $1.getIri()); return $0;}")
                    .apply(ccNestedClass);
        });

        final var newBuildMethod = new CtMethod(parentCc, BUILDER_METHOD_NAME, NO_PARAMETERS, ccNestedClass);

        addMethodWithBody(newBuildMethod, "{return " + fieldName + ";}")
                .apply(ccNestedClass);

        toJavaImplClass.apply(ccNestedClass);
    }

}
