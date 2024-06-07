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
import uk.gov.gchq.magmacore.hqdm.model.AcceptanceOfOfferForGoods;
import uk.gov.gchq.magmacore.hqdm.model.Activity;
import uk.gov.gchq.magmacore.hqdm.model.AgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.OfferAndAcceptanceForGoods;
import uk.gov.gchq.magmacore.hqdm.model.OfferForGoods;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of AcceptanceOfOfferForGoods.
 */
public class AcceptanceOfOfferForGoodsBuilder {

    private final AcceptanceOfOfferForGoods acceptanceOfOfferForGoods;

    /**
     * Constructs a Builder for a new AcceptanceOfOfferForGoods.
     *
     * @param iri IRI of the AcceptanceOfOfferForGoods.
     */
    public AcceptanceOfOfferForGoodsBuilder(final IRI iri) {
        this.acceptanceOfOfferForGoods = SpatioTemporalExtentServices.createAcceptanceOfOfferForGoods(iri);
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may be aggregated into one or more
     * others.
     *
     * <p>
     * Note: This has the same meaning as, but different representation to, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} entity type.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.acceptanceOfOfferForGoods.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder beginning(final Event event) {
        this.acceptanceOfOfferForGoods.addValue(BEGINNING, event.getId());
        return this;
    }

    /**
     * A relationship type where each {@link Activity} is the cause of one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder causes_M(final Event event) {
        this.acceptanceOfOfferForGoods.addValue(CAUSES, event.getId());
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
    public final AcceptanceOfOfferForGoodsBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.acceptanceOfOfferForGoods.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where an
     * {@link Activity} may {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} one or more
     * other {@link Activity}.
     *
     * @param activity The Activity.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder consists_Of(final Activity activity) {
        this.acceptanceOfOfferForGoods.addValue(CONSISTS_OF, activity.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where an
     * {@link Activity} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} one or more
     * {@link Participant}s.
     *
     * @param participant The Participant.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder consists_Of_Participant(final Participant participant) {
        this.acceptanceOfOfferForGoods.addValue(CONSISTS_OF_PARTICIPANT, participant.getId());
        return this;
    }

    /**
     * A relationship type where an {@link Activity} may determine one or more {@link Thing} to be the
     * case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder determines(final Thing thing) {
        this.acceptanceOfOfferForGoods.addValue(DETERMINES, thing.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder ending(final Event event) {
        this.acceptanceOfOfferForGoods.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder member__Of(final Class clazz) {
        this.acceptanceOfOfferForGoods.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfSociallyConstructedActivity}.
     *
     * @param classOfSociallyConstructedActivity The ClassOfSociallyConstructedActivity.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder member_Of(
            final ClassOfSociallyConstructedActivity classOfSociallyConstructedActivity) {
        this.acceptanceOfOfferForGoods.addValue(MEMBER_OF,
                classOfSociallyConstructedActivity.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where each
     * {@link Activity} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfActivity}.
     *
     * @param kindOfActivity The KindOfActivity.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        this.acceptanceOfOfferForGoods.addValue(MEMBER_OF_KIND, kindOfActivity.getId());
        this.acceptanceOfOfferForGoods.addValue(RDFS.RDF_TYPE, kindOfActivity.getId());
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
    public final AcceptanceOfOfferForGoodsBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.acceptanceOfOfferForGoods.addValue(PART__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where an
     * {@link AcceptanceOfOfferForGoods} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF}
     * exactly one {@link OfferAndAcceptanceForGoods}.
     *
     * @param offerAndAcceptanceForGoods The OfferAndAcceptanceForGoods.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder part_Of_M(
            final OfferAndAcceptanceForGoods offerAndAcceptanceForGoods) {
        this.acceptanceOfOfferForGoods.addValue(PART_OF, offerAndAcceptanceForGoods.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} one or more {@link AgreementExecution}.
     *
     * @param agreementExecution The AgreementExecution.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder part_Of_(final AgreementExecution agreementExecution) {
        this.acceptanceOfOfferForGoods.addValue(PART_OF_, agreementExecution.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} one
     * or more {@link PossibleWorld}.
     *
     * <p>
     * Note: The relationship is optional because a {@link PossibleWorld} is not
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} any other {@link SpatioTemporalExtent}.
     * </p>
     *
     * @param possibleWorld The PossibleWorld.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.acceptanceOfOfferForGoods.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
        return this;
    }

    /**
     * A references relationship to exactly one {@link OfferForGoods} accepted.
     *
     * @param offerForGoods The OfferForGoods.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder references_M(final OfferForGoods offerForGoods) {
        this.acceptanceOfOfferForGoods.addValue(REFERENCES, offerForGoods.getId());
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
    public final AcceptanceOfOfferForGoodsBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.acceptanceOfOfferForGoods.addValue(TEMPORAL__PART_OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more {@link Individual}.
     *
     * <p>
     * Note: The relationship is optional because an {@link Individual} is not necessarily a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} another {@link Individual}, yet
     * is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as {@link Individual}. This applies to all
     * subtypes of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} that are between a
     * {@code state_of_X} and {@code X}.
     * </p>
     *
     * @param individual The Individual.
     * @return This builder.
     */
    public final AcceptanceOfOfferForGoodsBuilder temporal_Part_Of(final Individual individual) {
        this.acceptanceOfOfferForGoods.addValue(TEMPORAL_PART_OF, individual.getId());
        return this;
    }

    /**
     * Returns an instance of AcceptanceOfOfferForGoods created from the properties set on this builder.
     *
     * @return The built AcceptanceOfOfferForGoods.
     * @throws HqdmException If the AcceptanceOfOfferForGoods is missing any mandatory properties.
     */
    public AcceptanceOfOfferForGoods build() throws HqdmException {
        if (this.acceptanceOfOfferForGoods.hasValue(AGGREGATED_INTO)
                && this.acceptanceOfOfferForGoods.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(BEGINNING)
                && this.acceptanceOfOfferForGoods.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!this.acceptanceOfOfferForGoods.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(DETERMINES)
                && this.acceptanceOfOfferForGoods.values(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(ENDING)
                && this.acceptanceOfOfferForGoods.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(MEMBER__OF)
                && this.acceptanceOfOfferForGoods.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(MEMBER_OF)
                && this.acceptanceOfOfferForGoods.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.acceptanceOfOfferForGoods.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(PART__OF)
                && this.acceptanceOfOfferForGoods.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.acceptanceOfOfferForGoods.hasValue(PART_OF)) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(PART_OF_)
                && this.acceptanceOfOfferForGoods.values(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!this.acceptanceOfOfferForGoods.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (!this.acceptanceOfOfferForGoods.hasValue(REFERENCES)) {
            throw new HqdmException("Property Not Set: references");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(TEMPORAL__PART_OF)
                && this.acceptanceOfOfferForGoods.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.acceptanceOfOfferForGoods.hasValue(TEMPORAL_PART_OF)
                && this.acceptanceOfOfferForGoods.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return this.acceptanceOfOfferForGoods;
    }
}
