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
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER_OF;
import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.ClassOfClass;
import uk.gov.gchq.magmacore.hqdm.model.EnumeratedClass;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfClassServices;

/**
 * Builder for constructing instances of EnumeratedClass.
 */
public class EnumeratedClassBuilder {

    private final EnumeratedClass enumeratedClass;

    /**
     * Constructs a Builder for a new EnumeratedClass.
     *
     * @param iri IRI of the EnumeratedClass.
     */
    public EnumeratedClassBuilder(final IRI iri) {
        enumeratedClass = RdfClassServices.createEnumeratedClass(iri.getIri());
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final EnumeratedClassBuilder has_Superclass(final Class clazz) {
        enumeratedClass.addValue(HAS_SUPERCLASS, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final EnumeratedClassBuilder member__Of(final Class clazz) {
        enumeratedClass.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} relationship type where a
     * {@link Class} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI.HQDM#MEMBER_OF} one or more
     * {@link ClassOfClass}.
     *
     * @param classOfClass The ClassOfClass.
     * @return This builder.
     */
    public final EnumeratedClassBuilder member_Of(final ClassOfClass classOfClass) {
        enumeratedClass.addValue(MEMBER_OF, new IRI(classOfClass.getId()));
        return this;
    }

    /**
     * Returns an instance of EnumeratedClass created from the properties set on this builder.
     *
     * @return The built EnumeratedClass.
     * @throws HqdmException If the EnumeratedClass is missing any mandatory properties.
     */
    public EnumeratedClass build() throws HqdmException {
        if (enumeratedClass.hasValue(HAS_SUPERCLASS)
                && enumeratedClass.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (enumeratedClass.hasValue(MEMBER__OF)
                && enumeratedClass.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (enumeratedClass.hasValue(MEMBER_OF)
                && enumeratedClass.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        return enumeratedClass;
    }
}
