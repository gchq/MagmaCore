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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSalesProductInstance;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.KindOfOrdinaryFunctionalObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.SalesProductInstance;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of SalesProductInstance.
 */
public class SalesProductInstanceBuilder {

    private final SalesProductInstance salesProductInstance;

    /**
     * Constructs a Builder for a new SalesProductInstance.
     *
     * @param iri IRI of the SalesProductInstance.
     */
    public SalesProductInstanceBuilder(final IRI iri) {
        salesProductInstance = SpatioTemporalExtentServices.createSalesProductInstance(iri);
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
    public final SalesProductInstanceBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.salesProductInstance.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder beginning(final Event event) {
        this.salesProductInstance.addValue(BEGINNING, event.getId());
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
    public final SalesProductInstanceBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.salesProductInstance.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder ending(final Event event) {
        this.salesProductInstance.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.FunctionalObject} has one or
     * more intended {@link Role}(s).
     *
     * @param role The Role.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder intended_Role_M(final Role role) {
        this.salesProductInstance.addValue(INTENDED_ROLE, role.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder member__Of(final Class clazz) {
        this.salesProductInstance.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link SalesProductInstance} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfSalesProductInstance}.
     *
     * @param classOfSalesProductInstance The ClassOfSalesProductInstance.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder member_Of(
            final ClassOfSalesProductInstance classOfSalesProductInstance) {
        this.salesProductInstance.addValue(MEMBER_OF, classOfSalesProductInstance.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.OrdinaryFunctionalObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfOrdinaryFunctionalObject}.
     *
     * @param kindOfOrdinaryFunctionalObject The KindOfOrdinaryFunctionalObject.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder member_Of_Kind(
            final KindOfOrdinaryFunctionalObject kindOfOrdinaryFunctionalObject) {
        this.salesProductInstance.addValue(MEMBER_OF_KIND,
                kindOfOrdinaryFunctionalObject.getId());
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
    public final SalesProductInstanceBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.salesProductInstance.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final SalesProductInstanceBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.salesProductInstance.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
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
    public final SalesProductInstanceBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.salesProductInstance.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfSalesProductInstance} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link SalesProductInstance}.
     *
     * @param salesProductInstance The SalesProductInstance.
     * @return This builder.
     */
    public final SalesProductInstanceBuilder temporal_Part_Of(final SalesProductInstance salesProductInstance) {
        this.salesProductInstance.addValue(TEMPORAL_PART_OF, salesProductInstance.getId());
        return this;
    }

    /**
     * Returns an instance of SalesProductInstance created from the properties set on this builder.
     *
     * @return The built SalesProductInstance.
     * @throws HqdmException If the SalesProductInstance is missing any mandatory properties.
     */
    public SalesProductInstance build() throws HqdmException {
        if (this.salesProductInstance.hasValue(AGGREGATED_INTO)
                && this.salesProductInstance.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.salesProductInstance.hasValue(BEGINNING)
                && this.salesProductInstance.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.salesProductInstance.hasValue(ENDING)
                && this.salesProductInstance.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (!this.salesProductInstance.hasValue(INTENDED_ROLE)) {
            throw new HqdmException("Property Not Set: intended_role");
        }
        if (this.salesProductInstance.hasValue(MEMBER__OF)
                && this.salesProductInstance.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.salesProductInstance.hasValue(MEMBER_OF)
                && this.salesProductInstance.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.salesProductInstance.hasValue(MEMBER_OF_KIND)
                && this.salesProductInstance.value(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.salesProductInstance.hasValue(PART__OF)
                && this.salesProductInstance.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.salesProductInstance.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.salesProductInstance.hasValue(TEMPORAL__PART_OF)
                && this.salesProductInstance.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.salesProductInstance.hasValue(TEMPORAL_PART_OF)
                && this.salesProductInstance.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return salesProductInstance;
    }
}
