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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * An invertible ordered sequence of change sets.
 */
public class DbTransformation implements Function<MagmaCoreService, MagmaCoreService> {
    private List<DbChangeSet> transformations;

    /**
     * Constructs a DbTransformation with no operations.
     */
    public DbTransformation() {
        this(new LinkedList<>());
    }

    /**
     * Constructs a DbTransformation with a list of transformations.
     *
     * @param transformations A {@link List} of {@link DbChangeSet}.
     */
    public DbTransformation(final List<DbChangeSet> transformations) {
        this.transformations = transformations;
    }

    /**
     * Apply the transformation to a {@link MagmaCoreService}.
     */
    @Override
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        transformations.stream().forEach(changeSet -> changeSet.apply(mcService));
        return mcService;
    }

    /**
     * Invert this {@link DbTransformation}.
     *
     * @return The inverted {@link DbTransformation}.
     */
    public DbTransformation invert() {
        final List<DbChangeSet> list = transformations
                .stream()
                .map(DbChangeSet::invert)
                .collect(Collectors.toList());

        Collections.reverse(list);

        return new DbTransformation(list);
    }

    /**
     * Add a DbChangeSet to this transformation.
     *
     * @param changeSet {@link DbChangeSet} to add.
     */
    public void add(final DbChangeSet changeSet) {
        this.transformations.add(changeSet);
    }
}
