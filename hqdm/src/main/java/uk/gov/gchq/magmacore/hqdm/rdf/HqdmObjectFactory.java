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
import static uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Function;
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
    @SuppressWarnings("unchecked")
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
    public static Thing create(final IRI iri, final List<Pair<IRI, Object>> pairs) throws HqdmException {
        try {
            final Set<IRI> iris = new HashSet<>();
            for (final Pair<IRI, Object> pair : pairs.stream()
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

                for (final Pair<IRI, Object> pair : pairs) {
                    if (pair.getRight() instanceof IRI i) {
                        result.addValue(pair.getLeft(), i);
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
    @SuppressWarnings("unchecked")
    private static <T extends Thing> java.lang.Class<T>[] irisToClasses(final Set<IRI> iris) {
        final Set<java.lang.Class<? extends Thing>> classes = new HashSet<>(3);

        // It will be a small list so just iterate it.
        for (final IRI iri : iris) {
            classes.add(iriToClassMap.getOrDefault(iri, Thing.class));
        }

        return (java.lang.Class<T>[]) classes.toArray(new java.lang.Class<?>[] {});
    }

    // A statically initialized Map of IRIs to HQDM classes.
    private static final Map<IRI, java.lang.Class<? extends Thing>> iriToClassMap = new HashMap<>(400);

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
        // Get a method to create a new Thing of the right type.
        return factoryMethods.getOrDefault(typeName, 
            // If no method then return a lambda that will search for extensions.
            (ir) -> {
                return findExtendedTypes(typeName, ir);
            })
        // Call the method to create the new entity.
        .apply(iri);
    }

    /**
     * Search extension libraries for the named type.
     *
     * @param typeName String type name.
     * @param iri {@link IRI}
     * @return {@link Thing}
     */
    private static Thing findExtendedTypes(final String typeName, final IRI iri) {
        // Check whether any extensions can handle the type.
        for (final var service : getExtensionServices()) {
            final Thing t = service.createEntity(typeName, iri);
            if (t != null) {
                return t;
            }
        }
        // We still don't recognise the type so just create a Thing to represent it.
        return createThing(iri);
    }

    // A Map of type names to factoryMethods that create instances of the type.
    private static Map<String, Function<IRI, Thing>> factoryMethods = new HashMap<>();

    static {
        //
        // Populate a map of type names to factory methods.
        //
        factoryMethods.put("abstract_object", SpatioTemporalExtentServices::createAbstractObject);
        factoryMethods.put("acceptance_of_offer", SpatioTemporalExtentServices::createAcceptanceOfOffer);
        factoryMethods.put("acceptance_of_offer_for_goods", 
                SpatioTemporalExtentServices::createAcceptanceOfOfferForGoods);
        factoryMethods.put("activity", SpatioTemporalExtentServices::createActivity);
        factoryMethods.put("aggregation", RelationshipServices::createAggregation);
        factoryMethods.put("agree_contract", SpatioTemporalExtentServices::createAgreeContract);
        factoryMethods.put("agreement_execution", SpatioTemporalExtentServices::createAgreementExecution);
        factoryMethods.put("agreement_process", SpatioTemporalExtentServices::createAgreementProcess);
        factoryMethods.put("amount_of_money", SpatioTemporalExtentServices::createAmountOfMoney);
        factoryMethods.put("asset", SpatioTemporalExtentServices::createAsset);
        factoryMethods.put("association", SpatioTemporalExtentServices::createAssociation);
        factoryMethods.put("beginning_of_ownership", SpatioTemporalExtentServices::createBeginningOfOwnership);
        factoryMethods.put("biological_object", SpatioTemporalExtentServices::createBiologicalObject);
        factoryMethods.put("biological_system", SpatioTemporalExtentServices::createBiologicalSystem);
        factoryMethods.put("biological_system_component", 
                SpatioTemporalExtentServices::createBiologicalSystemComponent);
        factoryMethods.put("class", ClassServices::createClass);
        factoryMethods.put("classification", RelationshipServices::createClassification);
        factoryMethods.put("class_of_abstract_object", ClassServices::createClassOfAbstractObject);
        factoryMethods.put("class_of_activity", ClassServices::createClassOfActivity);
        factoryMethods.put("class_of_agree_contract", ClassServices::createClassOfAgreeContract);
        factoryMethods.put("class_of_agreement_execution", ClassServices::createClassOfAgreementExecution);
        factoryMethods.put("class_of_agreement_process", ClassServices::createClassOfAgreementProcess);
        factoryMethods.put("class_of_amount_of_money", ClassServices::createClassOfAmountOfMoney);
        factoryMethods.put("class_of_association", ClassServices::createClassOfAssociation);
        factoryMethods.put("class_of_biological_object", ClassServices::createClassOfBiologicalObject);
        factoryMethods.put("class_of_biological_system", ClassServices::createClassOfBiologicalSystem);
        factoryMethods.put("class_of_biological_system_component", 
                ClassServices::createClassOfBiologicalSystemComponent);
        factoryMethods.put("class_of_class", ClassServices::createClassOfClass);
        factoryMethods.put("class_of_class_of_spatio_temporal_extent", 
                ClassServices::createClassOfSpatioTemporalExtent);
        factoryMethods.put("class_of_contract_execution", ClassServices::createClassOfContractExecution);
        factoryMethods.put("class_of_contract_process", ClassServices::createClassOfContractProcess);
        factoryMethods.put("class_of_event", ClassServices::createClassOfEvent);
        factoryMethods.put("class_of_functional_object", ClassServices::createClassOfFunctionalObject);
        factoryMethods.put("class_of_functional_system", ClassServices::createClassOfFunctionalSystem);
        factoryMethods.put("class_of_functional_system_component", 
                ClassServices::createClassOfFunctionalSystemComponent);
        factoryMethods.put("class_of_individual", ClassServices::createClassOfIndividual);
        factoryMethods.put("class_of_in_place_biological_component", 
                ClassServices::createClassOfInPlaceBiologicalComponent);
        factoryMethods.put("class_of_installed_functional_system_component", 
                ClassServices::createClassOfInstalledFunctionalSystemComponent);
        factoryMethods.put("class_of_installed_object", ClassServices::createClassOfInstalledObject);
        factoryMethods.put("class_of_intentionally_constructed_object", 
                ClassServices::createClassOfIntentionallyConstructedObject);
        factoryMethods.put("class_of_offer", ClassServices::createClassOfOffer);
        factoryMethods.put("class_of_ordinary_biological_object", ClassServices::createClassOfOrdinaryBiologicalObject);
        factoryMethods.put("class_of_ordinary_functional_object", ClassServices::createClassOfOrdinaryFunctionalObject);
        factoryMethods.put("class_of_ordinary_physical_object", ClassServices::createClassOfOrdinaryPhysicalObject);
        factoryMethods.put("class_of_organization", ClassServices::createClassOfOrganization);
        factoryMethods.put("class_of_organization_component", ClassServices::createClassOfOrganizationComponent);
        factoryMethods.put("class_of_participant", ClassServices::createClassOfParticipant);
        factoryMethods.put("class_of_party", ClassServices::createClassOfParty);
        factoryMethods.put("class_of_period_of_time", ClassServices::createClassOfPeriodOfTime);
        factoryMethods.put("class_of_person", ClassServices::createClassOfPerson);
        factoryMethods.put("class_of_person_in_position", ClassServices::createClassOfPersonInPosition);
        factoryMethods.put("class_of_physical_object", ClassServices::createClassOfPhysicalObject);
        factoryMethods.put("class_of_physical_property", ClassServices::createClassOfPhysicalProperty);
        factoryMethods.put("class_of_physical_quantity", ClassServices::createClassOfPhysicalQuantity);
        factoryMethods.put("class_of_point_in_time", ClassServices::createClassOfPointInTime);
        factoryMethods.put("class_of_position", ClassServices::createClassOfPosition);
        factoryMethods.put("class_of_possible_world", ClassServices::createClassOfPossibleWorld);
        factoryMethods.put("class_of_reaching_agreement", ClassServices::createClassOfReachingAgreement);
        factoryMethods.put("class_of_relationship", ClassServices::createClassOfRelationship);
        factoryMethods.put("class_of_representation", ClassServices::createClassOfRepresentation);
        factoryMethods.put("class_of_sales_product_instance", ClassServices::createClassOfSalesProductInstance);
        factoryMethods.put("class_of_sign", ClassServices::createClassOfSign);
        factoryMethods.put("class_of_socially_constructed_activity", 
                ClassServices::createClassOfSociallyConstructedActivity);
        factoryMethods.put("class_of_socially_constructed_object", 
                ClassServices::createClassOfSociallyConstructedObject);
        factoryMethods.put("class_of_spatio_temporal_extent", ClassServices::createClassOfSpatioTemporalExtent);
        factoryMethods.put("class_of_state", ClassServices::createClassOfState);
        factoryMethods.put("class_of_state_of_activity", ClassServices::createClassOfStateOfActivity);
        factoryMethods.put("class_of_state_of_amount_of_money", ClassServices::createClassOfStateOfAmountOfMoney);
        factoryMethods.put("class_of_state_of_association", ClassServices::createClassOfStateOfAssociation);
        factoryMethods.put("class_of_state_of_biological_object", ClassServices::createClassOfStateOfBiologicalObject);
        factoryMethods.put("class_of_state_of_biological_system", ClassServices::createClassOfStateOfBiologicalSystem);
        factoryMethods.put("class_of_state_of_biological_system_component", 
                ClassServices::createClassOfStateOfBiologicalSystemComponent);
        factoryMethods.put("class_of_state_of_functional_object", ClassServices::createClassOfStateOfFunctionalObject);
        factoryMethods.put("class_of_state_of_functional_system", ClassServices::createClassOfStateOfFunctionalSystem);
        factoryMethods.put("class_of_state_of_functional_system_component", 
                ClassServices::createClassOfStateOfFunctionalSystemComponent);
        factoryMethods.put("class_of_state_of_intentionally_constructed_object", 
                ClassServices::createClassOfStateOfIntentionallyConstructedObject);
        factoryMethods.put("class_of_state_of_ordinary_biological_object", 
                ClassServices::createClassOfStateOfOrdinaryBiologicalObject);
        factoryMethods.put("class_of_state_of_ordinary_functional_object", 
                ClassServices::createClassOfStateOfOrdinaryFunctionalObject);
        factoryMethods.put("class_of_state_of_ordinary_physical_object", 
                ClassServices::createClassOfStateOfOrdinaryPhysicalObject);
        factoryMethods.put("class_of_state_of_organization", ClassServices::createClassOfStateOfOrganization);
        factoryMethods.put("class_of_state_of_organization_component", 
                ClassServices::createClassOfStateOfOrganizationComponent);
        factoryMethods.put("class_of_state_of_party", ClassServices::createClassOfStateOfParty);
        factoryMethods.put("class_of_state_of_person", ClassServices::createClassOfStateOfPerson);
        factoryMethods.put("class_of_state_of_physical_object", ClassServices::createClassOfStateOfPhysicalObject);
        factoryMethods.put("class_of_state_of_position", ClassServices::createClassOfStateOfPosition);
        factoryMethods.put("class_of_state_of_sales_product_instance", 
                ClassServices::createClassOfStateOfSalesProductInstance);
        factoryMethods.put("class_of_state_of_sign", ClassServices::createClassOfStateOfSign);
        factoryMethods.put("class_of_state_of_socially_constructed_activity", 
                ClassServices::createClassOfStateOfSociallyConstructedActivity);
        factoryMethods.put("class_of_state_of_socially_constructed_object", 
                ClassServices::createClassOfStateOfSociallyConstructedObject);
        factoryMethods.put("class_of_state_of_system", ClassServices::createClassOfStateOfSystem);
        factoryMethods.put("class_of_state_of_system_component", ClassServices::createClassOfStateOfSystemComponent);
        factoryMethods.put("class_of_system", ClassServices::createClassOfSystem);
        factoryMethods.put("class_of_system_component", ClassServices::createClassOfSystemComponent);
        factoryMethods.put("composition", RelationshipServices::createComposition);
        factoryMethods.put("contract_execution", SpatioTemporalExtentServices::createContractExecution);
        factoryMethods.put("contract_process", SpatioTemporalExtentServices::createContractProcess);
        factoryMethods.put("currency", SpatioTemporalExtentServices::createCurrency);
        factoryMethods.put("defined_relationship", RelationshipServices::createDefinedRelationship);
        factoryMethods.put("definition", ClassServices::createDefinition);
        factoryMethods.put("description", ClassServices::createDescription);
        factoryMethods.put("employee", SpatioTemporalExtentServices::createEmployee);
        factoryMethods.put("employer", SpatioTemporalExtentServices::createEmployer);
        factoryMethods.put("employment", SpatioTemporalExtentServices::createEmployment);
        factoryMethods.put("ending_of_ownership", SpatioTemporalExtentServices::createEndingOfOwnership);
        factoryMethods.put("enumerated_class", ClassServices::createEnumeratedClass);
        factoryMethods.put("event", SpatioTemporalExtentServices::createEvent);
        factoryMethods.put("exchange_of_goods_and_money", SpatioTemporalExtentServices::createExchangeOfGoodsAndMoney);
        factoryMethods.put("function_", RelationshipServices::createFunction);
        factoryMethods.put("functional_object", SpatioTemporalExtentServices::createFunctionalObject);
        factoryMethods.put("functional_system", SpatioTemporalExtentServices::createFunctionalSystem);
        factoryMethods.put("functional_system_component", 
                SpatioTemporalExtentServices::createFunctionalSystemComponent);
        factoryMethods.put("identification", ClassServices::createIdentification);
        factoryMethods.put("identification_of_physical_quantity", 
                SpatioTemporalExtentServices::createIdentificationOfPhysicalQuantity);
        factoryMethods.put("individual", SpatioTemporalExtentServices::createIndividual);
        factoryMethods.put("in_place_biological_component", 
                SpatioTemporalExtentServices::createInPlaceBiologicalComponent);
        factoryMethods.put("installed_functional_system_component", 
                SpatioTemporalExtentServices::createInstalledFunctionalSystemComponent);
        factoryMethods.put("installed_object", SpatioTemporalExtentServices::createInstalledObject);
        factoryMethods.put("intentionally_constructed_object", 
                SpatioTemporalExtentServices::createIntentionallyConstructedObject);
        factoryMethods.put("kind_of_activity", ClassServices::createKindOfActivity);
        factoryMethods.put("kind_of_association", ClassServices::createKindOfAssociation);
        factoryMethods.put("kind_of_biological_object", ClassServices::createKindOfBiologicalObject);
        factoryMethods.put("kind_of_biological_system", ClassServices::createKindOfBiologicalSystem);
        factoryMethods.put("kind_of_biological_system_component", ClassServices::createKindOfBiologicalSystemComponent);
        factoryMethods.put("kind_of_functional_object", ClassServices::createKindOfFunctionalObject);
        factoryMethods.put("kind_of_functional_system", ClassServices::createKindOfFunctionalSystem);
        factoryMethods.put("kind_of_functional_system_component", ClassServices::createKindOfFunctionalSystemComponent);
        factoryMethods.put("kind_of_individual", ClassServices::createKindOfIndividual);
        factoryMethods.put("kind_of_intentionally_constructed_object", 
                ClassServices::createKindOfIntentionallyConstructedObject);
        factoryMethods.put("kind_of_ordinary_biological_object", ClassServices::createKindOfOrdinaryBiologicalObject);
        factoryMethods.put("kind_of_ordinary_functional_object", ClassServices::createKindOfOrdinaryFunctionalObject);
        factoryMethods.put("kind_of_ordinary_physical_object", ClassServices::createKindOfOrdinaryPhysicalObject);
        factoryMethods.put("kind_of_organization", ClassServices::createKindOfOrganization);
        factoryMethods.put("kind_of_organization_component", ClassServices::createKindOfOrganizationComponent);
        factoryMethods.put("kind_of_party", ClassServices::createKindOfParty);
        factoryMethods.put("kind_of_person", ClassServices::createKindOfPerson);
        factoryMethods.put("kind_of_physical_object", ClassServices::createKindOfPhysicalObject);
        factoryMethods.put("kind_of_physical_property", ClassServices::createKindOfPhysicalProperty);
        factoryMethods.put("kind_of_physical_quantity", ClassServices::createKindOfPhysicalQuantity);
        factoryMethods.put("kind_of_position", ClassServices::createKindOfPosition);
        factoryMethods.put("kind_of_relationship_with_restriction", 
                ClassServices::createKindOfRelationshipWithRestriction);
        factoryMethods.put("kind_of_relationship_with_signature", ClassServices::createKindOfRelationshipWithSignature);
        factoryMethods.put("kind_of_socially_constructed_object", ClassServices::createKindOfSociallyConstructedObject);
        factoryMethods.put("kind_of_system", ClassServices::createKindOfSystem);
        factoryMethods.put("kind_of_system_component", ClassServices::createKindOfSystemComponent);
        factoryMethods.put("language_community", SpatioTemporalExtentServices::createLanguageCommunity);
        factoryMethods.put("money_asset", SpatioTemporalExtentServices::createMoneyAsset);
        factoryMethods.put("offer", SpatioTemporalExtentServices::createOffer);
        factoryMethods.put("offer_and_acceptance_for_goods", 
                SpatioTemporalExtentServices::createOfferAndAcceptanceForGoods);
        factoryMethods.put("offer_for_goods", SpatioTemporalExtentServices::createOfferForGoods);
        factoryMethods.put("offering", SpatioTemporalExtentServices::createOffering);
        factoryMethods.put("ordinary_biological_object", SpatioTemporalExtentServices::createOrdinaryBiologicalObject);
        factoryMethods.put("ordinary_functional_object", SpatioTemporalExtentServices::createOrdinaryFunctionalObject);
        factoryMethods.put("ordinary_physical_object", SpatioTemporalExtentServices::createOrdinaryPhysicalObject);
        factoryMethods.put("organization", SpatioTemporalExtentServices::createOrganization);
        factoryMethods.put("organization_component", SpatioTemporalExtentServices::createOrganizationComponent);
        factoryMethods.put("owner", SpatioTemporalExtentServices::createOwner);
        factoryMethods.put("ownership", SpatioTemporalExtentServices::createOwnership);
        factoryMethods.put("participant", SpatioTemporalExtentServices::createParticipant);
        factoryMethods.put("party", SpatioTemporalExtentServices::createParty);
        factoryMethods.put("pattern", ClassServices::createPattern);
        factoryMethods.put("period_of_time", SpatioTemporalExtentServices::createPeriodOfTime);
        factoryMethods.put("person", SpatioTemporalExtentServices::createPerson);
        factoryMethods.put("person_in_position", SpatioTemporalExtentServices::createPersonInPosition);
        factoryMethods.put("physical_object", SpatioTemporalExtentServices::createPhysicalObject);
        factoryMethods.put("physical_property", SpatioTemporalExtentServices::createPhysicalProperty);
        factoryMethods.put("physical_property_range", SpatioTemporalExtentServices::createPhysicalPropertyRange);
        factoryMethods.put("physical_quantity", SpatioTemporalExtentServices::createPhysicalQuantity);
        factoryMethods.put("physical_quantity_range", SpatioTemporalExtentServices::createPhysicalQuantityRange);
        factoryMethods.put("plan", SpatioTemporalExtentServices::createPlan);
        factoryMethods.put("point_in_time", SpatioTemporalExtentServices::createPointInTime);
        factoryMethods.put("position", SpatioTemporalExtentServices::createPosition);
        factoryMethods.put("possible_world", SpatioTemporalExtentServices::createPossibleWorld);
        factoryMethods.put("price", SpatioTemporalExtentServices::createPrice);
        factoryMethods.put("product_brand", SpatioTemporalExtentServices::createProductBrand);
        factoryMethods.put("product_offering", SpatioTemporalExtentServices::createProductOffering);
        factoryMethods.put("reaching_agreement", SpatioTemporalExtentServices::createReachingAgreement);
        factoryMethods.put("recognizing_language_community", 
                SpatioTemporalExtentServices::createRecognizingLanguageCommunity);
        factoryMethods.put("relationship", RelationshipServices::createRelationship);
        factoryMethods.put("representation_by_pattern", ClassServices::createRepresentationByPattern);
        factoryMethods.put("representation_by_sign", SpatioTemporalExtentServices::createRepresentationBySign);
        factoryMethods.put("requirement", SpatioTemporalExtentServices::createRequirement);
        factoryMethods.put("requirement_specification", SpatioTemporalExtentServices::createRequirementSpecification);
        factoryMethods.put("role", ClassServices::createRole);
        factoryMethods.put("sale_of_goods", SpatioTemporalExtentServices::createSaleOfGoods);
        factoryMethods.put("sales_product", SpatioTemporalExtentServices::createSalesProduct);
        factoryMethods.put("sales_product_instance", SpatioTemporalExtentServices::createSalesProductInstance);
        factoryMethods.put("sales_product_version", SpatioTemporalExtentServices::createSalesProductVersion);
        factoryMethods.put("scale", RelationshipServices::createScale);
        factoryMethods.put("sign", SpatioTemporalExtentServices::createSign);
        factoryMethods.put("socially_constructed_activity", 
                SpatioTemporalExtentServices::createSociallyConstructedActivity);
        factoryMethods.put("socially_constructed_object", 
                SpatioTemporalExtentServices::createSociallyConstructedObject);
        factoryMethods.put("spatio_temporal_extent", SpatioTemporalExtentServices::createSpatioTemporalExtent);
        factoryMethods.put("specialization", RelationshipServices::createSpecialization);
        factoryMethods.put("state", SpatioTemporalExtentServices::createState);
        factoryMethods.put("state_of_activity", SpatioTemporalExtentServices::createStateOfActivity);
        factoryMethods.put("state_of_amount_of_money", SpatioTemporalExtentServices::createStateOfAmountOfMoney);
        factoryMethods.put("state_of_association", SpatioTemporalExtentServices::createStateOfAssociation);
        factoryMethods.put("state_of_biological_object", SpatioTemporalExtentServices::createStateOfBiologicalObject);
        factoryMethods.put("state_of_biological_system", SpatioTemporalExtentServices::createStateOfBiologicalSystem);
        factoryMethods.put("state_of_biological_system_component", 
                SpatioTemporalExtentServices::createStateOfBiologicalSystemComponent);
        factoryMethods.put("state_of_functional_object", SpatioTemporalExtentServices::createStateOfFunctionalObject);
        factoryMethods.put("state_of_functional_system", SpatioTemporalExtentServices::createStateOfFunctionalSystem);
        factoryMethods.put("state_of_functional_system_component", 
                SpatioTemporalExtentServices::createStateOfFunctionalSystemComponent);
        factoryMethods.put("state_of_intentionally_constructed_object", 
                SpatioTemporalExtentServices::createStateOfIntentionallyConstructedObject);
        factoryMethods.put("state_of_language_community", SpatioTemporalExtentServices::createStateOfLanguageCommunity);
        factoryMethods.put("state_of_ordinary_biological_object", 
                SpatioTemporalExtentServices::createStateOfOrdinaryBiologicalObject);
        factoryMethods.put("state_of_ordinary_functional_object", 
                SpatioTemporalExtentServices::createStateOfOrdinaryFunctionalObject);
        factoryMethods.put("state_of_ordinary_physical_object", 
                SpatioTemporalExtentServices::createStateOfOrdinaryPhysicalObject);
        factoryMethods.put("state_of_organization", SpatioTemporalExtentServices::createStateOfOrganization);
        factoryMethods.put("state_of_organization_component", 
                SpatioTemporalExtentServices::createStateOfOrganizationComponent);
        factoryMethods.put("state_of_party", SpatioTemporalExtentServices::createStateOfParty);
        factoryMethods.put("state_of_person", SpatioTemporalExtentServices::createStateOfPerson);
        factoryMethods.put("state_of_physical_object", SpatioTemporalExtentServices::createStateOfPhysicalObject);
        factoryMethods.put("state_of_position", SpatioTemporalExtentServices::createStateOfPosition);
        factoryMethods.put("state_of_sales_product_instance", 
                SpatioTemporalExtentServices::createStateOfSalesProductInstance);
        factoryMethods.put("state_of_sign", SpatioTemporalExtentServices::createStateOfSign);
        factoryMethods.put("state_of_socially_constructed_activity", 
                SpatioTemporalExtentServices::createStateOfSociallyConstructedActivity);
        factoryMethods.put("state_of_socially_constructed_object", 
                SpatioTemporalExtentServices::createStateOfSociallyConstructedObject);
        factoryMethods.put("state_of_system", SpatioTemporalExtentServices::createStateOfSystem);
        factoryMethods.put("state_of_system_component", SpatioTemporalExtentServices::createStateOfSystemComponent);
        factoryMethods.put("system", SpatioTemporalExtentServices::createSystem);
        factoryMethods.put("system_component", SpatioTemporalExtentServices::createSystemComponent);
        factoryMethods.put("temporal_composition", RelationshipServices::createTemporalComposition);
        factoryMethods.put("thing", SpatioTemporalExtentServices::createThing);
        factoryMethods.put("transferee", SpatioTemporalExtentServices::createTransferee);
        factoryMethods.put("transfer_of_ownership", SpatioTemporalExtentServices::createTransferOfOwnership);
        factoryMethods.put("transfer_of_ownership_of_money", 
                SpatioTemporalExtentServices::createTransferOfOwnershipOfMoney);
        factoryMethods.put("transferor", SpatioTemporalExtentServices::createTransferor);
        factoryMethods.put("unit_of_measure", RelationshipServices::createUnitOfMeasure);
    }
}
