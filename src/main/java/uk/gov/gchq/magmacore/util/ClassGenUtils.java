package uk.gov.gchq.magmacore.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import io.vavr.API;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.control.Option;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.jena.riot.Lang;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.RDFS;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.generators.GeneratorsConfig;

/**
 * General utility methods for class generation.
 */
@Log4j2
public class ClassGenUtils {

    /**
     * Write a class file to the target classes directory.
     */
    public static final Function1<CtClass, CtClass> writeClass = cc -> {
        try {
            cc.writeFile(TARGET_CLASSES_DIR);
            return cc;
        } catch (final Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    };

    /**
     * Convert a CtClass to a java Class.
     */
    public static final Function1<CtClass, Class<?>> toJavaImplClass = c -> API.unchecked(
                    () -> c.toClass(NEIGHBOUR_IMPL_CLASS))
            .apply();

    /**
     * Convert a CtClass to a java interface.
     */
    public static final Function1<CtClass, Class<?>> toJavaInterface = c -> API.unchecked(
                    () -> c.toClass(NEIGHBOUR_INTERFACE_CLASS))
            .apply();

    /**
     * The default class pool.
     */
    public static final ClassPool pool = ClassPool.getDefault();

    public static final Function1<String, CtClass> getCtClass = name -> API.unchecked(() -> pool.get(name))
            .apply();

    /**
     * Add a nested Builder class to a class.
     */
    public static final Function1<CtClass, CtClass> addNestedBuilder = c -> c.makeNestedClass(
            GeneratorsConfig.BUILDER_CLASS_SHORT_NAME, true);

    public static final Function1<MagmaCoreDatabase, MagmaCoreDatabase> drop = jenaDatabase -> jenaDatabase.begin()
            .drop()
            .commit();

    /**
     * Instantiate a MagmaCoreJenaDatabase and populate it with example objects.
     */
    public static Function0<Option<MagmaCoreJenaDatabase>> initJenaDatabase(
            @NonNull final Collection<Thing> inputObjects) {
        return () -> {
            final var jenaDatabase = new MagmaCoreJenaDatabase();
            jenaDatabase.register(HQDM.HQDM);
            jenaDatabase.register(RDFS.RDFS);

            // Add example data objects to dataset.
            jenaDatabase.begin();
            inputObjects.forEach(jenaDatabase::create);
            jenaDatabase.commit();

            return Option(jenaDatabase);
        };
    }

    /**
     * Set the superclass for a class.
     *
     * @param superImplClass a CtClass
     * @return a Function that adds a superclass to a CtClass
     */
    public static Function1<CtClass, CtClass> setSuperclass(@NonNull final Class<?> superImplClass) {
        return c -> {
            try {
                c.setSuperclass(pool.get(superImplClass.getName()));
            } catch (final Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
            return c;
        };
    }

    /**
     * Set the implemented interfaces for a CtClass.
     *
     * @param l a List of Strings that are fully qualified interface names.
     * @return a Function that adds implemented interfaces to a CtClass.
     */
    public static Function1<CtClass, CtClass> setInterfaces(@NonNull final Collection<String> l) {
        return c -> {
            c.setInterfaces(l.stream()
                    .map(ClassGenUtils.getCtClass)
                    .toArray(CtClass[]::new));
            return c;
        };
    }

    /**
     * Dump the details of an Object's class.
     *
     * @param o an Object.
     */
    public static void dumpObjectsClass(@NonNull final Thing o) {
        dumpClass(o.getClass());
        log.debug("The object's iri is: " + o.getId());
    }

    /**
     * Dump the details for a Class.
     *
     * @param c the Class
     */
    public static void dumpClass(@NonNull final Class<?> c) {
        // First inspect the object's class by reflection
        log.debug("test class name = " + c.getName());
        log.debug("Methods:");
        for (final var method : c.getMethods()) {
            log.debug("\t"
                    + method.getName() + " with parameter types: "
                    + Arrays.toString(method.getParameterTypes())
                    + " and return type: "
                    + method.getReturnType());
        }
        log.debug("test package name = " + c.getPackage()
                .getName());
        log.debug("test superclass name = " + c.getSuperclass()
                .getName());

        log.debug("Interfaces:");
        for (final var dcinterface : c.getInterfaces()) {
            log.debug("\t" + dcinterface.getName());
        }

        log.debug("Constructors:");
        for (final var dcconstructor : c.getConstructors()) {
            log.debug("\t" + dcconstructor.getName());
        }

        log.debug("Fields:");
        for (final var dcfield : c.getDeclaredFields()) {
            log.debug("\t" + dcfield.getName() + " of type: " + dcfield.getType());
        }

        // Search for nested classed link the Builder
        log.debug("Nested classes (e.g. builder):");
        for (final var dcclasses : c.getClasses()) {
            log.debug("\t" + dcclasses.getName());
        }
    }

    /**
     * Create a Function that adds a constructor.
     *
     * @param classNameShort a String.
     * @return a Function that adds a constructor to a CtClass.
     */
    public static Function1<CtClass, CtClass> addConstructor(@NonNull final String classNameShort) {
        return c -> {
            try {
                final var ccConstructor = new CtConstructor(new CtClass[]{pool.get(IRI_CLASS.getName())}, c);
                ccConstructor.setBody(
                        "{super($class,$1, new uk.gov.gchq.hqdm.iri.HqdmIri(new uk.gov.gchq.hqdm.iri.IriBase"
                                + "(\"hqdm\", " + "\"http://www.semanticweb.org/hqdm#\"),\"" + classNameShort
                                + "\"));}");
                c.addConstructor(ccConstructor);
                return c;
            } catch (final Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Create a Function to dump a MagmaCoreJenaDatabase to a file.
     *
     * @param filename a String
     * @return a Function
     */
    public static Function1<MagmaCoreJenaDatabase, MagmaCoreJenaDatabase> dumpDatabase(@NonNull final String filename) {
        return jenaDatabase -> {
            try (final var ps = new PrintStream(filename)) {
                jenaDatabase.dump(ps, Lang.TTL);
            } catch (final FileNotFoundException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
            return jenaDatabase;
        };
    }

    /**
     * Create a Function to add a method to a class.
     *
     * @param newMethod  a CtMethod
     * @param methodBody a String with the method body.
     * @return a Function.
     */
    public static Function1<CtClass, CtClass> addMethodWithBody(
            @NonNull final CtMethod newMethod,
            @NonNull final String methodBody) {

        return c -> {
            try {
                newMethod.setBody(methodBody);
                c.addMethod(newMethod);
                return c;
            } catch (final CannotCompileException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Create a Function to set a class's super interface.
     *
     * @param i the interface Class.
     * @return a Function
     */
    public static Function1<CtClass, CtClass> setSuperInterface(@NonNull final Class<?> i) {
        return c -> {
            try {
                c.addInterface(pool.get(i.getName()));
                return c;
            } catch (final Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        };
    }
}
