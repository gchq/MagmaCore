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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HAS_SUPERCLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.LOWER_BOUND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.RANGES_OVER;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.UPPER_BOUND;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.PhysicalProperty;
import uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity;
import uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantityRange;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of PhysicalQuantityRange.
 */
public class PhysicalQuantityRangeBuilder {

    private final PhysicalQuantityRange physicalQuantityRange;

    /**
     * Constructs a Builder for a new PhysicalQuantityRange.
     *
     * @param iri IRI of the PhysicalQuantityRange.
     */
    public PhysicalQuantityRangeBuilder(final IRI iri) {
        physicalQuantityRange = SpatioTemporalExtentServices.createPhysicalQuantityRange(iri);
    }

    /**
     * An inverse {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART__OF_BY_CLASS} relationship
     * type where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one
     * {@link ClassOfSpatioTemporalExtent}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} another
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a
     * {@link ClassOfSpatioTemporalExtent}.
     *
     * @param classOfSpatioTemporalExtent The ClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.physicalQuantityRange.addValue(CONSISTS__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder has_Superclass(final Class clazz) {
        this.physicalQuantityRange.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * A supertype_of relationship type where each {@link PhysicalQuantityRange} must have as
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#LOWER_BOUND} exactly one
     * {@link PhysicalQuantity}.
     *
     * @param physicalQuantity The PhysicalQuantity.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder lower_Bound_M(final PhysicalQuantity physicalQuantity) {
        this.physicalQuantityRange.addValue(LOWER_BOUND, physicalQuantity.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder member__Of(final Class clazz) {
        this.physicalQuantityRange.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link Class} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfClass}.
     *
     * @param classOfClass The ClassOfClass.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder member_Of(final ClassOfClass classOfClass) {
        this.physicalQuantityRange.addValue(MEMBER_OF, classOfClass.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link ClassOfSpatioTemporalExtent} may be a member of one or more
     * {@link ClassOfClassOfSpatioTemporalExtent}.
     *
     * @param classOfClassOfSpatioTemporalExtent The ClassOfClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        this.physicalQuantityRange.addValue(MEMBER_OF_,
                classOfClassOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a
     * {@link ClassOfSpatioTemporalExtent} is
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} some
     * {@link ClassOfSpatioTemporalExtent}.
     *
     * @param classOfSpatioTemporalExtent The ClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.physicalQuantityRange.addValue(PART__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A supertype_of relationship type where the members of each {@link PhysicalProperty} in the
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalPropertyRange} are members of the
     * {@link uk.gov.gchq.magmacore.hqdm.model.PhysicalPropertyRange}.
     *
     * @param physicalProperty The PhysicalProperty.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder ranges_Over_M(final PhysicalProperty physicalProperty) {
        this.physicalQuantityRange.addValue(RANGES_OVER, physicalProperty.getId());
        return this;
    }

    /**
     * A supertype_of relationship type where each {@link PhysicalQuantityRange} must have as
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#UPPER_BOUND} exactly one
     * {@link PhysicalQuantity}.
     *
     * @param physicalQuantity The PhysicalQuantity.
     * @return This builder.
     */
    public final PhysicalQuantityRangeBuilder upper_Bound_M(final PhysicalQuantity physicalQuantity) {
        this.physicalQuantityRange.addValue(UPPER_BOUND, physicalQuantity.getId());
        return this;
    }

    /**
     * Returns an instance of PhysicalQuantityRange created from the properties set on this builder.
     *
     * @return The built PhysicalQuantityRange.
     * @throws HqdmException If the PhysicalQuantityRange is missing any mandatory properties.
     */
    public PhysicalQuantityRange build() throws HqdmException {
        if (this.physicalQuantityRange.hasValue(HAS_SUPERCLASS)
                && this.physicalQuantityRange.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (!this.physicalQuantityRange.hasValue(LOWER_BOUND)) {
            throw new HqdmException("Property Not Set: lower_bound");
        }
        if (this.physicalQuantityRange.hasValue(MEMBER__OF)
                && this.physicalQuantityRange.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.physicalQuantityRange.hasValue(MEMBER_OF)
                && this.physicalQuantityRange.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.physicalQuantityRange.hasValue(MEMBER_OF_)
                && this.physicalQuantityRange.value(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (this.physicalQuantityRange.hasValue(PART__OF_BY_CLASS)
                && this.physicalQuantityRange.value(PART__OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        if (!this.physicalQuantityRange.hasValue(RANGES_OVER)) {
            throw new HqdmException("Property Not Set: ranges_over");
        }
        if (!this.physicalQuantityRange.hasValue(UPPER_BOUND)) {
            throw new HqdmException("Property Not Set: upper_bound");
        }
        return physicalQuantityRange;
    }
}
