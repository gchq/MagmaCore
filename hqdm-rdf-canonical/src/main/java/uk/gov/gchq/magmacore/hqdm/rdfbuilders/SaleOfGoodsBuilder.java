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
import uk.gov.gchq.magmacore.hqdm.model.AgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfContractProcess;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.ExchangeOfGoodsAndMoney;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.OfferAndAcceptanceForGoods;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.ReachingAgreement;
import uk.gov.gchq.magmacore.hqdm.model.SaleOfGoods;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of SaleOfGoods.
 */
public class SaleOfGoodsBuilder {

    private final SaleOfGoods saleOfGoods;

    /**
     * Constructs a Builder for a new SaleOfGoods.
     *
     * @param iri IRI of the SaleOfGoods.
     */
    public SaleOfGoodsBuilder(final IRI iri) {
        saleOfGoods = RdfSpatioTemporalExtentServices.createSaleOfGoods(iri.getIri());
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
    public final SaleOfGoodsBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        saleOfGoods.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder beginning(final Event event) {
        saleOfGoods.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.model.Activity} is the cause of
     * one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder causes_M(final Event event) {
        saleOfGoods.addValue(CAUSES, new IRI(event.getId()));
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
    public final SaleOfGoodsBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        saleOfGoods.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where a
     * {@link SaleOfGoods} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} exactly one
     * {@link OfferAndAcceptanceForGoods}.
     *
     * @param offerAndAcceptanceForGoods The OfferAndAcceptanceForGoods.
     * @return Builder
     */
    public final SaleOfGoodsBuilder consists_Of(
            final OfferAndAcceptanceForGoods offerAndAcceptanceForGoods) {
        saleOfGoods.addValue(CONSISTS_OF, new IRI(offerAndAcceptanceForGoods.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where a
     * {@link SaleOfGoods} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} exactly one
     * {@link ExchangeOfGoodsAndMoney}.
     *
     * @param exchangeOfGoodsAndMoney The ExchangeOfGoodsAndMoney.
     * @return Builder
     */
    public final SaleOfGoodsBuilder consists_Of_(final ExchangeOfGoodsAndMoney exchangeOfGoodsAndMoney) {
        saleOfGoods.addValue(CONSISTS_OF_, new IRI(exchangeOfGoodsAndMoney.getId()));
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
    public final SaleOfGoodsBuilder consists_Of_Participant(final Participant participant) {
        saleOfGoods.addValue(CONSISTS_OF_PARTICIPANT, new IRI(participant.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may determine one
     * or more {@link Thing} to be the case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder determines(final Thing thing) {
        saleOfGoods.addValue(DETERMINES, new IRI(thing.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder ending(final Event event) {
        saleOfGoods.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder member__Of(final Class clazz) {
        saleOfGoods.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * contract process may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or
     * more {@link ClassOfContractProcess}.
     *
     * @param classOfContractProcess The ClassOfContractProcess.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder member_Of(final ClassOfContractProcess classOfContractProcess) {
        saleOfGoods.addValue(MEMBER_OF, new IRI(classOfContractProcess.getId()));
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
    public final SaleOfGoodsBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        saleOfGoods.addValue(MEMBER_OF_KIND, new IRI(kindOfActivity.getId()));
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
    public final SaleOfGoodsBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        saleOfGoods.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedActivity} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} one or more
     * {@link ReachingAgreement}.
     *
     * @param reachingAgreement The ReachingAgreement.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder part_Of(final ReachingAgreement reachingAgreement) {
        saleOfGoods.addValue(PART_OF, new IRI(reachingAgreement.getId()));
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
    public final SaleOfGoodsBuilder part_Of_(final AgreementExecution agreementExecution) {
        saleOfGoods.addValue(PART_OF_, new IRI(agreementExecution.getId()));
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
    public final SaleOfGoodsBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        saleOfGoods.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Activity} may reference one
     * or more {@link Thing}.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final SaleOfGoodsBuilder references(final Thing thing) {
        saleOfGoods.addValue(REFERENCES, new IRI(thing.getId()));
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
    public final SaleOfGoodsBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        saleOfGoods.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
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
    public final SaleOfGoodsBuilder temporal_Part_Of(final Individual individual) {
        saleOfGoods.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of SaleOfGoods created from the properties set on this builder.
     *
     * @return The built SaleOfGoods.
     * @throws HqdmException If the SaleOfGoods is missing any mandatory properties.
     */
    public SaleOfGoods build() throws HqdmException {
        if (saleOfGoods.hasValue(AGGREGATED_INTO)
                && saleOfGoods.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (saleOfGoods.hasValue(BEGINNING)
                && saleOfGoods.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!saleOfGoods.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (saleOfGoods.hasValue(DETERMINES)
                && saleOfGoods.value(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (saleOfGoods.hasValue(ENDING)
                && saleOfGoods.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (saleOfGoods.hasValue(MEMBER__OF)
                && saleOfGoods.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (saleOfGoods.hasValue(MEMBER_OF)
                && saleOfGoods.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!saleOfGoods.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (saleOfGoods.hasValue(PART__OF)
                && saleOfGoods.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (saleOfGoods.hasValue(PART_OF)
                && saleOfGoods.value(PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (saleOfGoods.hasValue(PART_OF_)
                && saleOfGoods.value(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!saleOfGoods.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (saleOfGoods.hasValue(REFERENCES)
                && saleOfGoods.value(REFERENCES).isEmpty()) {
            throw new HqdmException("Property Not Set: references");
        }
        if (saleOfGoods.hasValue(TEMPORAL__PART_OF)
                && saleOfGoods.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (saleOfGoods.hasValue(TEMPORAL_PART_OF)
                && saleOfGoods.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return saleOfGoods;
    }
}
