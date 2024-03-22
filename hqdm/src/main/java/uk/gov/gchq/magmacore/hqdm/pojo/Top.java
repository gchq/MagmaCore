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

package uk.gov.gchq.magmacore.hqdm.pojo;

import java.util.Map;
import java.util.Set;

import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * Top-level interface for HQDM objects.
 */
public interface Top {

    /**
     * Get the ID of the HQDM object.
     *
     * @return ID of the HQDM object.
     */
    IRI getId();

    /**
     * Set the ID of the HQDM object.
     *
     * @param iri IRI of the HQDM object.
     */
    void setId(IRI iri);

    /**
     * Get the predications of the HQDM object.
     *
     * @return Map of HQDM objects and Object predicates of the entity.
     */
    Map<IRI, Set<Object>> getPredicates();

    /**
     * Set the predications of the HQDM object.
     *
     * @param predicates Predicates of the HQDM object.
     */
    void setPredicates(Map<IRI, Set<Object>> predicates);

    /**
     * Get predicate value(s) by predicate Object.
     *
     * @param predicateIri Predicate {@link IRI}
     * @return Set of predicate values (Object or string-literals).
     */
    <T> Set<T> values(IRI predicateIri);

    /**
     * Get predicate value(s) by predicate Object.
     *
     * @param predicateIri Predicate {@link IRI}
     * @return A single value of type T or null (Object or string-literals).
     */
    <T> T oneValue(IRI predicateIri);

    /**
     * Add predicate and object String reference to entity.
     *
     * @param predicateIri Predicate IRI.
     * @param objectIri    IRI of the object.
     */
    void addValue(IRI predicateIri, IRI objectIri);

    /**
     * Add predicate Object and string value to object.
     *
     * @param predicateIri Predicate IRI.
     * @param value       String value.
     */
    void addStringValue(IRI predicateIri, String value);

    /**
     * Add predicate Object and real number value to object.
     *
     * @param predicateIri Predicate IRI.
     * @param value       Real number value.
     */
    void addRealValue(IRI predicateIri, double value);

    /**
     * Remove a predicate value.
     *
     * @param predicateIri The IRI of the predicate.
     * @param value       The {@link Object} value to be removed.
     */
    void removeValue(IRI predicateIri, Object value);

    /**
     * Does the entity have a given predicate.
     *
     * @param predicateIri Predicate IRI.
     * @return {@code true} if has predicate value.
     */
    boolean hasValue(IRI predicateIri);

    /**
     * Does the entity have a given predicate and object value.
     *
     * @param predicateIri Predicate IRI.
     * @param objectId    ID of the object.
     * @return {@code true} if has this object value.
     */
    boolean hasThisValue(IRI predicateIri, Object objectId);

    /**
     * Does the entity have a given predicate and string value.
     *
     * @param predicateIri Predicate IRI.
     * @param value       String value.
     * @return {@code true} if has this string value.
     */
    boolean hasThisStringValue(IRI predicateIri, String value);

    /**
     * Does the entity have a given predicate and string value (case-insensitive).
     *
     * @param predicateIri Predicate IRI.
     * @param value       Case-insensitive string value.
     * @return {@code true} if has this string value.
     */
    boolean hasThisStringValueIgnoreCase(IRI predicateIri, String value);

    /**
     * Does the entity have a given predicate and string value.
     *
     * @param predicateIri Predicate IRI.
     * @param value       String value.
     * @return {@code true} if has fuzzy string value.
     */
    boolean hasThisStringValueFuzzy(IRI predicateIri, String value);
}
