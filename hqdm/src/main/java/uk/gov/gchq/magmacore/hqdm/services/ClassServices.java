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
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.impl.*;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * Service for creating HQDM Classes.
 */
public class ClassServices {
    /**
     * Create a {@link Class} with an String.
     *
     * @param id ID of the Class.
     * @return A Class instance.
     */
    public static Class createClass(final IRI id) {
        return new ClassImpl(id);
    }

    /**
     * Create a {@link ClassOfAbstractObject} with an String.
     *
     * @param id ID of the ClassOfAbstractObject.
     * @return A ClassOfAbstractObject instance.
     */
    public static ClassOfAbstractObject createClassOfAbstractObject(final IRI id) {
        return new ClassOfAbstractObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfActivity} with an String.
     *
     * @param id ID of the ClassOfActivity.
     * @return A ClassOfActivity instance.
     */
    public static ClassOfActivity createClassOfActivity(final IRI id) {
        return new ClassOfActivityImpl(id);
    }

    /**
     * Create a {@link ClassOfAgreeContract} with an String.
     *
     * @param id ID of the ClassOfAgreeContract.
     * @return A ClassOfAgreeContract instance.
     */
    public static ClassOfAgreeContract createClassOfAgreeContract(final IRI id) {
        return new ClassOfAgreeContractImpl(id);
    }

    /**
     * Create a {@link ClassOfAgreementExecution} with an String.
     *
     * @param id ID of the ClassOfAgreementExecution.
     * @return A ClassOfAgreementExecution instance.
     */
    public static ClassOfAgreementExecution createClassOfAgreementExecution(final IRI id) {
        return new ClassOfAgreementExecutionImpl(id);
    }

    /**
     * Create a {@link ClassOfAgreementProcess} with an String.
     *
     * @param id ID of the ClassOfAgreementProcess.
     * @return A ClassOfAgreementProcess instance.
     */
    public static ClassOfAgreementProcess createClassOfAgreementProcess(final IRI id) {
        return new ClassOfAgreementProcessImpl(id);
    }

    /**
     * Create a {@link ClassOfAmountOfMoney} with an String.
     *
     * @param id ID of the ClassOfAmountOfMoney.
     * @return A ClassOfAmountOfMoney instance.
     */
    public static ClassOfAmountOfMoney createClassOfAmountOfMoney(final IRI id) {
        return new ClassOfAmountOfMoneyImpl(id);
    }

    /**
     * Create a {@link ClassOfAssociation} with an String.
     *
     * @param id ID of the ClassOfAssociation.
     * @return A ClassOfAssociation instance.
     */
    public static ClassOfAssociation createClassOfAssociation(final IRI id) {
        return new ClassOfAssociationImpl(id);
    }

    /**
     * Create a {@link ClassOfBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfBiologicalObject.
     * @return A ClassOfBiologicalObject instance.
     */
    public static ClassOfBiologicalObject createClassOfBiologicalObject(final IRI id) {
        return new ClassOfBiologicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfBiologicalSystem} with an String.
     *
     * @param id ID of the ClassOfBiologicalSystem.
     * @return A ClassOfBiologicalSystem instance.
     */
    public static ClassOfBiologicalSystem createClassOfBiologicalSystem(final IRI id) {
        return new ClassOfBiologicalSystemImpl(id);
    }

    /**
     * Create a {@link ClassOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfBiologicalSystemComponent.
     * @return A ClassOfBiologicalSystemComponent instance.
     */
    public static ClassOfBiologicalSystemComponent createClassOfBiologicalSystemComponent(final IRI id) {
        return new ClassOfBiologicalSystemComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfClass} with an String.
     *
     * @param id ID of the ClassOfClass.
     * @return A ClassOfClass instance.
     */
    public static ClassOfClass createClassOfClass(final IRI id) {
        return new ClassOfClassImpl(id);
    }

    /**
     * Create a {@link ClassOfClassOfSpatioTemporalExtent} with an String.
     *
     * @param id ID of the ClassOfClassOfSpatioTemporalExtent.
     * @return A ClassOfClassOfSpatioTemporalExtent instance.
     */
    public static ClassOfClassOfSpatioTemporalExtent createClassOfClassOfSpatioTemporalExtent(final IRI id) {
        return new ClassOfClassOfSpatioTemporalExtentImpl(id);
    }

    /**
     * Create a {@link ClassOfContractExecution} with an String.
     *
     * @param id ID of the ClassOfContractExecution.
     * @return A ClassOfContractExecution instance.
     */
    public static ClassOfContractExecution createClassOfContractExecution(final IRI id) {
        return new ClassOfContractExecutionImpl(id);
    }

    /**
     * Create a {@link ClassOfContractProcess} with an String.
     *
     * @param id ID of the ClassOfContractProcess.
     * @return A ClassOfContractProcess instance.
     */
    public static ClassOfContractProcess createClassOfContractProcess(final IRI id) {
        return new ClassOfContractProcessImpl(id);
    }

    /**
     * Create a {@link ClassOfEvent} with an String.
     *
     * @param id ID of the ClassOfEvent.
     * @return A ClassOfEvent instance.
     */
    public static ClassOfEvent createClassOfEvent(final IRI id) {
        return new ClassOfEventImpl(id);
    }

    /**
     * Create a {@link ClassOfFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfFunctionalObject.
     * @return A ClassOfFunctionalObject instance.
     */
    public static ClassOfFunctionalObject createClassOfFunctionalObject(final IRI id) {
        return new ClassOfFunctionalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfFunctionalSystem} with an String.
     *
     * @param id ID of the ClassOfFunctionalSystem.
     * @return A ClassOfFunctionalSystem instance.
     */
    public static ClassOfFunctionalSystem createClassOfFunctionalSystem(final IRI id) {
        return new ClassOfFunctionalSystemImpl(id);
    }

    /**
     * Create a {@link ClassOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfFunctionalSystemComponent.
     * @return A ClassOfFunctionalSystemComponent instance.
     */
    public static ClassOfFunctionalSystemComponent createClassOfFunctionalSystemComponent(final IRI id) {
        return new ClassOfFunctionalSystemComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfInPlaceBiologicalComponent} with an String.
     *
     * @param id ID of the ClassOfInPlaceBiologicalComponent.
     * @return A ClassOfInPlaceBiologicalComponent instance.
     */
    public static ClassOfInPlaceBiologicalComponent createClassOfInPlaceBiologicalComponent(final IRI id) {
        return new ClassOfInPlaceBiologicalComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfIndividual} with an String.
     *
     * @param id ID of the ClassOfIndividual.
     * @return A ClassOfIndividual instance.
     */
    public static ClassOfIndividual createClassOfIndividual(final IRI id) {
        return new ClassOfIndividualImpl(id);
    }

    /**
     * Create a {@link ClassOfInstalledFunctionalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfInstalledFunctionalSystemComponent.
     * @return A ClassOfInstalledFunctionalSystemComponent instance.
     */
    public static ClassOfInstalledFunctionalSystemComponent createClassOfInstalledFunctionalSystemComponent(
            final IRI id) {
        return new ClassOfInstalledFunctionalSystemComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfInstalledObject} with an String.
     *
     * @param id ID of the ClassOfInstalledObject.
     * @return A ClassOfInstalledObject instance.
     */
    public static ClassOfInstalledObject createClassOfInstalledObject(final IRI id) {
        return new ClassOfInstalledObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfIntentionallyConstructedObject.
     * @return A ClassOfIntentionallyConstructedObject instance.
     */
    public static ClassOfIntentionallyConstructedObject createClassOfIntentionallyConstructedObject(final IRI id) {
        return new ClassOfIntentionallyConstructedObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfOffer} with an String.
     *
     * @param id ID of the ClassOfOffer.
     * @return A ClassOfOffer instance.
     */
    public static ClassOfOffer createClassOfOffer(final IRI id) {
        return new ClassOfOfferImpl(id);
    }

    /**
     * Create a {@link ClassOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfOrdinaryBiologicalObject.
     * @return A ClassOfOrdinaryBiologicalObject instance.
     */
    public static ClassOfOrdinaryBiologicalObject createClassOfOrdinaryBiologicalObject(final IRI id) {
        return new ClassOfOrdinaryBiologicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfOrdinaryFunctionalObject.
     * @return A ClassOfOrdinaryFunctionalObject instance.
     */
    public static ClassOfOrdinaryFunctionalObject createClassOfOrdinaryFunctionalObject(final IRI id) {
        return new ClassOfOrdinaryFunctionalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfOrdinaryPhysicalObject.
     * @return A ClassOfOrdinaryPhysicalObject instance.
     */
    public static ClassOfOrdinaryPhysicalObject createClassOfOrdinaryPhysicalObject(final IRI id) {
        return new ClassOfOrdinaryPhysicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfOrganization} with an String.
     *
     * @param id ID of the ClassOfOrganization.
     * @return A ClassOfOrganization instance.
     */
    public static ClassOfOrganization createClassOfOrganization(final IRI id) {
        return new ClassOfOrganizationImpl(id);
    }

    /**
     * Create a {@link ClassOfOrganizationComponent} with an String.
     *
     * @param id ID of the ClassOfOrganizationComponent.
     * @return A ClassOfOrganizationComponent instance.
     */
    public static ClassOfOrganizationComponent createClassOfOrganizationComponent(final IRI id) {
        return new ClassOfOrganizationComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfParticipant} with an String.
     *
     * @param id ID of the ClassOfParticipant.
     * @return A ClassOfParticipant instance.
     */
    public static ClassOfParticipant createClassOfParticipant(final IRI id) {
        return new ClassOfParticipantImpl(id);
    }

    /**
     * Create a {@link ClassOfParty} with an String.
     *
     * @param id ID of the ClassOfParty.
     * @return A ClassOfParty instance.
     */
    public static ClassOfParty createClassOfParty(final IRI id) {
        return new ClassOfPartyImpl(id);
    }

    /**
     * Create a {@link ClassOfPeriodOfTime} with an String.
     *
     * @param id ID of the ClassOfPeriodOfTime.
     * @return A ClassOfPeriodOfTime instance.
     */
    public static ClassOfPeriodOfTime createClassOfPeriodOfTime(final IRI id) {
        return new ClassOfPeriodOfTimeImpl(id);
    }

    /**
     * Create a {@link ClassOfPerson} with an String.
     *
     * @param id ID of the ClassOfPerson.
     * @return A ClassOfPerson instance.
     */
    public static ClassOfPerson createClassOfPerson(final IRI id) {
        return new ClassOfPersonImpl(id);
    }

    /**
     * Create a {@link ClassOfPersonInPosition} with an String.
     *
     * @param id ID of the ClassOfPersonInPosition.
     * @return A ClassOfPersonInPosition instance.
     */
    public static ClassOfPersonInPosition createClassOfPersonInPosition(final IRI id) {
        return new ClassOfPersonInPositionImpl(id);
    }

    /**
     * Create a {@link ClassOfPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfPhysicalObject.
     * @return A ClassOfPhysicalObject instance.
     */
    public static ClassOfPhysicalObject createClassOfPhysicalObject(final IRI id) {
        return new ClassOfPhysicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfPhysicalProperty} with an String.
     *
     * @param id ID of the ClassOfPhysicalProperty.
     * @return A ClassOfPhysicalProperty instance.
     */
    public static ClassOfPhysicalProperty createClassOfPhysicalProperty(final IRI id) {
        return new ClassOfPhysicalPropertyImpl(id);
    }

    /**
     * Create a {@link ClassOfPhysicalQuantity} with an String.
     *
     * @param id ID of the ClassOfPhysicalQuantity.
     * @return A ClassOfPhysicalQuantity instance.
     */
    public static ClassOfPhysicalQuantity createClassOfPhysicalQuantity(final IRI id) {
        return new ClassOfPhysicalQuantityImpl(id);
    }

    /**
     * Create a {@link ClassOfPointInTime} with an String.
     *
     * @param id ID of the ClassOfPointInTime.
     * @return A ClassOfPointInTime instance.
     */
    public static ClassOfPointInTime createClassOfPointInTime(final IRI id) {
        return new ClassOfPointInTimeImpl(id);
    }

    /**
     * Create a {@link ClassOfPosition} with an String.
     *
     * @param id ID of the ClassOfPosition.
     * @return A ClassOfPosition instance.
     */
    public static ClassOfPosition createClassOfPosition(final IRI id) {
        return new ClassOfPositionImpl(id);
    }

    /**
     * Create a {@link ClassOfPossibleWorld} with an String.
     *
     * @param id ID of the ClassOfPossibleWorld.
     * @return A ClassOfPossibleWorld instance.
     */
    public static ClassOfPossibleWorld createClassOfPossibleWorld(final IRI id) {
        return new ClassOfPossibleWorldImpl(id);
    }

    /**
     * Create a {@link ClassOfReachingAgreement} with an String.
     *
     * @param id ID of the ClassOfReachingAgreement.
     * @return A ClassOfReachingAgreement instance.
     */
    public static ClassOfReachingAgreement createClassOfReachingAgreement(final IRI id) {
        return new ClassOfReachingAgreementImpl(id);
    }

    /**
     * Create a {@link ClassOfRelationship} with an String.
     *
     * @param id ID of the ClassOfRelationship.
     * @return A ClassOfRelationship instance.
     */
    public static ClassOfRelationship createClassOfRelationship(final IRI id) {
        return new ClassOfRelationshipImpl(id);
    }

    /**
     * Create a {@link ClassOfRepresentation} with an String.
     *
     * @param id ID of the ClassOfRepresentation.
     * @return A ClassOfRepresentation instance.
     */
    public static ClassOfRepresentation createClassOfRepresentation(final IRI id) {
        return new ClassOfRepresentationImpl(id);
    }

    /**
     * Create a {@link ClassOfSalesProductInstance} with an String.
     *
     * @param id ID of the ClassOfSalesProductInstance.
     * @return A ClassOfSalesProductInstance instance.
     */
    public static ClassOfSalesProductInstance createClassOfSalesProductInstance(final IRI id) {
        return new ClassOfSalesProductInstanceImpl(id);
    }

    /**
     * Create a {@link ClassOfSign} with an String.
     *
     * @param id ID of the ClassOfSign.
     * @return A ClassOfSign instance.
     */
    public static ClassOfSign createClassOfSign(final IRI id) {
        return new ClassOfSignImpl(id);
    }

    /**
     * Create a {@link ClassOfSociallyConstructedActivity} with an String.
     *
     * @param id ID of the ClassOfSociallyConstructedActivity.
     * @return A ClassOfSociallyConstructedActivity instance.
     */
    public static ClassOfSociallyConstructedActivity createClassOfSociallyConstructedActivity(final IRI id) {
        return new ClassOfSociallyConstructedActivityImpl(id);
    }

    /**
     * Create a {@link ClassOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfSociallyConstructedObject.
     * @return A ClassOfSociallyConstructedObject instance.
     */
    public static ClassOfSociallyConstructedObject createClassOfSociallyConstructedObject(final IRI id) {
        return new ClassOfSociallyConstructedObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfSpatioTemporalExtent} with an String.
     *
     * @param id ID of the ClassOfSpatioTemporalExtent.
     * @return A ClassOfSpatioTemporalExtent instance.
     */
    public static ClassOfSpatioTemporalExtent createClassOfSpatioTemporalExtent(final IRI id) {
        return new ClassOfSpatioTemporalExtentImpl(id);
    }

    /**
     * Create a {@link ClassOfState} with an String.
     *
     * @param id ID of the ClassOfState.
     * @return A ClassOfState instance.
     */
    public static ClassOfState createClassOfState(final IRI id) {
        return new ClassOfStateImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfActivity} with an String.
     *
     * @param id ID of the ClassOfStateOfActivity.
     * @return A ClassOfStateOfActivity instance.
     */
    public static ClassOfStateOfActivity createClassOfStateOfActivity(final IRI id) {
        return new ClassOfStateOfActivityImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfAmountOfMoney} with an String.
     *
     * @param id ID of the ClassOfStateOfAmountOfMoney.
     * @return A ClassOfStateOfAmountOfMoney instance.
     */
    public static ClassOfStateOfAmountOfMoney createClassOfStateOfAmountOfMoney(final IRI id) {
        return new ClassOfStateOfAmountOfMoneyImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfAssociation} with an String.
     *
     * @param id ID of the ClassOfStateOfAssociation.
     * @return A ClassOfStateOfAssociation instance.
     */
    public static ClassOfStateOfAssociation createClassOfStateOfAssociation(final IRI id) {
        return new ClassOfStateOfAssociationImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfBiologicalObject.
     * @return A ClassOfStateOfBiologicalObject instance.
     */
    public static ClassOfStateOfBiologicalObject createClassOfStateOfBiologicalObject(final IRI id) {
        return new ClassOfStateOfBiologicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfBiologicalSystem} with an String.
     *
     * @param id ID of the ClassOfStateOfBiologicalSystem.
     * @return A ClassOfStateOfBiologicalSystem instance.
     */
    public static ClassOfStateOfBiologicalSystem createClassOfStateOfBiologicalSystem(final IRI id) {
        return new ClassOfStateOfBiologicalSystemImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfBiologicalSystemComponent.
     * @return A ClassOfStateOfBiologicalSystemComponent instance.
     */
    public static ClassOfStateOfBiologicalSystemComponent createClassOfStateOfBiologicalSystemComponent(
            final IRI id) {
        return new ClassOfStateOfBiologicalSystemComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfFunctionalObject.
     * @return A ClassOfStateOfFunctionalObject instance.
     */
    public static ClassOfStateOfFunctionalObject createClassOfStateOfFunctionalObject(final IRI id) {
        return new ClassOfStateOfFunctionalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfFunctionalSystem} with an String.
     *
     * @param id ID of the ClassOfStateOfFunctionalSystem.
     * @return A ClassOfStateOfFunctionalSystem instance.
     */
    public static ClassOfStateOfFunctionalSystem createClassOfStateOfFunctionalSystem(final IRI id) {
        return new ClassOfStateOfFunctionalSystemImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfFunctionalSystemComponent.
     * @return A ClassOfStateOfFunctionalSystemComponent instance.
     */
    public static ClassOfStateOfFunctionalSystemComponent createClassOfStateOfFunctionalSystemComponent(
            final IRI id) {
        return new ClassOfStateOfFunctionalSystemComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfStateOfIntentionallyConstructedObject.
     * @return A ClassOfStateOfIntentionallyConstructedObject instance.
     */
    public static ClassOfStateOfIntentionallyConstructedObject createClassOfStateOfIntentionallyConstructedObject(
            final IRI id) {
        return new ClassOfStateOfIntentionallyConstructedObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfOrdinaryBiologicalObject.
     * @return A ClassOfStateOfOrdinaryBiologicalObject instance.
     */
    public static ClassOfStateOfOrdinaryBiologicalObject createClassOfStateOfOrdinaryBiologicalObject(final IRI id) {
        return new ClassOfStateOfOrdinaryBiologicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfOrdinaryFunctionalObject.
     * @return A ClassOfStateOfOrdinaryFunctionalObject instance.
     */
    public static ClassOfStateOfOrdinaryFunctionalObject createClassOfStateOfOrdinaryFunctionalObject(final IRI id) {
        return new ClassOfStateOfOrdinaryFunctionalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfOrdinaryPhysicalObject.
     * @return A ClassOfStateOfOrdinaryPhysicalObject instance.
     */
    public static ClassOfStateOfOrdinaryPhysicalObject createClassOfStateOfOrdinaryPhysicalObject(final IRI id) {
        return new ClassOfStateOfOrdinaryPhysicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfOrganization} with an String.
     *
     * @param id ID of the ClassOfStateOfOrganization.
     * @return A ClassOfStateOfOrganization instance.
     */
    public static ClassOfStateOfOrganization createClassOfStateOfOrganization(final IRI id) {
        return new ClassOfStateOfOrganizationImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfOrganizationComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfOrganizationComponent.
     * @return A ClassOfStateOfOrganizationComponent instance.
     */
    public static ClassOfStateOfOrganizationComponent createClassOfStateOfOrganizationComponent(final IRI id) {
        return new ClassOfStateOfOrganizationComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfParty} with an String.
     *
     * @param id ID of the ClassOfStateOfParty.
     * @return A ClassOfStateOfParty instance.
     */
    public static ClassOfStateOfParty createClassOfStateOfParty(final IRI id) {
        return new ClassOfStateOfPartyImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfPerson} with an String.
     *
     * @param id ID of the ClassOfStateOfPerson.
     * @return A ClassOfStateOfPerson instance.
     */
    public static ClassOfStateOfPerson createClassOfStateOfPerson(final IRI id) {
        return new ClassOfStateOfPersonImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfPhysicalObject.
     * @return A ClassOfStateOfPhysicalObject instance.
     */
    public static ClassOfStateOfPhysicalObject createClassOfStateOfPhysicalObject(final IRI id) {
        return new ClassOfStateOfPhysicalObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfPosition} with an String.
     *
     * @param id ID of the ClassOfStateOfPosition.
     * @return A ClassOfStateOfPosition instance.
     */
    public static ClassOfStateOfPosition createClassOfStateOfPosition(final IRI id) {
        return new ClassOfStateOfPositionImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfSalesProductInstance} with an String.
     *
     * @param id ID of the ClassOfStateOfSalesProductInstance.
     * @return A ClassOfStateOfSalesProductInstance instance.
     */
    public static ClassOfStateOfSalesProductInstance createClassOfStateOfSalesProductInstance(final IRI id) {
        return new ClassOfStateOfSalesProductInstanceImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfSign} with an String.
     *
     * @param id ID of the ClassOfStateOfSign.
     * @return A ClassOfStateOfSign instance.
     */
    public static ClassOfStateOfSign createClassOfStateOfSign(final IRI id) {
        return new ClassOfStateOfSignImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfSociallyConstructedActivity} with an String.
     *
     * @param id ID of the ClassOfStateOfSociallyConstructedActivity.
     * @return A ClassOfStateOfSociallyConstructedActivity instance.
     */
    public static ClassOfStateOfSociallyConstructedActivity createClassOfStateOfSociallyConstructedActivity(
            final IRI id) {
        return new ClassOfStateOfSociallyConstructedActivityImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfStateOfSociallyConstructedObject.
     * @return A ClassOfStateOfSociallyConstructedObject instance.
     */
    public static ClassOfStateOfSociallyConstructedObject createClassOfStateOfSociallyConstructedObject(
            final IRI id) {
        return new ClassOfStateOfSociallyConstructedObjectImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfSystem} with an String.
     *
     * @param id ID of the ClassOfStateOfSystem.
     * @return A ClassOfStateOfSystem instance.
     */
    public static ClassOfStateOfSystem createClassOfStateOfSystem(final IRI id) {
        return new ClassOfStateOfSystemImpl(id);
    }

    /**
     * Create a {@link ClassOfStateOfSystemComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfSystemComponent.
     * @return A ClassOfStateOfSystemComponent instance.
     */
    public static ClassOfStateOfSystemComponent createClassOfStateOfSystemComponent(final IRI id) {
        return new ClassOfStateOfSystemComponentImpl(id);
    }

    /**
     * Create a {@link ClassOfSystem} with an String.
     *
     * @param id ID of the ClassOfSystem.
     * @return A ClassOfSystem instance.
     */
    public static ClassOfSystem createClassOfSystem(final IRI id) {
        return new ClassOfSystemImpl(id);
    }

    /**
     * Create a {@link ClassOfSystemComponent} with an String.
     *
     * @param id ID of the ClassOfSystemComponent.
     * @return A ClassOfSystemComponent instance.
     */
    public static ClassOfSystemComponent createClassOfSystemComponent(final IRI id) {
        return new ClassOfSystemComponentImpl(id);
    }

    /**
     * Create a {@link Definition} with an String.
     *
     * @param id ID of the Definition.
     * @return A Definition instance.
     */
    public static Definition createDefinition(final IRI id) {
        return new DefinitionImpl(id);
    }

    /**
     * Create a {@link Description} with an String.
     *
     * @param id ID of the Description.
     * @return A Description instance.
     */
    public static Description createDescription(final IRI id) {
        return new DescriptionImpl(id);
    }

    /**
     * Create a {@link EnumeratedClass} with an String.
     *
     * @param id ID of the EnumeratedClass.
     * @return A EnumeratedClass instance.
     */
    public static EnumeratedClass createEnumeratedClass(final IRI id) {
        return new EnumeratedClassImpl(id);
    }

    /**
     * Create a {@link KindOfActivity} with an String.
     *
     * @param id ID of the KindOfActivity.
     * @return A KindOfActivity instance.
     */
    public static KindOfActivity createKindOfActivity(final IRI id) {
        return new KindOfActivityImpl(id);
    }

    /**
     * Create a {@link KindOfAssociation} with an String.
     *
     * @param id ID of the KindOfAssociation.
     * @return A KindOfAssociation instance.
     */
    public static KindOfAssociation createKindOfAssociation(final IRI id) {
        return new KindOfAssociationImpl(id);
    }

    /**
     * Create a {@link KindOfBiologicalObject} with an String.
     *
     * @param id ID of the KindOfBiologicalObject.
     * @return A KindOfBiologicalObject instance.
     */
    public static KindOfBiologicalObject createKindOfBiologicalObject(final IRI id) {
        return new KindOfBiologicalObjectImpl(id);
    }

    /**
     * Create a {@link KindOfBiologicalSystem} with an String.
     *
     * @param id ID of the KindOfBiologicalSystem.
     * @return A KindOfBiologicalSystem instance.
     */
    public static KindOfBiologicalSystem createKindOfBiologicalSystem(final IRI id) {
        return new KindOfBiologicalSystemImpl(id);
    }

    /**
     * Create a {@link KindOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the KindOfBiologicalSystemComponent.
     * @return A KindOfBiologicalSystemComponent instance.
     */
    public static KindOfBiologicalSystemComponent createKindOfBiologicalSystemComponent(final IRI id) {
        return new KindOfBiologicalSystemComponentImpl(id);
    }

    /**
     * Create a {@link KindOfFunctionalObject} with an String.
     *
     * @param id ID of the KindOfFunctionalObject.
     * @return A KindOfFunctionalObject instance.
     */
    public static KindOfFunctionalObject createKindOfFunctionalObject(final IRI id) {
        return new KindOfFunctionalObjectImpl(id);
    }

    /**
     * Create a {@link KindOfFunctionalSystem} with an String.
     *
     * @param id ID of the KindOfFunctionalSystem.
     * @return A KindOfFunctionalSystem instance.
     */
    public static KindOfFunctionalSystem createKindOfFunctionalSystem(final IRI id) {
        return new KindOfFunctionalSystemImpl(id);
    }

    /**
     * Create a {@link KindOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the KindOfFunctionalSystemComponent.
     * @return A KindOfFunctionalSystemComponent instance.
     */
    public static KindOfFunctionalSystemComponent createKindOfFunctionalSystemComponent(final IRI id) {
        return new KindOfFunctionalSystemComponentImpl(id);
    }

    /**
     * Create a {@link KindOfIndividual} with an String.
     *
     * @param id ID of the KindOfIndividual.
     * @return A KindOfIndividual instance.
     */
    public static KindOfIndividual createKindOfIndividual(final IRI id) {
        return new KindOfIndividualImpl(id);
    }

    /**
     * Create a {@link KindOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the KindOfIntentionallyConstructedObject.
     * @return A KindOfIntentionallyConstructedObject instance.
     */
    public static KindOfIntentionallyConstructedObject createKindOfIntentionallyConstructedObject(final IRI id) {
        return new KindOfIntentionallyConstructedObjectImpl(id);
    }

    /**
     * Create a {@link KindOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the KindOfOrdinaryBiologicalObject.
     * @return A KindOfOrdinaryBiologicalObject instance.
     */
    public static KindOfOrdinaryBiologicalObject createKindOfOrdinaryBiologicalObject(final IRI id) {
        return new KindOfOrdinaryBiologicalObjectImpl(id);
    }

    /**
     * Create a {@link KindOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the KindOfOrdinaryFunctionalObject.
     * @return A KindOfOrdinaryFunctionalObject instance.
     */
    public static KindOfOrdinaryFunctionalObject createKindOfOrdinaryFunctionalObject(final IRI id) {
        return new KindOfOrdinaryFunctionalObjectImpl(id);
    }

    /**
     * Create a {@link KindOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the KindOfOrdinaryPhysicalObject.
     * @return A KindOfOrdinaryPhysicalObject instance.
     */
    public static KindOfOrdinaryPhysicalObject createKindOfOrdinaryPhysicalObject(final IRI id) {
        return new KindOfOrdinaryPhysicalObjectImpl(id);
    }

    /**
     * Create a {@link KindOfOrganization} with an String.
     *
     * @param id ID of the KindOfOrganization.
     * @return A KindOfOrganization instance.
     */
    public static KindOfOrganization createKindOfOrganization(final IRI id) {
        return new KindOfOrganizationImpl(id);
    }

    /**
     * Create a {@link KindOfOrganizationComponent} with an String.
     *
     * @param id ID of the KindOfOrganizationComponent.
     * @return A KindOfOrganizationComponent instance.
     */
    public static KindOfOrganizationComponent createKindOfOrganizationComponent(final IRI id) {
        return new KindOfOrganizationComponentImpl(id);
    }

    /**
     * Create a {@link KindOfParty} with an String.
     *
     * @param id ID of the KindOfParty.
     * @return A KindOfParty instance.
     */
    public static KindOfParty createKindOfParty(final IRI id) {
        return new KindOfPartyImpl(id);
    }

    /**
     * Create a {@link KindOfPerson} with an String.
     *
     * @param id ID of the KindOfPerson.
     * @return A KindOfPerson instance.
     */
    public static KindOfPerson createKindOfPerson(final IRI id) {
        return new KindOfPersonImpl(id);
    }

    /**
     * Create a {@link KindOfPhysicalObject} with an String.
     *
     * @param id ID of the KindOfPhysicalObject.
     * @return A KindOfPhysicalObject instance.
     */
    public static KindOfPhysicalObject createKindOfPhysicalObject(final IRI id) {
        return new KindOfPhysicalObjectImpl(id);
    }

    /**
     * Create a {@link KindOfPhysicalProperty} with an String.
     *
     * @param id ID of the KindOfPhysicalProperty.
     * @return A KindOfPhysicalProperty instance.
     */
    public static KindOfPhysicalProperty createKindOfPhysicalProperty(final IRI id) {
        return new KindOfPhysicalPropertyImpl(id);
    }

    /**
     * Create a {@link KindOfPhysicalQuantity} with an String.
     *
     * @param id ID of the KindOfPhysicalQuantity.
     * @return A KindOfPhysicalQuantity instance.
     */
    public static KindOfPhysicalQuantity createKindOfPhysicalQuantity(final IRI id) {
        return new KindOfPhysicalQuantityImpl(id);
    }

    /**
     * Create a {@link KindOfPosition} with an String.
     *
     * @param id ID of the KindOfPosition.
     * @return A KindOfPosition instance.
     */
    public static KindOfPosition createKindOfPosition(final IRI id) {
        return new KindOfPositionImpl(id);
    }

    /**
     * Create a {@link KindOfRelationshipWithRestriction} with an String.
     *
     * @param id ID of the KindOfRelationshipWithRestriction.
     * @return A KindOfRelationshipWithRestriction instance.
     */
    public static KindOfRelationshipWithRestriction createKindOfRelationshipWithRestriction(final IRI id) {
        return new KindOfRelationshipWithRestrictionImpl(id);
    }

    /**
     * Create a {@link KindOfRelationshipWithSignature} with an String.
     *
     * @param id ID of the KindOfRelationshipWithSignature.
     * @return A KindOfRelationshipWithSignature instance.
     */
    public static KindOfRelationshipWithSignature createKindOfRelationshipWithSignature(final IRI id) {
        return new KindOfRelationshipWithSignatureImpl(id);
    }

    /**
     * Create a {@link KindOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the KindOfSociallyConstructedObject.
     * @return A KindOfSociallyConstructedObject instance.
     */
    public static KindOfSociallyConstructedObject createKindOfSociallyConstructedObject(final IRI id) {
        return new KindOfSociallyConstructedObjectImpl(id);
    }

    /**
     * Create a {@link KindOfSystem} with an String.
     *
     * @param id ID of the KindOfSystem.
     * @return A KindOfSystem instance.
     */
    public static KindOfSystem createKindOfSystem(final IRI id) {
        return new KindOfSystemImpl(id);
    }

    /**
     * Create a {@link KindOfSystemComponent} with an String.
     *
     * @param id ID of the KindOfSystemComponent.
     * @return A KindOfSystemComponent instance.
     */
    public static KindOfSystemComponent createKindOfSystemComponent(final IRI id) {
        return new KindOfSystemComponentImpl(id);
    }

    /**
     * Create a {@link Identification} with an String.
     *
     * @param id ID of the Identification.
     * @return A Identification instance.
     */
    public static Identification createIdentification(final IRI id) {
        return new IdentificationImpl(id);
    }

    /**
     * Create a {@link Pattern} with an String.
     *
     * @param id ID of the Pattern.
     * @return A Pattern instance.
     */
    public static Pattern createPattern(final IRI id) {
        return new PatternImpl(id);
    }

    /**
     * Create a {@link RepresentationByPattern} with an String.
     *
     * @param id ID of the RepresentationByPattern.
     * @return A RepresentationByPattern instance.
     */
    public static RepresentationByPattern createRepresentationByPattern(final IRI id) {
        return new RepresentationByPatternImpl(id);
    }

    /**
     * Create a {@link Role} with an String.
     *
     * @param id ID of the Role.
     * @return A Role instance.
     */
    public static Role createRole(final IRI id) {
        return new RoleImpl(id);
    }
}
