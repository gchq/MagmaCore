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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.INTENDED_ROLE;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfFunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.KindOfFunctionalSystem;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.System;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of FunctionalSystem.
 */
public class FunctionalSystemBuilder {

    private final FunctionalSystem functionalSystem;

    /**
     * Constructs a Builder for a new FunctionalSystem.
     *
     * @param iri IRI of the FunctionalSystem.
     */
    public FunctionalSystemBuilder(final IRI iri) {
        functionalSystem = RdfSpatioTemporalExtentServices.createFunctionalSystem(iri);
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
    public final FunctionalSystemBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.functionalSystem.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final FunctionalSystemBuilder beginning(final Event event) {
        this.functionalSystem.addValue(BEGINNING, new IRI(event.getId()));
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
    public final FunctionalSystemBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.functionalSystem.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final FunctionalSystemBuilder ending(final Event event) {
        this.functionalSystem.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject} has one or
     * more intended {@link Role}(s).
     *
     * @param role The Role.
     * @return This builder.
     */
    public final FunctionalSystemBuilder intended_Role_M(final Role role) {
        this.functionalSystem.addValue(INTENDED_ROLE, new IRI(role.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final FunctionalSystemBuilder member__Of(final Class clazz) {
        this.functionalSystem.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link FunctionalSystem} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * one or more {@link ClassOfFunctionalSystem}.
     *
     * @param classOfFunctionalSystem The ClassOfFunctionalSystem.
     * @return This builder.
     */
    public final FunctionalSystemBuilder member_Of(final ClassOfFunctionalSystem classOfFunctionalSystem) {
        this.functionalSystem.addValue(MEMBER_OF, new IRI(classOfFunctionalSystem.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where a
     * {@link FunctionalSystem} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * one or more {@link KindOfFunctionalSystem}.
     *
     * @param kindOfFunctionalSystem The KindOfFunctionalSystem.
     * @return This builder.
     */
    public final FunctionalSystemBuilder member_Of_Kind(final KindOfFunctionalSystem kindOfFunctionalSystem) {
        this.functionalSystem.addValue(MEMBER_OF_KIND, new IRI(kindOfFunctionalSystem.getId()));
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
    public final FunctionalSystemBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.functionalSystem.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final FunctionalSystemBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.functionalSystem.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
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
    public final FunctionalSystemBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.functionalSystem.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSystem} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more {@link System}.
     *
     * @param system The System.
     * @return This builder.
     */
    public final FunctionalSystemBuilder temporal_Part_Of(final System system) {
        this.functionalSystem.addValue(TEMPORAL_PART_OF, new IRI(system.getId()));
        return this;
    }

    /**
     * Returns an instance of FunctionalSystem created from the properties set on this builder.
     *
     * @return The built FunctionalSystem.
     * @throws HqdmException If the FunctionalSystem is missing any mandatory properties.
     */
    public FunctionalSystem build() throws HqdmException {
        if (this.functionalSystem.hasValue(AGGREGATED_INTO)
                && this.functionalSystem.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.functionalSystem.hasValue(BEGINNING)
                && this.functionalSystem.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.functionalSystem.hasValue(ENDING)
                && this.functionalSystem.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (!this.functionalSystem.hasValue(INTENDED_ROLE)) {
            throw new HqdmException("Property Not Set: intended_role");
        }
        if (this.functionalSystem.hasValue(MEMBER__OF)
                && this.functionalSystem.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.functionalSystem.hasValue(MEMBER_OF)
                && this.functionalSystem.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.functionalSystem.hasValue(MEMBER_OF_KIND)
                && this.functionalSystem.value(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.functionalSystem.hasValue(PART__OF)
                && this.functionalSystem.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.functionalSystem.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.functionalSystem.hasValue(TEMPORAL__PART_OF)
                && this.functionalSystem.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.functionalSystem.hasValue(TEMPORAL_PART_OF)
                && this.functionalSystem.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return functionalSystem;
    }
}
