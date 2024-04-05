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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ENDING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_CURRENCY;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.AmountOfMoney;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfAmountOfMoney;
import uk.gov.gchq.magmacore.hqdm.model.Currency;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.KindOfSociallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of AmountOfMoney.
 */
public class AmountOfMoneyBuilder {

    private final AmountOfMoney amountOfMoney;

    /**
     * Constructs a Builder for a new AmountOfMoney.
     *
     * @param iri IRI of the AmountOfMoney.
     */
    public AmountOfMoneyBuilder(final IRI iri) {
        this.amountOfMoney = SpatioTemporalExtentServices.createAmountOfMoney(iri);
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
    public final AmountOfMoneyBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.amountOfMoney.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder beginning(final Event event) {
        this.amountOfMoney.addValue(BEGINNING, event.getId());
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
    public final AmountOfMoneyBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.amountOfMoney.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder ending(final Event event) {
        this.amountOfMoney.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder member__Of(final Class clazz) {
        this.amountOfMoney.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link AmountOfMoney} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or
     * more {@link ClassOfAmountOfMoney}.
     *
     * @param classOfAmountOfMoney The ClassOfAmountOfMoney.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder member_Of(final ClassOfAmountOfMoney classOfAmountOfMoney) {
        this.amountOfMoney.addValue(MEMBER_OF, classOfAmountOfMoney.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link AmountOfMoney} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} exactly
     * one {@link Currency}.
     *
     * @param currency The Currency.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder member_Of_Currency(final Currency currency) {
        this.amountOfMoney.addValue(MEMBER_OF_CURRENCY, currency.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfSociallyConstructedObject}.
     *
     * @param kindOfSociallyConstructedObject The KindOfSociallyConstructedObject.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder member_Of_Kind(
            final KindOfSociallyConstructedObject kindOfSociallyConstructedObject) {
        this.amountOfMoney.addValue(MEMBER_OF_KIND, kindOfSociallyConstructedObject.getId());
        this.amountOfMoney.addValue(RDFS.RDF_TYPE, kindOfSociallyConstructedObject.getId());
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
    public final AmountOfMoneyBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.amountOfMoney.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final AmountOfMoneyBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.amountOfMoney.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
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
    public final AmountOfMoneyBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.amountOfMoney.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfAmountOfMoney} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link AmountOfMoney}.
     *
     * @param amountOfMoney The AmountOfMoney.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder temporal_Part_Of(final AmountOfMoney amountOfMoney) {
        this.amountOfMoney.addValue(TEMPORAL_PART_OF, amountOfMoney.getId());
        return this;
    }

    /**
     * Returns an instance of AmountOfMoney created from the properties set on this builder.
     *
     * @return The built AmountOfMoney.
     * @throws HqdmException If the AmountOfMoney is missing any mandatory properties.
     */
    public AmountOfMoney build() throws HqdmException {
        if (this.amountOfMoney.hasValue(AGGREGATED_INTO)
                && this.amountOfMoney.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.amountOfMoney.hasValue(BEGINNING)
                && this.amountOfMoney.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.amountOfMoney.hasValue(ENDING)
                && this.amountOfMoney.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.amountOfMoney.hasValue(MEMBER__OF)
                && this.amountOfMoney.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.amountOfMoney.hasValue(MEMBER_OF)
                && this.amountOfMoney.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.amountOfMoney.hasValue(MEMBER_OF_CURRENCY)
                && this.amountOfMoney.values(MEMBER_OF_CURRENCY).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_currency");
        }
        if (this.amountOfMoney.hasValue(MEMBER_OF_KIND)
                && this.amountOfMoney.values(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.amountOfMoney.hasValue(PART__OF)
                && this.amountOfMoney.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.amountOfMoney.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.amountOfMoney.hasValue(TEMPORAL__PART_OF)
                && this.amountOfMoney.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.amountOfMoney.hasValue(TEMPORAL_PART_OF)
                && this.amountOfMoney.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return this.amountOfMoney;
    }
}
