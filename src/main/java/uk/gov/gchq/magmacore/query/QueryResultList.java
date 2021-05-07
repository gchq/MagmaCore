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
 * 
 */
public class QueryResultList {

    /** */
    private List<String> varNames;

    /** */
    private List<QueryResult> queryResults;

    /**
     *
     * @param varNames
     * @param queryResults
     */
    public QueryResultList(final List<String> varNames, final List<QueryResult> queryResults) {
        this.varNames = varNames;
        this.queryResults = queryResults;
    }

    /**
     *
     * @return
     */
    public final List<String> getVarNames() {
        return varNames;
    }

    /**
     *
     * @param varNames
     */
    public final void setVarNames(final List<String> varNames) {
        this.varNames = varNames;
    }

    /**
     *
     * @return
     */
    public final List<QueryResult> getQueryResults() {
        return queryResults;
    }

    /**
     *
     * @param queryResults
     */
    public final void setQueryResults(final List<QueryResult> queryResults) {
        this.queryResults = queryResults;
    }

    /**
     *
     * @return
     */
    public final int size() {
        return queryResults.size();
    }
}
