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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HAS_SUPERCLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ROLES;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of KindOfRelationshipWithSignature.
 */
public class KindOfRelationshipWithSignatureBuilder {

    private final KindOfRelationshipWithSignature kindOfRelationshipWithSignature;

    /**
     * Constructs a Builder for a new KindOfRelationshipWithSignature.
     *
     * @param iri IRI of the KindOfRelationshipWithSignature.
     */
    public KindOfRelationshipWithSignatureBuilder(final IRI iri) {
        kindOfRelationshipWithSignature = ClassServices.createKindOfRelationshipWithSignature(iri);
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfRelationshipWithSignatureBuilder has_Superclass(final Class clazz) {
        this.kindOfRelationshipWithSignature.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfRelationshipWithSignatureBuilder member__Of(final Class clazz) {
        this.kindOfRelationshipWithSignature.addValue(MEMBER__OF, clazz.getId());
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
    public final KindOfRelationshipWithSignatureBuilder member_Of(final ClassOfClass classOfClass) {
        this.kindOfRelationshipWithSignature.addValue(MEMBER_OF, classOfClass.getId());
        return this;
    }

    /**
     * The roles that must be filled by members of a {@link KindOfRelationshipWithSignature}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfRelationshipWithSignatureBuilder roles_M(final Class clazz) {
        this.kindOfRelationshipWithSignature.addValue(ROLES, clazz.getId());
        return this;
    }

    /**
     * Returns an instance of KindOfRelationshipWithSignature created from the properties set on this
     * builder.
     *
     * @return The built KindOfRelationshipWithSignature.
     * @throws HqdmException If the KindOfRelationshipWithSignature is missing any mandatory properties.
     */
    public KindOfRelationshipWithSignature build() throws HqdmException {
        if (this.kindOfRelationshipWithSignature.hasValue(HAS_SUPERCLASS)
                && this.kindOfRelationshipWithSignature.values(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.kindOfRelationshipWithSignature.hasValue(MEMBER__OF)
                && this.kindOfRelationshipWithSignature.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.kindOfRelationshipWithSignature.hasValue(MEMBER_OF)
                && this.kindOfRelationshipWithSignature.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.kindOfRelationshipWithSignature.hasValue(ROLES)) {
            throw new HqdmException("Property Not Set: roles");
        }
        return kindOfRelationshipWithSignature;
    }
}
