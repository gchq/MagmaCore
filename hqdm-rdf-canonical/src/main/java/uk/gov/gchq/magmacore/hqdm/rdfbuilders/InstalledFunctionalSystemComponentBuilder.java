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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART_OF_POSSIBLE_WORLD;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.PART__OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL_PART_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.TEMPORAL__PART_OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfInstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.Event;
import uk.gov.gchq.magmacore.hqdm.model.FunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.InstalledFunctionalSystemComponent;
import uk.gov.gchq.magmacore.hqdm.model.PossibleWorld;
import uk.gov.gchq.magmacore.hqdm.model.SpatioTemporalExtent;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of InstalledFunctionalSystemComponent.
 */
public class InstalledFunctionalSystemComponentBuilder {

    private final InstalledFunctionalSystemComponent installedFunctionalSystemComponent;

    /**
     * Constructs a Builder for a new InstalledFunctionalSystemComponent.
     *
     * @param iri IRI of the InstalledFunctionalSystemComponent.
     */
    public InstalledFunctionalSystemComponentBuilder(final IRI iri) {
        installedFunctionalSystemComponent = RdfSpatioTemporalExtentServices
                .createInstalledFunctionalSystemComponent(iri.getIri());
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
    public final InstalledFunctionalSystemComponentBuilder aggregated_Into(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.installedFunctionalSystemComponent.addValue(AGGREGATED_INTO,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its beginning.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final InstalledFunctionalSystemComponentBuilder beginning(final Event event) {
        this.installedFunctionalSystemComponent.addValue(BEGINNING, new IRI(event.getId()));
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
    public final InstalledFunctionalSystemComponentBuilder consists__Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.installedFunctionalSystemComponent.addValue(CONSISTS__OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PART_OF} relationship type where a
     * {@link SpatioTemporalExtent} has exactly one {@link Event} that is its ending.
     *
     * @param event The Event.
     * @return This builder.
     */
    public final InstalledFunctionalSystemComponentBuilder ending(final Event event) {
        this.installedFunctionalSystemComponent.addValue(ENDING, new IRI(event.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final InstalledFunctionalSystemComponentBuilder member__Of(final Class clazz) {
        this.installedFunctionalSystemComponent.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where an
     * {@link InstalledFunctionalSystemComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfInstalledFunctionalSystemComponent}.
     *
     * @param classOfInstalledFunctionalSystemComponent classOfInstalledFunctionalSystemComponent.
     * @return This builder.
     */
    @SuppressWarnings("LineLength")
    public final InstalledFunctionalSystemComponentBuilder member_Of(
            final ClassOfInstalledFunctionalSystemComponent classOfInstalledFunctionalSystemComponent) {
        this.installedFunctionalSystemComponent.addValue(MEMBER_OF,
                new IRI(classOfInstalledFunctionalSystemComponent.getId()));
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
    public final InstalledFunctionalSystemComponentBuilder part__Of(final SpatioTemporalExtent spatioTemporalExtent) {
        this.installedFunctionalSystemComponent.addValue(PART__OF,
                new IRI(spatioTemporalExtent.getId()));
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
    public final InstalledFunctionalSystemComponentBuilder part_Of_Possible_World_M(final PossibleWorld possibleWorld) {
        this.installedFunctionalSystemComponent.addValue(PART_OF_POSSIBLE_WORLD,
                new IRI(possibleWorld.getId()));
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
    public final InstalledFunctionalSystemComponentBuilder temporal__Part_Of(
            final SpatioTemporalExtent spatioTemporalExtent) {
        this.installedFunctionalSystemComponent.addValue(TEMPORAL__PART_OF,
                new IRI(spatioTemporalExtent.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.model.StateOfFunctionalSystemComponent} may be a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#TEMPORAL_PART_OF} one or more
     * {@link FunctionalSystemComponent}.
     *
     * @param functionalSystemComponent The FunctionalSystemComponent.
     * @return This builder.
     */
    public final InstalledFunctionalSystemComponentBuilder temporal_Part_Of(
            final FunctionalSystemComponent functionalSystemComponent) {
        this.installedFunctionalSystemComponent.addValue(TEMPORAL_PART_OF,
                new IRI(functionalSystemComponent.getId()));
        return this;
    }

    /**
     * Returns an instance of InstalledFunctionalSystemComponent created from the properties set on this
     * builder.
     *
     * @return The built InstalledFunctionalSystemComponent.
     * @throws HqdmException If the InstalledFunctionalSystemComponent is missing any mandatory
     *                       properties.
     */
    public InstalledFunctionalSystemComponent build() throws HqdmException {
        if (this.installedFunctionalSystemComponent.hasValue(AGGREGATED_INTO)
                && this.installedFunctionalSystemComponent.value(AGGREGATED_INTO).isEmpty()) {
            throw new HqdmException("Property Not Set: aggregated_into");
        }
        if (this.installedFunctionalSystemComponent.hasValue(BEGINNING)
                && this.installedFunctionalSystemComponent.value(BEGINNING).isEmpty()) {
            throw new HqdmException("Property Not Set: beginning");
        }
        if (this.installedFunctionalSystemComponent.hasValue(ENDING)
                && this.installedFunctionalSystemComponent.value(ENDING).isEmpty()) {
            throw new HqdmException("Property Not Set: ending");
        }
        if (this.installedFunctionalSystemComponent.hasValue(MEMBER__OF)
                && this.installedFunctionalSystemComponent.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.installedFunctionalSystemComponent.hasValue(MEMBER_OF)
                && this.installedFunctionalSystemComponent.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        if (this.installedFunctionalSystemComponent.hasValue(PART__OF)
                && this.installedFunctionalSystemComponent.value(PART__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: part__of");
        }
        if (!this.installedFunctionalSystemComponent.hasValue(PART_OF_POSSIBLE_WORLD)) {
            throw new HqdmException("Property Not Set: part_of_possible_world");
        }
        if (this.installedFunctionalSystemComponent.hasValue(TEMPORAL__PART_OF)
                && this.installedFunctionalSystemComponent.value(TEMPORAL__PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal__part_of");
        }
        if (this.installedFunctionalSystemComponent.hasValue(TEMPORAL_PART_OF)
                && this.installedFunctionalSystemComponent.value(TEMPORAL_PART_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: temporal_part_of");
        }
        return installedFunctionalSystemComponent;
    }
}
