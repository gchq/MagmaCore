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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HAS_COMPONENT_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HAS_SUPERCLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.NATURAL_ROLE_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF_BY_CLASS;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfSpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalSystem;
import uk.gov.gchq.magmacore.hqdm.model.KindOfBiologicalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of KindOfBiologicalSystem.
 */
public class KindOfBiologicalSystemBuilder {

    private final KindOfBiologicalSystem kindOfBiologicalSystem;

    /**
     * Constructs a Builder for a new KindOfBiologicalSystem.
     *
     * @param iri IRI of the KindOfBiologicalSystem.
     */
    public KindOfBiologicalSystemBuilder(final IRI iri) {
        kindOfBiologicalSystem = ClassServices.createKindOfBiologicalSystem(iri);
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
    public final KindOfBiologicalSystemBuilder consists__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.kindOfBiologicalSystem.addValue(CONSISTS__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A has_component_by_class relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a {@link KindOfBiologicalSystem} has a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfBiologicalSystemComponent} as a component.
     *
     * @param kindOfBiologicalSystemComponent The KindOfBiologicalSystemComponent.
     * @return This builder.
     */
    public final KindOfBiologicalSystemBuilder has_Component_By_Class_M(
            final KindOfBiologicalSystemComponent kindOfBiologicalSystemComponent) {
        this.kindOfBiologicalSystem.addValue(HAS_COMPONENT_BY_CLASS,
                kindOfBiologicalSystemComponent.getId());
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfBiologicalSystemBuilder has_Superclass(final Class clazz) {
        this.kindOfBiologicalSystem.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfBiologicalSystemBuilder member__Of(final Class clazz) {
        this.kindOfBiologicalSystem.addValue(MEMBER__OF, clazz.getId());
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
    public final KindOfBiologicalSystemBuilder member_Of(final ClassOfClass classOfClass) {
        this.kindOfBiologicalSystem.addValue(MEMBER_OF, classOfClass.getId());
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
    public final KindOfBiologicalSystemBuilder member_Of_(
            final ClassOfClassOfSpatioTemporalExtent classOfClassOfSpatioTemporalExtent) {
        this.kindOfBiologicalSystem.addValue(MEMBER_OF_,
                classOfClassOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link KindOfBiologicalSystem} naturally participates in the {@link Role}.
     *
     * @param role The Role.
     * @return This builder.
     */
    public final KindOfBiologicalSystemBuilder natural_Role_By_Class_M(final Role role) {
        this.kindOfBiologicalSystem.addValue(NATURAL_ROLE_BY_CLASS, role.getId());
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
    public final KindOfBiologicalSystemBuilder part__Of_By_Class(
            final ClassOfSpatioTemporalExtent classOfSpatioTemporalExtent) {
        this.kindOfBiologicalSystem.addValue(PART__OF_BY_CLASS,
                classOfSpatioTemporalExtent.getId());
        return this;
    }

    /**
     * Returns an instance of KindOfBiologicalSystem created from the properties set on this builder.
     *
     * @return The built KindOfBiologicalSystem.
     * @throws HqdmException If the KindOfBiologicalSystem is missing any mandatory properties.
     */
    public KindOfBiologicalSystem build() throws HqdmException {
        if (!this.kindOfBiologicalSystem.hasValue(HAS_COMPONENT_BY_CLASS)) {
            throw new HqdmException("Property Not Set: has_component_by_class");
        }
        if (this.kindOfBiologicalSystem.hasValue(HAS_SUPERCLASS)
                && this.kindOfBiologicalSystem.values(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.kindOfBiologicalSystem.hasValue(MEMBER__OF)
                && this.kindOfBiologicalSystem.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.kindOfBiologicalSystem.hasValue(MEMBER_OF)
                && this.kindOfBiologicalSystem.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.kindOfBiologicalSystem.hasValue(MEMBER_OF_)
                && this.kindOfBiologicalSystem.values(MEMBER_OF_).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (!this.kindOfBiologicalSystem.hasValue(NATURAL_ROLE_BY_CLASS)) {
            throw new HqdmException("Property Not Set: natural_role_by_class");
        }
        if (this.kindOfBiologicalSystem.hasValue(PART__OF_BY_CLASS)
                && this.kindOfBiologicalSystem.values(PART__OF_BY_CLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of_by_class");
        }
        return kindOfBiologicalSystem;
    }
}
