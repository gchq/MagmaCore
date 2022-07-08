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

import java.util.Objects;

/**
 * The base namespace of an {@link IRI}.
 */
public class IriBase {

    private String prefix;

    private String namespace;

    /**
     * Constructs a new blank IriBase.
     */
    public IriBase() {
    }

    /**
     * Constructs a new IriBase with a prefix and namespace.
     *
     * @param prefix    Prefix label for the vocabulary.
     * @param namespace URL of the namespace.
     */
    public IriBase(final String prefix, final String namespace) {
        this.prefix = prefix;
        this.namespace = namespace;
    }

    /**
     * Return the prefix label of the namespace.
     *
     * @return Namespace prefix.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Set the prefix label for the namespace.
     *
     * @param prefix Namespace prefix.
     */
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    /**
     * Return the URL of the namespace.
     *
     * @return Namespace URL.
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Set the URL of the namespace.
     *
     * @param namespace Namespace URL.
     */
    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof IriBase)) {
            return false;
        }
        final IriBase iri = (IriBase) object;
        return Objects.equals(namespace, iri.namespace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace);
    }
}
