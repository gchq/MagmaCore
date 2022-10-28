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
import uk.gov.gchq.magmacore.hqdm.model.Aggregation;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfRelationshipServices;

/**
 * Builder for constructing instances of Aggregation.
 */
public class AggregationBuilder {

    private final Aggregation aggregation;

    /**
     * Constructs a Builder for a new Aggregation.
     *
     * @param iri IRI of the Aggregation.
     */
    public AggregationBuilder(final IRI iri) {
        aggregation = RdfRelationshipServices.createAggregation(iri.getIri());
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final AggregationBuilder member__Of(final Class clazz) {
        aggregation.addValue(MEMBER__OF, new IRI(clazz.getId()));
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
    public final AggregationBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        aggregation.addValue(MEMBER_OF, new IRI(classOfRelationship.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link Aggregation} has exactly one {@link SpatioTemporalExtent} as
     * the part.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AggregationBuilder part_M(final SpatioTemporalExtent spatioTemporalExtent) {
        aggregation.addValue(PART, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link Aggregation} has exactly one {@link SpatioTemporalExtent} as
     * the whole.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final AggregationBuilder whole_M(final SpatioTemporalExtent spatioTemporalExtent) {
        aggregation.addValue(WHOLE, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * Returns an instance of Aggregation created from the properties set on this builder.
     *
     * @return The built Aggregation.
     * @throws HqdmException If the Aggregation is missing any mandatory properties.
     */
    public Aggregation build() throws HqdmException {
        if (aggregation.hasValue(MEMBER__OF)
                && aggregation.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (aggregation.hasValue(MEMBER_OF)
                && aggregation.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!aggregation.hasValue(PART)) {
            throw new HqdmException("Property Not Set: part");
        }
        if (!aggregation.hasValue(WHOLE)) {
            throw new HqdmException("Property Not Set: whole");
        }
        return aggregation;
    }
}
