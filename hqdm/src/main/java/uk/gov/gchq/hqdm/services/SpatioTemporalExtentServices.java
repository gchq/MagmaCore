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

package uk.gov.gchq.hqdm.services;

import uk.gov.gchq.hqdm.model.*;
import uk.gov.gchq.hqdm.model.impl.*;

/**
 * Services for creating SpatioTemporalExtent objects.
 */
public class SpatioTemporalExtentServices {

    /**
     * Create a AbstractObject with an String.
     *
     * @param id ID of the AbstractObject.
     * @return A AbstractObject instance.
     */
    public static AbstractObject createAbstractObject(final String id) {
        return new AbstractObjectImpl(id);
    }

    /**
     * Create a AcceptanceOfOffer with an String.
     *
     * @param id ID of the AcceptanceOfOffer.
     * @return A AcceptanceOfOffer instance.
     */
    public static AcceptanceOfOffer createAcceptanceOfOffer(final String id) {
        return new AcceptanceOfOfferImpl(id);
    }

    /**
     * Create a AcceptanceOfOfferForGoods with an String.
     *
     * @param id ID of the AcceptanceOfOfferForGoods.
     * @return A AcceptanceOfOfferForGoods instance.
     */
    public static AcceptanceOfOfferForGoods createAcceptanceOfOfferForGoods(final String id) {
        return new AcceptanceOfOfferForGoodsImpl(id);
    }

    /**
     * Create a Activity with an String.
     *
     * @param id ID of the Activity.
     * @return A Activity instance.
     */
    public static Activity createActivity(final String id) {
        return new ActivityImpl(id);
    }

    /**
     * Create a AgreeContract with an String.
     *
     * @param id ID of the AgreeContract.
     * @return A AgreeContract instance.
     */
    public static AgreeContract createAgreeContract(final String id) {
        return new AgreeContractImpl(id);
    }

    /**
     * Create a AgreementExecution with an String.
     *
     * @param id ID of the AgreementExecution.
     * @return A AgreementExecution instance.
     */
    public static AgreementExecution createAgreementExecution(final String id) {
        return new AgreementExecutionImpl(id);
    }

    /**
     * Create a AgreementProcess with an String.
     *
     * @param id ID of the AgreementProcess.
     * @return A AgreementProcess instance.
     */
    public static AgreementProcess createAgreementProcess(final String id) {
        return new AgreementProcessImpl(id);
    }

    /**
     * Create a AmountOfMoney with an String.
     *
     * @param id ID of the AmountOfMoney.
     * @return A AmountOfMoney instance.
     */
    public static AmountOfMoney createAmountOfMoney(final String id) {
        return new AmountOfMoneyImpl(id);
    }

    /**
     * Create a Asset with an String.
     *
     * @param id ID of the Asset.
     * @return A Asset instance.
     */
    public static Asset createAsset(final String id) {
        return new AssetImpl(id);
    }

    /**
     * Create a Association with an String.
     *
     * @param id ID of the Association.
     * @return A Association instance.
     */
    public static Association createAssociation(final String id) {
        return new AssociationImpl(id);
    }

    /**
     * Create a BeginningOfOwnership with an String.
     *
     * @param id ID of the BeginningOfOwnership.
     * @return A BeginningOfOwnership instance.
     */
    public static BeginningOfOwnership createBeginningOfOwnership(final String id) {
        return new BeginningOfOwnershipImpl(id);
    }

    /**
     * Create a BiologicalObject with an String.
     *
     * @param id ID of the BiologicalObject.
     * @return A BiologicalObject instance.
     */
    public static BiologicalObject createBiologicalObject(final String id) {
        return new BiologicalObjectImpl(id);
    }

    /**
     * Create a BiologicalSystem with an String.
     *
     * @param id ID of the BiologicalSystem.
     * @return A BiologicalSystem instance.
     */
    public static BiologicalSystem createBiologicalSystem(final String id) {
        return new BiologicalSystemImpl(id);
    }

    /**
     * Create a BiologicalSystemComponent with an String.
     *
     * @param id ID of the BiologicalSystemComponent.
     * @return A BiologicalSystemComponent instance.
     */
    public static BiologicalSystemComponent createBiologicalSystemComponent(final String id) {
        return new BiologicalSystemComponentImpl(id);
    }

    /**
     * Create a ContractExecution with an String.
     *
     * @param id ID of the ContractExecution.
     * @return A ContractExecution instance.
     */
    public static ContractExecution createContractExecution(final String id) {
        return new ContractExecutionImpl(id);
    }

    /**
     * Create a ContractProcess with an String.
     *
     * @param id ID of the ContractProcess.
     * @return A ContractProcess instance.
     */
    public static ContractProcess createContractProcess(final String id) {
        return new ContractProcessImpl(id);
    }

    /**
     * Create a Currency with an String.
     *
     * @param id ID of the Currency.
     * @return A Currency instance.
     */
    public static Currency createCurrency(final String id) {
        return new CurrencyImpl(id);
    }

    /**
     * Create a Definition with an String.
     *
     * @param id ID of the Definition.
     * @return A Definition instance.
     */
    public static Definition createDefinition(final String id) {
        return new DefinitionImpl(id);
    }

    /**
     * Create a Description with an String.
     *
     * @param id ID of the Description.
     * @return A Description instance.
     */
    public static Description createDescription(final String id) {
        return new DescriptionImpl(id);
    }

    /**
     * Create a Employee with an String.
     *
     * @param id ID of the Employee.
     * @return A Employee instance.
     */
    public static Employee createEmployee(final String id) {
        return new EmployeeImpl(id);
    }

    /**
     * Create a Employer with an String.
     *
     * @param id ID of the Employer.
     * @return A Employer instance.
     */
    public static Employer createEmployer(final String id) {
        return new EmployerImpl(id);
    }

    /**
     * Create a Employment with an String.
     *
     * @param id ID of the Employment.
     * @return A Employment instance.
     */
    public static Employment createEmployment(final String id) {
        return new EmploymentImpl(id);
    }

    /**
     * Create a EndingOfOwnership with an String.
     *
     * @param id ID of the EndingOfOwnership.
     * @return A EndingOfOwnership instance.
     */
    public static EndingOfOwnership createEndingOfOwnership(final String id) {
        return new EndingOfOwnershipImpl(id);
    }

    /**
     * Create a Event with an String.
     *
     * @param id ID of the Event.
     * @return A Event instance.
     */
    public static Event createEvent(final String id) {
        return new EventImpl(id);
    }

    /**
     * Create a ExchangeOfGoodsAndMoney with an String.
     *
     * @param id ID of the ExchangeOfGoodsAndMoney.
     * @return A ExchangeOfGoodsAndMoney instance.
     */
    public static ExchangeOfGoodsAndMoney createExchangeOfGoodsAndMoney(final String id) {
        return new ExchangeOfGoodsAndMoneyImpl(id);
    }

    /**
     * Create a FunctionalObject with an String.
     *
     * @param id ID of the FunctionalObject.
     * @return A FunctionalObject instance.
     */
    public static FunctionalObject createFunctionalObject(final String id) {
        return new FunctionalObjectImpl(id);
    }

    /**
     * Create a FunctionalSystem with an String.
     *
     * @param id ID of the FunctionalSystem.
     * @return A FunctionalSystem instance.
     */
    public static FunctionalSystem createFunctionalSystem(final String id) {
        return new FunctionalSystemImpl(id);
    }

    /**
     * Create a FunctionalSystemComponent with an String.
     *
     * @param id ID of the FunctionalSystemComponent.
     * @return A FunctionalSystemComponent instance.
     */
    public static FunctionalSystemComponent createFunctionalSystemComponent(final String id) {
        return new FunctionalSystemComponentImpl(id);
    }

    /**
     * Create a Identification with an String.
     *
     * @param id ID of the Identification.
     * @return A Identification instance.
     */
    public static Identification createIdentification(final String id) {
        return new IdentificationImpl(id);
    }

    /**
     * Create a IdentificationOfPhysicalQuantity with an String.
     *
     * @param id ID of the IdentificationOfPhysicalQuantity.
     * @return A IdentificationOfPhysicalQuantity instance.
     */
    public static IdentificationOfPhysicalQuantity createIdentificationOfPhysicalQuantity(final String id) {
        return new IdentificationOfPhysicalQuantityImpl(id);
    }

    /**
     * Create a InPlaceBiologicalComponent with an String.
     *
     * @param id ID of the InPlaceBiologicalComponent.
     * @return A InPlaceBiologicalComponent instance.
     */
    public static InPlaceBiologicalComponent createInPlaceBiologicalComponent(final String id) {
        return new InPlaceBiologicalComponentImpl(id);
    }

    /**
     * Create a Individual with an String.
     *
     * @param id ID of the Individual.
     * @return A Individual instance.
     */
    public static Individual createIndividual(final String id) {
        return new IndividualImpl(id);
    }

    /**
     * Create a InstalledFunctionalSystemComponent with an String.
     *
     * @param id ID of the InstalledFunctionalSystemComponent.
     * @return A InstalledFunctionalSystemComponent instance.
     */
    public static InstalledFunctionalSystemComponent createInstalledFunctionalSystemComponent(final String id) {
        return new InstalledFunctionalSystemComponentImpl(id);
    }

    /**
     * Create a InstalledObject with an String.
     *
     * @param id ID of the InstalledObject.
     * @return A InstalledObject instance.
     */
    public static InstalledObject createInstalledObject(final String id) {
        return new InstalledObjectImpl(id);
    }

    /**
     * Create a IntentionallyConstructedObject with an String.
     *
     * @param id ID of the IntentionallyConstructedObject.
     * @return A IntentionallyConstructedObject instance.
     */
    public static IntentionallyConstructedObject createIntentionallyConstructedObject(final String id) {
        return new IntentionallyConstructedObjectImpl(id);
    }

    /**
     * Create a LanguageCommunity with an String.
     *
     * @param id ID of the LanguageCommunity.
     * @return A LanguageCommunity instance.
     */
    public static LanguageCommunity createLanguageCommunity(final String id) {
        return new LanguageCommunityImpl(id);
    }

    /**
     * Create a MoneyAsset with an String.
     *
     * @param id ID of the MoneyAsset.
     * @return A MoneyAsset instance.
     */
    public static MoneyAsset createMoneyAsset(final String id) {
        return new MoneyAssetImpl(id);
    }

    /**
     * Create a Offer with an String.
     *
     * @param id ID of the Offer.
     * @return A Offer instance.
     */
    public static Offer createOffer(final String id) {
        return new OfferImpl(id);
    }

    /**
     * Create a OfferAndAcceptanceForGoods with an String.
     *
     * @param id ID of the OfferAndAcceptanceForGoods.
     * @return A OfferAndAcceptanceForGoods instance.
     */
    public static OfferAndAcceptanceForGoods createOfferAndAcceptanceForGoods(final String id) {
        return new OfferAndAcceptanceForGoodsImpl(id);
    }

    /**
     * Create a OfferForGoods with an String.
     *
     * @param id ID of the OfferForGoods.
     * @return A OfferForGoods instance.
     */
    public static OfferForGoods createOfferForGoods(final String id) {
        return new OfferForGoodsImpl(id);
    }

    /**
     * Create a Offering with an String.
     *
     * @param id ID of the Offering.
     * @return A Offering instance.
     */
    public static Offering createOffering(final String id) {
        return new OfferingImpl(id);
    }

    /**
     * Create a OrdinaryBiologicalObject with an String.
     *
     * @param id ID of the OrdinaryBiologicalObject.
     * @return A OrdinaryBiologicalObject instance.
     */
    public static OrdinaryBiologicalObject createOrdinaryBiologicalObject(final String id) {
        return new OrdinaryBiologicalObjectImpl(id);
    }

    /**
     * Create a OrdinaryFunctionalObject with an String.
     *
     * @param id ID of the OrdinaryFunctionalObject.
     * @return A OrdinaryFunctionalObject instance.
     */
    public static OrdinaryFunctionalObject createOrdinaryFunctionalObject(final String id) {
        return new OrdinaryFunctionalObjectImpl(id);
    }

    /**
     * Create a OrdinaryPhysicalObject with an String.
     *
     * @param id ID of the OrdinaryPhysicalObject.
     * @return A OrdinaryPhysicalObject instance.
     */
    public static OrdinaryPhysicalObject createOrdinaryPhysicalObject(final String id) {
        return new OrdinaryPhysicalObjectImpl(id);
    }

    /**
     * Create a Organization with an String.
     *
     * @param id ID of the Organization.
     * @return A Organization instance.
     */
    public static Organization createOrganization(final String id) {
        return new OrganizationImpl(id);
    }

    /**
     * Create a OrganizationComponent with an String.
     *
     * @param id ID of the OrganizationComponent.
     * @return A OrganizationComponent instance.
     */
    public static OrganizationComponent createOrganizationComponent(final String id) {
        return new OrganizationComponentImpl(id);
    }

    /**
     * Create a Owner with an String.
     *
     * @param id ID of the Owner.
     * @return A Owner instance.
     */
    public static Owner createOwner(final String id) {
        return new OwnerImpl(id);
    }

    /**
     * Create a Ownership with an String.
     *
     * @param id ID of the Ownership.
     * @return A Ownership instance.
     */
    public static Ownership createOwnership(final String id) {
        return new OwnershipImpl(id);
    }

    /**
     * Create a Participant with an String.
     *
     * @param id ID of the Participant.
     * @return A Participant instance.
     */
    public static Participant createParticipant(final String id) {
        return new ParticipantImpl(id);
    }

    /**
     * Create a ParticipantInActivityOrAssociation with an String.
     *
     * @param id ID of the ParticipantInActivityOrAssociation.
     * @return A ParticipantInActivityOrAssociation instance.
     */
    public static ParticipantInActivityOrAssociation createParticipantInActivityOrAssociation(final String id) {
        return new ParticipantInActivityOrAssociationImpl(id);
    }

    /**
     * Create a Party with an String.
     *
     * @param id ID of the Party.
     * @return A Party instance.
     */
    public static Party createParty(final String id) {
        return new PartyImpl(id);
    }

    /**
     * Create a Pattern with an String.
     *
     * @param id ID of the Pattern.
     * @return A Pattern instance.
     */
    public static Pattern createPattern(final String id) {
        return new PatternImpl(id);
    }

    /**
     * Create a PeriodOfTime with an String.
     *
     * @param id ID of the PeriodOfTime.
     * @return A PeriodOfTime instance.
     */
    public static PeriodOfTime createPeriodOfTime(final String id) {
        return new PeriodOfTimeImpl(id);
    }

    /**
     * Create a Person with an String.
     *
     * @param id ID of the Person.
     * @return A Person instance.
     */
    public static Person createPerson(final String id) {
        return new PersonImpl(id);
    }

    /**
     * Create a PersonInPosition with an String.
     *
     * @param id ID of the PersonInPosition.
     * @return A PersonInPosition instance.
     */
    public static PersonInPosition createPersonInPosition(final String id) {
        return new PersonInPositionImpl(id);
    }

    /**
     * Create a PhysicalObject with an String.
     *
     * @param id ID of the PhysicalObject.
     * @return A PhysicalObject instance.
     */
    public static PhysicalObject createPhysicalObject(final String id) {
        return new PhysicalObjectImpl(id);
    }

    /**
     * Create a PhysicalProperty with an String.
     *
     * @param id ID of the PhysicalProperty.
     * @return A PhysicalProperty instance.
     */
    public static PhysicalProperty createPhysicalProperty(final String id) {
        return new PhysicalPropertyImpl(id);
    }

    /**
     * Create a PhysicalPropertyRange with an String.
     *
     * @param id ID of the PhysicalPropertyRange.
     * @return A PhysicalPropertyRange instance.
     */
    public static PhysicalPropertyRange createPhysicalPropertyRange(final String id) {
        return new PhysicalPropertyRangeImpl(id);
    }

    /**
     * Create a PhysicalQuantity with an String.
     *
     * @param id ID of the PhysicalQuantity.
     * @return A PhysicalQuantity instance.
     */
    public static PhysicalQuantity createPhysicalQuantity(final String id) {
        return new PhysicalQuantityImpl(id);
    }

    /**
     * Create a PhysicalQuantityRange with an String.
     *
     * @param id ID of the PhysicalQuantityRange.
     * @return A PhysicalQuantityRange instance.
     */
    public static PhysicalQuantityRange createPhysicalQuantityRange(final String id) {
        return new PhysicalQuantityRangeImpl(id);
    }

    /**
     * Create a Plan with an String.
     *
     * @param id ID of the Plan.
     * @return A Plan instance.
     */
    public static Plan createPlan(final String id) {
        return new PlanImpl(id);
    }

    /**
     * Create a PointInTime with an String.
     *
     * @param id ID of the PointInTime.
     * @return A PointInTime instance.
     */
    public static PointInTime createPointInTime(final String id) {
        return new PointInTimeImpl(id);
    }

    /**
     * Create a Position with an String.
     *
     * @param id ID of the Position.
     * @return A Position instance.
     */
    public static Position createPosition(final String id) {
        return new PositionImpl(id);
    }

    /**
     * Create a PossibleWorld with an String.
     *
     * @param id ID of the PossibleWorld.
     * @return A PossibleWorld instance.
     */
    public static PossibleWorld createPossibleWorld(final String id) {
        return new PossibleWorldImpl(id);
    }

    /**
     * Create a Price with an String.
     *
     * @param id ID of the Price.
     * @return A Price instance.
     */
    public static Price createPrice(final String id) {
        return new PriceImpl(id);
    }

    /**
     * Create a ProductBrand with an String.
     *
     * @param id ID of the ProductBrand.
     * @return A ProductBrand instance.
     */
    public static ProductBrand createProductBrand(final String id) {
        return new ProductBrandImpl(id);
    }

    /**
     * Create a ProductOffering with an String.
     *
     * @param id ID of the ProductOffering.
     * @return A ProductOffering instance.
     */
    public static ProductOffering createProductOffering(final String id) {
        return new ProductOfferingImpl(id);
    }

    /**
     * Create a ReachingAgreement with an String.
     *
     * @param id ID of the ReachingAgreement.
     * @return A ReachingAgreement instance.
     */
    public static ReachingAgreement createReachingAgreement(final String id) {
        return new ReachingAgreementImpl(id);
    }

    /**
     * Create a RecognizingLanguageCommunity with an String.
     *
     * @param id ID of the RecognizingLanguageCommunity.
     * @return A RecognizingLanguageCommunity instance.
     */
    public static RecognizingLanguageCommunity createRecognizingLanguageCommunity(final String id) {
        return new RecognizingLanguageCommunityImpl(id);
    }

    /**
     * Create a RepresentationByPattern with an String.
     *
     * @param id ID of the RepresentationByPattern.
     * @return A RepresentationByPattern instance.
     */
    public static RepresentationByPattern createRepresentationByPattern(final String id) {
        return new RepresentationByPatternImpl(id);
    }

    /**
     * Create a RepresentationBySign with an String.
     *
     * @param id ID of the RepresentationBySign.
     * @return A RepresentationBySign instance.
     */
    public static RepresentationBySign createRepresentationBySign(final String id) {
        return new RepresentationBySignImpl(id);
    }

    /**
     * Create a Requirement with an String.
     *
     * @param id ID of the Requirement.
     * @return A Requirement instance.
     */
    public static Requirement createRequirement(final String id) {
        return new RequirementImpl(id);
    }

    /**
     * Create a RequirementSpecification with an String.
     *
     * @param id ID of the RequirementSpecification.
     * @return A RequirementSpecification instance.
     */
    public static RequirementSpecification createRequirementSpecification(final String id) {
        return new RequirementSpecificationImpl(id);
    }

    /**
     * Create a SaleOfGoods with an String.
     *
     * @param id ID of the SaleOfGoods.
     * @return A SaleOfGoods instance.
     */
    public static SaleOfGoods createSaleOfGoods(final String id) {
        return new SaleOfGoodsImpl(id);
    }

    /**
     * Create a SalesProduct with an String.
     *
     * @param id ID of the SalesProduct.
     * @return A SalesProduct instance.
     */
    public static SalesProduct createSalesProduct(final String id) {
        return new SalesProductImpl(id);
    }

    /**
     * Create a SalesProductInstance with an String.
     *
     * @param id ID of the SalesProductInstance.
     * @return A SalesProductInstance instance.
     */
    public static SalesProductInstance createSalesProductInstance(final String id) {
        return new SalesProductInstanceImpl(id);
    }

    /**
     * Create a SalesProductVersion with an String.
     *
     * @param id ID of the SalesProductVersion.
     * @return A SalesProductVersion instance.
     */
    public static SalesProductVersion createSalesProductVersion(final String id) {
        return new SalesProductVersionImpl(id);
    }

    /**
     * Create a Sign with an String.
     *
     * @param id ID of the Sign.
     * @return A Sign instance.
     */
    public static Sign createSign(final String id) {
        return new SignImpl(id);
    }

    /**
     * Create a SociallyConstructedActivity with an String.
     *
     * @param id ID of the SociallyConstructedActivity.
     * @return A SociallyConstructedActivity instance.
     */
    public static SociallyConstructedActivity createSociallyConstructedActivity(final String id) {
        return new SociallyConstructedActivityImpl(id);
    }

    /**
     * Create a SociallyConstructedObject with an String.
     *
     * @param id ID of the SociallyConstructedObject.
     * @return A SociallyConstructedObject instance.
     */
    public static SociallyConstructedObject createSociallyConstructedObject(final String id) {
        return new SociallyConstructedObjectImpl(id);
    }

    /**
     * Create a SpatioTemporalExtent with an String.
     *
     * @param id ID of the SpatioTemporalExtent.
     * @return A SpatioTemporalExtent instance.
     */
    public static SpatioTemporalExtent createSpatioTemporalExtent(final String id) {
        return new SpatioTemporalExtentImpl(id);
    }

    /**
     * Create a State with an String.
     *
     * @param id ID of the State.
     * @return A State instance.
     */
    public static State createState(final String id) {
        return new StateImpl(id);
    }

    /**
     * Create a StateOfActivity with an String.
     *
     * @param id ID of the StateOfActivity.
     * @return A StateOfActivity instance.
     */
    public static StateOfActivity createStateOfActivity(final String id) {
        return new StateOfActivityImpl(id);
    }

    /**
     * Create a StateOfAmountOfMoney with an String.
     *
     * @param id ID of the StateOfAmountOfMoney.
     * @return A StateOfAmountOfMoney instance.
     */
    public static StateOfAmountOfMoney createStateOfAmountOfMoney(final String id) {
        return new StateOfAmountOfMoneyImpl(id);
    }

    /**
     * Create a StateOfAssociation with an String.
     *
     * @param id ID of the StateOfAssociation.
     * @return A StateOfAssociation instance.
     */
    public static StateOfAssociation createStateOfAssociation(final String id) {
        return new StateOfAssociationImpl(id);
    }

    /**
     * Create a StateOfBiologicalObject with an String.
     *
     * @param id ID of the StateOfBiologicalObject.
     * @return A StateOfBiologicalObject instance.
     */
    public static StateOfBiologicalObject createStateOfBiologicalObject(final String id) {
        return new StateOfBiologicalObjectImpl(id);
    }

    /**
     * Create a StateOfBiologicalSystem with an String.
     *
     * @param id ID of the StateOfBiologicalSystem.
     * @return A StateOfBiologicalSystem instance.
     */
    public static StateOfBiologicalSystem createStateOfBiologicalSystem(final String id) {
        return new StateOfBiologicalSystemImpl(id);
    }

    /**
     * Create a StateOfBiologicalSystemComponent with an String.
     *
     * @param id ID of the StateOfBiologicalSystemComponent.
     * @return A StateOfBiologicalSystemComponent instance.
     */
    public static StateOfBiologicalSystemComponent createStateOfBiologicalSystemComponent(final String id) {
        return new StateOfBiologicalSystemComponentImpl(id);
    }

    /**
     * Create a StateOfFunctionalObject with an String.
     *
     * @param id ID of the StateOfFunctionalObject.
     * @return A StateOfFunctionalObject instance.
     */
    public static StateOfFunctionalObject createStateOfFunctionalObject(final String id) {
        return new StateOfFunctionalObjectImpl(id);
    }

    /**
     * Create a StateOfFunctionalSystem with an String.
     *
     * @param id ID of the StateOfFunctionalSystem.
     * @return A StateOfFunctionalSystem instance.
     */
    public static StateOfFunctionalSystem createStateOfFunctionalSystem(final String id) {
        return new StateOfFunctionalSystemImpl(id);
    }

    /**
     * Create a StateOfFunctionalSystemComponent with an String.
     *
     * @param id ID of the StateOfFunctionalSystemComponent.
     * @return A StateOfFunctionalSystemComponent instance.
     */
    public static StateOfFunctionalSystemComponent createStateOfFunctionalSystemComponent(final String id) {
        return new StateOfFunctionalSystemComponentImpl(id);
    }

    /**
     * Create a StateOfIntentionallyConstructedObject with an String.
     *
     * @param id ID of the StateOfIntentionallyConstructedObject.
     * @return A StateOfIntentionallyConstructedObject instance.
     */
    public static StateOfIntentionallyConstructedObject createStateOfIntentionallyConstructedObject(final String id) {
        return new StateOfIntentionallyConstructedObjectImpl(id);
    }

    /**
     * Create a StateOfLanguageCommunity with an String.
     *
     * @param id ID of the StateOfLanguageCommunity.
     * @return A StateOfLanguageCommunity instance.
     */
    public static StateOfLanguageCommunity createStateOfLanguageCommunity(final String id) {
        return new StateOfLanguageCommunityImpl(id);
    }

    /**
     * Create a StateOfOrdinaryBiologicalObject with an String.
     *
     * @param id ID of the StateOfOrdinaryBiologicalObject.
     * @return A StateOfOrdinaryBiologicalObject instance.
     */
    public static StateOfOrdinaryBiologicalObject createStateOfOrdinaryBiologicalObject(final String id) {
        return new StateOfOrdinaryBiologicalObjectImpl(id);
    }

    /**
     * Create a StateOfOrdinaryFunctionalObject with an String.
     *
     * @param id ID of the StateOfOrdinaryFunctionalObject.
     * @return A StateOfOrdinaryFunctionalObject instance.
     */
    public static StateOfOrdinaryFunctionalObject createStateOfOrdinaryFunctionalObject(final String id) {
        return new StateOfOrdinaryFunctionalObjectImpl(id);
    }

    /**
     * Create a StateOfOrdinaryPhysicalObject with an String.
     *
     * @param id ID of the StateOfOrdinaryPhysicalObject.
     * @return A StateOfOrdinaryPhysicalObject instance.
     */
    public static StateOfOrdinaryPhysicalObject createStateOfOrdinaryPhysicalObject(final String id) {
        return new StateOfOrdinaryPhysicalObjectImpl(id);
    }

    /**
     * Create a StateOfOrganization with an String.
     *
     * @param id ID of the StateOfOrganization.
     * @return A StateOfOrganization instance.
     */
    public static StateOfOrganization createStateOfOrganization(final String id) {
        return new StateOfOrganizationImpl(id);
    }

    /**
     * Create a StateOfOrganizationComponent with an String.
     *
     * @param id ID of the StateOfOrganizationComponent.
     * @return A StateOfOrganizationComponent instance.
     */
    public static StateOfOrganizationComponent createStateOfOrganizationComponent(final String id) {
        return new StateOfOrganizationComponentImpl(id);
    }

    /**
     * Create a StateOfParty with an String.
     *
     * @param id ID of the StateOfParty.
     * @return A StateOfParty instance.
     */
    public static StateOfParty createStateOfParty(final String id) {
        return new StateOfPartyImpl(id);
    }

    /**
     * Create a StateOfPerson with an String.
     *
     * @param id ID of the StateOfPerson.
     * @return A StateOfPerson instance.
     */
    public static StateOfPerson createStateOfPerson(final String id) {
        return new StateOfPersonImpl(id);
    }

    /**
     * Create a StateOfPhysicalObject with an String.
     *
     * @param id ID of the StateOfPhysicalObject.
     * @return A StateOfPhysicalObject instance.
     */
    public static StateOfPhysicalObject createStateOfPhysicalObject(final String id) {
        return new StateOfPhysicalObjectImpl(id);
    }

    /**
     * Create a StateOfPosition with an String.
     *
     * @param id ID of the StateOfPosition.
     * @return A StateOfPosition instance.
     */
    public static StateOfPosition createStateOfPosition(final String id) {
        return new StateOfPositionImpl(id);
    }

    /**
     * Create a StateOfSalesProductInstance with an String.
     *
     * @param id ID of the StateOfSalesProductInstance.
     * @return A StateOfSalesProductInstance instance.
     */
    public static StateOfSalesProductInstance createStateOfSalesProductInstance(final String id) {
        return new StateOfSalesProductInstanceImpl(id);
    }

    /**
     * Create a StateOfSign with an String.
     *
     * @param id ID of the StateOfSign.
     * @return A StateOfSign instance.
     */
    public static StateOfSign createStateOfSign(final String id) {
        return new StateOfSignImpl(id);
    }

    /**
     * Create a StateOfSociallyConstructedActivity with an String.
     *
     * @param id ID of the StateOfSociallyConstructedActivity.
     * @return A StateOfSociallyConstructedActivity instance.
     */
    public static StateOfSociallyConstructedActivity createStateOfSociallyConstructedActivity(final String id) {
        return new StateOfSociallyConstructedActivityImpl(id);
    }

    /**
     * Create a StateOfSociallyConstructedObject with an String.
     *
     * @param id ID of the StateOfSociallyConstructedObject.
     * @return A StateOfSociallyConstructedObject instance.
     */
    public static StateOfSociallyConstructedObject createStateOfSociallyConstructedObject(final String id) {
        return new StateOfSociallyConstructedObjectImpl(id);
    }

    /**
     * Create a StateOfSystem with an String.
     *
     * @param id ID of the StateOfSystem.
     * @return A StateOfSystem instance.
     */
    public static StateOfSystem createStateOfSystem(final String id) {
        return new StateOfSystemImpl(id);
    }

    /**
     * Create a StateOfSystemComponent with an String.
     *
     * @param id ID of the StateOfSystemComponent.
     * @return A StateOfSystemComponent instance.
     */
    public static StateOfSystemComponent createStateOfSystemComponent(final String id) {
        return new StateOfSystemComponentImpl(id);
    }

    /**
     * Create a System with an String.
     *
     * @param id ID of the System.
     * @return A System instance.
     */
    public static uk.gov.gchq.hqdm.model.System createSystem(final String id) {
        return new SystemImpl(id);
    }

    /**
     * Create a SystemComponent with an String.
     *
     * @param id ID of the SystemComponent.
     * @return A SystemComponent instance.
     */
    public static SystemComponent createSystemComponent(final String id) {
        return new SystemComponentImpl(id);
    }

    /**
     * Create a Thing with an String.
     *
     * @param id ID of the Thing.
     * @return A Thing instance.
     */
    public static Thing createThing(final String id) {
        return new ThingImpl(id);
    }

    /**
     * Create a TransferOfOwnership with an String.
     *
     * @param id ID of the TransferOfOwnership.
     * @return A TransferOfOwnership instance.
     */
    public static TransferOfOwnership createTransferOfOwnership(final String id) {
        return new TransferOfOwnershipImpl(id);
    }

    /**
     * Create a TransferOfOwnershipOfMoney with an String.
     *
     * @param id ID of the TransferOfOwnershipOfMoney.
     * @return A TransferOfOwnershipOfMoney instance.
     */
    public static TransferOfOwnershipOfMoney createTransferOfOwnershipOfMoney(final String id) {
        return new TransferOfOwnershipOfMoneyImpl(id);
    }

    /**
     * Create a Transferee with an String.
     *
     * @param id ID of the Transferee.
     * @return A Transferee instance.
     */
    public static Transferee createTransferee(final String id) {
        return new TransfereeImpl(id);
    }

    /**
     * Create a Transferor with an String.
     *
     * @param id ID of the Transferor.
     * @return A Transferor instance.
     */
    public static Transferor createTransferor(final String id) {
        return new TransferorImpl(id);
    }
}
