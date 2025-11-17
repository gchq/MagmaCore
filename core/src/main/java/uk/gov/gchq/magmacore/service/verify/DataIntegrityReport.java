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
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
            ?s hqdm:errorMissingEntityName "Should have a dataentityName.".
            ?s a ?type.
            }
            where {
            ?s a ?type;
            ?p ?o.
            OPTIONAL {
            ?s hqdm:dataentityName ?name.
            }

            FILTER(!bound(?name))
            FILTER(?type in (
            hqdm:class,
            hqdm:classification,
            hqdm:classOfAbstractObject,
            hqdm:classOfActivity,
            hqdm:classOfAgreeContract,
            hqdm:classOfAgreementExecution,
            hqdm:classOfAgreementProcess,
            hqdm:classOfAmountOfMoney,
            hqdm:classOfAssociation,
            hqdm:classOfBiologicalObject,
            hqdm:classOfBiologicalSystem,
            hqdm:classOfBiologicalSystemComponent,
            hqdm:classOfClass,
            hqdm:classOfClassOfSpatioTemporalExtent,
            hqdm:classOfContractExecution,
            hqdm:classOfContractProcess,
            hqdm:classOfEvent,
            hqdm:classOfFunctionalObject,
            hqdm:classOfFunctionalSystem,
            hqdm:classOfFunctionalSystemComponent,
            hqdm:classOfIndividual,
            hqdm:classOfInPlaceBiologicalComponent,
            hqdm:classOfInstalledFunctionalSystemComponent,
            hqdm:classOfInstalledObject,
            hqdm:classOfIntentionallyConstructedObject,
            hqdm:classOfOffer,
            hqdm:classOfOrdinaryBiologicalObject,
            hqdm:classOfOrdinaryFunctionalObject,
            hqdm:classOfOrdinaryPhysicalObject,
            hqdm:classOfOrganization,
            hqdm:classOfOrganizationComponent,
            hqdm:classOfParticipant,
            hqdm:classOfParty,
            hqdm:classOfPeriodOfTime,
            hqdm:classOfPerson,
            hqdm:classOfpersonInPosition,
            hqdm:classOfPhysicalObject,
            hqdm:classOfPhysicalProperty,
            hqdm:classOfPhysicalQuantity,
            hqdm:classOfPointInTime,
            hqdm:classOfPosition,
            hqdm:classOfPossibleWorld,
            hqdm:classOfReachingAgreement,
            hqdm:classOfRelationship,
            hqdm:classOfRepresentation,
            hqdm:classOfSalesProductInstance,
            hqdm:classOfSign,
            hqdm:classOfSociallyConstructedActivity,
            hqdm:classOfSociallyConstructedObject,
            hqdm:classOfSpatioTemporalExtent,
            hqdm:classOfState,
            hqdm:classOfStateOfActivity,
            hqdm:classOfStateOfAmountOfMoney,
            hqdm:classOfStateOfAssociation,
            hqdm:classOfStateOfBiologicalObject,
            hqdm:classOfStateOfBiologicalSystem,
            hqdm:classOfStateOfBiologicalSystemComponent,
            hqdm:classOfStateOfFunctionalObject,
            hqdm:classOfStateOfFunctionalSystem,
            hqdm:classOfStateOfFunctionalSystemComponent,
            hqdm:classOfStateOfIntentionallyConstructedObject,
            hqdm:classOfStateOfOrdinaryBiologicalObject,
            hqdm:classOfStateOfOrdinaryFunctionalObject,
            hqdm:classOfStateOfOrdinaryPhysicalObject,
            hqdm:classOfStateOfOrganization,
            hqdm:classOfStateOfOrganizationComponent,
            hqdm:classOfStateOfParty,
            hqdm:classOfStateOfPerson,
            hqdm:classOfStateOfPhysicalObject,
            hqdm:classOfStateOfPosition,
            hqdm:classOfStateOfSalesProductInstance,
            hqdm:classOfStateOfSign,
            hqdm:classOfStateOfSociallyConstructedActivity,
            hqdm:classOfStateOfSociallyConstructedObject,
            hqdm:classOfStateOfSystem,
            hqdm:classOfStateOfSystemComponent,
            hqdm:classOfSystem,
            hqdm:classOfSystemComponent,
            hqdm:kindOfActivity,
            hqdm:kindOfAssociation,
            hqdm:kindOfBiologicalObject,
            hqdm:kindOfBiologicalSystem,
            hqdm:kindOfBiologicalSystemComponent,
            hqdm:kindOfFunctionalObject,
            hqdm:kindOfFunctionalSystem,
            hqdm:kindOfFunctionalSystemComponent,
            hqdm:kindOfIndividual,
            hqdm:kindOfIntentionallyConstructedObject,
            hqdm:kindOfOrdinaryBiologicalObject,
            hqdm:kindOfOrdinaryFunctionalObject,
            hqdm:kindOfOrdinaryPhysicalObject,
            hqdm:kindOfOrganization,
            hqdm:kindOfOrganizationComponent,
            hqdm:kindOfParty,
            hqdm:kindOfPerson,
            hqdm:kindOfPhysicalObject,
            hqdm:kindOfPhysicalProperty,
            hqdm:kindOfPhysicalQuantity,
            hqdm:kindOfPosition,
            hqdm:kindOfRelationshipWithRestriction,
            hqdm:kindOfRelationshipWithSignature,
            hqdm:kindOfSociallyConstructedObject,
            hqdm:kindOfSystem,
            hqdm:kindOfSystemComponent,
            hqdm:role,
            hqdm:pattern,
            hqdm:description,
            hqdm:definition,
            hqdm:identification
            ))
            }
            """;

    private static final String CHECK_MISSING_PARTICIPANT_ROLES = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
            ?s hqdm:errorParticipantWithNoRole "Should be a memberOfKind of a role.".
            ?s a ?type.
            }
            where {
            ?s hqdm:participantIn ?association;
            a ?type.

            ?association a hqdm:association.

            OPTIONAL {
            ?s hqdm:memberOfKind ?kind.
            ?kind a hqdm:role.
            }

            FILTER(!bound(?kind))
            }
            """;

    private static final String CHECK_POSSIBLE_WORLD_MEMBERSHIP = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>
            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

            construct {
            ?s hqdm:errorPossibleWorld "Should be part of a possible world.".
            ?s a ?type.
            }
            where {
            ?s a ?type;
            ?p ?o.
            OPTIONAL {
            ?s hqdm:partOfPossibleWorld ?pw.
            }

            FILTER(!bound(?pw))
            FILTER(?type in (
            hqdm:acceptanceOfOffer,
            hqdm:acceptanceOfOfferForGoods,
            hqdm:activity,
            hqdm:aggregation,
            hqdm:agreeContract,
            hqdm:agreementExecution,
            hqdm:agreementProcess,
            hqdm:amountOfMoney,
            hqdm:asset,
            hqdm:association,
            hqdm:beginningOfOwnership,
            hqdm:biologicalObject,
            hqdm:biologicalSystem,
            hqdm:biologicalSystemComponent,
            hqdm:composition,
            hqdm:contractExecution,
            hqdm:contractProcess,
            hqdm:currency,
            hqdm:employee,
            hqdm:employer,
            hqdm:employment,
            hqdm:endingOfOwnership,
            hqdm:event,
            hqdm:exchangeOfGoodsAndMoney,
            hqdm:function,
            hqdm:functionalObject,
            hqdm:functionalSystem,
            hqdm:functionalSystemComponent,
            hqdm:identificationOfPhysicalQuantity,
            hqdm:individual,
            hqdm:inPlaceBiologicalComponent,
            hqdm:installedFunctionalSystemComponent,
            hqdm:installedObject,
            hqdm:intentionallyConstructedObject,
            hqdm:languageCommunity,
            hqdm:moneyAsset,
            hqdm:offer,
            hqdm:offerAndAcceptanceForGoods,
            hqdm:offerForGoods,
            hqdm:offering,
            hqdm:ordinaryBiologicalObject,
            hqdm:ordinaryFunctionalObject,
            hqdm:ordinaryPhysicalObject,
            hqdm:organization,
            hqdm:organizationComponent,
            hqdm:owner,
            hqdm:ownership,
            hqdm:participant,
            hqdm:participantInActivityOrAssociation,
            hqdm:party,
            hqdm:periodOfTime,
            hqdm:person,
            hqdm:personInPosition,
            hqdm:physicalObject,
            hqdm:physicalProperty,
            hqdm:physicalPropertyRange,
            hqdm:physicalQuantity,
            hqdm:physicalQuantityRange,
            hqdm:plan,
            hqdm:pointInTime,
            hqdm:position,
            hqdm:possibleWorld,
            hqdm:price,
            hqdm:productBrand,
            hqdm:productOffering,
            hqdm:reachingAgreement,
            hqdm:recognizingLanguageCommunity,
            hqdm:relationship,
            hqdm:representationBySign,
            hqdm:requirement,
            hqdm:requirementSpecification,
            hqdm:saleOfGoods,
            hqdm:salesProduct,
            hqdm:salesProductInstance,
            hqdm:salesProductVersion,
            hqdm:scale,
            hqdm:sign,
            hqdm:sociallyConstructedActivity,
            hqdm:sociallyConstructedObject,
            hqdm:spatioTemporalExtent,
            hqdm:state,
            hqdm:stateOfActivity,
            hqdm:stateOfAmountOfMoney,
            hqdm:stateOfAssociation,
            hqdm:stateOfBiologicalObject,
            hqdm:stateOfBiologicalSystem,
            hqdm:stateOfBiologicalSystemComponent,
            hqdm:stateOfFunctionalObject,
            hqdm:stateOfFunctionalSystem,
            hqdm:stateOfFunctionalSystemComponent,
            hqdm:stateOfIntentionallyConstructedObject,
            hqdm:stateOfLanguageCommunity,
            hqdm:stateOfOrdinaryBiologicalObject,
            hqdm:stateOfOrdinaryFunctionalObject,
            hqdm:stateOfOrdinaryPhysicalObject,
            hqdm:stateOfOrganization,
            hqdm:stateOfOrganizationComponent,
            hqdm:stateOfParty,
            hqdm:stateOfPerson,
            hqdm:stateOfPhysicalObject,
            hqdm:stateOfPosition,
            hqdm:stateOfSalesProductInstance,
            hqdm:stateOfSign,
            hqdm:stateOfSociallyConstructedActivity,
            hqdm:stateOfSociallyConstructedObject,
            hqdm:stateOfSystem,
            hqdm:stateOfSystemComponent,
            hqdm:system,
            hqdm:systemComponent,
            hqdm:temporalComposition,
            hqdm:transferee,
            hqdm:transferOfOwnership,
            hqdm:transferOfOwnershipOfMoney,
            hqdm:transferor,
            hqdm:unitOfMeasure
            ))
            }
            """;

    private static final String CHECK_STATE_TEMPORAL_PART_OF = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingTemporalPartOf "Should be a temporalPartOf some individual.".
              ?s a ?type.
            }
            where {
                ?s a ?type;
                   ?p ?o.
                OPTIONAL {
                  ?s hqdm:temporalPartOf ?individual.
                }

              FILTER(!bound(?individual))
              FILTER(?type in (
                    hqdm:stateOfActivity,
                    hqdm:stateOfAmountOfMoney,
                    hqdm:stateOfAssociation,
                    hqdm:stateOfBiologicalObject,
                    hqdm:stateOfBiologicalSystem,
                    hqdm:stateOfBiologicalSystemComponent,
                    hqdm:stateOfFunctionalObject,
                    hqdm:stateOfFunctionalSystem,
                    hqdm:stateOfFunctionalSystemComponent,
                    hqdm:stateOfIntentionallyConstructedObject,
                    hqdm:stateOfLanguageCommunity,
                    hqdm:stateOfOrdinaryBiologicalObject,
                    hqdm:stateOfOrdinaryFunctionalObject,
                    hqdm:stateOfOrdinaryPhysicalObject,
                    hqdm:stateOfOrganization,
                    hqdm:stateOfOrganizationComponent,
                    hqdm:stateOfParty,
                    hqdm:stateOfPerson,
                    hqdm:stateOfPhysicalObject,
                    hqdm:stateOfPosition,
                    hqdm:stateOfSalesProductInstance,
                    hqdm:stateOfSign,
                    hqdm:stateOfSociallyConstructedActivity,
                    hqdm:stateOfSociallyConstructedObject,
                    hqdm:stateOfSystem,
                    hqdm:stateOfSystemComponent
                ))
            }
            """;

    private static final String CHECK_SIGN_VALUE_ = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingValue "Should have a value for a sign.".
              ?s a hqdm:sign.
            }
            where {
                ?s a hqdm:sign;
                   ?p ?o.
                OPTIONAL {
                  ?s skos:definition ?v.
                }

              FILTER(!bound(?v))
            }
            """;

    private static final String CHECK_SIGN_MEMBER_OF_PATTERN = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingMemberOf "Should be a memberOf of some pattern.".
              ?s a hqdm:sign.
            }
            where {
                ?s a hqdm:sign;
                   ?p ?o.
                OPTIONAL {
                  ?s hqdm:memberOf ?pattern.
                }

              FILTER(!bound(?pattern))
            }
            """;

    private static final String CHECK_REP_BY_PATTERN_CONSISTS_OF_BY_CLASS = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingConsistsOfByClass
                  "Should be a target of a consistsOfByClass from Rep By Pattern.".
              ?s a hqdm:pattern.
            }
            where {
                ?s a hqdm:pattern.
                OPTIONAL {
                  ?repByPattern hqdm:consistsOfByClass ?s.
                }

              FILTER(!bound(?repByPattern))
            }
            """;

    private static final String CHECK_REP_BY_SIGN_CONSISTS_OF_COMMUNITY = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingConsistsOf "Should have a consistsOf from Rep By Sign.".
              ?s a hqdm:representationBySign.
            }
            where {
                ?s a hqdm:representationBySign.
                OPTIONAL {
                  ?s hqdm:consistsOf ?community.
                }

              FILTER(!bound(?community))
            }
            """;

    private static final String CHECK_REP_BY_SIGN_CONSISTS_OF_SIGN = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingConsistsOf "Should have a consistsOf from Rep By Sign.".
              ?s a hqdm:representationBySign.
            }
            where {
                ?s a hqdm:representationBySign.
                OPTIONAL {
                  ?s hqdm:consistsOf ?stateOfSign.
                }

              FILTER(!bound(?stateOfSign))
            }
            """;

    private static final String CHECK_REP_BY_PATTERN_CONSISTS_OF_IN_MEMBERS = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingConsistsOfInMembers "Should have a consistsOfInMembers from Rep By Pattern.".
              ?s a ?type.
            }
            where {
                ?s a ?type.
                OPTIONAL {
                  ?s hqdm:consistsOfInMembers ?community.
                }

              FILTER(!bound(?community))
                  FILTER(?type in (
                              hqdm:representationByPattern,
                              hqdm:identification,
                              hqdm:definition,
                              hqdm:description
                              ))
            }
            """;

    private static final String CHECK_REP_BY_SIGN_REPRESENTS = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingRepresents "Should have a represents from Rep By Sign.".
              ?s a hqdm:representationBySign.
            }
            where {
                ?s a hqdm:representationBySign.
                OPTIONAL {
                  ?s hqdm:represents ?thing.
                }

              FILTER(!bound(?thing))
            }
            """;

    private static final String CHECK_STATE_OF_SIGN_PARTICIPANT_IN = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingSignParticipantIn "Should have a participantIn to Rep By Sign.".
              ?s a hqdm:stateOfSign.
            }
            where {
                ?s a hqdm:stateOfSign.
                OPTIONAL {
                  ?s hqdm:participantIn ?repBySign.
                }

              FILTER(!bound(?repBySign))
            }
            """;

    private static final String CHECK_REP_BY_SIGN_MEMBER_OF = """
            PREFIX hqdm: <https://hqdTop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingMemberOf "Should have a memberOf to Rep By Pattern.".
              ?s a hqdm:representationBySign.
            }
            where {
                ?s a hqdm:representationBySign.
                OPTIONAL {
                  ?s hqdm:memberOf ?repByPattern.
                }

              FILTER(!bound(?repByPattern))
            }
            """;

    private static final String CHECK_REP_BY_SIGN_HAS_SIGN_PARTICIPANT = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingSign "Should have a stateOfSign as a participantIn this Rep By Sign.".
              ?s a hqdm:representationBySign.
            }
            where {
                ?s a hqdm:representationBySign.
                OPTIONAL {
                  ?sign hqdm:participantIn ?s;
                        a hqdm:stateOfSign.
                }

              FILTER(!bound(?sign))
            }
            """;

    private static final String CHECK_REP_BY_SIGN_HAS_COMMUNITY_PARTICIPANT = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingCommunity
                  "Should have a recognizingLanguageCommunity as a participantIn this Rep By Sign.".
              ?s a hqdm:representationBySign.
            }
            where {
                ?s a hqdm:representationBySign.
                OPTIONAL {
                  ?community hqdm:participantIn ?s;
                        a hqdm:recognizingLanguageCommunity.
                }

              FILTER(!bound(?community))
            }
            """;

    private static final String CHECK_ROLE_PART_OF_BY_CLASS_ = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingPartOfByClass "Should have a partOfByClass to a kindOfAssociation.".
              ?s a hqdm:role.
            }
            where {
                ?s a hqdm:role.
                OPTIONAL {
                  ?s hqdm:partOfByClass ?kindOfAssociation.
                  ?kindOfAssociation a hqdm:kindOfAssociation.
                }

              FILTER(!bound(?kindOfAssociation))
            }
            """;

    private static final String CHECK_ASSOCIATION_MEMBER_OF_KIND = """
            PREFIX hqdm: <https://hqdmtop.github.io/hqdmOntology/1.0.0#>

            construct {
              ?s hqdm:errorMissingKindOfAssociation "Should have a memberOfKind to a kindOfAssociation.".
              ?s a hqdm:association.
            }
            where {
                ?s a hqdm:association.
                OPTIONAL {
                  ?s hqdm:memberOfKind ?kindOfAssociation.
                  ?kindOfAssociation a hqdm:kindOfAssociation.
                }

              FILTER(!bound(?kindOfAssociation))
            }
            """;

    /**
     * Verify a HQDM Data Model.
     *
     * @param db {@link MagmaCoreDatabase}.
     * @return A {@link List} of {@link Thing} that represent data integrity errors.
     */
    public static List<Thing> verify(final MagmaCoreDatabase db) {
        db.beginRead();

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
        errors.addAll(db.executeConstruct(CHECK_STATE_OF_SIGN_PARTICIPANT_IN));
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
