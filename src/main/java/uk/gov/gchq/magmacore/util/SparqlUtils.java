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

import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.IriBase;

/**
 * Utilities for building and executing SPARQL queries.
 */
public final class SparqlUtils {

    private SparqlUtils() {}

    public static final IriBase RDFS = new IriBase("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
    public static final IRI RDFS_LITERAL = new IRI(RDFS, "Literal");

    public static final IriBase OWL = new IriBase("owl", "http://www.w3.org/2002/07/owl#");
    public static final IRI OWL_CLASS = new IRI(OWL, "onClass");
    public static final IRI OWL_ONPROPERTY = new IRI(OWL, "onProperty");
    public static final IRI OWL_ONDATARANGE = new IRI(OWL, "onDataRange");

    public static final IriBase XSD = new IriBase("xsd", "http://www.w3.org/2001/XMLSchema#");
    public static final IRI XSD_DATETIME = new IRI(XSD, "dateTime");
}
