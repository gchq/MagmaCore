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
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

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
        amountOfMoney = RdfSpatioTemporalExtentServices.createAmountOfMoney(iri.getIri());
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
        amountOfMoney.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
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
        amountOfMoney.addValue(BEGINNING, new IRI(event.getId()));
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
        amountOfMoney.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
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
        amountOfMoney.addValue(ENDING, new IRI(event.getId()));
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
        amountOfMoney.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link AmountOfMoney} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one
     * or more {@link ClassOfAmountOfMoney}.
     *
     * @param classOfAmountOfMoney The ClassOfAmountOfMoney.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder member_Of(final ClassOfAmountOfMoney classOfAmountOfMoney) {
        amountOfMoney.addValue(MEMBER_OF, new IRI(classOfAmountOfMoney.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link AmountOfMoney} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * exactly one {@link Currency}.
     *
     * @param currency The Currency.
     * @return This builder.
     */
    public final AmountOfMoneyBuilder member_Of_Currency(final Currency currency) {
        amountOfMoney.addValue(MEMBER_OF_CURRENCY, new IRI(currency.getId()));
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
        amountOfMoney.addValue(MEMBER_OF_KIND, new IRI(kindOfSociallyConstructedObject.getId()));
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
        amountOfMoney.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final AmountOfMoneyBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        amountOfMoney.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
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
        amountOfMoney.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
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
        amountOfMoney.addValue(TEMPORAL_PART_OF, new IRI(amountOfMoney.getId()));
        return this;
    }

    /**
     * Returns an instance of AmountOfMoney created from the properties set on this builder.
     *
     * @return The built AmountOfMoney.
     * @throws HqdmException If the AmountOfMoney is missing any mandatory properties.
     */
    public AmountOfMoney build() throws HqdmException {
        if (amountOfMoney.hasValue(AGGREGATED_INTO)
                && amountOfMoney.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (amountOfMoney.hasValue(BEGINNING)
                && amountOfMoney.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (amountOfMoney.hasValue(ENDING)
                && amountOfMoney.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (amountOfMoney.hasValue(MEMBER__OF)
                && amountOfMoney.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (amountOfMoney.hasValue(MEMBER_OF)
                && amountOfMoney.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (amountOfMoney.hasValue(MEMBER_OF_CURRENCY)
                && amountOfMoney.value(MEMBER_OF_CURRENCY).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_currency");
        }
        if (amountOfMoney.hasValue(MEMBER_OF_KIND)
                && amountOfMoney.value(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (amountOfMoney.hasValue(PART__OF)
                && amountOfMoney.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!amountOfMoney.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (amountOfMoney.hasValue(TEMPORAL__PART_OF)
                && amountOfMoney.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (amountOfMoney.hasValue(TEMPORAL_PART_OF)
                && amountOfMoney.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return amountOfMoney;
    }
}
