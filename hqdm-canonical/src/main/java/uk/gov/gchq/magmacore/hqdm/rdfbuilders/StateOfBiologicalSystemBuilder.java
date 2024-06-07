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
import uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfBiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of StateOfBiologicalSystem.
 */
public class StateOfBiologicalSystemBuilder {

    private final StateOfBiologicalSystem stateOfBiologicalSystem;

    /**
     * Constructs a Builder for a new StateOfBiologicalSystem.
     *
     * @param iri IRI of the StateOfBiologicalSystem.
     */
    public StateOfBiologicalSystemBuilder(final IRI iri) {
        stateOfBiologicalSystem = SpatioTemporalExtentServices.createStateOfBiologicalSystem(iri);
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
    public final StateOfBiologicalSystemBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfBiologicalSystem.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfBiologicalSystemBuilder beginning(final Event event) {
        this.stateOfBiologicalSystem.addValue(BEGINNING, event.getId());
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
    public final StateOfBiologicalSystemBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfBiologicalSystem.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfBiologicalSystemBuilder ending(final Event event) {
        this.stateOfBiologicalSystem.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final StateOfBiologicalSystemBuilder member__Of(final Class clazz) {
        this.stateOfBiologicalSystem.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link StateOfBiologicalSystem} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfBiologicalSystem}.
     *
     * @param classOfStateOfBiologicalSystem The ClassOfStateOfBiologicalSystem.
     * @return This builder.
     */
    public final StateOfBiologicalSystemBuilder member_Of(
            final ClassOfStateOfBiologicalSystem classOfStateOfBiologicalSystem) {
        this.stateOfBiologicalSystem.addValue(MEMBER_OF,
                classOfStateOfBiologicalSystem.getId());
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
    public final StateOfBiologicalSystemBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfBiologicalSystem.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final StateOfBiologicalSystemBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.stateOfBiologicalSystem.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
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
    public final StateOfBiologicalSystemBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfBiologicalSystem.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link StateOfBiologicalSystem} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link BiologicalSystem}.
     *
     * @param biologicalSystem The BiologicalSystem.
     * @return This builder.
     */
    public final StateOfBiologicalSystemBuilder temporal_Part_Of(final BiologicalSystem biologicalSystem) {
        this.stateOfBiologicalSystem.addValue(TEMPORAL_PART_OF, biologicalSystem.getId());
        return this;
    }

    /**
     * Returns an instance of StateOfBiologicalSystem created from the properties set on this builder.
     *
     * @return The built StateOfBiologicalSystem.
     * @throws HqdmException If the StateOfBiologicalSystem is missing any mandatory properties.
     */
    public StateOfBiologicalSystem build() throws HqdmException {
        if (this.stateOfBiologicalSystem.hasValue(AGGREGATED_INTO)
                && this.stateOfBiologicalSystem.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.stateOfBiologicalSystem.hasValue(BEGINNING)
                && this.stateOfBiologicalSystem.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.stateOfBiologicalSystem.hasValue(ENDING)
                && this.stateOfBiologicalSystem.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.stateOfBiologicalSystem.hasValue(MEMBER__OF)
                && this.stateOfBiologicalSystem.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.stateOfBiologicalSystem.hasValue(MEMBER_OF)
                && this.stateOfBiologicalSystem.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.stateOfBiologicalSystem.hasValue(PART__OF)
                && this.stateOfBiologicalSystem.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.stateOfBiologicalSystem.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.stateOfBiologicalSystem.hasValue(TEMPORAL__PART_OF)
                && this.stateOfBiologicalSystem.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.stateOfBiologicalSystem.hasValue(TEMPORAL_PART_OF)
                && this.stateOfBiologicalSystem.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return stateOfBiologicalSystem;
    }
}
