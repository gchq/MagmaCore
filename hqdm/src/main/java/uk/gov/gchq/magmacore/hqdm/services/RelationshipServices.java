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
    public static Specialization createSpecialization(final String id) {
        return new SpecializationImpl(id);
    }

    /**
     * Create a {@link Scale} with an String.
     *
     * @param id ID of the Scale.
     * @return A Scale instance.
     */
    public static Scale createScale(final String id) {
        return new ScaleImpl(id);
    }

    /**
     * Create a {@link UnitOfMeasure} with an String.
     *
     * @param id ID of the UnitOfMeasure.
     * @return A UnitOfMeasure instance.
     */
    public static UnitOfMeasure createUnitOfMeasure(final String id) {
        return new UnitOfMeasureImpl(id);
    }

    /**
     * Create a {@link Function_} with an String.
     *
     * @param id ID of the Function_.
     * @return A Function_ instance.
     */
    public static Function_ createFunction(final String id) {
        return new FunctionImpl(id);
    }

    /**
     * Create a {@link Classification} with an String.
     *
     * @param id ID of the Classification.
     * @return A Classification instance.
     */
    public static Classification createClassification(final String id) {
        return new ClassificationImpl(id);
    }

    /**
     * Create a {@link TemporalComposition} with an String.
     *
     * @param id ID of the TemporalComposition.
     * @return A TemporalComposition instance.
     */
    public static TemporalComposition createTemporalComposition(final String id) {
        return new TemporalCompositionImpl(id);
    }

    /**
     * Create a {@link Composition} with an String.
     *
     * @param id ID of the Composition.
     * @return A Composition instance.
     */
    public static Composition createComposition(final String id) {
        return new CompositionImpl(id);
    }

    /**
     * Create a {@link Aggregation} with an String.
     *
     * @param id ID of the Aggregation.
     * @return A Aggregation instance.
     */
    public static Aggregation createAggregation(final String id) {
        return new AggregationImpl(id);
    }

    /**
     * Create a {@link Relationship} with an String.
     *
     * @param id ID of the Relationship.
     * @return A Relationship instance.
     */
    public static Relationship createRelationship(final String id) {
        return new RelationshipImpl(id);
    }

    /**
     * Create a {@link DefinedRelationship} with an String.
     *
     * @param id ID of the DefinedRelationship.
     * @return A DefinedRelationship instance.
     */
    public static DefinedRelationship createDefinedRelationship(final String id) {
        return new DefinedRelationshipImpl(id);
    }
}
