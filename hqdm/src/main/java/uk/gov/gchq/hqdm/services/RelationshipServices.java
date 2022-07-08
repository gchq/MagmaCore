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

package uk.gov.gchq.hqdm.services;

import uk.gov.gchq.hqdm.model.Aggregation;
import uk.gov.gchq.hqdm.model.Classification;
import uk.gov.gchq.hqdm.model.Composition;
import uk.gov.gchq.hqdm.model.DefinedRelationship;
import uk.gov.gchq.hqdm.model.Function_;
import uk.gov.gchq.hqdm.model.Relationship;
import uk.gov.gchq.hqdm.model.Scale;
import uk.gov.gchq.hqdm.model.Specialization;
import uk.gov.gchq.hqdm.model.TemporalComposition;
import uk.gov.gchq.hqdm.model.UnitOfMeasure;
import uk.gov.gchq.hqdm.model.impl.AggregationImpl;
import uk.gov.gchq.hqdm.model.impl.ClassificationImpl;
import uk.gov.gchq.hqdm.model.impl.CompositionImpl;
import uk.gov.gchq.hqdm.model.impl.DefinedRelationshipImpl;
import uk.gov.gchq.hqdm.model.impl.FunctionImpl;
import uk.gov.gchq.hqdm.model.impl.RelationshipImpl;
import uk.gov.gchq.hqdm.model.impl.ScaleImpl;
import uk.gov.gchq.hqdm.model.impl.SpecializationImpl;
import uk.gov.gchq.hqdm.model.impl.TemporalCompositionImpl;
import uk.gov.gchq.hqdm.model.impl.UnitOfMeasureImpl;

/**
 * Service for creating HQDM Relationships.
 */
public class RelationshipServices {
    /**
     * Create a Specialization with an String.
     *
     * @param id ID of the Specialization.
     * @return A Specialization instance.
     */
    public static Specialization createSpecialization(final String id) {
        return new SpecializationImpl(id);
    }

    /**
     * Create a Scale with an String.
     *
     * @param id ID of the Scale.
     * @return A Scale instance.
     */
    public static Scale createScale(final String id) {
        return new ScaleImpl(id);
    }

    /**
     * Create a UnitOfMeasure with an String.
     *
     * @param id ID of the UnitOfMeasure.
     * @return A UnitOfMeasure instance.
     */
    public static UnitOfMeasure createUnitOfMeasure(final String id) {
        return new UnitOfMeasureImpl(id);
    }

    /**
     * Create a Function_ with an String.
     *
     * @param id ID of the .
     * @return A Function_ instance.
     */
    public static Function_ createFunction(final String id) {
        return new FunctionImpl(id);
    }

    /**
     * Create a Classification with an String.
     *
     * @param id ID of the Classification.
     * @return A Classification instance.
     */
    public static Classification createClassification(final String id) {
        return new ClassificationImpl(id);
    }

    /**
     * Create a TemporalComposition with an String.
     *
     * @param id ID of the TemporalComposition.
     * @return A TemporalComposition instance.
     */
    public static TemporalComposition createTemporalComposition(final String id) {
        return new TemporalCompositionImpl(id);
    }

    /**
     * Create a Composition with an String.
     *
     * @param id ID of the Composition.
     * @return A Composition instance.
     */
    public static Composition createComposition(final String id) {
        return new CompositionImpl(id);
    }

    /**
     * Create a Aggregation with an String.
     *
     * @param id ID of the Aggregation.
     * @return A Aggregation instance.
     */
    public static Aggregation createAggregation(final String id) {
        return new AggregationImpl(id);
    }

    /**
     * Create a Relationship with an String.
     *
     * @param id ID of the Relationship.
     * @return A Relationship instance.
     */
    public static Relationship createRelationship(final String id) {
        return new RelationshipImpl(id);
    }

    /**
     * Create a DefinedRelationship with an String.
     *
     * @param id ID of the DefinedRelationship.
     * @return A DefinedRelationship instance.
     */
    public static DefinedRelationship createDefinedRelationship(final String id) {
        return new DefinedRelationshipImpl(id);
    }
}
