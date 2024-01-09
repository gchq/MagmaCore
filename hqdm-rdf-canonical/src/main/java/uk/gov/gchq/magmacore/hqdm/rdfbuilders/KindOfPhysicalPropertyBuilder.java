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
import uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalProperty;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfClassServices;

/**
 * Builder for constructing instances of KindOfPhysicalProperty.
 */
public class KindOfPhysicalPropertyBuilder {

    private final KindOfPhysicalProperty kindOfPhysicalProperty;

    /**
     * Constructs a Builder for a new KindOfPhysicalProperty.
     *
     * @param iri IRI of the KindOfPhysicalProperty.
     */
    public KindOfPhysicalPropertyBuilder(final IRI iri) {
        kindOfPhysicalProperty = RdfClassServices.createKindOfPhysicalProperty(iri);
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfPhysicalPropertyBuilder has_Superclass(final Class clazz) {
        this.kindOfPhysicalProperty.addValue(HAS_SUPERCLASS, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfPhysicalPropertyBuilder member__Of(final Class clazz) {
        this.kindOfPhysicalProperty.addValue(MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} relationship type where a
     * {@link Class} may be a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} one or more
     * {@link ClassOfClass}.
     *
     * @param classOfClass The ClassOfClass.
     * @return This builder.
     */
    public final KindOfPhysicalPropertyBuilder member_Of(final ClassOfClass classOfClass) {
        this.kindOfPhysicalProperty.addValue(MEMBER_OF, new IRI(classOfClass.getId()));
        return this;
    }

    /**
     * Returns an instance of KindOfPhysicalProperty created from the properties set on this builder.
     *
     * @return The built KindOfPhysicalProperty.
     * @throws HqdmException If the KindOfPhysicalProperty is missing any mandatory properties.
     */
    public KindOfPhysicalProperty build() throws HqdmException {
        if (this.kindOfPhysicalProperty.hasValue(HAS_SUPERCLASS)
                && this.kindOfPhysicalProperty.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.kindOfPhysicalProperty.hasValue(MEMBER__OF)
                && this.kindOfPhysicalProperty.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.kindOfPhysicalProperty.hasValue(MEMBER_OF)
                && this.kindOfPhysicalProperty.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        return kindOfPhysicalProperty;
    }
}
