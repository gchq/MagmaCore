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

import static uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM.MEMBER__OF;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.Class;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of Thing.
 */
public class ThingBuilder {

    private final Thing thing;

    /**
     * Constructs a Builder for a new Thing.
     *
     * @param iri IRI of the Thing.
     */
    public ThingBuilder(final IRI iri) {
        thing = SpatioTemporalExtentServices.createThing(iri);
    }

    /**
     * A relationship type where a {@link Thing} may be a member of one or more {@link Class}.
     *
     * @param clazz Class of the Thing.
     * @return This builder.
     */
    public final ThingBuilder member__Of(final Class clazz) {
        this.thing.addValue(MEMBER__OF, clazz.getId());
        return this;
    }

    /**
     * Returns an instance of Thing created from the properties set on this builder.
     *
     * @return The built Thing.
     * @throws HqdmException If the Thing is missing any mandatory properties.
     */
    public Thing build() throws HqdmException {
        if (this.thing.hasValue(MEMBER__OF)
                && this.thing.value(MEMBER__OF).isEmpty()) {
            throw new HqdmException("Property Not Set: member__of");
        }
        return thing;
    }
}
