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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.REQUIRED_ROLE_PLAYER;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ROLES;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.Classification;
import uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithRestriction;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of KindOfRelationshipWithRestriction.
 */
public class KindOfRelationshipWithRestrictionBuilder {

    private final KindOfRelationshipWithRestriction kindOfRelationshipWithRestriction;

    /**
     * Constructs a Builder for a new KindOfRelationshipWithRestriction.
     *
     * @param iri IRI of the KindOfRelationshipWithRestriction.
     */
    public KindOfRelationshipWithRestrictionBuilder(final IRI iri) {
        kindOfRelationshipWithRestriction = ClassServices.createKindOfRelationshipWithRestriction(iri);
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfRelationshipWithRestrictionBuilder has_Superclass(final Class clazz) {
        this.kindOfRelationshipWithRestriction.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfRelationshipWithRestrictionBuilder member__Of(final Class clazz) {
        this.kindOfRelationshipWithRestriction.addValue(MEMBER__OF, clazz.getId());
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
    public final KindOfRelationshipWithRestrictionBuilder member_Of(final ClassOfClass classOfClass) {
        this.kindOfRelationshipWithRestriction.addValue(MEMBER_OF, classOfClass.getId());
        return this;
    }

    /**
     * A relationship type where the {@link Classification} is of a required role player for the members
     * of a {@link KindOfRelationshipWithRestriction}.
     *
     * @param classification The Classification.
     * @return This builder.
     */
    public final KindOfRelationshipWithRestrictionBuilder required_Role_Player_M(final Classification classification) {
        this.kindOfRelationshipWithRestriction.addValue(REQUIRED_ROLE_PLAYER,
                classification.getId());
        return this;
    }

    /**
     * The roles that must be filled by members of a
     * {@link uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfRelationshipWithRestrictionBuilder roles_M(final Class clazz) {
        this.kindOfRelationshipWithRestriction.addValue(ROLES, clazz.getId());
        return this;
    }

    /**
     * Returns an instance of KindOfRelationshipWithRestriction created from the properties set on this
     * builder.
     *
     * @return The built KindOfRelationshipWithRestriction.
     * @throws HqdmException If the KindOfRelationshipWithRestriction is missing any mandatory
     *                       properties.
     */
    public KindOfRelationshipWithRestriction build() throws HqdmException {
        if (this.kindOfRelationshipWithRestriction.hasValue(HAS_SUPERCLASS)
                && this.kindOfRelationshipWithRestriction.values(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.kindOfRelationshipWithRestriction.hasValue(MEMBER__OF)
                && this.kindOfRelationshipWithRestriction.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.kindOfRelationshipWithRestriction.hasValue(MEMBER_OF)
                && this.kindOfRelationshipWithRestriction.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.kindOfRelationshipWithRestriction.hasValue(REQUIRED_ROLE_PLAYER)) {
            throw new HqdmException("Property Not Set: required_role_player");
        }
        if (!this.kindOfRelationshipWithRestriction.hasValue(ROLES)) {
            throw new HqdmException("Property Not Set: roles");
        }
        return kindOfRelationshipWithRestriction;
    }
}
