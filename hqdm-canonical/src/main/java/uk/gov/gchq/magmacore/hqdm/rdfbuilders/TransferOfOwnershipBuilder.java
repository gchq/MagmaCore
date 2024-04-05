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
import uk.gov.gchq.magmacore.hqdm.model.Asset;
import uk.gov.gchq.magmacore.hqdm.model.BeginningOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity;
import uk.gov.gchq.magmacore.hqdm.model.EndingOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.ExchangeOfGoodsAndMoney;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfActivity;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.model.TransferOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Transferee;
import uk.gov.gchq.magmacore.hqdm.model.Transferor;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of TransferOfOwnership.
 */
public class TransferOfOwnershipBuilder {

    private final TransferOfOwnership transferOfOwnership;

    /**
     * Constructs a Builder for a new TransferOfOwnership.
     *
     * @param iri IRI of the TransferOfOwnership.
     */
    public TransferOfOwnershipBuilder(final IRI iri) {
        transferOfOwnership = SpatioTemporalExtentServices.createTransferOfOwnership(iri);
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
    public final TransferOfOwnershipBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.transferOfOwnership.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder beginning(final Event event) {
        this.transferOfOwnership.addValue(BEGINNING, event.getId());
        return this;
    }

    /**
     * A relationship type where each {@link Activity} is the cause of one or more {@link Event}.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder causes_M(final Event event) {
        this.transferOfOwnership.addValue(CAUSES, event.getId());
        return this;
    }

    /**
     * A causes relationship type where a {@link TransferOfOwnership}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CAUSES} exactly one {@link BeginningOfOwnership}.
     *
     * @param beginningOfOwnership The BeginningOfOwnership.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder causes_Beginning_M(final BeginningOfOwnership beginningOfOwnership) {
        this.transferOfOwnership.addValue(CAUSES_BEGINNING, beginningOfOwnership.getId());
        return this;
    }

    /**
     * A causes relationship type where a {@link TransferOfOwnership} causes exactly one
     * {@link EndingOfOwnership}.
     *
     * @param endingOfOwnership The EndingOfOwnership.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder causes_Ending_M(final EndingOfOwnership endingOfOwnership) {
        this.transferOfOwnership.addValue(CAUSES_ENDING, endingOfOwnership.getId());
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
    public final TransferOfOwnershipBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.transferOfOwnership.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
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
    public final TransferOfOwnershipBuilder consists_Of(final Activity activity) {
        this.transferOfOwnership.addValue(CONSISTS_OF, activity.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT} relationship type where
     * a {@link TransferOfOwnership}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT} exactly one
     * {@link Transferor}.
     *
     * @param transferor The Transferor.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder consists_Of_Participant(final Transferor transferor) {
        this.transferOfOwnership.addValue(CONSISTS_OF_PARTICIPANT, transferor.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT} relationship type where
     * a {@link TransferOfOwnership}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT} exactly one
     * {@link Transferee}.
     *
     * @param transferee The Transferee.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder consists_Of_Participant_(final Transferee transferee) {
        this.transferOfOwnership.addValue(CONSISTS_OF_PARTICIPANT_, transferee.getId());
        return this;
    }

    /**
     * A relationship type where an {@link Activity} may determine one or more {@link Thing} to be the
     * case.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder determines(final Thing thing) {
        this.transferOfOwnership.addValue(DETERMINES, thing.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder ending(final Event event) {
        this.transferOfOwnership.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder member__Of(final Class clazz) {
        this.transferOfOwnership.addValue(MEMBER__OF, clazz.getId());
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
    public final TransferOfOwnershipBuilder member_Of(
            final ClassOfSociallyConstructedActivity classOfSociallyConstructedActivity) {
        this.transferOfOwnership.addValue(MEMBER_OF,
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
    public final TransferOfOwnershipBuilder member_Of_Kind_M(final KindOfActivity kindOfActivity) {
        this.transferOfOwnership.addValue(MEMBER_OF_KIND, kindOfActivity.getId());
        this.transferOfOwnership.addValue(RDFS.RDF_TYPE, kindOfActivity.getId());
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
    public final TransferOfOwnershipBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.transferOfOwnership.addValue(PART__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link TransferOfOwnership} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} not
     * more than one {@link ExchangeOfGoodsAndMoney}.
     *
     * @param exchangeOfGoodsAndMoney The ExchangeOfGoodsAndMoney.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder part_Of(final ExchangeOfGoodsAndMoney exchangeOfGoodsAndMoney) {
        this.transferOfOwnership.addValue(PART_OF, exchangeOfGoodsAndMoney.getId());
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
    public final TransferOfOwnershipBuilder part_Of_(final AgreementExecution agreementExecution) {
        this.transferOfOwnership.addValue(PART_OF_, agreementExecution.getId());
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
    public final TransferOfOwnershipBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.transferOfOwnership.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
        return this;
    }

    /**
     * A references relationship type where a {@link TransferOfOwnership} references exactly one
     * {@link Asset}.
     *
     * @param asset The Asset.
     * @return This builder.
     */
    public final TransferOfOwnershipBuilder references_M(final Asset asset) {
        this.transferOfOwnership.addValue(REFERENCES, asset.getId());
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
    public final TransferOfOwnershipBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.transferOfOwnership.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
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
    public final TransferOfOwnershipBuilder temporal_Part_Of(final Individual individual) {
        this.transferOfOwnership.addValue(TEMPORAL_PART_OF, individual.getId());
        return this;
    }

    /**
     * Returns an instance of TransferOfOwnership created from the properties set on this builder.
     *
     * @return The built TransferOfOwnership.
     * @throws HqdmException If the TransferOfOwnership is missing any mandatory properties.
     */
    public TransferOfOwnership build() throws HqdmException {
        if (this.transferOfOwnership.hasValue(AGGREGATED_INTO)
                && this.transferOfOwnership.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.transferOfOwnership.hasValue(BEGINNING)
                && this.transferOfOwnership.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!this.transferOfOwnership.hasValue(CAUSES)) {
            throw new HqdmException("Property Not Set: causes");
        }
        if (!this.transferOfOwnership.hasValue(CAUSES_BEGINNING)) {
            throw new HqdmException("Property Not Set: causes_beginning");
        }
        if (!this.transferOfOwnership.hasValue(CAUSES_ENDING)) {
            throw new HqdmException("Property Not Set: causes_ending");
        }
        if (this.transferOfOwnership.hasValue(DETERMINES)
                && this.transferOfOwnership.values(DETERMINES).isEmpty()) {
            throw new HqdmException("Property Not Set: determines");
        }
        if (this.transferOfOwnership.hasValue(ENDING)
                && this.transferOfOwnership.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.transferOfOwnership.hasValue(MEMBER__OF)
                && this.transferOfOwnership.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.transferOfOwnership.hasValue(MEMBER_OF)
                && this.transferOfOwnership.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.transferOfOwnership.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.transferOfOwnership.hasValue(PART__OF)
                && this.transferOfOwnership.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (this.transferOfOwnership.hasValue(PART_OF)
                && this.transferOfOwnership.values(PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of");
        }
        if (this.transferOfOwnership.hasValue(PART_OF_)
                && this.transferOfOwnership.values(PART_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_");
        }
        if (!this.transferOfOwnership.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (!this.transferOfOwnership.hasValue(REFERENCES)) {
            throw new HqdmException("Property Not Set: references");
        }
        if (this.transferOfOwnership.hasValue(TEMPORAL__PART_OF)
                && this.transferOfOwnership.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.transferOfOwnership.hasValue(TEMPORAL_PART_OF)
                && this.transferOfOwnership.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return transferOfOwnership;
    }
}
