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
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

/**
 * A single result returned from a SPARQL query.
 */
public class QueryResult {

    private final Map<String, RDFNode> map = new HashMap<>();

    /**
     * Get either the subject (s), object (o), or predicate (p) of the result triple.
     *
     * @param varName Name of variable within query result to get.
     * @return Corresponding RDFNode.
     */
    public final RDFNode get(final String varName) {
        return map.get(varName);
    }

    /**
     * Set either the subject (s), object (o), or predicate (p) of the result triple.
     *
     * @param varName Name of variable within the query result to set.
     * @param node    RDF node to set.
     */
    public final void set(final String varName, final RDFNode node) {
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
        map.keySet().forEach(key -> results.put(key, getValue(map.get(key))));
        return results;
    }

    /**
     * Get the value of an RDF node. This could either be the URI of the node, string literal, or the
     * local name of the property within this namespace.
     *
     * @param node Node to get the value of.
     * @return Value of the RDF node.
     */
    private static String getValue(final RDFNode node) {
        if (node instanceof Literal) {
            return node.asLiteral().getString();
        }
        if (node instanceof Resource) {
            final Resource resource = node.asResource();
            final String uri = resource.getURI();
            if (uri != null) {
                return uri;
            }
            final String localName = resource.getLocalName();
            if (localName != null) {
                return localName;
            }
            return resource.toString();
        }
        return "";
    }
}
