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

/**
 * Top-level interface for HQDM objects.
 */
public interface Top {

    /**
     * Get the ID of the HQDM object.
     *
     * @return ID of the HQDM object.
     */
    String getId();

    /**
     * Set the ID of the HQDM object.
     *
     * @param id ID of the HQDM object.
     */
    void setId(String id);

    /**
     * Get the predications of the HQDM object.
     *
     * @return Map of HQDM objects and Object predicates of the entity.
     */
    Map<Object, Set<Object>> getPredicates();

    /**
     * Set the predications of the HQDM object.
     *
     * @param predicates Predicates of the HQDM object.
     */
    void setPredicates(Map<Object, Set<Object>> predicates);

    /**
     * Get predicate value(s) by predicate Object.
     *
     * @param predicateId Predicate ID.
     * @return Set of predicate values (Object or string-literals).
     */
    Set<Object> value(Object predicateId);

    /**
     * Add predicate and object String reference to entity.
     *
     * @param predicateId Predicate ID.
     * @param objectId    ID of the object.
     */
    void addValue(Object predicateId, Object objectId);

    /**
     * Add predicate Object and string value to object.
     *
     * @param predicateId Predicate ID.
     * @param value       String value.
     */
    void addStringValue(Object predicateId, String value);

    /**
     * Add predicate Object and real number value to object.
     *
     * @param predicateId Predicate ID.
     * @param value       Real number value.
     */
    void addRealValue(Object predicateId, double value);

    /**
     * Remove a predicate value.
     *
     * @param predicateId The ID of the predicate.
     * @param value       The {@link Object} value to be removed.
     */
    void removeValue(Object predicateId, Object value);

    /**
     * Does the entity have a given predicate.
     *
     * @param predicateId Predicate ID.
     * @return {@code true} if has predicate value.
     */
    boolean hasValue(Object predicateId);

    /**
     * Does the entity have a given predicate and object value.
     *
     * @param predicateId Predicate ID.
     * @param objectId    ID of the object.
     * @return {@code true} if has this object value.
     */
    boolean hasThisValue(Object predicateId, Object objectId);

    /**
     * Does the entity have a given predicate and string value.
     *
     * @param predicateId Predicate ID.
     * @param value       String value.
     * @return {@code true} if has this string value.
     */
    boolean hasThisStringValue(Object predicateId, String value);

    /**
     * Does the entity have a given predicate and string value (case-insensitive).
     *
     * @param predicateId Predicate ID.
     * @param value       Case-insensitive string value.
     * @return {@code true} if has this string value.
     */
    boolean hasThisStringValueIgnoreCase(Object predicateId, String value);

    /**
     * Does the entity have a given predicate and string value.
     *
     * @param predicateId Predicate ID.
     * @param value       String value.
     * @return {@code true} if has fuzzy string value.
     */
    boolean hasThisStringValueFuzzy(Object predicateId, String value);
}
