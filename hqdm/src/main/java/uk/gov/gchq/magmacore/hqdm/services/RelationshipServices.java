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

package uk.gov.gchq.magmacore.hqdm.services;

import uk.gov.gchq.magmacore.hqdm.model.Aggregation;
import uk.gov.gchq.magmacore.hqdm.model.Classification;
import uk.gov.gchq.magmacore.hqdm.model.Composition;
import uk.gov.gchq.magmacore.hqdm.model.DefinedRelationship;
import uk.gov.gchq.magmacore.hqdm.model.Function_;
import uk.gov.gchq.magmacore.hqdm.model.Relationship;
import uk.gov.gchq.magmacore.hqdm.model.Scale;
import uk.gov.gchq.magmacore.hqdm.model.Specialization;
import uk.gov.gchq.magmacore.hqdm.model.TemporalComposition;
import uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure;
import uk.gov.gchq.magmacore.hqdm.model.impl.AggregationImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.ClassificationImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.CompositionImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.DefinedRelationshipImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.FunctionImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.RelationshipImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.ScaleImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.SpecializationImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.TemporalCompositionImpl;
import uk.gov.gchq.magmacore.hqdm.model.impl.UnitOfMeasureImpl;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;

/**
 * Service for creating HQDM Relationships.
 */
public class RelationshipServices {
    /**
     * Create a {@link Specialization} with an String.
     *
     * @param id ID of the Specialization.
     * @return A Specialization instance.
     */
    public static Specialization createSpecialization(final IRI id) {
        final Specialization result = new SpecializationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SPECIALIZATION);
        return result;
    }

    /**
     * Create a {@link Scale} with an String.
     *
     * @param id ID of the Scale.
     * @return A Scale instance.
     */
    public static Scale createScale(final IRI id) {
        final Scale result = new ScaleImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.SCALE);
        return result;
    }

    /**
     * Create a {@link UnitOfMeasure} with an String.
     *
     * @param id ID of the UnitOfMeasure.
     * @return A UnitOfMeasure instance.
     */
    public static UnitOfMeasure createUnitOfMeasure(final IRI id) {
        final UnitOfMeasure result = new UnitOfMeasureImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.UNIT_OF_MEASURE);
        return result;
    }

    /**
     * Create a {@link Function_} with an String.
     *
     * @param id ID of the Function_.
     * @return A Function_ instance.
     */
    public static Function_ createFunction(final IRI id) {
        final Function_ result = new FunctionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.FUNCTION_);
        return result;
    }

    /**
     * Create a {@link Classification} with an String.
     *
     * @param id ID of the Classification.
     * @return A Classification instance.
     */
    public static Classification createClassification(final IRI id) {
        final Classification result = new ClassificationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.CLASSIFICATION);
        return result;
    }

    /**
     * Create a {@link TemporalComposition} with an String.
     *
     * @param id ID of the TemporalComposition.
     * @return A TemporalComposition instance.
     */
    public static TemporalComposition createTemporalComposition(final IRI id) {
        final TemporalComposition result = new TemporalCompositionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.TEMPORAL_COMPOSITION);
        return result;
    }

    /**
     * Create a {@link Composition} with an String.
     *
     * @param id ID of the Composition.
     * @return A Composition instance.
     */
    public static Composition createComposition(final IRI id) {
        final Composition result = new CompositionImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.COMPOSITION);
        return result;
    }

    /**
     * Create a {@link Aggregation} with an String.
     *
     * @param id ID of the Aggregation.
     * @return A Aggregation instance.
     */
    public static Aggregation createAggregation(final IRI id) {
        final Aggregation result = new AggregationImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.AGGREGATION);
        return result;
    }

    /**
     * Create a {@link Relationship} with an String.
     *
     * @param id ID of the Relationship.
     * @return A Relationship instance.
     */
    public static Relationship createRelationship(final IRI id) {
        final Relationship result = new RelationshipImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.RELATIONSHIP);
        return result;
    }

    /**
     * Create a {@link DefinedRelationship} with an String.
     *
     * @param id ID of the DefinedRelationship.
     * @return A DefinedRelationship instance.
     */
    public static DefinedRelationship createDefinedRelationship(final IRI id) {
        final DefinedRelationship result = new DefinedRelationshipImpl(id);
        result.addValue(RDFS.RDF_TYPE, HQDM.DEFINED_RELATIONSHIP);
        return result;
    }
}
