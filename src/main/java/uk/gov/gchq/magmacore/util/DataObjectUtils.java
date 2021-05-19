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

package uk.gov.gchq.magmacore.util;

import java.time.LocalDateTime;
import java.util.UUID;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.iri.IriBase;
import uk.gov.gchq.hqdm.model.PointInTime;
import uk.gov.gchq.hqdm.model.PossibleWorld;
import uk.gov.gchq.hqdm.model.impl.PointInTimeImpl;

/**
 * Utilities for building and generating HQDM objects.
 */
public final class DataObjectUtils {

    /** IriBase for Reference Data Library. */
    public static final IriBase REF_BASE =
            new IriBase("rdl", "http://www.semanticweb.org/magma-core/ontologies/rdl#");

    /** IriBase for User data. */
    public static final IriBase USER_BASE =
            new IriBase("user", "http://www.semanticweb.org/magma-core/ontologies/user#");

    private DataObjectUtils() {}

    /**
     * Create a new random UUID to assign to an object.
     *
     * @return A Random UUID.
     */
    public static String uid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get the current date-time in the ISO-8601 calendar system.
     *
     * @return Local date-time in the format {@code 2007-12-03T10:15:30.123456789}.
     */
    public static String timeNow() {
        final LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }

    /**
     * Generate a new PointInTime.
     *
     * @param eventTime Entity name of the point in time.
     * @param pw The PossibleWorld the PointInTime is a part of.
     * @param baseIri IriBase of the PointInTime.
     * @return The generated PointInTime.
     */
    public static PointInTime event(final String eventTime, final PossibleWorld pw,
            final IriBase baseIri) {
        final PointInTime timeEvent = new PointInTimeImpl.Builder(new IRI(baseIri, uid()))
                .part_Of_Possible_World_M(pw)
                .build();
        timeEvent.addStringValue(HQDM.ENTITY_NAME, eventTime);

        return timeEvent;
    }
}
