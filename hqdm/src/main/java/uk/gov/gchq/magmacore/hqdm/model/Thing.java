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

package uk.gov.gchq.magmacore.hqdm.model;

import uk.gov.gchq.magmacore.hqdm.model.impl.ThingImpl;
import uk.gov.gchq.magmacore.hqdm.pojo.Top;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * Anything that exists, real or imagined.
 */
public interface Thing extends Top {
    /**
     * Create a Thing with an String.
     *
     * @param id the String.
     * @return a Thing instance.
     */
    public static Thing createThing(final IRI id) {
        return new ThingImpl(id);
    }
}
