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

package uk.gov.gchq.magmacore.hqdm.rdfservices;

import uk.gov.gchq.magmacore.hqdm.model.*;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Services for creating SpatioTemporalExtent objects.
 */
public class RdfSpatioTemporalExtentServices {

    /**
     * Create a {@link AbstractObject} with an String.
     *
     * @param id ID of the AbstractObject.
     * @return A AbstractObject instance.
     */
    public static AbstractObject createAbstractObject(final String id) {
        final AbstractObject result = SpatioTemporalExtentServices.createAbstractObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ABSTRACT_OBJECT);
        return result;
    }

    /**
     * Create a {@link AcceptanceOfOffer} with an String.
     *
     * @param id ID of the AcceptanceOfOffer.
     * @return A AcceptanceOfOffer instance.
     */
    public static AcceptanceOfOffer createAcceptanceOfOffer(final String id) {
        final AcceptanceOfOffer result = SpatioTemporalExtentServices.createAcceptanceOfOffer(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ACCEPTANCE_OF_OFFER);
        return result;
    }

    /**
     * Create a {@link AcceptanceOfOfferForGoods} with an String.
     *
     * @param id ID of the AcceptanceOfOfferForGoods.
     * @return A AcceptanceOfOfferForGoods instance.
     */
    public static AcceptanceOfOfferForGoods createAcceptanceOfOfferForGoods(final String id) {
        final AcceptanceOfOfferForGoods result = SpatioTemporalExtentServices.createAcceptanceOfOfferForGoods(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ACCEPTANCE_OF_OFFER_FOR_GOODS);
        return result;
    }

    /**
     * Create a {@link Activity} with an String.
     *
     * @param id ID of the Activity.
     * @return A Activity instance.
     */
    public static Activity createActivity(final String id) {
        final Activity result = SpatioTemporalExtentServices.createActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ACTIVITY);
        return result;
    }

    /**
     * Create a {@link AgreeContract} with an String.
     *
     * @param id ID of the AgreeContract.
     * @return A AgreeContract instance.
     */
    public static AgreeContract createAgreeContract(final String id) {
        final AgreeContract result = SpatioTemporalExtentServices.createAgreeContract(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGREE_CONTRACT);
        return result;
    }

    /**
     * Create a {@link AgreementExecution} with an String.
     *
     * @param id ID of the AgreementExecution.
     * @return A AgreementExecution instance.
     */
    public static AgreementExecution createAgreementExecution(final String id) {
        final AgreementExecution result = SpatioTemporalExtentServices.createAgreementExecution(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGREEMENT_EXECUTION);
        return result;
    }

    /**
     * Create a {@link AgreementProcess} with an String.
     *
     * @param id ID of the AgreementProcess.
     * @return A AgreementProcess instance.
     */
    public static AgreementProcess createAgreementProcess(final String id) {
        final AgreementProcess result = SpatioTemporalExtentServices.createAgreementProcess(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGREEMENT_PROCESS);
        return result;
    }

    /**
     * Create a {@link AmountOfMoney} with an String.
     *
     * @param id ID of the AmountOfMoney.
     * @return A AmountOfMoney instance.
     */
    public static AmountOfMoney createAmountOfMoney(final String id) {
        final AmountOfMoney result = SpatioTemporalExtentServices.createAmountOfMoney(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AMOUNT_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link Asset} with an String.
     *
     * @param id ID of the Asset.
     * @return A Asset instance.
     */
    public static Asset createAsset(final String id) {
        final Asset result = SpatioTemporalExtentServices.createAsset(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ASSET);
        return result;
    }

    /**
     * Create a {@link Association} with an String.
     *
     * @param id ID of the Association.
     * @return A Association instance.
     */
    public static Association createAssociation(final String id) {
        final Association result = SpatioTemporalExtentServices.createAssociation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link BeginningOfOwnership} with an String.
     *
     * @param id ID of the BeginningOfOwnership.
     * @return A BeginningOfOwnership instance.
     */
    public static BeginningOfOwnership createBeginningOfOwnership(final String id) {
        final BeginningOfOwnership result = SpatioTemporalExtentServices.createBeginningOfOwnership(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BEGINNING_OF_OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link BiologicalObject} with an String.
     *
     * @param id ID of the BiologicalObject.
     * @return A BiologicalObject instance.
     */
    public static BiologicalObject createBiologicalObject(final String id) {
        final BiologicalObject result = SpatioTemporalExtentServices.createBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link BiologicalSystem} with an String.
     *
     * @param id ID of the BiologicalSystem.
     * @return A BiologicalSystem instance.
     */
    public static BiologicalSystem createBiologicalSystem(final String id) {
        final BiologicalSystem result = SpatioTemporalExtentServices.createBiologicalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link BiologicalSystemComponent} with an String.
     *
     * @param id ID of the BiologicalSystemComponent.
     * @return A BiologicalSystemComponent instance.
     */
    public static BiologicalSystemComponent createBiologicalSystemComponent(final String id) {
        final BiologicalSystemComponent result = SpatioTemporalExtentServices.createBiologicalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ContractExecution} with an String.
     *
     * @param id ID of the ContractExecution.
     * @return A ContractExecution instance.
     */
    public static ContractExecution createContractExecution(final String id) {
        final ContractExecution result = SpatioTemporalExtentServices.createContractExecution(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CONTRACT_EXECUTION);
        return result;
    }

    /**
     * Create a {@link ContractProcess} with an String.
     *
     * @param id ID of the ContractProcess.
     * @return A ContractProcess instance.
     */
    public static ContractProcess createContractProcess(final String id) {
        final ContractProcess result = SpatioTemporalExtentServices.createContractProcess(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CONTRACT_PROCESS);
        return result;
    }

    /**
     * Create a {@link Currency} with an String.
     *
     * @param id ID of the Currency.
     * @return A Currency instance.
     */
    public static Currency createCurrency(final String id) {
        final Currency result = SpatioTemporalExtentServices.createCurrency(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CURRENCY);
        return result;
    }

    /**
     * Create a {@link Definition} with an String.
     *
     * @param id ID of the Definition.
     * @return A Definition instance.
     */
    public static Definition createDefinition(final String id) {
        final Definition result = SpatioTemporalExtentServices.createDefinition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.DEFINITION);
        return result;
    }

    /**
     * Create a {@link Description} with an String.
     *
     * @param id ID of the Description.
     * @return A Description instance.
     */
    public static Description createDescription(final String id) {
        final Description result = SpatioTemporalExtentServices.createDescription(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.DESCRIPTION);
        return result;
    }

    /**
     * Create a {@link Employee} with an String.
     *
     * @param id ID of the Employee.
     * @return A Employee instance.
     */
    public static Employee createEmployee(final String id) {
        final Employee result = SpatioTemporalExtentServices.createEmployee(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EMPLOYEE);
        return result;
    }

    /**
     * Create a {@link Employer} with an String.
     *
     * @param id ID of the Employer.
     * @return A Employer instance.
     */
    public static Employer createEmployer(final String id) {
        final Employer result = SpatioTemporalExtentServices.createEmployer(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EMPLOYER);
        return result;
    }

    /**
     * Create a {@link Employment} with an String.
     *
     * @param id ID of the Employment.
     * @return A Employment instance.
     */
    public static Employment createEmployment(final String id) {
        final Employment result = SpatioTemporalExtentServices.createEmployment(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EMPLOYMENT);
        return result;
    }

    /**
     * Create a {@link EndingOfOwnership} with an String.
     *
     * @param id ID of the EndingOfOwnership.
     * @return A EndingOfOwnership instance.
     */
    public static EndingOfOwnership createEndingOfOwnership(final String id) {
        final EndingOfOwnership result = SpatioTemporalExtentServices.createEndingOfOwnership(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ENDING_OF_OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link Event} with an String.
     *
     * @param id ID of the Event.
     * @return A Event instance.
     */
    public static Event createEvent(final String id) {
        final Event result = SpatioTemporalExtentServices.createEvent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EVENT);
        return result;
    }

    /**
     * Create a {@link ExchangeOfGoodsAndMoney} with an String.
     *
     * @param id ID of the ExchangeOfGoodsAndMoney.
     * @return A ExchangeOfGoodsAndMoney instance.
     */
    public static ExchangeOfGoodsAndMoney createExchangeOfGoodsAndMoney(final String id) {
        final ExchangeOfGoodsAndMoney result = SpatioTemporalExtentServices.createExchangeOfGoodsAndMoney(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.EXCHANGE_OF_GOODS_AND_MONEY);
        return result;
    }

    /**
     * Create a {@link FunctionalObject} with an String.
     *
     * @param id ID of the FunctionalObject.
     * @return A FunctionalObject instance.
     */
    public static FunctionalObject createFunctionalObject(final String id) {
        final FunctionalObject result = SpatioTemporalExtentServices.createFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link FunctionalSystem} with an String.
     *
     * @param id ID of the FunctionalSystem.
     * @return A FunctionalSystem instance.
     */
    public static FunctionalSystem createFunctionalSystem(final String id) {
        final FunctionalSystem result = SpatioTemporalExtentServices.createFunctionalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTIONAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link FunctionalSystemComponent} with an String.
     *
     * @param id ID of the FunctionalSystemComponent.
     * @return A FunctionalSystemComponent instance.
     */
    public static FunctionalSystemComponent createFunctionalSystemComponent(final String id) {
        final FunctionalSystemComponent result = SpatioTemporalExtentServices.createFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Identification} with an String.
     *
     * @param id ID of the Identification.
     * @return A Identification instance.
     */
    public static Identification createIdentification(final String id) {
        final Identification result = SpatioTemporalExtentServices.createIdentification(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.IDENTIFICATION);
        return result;
    }

    /**
     * Create a {@link IdentificationOfPhysicalQuantity} with an String.
     *
     * @param id ID of the IdentificationOfPhysicalQuantity.
     * @return A IdentificationOfPhysicalQuantity instance.
     */
    public static IdentificationOfPhysicalQuantity createIdentificationOfPhysicalQuantity(final String id) {
        final IdentificationOfPhysicalQuantity result = SpatioTemporalExtentServices
                .createIdentificationOfPhysicalQuantity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.IDENTIFICATION_OF_PHYSICAL_QUANTITY);
        return result;
    }

    /**
     * Create a {@link InPlaceBiologicalComponent} with an String.
     *
     * @param id ID of the InPlaceBiologicalComponent.
     * @return A InPlaceBiologicalComponent instance.
     */
    public static InPlaceBiologicalComponent createInPlaceBiologicalComponent(final String id) {
        final InPlaceBiologicalComponent result = SpatioTemporalExtentServices.createInPlaceBiologicalComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.IN_PLACE_BIOLOGICAL_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Individual} with an String.
     *
     * @param id ID of the Individual.
     * @return A Individual instance.
     */
    public static Individual createIndividual(final String id) {
        final Individual result = SpatioTemporalExtentServices.createIndividual(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INDIVIDUAL);
        return result;
    }

    /**
     * Create a {@link InstalledFunctionalSystemComponent} with an String.
     *
     * @param id ID of the InstalledFunctionalSystemComponent.
     * @return A InstalledFunctionalSystemComponent instance.
     */
    public static InstalledFunctionalSystemComponent createInstalledFunctionalSystemComponent(final String id) {
        final InstalledFunctionalSystemComponent result = SpatioTemporalExtentServices
                .createInstalledFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link InstalledObject} with an String.
     *
     * @param id ID of the InstalledObject.
     * @return A InstalledObject instance.
     */
    public static InstalledObject createInstalledObject(final String id) {
        final InstalledObject result = SpatioTemporalExtentServices.createInstalledObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INSTALLED_OBJECT);
        return result;
    }

    /**
     * Create a {@link IntentionallyConstructedObject} with an String.
     *
     * @param id ID of the IntentionallyConstructedObject.
     * @return A IntentionallyConstructedObject instance.
     */
    public static IntentionallyConstructedObject createIntentionallyConstructedObject(final String id) {
        final IntentionallyConstructedObject result = SpatioTemporalExtentServices
                .createIntentionallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link LanguageCommunity} with an String.
     *
     * @param id ID of the LanguageCommunity.
     * @return A LanguageCommunity instance.
     */
    public static LanguageCommunity createLanguageCommunity(final String id) {
        final LanguageCommunity result = SpatioTemporalExtentServices.createLanguageCommunity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.LANGUAGE_COMMUNITY);
        return result;
    }

    /**
     * Create a {@link MoneyAsset} with an String.
     *
     * @param id ID of the MoneyAsset.
     * @return A MoneyAsset instance.
     */
    public static MoneyAsset createMoneyAsset(final String id) {
        final MoneyAsset result = SpatioTemporalExtentServices.createMoneyAsset(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.MONEY_ASSET);
        return result;
    }

    /**
     * Create a {@link Offer} with an String.
     *
     * @param id ID of the Offer.
     * @return A Offer instance.
     */
    public static Offer createOffer(final String id) {
        final Offer result = SpatioTemporalExtentServices.createOffer(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFER);
        return result;
    }

    /**
     * Create a {@link OfferAndAcceptanceForGoods} with an String.
     *
     * @param id ID of the OfferAndAcceptanceForGoods.
     * @return A OfferAndAcceptanceForGoods instance.
     */
    public static OfferAndAcceptanceForGoods createOfferAndAcceptanceForGoods(final String id) {
        final OfferAndAcceptanceForGoods result = SpatioTemporalExtentServices.createOfferAndAcceptanceForGoods(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFER_AND_ACCEPTANCE_FOR_GOODS);
        return result;
    }

    /**
     * Create a {@link OfferForGoods} with an String.
     *
     * @param id ID of the OfferForGoods.
     * @return A OfferForGoods instance.
     */
    public static OfferForGoods createOfferForGoods(final String id) {
        final OfferForGoods result = SpatioTemporalExtentServices.createOfferForGoods(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFER_FOR_GOODS);
        return result;
    }

    /**
     * Create a {@link Offering} with an String.
     *
     * @param id ID of the Offering.
     * @return A Offering instance.
     */
    public static Offering createOffering(final String id) {
        final Offering result = SpatioTemporalExtentServices.createOffering(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OFFERING);
        return result;
    }

    /**
     * Create a {@link OrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the OrdinaryBiologicalObject.
     * @return A OrdinaryBiologicalObject instance.
     */
    public static OrdinaryBiologicalObject createOrdinaryBiologicalObject(final String id) {
        final OrdinaryBiologicalObject result = SpatioTemporalExtentServices.createOrdinaryBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link OrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the OrdinaryFunctionalObject.
     * @return A OrdinaryFunctionalObject instance.
     */
    public static OrdinaryFunctionalObject createOrdinaryFunctionalObject(final String id) {
        final OrdinaryFunctionalObject result = SpatioTemporalExtentServices.createOrdinaryFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link OrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the OrdinaryPhysicalObject.
     * @return A OrdinaryPhysicalObject instance.
     */
    public static OrdinaryPhysicalObject createOrdinaryPhysicalObject(final String id) {
        final OrdinaryPhysicalObject result = SpatioTemporalExtentServices.createOrdinaryPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link Organization} with an String.
     *
     * @param id ID of the Organization.
     * @return A Organization instance.
     */
    public static Organization createOrganization(final String id) {
        final Organization result = SpatioTemporalExtentServices.createOrganization(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link OrganizationComponent} with an String.
     *
     * @param id ID of the OrganizationComponent.
     * @return A OrganizationComponent instance.
     */
    public static OrganizationComponent createOrganizationComponent(final String id) {
        final OrganizationComponent result = SpatioTemporalExtentServices.createOrganizationComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Owner} with an String.
     *
     * @param id ID of the Owner.
     * @return A Owner instance.
     */
    public static Owner createOwner(final String id) {
        final Owner result = SpatioTemporalExtentServices.createOwner(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OWNER);
        return result;
    }

    /**
     * Create a {@link Ownership} with an String.
     *
     * @param id ID of the Ownership.
     * @return A Ownership instance.
     */
    public static Ownership createOwnership(final String id) {
        final Ownership result = SpatioTemporalExtentServices.createOwnership(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link Participant} with an String.
     *
     * @param id ID of the Participant.
     * @return A Participant instance.
     */
    public static Participant createParticipant(final String id) {
        final Participant result = SpatioTemporalExtentServices.createParticipant(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT);
        return result;
    }

    /**
     * Create a {@link ParticipantInActivityOrAssociation} with an String.
     *
     * @param id ID of the ParticipantInActivityOrAssociation.
     * @return A ParticipantInActivityOrAssociation instance.
     */
    public static ParticipantInActivityOrAssociation createParticipantInActivityOrAssociation(final String id) {
        final ParticipantInActivityOrAssociation result = SpatioTemporalExtentServices
                .createParticipantInActivityOrAssociation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PARTICIPANT_IN_ACTIVITY_OR_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link Party} with an String.
     *
     * @param id ID of the Party.
     * @return A Party instance.
     */
    public static Party createParty(final String id) {
        final Party result = SpatioTemporalExtentServices.createParty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PARTY);
        return result;
    }

    /**
     * Create a {@link Pattern} with an String.
     *
     * @param id ID of the Pattern.
     * @return A Pattern instance.
     */
    public static Pattern createPattern(final String id) {
        final Pattern result = SpatioTemporalExtentServices.createPattern(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PATTERN);
        return result;
    }

    /**
     * Create a {@link PeriodOfTime} with an String.
     *
     * @param id ID of the PeriodOfTime.
     * @return A PeriodOfTime instance.
     */
    public static PeriodOfTime createPeriodOfTime(final String id) {
        final PeriodOfTime result = SpatioTemporalExtentServices.createPeriodOfTime(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PERIOD_OF_TIME);
        return result;
    }

    /**
     * Create a {@link Person} with an String.
     *
     * @param id ID of the Person.
     * @return A Person instance.
     */
    public static Person createPerson(final String id) {
        final Person result = SpatioTemporalExtentServices.createPerson(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PERSON);
        return result;
    }

    /**
     * Create a {@link PersonInPosition} with an String.
     *
     * @param id ID of the PersonInPosition.
     * @return A PersonInPosition instance.
     */
    public static PersonInPosition createPersonInPosition(final String id) {
        final PersonInPosition result = SpatioTemporalExtentServices.createPersonInPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PERSON_IN_POSITION);
        return result;
    }

    /**
     * Create a {@link PhysicalObject} with an String.
     *
     * @param id ID of the PhysicalObject.
     * @return A PhysicalObject instance.
     */
    public static PhysicalObject createPhysicalObject(final String id) {
        final PhysicalObject result = SpatioTemporalExtentServices.createPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link PhysicalProperty} with an String.
     *
     * @param id ID of the PhysicalProperty.
     * @return A PhysicalProperty instance.
     */
    public static PhysicalProperty createPhysicalProperty(final String id) {
        final PhysicalProperty result = SpatioTemporalExtentServices.createPhysicalProperty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_PROPERTY);
        return result;
    }

    /**
     * Create a {@link PhysicalPropertyRange} with an String.
     *
     * @param id ID of the PhysicalPropertyRange.
     * @return A PhysicalPropertyRange instance.
     */
    public static PhysicalPropertyRange createPhysicalPropertyRange(final String id) {
        final PhysicalPropertyRange result = SpatioTemporalExtentServices.createPhysicalPropertyRange(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_PROPERTY_RANGE);
        return result;
    }

    /**
     * Create a {@link PhysicalQuantity} with an String.
     *
     * @param id ID of the PhysicalQuantity.
     * @return A PhysicalQuantity instance.
     */
    public static PhysicalQuantity createPhysicalQuantity(final String id) {
        final PhysicalQuantity result = SpatioTemporalExtentServices.createPhysicalQuantity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_QUANTITY);
        return result;
    }

    /**
     * Create a {@link PhysicalQuantityRange} with an String.
     *
     * @param id ID of the PhysicalQuantityRange.
     * @return A PhysicalQuantityRange instance.
     */
    public static PhysicalQuantityRange createPhysicalQuantityRange(final String id) {
        final PhysicalQuantityRange result = SpatioTemporalExtentServices.createPhysicalQuantityRange(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PHYSICAL_QUANTITY_RANGE);
        return result;
    }

    /**
     * Create a {@link Plan} with an String.
     *
     * @param id ID of the Plan.
     * @return A Plan instance.
     */
    public static Plan createPlan(final String id) {
        final Plan result = SpatioTemporalExtentServices.createPlan(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PLAN);
        return result;
    }

    /**
     * Create a {@link PointInTime} with an String.
     *
     * @param id ID of the PointInTime.
     * @return A PointInTime instance.
     */
    public static PointInTime createPointInTime(final String id) {
        final PointInTime result = SpatioTemporalExtentServices.createPointInTime(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.POINT_IN_TIME);
        return result;
    }

    /**
     * Create a {@link Position} with an String.
     *
     * @param id ID of the Position.
     * @return A Position instance.
     */
    public static Position createPosition(final String id) {
        final Position result = SpatioTemporalExtentServices.createPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.POSITION);
        return result;
    }

    /**
     * Create a {@link PossibleWorld} with an String.
     *
     * @param id ID of the PossibleWorld.
     * @return A PossibleWorld instance.
     */
    public static PossibleWorld createPossibleWorld(final String id) {
        final PossibleWorld result = SpatioTemporalExtentServices.createPossibleWorld(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.POSSIBLE_WORLD);
        return result;
    }

    /**
     * Create a {@link Price} with an String.
     *
     * @param id ID of the Price.
     * @return A Price instance.
     */
    public static Price createPrice(final String id) {
        final Price result = SpatioTemporalExtentServices.createPrice(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PRICE);
        return result;
    }

    /**
     * Create a {@link ProductBrand} with an String.
     *
     * @param id ID of the ProductBrand.
     * @return A ProductBrand instance.
     */
    public static ProductBrand createProductBrand(final String id) {
        final ProductBrand result = SpatioTemporalExtentServices.createProductBrand(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PRODUCT_BRAND);
        return result;
    }

    /**
     * Create a {@link ProductOffering} with an String.
     *
     * @param id ID of the ProductOffering.
     * @return A ProductOffering instance.
     */
    public static ProductOffering createProductOffering(final String id) {
        final ProductOffering result = SpatioTemporalExtentServices.createProductOffering(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.PRODUCT_OFFERING);
        return result;
    }

    /**
     * Create a {@link ReachingAgreement} with an String.
     *
     * @param id ID of the ReachingAgreement.
     * @return A ReachingAgreement instance.
     */
    public static ReachingAgreement createReachingAgreement(final String id) {
        final ReachingAgreement result = SpatioTemporalExtentServices.createReachingAgreement(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REACHING_AGREEMENT);
        return result;
    }

    /**
     * Create a {@link RecognizingLanguageCommunity} with an String.
     *
     * @param id ID of the RecognizingLanguageCommunity.
     * @return A RecognizingLanguageCommunity instance.
     */
    public static RecognizingLanguageCommunity createRecognizingLanguageCommunity(final String id) {
        final RecognizingLanguageCommunity result = SpatioTemporalExtentServices.createRecognizingLanguageCommunity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY);
        return result;
    }

    /**
     * Create a {@link RepresentationByPattern} with an String.
     *
     * @param id ID of the RepresentationByPattern.
     * @return A RepresentationByPattern instance.
     */
    public static RepresentationByPattern createRepresentationByPattern(final String id) {
        final RepresentationByPattern result = SpatioTemporalExtentServices.createRepresentationByPattern(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_PATTERN);
        return result;
    }

    /**
     * Create a {@link RepresentationBySign} with an String.
     *
     * @param id ID of the RepresentationBySign.
     * @return A RepresentationBySign instance.
     */
    public static RepresentationBySign createRepresentationBySign(final String id) {
        final RepresentationBySign result = SpatioTemporalExtentServices.createRepresentationBySign(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REPRESENTATION_BY_SIGN);
        return result;
    }

    /**
     * Create a {@link Requirement} with an String.
     *
     * @param id ID of the Requirement.
     * @return A Requirement instance.
     */
    public static Requirement createRequirement(final String id) {
        final Requirement result = SpatioTemporalExtentServices.createRequirement(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REQUIREMENT);
        return result;
    }

    /**
     * Create a {@link RequirementSpecification} with an String.
     *
     * @param id ID of the RequirementSpecification.
     * @return A RequirementSpecification instance.
     */
    public static RequirementSpecification createRequirementSpecification(final String id) {
        final RequirementSpecification result = SpatioTemporalExtentServices.createRequirementSpecification(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.REQUIREMENT_SPECIFICATION);
        return result;
    }

    /**
     * Create a {@link SaleOfGoods} with an String.
     *
     * @param id ID of the SaleOfGoods.
     * @return A SaleOfGoods instance.
     */
    public static SaleOfGoods createSaleOfGoods(final String id) {
        final SaleOfGoods result = SpatioTemporalExtentServices.createSaleOfGoods(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALE_OF_GOODS);
        return result;
    }

    /**
     * Create a {@link SalesProduct} with an String.
     *
     * @param id ID of the SalesProduct.
     * @return A SalesProduct instance.
     */
    public static SalesProduct createSalesProduct(final String id) {
        final SalesProduct result = SpatioTemporalExtentServices.createSalesProduct(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALES_PRODUCT);
        return result;
    }

    /**
     * Create a {@link SalesProductInstance} with an String.
     *
     * @param id ID of the SalesProductInstance.
     * @return A SalesProductInstance instance.
     */
    public static SalesProductInstance createSalesProductInstance(final String id) {
        final SalesProductInstance result = SpatioTemporalExtentServices.createSalesProductInstance(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALES_PRODUCT_INSTANCE);
        return result;
    }

    /**
     * Create a {@link SalesProductVersion} with an String.
     *
     * @param id ID of the SalesProductVersion.
     * @return A SalesProductVersion instance.
     */
    public static SalesProductVersion createSalesProductVersion(final String id) {
        final SalesProductVersion result = SpatioTemporalExtentServices.createSalesProductVersion(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SALES_PRODUCT_VERSION);
        return result;
    }

    /**
     * Create a {@link Sign} with an String.
     *
     * @param id ID of the Sign.
     * @return A Sign instance.
     */
    public static Sign createSign(final String id) {
        final Sign result = SpatioTemporalExtentServices.createSign(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SIGN);
        return result;
    }

    /**
     * Create a {@link SociallyConstructedActivity} with an String.
     *
     * @param id ID of the SociallyConstructedActivity.
     * @return A SociallyConstructedActivity instance.
     */
    public static SociallyConstructedActivity createSociallyConstructedActivity(final String id) {
        final SociallyConstructedActivity result = SpatioTemporalExtentServices.createSociallyConstructedActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SOCIALLY_CONSTRUCTED_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link SociallyConstructedObject} with an String.
     *
     * @param id ID of the SociallyConstructedObject.
     * @return A SociallyConstructedObject instance.
     */
    public static SociallyConstructedObject createSociallyConstructedObject(final String id) {
        final SociallyConstructedObject result = SpatioTemporalExtentServices.createSociallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link SpatioTemporalExtent} with an String.
     *
     * @param id ID of the SpatioTemporalExtent.
     * @return A SpatioTemporalExtent instance.
     */
    public static SpatioTemporalExtent createSpatioTemporalExtent(final String id) {
        final SpatioTemporalExtent result = SpatioTemporalExtentServices.createSpatioTemporalExtent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SPATIO_TEMPORAL_EXTENT);
        return result;
    }

    /**
     * Create a {@link State} with an String.
     *
     * @param id ID of the State.
     * @return A State instance.
     */
    public static State createState(final String id) {
        final State result = SpatioTemporalExtentServices.createState(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE);
        return result;
    }

    /**
     * Create a {@link StateOfActivity} with an String.
     *
     * @param id ID of the StateOfActivity.
     * @return A StateOfActivity instance.
     */
    public static StateOfActivity createStateOfActivity(final String id) {
        final StateOfActivity result = SpatioTemporalExtentServices.createStateOfActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link StateOfAmountOfMoney} with an String.
     *
     * @param id ID of the StateOfAmountOfMoney.
     * @return A StateOfAmountOfMoney instance.
     */
    public static StateOfAmountOfMoney createStateOfAmountOfMoney(final String id) {
        final StateOfAmountOfMoney result = SpatioTemporalExtentServices.createStateOfAmountOfMoney(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_AMOUNT_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link StateOfAssociation} with an String.
     *
     * @param id ID of the StateOfAssociation.
     * @return A StateOfAssociation instance.
     */
    public static StateOfAssociation createStateOfAssociation(final String id) {
        final StateOfAssociation result = SpatioTemporalExtentServices.createStateOfAssociation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link StateOfBiologicalObject} with an String.
     *
     * @param id ID of the StateOfBiologicalObject.
     * @return A StateOfBiologicalObject instance.
     */
    public static StateOfBiologicalObject createStateOfBiologicalObject(final String id) {
        final StateOfBiologicalObject result = SpatioTemporalExtentServices.createStateOfBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfBiologicalSystem} with an String.
     *
     * @param id ID of the StateOfBiologicalSystem.
     * @return A StateOfBiologicalSystem instance.
     */
    public static StateOfBiologicalSystem createStateOfBiologicalSystem(final String id) {
        final StateOfBiologicalSystem result = SpatioTemporalExtentServices.createStateOfBiologicalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link StateOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the StateOfBiologicalSystemComponent.
     * @return A StateOfBiologicalSystemComponent instance.
     */
    public static StateOfBiologicalSystemComponent createStateOfBiologicalSystemComponent(final String id) {
        final StateOfBiologicalSystemComponent result = SpatioTemporalExtentServices
                .createStateOfBiologicalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link StateOfFunctionalObject} with an String.
     *
     * @param id ID of the StateOfFunctionalObject.
     * @return A StateOfFunctionalObject instance.
     */
    public static StateOfFunctionalObject createStateOfFunctionalObject(final String id) {
        final StateOfFunctionalObject result = SpatioTemporalExtentServices.createStateOfFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfFunctionalSystem} with an String.
     *
     * @param id ID of the StateOfFunctionalSystem.
     * @return A StateOfFunctionalSystem instance.
     */
    public static StateOfFunctionalSystem createStateOfFunctionalSystem(final String id) {
        final StateOfFunctionalSystem result = SpatioTemporalExtentServices.createStateOfFunctionalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link StateOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the StateOfFunctionalSystemComponent.
     * @return A StateOfFunctionalSystemComponent instance.
     */
    public static StateOfFunctionalSystemComponent createStateOfFunctionalSystemComponent(final String id) {
        final StateOfFunctionalSystemComponent result = SpatioTemporalExtentServices
                .createStateOfFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link StateOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the StateOfIntentionallyConstructedObject.
     * @return A StateOfIntentionallyConstructedObject instance.
     */
    public static StateOfIntentionallyConstructedObject createStateOfIntentionallyConstructedObject(final String id) {
        final StateOfIntentionallyConstructedObject result = SpatioTemporalExtentServices
                .createStateOfIntentionallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfLanguageCommunity} with an String.
     *
     * @param id ID of the StateOfLanguageCommunity.
     * @return A StateOfLanguageCommunity instance.
     */
    public static StateOfLanguageCommunity createStateOfLanguageCommunity(final String id) {
        final StateOfLanguageCommunity result = SpatioTemporalExtentServices.createStateOfLanguageCommunity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_LANGUAGE_COMMUNITY);
        return result;
    }

    /**
     * Create a {@link StateOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the StateOfOrdinaryBiologicalObject.
     * @return A StateOfOrdinaryBiologicalObject instance.
     */
    public static StateOfOrdinaryBiologicalObject createStateOfOrdinaryBiologicalObject(final String id) {
        final StateOfOrdinaryBiologicalObject result = SpatioTemporalExtentServices
                .createStateOfOrdinaryBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the StateOfOrdinaryFunctionalObject.
     * @return A StateOfOrdinaryFunctionalObject instance.
     */
    public static StateOfOrdinaryFunctionalObject createStateOfOrdinaryFunctionalObject(final String id) {
        final StateOfOrdinaryFunctionalObject result = SpatioTemporalExtentServices
                .createStateOfOrdinaryFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the StateOfOrdinaryPhysicalObject.
     * @return A StateOfOrdinaryPhysicalObject instance.
     */
    public static StateOfOrdinaryPhysicalObject createStateOfOrdinaryPhysicalObject(final String id) {
        final StateOfOrdinaryPhysicalObject result = SpatioTemporalExtentServices
                .createStateOfOrdinaryPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfOrganization} with an String.
     *
     * @param id ID of the StateOfOrganization.
     * @return A StateOfOrganization instance.
     */
    public static StateOfOrganization createStateOfOrganization(final String id) {
        final StateOfOrganization result = SpatioTemporalExtentServices.createStateOfOrganization(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link StateOfOrganizationComponent} with an String.
     *
     * @param id ID of the StateOfOrganizationComponent.
     * @return A StateOfOrganizationComponent instance.
     */
    public static StateOfOrganizationComponent createStateOfOrganizationComponent(final String id) {
        final StateOfOrganizationComponent result = SpatioTemporalExtentServices.createStateOfOrganizationComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link StateOfParty} with an String.
     *
     * @param id ID of the StateOfParty.
     * @return A StateOfParty instance.
     */
    public static StateOfParty createStateOfParty(final String id) {
        final StateOfParty result = SpatioTemporalExtentServices.createStateOfParty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PARTY);
        return result;
    }

    /**
     * Create a {@link StateOfPerson} with an String.
     *
     * @param id ID of the StateOfPerson.
     * @return A StateOfPerson instance.
     */
    public static StateOfPerson createStateOfPerson(final String id) {
        final StateOfPerson result = SpatioTemporalExtentServices.createStateOfPerson(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PERSON);
        return result;
    }

    /**
     * Create a {@link StateOfPhysicalObject} with an String.
     *
     * @param id ID of the StateOfPhysicalObject.
     * @return A StateOfPhysicalObject instance.
     */
    public static StateOfPhysicalObject createStateOfPhysicalObject(final String id) {
        final StateOfPhysicalObject result = SpatioTemporalExtentServices.createStateOfPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfPosition} with an String.
     *
     * @param id ID of the StateOfPosition.
     * @return A StateOfPosition instance.
     */
    public static StateOfPosition createStateOfPosition(final String id) {
        final StateOfPosition result = SpatioTemporalExtentServices.createStateOfPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_POSITION);
        return result;
    }

    /**
     * Create a {@link StateOfSalesProductInstance} with an String.
     *
     * @param id ID of the StateOfSalesProductInstance.
     * @return A StateOfSalesProductInstance instance.
     */
    public static StateOfSalesProductInstance createStateOfSalesProductInstance(final String id) {
        final StateOfSalesProductInstance result = SpatioTemporalExtentServices.createStateOfSalesProductInstance(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SALES_PRODUCT_INSTANCE);
        return result;
    }

    /**
     * Create a {@link StateOfSign} with an String.
     *
     * @param id ID of the StateOfSign.
     * @return A StateOfSign instance.
     */
    public static StateOfSign createStateOfSign(final String id) {
        final StateOfSign result = SpatioTemporalExtentServices.createStateOfSign(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SIGN);
        return result;
    }

    /**
     * Create a {@link StateOfSociallyConstructedActivity} with an String.
     *
     * @param id ID of the StateOfSociallyConstructedActivity.
     * @return A StateOfSociallyConstructedActivity instance.
     */
    public static StateOfSociallyConstructedActivity createStateOfSociallyConstructedActivity(final String id) {
        final StateOfSociallyConstructedActivity result = SpatioTemporalExtentServices
                .createStateOfSociallyConstructedActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link StateOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the StateOfSociallyConstructedObject.
     * @return A StateOfSociallyConstructedObject instance.
     */
    public static StateOfSociallyConstructedObject createStateOfSociallyConstructedObject(final String id) {
        final StateOfSociallyConstructedObject result = SpatioTemporalExtentServices
                .createStateOfSociallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link StateOfSystem} with an String.
     *
     * @param id ID of the StateOfSystem.
     * @return A StateOfSystem instance.
     */
    public static StateOfSystem createStateOfSystem(final String id) {
        final StateOfSystem result = SpatioTemporalExtentServices.createStateOfSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SYSTEM);
        return result;
    }

    /**
     * Create a {@link StateOfSystemComponent} with an String.
     *
     * @param id ID of the StateOfSystemComponent.
     * @return A StateOfSystemComponent instance.
     */
    public static StateOfSystemComponent createStateOfSystemComponent(final String id) {
        final StateOfSystemComponent result = SpatioTemporalExtentServices.createStateOfSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.STATE_OF_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link uk.gov.gchq.magmacore.hqdm.model.System} with an String.
     *
     * @param id ID of the System.
     * @return A System instance.
     */
    public static uk.gov.gchq.magmacore.hqdm.model.System createSystem(final String id) {
        final uk.gov.gchq.magmacore.hqdm.model.System result = SpatioTemporalExtentServices.createSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SYSTEM);
        return result;
    }

    /**
     * Create a {@link SystemComponent} with an String.
     *
     * @param id ID of the SystemComponent.
     * @return A SystemComponent instance.
     */
    public static SystemComponent createSystemComponent(final String id) {
        final SystemComponent result = SpatioTemporalExtentServices.createSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Thing} with an String.
     *
     * @param id ID of the Thing.
     * @return A Thing instance.
     */
    public static Thing createThing(final String id) {
        final Thing result = SpatioTemporalExtentServices.createThing(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.THING);
        return result;
    }

    /**
     * Create a {@link TransferOfOwnership} with an String.
     *
     * @param id ID of the TransferOfOwnership.
     * @return A TransferOfOwnership instance.
     */
    public static TransferOfOwnership createTransferOfOwnership(final String id) {
        final TransferOfOwnership result = SpatioTemporalExtentServices.createTransferOfOwnership(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFER_OF_OWNERSHIP);
        return result;
    }

    /**
     * Create a {@link TransferOfOwnershipOfMoney} with an String.
     *
     * @param id ID of the TransferOfOwnershipOfMoney.
     * @return A TransferOfOwnershipOfMoney instance.
     */
    public static TransferOfOwnershipOfMoney createTransferOfOwnershipOfMoney(final String id) {
        final TransferOfOwnershipOfMoney result = SpatioTemporalExtentServices.createTransferOfOwnershipOfMoney(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFER_OF_OWNERSHIP_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link Transferee} with an String.
     *
     * @param id ID of the Transferee.
     * @return A Transferee instance.
     */
    public static Transferee createTransferee(final String id) {
        final Transferee result = SpatioTemporalExtentServices.createTransferee(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFEREE);
        return result;
    }

    /**
     * Create a {@link Transferor} with an String.
     *
     * @param id ID of the Transferor.
     * @return A Transferor instance.
     */
    public static Transferor createTransferor(final String id) {
        final Transferor result = SpatioTemporalExtentServices.createTransferor(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TRANSFEROR);
        return result;
    }
}
