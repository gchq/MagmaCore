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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEETS_SPECIFICATION;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.SOLD_UNDER;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ProductBrand;
import uk.gov.gchq.magmacore.hqdm.model.RequirementSpecification;
import uk.gov.gchq.magmacore.hqdm.model.SalesProduct;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of SalesProduct.
 */
public class SalesProductBuilder {

    private final SalesProduct salesProduct;

    /**
     * Constructs a Builder for a new SalesProduct.
     *
     * @param iri IRI of the SalesProduct.
     */
    public SalesProductBuilder(final IRI iri) {
        salesProduct = RdfSpatioTemporalExtentServices.createSalesProduct(iri.getIri());
    }

    /**
     * An inverse {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART__OF_BY_CLASS} relationship
     * type where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one
     * {@link ClassOfSpatioTemporalExtent}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CONSISTS_OF} another
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a
     * {@link ClassOfSpatioTemporalExtent}.
     *
     * @param classOfSpatioTemporalExtent The ClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final SalesProductBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        salesProduct.addValue(CONSISTS__OF_BY_CLASS, new IRI(classOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SalesProductBuilder has_Superclass(final Class clazz) {
        salesProduct.addValue(HAS_SUPERCLASS, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A subclass_of relationship type where when a {@link SalesProduct}
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEETS_SPECIFICATION} of a
     * {@link RequirementSpecification}, each
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a {@link SalesProduct} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the
     * {@link RequirementSpecification}.
     *
     * @param requirementSpecification The RequirementSpecification.
     * @return This builder.
     */
    public final SalesProductBuilder meets_Specification(
            final RequirementSpecification requirementSpecification) {
        salesProduct.addValue(MEETS_SPECIFICATION, new IRI(requirementSpecification.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SalesProductBuilder member__Of(final Class clazz) {
        salesProduct.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link Class} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfClass}.
     *
     * @param classOfClass The ClassOfClass.
     * @return This builder.
     */
    public final SalesProductBuilder member_Of(final ClassOfClass classOfClass) {
        salesProduct.addValue(MEMBER_OF, new IRI(classOfClass.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link ClassOfSpatioTemporalExtent} may be a member of one or more
     * {@link ClassOfClassOfSpatioTemporalExtent}.
     *
     * @param classOfClassOfSpatioTemporalExtent The ClassOfClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final SalesProductBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        salesProduct.addValue(MEMBER_OF_, new IRI(classOfClassOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a
     * {@link ClassOfSpatioTemporalExtent} is
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} some
     * {@link ClassOfSpatioTemporalExtent}.
     *
     * @param classOfSpatioTemporalExtent The ClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final SalesProductBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        salesProduct.addValue(PART__OF_BY_CLASS, new IRI(classOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.model.Specialization} where the {@link SalesProduct} is sold
     * under a {@link ProductBrand}.
     *
     * @param productBrand The ProductBrand.
     * @return This builder.
     */
    public final SalesProductBuilder sold_Under(final ProductBrand productBrand) {
        salesProduct.addValue(SOLD_UNDER, new IRI(productBrand.getId()));
        return this;
    }

    /**
     * Returns an instance of SalesProduct created from the properties set on this builder.
     *
     * @return The built SalesProduct.
     * @throws HqdmException If the SalesProduct is missing any mandatory properties.
     */
    public SalesProduct build() throws HqdmException {
        if (salesProduct.hasValue(HAS_SUPERCLASS)
                && salesProduct.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (salesProduct.hasValue(MEETS_SPECIFICATION)
                && salesProduct.value(MEETS_SPECIFICATION).isEmpty()) {
            throw new HqdmException("Property Not Set: meets_specification");
        }
        if (salesProduct.hasValue(MEMBER__OF)
                && salesProduct.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (salesProduct.hasValue(MEMBER_OF)
                && salesProduct.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (salesProduct.hasValue(MEMBER_OF_)
                && salesProduct.value(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (salesProduct.hasValue(PART__OF_BY_CLASS)
                && salesProduct.value(PART__OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        if (salesProduct.hasValue(SOLD_UNDER)
                && salesProduct.value(SOLD_UNDER).isEmpty()) {
            throw new HqdmException("Property Not Set: sold_under");
        }
        return salesProduct;
    }
}
