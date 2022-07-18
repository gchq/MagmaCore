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

package uk.gov.gchq.magmacore.internal.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.function.Predicate;

import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.hqdm.model.PointInTime;
import uk.gov.gchq.magmacore.hqdm.model.Sign;
import uk.gov.gchq.magmacore.hqdm.model.Thing;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.HQDM;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;

/**
 * Predicates for filtering streams.
 */
public final class Predicates {

    private Predicates() {
    }

    // Check whether a Thing is a Sign or not.
    public static Predicate<Thing> isSign = thing -> thing instanceof Sign;

    /**
     * Create a {@link Predicate} to check whether a thing exists at a {@link PointInTime}.
     *
     * @param database    a {@link MagmaCoreDatabase} instance
     * @param pointInTime the {@link PointInTime}
     * @return a {@link Predicate}
     */
    public static Predicate<Thing> isValidAtPointInTime(final MagmaCoreDatabase database,
            final PointInTime pointInTime) {
        return thing -> {
            // Get the beginning and ending of the thing
            final Set<Object> beginningId = thing.value(HQDM.BEGINNING);
            final Set<Object> endingId = thing.value(HQDM.ENDING);

            final LocalDateTime beginning;
            final LocalDateTime ending;

            if (beginningId.size() > 0) {
                final Thing event = database.get(new IRI(beginningId.iterator().next().toString()));
                beginning = Predicates.getInstant(event, LocalDateTime.MIN);
            } else {
                beginning = LocalDateTime.MIN;
            }

            if (endingId.size() > 0) {
                final Thing event = database.get(new IRI(endingId.iterator().next().toString()));
                ending = Predicates.getInstant(event, LocalDateTime.MAX);
            } else {
                ending = LocalDateTime.MAX;
            }

            // Get the requested PointInTime Instant
            final LocalDateTime when = LocalDateTime
                    .parse(pointInTime.value(HQDM.ENTITY_NAME).iterator().next().toString());
            return (when.equals(beginning) || when.isAfter(beginning))
                    && (when.equals(ending) || when.isBefore(ending));
        };
    }

    /**
     * Get the {@link Instant} or return the default value.
     *
     * @param thing        the {@link Thing} of interest.
     * @param defaultValue the default {@link Instant}
     * @return an {@link Instant}
     */
    public static LocalDateTime getInstant(final Thing thing, final LocalDateTime defaultValue) {
        final var values = thing.value(HQDM.ENTITY_NAME);
        if (values != null && values.size() == 1) {
            try {
                return LocalDateTime.parse(values.iterator().next().toString());
            } catch (final DateTimeParseException exc) {
                // This can happen if called with a Thing that isn't an Event.
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
