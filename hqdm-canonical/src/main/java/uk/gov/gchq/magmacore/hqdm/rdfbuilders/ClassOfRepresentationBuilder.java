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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.HAS_SUPERCLASS;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfRepresentation;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.ClassServices;

/**
 * Builder for constructing instances of ClassOfRepresentation.
 */
public class ClassOfRepresentationBuilder {

    private final ClassOfRepresentation classOfRepresentation;

    /**
     * Constructs a Builder for a new ClassOfRepresentation.
     *
     * @param iri IRI of the ClassOfRepresentation.
     */
    public ClassOfRepresentationBuilder(final IRI iri) {
        this.classOfRepresentation = ClassServices.createClassOfRepresentation(iri);
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz the Class.
     * @return This builder.
     */
    public final ClassOfRepresentationBuilder has_Superclass(final Class clazz) {
        this.classOfRepresentation.addValue(HAS_SUPERCLASS, clazz.getId());
        return this;
    }

    /**
     * Returns an instance of ClassOfRepresentation created from the properties set on this builder.
     *
     * @return The built ClassOfRepresentation.
     * @throws HqdmException If the ClassOfRepresentation is missing any mandatory properties.
     */
    public ClassOfRepresentation build() throws HqdmException {
        if (this.classOfRepresentation.hasValue(HAS_SUPERCLASS)
                && this.classOfRepresentation.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        return this.classOfRepresentation;
    }
}
