package uk.gov.gchq.magmacore.util;

import java.util.function.BiFunction;

/**
 * Utility functions to combing BiFunctions for use with an environment parameter.
 * A pattern for composing BiFinctions.
 *
 * @author Tony Walmsley, AOSD Ltd.
 */
public interface ComposableBiFunction<E, T, R> extends BiFunction<E, T, R> {

    /**
     * Combines two ComposableBiFunction by treating the first parameter to both functions as an `environment` that
     * needs to be available to both ComposableBiFunction. The return type of the first BiFunction must match the
     * second parameter type of the second ComposableBiFunction.
     *
     * @param first a ComposableBiFunction - executed before this
     * @return a combination of the two ComposableBiFunction that implements `this(env, first(env, p1))`
     */
    default <U> ComposableBiFunction<E, U, R> compose(final ComposableBiFunction<E, U, T> first) {
        return (e, t) -> this.apply(e, first.apply(e, t));
    }

    /**
     * Combines two ComposableBiFunction by treating the first parameter to both functions as an `environment` that
     * needs to be available to both ComposableBiFunction. The return type of the first ComposableBiFunction must
     * match the second parameter type of the second ComposableBiFunction.
     *
     * @param second a ComposableBiFunction - executed after this
     * @return a combination of the two ComposableBiFunction that implements `second(env, this(env, p1))`
     */
    default <U> ComposableBiFunction<E, T, U> andThen(final ComposableBiFunction<E, R, U> second) {
        return (e, t) -> second.apply(e, this.apply(e, t));
    }

}
