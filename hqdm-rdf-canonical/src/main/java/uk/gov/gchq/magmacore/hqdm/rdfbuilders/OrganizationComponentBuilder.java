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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.COMPONENT_OF;
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
import uk.gov.gchq.magmacore.hqdm.model.ClassOfOrganizationComponent;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.KindOfOrganizationComponent;
import uk.gov.gchq.magmacore.hqdm.model.Organization;
import uk.gov.gchq.magmacore.hqdm.model.OrganizationComponent;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of OrganizationComponent.
 */
public class OrganizationComponentBuilder {

    private final OrganizationComponent organizationComponent;

    /**
     * Constructs a Builder for a new OrganizationComponent.
     *
     * @param iri IRI of the OrganizationComponent.
     */
    public OrganizationComponentBuilder(final IRI iri) {
        organizationComponent = RdfSpatioTemporalExtentServices.createOrganizationComponent(iri.getIri());
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
    public final OrganizationComponentBuilder aggregated_Into(final SpatioTemporalExtent spatioTemporalExtent) {
        this.organizationComponent.addValue(AGGREGATED_INTO, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OrganizationComponentBuilder beginning(final Event event) {
        this.organizationComponent.addValue(BEGINNING, new IRI(event.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#COMPONENT_OF} relationship type where an
     * {@link OrganizationComponent} is a replaceable component of exactly one {@link Organization}.
     *
     * @param organization The Organization.
     * @return This builder.
     */
    public final OrganizationComponentBuilder component_Of_M(final Organization organization) {
        this.organizationComponent.addValue(COMPONENT_OF, new IRI(organization.getId()));
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
    public final OrganizationComponentBuilder consists__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.organizationComponent.addValue(CONSISTS__OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final OrganizationComponentBuilder ending(final Event event) {
        this.organizationComponent.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final OrganizationComponentBuilder member__Of(final Class clazz) {
        this.organizationComponent.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link OrganizationComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfOrganizationComponent}.
     *
     * @param classOfOrganizationComponent The ClassOfOrganizationComponent.
     * @return This builder.
     */
    public final OrganizationComponentBuilder member_Of(
            final ClassOfOrganizationComponent classOfOrganizationComponent) {
        this.organizationComponent.addValue(MEMBER_OF, new IRI(classOfOrganizationComponent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF_KIND} relationship type where an
     * {@link OrganizationComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link KindOfOrganizationComponent}.
     *
     * @param kindOfOrganizationComponent The KindOfOrganizationComponent.
     * @return This builder.
     */
    public final OrganizationComponentBuilder member_Of_Kind(
            final KindOfOrganizationComponent kindOfOrganizationComponent) {
        this.organizationComponent.addValue(MEMBER_OF_KIND,
                new IRI(kindOfOrganizationComponent.getId()));
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
    public final OrganizationComponentBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.organizationComponent.addValue(PART__OF, new IRI(spatioTemporalExtent.getId()));
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
    public final OrganizationComponentBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.organizationComponent.addValue(PART_OF_POSSIBLE_WORLD, new IRI(possibleWorld.getId()));
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
    public final OrganizationComponentBuilder temporal__Part_Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.organizationComponent.addValue(TEMPORAL__PART_OF, new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfOrganizationComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link OrganizationComponent}.
     *
     * @param organizationComponent The OrganizationComponent.
     * @return This builder.
     */
    public final OrganizationComponentBuilder temporal_Part_Of(final OrganizationComponent organizationComponent) {
        this.organizationComponent.addValue(TEMPORAL_PART_OF, new IRI(organizationComponent.getId()));
        return this;
    }

    /**
     * Returns an instance of OrganizationComponent created from the properties set on this builder.
     *
     * @return The built OrganizationComponent.
     * @throws HqdmException If the OrganizationComponent is missing any mandatory properties.
     */
    public OrganizationComponent build() throws HqdmException {
        if (this.organizationComponent.hasValue(AGGREGATED_INTO)
                && this.organizationComponent.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.organizationComponent.hasValue(BEGINNING)
                && this.organizationComponent.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (!this.organizationComponent.hasValue(COMPONENT_OF)) {
            throw new HqdmException("Property Not Set: component_of");
        }
        if (this.organizationComponent.hasValue(ENDING)
                && this.organizationComponent.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.organizationComponent.hasValue(MEMBER__OF)
                && this.organizationComponent.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.organizationComponent.hasValue(MEMBER_OF)
                && this.organizationComponent.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.organizationComponent.hasValue(MEMBER_OF_KIND)
                && this.organizationComponent.value(MEMBER_OF_KIND).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of_kind");
        }
        if (this.organizationComponent.hasValue(PART__OF)
                && this.organizationComponent.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.organizationComponent.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.organizationComponent.hasValue(TEMPORAL__PART_OF)
                && this.organizationComponent.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.organizationComponent.hasValue(TEMPORAL_PART_OF)
                && this.organizationComponent.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return organizationComponent;
    }
}
