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

package uk.gov.gchq.hqdm.rdf.iri;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import uk.gov.gchq.hqdm.rdf.exception.IriException;

/**
 * An implementation of Internationalized Resource Identifiers.
 */
public class IRI {

    private String resource;

    private String iri;

    /**
     * Constructs a new IRI from a base namespace and resource name.
     *
     * @param base     IRI base namespace.
     * @param resource Resource name.
     */
    public IRI(final IriBase base, final String resource) {
        fromString(base.getNamespace() + resource);
    }

    /**
     * Constructs a new IRI from a string.
     *
     * @param iri IRI string.
     * @throws IriException If the IRI string is malformed.
     */
    public IRI(final String iri) throws IriException {
        fromString(iri);
    }

    /**
     * The name of the resource.
     *
     * @return Resource name.
     */
    public String getResource() {
        return resource;
    }

    /**
     * The full IRI string of the resource.
     *
     * @return IRI string.
     */
    public String getIri() {
        return iri;
    }

    /**
     * Convert a {@link String} to an IRI.
     *
     * @param iri {@link String}
     * @throws IriException if the {@link String} is not a valid URL.
     */
    private void fromString(final String iri) throws IriException {
        try {
            new URL(iri);
            this.iri = iri;
        } catch (final MalformedURLException m) {
            throw new IriException("Cannot parse IRI: " + iri);
        }

        int index = iri.lastIndexOf('#');
        if (index < 0) {
            index = iri.lastIndexOf('/');
        }
        if (index < 0) {
            throw new IriException("Cannot parse IRI: " + iri);
        } else {
            this.resource = iri.substring(index + 1);
        }

    }

    /**
     * Returns the full IRI string value.
     *
     * @return IRI string.
     */
    @Override
    public String toString() {
        return getIri();
    }

    /**
     * Compare to another {@code Object}.
     *
     * @param object Object to compare.
     * @return True if
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof IRI)) {
            return false;
        }
        final IRI other = (IRI) object;
        return Objects.equals(this.iri, other.iri);
    }

    @Override
    public int hashCode() {
        return iri.hashCode();
    }
}
