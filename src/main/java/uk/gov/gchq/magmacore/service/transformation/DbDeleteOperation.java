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

package uk.gov.gchq.magmacore.service.transformation;

import java.util.function.Function;

import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.exception.DbTransformationException;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * Class representing an invertible operation to delete a predicate.
 */
public class DbDeleteOperation implements Function<MagmaCoreService, MagmaCoreService> {
    private IRI subject;
    private IRI predicate;
    private String object;

    /**
     * Constructor.
     *
     * @param subject {@link IRI}
     * @param predicate {@link IRI}
     * @param object {@link String}
    */
    public DbDeleteOperation(final IRI subject, final IRI predicate, final String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        final var thing = mcService.get(subject);

        if (thing != null && thing.hasThisValue(predicate.getIri(), object)) {
            thing.removeValue(predicate.getIri(), object);
            mcService.update(thing);
            return mcService;
        }

        throw new DbTransformationException(
                String.format("Triple not found for delete: %s, %s, %s", subject, predicate, object)
                );
    }

    /**
     * Invert a {@link DbDeleteOperation}.
     *
     * @param d the {@link DbDeleteOperation}
     * @return The inverted {@link DbCreateOperation}.
    */
    public static DbCreateOperation invert(final DbDeleteOperation d) {
        return new DbCreateOperation(d.subject, d.predicate, d.object);
    }

    /**
     * Calculate a hashcode.
     *
     * @return int
     * */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((object == null) ? 0 : object.hashCode());
        result = prime * result + ((predicate == null) ? 0 : predicate.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        return result;
    }

    /**
     * Check for equality.
     *
     * @param obj {@link Object}
     * @return true if the objects are euqal, false otherwise.
     * */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DbDeleteOperation other = (DbDeleteOperation) obj;
        if (object == null) {
            if (other.object != null) {
                return false;
            }
        } else if (!object.equals(other.object)) {
            return false;
        }
        if (predicate == null) {
            if (other.predicate != null) {
                return false;
            }
        } else if (!predicate.equals(other.predicate)) {
            return false;
        }
        if (subject == null) {
            if (other.subject != null) {
                return false;
            }
        } else if (!subject.equals(other.subject)) {
            return false;
        }
        return true;
    }

}
