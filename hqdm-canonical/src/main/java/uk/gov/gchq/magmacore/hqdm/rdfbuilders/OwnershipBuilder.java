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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_PARTICIPANT;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_PARTICIPANT_;
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
import uk.gov.gchq.magmacore.hqdm.model.Asset;
import uk.gov.gchq.magmacore.hqdm.model.BeginningOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfAssociation;
import uk.gov.gchq.magmacore.hqdm.model.EndingOfOwnership;
import uk.gov.gchq.magmacore.hqdm.model.Individual;
import uk.gov.gchq.magmacore.hqdm.model.KindOfAssociation;
import uk.gov.gchq.magmacore.hqdm.model.Owner;
import uk.gov.gchq.magmacore.hqdm.model.Ownership;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of Ownership.
 */
public class OwnershipBuilder {

    private final Ownership ownership;

    /**
     * Constructs a Builder for a new Ownership.
     *
     * @param iri IRI of the Ownership.
     */
    public OwnershipBuilder(final IRI iri) {
        ownership = SpatioTemporalExtentServices.createOwnership(iri);
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
    public final OwnershipBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.ownership.addValue(AGGREGATED_INTO, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#BEGINNING} relationship type where an
     * {@link Ownership} has as {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#BEGINNING} exactly one
     * {@link BeginningOfOwnership}.
     *
     * @param beginningOfOwnership The BeginningOfOwnership.
     * @return This builder.
     */
    public final OwnershipBuilder beginning_M(final BeginningOfOwnership beginningOfOwnership) {
        this.ownership.addValue(BEGINNING, beginningOfOwnership.getId());
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
    public final OwnershipBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.ownership.addValue(CONSISTS__OF, spatioTemporalExtent.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT} relationship type where
     * an {@link Ownership} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT}
     * exactly one {@link Owner}.
     *
     * @param owner The Owner.
     * @return This builder.
     */
    public final OwnershipBuilder consists_Of_Participant(final Owner owner) {
        this.ownership.addValue(CONSISTS_OF_PARTICIPANT, owner.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT} relationship type where
     * an {@link Ownership} {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_PARTICIPANT}
     * exactly one {@link Asset}.
     *
     * @param asset The Asset.
     * @return This builder.
     */
    public final OwnershipBuilder consists_Of_Participant_(final Asset asset) {
        this.ownership.addValue(CONSISTS_OF_PARTICIPANT_, asset.getId());
        return this;
    }

    /**
     * An {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#ENDING} relationship type where an
     * {@link Ownership} has as {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#ENDING} not more than one
     * {@link EndingOfOwnership}.
     *
     * @param endingOfOwnership The EndingOfOwnership.
     * @return This builder.
     */
    public final OwnershipBuilder ending(final EndingOfOwnership endingOfOwnership) {
        this.ownership.addValue(ENDING, endingOfOwnership.getId());
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final OwnershipBuilder member__Of(final Class clazz) {
        this.ownership.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more {@link ClassOfAssociation}.
     *
     * @param classOfAssociation The ClassOfAssociation.
     * @return This builder.
     */
    public final OwnershipBuilder member_Of(final ClassOfAssociation classOfAssociation) {
        this.ownership.addValue(MEMBER_OF, classOfAssociation.getId());
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where each
     * {@link uk.gov.gchq.magmacore.hqdm.model.Association} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more {@link KindOfAssociation}.
     *
     * @param kindOfAssociation The KindOfAssociation.
     * @return This builder.
     */
    public final OwnershipBuilder member_Of_Kind_M(final KindOfAssociation kindOfAssociation) {
        this.ownership.addValue(MEMBER_OF_KIND, kindOfAssociation.getId());
        this.ownership.addValue(RDFS.RDF_TYPE, kindOfAssociation.getId());
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
    public final OwnershipBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.ownership.addValue(PART__OF, spatioTemporalExtent.getId());
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
    public final OwnershipBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.ownership.addValue(PART_OF_POSSIBLE_WORLD, possibleWorld.getId());
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
    public final OwnershipBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.ownership.addValue(TEMPORAL__PART_OF, spatioTemporalExtent.getId());
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
    public final OwnershipBuilder temporal_Part_Of(final Individual individual) {
        this.ownership.addValue(TEMPORAL_PART_OF, individual.getId());
        return this;
    }

    /**
     * Returns an instance of Ownership created from the properties set on this builder.
     *
     * @return The built Ownership.
     * @throws HqdmException If the Ownership is missing any mandatory properties.
     */
    public Ownership build() throws HqdmException {
        if (this.ownership.hasValue(AGGREGATED_INTO)
                && this.ownership.values(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (!this.ownership.hasValue(BEGINNING)) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.ownership.hasValue(ENDING)
                && this.ownership.values(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.ownership.hasValue(MEMBER__OF)
                && this.ownership.values(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.ownership.hasValue(MEMBER_OF)
                && this.ownership.values(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (!this.ownership.hasValue(MEMBER_OF_KIND)) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.ownership.hasValue(PART__OF)
                && this.ownership.values(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.ownership.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.ownership.hasValue(TEMPORAL__PART_OF)
                && this.ownership.values(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.ownership.hasValue(TEMPORAL_PART_OF)
                && this.ownership.values(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return ownership;
    }
}
