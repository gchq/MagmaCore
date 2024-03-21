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
import org.apache.jena.query.TxnType;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.util.PrintUtil;

import uk.gov.gchq.magmacore.database.query.QueryResult;
import uk.gov.gchq.magmacore.database.query.QueryResultList;
import uk.gov.gchq.magmacore.database.query.RdfNode;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.HqdmObjectFactory;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.rdf.util.Pair;
import uk.gov.gchq.magmacore.service.transformation.DbCreateOperation;
import uk.gov.gchq.magmacore.service.transformation.DbDeleteOperation;

/**
 * Connection to a remote SPARQL endpoint.
 */
public class MagmaCoreRemoteSparqlDatabase implements MagmaCoreDatabase {

    private final RDFConnection connection;

    /**
     * Constructs a MagmaCoreRemoteSparqlDatabase connection to a SPARQL server.
     *
     * @param serviceUrl The URL of the SPARQL update endpoint.
     */
    public MagmaCoreRemoteSparqlDatabase(final String serviceUrl) {
        connection = RDFConnectionRemote.newBuilder().destination(serviceUrl).queryEndpoint("query")
                .updateEndpoint("update").triplesFormat(RDFFormat.RDFJSON).build();
    }

    /**
     * Constructs a MagmaCoreRemoteSparqlDatabase connection to a SPARQL server and populates it with
     * the dataset.
     *
     * @param serviceUrl The URL String of the SPARQL update endpoint.
     * @param dataset    The Dataset to be loaded into the database.
     */
    public MagmaCoreRemoteSparqlDatabase(final String serviceUrl, final Dataset dataset) {
        this(serviceUrl);

        connection.load(dataset.getDefaultModel());
    }

    /**
     * {@inheritDoc}
     */
    public final void beginRead() {
        if (!connection.isInTransaction()) {
            connection.begin(TxnType.READ);
        } else {
            throw new IllegalStateException("Already in a transaction");
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void beginWrite() {
        if (!connection.isInTransaction()) {
            connection.begin(TxnType.WRITE);
        } else {
            throw new IllegalStateException("Already in a transaction");
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void abort() {
        if (connection.isInTransaction()) {
            connection.abort();
        } else {
            throw new IllegalStateException("Not in a transaction");
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void drop() {
        final String drop = "drop all";
        executeUpdate(drop);
    }

    /**
     * Commit the current transaction.
     */
    public final void commit() {
        if (connection.isInTransaction()) {
            connection.commit();
        } else {
            throw new IllegalStateException("Not in a transaction");
        }
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

        final Model model = ModelFactory.createDefaultModel();

        final Resource resource = model.createResource(object.getId().getIri());

        object.getPredicates().forEach((iri, predicates) -> predicates.forEach(value -> {
            if (value instanceof IRI) {
                final Resource valueResource = model.createResource(value.toString());
                resource.addProperty(model.createProperty(iri.toString()), valueResource);
            } else {
                resource.addProperty(model.createProperty(iri.toString()), value.toString());
            }
        }));
        connection.load(model);
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

        connection.load(forCreation);
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
        if (deletes.isEmpty()) {
            return;
        }

        final StringBuilder statement = new StringBuilder();
        statement.append("delete data { ");
        deletes.forEach(delete -> {
            statement.append("<");
            statement.append(delete.subject.getIri());
            statement.append("> ");

            statement.append("<");
            statement.append(delete.predicate.getIri());
            statement.append("> ");

            if (delete.object instanceof IRI) {
                statement.append("<");
                statement.append(delete.object.toString());
                statement.append(">.");
            } else {
                statement.append("\"");
                statement.append(delete.object.toString());
                statement.append("\".");
            }
        });
        statement.append("}");

        executeUpdate(statement.toString());
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
        final String query;

        if (value instanceof IRI) {
            query = "SELECT ?s ?p ?o WHERE { ?s ?p ?o.  ?s <" + predicateIri.toString() + "> <" + value
                    + ">.}";
        } else {
            query = "SELECT ?s ?p ?o WHERE { ?s ?p ?o.  ?s <" + predicateIri.toString() + "> \"\"\"" + value
                    + "\"\"\".}";
        }
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
     * @return a List of {@link Thing}
     */
    public List<Thing> executeConstruct(final String sparqlQueryString) {
        final QueryExecution queryExec = connection.query(sparqlQueryString);

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

        connection.update(statement);

    }

    /**
     * Perform a SPARQL query on the dataset.
     *
     * @param sparqlQueryString SPARQL query to execute.
     * @return Results of the query.
     */
    public QueryResultList executeQuery(final String sparqlQueryString) {
        final QueryExecution queryExec = connection.query(sparqlQueryString);
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
                if (node instanceof Literal literal) {
                    queryResult.set(varName, new uk.gov.gchq.magmacore.database.query.Literal(literal.toString()));
                } else if (node instanceof Resource resource) {
                    queryResult.set(varName, new uk.gov.gchq.magmacore.database.query.Resource(resource.toString()));
                }
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
        final Map<RdfNode, List<Pair<IRI, Object>>> objectMap = new HashMap<>();
        final List<String> varNames = (List<String>) queryResultsList.getVarNames();

        final String subjectVarName = varNames.get(0);
        final String predicateVarName = varNames.get(1);
        final String objectVarName = varNames.get(2);

        // Create a map of the triples for each unique subject IRI
        final List<QueryResult> queryResults = queryResultsList.getQueryResults();
        queryResults.forEach(queryResult -> {
            final RdfNode subjectValue = queryResult.get(subjectVarName);
            final RdfNode predicateValue = queryResult.get(predicateVarName);
            final RdfNode objectValue = queryResult.get(objectVarName);

            List<Pair<IRI, Object>> dataModelObject = objectMap.get(subjectValue);
            if (dataModelObject == null) {
                dataModelObject = new ArrayList<>();
                objectMap.put(
                        new uk.gov.gchq.magmacore.database.query.Resource(subjectValue.toString()), dataModelObject);
            }
            if (objectValue instanceof uk.gov.gchq.magmacore.database.query.Literal) {
                dataModelObject.add(new Pair<>(new IRI(predicateValue.toString()), objectValue.toString()));
            } else {
                dataModelObject.add(new Pair<>(new IRI(predicateValue.toString()), new IRI(objectValue.toString())));
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
        final Dataset dataset = connection.fetchDataset();
        final Model model = dataset.getDefaultModel();
        final StmtIterator statements = model.listStatements();

        while (statements.hasNext()) {
            final Statement statement = statements.nextStatement();
            out.println(" - " + PrintUtil.print(statement));
        }
    }

    /**
     * Dump the contents of the collection as text in specified RDF language.
     *
     * @param out      Output stream to dump to.
     * @param language RDF language syntax to output data as.
     */
    public final void dump(final PrintStream out, final Lang language) {
        final Dataset dataset = connection.fetchDataset();
        RDFDataMgr.write(out, dataset.getDefaultModel(), language);
    }

    /**
     * Import data into the model.
     *
     * @param in       {@link InputStream} to read from.
     * @param language RDF language syntax to output data as.
     */
    public final void load(final InputStream in, final Lang language) {
        beginWrite();
        final Dataset dataset = connection.fetchDataset();
        final Model model = dataset.getDefaultModel();
        RDFDataMgr.read(model, in, language);
        connection.load(model);
        commit();
    }
}
