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

package uk.gov.gchq.magmacore.hqdm.rdf.iri;

import uk.gov.gchq.magmacore.hqdm.rdf.exception.IriException;

/**
 * An implementation of Internationalized Resource Identifiers for defining HQDM entities and
 * relationship types.
 */
public class HqdmIri extends IRI {

    /**
     * Constructs a new IRI to define a HQDM resource.
     *
     * @param base     IRI base namespace.
     * @param resource HQDM resource name.
     */
    public HqdmIri(final IriBase base, final String resource) {
        super(base, resource);
    }

    /**
     * Constructs a new IRI from a string to define a HQDM resource.
     *
     * @param iri IRI string.
     * @throws IriException If the IRI string is malformed.
     */
    public HqdmIri(final String iri) throws IriException {
        super(iri);
    }
}
