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

package uk.gov.gchq.magmacore.query;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

/**
 *
 */
public class QueryResult {

    /** */
    private final Map<String, RDFNode> map = new HashMap<>();

    /**
     *
     * @param varName
     * @return
     */
    public final RDFNode get(final String varName) {
        return map.get(varName);
    }

    /**
     * Method to support JSON Serialisation.
     *
     * @return
     */
    @JsonProperty("binding")
    public final Map<String, String> getMap() {
        final HashMap<String, String> result = new HashMap<>();
        map.keySet().forEach(key -> result.put(key, getValue(map.get(key))));
        return result;
    }

    /**
     *
     * @param varName
     * @param node
     */
    public final void set(final String varName, final RDFNode node) {
        map.put(varName, node);
    }

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
