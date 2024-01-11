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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.extensions.*;
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

    private static List<ExtensionService> providers = null;

    private HqdmObjectFactory() {
    }

    private static List<ExtensionService> getExtensionServices() {
        if (providers == null) {
            providers = new ArrayList<>();

            ServiceLoader
                .load(ExtensionServiceProvider.class)
                .iterator()
                .forEachRemaining(p -> {
                    providers.add(p.createService(iriToClassMap));
                });
        }
        return providers;
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
            final Set<IRI> iris = new HashSet<>();
            for (final Pair<Object, Object> pair : pairs.stream()
                    .filter(pair -> pair.getLeft().equals(RDF_TYPE))
                    .filter(pair -> pair.getRight().toString().startsWith(HQDM.HQDM.getNamespace()))
                    .collect(Collectors.toList())) {
                iris.add((IRI) pair.getRight());
            }

            if (!iris.isEmpty()) {
                final Thing result;

                if (iris.size() == 1) {
                    result = mapToThing(iris.iterator().next().getResource(), iri);
                } else {
                    result = DynamicObjects.create(iri, Thing.class, irisToClasses(iris));
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
    private static <T extends Thing> java.lang.Class<T>[] irisToClasses(final Set<IRI> iris) {
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
                return SpatioTemporalExtentServices.createAbstractObject(iri);
            case "acceptance_of_offer":
                return SpatioTemporalExtentServices.createAcceptanceOfOffer(iri);
            case "acceptance_of_offer_for_goods":
                return SpatioTemporalExtentServices.createAcceptanceOfOfferForGoods(iri);
            case "activity":
                return SpatioTemporalExtentServices.createActivity(iri);
            case "aggregation":
                return RelationshipServices.createAggregation(iri);
            case "agree_contract":
                return SpatioTemporalExtentServices.createAgreeContract(iri);
            case "agreement_execution":
                return SpatioTemporalExtentServices.createAgreementExecution(iri);
            case "agreement_process":
                return SpatioTemporalExtentServices.createAgreementProcess(iri);
            case "amount_of_money":
                return SpatioTemporalExtentServices.createAmountOfMoney(iri);
            case "asset":
                return SpatioTemporalExtentServices.createAsset(iri);
            case "association":
                return SpatioTemporalExtentServices.createAssociation(iri);
            case "beginning_of_ownership":
                return SpatioTemporalExtentServices.createBeginningOfOwnership(iri);
            case "biological_object":
                return SpatioTemporalExtentServices.createBiologicalObject(iri);
            case "biological_system":
                return SpatioTemporalExtentServices.createBiologicalSystem(iri);
            case "biological_system_component":
                return SpatioTemporalExtentServices.createBiologicalSystemComponent(iri);
            case "class":
                return ClassServices.createClass(iri);
            case "classification":
                return RelationshipServices.createClassification(iri);
            case "class_of_abstract_object":
                return ClassServices.createClassOfAbstractObject(iri);
            case "class_of_activity":
                return ClassServices.createClassOfActivity(iri);
            case "class_of_agree_contract":
                return ClassServices.createClassOfAgreeContract(iri);
            case "class_of_agreement_execution":
                return ClassServices.createClassOfAgreementExecution(iri);
            case "class_of_agreement_process":
                return ClassServices.createClassOfAgreementProcess(iri);
            case "class_of_amount_of_money":
                return ClassServices.createClassOfAmountOfMoney(iri);
            case "class_of_association":
                return ClassServices.createClassOfAssociation(iri);
            case "class_of_biological_object":
                return ClassServices.createClassOfBiologicalObject(iri);
            case "class_of_biological_system":
                return ClassServices.createClassOfBiologicalSystem(iri);
            case "class_of_biological_system_component":
                return ClassServices.createClassOfBiologicalSystemComponent(iri);
            case "class_of_class":
                return ClassServices.createClassOfClass(iri);
            case "class_of_class_of_spatio_temporal_extent":
                return ClassServices.createClassOfSpatioTemporalExtent(iri);
            case "class_of_contract_execution":
                return ClassServices.createClassOfContractExecution(iri);
            case "class_of_contract_process":
                return ClassServices.createClassOfContractProcess(iri);
            case "class_of_event":
                return ClassServices.createClassOfEvent(iri);
            case "class_of_functional_object":
                return ClassServices.createClassOfFunctionalObject(iri);
            case "class_of_functional_system":
                return ClassServices.createClassOfFunctionalSystem(iri);
            case "class_of_functional_system_component":
                return ClassServices.createClassOfFunctionalSystemComponent(iri);
            case "class_of_individual":
                return ClassServices.createClassOfIndividual(iri);
            case "class_of_in_place_biological_component":
                return ClassServices.createClassOfInPlaceBiologicalComponent(iri);
            case "class_of_installed_functional_system_component":
                return ClassServices.createClassOfInstalledFunctionalSystemComponent(iri);
            case "class_of_installed_object":
                return ClassServices.createClassOfInstalledObject(iri);
            case "class_of_intentionally_constructed_object":
                return ClassServices.createClassOfIntentionallyConstructedObject(iri);
            case "class_of_offer":
                return ClassServices.createClassOfOffer(iri);
            case "class_of_ordinary_biological_object":
                return ClassServices.createClassOfOrdinaryBiologicalObject(iri);
            case "class_of_ordinary_functional_object":
                return ClassServices.createClassOfOrdinaryFunctionalObject(iri);
            case "class_of_ordinary_physical_object":
                return ClassServices.createClassOfOrdinaryPhysicalObject(iri);
            case "class_of_organization":
                return ClassServices.createClassOfOrganization(iri);
            case "class_of_organization_component":
                return ClassServices.createClassOfOrganizationComponent(iri);
            case "class_of_participant":
                return ClassServices.createClassOfParticipant(iri);
            case "class_of_party":
                return ClassServices.createClassOfParty(iri);
            case "class_of_period_of_time":
                return ClassServices.createClassOfPeriodOfTime(iri);
            case "class_of_person":
                return ClassServices.createClassOfPerson(iri);
            case "class_of_person_in_position":
                return ClassServices.createClassOfPersonInPosition(iri);
            case "class_of_physical_object":
                return ClassServices.createClassOfPhysicalObject(iri);
            case "class_of_physical_property":
                return ClassServices.createClassOfPhysicalProperty(iri);
            case "class_of_physical_quantity":
                return ClassServices.createClassOfPhysicalQuantity(iri);
            case "class_of_point_in_time":
                return ClassServices.createClassOfPointInTime(iri);
            case "class_of_position":
                return ClassServices.createClassOfPosition(iri);
            case "class_of_possible_world":
                return ClassServices.createClassOfPossibleWorld(iri);
            case "class_of_reaching_agreement":
                return ClassServices.createClassOfReachingAgreement(iri);
            case "class_of_relationship":
                return ClassServices.createClassOfRelationship(iri);
            case "class_of_representation":
                return ClassServices.createClassOfRepresentation(iri);
            case "class_of_sales_product_instance":
                return ClassServices.createClassOfSalesProductInstance(iri);
            case "class_of_sign":
                return ClassServices.createClassOfSign(iri);
            case "class_of_socially_constructed_activity":
                return ClassServices.createClassOfSociallyConstructedActivity(iri);
            case "class_of_socially_constructed_object":
                return ClassServices.createClassOfSociallyConstructedObject(iri);
            case "class_of_spatio_temporal_extent":
                return ClassServices.createClassOfSpatioTemporalExtent(iri);
            case "class_of_state":
                return ClassServices.createClassOfState(iri);
            case "class_of_state_of_activity":
                return ClassServices.createClassOfStateOfActivity(iri);
            case "class_of_state_of_amount_of_money":
                return ClassServices.createClassOfStateOfAmountOfMoney(iri);
            case "class_of_state_of_association":
                return ClassServices.createClassOfStateOfAssociation(iri);
            case "class_of_state_of_biological_object":
                return ClassServices.createClassOfStateOfBiologicalObject(iri);
            case "class_of_state_of_biological_system":
                return ClassServices.createClassOfStateOfBiologicalSystem(iri);
            case "class_of_state_of_biological_system_component":
                return ClassServices.createClassOfStateOfBiologicalSystemComponent(iri);
            case "class_of_state_of_functional_object":
                return ClassServices.createClassOfStateOfFunctionalObject(iri);
            case "class_of_state_of_functional_system":
                return ClassServices.createClassOfStateOfFunctionalSystem(iri);
            case "class_of_state_of_functional_system_component":
                return ClassServices.createClassOfStateOfFunctionalSystemComponent(iri);
            case "class_of_state_of_intentionally_constructed_object":
                return ClassServices.createClassOfStateOfIntentionallyConstructedObject(iri);
            case "class_of_state_of_ordinary_biological_object":
                return ClassServices.createClassOfStateOfOrdinaryBiologicalObject(iri);
            case "class_of_state_of_ordinary_functional_object":
                return ClassServices.createClassOfStateOfOrdinaryFunctionalObject(iri);
            case "class_of_state_of_ordinary_physical_object":
                return ClassServices.createClassOfStateOfOrdinaryPhysicalObject(iri);
            case "class_of_state_of_organization":
                return ClassServices.createClassOfStateOfOrganization(iri);
            case "class_of_state_of_organization_component":
                return ClassServices.createClassOfStateOfOrganizationComponent(iri);
            case "class_of_state_of_party":
                return ClassServices.createClassOfStateOfParty(iri);
            case "class_of_state_of_person":
                return ClassServices.createClassOfStateOfPerson(iri);
            case "class_of_state_of_physical_object":
                return ClassServices.createClassOfStateOfPhysicalObject(iri);
            case "class_of_state_of_position":
                return ClassServices.createClassOfStateOfPosition(iri);
            case "class_of_state_of_sales_product_instance":
                return ClassServices.createClassOfStateOfSalesProductInstance(iri);
            case "class_of_state_of_sign":
                return ClassServices.createClassOfStateOfSign(iri);
            case "class_of_state_of_socially_constructed_activity":
                return ClassServices.createClassOfStateOfSociallyConstructedActivity(iri);
            case "class_of_state_of_socially_constructed_object":
                return ClassServices.createClassOfStateOfSociallyConstructedObject(iri);
            case "class_of_state_of_system":
                return ClassServices.createClassOfStateOfSystem(iri);
            case "class_of_state_of_system_component":
                return ClassServices.createClassOfStateOfSystemComponent(iri);
            case "class_of_system":
                return ClassServices.createClassOfSystem(iri);
            case "class_of_system_component":
                return ClassServices.createClassOfSystemComponent(iri);
            case "composition":
                return RelationshipServices.createComposition(iri);
            case "contract_execution":
                return SpatioTemporalExtentServices.createContractExecution(iri);
            case "contract_process":
                return SpatioTemporalExtentServices.createContractProcess(iri);
            case "currency":
                return SpatioTemporalExtentServices.createCurrency(iri);
            case "defined_relationship":
                return RelationshipServices.createDefinedRelationship(iri);
            case "definition":
                return ClassServices.createDefinition(iri);
            case "description":
                return ClassServices.createDescription(iri);
            case "employee":
                return SpatioTemporalExtentServices.createEmployee(iri);
            case "employer":
                return SpatioTemporalExtentServices.createEmployer(iri);
            case "employment":
                return SpatioTemporalExtentServices.createEmployment(iri);
            case "ending_of_ownership":
                return SpatioTemporalExtentServices.createEndingOfOwnership(iri);
            case "enumerated_class":
                return ClassServices.createEnumeratedClass(iri);
            case "event":
                return SpatioTemporalExtentServices.createEvent(iri);
            case "exchange_of_goods_and_money":
                return SpatioTemporalExtentServices.createExchangeOfGoodsAndMoney(iri);
            case "function_":
                return RelationshipServices.createFunction(iri);
            case "functional_object":
                return SpatioTemporalExtentServices.createFunctionalObject(iri);
            case "functional_system":
                return SpatioTemporalExtentServices.createFunctionalSystem(iri);
            case "functional_system_component":
                return SpatioTemporalExtentServices.createFunctionalSystemComponent(iri);
            case "identification":
                return ClassServices.createIdentification(iri);
            case "identification_of_physical_quantity":
                return SpatioTemporalExtentServices.createIdentificationOfPhysicalQuantity(iri);
            case "individual":
                return SpatioTemporalExtentServices.createIndividual(iri);
            case "in_place_biological_component":
                return SpatioTemporalExtentServices.createInPlaceBiologicalComponent(iri);
            case "installed_functional_system_component":
                return SpatioTemporalExtentServices.createInstalledFunctionalSystemComponent(iri);
            case "installed_object":
                return SpatioTemporalExtentServices.createInstalledObject(iri);
            case "intentionally_constructed_object":
                return SpatioTemporalExtentServices.createIntentionallyConstructedObject(iri);
            case "kind_of_activity":
                return ClassServices.createKindOfActivity(iri);
            case "kind_of_association":
                return ClassServices.createKindOfAssociation(iri);
            case "kind_of_biological_object":
                return ClassServices.createKindOfBiologicalObject(iri);
            case "kind_of_biological_system":
                return ClassServices.createKindOfBiologicalSystem(iri);
            case "kind_of_biological_system_component":
                return ClassServices.createKindOfBiologicalSystemComponent(iri);
            case "kind_of_functional_object":
                return ClassServices.createKindOfFunctionalObject(iri);
            case "kind_of_functional_system":
                return ClassServices.createKindOfFunctionalSystem(iri);
            case "kind_of_functional_system_component":
                return ClassServices.createKindOfFunctionalSystemComponent(iri);
            case "kind_of_individual":
                return ClassServices.createKindOfIndividual(iri);
            case "kind_of_intentionally_constructed_object":
                return ClassServices.createKindOfIntentionallyConstructedObject(iri);
            case "kind_of_ordinary_biological_object":
                return ClassServices.createKindOfOrdinaryBiologicalObject(iri);
            case "kind_of_ordinary_functional_object":
                return ClassServices.createKindOfOrdinaryFunctionalObject(iri);
            case "kind_of_ordinary_physical_object":
                return ClassServices.createKindOfOrdinaryPhysicalObject(iri);
            case "kind_of_organization":
                return ClassServices.createKindOfOrganization(iri);
            case "kind_of_organization_component":
                return ClassServices.createKindOfOrganizationComponent(iri);
            case "kind_of_party":
                return ClassServices.createKindOfParty(iri);
            case "kind_of_person":
                return ClassServices.createKindOfPerson(iri);
            case "kind_of_physical_object":
                return ClassServices.createKindOfPhysicalObject(iri);
            case "kind_of_physical_property":
                return ClassServices.createKindOfPhysicalProperty(iri);
            case "kind_of_physical_quantity":
                return ClassServices.createKindOfPhysicalQuantity(iri);
            case "kind_of_position":
                return ClassServices.createKindOfPosition(iri);
            case "kind_of_relationship_with_restriction":
                return ClassServices.createKindOfRelationshipWithRestriction(iri);
            case "kind_of_relationship_with_signature":
                return ClassServices.createKindOfRelationshipWithSignature(iri);
            case "kind_of_socially_constructed_object":
                return ClassServices.createKindOfSociallyConstructedObject(iri);
            case "kind_of_system":
                return ClassServices.createKindOfSystem(iri);
            case "kind_of_system_component":
                return ClassServices.createKindOfSystemComponent(iri);
            case "language_community":
                return SpatioTemporalExtentServices.createLanguageCommunity(iri);
            case "money_asset":
                return SpatioTemporalExtentServices.createMoneyAsset(iri);
            case "offer":
                return SpatioTemporalExtentServices.createOffer(iri);
            case "offer_and_acceptance_for_goods":
                return SpatioTemporalExtentServices.createOfferAndAcceptanceForGoods(iri);
            case "offer_for_goods":
                return SpatioTemporalExtentServices.createOfferForGoods(iri);
            case "offering":
                return SpatioTemporalExtentServices.createOffering(iri);
            case "ordinary_biological_object":
                return SpatioTemporalExtentServices.createOrdinaryBiologicalObject(iri);
            case "ordinary_functional_object":
                return SpatioTemporalExtentServices.createOrdinaryFunctionalObject(iri);
            case "ordinary_physical_object":
                return SpatioTemporalExtentServices.createOrdinaryPhysicalObject(iri);
            case "organization":
                return SpatioTemporalExtentServices.createOrganization(iri);
            case "organization_component":
                return SpatioTemporalExtentServices.createOrganizationComponent(iri);
            case "owner":
                return SpatioTemporalExtentServices.createOwner(iri);
            case "ownership":
                return SpatioTemporalExtentServices.createOwnership(iri);
            case "participant":
                return SpatioTemporalExtentServices.createParticipant(iri);
            case "party":
                return SpatioTemporalExtentServices.createParty(iri);
            case "pattern":
                return ClassServices.createPattern(iri);
            case "period_of_time":
                return SpatioTemporalExtentServices.createPeriodOfTime(iri);
            case "person":
                return SpatioTemporalExtentServices.createPerson(iri);
            case "person_in_position":
                return SpatioTemporalExtentServices.createPersonInPosition(iri);
            case "physical_object":
                return SpatioTemporalExtentServices.createPhysicalObject(iri);
            case "physical_property":
                return SpatioTemporalExtentServices.createPhysicalProperty(iri);
            case "physical_property_range":
                return SpatioTemporalExtentServices.createPhysicalPropertyRange(iri);
            case "physical_quantity":
                return SpatioTemporalExtentServices.createPhysicalQuantity(iri);
            case "physical_quantity_range":
                return SpatioTemporalExtentServices.createPhysicalQuantityRange(iri);
            case "plan":
                return SpatioTemporalExtentServices.createPlan(iri);
            case "point_in_time":
                return SpatioTemporalExtentServices.createPointInTime(iri);
            case "position":
                return SpatioTemporalExtentServices.createPosition(iri);
            case "possible_world":
                return SpatioTemporalExtentServices.createPossibleWorld(iri);
            case "price":
                return SpatioTemporalExtentServices.createPrice(iri);
            case "product_brand":
                return SpatioTemporalExtentServices.createProductBrand(iri);
            case "product_offering":
                return SpatioTemporalExtentServices.createProductOffering(iri);
            case "reaching_agreement":
                return SpatioTemporalExtentServices.createReachingAgreement(iri);
            case "recognizing_language_community":
                return SpatioTemporalExtentServices.createRecognizingLanguageCommunity(iri);
            case "relationship":
                return RelationshipServices.createRelationship(iri);
            case "representation_by_pattern":
                return ClassServices.createRepresentationByPattern(iri);
            case "representation_by_sign":
                return SpatioTemporalExtentServices.createRepresentationBySign(iri);
            case "requirement":
                return SpatioTemporalExtentServices.createRequirement(iri);
            case "requirement_specification":
                return SpatioTemporalExtentServices.createRequirementSpecification(iri);
            case "role":
                return ClassServices.createRole(iri);
            case "sale_of_goods":
                return SpatioTemporalExtentServices.createSaleOfGoods(iri);
            case "sales_product":
                return SpatioTemporalExtentServices.createSalesProduct(iri);
            case "sales_product_instance":
                return SpatioTemporalExtentServices.createSalesProductInstance(iri);
            case "sales_product_version":
                return SpatioTemporalExtentServices.createSalesProductVersion(iri);
            case "scale":
                return RelationshipServices.createScale(iri);
            case "sign":
                return SpatioTemporalExtentServices.createSign(iri);
            case "socially_constructed_activity":
                return SpatioTemporalExtentServices.createSociallyConstructedActivity(iri);
            case "socially_constructed_object":
                return SpatioTemporalExtentServices.createSociallyConstructedObject(iri);
            case "spatio_temporal_extent":
                return SpatioTemporalExtentServices.createSpatioTemporalExtent(iri);
            case "specialization":
                return RelationshipServices.createSpecialization(iri);
            case "state":
                return SpatioTemporalExtentServices.createState(iri);
            case "state_of_activity":
                return SpatioTemporalExtentServices.createStateOfActivity(iri);
            case "state_of_amount_of_money":
                return SpatioTemporalExtentServices.createStateOfAmountOfMoney(iri);
            case "state_of_association":
                return SpatioTemporalExtentServices.createStateOfAssociation(iri);
            case "state_of_biological_object":
                return SpatioTemporalExtentServices.createStateOfBiologicalObject(iri);
            case "state_of_biological_system":
                return SpatioTemporalExtentServices.createStateOfBiologicalSystem(iri);
            case "state_of_biological_system_component":
                return SpatioTemporalExtentServices.createStateOfBiologicalSystemComponent(iri);
            case "state_of_functional_object":
                return SpatioTemporalExtentServices.createStateOfFunctionalObject(iri);
            case "state_of_functional_system":
                return SpatioTemporalExtentServices.createStateOfFunctionalSystem(iri);
            case "state_of_functional_system_component":
                return SpatioTemporalExtentServices.createStateOfFunctionalSystemComponent(iri);
            case "state_of_intentionally_constructed_object":
                return SpatioTemporalExtentServices.createStateOfIntentionallyConstructedObject(iri);
            case "state_of_language_community":
                return SpatioTemporalExtentServices.createStateOfLanguageCommunity(iri);
            case "state_of_ordinary_biological_object":
                return SpatioTemporalExtentServices.createStateOfOrdinaryBiologicalObject(iri);
            case "state_of_ordinary_functional_object":
                return SpatioTemporalExtentServices.createStateOfOrdinaryFunctionalObject(iri);
            case "state_of_ordinary_physical_object":
                return SpatioTemporalExtentServices.createStateOfOrdinaryPhysicalObject(iri);
            case "state_of_organization":
                return SpatioTemporalExtentServices.createStateOfOrganization(iri);
            case "state_of_organization_component":
                return SpatioTemporalExtentServices.createStateOfOrganizationComponent(iri);
            case "state_of_party":
                return SpatioTemporalExtentServices.createStateOfParty(iri);
            case "state_of_person":
                return SpatioTemporalExtentServices.createStateOfPerson(iri);
            case "state_of_physical_object":
                return SpatioTemporalExtentServices.createStateOfPhysicalObject(iri);
            case "state_of_position":
                return SpatioTemporalExtentServices.createStateOfPosition(iri);
            case "state_of_sales_product_instance":
                return SpatioTemporalExtentServices.createStateOfSalesProductInstance(iri);
            case "state_of_sign":
                return SpatioTemporalExtentServices.createStateOfSign(iri);
            case "state_of_socially_constructed_activity":
                return SpatioTemporalExtentServices.createStateOfSociallyConstructedActivity(iri);
            case "state_of_socially_constructed_object":
                return SpatioTemporalExtentServices.createStateOfSociallyConstructedObject(iri);
            case "state_of_system":
                return SpatioTemporalExtentServices.createStateOfSystem(iri);
            case "state_of_system_component":
                return SpatioTemporalExtentServices.createStateOfSystemComponent(iri);
            case "system":
                return SpatioTemporalExtentServices.createSystem(iri);
            case "system_component":
                return SpatioTemporalExtentServices.createSystemComponent(iri);
            case "temporal_composition":
                return RelationshipServices.createTemporalComposition(iri);
            case "thing":
                return SpatioTemporalExtentServices.createThing(iri);
            case "transferee":
                return SpatioTemporalExtentServices.createTransferee(iri);
            case "transfer_of_ownership":
                return SpatioTemporalExtentServices.createTransferOfOwnership(iri);
            case "transfer_of_ownership_of_money":
                return SpatioTemporalExtentServices.createTransferOfOwnershipOfMoney(iri);
            case "transferor":
                return SpatioTemporalExtentServices.createTransferor(iri);
            case "unit_of_measure":
                return RelationshipServices.createUnitOfMeasure(iri);
            case "participant_in_activity_or_association":
            default:
                //
                // Check whether any extensions can handle the type.
                //
                for (final var service : getExtensionServices()) {
                    final Thing t = service.createEntity(typeName, iri);
                    if (t != null) {
                        return t;
                    }
                }
                //
                // We still don't recognise the type so throw an exception.
                //
                throw new HqdmException("Unknown type name: " + typeName);
        }
    }
}
