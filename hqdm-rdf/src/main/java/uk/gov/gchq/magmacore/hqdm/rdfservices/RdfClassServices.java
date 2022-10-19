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
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Service for creating HQDM Classes.
 */
public class RdfClassServices {
    /**
     * Create a {@link Class} with an String.
     *
     * @param id ID of the Class.
     * @return A Class instance.
     */
    public static Class createClass(final String id) {
        final Class result = ClassServices.createClass(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS);
        return result;
    }

    /**
     * Create a {@link ClassOfAbstractObject} with an String.
     *
     * @param id ID of the ClassOfAbstractObject.
     * @return A ClassOfAbstractObject instance.
     */
    public static ClassOfAbstractObject createClassOfAbstractObject(final String id) {
        final ClassOfAbstractObject result = ClassServices.createClassOfAbstractObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ABSTRACT_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfActivity} with an String.
     *
     * @param id ID of the ClassOfActivity.
     * @return A ClassOfActivity instance.
     */
    public static ClassOfActivity createClassOfActivity(final String id) {
        final ClassOfActivity result = ClassServices.createClassOfActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link ClassOfAgreeContract} with an String.
     *
     * @param id ID of the ClassOfAgreeContract.
     * @return A ClassOfAgreeContract instance.
     */
    public static ClassOfAgreeContract createClassOfAgreeContract(final String id) {
        final ClassOfAgreeContract result = ClassServices.createClassOfAgreeContract(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_AGREE_CONTRACT);
        return result;
    }

    /**
     * Create a {@link ClassOfAgreementExecution} with an String.
     *
     * @param id ID of the ClassOfAgreementExecution.
     * @return A ClassOfAgreementExecution instance.
     */
    public static ClassOfAgreementExecution createClassOfAgreementExecution(final String id) {
        final ClassOfAgreementExecution result = ClassServices.createClassOfAgreementExecution(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_AGREEMENT_EXECUTION);
        return result;
    }

    /**
     * Create a {@link ClassOfAgreementProcess} with an String.
     *
     * @param id ID of the ClassOfAgreementProcess.
     * @return A ClassOfAgreementProcess instance.
     */
    public static ClassOfAgreementProcess createClassOfAgreementProcess(final String id) {
        final ClassOfAgreementProcess result = ClassServices.createClassOfAgreementProcess(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_AGREEMENT_PROCESS);
        return result;
    }

    /**
     * Create a {@link ClassOfAmountOfMoney} with an String.
     *
     * @param id ID of the ClassOfAmountOfMoney.
     * @return A ClassOfAmountOfMoney instance.
     */
    public static ClassOfAmountOfMoney createClassOfAmountOfMoney(final String id) {
        final ClassOfAmountOfMoney result = ClassServices.createClassOfAmountOfMoney(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_AMOUNT_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link ClassOfAssociation} with an String.
     *
     * @param id ID of the ClassOfAssociation.
     * @return A ClassOfAssociation instance.
     */
    public static ClassOfAssociation createClassOfAssociation(final String id) {
        final ClassOfAssociation result = ClassServices.createClassOfAssociation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link ClassOfBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfBiologicalObject.
     * @return A ClassOfBiologicalObject instance.
     */
    public static ClassOfBiologicalObject createClassOfBiologicalObject(final String id) {
        final ClassOfBiologicalObject result = ClassServices.createClassOfBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfBiologicalSystem} with an String.
     *
     * @param id ID of the ClassOfBiologicalSystem.
     * @return A ClassOfBiologicalSystem instance.
     */
    public static ClassOfBiologicalSystem createClassOfBiologicalSystem(final String id) {
        final ClassOfBiologicalSystem result = ClassServices.createClassOfBiologicalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link ClassOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfBiologicalSystemComponent.
     * @return A ClassOfBiologicalSystemComponent instance.
     */
    public static ClassOfBiologicalSystemComponent createClassOfBiologicalSystemComponent(final String id) {
        final ClassOfBiologicalSystemComponent result = ClassServices.createClassOfBiologicalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfClass} with an String.
     *
     * @param id ID of the ClassOfClass.
     * @return A ClassOfClass instance.
     */
    public static ClassOfClass createClassOfClass(final String id) {
        final ClassOfClass result = ClassServices.createClassOfClass(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_CLASS);
        return result;
    }

    /**
     * Create a {@link ClassOfClassOfSpatioTemporalExtent} with an String.
     *
     * @param id ID of the ClassOfClassOfSpatioTemporalExtent.
     * @return A ClassOfClassOfSpatioTemporalExtent instance.
     */
    public static ClassOfClassOfSpatioTemporalExtent createClassOfClassOfSpatioTemporalExtent(final String id) {
        final ClassOfClassOfSpatioTemporalExtent result = ClassServices.createClassOfClassOfSpatioTemporalExtent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_CLASS_OF_SPATIO_TEMPORAL_EXTENT);
        return result;
    }

    /**
     * Create a {@link ClassOfContractExecution} with an String.
     *
     * @param id ID of the ClassOfContractExecution.
     * @return A ClassOfContractExecution instance.
     */
    public static ClassOfContractExecution createClassOfContractExecution(final String id) {
        final ClassOfContractExecution result = ClassServices.createClassOfContractExecution(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_CONTRACT_EXECUTION);
        return result;
    }

    /**
     * Create a {@link ClassOfContractProcess} with an String.
     *
     * @param id ID of the ClassOfContractProcess.
     * @return A ClassOfContractProcess instance.
     */
    public static ClassOfContractProcess createClassOfContractProcess(final String id) {
        final ClassOfContractProcess result = ClassServices.createClassOfContractProcess(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_CONTRACT_PROCESS);
        return result;
    }

    /**
     * Create a {@link ClassOfEvent} with an String.
     *
     * @param id ID of the ClassOfEvent.
     * @return A ClassOfEvent instance.
     */
    public static ClassOfEvent createClassOfEvent(final String id) {
        final ClassOfEvent result = ClassServices.createClassOfEvent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_EVENT);
        return result;
    }

    /**
     * Create a {@link ClassOfFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfFunctionalObject.
     * @return A ClassOfFunctionalObject instance.
     */
    public static ClassOfFunctionalObject createClassOfFunctionalObject(final String id) {
        final ClassOfFunctionalObject result = ClassServices.createClassOfFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfFunctionalSystem} with an String.
     *
     * @param id ID of the ClassOfFunctionalSystem.
     * @return A ClassOfFunctionalSystem instance.
     */
    public static ClassOfFunctionalSystem createClassOfFunctionalSystem(final String id) {
        final ClassOfFunctionalSystem result = ClassServices.createClassOfFunctionalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_FUNCTIONAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link ClassOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfFunctionalSystemComponent.
     * @return A ClassOfFunctionalSystemComponent instance.
     */
    public static ClassOfFunctionalSystemComponent createClassOfFunctionalSystemComponent(final String id) {
        final ClassOfFunctionalSystemComponent result = ClassServices.createClassOfFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfInPlaceBiologicalComponent} with an String.
     *
     * @param id ID of the ClassOfInPlaceBiologicalComponent.
     * @return A ClassOfInPlaceBiologicalComponent instance.
     */
    public static ClassOfInPlaceBiologicalComponent createClassOfInPlaceBiologicalComponent(final String id) {
        final ClassOfInPlaceBiologicalComponent result = ClassServices.createClassOfInPlaceBiologicalComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_IN_PLACE_BIOLOGICAL_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfIndividual} with an String.
     *
     * @param id ID of the ClassOfIndividual.
     * @return A ClassOfIndividual instance.
     */
    public static ClassOfIndividual createClassOfIndividual(final String id) {
        final ClassOfIndividual result = ClassServices.createClassOfIndividual(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_INDIVIDUAL);
        return result;
    }

    /**
     * Create a {@link ClassOfInstalledFunctionalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfInstalledFunctionalSystemComponent.
     * @return A ClassOfInstalledFunctionalSystemComponent instance.
     */
    public static ClassOfInstalledFunctionalSystemComponent createClassOfInstalledFunctionalSystemComponent(
            final String id) {
        final ClassOfInstalledFunctionalSystemComponent result = ClassServices

                .createClassOfInstalledFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfInstalledObject} with an String.
     *
     * @param id ID of the ClassOfInstalledObject.
     * @return A ClassOfInstalledObject instance.
     */
    public static ClassOfInstalledObject createClassOfInstalledObject(final String id) {
        final ClassOfInstalledObject result = ClassServices.createClassOfInstalledObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_INSTALLED_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfIntentionallyConstructedObject.
     * @return A ClassOfIntentionallyConstructedObject instance.
     */
    public static ClassOfIntentionallyConstructedObject createClassOfIntentionallyConstructedObject(final String id) {
        final ClassOfIntentionallyConstructedObject result = ClassServices
                .createClassOfIntentionallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfOffer} with an String.
     *
     * @param id ID of the ClassOfOffer.
     * @return A ClassOfOffer instance.
     */
    public static ClassOfOffer createClassOfOffer(final String id) {
        final ClassOfOffer result = ClassServices.createClassOfOffer(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_OFFER);
        return result;
    }

    /**
     * Create a {@link ClassOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfOrdinaryBiologicalObject.
     * @return A ClassOfOrdinaryBiologicalObject instance.
     */
    public static ClassOfOrdinaryBiologicalObject createClassOfOrdinaryBiologicalObject(final String id) {
        final ClassOfOrdinaryBiologicalObject result = ClassServices.createClassOfOrdinaryBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfOrdinaryFunctionalObject.
     * @return A ClassOfOrdinaryFunctionalObject instance.
     */
    public static ClassOfOrdinaryFunctionalObject createClassOfOrdinaryFunctionalObject(final String id) {
        final ClassOfOrdinaryFunctionalObject result = ClassServices.createClassOfOrdinaryFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfOrdinaryPhysicalObject.
     * @return A ClassOfOrdinaryPhysicalObject instance.
     */
    public static ClassOfOrdinaryPhysicalObject createClassOfOrdinaryPhysicalObject(final String id) {
        final ClassOfOrdinaryPhysicalObject result = ClassServices.createClassOfOrdinaryPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfOrganization} with an String.
     *
     * @param id ID of the ClassOfOrganization.
     * @return A ClassOfOrganization instance.
     */
    public static ClassOfOrganization createClassOfOrganization(final String id) {
        final ClassOfOrganization result = ClassServices.createClassOfOrganization(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link ClassOfOrganizationComponent} with an String.
     *
     * @param id ID of the ClassOfOrganizationComponent.
     * @return A ClassOfOrganizationComponent instance.
     */
    public static ClassOfOrganizationComponent createClassOfOrganizationComponent(final String id) {
        final ClassOfOrganizationComponent result = ClassServices.createClassOfOrganizationComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfParticipant} with an String.
     *
     * @param id ID of the ClassOfParticipant.
     * @return A ClassOfParticipant instance.
     */
    public static ClassOfParticipant createClassOfParticipant(final String id) {
        final ClassOfParticipant result = ClassServices.createClassOfParticipant(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PARTICIPANT);
        return result;
    }

    /**
     * Create a {@link ClassOfParty} with an String.
     *
     * @param id ID of the ClassOfParty.
     * @return A ClassOfParty instance.
     */
    public static ClassOfParty createClassOfParty(final String id) {
        final ClassOfParty result = ClassServices.createClassOfParty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PARTY);
        return result;
    }

    /**
     * Create a {@link ClassOfPeriodOfTime} with an String.
     *
     * @param id ID of the ClassOfPeriodOfTime.
     * @return A ClassOfPeriodOfTime instance.
     */
    public static ClassOfPeriodOfTime createClassOfPeriodOfTime(final String id) {
        final ClassOfPeriodOfTime result = ClassServices.createClassOfPeriodOfTime(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PERIOD_OF_TIME);
        return result;
    }

    /**
     * Create a {@link ClassOfPerson} with an String.
     *
     * @param id ID of the ClassOfPerson.
     * @return A ClassOfPerson instance.
     */
    public static ClassOfPerson createClassOfPerson(final String id) {
        final ClassOfPerson result = ClassServices.createClassOfPerson(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PERSON);
        return result;
    }

    /**
     * Create a {@link ClassOfPersonInPosition} with an String.
     *
     * @param id ID of the ClassOfPersonInPosition.
     * @return A ClassOfPersonInPosition instance.
     */
    public static ClassOfPersonInPosition createClassOfPersonInPosition(final String id) {
        final ClassOfPersonInPosition result = ClassServices.createClassOfPersonInPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PERSON_IN_POSITION);
        return result;
    }

    /**
     * Create a {@link ClassOfPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfPhysicalObject.
     * @return A ClassOfPhysicalObject instance.
     */
    public static ClassOfPhysicalObject createClassOfPhysicalObject(final String id) {
        final ClassOfPhysicalObject result = ClassServices.createClassOfPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfPhysicalProperty} with an String.
     *
     * @param id ID of the ClassOfPhysicalProperty.
     * @return A ClassOfPhysicalProperty instance.
     */
    public static ClassOfPhysicalProperty createClassOfPhysicalProperty(final String id) {
        final ClassOfPhysicalProperty result = ClassServices.createClassOfPhysicalProperty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PHYSICAL_PROPERTY);
        return result;
    }

    /**
     * Create a {@link ClassOfPhysicalQuantity} with an String.
     *
     * @param id ID of the ClassOfPhysicalQuantity.
     * @return A ClassOfPhysicalQuantity instance.
     */
    public static ClassOfPhysicalQuantity createClassOfPhysicalQuantity(final String id) {
        final ClassOfPhysicalQuantity result = ClassServices.createClassOfPhysicalQuantity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_PHYSICAL_QUANTITY);
        return result;
    }

    /**
     * Create a {@link ClassOfPointInTime} with an String.
     *
     * @param id ID of the ClassOfPointInTime.
     * @return A ClassOfPointInTime instance.
     */
    public static ClassOfPointInTime createClassOfPointInTime(final String id) {
        final ClassOfPointInTime result = ClassServices.createClassOfPointInTime(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_POINT_IN_TIME);
        return result;
    }

    /**
     * Create a {@link ClassOfPosition} with an String.
     *
     * @param id ID of the ClassOfPosition.
     * @return A ClassOfPosition instance.
     */
    public static ClassOfPosition createClassOfPosition(final String id) {
        final ClassOfPosition result = ClassServices.createClassOfPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_POSITION);
        return result;
    }

    /**
     * Create a {@link ClassOfPossibleWorld} with an String.
     *
     * @param id ID of the ClassOfPossibleWorld.
     * @return A ClassOfPossibleWorld instance.
     */
    public static ClassOfPossibleWorld createClassOfPossibleWorld(final String id) {
        final ClassOfPossibleWorld result = ClassServices.createClassOfPossibleWorld(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_POSSIBLE_WORLD);
        return result;
    }

    /**
     * Create a {@link ClassOfReachingAgreement} with an String.
     *
     * @param id ID of the ClassOfReachingAgreement.
     * @return A ClassOfReachingAgreement instance.
     */
    public static ClassOfReachingAgreement createClassOfReachingAgreement(final String id) {
        final ClassOfReachingAgreement result = ClassServices.createClassOfReachingAgreement(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_REACHING_AGREEMENT);
        return result;
    }

    /**
     * Create a {@link ClassOfRelationship} with an String.
     *
     * @param id ID of the ClassOfRelationship.
     * @return A ClassOfRelationship instance.
     */
    public static ClassOfRelationship createClassOfRelationship(final String id) {
        final ClassOfRelationship result = ClassServices.createClassOfRelationship(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_RELATIONSHIP);
        return result;
    }

    /**
     * Create a {@link ClassOfRepresentation} with an String.
     *
     * @param id ID of the ClassOfRepresentation.
     * @return A ClassOfRepresentation instance.
     */
    public static ClassOfRepresentation createClassOfRepresentation(final String id) {
        final ClassOfRepresentation result = ClassServices.createClassOfRepresentation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_REPRESENTATION);
        return result;
    }

    /**
     * Create a {@link ClassOfSalesProductInstance} with an String.
     *
     * @param id ID of the ClassOfSalesProductInstance.
     * @return A ClassOfSalesProductInstance instance.
     */
    public static ClassOfSalesProductInstance createClassOfSalesProductInstance(final String id) {
        final ClassOfSalesProductInstance result = ClassServices.createClassOfSalesProductInstance(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SALES_PRODUCT_INSTANCE);
        return result;
    }

    /**
     * Create a {@link ClassOfSign} with an String.
     *
     * @param id ID of the ClassOfSign.
     * @return A ClassOfSign instance.
     */
    public static ClassOfSign createClassOfSign(final String id) {
        final ClassOfSign result = ClassServices.createClassOfSign(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SIGN);
        return result;
    }

    /**
     * Create a {@link ClassOfSociallyConstructedActivity} with an String.
     *
     * @param id ID of the ClassOfSociallyConstructedActivity.
     * @return A ClassOfSociallyConstructedActivity instance.
     */
    public static ClassOfSociallyConstructedActivity createClassOfSociallyConstructedActivity(final String id) {
        final ClassOfSociallyConstructedActivity result = ClassServices.createClassOfSociallyConstructedActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SOCIALLY_CONSTRUCTED_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link ClassOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfSociallyConstructedObject.
     * @return A ClassOfSociallyConstructedObject instance.
     */
    public static ClassOfSociallyConstructedObject createClassOfSociallyConstructedObject(final String id) {
        final ClassOfSociallyConstructedObject result = ClassServices.createClassOfSociallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfSpatioTemporalExtent} with an String.
     *
     * @param id ID of the ClassOfSpatioTemporalExtent.
     * @return A ClassOfSpatioTemporalExtent instance.
     */
    public static ClassOfSpatioTemporalExtent createClassOfSpatioTemporalExtent(final String id) {
        final ClassOfSpatioTemporalExtent result = ClassServices.createClassOfSpatioTemporalExtent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SPATIO_TEMPORAL_EXTENT);
        return result;
    }

    /**
     * Create a {@link ClassOfState} with an String.
     *
     * @param id ID of the ClassOfState.
     * @return A ClassOfState instance.
     */
    public static ClassOfState createClassOfState(final String id) {
        final ClassOfState result = ClassServices.createClassOfState(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfActivity} with an String.
     *
     * @param id ID of the ClassOfStateOfActivity.
     * @return A ClassOfStateOfActivity instance.
     */
    public static ClassOfStateOfActivity createClassOfStateOfActivity(final String id) {
        final ClassOfStateOfActivity result = ClassServices.createClassOfStateOfActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfAmountOfMoney} with an String.
     *
     * @param id ID of the ClassOfStateOfAmountOfMoney.
     * @return A ClassOfStateOfAmountOfMoney instance.
     */
    public static ClassOfStateOfAmountOfMoney createClassOfStateOfAmountOfMoney(final String id) {
        final ClassOfStateOfAmountOfMoney result = ClassServices.createClassOfStateOfAmountOfMoney(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_AMOUNT_OF_MONEY);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfAssociation} with an String.
     *
     * @param id ID of the ClassOfStateOfAssociation.
     * @return A ClassOfStateOfAssociation instance.
     */
    public static ClassOfStateOfAssociation createClassOfStateOfAssociation(final String id) {
        final ClassOfStateOfAssociation result = ClassServices.createClassOfStateOfAssociation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfBiologicalObject.
     * @return A ClassOfStateOfBiologicalObject instance.
     */
    public static ClassOfStateOfBiologicalObject createClassOfStateOfBiologicalObject(final String id) {
        final ClassOfStateOfBiologicalObject result = ClassServices.createClassOfStateOfBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfBiologicalSystem} with an String.
     *
     * @param id ID of the ClassOfStateOfBiologicalSystem.
     * @return A ClassOfStateOfBiologicalSystem instance.
     */
    public static ClassOfStateOfBiologicalSystem createClassOfStateOfBiologicalSystem(final String id) {
        final ClassOfStateOfBiologicalSystem result = ClassServices.createClassOfStateOfBiologicalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfBiologicalSystemComponent.
     * @return A ClassOfStateOfBiologicalSystemComponent instance.
     */
    public static ClassOfStateOfBiologicalSystemComponent createClassOfStateOfBiologicalSystemComponent(
            final String id) {
        final ClassOfStateOfBiologicalSystemComponent result = ClassServices
                .createClassOfStateOfBiologicalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfFunctionalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfFunctionalObject.
     * @return A ClassOfStateOfFunctionalObject instance.
     */
    public static ClassOfStateOfFunctionalObject createClassOfStateOfFunctionalObject(final String id) {
        final ClassOfStateOfFunctionalObject result = ClassServices.createClassOfStateOfFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfFunctionalSystem} with an String.
     *
     * @param id ID of the ClassOfStateOfFunctionalSystem.
     * @return A ClassOfStateOfFunctionalSystem instance.
     */
    public static ClassOfStateOfFunctionalSystem createClassOfStateOfFunctionalSystem(final String id) {
        final ClassOfStateOfFunctionalSystem result = ClassServices.createClassOfStateOfFunctionalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SYSTEM);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfFunctionalSystemComponent.
     * @return A ClassOfStateOfFunctionalSystemComponent instance.
     */

    public static ClassOfStateOfFunctionalSystemComponent createClassOfStateOfFunctionalSystemComponent(
            final String id) {
        final ClassOfStateOfFunctionalSystemComponent result = ClassServices
                .createClassOfStateOfFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfStateOfIntentionallyConstructedObject.
     * @return A ClassOfStateOfIntentionallyConstructedObject instance.
     */

    public static ClassOfStateOfIntentionallyConstructedObject createClassOfStateOfIntentionallyConstructedObject(
            final String id) {
        final ClassOfStateOfIntentionallyConstructedObject result = ClassServices
                .createClassOfStateOfIntentionallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE,
                HQDM.CLASS_OF_STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfOrdinaryBiologicalObject.
     * @return A ClassOfStateOfOrdinaryBiologicalObject instance.
     */
    public static ClassOfStateOfOrdinaryBiologicalObject createClassOfStateOfOrdinaryBiologicalObject(final String id) {
        final ClassOfStateOfOrdinaryBiologicalObject result = ClassServices
                .createClassOfStateOfOrdinaryBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfOrdinaryFunctionalObject} with an Stri ng.
     *
     * @param id ID of the ClassOfStateOfOrdinaryFunctionalObject.
     * @return A ClassOfStateOfOrdinaryFunctionalObject instance.
     */
    public static ClassOfStateOfOrdinaryFunctionalObject createClassOfStateOfOrdinaryFunctionalObject(final String id) {
        final ClassOfStateOfOrdinaryFunctionalObject result = ClassServices
                .createClassOfStateOfOrdinaryFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfOrdinaryPhysicalObject.
     * @return A ClassOfStateOfOrdinaryPhysicalObject instance.
     */
    public static ClassOfStateOfOrdinaryPhysicalObject createClassOfStateOfOrdinaryPhysicalObject(final String id) {
        final ClassOfStateOfOrdinaryPhysicalObject result = ClassServices
                .createClassOfStateOfOrdinaryPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfOrganization} with an String.
     *
     * @param id ID of the ClassOfStateOfOrganization.
     * @return A ClassOfStateOfOrganization instance.
     */
    public static ClassOfStateOfOrganization createClassOfStateOfOrganization(final String id) {
        final ClassOfStateOfOrganization result = ClassServices.createClassOfStateOfOrganization(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfOrganizationComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfOrganizationComponent.
     * @return A ClassOfStateOfOrganizationComponent instance.
     */
    public static ClassOfStateOfOrganizationComponent createClassOfStateOfOrganizationComponent(final String id) {
        final ClassOfStateOfOrganizationComponent result = ClassServices.createClassOfStateOfOrganizationComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfParty} with an String.
     *
     * @param id ID of the ClassOfStateOfParty.
     * @return A ClassOfStateOfParty instance.
     */
    public static ClassOfStateOfParty createClassOfStateOfParty(final String id) {
        final ClassOfStateOfParty result = ClassServices.createClassOfStateOfParty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_PARTY);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfPerson} with an String.
     *
     * @param id ID of the ClassOfStateOfPerson.
     * @return A ClassOfStateOfPerson instance.
     */
    public static ClassOfStateOfPerson createClassOfStateOfPerson(final String id) {
        final ClassOfStateOfPerson result = ClassServices.createClassOfStateOfPerson(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_PERSON);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfPhysicalObject} with an String.
     *
     * @param id ID of the ClassOfStateOfPhysicalObject.
     * @return A ClassOfStateOfPhysicalObject instance.
     */
    public static ClassOfStateOfPhysicalObject createClassOfStateOfPhysicalObject(final String id) {
        final ClassOfStateOfPhysicalObject result = ClassServices.createClassOfStateOfPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfPosition} with an String.
     *
     * @param id ID of the ClassOfStateOfPosition.
     * @return A ClassOfStateOfPosition instance.
     */
    public static ClassOfStateOfPosition createClassOfStateOfPosition(final String id) {
        final ClassOfStateOfPosition result = ClassServices.createClassOfStateOfPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_POSITION);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfSalesProductInstance} with an String.
     *
     * @param id ID of the ClassOfStateOfSalesProductInstance.
     * @return A ClassOfStateOfSalesProductInstance instance.
     */
    public static ClassOfStateOfSalesProductInstance createClassOfStateOfSalesProductInstance(final String id) {
        final ClassOfStateOfSalesProductInstance result = ClassServices.createClassOfStateOfSalesProductInstance(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SALES_PRODUCT_INSTANCE);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfSign} with an String.
     *
     * @param id ID of the ClassOfStateOfSign.
     * @return A ClassOfStateOfSign instance.
     */
    public static ClassOfStateOfSign createClassOfStateOfSign(final String id) {
        final ClassOfStateOfSign result = ClassServices.createClassOfStateOfSign(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SIGN);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfSociallyConstructedActivity} with an String.
     *
     * @param id ID of the ClassOfStateOfSociallyConstructedActivity.
     * @return A ClassOfStateOfSociallyConstructedActivity instance.
     */
    public static ClassOfStateOfSociallyConstructedActivity createClassOfStateOfSociallyConstructedActivity(
            final String id) {
        final ClassOfStateOfSociallyConstructedActivity result = ClassServices
                .createClassOfStateOfSociallyConstructedActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the ClassOfStateOfSociallyConstructedObject.
     * @return A ClassOfStateOfSociallyConstructedObject instance.
     */
    public static ClassOfStateOfSociallyConstructedObject createClassOfStateOfSociallyConstructedObject(
            final String id) {
        final ClassOfStateOfSociallyConstructedObject result = ClassServices
                .createClassOfStateOfSociallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfSystem} with an String.
     *
     * @param id ID of the ClassOfStateOfSystem.
     * @return A ClassOfStateOfSystem instance.
     */
    public static ClassOfStateOfSystem createClassOfStateOfSystem(final String id) {
        final ClassOfStateOfSystem result = ClassServices.createClassOfStateOfSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SYSTEM);
        return result;
    }

    /**
     * Create a {@link ClassOfStateOfSystemComponent} with an String.
     *
     * @param id ID of the ClassOfStateOfSystemComponent.
     * @return A ClassOfStateOfSystemComponent instance.
     */
    public static ClassOfStateOfSystemComponent createClassOfStateOfSystemComponent(final String id) {
        final ClassOfStateOfSystemComponent result = ClassServices.createClassOfStateOfSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_STATE_OF_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link ClassOfSystem} with an String.
     *
     * @param id ID of the ClassOfSystem.
     * @return A ClassOfSystem instance.
     */
    public static ClassOfSystem createClassOfSystem(final String id) {
        final ClassOfSystem result = ClassServices.createClassOfSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SYSTEM);
        return result;
    }

    /**
     * Create a {@link ClassOfSystemComponent} with an String.
     *
     * @param id ID of the ClassOfSystemComponent.
     * @return A ClassOfSystemComponent instance.
     */
    public static ClassOfSystemComponent createClassOfSystemComponent(final String id) {
        final ClassOfSystemComponent result = ClassServices.createClassOfSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASS_OF_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link KindOfActivity} with an String.
     *
     * @param id ID of the KindOfActivity.
     * @return A KindOfActivity instance.
     */
    public static KindOfActivity createKindOfActivity(final String id) {
        final KindOfActivity result = ClassServices.createKindOfActivity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ACTIVITY);
        return result;
    }

    /**
     * Create a {@link KindOfAssociation} with an String.
     *
     * @param id ID of the KindOfAssociation.
     * @return A KindOfAssociation instance.
     */
    public static KindOfAssociation createKindOfAssociation(final String id) {
        final KindOfAssociation result = ClassServices.createKindOfAssociation(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ASSOCIATION);
        return result;
    }

    /**
     * Create a {@link KindOfBiologicalObject} with an String.
     *
     * @param id ID of the KindOfBiologicalObject.
     * @return A KindOfBiologicalObject instance.
     */
    public static KindOfBiologicalObject createKindOfBiologicalObject(final String id) {
        final KindOfBiologicalObject result = ClassServices.createKindOfBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfBiologicalSystem} with an String.
     *
     * @param id ID of the KindOfBiologicalSystem.
     * @return A KindOfBiologicalSystem instance.
     */
    public static KindOfBiologicalSystem createKindOfBiologicalSystem(final String id) {
        final KindOfBiologicalSystem result = ClassServices.createKindOfBiologicalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_BIOLOGICAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link KindOfBiologicalSystemComponent} with an String.
     *
     * @param id ID of the KindOfBiologicalSystemComponent.
     * @return A KindOfBiologicalSystemComponent instance.
     */
    public static KindOfBiologicalSystemComponent createKindOfBiologicalSystemComponent(final String id) {
        final KindOfBiologicalSystemComponent result = ClassServices.createKindOfBiologicalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_BIOLOGICAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link KindOfFunctionalObject} with an String.
     *
     * @param id ID of the KindOfFunctionalObject.
     * @return A KindOfFunctionalObject instance.
     */
    public static KindOfFunctionalObject createKindOfFunctionalObject(final String id) {
        final KindOfFunctionalObject result = ClassServices.createKindOfFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfFunctionalSystem} with an String.
     *
     * @param id ID of the KindOfFunctionalSystem.
     * @return A KindOfFunctionalSystem instance.
     */
    public static KindOfFunctionalSystem createKindOfFunctionalSystem(final String id) {
        final KindOfFunctionalSystem result = ClassServices.createKindOfFunctionalSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_FUNCTIONAL_SYSTEM);
        return result;
    }

    /**
     * Create a {@link KindOfFunctionalSystemComponent} with an String.
     *
     * @param id ID of the KindOfFunctionalSystemComponent.
     * @return A KindOfFunctionalSystemComponent instance.
     */
    public static KindOfFunctionalSystemComponent createKindOfFunctionalSystemComponent(final String id) {
        final KindOfFunctionalSystemComponent result = ClassServices.createKindOfFunctionalSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_FUNCTIONAL_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link KindOfIndividual} with an String.
     *
     * @param id ID of the KindOfIndividual.
     * @return A KindOfIndividual instance.
     */
    public static KindOfIndividual createKindOfIndividual(final String id) {
        final KindOfIndividual result = ClassServices.createKindOfIndividual(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_INDIVIDUAL);
        return result;

    }

    /**
     * Create a {@link KindOfIntentionallyConstructedObject} with an String.
     *
     * @param id ID of the KindOfIntentionallyConstructedObject.
     * @return A KindOfIntentionallyConstructedObject instance.
     */
    public static KindOfIntentionallyConstructedObject createKindOfIntentionallyConstructedObject(final String id) {
        final KindOfIntentionallyConstructedObject result = ClassServices
                .createKindOfIntentionallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_INTENTIONALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfOrdinaryBiologicalObject} with an String.
     *
     * @param id ID of the KindOfOrdinaryBiologicalObject.
     * @return A KindOfOrdinaryBiologicalObject instance.
     */
    public static KindOfOrdinaryBiologicalObject createKindOfOrdinaryBiologicalObject(final String id) {
        final KindOfOrdinaryBiologicalObject result = ClassServices.createKindOfOrdinaryBiologicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ORDINARY_BIOLOGICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfOrdinaryFunctionalObject} with an String.
     *
     * @param id ID of the KindOfOrdinaryFunctionalObject.
     * @return A KindOfOrdinaryFunctionalObject instance.
     */
    public static KindOfOrdinaryFunctionalObject createKindOfOrdinaryFunctionalObject(final String id) {
        final KindOfOrdinaryFunctionalObject result = ClassServices.createKindOfOrdinaryFunctionalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ORDINARY_FUNCTIONAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfOrdinaryPhysicalObject} with an String.
     *
     * @param id ID of the KindOfOrdinaryPhysicalObject.
     * @return A KindOfOrdinaryPhysicalObject instance.
     */
    public static KindOfOrdinaryPhysicalObject createKindOfOrdinaryPhysicalObject(final String id) {
        final KindOfOrdinaryPhysicalObject result = ClassServices.createKindOfOrdinaryPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ORDINARY_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfOrganization} with an String.
     *
     * @param id ID of the KindOfOrganization.
     * @return A KindOfOrganization instance.
     */
    public static KindOfOrganization createKindOfOrganization(final String id) {
        final KindOfOrganization result = ClassServices.createKindOfOrganization(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ORGANIZATION);
        return result;
    }

    /**
     * Create a {@link KindOfOrganizationComponent} with an String.
     *
     * @param id ID of the KindOfOrganizationComponent.
     * @return A KindOfOrganizationComponent instance.
     */
    public static KindOfOrganizationComponent createKindOfOrganizationComponent(final String id) {
        final KindOfOrganizationComponent result = ClassServices.createKindOfOrganizationComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_ORGANIZATION_COMPONENT);
        return result;
    }

    /**
     * Create a {@link KindOfParty} with an String.
     *
     * @param id ID of the KindOfParty.
     * @return A KindOfParty instance.
     */
    public static KindOfParty createKindOfParty(final String id) {
        final KindOfParty result = ClassServices.createKindOfParty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_PARTY);
        return result;
    }

    /**
     * Create a {@link KindOfPerson} with an String.
     *
     * @param id ID of the KindOfPerson.
     * @return A KindOfPerson instance.
     */
    public static KindOfPerson createKindOfPerson(final String id) {
        final KindOfPerson result = ClassServices.createKindOfPerson(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_PERSON);
        return result;
    }

    /**
     * Create a {@link KindOfPhysicalObject} with an String.
     *
     * @param id ID of the KindOfPhysicalObject.
     * @return A KindOfPhysicalObject instance.
     */
    public static KindOfPhysicalObject createKindOfPhysicalObject(final String id) {
        final KindOfPhysicalObject result = ClassServices.createKindOfPhysicalObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_PHYSICAL_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfPhysicalProperty} with an String.
     *
     * @param id ID of the KindOfPhysicalProperty.
     * @return A KindOfPhysicalProperty instance.
     */
    public static KindOfPhysicalProperty createKindOfPhysicalProperty(final String id) {
        final KindOfPhysicalProperty result = ClassServices.createKindOfPhysicalProperty(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_PHYSICAL_PROPERTY);
        return result;
    }

    /**
     * Create a {@link KindOfPhysicalQuantity} with an String.
     *
     * @param id ID of the KindOfPhysicalQuantity.
     * @return A KindOfPhysicalQuantity instance.
     */
    public static KindOfPhysicalQuantity createKindOfPhysicalQuantity(final String id) {
        final KindOfPhysicalQuantity result = ClassServices.createKindOfPhysicalQuantity(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_PHYSICAL_QUANTITY);
        return result;
    }

    /**
     * Create a {@link KindOfPosition} with an String.
     *
     * @param id ID of the KindOfPosition.
     * @return A KindOfPosition instance.
     */
    public static KindOfPosition createKindOfPosition(final String id) {
        final KindOfPosition result = ClassServices.createKindOfPosition(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_POSITION);
        return result;
    }

    /**
     * Create a {@link KindOfRelationshipWithRestriction} with an String.
     *
     * @param id ID of the KindOfRelationshipWithRestriction.
     * @return A KindOfRelationshipWithRestriction instance.
     */
    public static KindOfRelationshipWithRestriction createKindOfRelationshipWithRestriction(final String id) {
        final KindOfRelationshipWithRestriction result = ClassServices.createKindOfRelationshipWithRestriction(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_RELATIONSHIP_WITH_RESTRICTION);
        return result;
    }

    /**
     * Create a {@link KindOfRelationshipWithSignature} with an String.
     *
     * @param id ID of the KindOfRelationshipWithSignature.
     * @return A KindOfRelationshipWithSignature instance.
     */
    public static KindOfRelationshipWithSignature createKindOfRelationshipWithSignature(final String id) {
        final KindOfRelationshipWithSignature result = ClassServices.createKindOfRelationshipWithSignature(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_RELATIONSHIP_WITH_SIGNATURE);
        return result;
    }

    /**
     * Create a {@link KindOfSociallyConstructedObject} with an String.
     *
     * @param id ID of the KindOfSociallyConstructedObject.
     * @return A KindOfSociallyConstructedObject instance.
     */
    public static KindOfSociallyConstructedObject createKindOfSociallyConstructedObject(final String id) {
        final KindOfSociallyConstructedObject result = ClassServices.createKindOfSociallyConstructedObject(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_SOCIALLY_CONSTRUCTED_OBJECT);
        return result;
    }

    /**
     * Create a {@link KindOfSystem} with an String.
     *
     * @param id ID of the KindOfSystem.
     * @return A KindOfSystem instance.
     */
    public static KindOfSystem createKindOfSystem(final String id) {
        final KindOfSystem result = ClassServices.createKindOfSystem(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_SYSTEM);
        return result;
    }

    /**
     * Create a {@link KindOfSystemComponent} with an String.
     *
     * @param id ID of the KindOfSystemComponent.
     * @return A KindOfSystemComponent instance.
     */
    public static KindOfSystemComponent createKindOfSystemComponent(final String id) {
        final KindOfSystemComponent result = ClassServices.createKindOfSystemComponent(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.KIND_OF_SYSTEM_COMPONENT);
        return result;
    }

    /**
     * Create a {@link Role} with an String.
     *
     * @param id ID of the Role.
     * @return A Role instance.
     */
    public static Role createRole(final String id) {
        final Role result = ClassServices.createRole(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.ROLE);
        return result;
    }
}
