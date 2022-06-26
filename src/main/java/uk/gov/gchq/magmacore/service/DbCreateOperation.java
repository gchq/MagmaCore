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

import java.util.function.Function;

import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.rdf.exception.HqdmException;
import uk.gov.gchq.hqdm.rdf.iri.IRI;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.exception.DbTransformationException;

/**
 * Class representing an invertible operation to create a predicate.
 */
public class DbCreateOperation implements Function<MagmaCoreService, MagmaCoreService> {

    // The IRI of the Thing we're referring to.
    private IRI subject;

    // The IRI of the property we're referring to.
    private IRI predicate;

    // The value of the property we're referring to.
    private String object;

    /**
     * Constructor.
     *
     * @param subject {@link IRI}
     * @param predicate {@link IRI}
     * @param object {@link String}
    */
    public DbCreateOperation(final IRI subject, final IRI predicate, final String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        Thing thing = null;
        try {
            thing = mcService.get(subject);
        } catch (final HqdmException e) {
            // The object does not exist.
        }

        if (thing == null) {
            final  var newThing = SpatioTemporalExtentServices.createSpatioTemporalExtent(subject.getIri());
            newThing.addStringValue(predicate.getIri(), object);
            mcService.create(newThing);
        } else {
            if (!thing.hasThisValue(predicate.getIri(), object)) {
                thing.addValue(predicate.getIri(), object);
                mcService.update(thing);
            } else {
                throw new DbTransformationException(
                    String.format("Triple already exists: %s, %s, %s", subject, predicate, object)
                );
            }
        }

        return mcService;
    }

    /**
     * Invert an operation.
     *
     * @param c {@link DbCreateOperation}
     * @return The inverted {@link DbDeleteOperation}.
    */
    public static DbDeleteOperation invert(final DbCreateOperation c) {
        return new DbDeleteOperation(c.subject, c.predicate, c.object);
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
        final DbCreateOperation other = (DbCreateOperation) obj;
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

