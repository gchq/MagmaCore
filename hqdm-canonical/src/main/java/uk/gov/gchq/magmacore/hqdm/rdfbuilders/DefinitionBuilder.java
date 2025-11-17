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
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.Definition;
import uk.gov.gchq.magmacore.hqdm.model.Pattern;
import uk.gov.gchq.magmacore.hqdm.model.RecognizingLanguageCommunity;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of Definition.
 */
public class DefinitionBuilder {

    private final Definition definition;

    /**
     * Constructs a Builder for a new Definition.
     *
     * @param iri IRI of the Definition.
     */
    public DefinitionBuilder(final IRI iri) {
        this.definition = ClassServices.createDefinition(iri);
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#CONSISTS_OF_BY_CLASS} relationship type where a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link uk.gov.gchq.magmacore.hqdm.model.RepresentationByPattern} has a
     * {@link uk.gov.gchq.magmacore.hqdm.model.Sign} that is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the {@link Pattern}.
     *
     * @param pattern The Pattern.
     * @return This builder.
     */
    public final DefinitionBuilder consists_Of_By_Class_M(final Pattern pattern) {
        this.definition.addValue(CONSISTS_OF_BY_CLASS, pattern.getId());
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
    public final DefinitionBuilder consists_Of_In_Members_M(
            final RecognizingLanguageCommunity recognizingLanguageCommunity) {
        this.definition.addValue(CONSISTS_OF_IN_MEMBERS, recognizingLanguageCommunity.getId());
        return this;
    }

    /**
     * A relationship type where exactly one {@link Class} is defined by the {@link Definition}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final DefinitionBuilder represented_M(final Class clazz) {
        this.definition.addValue(REPRESENTED, clazz.getId());
        return this;
    }

    /**
     * Returns an instance of Definition created from the properties set on this builder.
     *
     * @return The built Definition.
     * @throws HqdmException If the Definition is missing any mandatory properties.
     */
    public Definition build() throws HqdmException {
        if (!this.definition.hasValue(CONSISTS_OF_BY_CLASS)) {
            throw new HqdmException("Property Not Set: consists_of_by_class");
        }
        if (!this.definition.hasValue(CONSISTS_OF_IN_MEMBERS)) {
            throw new HqdmException("Property Not Set: consists_of_in_members");
        }
        if (!this.definition.hasValue(REPRESENTED)) {
            throw new HqdmException("Property Not Set: represented");
        }
        return this.definition;
    }
}
