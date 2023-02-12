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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrdinaryPhysicalObject;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.OrdinaryPhysicalObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryPhysicalObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of StateOfOrdinaryPhysicalObject.
 */
public class StateOfOrdinaryPhysicalObjectBuilder {

    private final StateOfOrdinaryPhysicalObject stateOfOrdinaryPhysicalObject;

    /**
     * Constructs a Builder for a new StateOfOrdinaryPhysicalObject.
     *
     * @param iri IRI of the StateOfOrdinaryPhysicalObject.
     */
    public StateOfOrdinaryPhysicalObjectBuilder(final IRI iri) {
        stateOfOrdinaryPhysicalObject = RdfSpatioTemporalExtentServices
                .createStateOfOrdinaryPhysicalObject(iri.getIri());
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
    public final StateOfOrdinaryPhysicalObjectBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryPhysicalObject.addValue(AGGREGATED_INTO,
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
    public final StateOfOrdinaryPhysicalObjectBuilder beginning(final Event event) {
        this.stateOfOrdinaryPhysicalObject.addValue(BEGINNING, new IRI(event.getId()));
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
    public final StateOfOrdinaryPhysicalObjectBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryPhysicalObject.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final StateOfOrdinaryPhysicalObjectBuilder ending(final Event event) {
        this.stateOfOrdinaryPhysicalObject.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final StateOfOrdinaryPhysicalObjectBuilder member__Of(final Class clazz) {
        this.stateOfOrdinaryPhysicalObject.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link StateOfOrdinaryPhysicalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfOrdinaryPhysicalObject}.
     *
     * @param classOfStateOfOrdinaryPhysicalObject The ClassOfStateOfOrdinaryPhysicalObject.
     * @return This builder.
     */
    public final StateOfOrdinaryPhysicalObjectBuilder member_Of(
            final ClassOfStateOfOrdinaryPhysicalObject classOfStateOfOrdinaryPhysicalObject) {
        this.stateOfOrdinaryPhysicalObject.addValue(MEMBER_OF,
                new IRI(classOfStateOfOrdinaryPhysicalObject.getId()));
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
    public final StateOfOrdinaryPhysicalObjectBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryPhysicalObject.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final StateOfOrdinaryPhysicalObjectBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.stateOfOrdinaryPhysicalObject.addValue(PART_OF_POSSIBLE_WORLD,
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
    public final StateOfOrdinaryPhysicalObjectBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.stateOfOrdinaryPhysicalObject.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link StateOfOrdinaryPhysicalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link OrdinaryPhysicalObject}.
     *
     * @param ordinaryPhysicalObject The OrdinaryPhysicalObject.
     * @return This builder.
     */
    public final StateOfOrdinaryPhysicalObjectBuilder temporal_Part_Of(
            final OrdinaryPhysicalObject ordinaryPhysicalObject) {
        this.stateOfOrdinaryPhysicalObject.addValue(TEMPORAL_PART_OF,
                new IRI(ordinaryPhysicalObject.getId()));
        return this;
    }

    /**
     * Returns an instance of StateOfOrdinaryPhysicalObject created from the properties set on this
     * builder.
     *
     * @return The built StateOfOrdinaryPhysicalObject.
     * @throws HqdmException If the StateOfOrdinaryPhysicalObject is missing any mandatory properties.
     */
    public StateOfOrdinaryPhysicalObject build() throws HqdmException {
        if (this.stateOfOrdinaryPhysicalObject.hasValue(AGGREGATED_INTO)
                && this.stateOfOrdinaryPhysicalObject.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(BEGINNING)
                && this.stateOfOrdinaryPhysicalObject.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(ENDING)
                && this.stateOfOrdinaryPhysicalObject.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(MEMBER__OF)
                && this.stateOfOrdinaryPhysicalObject.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(MEMBER_OF)
                && this.stateOfOrdinaryPhysicalObject.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(PART__OF)
                && this.stateOfOrdinaryPhysicalObject.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.stateOfOrdinaryPhysicalObject.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(TEMPORAL__PART_OF)
                && this.stateOfOrdinaryPhysicalObject.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.stateOfOrdinaryPhysicalObject.hasValue(TEMPORAL_PART_OF)
                && this.stateOfOrdinaryPhysicalObject.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return stateOfOrdinaryPhysicalObject;
    }
}
