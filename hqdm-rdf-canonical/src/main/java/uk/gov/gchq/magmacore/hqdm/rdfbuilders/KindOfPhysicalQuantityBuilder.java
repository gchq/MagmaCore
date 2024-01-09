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
import uk.gov.gchq.magmacore.hqdm.model.KindOfPhysicalQuantity;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfClassServices;

/**
 * Builder for constructing instances of KindOfPhysicalQuantity.
 */
public class KindOfPhysicalQuantityBuilder {

    private final KindOfPhysicalQuantity kindOfPhysicalQuantity;

    /**
     * Constructs a Builder for a new KindOfPhysicalQuantity.
     *
     * @param iri IRI of the KindOfPhysicalQuantity.
     */
    public KindOfPhysicalQuantityBuilder(final IRI iri) {
        kindOfPhysicalQuantity = RdfClassServices.createKindOfPhysicalQuantity(iri);
    }

    /**
     * A relationship type where each {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the
     * {@link Class} is a {@link uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM#MEMBER_OF} the superclass.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfPhysicalQuantityBuilder has_Superclass(final Class clazz) {
        this.kindOfPhysicalQuantity.addValue(HAS_SUPERCLASS, new IRI(clazz.getId()));
        return this;
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final KindOfPhysicalQuantityBuilder member__Of(final Class clazz) {
        this.kindOfPhysicalQuantity.addValue(MEMBER__OF, new IRI(clazz.getId()));
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
    public final KindOfPhysicalQuantityBuilder member_Of(final ClassOfClass classOfClass) {
        this.kindOfPhysicalQuantity.addValue(MEMBER_OF, new IRI(classOfClass.getId()));
        return this;
    }

    /**
     * Returns an instance of KindOfPhysicalQuantity created from the properties set on this builder.
     *
     * @return The built KindOfPhysicalQuantity.
     * @throws HqdmException If the KindOfPhysicalQuantity is missing any mandatory properties.
     */
    public KindOfPhysicalQuantity build() throws HqdmException {
        if (this.kindOfPhysicalQuantity.hasValue(HAS_SUPERCLASS)
                && this.kindOfPhysicalQuantity.value(HAS_SUPERCLASS).isEmpty()) {
            throw new HqdmException("Property Not Set: has_superclass");
        }
        if (this.kindOfPhysicalQuantity.hasValue(MEMBER__OF)
                && this.kindOfPhysicalQuantity.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        if (this.kindOfPhysicalQuantity.hasValue(MEMBER_OF)
                && this.kindOfPhysicalQuantity.value(MEMBER_OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member_of");
        }
        return kindOfPhysicalQuantity;
    }
}
