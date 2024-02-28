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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.COMPONENT_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ENDING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.BiologicalObject;
import uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.BiologicalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of BiologicalSystemComponent.
 */
public class BiologicalSystemComponentBuilder {

    private final BiologicalSystemComponent biologicalSystemComponent;

    /**
     * Constructs a Builder for a new BiologicalSystemComponent.
     *
     * @param iri IRI of the BiologicalSystemComponent.
     */
    public BiologicalSystemComponentBuilder(final IRI iri) {
        this.biologicalSystemComponent = SpatioTemporalExtentServices.createBiologicalSystemComponent(iri);
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
    public final BiologicalSystemComponentBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystemComponent.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder beginning(final Event event) {
        this.biologicalSystemComponent.addValue(BEGINNING, event.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#COMPONENT_OF} relationship type where a
     * {@link BiologicalSystemComponent} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#COMPONENT_OF} exactly one
     * {@link BiologicalSystem}.
     *
     * @param biologicalSystem The BiologicalSystem.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder component_Of_M(final BiologicalSystem biologicalSystem) {
        this.biologicalSystemComponent.addValue(COMPONENT_OF, biologicalSystem.getId());
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
    public final BiologicalSystemComponentBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystemComponent.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder ending(final Event event) {
        this.biologicalSystemComponent.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder member__Of(final Class clazz) {
        this.biologicalSystemComponent.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link BiologicalSystemComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfBiologicalSystemComponent}.
     *
     * @param classOfBiologicalSystemComponent The ClassOfBiologicalSystemComponent.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder member_Of(
            final ClassOfBiologicalSystemComponent classOfBiologicalSystemComponent) {
        this.biologicalSystemComponent.addValue(MEMBER_OF,
                classOfBiologicalSystemComponent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link BiologicalObject} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * one or more {@link KindOfBiologicalObject}.
     *
     * @param kindOfBiologicalObject The KindOfBiologicalObject.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder member_Of_Kind(final KindOfBiologicalObject kindOfBiologicalObject) {
        this.biologicalSystemComponent.addValue(MEMBER_OF_KIND, kindOfBiologicalObject.getId());
        this.biologicalSystemComponent.addValue(RDFS.RDF_TYPE, kindOfBiologicalObject.getId());
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
    public final BiologicalSystemComponentBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystemComponent.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final BiologicalSystemComponentBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.biologicalSystemComponent.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
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
    public final BiologicalSystemComponentBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystemComponent.addValue(TEMPORAL__PART_OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfBiologicalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link BiologicalObject}.
     *
     * @param biologicalObject The BiologicalObject.
     * @return This builder.
     */
    public final BiologicalSystemComponentBuilder temporal_Part_Of(final BiologicalObject biologicalObject) {
        this.biologicalSystemComponent.addValue(TEMPORAL_PART_OF, biologicalObject.getId());
        return this;
    }

    /**
     * Returns an instance of BiologicalSystemComponent created from the properties set on this builder.
     *
     * @return The built BiologicalSystemComponent.
     * @throws HqdmException If the BiologicalSystemComponent is missing any mandatory properties.
     */
    public BiologicalSystemComponent build() throws HqdmException {
        if (this.biologicalSystemComponent.hasValue(AGGREGATED_INTO)
                && this.biologicalSystemComponent.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.biologicalSystemComponent.hasValue(BEGINNING)
                && this.biologicalSystemComponent.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!this.biologicalSystemComponent.hasValue(COMPONENT_OF)) {
            throw new HqdmException("Property Not Set: component_of");
        }
        if (this.biologicalSystemComponent.hasValue(ENDING)
                && this.biologicalSystemComponent.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.biologicalSystemComponent.hasValue(MEMBER__OF)
                && this.biologicalSystemComponent.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.biologicalSystemComponent.hasValue(MEMBER_OF)
                && this.biologicalSystemComponent.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.biologicalSystemComponent.hasValue(MEMBER_OF_KIND)
                && this.biologicalSystemComponent.values(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.biologicalSystemComponent.hasValue(PART__OF)
                && this.biologicalSystemComponent.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.biologicalSystemComponent.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.biologicalSystemComponent.hasValue(TEMPORAL__PART_OF)
                && this.biologicalSystemComponent.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.biologicalSystemComponent.hasValue(TEMPORAL_PART_OF)
                && this.biologicalSystemComponent.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return this.biologicalSystemComponent;
    }
}
