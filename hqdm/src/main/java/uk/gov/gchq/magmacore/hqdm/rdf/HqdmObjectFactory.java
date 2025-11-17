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
        factoryMethods.put("AbstractObject", SpatioTemporalExtentServices::createAbstractObject);
        factoryMethods.put("AcceptanceOfOffer", SpatioTemporalExtentServices::createAcceptanceOfOffer);
        factoryMethods.put("AcceptanceOfOfferForGoods", 
                SpatioTemporalExtentServices::createAcceptanceOfOfferForGoods);
        factoryMethods.put("aCtivity", SpatioTemporalExtentServices::createActivity);
        factoryMethods.put("Aggregation", RelationshipServices::createAggregation);
        factoryMethods.put("AgreeContract", SpatioTemporalExtentServices::createAgreeContract);
        factoryMethods.put("AgreementExecution", SpatioTemporalExtentServices::createAgreementExecution);
        factoryMethods.put("AgreementProcess", SpatioTemporalExtentServices::createAgreementProcess);
        factoryMethods.put("AmountOfMoney", SpatioTemporalExtentServices::createAmountOfMoney);
        factoryMethods.put("Asset", SpatioTemporalExtentServices::createAsset);
        factoryMethods.put("Association", SpatioTemporalExtentServices::createAssociation);
        factoryMethods.put("BeginningOfOwnership", SpatioTemporalExtentServices::createBeginningOfOwnership);
        factoryMethods.put("BiologicalObject", SpatioTemporalExtentServices::createBiologicalObject);
        factoryMethods.put("BiologicalSystem", SpatioTemporalExtentServices::createBiologicalSystem);
        factoryMethods.put("BiologicalSystemComponent", 
                SpatioTemporalExtentServices::createBiologicalSystemComponent);
        factoryMethods.put("Class", ClassServices::createClass);
        factoryMethods.put("Classification", RelationshipServices::createClassification);
        factoryMethods.put("ClassOfAbstractObject", ClassServices::createClassOfAbstractObject);
        factoryMethods.put("ClassOfActivity", ClassServices::createClassOfActivity);
        factoryMethods.put("ClassOfAgreeContract", ClassServices::createClassOfAgreeContract);
        factoryMethods.put("ClassOfAgreementExecution", ClassServices::createClassOfAgreementExecution);
        factoryMethods.put("ClassOfAgreementProcess", ClassServices::createClassOfAgreementProcess);
        factoryMethods.put("ClassOfAmountOfMoney", ClassServices::createClassOfAmountOfMoney);
        factoryMethods.put("ClassOfAssociation", ClassServices::createClassOfAssociation);
        factoryMethods.put("ClassOfBiologicalObject", ClassServices::createClassOfBiologicalObject);
        factoryMethods.put("ClassOfBiologicalSystem", ClassServices::createClassOfBiologicalSystem);
        factoryMethods.put("ClassOfBiologicalSystemComponent", 
                ClassServices::createClassOfBiologicalSystemComponent);
        factoryMethods.put("ClassOfClass", ClassServices::createClassOfClass);
        factoryMethods.put("ClassOfClassOfSpatioTemporalExtent", 
                ClassServices::createClassOfSpatioTemporalExtent);
        factoryMethods.put("ClassOfContractExecution", ClassServices::createClassOfContractExecution);
        factoryMethods.put("ClassOfContractProcess", ClassServices::createClassOfContractProcess);
        factoryMethods.put("ClassOfEvent", ClassServices::createClassOfEvent);
        factoryMethods.put("ClassOfFunctionalObject", ClassServices::createClassOfFunctionalObject);
        factoryMethods.put("ClassOfFunctionalSystem", ClassServices::createClassOfFunctionalSystem);
        factoryMethods.put("ClassOfFunctionalSystemComponent", 
                ClassServices::createClassOfFunctionalSystemComponent);
        factoryMethods.put("ClassOfIndividual", ClassServices::createClassOfIndividual);
        factoryMethods.put("ClassOfInPlaceBiologicalComponent", 
                ClassServices::createClassOfInPlaceBiologicalComponent);
        factoryMethods.put("ClassOfInstalledFunctionalSystemComponent", 
                ClassServices::createClassOfInstalledFunctionalSystemComponent);
        factoryMethods.put("ClassOfInstalledObject", ClassServices::createClassOfInstalledObject);
        factoryMethods.put("ClassOfIntentionallyConstructedObject", 
                ClassServices::createClassOfIntentionallyConstructedObject);
        factoryMethods.put("ClassOfOffer", ClassServices::createClassOfOffer);
        factoryMethods.put("ClassOfOrdinaryBiologicalObject", ClassServices::createClassOfOrdinaryBiologicalObject);
        factoryMethods.put("ClassOfOrdinaryFunctionalObject", ClassServices::createClassOfOrdinaryFunctionalObject);
        factoryMethods.put("ClassOfOrdinaryPhysicalObject", ClassServices::createClassOfOrdinaryPhysicalObject);
        factoryMethods.put("ClassOfOrganization", ClassServices::createClassOfOrganization);
        factoryMethods.put("ClassOfOrganizationComponent", ClassServices::createClassOfOrganizationComponent);
        factoryMethods.put("ClassOfParticipant", ClassServices::createClassOfParticipant);
        factoryMethods.put("ClassOfParty", ClassServices::createClassOfParty);
        factoryMethods.put("ClassOfPeriodOfTime", ClassServices::createClassOfPeriodOfTime);
        factoryMethods.put("ClassOfPerson", ClassServices::createClassOfPerson);
        factoryMethods.put("ClassOfPersonInPosition", ClassServices::createClassOfPersonInPosition);
        factoryMethods.put("ClassOfPhysicalObject", ClassServices::createClassOfPhysicalObject);
        factoryMethods.put("ClassOfPhysicalProperty", ClassServices::createClassOfPhysicalProperty);
        factoryMethods.put("ClassOfPhysicalQuantity", ClassServices::createClassOfPhysicalQuantity);
        factoryMethods.put("ClassOfPointInTime", ClassServices::createClassOfPointInTime);
        factoryMethods.put("ClassOfPosition", ClassServices::createClassOfPosition);
        factoryMethods.put("ClassOfPossibleWorld", ClassServices::createClassOfPossibleWorld);
        factoryMethods.put("ClassOfReachingAgreement", ClassServices::createClassOfReachingAgreement);
        factoryMethods.put("ClassOfRelationship", ClassServices::createClassOfRelationship);
        factoryMethods.put("ClassOfRepresentation", ClassServices::createClassOfRepresentation);
        factoryMethods.put("ClassOfSalesProductInstance", ClassServices::createClassOfSalesProductInstance);
        factoryMethods.put("ClassOfSign", ClassServices::createClassOfSign);
        factoryMethods.put("ClassOfSociallyConstructedActivity", 
                ClassServices::createClassOfSociallyConstructedActivity);
        factoryMethods.put("ClassOfSociallyConstructedObject", 
                ClassServices::createClassOfSociallyConstructedObject);
        factoryMethods.put("ClassOfSpatioTemporalExtent", ClassServices::createClassOfSpatioTemporalExtent);
        factoryMethods.put("ClassOfState", ClassServices::createClassOfState);
        factoryMethods.put("ClassOfStateOfActivity", ClassServices::createClassOfStateOfActivity);
        factoryMethods.put("ClassOfStateOfAmountOfMoney", ClassServices::createClassOfStateOfAmountOfMoney);
        factoryMethods.put("ClassOfStateOfAssociation", ClassServices::createClassOfStateOfAssociation);
        factoryMethods.put("ClassOfStateOfBiologicalObject", ClassServices::createClassOfStateOfBiologicalObject);
        factoryMethods.put("ClassOfStateOfBiologicalSystem", ClassServices::createClassOfStateOfBiologicalSystem);
        factoryMethods.put("ClassOfStateOfBiologicalSystemComponent", 
                ClassServices::createClassOfStateOfBiologicalSystemComponent);
        factoryMethods.put("ClassOfStateOfFunctionalObject", ClassServices::createClassOfStateOfFunctionalObject);
        factoryMethods.put("ClassOfStateOfFunctionalSystem", ClassServices::createClassOfStateOfFunctionalSystem);
        factoryMethods.put("ClassOfStateOfFunctionalSystemComponent", 
                ClassServices::createClassOfStateOfFunctionalSystemComponent);
        factoryMethods.put("ClassOfStateOfIntentionallyConstructedObject", 
                ClassServices::createClassOfStateOfIntentionallyConstructedObject);
        factoryMethods.put("ClassOfStateOfOrdinaryBiologicalObject", 
                ClassServices::createClassOfStateOfOrdinaryBiologicalObject);
        factoryMethods.put("ClassOfStateOfOrdinaryFunctionalObject", 
                ClassServices::createClassOfStateOfOrdinaryFunctionalObject);
        factoryMethods.put("ClassOfStateOfOrdinaryPhysicalObject", 
                ClassServices::createClassOfStateOfOrdinaryPhysicalObject);
        factoryMethods.put("ClassOfStateOfOrganization", ClassServices::createClassOfStateOfOrganization);
        factoryMethods.put("ClassOfStateOfOrganizationComponent", 
                ClassServices::createClassOfStateOfOrganizationComponent);
        factoryMethods.put("ClassOfStateOfParty", ClassServices::createClassOfStateOfParty);
        factoryMethods.put("ClassOfStateOfPerson", ClassServices::createClassOfStateOfPerson);
        factoryMethods.put("ClassOfStateOfPhysicalObject", ClassServices::createClassOfStateOfPhysicalObject);
        factoryMethods.put("ClassOfStateOfPosition", ClassServices::createClassOfStateOfPosition);
        factoryMethods.put("ClassOfStateOfSalesProductInstance", 
                ClassServices::createClassOfStateOfSalesProductInstance);
        factoryMethods.put("ClassOfStateOfSign", ClassServices::createClassOfStateOfSign);
        factoryMethods.put("ClassOfStateOfSociallyConstructedActivity", 
                ClassServices::createClassOfStateOfSociallyConstructedActivity);
        factoryMethods.put("ClassOfStateOfSociallyConstructedObject", 
                ClassServices::createClassOfStateOfSociallyConstructedObject);
        factoryMethods.put("ClassOfStateOfSystem", ClassServices::createClassOfStateOfSystem);
        factoryMethods.put("ClassOfStateOfSystemComponent", ClassServices::createClassOfStateOfSystemComponent);
        factoryMethods.put("ClassOfSystem", ClassServices::createClassOfSystem);
        factoryMethods.put("ClassOfSystemComponent", ClassServices::createClassOfSystemComponent);
        factoryMethods.put("Composition", RelationshipServices::createComposition);
        factoryMethods.put("ContractExecution", SpatioTemporalExtentServices::createContractExecution);
        factoryMethods.put("ContractProcess", SpatioTemporalExtentServices::createContractProcess);
        factoryMethods.put("Currency", SpatioTemporalExtentServices::createCurrency);
        factoryMethods.put("DefinedRelationship", RelationshipServices::createDefinedRelationship);
        factoryMethods.put("Definition", ClassServices::createDefinition);
        factoryMethods.put("Description", ClassServices::createDescription);
        factoryMethods.put("Employee", SpatioTemporalExtentServices::createEmployee);
        factoryMethods.put("Employer", SpatioTemporalExtentServices::createEmployer);
        factoryMethods.put("Employment", SpatioTemporalExtentServices::createEmployment);
        factoryMethods.put("EndingOfOwnership", SpatioTemporalExtentServices::createEndingOfOwnership);
        factoryMethods.put("EnumeratedClass", ClassServices::createEnumeratedClass);
        factoryMethods.put("Event", SpatioTemporalExtentServices::createEvent);
        factoryMethods.put("ExchangeOfGoodsAndMoney", SpatioTemporalExtentServices::createExchangeOfGoodsAndMoney);
        factoryMethods.put("Function", RelationshipServices::createFunction);
        factoryMethods.put("FunctionalObject", SpatioTemporalExtentServices::createFunctionalObject);
        factoryMethods.put("FunctionalSystem", SpatioTemporalExtentServices::createFunctionalSystem);
        factoryMethods.put("FunctionalSystemComponent", 
                SpatioTemporalExtentServices::createFunctionalSystemComponent);
        factoryMethods.put("Identification", ClassServices::createIdentification);
        factoryMethods.put("IdentificationOfPhysicalQuantity", 
                SpatioTemporalExtentServices::createIdentificationOfPhysicalQuantity);
        factoryMethods.put("Individual", SpatioTemporalExtentServices::createIndividual);
        factoryMethods.put("InPlaceBiologicalComponent", 
                SpatioTemporalExtentServices::createInPlaceBiologicalComponent);
        factoryMethods.put("InstalledFunctionalSystemComponent", 
                SpatioTemporalExtentServices::createInstalledFunctionalSystemComponent);
        factoryMethods.put("InstalledObject", SpatioTemporalExtentServices::createInstalledObject);
        factoryMethods.put("IntentionallyConstructedObject", 
                SpatioTemporalExtentServices::createIntentionallyConstructedObject);
        factoryMethods.put("KindOfActivity", ClassServices::createKindOfActivity);
        factoryMethods.put("KindOfAssociation", ClassServices::createKindOfAssociation);
        factoryMethods.put("KindOfBiologicalObject", ClassServices::createKindOfBiologicalObject);
        factoryMethods.put("KindOfBiologicalSystem", ClassServices::createKindOfBiologicalSystem);
        factoryMethods.put("KindOfBiologicalSystemComponent", ClassServices::createKindOfBiologicalSystemComponent);
        factoryMethods.put("KindOfFunctionalObject", ClassServices::createKindOfFunctionalObject);
        factoryMethods.put("KindOfFunctionalSystem", ClassServices::createKindOfFunctionalSystem);
        factoryMethods.put("KindOfFunctionalSystemComponent", ClassServices::createKindOfFunctionalSystemComponent);
        factoryMethods.put("KindOfIndividual", ClassServices::createKindOfIndividual);
        factoryMethods.put("KindOfIntentionallyConstructedObject", 
                ClassServices::createKindOfIntentionallyConstructedObject);
        factoryMethods.put("KindOfOrdinaryBiologicalObject", ClassServices::createKindOfOrdinaryBiologicalObject);
        factoryMethods.put("KindOfOrdinaryFunctionalObject", ClassServices::createKindOfOrdinaryFunctionalObject);
        factoryMethods.put("KindOfOrdinaryPhysicalObject", ClassServices::createKindOfOrdinaryPhysicalObject);
        factoryMethods.put("KindOfOrganization", ClassServices::createKindOfOrganization);
        factoryMethods.put("KindOfOrganizationComponent", ClassServices::createKindOfOrganizationComponent);
        factoryMethods.put("KindOfParty", ClassServices::createKindOfParty);
        factoryMethods.put("KindOfPerson", ClassServices::createKindOfPerson);
        factoryMethods.put("KindOfPhysicalObject", ClassServices::createKindOfPhysicalObject);
        factoryMethods.put("KindOfPhysicalProperty", ClassServices::createKindOfPhysicalProperty);
        factoryMethods.put("KindOfPhysicalQuantity", ClassServices::createKindOfPhysicalQuantity);
        factoryMethods.put("KindOfPosition", ClassServices::createKindOfPosition);
        factoryMethods.put("KindOfRelationshipWithRestriction", 
                ClassServices::createKindOfRelationshipWithRestriction);
        factoryMethods.put("KindOfRelationshipWithSignature", ClassServices::createKindOfRelationshipWithSignature);
        factoryMethods.put("KindOfSociallyConstructedObject", ClassServices::createKindOfSociallyConstructedObject);
        factoryMethods.put("KindOfSystem", ClassServices::createKindOfSystem);
        factoryMethods.put("KindOfSystemComponent", ClassServices::createKindOfSystemComponent);
        factoryMethods.put("LanguageCommunity", SpatioTemporalExtentServices::createLanguageCommunity);
        factoryMethods.put("MoneyAsset", SpatioTemporalExtentServices::createMoneyAsset);
        factoryMethods.put("Offer", SpatioTemporalExtentServices::createOffer);
        factoryMethods.put("OfferAndAcceptanceForGoods", 
                SpatioTemporalExtentServices::createOfferAndAcceptanceForGoods);
        factoryMethods.put("OfferForGoods", SpatioTemporalExtentServices::createOfferForGoods);
        factoryMethods.put("Offering", SpatioTemporalExtentServices::createOffering);
        factoryMethods.put("OrdinaryBiologicalObject", SpatioTemporalExtentServices::createOrdinaryBiologicalObject);
        factoryMethods.put("OrdinaryFunctionalObject", SpatioTemporalExtentServices::createOrdinaryFunctionalObject);
        factoryMethods.put("OrdinaryPhysicalObject", SpatioTemporalExtentServices::createOrdinaryPhysicalObject);
        factoryMethods.put("Organization", SpatioTemporalExtentServices::createOrganization);
        factoryMethods.put("OrganizationComponent", SpatioTemporalExtentServices::createOrganizationComponent);
        factoryMethods.put("Owner", SpatioTemporalExtentServices::createOwner);
        factoryMethods.put("Ownership", SpatioTemporalExtentServices::createOwnership);
        factoryMethods.put("Participant", SpatioTemporalExtentServices::createParticipant);
        factoryMethods.put("Party", SpatioTemporalExtentServices::createParty);
        factoryMethods.put("Pattern", ClassServices::createPattern);
        factoryMethods.put("PeriodOfTime", SpatioTemporalExtentServices::createPeriodOfTime);
        factoryMethods.put("Person", SpatioTemporalExtentServices::createPerson);
        factoryMethods.put("PersonInPosition", SpatioTemporalExtentServices::createPersonInPosition);
        factoryMethods.put("PhysicalObject", SpatioTemporalExtentServices::createPhysicalObject);
        factoryMethods.put("PhysicalProperty", SpatioTemporalExtentServices::createPhysicalProperty);
        factoryMethods.put("PhysicalPropertyRange", SpatioTemporalExtentServices::createPhysicalPropertyRange);
        factoryMethods.put("PhysicalQuantity", SpatioTemporalExtentServices::createPhysicalQuantity);
        factoryMethods.put("PhysicalQuantityRange", SpatioTemporalExtentServices::createPhysicalQuantityRange);
        factoryMethods.put("Plan", SpatioTemporalExtentServices::createPlan);
        factoryMethods.put("PointInTime", SpatioTemporalExtentServices::createPointInTime);
        factoryMethods.put("Position", SpatioTemporalExtentServices::createPosition);
        factoryMethods.put("PossibleWorld", SpatioTemporalExtentServices::createPossibleWorld);
        factoryMethods.put("Price", SpatioTemporalExtentServices::createPrice);
        factoryMethods.put("ProductBrand", SpatioTemporalExtentServices::createProductBrand);
        factoryMethods.put("ProductOffering", SpatioTemporalExtentServices::createProductOffering);
        factoryMethods.put("ReachingAgreement", SpatioTemporalExtentServices::createReachingAgreement);
        factoryMethods.put("RecognizingLanguageCommunity", 
                SpatioTemporalExtentServices::createRecognizingLanguageCommunity);
        factoryMethods.put("Relationship", RelationshipServices::createRelationship);
        factoryMethods.put("RepresentationByPattern", ClassServices::createRepresentationByPattern);
        factoryMethods.put("RepresentationBySign", SpatioTemporalExtentServices::createRepresentationBySign);
        factoryMethods.put("Requirement", SpatioTemporalExtentServices::createRequirement);
        factoryMethods.put("RequirementSpecification", SpatioTemporalExtentServices::createRequirementSpecification);
        factoryMethods.put("Role", ClassServices::createRole);
        factoryMethods.put("SaleOfGoods", SpatioTemporalExtentServices::createSaleOfGoods);
        factoryMethods.put("SalesProduct", SpatioTemporalExtentServices::createSalesProduct);
        factoryMethods.put("SalesProductInstance", SpatioTemporalExtentServices::createSalesProductInstance);
        factoryMethods.put("SalesProductVersion", SpatioTemporalExtentServices::createSalesProductVersion);
        factoryMethods.put("Scale", RelationshipServices::createScale);
        factoryMethods.put("Sign", SpatioTemporalExtentServices::createSign);
        factoryMethods.put("SociallyConstructedActivity", 
                SpatioTemporalExtentServices::createSociallyConstructedActivity);
        factoryMethods.put("SociallyConstructedObject", 
                SpatioTemporalExtentServices::createSociallyConstructedObject);
        factoryMethods.put("SpatioTemporalExtent", SpatioTemporalExtentServices::createSpatioTemporalExtent);
        factoryMethods.put("Specialization", RelationshipServices::createSpecialization);
        factoryMethods.put("State", SpatioTemporalExtentServices::createState);
        factoryMethods.put("StateOfActivity", SpatioTemporalExtentServices::createStateOfActivity);
        factoryMethods.put("StateOfAmountOfMoney", SpatioTemporalExtentServices::createStateOfAmountOfMoney);
        factoryMethods.put("StateOfAssociation", SpatioTemporalExtentServices::createStateOfAssociation);
        factoryMethods.put("StateOfBiologicalObject", SpatioTemporalExtentServices::createStateOfBiologicalObject);
        factoryMethods.put("StateOfBiologicalSystem", SpatioTemporalExtentServices::createStateOfBiologicalSystem);
        factoryMethods.put("StateOfBiologicalSystemComponent", 
                SpatioTemporalExtentServices::createStateOfBiologicalSystemComponent);
        factoryMethods.put("StateOfFunctionalObject", SpatioTemporalExtentServices::createStateOfFunctionalObject);
        factoryMethods.put("StateOfFunctionalSystem", SpatioTemporalExtentServices::createStateOfFunctionalSystem);
        factoryMethods.put("StateOfFunctionalSystemComponent", 
                SpatioTemporalExtentServices::createStateOfFunctionalSystemComponent);
        factoryMethods.put("StateOfIntentionallyConstructedObject", 
                SpatioTemporalExtentServices::createStateOfIntentionallyConstructedObject);
        factoryMethods.put("StateOfLanguageCommunity", SpatioTemporalExtentServices::createStateOfLanguageCommunity);
        factoryMethods.put("StateOfOrdinaryBiologicalObject", 
                SpatioTemporalExtentServices::createStateOfOrdinaryBiologicalObject);
        factoryMethods.put("StateOfOrdinaryFunctionalObject", 
                SpatioTemporalExtentServices::createStateOfOrdinaryFunctionalObject);
        factoryMethods.put("StateOfOrdinaryPhysicalObject", 
                SpatioTemporalExtentServices::createStateOfOrdinaryPhysicalObject);
        factoryMethods.put("StateOfOrganization", SpatioTemporalExtentServices::createStateOfOrganization);
        factoryMethods.put("StateOfOrganizationComponent", 
                SpatioTemporalExtentServices::createStateOfOrganizationComponent);
        factoryMethods.put("StateOfParty", SpatioTemporalExtentServices::createStateOfParty);
        factoryMethods.put("StateOfPerson", SpatioTemporalExtentServices::createStateOfPerson);
        factoryMethods.put("StateOfPhysicalObject", SpatioTemporalExtentServices::createStateOfPhysicalObject);
        factoryMethods.put("StateOfPosition", SpatioTemporalExtentServices::createStateOfPosition);
        factoryMethods.put("StateOfSalesProductInstance", 
                SpatioTemporalExtentServices::createStateOfSalesProductInstance);
        factoryMethods.put("StateOfSign", SpatioTemporalExtentServices::createStateOfSign);
        factoryMethods.put("StateOfSociallyConstructedActivity", 
                SpatioTemporalExtentServices::createStateOfSociallyConstructedActivity);
        factoryMethods.put("StateOfSociallyConstructedObject", 
                SpatioTemporalExtentServices::createStateOfSociallyConstructedObject);
        factoryMethods.put("StateOfSystem", SpatioTemporalExtentServices::createStateOfSystem);
        factoryMethods.put("StateOfSystemComponent", SpatioTemporalExtentServices::createStateOfSystemComponent);
        factoryMethods.put("System", SpatioTemporalExtentServices::createSystem);
        factoryMethods.put("SystemComponent", SpatioTemporalExtentServices::createSystemComponent);
        factoryMethods.put("TemporalComposition", RelationshipServices::createTemporalComposition);
        factoryMethods.put("Thing", SpatioTemporalExtentServices::createThing);
        factoryMethods.put("Transferee", SpatioTemporalExtentServices::createTransferee);
        factoryMethods.put("TransferOfOwnership", SpatioTemporalExtentServices::createTransferOfOwnership);
        factoryMethods.put("TransferOfOwnershipOfMoney", 
                SpatioTemporalExtentServices::createTransferOfOwnershipOfMoney);
        factoryMethods.put("Transferor", SpatioTemporalExtentServices::createTransferor);
        factoryMethods.put("UnitOfMeasure", RelationshipServices::createUnitOfMeasure);
    }
}
