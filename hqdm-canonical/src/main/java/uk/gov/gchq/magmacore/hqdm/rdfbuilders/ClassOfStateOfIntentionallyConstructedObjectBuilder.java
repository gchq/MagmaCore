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

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfIntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of ClassOfStateOfIntentionallyConstructedObject.
 */
public class ClassOfStateOfIntentionallyConstructedObjectBuilder {

    @SuppressWarnings("LineLength")
    private final ClassOfStateOfIntentionallyConstructedObject classOfStateOfIntentionallyConstructedObject;

    /**
     * Constructs a Builder for a new ClassOfStateOfIntentionallyConstructedObject.
     *
     * @param iri IRI of the ClassOfStateOfIntentionallyConstructedObject.
     */
    public ClassOfStateOfIntentionallyConstructedObjectBuilder(final IRI iri) {
        this.classOfStateOfIntentionallyConstructedObject = ClassServices
                .createClassOfStateOfIntentionallyConstructedObject(iri);
    }

    /**
     * An inverse {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART__OF_BY_CLASS} relationship type
     * where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one
     * {@link ClassOfSpatioTemporalExtent} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF}
     * another {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a
     * {@link ClassOfSpatioTemporalExtent}.
     *
     * @param classOfSpatioTemporalExtent The ClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final ClassOfStateOfIntentionallyConstructedObjectBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.classOfStateOfIntentionallyConstructedObject.addValue(CONSISTS__OF_BY_CLASS,
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
    public final ClassOfStateOfIntentionallyConstructedObjectBuilder has_Superclass(final Class clazz) {
        this.classOfStateOfIntentionallyConstructedObject.addValue(HAS_SUPERCLASS,
                clazz.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ClassOfStateOfIntentionallyConstructedObjectBuilder member__Of(final Class clazz) {
        this.classOfStateOfIntentionallyConstructedObject.addValue(MEMBER__OF, clazz.getId());
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
    public final ClassOfStateOfIntentionallyConstructedObjectBuilder member_Of(final ClassOfClass classOfClass) {
        this.classOfStateOfIntentionallyConstructedObject.addValue(MEMBER_OF,
                classOfClass.getId());
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
    public final ClassOfStateOfIntentionallyConstructedObjectBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        this.classOfStateOfIntentionallyConstructedObject.addValue(MEMBER_OF_,
                classOfClassOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a
     * {@link ClassOfSpatioTemporalExtent} is {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} some
     * {@link ClassOfSpatioTemporalExtent}.
     *
     * @param classOfSpatioTemporalExtent The ClassOfSpatioTemporalExtent.
     * @return This builder.
     */
    public final ClassOfStateOfIntentionallyConstructedObjectBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.classOfStateOfIntentionallyConstructedObject.addValue(PART__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * Returns an instance of ClassOfStateOfIntentionallyConstructedObject created from the properties
     * set on this builder.
     *
     * @return The built ClassOfStateOfIntentionallyConstructedObject.
     * @throws HqdmException If the ClassOfStateOfIntentionallyConstructedObject is missing any
     *                       mandatory properties.
     */
    public ClassOfStateOfIntentionallyConstructedObject build() throws HqdmException {
        if (this.classOfStateOfIntentionallyConstructedObject.hasValue(HAS_SUPERCLASS)
                && this.classOfStateOfIntentionallyConstructedObject.values(HAS_SUPERCLASS)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.classOfStateOfIntentionallyConstructedObject.hasValue(MEMBER__OF)
                && this.classOfStateOfIntentionallyConstructedObject.values(MEMBER__OF)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.classOfStateOfIntentionallyConstructedObject.hasValue(MEMBER_OF)
                && this.classOfStateOfIntentionallyConstructedObject.values(MEMBER_OF)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.classOfStateOfIntentionallyConstructedObject.hasValue(MEMBER_OF_)
                && this.classOfStateOfIntentionallyConstructedObject.values(MEMBER_OF_)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (this.classOfStateOfIntentionallyConstructedObject.hasValue(PART__OF_BY_CLASS)
                && this.classOfStateOfIntentionallyConstructedObject.values(PART__OF_BY_CLASS)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        return this.classOfStateOfIntentionallyConstructedObject;
    }
}
