package uk.gov.gchq.magmacore.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.jena.riot.Lang;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.GraphQueryResult;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryException;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.sail.nativerdf.NativeStore;

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
 * RDF4J Sail Repository with In-Memory data store.
 */
public class  MagmaCoreSailDatabase implements MagmaCoreDatabase {

    private final RepositoryConnection repository;

    public MagmaCoreSailDatabase() {
        repository = new SailRepository(new MemoryStore()).getConnection();
    }

    public MagmaCoreSailDatabase(final File dataDir) {
        repository = new SailRepository(new NativeStore(dataDir)).getConnection();
    }

    @Override
    public void beginRead() {
        repository.begin();
    }

    @Override
    public void beginWrite() {
        repository.begin();
    }

    @Override
    public void commit() {
        repository.commit();
    }

    @Override
    public void abort() {
        repository.rollback();
    }

    @Override
    public void drop() {
        repository.clear();
    }

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

    @Override
    public void create(final Thing object) {
        final org.eclipse.rdf4j.model.IRI subject = Values.iri(object.getId().getIri());

        object.getPredicates().forEach((iri, predicates) -> predicates.forEach(value -> {
            if (value instanceof IRI) {
                final Resource valueResource = Values.iri(value.toString());
                repository.add(subject, Values.iri(iri.getIri()), valueResource);
            } else {
                final Literal valueResource = Values.literal(value.toString());
                repository.add(subject, Values.iri(iri.getIri()), valueResource);
            }
        }));
    }

    @Override
    public void create(final List<DbCreateOperation> creates) {
        creates.forEach(create -> {
            final org.eclipse.rdf4j.model.IRI subject = Values.iri(create.subject.getIri());
            final org.eclipse.rdf4j.model.IRI predicate = Values.iri(create.predicate.getIri());
            final Value object = (create.object instanceof IRI iri)
                ? Values.iri(iri.getIri())
                : Values.literal(create.object);
            repository.add(subject, predicate, object);
        });
    }

    @Override
    public void update(final Thing object) {
        delete(object);
        create(object);
    }

    @Override
    public void delete(final Thing object) {
        executeUpdate(String.format("delete {<%s> ?p ?o} WHERE {<%s> ?p ?o}", object.getId(), object.getId()));
    }

    @Override
    public void delete(final List<DbDeleteOperation> deletes) {
        deletes.forEach(delete -> {
            final org.eclipse.rdf4j.model.IRI subject = Values.iri(delete.subject.getIri());
            final org.eclipse.rdf4j.model.IRI predicate = Values.iri(delete.predicate.getIri());
            final Value object = (delete.object instanceof IRI iri)
                ? Values.iri(iri.getIri())
                : Values.literal(delete.object);
            repository.remove(subject, predicate, object);
        });
    }

    @Override
    public List<Thing> findByPredicateIri(final IRI predicateIri, final IRI objectIri) {
        final String query = "SELECT ?s ?p ?o WHERE {?s ?p ?o. ?s <" + predicateIri.toString() + "> <"
                + objectIri.toString() + ">.}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    @Override
    public List<Thing> findByPredicateIriOnly(final IRI predicateIri) {
        final String query = "SELECT ?s ?p ?o WHERE {{select ?s ?p ?o where { ?s ?p ?o.}}{select ?s where {?s <"
                + predicateIri.toString() + "> ?o.}}}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

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

    @Override
    public List<Thing> findByPredicateIriAndStringCaseInsensitive(final IRI predicateIri, final String value) {
        final String query = "SELECT ?s ?p ?o WHERE {{ SELECT ?s ?p ?o where { ?s ?p ?o.}}{select ?s where {?s <"
                + predicateIri.toString() + "> ?o. BIND(LCASE(?o) AS ?lcase) FILTER(?lcase= \"\"\"" + value
                + "\"\"\")}}}";
        final QueryResultList list = executeQuery(query);
        return toTopObjects(list);
    }

    @Override
    public void dump(final PrintStream out) {
        final RDFWriter writer = Rio.createWriter(RDFFormat.TURTLE, out);
        repository.prepareGraphQuery(QueryLanguage.SPARQL, "CONSTRUCT {?s ?p ?o } WHERE {?s ?p ?o } ").evaluate(writer);
    }

    @Override
    public void dump(final PrintStream out, final Lang language) {
        final RDFWriter writer;

        if (language == Lang.TURTLE) {
            writer = Rio.createWriter(RDFFormat.TURTLE, out);
        } else if (language == Lang.N3) {
            writer = Rio.createWriter(RDFFormat.N3, out);
        } else if (language == Lang.NQ) {
            writer = Rio.createWriter(RDFFormat.NQUADS, out);
        } else if (language == Lang.NT) {
            writer = Rio.createWriter(RDFFormat.NTRIPLES, out);
        } else if (language == Lang.TRIG) {
            writer = Rio.createWriter(RDFFormat.TRIG, out);
        } else if (language == Lang.TRIX) {
            writer = Rio.createWriter(RDFFormat.TRIX, out);
        } else if (language == Lang.JSONLD) {
            writer = Rio.createWriter(RDFFormat.JSONLD, out);
        } else if (language == Lang.RDFXML) {
            writer = Rio.createWriter(RDFFormat.RDFXML, out);
        } else if (language == Lang.NQUADS) {
            writer = Rio.createWriter(RDFFormat.NQUADS, out);
        } else if (language == Lang.NTRIPLES) {
            writer = Rio.createWriter(RDFFormat.NTRIPLES, out);
        } else {
            throw new UnsupportedOperationException(language.toString() + " is not supported.");
        }

        beginRead();
        repository.prepareGraphQuery(QueryLanguage.SPARQL, "CONSTRUCT {?s ?p ?o } WHERE {?s ?p ?o } ").evaluate(writer);
        abort();
    }

    @Override
    public void load(final InputStream in, final Lang language) {
        final RDFFormat format;

        if (language == Lang.TURTLE) {
            format = RDFFormat.TURTLE;
        } else if (language == Lang.N3) {
            format = RDFFormat.N3;
        } else if (language == Lang.NQ) {
            format = RDFFormat.NQUADS;
        } else if (language == Lang.NT) {
            format = RDFFormat.NTRIPLES;
        } else if (language == Lang.TRIG) {
            format = RDFFormat.TRIG;
        } else if (language == Lang.TRIX) {
            format = RDFFormat.TRIX;
        } else if (language == Lang.JSONLD) {
            format = RDFFormat.JSONLD;
        } else if (language == Lang.RDFXML) {
            format = RDFFormat.RDFXML;
        } else if (language == Lang.NQUADS) {
            format = RDFFormat.NQUADS;
        } else if (language == Lang.NTRIPLES) {
            format = RDFFormat.NTRIPLES;
        } else {
            throw new UnsupportedOperationException(language.toString() + " is not supported.");
        }

        beginWrite();
        try {
            repository.add(in, null, format);
        } catch (RDFParseException | RepositoryException | IOException e) {
            e.printStackTrace();
        }
        commit();
    }

    /**
     * Perform an update query on the dataset.
     *
     * @param statement SPARQL update query to execute.
     */
    protected void executeUpdate(final String statement) {
        repository.prepareUpdate(statement).execute();
    }

    @Override
    public QueryResultList executeQuery(final String sparqlQueryString) {
        final TupleQuery tupleQuery = repository.prepareTupleQuery(sparqlQueryString);
        try (final TupleQueryResult result = tupleQuery.evaluate()) {
            return getQueryResultList(result);
        }
    }

    private QueryResultList getQueryResultList(final TupleQueryResult result) {
        final List<QueryResult> queryResults = new ArrayList<>();
        final QueryResultList queryResultList = new QueryResultList(result.getBindingNames(), queryResults);
        while (result.hasNext()) {
            final BindingSet querySolution = result.next();
            final Iterator<String> varNames = result.getBindingNames().iterator();
            final QueryResult queryResult = new QueryResult();

            while (varNames.hasNext()) {
                final String varName = varNames.next();
                final Value node = querySolution.getValue(varName);
                if (node instanceof Literal) {
                    queryResult.set(varName, new uk.gov.gchq.magmacore.database.query.Literal(node.stringValue()));
                } else if (node instanceof Resource r) {
                    queryResult.set(varName, new uk.gov.gchq.magmacore.database.query.Resource(r.stringValue()));
                } else {
                    throw new UnsupportedOperationException("Unknown node type: " + node.getClass().getName());
                }
            }
            queryResults.add(queryResult);
        }
        return queryResultList;
    }

    /**
     * Convert a GraphQueryResult to a QueryResultList.
     *
     * @param result {@link GraphQueryResult}
     * @return {@link QueryResultList}
     */
    private QueryResultList getQueryResultList(final GraphQueryResult result) {
        final List<QueryResult> queryResults = new ArrayList<>();
        final List<String> varNames = List.of("subject", "predicate", "object");
        final QueryResultList queryResultList = new QueryResultList(varNames, queryResults);
        while (result.hasNext()) {
            final Statement statement = result.next();
            final QueryResult queryResult = new QueryResult();

            final Value subject = statement.getSubject();
            final Value predicate = statement.getPredicate();
            final Value object = statement.getObject();

            queryResult.set("subject", new uk.gov.gchq.magmacore.database.query.Resource(subject.stringValue()));
            queryResult.set("predicate", new uk.gov.gchq.magmacore.database.query.Resource(predicate.stringValue()));

            if (object instanceof Literal) {
                queryResult.set("object", new uk.gov.gchq.magmacore.database.query.Literal(object.stringValue()));
            } else if (object instanceof Resource r) {
                queryResult.set("object", new uk.gov.gchq.magmacore.database.query.Resource(r.stringValue()));
            } else {
                throw new UnsupportedOperationException("Unknown node type: " + object.getClass().getName());
            }

            queryResults.add(queryResult);
        }
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
                objectMap.put(subjectValue, dataModelObject);
            }
            if (objectValue instanceof uk.gov.gchq.magmacore.database.query.Literal) {
                dataModelObject.add(new Pair<>(new IRI(predicateValue.toString()), objectValue.toString()));
            } else if (objectValue instanceof uk.gov.gchq.magmacore.database.query.Resource) {
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
     * Execute a CONSTRUCT query.
     *
     * @param query a CONSTRUCT query {@link String}
     * @return a {@link List} of {@link Thing}
     */
    @Override
    public List<Thing> executeConstruct(final String query) {
        final GraphQueryResult result = repository.prepareGraphQuery(query).evaluate();
        return toTopObjects(getQueryResultList(result));
    }

}
