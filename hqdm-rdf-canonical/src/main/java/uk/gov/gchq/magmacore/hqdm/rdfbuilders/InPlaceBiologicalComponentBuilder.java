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
import uk.gov.gchq.magmacore.hqdm.model.BiologicalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfInPlaceBiologicalComponent;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.InPlaceBiologicalComponent;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of InPlaceBiologicalComponent.
 */
public class InPlaceBiologicalComponentBuilder {

    private final InPlaceBiologicalComponent inPlaceBiologicalComponent;

    /**
     * Constructs a Builder for a new InPlaceBiologicalComponent.
     *
     * @param iri IRI of the InPlaceBiologicalComponent.
     */
    public InPlaceBiologicalComponentBuilder(final IRI iri) {
        inPlaceBiologicalComponent = RdfSpatioTemporalExtentServices.createInPlaceBiologicalComponent(iri.getIri());
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
    public final InPlaceBiologicalComponentBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        inPlaceBiologicalComponent.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final InPlaceBiologicalComponentBuilder beginning(final Event event) {
        inPlaceBiologicalComponent.addValue(BEGINNING, new IRI(event.getId()));
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
    public final InPlaceBiologicalComponentBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        inPlaceBiologicalComponent.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final InPlaceBiologicalComponentBuilder ending(final Event event) {
        inPlaceBiologicalComponent.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final InPlaceBiologicalComponentBuilder member__Of(final Class clazz) {
        inPlaceBiologicalComponent.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link InPlaceBiologicalComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfInPlaceBiologicalComponent}.
     *
     * @param classOfInPlaceBiologicalComponent The ClassOfInPlaceBiologicalComponent.
     * @return This builder.
     */
    public final InPlaceBiologicalComponentBuilder member_Of(
            final ClassOfInPlaceBiologicalComponent classOfInPlaceBiologicalComponent) {
        inPlaceBiologicalComponent.addValue(MEMBER_OF,
                new IRI(classOfInPlaceBiologicalComponent.getId()));
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
    public final InPlaceBiologicalComponentBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        inPlaceBiologicalComponent.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final InPlaceBiologicalComponentBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        inPlaceBiologicalComponent.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
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
    public final InPlaceBiologicalComponentBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        inPlaceBiologicalComponent.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalSystemComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link BiologicalSystemComponent}.
     *
     * @param biologicalSystemComponent The BiologicalSystemComponent.
     * @return This builder.
     */
    public final InPlaceBiologicalComponentBuilder temporal_Part_Of(
            final BiologicalSystemComponent biologicalSystemComponent) {
        inPlaceBiologicalComponent.addValue(TEMPORAL_PART_OF,
                new IRI(biologicalSystemComponent.getId()));
        return this;
    }

    /**
     * Returns an instance of InPlaceBiologicalComponent created from the properties set on this
     * builder.
     *
     * @return The built InPlaceBiologicalComponent.
     * @throws HqdmException If the InPlaceBiologicalComponent is missing any mandatory properties.
     */
    public InPlaceBiologicalComponent build() throws HqdmException {
        if (inPlaceBiologicalComponent.hasValue(AGGREGATED_INTO)
                && inPlaceBiologicalComponent.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (inPlaceBiologicalComponent.hasValue(BEGINNING)
                && inPlaceBiologicalComponent.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (inPlaceBiologicalComponent.hasValue(ENDING)
                && inPlaceBiologicalComponent.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (inPlaceBiologicalComponent.hasValue(MEMBER__OF)
                && inPlaceBiologicalComponent.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (inPlaceBiologicalComponent.hasValue(MEMBER_OF)
                && inPlaceBiologicalComponent.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (inPlaceBiologicalComponent.hasValue(PART__OF)
                && inPlaceBiologicalComponent.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!inPlaceBiologicalComponent.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (inPlaceBiologicalComponent.hasValue(TEMPORAL__PART_OF)
                && inPlaceBiologicalComponent.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (inPlaceBiologicalComponent.hasValue(TEMPORAL_PART_OF)
                && inPlaceBiologicalComponent.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return inPlaceBiologicalComponent;
    }
}
