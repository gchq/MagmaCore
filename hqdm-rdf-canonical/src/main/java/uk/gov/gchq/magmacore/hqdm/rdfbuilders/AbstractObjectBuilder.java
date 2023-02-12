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

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.AbstractObject;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdfservices.RdfSpatioTemporalExtentServices;

/**
 * Builder for constructing instances of AbstractObject.
 */
public class AbstractObjectBuilder {

    private final AbstractObject abstractObject;

    /**
     * Constructs a Builder for a new AbstractObject.
     *
     * @param iri IRI of the AbstractObject.
     */
    public AbstractObjectBuilder(final IRI iri) {
        this.abstractObject = RdfSpatioTemporalExtentServices.createAbstractObject(iri.getIri());
    }

    /**
     * A relationship type where a {@link uk.gov.gchq.magmacore.hqdm.model.Thing} may be a member of one
     * or more {@link Class}.
     *
     * @param clazz The Class.
     * @return This builder.
     */
    public final AbstractObjectBuilder member__Of(final Class clazz) {
        this.abstractObject.addValue(HQDM.MEMBER__OF, new IRI(clazz.getId()));
        return this;
    }

    /**
     * Returns an instance of AbstractObject created from the properties set on this builder.
     *
     * @return The built AbstractObject.
     * @throws HqdmException If the AbstractObject is missing any mandatory properties.
     */
    public AbstractObject build() throws HqdmException {
        if (this.abstractObject.hasValue(HQDM.MEMBER__OF)
                && this.abstractObject.value(HQDM.MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        return this.abstractObject;
    }
}
