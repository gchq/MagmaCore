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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.DOMAIN;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.UNIT;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalQuantity;
import uk.gov.gchq.magmacore.hqdm.model.Scale;
import uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.RelationshipServices;

/**
 * Builder for constructing instances of Scale.
 */
public class ScaleBuilder {

    private final Scale scale;

    /**
     * Constructs a Builder for a new Scale.
     *
     * @param iri IRI of the Scale.
     */
    public ScaleBuilder(final IRI iri) {
        scale = RelationshipServices.createScale(iri);
    }

    /**
     * A {@link Scale} has exactly one {@link KindOfPhysicalQuantity} as its domain.
     *
     * @param kindOfPhysicalQuantity The KindOfPhysicalQuantity.
     * @return This builder.
     */
    public final ScaleBuilder domain_M(final KindOfPhysicalQuantity kindOfPhysicalQuantity) {
        this.scale.addValue(DOMAIN, kindOfPhysicalQuantity.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ScaleBuilder member__Of(final Class clazz) {
        this.scale.addValue(MEMBER__OF, clazz.getId());
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
    public final ScaleBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        this.scale.addValue(MEMBER_OF, classOfRelationship.getId());
        return this;
    }

    /**
     * A {@link Scale} may have at most one {@link UnitOfMeasure}.
     *
     * <p>
     * Note 1: A {@link UnitOfMeasure} may apply to more than one {@link Scale}.
     * </p>
     * <p>
     * Note 2: A {@link Scale} may not have a {@link UnitOfMeasure}. To have a {@link UnitOfMeasure} the
     * points on the {@link Scale} must be evenly placed so that adding one means the same
     * {@link uk.gov.gchq.magmacore.hqdm.model.Thing}. This is not true for some {@link Scale}s such as
     * Rockwell Hardness where the points on the {@link Scale} are an arbitrary distance apart. A
     * {@link Scale} will also not have a {@link UnitOfMeasure} when it is a dimensionless
     * {@link Scale}.
     * </p>
     *
     * @param unitOfMeasure The UnitOfMeasure.
     * @return This builder.
     */
    public final ScaleBuilder unit(final UnitOfMeasure unitOfMeasure) {
        this.scale.addValue(UNIT, unitOfMeasure.getId());
        return this;
    }

    /**
     * Returns an instance of Scale created from the properties set on this builder.
     *
     * @return The built Scale.
     * @throws HqdmException If the Scale is missing any mandatory properties.
     */
    public Scale build() throws HqdmException {
        if (!this.scale.hasValue(DOMAIN)) {
            throw new HqdmException("Property Not Set: domain");
        }
        if (this.scale.hasValue(MEMBER__OF)
                && this.scale.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.scale.hasValue(MEMBER_OF)
                && this.scale.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.scale.hasValue(UNIT)
                && this.scale.value(UNIT).isEmpty()) {
            throw new HqdmException("Property Not Set: unit");
        }
        return scale;
    }
}
