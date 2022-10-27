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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CAUSES_BEGINNING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CAUSES_ENDING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_PARTICIPANT;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_PARTICIPANT_;
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
import uk.gov.gchq.magmacore.hqdm.model.Activity;
import uk.gov.gchq.magmacore.hqdm.model.AgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.BeginningOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity;
import uk.gov.gchq.magmacore.hqdm.model.EndingOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.ExchangeOfGoodsAndMoney;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.MoneyAsset;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnershipOfMoney;
import uk.gov.gchq.magmacore.hqdm.model.Transferee;
import uk.gov.gchq.magmacore.hqdm.model.Transferor;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of TransferOfOwnershipOfMoney.
 */
public class TransferOfOwnershipOfMoneyBuilder {

    private final TransferOfOwnershipOfMoney transferOfOwnershipOfMoney;

    /**
     * Constructs a Builder for a new TransferOfOwnershipOfMoney.
     *
     * @param iri IRI of the TransferOfOwnershipOfMoney.
     */
    public TransferOfOwnershipOfMoneyBuilder(final IRI iri) {
        transferOfOwnershipOfMoney = SpatioTemporalExtentServices.createTransferOfOwnershipOfMoney(iri.getIri());
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
    public final TransferOfOwnershipOfMoneyBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        transferOfOwnershipOfMoney.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder beginning(final Event event) {
        transferOfOwnershipOfMoney.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link Activity} is the cause of one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder causes_M(final Event event) {
        transferOfOwnershipOfMoney.addValue(CAUSES, new IRI(event.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CAUSES} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CAUSES} exactly one
     * {@link BeginningOfOwnership}.
     *
     * @param beginningOfOwnership The BeginningOfOwnership.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder causes_Beginning_M(final BeginningOfOwnership beginningOfOwnership) {
        transferOfOwnershipOfMoney.addValue(CAUSES_BEGINNING,
                new IRI(beginningOfOwnership.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CAUSES} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership} causes exactly one
     * {@link EndingOfOwnership}.
     *
     * @param endingOfOwnership The EndingOfOwnership.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder causes_Ending_M(final EndingOfOwnership endingOfOwnership) {
        transferOfOwnershipOfMoney.addValue(CAUSES_ENDING, new IRI(endingOfOwnership.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        transferOfOwnershipOfMoney.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder consists_Of(final Activity activity) {
        transferOfOwnershipOfMoney.addValue(CONSISTS_OF, new IRI(activity.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF_PARTICIPANT} relationship type
     * where a {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF_PARTICIPANT} exactly one
     * {@link Transferor}.
     *
     * @param transferor The Transferor.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder consists_Of_Participant(final Transferor transferor) {
        transferOfOwnershipOfMoney.addValue(CONSISTS_OF_PARTICIPANT, new IRI(transferor.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF_PARTICIPANT} relationship type
     * where a {@link uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF_PARTICIPANT} exactly one
     * {@link Transferee}.
     *
     * @param transferee The Transferee.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder consists_Of_Participant_(final Transferee transferee) {
        transferOfOwnershipOfMoney.addValue(CONSISTS_OF_PARTICIPANT_, new IRI(transferee.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link Activity} may determine one or more {@link Thing} to be the
     * case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder determines(final Thing thing) {
        transferOfOwnershipOfMoney.addValue(DETERMINES, new IRI(thing.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder ending(final Event event) {
        transferOfOwnershipOfMoney.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder member__Of(final Class clazz) {
        transferOfOwnershipOfMoney.addValue(MEMBER__OF, new IRI(clazz.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder member_Of(
            final ClassOfSociallyConstructedActivity classOfSociallyConstructedActivity) {
        transferOfOwnershipOfMoney.addValue(MEMBER_OF,
                new IRI(classOfSociallyConstructedActivity.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        transferOfOwnershipOfMoney.addValue(MEMBER_OF_KIND, new IRI(kindOfActivity.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        transferOfOwnershipOfMoney.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link TransferOfOwnershipOfMoney}may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} at most one
     * {@link ExchangeOfGoodsAndMoney}.
     *
     * @param exchangeOfGoodsAndMoney The ExchangeOfGoodsAndMoney.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder part_Of(final ExchangeOfGoodsAndMoney exchangeOfGoodsAndMoney) {
        transferOfOwnershipOfMoney.addValue(PART_OF, new IRI(exchangeOfGoodsAndMoney.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder part_Of_(final AgreementExecution agreementExecution) {
        transferOfOwnershipOfMoney.addValue(PART_OF_, new IRI(agreementExecution.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        transferOfOwnershipOfMoney.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A references relationship type where a {@link TransferOfOwnershipOfMoney}references exactly one
     * {@link MoneyAsset}.
     *
     * @param moneyAsset The MoneyAsset.
     * @return This builder.
     */
    public final TransferOfOwnershipOfMoneyBuilder references_M(final MoneyAsset moneyAsset) {
        transferOfOwnershipOfMoney.addValue(REFERENCES, new IRI(moneyAsset.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        transferOfOwnershipOfMoney.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
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
    public final TransferOfOwnershipOfMoneyBuilder temporal_Part_Of(final Individual individual) {
        transferOfOwnershipOfMoney.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of TransferOfOwnershipOfMoney created from the properties set on this
     * builder.
     *
     * @return The built TransferOfOwnershipOfMoney.
     * @throws HqdmException If the TransferOfOwnershipOfMoney is missing any mandatory properties.
     */
    public TransferOfOwnershipOfMoney build() throws HqdmException {
        if (transferOfOwnershipOfMoney.hasValue(AGGREGATED_INTO)
                && transferOfOwnershipOfMoney.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (transferOfOwnershipOfMoney.hasValue(BEGINNING)
                && transferOfOwnershipOfMoney.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!transferOfOwnershipOfMoney.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (!transferOfOwnershipOfMoney.hasValue(CAUSES_BEGINNING)) {
            throw new HqdmException("Property Not Set: causes_beginning");
        }
        if (!transferOfOwnershipOfMoney.hasValue(CAUSES_ENDING)) {
            throw new HqdmException("Property Not Set: causes_ending");
        }
        if (transferOfOwnershipOfMoney.hasValue(DETERMINES)
                && transferOfOwnershipOfMoney.value(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (transferOfOwnershipOfMoney.hasValue(ENDING)
                && transferOfOwnershipOfMoney.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (transferOfOwnershipOfMoney.hasValue(MEMBER__OF)
                && transferOfOwnershipOfMoney.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (transferOfOwnershipOfMoney.hasValue(MEMBER_OF)
                && transferOfOwnershipOfMoney.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!transferOfOwnershipOfMoney.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (transferOfOwnershipOfMoney.hasValue(PART__OF)
                && transferOfOwnershipOfMoney.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (transferOfOwnershipOfMoney.hasValue(PART_OF)
                && transferOfOwnershipOfMoney.value(PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (transferOfOwnershipOfMoney.hasValue(PART_OF_)
                && transferOfOwnershipOfMoney.value(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!transferOfOwnershipOfMoney.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (!transferOfOwnershipOfMoney.hasValue(REFERENCES)) {
            throw new HqdmException("Property Not Set: references");
        }
        if (transferOfOwnershipOfMoney.hasValue(TEMPORAL__PART_OF)
                && transferOfOwnershipOfMoney.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (transferOfOwnershipOfMoney.hasValue(TEMPORAL_PART_OF)
                && transferOfOwnershipOfMoney.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return transferOfOwnershipOfMoney;
    }
}
