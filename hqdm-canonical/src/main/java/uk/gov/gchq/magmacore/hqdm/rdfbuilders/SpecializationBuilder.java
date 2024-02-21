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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.SUBCLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.SUPERCLASS;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.Specialization;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.RelationshipServices;

/**
 * Builder for constructing instances of Specialization.
 */
public class SpecializationBuilder {

    private final Specialization specialization;

    /**
     * Constructs a Builder for a new Specialization.
     *
     * @param iri IRI of the Specialization.
     */
    public SpecializationBuilder(final IRI iri) {
        specialization = RelationshipServices.createSpecialization(iri);
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SpecializationBuilder member__Of(final Class clazz) {
        this.specialization.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * relationship is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a
     * {@link ClassOfRelationship}.
     *
     * @param classOfRelationship The ClassOfRelationship.
     * @return This builder.
     */
    public final SpecializationBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        this.specialization.addValue(MEMBER_OF, classOfRelationship.getId());
        return this;
    }

    /**
     * A relationship type where each {@link Specialization} has exactly one {@link Class} as subclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SpecializationBuilder subclass_M(final Class clazz) {
        this.specialization.addValue(SUBCLASS, clazz.getId());
        return this;
    }

    /**
     * A relationship type where each {@link Specialization} has exactly one {@link Class} as
     * superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final SpecializationBuilder superclass_M(final Class clazz) {
        this.specialization.addValue(SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * Returns an instance of Specialization created from the properties set on this builder.
     *
     * @return The built Specialization.
     * @throws HqdmException If the Specialization is missing any mandatory properties.
     */
    public Specialization build() throws HqdmException {
        if (this.specialization.hasValue(MEMBER__OF)
                && this.specialization.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.specialization.hasValue(MEMBER_OF)
                && this.specialization.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.specialization.hasValue(SUBCLASS)) {
            throw new HqdmException("Property Not Set: subclass");
        }
        if (!this.specialization.hasValue(SUPERCLASS)) {
            throw new HqdmException("Property Not Set: superclass");
        }
        return specialization;
    }
}
