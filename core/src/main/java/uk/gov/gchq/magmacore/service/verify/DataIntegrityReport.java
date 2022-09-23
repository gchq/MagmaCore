package uk.gov.gchq.magmacore.service.verify;

import java.util.ArrayList;
import java.util.List;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.hqdm.model.Thing;

/**
 * Check an HQDM model for missing predicates.
 */
public class DataIntegrityReport {

    private static final String CHECK_MISSING_DATA_ENTITY_NAME = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
            ?s hqdm:error_missing_entity_name "Should have a data_EntityName.".
            ?s a ?type.
            }
            where {
            ?s a ?type;
            ?p ?o.
            OPTIONAL {
            ?s hqdm:data_EntityName ?name.
            }

            FILTER(!bound(?name))
            FILTER(?type in (
            hqdm:class,
            hqdm:classification,
            hqdm:class_of_abstract_object,
            hqdm:class_of_activity,
            hqdm:class_of_agree_contract,
            hqdm:class_of_agreement_execution,
            hqdm:class_of_agreement_process,
            hqdm:class_of_amount_of_money,
            hqdm:class_of_association,
            hqdm:class_of_biological_object,
            hqdm:class_of_biological_system,
            hqdm:class_of_biological_system_component,
            hqdm:class_of_class,
            hqdm:class_of_class_of_spatio_temporal_extent,
            hqdm:class_of_contract_execution,
            hqdm:class_of_contract_process,
            hqdm:class_of_event,
            hqdm:class_of_functional_object,
            hqdm:class_of_functional_system,
            hqdm:class_of_functional_system_component,
            hqdm:class_of_individual,
            hqdm:class_of_in_place_biological_component,
            hqdm:class_of_installed_functional_system_component,
            hqdm:class_of_installed_object,
            hqdm:class_of_intentionally_constructed_object,
            hqdm:class_of_offer,
            hqdm:class_of_ordinary_biological_object,
            hqdm:class_of_ordinary_functional_object,
            hqdm:class_of_ordinary_physical_object,
            hqdm:class_of_organization,
            hqdm:class_of_organization_component,
            hqdm:class_of_participant,
            hqdm:class_of_party,
            hqdm:class_of_period_of_time,
            hqdm:class_of_person,
            hqdm:class_of_person_in_position,
            hqdm:class_of_physical_object,
            hqdm:class_of_physical_property,
            hqdm:class_of_physical_quantity,
            hqdm:class_of_point_in_time,
            hqdm:class_of_position,
            hqdm:class_of_possible_world,
            hqdm:class_of_reaching_agreement,
            hqdm:class_of_relationship,
            hqdm:class_of_representation,
            hqdm:class_of_sales_product_instance,
            hqdm:class_of_sign,
            hqdm:class_of_socially_constructed_activity,
            hqdm:class_of_socially_constructed_object,
            hqdm:class_of_spatio_temporal_extent,
            hqdm:class_of_state,
            hqdm:class_of_state_of_activity,
            hqdm:class_of_state_of_amount_of_money,
            hqdm:class_of_state_of_association,
            hqdm:class_of_state_of_biological_object,
            hqdm:class_of_state_of_biological_system,
            hqdm:class_of_state_of_biological_system_component,
            hqdm:class_of_state_of_functional_object,
            hqdm:class_of_state_of_functional_system,
            hqdm:class_of_state_of_functional_system_component,
            hqdm:class_of_state_of_intentionally_constructed_object,
            hqdm:class_of_state_of_ordinary_biological_object,
            hqdm:class_of_state_of_ordinary_functional_object,
            hqdm:class_of_state_of_ordinary_physical_object,
            hqdm:class_of_state_of_organization,
            hqdm:class_of_state_of_organization_component,
            hqdm:class_of_state_of_party,
            hqdm:class_of_state_of_person,
            hqdm:class_of_state_of_physical_object,
            hqdm:class_of_state_of_position,
            hqdm:class_of_state_of_sales_product_instance,
            hqdm:class_of_state_of_sign,
            hqdm:class_of_state_of_socially_constructed_activity,
            hqdm:class_of_state_of_socially_constructed_object,
            hqdm:class_of_state_of_system,
            hqdm:class_of_state_of_system_component,
            hqdm:class_of_system,
            hqdm:class_of_system_component,
            hqdm:kind_of_activity,
            hqdm:kind_of_association,
            hqdm:kind_of_biological_object,
            hqdm:kind_of_biological_system,
            hqdm:kind_of_biological_system_component,
            hqdm:kind_of_functional_object,
            hqdm:kind_of_functional_system,
            hqdm:kind_of_functional_system_component,
            hqdm:kind_of_individual,
            hqdm:kind_of_intentionally_constructed_object,
            hqdm:kind_of_ordinary_biological_object,
            hqdm:kind_of_ordinary_functional_object,
            hqdm:kind_of_ordinary_physical_object,
            hqdm:kind_of_organization,
            hqdm:kind_of_organization_component,
            hqdm:kind_of_party,
            hqdm:kind_of_person,
            hqdm:kind_of_physical_object,
            hqdm:kind_of_physical_property,
            hqdm:kind_of_physical_quantity,
            hqdm:kind_of_position,
            hqdm:kind_of_relationship_with_restriction,
            hqdm:kind_of_relationship_with_signature,
            hqdm:kind_of_socially_constructed_object,
            hqdm:kind_of_system,
            hqdm:kind_of_system_component,
            hqdm:role,
            hqdm:pattern,
            hqdm:description,
            hqdm:definition,
            hqdm:identification
            ))
            }
            """;
    private static final String CHECK_MISSING_PARTICIPANT_ROLES = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
            ?s hqdm:error_participant_with_no_role "Should be a member_of_kind of a role.".
            ?s a ?type.
            }
            where {
            ?s hqdm:participant_in ?association;
            a ?type.

            ?association a hqdm:association.

            OPTIONAL {
            ?s hqdm:member_of_kind ?kind.
            ?kind a hqdm:role.
            }

            FILTER(!bound(?kind))
            }
                                """;
    private static final String CHECK_POSSIBLE_WORLD_MEMBERSHIP = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>
            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

            construct {
            ?s hqdm:error_possible_world "Should be part of a possible world.".
            ?s a ?type.
            }
            where {
            ?s a ?type;
            ?p ?o.
            OPTIONAL {
            ?s hqdm:part_of_possible_world ?pw.
            }

            FILTER(!bound(?pw))
            FILTER(?type in (
            hqdm:acceptance_of_offer,
            hqdm:acceptance_of_offer_for_goods,
            hqdm:activity,
            hqdm:aggregation,
            hqdm:agree_contract,
            hqdm:agreement_execution,
            hqdm:agreement_process,
            hqdm:amount_of_money,
            hqdm:asset,
            hqdm:association,
            hqdm:beginning_of_ownership,
            hqdm:biological_object,
            hqdm:biological_system,
            hqdm:biological_system_component,
            hqdm:composition,
            hqdm:contract_execution,
            hqdm:contract_process,
            hqdm:currency,
            hqdm:employee,
            hqdm:employer,
            hqdm:employment,
            hqdm:ending_of_ownership,
            hqdm:event,
            hqdm:exchange_of_goods_and_money,
            hqdm:function_,
            hqdm:functional_object,
            hqdm:functional_system,
            hqdm:functional_system_component,
            hqdm:identification_of_physical_quantity,
            hqdm:individual,
            hqdm:in_place_biological_component,
            hqdm:installed_functional_system_component,
            hqdm:installed_object,
            hqdm:intentionally_constructed_object,
            hqdm:language_community,
            hqdm:money_asset,
            hqdm:offer,
            hqdm:offer_and_acceptance_for_goods,
            hqdm:offer_for_goods,
            hqdm:offering,
            hqdm:ordinary_biological_object,
            hqdm:ordinary_functional_object,
            hqdm:ordinary_physical_object,
            hqdm:organization,
            hqdm:organization_component,
            hqdm:owner,
            hqdm:ownership,
            hqdm:participant,
            hqdm:participant_in_activity_or_association,
            hqdm:party,
            hqdm:period_of_time,
            hqdm:person,
            hqdm:person_in_position,
            hqdm:physical_object,
            hqdm:physical_property,
            hqdm:physical_property_range,
            hqdm:physical_quantity,
            hqdm:physical_quantity_range,
            hqdm:plan,
            hqdm:point_in_time,
            hqdm:position,
            hqdm:possible_world,
            hqdm:price,
            hqdm:product_brand,
            hqdm:product_offering,
            hqdm:reaching_agreement,
            hqdm:recognizing_language_community,
            hqdm:relationship,
            hqdm:representation_by_sign,
            hqdm:requirement,
            hqdm:requirement_specification,
            hqdm:sale_of_goods,
            hqdm:sales_product,
            hqdm:sales_product_instance,
            hqdm:sales_product_version,
            hqdm:scale,
            hqdm:sign,
            hqdm:socially_constructed_activity,
            hqdm:socially_constructed_object,
            hqdm:spatio_temporal_extent,
            hqdm:state,
            hqdm:state_of_activity,
            hqdm:state_of_amount_of_money,
            hqdm:state_of_association,
            hqdm:state_of_biological_object,
            hqdm:state_of_biological_system,
            hqdm:state_of_biological_system_component,
            hqdm:state_of_functional_object,
            hqdm:state_of_functional_system,
            hqdm:state_of_functional_system_component,
            hqdm:state_of_intentionally_constructed_object,
            hqdm:state_of_language_community,
            hqdm:state_of_ordinary_biological_object,
            hqdm:state_of_ordinary_functional_object,
            hqdm:state_of_ordinary_physical_object,
            hqdm:state_of_organization,
            hqdm:state_of_organization_component,
            hqdm:state_of_party,
            hqdm:state_of_person,
            hqdm:state_of_physical_object,
            hqdm:state_of_position,
            hqdm:state_of_sales_product_instance,
            hqdm:state_of_sign,
            hqdm:state_of_socially_constructed_activity,
            hqdm:state_of_socially_constructed_object,
            hqdm:state_of_system,
            hqdm:state_of_system_component,
            hqdm:system,
            hqdm:system_component,
            hqdm:temporal_composition,
            hqdm:transferee,
            hqdm:transfer_of_ownership,
            hqdm:transfer_of_ownership_of_money,
            hqdm:transferor,
            hqdm:unit_of_measure
            ))
            }
            """;

    private static final String CHECK_STATE_TEMPORAL_PART_OF = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_temporal_part_of "Should be a temporal_part_of some individual.".
              ?s a ?type.
            }
            where {
                ?s a ?type;
                   ?p ?o.
                OPTIONAL {
                  ?s hqdm:temporal_part_of ?individual.
                }

              FILTER(!bound(?individual))
              FILTER(?type in (
                    hqdm:state_of_activity,
                    hqdm:state_of_amount_of_money,
                    hqdm:state_of_association,
                    hqdm:state_of_biological_object,
                    hqdm:state_of_biological_system,
                    hqdm:state_of_biological_system_component,
                    hqdm:state_of_functional_object,
                    hqdm:state_of_functional_system,
                    hqdm:state_of_functional_system_component,
                    hqdm:state_of_intentionally_constructed_object,
                    hqdm:state_of_language_community,
                    hqdm:state_of_ordinary_biological_object,
                    hqdm:state_of_ordinary_functional_object,
                    hqdm:state_of_ordinary_physical_object,
                    hqdm:state_of_organization,
                    hqdm:state_of_organization_component,
                    hqdm:state_of_party,
                    hqdm:state_of_person,
                    hqdm:state_of_physical_object,
                    hqdm:state_of_position,
                    hqdm:state_of_sales_product_instance,
                    hqdm:state_of_sign,
                    hqdm:state_of_socially_constructed_activity,
                    hqdm:state_of_socially_constructed_object,
                    hqdm:state_of_system,
                    hqdm:state_of_system_component
                ))
            }
            """;
    private static final String CHECK_SIGN_VALUE_ = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_value_ "Should have a value_ for a sign.".
              ?s a hqdm:sign.
            }
            where {
                ?s a hqdm:sign;
                   ?p ?o.
                OPTIONAL {
                  ?s hqdm:value_ ?v.
                }

              FILTER(!bound(?v))
            }
            """;
    private static final String CHECK_SIGN_MEMBER_OF_PATTERN = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_member_of_ "Should be a member_of_ of some pattern.".
              ?s a hqdm:sign.
            }
            where {
                ?s a hqdm:sign;
                   ?p ?o.
                OPTIONAL {
                  ?s hqdm:member_of_ ?pattern.
                }

              FILTER(!bound(?pattern))
            }
            """;
    private static final String CHECK_REP_BY_PATTERN_CONSISTS_OF_BY_CLASS = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_consists_of_by_class
                  "Should be a target of a consists_of_by_class from Rep By Pattern.".
              ?s a hqdm:pattern.
            }
            where {
                ?s a hqdm:pattern.
                OPTIONAL {
                  ?repByPattern hqdm:consists_of_by_class ?s.
                }

              FILTER(!bound(?repByPattern))
            }
            """;
    private static final String CHECK_REP_BY_SIGN_CONSISTS_OF_COMMUNITY = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_consists_of_ "Should have a consists_of_ from Rep By Sign.".
              ?s a hqdm:representation_by_sign.
            }
            where {
                ?s a hqdm:representation_by_sign.
                OPTIONAL {
                  ?s hqdm:consists_of_ ?community.
                }

              FILTER(!bound(?community))
            }
            """;
    private static final String CHECK_REP_BY_SIGN_CONSISTS_OF_SIGN = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_consists_of "Should have a consists_of from Rep By Sign.".
              ?s a hqdm:representation_by_sign.
            }
            where {
                ?s a hqdm:representation_by_sign.
                OPTIONAL {
                  ?s hqdm:consists_of ?state_of_sign.
                }

              FILTER(!bound(?state_of_sign))
            }
            """;
    private static final String CHECK_REP_BY_PATTERN_CONSISTS_OF_IN_MEMBERS = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_consists_of_in_members "Should have a consists_of_in_members from Rep By Pattern.".
              ?s a ?type.
            }
            where {
                ?s a ?type.
                OPTIONAL {
                  ?s hqdm:consists_of_in_members ?community.
                }

              FILTER(!bound(?community))
                  FILTER(?type in (
                              hqdm:representation_by_pattern,
                              hqdm:identification,
                              hqdm:definition,
                              hqdm:description
                              ))
            }
            """;
    private static final String CHECK_REP_BY_SIGN_REPRESENTS = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_represents "Should have a represents from Rep By Sign.".
              ?s a hqdm:representation_by_sign.
            }
            where {
                ?s a hqdm:representation_by_sign.
                OPTIONAL {
                  ?s hqdm:represents ?thing.
                }

              FILTER(!bound(?thing))
            }
            """;
    private static final String CHECK_STATE_OF_SIGN_PRTICIPANT_IN = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_sign_participant_in "Should have a participant_in to Rep By Sign.".
              ?s a hqdm:state_of_sign.
            }
            where {
                ?s a hqdm:state_of_sign.
                OPTIONAL {
                  ?s hqdm:participant_in ?repBySign.
                }

              FILTER(!bound(?repBySign))
            }
            """;
    private static final String CHECK_REP_BY_SIGN_MEMBER_OF = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_member_of_ "Should have a member_of_ to Rep By Pattern.".
              ?s a hqdm:representation_by_sign.
            }
            where {
                ?s a hqdm:representation_by_sign.
                OPTIONAL {
                  ?s hqdm:member_of_ ?repByPattern.
                }

              FILTER(!bound(?repByPattern))
            }
            """;
    private static final String CHECK_REP_BY_SIGN_HAS_SIGN_PARTICIPANT = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_sign "Should have a state_of_sign as a participant_in this Rep By Sign.".
              ?s a hqdm:representation_by_sign.
            }
            where {
                ?s a hqdm:representation_by_sign.
                OPTIONAL {
                  ?sign hqdm:participant_in ?s;
                        a hqdm:state_of_sign.
                }

              FILTER(!bound(?sign))
            }
            """;
    private static final String CHECK_REP_BY_SIGN_HAS_COMMUNITY_PARTICIPANT = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_community
                  "Should have a recognizing_language_community as a participant_in this Rep By Sign.".
              ?s a hqdm:representation_by_sign.
            }
            where {
                ?s a hqdm:representation_by_sign.
                OPTIONAL {
                  ?community hqdm:participant_in ?s;
                        a hqdm:recognizing_language_community.
                }

              FILTER(!bound(?community))
            }
            """;
    private static final String CHECK_ROLE_PART_OF_BY_CLASS_ = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_part_of_by_class_ "Should have a part_of_by_class_ to a kind_of_association.".
              ?s a hqdm:role.
            }
            where {
                ?s a hqdm:role.
                OPTIONAL {
                  ?s hqdm:part_of_by_class_ ?kind_of_association.
                  ?kind_of_association a hqdm:kind_of_association.
                }

              FILTER(!bound(?kind_of_association))
            }
            """;
    private static final String CHECK_ASSOCIATION_MEMBER_OF_KIND = """
            PREFIX hqdm: <http://www.semanticweb.org/hqdm#>

            construct {
              ?s hqdm:error_missing_kind_of_association "Should have a member_of_kind to a kind_of_association.".
              ?s a hqdm:association.
            }
            where {
                ?s a hqdm:association.
                OPTIONAL {
                  ?s hqdm:member_of_kind ?kind_of_association.
                  ?kind_of_association a hqdm:kind_of_association.
                }

              FILTER(!bound(?kind_of_association))
            }
            """;

    /**
     * Verify a HQDM Data Model.
     *
     * @param db {@link MagmaCoreDatabase}
     * @return a {@link List} of {@link Thing} that represent data integrity errors.
     */
    public static List<Thing> verify(final MagmaCoreDatabase db) {
        db.begin();

        final List<Thing> errors = new ArrayList<>();

        errors.addAll(db.executeConstruct(CHECK_POSSIBLE_WORLD_MEMBERSHIP));
        errors.addAll(db.executeConstruct(CHECK_MISSING_PARTICIPANT_ROLES));
        errors.addAll(db.executeConstruct(CHECK_MISSING_DATA_ENTITY_NAME));
        errors.addAll(db.executeConstruct(CHECK_STATE_TEMPORAL_PART_OF));
        errors.addAll(db.executeConstruct(CHECK_SIGN_MEMBER_OF_PATTERN));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_PATTERN_CONSISTS_OF_BY_CLASS));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_SIGN_CONSISTS_OF_COMMUNITY));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_SIGN_CONSISTS_OF_SIGN));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_SIGN_REPRESENTS));
        errors.addAll(db.executeConstruct(CHECK_STATE_OF_SIGN_PRTICIPANT_IN));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_SIGN_MEMBER_OF));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_SIGN_HAS_SIGN_PARTICIPANT));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_SIGN_HAS_COMMUNITY_PARTICIPANT));
        errors.addAll(db.executeConstruct(CHECK_ROLE_PART_OF_BY_CLASS_));
        errors.addAll(db.executeConstruct(CHECK_ASSOCIATION_MEMBER_OF_KIND));
        errors.addAll(db.executeConstruct(CHECK_REP_BY_PATTERN_CONSISTS_OF_IN_MEMBERS));
        errors.addAll(db.executeConstruct(CHECK_SIGN_VALUE_));

        db.abort();

        return errors;
    }
}
