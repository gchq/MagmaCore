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

package uk.gov.gchq.magmacore.hqdm.rdf.iri;

/**
 * IRI definitions for HQDM entities and associations.
 */
public final class HQDM {

    private HQDM() {
    }

    /** HQDM namespace. */
    public static final IriBase HQDM = new IriBase("hqdm", "https://hqdmtop.github.io/hqdmOntology/1.0.0#");

    /** SKOS namespace. */
    public static final IriBase SKOS = new IriBase("skos", "http://www.w3.org/2004/02/skos/core#");

    /** A human-interpretable name for a particular HQDM entity. */
    public static final HqdmIri ENTITY_NAME = new HqdmIri(SKOS, "prefLabel");

    /** Used to add values to signs etc. **/
    public static final HqdmIri SKOS_DEFINITION = new HqdmIri(SKOS, "definition");
    // =======================================================================
    // Entities
    // =======================================================================

    /** A {@link uk.gov.gchq.magmacore.hqdm.model.Thing} that does not exist in space or time. */
    public static final HqdmIri ABSTRACT_OBJECT = new HqdmIri(HQDM, "AbstractObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} that is the acceptance of
     * an {@link uk.gov.gchq.magmacore.hqdm.model.Offer} as {@link #PART_OF} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreeContract}.
     */
    public static final HqdmIri ACCEPTANCE_OF_OFFER = new HqdmIri(HQDM, "AcceptanceOfOffer");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement} that consists of one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offer}s of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnershipOfMoney} for a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} of goods where one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offer} is accepted.
     */
    public static final HqdmIri ACCEPTANCE_OF_OFFER_FOR_GOODS = new HqdmIri(HQDM, "AcceptanceOfOfferForGoods");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Individual} that consists of its
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant}s and causes some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Event}.
     */
    public static final HqdmIri ACTIVITY = new HqdmIri(HQDM, "Activity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Relationship} where the whole is at least the sum of
     * the parts.
     */
    public static final HqdmIri AGGREGATION = new HqdmIri(HQDM, "Aggregation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement} that consists of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offer} of some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} in exchange for some consideration, and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.AcceptanceOfOffer}.
     */
    public static final HqdmIri AGREE_CONTRACT = new HqdmIri(HQDM, "AgreeContract");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} where two or more parties
     * carry out a course of action previously agreed upon.
     */
    public static final HqdmIri AGREEMENT_EXECUTION = new HqdmIri(HQDM, "AgreementExecution");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} that consists of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement} and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreementExecution}, where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreementExecution} is the course of action agreed to in
     * the {@link uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement}.
     */
    public static final HqdmIri AGREEMENT_PROCESS = new HqdmIri(HQDM, "AgreementProcess");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfAmountOfMoney}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} that is intended and accepted for use as
     * a means of exchange.
     */
    public static final HqdmIri AMOUNT_OF_MONEY = new HqdmIri(HQDM, "AmountOfMoney");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} that is the {@link #PARTICIPANT_IN} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Ownership} that is owned.
     */
    public static final HqdmIri ASSET = new HqdmIri(HQDM, "Asset");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Individual} that {@link #CONSISTS_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant}s that are associated, and where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant}s are {@link #PART_OF} the same
     * {@link uk.gov.gchq.magmacore.hqdm.model.PeriodOfTime}.
     */
    public static final HqdmIri ASSOCIATION = new HqdmIri(HQDM, "Association");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Event} that is the {@link #BEGINNING} of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Ownership}.
     */
    public static final HqdmIri BEGINNING_OF_OWNERSHIP = new HqdmIri(HQDM, "BeginningOfOwnership");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} that sustains itself and reproduces.
     */
    public static final HqdmIri BIOLOGICAL_OBJECT = new HqdmIri(HQDM, "BiologicalObject");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.System} that is also an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryBiologicalObject} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystem}.
     */
    public static final HqdmIri BIOLOGICAL_SYSTEM = new HqdmIri(HQDM, "BiologicalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject},
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystemComponent} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent} that is any
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent}.
     */
    public static final HqdmIri BIOLOGICAL_SYSTEM_COMPONENT = new HqdmIri(HQDM, "BiologicalSystemComponent");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.AbstractObject} that has members and whose identity is
     * defined by its membership.
     */
    public static final HqdmIri CLASS = new HqdmIri(HQDM, "Class");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Relationship} where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} is a member of a
     * class{@link uk.gov.gchq.magmacore.hqdm.model.Class}.
     */
    public static final HqdmIri CLASSIFICATION = new HqdmIri(HQDM, "Classification");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Class} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.AbstractObject} or any its subsets.
     */
    public static final HqdmIri CLASS_OF_ABSTRACT_OBJECT = new HqdmIri(HQDM, "ClassOfAbstractObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfActivity} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity} or any of its possible subsets.
     */
    public static final HqdmIri CLASS_OF_ACTIVITY = new HqdmIri(HQDM, "ClassOfActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfReachingAgreement} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreeContract} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_AGREE_CONTRACT = new HqdmIri(HQDM, "ClassOfAgreeContract");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreementExecution} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_AGREEMENT_EXECUTION = new HqdmIri(HQDM, "ClassOfAgreementExecution");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreementProcess} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_AGREEMENT_PROCESS = new HqdmIri(HQDM, "ClassOfAgreementProcess");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfAmountOfMoney}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.AmountOfMoney} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_AMOUNT_OF_MONEY = new HqdmIri(HQDM, "ClassOfAmountOfMoney");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfAssociation} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_ASSOCIATION = new HqdmIri(HQDM, "ClassOfAssociation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_BIOLOGICAL_OBJECT = new HqdmIri(HQDM, "ClassOfBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalSystem},
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryBiologicalObject}, and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_BIOLOGICAL_SYSTEM = new HqdmIri(HQDM, "ClassOfBiologicalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalObject},
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalSystemComponent}, and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystemComponent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystemComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_BIOLOGICAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "ClassOfBiologicalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Class} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Class} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_CLASS = new HqdmIri(HQDM, "ClassOfClass");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfClass} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_CLASS_OF_SPATIO_TEMPORAL_EXTENT = new HqdmIri(HQDM,
            "ClassOfClassOfSpatioTemporalExtent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfAgreementExecution} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.ContractExecution} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_CONTRACT_EXECUTION = new HqdmIri(HQDM, "ClassOfContractExecution");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfAgreementProcess} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.ContractProcess} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_CONTRACT_PROCESS = new HqdmIri(HQDM, "ClassOfContractProcess");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Event} or any of its possible subsets.
     */
    public static final HqdmIri CLASS_OF_EVENT = new HqdmIri(HQDM, "ClassOfEvent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalObject},
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIntentionallyConstructedObject}, and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfFunctionalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_FUNCTIONAL_OBJECT = new HqdmIri(HQDM, "ClassOfFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfFunctionalSystem}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryFunctionalObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_FUNCTIONAL_SYSTEM = new HqdmIri(HQDM, "ClassOfFunctionalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfFunctionalSystemComponent}, and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystemComponent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent} and any of its subsets.
     */
    public static final HqdmIri CLASS_OF_FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "ClassOfFunctionalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_INDIVIDUAL = new HqdmIri(HQDM, "ClassOfIndividual");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalSystemComponent}, that is also
     * a {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryBiologicalObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfInstalledObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.InPlaceBiologicalComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_IN_PLACE_BIOLOGICAL_COMPONENT = new HqdmIri(HQDM,
            "ClassOfInPlaceBiologicalComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfFunctionalSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfInstalledObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent} and any of its
     * subsets.
     */
    public static final HqdmIri CLASS_OF_INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "ClassOfInstalledFunctionalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.InstalledObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_INSTALLED_OBJECT = new HqdmIri(HQDM, "ClassOfInstalledObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfIntentionallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_INTENTIONALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "ClassOfIntentionallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offer} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_OFFER = new HqdmIri(HQDM, "ClassOfOffer");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalObject},
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryPhysicalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryBiologicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryBiologicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_ORDINARY_BIOLOGICAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfOrdinaryBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryFunctionalObject}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfFunctionalObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_ORDINARY_FUNCTIONAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfOrdinaryFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryPhysicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_ORDINARY_PHYSICAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfOrdinaryPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrganization}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfParty} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Organization} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_ORGANIZATION = new HqdmIri(HQDM, "ClassOfOrganization");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrganizationComponent}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystemComponent}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrganizationComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_ORGANIZATION_COMPONENT = new HqdmIri(HQDM, "ClassOfOrganizationComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PARTICIPANT = new HqdmIri(HQDM, "ClassOfParticipant");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party} or any of its subtypes.
     */
    public static final HqdmIri CLASS_OF_PARTY = new HqdmIri(HQDM, "ClassOfParty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PeriodOfTime} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PERIOD_OF_TIME = new HqdmIri(HQDM, "ClassOfPeriodOfTime");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalSystem},
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPerson} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfParty} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PERSON = new HqdmIri(HQDM, "ClassOfPerson");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfInstalledObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPosition} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PersonInPosition} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PERSON_IN_POSITION = new HqdmIri(HQDM, "ClassOfPersonInPosition");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PHYSICAL_OBJECT = new HqdmIri(HQDM, "ClassOfPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalProperty} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PHYSICAL_PROPERTY = new HqdmIri(HQDM, "ClassOfPhysicalProperty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalProperty} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_PHYSICAL_QUANTITY = new HqdmIri(HQDM, "ClassOfPhysicalQuantity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfEvent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PointInTime} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_POINT_IN_TIME = new HqdmIri(HQDM, "ClassOfPointInTime");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPosition} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrganizationComponent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Position} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_POSITION = new HqdmIri(HQDM, "ClassOfPosition");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPeriodOfTime} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.PossibleWorld} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_POSSIBLE_WORLD = new HqdmIri(HQDM, "ClassOfPossibleWorld");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_REACHING_AGREEMENT = new HqdmIri(HQDM, "ClassOfReachingAgreement");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Class} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Relationship} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_RELATIONSHIP = new HqdmIri(HQDM, "ClassOfRelationship");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfAssociation} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_REPRESENTATION = new HqdmIri(HQDM, "ClassOfRepresentation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSalesProductInstance} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryFunctionalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProductInstance} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SALES_PRODUCT_INSTANCE = new HqdmIri(HQDM, "ClassOfSalesProductInstance");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSign} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SIGN = new HqdmIri(HQDM, "ClassOfSign");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfActivity} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SOCIALLY_CONSTRUCTED_ACTIVITY = new HqdmIri(HQDM,
            "ClassOfSociallyConstructedActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIntentionallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SOCIALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "ClassOfSociallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Class} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SPATIO_TEMPORAL_EXTENT = new HqdmIri(HQDM, "ClassOfSpatioTemporalExtent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE = new HqdmIri(HQDM, "ClassOfState");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfActivity} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ACTIVITY = new HqdmIri(HQDM, "ClassOfStateOfActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSociallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfAmountOfMoney} or one of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_AMOUNT_OF_MONEY = new HqdmIri(HQDM,
            "ClassOfStateOfAmountOfMoney");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfAssociation} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ASSOCIATION = new HqdmIri(HQDM, "ClassOfStateOfAssociation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_BIOLOGICAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryBiologicalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystem} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_BIOLOGICAL_SYSTEM = new HqdmIri(HQDM,
            "ClassOfStateOfBiologicalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystemComponent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystemComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "ClassOfStateOfBiologicalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfIntentionallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_FUNCTIONAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryFunctionalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystem} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM = new HqdmIri(HQDM,
            "ClassOfStateOfFunctionalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryFunctionalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystemComponent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "ClassOfStateOfFunctionalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfIntentionallyConstructedObject} or any of its
     * subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfIntentionallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryBiologicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ORDINARY_BIOLOGICAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfOrdinaryBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfFunctionalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ORDINARY_FUNCTIONAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfOrdinaryFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryPhysicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ORDINARY_PHYSICAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfOrdinaryPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSociallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfParty} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganization} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ORGANIZATION = new HqdmIri(HQDM, "ClassOfStateOfOrganization");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSociallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganizationComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_ORGANIZATION_COMPONENT = new HqdmIri(HQDM,
            "ClassOfStateOfOrganizationComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} or any of its subtypes.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_PARTY = new HqdmIri(HQDM, "ClassOfStateOfParty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalSystem} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfParty} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPerson} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_PERSON = new HqdmIri(HQDM, "ClassOfStateOfPerson");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_PHYSICAL_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrganizationComponent} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPosition} and any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_POSITION = new HqdmIri(HQDM, "ClassOfStateOfPosition");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryFunctionalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSalesProductInstance} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_SALES_PRODUCT_INSTANCE = new HqdmIri(HQDM,
            "ClassOfStateOfSalesProductInstance");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSociallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSign} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_SIGN = new HqdmIri(HQDM, "ClassOfStateOfSign");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSociallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY = new HqdmIri(HQDM,
            "ClassOfStateOfSociallyConstructedActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfIntentionallyConstructedObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} or one of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "ClassOfStateOfSociallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystem} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_SYSTEM = new HqdmIri(HQDM, "ClassOfStateOfSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystemComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_STATE_OF_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "ClassOfStateOfSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystem} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.System} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SYSTEM = new HqdmIri(HQDM, "ClassOfSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalObject} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent} or any of its subsets.
     */
    public static final HqdmIri CLASS_OF_SYSTEM_COMPONENT = new HqdmIri(HQDM, "ClassOfSystemComponent");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} where the {@link #WHOLE} is an
     * arrangement of the parts that results in emergent properties.
     */
    public static final HqdmIri COMPOSITION = new HqdmIri(HQDM, "Composition");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.AgreementExecution} that is the provision of some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} in exchange for some consideration.
     */
    public static final HqdmIri CONTRACT_EXECUTION = new HqdmIri(HQDM, "ContractExecution");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.AgreementProcess} that consists of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreeContract} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ContractExecution}.
     */
    public static final HqdmIri CONTRACT_PROCESS = new HqdmIri(HQDM, "ContractProcess");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfAmountOfMoney} that is the subset of
     * {@link uk.gov.gchq.magmacore.hqdm.model.AmountOfMoney} that has as members all the money issued
     * by an issuing authority.
     */
    public static final HqdmIri CURRENCY = new HqdmIri(HQDM, "Currency");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Relationship} that is defined by a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature}.
     */
    public static final HqdmIri DEFINED_RELATIONSHIP = new HqdmIri(HQDM, "DefinedRelationship");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern} that defines a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Class}.
     */
    public static final HqdmIri DEFINITION = new HqdmIri(HQDM, "Definition");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern} that describes some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing}.
     */
    public static final HqdmIri DESCRIPTION = new HqdmIri(HQDM, "Description");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPerson} that is a {@link #PARTICIPANT_IN} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Employment}.
     */
    public static final HqdmIri EMPLOYEE = new HqdmIri(HQDM, "Employee");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} that is a {@link #PARTICIPANT_IN} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Employment}.
     */
    public static final HqdmIri EMPLOYER = new HqdmIri(HQDM, "Employer");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Association} that consists of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Employer} and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Employee} where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Employer} pays the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Employee} to work for them.
     */
    public static final HqdmIri EMPLOYMENT = new HqdmIri(HQDM, "Employment");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Event} that is the {@link #ENDING} of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Ownership}.
     */
    public static final HqdmIri ENDING_OF_OWNERSHIP = new HqdmIri(HQDM, "EndingOfOwnership");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Class} where each {@link #MEMBER__OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Class} is known.
     */
    public static final HqdmIri ENUMERATED_CLASS = new HqdmIri(HQDM, "EnumeratedClass");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} that has zero temporal thickness
     * and may bound some {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent}.
     */
    public static final HqdmIri EVENT = new HqdmIri(HQDM, "Event");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.AgreementExecution} that consists of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} of goods and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnershipOfMoney}.
     */
    public static final HqdmIri EXCHANGE_OF_GOODS_AND_MONEY = new HqdmIri(HQDM, "ExchangeOfGoodsAndMoney");

    /**
     * A one-to-many {@link uk.gov.gchq.magmacore.hqdm.model.Relationship}.
     */
    public static final HqdmIri FUNCTION_ = new HqdmIri(HQDM, "Function");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} that has an {@link #INTENDED_ROLE}.
     */
    public static final HqdmIri FUNCTIONAL_OBJECT = new HqdmIri(HQDM, "FunctionalObject");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystem} that is also an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.System}.
     */
    public static final HqdmIri FUNCTIONAL_SYSTEM = new HqdmIri(HQDM, "FunctionalSystem");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject} that is a replaceable
     * {@link #COMPONENT_OF} a {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem}.
     */
    public static final HqdmIri FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM, "FunctionalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern} that is a surrogate for the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} {@link #REPRESENTED}.
     */
    public static final HqdmIri IDENTIFICATION = new HqdmIri(HQDM, "Identification");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Identification} that identifies a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity}. An
     * {@link uk.gov.gchq.magmacore.hqdm.model.IdentificationOfPhysicalQuantity} consists of a REAL that
     * is a representation of the {@link #VALUE_} the physical quantity maps to on the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale}.
     */
    public static final HqdmIri IDENTIFICATION_OF_PHYSICAL_QUANTITY = new HqdmIri(HQDM,
            "IdentificationOfPhysicalQuantity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} that is not a proper
     * {@link #TEMPORAL_PART_OF} any other {@link uk.gov.gchq.magmacore.hqdm.model.Individual} of the
     * same kind.
     */
    public static final HqdmIri INDIVIDUAL = new HqdmIri(HQDM, "Individual");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.InstalledObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryBiologicalObject} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystemComponent}.
     */
    public static final HqdmIri IN_PLACE_BIOLOGICAL_COMPONENT = new HqdmIri(HQDM, "InPlaceBiologicalComponent");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.InstalledObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent}.
     */
    public static final HqdmIri INSTALLED_FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "InstalledFunctionalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryPhysicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystemComponent} that is a
     * {@link #TEMPORAL_PART_OF} an {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} from
     * when it is installed as a {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent} to when it is
     * removed.
     */
    public static final HqdmIri INSTALLED_OBJECT = new HqdmIri(HQDM, "InstalledObject");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Individual} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfIntentionallyConstructedObject} that is
     * intentionally constructed.
     */
    public static final HqdmIri INTENTIONALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "IntentionallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfActivity} all of whose members are of the same
     * kind.
     */
    public static final HqdmIri KIND_OF_ACTIVITY = new HqdmIri(HQDM, "KindOfActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfAssociation} where all the members are of the
     * same kind.
     */
    public static final HqdmIri KIND_OF_ASSOCIATION = new HqdmIri(HQDM, "KindOfAssociation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalObject} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalObject} where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalObject} is of the same kind.
     */
    public static final HqdmIri KIND_OF_BIOLOGICAL_OBJECT = new HqdmIri(HQDM, "KindOfBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalSystem} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystem} all of whose members have a natural
     * {@link uk.gov.gchq.magmacore.hqdm.model.Role} that they play.
     */
    public static final HqdmIri KIND_OF_BIOLOGICAL_SYSTEM = new HqdmIri(HQDM, "KindOfBiologicalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystemComponent} where all the member components
     * play the same {@link uk.gov.gchq.magmacore.hqdm.model.Role} in some
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem}.
     */
    public static final HqdmIri KIND_OF_BIOLOGICAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "KindOfBiologicalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfFunctionalObject}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfIntentionallyConstructedObject} where each
     * {@link #MEMBER_OF} a {@link uk.gov.gchq.magmacore.hqdm.model.KindOfFunctionalObject} is of the
     * same kind.
     */
    public static final HqdmIri KIND_OF_FUNCTIONAL_OBJECT = new HqdmIri(HQDM, "KindOfFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfFunctionalSystem} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystem} where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfFunctionalSystem} has members that are of the same
     * kind.
     */
    public static final HqdmIri KIND_OF_FUNCTIONAL_SYSTEM = new HqdmIri(HQDM, "KindOfFunctionalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfFunctionalSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystemComponent} where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfFunctionalSystemComponent} is of the same kind.
     */
    public static final HqdmIri KIND_OF_FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "KindOfFunctionalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} where all the members possess
     * attributes in common.
     */
    public static final HqdmIri KIND_OF_INDIVIDUAL = new HqdmIri(HQDM, "KindOfIndividual");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIntentionallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfIndividual} where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfIntentionallyConstructedObject} is of the same
     * kind.
     */
    public static final HqdmIri KIND_OF_INTENTIONALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "KindOfIntentionallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryBiologicalObject} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryPhysicalObject} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalObject} where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryBiologicalObject} is of the same kind.
     */
    public static final HqdmIri KIND_OF_ORDINARY_BIOLOGICAL_OBJECT = new HqdmIri(HQDM,
            "KindOfOrdinaryBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryBiologicalObject}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryPhysicalObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalObject} where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryBiologicalObject} is of the same kind.
     */
    public static final HqdmIri KIND_OF_ORDINARY_FUNCTIONAL_OBJECT = new HqdmIri(HQDM,
            "KindOfOrdinaryFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryPhysicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalObject} where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} has members that are of the same
     * kind.
     */
    public static final HqdmIri KIND_OF_ORDINARY_PHYSICAL_OBJECT = new HqdmIri(HQDM,
            "KindOfOrdinaryPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryPhysicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalObject} where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} has members that are of the same
     * kind.
     */
    public static final HqdmIri KIND_OF_ORGANIZATION = new HqdmIri(HQDM, "KindOfOrganization");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOrganizationComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystemComponent} whose members are all of the same
     * kind.
     */
    public static final HqdmIri KIND_OF_ORGANIZATION_COMPONENT = new HqdmIri(HQDM, "KindOfOrganizationComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfParty} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystem} where all the members are of the same kind.
     */
    public static final HqdmIri KIND_OF_PARTY = new HqdmIri(HQDM, "KindOfParty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPerson} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfParty} whose members are all of the same kind.
     */
    public static final HqdmIri KIND_OF_PERSON = new HqdmIri(HQDM, "KindOfPerson");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfIndividual} where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} has members that are of the same kind.
     */
    public static final HqdmIri KIND_OF_PHYSICAL_OBJECT = new HqdmIri(HQDM, "KindOfPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalProperty} where each {@link #MEMBER_OF}
     * a {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalProperty} is of the same kind.
     */
    public static final HqdmIri KIND_OF_PHYSICAL_PROPERTY = new HqdmIri(HQDM, "KindOfPhysicalProperty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPhysicalQuantity} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalProperty} such that each {@link #MEMBER_OF}
     * the same {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalQuantity} is comparable to the
     * others.
     */
    public static final HqdmIri KIND_OF_PHYSICAL_QUANTITY = new HqdmIri(HQDM, "KindOfPhysicalQuantity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfPosition} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfOrganizationComponent} where all the members are of
     * the same kind.
     */
    public static final HqdmIri KIND_OF_POSITION = new HqdmIri(HQDM, "KindOfPosition");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature} where one or more
     * {@link #ROLES} have fixed players.
     */
    public static final HqdmIri KIND_OF_RELATIONSHIP_WITH_RESTRICTION = new HqdmIri(HQDM,
            "KindOfRelationshipWithRestriction");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship} that is a subset of
     * {@link uk.gov.gchq.magmacore.hqdm.model.DefinedRelationship} type where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Classification}s involved in each
     * {@link uk.gov.gchq.magmacore.hqdm.model.DefinedRelationship} have as {@link #CLASSIFIER}s the
     * {@link #ROLES} specified by the
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature}.
     */
    public static final HqdmIri KIND_OF_RELATIONSHIP_WITH_SIGNATURE = new HqdmIri(HQDM,
            "KindOfRelationshipWithSignature");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfIntentionallyConstructedObject} where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSociallyConstructedObject} has members that are of
     * the same kind.
     */
    public static final HqdmIri KIND_OF_SOCIALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "KindOfSociallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystem} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryPhysicalObject} where each
     * {@link #MEMBER_OF} a {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystem} is of the same kind.
     */
    public static final HqdmIri KIND_OF_SYSTEM = new HqdmIri(HQDM, "KindOfSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalObject} where all the members are of the
     * same kind.
     */
    public static final HqdmIri KIND_OF_SYSTEM_COMPONENT = new HqdmIri(HQDM, "KindOfSystemComponent");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Organization} that is a group of people who share a
     * common understanding of a set of {@link uk.gov.gchq.magmacore.hqdm.model.Sign}s.
     */
    public static final HqdmIri LANGUAGE_COMMUNITY = new HqdmIri(HQDM, "LanguageCommunity");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Asset} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfAmountOfMoney}.
     */
    public static final HqdmIri MONEY_ASSET = new HqdmIri(HQDM, "MoneyAsset");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} that proposes an exchange
     * of some {@link uk.gov.gchq.magmacore.hqdm.model.Thing} for some consideration.
     */
    public static final HqdmIri OFFER = new HqdmIri(HQDM, "Offer");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement} that {@link #CONSISTS_OF} exactly
     * one {@link uk.gov.gchq.magmacore.hqdm.model.Offer} of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnershipOfMoney} for exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} that is accepted.
     */
    public static final HqdmIri OFFER_AND_ACCEPTANCE_FOR_GOODS = new HqdmIri(HQDM, "OfferAndAcceptanceForGoods");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Offer} of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.ExchangeOfGoodsAndMoney}.
     */
    public static final HqdmIri OFFER_FOR_GOODS = new HqdmIri(HQDM, "OfferForGoods");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfOffer} that is for a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual}, at a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Price}, by a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party}, for a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PeriodOfTime}.
     */
    public static final HqdmIri OFFERING = new HqdmIri(HQDM, "Offering");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryBiologicalObject}, a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject}, and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject} that does not survive the replacement
     * of all of its parts.
     */
    public static final HqdmIri ORDINARY_BIOLOGICAL_OBJECT = new HqdmIri(HQDM, "OrdinaryBiologicalObject");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject}.
     */
    public static final HqdmIri ORDINARY_FUNCTIONAL_OBJECT = new HqdmIri(HQDM, "OrdinaryFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} that does not survive changing all its
     * parts at once.
     */
    public static final HqdmIri ORDINARY_PHYSICAL_OBJECT = new HqdmIri(HQDM, "OrdinaryPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganization}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} that is an organized body of
     * people.
     */
    public static final HqdmIri ORGANIZATION = new HqdmIri(HQDM, "Organization");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganizationComponent},
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent}, and
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} that is a
     * {@link #COMPONENT_OF} an {@link uk.gov.gchq.magmacore.hqdm.model.Organization} that can be
     * completely replaced without losing its identity.
     */
    public static final HqdmIri ORGANIZATION_COMPONENT = new HqdmIri(HQDM, "OrganizationComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} that is a {@link #PARTICIPANT_IN} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Ownership}.
     */
    public static final HqdmIri OWNER = new HqdmIri(HQDM, "Owner");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Association} that {@link #CONSISTS_OF} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Owner} and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Asset} where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Owner} owns the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Asset}.
     */
    public static final HqdmIri OWNERSHIP = new HqdmIri(HQDM, "Ownership");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.State} that is a {@link #PARTICIPANT_IN} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity} or
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association}.
     */
    public static final HqdmIri PARTICIPANT = new HqdmIri(HQDM, "Participant");

    /**
     * A SELECT where a {@link uk.gov.gchq.magmacore.hqdm.model.Participant} may be a
     * {@link #PARTICIPANT_IN} an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} or an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association}.
     */
    public static final HqdmIri PARTICIPANT_IN_ACTIVITY_OR_ASSOCIATION = new HqdmIri(HQDM,
            "ParticipantInActivityOrAssociation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.System} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person} or an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Organization}.
     */
    public static final HqdmIri PARTY = new HqdmIri(HQDM, "Party");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSign} where all the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign}s are of the same
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern}.
     */
    public static final HqdmIri PATTERN = new HqdmIri(HQDM, "Pattern");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.State} that is a {@link #TEMPORAL_PART_OF} some
     * {@link uk.gov.gchq.magmacore.hqdm.model.PossibleWorld}.
     */
    public static final HqdmIri PERIOD_OF_TIME = new HqdmIri(HQDM, "PeriodOfTime");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem} that is also, a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPerson}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party} that is a human being.
     */
    public static final HqdmIri PERSON = new HqdmIri(HQDM, "Person");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPosition}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPerson}, and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.InstalledObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person} while they are in a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Position} and also the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Position} while it is filled by the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person}.
     */
    public static final HqdmIri PERSON_IN_POSITION = new HqdmIri(HQDM, "PersonInPosition");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Individual} that consists of a distribution of matter
     * and/or energy.
     */
    public static final HqdmIri PHYSICAL_OBJECT = new HqdmIri(HQDM, "PhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} that is some characteristic that is the
     * same for each {@link uk.gov.gchq.magmacore.hqdm.model.State} that possesses it (is a
     * {@link #MEMBER_OF} it).
     */
    public static final HqdmIri PHYSICAL_PROPERTY = new HqdmIri(HQDM, "PhysicalProperty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState} where each member of the set is a member
     * of a {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalProperty} within the range.
     */
    public static final HqdmIri PHYSICAL_PROPERTY_RANGE = new HqdmIri(HQDM, "PhysicalPropertyRange");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity} is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalProperty} that is a measurable quantity of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalProperty}.
     */
    public static final HqdmIri PHYSICAL_QUANTITY = new HqdmIri(HQDM, "PhysicalQuantity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalPropertyRange} that ranges over
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity} values.
     */
    public static final HqdmIri PHYSICAL_QUANTITY_RANGE = new HqdmIri(HQDM, "PhysicalQuantityRange");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.PossibleWorld} that some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party} would like to bring about.
     */
    public static final HqdmIri PLAN = new HqdmIri(HQDM, "Plan");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Event} that is all of space at an instant from some
     * viewpoint.
     */
    public static final HqdmIri POINT_IN_TIME = new HqdmIri(HQDM, "PointInTime");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.OrganizationComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPosition} that may be held by a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person}.
     */
    public static final HqdmIri POSITION = new HqdmIri(HQDM, "Position");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Individual} that is a complete spatio-temporal history
     * of some possible world.
     */
    public static final HqdmIri POSSIBLE_WORLD = new HqdmIri(HQDM, "PossibleWorld");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfAmountOfMoney} that is the
     * {@link #CONSIDERATION_BY_CLASS} in an {@link uk.gov.gchq.magmacore.hqdm.model.Offering}.
     */
    public static final HqdmIri PRICE = new HqdmIri(HQDM, "Price");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSalesProductInstance} that is a set of
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProductInstance} sold under a brand name.
     */
    public static final HqdmIri PRODUCT_BRAND = new HqdmIri(HQDM, "ProductBrand");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Offering} that is for a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProduct}.
     */
    public static final HqdmIri PRODUCT_OFFERING = new HqdmIri(HQDM, "ProductOffering");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} where two or more parties
     * determine a course of action.
     */
    public static final HqdmIri REACHING_AGREEMENT = new HqdmIri(HQDM, "ReachingAgreement");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfLanguageCommunity} that recognizes what a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} is intended to represent.
     */
    public static final HqdmIri RECOGNIZING_LANGUAGE_COMMUNITY = new HqdmIri(HQDM, "RecognizingLanguageCommunity");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.AbstractObject} that is what one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} has to do with one or more others.
     */
    public static final HqdmIri RELATIONSHIP = new HqdmIri(HQDM, "Relationship");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfRepresentation} where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} in all the members are members of the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Pattern} specified.
     */
    public static final HqdmIri REPRESENTATION_BY_PATTERN = new HqdmIri(HQDM, "RepresentationByPattern");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.Association} of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity} that recognizes the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} as representing some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing}.
     */
    public static final HqdmIri REPRESENTATION_BY_SIGN = new HqdmIri(HQDM, "RepresentationBySign");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} that is {@link #PART_OF_PLAN} at
     * least one {@link uk.gov.gchq.magmacore.hqdm.model.Plan} and is {@link #DEFINED_BY} exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification}.
     */
    public static final HqdmIri REQUIREMENT = new HqdmIri(HQDM, "Requirement");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent} that is the
     * {@link #INTERSECTION_OF} one or more {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState}.
     */
    public static final HqdmIri REQUIREMENT_SPECIFICATION = new HqdmIri(HQDM, "RequirementSpecification");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfParticipant} where each member participates in
     * the same way in an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} or
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association}.
     */
    public static final HqdmIri ROLE = new HqdmIri(HQDM, "Role");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.AgreementProcess} that consists of an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OfferAndAcceptanceForGoods} and an
     * {@link uk.gov.gchq.magmacore.hqdm.model.ExchangeOfGoodsAndMoney}.
     */
    public static final HqdmIri SALE_OF_GOODS = new HqdmIri(HQDM, "SaleOfGoods");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSalesProductInstance} that is a set of
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProductInstance} sold under the same product name.
     */
    public static final HqdmIri SALES_PRODUCT = new HqdmIri(HQDM, "SalesProduct");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject} that is produced in order to
     * be sold.
     */
    public static final HqdmIri SALES_PRODUCT_INSTANCE = new HqdmIri(HQDM, "SalesProductInstance");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSalesProductInstance} that is the customer
     * facing specification of a version of a {@link uk.gov.gchq.magmacore.hqdm.model.SalesProduct}.
     */
    public static final HqdmIri SALES_PRODUCT_VERSION = new HqdmIri(HQDM, "SalesProductVersion");

    /**
     * A scale is a function from {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalQuantity} to the
     * real numbers.
     */
    public static final HqdmIri SCALE = new HqdmIri(HQDM, "Scale");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSign}, that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject}, and a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} that represents some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} for some community in one or more
     * {@link #REPRESENTATION_BY_SIGN}.
     */
    public static final HqdmIri SIGN = new HqdmIri(HQDM, "Sign");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} that is also an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity}.
     */
    public static final HqdmIri SOCIALLY_CONSTRUCTED_ACTIVITY = new HqdmIri(HQDM, "SociallyConstructedActivity");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject} that is necessarily
     * constructed by agreement or at least acquiescence of many people.
     */
    public static final HqdmIri SOCIALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM, "SociallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Thing} that exists in time and space.
     */
    public static final HqdmIri SPATIO_TEMPORAL_EXTENT = new HqdmIri(HQDM, "SpatioTemporalExtent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Relationship} where each {@link #MEMBER__OF} the
     * {@link #SUBCLASS} is a {@link #MEMBER__OF} the {@link #SUPERCLASS}.
     */
    public static final HqdmIri SPECIALIZATION = new HqdmIri(HQDM, "Specialization");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} that is an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual} or a {@link #TEMPORAL_PART_OF} some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual}.
     */
    public static final HqdmIri STATE = new HqdmIri(HQDM, "State");

    /**
     * A state that is an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} or a
     * {@link #TEMPORAL_PART_OF} an {@link uk.gov.gchq.magmacore.hqdm.model.Activity}.
     */
    public static final HqdmIri STATE_OF_ACTIVITY = new HqdmIri(HQDM, "StateOfActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} that is a
     * {@link #TEMPORAL_PART_OF} an {@link uk.gov.gchq.magmacore.hqdm.model.AmountOfMoney}.
     */
    public static final HqdmIri STATE_OF_AMOUNT_OF_MONEY = new HqdmIri(HQDM, "StateOfAmountOfMoney");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.State} that is an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} or a {@link #TEMPORAL_PART_OF} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association}.
     */
    public static final HqdmIri STATE_OF_ASSOCIATION = new HqdmIri(HQDM, "StateOfAssociation");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalObject}.
     */
    public static final HqdmIri STATE_OF_BIOLOGICAL_OBJECT = new HqdmIri(HQDM, "StateOfBiologicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryBiologicalObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystem} that is
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem}.
     */
    public static final HqdmIri STATE_OF_BIOLOGICAL_SYSTEM = new HqdmIri(HQDM, "stateOfBiologicalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystemComponent} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystemComponent} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystemComponent} or a {@link #TEMPORAL_PART_OF}
     * a {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystemComponent}.
     */
    public static final HqdmIri STATE_OF_BIOLOGICAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "StateOfBiologicalSystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfIntentionallyConstructedObject} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject}.
     */
    public static final HqdmIri STATE_OF_FUNCTIONAL_OBJECT = new HqdmIri(HQDM, "StateOfFunctionalObject");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystem}.
     */
    public static final HqdmIri STATE_OF_FUNCTIONAL_SYSTEM = new HqdmIri(HQDM, "StateOfFunctionalSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfIntentionallyConstructedObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent}.
     */
    public static final HqdmIri STATE_OF_FUNCTIONAL_SYSTEM_COMPONENT = new HqdmIri(HQDM,
            "StateOfFunctionalSystemComponent");

    /**
     * A state that is an {@link uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject} or a
     * {@link #TEMPORAL_PART_OF} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject}.
     */
    public static final HqdmIri STATE_OF_INTENTIONALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "StateOfIntentionallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganization} that is a
     * {@link #TEMPORAL_PART_OF} a {@link uk.gov.gchq.magmacore.hqdm.model.LanguageCommunity}.
     */
    public static final HqdmIri STATE_OF_LANGUAGE_COMMUNITY = new HqdmIri(HQDM, "StateOfLanguageCommunity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryPhysicalObject} that is an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryBiologicalObject} or a {@link #TEMPORAL_PART_OF}
     * an {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryBiologicalObject}.
     */
    public static final HqdmIri STATE_OF_ORDINARY_BIOLOGICAL_OBJECT = new HqdmIri(HQDM,
            "StateOfOrdinaryBiologicalObject");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryPhysicalObject}.
     */
    public static final HqdmIri STATE_OF_ORDINARY_FUNCTIONAL_OBJECT = new HqdmIri(HQDM,
            "StateOfOrdinaryFunctionalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} that is an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} or a {@link #TEMPORAL_PART_OF}
     * one.
     */
    public static final HqdmIri STATE_OF_ORDINARY_PHYSICAL_OBJECT = new HqdmIri(HQDM,
            "StateOfOrdinaryPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} that is an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Organization} or a {@link #TEMPORAL_PART_OF} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Organization}.
     */
    public static final HqdmIri STATE_OF_ORGANIZATION = new HqdmIri(HQDM, "StateOfOrganization");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystemComponent} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} that is a
     * {@link #TEMPORAL_PART_OF} an {@link uk.gov.gchq.magmacore.hqdm.model.OrganizationComponent}.
     */
    public static final HqdmIri STATE_OF_ORGANIZATION_COMPONENT = new HqdmIri(HQDM, "StateOfOrganizationComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystem} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party}.
     */
    public static final HqdmIri STATE_OF_PARTY = new HqdmIri(HQDM, "StateOfParty");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystem} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Person}.
     */
    public static final HqdmIri STATE_OF_PERSON = new HqdmIri(HQDM, "StateOfPerson");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.State} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject}.
     */
    public static final HqdmIri STATE_OF_PHYSICAL_OBJECT = new HqdmIri(HQDM, "StateOfPhysicalObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganizationComponent} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Position} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Position}.
     */
    public static final HqdmIri STATE_OF_POSITION = new HqdmIri(HQDM, "StateOfPosition");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProductInstance} or a {@link #TEMPORAL_PART_OF} one.
     */
    public static final HqdmIri STATE_OF_SALES_PRODUCT_INSTANCE = new HqdmIri(HQDM, "StateOfSalesProductInstance");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign}.
     */
    public static final HqdmIri STATE_OF_SIGN = new HqdmIri(HQDM, "StateOfSign");

    /**
     * Any {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfActivity}.
     */
    public static final HqdmIri STATE_OF_SOCIALLY_CONSTRUCTED_ACTIVITY = new HqdmIri(HQDM,
            "StateOfSociallyConstructedActivity");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfIntentionallyConstructedObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} or a {@link #TEMPORAL_PART_OF}
     * a {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject}.
     */
    public static final HqdmIri STATE_OF_SOCIALLY_CONSTRUCTED_OBJECT = new HqdmIri(HQDM,
            "StateOfSociallyConstructedObject");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryPhysicalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.System} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.System}.
     */
    public static final HqdmIri STATE_OF_SYSTEM = new HqdmIri(HQDM, "StateOfSystem");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfPhysicalObject} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent} or a {@link #TEMPORAL_PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent}.
     */
    public static final HqdmIri STATE_OF_SYSTEM_COMPONENT = new HqdmIri(HQDM, "StateOfSystemComponent");

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject} that is an organized or
     * connected group of {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject}.
     */
    public static final HqdmIri SYSTEM = new HqdmIri(HQDM, "System");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject} that is a {@link #COMPONENT_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.System} and that can be completely replaced without
     * losing identity.
     */
    public static final HqdmIri SYSTEM_COMPONENT = new HqdmIri(HQDM, "SystemComponent");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Composition} where the part is the entire
     * {@link #WHOLE} spatially, but part of the {@link #WHOLE} temporally.
     */
    public static final HqdmIri TEMPORAL_COMPOSITION = new HqdmIri(HQDM, "TemporalComposition");

    /**
     * Anything that exists, real or imagined.
     */
    public static final HqdmIri THING = new HqdmIri(HQDM, "Thing");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} and
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} receiving
     * {@link uk.gov.gchq.magmacore.hqdm.model.Ownership} in a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership}.
     */
    public static final HqdmIri TRANSFEREE = new HqdmIri(HQDM, "Transferee");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} that ends one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Ownership} and begins another for
     * {@link uk.gov.gchq.magmacore.hqdm.model.Asset}s that are a {@link #TEMPORAL_PART_OF} the same
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalObject}.
     */
    public static final HqdmIri TRANSFER_OF_OWNERSHIP = new HqdmIri(HQDM, "TransferOfOwnership");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Asset} is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.MoneyAsset}.
     */
    public static final HqdmIri TRANSFER_OF_OWNERSHIP_OF_MONEY = new HqdmIri(HQDM, "TransferOfOwnershipOfMoney");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.StateOfParty} that is also a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} that is a {@link #TEMPORAL_PART_OF} an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Owner} that is a {@link #PARTICIPANT_IN} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership}.
     */
    public static final HqdmIri TRANSFEROR = new HqdmIri(HQDM, "Transferor");

    /**
     * A plus one {@link uk.gov.gchq.magmacore.hqdm.model.Function_} for a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale}.
     */
    public static final HqdmIri UNIT_OF_MEASURE = new HqdmIri(HQDM, "UnitOfMeasure");

    // =======================================================================
    // Associations
    // =======================================================================

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} may be
     * aggregated into one or more others.
     *
     * <p>
     * Note: This has the same meaning as, but different representation to, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} entity type.
     * </p>
     */
    public static final HqdmIri AGGREGATED_INTO = new HqdmIri(HQDM, "aggregatedInto");

    /**
     * A {@link #PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Event} that is its beginning.
     */
    public static final HqdmIri BEGINNING = new HqdmIri(HQDM, "beginning");

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.model.Activity} is the cause of
     * one or more {@link uk.gov.gchq.magmacore.hqdm.model.Event}.
     */
    public static final HqdmIri CAUSES = new HqdmIri(HQDM, "causes");

    /**
     * A {@link #CAUSES} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} {@link #CAUSES} exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.BeginningOfOwnership}.
     */
    public static final HqdmIri CAUSES_BEGINNING = new HqdmIri(HQDM, "causesBeginning");

    /**
     * A relationship type where a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfActivity} causes a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfEvent}.
     */
    public static final HqdmIri CAUSES_BY_CLASS = new HqdmIri(HQDM, "causesByClass");

    /**
     * A {@link #CAUSES} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} {@link #CAUSES} exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.EndingOfOwnership}.
     */
    public static final HqdmIri CAUSES_ENDING = new HqdmIri(HQDM, "causesEnding");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Offering} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfIndividual} some {@link #MEMBER_OF} which is
     * offered.
     */
    public static final HqdmIri CLASS_OF_OFFERED = new HqdmIri(HQDM, "classOfOffered");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Classification} has exactly
     * one classifier.
     */
    public static final HqdmIri CLASSIFIER = new HqdmIri(HQDM, "classifier");

    /**
     * A {@link #PART_OF} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.SystemComponent} is {@link #PART_OF} exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.System}.
     */
    public static final HqdmIri COMPONENT_OF = new HqdmIri(HQDM, "componentOf");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Offering} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Price} at which the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offering} is made.
     */
    public static final HqdmIri CONSIDERATION_BY_CLASS = new HqdmIri(HQDM, "considerationByClass");

    /**
     * A {@link #CONSISTS_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may {@link #CONSISTS_OF} one or more other
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity}.
     */
    public static final HqdmIri CONSISTS_OF = new HqdmIri(HQDM, "consistsOf");

    /**
     * A {@link #CONSISTS_OF} relationship subtype where an entity has another {@link CONSISTS_OF}
     * relationship.
     */
    public static final HqdmIri CONSISTS_OF_ = new HqdmIri(HQDM, "consistsOf");

    /**
     * A relationship type where a {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfActivity} or
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfAssociation} has a {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Role} as a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} or part.
     */
    public static final HqdmIri CONSISTS_OF_BY_CLASS = new HqdmIri(HQDM, "consistsOfByClass");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity}
     * is a {@link #PARTICIPANT_IN} each {@link #MEMBER_OF} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern}.
     */
    public static final HqdmIri CONSISTS_OF_IN_MEMBERS = new HqdmIri(HQDM, "consistsOfInMembers");

    /**
     * A {@link #CONSISTS_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity} or
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} {@link #CONSISTS_OF} at least one (for
     * Activity) or two (for Association) of {@link uk.gov.gchq.magmacore.hqdm.model.Participant}.
     */
    public static final HqdmIri CONSISTS_OF_PARTICIPANT = new HqdmIri(HQDM, "consistsOfParticipant");

    /**
     * A {@link #MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Requirement} is {@link #DEFINED_BY} exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification}.
     */
    public static final HqdmIri DEFINED_BY = new HqdmIri(HQDM, "definedBy");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may determine one
     * or more {@link uk.gov.gchq.magmacore.hqdm.model.Thing} to be the case.
     */
    public static final HqdmIri DETERMINES = new HqdmIri(HQDM, "determines");

    /**
     * A relationship type where a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfActivity} determines a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Class}.
     */
    public static final HqdmIri DETERMINES_BY_CLASS = new HqdmIri(HQDM, "determinesByClass");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Scale} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalQuantity} as its {@link #DOMAIN}.
     */
    public static final HqdmIri DOMAIN = new HqdmIri(HQDM, "domain");

    /**
     * A {@link #PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} has exactly one event that is its
     * ending.
     */
    public static final HqdmIri ENDING = new HqdmIri(HQDM, "ending");

    /**
     * A {@link #CONSISTS_OF_BY_CLASS} relationship type where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystem} has a {@link #MEMBER_OF} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfSystemComponent} as a component.
     */
    public static final HqdmIri HAS_COMPONENT_BY_CLASS = new HqdmIri(HQDM, "hasComponentByClass");

    /**
     * A relationship type where each {@link #MEMBER_OF} the class is a {@link #MEMBER_OF} the
     * {@link #SUPERCLASS}.
     */
    public static final HqdmIri HAS_SUPERCLASS = new HqdmIri(HQDM, "hasSuperclass");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject} has one or
     * more intended {@link uk.gov.gchq.magmacore.hqdm.model.Role}(s).
     */
    public static final HqdmIri INTENDED_ROLE = new HqdmIri(HQDM, "intendedRole");

    /**
     * A relationship type where each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfFunctionalObject} is intended to play one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.Role}(s).
     */
    public static final HqdmIri INTENDED_ROLE_BY_CLASS = new HqdmIri(HQDM, "intendedRoleByClass");

    /**
     * A {@code subtype_of} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification} is the {@link #INTERSECTION_OF}
     * one or more {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState}.
     * 
     * <p>
     * Note: The {@link uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification} is a subtype of each
     * of the related {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfState}.
     * </p>
     */
    public static final HqdmIri INTERSECTION_OF = new HqdmIri(HQDM, "intersectionOf");

    /**
     * A meta-relationship type where the {@link uk.gov.gchq.magmacore.hqdm.model.Classification} of
     * some thing in a {@link uk.gov.gchq.magmacore.hqdm.model.Role} is involved in a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Relationship}.
     */
    public static final HqdmIri INVOLVES = new HqdmIri(HQDM, "involves");

    /**
     * A {@code supertype_of} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantityRange} must have as {@link #LOWER_BOUND}
     * exactly one {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity}.
     */
    public static final HqdmIri LOWER_BOUND = new HqdmIri(HQDM, "lowerBound");

    /**
     * A {@code subclass_of} relationship type where when a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProduct} {@link #MEETS_SPECIFICATION} of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification}, each {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProduct} is a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification}.
     */
    public static final HqdmIri MEETS_SPECIFICATION = new HqdmIri(HQDM, "meetsSpecification");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Classification} has exactly
     * one {@link #MEMBER}.
     */
    public static final HqdmIri MEMBER = new HqdmIri(HQDM, "member");

    /**
     * A {@link #MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} is a {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent}.
     */
    public static final HqdmIri MEMBER_OF = new HqdmIri(HQDM, "memberOf");

    /**
     * A {@link #MEMBER_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.AmountOfMoney} may be a {@link #MEMBER_OF} exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Currency}.
     */
    public static final HqdmIri MEMBER_OF_CURRENCY = new HqdmIri(HQDM, "memberOfCurrency");

    /**
     * A {@link #MEMBER_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual} may be a {@link #MEMBER_OF} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfIndividual}.
     */
    public static final HqdmIri MEMBER_OF_KIND = new HqdmIri(HQDM, "memberOfKind");

    /**
     * A {@link #MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem} has a natural role that it plays.
     *
     * <p>
     * Example: My circulatory system has the natural role of circulating blood around the body.
     * </p>
     */
    public static final HqdmIri NATURAL_ROLE = new HqdmIri(HQDM, "naturalRole");

    /**
     * A relationship type where each {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalSystem} naturally participates in the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Role}.
     */
    public static final HqdmIri NATURAL_ROLE_BY_CLASS = new HqdmIri(HQDM, "naturalRoleByClass");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Offering} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Party} who makes the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offering}.
     */
    public static final HqdmIri OFFEROR = new HqdmIri(HQDM, "offeror");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} as the {@link #PART}.
     */
    public static final HqdmIri PART = new HqdmIri(HQDM, "part");

    /**
     * A {@link #PART_OF} relationship type where one {@link uk.gov.gchq.magmacore.hqdm.model.Activity}
     * may be a {@link #PART_OF} one or more others.
     */
    public static final HqdmIri PART_OF = new HqdmIri(HQDM, "partOf");

    /**
     * A {@link #PART_OF_BY_CLASS} where a {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Role} is a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} in a {@link #MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfActivity}.
     */
    public static final HqdmIri PART_OF_BY_CLASS = new HqdmIri(HQDM, "partOfByClass");

    /**
     * A {@link #PART_OF} relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Requirement}
     * must be {@link #PART_OF} one or more {@link uk.gov.gchq.magmacore.hqdm.model.Plan}s.
     */
    public static final HqdmIri PART_OF_PLAN = new HqdmIri(HQDM, "partOfPlan");

    /**
     * A {@link #PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} may be {@link #PART_OF} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.PossibleWorld}.
     * 
     * <p>
     * Note: The relationship is optional because a
     * {@link uk.gov.gchq.magmacore.hqdm.model.PossibleWorld} is not {@link #PART_OF} any other
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent}.
     * </p>
     */
    public static final HqdmIri PART_OF_POSSIBLE_WORLD = new HqdmIri(HQDM, "partOfPossibleWorld");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Participant} is a
     * {@link #PARTICIPANT_IN} an {@link uk.gov.gchq.magmacore.hqdm.model.Association} or
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity}.
     */
    public static final HqdmIri PARTICIPANT_IN = new HqdmIri(HQDM, "participantIn");

    /**
     * A relationship that is exactly one {@link uk.gov.gchq.magmacore.hqdm.model.PeriodOfTime} for
     * which the {@link uk.gov.gchq.magmacore.hqdm.model.Offering} is valid.
     */
    public static final HqdmIri PERIOD_OFFERED = new HqdmIri(HQDM, "periodOffered");

    /**
     * A {@code supertype_of} relationship type where the members of each
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalProperty} in the
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalPropertyRange} are members of the
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalPropertyRange}.
     */
    public static final HqdmIri RANGES_OVER = new HqdmIri(HQDM, "rangesOver");

    /**
     * A relationship type where a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfActivity} references a {@link #MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Class}.
     */
    public static final HqdmIri REFERENCES_BY_CLASS = new HqdmIri(HQDM, "referencesByClass");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may reference one
     * or more {@link uk.gov.gchq.magmacore.hqdm.model.Thing}.
     */
    public static final HqdmIri REFERENCES = new HqdmIri(HQDM, "references");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign}
     * {@link #REPRESENTS} one or more {@link uk.gov.gchq.magmacore.hqdm.model.Thing}.
     */
    public static final HqdmIri REPRESENTS = new HqdmIri(HQDM, "represents");

    /**
     * A relationship type where the {@link uk.gov.gchq.magmacore.hqdm.model.Thing} is represented by
     * each {@link #MEMBER_OF} the {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern}.
     */
    public static final HqdmIri REPRESENTED = new HqdmIri(HQDM, "represented");

    /**
     * A relationship type where the {@link uk.gov.gchq.magmacore.hqdm.model.Classification} is of a
     * required role player for the members of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithRestriction}.
     */
    public static final HqdmIri REQUIRED_ROLE_PLAYER = new HqdmIri(HQDM, "requiredRolePlayer");

    /**
     * The roles that must be filled by members of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature}.
     */
    public static final HqdmIri ROLES = new HqdmIri(HQDM, "roles");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Specialization} where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProductVersion} may be sold as a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProduct}.
     */
    public static final HqdmIri SOLD_AS = new HqdmIri(HQDM, "soldAs");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Specialization} where the
     * {@link uk.gov.gchq.magmacore.hqdm.model.SalesProduct} is sold under a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ProductBrand}.
     */
    public static final HqdmIri SOLD_UNDER = new HqdmIri(HQDM, "soldUnder");

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.model.Specialization} has
     * exactly one {@link uk.gov.gchq.magmacore.hqdm.model.Class} as {@link #SUBCLASS}.
     */
    public static final HqdmIri SUBCLASS = new HqdmIri(HQDM, "subclass");

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.SalesProductVersion} may have
     * exactly one {@link #SUCCESSOR}.
     */
    public static final HqdmIri SUCCESSOR = new HqdmIri(HQDM, "successor");

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.model.Specialization} has
     * exactly one {@link uk.gov.gchq.magmacore.hqdm.model.Class} as {@link #SUPERCLASS}.
     */
    public static final HqdmIri SUPERCLASS = new HqdmIri(HQDM, "superclass");

    /**
     * A {@link #TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a {@link #TEMPORAL_PART_OF} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual}.
     * 
     * <p>
     * Note: The relationship is optional because an {@link uk.gov.gchq.magmacore.hqdm.model.Individual}
     * is not necessarily a {@link #TEMPORAL_PART_OF} another
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual}, yet is a {@link #MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as
     * {@link uk.gov.gchq.magmacore.hqdm.model.Individual}. This applies to all subtypes of
     * {@link #TEMPORAL_PART_OF} that are between a {@code state_of_X} and {@code X}.
     * </p>
     */
    public static final HqdmIri TEMPORAL_PART_OF = new HqdmIri(HQDM, "temporalPartOf");

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Scale} may have at most one
     * {@link uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure}.
     *
     * <p>
     * Note 1: A {@link uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure} may apply to more than one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale}.
     * </p>
     *
     * <p>
     * Note 2: A {@link uk.gov.gchq.magmacore.hqdm.model.Scale} may not have a
     * {@link uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure}. To have a
     * {@link uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure} the points on the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale} must be evenly placed so that adding one means the
     * same {@link uk.gov.gchq.magmacore.hqdm.model.Thing}. This is not true for some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale}s such as Rockwell Hardness where the points on the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale} are an arbitrary distance apart. A
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale} will also not have a
     * {@link uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure} when it is a dimensionless
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale}.
     * </p>
     */
    public static final HqdmIri UNIT = new HqdmIri(HQDM, "unit");

    /**
     * A {@code supertype_of} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantityRange} must have as {@link #UPPER_BOUND}
     * exactly one {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity}.
     */
    public static final HqdmIri UPPER_BOUND = new HqdmIri(HQDM, "upperBound");

    /**
     * A relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.IdentificationOfPhysicalQuantity} uses exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.Scale}.
     */
    public static final HqdmIri USES = new HqdmIri(HQDM, "uses");

    /**
     * A relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.IdentificationOfPhysicalQuantity} consists of exactly one
     * REAL as its value.
     *
     * <p>
     * Note 1: The members of the data type REAL provide an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Identification} of a real number.
     * </p>
     *
     * <p>
     * Note 2: The relationship name has been renamed from consists of by
     * {@link uk.gov.gchq.magmacore.hqdm.model.Class} to value.
     * </p>
     */
    public static final HqdmIri VALUE_ = new HqdmIri(HQDM, "value");

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} has exactly one
     * {@link uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent} as the whole.
     */
    public static final HqdmIri WHOLE = new HqdmIri(HQDM, "whole");
}
