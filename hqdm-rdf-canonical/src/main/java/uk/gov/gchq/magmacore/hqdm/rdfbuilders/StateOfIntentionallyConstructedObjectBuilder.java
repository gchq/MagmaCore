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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfIntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.StateOfIntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of StateOfIntentionallyConstructedObject.
 */
public class StateOfIntentionallyConstructedObjectBuilder {

    @SuppressWarnings("LineLength")
    private final StateOfIntentionallyConstructedObject stateOfIntentionallyConstructedObject;

    /**
     * Constructs a Builder for a new StateOfIntentionallyConstructedObject.
     *
     * @param iri IRI of the StateOfIntentionallyConstructedObject.
     */
    public StateOfIntentionallyConstructedObjectBuilder(final IRI iri) {
        stateOfIntentionallyConstructedObject = RdfSpatioTemporalExtentServices
                .createStateOfIntentionallyConstructedObject(iri.getIri());
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
    public final StateOfIntentionallyConstructedObjectBuilder aggregated_Into(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfIntentionallyConstructedObject.addValue(AGGREGATED_INTO,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfIntentionallyConstructedObjectBuilder beginning(final Event event) {
        stateOfIntentionallyConstructedObject.addValue(BEGINNING, new IRI(event.getId()));
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
    public final StateOfIntentionallyConstructedObjectBuilder consists__Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfIntentionallyConstructedObject.addValue(CONSISTS__OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfIntentionallyConstructedObjectBuilder ending(final Event event) {
        stateOfIntentionallyConstructedObject.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final StateOfIntentionallyConstructedObjectBuilder member__Of(final Class clazz) {
        stateOfIntentionallyConstructedObject.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link StateOfIntentionallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfIntentionallyConstructedObject}.
     *
     * @param classOfStateOfIntentionallyConstructedObject classOfStateOfIntentionallyConstructedObject.
     * @return This builder.
     */
    @SuppressWarnings("LineLength")
    public final StateOfIntentionallyConstructedObjectBuilder member_Of(
            final ClassOfStateOfIntentionallyConstructedObject classOfStateOfIntentionallyConstructedObject) {
        stateOfIntentionallyConstructedObject.addValue(MEMBER_OF,
                new IRI(classOfStateOfIntentionallyConstructedObject.getId()));
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
    public final StateOfIntentionallyConstructedObjectBuilder part__Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfIntentionallyConstructedObject.addValue(PART__OF,
                new IRI(spatioTemporalExtent.getId()));
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
    public final StateOfIntentionallyConstructedObjectBuilder part_Of_Possible_World_M(
            final PossibleWorld possibleWorld) {
        stateOfIntentionallyConstructedObject.addValue(PART_OF_POSSIBLE_WORLD,
                new IRI(possibleWorld.getId()));
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
    public final StateOfIntentionallyConstructedObjectBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        stateOfIntentionallyConstructedObject.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link StateOfIntentionallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link IntentionallyConstructedObject}.
     *
     * @param intentionallyConstructedObject The IntentionallyConstructedObject.
     * @return This builder.
     */
    public final StateOfIntentionallyConstructedObjectBuilder temporal_Part_Of(
            final IntentionallyConstructedObject intentionallyConstructedObject) {
        stateOfIntentionallyConstructedObject.addValue(TEMPORAL_PART_OF,
                new IRI(intentionallyConstructedObject.getId()));
        return this;
    }

    /**
     * Returns an instance of StateOfIntentionallyConstructedObject created from the properties set on
     * this builder.
     *
     * @return The built StateOfIntentionallyConstructedObject.
     * @throws HqdmException If the StateOfIntentionallyConstructedObject is missing any mandatory
     *                       properties.
     */
    public StateOfIntentionallyConstructedObject build() throws HqdmException {
        if (stateOfIntentionallyConstructedObject.hasValue(AGGREGATED_INTO)
                && stateOfIntentionallyConstructedObject.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(BEGINNING)
                && stateOfIntentionallyConstructedObject.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(ENDING)
                && stateOfIntentionallyConstructedObject.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(MEMBER__OF)
                && stateOfIntentionallyConstructedObject.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(MEMBER_OF)
                && stateOfIntentionallyConstructedObject.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(PART__OF)
                && stateOfIntentionallyConstructedObject.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!stateOfIntentionallyConstructedObject.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(TEMPORAL__PART_OF)
                && stateOfIntentionallyConstructedObject.value(TEMPORAL__PART_OF)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (stateOfIntentionallyConstructedObject.hasValue(TEMPORAL_PART_OF)
                && stateOfIntentionallyConstructedObject.value(TEMPORAL_PART_OF)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return stateOfIntentionallyConstructedObject;
    }
}
