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
import uk.gov.gchq.magmacore.hqdm.model.Activity;
import uk.gov.gchq.magmacore.hqdm.model.AgreeContract;
import uk.gov.gchq.magmacore.hqdm.model.AgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity;
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
 * Builder for constructing instances of AcceptanceOfOffer.
 */
public class AcceptanceOfOfferBuilder {

    private final AcceptanceOfOffer acceptanceOfOffer;

    /**
     * Constructs a Builder for a new AcceptanceOfOffer.
     *
     * @param iri IRI of the AcceptanceOfOffer.
     */
    public AcceptanceOfOfferBuilder(final IRI iri) {
        acceptanceOfOffer = RdfSpatioTemporalExtentServices.createAcceptanceOfOffer(iri.getIri());
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
    public final AcceptanceOfOfferBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        acceptanceOfOffer.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder beginning(final Event event) {
        acceptanceOfOffer.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link Activity} is the cause of one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder causes_M(final Event event) {
        acceptanceOfOffer.addValue(CAUSES, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may consist of one or more others.
     *
     * <p>
     * Note: This is the inverse of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART__OF}.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        acceptanceOfOffer.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF} relationship type where an
     * {@link Activity} may {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF} one or more
     * other {@link Activity}.
     *
     * @param activity The Activity.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder consists_Of(final Activity activity) {
        acceptanceOfOffer.addValue(CONSISTS_OF, new IRI(activity.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF} relationship type where an
     * {@link Activity} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF} one or more
     * {@link Participant}s.
     *
     * @param participant The Participant.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder consists_Of_Participant(final Participant participant) {
        acceptanceOfOffer.addValue(CONSISTS_OF_PARTICIPANT, new IRI(participant.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link Activity} may determine one or more {@link Thing} to be the
     * case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder determines(final Thing thing) {
        acceptanceOfOffer.addValue(DETERMINES, new IRI(thing.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder ending(final Event event) {
        acceptanceOfOffer.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * <p>
     * Note: This relationship is the same as the entity type
     * {@link uk.gov.gchq.magmacore.hqdm.model.Classification}.
     * </p>
     * clazz.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder member__Of(final Class clazz) {
        acceptanceOfOffer.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfSociallyConstructedActivity}.
     *
     * @param classOfSociallyConstructedActivity The ClassOfSociallyConstructedActivity.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder member_Of(
            final ClassOfSociallyConstructedActivity classOfSociallyConstructedActivity) {
        acceptanceOfOffer.addValue(MEMBER_OF, new IRI(classOfSociallyConstructedActivity.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF_KIND} relationship type where each
     * {@link Activity} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link KindOfActivity}.
     *
     * @param kindOfActivity The KindOfActivity.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        acceptanceOfOffer.addValue(MEMBER_OF_KIND, new IRI(kindOfActivity.getId()));
        return this;
    }

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#AGGREGATED_INTO} relationship type where a
     * {@link SpatioTemporalExtent} may be part of another and the whole has emergent properties and is
     * more than just the sum of its parts.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        acceptanceOfOffer.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where an
     * {@link AcceptanceOfOffer} is {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} just one
     * {@link AgreeContract}.
     *
     * @param agreeContract The AgreeContract.
     * @return Builder
     */
    public final AcceptanceOfOfferBuilder part_Of_M(final AgreeContract agreeContract) {
        acceptanceOfOffer.addValue(PART_OF, new IRI(agreeContract.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} one or more
     * {@link AgreementExecution}.
     *
     * @param agreementExecution The AgreementExecution.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder part_Of_(final AgreementExecution agreementExecution) {
        acceptanceOfOffer.addValue(PART_OF_, new IRI(agreementExecution.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF}
     * one or more {@link PossibleWorld}.
     *
     * <p>
     * Note: The relationship is optional because a {@link PossibleWorld} is not
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} any other
     * {@link SpatioTemporalExtent}.
     * </p>
     *
     * @param possibleWorld The PossibleWorld.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        acceptanceOfOffer.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#REFERENCES} relationship type where an
     * {@link AcceptanceOfOffer} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#REFERENCES} exactly
     * one {@link Offer} that is accepted.
     *
     * @param offer The Offer.
     * @return Builder
     */
    public final AcceptanceOfOfferBuilder references_M(final Offer offer) {
        acceptanceOfOffer.addValue(REFERENCES, new IRI(offer.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be a temporal part of one or more other
     * {@link SpatioTemporalExtent}.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        acceptanceOfOffer.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} one or more
     * {@link Individual}.
     *
     * <p>
     * Note: The relationship is optional because an {@link Individual} is not necessarily a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} another {@link Individual},
     * yet is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as {@link Individual}. This applies to all
     * subtypes of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} that are between
     * a {@code state_of_X} and {@code X}.
     * </p>
     *
     * @param individual The Individual.
     * @return This builder.
     */
    public final AcceptanceOfOfferBuilder temporal_Part_Of(final Individual individual) {
        acceptanceOfOffer.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of AcceptanceOfOffer created from the properties set on this builder.
     *
     * @return The built AcceptanceOfOffer.
     * @throws HqdmException If the AcceptanceOfOffer is missing any mandatory properties.
     */
    public AcceptanceOfOffer build() throws HqdmException {
        if (acceptanceOfOffer.hasValue(AGGREGATED_INTO)
                && acceptanceOfOffer.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (acceptanceOfOffer.hasValue(BEGINNING)
                && acceptanceOfOffer.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!acceptanceOfOffer.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (acceptanceOfOffer.hasValue(DETERMINES)
                && acceptanceOfOffer.value(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (acceptanceOfOffer.hasValue(ENDING)
                && acceptanceOfOffer.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (acceptanceOfOffer.hasValue(MEMBER__OF)
                && acceptanceOfOffer.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (acceptanceOfOffer.hasValue(MEMBER_OF)
                && acceptanceOfOffer.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!acceptanceOfOffer.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (acceptanceOfOffer.hasValue(PART__OF)
                && acceptanceOfOffer.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!acceptanceOfOffer.hasValue(PART_OF)) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (acceptanceOfOffer.hasValue(PART_OF_)
                && acceptanceOfOffer.value(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!acceptanceOfOffer.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (!acceptanceOfOffer.hasValue(REFERENCES)) {
            throw new HqdmException("Property Not Set: references");
        }
        if (acceptanceOfOffer.hasValue(TEMPORAL__PART_OF)
                && acceptanceOfOffer.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (acceptanceOfOffer.hasValue(TEMPORAL_PART_OF)
                && acceptanceOfOffer.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return acceptanceOfOffer;
    }
}
