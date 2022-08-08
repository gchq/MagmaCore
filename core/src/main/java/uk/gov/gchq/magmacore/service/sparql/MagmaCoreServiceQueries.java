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
     * It needs three parameters privided using String.format() - the sign value {@link String}, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} IRI {@link String}, and the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} IRI String.
     * </p>
     * <p>
     * The Things are likely to be states of some individual.
     * </p>
     */
    public static final String FIND_BY_SIGN_VALUE_QUERY = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>


            SELECT ?s ?p ?o ?start ?finish
            WHERE {
                BIND("%s" as ?signvalue)
                BIND(<%s> as ?rlc)
                BIND(<%s> as ?pattern)

                ?sign hqdm:value_ ?signvalue;
                    hqdm:member_of_ ?pattern.
                ?sos hqdm:temporal_part_of ?sign;
                    hqdm:participant_in ?repBySign.
                ?rlc hqdm:participant_in ?repBySign.
                ?repBySign hqdm:represents ?s.
                ?s ?p ?o.
                OPTIONAL {
                    ?repBySign hqdm:beginning ?begin.
                    ?begin hqdm:data_EntityName ?start.
                    ?repBySign hqdm:ending ?end.
                    ?end hqdm:data_EntityName ?finish.
                }

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
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>
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

                        ?s hqdm:temporal_part_of ?ind1;
                            hqdm:participant_in ?assoc1;
                            ?p ?o.
                        ?i2stat hqdm:temporal_part_of ?ind2;
                            hqdm:participant_in ?assoc1;
                            ?i2statp ?i2stato.
                        ?assoc1 hqdm:member_of_kind ?kind.
                        OPTIONAL {
                            ?assoc1 hqdm:beginning ?begin.
                            ?begin hqdm:data_EntityName ?start
                        }
                        OPTIONAL {
                            ?assoc1 hqdm:ending ?end.
                            ?end hqdm:data_EntityName ?finish
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

                        ?i2stat hqdm:temporal_part_of ?ind1;
                            hqdm:participant_in ?assoc1;
                            ?i2statp ?i2stato.
                        ?s hqdm:temporal_part_of ?ind2;
                            hqdm:participant_in ?assoc1;
                            ?p ?o.
                        ?assoc1 hqdm:member_of_kind ?kind.
                        OPTIONAL {
                            ?assoc1 hqdm:beginning ?begin.
                            ?begin hqdm:data_EntityName ?start
                        }
                        OPTIONAL {
                            ?assoc1 hqdm:ending ?end.
                            ?end hqdm:data_EntityName ?finish.
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
     * The result includes `hqdm:value_` predicates for the signValues.
     * </p>
     */
    public static final String FIND_OBJECTS_BY_TYPE_AND_SIGN_PATTERN = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>
            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

            select distinct *
            where {
                {
                    SELECT ?s ?p ?o ?start ?finish
                    WHERE {
                    BIND(<%s> as ?type)
                    BIND(<%s> as ?kind)
                    BIND(<%s> as ?pattern)

                    ?s a ?type;
                        hqdm:member_of_kind ?kind;
                    ?p ?o.
                    ?st hqdm:temporal_part_of ?s.
                    ?repBySign hqdm:represents ?st.
                    ?signst hqdm:participant_in ?repBySign;
                        a hqdm:state_of_sign;
                        hqdm:temporal_part_of ?sign.
                    ?sign hqdm:value_ ?signvalue;
                        hqdm:member_of_ ?pattern.

                    OPTIONAL {
                        ?repBySign hqdm:beginning ?begin.
                        ?begin hqdm:data_EntityName ?start
                        }
                    OPTIONAL {
                        ?repBySign hqdm:ending ?end.
                        ?end hqdm:data_EntityName ?finish
                        }

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
                            hqdm:member_of_kind ?kind;
                            ?pr ?ob.
                        ?st hqdm:temporal_part_of ?s.
                        ?repBySign hqdm:represents ?st.
                        ?signst hqdm:participant_in ?repBySign;
                            a hqdm:state_of_sign;
                            hqdm:temporal_part_of ?sign.
                        ?sign hqdm:value_ ?o;
                            ?p ?o;
                            hqdm:member_of_ ?pattern.

                    OPTIONAL {
                        ?repBySign hqdm:beginning ?begin.
                        ?begin hqdm:data_EntityName ?start
                        }
                    OPTIONAL {
                        ?repBySign hqdm:ending ?end.
                        ?end hqdm:data_EntityName ?finish
                        }

                    }
                }
            }
            order by ?s ?p ?o

                        """;

    /**
     * Find Individuals with states participting in associations of a specified kind, their roles and
     * signs.
     * <p>
     * The Kind IRI is needed in 3 places, e.g. String.format(FIND_BY_KIND_OF_ASSOCIATION, iri, iri,
     * iri).
     * </p>
     */
    public static final String FIND_BY_KIND_OF_ASSOCIATION = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            select ?s ?p ?o  ?start ?finish
            where
            {
                {
                    select distinct ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?kind_of_association)
                        ?association hqdm:member_of_kind ?kind_of_association.
                        ?participant hqdm:participant_in ?association;
                            hqdm:member_of_kind ?role;
                            hqdm:temporal_part_of ?s.
                        ?s ?p ?o.
                        OPTIONAL {
                            ?association hqdm:beginning ?begin.
                            ?begin hqdm:data_EntityName ?start.
                        }
                        OPTIONAL {
                            ?association hqdm:ending ?end.
                            ?end hqdm:data_EntityName ?finish.
                        }
                    }
                }
                UNION
                {
                    select distinct ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?kind_of_association)
                        ?association hqdm:member_of_kind ?kind_of_association.
                        ?participant hqdm:participant_in ?association;
                            hqdm:member_of_kind ?role;
                            hqdm:temporal_part_of ?s.
                        ?role hqdm:data_EntityName ?o;
                        ?p ?o.
                        OPTIONAL {
                            ?association hqdm:beginning ?begin.
                            ?begin hqdm:data_EntityName ?start.
                        }
                        OPTIONAL {
                            ?association hqdm:ending ?end.
                            ?end hqdm:data_EntityName ?finish.
                        }
                    }
                }
                UNION
                {
                    select distinct ?s ?p ?o ?start ?finish
                    WHERE {
                        BIND(<%s> as ?kind_of_association)
                        ?association hqdm:member_of_kind ?kind_of_association.
                        ?participant hqdm:participant_in ?association;
                            hqdm:temporal_part_of ?individual.
                        ?temporal_part hqdm:temporal_part_of ?individual.
                        ?state_of_individual hqdm:temporal_part_of ?individual.
                        ?repBySign hqdm:represents ?state_of_individual.
                        ?repBySign a hqdm:representation_by_sign.
                        ?state_of_individual hqdm:temporal_part_of ?s.
                        ?state_of_sign hqdm:participant_in ?repBySign;
                            hqdm:temporal_part_of ?sign.
                        ?sign hqdm:value_ ?o;
                            ?p ?o.
                        OPTIONAL {
                            ?repBySign hqdm:beginning ?begin.
                            ?begin hqdm:data_EntityName ?start.
                        }
                        OPTIONAL {
                            ?repBySign hqdm:ending ?end.
                            ?end hqdm:data_EntityName ?finish.
                        }
                    }
                }
            }
            order by ?s ?p ?o
                        """;
}