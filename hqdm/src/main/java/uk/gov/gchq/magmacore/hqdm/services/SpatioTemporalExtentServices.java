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

package uk.gov.gchq.magmacore.hqdm.services;

import uk.gov.gchq.magmacore.hqdm.model.*;
import uk.gov.gchq.magmacore.hqdm.model.System;
import uk.gov.gchq.magmacore.hqdm.model.impl.*;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * Services for creating SpatioTemporalExtent objects.
 */
public class SpatioTemporalExtentServices {

    /**
     * Create a {@link AbstractObject} with an String.
     *
     * @param id ID of the AbstractObject.
     * @return A AbstractObject instance.
     */
    public static AbstractObject createAbstractObject(final IRI id) {
        final AbstractObject result = new AbstractObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ABSTRACT_OBJECT);
        return result;
    }

    /**
     * Create a {@link AcceptanceOfOffer} with an String.
     *
     * @param id ID of the AcceptanceOfOffer.
     * @return A AcceptanceOfOffer instance.
     */
    public static AcceptanceOfOffer createAcceptanceOfOffer(final IRI id) {
        final AcceptanceOfOffer result = new AcceptanceOfOfferImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ACCEPTANCE_OF_OFFER);
        return result;
    }

    /**
     * Create a {@link AcceptanceOfOfferForGoods} with an String.
     *
     * @param id ID of the AcceptanceOfOfferForGoods.
     * @return A AcceptanceOfOfferForGoods instance.
     */
    public static AcceptanceOfOfferForGoods createAcceptanceOfOfferForGoods(final IRI id) {
        final AcceptanceOfOfferForGoods result = new AcceptanceOfOfferForGoodsImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ACCEPTANCE_OF_OFFER_FOR_GOODS);
        return result;
    }

    /**
     * Create a {@link Activity} with an String.
     *
     * @param id ID of the Activity.
     * @return A Activity instance.
     */
    public static Activity createActivity(final IRI id) {
        final Activity result = new ActivityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ACTIVITY);
        return result;
    }

    /**
     * Create a {@link AgreeContract} with an String.
     *
     * @param id ID of the AgreeContract.
     * @return A AgreeContract instance.
     */
    public static AgreeContract createAgreeContract(final IRI id) {
        final AgreeContract result = new AgreeContractImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGREE_CONTRACT);
        return result;
    }

    /**
     * Create a {@link AgreementExecution} with an String.
     *
     * @param id ID of the AgreementExecution.
     * @return A AgreementExecution instance.
     */
    public static AgreementExecution createAgreementExecution(final IRI id) {
        final AgreementExecution result = new AgreementExecutionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGREEMENT_EXECUTION);
        return result;
    }

    /**
     * Create a {@link AgreementProcess} with an String.
     *
     * @param id ID of the AgreementProcess.
     * @return A AgreementProcess instance.
     */
    public static AgreementProcess createAgreementProcess(final IRI id) {
        final AgreementProcess result = new AgreementProcessImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGREEMENT_PROCESS);
        return result;
    }

    /**
     * Create a {@link AmountOfMoney} with an String.
     *
     * @param id ID of the AmountOfMoney.
     * @return A AmountOfMoney instance.
     */
    public static AmountOfMoney createAmountOfMoney(final IRI id) {
        final AmountOfMoney result = new AmountOfMoneyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AMOUNT_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link Asset} with an String.
     *
     * @param id ID of the Asset.
     * @return A Asset instance.
     */
    public static Asset createAsset(final IRI id) {
        final Asset result = new AssetImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ASSET);
        return result;
    }

    /**
     * Create a {@link Association} with an String.
     *
     * @param id ID of the Association.
     * @return A Association instance.
     */
    public static Association createAssociation(final IRI id) {
        final Association result = new AssociationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link BeginningOfOwnership} with an String.
     *
     * @param id ID of the BeginningOfOwnership.
     * @return A BeginningOfOwnership instance.
     */
    public static BeginningOfOwnership createBeginningOfOwnership(final IRI id) {
        final BeginningOfOwnership result = new BeginningOfOwnershipImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BEGINNING_OF_OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link BiologicalObject} with an String.
     *
     * @param id ID of the BiologicalObject.
     * @return A BiologicalObject instance.
     */
    public static BiologicalObject createBiologicalObject(final IRI id) {
        final BiologicalObject result = new BiologicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link BiologicalSystem} with an String.
     *
     * @param id ID of the BiologicalSystem.
     * @return A BiologicalSystem instance.
     */
    public static BiologicalSystem createBiologicalSystem(final IRI id) {
        final BiologicalSystem result = new BiologicalSystemImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link BiologicalSystemComponent} with an String.
     *
     * @param id ID of the BiologicalSystemComponent.
     * @return A BiologicalSystemComponent instance.
     */
    public static BiologicalSystemComponent createBiologicalSystemComponent(final IRI id) {
        final BiologicalSystemComponent result = new BiologicalSystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ContractExecution} with an String.
     *
     * @param id ID of the ContractExecution.
     * @return A ContractExecution instance.
     */
    public static ContractExecution createContractExecution(final IRI id) {
        final ContractExecution result = new ContractExecutionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CONTRACT_EXECUTION);
        return result;
    }

    /**
     * Create a {@link ContractProcess} with an String.
     *
     * @param id ID of the ContractProcess.
     * @return A ContractProcess instance.
     */
    public static ContractProcess createContractProcess(final IRI id) {
        final ContractProcess result = new ContractProcessImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CONTRACT_PROCESS);
        return result;
    }

    /**
     * Create a {@link Currency} with an String.
     *
     * @param id ID of the Currency.
     * @return A Currency instance.
     */
    public static Currency createCurrency(final IRI id) {
        final Currency result = new CurrencyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CURRENCY);
        return result;
    }

    /**
     * Create a {@link Employee} with an String.
     *
     * @param id ID of the Employee.
     * @return A Employee instance.
     */
    public static Employee createEmployee(final IRI id) {
        final Employee result = new EmployeeImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EMPLOYEE);
        return result;
    }

    /**
     * Create a {@link Employer} with an String.
     *
     * @param id ID of the Employer.
     * @return A Employer instance.
     */
    public static Employer createEmployer(final IRI id) {
        final Employer result = new EmployerImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EMPLOYER);
        return result;
    }

    /**
     * Create a {@link Employment} with an String.
     *
     * @param id ID of the Employment.
     * @return A Employment instance.
     */
    public static Employment createEmployment(final IRI id) {
        final Employment result = new EmploymentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EMPLOYMENT);
        return result;
    }

    /**
     * Create a {@link EndingOfOwnership} with an String.
     *
     * @param id ID of the EndingOfOwnership.
     * @return A EndingOfOwnership instance.
     */
    public static EndingOfOwnership createEndingOfOwnership(final IRI id) {
        final EndingOfOwnership result = new EndingOfOwnershipImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ENDING_OF_OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link Event} with an String.
     *
     * @param id ID of the Event.
     * @return A Event instance.
     */
    public static Event createEvent(final IRI id) {
        final Event result = new EventImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EVENT);
        return result;
    }

    /**
     * Create a {@link ExchangeOfGoodsAndMoney} with an String.
     *
     * @param id ID of the ExchangeOfGoodsAndMoney.
     * @return A ExchangeOfGoodsAndMoney instance.
     */
    public static ExchangeOfGoodsAndMoney createExchangeOfGoodsAndMoney(final IRI id) {
        final ExchangeOfGoodsAndMoney result = new ExchangeOfGoodsAndMoneyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EXCHANGE_OF_GOODS_AND_MONEY);
        return result;
    }

    /**
     * Create a {@link FunctionalObject} with an String.
     *
     * @param id ID of the FunctionalObject.
     * @return A FunctionalObject instance.
     */
    public static FunctionalObject createFunctionalObject(final IRI id) {
        final FunctionalObject result = new FunctionalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link FunctionalSystem} with an String.
     *
     * @param id ID of the FunctionalSystem.
     * @return A FunctionalSystem instance.
     */
    public static FunctionalSystem createFunctionalSystem(final IRI id) {
        final FunctionalSystem result = new FunctionalSystemImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTIONAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link FunctionalSystemComponent} with an String.
     *
     * @param id ID of the FunctionalSystemComponent.
     * @return A FunctionalSystemComponent instance.
     */
    public static FunctionalSystemComponent createFunctionalSystemComponent(final IRI id) {
        final FunctionalSystemComponent result = new FunctionalSystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link IdentificationOfPhysicalQuantity} with an String.
     *
     * @param id ID of the IdentificationOfPhysicalQuantity.
     * @return A IdentificationOfPhysicalQuantity instance.
     */
    public static IdentificationOfPhysicalQuantity createIdentificationOfPhysicalQuantity(final IRI id) {
        final IdentificationOfPhysicalQuantity result = new IdentificationOfPhysicalQuantityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.IDENTIFICATION_OF_PHYSICAL_QUANTITY);
        return result;
    }

    /**
     * Create a {@link InPlaceBiologicalComponent} with an String.
     *
     * @param id ID of the InPlaceBiologicalComponent.
     * @return A InPlaceBiologicalComponent instance.
     */
    public static InPlaceBiologicalComponent createInPlaceBiologicalComponent(final IRI id) {
        final InPlaceBiologicalComponent result = new InPlaceBiologicalComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.IN_PLACE_BIOLOGICAL_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Individual} with an String.
     *
     * @param id ID of the Individual.
     * @return A Individual instance.
     */
    public static Individual createIndividual(final IRI id) {
        final Individual result = new IndividualImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INDIVIDUAL);
        return result;
    }

    /**
     * Create a {@link InstalledFunctionalSystemComponent} with an String.
     *
     * @param id ID of the InstalledFunctionalSystemComponent.
     * @return A InstalledFunctionalSystemComponent instance.
     */
    public static InstalledFunctionalSystemComponent createInstalledFunctionalSystemComponent(final IRI id) {
        final InstalledFunctionalSystemComponent result = new InstalledFunctionalSystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link InstalledObject} with an String.
     *
     * @param id ID of the InstalledObject.
     * @return A InstalledObject instance.
     */
    public static InstalledObject createInstalledObject(final IRI id) {
        final InstalledObject result = new InstalledObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INSTALLED_OBJECT);
        return result;
    }

    /**
     * Create a {@link IntentionallyConstructedObject} with an String.
     *
     * @param id ID of the IntentionallyConstructedObject.
     * @return A IntentionallyConstructedObject instance.
     */
    public static IntentionallyConstructedObject createIntentionallyConstructedObject(final IRI id) {
        final IntentionallyConstructedObject result = new IntentionallyConstructedObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link LanguageCommunity} with an String.
     *
     * @param id ID of the LanguageCommunity.
     * @return A LanguageCommunity instance.
     */
    public static LanguageCommunity createLanguageCommunity(final IRI id) {
        final LanguageCommunity result = new LanguageCommunityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.LANGUAGE_COMMUNITY);
        return result;
    }

    /**
     * Create a {@link MoneyAsset} with an String.
     *
     * @param id ID of the MoneyAsset.
     * @return A MoneyAsset instance.
     */
    public static MoneyAsset createMoneyAsset(final IRI id) {
        final MoneyAsset result = new MoneyAssetImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.MONEY_ASSET);
        return result;
    }

    /**
     * Create a {@link Offer} with an String.
     *
     * @param id ID of the Offer.
     * @return A Offer instance.
     */
    public static Offer createOffer(final IRI id) {
        final Offer result = new OfferImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFER);
        return result;
    }

    /**
     * Create a {@link OfferAndAcceptanceForGoods} with an String.
     *
     * @param id ID of the OfferAndAcceptanceForGoods.
     * @return A OfferAndAcceptanceForGoods instance.
     */
    public static OfferAndAcceptanceForGoods createOfferAndAcceptanceForGoods(final IRI id) {
        final OfferAndAcceptanceForGoods result = new OfferAndAcceptanceForGoodsImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFER_AND_ACCEPTANCE_FOR_GOODS);
        return result;
    }

    /**
     * Create a {@link OfferForGoods} with an String.
     *
     * @param id ID of the OfferForGoods.
     * @return A OfferForGoods instance.
     */
    public static OfferForGoods createOfferForGoods(final IRI id) {
        final OfferForGoods result = new OfferForGoodsImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFER_FOR_GOODS);
        return result;
    }

    /**
     * Create a {@link Offering} with an String.
     *
     * @param id ID of the Offering.
     * @return A Offering instance.
     */
    public static Offering createOffering(final IRI id) {
        final Offering result = new OfferingImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFERING);
        return result;
    }

    /**
     * Create a {@link OrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the OrdinaryBiologicalObject.
     * @return A OrdinaryBiologicalObject instance.
     */
    public static OrdinaryBiologicalObject createOrdinaryBiologicalObject(final IRI id) {
        final OrdinaryBiologicalObject result = new OrdinaryBiologicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link OrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the OrdinaryFunctionalObject.
     * @return A OrdinaryFunctionalObject instance.
     */
    public static OrdinaryFunctionalObject createOrdinaryFunctionalObject(final IRI id) {
        final OrdinaryFunctionalObject result = new OrdinaryFunctionalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link OrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the OrdinaryPhysicalObject.
     * @return A OrdinaryPhysicalObject instance.
     */
    public static OrdinaryPhysicalObject createOrdinaryPhysicalObject(final IRI id) {
        final OrdinaryPhysicalObject result = new OrdinaryPhysicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link Organization} with an String.
     *
     * @param id ID of the Organization.
     * @return A Organization instance.
     */
    public static Organization createOrganization(final IRI id) {
        final Organization result = new OrganizationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link OrganizationComponent} with an String.
     *
     * @param id ID of the OrganizationComponent.
     * @return A OrganizationComponent instance.
     */
    public static OrganizationComponent createOrganizationComponent(final IRI id) {
        final OrganizationComponent result = new OrganizationComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Owner} with an String.
     *
     * @param id ID of the Owner.
     * @return A Owner instance.
     */
    public static Owner createOwner(final IRI id) {
        final Owner result = new OwnerImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OWNER);
        return result;
    }

    /**
     * Create a {@link Ownership} with an String.
     *
     * @param id ID of the Ownership.
     * @return A Ownership instance.
     */
    public static Ownership createOwnership(final IRI id) {
        final Ownership result = new OwnershipImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link Participant} with an String.
     *
     * @param id ID of the Participant.
     * @return A Participant instance.
     */
    public static Participant createParticipant(final IRI id) {
        final Participant result = new ParticipantImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        return result;
    }

    /**
     * Create a {@link ParticipantInActivityOrAssociation} with an String.
     *
     * @param id ID of the ParticipantInActivityOrAssociation.
     * @return A ParticipantInActivityOrAssociation instance.
     */
    public static ParticipantInActivityOrAssociation createParticipantInActivityOrAssociation(final IRI id) {
        final ParticipantInActivityOrAssociation result = new ParticipantInActivityOrAssociationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT_IN_ACTIVITY_OR_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link Party} with an String.
     *
     * @param id ID of the Party.
     * @return A Party instance.
     */
    public static Party createParty(final IRI id) {
        final Party result = new PartyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PARTY);
        return result;
    }

    /**
     * Create a {@link PeriodOfTime} with an String.
     *
     * @param id ID of the PeriodOfTime.
     * @return A PeriodOfTime instance.
     */
    public static PeriodOfTime createPeriodOfTime(final IRI id) {
        final PeriodOfTime result = new PeriodOfTimeImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PERIOD_OF_TIME);
        return result;
    }

    /**
     * Create a {@link Person} with an String.
     *
     * @param id ID of the Person.
     * @return A Person instance.
     */
    public static Person createPerson(final IRI id) {
        final Person result = new PersonImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        return result;
    }

    /**
     * Create a {@link PersonInPosition} with an String.
     *
     * @param id ID of the PersonInPosition.
     * @return A PersonInPosition instance.
     */
    public static PersonInPosition createPersonInPosition(final IRI id) {
        final PersonInPosition result = new PersonInPositionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PERSON_IN_POSITION);
        return result;
    }

    /**
     * Create a {@link PhysicalObject} with an String.
     *
     * @param id ID of the PhysicalObject.
     * @return A PhysicalObject instance.
     */
    public static PhysicalObject createPhysicalObject(final IRI id) {
        final PhysicalObject result = new PhysicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link PhysicalProperty} with an String.
     *
     * @param id ID of the PhysicalProperty.
     * @return A PhysicalProperty instance.
     */
    public static PhysicalProperty createPhysicalProperty(final IRI id) {
        final PhysicalProperty result = new PhysicalPropertyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_PROPERTY);
        return result;
    }

    /**
     * Create a {@link PhysicalPropertyRange} with an String.
     *
     * @param id ID of the PhysicalPropertyRange.
     * @return A PhysicalPropertyRange instance.
     */
    public static PhysicalPropertyRange createPhysicalPropertyRange(final IRI id) {
        final PhysicalPropertyRange result = new PhysicalPropertyRangeImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_PROPERTY_RANGE);
        return result;
    }

    /**
     * Create a {@link PhysicalQuantity} with an String.
     *
     * @param id ID of the PhysicalQuantity.
     * @return A PhysicalQuantity instance.
     */
    public static PhysicalQuantity createPhysicalQuantity(final IRI id) {
        final PhysicalQuantity result = new PhysicalQuantityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_QUANTITY);
        return result;
    }

    /**
     * Create a {@link PhysicalQuantityRange} with an String.
     *
     * @param id ID of the PhysicalQuantityRange.
     * @return A PhysicalQuantityRange instance.
     */
    public static PhysicalQuantityRange createPhysicalQuantityRange(final IRI id) {
        final PhysicalQuantityRange result = new PhysicalQuantityRangeImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_QUANTITY_RANGE);
        return result;
    }

    /**
     * Create a {@link Plan} with an String.
     *
     * @param id ID of the Plan.
     * @return A Plan instance.
     */
    public static Plan createPlan(final IRI id) {
        final Plan result = new PlanImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PLAN);
        return result;
    }

    /**
     * Create a {@link PointInTime} with an String.
     *
     * @param id ID of the PointInTime.
     * @return A PointInTime instance.
     */
    public static PointInTime createPointInTime(final IRI id) {
        final PointInTime result = new PointInTimeImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        return result;
    }

    /**
     * Create a {@link Position} with an String.
     *
     * @param id ID of the Position.
     * @return A Position instance.
     */
    public static Position createPosition(final IRI id) {
        final Position result = new PositionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.POSITION);
        return result;
    }

    /**
     * Create a {@link PossibleWorld} with an String.
     *
     * @param id ID of the PossibleWorld.
     * @return A PossibleWorld instance.
     */
    public static PossibleWorld createPossibleWorld(final IRI id) {
        final PossibleWorld result = new PossibleWorldImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.POSSIBLE_WORLD);
        return result;
    }

    /**
     * Create a {@link Price} with an String.
     *
     * @param id ID of the Price.
     * @return A Price instance.
     */
    public static Price createPrice(final IRI id) {
        final Price result = new PriceImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PRICE);
        return result;
    }

    /**
     * Create a {@link ProductBrand} with an String.
     *
     * @param id ID of the ProductBrand.
     * @return A ProductBrand instance.
     */
    public static ProductBrand createProductBrand(final IRI id) {
        final ProductBrand result = new ProductBrandImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PRODUCT_BRAND);
        return result;
    }

    /**
     * Create a {@link ProductOffering} with an String.
     *
     * @param id ID of the ProductOffering.
     * @return A ProductOffering instance.
     */
    public static ProductOffering createProductOffering(final IRI id) {
        final ProductOffering result = new ProductOfferingImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PRODUCT_OFFERING);
        return result;
    }

    /**
     * Create a {@link ReachingAgreement} with an String.
     *
     * @param id ID of the ReachingAgreement.
     * @return A ReachingAgreement instance.
     */
    public static ReachingAgreement createReachingAgreement(final IRI id) {
        final ReachingAgreement result = new ReachingAgreementImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REACHING_AGREEMENT);
        return result;
    }

    /**
     * Create a {@link RecognizingLanguageCommunity} with an String.
     *
     * @param id ID of the RecognizingLanguageCommunity.
     * @return A RecognizingLanguageCommunity instance.
     */
    public static RecognizingLanguageCommunity createRecognizingLanguageCommunity(final IRI id) {
        final RecognizingLanguageCommunity result = new RecognizingLanguageCommunityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY);
        return result;
    }

    /**
     * Create a {@link RepresentationBySign} with an String.
     *
     * @param id ID of the RepresentationBySign.
     * @return A RepresentationBySign instance.
     */
    public static RepresentationBySign createRepresentationBySign(final IRI id) {
        final RepresentationBySign result = new RepresentationBySignImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN);
        return result;
    }

    /**
     * Create a {@link Requirement} with an String.
     *
     * @param id ID of the Requirement.
     * @return A Requirement instance.
     */
    public static Requirement createRequirement(final IRI id) {
        final Requirement result = new RequirementImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REQUIREMENT);
        return result;
    }

    /**
     * Create a {@link RequirementSpecification} with an String.
     *
     * @param id ID of the RequirementSpecification.
     * @return A RequirementSpecification instance.
     */
    public static RequirementSpecification createRequirementSpecification(final IRI id) {
        final RequirementSpecification result = new RequirementSpecificationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REQUIREMENT_SPECIFICATION);
        return result;
    }

    /**
     * Create a {@link SaleOfGoods} with an String.
     *
     * @param id ID of the SaleOfGoods.
     * @return A SaleOfGoods instance.
     */
    public static SaleOfGoods createSaleOfGoods(final IRI id) {
        final SaleOfGoods result = new SaleOfGoodsImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALE_OF_GOODS);
        return result;
    }

    /**
     * Create a {@link SalesProduct} with an String.
     *
     * @param id ID of the SalesProduct.
     * @return A SalesProduct instance.
     */
    public static SalesProduct createSalesProduct(final IRI id) {
        final SalesProduct result = new SalesProductImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALES_PRODUCT);
        return result;
    }

    /**
     * Create a {@link SalesProductInstance} with an String.
     *
     * @param id ID of the SalesProductInstance.
     * @return A SalesProductInstance instance.
     */
    public static SalesProductInstance createSalesProductInstance(final IRI id) {
        final SalesProductInstance result = new SalesProductInstanceImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALES_PRODUCT_INSTANCE);
        return result;
    }

    /**
     * Create a {@link SalesProductVersion} with an String.
     *
     * @param id ID of the SalesProductVersion.
     * @return A SalesProductVersion instance.
     */
    public static SalesProductVersion createSalesProductVersion(final IRI id) {
        final SalesProductVersion result = new SalesProductVersionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALES_PRODUCT_VERSION);
        return result;
    }

    /**
     * Create a {@link Sign} with an String.
     *
     * @param id ID of the Sign.
     * @return A Sign instance.
     */
    public static Sign createSign(final IRI id) {
        final Sign result = new SignImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SIGN);
        return result;
    }

    /**
     * Create a {@link SociallyConstructedActivity} with an String.
     *
     * @param id ID of the SociallyConstructedActivity.
     * @return A SociallyConstructedActivity instance.
     */
    public static SociallyConstructedActivity createSociallyConstructedActivity(final IRI id) {
        final SociallyConstructedActivity result = new SociallyConstructedActivityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SOCIALLY_CONSTRUCTED_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link SociallyConstructedObject} with an String.
     *
     * @param id ID of the SociallyConstructedObject.
     * @return A SociallyConstructedObject instance.
     */
    public static SociallyConstructedObject createSociallyConstructedObject(final IRI id) {
        final SociallyConstructedObject result = new SociallyConstructedObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link SpatioTemporalExtent} with an String.
     *
     * @param id ID of the SpatioTemporalExtent.
     * @return A SpatioTemporalExtent instance.
     */
    public static SpatioTemporalExtent createSpatioTemporalExtent(final IRI id) {
        final SpatioTemporalExtent result = new SpatioTemporalExtentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SPATIO_TEMPORAL_EXTENT);
        return result;
    }

    /**
     * Create a {@link State} with an String.
     *
     * @param id ID of the State.
     * @return A State instance.
     */
    public static State createState(final IRI id) {
        final State result = new StateImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE);
        return result;
    }

    /**
     * Create a {@link StateOfActivity} with an String.
     *
     * @param id ID of the StateOfActivity.
     * @return A StateOfActivity instance.
     */
    public static StateOfActivity createStateOfActivity(final IRI id) {
        final StateOfActivity result = new StateOfActivityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link StateOfAmountOfMoney} with an String.
     *
     * @param id ID of the StateOfAmountOfMoney.
     * @return A StateOfAmountOfMoney instance.
     */
    public static StateOfAmountOfMoney createStateOfAmountOfMoney(final IRI id) {
        final StateOfAmountOfMoney result = new StateOfAmountOfMoneyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_AMOUNT_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link StateOfAssociation} with an String.
     *
     * @param id ID of the StateOfAssociation.
     * @return A StateOfAssociation instance.
     */
    public static StateOfAssociation createStateOfAssociation(final IRI id) {
        final StateOfAssociation result = new StateOfAssociationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link StateOfBiologicalObject} with an String.
     *
     * @param id ID of the StateOfBiologicalObject.
     * @return A StateOfBiologicalObject instance.
     */
    public static StateOfBiologicalObject createStateOfBiologicalObject(final IRI id) {
        final StateOfBiologicalObject result = new StateOfBiologicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfBiologicalSystem} with an String.
     *
     * @param id ID of the StateOfBiologicalSystem.
     * @return A StateOfBiologicalSystem instance.
     */
    public static StateOfBiologicalSystem createStateOfBiologicalSystem(final IRI id) {
        final StateOfBiologicalSystem result = new StateOfBiologicalSystemImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link StateOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the StateOfBiologicalSystemComponent.
     * @return A StateOfBiologicalSystemComponent instance.
     */
    public static StateOfBiologicalSystemComponent createStateOfBiologicalSystemComponent(final IRI id) {
        final StateOfBiologicalSystemComponent result = new StateOfBiologicalSystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link StateOfFunctionalObject} with an String.
     *
     * @param id ID of the StateOfFunctionalObject.
     * @return A StateOfFunctionalObject instance.
     */
    public static StateOfFunctionalObject createStateOfFunctionalObject(final IRI id) {
        final StateOfFunctionalObject result = new StateOfFunctionalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfFunctionalSystem} with an String.
     *
     * @param id ID of the StateOfFunctionalSystem.
     * @return A StateOfFunctionalSystem instance.
     */
    public static StateOfFunctionalSystem createStateOfFunctionalSystem(final IRI id) {
        final StateOfFunctionalSystem result = new StateOfFunctionalSystemImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link StateOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the StateOfFunctionalSystemComponent.
     * @return A StateOfFunctionalSystemComponent instance.
     */
    public static StateOfFunctionalSystemComponent createStateOfFunctionalSystemComponent(final IRI id) {
        final StateOfFunctionalSystemComponent result = new StateOfFunctionalSystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link StateOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the StateOfIntentionallyConstructedObject.
     * @return A StateOfIntentionallyConstructedObject instance.
     */
    public static StateOfIntentionallyConstructedObject createStateOfIntentionallyConstructedObject(final IRI id) {
        final StateOfIntentionallyConstructedObject result = new StateOfIntentionallyConstructedObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfLanguageCommunity} with an String.
     *
     * @param id ID of the StateOfLanguageCommunity.
     * @return A StateOfLanguageCommunity instance.
     */
    public static StateOfLanguageCommunity createStateOfLanguageCommunity(final IRI id) {
        final StateOfLanguageCommunity result = new StateOfLanguageCommunityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_LANGUAGE_COMMUNITY);
        return result;
    }

    /**
     * Create a {@link StateOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the StateOfOrdinaryBiologicalObject.
     * @return A StateOfOrdinaryBiologicalObject instance.
     */
    public static StateOfOrdinaryBiologicalObject createStateOfOrdinaryBiologicalObject(final IRI id) {
        final StateOfOrdinaryBiologicalObject result = new StateOfOrdinaryBiologicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the StateOfOrdinaryFunctionalObject.
     * @return A StateOfOrdinaryFunctionalObject instance.
     */
    public static StateOfOrdinaryFunctionalObject createStateOfOrdinaryFunctionalObject(final IRI id) {
        final StateOfOrdinaryFunctionalObject result = new StateOfOrdinaryFunctionalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the StateOfOrdinaryPhysicalObject.
     * @return A StateOfOrdinaryPhysicalObject instance.
     */
    public static StateOfOrdinaryPhysicalObject createStateOfOrdinaryPhysicalObject(final IRI id) {
        final StateOfOrdinaryPhysicalObject result = new StateOfOrdinaryPhysicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfOrganization} with an String.
     *
     * @param id ID of the StateOfOrganization.
     * @return A StateOfOrganization instance.
     */
    public static StateOfOrganization createStateOfOrganization(final IRI id) {
        final StateOfOrganization result = new StateOfOrganizationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link StateOfOrganizationComponent} with an String.
     *
     * @param id ID of the StateOfOrganizationComponent.
     * @return A StateOfOrganizationComponent instance.
     */
    public static StateOfOrganizationComponent createStateOfOrganizationComponent(final IRI id) {
        final StateOfOrganizationComponent result = new StateOfOrganizationComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link StateOfParty} with an String.
     *
     * @param id ID of the StateOfParty.
     * @return A StateOfParty instance.
     */
    public static StateOfParty createStateOfParty(final IRI id) {
        final StateOfParty result = new StateOfPartyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PARTY);
        return result;
    }

    /**
     * Create a {@link StateOfPerson} with an String.
     *
     * @param id ID of the StateOfPerson.
     * @return A StateOfPerson instance.
     */
    public static StateOfPerson createStateOfPerson(final IRI id) {
        final StateOfPerson result = new StateOfPersonImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        return result;
    }

    /**
     * Create a {@link StateOfPhysicalObject} with an String.
     *
     * @param id ID of the StateOfPhysicalObject.
     * @return A StateOfPhysicalObject instance.
     */
    public static StateOfPhysicalObject createStateOfPhysicalObject(final IRI id) {
        final StateOfPhysicalObject result = new StateOfPhysicalObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfPosition} with an String.
     *
     * @param id ID of the StateOfPosition.
     * @return A StateOfPosition instance.
     */
    public static StateOfPosition createStateOfPosition(final IRI id) {
        final StateOfPosition result = new StateOfPositionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_POSITION);
        return result;
    }

    /**
     * Create a {@link StateOfSalesProductInstance} with an String.
     *
     * @param id ID of the StateOfSalesProductInstance.
     * @return A StateOfSalesProductInstance instance.
     */
    public static StateOfSalesProductInstance createStateOfSalesProductInstance(final IRI id) {
        final StateOfSalesProductInstance result = new StateOfSalesProductInstanceImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SALES_PRODUCT_INSTANCE);
        return result;
    }

    /**
     * Create a {@link StateOfSign} with an String.
     *
     * @param id ID of the StateOfSign.
     * @return A StateOfSign instance.
     */
    public static StateOfSign createStateOfSign(final IRI id) {
        final StateOfSign result = new StateOfSignImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN);
        return result;
    }

    /**
     * Create a {@link StateOfSociallyConstructedActivity} with an String.
     *
     * @param id ID of the StateOfSociallyConstructedActivity.
     * @return A StateOfSociallyConstructedActivity instance.
     */
    public static StateOfSociallyConstructedActivity createStateOfSociallyConstructedActivity(final IRI id) {
        final StateOfSociallyConstructedActivity result = new StateOfSociallyConstructedActivityImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link StateOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the StateOfSociallyConstructedObject.
     * @return A StateOfSociallyConstructedObject instance.
     */
    public static StateOfSociallyConstructedObject createStateOfSociallyConstructedObject(final IRI id) {
        final StateOfSociallyConstructedObject result = new StateOfSociallyConstructedObjectImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfSystem} with an String.
     *
     * @param id ID of the StateOfSystem.
     * @return A StateOfSystem instance.
     */
    public static StateOfSystem createStateOfSystem(final IRI id) {
        final StateOfSystem result = new StateOfSystemImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SYSTEM);
        return result;
    }

    /**
     * Create a {@link StateOfSystemComponent} with an String.
     *
     * @param id ID of the StateOfSystemComponent.
     * @return A StateOfSystemComponent instance.
     */
    public static StateOfSystemComponent createStateOfSystemComponent(final IRI id) {
        final StateOfSystemComponent result = new StateOfSystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link uk.gov.gchq.magmacore.hqdm.model.System} with an String.
     *
     * @param id ID of the System.
     * @return A System instance.
     */
    public static uk.gov.gchq.magmacore.hqdm.model.System createSystem(final IRI id) {
        final System result = new SystemImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SYSTEM);
        return result;
    }

    /**
     * Create a {@link SystemComponent} with an String.
     *
     * @param id ID of the SystemComponent.
     * @return A SystemComponent instance.
     */
    public static SystemComponent createSystemComponent(final IRI id) {
        final SystemComponent result = new SystemComponentImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Thing} with an String.
     *
     * @param id ID of the Thing.
     * @return A Thing instance.
     */
    public static Thing createThing(final IRI id) {
        return new ThingImpl(id);
    }

    /**
     * Create a {@link TransferOfOwnership} with an String.
     *
     * @param id ID of the TransferOfOwnership.
     * @return A TransferOfOwnership instance.
     */
    public static TransferOfOwnership createTransferOfOwnership(final IRI id) {
        final TransferOfOwnership result = new TransferOfOwnershipImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFER_OF_OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link TransferOfOwnershipOfMoney} with an String.
     *
     * @param id ID of the TransferOfOwnershipOfMoney.
     * @return A TransferOfOwnershipOfMoney instance.
     */
    public static TransferOfOwnershipOfMoney createTransferOfOwnershipOfMoney(final IRI id) {
        final TransferOfOwnershipOfMoney result = new TransferOfOwnershipOfMoneyImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFER_OF_OWNERSHIP_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link Transferee} with an String.
     *
     * @param id ID of the Transferee.
     * @return A Transferee instance.
     */
    public static Transferee createTransferee(final IRI id) {
        final Transferee result = new TransfereeImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFEREE);
        return result;
    }

    /**
     * Create a {@link Transferor} with an String.
     *
     * @param id ID of the Transferor.
     * @return A Transferor instance.
     */
    public static Transferor createTransferor(final IRI id) {
        final Transferor result = new TransferorImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFEROR);
        return result;
    }
}
