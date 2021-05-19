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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import uk.gov.gchq.hqdm.iri.HqdmIri;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Thing;

/**
 * In-memory collection of HQDM objects.
 */
public class MagmaCoreObjectDatabase implements MagmaCoreDatabase {

    private final Map<IRI, Thing> objects;

    /**
     * Instantiate a new empty MagmaCoreObjectDatabase.
     */
    public MagmaCoreObjectDatabase() {
        objects = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Thing get(final IRI iri) {
        return objects.get(iri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(final Thing object) {
        final IRI iri = object.getIri();
        if (!objects.keySet().contains(iri)) {
            objects.put(iri, object);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Thing object) {
        final IRI iri = object.getIri();
        if (objects.keySet().contains(iri)) {
            objects.put(iri, object);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final Thing object) {
        final IRI iri = object.getIri();
        if (objects.keySet().contains(iri)) {
            objects.remove(iri, object);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIri(final IRI predicateIri, final IRI objectIri) {
        return objects.values().stream()
                .filter(object -> object.hasThisValue(predicateIri, objectIri))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriOnly(final HqdmIri predicateIri) {
        throw new RuntimeException("findByPredicateIRIOnly not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriAndStringValue(final IRI predicateIri,
            final String value) {
        return objects
                .values()
                .stream()
                .filter(object -> object.hasThisStringValue(predicateIri, value))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Thing> findByPredicateIriAndStringCaseInsensitive(final IRI predicateIri,
            final String value) {
        throw new RuntimeException(
                "findByPredicateIRIAndStringCaseInsensitive not yet implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dump(final PrintStream out) {
        objects.values().forEach(object -> out.println(object.toString()));
    }

    /**
     * Dump contents of dataset to output as RDF triples in turtle format.
     *
     * @param out Output stream to dump data to.
     */
    public final void dumpTurtle(final PrintStream out) {
        objects.values().forEach(object -> out.println(object.toTriples()));
    }
}
