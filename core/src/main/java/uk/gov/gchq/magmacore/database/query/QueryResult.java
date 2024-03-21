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

package uk.gov.gchq.magmacore.database.query;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single result returned from a SPARQL query.
 */
public class QueryResult {

    private final Map<String, RdfNode> map = new HashMap<>();

    /**
     * Get either the subject (s), object (o), or predicate (p) of the result triple.
     *
     * @param varName Name of variable within query result to get.
     * @return Corresponding RdfNode.
     */
    public final RdfNode get(final String varName) {
        return map.get(varName);
    }

    /**
     * Set either the subject (s), object (o), or predicate (p) of the result triple.
     *
     * @param varName Name of variable within the query result to set.
     * @param node    Rdf node to set.
     */
    public final void set(final String varName, final RdfNode node) {
        map.put(varName, node);
    }

    /**
     * Method to support JSON Serialisation.
     *
     * @return Map of variable names and IRIs in the QueryResultsList.
     */
    @JsonProperty("binding")
    public final Map<String, String> getMap() {
        final HashMap<String, String> results = new HashMap<>();
        map.keySet().forEach(key -> results.put(key, map.get(key).toString()));
        return results;
    }

    @Override
    public String toString() {
        return "QueryResult [map=" + map + "]";
    }
}
