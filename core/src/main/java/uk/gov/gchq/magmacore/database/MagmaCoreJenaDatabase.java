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

package uk.gov.gchq.magmacore.database;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.tdb2.TDB2Factory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.util.PrintUtil;

import uk.gov.gchq.magmacore.database.query.QueryResult;
import uk.gov.gchq.magmacore.database.query.QueryResultList;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.HqdmObjectFactory;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IriBase;
import uk.gov.gchq.magmacore.hqdm.rdf.util.Pair;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbDeleteOperation;

/**
 * Apache Jena triplestore to store HQDM objects as RDF triples either as an in-memory Jena dataset
 * or persistent TDB triplestore.
 */
public class MagmaCoreJenaDatabase implements MagmaCoreDatabase {

    private final Dataset dataset;

    /**
     * Constructs a MagmaCoreJenaDatabase with a new in-memory Jena dataset.
     */
    public MagmaCoreJenaDatabase() {
        dataset = TDB2Factory.createDataset();
    }

    /**
     * Constructs a MagmaCoreJenaDatabase with an existing Jena dataset.
     *
     * @param dataset Existing in-memory Jena dataset.
     */
    public MagmaCoreJenaDatabase(final Dataset dataset) {
        this.dataset = dataset;
    }

    /**
     * Constructs a MagmaCoreJenaDatabase connected to a remote Jena TDB2 dataset.
     *
     * @param location URL of the remote dataset.
     */
    public MagmaCoreJenaDatabase(final String location) {
        dataset = TDB2Factory.connectDataset(location);
    }

    /**
     * Get the Jena {@link dataset}.
     *
     * @return The Jena {@link dataset}.
     */
    public final Dataset getDataset() {
        return dataset;
    }

    /**
     * Register a new prefix/namespace mapping which will be used to shorten the print strings for
     * resources in known namespaces.
     *
     * @param base {@link IriBase} to register.
     */
    public void register(final IriBase base) {
        PrintUtil.registerPrefix(base.getPrefix(), base.getNamespace());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void begin() {
        if (!dataset.isInTransaction()) {
            dataset.begin();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commit() {
        if (dataset.isInTransaction()) {
            dataset.commit();
            dataset.end();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void abort() {
        if (dataset.isInTransaction()) {
            dataset.abort();
            dataset.end();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drop() {
        final String drop = "drop all";
        executeUpdate(drop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Thing get(final IRI iri) {
        final String query = String.format("SELECT (<%1$s> as ?s) ?p ?o WHERE {<%1$s> ?p ?o.}", iri.toString());
        final QueryResultList list = executeQuery(query);
        final List<Thing> objects = toTopObjects(list);
        if (!objects.isEmpty()) {
            return objects.get(0);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(final Thing object) {
        final Model defaultModel = dataset.getDefaultModel();

        final Resource resource = defaultModel.createResource(object.getId());

        object.getPredicates().forEach((iri, predicates) -> predicates.forEach(value -> {
            if (value instanceof IRI) {
                final Resource valueResource = defaultModel.createResource(value.toString());
                resource.addProperty(defaultModel.createProperty(iri.toString()), valueResource);
            } else {
                resource.addProperty(defaultModel.createProperty(iri.toString()), value.toString());
            }
        }));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(final List<DbCreateOperation> creates) {
        final Model forCreation = ModelFactory.createDefaultModel();

        creates.forEach(create -> {
            final Resource s = forCreation.createResource(create.subject.getIri());
            final Property p = forCreation.createProperty(create.predicate.getIri());
            final Object value = create.object;

            final RDFNode o;
            if (value instanceof IRI) {
                o = forCreation.createResource(value.toString());
            } else {
                o = forCreation.createLiteral(value.toString());
            }
            forCreation.add(forCreation.createStatement(s, p, o));
        });

        final Model model = dataset.getDefaultModel();

        model.add(forCreation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Thing object) {
        delete(object);
        create(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Thing object) {
        executeUpdate(String.format("delete {<%s> ?p ?o} WHERE {<%s> ?p ?o}", object.getId(), object.getId()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final List<DbDeleteOperation> deletes) {
        final Model forDeletion = ModelFactory.createDefaultModel();

        deletes.forEach(delete -> {
            final Resource s = forDeletion.createResource(delete.subject.getIri());
            final Property p = forDeletion.createProperty(delete.predicate.getIri());
            final Object value = delete.object;

            final RDFNode o;
            if (value instanceof IRI) {
                o = forDeletion.createResource(value.toString());
            } else {
                o = forDeletion.createLiteral(value.toString());
            }

            forDeletion.add(forDeletion.createStatement(s, p, o));
        });

        final Model model = dataset.getDefaultModel();

        model.remove(forDeletion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIri(final IRI predicateIri, final IRI objectIri) {
        final String query = "SELECT ?s ?p ?o WHERE {?s ?p ?o. ?s <" + predicateIri.toString() + "> <"
                + objectIri.toString() + ">.}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriOnly(final IRI predicateIri) {
        final String query = "SELECT ?s ?p ?o WHERE {{select ?s ?p ?o where { ?s ?p ?o.}}{select ?s where {?s <"
                + predicateIri.toString() + "> ?o.}}}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriAndValue(final IRI predicateIri, final Object value) {
        final String query = "SELECT ?s ?p ?o WHERE { ?s ?p ?o.  ?s <" + predicateIri.toString() + "> \"\"\"" + value
                + "\"\"\".}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriAndStringCaseInsensitive(final IRI predicateIri, final String value) {
        final String query = "SELECT ?s ?p ?o WHERE {{ SELECT ?s ?p ?o where { ?s ?p ?o.}}{select ?s where {?s <"
                + predicateIri.toString() + "> ?o. BIND(LCASE(?o) AS ?lcase) FILTER(?lcase= \"\"\"" + value
                + "\"\"\")}}}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    /**
     * Execute a CONSTRUCT query.
     *
     * @param sparqlQueryString a CONSTRUCT query {@link String}
     * @return a {@link List} of {@link Thing}
     */
    public List<Thing> executeConstruct(final String sparqlQueryString) {
        final Query query = QueryFactory.create(sparqlQueryString);
        final QueryExecution queryExec = QueryExecutionFactory.create(query, dataset);

        final Model model = queryExec.execConstruct();
        final Query selectAllQuery = QueryFactory.create("select ?s ?p ?o where { ?s ?p ?o. }");
        final QueryExecution selectAllQueryExec = QueryExecutionFactory.create(selectAllQuery, model);
        return toTopObjects(getQueryResultList(selectAllQueryExec));
    }

    /**
     * Perform an update query on the dataset.
     *
     * @param statement SPARQL update query to execute.
     */
    protected void executeUpdate(final String statement) {
        final UpdateRequest update = UpdateFactory.create(statement);
        final UpdateProcessor updateExec = UpdateExecutionFactory.create(update, dataset);
        updateExec.execute();
    }

    /**
     * Perform a SPARQL query on the dataset.
     *
     * @param sparqlQueryString SPARQL query to execute.
     * @return Results of the query.
     */
    public QueryResultList executeQuery(final String sparqlQueryString) {
        final Query query = QueryFactory.create(sparqlQueryString);
        final QueryExecution queryExec = QueryExecutionFactory.create(query, dataset);
        return getQueryResultList(queryExec);
    }

    /**
     * Execute a SPARQL query and construct a list of HQDM objects from the resulting RDF triples.
     *
     * @param queryExec SPARQL query to execute.
     * @return Results of the query.
     */
    private final QueryResultList getQueryResultList(final QueryExecution queryExec) {
        final ResultSet resultSet = queryExec.execSelect();
        final List<QueryResult> queryResults = new ArrayList<>();
        final QueryResultList queryResultList = new QueryResultList(resultSet.getResultVars(), queryResults);
        while (resultSet.hasNext()) {
            final QuerySolution querySolution = resultSet.next();
            final Iterator<String> varNames = querySolution.varNames();
            final QueryResult queryResult = new QueryResult();

            while (varNames.hasNext()) {
                final String varName = varNames.next();
                final RDFNode node = querySolution.get(varName);
                queryResult.set(varName, node);
            }
            queryResults.add(queryResult);
        }
        queryExec.close();
        return queryResultList;
    }

    /**
     * Convert a {@link QueryResultList} to a {@link List} of {@link Thing}.
     *
     * @param queryResultsList {@link QueryResultList}
     * @return a {@link List} of {@link Thing}
     */
    public final List<Thing> toTopObjects(final QueryResultList queryResultsList) {
        final Map<RDFNode, List<Pair<Object, Object>>> objectMap = new HashMap<>();
        final List<String> varNames = (List<String>) queryResultsList.getVarNames();

        final String subjectVarName = varNames.get(0);
        final String predicateVarName = varNames.get(1);
        final String objectVarName = varNames.get(2);

        // Create a map of the triples for each unique subject IRI
        final List<QueryResult> queryResults = queryResultsList.getQueryResults();
        queryResults.forEach(queryResult -> {
            final RDFNode subjectValue = queryResult.get(subjectVarName);
            final RDFNode predicateValue = queryResult.get(predicateVarName);
            final RDFNode objectValue = queryResult.get(objectVarName);

            List<Pair<Object, Object>> dataModelObject = objectMap.get(subjectValue);
            if (dataModelObject == null) {
                dataModelObject = new ArrayList<>();
                objectMap.put(subjectValue, dataModelObject);
            }
            if (objectValue instanceof Literal) {
                dataModelObject.add(new Pair<>(new IRI(predicateValue.toString()), objectValue.toString()));
            } else if (objectValue instanceof Resource) {
                dataModelObject.add(new Pair<>(new IRI(predicateValue.toString()), new IRI(objectValue.toString())));
            } else {
                throw new RuntimeException("objectValue is of unknown type: " + objectValue.getClass());
            }
        });

        return objectMap
                .entrySet()
                .stream()
                .map(entry -> HqdmObjectFactory.create(new IRI(entry.getKey().toString()), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dump(final PrintStream out) {
        begin();
        final Model model = dataset.getDefaultModel();
        final StmtIterator statements = model.listStatements();

        while (statements.hasNext()) {
            final Statement statement = statements.nextStatement();
            out.println(" - " + PrintUtil.print(statement));
        }
        abort();
    }

    /**
     * Dump the contents of the collection as text in specified RDF language.
     *
     * @param out      Output stream to dump to.
     * @param language RDF language syntax to output data as.
     */
    public final void dump(final PrintStream out, final Lang language) {
        begin();
        RDFDataMgr.write(out, dataset.getDefaultModel(), language);
        abort();
    }

    /**
     * Import data into the model.
     *
     * @param in       {@link InputStream} to read from.
     * @param language RDF language syntax to output data as.
     */
    public final void load(final InputStream in, final Lang language) {
        begin();
        final Model model = dataset.getDefaultModel();
        RDFDataMgr.read(model, in, language);
        commit();
    }
}
