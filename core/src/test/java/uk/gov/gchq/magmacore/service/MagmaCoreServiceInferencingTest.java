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

package uk.gov.gchq.magmacore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.junit.Test;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.database.query.QueryResultList;
import uk.gov.gchq.magmacore.database.validation.ValidationReportEntry;
import uk.gov.gchq.magmacore.exception.MagmaCoreException;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.service.transformation.DbChangeSet;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbTransformation;

/**
 * Check that {@link MagmaCoreService} works correctly.
 */
public class MagmaCoreServiceInferencingTest {
    private static final String RULE_SET = """

            @prefix ex: <http://example.com/test#> .

            [transitiveDependencies:
                (?x ex:depends_on ?y) (?y ex:depends_on ?z)
                ->
                (?x ex:depends_on ?z)
            ]

            [validationRule1:
                (?y rb:violation error('Object has ex:some_predicate', 'No objects should have ex:some_predicate', ?s))
                <-
                (?s ex:some_predicate ?value)
            ]
                                """;
    private static final IriBase TEST_BASE = new IriBase("test", "http://example.com/test#");
    private static final IRI DEPENDS_ON = new IRI(TEST_BASE, "depends_on");
    private static final IRI SOME_PREDICATE = new IRI(TEST_BASE, "some_predicate");

    /**
     * Test that inferencing can be performed using the MagmaCoreService.
     */
    @Test
    public void testInferencingSuccess() throws MagmaCoreException, FileNotFoundException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        SignPatternTestData.createSignPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Add some data.
        final IRI a = new IRI(TEST_BASE, "a");
        final IRI b = new IRI(TEST_BASE, "b");
        final IRI c = new IRI(TEST_BASE, "c");

        final List<DbChangeSet> changes = List.of(new DbChangeSet(
                List.of(),
                List.of(
                        new DbCreateOperation(a, DEPENDS_ON, b),
                        new DbCreateOperation(b, DEPENDS_ON, c))));
        final DbTransformation transform = new DbTransformation(changes);
        service.runInWriteTransaction(transform);

        // Use a CONSTRUCT query to subselect from the model.
        final String query = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o}";
        final MagmaCoreService inferencingSvc = service.applyInferenceRules(query, RULE_SET, false);

        // Query the database to check the result.
        final QueryResultList result = inferencingSvc
                .executeQuery("PREFIX ex: <http://example.com/test#> SELECT * WHERE {?s ex:depends_on ?o.}");

        // The result should be 3 since " a depends_on c" is inferred.
        assertEquals(3, result.getQueryResults().size());

        // Make sure there are no validation errors.
        final List<ValidationReportEntry> entries = inferencingSvc.validate(query, RULE_SET, true);
        assertEquals(0, entries.size());
    }

    /**
     * Test that inferencing can be performed using the MagmaCoreService.
     */
    @Test
    public void testInferencingValidationFail() throws MagmaCoreException, FileNotFoundException {

        // Create and populate an in-memory database.
        final MagmaCoreDatabase db = new MagmaCoreJenaDatabase();
        SignPatternTestData.createSignPattern(db);

        // Use it to create the services
        final MagmaCoreService service = new MagmaCoreService(db);

        // Add some data.
        final IRI a = new IRI(TEST_BASE, "a");
        final IRI b = new IRI(TEST_BASE, "b");
        final IRI c = new IRI(TEST_BASE, "c");

        final List<DbChangeSet> changes = List.of(new DbChangeSet(
                List.of(),
                List.of(
                        new DbCreateOperation(a, DEPENDS_ON, b),
                        new DbCreateOperation(b, DEPENDS_ON, c),
                        new DbCreateOperation(b, SOME_PREDICATE, "This predicate is invalid"))));
        final DbTransformation transform = new DbTransformation(changes);
        service.runInWriteTransaction(transform);

        // Use a CONSTRUCT query to subselect from the model.
        final String query = "CONSTRUCT {?s ?p ?o} WHERE {?s ?p ?o}";

        // Make sure there are no validation errors.
        final List<ValidationReportEntry> entries = service.validate(query, RULE_SET, false);

        assertEquals(1, entries.size());

        final ValidationReportEntry entry = entries.get(0);

        assertEquals("\"Object has ex:some_predicate\"", entry.type());
        assertEquals(
                "\"No objects should have ex:some_predicate\"\nCulprit = *\nImplicated node: <http://example.com/test#b>\n",
                entry.description());
        assertTrue(entry.additionalInformation() instanceof ResourceImpl);

        final Resource resource = (Resource) entry.additionalInformation();
        assertEquals("*", resource.toString());
    }
}
