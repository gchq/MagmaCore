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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of StateOfOrdinaryFunctionalObject.
 */
public class StateOfOrdinaryFunctionalObjectBuilder {

    private final StateOfOrdinaryFunctionalObject stateOfOrdinaryFunctionalObject;

    /**
     * Constructs a Builder for a new StateOfOrdinaryFunctionalObject.
     *
     * @param iri IRI of the StateOfOrdinaryFunctionalObject.
     */
    public StateOfOrdinaryFunctionalObjectBuilder(final IRI iri) {
        stateOfOrdinaryFunctionalObject = SpatioTemporalExtentServices
                .createStateOfOrdinaryFunctionalObject(iri);
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
    public final StateOfOrdinaryFunctionalObjectBuilder aggregated_Into(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryFunctionalObject.addValue(AGGREGATED_INTO,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfOrdinaryFunctionalObjectBuilder beginning(final Event event) {
        this.stateOfOrdinaryFunctionalObject.addValue(BEGINNING, event.getId());
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
    public final StateOfOrdinaryFunctionalObjectBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryFunctionalObject.addValue(CONSISTS__OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfOrdinaryFunctionalObjectBuilder ending(final Event event) {
        this.stateOfOrdinaryFunctionalObject.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final StateOfOrdinaryFunctionalObjectBuilder member__Of(final Class clazz) {
        this.stateOfOrdinaryFunctionalObject.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link StateOfOrdinaryFunctionalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfOrdinaryFunctionalObject}.
     *
     * @param classOfStateOfOrdinaryFunctionalObject The ClassOfStateOfOrdinaryFunctionalObject.
     * @return This builder.
     */
    @SuppressWarnings("LineLength")
    public final StateOfOrdinaryFunctionalObjectBuilder member_Of(
            final ClassOfStateOfOrdinaryFunctionalObject classOfStateOfOrdinaryFunctionalObject) {
        this.stateOfOrdinaryFunctionalObject.addValue(MEMBER_OF,
                classOfStateOfOrdinaryFunctionalObject.getId());
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
    public final StateOfOrdinaryFunctionalObjectBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryFunctionalObject.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final StateOfOrdinaryFunctionalObjectBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.stateOfOrdinaryFunctionalObject.addValue(PART_OF_POSSIBLE_WORLD,
                possibleWorld.getId());
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
    public final StateOfOrdinaryFunctionalObjectBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryFunctionalObject.addValue(TEMPORAL__PART_OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link StateOfOrdinaryFunctionalObject} may be a temporal part of one or more
     * {@link OrdinaryFunctionalObject}.
     *
     * @param ordinaryFunctionalObject The OrdinaryFunctionalObject.
     * @return This builder.
     */
    public final StateOfOrdinaryFunctionalObjectBuilder temporal_Part_Of(
            final OrdinaryFunctionalObject ordinaryFunctionalObject) {
        this.stateOfOrdinaryFunctionalObject.addValue(TEMPORAL_PART_OF,
                ordinaryFunctionalObject.getId());
        return this;
    }

    /**
     * Returns an instance of StateOfOrdinaryFunctionalObject created from the properties set on this
     * builder.
     *
     * @return The built StateOfOrdinaryFunctionalObject.
     * @throws HqdmException If the StateOfOrdinaryFunctionalObject is missing any mandatory properties.
     */
    public StateOfOrdinaryFunctionalObject build() throws HqdmException {
        if (this.stateOfOrdinaryFunctionalObject.hasValue(AGGREGATED_INTO)
                && this.stateOfOrdinaryFunctionalObject.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(BEGINNING)
                && this.stateOfOrdinaryFunctionalObject.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(ENDING)
                && this.stateOfOrdinaryFunctionalObject.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(MEMBER__OF)
                && this.stateOfOrdinaryFunctionalObject.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(MEMBER_OF)
                && this.stateOfOrdinaryFunctionalObject.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(PART__OF)
                && this.stateOfOrdinaryFunctionalObject.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.stateOfOrdinaryFunctionalObject.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(TEMPORAL__PART_OF)
                && this.stateOfOrdinaryFunctionalObject.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.stateOfOrdinaryFunctionalObject.hasValue(TEMPORAL_PART_OF)
                && this.stateOfOrdinaryFunctionalObject.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return stateOfOrdinaryFunctionalObject;
    }
}
