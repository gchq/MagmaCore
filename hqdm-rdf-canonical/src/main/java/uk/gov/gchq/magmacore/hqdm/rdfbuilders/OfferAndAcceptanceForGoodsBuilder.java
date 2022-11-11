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
import uk.gov.gchq.magmacore.hqdm.model.AcceptanceOfOfferForGoods;
import uk.gov.gchq.magmacore.hqdm.model.AgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfAgreeContract;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.OfferAndAcceptanceForGoods;
import uk.gov.gchq.magmacore.hqdm.model.OfferForGoods;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SaleOfGoods;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of OfferAndAcceptanceForGoods.
 */
public class OfferAndAcceptanceForGoodsBuilder {

    private final OfferAndAcceptanceForGoods offerAndAcceptanceForGoods;

    /**
     * Constructs a Builder for a new OfferAndAcceptanceForGoods.
     *
     * @param iri IRI of the OfferAndAcceptanceForGoods.
     */
    public OfferAndAcceptanceForGoodsBuilder(final IRI iri) {
        offerAndAcceptanceForGoods = RdfSpatioTemporalExtentServices.createOfferAndAcceptanceForGoods(iri.getIri());
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
    public final OfferAndAcceptanceForGoodsBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        offerAndAcceptanceForGoods.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder beginning(final Event event) {
        offerAndAcceptanceForGoods.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.model.Activity} is the cause of
     * one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder causes_M(final Event event) {
        offerAndAcceptanceForGoods.addValue(CAUSES, new IRI(event.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        offerAndAcceptanceForGoods.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where an
     * {@link OfferAndAcceptanceForGoods} consists of exactly one {@link AcceptanceOfOfferForGoods}.
     *
     * @param acceptanceOfOfferForGoods The AcceptanceOfOfferForGoods.
     * @return Builder
     */
    public final OfferAndAcceptanceForGoodsBuilder consists_Of(
            final AcceptanceOfOfferForGoods acceptanceOfOfferForGoods) {
        offerAndAcceptanceForGoods.addValue(CONSISTS_OF,
                new IRI(acceptanceOfOfferForGoods.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_} relationship type where an
     * {@link OfferAndAcceptanceForGoods} consists_of exactly one {@link OfferForGoods}.
     *
     * @param offerForGoods The OfferForGoods.
     * @return Builder
     */
    public final OfferAndAcceptanceForGoodsBuilder consists_Of_(final OfferForGoods offerForGoods) {
        offerAndAcceptanceForGoods.addValue(CONSISTS_OF_, new IRI(offerForGoods.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder consists_Of_Participant(final Participant participant) {
        offerAndAcceptanceForGoods.addValue(CONSISTS_OF_PARTICIPANT, new IRI(participant.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may determine one
     * or more {@link Thing} to be the case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder determines(final Thing thing) {
        offerAndAcceptanceForGoods.addValue(DETERMINES, new IRI(thing.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder ending(final Event event) {
        offerAndAcceptanceForGoods.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder member__Of(final Class clazz) {
        offerAndAcceptanceForGoods.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.AgreeContract} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfAgreeContract}.
     *
     * @param classOfAgreeContract The ClassOfAgreeContract.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder member_Of(final ClassOfAgreeContract classOfAgreeContract) {
        offerAndAcceptanceForGoods.addValue(MEMBER_OF, new IRI(classOfAgreeContract.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        offerAndAcceptanceForGoods.addValue(MEMBER_OF_KIND, new IRI(kindOfActivity.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        offerAndAcceptanceForGoods.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where an
     * {@link OfferAndAcceptanceForGoods} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} exactly one {@link SaleOfGoods}.
     *
     * @param saleOfGoods The SaleOfGoods.
     * @return Builder
     */
    public final OfferAndAcceptanceForGoodsBuilder part_Of_M(final SaleOfGoods saleOfGoods) {
        offerAndAcceptanceForGoods.addValue(PART_OF, new IRI(saleOfGoods.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder part_Of_(final AgreementExecution agreementExecution) {
        offerAndAcceptanceForGoods.addValue(PART_OF_, new IRI(agreementExecution.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        offerAndAcceptanceForGoods.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may reference one
     * or more {@link Thing}.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final OfferAndAcceptanceForGoodsBuilder references(final Thing thing) {
        offerAndAcceptanceForGoods.addValue(REFERENCES, new IRI(thing.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        offerAndAcceptanceForGoods.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
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
    public final OfferAndAcceptanceForGoodsBuilder temporal_Part_Of(final Individual individual) {
        offerAndAcceptanceForGoods.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of OfferAndAcceptanceForGoods created from the properties set on this
     * builder.
     *
     * @return The built OfferAndAcceptanceForGoods.
     * @throws HqdmException If the OfferAndAcceptanceForGoods is missing any mandatory properties.
     */
    public OfferAndAcceptanceForGoods build() throws HqdmException {
        if (offerAndAcceptanceForGoods.hasValue(AGGREGATED_INTO)
                && offerAndAcceptanceForGoods.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (offerAndAcceptanceForGoods.hasValue(BEGINNING)
                && offerAndAcceptanceForGoods.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!offerAndAcceptanceForGoods.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (offerAndAcceptanceForGoods.hasValue(DETERMINES)
                && offerAndAcceptanceForGoods.value(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (offerAndAcceptanceForGoods.hasValue(ENDING)
                && offerAndAcceptanceForGoods.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (offerAndAcceptanceForGoods.hasValue(MEMBER__OF)
                && offerAndAcceptanceForGoods.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (offerAndAcceptanceForGoods.hasValue(MEMBER_OF)
                && offerAndAcceptanceForGoods.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!offerAndAcceptanceForGoods.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (offerAndAcceptanceForGoods.hasValue(PART__OF)
                && offerAndAcceptanceForGoods.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!offerAndAcceptanceForGoods.hasValue(PART_OF)) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (offerAndAcceptanceForGoods.hasValue(PART_OF_)
                && offerAndAcceptanceForGoods.value(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!offerAndAcceptanceForGoods.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (offerAndAcceptanceForGoods.hasValue(REFERENCES)
                && offerAndAcceptanceForGoods.value(REFERENCES).isEmpty()) {
            throw new HqdmException("Property Not Set: references");
        }
        if (offerAndAcceptanceForGoods.hasValue(TEMPORAL__PART_OF)
                && offerAndAcceptanceForGoods.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (offerAndAcceptanceForGoods.hasValue(TEMPORAL_PART_OF)
                && offerAndAcceptanceForGoods.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return offerAndAcceptanceForGoods;
    }
}
