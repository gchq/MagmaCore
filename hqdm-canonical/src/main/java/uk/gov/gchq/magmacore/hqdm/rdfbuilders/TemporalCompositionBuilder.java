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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.WHOLE;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.TemporalComposition;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.RelationshipServices;

/**
 * Builder for constructing instances of TemporalComposition.
 */
public class TemporalCompositionBuilder {

    private final TemporalComposition temporalComposition;

    /**
     * Constructs a Builder for a new TemporalComposition.
     *
     * @param iri IRI of the TemporalComposition.
     */
    public TemporalCompositionBuilder(final IRI iri) {
        temporalComposition = RelationshipServices.createTemporalComposition(iri);
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final TemporalCompositionBuilder member__Of(final Class clazz) {
        this.temporalComposition.addValue(MEMBER__OF, clazz.getId());
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
    public final TemporalCompositionBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        this.temporalComposition.addValue(MEMBER_OF, classOfRelationship.getId());
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} has exactly one
     * {@link SpatioTemporalExtent} as the part.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final TemporalCompositionBuilder part_M(final SpatioTemporalExtent spatioTemporalExtent) {
        this.temporalComposition.addValue(PART, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A relationship type where an {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} has exactly one
     * {@link SpatioTemporalExtent} as the whole.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final TemporalCompositionBuilder whole_M(final SpatioTemporalExtent spatioTemporalExtent) {
        this.temporalComposition.addValue(WHOLE, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * Returns an instance of TemporalComposition created from the properties set on this builder.
     *
     * @return The built TemporalComposition.
     * @throws HqdmException If the TemporalComposition is missing any mandatory properties.
     */
    public TemporalComposition build() throws HqdmException {
        if (this.temporalComposition.hasValue(MEMBER__OF)
                && this.temporalComposition.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.temporalComposition.hasValue(MEMBER_OF)
                && this.temporalComposition.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.temporalComposition.hasValue(PART)) {
            throw new HqdmException("Property Not Set: part");
        }
        if (!this.temporalComposition.hasValue(WHOLE)) {
            throw new HqdmException("Property Not Set: whole");
        }
        return temporalComposition;
    }
}
