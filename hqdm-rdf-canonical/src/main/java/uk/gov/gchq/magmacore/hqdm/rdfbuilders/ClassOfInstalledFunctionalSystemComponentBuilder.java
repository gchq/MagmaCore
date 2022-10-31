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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfInstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfClassServices;

/**
 * Builder for constructing instances of ClassOfInstalledFunctionalSystemComponent.
 */
public class ClassOfInstalledFunctionalSystemComponentBuilder {

    @SuppressWarnings("LineLength")
    private final ClassOfInstalledFunctionalSystemComponent classOfInstalledFunctionalSystemComponent;

    /**
     * Constructs a Builder for a new ClassOfInstalledFunctionalSystemComponent.
     *
     * @param iri IRI of the ClassOfInstalledFunctionalSystemComponent.
     */
    public ClassOfInstalledFunctionalSystemComponentBuilder(final IRI iri) {
        classOfInstalledFunctionalSystemComponent = RdfClassServices
                .createClassOfInstalledFunctionalSystemComponent(iri.getIri());
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
    public final ClassOfInstalledFunctionalSystemComponentBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        classOfInstalledFunctionalSystemComponent.addValue(CONSISTS__OF_BY_CLASS,
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
    public final ClassOfInstalledFunctionalSystemComponentBuilder has_Superclass(final Class clazz) {
        classOfInstalledFunctionalSystemComponent.addValue(HAS_SUPERCLASS, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ClassOfInstalledFunctionalSystemComponentBuilder member__Of(final Class clazz) {
        classOfInstalledFunctionalSystemComponent.addValue(MEMBER__OF, new IRI(clazz.getId()));
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
    public final ClassOfInstalledFunctionalSystemComponentBuilder member_Of(final ClassOfClass classOfClass) {
        classOfInstalledFunctionalSystemComponent.addValue(MEMBER_OF,
                new IRI(classOfClass.getId()));
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
    public final ClassOfInstalledFunctionalSystemComponentBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        classOfInstalledFunctionalSystemComponent.addValue(MEMBER_OF_,
                new IRI(classOfClassOfSpatioTemporalExtent.getId()));
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
    public final ClassOfInstalledFunctionalSystemComponentBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        classOfInstalledFunctionalSystemComponent.addValue(PART__OF_BY_CLASS,
                new IRI(classOfSpatioTemporalExtent.getId()));
        return this;
    }

    /**
     * Returns an instance of ClassOfInstalledFunctionalSystemComponent created from the properties set
     * on this builder.
     *
     * @return The built ClassOfInstalledFunctionalSystemComponent.
     * @throws HqdmException If the ClassOfInstalledFunctionalSystemComponent is missing any mandatory
     *                       properties.
     */
    public ClassOfInstalledFunctionalSystemComponent build() throws HqdmException {
        if (classOfInstalledFunctionalSystemComponent.hasValue(HAS_SUPERCLASS)
                && classOfInstalledFunctionalSystemComponent
                        .value(HAS_SUPERCLASS)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (classOfInstalledFunctionalSystemComponent.hasValue(MEMBER__OF)
                && classOfInstalledFunctionalSystemComponent.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (classOfInstalledFunctionalSystemComponent.hasValue(MEMBER_OF)
                && classOfInstalledFunctionalSystemComponent.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (classOfInstalledFunctionalSystemComponent.hasValue(MEMBER_OF_)
                && classOfInstalledFunctionalSystemComponent.value(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (classOfInstalledFunctionalSystemComponent.hasValue(PART__OF_BY_CLASS)
                && classOfInstalledFunctionalSystemComponent
                        .value(PART__OF_BY_CLASS)
                        .isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        return classOfInstalledFunctionalSystemComponent;
    }
}