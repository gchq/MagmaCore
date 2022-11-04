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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.CLASSIFIER;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.Classification;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfRelationshipServices;

/**
 * Builder for constructing instances of Classification.
 */
public class ClassificationBuilder {

    private final Classification classification;

    /**
     * Constructs a Builder for a new Classification.
     *
     * @param iri IRI of the Classification.
     */
    public ClassificationBuilder(final IRI iri) {
        classification = RdfRelationshipServices.createClassification(iri.getIri());
    }

    /**
     * A relationship type where a {@link Classification} has exactly one classifier.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ClassificationBuilder classifier_M(final Class clazz) {
        classification.addValue(CLASSIFIER, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Classification} has exactly one member.
     *
     * @param thing The Thing.
     * @return This builder.
     */
    public final ClassificationBuilder member_M(final Thing thing) {
        classification.addValue(MEMBER, new IRI(thing.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * <p>
     * Note: This relationship is the same as the entity type {@link Classification}.
     * </p>
     * clazz.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final ClassificationBuilder member__Of(final Class clazz) {
        classification.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a relationship is a
     * {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a {@link ClassOfRelationship}.
     *
     * @param classOfRelationship The ClassOfRelationship.
     * @return This builder.
     */
    public final ClassificationBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        classification.addValue(MEMBER_OF, new IRI(classOfRelationship.getId()));
        return this;
    }

    /**
     * Returns an instance of Classification created from the properties set on this builder.
     *
     * @return The built Classification.
     * @throws HqdmException If the Classification is missing any mandatory properties.
     */
    public Classification build() throws HqdmException {
        if (!classification.hasValue(CLASSIFIER)) {
            throw new HqdmException("Property Not Set: classifier");
        }
        if (!classification.hasValue(MEMBER)) {
            throw new HqdmException("Property Not Set: member");
        }
        if (classification.hasValue(MEMBER__OF)
                && classification.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (classification.hasValue(MEMBER_OF)
                && classification.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        return classification;
    }
}
