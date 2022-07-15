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

package uk.gov.gchq.magmacore.hqdm.rdf.iri;

/**
 * RDF Schema IRI definitions.
 */
public final class RDFS {

    private RDFS() {
    }

    /**
     * Base namespace of the RDF Concepts Vocabulary (RDF).
     * 
     * <p>
     * RDF Schema for the RDF vocabulary terms in the RDF Namespace.
     * </p>
     *
     * @see <a href= "https://www.w3.org/TR/rdf-schema/">https://www.w3.org/TR/rdf-schema/</a>
     */
    public static final IriBase RDF = new IriBase("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");

    /**
     * {@code rdf:type} is an instance of {@code rdf:Property} that is used to state that a resource is
     * an instance of a class.
     */
    public static final IRI RDF_TYPE = new IRI(RDF, "type");

    /**
     * Base namespace of the RDF Schema vocabulary (RDFS).
     *
     * <p>
     * RDF Schema provides a data-modelling vocabulary for RDF data. RDF Schema is an extension of the
     * basic RDF vocabulary.
     * </p>
     *
     * @see <a href= "https://www.w3.org/TR/rdf-schema/">https://www.w3.org/TR/rdf-schema/</a>
     */
    public static final IriBase RDFS = new IriBase("rdfs", "http://www.w3.org/2000/01/rdf-schema#");

    /**
     * This is the class of resources that are RDF classes.
     */
    public static final IRI RDFS_CLASS = new IRI(RDFS, "class");

    /**
     * The class {@code rdfs:Literal} is the class of literal values such as strings and integers.
     * Property values such as textual strings are examples of RDF literals.
     */
    public static final IRI RDFS_LITERAL = new IRI(RDFS, "Literal");

    /**
     * The property {@code rdfs:subClassOf} is an instance of {@code rdf:Property} that is used to state
     * that all the instances of one class are instances of another.
     */
    public static final IRI RDFS_SUB_CLASS_OF = new IRI(RDFS, "subClassOf");

}
