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

package uk.gov.gchq.magmacore.query;

import java.util.List;

/**
 * A list of {@link QueryResult}s returned from a SPARQL query.
 */
public class QueryResultList {

    private List<String> varNames;

    private List<QueryResult> queryResults;

    /**
     * Constructs a new QueryResultList from a list of QueryResults and variable names.
     *
     * @param varNames     Variable names used in the results list.
     * @param queryResults Results of the query.
     */
    public QueryResultList(final List<String> varNames, final List<QueryResult> queryResults) {
        this.varNames = varNames;
        this.queryResults = queryResults;
    }

    /**
     * Get the list of variable names from the results list.
     *
     * @return The list of variable names.
     */
    public final List<String> getVarNames() {
        return varNames;
    }

    /**
     * Set the list of variable names.
     *
     * @param varNames The list of variable names used in the results list.
     */
    public final void setVarNames(final List<String> varNames) {
        this.varNames = varNames;
    }

    /**
     * Get the list of QueryResults.
     *
     * @return The list of QueryResults.
     */
    public final List<QueryResult> getQueryResults() {
        return queryResults;
    }

    /**
     * Set the list of QueryResults found by the query.
     *
     * @param queryResults The list of QueryResults
     */
    public final void setQueryResults(final List<QueryResult> queryResults) {
        this.queryResults = queryResults;
    }

    /**
     * Get the total number of QueryResults in the results list.
     *
     * @return Size of the QueryResultsList.
     */
    public final int size() {
        return queryResults.size();
    }
}
