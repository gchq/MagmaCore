package uk.gov.gchq.magmacore.generators;

import static uk.gov.gchq.magmacore.util.ClassGenUtils.pool;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.setInterfaces;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.toJavaInterface;

import java.util.Collection;
import java.util.function.Function;

import javassist.CtClass;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

/**
 * Functions for generating a new interface.
 */
@Log4j2
@UtilityClass
public class GenerateHqdmInterface {

    private static final Function<String, CtClass> makeInterface = pool::makeInterface;

    /**
     * Generate a new interface.
     *
     * @param interfaceName the interface name.
     * @param extendsNames  a List of Strings that are the super interface names.
     * @return a Class.
     */
    public static Class<?> generateInterface(
            @NonNull final String interfaceName,
            @NonNull final Collection<String> extendsNames
    ) {
        return toJavaInterface
                .compose(setInterfaces(extendsNames))
                .compose(makeInterface)
                .apply(interfaceName);
    }
}
