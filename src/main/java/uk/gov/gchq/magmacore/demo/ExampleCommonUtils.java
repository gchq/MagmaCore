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

package uk.gov.gchq.magmacore.demo;

import static uk.gov.gchq.magmacore.util.UID.uid;

import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.IriBase;

/**
 * Utilities for creating example data for the demos.
 */
public class ExampleCommonUtils {

    /** IriBase for User data. */
    public static final IriBase USER_BASE = new IriBase("user", "http://www.semanticweb.org/magma-core/user#");

    /**
     * Create a new IRI in the USER_BASE namespace.
     *
     * @return {@link IRI}.
     */
    public static IRI mkUserBaseIri() {
        return new IRI(USER_BASE, uid());
    }

    /** IriBase for Reference Data Library. */
    public static final IriBase REF_BASE = new IriBase("rdl", "http://www.semanticweb.org/magma-core/rdl#");

    /**
     * Create a new IRI in the REF_BASE namespace.
     *
     * @return {@link IRI}.
     */
    public static IRI mkRefBaseIri() {
        return new IRI(REF_BASE, uid());
    }
}
