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

package uk.gov.gchq.magmacore.hqdm.rdf;

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS.RDF_TYPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.*;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.util.Pair;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;
import uk.gov.gchq.magmacore.hqdm.services.DynamicObjects;
import uk.gov.gchq.magmacore.hqdm.services.RelationshipServices;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Object factory for building HQDM Java objects from RDF triples.
 */
public final class HqdmObjectFactory {

    private HqdmObjectFactory() {
    }

    /**
     * Create a new HQDM object from a HQDM entity type and IRI.
     *
     * @param <T>      {@link Thing} or any of its subclasses.
     * @param hqdmType IRI definition of HQDM object type defined in
     *                 {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM}.
     * @param iri      IRI of the object.
     * @return The constructed HQDM object.
     * @throws HqdmException If the HqdmObject could not be built.
     */
    public static <T extends Thing> T create(final HqdmIri hqdmType, final IRI iri) throws HqdmException {
        return (T) mapToThing(hqdmType.getResource(), iri);
    }

    /**
     * Create a HqdmObject from an IRI and list of predicates.
     *
     * @param iri   IRI of the object.
     * @param pairs Object attributes.
     * @return The constructed HQDM object.
     * @throws HqdmException If the HqdmObject could not be built.
     */
    public static Thing create(final IRI iri, final List<Pair<Object, Object>> pairs) throws HqdmException {
        try {
            final List<IRI> iris = new ArrayList<>();
            for (final Pair<Object, Object> pair : pairs.stream()
                    .filter(pair -> pair.getLeft().equals(RDF_TYPE))
                    .filter(pair -> pair.getRight().toString().startsWith(HQDM.HQDM.getNamespace()))
                    .collect(Collectors.toList())) {
                iris.add((IRI) pair.getRight());
            }

            if (!iris.isEmpty()) {
                final Thing result;

                if (iris.size() == 1) {
                    result = mapToThing(iris.get(0).getResource(), iri);
                } else {
                    result = DynamicObjects.create(iri.toString(), Thing.class, irisToClasses(iris));
                }

                for (final Pair<Object, Object> pair : pairs) {
                    if (pair.getRight() instanceof IRI) {
                        result.addValue(pair.getLeft(), pair.getRight());
                    } else {
                        result.addStringValue(pair.getLeft(), pair.getRight().toString());
                    }
                }
                return result;
            } else {
                throw new HqdmException("No type information for: " + iri);
            }
        } catch (final Exception ex) {
            throw new HqdmException(ex);
        }
    }

    /**
     * Convert a list of IRI Strings to class names.
     *
     * @param iris List of {@link IRI}.
     * @return Array of Class.
     */
    private static <T extends Thing> java.lang.Class<T>[] irisToClasses(final List<IRI> iris) {
        final List<java.lang.Class<? extends Thing>> classes = new ArrayList<>(3);

        // It will be a small list so just iterate it.
        for (final IRI iri : iris) {
            classes.add(iriToClassMap.getOrDefault(iri, Thing.class));
        }

        return (java.lang.Class<T>[]) classes.toArray(new java.lang.Class<?>[] {});
    }

    // A statically initialized Map of IRIs to HQDM classes.
    private static final Map<IRI, java.lang.Class<? extends Thing>> iriToClassMap = new HashMap<>(250);

    static {
        iriToClassMap.put(HQDM.ABSTRACT_OBJECT, AbstractObject.class);
        iriToClassMap.put(HQDM.ACCEPTANCE_OF_OFFER, AcceptanceOfOffer.class);
        iriToClassMap.put(HQDM.ACCEPTANCE_OF_OFFER_FOR_GOODS, AcceptanceOfOfferForGoods.class);
        iriToClassMap.put(HQDM.ACTIVITY, Activity.class);
        iriToClassMap.put(HQDM.AGGREGATION, Aggregation.class);
        iriToClassMap.put(HQDM.AGREE_CONTRACT, AgreeContract.class);
        iriToClassMap.put(HQDM.AGREEMENT_EXECUTION, AgreementExecution.class);
        iriToClassMap.put(HQDM.AGREEMENT_PROCESS, AgreementProcess.class);
        iriToClassMap.put(HQDM.AMOUNT_OF_MONEY, AmountOfMoney.class);
        iriToClassMap.put(HQDM.ASSET, Asset.class);
        iriToClassMap.put(HQDM.ASSOCIATION, Association.class);
        iriToClassMap.put(HQDM.BEGINNING_OF_OWNERSHIP, BeginningOfOwnership.class);
        iriToClassMap.put(HQDM.BIOLOGICAL_OBJECT, BiologicalObject.class);
        iriToClassMap.put(HQDM.BIOLOGICAL_SYSTEM, BiologicalSystem.class);
        iriToClassMap.put(HQDM.BIOLOGICAL_SYSTEM_COMPONENT, BiologicalSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS, uk.gov.gchq.magmacore.hqdm.model.Class.class);
        iriToClassMap.put(HQDM.CLASSIFICATION, Classification.class);
        iriToClassMap.put(HQDM.CLASS_OF_ABSTRACT_OBJECT, ClassOfAbstractObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_ACTIVITY, ClassOfActivity.class);
        iriToClassMap.put(HQDM.CLASS_OF_AGREE_CONTRACT, ClassOfAgreeContract.class);
        iriToClassMap.put(HQDM.CLASS_OF_AGREEMENT_EXECUTION, ClassOfAgreementExecution.class);
        iriToClassMap.put(HQDM.CLASS_OF_AGREEMENT_PROCESS, ClassOfAgreementProcess.class);
        iriToClassMap.put(HQDM.CLASS_OF_AMOUNT_OF_MONEY, ClassOfAmountOfMoney.class);
        iriToClassMap.put(HQDM.CLASS_OF_ASSOCIATION, ClassOfAssociation.class);
        iriToClassMap.put(HQDM.CLASS_OF_BIOLOGICAL_OBJECT, ClassOfBiologicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_BIOLOGICAL_SYSTEM, ClassOfBiologicalSystem.class);
        iriToClassMap.put(HQDM.CLASS_OF_BIOLOGICAL_SYSTEM_COMPONENT, ClassOfBiologicalSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_CLASS, ClassOfClass.class);
        iriToClassMap.put(HQDM.CLASS_OF_CLASS_OF_SPATIO_TEMPORAL_EXTENT, ClassOfClassOfSpatioTemporalExtent.class);
        iriToClassMap.put(HQDM.CLASS_OF_CONTRACT_EXECUTION, ClassOfContractExecution.class);
        iriToClassMap.put(HQDM.CLASS_OF_CONTRACT_PROCESS, ClassOfContractProcess.class);
        iriToClassMap.put(HQDM.CLASS_OF_EVENT, ClassOfEvent.class);
        iriToClassMap.put(HQDM.CLASS_OF_FUNCTIONAL_OBJECT, ClassOfFunctionalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_FUNCTIONAL_SYSTEM, ClassOfFunctionalSystem.class);
        iriToClassMap.put(HQDM.CLASS_OF_FUNCTIONAL_SYSTEM_COMPONENT, ClassOfFunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_INDIVIDUAL, ClassOfIndividual.class);
        iriToClassMap.put(HQDM.CLASS_OF_IN_PLACE_BIOLOGICAL_COMPONENT, ClassOfInPlaceBiologicalComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT,
                ClassOfInstalledFunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_INSTALLED_OBJECT, ClassOfInstalledObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_INTENTIONALLY_CONSTRUCTED_OBJECT, ClassOfIntentionallyConstructedObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_OFFER, ClassOfOffer.class);
        iriToClassMap.put(HQDM.CLASS_OF_ORDINARY_BIOLOGICAL_OBJECT, ClassOfOrdinaryBiologicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_ORDINARY_FUNCTIONAL_OBJECT, ClassOfOrdinaryFunctionalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_ORDINARY_PHYSICAL_OBJECT, ClassOfOrdinaryPhysicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_ORGANIZATION, ClassOfOrganization.class);
        iriToClassMap.put(HQDM.CLASS_OF_ORGANIZATION_COMPONENT, ClassOfOrganizationComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_PARTICIPANT, ClassOfParticipant.class);
        iriToClassMap.put(HQDM.CLASS_OF_PARTY, ClassOfParty.class);
        iriToClassMap.put(HQDM.CLASS_OF_PERIOD_OF_TIME, ClassOfPeriodOfTime.class);
        iriToClassMap.put(HQDM.CLASS_OF_PERSON, ClassOfPerson.class);
        iriToClassMap.put(HQDM.CLASS_OF_PERSON_IN_POSITION, ClassOfPersonInPosition.class);
        iriToClassMap.put(HQDM.CLASS_OF_PHYSICAL_OBJECT, ClassOfPhysicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_PHYSICAL_PROPERTY, ClassOfPhysicalProperty.class);
        iriToClassMap.put(HQDM.CLASS_OF_PHYSICAL_QUANTITY, ClassOfPhysicalQuantity.class);
        iriToClassMap.put(HQDM.CLASS_OF_POINT_IN_TIME, ClassOfPointInTime.class);
        iriToClassMap.put(HQDM.CLASS_OF_POSITION, ClassOfPosition.class);
        iriToClassMap.put(HQDM.CLASS_OF_POSSIBLE_WORLD, ClassOfPossibleWorld.class);
        iriToClassMap.put(HQDM.CLASS_OF_REACHING_AGREEMENT, ClassOfReachingAgreement.class);
        iriToClassMap.put(HQDM.CLASS_OF_RELATIONSHIP, ClassOfRelationship.class);
        iriToClassMap.put(HQDM.CLASS_OF_REPRESENTATION, ClassOfRepresentation.class);
        iriToClassMap.put(HQDM.CLASS_OF_SALES_PRODUCT_INSTANCE, ClassOfSalesProductInstance.class);
        iriToClassMap.put(HQDM.CLASS_OF_SIGN, ClassOfSign.class);
        iriToClassMap.put(HQDM.CLASS_OF_SOCIALLY_CONSTRUCTED_ACTIVITY, ClassOfSociallyConstructedActivity.class);
        iriToClassMap.put(HQDM.CLASS_OF_SOCIALLY_CONSTRUCTED_OBJECT, ClassOfSociallyConstructedObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_SPATIO_TEMPORAL_EXTENT, ClassOfSpatioTemporalExtent.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE, ClassOfState.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ACTIVITY, ClassOfStateOfActivity.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_AMOUNT_OF_MONEY, ClassOfStateOfAmountOfMoney.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ASSOCIATION, ClassOfStateOfAssociation.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_BIOLOGICAL_OBJECT, ClassOfStateOfBiologicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_BIOLOGICAL_SYSTEM, ClassOfStateOfBiologicalSystem.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT,
                ClassOfStateOfBiologicalSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_OBJECT, ClassOfStateOfFunctionalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM, ClassOfStateOfFunctionalSystem.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT,
                ClassOfStateOfFunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT,
                ClassOfStateOfIntentionallyConstructedObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ORDINARY_BIOLOGICAL_OBJECT,
                ClassOfStateOfOrdinaryBiologicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ORDINARY_FUNCTIONAL_OBJECT,
                ClassOfStateOfOrdinaryFunctionalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ORDINARY_PHYSICAL_OBJECT, ClassOfStateOfOrdinaryPhysicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ORGANIZATION, ClassOfStateOfOrganization.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_ORGANIZATION_COMPONENT, ClassOfStateOfOrganizationComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_PARTY, ClassOfStateOfParty.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_PERSON, ClassOfStateOfPerson.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_PHYSICAL_OBJECT, ClassOfStateOfPhysicalObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_POSITION, ClassOfStateOfPosition.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_SALES_PRODUCT_INSTANCE, ClassOfStateOfSalesProductInstance.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_SIGN, ClassOfStateOfSign.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY,
                ClassOfStateOfSociallyConstructedActivity.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT,
                ClassOfStateOfSociallyConstructedObject.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_SYSTEM, ClassOfStateOfSystem.class);
        iriToClassMap.put(HQDM.CLASS_OF_STATE_OF_SYSTEM_COMPONENT, ClassOfStateOfSystemComponent.class);
        iriToClassMap.put(HQDM.CLASS_OF_SYSTEM, ClassOfSystem.class);
        iriToClassMap.put(HQDM.CLASS_OF_SYSTEM_COMPONENT, ClassOfSystemComponent.class);
        iriToClassMap.put(HQDM.COMPOSITION, Composition.class);
        iriToClassMap.put(HQDM.CONTRACT_EXECUTION, ContractExecution.class);
        iriToClassMap.put(HQDM.CONTRACT_PROCESS, ContractProcess.class);
        iriToClassMap.put(HQDM.CURRENCY, Currency.class);
        iriToClassMap.put(HQDM.DEFINED_RELATIONSHIP, DefinedRelationship.class);
        iriToClassMap.put(HQDM.DEFINITION, Definition.class);
        iriToClassMap.put(HQDM.DESCRIPTION, Description.class);
        iriToClassMap.put(HQDM.EMPLOYEE, Employee.class);
        iriToClassMap.put(HQDM.EMPLOYER, Employer.class);
        iriToClassMap.put(HQDM.EMPLOYMENT, Employment.class);
        iriToClassMap.put(HQDM.ENDING_OF_OWNERSHIP, EndingOfOwnership.class);
        iriToClassMap.put(HQDM.ENUMERATED_CLASS, EnumeratedClass.class);
        iriToClassMap.put(HQDM.EVENT, Event.class);
        iriToClassMap.put(HQDM.EXCHANGE_OF_GOODS_AND_MONEY, ExchangeOfGoodsAndMoney.class);
        iriToClassMap.put(HQDM.FUNCTION_, Function_.class);
        iriToClassMap.put(HQDM.FUNCTIONAL_OBJECT, FunctionalObject.class);
        iriToClassMap.put(HQDM.FUNCTIONAL_SYSTEM, FunctionalSystem.class);
        iriToClassMap.put(HQDM.FUNCTIONAL_SYSTEM_COMPONENT, FunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.IDENTIFICATION, Identification.class);
        iriToClassMap.put(HQDM.IDENTIFICATION_OF_PHYSICAL_QUANTITY, IdentificationOfPhysicalQuantity.class);
        iriToClassMap.put(HQDM.INDIVIDUAL, Individual.class);
        iriToClassMap.put(HQDM.IN_PLACE_BIOLOGICAL_COMPONENT, InPlaceBiologicalComponent.class);
        iriToClassMap.put(HQDM.INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT, InstalledFunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.INSTALLED_OBJECT, InstalledObject.class);
        iriToClassMap.put(HQDM.INTENTIONALLY_CONSTRUCTED_OBJECT, IntentionallyConstructedObject.class);
        iriToClassMap.put(HQDM.KIND_OF_ACTIVITY, KindOfActivity.class);
        iriToClassMap.put(HQDM.KIND_OF_ASSOCIATION, KindOfAssociation.class);
        iriToClassMap.put(HQDM.KIND_OF_BIOLOGICAL_OBJECT, KindOfBiologicalObject.class);
        iriToClassMap.put(HQDM.KIND_OF_BIOLOGICAL_SYSTEM, KindOfBiologicalSystem.class);
        iriToClassMap.put(HQDM.KIND_OF_BIOLOGICAL_SYSTEM_COMPONENT, KindOfBiologicalSystemComponent.class);
        iriToClassMap.put(HQDM.KIND_OF_FUNCTIONAL_OBJECT, KindOfFunctionalObject.class);
        iriToClassMap.put(HQDM.KIND_OF_FUNCTIONAL_SYSTEM, KindOfFunctionalSystem.class);
        iriToClassMap.put(HQDM.KIND_OF_FUNCTIONAL_SYSTEM_COMPONENT, KindOfFunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.KIND_OF_INDIVIDUAL, KindOfIndividual.class);
        iriToClassMap.put(HQDM.KIND_OF_INTENTIONALLY_CONSTRUCTED_OBJECT, KindOfIntentionallyConstructedObject.class);
        iriToClassMap.put(HQDM.KIND_OF_ORDINARY_BIOLOGICAL_OBJECT, KindOfOrdinaryBiologicalObject.class);
        iriToClassMap.put(HQDM.KIND_OF_ORDINARY_FUNCTIONAL_OBJECT, KindOfOrdinaryFunctionalObject.class);
        iriToClassMap.put(HQDM.KIND_OF_ORDINARY_PHYSICAL_OBJECT, KindOfOrdinaryPhysicalObject.class);
        iriToClassMap.put(HQDM.KIND_OF_ORGANIZATION, KindOfOrganization.class);
        iriToClassMap.put(HQDM.KIND_OF_ORGANIZATION_COMPONENT, KindOfOrganizationComponent.class);
        iriToClassMap.put(HQDM.KIND_OF_PARTY, KindOfParty.class);
        iriToClassMap.put(HQDM.KIND_OF_PERSON, KindOfPerson.class);
        iriToClassMap.put(HQDM.KIND_OF_PHYSICAL_OBJECT, KindOfPhysicalObject.class);
        iriToClassMap.put(HQDM.KIND_OF_PHYSICAL_PROPERTY, KindOfPhysicalProperty.class);
        iriToClassMap.put(HQDM.KIND_OF_PHYSICAL_QUANTITY, KindOfPhysicalQuantity.class);
        iriToClassMap.put(HQDM.KIND_OF_POSITION, KindOfPosition.class);
        iriToClassMap.put(HQDM.KIND_OF_RELATIONSHIP_WITH_RESTRICTION, KindOfRelationshipWithRestriction.class);
        iriToClassMap.put(HQDM.KIND_OF_RELATIONSHIP_WITH_SIGNATURE, KindOfRelationshipWithSignature.class);
        iriToClassMap.put(HQDM.KIND_OF_SOCIALLY_CONSTRUCTED_OBJECT, KindOfSociallyConstructedObject.class);
        iriToClassMap.put(HQDM.KIND_OF_SYSTEM, KindOfSystem.class);
        iriToClassMap.put(HQDM.KIND_OF_SYSTEM_COMPONENT, KindOfSystemComponent.class);
        iriToClassMap.put(HQDM.LANGUAGE_COMMUNITY, LanguageCommunity.class);
        iriToClassMap.put(HQDM.MONEY_ASSET, MoneyAsset.class);
        iriToClassMap.put(HQDM.OFFER, Offer.class);
        iriToClassMap.put(HQDM.OFFER_AND_ACCEPTANCE_FOR_GOODS, OfferAndAcceptanceForGoods.class);
        iriToClassMap.put(HQDM.OFFER_FOR_GOODS, OfferForGoods.class);
        iriToClassMap.put(HQDM.OFFERING, Offering.class);
        iriToClassMap.put(HQDM.ORDINARY_BIOLOGICAL_OBJECT, OrdinaryBiologicalObject.class);
        iriToClassMap.put(HQDM.ORDINARY_FUNCTIONAL_OBJECT, OrdinaryFunctionalObject.class);
        iriToClassMap.put(HQDM.ORDINARY_PHYSICAL_OBJECT, OrdinaryPhysicalObject.class);
        iriToClassMap.put(HQDM.ORGANIZATION, Organization.class);
        iriToClassMap.put(HQDM.ORGANIZATION_COMPONENT, OrganizationComponent.class);
        iriToClassMap.put(HQDM.OWNER, Owner.class);
        iriToClassMap.put(HQDM.OWNERSHIP, Ownership.class);
        iriToClassMap.put(HQDM.PARTICIPANT, Participant.class);
        // iriToClassMap.put(HQDM.PARTICIPANT_IN_ACTIVITY_OR_ASSOCIATION,
        // ParticipantInActivityOrAssociation.class);
        iriToClassMap.put(HQDM.PARTY, Party.class);
        iriToClassMap.put(HQDM.PATTERN, Pattern.class);
        iriToClassMap.put(HQDM.PERIOD_OF_TIME, PeriodOfTime.class);
        iriToClassMap.put(HQDM.PERSON, Person.class);
        iriToClassMap.put(HQDM.PERSON_IN_POSITION, PersonInPosition.class);
        iriToClassMap.put(HQDM.PHYSICAL_OBJECT, PhysicalObject.class);
        iriToClassMap.put(HQDM.PHYSICAL_PROPERTY, PhysicalProperty.class);
        iriToClassMap.put(HQDM.PHYSICAL_PROPERTY_RANGE, PhysicalPropertyRange.class);
        iriToClassMap.put(HQDM.PHYSICAL_QUANTITY, PhysicalQuantity.class);
        iriToClassMap.put(HQDM.PHYSICAL_QUANTITY_RANGE, PhysicalQuantityRange.class);
        iriToClassMap.put(HQDM.PLAN, Plan.class);
        iriToClassMap.put(HQDM.POINT_IN_TIME, PointInTime.class);
        iriToClassMap.put(HQDM.POSITION, Position.class);
        iriToClassMap.put(HQDM.POSSIBLE_WORLD, PossibleWorld.class);
        iriToClassMap.put(HQDM.PRICE, Price.class);
        iriToClassMap.put(HQDM.PRODUCT_BRAND, ProductBrand.class);
        iriToClassMap.put(HQDM.PRODUCT_OFFERING, ProductOffering.class);
        iriToClassMap.put(HQDM.REACHING_AGREEMENT, ReachingAgreement.class);
        iriToClassMap.put(HQDM.RECOGNIZING_LANGUAGE_COMMUNITY, RecognizingLanguageCommunity.class);
        iriToClassMap.put(HQDM.RELATIONSHIP, Relationship.class);
        iriToClassMap.put(HQDM.REPRESENTATION_BY_PATTERN, RepresentationByPattern.class);
        iriToClassMap.put(HQDM.REPRESENTATION_BY_SIGN, RepresentationBySign.class);
        iriToClassMap.put(HQDM.REQUIREMENT, Requirement.class);
        iriToClassMap.put(HQDM.REQUIREMENT_SPECIFICATION, RequirementSpecification.class);
        iriToClassMap.put(HQDM.ROLE, Role.class);
        iriToClassMap.put(HQDM.SALE_OF_GOODS, SaleOfGoods.class);
        iriToClassMap.put(HQDM.SALES_PRODUCT, SalesProduct.class);
        iriToClassMap.put(HQDM.SALES_PRODUCT_INSTANCE, SalesProductInstance.class);
        iriToClassMap.put(HQDM.SALES_PRODUCT_VERSION, SalesProductVersion.class);
        iriToClassMap.put(HQDM.SCALE, Scale.class);
        iriToClassMap.put(HQDM.SIGN, Sign.class);
        iriToClassMap.put(HQDM.SOCIALLY_CONSTRUCTED_ACTIVITY, SociallyConstructedActivity.class);
        iriToClassMap.put(HQDM.SOCIALLY_CONSTRUCTED_OBJECT, SociallyConstructedObject.class);
        iriToClassMap.put(HQDM.SPATIO_TEMPORAL_EXTENT, SpatioTemporalExtent.class);
        iriToClassMap.put(HQDM.SPECIALIZATION, Specialization.class);
        iriToClassMap.put(HQDM.STATE, State.class);
        iriToClassMap.put(HQDM.STATE_OF_ACTIVITY, StateOfActivity.class);
        iriToClassMap.put(HQDM.STATE_OF_AMOUNT_OF_MONEY, StateOfAmountOfMoney.class);
        iriToClassMap.put(HQDM.STATE_OF_ASSOCIATION, StateOfAssociation.class);
        iriToClassMap.put(HQDM.STATE_OF_BIOLOGICAL_OBJECT, StateOfBiologicalObject.class);
        iriToClassMap.put(HQDM.STATE_OF_BIOLOGICAL_SYSTEM, StateOfBiologicalSystem.class);
        iriToClassMap.put(HQDM.STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT, StateOfBiologicalSystemComponent.class);
        iriToClassMap.put(HQDM.STATE_OF_FUNCTIONAL_OBJECT, StateOfFunctionalObject.class);
        iriToClassMap.put(HQDM.STATE_OF_FUNCTIONAL_SYSTEM, StateOfFunctionalSystem.class);
        iriToClassMap.put(HQDM.STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT, StateOfFunctionalSystemComponent.class);
        iriToClassMap.put(HQDM.STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT, StateOfIntentionallyConstructedObject.class);
        iriToClassMap.put(HQDM.STATE_OF_LANGUAGE_COMMUNITY, StateOfLanguageCommunity.class);
        iriToClassMap.put(HQDM.STATE_OF_ORDINARY_BIOLOGICAL_OBJECT, StateOfOrdinaryBiologicalObject.class);
        iriToClassMap.put(HQDM.STATE_OF_ORDINARY_FUNCTIONAL_OBJECT, StateOfOrdinaryFunctionalObject.class);
        iriToClassMap.put(HQDM.STATE_OF_ORDINARY_PHYSICAL_OBJECT, StateOfOrdinaryPhysicalObject.class);
        iriToClassMap.put(HQDM.STATE_OF_ORGANIZATION, StateOfOrganization.class);
        iriToClassMap.put(HQDM.STATE_OF_ORGANIZATION_COMPONENT, StateOfOrganizationComponent.class);
        iriToClassMap.put(HQDM.STATE_OF_PARTY, StateOfParty.class);
        iriToClassMap.put(HQDM.STATE_OF_PERSON, StateOfPerson.class);
        iriToClassMap.put(HQDM.STATE_OF_PHYSICAL_OBJECT, StateOfPhysicalObject.class);
        iriToClassMap.put(HQDM.STATE_OF_POSITION, StateOfPosition.class);
        iriToClassMap.put(HQDM.STATE_OF_SALES_PRODUCT_INSTANCE, StateOfSalesProductInstance.class);
        iriToClassMap.put(HQDM.STATE_OF_SIGN, StateOfSign.class);
        iriToClassMap.put(HQDM.STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY, StateOfSociallyConstructedActivity.class);
        iriToClassMap.put(HQDM.STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT, StateOfSociallyConstructedObject.class);
        iriToClassMap.put(HQDM.STATE_OF_SYSTEM, StateOfSystem.class);
        iriToClassMap.put(HQDM.STATE_OF_SYSTEM_COMPONENT, StateOfSystemComponent.class);
        iriToClassMap.put(HQDM.SYSTEM, uk.gov.gchq.magmacore.hqdm.model.System.class);
        iriToClassMap.put(HQDM.SYSTEM_COMPONENT, SystemComponent.class);
        iriToClassMap.put(HQDM.TEMPORAL_COMPOSITION, TemporalComposition.class);
        iriToClassMap.put(HQDM.THING, Thing.class);
        iriToClassMap.put(HQDM.TRANSFEREE, Transferee.class);
        iriToClassMap.put(HQDM.TRANSFER_OF_OWNERSHIP, TransferOfOwnership.class);
        iriToClassMap.put(HQDM.TRANSFER_OF_OWNERSHIP_OF_MONEY, TransferOfOwnershipOfMoney.class);
        iriToClassMap.put(HQDM.TRANSFEROR, Transferor.class);
        iriToClassMap.put(HQDM.UNIT_OF_MEASURE, UnitOfMeasure.class);
    }

    /**
     * Create a {@link Thing} of the specified type.
     *
     * @param typeName The HQDM type name, e.g. spatio_temporal_extent
     * @param iri      The {@link IRI} of the object.
     * @return A {@link Thing}.
     * @throws HqdmException If the typeName is invalid.
     */
    private static Thing mapToThing(final String typeName, final IRI iri) {

        switch (typeName) {
            case "abstract_object":
                return SpatioTemporalExtentServices.createAbstractObject(iri.getIri());
            case "acceptance_of_offer":
                return SpatioTemporalExtentServices.createAcceptanceOfOffer(iri.getIri());
            case "acceptance_of_offer_for_goods":
                return SpatioTemporalExtentServices.createAcceptanceOfOfferForGoods(iri.getIri());
            case "activity":
                return SpatioTemporalExtentServices.createActivity(iri.getIri());
            case "aggregation":
                return RelationshipServices.createAggregation(iri.getIri());
            case "agree_contract":
                return SpatioTemporalExtentServices.createAgreeContract(iri.getIri());
            case "agreement_execution":
                return SpatioTemporalExtentServices.createAgreementExecution(iri.getIri());
            case "agreement_process":
                return SpatioTemporalExtentServices.createAgreementProcess(iri.getIri());
            case "amount_of_money":
                return SpatioTemporalExtentServices.createAmountOfMoney(iri.getIri());
            case "asset":
                return SpatioTemporalExtentServices.createAsset(iri.getIri());
            case "association":
                return SpatioTemporalExtentServices.createAssociation(iri.getIri());
            case "beginning_of_ownership":
                return SpatioTemporalExtentServices.createBeginningOfOwnership(iri.getIri());
            case "biological_object":
                return SpatioTemporalExtentServices.createBiologicalObject(iri.getIri());
            case "biological_system":
                return SpatioTemporalExtentServices.createBiologicalSystem(iri.getIri());
            case "biological_system_component":
                return SpatioTemporalExtentServices.createBiologicalSystemComponent(iri.getIri());
            case "class":
                return ClassServices.createClass(iri.getIri());
            case "classification":
                return RelationshipServices.createClassification(iri.getIri());
            case "class_of_abstract_object":
                return ClassServices.createClassOfAbstractObject(iri.getIri());
            case "class_of_activity":
                return ClassServices.createClassOfActivity(iri.getIri());
            case "class_of_agree_contract":
                return ClassServices.createClassOfAgreeContract(iri.getIri());
            case "class_of_agreement_execution":
                return ClassServices.createClassOfAgreementExecution(iri.getIri());
            case "class_of_agreement_process":
                return ClassServices.createClassOfAgreementProcess(iri.getIri());
            case "class_of_amount_of_money":
                return ClassServices.createClassOfAmountOfMoney(iri.getIri());
            case "class_of_association":
                return ClassServices.createClassOfAssociation(iri.getIri());
            case "class_of_biological_object":
                return ClassServices.createClassOfBiologicalObject(iri.getIri());
            case "class_of_biological_system":
                return ClassServices.createClassOfBiologicalSystem(iri.getIri());
            case "class_of_biological_system_component":
                return ClassServices.createClassOfBiologicalSystemComponent(iri.getIri());
            case "class_of_class":
                return ClassServices.createClassOfClass(iri.getIri());
            case "class_of_class_of_spatio_temporal_extent":
                return ClassServices.createClassOfSpatioTemporalExtent(iri.getIri());
            case "class_of_contract_execution":
                return ClassServices.createClassOfContractExecution(iri.getIri());
            case "class_of_contract_process":
                return ClassServices.createClassOfContractProcess(iri.getIri());
            case "class_of_event":
                return ClassServices.createClassOfEvent(iri.getIri());
            case "class_of_functional_object":
                return ClassServices.createClassOfFunctionalObject(iri.getIri());
            case "class_of_functional_system":
                return ClassServices.createClassOfFunctionalSystem(iri.getIri());
            case "class_of_functional_system_component":
                return ClassServices.createClassOfFunctionalSystemComponent(iri.getIri());
            case "class_of_individual":
                return ClassServices.createClassOfIndividual(iri.getIri());
            case "class_of_in_place_biological_component":
                return ClassServices.createClassOfInPlaceBiologicalComponent(iri.getIri());
            case "class_of_installed_functional_system_component":
                return ClassServices.createClassOfInstalledFunctionalSystemComponent(iri.getIri());
            case "class_of_installed_object":
                return ClassServices.createClassOfInstalledObject(iri.getIri());
            case "class_of_intentionally_constructed_object":
                return ClassServices.createClassOfIntentionallyConstructedObject(iri.getIri());
            case "class_of_offer":
                return ClassServices.createClassOfOffer(iri.getIri());
            case "class_of_ordinary_biological_object":
                return ClassServices.createClassOfOrdinaryBiologicalObject(iri.getIri());
            case "class_of_ordinary_functional_object":
                return ClassServices.createClassOfOrdinaryFunctionalObject(iri.getIri());
            case "class_of_ordinary_physical_object":
                return ClassServices.createClassOfOrdinaryPhysicalObject(iri.getIri());
            case "class_of_organization":
                return ClassServices.createClassOfOrganization(iri.getIri());
            case "class_of_organization_component":
                return ClassServices.createClassOfOrganizationComponent(iri.getIri());
            case "class_of_participant":
                return ClassServices.createClassOfParticipant(iri.getIri());
            case "class_of_party":
                return ClassServices.createClassOfParty(iri.getIri());
            case "class_of_period_of_time":
                return ClassServices.createClassOfPeriodOfTime(iri.getIri());
            case "class_of_person":
                return ClassServices.createClassOfPerson(iri.getIri());
            case "class_of_person_in_position":
                return ClassServices.createClassOfPersonInPosition(iri.getIri());
            case "class_of_physical_object":
                return ClassServices.createClassOfPhysicalObject(iri.getIri());
            case "class_of_physical_property":
                return ClassServices.createClassOfPhysicalProperty(iri.getIri());
            case "class_of_physical_quantity":
                return ClassServices.createClassOfPhysicalQuantity(iri.getIri());
            case "class_of_point_in_time":
                return ClassServices.createClassOfPointInTime(iri.getIri());
            case "class_of_position":
                return ClassServices.createClassOfPosition(iri.getIri());
            case "class_of_possible_world":
                return ClassServices.createClassOfPossibleWorld(iri.getIri());
            case "class_of_reaching_agreement":
                return ClassServices.createClassOfReachingAgreement(iri.getIri());
            case "class_of_relationship":
                return ClassServices.createClassOfRelationship(iri.getIri());
            case "class_of_representation":
                return ClassServices.createClassOfRepresentation(iri.getIri());
            case "class_of_sales_product_instance":
                return ClassServices.createClassOfSalesProductInstance(iri.getIri());
            case "class_of_sign":
                return ClassServices.createClassOfSign(iri.getIri());
            case "class_of_socially_constructed_activity":
                return ClassServices.createClassOfSociallyConstructedActivity(iri.getIri());
            case "class_of_socially_constructed_object":
                return ClassServices.createClassOfSociallyConstructedObject(iri.getIri());
            case "class_of_spatio_temporal_extent":
                return ClassServices.createClassOfSpatioTemporalExtent(iri.getIri());
            case "class_of_state":
                return ClassServices.createClassOfState(iri.getIri());
            case "class_of_state_of_activity":
                return ClassServices.createClassOfStateOfActivity(iri.getIri());
            case "class_of_state_of_amount_of_money":
                return ClassServices.createClassOfStateOfAmountOfMoney(iri.getIri());
            case "class_of_state_of_association":
                return ClassServices.createClassOfStateOfAssociation(iri.getIri());
            case "class_of_state_of_biological_object":
                return ClassServices.createClassOfStateOfBiologicalObject(iri.getIri());
            case "class_of_state_of_biological_system":
                return ClassServices.createClassOfStateOfBiologicalSystem(iri.getIri());
            case "class_of_state_of_biological_system_component":
                return ClassServices.createClassOfStateOfBiologicalSystemComponent(iri.getIri());
            case "class_of_state_of_functional_object":
                return ClassServices.createClassOfStateOfFunctionalObject(iri.getIri());
            case "class_of_state_of_functional_system":
                return ClassServices.createClassOfStateOfFunctionalSystem(iri.getIri());
            case "class_of_state_of_functional_system_component":
                return ClassServices.createClassOfStateOfFunctionalSystemComponent(iri.getIri());
            case "class_of_state_of_intentionally_constructed_object":
                return ClassServices.createClassOfStateOfIntentionallyConstructedObject(iri.getIri());
            case "class_of_state_of_ordinary_biological_object":
                return ClassServices.createClassOfStateOfOrdinaryBiologicalObject(iri.getIri());
            case "class_of_state_of_ordinary_functional_object":
                return ClassServices.createClassOfStateOfOrdinaryFunctionalObject(iri.getIri());
            case "class_of_state_of_ordinary_physical_object":
                return ClassServices.createClassOfStateOfOrdinaryPhysicalObject(iri.getIri());
            case "class_of_state_of_organization":
                return ClassServices.createClassOfStateOfOrganization(iri.getIri());
            case "class_of_state_of_organization_component":
                return ClassServices.createClassOfStateOfOrganizationComponent(iri.getIri());
            case "class_of_state_of_party":
                return ClassServices.createClassOfStateOfParty(iri.getIri());
            case "class_of_state_of_person":
                return ClassServices.createClassOfStateOfPerson(iri.getIri());
            case "class_of_state_of_physical_object":
                return ClassServices.createClassOfStateOfPhysicalObject(iri.getIri());
            case "class_of_state_of_position":
                return ClassServices.createClassOfStateOfPosition(iri.getIri());
            case "class_of_state_of_sales_product_instance":
                return ClassServices.createClassOfStateOfSalesProductInstance(iri.getIri());
            case "class_of_state_of_sign":
                return ClassServices.createClassOfStateOfSign(iri.getIri());
            case "class_of_state_of_socially_constructed_activity":
                return ClassServices.createClassOfStateOfSociallyConstructedActivity(iri.getIri());
            case "class_of_state_of_socially_constructed_object":
                return ClassServices.createClassOfStateOfSociallyConstructedObject(iri.getIri());
            case "class_of_state_of_system":
                return ClassServices.createClassOfStateOfSystem(iri.getIri());
            case "class_of_state_of_system_component":
                return ClassServices.createClassOfStateOfSystemComponent(iri.getIri());
            case "class_of_system":
                return ClassServices.createClassOfSystem(iri.getIri());
            case "class_of_system_component":
                return ClassServices.createClassOfSystemComponent(iri.getIri());
            case "composition":
                return RelationshipServices.createComposition(iri.getIri());
            case "contract_execution":
                return SpatioTemporalExtentServices.createContractExecution(iri.getIri());
            case "contract_process":
                return SpatioTemporalExtentServices.createContractProcess(iri.getIri());
            case "currency":
                return SpatioTemporalExtentServices.createCurrency(iri.getIri());
            case "defined_relationship":
                return RelationshipServices.createDefinedRelationship(iri.getIri());
            case "definition":
                return SpatioTemporalExtentServices.createDefinition(iri.getIri());
            case "description":
                return SpatioTemporalExtentServices.createDescription(iri.getIri());
            case "employee":
                return SpatioTemporalExtentServices.createEmployee(iri.getIri());
            case "employer":
                return SpatioTemporalExtentServices.createEmployer(iri.getIri());
            case "employment":
                return SpatioTemporalExtentServices.createEmployment(iri.getIri());
            case "ending_of_ownership":
                return SpatioTemporalExtentServices.createEndingOfOwnership(iri.getIri());
            case "event":
                return SpatioTemporalExtentServices.createEvent(iri.getIri());
            case "exchange_of_goods_and_money":
                return SpatioTemporalExtentServices.createExchangeOfGoodsAndMoney(iri.getIri());
            case "function_":
                return RelationshipServices.createFunction(iri.getIri());
            case "functional_object":
                return SpatioTemporalExtentServices.createFunctionalObject(iri.getIri());
            case "functional_system":
                return SpatioTemporalExtentServices.createFunctionalSystem(iri.getIri());
            case "functional_system_component":
                return SpatioTemporalExtentServices.createFunctionalSystemComponent(iri.getIri());
            case "identification":
                return SpatioTemporalExtentServices.createIdentification(iri.getIri());
            case "identification_of_physical_quantity":
                return SpatioTemporalExtentServices.createIdentificationOfPhysicalQuantity(iri.getIri());
            case "individual":
                return SpatioTemporalExtentServices.createIndividual(iri.getIri());
            case "in_place_biological_component":
                return SpatioTemporalExtentServices.createInPlaceBiologicalComponent(iri.getIri());
            case "installed_functional_system_component":
                return SpatioTemporalExtentServices.createInstalledFunctionalSystemComponent(iri.getIri());
            case "installed_object":
                return SpatioTemporalExtentServices.createInstalledObject(iri.getIri());
            case "intentionally_constructed_object":
                return SpatioTemporalExtentServices.createIntentionallyConstructedObject(iri.getIri());
            case "kind_of_activity":
                return ClassServices.createKindOfActivity(iri.getIri());
            case "kind_of_association":
                return ClassServices.createKindOfAssociation(iri.getIri());
            case "kind_of_biological_object":
                return ClassServices.createKindOfBiologicalObject(iri.getIri());
            case "kind_of_biological_system":
                return ClassServices.createKindOfBiologicalSystem(iri.getIri());
            case "kind_of_biological_system_component":
                return ClassServices.createKindOfBiologicalSystemComponent(iri.getIri());
            case "kind_of_functional_object":
                return ClassServices.createKindOfFunctionalObject(iri.getIri());
            case "kind_of_functional_system":
                return ClassServices.createKindOfFunctionalSystem(iri.getIri());
            case "kind_of_functional_system_component":
                return ClassServices.createKindOfFunctionalSystemComponent(iri.getIri());
            case "kind_of_individual":
                return ClassServices.createKindOfIndividual(iri.getIri());
            case "kind_of_intentionally_constructed_object":
                return ClassServices.createKindOfIntentionallyConstructedObject(iri.getIri());
            case "kind_of_ordinary_biological_object":
                return ClassServices.createKindOfOrdinaryBiologicalObject(iri.getIri());
            case "kind_of_ordinary_functional_object":
                return ClassServices.createKindOfOrdinaryFunctionalObject(iri.getIri());
            case "kind_of_ordinary_physical_object":
                return ClassServices.createKindOfOrdinaryPhysicalObject(iri.getIri());
            case "kind_of_organization":
                return ClassServices.createKindOfOrganization(iri.getIri());
            case "kind_of_organization_component":
                return ClassServices.createKindOfOrganizationComponent(iri.getIri());
            case "kind_of_party":
                return ClassServices.createKindOfParty(iri.getIri());
            case "kind_of_person":
                return ClassServices.createKindOfPerson(iri.getIri());
            case "kind_of_physical_object":
                return ClassServices.createKindOfPhysicalObject(iri.getIri());
            case "kind_of_physical_property":
                return ClassServices.createKindOfPhysicalProperty(iri.getIri());
            case "kind_of_physical_quantity":
                return ClassServices.createKindOfPhysicalQuantity(iri.getIri());
            case "kind_of_position":
                return ClassServices.createKindOfPosition(iri.getIri());
            case "kind_of_relationship_with_restriction":
                return ClassServices.createKindOfRelationshipWithRestriction(iri.getIri());
            case "kind_of_relationship_with_signature":
                return ClassServices.createKindOfRelationshipWithSignature(iri.getIri());
            case "kind_of_socially_constructed_object":
                return ClassServices.createKindOfSociallyConstructedObject(iri.getIri());
            case "kind_of_system":
                return ClassServices.createKindOfSystem(iri.getIri());
            case "kind_of_system_component":
                return ClassServices.createKindOfSystemComponent(iri.getIri());
            case "language_community":
                return SpatioTemporalExtentServices.createLanguageCommunity(iri.getIri());
            case "money_asset":
                return SpatioTemporalExtentServices.createMoneyAsset(iri.getIri());
            case "offer":
                return SpatioTemporalExtentServices.createOffer(iri.getIri());
            case "offer_and_acceptance_for_goods":
                return SpatioTemporalExtentServices.createOfferAndAcceptanceForGoods(iri.getIri());
            case "offer_for_goods":
                return SpatioTemporalExtentServices.createOfferForGoods(iri.getIri());
            case "offering":
                return SpatioTemporalExtentServices.createOffering(iri.getIri());
            case "ordinary_biological_object":
                return SpatioTemporalExtentServices.createOrdinaryBiologicalObject(iri.getIri());
            case "ordinary_functional_object":
                return SpatioTemporalExtentServices.createOrdinaryFunctionalObject(iri.getIri());
            case "ordinary_physical_object":
                return SpatioTemporalExtentServices.createOrdinaryPhysicalObject(iri.getIri());
            case "organization":
                return SpatioTemporalExtentServices.createOrganization(iri.getIri());
            case "organization_component":
                return SpatioTemporalExtentServices.createOrganizationComponent(iri.getIri());
            case "owner":
                return SpatioTemporalExtentServices.createOwner(iri.getIri());
            case "ownership":
                return SpatioTemporalExtentServices.createOwnership(iri.getIri());
            case "participant":
                return SpatioTemporalExtentServices.createParticipant(iri.getIri());
            case "party":
                return SpatioTemporalExtentServices.createParty(iri.getIri());
            case "pattern":
                return SpatioTemporalExtentServices.createPattern(iri.getIri());
            case "period_of_time":
                return SpatioTemporalExtentServices.createPeriodOfTime(iri.getIri());
            case "person":
                return SpatioTemporalExtentServices.createPerson(iri.getIri());
            case "person_in_position":
                return SpatioTemporalExtentServices.createPersonInPosition(iri.getIri());
            case "physical_object":
                return SpatioTemporalExtentServices.createPhysicalObject(iri.getIri());
            case "physical_property":
                return SpatioTemporalExtentServices.createPhysicalProperty(iri.getIri());
            case "physical_property_range":
                return SpatioTemporalExtentServices.createPhysicalPropertyRange(iri.getIri());
            case "physical_quantity":
                return SpatioTemporalExtentServices.createPhysicalQuantity(iri.getIri());
            case "physical_quantity_range":
                return SpatioTemporalExtentServices.createPhysicalQuantityRange(iri.getIri());
            case "plan":
                return SpatioTemporalExtentServices.createPlan(iri.getIri());
            case "point_in_time":
                return SpatioTemporalExtentServices.createPointInTime(iri.getIri());
            case "position":
                return SpatioTemporalExtentServices.createPosition(iri.getIri());
            case "possible_world":
                return SpatioTemporalExtentServices.createPossibleWorld(iri.getIri());
            case "price":
                return SpatioTemporalExtentServices.createPrice(iri.getIri());
            case "product_brand":
                return SpatioTemporalExtentServices.createProductBrand(iri.getIri());
            case "product_offering":
                return SpatioTemporalExtentServices.createProductOffering(iri.getIri());
            case "reaching_agreement":
                return SpatioTemporalExtentServices.createReachingAgreement(iri.getIri());
            case "recognizing_language_community":
                return SpatioTemporalExtentServices.createRecognizingLanguageCommunity(iri.getIri());
            case "relationship":
                return RelationshipServices.createRelationship(iri.getIri());
            case "representation_by_pattern":
                return SpatioTemporalExtentServices.createRepresentationByPattern(iri.getIri());
            case "representation_by_sign":
                return SpatioTemporalExtentServices.createRepresentationBySign(iri.getIri());
            case "requirement":
                return SpatioTemporalExtentServices.createRequirement(iri.getIri());
            case "requirement_specification":
                return SpatioTemporalExtentServices.createRequirementSpecification(iri.getIri());
            case "role":
                return ClassServices.createRole(iri.getIri());
            case "sale_of_goods":
                return SpatioTemporalExtentServices.createSaleOfGoods(iri.getIri());
            case "sales_product":
                return SpatioTemporalExtentServices.createSalesProduct(iri.getIri());
            case "sales_product_instance":
                return SpatioTemporalExtentServices.createSalesProductInstance(iri.getIri());
            case "sales_product_version":
                return SpatioTemporalExtentServices.createSalesProductVersion(iri.getIri());
            case "scale":
                return RelationshipServices.createScale(iri.getIri());
            case "sign":
                return SpatioTemporalExtentServices.createSign(iri.getIri());
            case "socially_constructed_activity":
                return SpatioTemporalExtentServices.createSociallyConstructedActivity(iri.getIri());
            case "socially_constructed_object":
                return SpatioTemporalExtentServices.createSociallyConstructedObject(iri.getIri());
            case "spatio_temporal_extent":
                return SpatioTemporalExtentServices.createSpatioTemporalExtent(iri.getIri());
            case "specialization":
                return RelationshipServices.createSpecialization(iri.getIri());
            case "state":
                return SpatioTemporalExtentServices.createState(iri.getIri());
            case "state_of_activity":
                return SpatioTemporalExtentServices.createStateOfActivity(iri.getIri());
            case "state_of_amount_of_money":
                return SpatioTemporalExtentServices.createStateOfAmountOfMoney(iri.getIri());
            case "state_of_association":
                return SpatioTemporalExtentServices.createStateOfAssociation(iri.getIri());
            case "state_of_biological_object":
                return SpatioTemporalExtentServices.createStateOfBiologicalObject(iri.getIri());
            case "state_of_biological_system":
                return SpatioTemporalExtentServices.createStateOfBiologicalSystem(iri.getIri());
            case "state_of_biological_system_component":
                return SpatioTemporalExtentServices.createStateOfBiologicalSystemComponent(iri.getIri());
            case "state_of_functional_object":
                return SpatioTemporalExtentServices.createStateOfFunctionalObject(iri.getIri());
            case "state_of_functional_system":
                return SpatioTemporalExtentServices.createStateOfFunctionalSystem(iri.getIri());
            case "state_of_functional_system_component":
                return SpatioTemporalExtentServices.createStateOfFunctionalSystemComponent(iri.getIri());
            case "state_of_intentionally_constructed_object":
                return SpatioTemporalExtentServices.createStateOfIntentionallyConstructedObject(iri.getIri());
            case "state_of_language_community":
                return SpatioTemporalExtentServices.createStateOfLanguageCommunity(iri.getIri());
            case "state_of_ordinary_biological_object":
                return SpatioTemporalExtentServices.createStateOfOrdinaryBiologicalObject(iri.getIri());
            case "state_of_ordinary_functional_object":
                return SpatioTemporalExtentServices.createStateOfOrdinaryFunctionalObject(iri.getIri());
            case "state_of_ordinary_physical_object":
                return SpatioTemporalExtentServices.createStateOfOrdinaryPhysicalObject(iri.getIri());
            case "state_of_organization":
                return SpatioTemporalExtentServices.createStateOfOrganization(iri.getIri());
            case "state_of_organization_component":
                return SpatioTemporalExtentServices.createStateOfOrganizationComponent(iri.getIri());
            case "state_of_party":
                return SpatioTemporalExtentServices.createStateOfParty(iri.getIri());
            case "state_of_person":
                return SpatioTemporalExtentServices.createStateOfPerson(iri.getIri());
            case "state_of_physical_object":
                return SpatioTemporalExtentServices.createStateOfPhysicalObject(iri.getIri());
            case "state_of_position":
                return SpatioTemporalExtentServices.createStateOfPosition(iri.getIri());
            case "state_of_sales_product_instance":
                return SpatioTemporalExtentServices.createStateOfSalesProductInstance(iri.getIri());
            case "state_of_sign":
                return SpatioTemporalExtentServices.createStateOfSign(iri.getIri());
            case "state_of_socially_constructed_activity":
                return SpatioTemporalExtentServices.createStateOfSociallyConstructedActivity(iri.getIri());
            case "state_of_socially_constructed_object":
                return SpatioTemporalExtentServices.createStateOfSociallyConstructedObject(iri.getIri());
            case "state_of_system":
                return SpatioTemporalExtentServices.createStateOfSystem(iri.getIri());
            case "state_of_system_component":
                return SpatioTemporalExtentServices.createStateOfSystemComponent(iri.getIri());
            case "system":
                return SpatioTemporalExtentServices.createSystem(iri.getIri());
            case "system_component":
                return SpatioTemporalExtentServices.createSystemComponent(iri.getIri());
            case "temporal_composition":
                return RelationshipServices.createTemporalComposition(iri.getIri());
            case "thing":
                return SpatioTemporalExtentServices.createThing(iri.getIri());
            case "transferee":
                return SpatioTemporalExtentServices.createTransferee(iri.getIri());
            case "transfer_of_ownership":
                return SpatioTemporalExtentServices.createTransferOfOwnership(iri.getIri());
            case "transfer_of_ownership_of_money":
                return SpatioTemporalExtentServices.createTransferOfOwnershipOfMoney(iri.getIri());
            case "transferor":
                return SpatioTemporalExtentServices.createTransferor(iri.getIri());
            case "unit_of_measure":
                return RelationshipServices.createUnitOfMeasure(iri.getIri());
            case "enumerated_class":
            case "participant_in_activity_or_association":
            default:
                throw new HqdmException("Unknown type name: " + typeName);
        }
    }
}
