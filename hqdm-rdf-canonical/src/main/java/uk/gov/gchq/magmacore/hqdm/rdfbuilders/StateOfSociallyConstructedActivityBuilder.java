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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfSociallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SociallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedActivity;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of StateOfSociallyConstructedActivity.
 */
public class StateOfSociallyConstructedActivityBuilder {

    private final StateOfSociallyConstructedActivity stateOfSociallyConstructedActivity;

    /**
     * Constructs a Builder for a new StateOfSociallyConstructedActivity.
     *
     * @param iri IRI of the StateOfSociallyConstructedActivity.
     */
    public StateOfSociallyConstructedActivityBuilder(final IRI iri) {
        stateOfSociallyConstructedActivity = SpatioTemporalExtentServices
                .createStateOfSociallyConstructedActivity(iri.getIri());
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
    public final StateOfSociallyConstructedActivityBuilder aggregated_Into(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfSociallyConstructedActivity.addValue(AGGREGATED_INTO,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfSociallyConstructedActivityBuilder beginning(final Event event) {
        stateOfSociallyConstructedActivity.addValue(BEGINNING, new IRI(event.getId()));
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
    public final StateOfSociallyConstructedActivityBuilder consists__Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfSociallyConstructedActivity.addValue(CONSISTS__OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfSociallyConstructedActivityBuilder ending(final Event event) {
        stateOfSociallyConstructedActivity.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final StateOfSociallyConstructedActivityBuilder member__Of(final Class clazz) {
        stateOfSociallyConstructedActivity.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfSociallyConstructedObject}.
     *
     * @param classOfStateOfSociallyConstructedObject The classOfStateOfSociallyConstructedObject.
     * @return This builder.
     */
    @SuppressWarnings("LineLength")
    public final StateOfSociallyConstructedActivityBuilder member_Of(
            final ClassOfStateOfSociallyConstructedObject classOfStateOfSociallyConstructedObject) {
        stateOfSociallyConstructedActivity.addValue(MEMBER_OF,
                new IRI(classOfStateOfSociallyConstructedObject.getId()));
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
    public final StateOfSociallyConstructedActivityBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfSociallyConstructedActivity.addValue(PART__OF,
                new IRI(spatioTemporalExtent.getId()));
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
    public final StateOfSociallyConstructedActivityBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        stateOfSociallyConstructedActivity.addValue(PART_OF_POSSIBLE_WORLD,
                new IRI(possibleWorld.getId()));
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
    public final StateOfSociallyConstructedActivityBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfSociallyConstructedActivity.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSociallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} one or more
     * {@link SociallyConstructedObject}.
     *
     * @param sociallyConstructedObject The SociallyConstructedObject.
     * @return This builder.
     */
    public final StateOfSociallyConstructedActivityBuilder temporal_Part_Of(
            final SociallyConstructedObject sociallyConstructedObject) {
        stateOfSociallyConstructedActivity.addValue(TEMPORAL_PART_OF,
                new IRI(sociallyConstructedObject.getId()));
        return this;
    }

    /**
     * Returns an instance of StateOfSociallyConstructedActivity created from the properties set on this
     * builder.
     *
     * @return The built StateOfSociallyConstructedActivity.
     * @throws HqdmException If the StateOfSociallyConstructedActivity is missing any mandatory
     *                       properties.
     */
    public StateOfSociallyConstructedActivity build() throws HqdmException {
        if (stateOfSociallyConstructedActivity.hasValue(AGGREGATED_INTO)
                && stateOfSociallyConstructedActivity.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (stateOfSociallyConstructedActivity.hasValue(BEGINNING)
                && stateOfSociallyConstructedActivity.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (stateOfSociallyConstructedActivity.hasValue(ENDING)
                && stateOfSociallyConstructedActivity.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (stateOfSociallyConstructedActivity.hasValue(MEMBER__OF)
                && stateOfSociallyConstructedActivity.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (stateOfSociallyConstructedActivity.hasValue(MEMBER_OF)
                && stateOfSociallyConstructedActivity.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (stateOfSociallyConstructedActivity.hasValue(PART__OF)
                && stateOfSociallyConstructedActivity.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!stateOfSociallyConstructedActivity.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (stateOfSociallyConstructedActivity.hasValue(TEMPORAL__PART_OF)
                && stateOfSociallyConstructedActivity.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (stateOfSociallyConstructedActivity.hasValue(TEMPORAL_PART_OF)
                && stateOfSociallyConstructedActivity.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return stateOfSociallyConstructedActivity;
    }
}
