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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of StateOfFunctionalSystemComponent.
 */
public class StateOfFunctionalSystemComponentBuilder {

    private final StateOfFunctionalSystemComponent stateOfFunctionalSystemComponent;

    /**
     * Constructs a Builder for a new StateOfFunctionalSystemComponent.
     *
     * @param iri IRI of the StateOfFunctionalSystemComponent.
     */
    public StateOfFunctionalSystemComponentBuilder(final IRI iri) {
        stateOfFunctionalSystemComponent = SpatioTemporalExtentServices
                .createStateOfFunctionalSystemComponent(iri);
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
    public final StateOfFunctionalSystemComponentBuilder aggregated_Into(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfFunctionalSystemComponent.addValue(AGGREGATED_INTO,
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
    public final StateOfFunctionalSystemComponentBuilder beginning(final Event event) {
        this.stateOfFunctionalSystemComponent.addValue(BEGINNING, event.getId());
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
    public final StateOfFunctionalSystemComponentBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfFunctionalSystemComponent.addValue(CONSISTS__OF,
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
    public final StateOfFunctionalSystemComponentBuilder ending(final Event event) {
        this.stateOfFunctionalSystemComponent.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final StateOfFunctionalSystemComponentBuilder member__Of(final Class clazz) {
        this.stateOfFunctionalSystemComponent.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link StateOfFunctionalSystemComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfFunctionalSystemComponent}.
     *
     * @param classOfStateOfFunctionalSystemComponent The classOfStateOfFunctionalSystemComponent.
     * @return This builder.
     */
    @SuppressWarnings("LineLength")
    public final StateOfFunctionalSystemComponentBuilder member_Of(
            final ClassOfStateOfFunctionalSystemComponent classOfStateOfFunctionalSystemComponent) {
        this.stateOfFunctionalSystemComponent.addValue(MEMBER_OF,
                classOfStateOfFunctionalSystemComponent.getId());
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
    public final StateOfFunctionalSystemComponentBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfFunctionalSystemComponent.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final StateOfFunctionalSystemComponentBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.stateOfFunctionalSystemComponent.addValue(PART_OF_POSSIBLE_WORLD,
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
    public final StateOfFunctionalSystemComponentBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfFunctionalSystemComponent.addValue(TEMPORAL__PART_OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link StateOfFunctionalSystemComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link FunctionalSystemComponent}.
     *
     * @param functionalSystemComponent The FunctionalSystemComponent.
     * @return This builder.
     */
    public final StateOfFunctionalSystemComponentBuilder temporal_Part_Of(
            final FunctionalSystemComponent functionalSystemComponent) {
        this.stateOfFunctionalSystemComponent.addValue(TEMPORAL_PART_OF,
                functionalSystemComponent.getId());
        return this;
    }

    /**
     * Returns an instance of StateOfFunctionalSystemComponent created from the properties set on this
     * builder.
     *
     * @return The built StateOfFunctionalSystemComponent.
     * @throws HqdmException If the StateOfFunctionalSystemComponent is missing any mandatory
     *                       properties.
     */
    public StateOfFunctionalSystemComponent build() throws HqdmException {
        if (this.stateOfFunctionalSystemComponent.hasValue(AGGREGATED_INTO)
                && this.stateOfFunctionalSystemComponent.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(BEGINNING)
                && this.stateOfFunctionalSystemComponent.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(ENDING)
                && this.stateOfFunctionalSystemComponent.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(MEMBER__OF)
                && this.stateOfFunctionalSystemComponent.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(MEMBER_OF)
                && this.stateOfFunctionalSystemComponent.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(PART__OF)
                && this.stateOfFunctionalSystemComponent.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.stateOfFunctionalSystemComponent.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(TEMPORAL__PART_OF)
                && this.stateOfFunctionalSystemComponent.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.stateOfFunctionalSystemComponent.hasValue(TEMPORAL_PART_OF)
                && this.stateOfFunctionalSystemComponent.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return stateOfFunctionalSystemComponent;
    }
}
