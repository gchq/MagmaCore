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

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
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

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.rdf.HqdmObjectFactory;
import uk.gov.gchq.hqdm.rdf.iri.HqdmIri;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.rdf.util.Pair;
import uk.gov.gchq.magmacore.query.QueryResult;
import uk.gov.gchq.magmacore.query.QueryResultList;

/**
 * Connection to a remote SPARQL endpoint.
 */
public class MagmaCoreRemoteSparqlDatabase implements MagmaCoreDatabase {

    private final RDFConnection connection;

    /**
     * Constructor to create a connection to a SPARQL endpoint.
     *
     * @param serviceUrl the URL String of the SPARQL update endpoint
     */
    public MagmaCoreRemoteSparqlDatabase(final String serviceUrl) {
        connection = RDFConnectionRemote.newBuilder().destination(serviceUrl).queryEndpoint("query")
                .updateEndpoint("update").triplesFormat(RDFFormat.RDFJSON).build();
    }

    /**
     * Constructor to create a connection to a SPARQL endpoint and load it with a dataset.
     *
     * @param serviceUrl the URL String of the SPARQL update endpoint
     * @param dataset    the Dataset to be loaded into the database
     */
    public MagmaCoreRemoteSparqlDatabase(final String serviceUrl, final Dataset dataset) {
        this(serviceUrl);

        connection.load(dataset.getDefaultModel());
    }

    /**
     * {@inheritDoc}
     */
    public final void begin() {
        if (!connection.isInTransaction()) {
            connection.begin();
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void abort() {
        if (connection.isInTransaction()) {
            connection.abort();
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

        final Resource resource = model.createResource(object.getId());

        object.getPredicates().forEach((iri, predicates) -> predicates.forEach(
                predicate -> resource.addProperty(model.createProperty(iri.toString()), predicate.toString())));

        connection.load(model);
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
    public List<Thing> findByPredicateIriOnly(final HqdmIri predicateIri) {
        final String query = "SELECT ?s ?p ?o WHERE {{select ?s ?p ?o where { ?s ?p ?o.}}{select ?s where {?s <"
                + predicateIri.toString() + "> ?o.}}}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriAndStringValue(final IRI predicateIri, final String value) {
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
    protected QueryResultList executeQuery(final String sparqlQueryString) {
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
                queryResult.set(varName, node);
            }
            queryResults.add(queryResult);
        }
        queryExec.close();
        return queryResultList;
    }

    private final List<Thing> toTopObjects(final QueryResultList queryResultsList) {
        final Map<String, List<Pair<String, String>>> objectMap = new HashMap<>();
        final String subjectVarName = queryResultsList.getVarNames().get(0);
        final String predicateVarName = queryResultsList.getVarNames().get(1);
        final String objectVarName = queryResultsList.getVarNames().get(2);

        // Create a map of the triples for each unique subject IRI
        final List<QueryResult> queryResults = queryResultsList.getQueryResults();
        queryResults.forEach(queryResult -> {
            final String subjectValue = queryResult.get(subjectVarName).toString();
            final String predicateValue = queryResult.get(predicateVarName).toString();
            final String objectValue = queryResult.get(objectVarName).toString();

            List<Pair<String, String>> dataModelObject = objectMap.get(subjectValue);
            if (dataModelObject == null) {
                dataModelObject = new ArrayList<>();
                objectMap.put(subjectValue, dataModelObject);
            }
            dataModelObject.add(new Pair<>(predicateValue, objectValue));
        });

        return objectMap.entrySet().stream().map(entry -> HqdmObjectFactory.create(entry.getKey(), entry.getValue()))
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
}
