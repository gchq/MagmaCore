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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfParty;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of ClassOfStateOfParty.
 */
public class ClassOfStateOfPartyBuilder {

    private final ClassOfStateOfParty classOfStateOfParty;

    /**
     * Constructs a Builder for a new ClassOfStateOfParty.
     *
     * @param iri IRI of the ClassOfStateOfParty.
     */
    public ClassOfStateOfPartyBuilder(final IRI iri) {
        this.classOfStateOfParty = ClassServices.createClassOfStateOfParty(iri);
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
    public final ClassOfStateOfPartyBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.classOfStateOfParty.addValue(CONSISTS__OF_BY_CLASS,
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
    public final ClassOfStateOfPartyBuilder has_Superclass(final Class clazz) {
        this.classOfStateOfParty.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ClassOfStateOfPartyBuilder member__Of(final Class clazz) {
        this.classOfStateOfParty.addValue(MEMBER__OF, clazz.getId());
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
    public final ClassOfStateOfPartyBuilder member_Of(final ClassOfClass classOfClass) {
        this.classOfStateOfParty.addValue(MEMBER_OF, classOfClass.getId());
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
    public final ClassOfStateOfPartyBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        this.classOfStateOfParty.addValue(MEMBER_OF_,
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
    public final ClassOfStateOfPartyBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.classOfStateOfParty.addValue(PART__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * Returns an instance of ClassOfStateOfParty created from the properties set on this builder.
     *
     * @return The built ClassOfStateOfParty.
     * @throws HqdmException If the ClassOfStateOfParty is missing any mandatory properties.
     */
    public ClassOfStateOfParty build() throws HqdmException {
        if (this.classOfStateOfParty.hasValue(HAS_SUPERCLASS)
                && this.classOfStateOfParty.values(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.classOfStateOfParty.hasValue(MEMBER__OF)
                && this.classOfStateOfParty.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.classOfStateOfParty.hasValue(MEMBER_OF)
                && this.classOfStateOfParty.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.classOfStateOfParty.hasValue(MEMBER_OF_)
                && this.classOfStateOfParty.values(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (this.classOfStateOfParty.hasValue(PART__OF_BY_CLASS)
                && this.classOfStateOfParty.values(PART__OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        return this.classOfStateOfParty;
    }
}
