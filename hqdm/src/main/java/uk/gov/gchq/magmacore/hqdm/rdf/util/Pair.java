/*
 * Copyright 2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package uk.gov.gchq.magmacore.hqdm.rdf.util;

/**
 * A utility Pair with Left (L) and Right (R) values.
 *
 * @param <L> The type of the Left element.
 * @param <R> The type of the Right element.
 */
public class Pair<L, R> {
    /** The left element. */
    private L left;

    /** The right element. */
    private R right;

    /**
     * All args constructor.
     *
     * @param left  The Left element of type L.
     * @param right The Right element of type R.
     */
    public Pair(final L left, final R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Getter for the Left element.
     *
     * @return The left element of type L.
     */
    public L getLeft() {
        return left;
    }

    /**
     * Getter for the right element.
     *
     * @return The right element of type R.
     */
    public R getRight() {
        return right;
    }

    /**
     * Convert a Pair to a {@link String}.
     *
     * @return {@link String}.
     */
    public String toString() {
        return "{ left=" + left + ", right=" + right + "}";
    }
}
