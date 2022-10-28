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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PARTICIPANT_IN;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfStateOfOrganization;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.LanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.RepresentationBySign;
import uk.gov.gchq.magmacore.hqdm.model.Role;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of RecognizingLanguageCommunity.
 */
public class RecognizingLanguageCommunityBuilder {

    private final RecognizingLanguageCommunity recognizingLanguageCommunity;

    /**
     * Constructs a Builder for a new RecognizingLanguageCommunity.
     *
     * @param iri IRI of the RecognizingLanguageCommunity.
     */
    public RecognizingLanguageCommunityBuilder(final IRI iri) {
        recognizingLanguageCommunity = RdfSpatioTemporalExtentServices.createRecognizingLanguageCommunity(iri.getIri());
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
    public final RecognizingLanguageCommunityBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        recognizingLanguageCommunity.addValue(AGGREGATED_INTO,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder beginning(final Event event) {
        recognizingLanguageCommunity.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link SpatioTemporalExtent} may consist of one or more others.
     *
     * <p>
     * Note: This is the inverse of {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART__OF}.
     * </p>
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        recognizingLanguageCommunity.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder ending(final Event event) {
        recognizingLanguageCommunity.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder member__Of(final Class clazz) {
        recognizingLanguageCommunity.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganization} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfStateOfOrganization}.
     *
     * @param classOfStateOfOrganization The ClassOfStateOfOrganization.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder member_Of(
            final ClassOfStateOfOrganization classOfStateOfOrganization) {
        recognizingLanguageCommunity.addValue(MEMBER_OF,
                new IRI(classOfStateOfOrganization.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF_KIND} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.Participant} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more {@link Role}.
     *
     * @param role The Role.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder member_Of_Kind_M(final Role role) {
        recognizingLanguageCommunity.addValue(MEMBER_OF_KIND, new IRI(role.getId()));
        return this;
    }

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#AGGREGATED_INTO} relationship type where a
     * {@link SpatioTemporalExtent} may be part of another and the whole has emergent properties and is
     * more than just the sum of its parts.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        recognizingLanguageCommunity.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF}
     * one or more {@link PossibleWorld}.
     *
     * <p>
     * Note: The relationship is optional because a {@link PossibleWorld} is not
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} any other
     * {@link SpatioTemporalExtent}.
     * </p>
     *
     * @param possibleWorld The PossibleWorld.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        recognizingLanguageCommunity.addValue(PART_OF_POSSIBLE_WORLD,
                new IRI(possibleWorld.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PARTICIPANT_IN} relationship type where a
     * {@link RecognizingLanguageCommunity} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PARTICIPANT_IN} one or more
     * {@link RepresentationBySign}.
     *
     * @param representationBySign The RepresentationBySign.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder participant_In(final RepresentationBySign representationBySign) {
        recognizingLanguageCommunity.addValue(PARTICIPANT_IN,
                new IRI(representationBySign.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} may be a temporal part of one or more other
     * {@link SpatioTemporalExtent}.
     *
     * @param spatioTemporalExtent The SpatioTemporalExtent.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        recognizingLanguageCommunity.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfLanguageCommunity} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#TEMPORAL_PART_OF} one or more
     * {@link LanguageCommunity}.
     *
     * @param languageCommunity The LanguageCommunity.
     * @return This builder.
     */
    public final RecognizingLanguageCommunityBuilder temporal_Part_Of(final LanguageCommunity languageCommunity) {
        recognizingLanguageCommunity.addValue(TEMPORAL_PART_OF, new IRI(languageCommunity.getId()));
        return this;
    }

    /**
     * Returns an instance of RecognizingLanguageCommunity created from the properties set on this
     * builder.
     *
     * @return The built RecognizingLanguageCommunity.
     * @throws HqdmException If the RecognizingLanguageCommunity is missing any mandatory properties.
     */
    public RecognizingLanguageCommunity build() throws HqdmException {
        if (recognizingLanguageCommunity.hasValue(AGGREGATED_INTO)
                && recognizingLanguageCommunity.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (recognizingLanguageCommunity.hasValue(BEGINNING)
                && recognizingLanguageCommunity.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (recognizingLanguageCommunity.hasValue(ENDING)
                && recognizingLanguageCommunity.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (recognizingLanguageCommunity.hasValue(MEMBER__OF)
                && recognizingLanguageCommunity.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (recognizingLanguageCommunity.hasValue(MEMBER_OF)
                && recognizingLanguageCommunity.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!recognizingLanguageCommunity.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (recognizingLanguageCommunity.hasValue(PART__OF)
                && recognizingLanguageCommunity.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!recognizingLanguageCommunity.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (recognizingLanguageCommunity.hasValue(PARTICIPANT_IN)
                && recognizingLanguageCommunity.value(PARTICIPANT_IN).isEmpty()) {
            throw new HqdmException("Property Not Set: participant_in");
        }
        if (recognizingLanguageCommunity.hasValue(TEMPORAL__PART_OF)
                && recognizingLanguageCommunity.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (recognizingLanguageCommunity.hasValue(TEMPORAL_PART_OF)
                && recognizingLanguageCommunity.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return recognizingLanguageCommunity;
    }
}
