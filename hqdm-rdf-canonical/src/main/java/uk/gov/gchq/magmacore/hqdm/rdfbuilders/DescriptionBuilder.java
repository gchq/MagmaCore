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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_BY_CLASS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CONSISTS_OF_IN_MEMBERS;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.REPRESENTED;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Description;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfClassServices;

/**
 * Builder for constructing instances of Description.
 */
public class DescriptionBuilder {

    private final Description description;

    /**
     * Constructs a Builder for a new Description.
     *
     * @param iri IRI of the Description.
     */
    public DescriptionBuilder(final IRI iri) {
        description = RdfClassServices.createDescription(iri.getIri());
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_BY_CLASS} relationship type
     * where a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern} has a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the {@link Pattern}.
     *
     * @param pattern The Pattern.
     * @return This builder.
     */
    public final DescriptionBuilder consists_Of_By_Class_M(final Pattern pattern) {
        description.addValue(CONSISTS_OF_BY_CLASS, new IRI(pattern.getId()));
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
    public final DescriptionBuilder consists_Of_In_Members_M(
            final RecognizingLanguageCommunity recognizingLanguageCommunity) {
        description.addValue(CONSISTS_OF_IN_MEMBERS, new IRI(recognizingLanguageCommunity.getId()));
        return this;
    }

    /**
     * A relationship type where the {@link Thing} is represented by each
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern}.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final DescriptionBuilder represented_M(final Thing thing) {
        description.addValue(REPRESENTED, new IRI(thing.getId()));
        return this;
    }

    /**
     * Returns an instance of Description created from the properties set on this builder.
     *
     * @return The built Description.
     * @throws HqdmException If the Description is missing any mandatory properties.
     */
    public Description build() throws HqdmException {
        if (!description.hasValue(CONSISTS_OF_BY_CLASS)) {
            throw new HqdmException("Property Not Set: consists_of_by_class");
        }
        if (!description.hasValue(CONSISTS_OF_IN_MEMBERS)) {
            throw new HqdmException("Property Not Set: consists_of_in_members");
        }
        if (!description.hasValue(REPRESENTED)) {
            throw new HqdmException("Property Not Set: represented");
        }
        return description;
    }
}
