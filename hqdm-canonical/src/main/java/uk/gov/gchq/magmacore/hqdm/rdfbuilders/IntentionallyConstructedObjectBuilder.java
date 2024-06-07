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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.AGGREGATED_INTO;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.BEGINNING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ENDING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfIntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.IntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.KindOfIntentionallyConstructedObject;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of IntentionallyConstructedObject.
 */
public class IntentionallyConstructedObjectBuilder {

    private final IntentionallyConstructedObject intentionallyConstructedObject;

    /**
     * Constructs a Builder for a new IntentionallyConstructedObject.
     *
     * @param iri IRI of the IntentionallyConstructedObject.
     */
    public IntentionallyConstructedObjectBuilder(final IRI iri) {
        intentionallyConstructedObject = SpatioTemporalExtentServices
                .createIntentionallyConstructedObject(iri);
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may be aggregated into one or more
     * others.
     * <p>
     * Note: This has the same meaning as, but different representation to, the
     * {@link uk.gov.gchq.magmacore.hqdm.model.Aggregation} entity type.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder aggregated_Into(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.intentionallyConstructedObject.addValue(AGGREGATED_INTO,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder beginning(final Event event) {
        this.intentionallyConstructedObject.addValue(BEGINNING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may consist of one or more others.
     *
     * <p>
     * Note: This is the inverse of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART__OF}.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.intentionallyConstructedObject.addValue(CONSISTS__OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder ending(final Event event) {
        this.intentionallyConstructedObject.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder member__Of(final Class clazz) {
        this.intentionallyConstructedObject.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link IntentionallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfIntentionallyConstructedObject}.
     *
     * @param classOfIntentionallyConstructedObject The ClassOfIntentionallyConstructedObject.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder member_Of(
            final ClassOfIntentionallyConstructedObject classOfIntentionallyConstructedObject) {
        this.intentionallyConstructedObject.addValue(MEMBER_OF,
                classOfIntentionallyConstructedObject.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where an
     * {@link IntentionallyConstructedObject} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfIntentionallyConstructedObject}.
     *
     * @param kindOfIntentionallyConstructedObject The KindOfIntentionallyConstructedObject.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder member_Of_Kind(
            final KindOfIntentionallyConstructedObject kindOfIntentionallyConstructedObject) {
        this.intentionallyConstructedObject.addValue(MEMBER_OF_KIND, kindOfIntentionallyConstructedObject.getId());
        this.intentionallyConstructedObject.addValue(RDFS.RDF_TYPE, kindOfIntentionallyConstructedObject.getId());
        return this;
    }

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#AGGREGATED_INTO} relationship type where a
     * {@link SpatioTemporalExtent} may be part of another and the whole has emergent properties and is
     * more than just the sum of its parts.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.intentionallyConstructedObject.addValue(PART__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} one
     * or more {@link PossibleWorld}.
     *
     * <p>
     * Note: The relationship is optional because a {@link PossibleWorld} is not
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} any other {@link SpatioTemporalExtent}.
     * </p>
     *
     * @param possibleWorld The PossibleWorld.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.intentionallyConstructedObject.addValue(PART_OF_POSSIBLE_WORLD,
                possibleWorld.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be a temporal part of one or more other
     * {@link SpatioTemporalExtent}.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.intentionallyConstructedObject.addValue(TEMPORAL__PART_OF,
                spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more {@link Individual}.
     *
     * <p>
     * Note: The relationship is optional because an {@link Individual} is not necessarily a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} another {@link Individual}, yet
     * is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as {@link Individual}. This applies to all
     * subtypes of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} that are between a
     * {@code state_of_X} and {@code X}.
     * </p>
     *
     * @param individual The Individual.
     * @return This builder.
     */
    public final IntentionallyConstructedObjectBuilder temporal_Part_Of(final Individual individual) {
        this.intentionallyConstructedObject.addValue(TEMPORAL_PART_OF, individual.getId());
        return this;
    }

    /**
     * Returns an instance of IntentionallyConstructedObject created from the properties set on this
     * builder.
     *
     * @return The built IntentionallyConstructedObject.
     * @throws HqdmException If the IntentionallyConstructedObject is missing any mandatory properties.
     */
    public IntentionallyConstructedObject build() throws HqdmException {
        if (this.intentionallyConstructedObject.hasValue(AGGREGATED_INTO)
                && this.intentionallyConstructedObject.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.intentionallyConstructedObject.hasValue(BEGINNING)
                && this.intentionallyConstructedObject.values(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.intentionallyConstructedObject.hasValue(ENDING)
                && this.intentionallyConstructedObject.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.intentionallyConstructedObject.hasValue(MEMBER__OF)
                && this.intentionallyConstructedObject.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.intentionallyConstructedObject.hasValue(MEMBER_OF)
                && this.intentionallyConstructedObject.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.intentionallyConstructedObject.hasValue(MEMBER_OF_KIND)
                && this.intentionallyConstructedObject.values(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.intentionallyConstructedObject.hasValue(PART__OF)
                && this.intentionallyConstructedObject.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.intentionallyConstructedObject.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.intentionallyConstructedObject.hasValue(TEMPORAL__PART_OF)
                && this.intentionallyConstructedObject.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.intentionallyConstructedObject.hasValue(TEMPORAL_PART_OF)
                && this.intentionallyConstructedObject.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return intentionallyConstructedObject;
    }
}
