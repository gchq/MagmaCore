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

package uk.gov.gchq.magmacore.hqdm.rdfbuilders;

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.AGGREGATED_INTO;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.BEGINNING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CAUSES;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_PARTICIPANT;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.DETERMINES;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ENDING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.REFERENCES;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.AcceptanceOfOffer;
import uk.gov.gchq.magmacore.hqdm.model.AgreeContract;
import uk.gov.gchq.magmacore.hqdm.model.AgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfAgreeContract;
import uk.gov.gchq.magmacore.hqdm.model.ContractProcess;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.Offer;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of AgreeContract.
 */
public class AgreeContractBuilder {

    private final AgreeContract agreeContract;

    /**
     * Constructs a Builder for a new AgreeContract.
     *
     * @param iri IRI of the AgreeContract.
     */
    public AgreeContractBuilder(final IRI iri) {
        agreeContract = RdfSpatioTemporalExtentServices.createAgreeContract(iri.getIri());
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may be aggregated into one or more
     * others.
     * <p>
     * Note: This has the same meaning as, but different representation to, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} entity type.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AgreeContractBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        agreeContract.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AgreeContractBuilder beginning(final Event event) {
        agreeContract.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.model.Activity} is the cause of
     * one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AgreeContractBuilder causes_M(final Event event) {
        agreeContract.addValue(CAUSES, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may consist of one or more others.
     *
     * <p>
     * Note: This is the inverse of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART__OF}.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AgreeContractBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        agreeContract.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where the
     * {@link AgreeContract} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} exactly one
     * {@link AcceptanceOfOffer}.
     *
     * @param acceptanceOfOffer The AcceptanceOfOffer.
     * @return Builder
     */
    public final AgreeContractBuilder consists_Of(final AcceptanceOfOffer acceptanceOfOffer) {
        agreeContract.addValue(CONSISTS_OF, new IRI(acceptanceOfOffer.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where an
     * {@link AgreeContract} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} exactly one
     * {@link Offer}.
     *
     * @param offer The Offer.
     * @return Builder
     */
    public final AgreeContractBuilder consists_Of_(final Offer offer) {
        agreeContract.addValue(CONSISTS_OF_, new IRI(offer.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} one or more {@link Participant}s.
     *
     * @param participant The Participant.
     * @return This builder.
     */
    public final AgreeContractBuilder consists_Of_Participant(final Participant participant) {
        agreeContract.addValue(CONSISTS_OF_PARTICIPANT, new IRI(participant.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may determine one
     * or more {@link Thing} to be the case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final AgreeContractBuilder determines(final Thing thing) {
        agreeContract.addValue(DETERMINES, new IRI(thing.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AgreeContractBuilder ending(final Event event) {
        agreeContract.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final AgreeContractBuilder member__Of(final Class clazz) {
        agreeContract.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link AgreeContract} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one
     * or more {@link ClassOfAgreeContract}.
     *
     * @param classOfAgreeContract The ClassOfAgreeContract.
     * @return Builder
     */
    public final AgreeContractBuilder member_Of(final ClassOfAgreeContract classOfAgreeContract) {
        agreeContract.addValue(MEMBER_OF, new IRI(classOfAgreeContract.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.Activity} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more {@link KindOfActivity}.
     *
     * @param kindOfActivity The KindOfActivity.
     * @return This builder.
     */
    public final AgreeContractBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        agreeContract.addValue(MEMBER_OF_KIND, new IRI(kindOfActivity.getId()));
        return this;
    }

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#AGGREGATED_INTO} relationship type where a
     * {@link SpatioTemporalExtent} may be part of another and the whole has emergent properties and is
     * more than just the sum of its parts.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AgreeContractBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        agreeContract.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where an
     * {@link AgreeContract} is {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} exactly one
     * {@link ContractProcess}.
     *
     * @param contractProcess The ContractProcess.
     * @return Builder
     */
    public final AgreeContractBuilder part_Of_M(final ContractProcess contractProcess) {
        agreeContract.addValue(PART_OF, new IRI(contractProcess.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} one or more
     * {@link AgreementExecution}.
     *
     * @param agreementExecution The AgreementExecution.
     * @return This builder.
     */
    public final AgreeContractBuilder part_Of_(final AgreementExecution agreementExecution) {
        agreeContract.addValue(PART_OF_, new IRI(agreementExecution.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF}
     * one or more {@link PossibleWorld}.
     *
     * <p>
     * Note: The relationship is optional because a {@link PossibleWorld} is not
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} any other
     * {@link SpatioTemporalExtent}.
     * </p>
     *
     * @param possibleWorld The PossibleWorld.
     * @return This builder.
     */
    public final AgreeContractBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        agreeContract.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may reference one
     * or more {@link Thing}.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final AgreeContractBuilder references(final Thing thing) {
        agreeContract.addValue(REFERENCES, new IRI(thing.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be a temporal part of one or more other
     * {@link SpatioTemporalExtent}.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AgreeContractBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        agreeContract.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link Individual}.
     *
     * <p>
     * Note: The relationship is optional because an {@link Individual} is not necessarily a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} another {@link Individual},
     * yet is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as {@link Individual}. This applies to all
     * subtypes of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} that are between
     * a {@code state_of_X} and {@code X}.
     * </p>
     *
     * @param individual The Individual.
     * @return This builder.
     */
    public final AgreeContractBuilder temporal_Part_Of(final Individual individual) {
        agreeContract.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of AgreeContract created from the properties set on this builder.
     *
     * @return The built AgreeContract.
     * @throws HqdmException If the AgreeContract is missing any mandatory properties.
     */
    public AgreeContract build() throws HqdmException {
        if (agreeContract.hasValue(AGGREGATED_INTO)
                && agreeContract.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (agreeContract.hasValue(BEGINNING)
                && agreeContract.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!agreeContract.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (agreeContract.hasValue(DETERMINES)
                && agreeContract.value(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (agreeContract.hasValue(ENDING)
                && agreeContract.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (agreeContract.hasValue(MEMBER__OF)
                && agreeContract.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (agreeContract.hasValue(MEMBER_OF)
                && agreeContract.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!agreeContract.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (agreeContract.hasValue(PART__OF)
                && agreeContract.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!agreeContract.hasValue(PART_OF)) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (agreeContract.hasValue(PART_OF_)
                && agreeContract.value(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!agreeContract.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (agreeContract.hasValue(REFERENCES)
                && agreeContract.value(REFERENCES).isEmpty()) {
            throw new HqdmException("Property Not Set: references");
        }
        if (agreeContract.hasValue(TEMPORAL__PART_OF)
                && agreeContract.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (agreeContract.hasValue(TEMPORAL_PART_OF)
                && agreeContract.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return agreeContract;
    }
}
