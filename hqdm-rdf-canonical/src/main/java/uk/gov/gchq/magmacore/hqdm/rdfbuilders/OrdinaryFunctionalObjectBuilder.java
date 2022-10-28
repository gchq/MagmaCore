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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfOrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of OrdinaryFunctionalObject.
 */
public class OrdinaryFunctionalObjectBuilder {

    private final OrdinaryFunctionalObject ordinaryFunctionalObject;

    /**
     * Constructs a Builder for a new OrdinaryFunctionalObject.
     *
     * @param iri IRI of the OrdinaryFunctionalObject.
     */
    public OrdinaryFunctionalObjectBuilder(final IRI iri) {
        ordinaryFunctionalObject = RdfSpatioTemporalExtentServices.createOrdinaryFunctionalObject(iri.getIri());
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
    public final OrdinaryFunctionalObjectBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        ordinaryFunctionalObject.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder beginning(final Event event) {
        ordinaryFunctionalObject.addValue(BEGINNING, new IRI(event.getId()));
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
    public final OrdinaryFunctionalObjectBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        ordinaryFunctionalObject.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder ending(final Event event) {
        ordinaryFunctionalObject.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject} has one or
     * more intended {@link Role}(s).
     *
     * @param role The Role.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder intended_Role_M(final Role role) {
        ordinaryFunctionalObject.addValue(INTENDED_ROLE, new IRI(role.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder member__Of(final Class clazz) {
        ordinaryFunctionalObject.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where an
     * {@link OrdinaryFunctionalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfOrdinaryFunctionalObject}.
     *
     * @param classOfOrdinaryFunctionalObject The ClassOfOrdinaryFunctionalObject.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder member_Of(
            final ClassOfOrdinaryFunctionalObject classOfOrdinaryFunctionalObject) {
        ordinaryFunctionalObject.addValue(MEMBER_OF,
                new IRI(classOfOrdinaryFunctionalObject.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where an
     * {@link OrdinaryFunctionalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link KindOfOrdinaryFunctionalObject}.
     *
     * @param kindOfOrdinaryFunctionalObject The KindOfOrdinaryFunctionalObject.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder member_Of_Kind(
            final KindOfOrdinaryFunctionalObject kindOfOrdinaryFunctionalObject) {
        ordinaryFunctionalObject.addValue(MEMBER_OF_KIND,
                new IRI(kindOfOrdinaryFunctionalObject.getId()));
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
    public final OrdinaryFunctionalObjectBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        ordinaryFunctionalObject.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final OrdinaryFunctionalObjectBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        ordinaryFunctionalObject.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
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
    public final OrdinaryFunctionalObjectBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        ordinaryFunctionalObject.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} one or more
     * {@link Individual}.
     *
     * <p>
     * Note: The relationship is optional because an {@link Individual} is not necessarily a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} another {@link Individual},
     * yet is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as {@link Individual}. This applies to all
     * subtypes of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} that are between
     * a {@code state_of_X} and {@code X}.
     * </p>
     *
     * @param individual The Individual.
     * @return This builder.
     */
    public final OrdinaryFunctionalObjectBuilder temporal_Part_Of(final Individual individual) {
        ordinaryFunctionalObject.addValue(TEMPORAL_PART_OF, new IRI(individual.getId()));
        return this;
    }

    /**
     * Returns an instance of OrdinaryFunctionalObject created from the properties set on this builder.
     *
     * @return The built OrdinaryFunctionalObject.
     * @throws HqdmException If the OrdinaryFunctionalObject is missing any mandatory properties.
     */
    public OrdinaryFunctionalObject build() throws HqdmException {
        if (ordinaryFunctionalObject.hasValue(AGGREGATED_INTO)
                && ordinaryFunctionalObject.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (ordinaryFunctionalObject.hasValue(BEGINNING)
                && ordinaryFunctionalObject.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (ordinaryFunctionalObject.hasValue(ENDING)
                && ordinaryFunctionalObject.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (!ordinaryFunctionalObject.hasValue(INTENDED_ROLE)) {
            throw new HqdmException("Property Not Set: intended_role");
        }
        if (ordinaryFunctionalObject.hasValue(MEMBER__OF)
                && ordinaryFunctionalObject.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (ordinaryFunctionalObject.hasValue(MEMBER_OF)
                && ordinaryFunctionalObject.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (ordinaryFunctionalObject.hasValue(MEMBER_OF_KIND)
                && ordinaryFunctionalObject.value(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (ordinaryFunctionalObject.hasValue(PART__OF)
                && ordinaryFunctionalObject.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!ordinaryFunctionalObject.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (ordinaryFunctionalObject.hasValue(TEMPORAL__PART_OF)
                && ordinaryFunctionalObject.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (ordinaryFunctionalObject.hasValue(TEMPORAL_PART_OF)
                && ordinaryFunctionalObject.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return ordinaryFunctionalObject;
    }
}
