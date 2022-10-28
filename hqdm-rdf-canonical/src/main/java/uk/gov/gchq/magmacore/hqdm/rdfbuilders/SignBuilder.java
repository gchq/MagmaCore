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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PARTICIPANT_IN;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSign;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfSociallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of Sign.
 */
public class SignBuilder {

    private final Sign sign;

    /**
     * Constructs a Builder for a new Sign.
     *
     * @param iri IRI of the Sign.
     */
    public SignBuilder(final IRI iri) {
        sign = RdfSpatioTemporalExtentServices.createSign(iri.getIri());
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
    public final SignBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        sign.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SignBuilder beginning(final Event event) {
        sign.addValue(BEGINNING, new IRI(event.getId()));
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
    public final SignBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        sign.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SignBuilder ending(final Event event) {
        sign.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SignBuilder member__Of(final Class clazz) {
        sign.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link Sign} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfSign}.
     *
     * @param classOfSign The ClassOfSign.
     * @return This builder.
     */
    public final SignBuilder member_Of(final ClassOfSign classOfSign) {
        sign.addValue(MEMBER_OF, new IRI(classOfSign.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link Sign} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link Pattern}.
     *
     * @param pattern The Pattern.
     * @return This builder.
     */
    public final SignBuilder member_Of__M(final Pattern pattern) {
        sign.addValue(MEMBER_OF_, new IRI(pattern.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF_KIND} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link KindOfSociallyConstructedObject}.
     *
     * @param kindOfSociallyConstructedObject The KindOfSociallyConstructedObject.
     * @return This builder.
     */
    public final SignBuilder member_Of_Kind(
            final KindOfSociallyConstructedObject kindOfSociallyConstructedObject) {
        sign.addValue(MEMBER_OF_KIND, new IRI(kindOfSociallyConstructedObject.getId()));
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
    public final SignBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        sign.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final SignBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        sign.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PARTICIPANT_IN} relationship type where a
     * {@link Sign} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PARTICIPANT_IN} one or more
     * {@link RepresentationBySign}.
     *
     * @param representationBySign The RepresentationBySign.
     * @return This builder.
     */
    public final SignBuilder participant_In_M(final RepresentationBySign representationBySign) {
        sign.addValue(PARTICIPANT_IN, new IRI(representationBySign.getId()));
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
    public final SignBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        sign.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
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
    public final SignBuilder temporal_Part_Of(final Individual individual) {
        sign.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of Sign created from the properties set on this builder.
     *
     * @return The built Sign.
     * @throws HqdmException If the Sign is missing any mandatory properties.
     */
    public Sign build() throws HqdmException {
        if (sign.hasValue(AGGREGATED_INTO)
                && sign.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (sign.hasValue(BEGINNING)
                && sign.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (sign.hasValue(ENDING)
                && sign.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (sign.hasValue(MEMBER__OF)
                && sign.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (sign.hasValue(MEMBER_OF)
                && sign.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!sign.hasValue(MEMBER_OF_)) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (sign.hasValue(MEMBER_OF_KIND)
                && sign.value(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (sign.hasValue(PART__OF)
                && sign.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!sign.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (!sign.hasValue(PARTICIPANT_IN)) {
            throw new HqdmException("Property Not Set: participant_in");
        }
        if (sign.hasValue(TEMPORAL__PART_OF)
                && sign.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (sign.hasValue(TEMPORAL_PART_OF)
                && sign.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return sign;
    }
}
