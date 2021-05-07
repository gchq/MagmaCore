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

package uk.gov.gchq.magmacore.util;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.HqdmIri;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.IriBase;

/**
 * 
 */
public final class SparqlUtils {

    private SparqlUtils() {}

    public static final String RDFS_LITERAL_IRI = "http://www.w3.org/2000/01/rdf-schema#Literal";
    public static final IriBase RDFS = new IriBase("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
    public static final IRI RDFS_LITERAL = new IRI(RDFS, "Literal");
    public static final HqdmIri HQDM_MEMBEROF = new HqdmIri(HQDM.HQDM_PREFIX, "memberOf");
    public static final IriBase HQDM_BASE = new IriBase("hqdm",
            "http://www.semanticweb.org/magma-core/ontologies/hqdm#");
    public static final HqdmIri HQDM_DATANAME = new HqdmIri(HQDM.HQDM_PREFIX, "data_EntityName");
    public static final HqdmIri HQDM_DATAID = new HqdmIri(HQDM.HQDM_PREFIX, "data_uniqueID");

    public static final String OWL_PREFIX = "http://www.w3.org/2002/07/owl#";
    public static final String OWL_CLASS = OWL_PREFIX + "onClass";
    public static final String OWL_ONPROPERTY = OWL_PREFIX + "onProperty";
    public static final String OWL_ONDATARANGE = OWL_PREFIX + "onDataRange";
    public static final String XSD_PREFIX = "http://www.w3.org/2001/XMLSchema#";
    public static final String XSD_DATETIME = XSD_PREFIX + "dateTime";

    public static final IriBase XSD_BASE = new IriBase("xsd", XSD_PREFIX);
    public static final IRI DATETIME_IRI = new IRI(XSD_BASE, "dateTime");

    /**
     *
     * @param value
     * @param type
     * @return
     */
    public static String formatForSparql(final String value, final String type) {
        final StringBuilder result = new StringBuilder();

        if (value.contains("^^" + XSD_PREFIX)) {
            // Handling for mangled Literals
            final String[] parts = value.split("\\^\\^");
            quoteLiteral(result, parts[0], parts[1]);
        } else if (value.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}.\\d{3}$")) {
            // Special handling for other mangled Literals
            quoteLiteral(result, value, XSD_DATETIME);
        } else {
            switch (type) {
            case XSD_DATETIME:
            case RDFS_LITERAL_IRI:
                quoteLiteral(result, value, type);
                break;
            default:
                result.append('<');
                if (!value.startsWith("http")) {
                    result.append(type);
                    result.append(value);
                    result.append('>');
                }
                break;
            }
            return result.toString();
        }

        return "";
    }

    /**
     *
     * @param node
     * @return
     */
    public static String getValue(final RDFNode node) {
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

    static String getNamespace(final Resource resource) {
        if (resource != null) {
            final String result = resource.getNameSpace();
            if (result.endsWith("#")) {
                return result;
            } else {
                return result.split("#")[0] + "#";
            }
        }
        return "";
    }

    private static void quoteLiteral(final StringBuilder result, final String value,
            final String type) {
        final String formattedValue = value.replace("\\", "\\\\");
        if (SparqlUtils.RDFS_LITERAL.toString().equals(type)) {
            result.append("\"\"\"");
            result.append(formattedValue);
            result.append("\"\"\"");
        } else {
            result.append("\"\"\"");
            result.append(formattedValue);
            result.append("\"\"\"^^<");
            result.append(type);
            result.append(">");
        }
    }
}
