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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.INVOLVES;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.Classification;
import uk.gov.gchq.magmacore.hqdm.model.DefinedRelationship;
import uk.gov.gchq.magmacore.hqdm.model.KindOfRelationshipWithSignature;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.RelationshipServices;

/**
 * Builder for constructing instances of DefinedRelationship.
 */
public class DefinedRelationshipBuilder {

    private final DefinedRelationship definedRelationship;

    /**
     * Constructs a Builder for a new DefinedRelationship.
     *
     * @param iri IRI of the DefinedRelationship.
     */
    public DefinedRelationshipBuilder(final IRI iri) {
        definedRelationship = RelationshipServices.createDefinedRelationship(iri.getIri());
    }

    /**
     * A meta-relationship type where the {@link Classification} of some
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing} in a role is involved in a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Relationship}.
     *
     * @param classification The Classification.
     * @return This builder.
     */
    public final DefinedRelationshipBuilder involves_M(final Classification classification) {
        definedRelationship.addValue(INVOLVES, new IRI(classification.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final DefinedRelationshipBuilder member__Of(final Class clazz) {
        definedRelationship.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * relationship is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} a
     * {@link ClassOfRelationship}.
     *
     * @param classOfRelationship The ClassOfRelationship.
     * @return This builder.
     */
    public final DefinedRelationshipBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        definedRelationship.addValue(MEMBER_OF, new IRI(classOfRelationship.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where each
     * {@link DefinedRelationship} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF}
     * exactly one {@link KindOfRelationshipWithSignature}.
     *
     * @param kindOfRelationshipWithSignature The KindOfRelationshipWithSignature.
     * @return This builder.
     */
    public final DefinedRelationshipBuilder member_Of_Kind_M(
            final KindOfRelationshipWithSignature kindOfRelationshipWithSignature) {
        definedRelationship.addValue(MEMBER_OF_KIND,
                new IRI(kindOfRelationshipWithSignature.getId()));
        return this;
    }

    /**
     * Returns an instance of DefinedRelationship created from the properties set on this builder.
     *
     * @return The built DefinedRelationship.
     * @throws HqdmException If the DefinedRelationship is missing any mandatory properties.
     */
    public DefinedRelationship build() throws HqdmException {
        if (!definedRelationship.hasValue(INVOLVES)) {
            throw new HqdmException("Property Not Set: involves");
        }
        if (definedRelationship.hasValue(MEMBER__OF)
                && definedRelationship.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (definedRelationship.hasValue(MEMBER_OF)
                && definedRelationship.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!definedRelationship.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        return definedRelationship;
    }
}
