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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_PARTICIPANT;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.ENDING;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF_KIND;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.REPRESENTS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRepresentation;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfAssociation;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of RepresentationBySign.
 */
public class RepresentationBySignBuilder {

    private final RepresentationBySign representationBySign;

    /**
     * Constructs a Builder for a new RepresentationBySign.
     *
     * @param iri IRI of the RepresentationBySign.
     */
    public RepresentationBySignBuilder(final IRI iri) {
        representationBySign = SpatioTemporalExtentServices.createRepresentationBySign(iri);
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
    public final RepresentationBySignBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.representationBySign.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final RepresentationBySignBuilder beginning(final Event event) {
        this.representationBySign.addValue(BEGINNING, event.getId());
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
    public final RepresentationBySignBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.representationBySign.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where one or
     * more {@link Sign} is used in the {@link RepresentationBySign}.
     *
     * @param sign The Sign.
     * @return This builder.
     */
    public final RepresentationBySignBuilder consists_Of(final Sign sign) {
        this.representationBySign.addValue(CONSISTS_OF, sign.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where a
     * {@link RepresentationBySign} consists of one or more {@link RecognizingLanguageCommunity}.
     *
     * @param recognizingLanguageCommunity The RecognizingLanguageCommunity.
     * @return This builder.
     */
    public final RepresentationBySignBuilder consists_Of_(
            final RecognizingLanguageCommunity recognizingLanguageCommunity) {
        this.representationBySign.addValue(CONSISTS_OF_, recognizingLanguageCommunity.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} consists of two or more participants.
     *
     * <p>
     * Note: The cardinality constraint shows a minimum cardinality of one because this relationship
     * will be retyped for particular participants in an association.
     * </p>
     *
     * @param participant The Participant.
     * @return This builder.
     */
    public final RepresentationBySignBuilder consists_Of_Participant(final Participant participant) {
        this.representationBySign.addValue(CONSISTS_OF_PARTICIPANT, participant.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final RepresentationBySignBuilder ending(final Event event) {
        this.representationBySign.addValue(ENDING, event.getId());
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final RepresentationBySignBuilder member__Of(final Class clazz) {
        this.representationBySign.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where the
     * {@link RepresentationBySign} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfRepresentation}.
     *
     * @param classOfRepresentation The ClassOfRepresentation.
     * @return This builder.
     */
    public final RepresentationBySignBuilder member_Of(final ClassOfRepresentation classOfRepresentation) {
        this.representationBySign.addValue(MEMBER_OF, classOfRepresentation.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where the
     * {@link RepresentationBySign} must be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} exactly one
     * {@link RepresentationByPattern}.
     *
     * @param representationByPattern The RepresentationByPattern.
     * @return This builder.
     */
    public final RepresentationBySignBuilder member_Of__M(final RepresentationByPattern representationByPattern) {
        this.representationBySign.addValue(MEMBER_OF_, representationByPattern.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfAssociation}.
     *
     * @param kindOfAssociation The KindOfAssociation.
     * @return This builder.
     */
    public final RepresentationBySignBuilder member_Of_Kind_M(final KindOfAssociation kindOfAssociation) {
        this.representationBySign.addValue(MEMBER_OF_KIND, kindOfAssociation.getId());
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
    public final RepresentationBySignBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.representationBySign.addValue(PART__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF}
     * one or more {@link PossibleWorld}.
     *
     * <p>
     * Note: The relationship is optional because a {@link PossibleWorld} is not
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} any other
     * {@link SpatioTemporalExtent}.
     * </p>
     *
     * @param possibleWorld The PossibleWorld.
     * @return This builder.
     */
    public final RepresentationBySignBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.representationBySign.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
        return this;
    }

    /**
     * A relationship type where a {@link RepresentationBySign} represents one or more {@link Thing}.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final RepresentationBySignBuilder represents_M(final Thing thing) {
        this.representationBySign.addValue(REPRESENTS, thing.getId());
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
    public final RepresentationBySignBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.representationBySign.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link Individual}.
     *
     * <p>
     * Note: The relationship is optional because an {@link Individual} is not necessarily a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} another {@link Individual},
     * yet is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF}
     * {@link uk.gov.gchq.magmacore.hqdm.model.State} as well as {@link Individual}. This applies to all
     * subtypes of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} that are between
     * a {@code state_of_X} and {@code X}.
     * </p>
     *
     * @param individual The Individual.
     * @return This builder.
     */
    public final RepresentationBySignBuilder temporal_Part_Of(final Individual individual) {
        this.representationBySign.addValue(TEMPORAL_PART_OF, individual.getId());
        return this;
    }

    /**
     * Returns an instance of RepresentationBySign created from the properties set on this builder.
     *
     * @return The built RepresentationBySign.
     * @throws HqdmException If the RepresentationBySign is missing any mandatory properties.
     */
    public RepresentationBySign build() throws HqdmException {
        if (this.representationBySign.hasValue(AGGREGATED_INTO)
                && this.representationBySign.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.representationBySign.hasValue(BEGINNING)
                && this.representationBySign.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.representationBySign.hasValue(ENDING)
                && this.representationBySign.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.representationBySign.hasValue(MEMBER__OF)
                && this.representationBySign.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.representationBySign.hasValue(MEMBER_OF)
                && this.representationBySign.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.representationBySign.hasValue(MEMBER_OF_)) {
            throw new HqdmException("Property Not Set: member_of_");
        }
        if (!this.representationBySign.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.representationBySign.hasValue(PART__OF)
                && this.representationBySign.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.representationBySign.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (!this.representationBySign.hasValue(REPRESENTS)) {
            throw new HqdmException("Property Not Set: represents");
        }
        if (this.representationBySign.hasValue(TEMPORAL__PART_OF)
                && this.representationBySign.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.representationBySign.hasValue(TEMPORAL_PART_OF)
                && this.representationBySign.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return representationBySign;
    }
}
