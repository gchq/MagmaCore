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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.SOLD_AS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.SUCCESSOR;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.SalesProduct;
import uk.gov.gchq.magmacore.hqdm.model.SalesProductVersion;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of SalesProductVersion.
 */
public class SalesProductVersionBuilder {

    private final SalesProductVersion salesProductVersion;

    /**
     * Constructs a Builder for a new SalesProductVersion.
     *
     * @param iri IRI of the SalesProductVersion.
     */
    public SalesProductVersionBuilder(final IRI iri) {
        salesProductVersion = SpatioTemporalExtentServices.createSalesProductVersion(iri);
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
    public final SalesProductVersionBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.salesProductVersion.addValue(CONSISTS__OF_BY_CLASS,
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
    public final SalesProductVersionBuilder has_Superclass(final Class clazz) {
        this.salesProductVersion.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SalesProductVersionBuilder member__Of(final Class clazz) {
        this.salesProductVersion.addValue(MEMBER__OF, clazz.getId());
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
    public final SalesProductVersionBuilder member_Of(final ClassOfClass classOfClass) {
        this.salesProductVersion.addValue(MEMBER_OF, classOfClass.getId());
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
    public final SalesProductVersionBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        this.salesProductVersion.addValue(MEMBER_OF_,
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
    public final SalesProductVersionBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.salesProductVersion.addValue(PART__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Specialization} where the {@link SalesProductVersion}
     * may be sold as a {@link SalesProduct}.
     *
     * @param salesProduct The SalesProduct.
     * @return This builder.
     */
    public final SalesProductVersionBuilder sold_As(final SalesProduct salesProduct) {
        this.salesProductVersion.addValue(SOLD_AS, salesProduct.getId());
        return this;
    }

    /**
     * A relationship type where a {@link SalesProductVersion} may have exactly one successor.
     *
     * @param salesProductVersion The SalesProductVersion.
     * @return This builder.
     */
    public final SalesProductVersionBuilder successor(final SalesProductVersion salesProductVersion) {
        this.salesProductVersion.addValue(SUCCESSOR, salesProductVersion.getId());
        return this;
    }

    /**
     * Returns an instance of SalesProductVersion created from the properties set on this builder.
     *
     * @return The built SalesProductVersion.
     * @throws HqdmException If the SalesProductVersion is missing any mandatory properties.
     */
    public SalesProductVersion build() throws HqdmException {
        if (this.salesProductVersion.hasValue(HAS_SUPERCLASS)
                && this.salesProductVersion.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.salesProductVersion.hasValue(MEMBER__OF)
                && this.salesProductVersion.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.salesProductVersion.hasValue(MEMBER_OF)
                && this.salesProductVersion.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.salesProductVersion.hasValue(MEMBER_OF_)
                && this.salesProductVersion.value(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (this.salesProductVersion.hasValue(PART__OF_BY_CLASS)
                && this.salesProductVersion.value(PART__OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        if (this.salesProductVersion.hasValue(SOLD_AS)
                && this.salesProductVersion.value(SOLD_AS).isEmpty()) {
            throw new HqdmException("Property Not Set: sold_as");
        }
        if (this.salesProductVersion.hasValue(SUCCESSOR)
                && this.salesProductVersion.value(SUCCESSOR).isEmpty()) {
            throw new HqdmException("Property Not Set: successor");
        }
        return salesProductVersion;
    }
}
