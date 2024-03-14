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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.NATURAL_ROLE;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.BiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfBiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.OrdinaryBiologicalObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of BiologicalSystem.
 */
public class BiologicalSystemBuilder {

    private final BiologicalSystem biologicalSystem;

    /**
     * Constructs a Builder for a new BiologicalSystem.
     *
     * @param iri IRI of the BiologicalSystem.
     */
    public BiologicalSystemBuilder(final IRI iri) {
        this.biologicalSystem = SpatioTemporalExtentServices.createBiologicalSystem(iri);
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
    public final BiologicalSystemBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystem.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final BiologicalSystemBuilder beginning(final Event event) {
        this.biologicalSystem.addValue(BEGINNING, event.getId());
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
    public final BiologicalSystemBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystem.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final BiologicalSystemBuilder ending(final Event event) {
        this.biologicalSystem.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final BiologicalSystemBuilder member__Of(final Class clazz) {
        this.biologicalSystem.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link BiologicalSystem} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * one or more {@link ClassOfBiologicalSystem}.
     *
     * @param classOfBiologicalSystem The ClassOfBiologicalSystem.
     * @return Builder
     */
    public final BiologicalSystemBuilder member_Of(final ClassOfBiologicalSystem classOfBiologicalSystem) {
        this.biologicalSystem.addValue(MEMBER_OF, classOfBiologicalSystem.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where a
     * {@link BiologicalSystem} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * one or more {@link KindOfBiologicalSystem}.
     *
     * @param kindOfBiologicalSystem The KindOfBiologicalSystem.
     * @return Builder
     */
    public final BiologicalSystemBuilder member_Of_Kind(final KindOfBiologicalSystem kindOfBiologicalSystem) {
        this.biologicalSystem.addValue(MEMBER_OF_KIND, kindOfBiologicalSystem.getId());
        this.biologicalSystem.addValue(RDFS.RDF_TYPE, kindOfBiologicalSystem.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link BiologicalSystem} has a natural {@link Role} that it plays.
     *
     * @param role The Role.
     * @return Builder
     */
    public final BiologicalSystemBuilder natural_Role_M(final Role role) {
        this.biologicalSystem.addValue(NATURAL_ROLE, role.getId());
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
    public final BiologicalSystemBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystem.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final BiologicalSystemBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.biologicalSystem.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
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
    public final BiologicalSystemBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.biologicalSystem.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrdinaryBiologicalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link OrdinaryBiologicalObject}.
     *
     * @param ordinaryBiologicalObject The OrdinaryBiologicalObject.
     * @return This builder.
     */
    public final BiologicalSystemBuilder temporal_Part_Of(
            final OrdinaryBiologicalObject ordinaryBiologicalObject) {
        this.biologicalSystem.addValue(TEMPORAL_PART_OF, ordinaryBiologicalObject.getId());
        return this;
    }

    /**
     * Returns an instance of BiologicalSystem created from the properties set on this builder.
     *
     * @return The built BiologicalSystem.
     * @throws HqdmException If the BiologicalSystem is missing any mandatory properties.
     */
    public BiologicalSystem build() throws HqdmException {
        if (this.biologicalSystem.hasValue(AGGREGATED_INTO)
                && this.biologicalSystem.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.biologicalSystem.hasValue(BEGINNING)
                && this.biologicalSystem.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.biologicalSystem.hasValue(ENDING)
                && this.biologicalSystem.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.biologicalSystem.hasValue(MEMBER__OF)
                && this.biologicalSystem.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.biologicalSystem.hasValue(MEMBER_OF)
                && this.biologicalSystem.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.biologicalSystem.hasValue(MEMBER_OF_KIND)
                && this.biologicalSystem.values(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (!this.biologicalSystem.hasValue(NATURAL_ROLE)) {
            throw new HqdmException("Property Not Set: natural_role");
        }
        if (this.biologicalSystem.hasValue(PART__OF)
                && this.biologicalSystem.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.biologicalSystem.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.biologicalSystem.hasValue(TEMPORAL__PART_OF)
                && this.biologicalSystem.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.biologicalSystem.hasValue(TEMPORAL_PART_OF)
                && this.biologicalSystem.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return this.biologicalSystem;
    }
}
