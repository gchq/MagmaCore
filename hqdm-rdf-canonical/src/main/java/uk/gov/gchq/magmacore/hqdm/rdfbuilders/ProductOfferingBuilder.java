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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CLASS_OF_OFFERED;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSIDERATION_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HAS_SUPERCLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.OFFEROR;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_BY_CLASS_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PERIOD_OFFERED;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfAgreementExecution;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfReachingAgreement;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Party;
import uk.gov.gchq.magmacore.hqdm.model.PeriodOfTime;
import uk.gov.gchq.magmacore.hqdm.model.Price;
import uk.gov.gchq.magmacore.hqdm.model.ProductOffering;
import uk.gov.gchq.magmacore.hqdm.model.SalesProduct;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of ProductOffering.
 */
public class ProductOfferingBuilder {

    private final ProductOffering productOffering;

    /**
     * Constructs a Builder for a new ProductOffering.
     *
     * @param iri IRI of the ProductOffering.
     */
    public ProductOfferingBuilder(final IRI iri) {
        productOffering = SpatioTemporalExtentServices.createProductOffering(iri.getIri());
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#CLASS_OF_OFFERED} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a {@link SalesProduct} is offered.
     *
     * @param salesProduct The SalesProduct.
     * @return This builder.
     */
    public final ProductOfferingBuilder class_Of_Offered_M(final SalesProduct salesProduct) {
        productOffering.addValue(CLASS_OF_OFFERED, new IRI(salesProduct.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Offering} has exactly one
     * price at which the {@link uk.gov.gchq.magmacore.hqdm.model.Offering} is made.
     *
     * @param price The Price.
     * @return This builder.
     */
    public final ProductOfferingBuilder consideration_By_Class_M(final Price price) {
        productOffering.addValue(CONSIDERATION_BY_CLASS, new IRI(price.getId()));
        return this;
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
    public final ProductOfferingBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        productOffering.addValue(CONSISTS__OF_BY_CLASS,
                new IRI(classOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ProductOfferingBuilder has_Superclass(final Class clazz) {
        productOffering.addValue(HAS_SUPERCLASS, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ProductOfferingBuilder member__Of(final Class clazz) {
        productOffering.addValue(MEMBER__OF, new IRI(clazz.getId()));
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
    public final ProductOfferingBuilder member_Of(final ClassOfClass classOfClass) {
        productOffering.addValue(MEMBER_OF, new IRI(classOfClass.getId()));
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
    public final ProductOfferingBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        productOffering.addValue(MEMBER_OF_, new IRI(classOfClassOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Offering} has exactly one
     * {@link Party} who makes the {@link uk.gov.gchq.magmacore.hqdm.model.Offering}.
     *
     * @param party The Party.
     * @return This builder.
     */
    public final ProductOfferingBuilder offeror_M(final Party party) {
        productOffering.addValue(OFFEROR, new IRI(party.getId()));
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
    public final ProductOfferingBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        productOffering.addValue(PART__OF_BY_CLASS, new IRI(classOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF_BY_CLASS} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a {@link ClassOfReachingAgreement}.
     *
     * @param classOfReachingAgreement The ClassOfReachingAgreement.
     * @return This builder.
     */
    public final ProductOfferingBuilder part_Of_By_Class(
            final ClassOfReachingAgreement classOfReachingAgreement) {
        productOffering.addValue(PART_OF_BY_CLASS, new IRI(classOfReachingAgreement.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF_BY_CLASS} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.model.ClassOfSociallyConstructedActivity} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a
     * {@link ClassOfAgreementExecution}.
     *
     * @param classOfAgreementExecution The ClassOfAgreementExecution.
     * @return This builder.
     */
    public final ProductOfferingBuilder part_Of_By_Class_(
            final ClassOfAgreementExecution classOfAgreementExecution) {
        productOffering.addValue(PART_OF_BY_CLASS_, new IRI(classOfAgreementExecution.getId()));
        return this;
    }

    /**
     * A relationship that is exactly one {@link PeriodOfTime} for which the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Offering} is valid.
     *
     * @param periodOfTime The PeriodOfTime.
     * @return This builder.
     */
    public final ProductOfferingBuilder period_Offered_M(final PeriodOfTime periodOfTime) {
        productOffering.addValue(PERIOD_OFFERED, new IRI(periodOfTime.getId()));
        return this;
    }

    /**
     * Returns an instance of ProductOffering created from the properties set on this builder.
     *
     * @return The built ProductOffering.
     * @throws HqdmException If the ProductOffering is missing any mandatory properties.
     */
    public ProductOffering build() throws HqdmException {
        if (!productOffering.hasValue(CLASS_OF_OFFERED)) {
            throw new HqdmException("Property Not Set: class_of_offered");
        }
        if (!productOffering.hasValue(CONSIDERATION_BY_CLASS)) {
            throw new HqdmException("Property Not Set: consideration_by_class");
        }
        if (productOffering.hasValue(HAS_SUPERCLASS)
                && productOffering.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (productOffering.hasValue(MEMBER__OF)
                && productOffering.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (productOffering.hasValue(MEMBER_OF)
                && productOffering.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (productOffering.hasValue(MEMBER_OF_)
                && productOffering.value(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (!productOffering.hasValue(OFFEROR)) {
            throw new HqdmException("Property Not Set: offeror");
        }
        if (productOffering.hasValue(PART__OF_BY_CLASS)
                && productOffering.value(PART__OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        if (productOffering.hasValue(PART_OF_BY_CLASS)
                && productOffering.value(PART_OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_by_class");
        }
        if (productOffering.hasValue(PART_OF_BY_CLASS_)
                && productOffering.value(PART_OF_BY_CLASS_).isEmpty()) {
            throw new HqdmException("Property Not Set: part_of_by_class_");
        }
        if (!productOffering.hasValue(PERIOD_OFFERED)) {
            throw new HqdmException("Property Not Set: period_offered");
        }
        return productOffering;
    }
}
