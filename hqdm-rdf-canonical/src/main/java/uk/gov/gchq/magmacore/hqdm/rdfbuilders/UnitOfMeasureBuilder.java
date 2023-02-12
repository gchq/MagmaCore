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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRelationship;
import uk.gov.gchq.magmacore.hqdm.model.UnitOfMeasure;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfRelationshipServices;

/**
 * Builder for constructing instances of UnitOfMeasure.
 */
public class UnitOfMeasureBuilder {

    private final UnitOfMeasure unitOfMeasure;

    /**
     * Constructs a Builder for a new UnitOfMeasure.
     *
     * @param iri IRI of the UnitOfMeasure.
     */
    public UnitOfMeasureBuilder(final IRI iri) {
        unitOfMeasure = RdfRelationshipServices.createUnitOfMeasure(iri.getIri());
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final UnitOfMeasureBuilder member__Of(final Class clazz) {
        this.unitOfMeasure.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * relationship is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} a
     * {@link ClassOfRelationship}.
     *
     * @param classOfRelationship The ClassOfRelationship.
     * @return This builder.
     */
    public final UnitOfMeasureBuilder member_Of(final ClassOfRelationship classOfRelationship) {
        this.unitOfMeasure.addValue(MEMBER_OF, new IRI(classOfRelationship.getId()));
        return this;
    }

    /**
     * Returns an instance of UnitOfMeasure created from the properties set on this builder.
     *
     * @return The built UnitOfMeasure.
     * @throws HqdmException If the UnitOfMeasure is missing any mandatory properties.
     */
    public UnitOfMeasure build() throws HqdmException {
        if (this.unitOfMeasure.hasValue(MEMBER__OF)
                && this.unitOfMeasure.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.unitOfMeasure.hasValue(MEMBER_OF)
                && this.unitOfMeasure.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        return unitOfMeasure;
    }
}
