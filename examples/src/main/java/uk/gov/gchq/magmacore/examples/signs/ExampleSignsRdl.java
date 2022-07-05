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

package uk.gov.gchq.magmacore.examples.signs;

import static uk.gov.gchq.magmacore.examples.util.DemoUtils.REF_BASE;
import static uk.gov.gchq.magmacore.examples.util.DemoUtils.USER_BASE;
import static uk.gov.gchq.magmacore.util.UID.uid;

import java.util.List;

import uk.gov.gchq.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.iri.RDFS;
import uk.gov.gchq.magmacore.service.transformation.DbChangeSet;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;

/**
 * Example signs reference data.
 */
public class ExampleSignsRdl {

    /**
     * Create a DbChangeSet that adds the RDL.
     *
     * @return {@link DbChangeSet}.
     */
    public static DbChangeSet createRefDataObjects() {

        // Create new unique IRIs for all the objects we need to create.
        final IRI urlPattern = new IRI(REF_BASE, uid());
        final IRI description = new IRI(REF_BASE, uid());
        final IRI englishSpeakers = new IRI(USER_BASE, uid());

        // Add DbCreateOperations to create the objects and their properties.
        final List<DbCreateOperation> creates = List.of(
                new DbCreateOperation(urlPattern, RDFS.RDF_TYPE, HQDM.PATTERN.getIri()),
                new DbCreateOperation(urlPattern, HQDM.ENTITY_NAME, "URL Pattern"),

                new DbCreateOperation(description, RDFS.RDF_TYPE, HQDM.DESCRIPTION.getIri()),
                new DbCreateOperation(description, HQDM.ENTITY_NAME, "Description By URL"),

                // Create the community that recognizes the signs.
                new DbCreateOperation(englishSpeakers, RDFS.RDF_TYPE, HQDM.RECOGNIZING_LANGUAGE_COMMUNITY.getIri()),
                new DbCreateOperation(englishSpeakers, HQDM.ENTITY_NAME, "English Speakers"),

                // Link the description to the Pattern.
                new DbCreateOperation(description, HQDM.CONSISTS_OF_BY_CLASS, urlPattern.getIri())

        );

        // Put the operations in a change set and return it.
        return new DbChangeSet(List.of(), creates);
    }
}
