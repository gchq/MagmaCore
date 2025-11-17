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

package uk.gov.gchq.magmacore.service.sparql;

/**
 * SPARQL queries for {@link uk.gov.gchq.magmacore.service.MagmaCoreService}.
 */
public class MagmaCoreServiceQueries {

    /**
     * This query is used to find the Things represented by a given sign value for a particular
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern}.
     * <p>
     * It needs three parameters provided using String.format() - the sign value {@link String}, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} IRI {@link String}, and the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} IRI String.
     * </p>
     * <p>
     * The Things are likely to be states of some individual.
     * </p>
     */
    public static final String FIND_BY_SIGN_VALUE_QUERY = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>


            SELECT ?s ?p ?o ?start ?finish
            WHERE {
                BIND("%s" as ?patternValue)
                BIND(<%s> as ?rlc)
                BIND(<%s> as ?patternIri)

                ?patternIri skos:definition ?patternValue;
                    a hqdm:Pattern.
                ?repByPattern hqdm:consistsOfByClass ?patternIri;
                    hqdm:represented ?s;
                    hqdm:consistsOfInMembers ?rlc.
                ?s ?p ?o.
            }
            """;

    /**
     * This query is used to find the Things represented by a given partial sign value for a particular
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern}.
     * <p>
     * It needs three parameters provided using String.format() - the partial sign value {@link String}, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} IRI {@link String}, and the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} IRI String.
     * </p>
     * <p>
     * The Things are likely to be states of some individual.
     * </p>
     */
    public static final String FIND_BY_PARTIAL_SIGN_VALUE_CASE_INSENSITIVE_QUERY = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>


            SELECT ?s ?p ?o ?start ?finish
            WHERE {
                BIND("%s" as ?text)
                BIND(<%s> as ?rlc)

                ?patternIri skos:definition ?patternValue;
                    a hqdm:Pattern.
                ?repByPattern hqdm:consistsOfByClass ?patternIri;
                    hqdm:consistsOfInMembers ?rlc;
                    hqdm:represented ?s.
                FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                
                ?s ?p ?o.
            }
            """;

    /**
     * This query finds PARTICIPANTS in associations of a specified kind between two individuals.
     * <p>
     * It needs three parameters repeated twice (three then the same three) which are the IRI String of
     * the first individual, the IRI String of the second individual, and the IRI String of the
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfAssociation}.
     * </p>
     */
    public static final String FIND_PARTICIPANT_DETAILS_QUERY = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>

            select distinct ?s ?p ?o ?start ?finish
            where {
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND( <%s> as ?ind1)
                        BIND( <%s> as ?ind2)
                        BIND( <%s> as ?kind)

                        ?s hqdm:temporalPartOf ?ind1;
                            hqdm:participantIn ?assoc1;
                            ?p ?o.
                        ?i2stat hqdm:temporalPartOf ?ind2;
                            hqdm:participantIn ?assoc1;
                            ?i2statp ?i2stato.
                        ?assoc1 hqdm:memberOfKind ?kind.
                        OPTIONAL {
                            ?assoc1 hqdm:beginning ?begin.
                            ?begin skos:prefLabel ?start
                        }
                        OPTIONAL {
                            ?assoc1 hqdm:ending ?end.
                            ?end skos:prefLabel ?finish
                        }
                    }
                }
                UNION
                {
                    SELECT  ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND( <%s> as ?ind1)
                        BIND( <%s> as ?ind2)
                        BIND( <%s> as ?kind)

                        ?i2stat hqdm:temporalPartOf ?ind1;
                            hqdm:participantIn ?assoc1;
                            ?i2statp ?i2stato.
                        ?s hqdm:temporalPartOf ?ind2;
                            hqdm:participantIn ?assoc1;
                            ?p ?o.
                        ?assoc1 hqdm:memberOfKind ?kind.
                        OPTIONAL {
                            ?assoc1 hqdm:beginning ?begin.
                            ?begin skos:prefLabel ?start
                        }
                        OPTIONAL {
                            ?assoc1 hqdm:ending ?end.
                            ?end skos:prefLabel ?finish.
                        }
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * This query finds objects of a specified type and kind, along with the signs of a specified
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} that represent them.
     * <p>
     * It needs two groups of three parameters:
     * <ol>
     * <li>the rdf:type IRI String</li>
     * <li>the kind IRI String</li>
     * <li>the sign pattern IRI String</li>
     * </ol>
     * </p>
     * <p>
     * The result includes `skos:definition` predicates for the signValues.
     * </p>
     */
    public static final String FIND_OBJECTS_BY_TYPE_CLASS_AND_SIGN_PATTERN = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            select distinct *
            where {
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?typeIri)
                        BIND(<%s> as ?kindIri)
                        BIND(<%s> as ?patternIri)

                        ?s a ?typeIri;
                            hqdm:memberOf ?kindIri;
                            ?p ?o.
                        ?st hqdm:temporalPartOf ?s.
                        ?repByPattern hqdm:represented ?st;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfByClass ?patternIri.
                        ?patternIri a hqdm:Pattern;
                            skos:definition ?patternValue.
                    }
                }
                UNION
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?typeIri)
                        BIND(<%s> as ?kindIri)
                        BIND(<%s> as ?patternIri)

                        ?s a ?typeIri;
                            hqdm:memberOf ?kindIri;
                            ?pr ?ob.
                        ?st hqdm:temporalPartOf ?s.
                        ?repByPattern hqdm:represented ?st;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfByClass ?patternIri.
                        ?patternIri a hqdm:Pattern;
                            skos:definition ?o;
                            ?p ?o.
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * This query finds objects of a specified type and kind, along with the signs of a specified
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} that represent them.
     * <p>
     * It needs two groups of three parameters:
     * <ol>
     * <li>the rdf:type IRI String</li>
     * <li>the kind IRI String</li>
     * <li>the sign pattern IRI String</li>
     * </ol>
     * </p>
     * <p>
     * The result includes `skos:definition` predicates for the signValues.
     * </p>
     */
    public static final String FIND_OBJECTS_BY_TYPE_AND_SIGN_PATTERN = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            select distinct *
            where {
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?typeIri)
                        BIND(<%s> as ?kindIri)
                        BIND(<%s> as ?patternIri)

                        ?s a ?typeIri;
                            hqdm:memberOfKind ?kindIri;
                            ?p ?o.
                        ?st hqdm:temporalPartOf ?s.
                        ?repByPattern a hqdm:RepresentationByPattern;
                            hqdm:represented ?st;
                            hqdm:consistsOfByClass ?patternIri.
                    }
                }
                UNION
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?type)
                        BIND(<%s> as ?kind)
                        BIND(<%s> as ?pattern)

                        ?s a ?type;
                            hqdm:memberOfKind ?kind;
                            ?pr ?ob.
                        ?st hqdm:temporalPartOf ?s.
                        ?repByPattern a hqdm:RepresentationByPattern;
                            hqdm:represented ?st;
                            hqdm:consistsOfByClass ?pattern.
                        ?pattern skos:definition ?o;
                            ?p ?o.
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * Find Individuals with states participating in associations of a specified kind, their roles and
     * signs.
     * <p>
     * The Kind IRI is needed in 3 places, e.g. {@code String.format(FIND_BY_KIND_OF_ASSOCIATION, iri, iri, iri)}.
     * </p>
     */
    public static final String FIND_BY_KIND_OF_ASSOCIATION = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>

            select ?s ?p ?o
            where
            {
                {
                    select distinct ?s ?p ?o
                    WHERE {
                        BIND(<%s> as ?kindOfAssociation)
                        ?association hqdm:memberOfKind ?kindOfAssociation.
                        ?participant hqdm:participantIn ?association;
                            hqdm:memberOfKind ?role;
                            hqdm:temporalPartOf ?s.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                    select distinct ?s ?p ?o
                    WHERE {
                        BIND(<%s> as ?kindOfAssociation)
                        ?association hqdm:memberOfKind ?kindOfAssociation.
                        ?participant hqdm:participantIn ?association;
                            hqdm:memberOfKind ?role;
                            hqdm:temporalPartOf ?s.
                        ?role skos:prefLabel ?o;
                            ?p ?o.
                    }
                }
                UNION
                {
                    select distinct ?s ?p ?o
                    WHERE {
                        BIND(<%s> as ?kindOfAssociation)
                        ?association hqdm:memberOfKind ?kindOfAssociation.
                        ?participant hqdm:participantIn ?association;
                            hqdm:temporalPartOf ?s.
                        ?stateOfIndividual hqdm:temporalPartOf ?s.
                        ?repByPattern hqdm:represented ?stateOfIndividual;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfByClass ?patternIri.

                        ?patternIri skos:definition ?o;
                            ?p ?o.

                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * Find things associated to a given thing by an association of a given kind.
     */
    public static final String FIND_ASSOCIATED = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>


            select ?s ?p ?o ?start ?finish
            where
            {
                {
                select distinct ?s ?p ?o ?start ?finish
                WHERE {
                    BIND(<%s> as ?kindOfAssociation)
                    ?from hqdm:temporalPartOf <%s>;
                        hqdm:participantIn ?association.
                    ?association hqdm:memberOfKind ?kindOfAssociation.
                    ?participant hqdm:participantIn ?association;
                        hqdm:temporalPartOf ?s.
                    ?s ?p ?o.
                    FILTER(?s != <%s>)
                    OPTIONAL {
                        ?association hqdm:beginning ?begin.
                        ?begin skos:prefLabel ?start.
                    }
                    OPTIONAL {
                        ?association hqdm:ending ?end.
                        ?end skos:prefLabel ?finish.
                    }
                }
            }
            UNION
            {
                select distinct ?s ?p ?o ?start ?finish
                WHERE {
                    BIND(<%s> as ?kindOfAssociation)
                    ?from hqdm:temporalPartOf <%s>;
                        hqdm:participantIn ?association.
                    ?association hqdm:memberOfKind ?kindOfAssociation.
                    ?participant hqdm:participantIn ?association;
                        hqdm:temporalPartOf ?s;
                    hqdm:memberOfKind ?role.
                    FILTER(?s != <%s>)
                    OPTIONAL {
                        ?association hqdm:beginning ?begin.
                        ?begin skos:prefLabel ?start.
                    }
                    OPTIONAL {
                        ?association hqdm:ending ?end.
                        ?end skos:prefLabel ?finish.
                    }
                    ?role skos:prefLabel ?o;
                    ?p ?o.
                }
            }
            UNION
            {
                select distinct ?s ?p ?o ?start ?finish
                WHERE {
                    BIND(<%s> as ?kindOfAssociation)
                    ?from hqdm:temporalPartOf <%s>;
                        hqdm:participantIn ?association.
                    ?association hqdm:memberOfKind ?kindOfAssociation.
                    ?participant hqdm:participantIn ?association;
                        hqdm:temporalPartOf ?s.
                    FILTER(?s != <%s>)
                    ?stateOfIndividual hqdm:temporalPartOf ?s.
                    ?repByPattern hqdm:represented ?stateOfIndividual.
                    ?repByPattern a hqdm:RepresentationByPattern.
                    ?repByPattern hqdm:consistsOfByClass ?patternIri.
                    OPTIONAL {
                        ?association hqdm:beginning ?begin.
                        ?begin skos:prefLabel ?start.
                    }
                    OPTIONAL {
                        ?association hqdm:ending ?end.
                        ?end skos:prefLabel ?finish.
                    }
                    ?patternIri skos:definition ?o;
                        ?p ?o.
                }
            }
        }
        order by ?s ?p ?o

            """;

    /**
     * Search for items whose sign contains some text and are members of a specific class.
     */
    public static final String FIND_MEMBERS_OF_CLASS_BY_PARTIAL_SIGN_CASE_SENSITIVE = """
                PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
                PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

                SELECT distinct ?s ?p ?o ?start ?finish
                WHERE {
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class) # IRI of the class

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(str(?patternValue), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                    SELECT ?s (skos:definition as ?p) ?o ?start ?finish
                    WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class) # IRI of the class

                        ?pattern skos:definition ?o.
                        FILTER(CONTAINS(str(?o), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                    }
                }
                UNION
                {
                    SELECT ?s (skos:prefLabel as ?p) ?o ?start ?finish
                    WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(str(?patternValue), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?s hqdm:memberOfKind ?kind.
                        ?kind skos:prefLabel ?o.
                    }
                }

            }
            order by ?s ?p ?o
            """;

    /**
     * Search for items whose sign contains some text and are members of a specific class.
     */
    public static final String FIND_MEMBERS_OF_CLASS_BY_PARTIAL_SIGN_CASE_INSENSITIVE = """
                PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
                PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

                SELECT distinct ?s ?p ?o ?start ?finish
                WHERE {
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class) # IRI of the class

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                    SELECT ?s (skos:definition as ?p) ?o ?start ?finish
                    WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class) # IRI of the class

                        ?pattern skos:definition ?o.
                        FILTER(CONTAINS(lcase(str(?o)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                    }
                }
                UNION
                {
                    SELECT ?s (skos:prefLabel as ?p) ?o ?start ?finish
                    WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?s hqdm:memberOfKind ?kind.
                        ?kind skos:prefLabel ?o.
                    }
                }

            }
            order by ?s ?p ?o
            """;

    /**
     * A partial search by sign for entities referenced by an Activity.
     */
    public static final String FIND_MEMBERS_OF_CLASS_BY_ACTIVITY_AND_PARTIAL_SIGN_CASE_SENSITIVE = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT distinct ?s ?p ?o ?start ?finish
            WHERE {
            {
                SELECT ?s ?p ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(str(?patternValue), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?topicId hqdm:references ?s.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                SELECT ?s (skos:definition as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?o.
                        FILTER(CONTAINS(str(?o), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?topicId hqdm:references ?s.
                    }
                }
                UNION
                {
                SELECT ?s (skos:prefLabel as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(str(?patternValue), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?topicId hqdm:references ?s.
                        ?s hqdm:memberOfKind ?kind.
                        ?kind skos:prefLabel ?o.
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * A partial search by sign for entities referenced by an Activity.
     */
    public static final String FIND_MEMBERS_OF_CLASS_BY_ACTIVITY_AND_PARTIAL_SIGN_CASE_INSENSITIVE = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT distinct ?s ?p ?o ?start ?finish
            WHERE {
            {
                SELECT ?s ?p ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?topicId hqdm:references ?s.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                SELECT ?s (skos:definition as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?o.
                        FILTER(CONTAINS(lcase(str(?o)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?topicId hqdm:references ?s.
                    }
                }
                UNION
                {
                SELECT ?s (skos:prefLabel as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?topicId hqdm:references ?s.
                        ?s hqdm:memberOfKind ?kind.
                        ?kind skos:prefLabel ?o.
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * A partial search by sign for entities composed into a whole entity.
     */
    public static final String FIND_MEMBERS_OF_CLASS_BY_COMPOSITION_AND_PARTIAL_SIGN_CASE_SENSITIVE = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT distinct ?s ?p ?o ?start ?finish
            WHERE {
            {
                SELECT ?s ?p ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(str(?patternValue), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.

                        ?comp hqdm:part ?s;
                            hqdm:whole ?topicId.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                SELECT ?s (skos:definition as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?o.
                        FILTER(CONTAINS(str(?o), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.

                        ?comp hqdm:part ?s;
                            hqdm:whole ?topicId.
                    }
                }
                UNION
                {
                SELECT ?s (skos:prefLabel as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(str(?patternValue), ?text))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?comp hqdm:part ?s;
                            hqdm:whole ?topicId.
                        ?s hqdm:memberOfKind ?kind.
                        ?kind skos:prefLabel ?o.
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * A partial search by sign for entities composed into a whole entity.
     */
    public static final String FIND_MEMBERS_OF_CLASS_BY_COMPOSITION_AND_PARTIAL_SIGN_CASE_INSENSITIVE = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT distinct ?s ?p ?o ?start ?finish
            WHERE {
            {
                SELECT ?s ?p ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.

                        ?comp hqdm:part ?s;
                            hqdm:whole ?topicId.
                        ?s ?p ?o.
                    }
                }
                UNION
                {
                SELECT ?s (skos:definition as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?o.
                        FILTER(CONTAINS(lcase(str(?o)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.

                        ?comp hqdm:part ?s;
                            hqdm:whole ?topicId.
                    }
                }
                UNION
                {
                SELECT ?s (skos:prefLabel as ?p) ?o ?start ?finish
                WHERE {
                        BIND("%s" as ?text)
                        BIND(<%s> as ?class)
                        BIND(<%s> as ?topicId)

                        ?pattern skos:definition ?patternValue.
                        FILTER(CONTAINS(lcase(str(?patternValue)), lcase(?text)))
                        ?repByPattern hqdm:consistsOfByClass ?pattern;
                            a hqdm:RepresentationByPattern;
                            hqdm:consistsOfInMembers ?rlc;
                            hqdm:represented ?state.
                        ?state hqdm:temporalPartOf ?s.
                        ?s hqdm:memberOf ?class.
                        ?comp hqdm:part ?s;
                            hqdm:whole ?topicId.
                        ?s hqdm:memberOfKind ?kind.
                        ?kind skos:prefLabel ?o.
                    }
                }
            }
            order by ?s ?p ?o
            """;

    /**
     * Find the signs for an entity and the pattern and representation by pattern ENTITY_NAMES.
     */
    public static final String FIND_SIGNS_FOR_ENTITY = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT distinct ?signValue ?patternName ?repByPatternName ?start ?finish
            WHERE
            {
                {
                    SELECT *
                    WHERE {
                        BIND(<%s> as ?s)
                        ?stateOfS hqdm:temporalPartOf ?s.
                        ?repByPattern hqdm:represented ?stateOfS;
                            hqdm:consistsOfByClass ?pattenrIri;
                            skos:prefLabel ?repByPatternName.
                        ?patternIri skos:definition ?signValue.
                    }
                }
            }
            """;

    /**
     * Find by field value and class query.
     */
    public static final String FIND_BY_FIELD_VALUE_AND_CLASS = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            SELECT ?s ?p ?o
            WHERE {
                BIND(<%s> as ?part)
                BIND(<%s> as ?partThing)
                BIND(<%s> as ?class)

                ?s ?part ?partThing;
                    ?p ?o.
            }
            """;
}
