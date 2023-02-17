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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_IN_MEMBERS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.REPRESENTED;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.USES;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.VALUE_;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.IdentificationOfPhysicalQuantity;
import uk.gov.gchq.magmacore.hqdm.model.PhysicalQuantity;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.Scale;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of IdentificationOfPhysicalQuantity.
 */
public class IdentificationOfPhysicalQuantityBuilder {

    private final IdentificationOfPhysicalQuantity identificationOfPhysicalQuantity;

    /**
     * Constructs a Builder for a new IdentificationOfPhysicalQuantity.
     *
     * @param iri IRI of the IdentificationOfPhysicalQuantity.
     */
    public IdentificationOfPhysicalQuantityBuilder(final IRI iri) {
        identificationOfPhysicalQuantity = RdfSpatioTemporalExtentServices
                .createIdentificationOfPhysicalQuantity(iri.getIri());
    }

    /**
     * A relationship type where an {@link IdentificationOfPhysicalQuantity} consists of exactly one
     * REAL as its value.
     *
     * @param value The Value.
     * @return This builder.
     */
    public final IdentificationOfPhysicalQuantityBuilder value__M(final double value) {
        this.identificationOfPhysicalQuantity.addRealValue(VALUE_, value);
        return this;
    }

    /**
     * A relationship type where a {@link RecognizingLanguageCommunity} is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#PARTICIPANT_IN} each
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern}.
     *
     * @param recognizingLanguageCommunity The RecognizingLanguageCommunity.
     * @return This builder.
     */
    public final IdentificationOfPhysicalQuantityBuilder consists_Of_In_Members_M(
            final RecognizingLanguageCommunity recognizingLanguageCommunity) {
        this.identificationOfPhysicalQuantity.addValue(CONSISTS_OF_IN_MEMBERS,
                new IRI(recognizingLanguageCommunity.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} an
     * {@link IdentificationOfPhysicalQuantity} represents exactly one {@link PhysicalQuantity}.
     *
     * @param physicalQuantity The PhysicalQuantity.
     * @return This builder.
     */
    public final IdentificationOfPhysicalQuantityBuilder represented_M(final PhysicalQuantity physicalQuantity) {
        this.identificationOfPhysicalQuantity.addValue(REPRESENTED, new IRI(physicalQuantity.getId()));
        return this;
    }

    /**
     * A relationship type where an {@link IdentificationOfPhysicalQuantity} uses exactly one
     * {@link Scale}.
     *
     * @param scale The Scale.
     * @return This builder.
     */
    public final IdentificationOfPhysicalQuantityBuilder uses_M(final Scale scale) {
        this.identificationOfPhysicalQuantity.addValue(USES, new IRI(scale.getId()));
        return this;
    }

    /**
     * Returns an instance of IdentificationOfPhysicalQuantity created from the properties set on this
     * builder.
     *
     * @return The built IdentificationOfPhysicalQuantity.
     * @throws HqdmException If the IdentificationOfPhysicalQuantity is missing any mandatory
     *                       properties.
     */
    public IdentificationOfPhysicalQuantity build() throws HqdmException {
        if (!this.identificationOfPhysicalQuantity.hasValue(VALUE_)) {
            throw new HqdmException("Property Not Set: value_");
        }
        if (!this.identificationOfPhysicalQuantity.hasValue(CONSISTS_OF_IN_MEMBERS)) {
            throw new HqdmException("Property Not Set: consists_of_in_members");
        }
        if (!this.identificationOfPhysicalQuantity.hasValue(REPRESENTED)) {
            throw new HqdmException("Property Not Set: represented");
        }
        if (!this.identificationOfPhysicalQuantity.hasValue(USES)) {
            throw new HqdmException("Property Not Set: uses");
        }
        return identificationOfPhysicalQuantity;
    }
}
