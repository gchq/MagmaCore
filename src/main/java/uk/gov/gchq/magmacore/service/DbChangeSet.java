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

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class representing an invertible set of deletes and creates.
 */
public class DbChangeSet implements Function<MagmaCoreService, MagmaCoreService> {
    private Set<DbDeleteOperation> deletes;
    private Set<DbCreateOperation> creates;

    /**
     * Constructor.
     *
     * @param deletes a {@link Set} of {@link DbDeleteOperation}
     * @param creates a {@link Set} of {@link DbCreateOperation}
    */
    public DbChangeSet(final Set<DbDeleteOperation> deletes, final Set<DbCreateOperation> creates) {
        this.deletes = deletes;
        this.creates = creates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        final var deleteFunction = deletes
            .stream()
            .map(x -> (Function<MagmaCoreService, MagmaCoreService>) x)
            .reduce(Function::andThen)
            .orElse(Function.identity());

        final var createFunction = creates
            .stream()
            .map(x -> (Function<MagmaCoreService, MagmaCoreService>) x)
            .reduce(Function::andThen)
            .orElse(Function.identity());

        mcService.runInTransaction(deleteFunction.andThen(createFunction));
        return mcService;
    }

    /**
     * Invert a {@link DbChangeSet}.
     *
     * @param c a {@link DbChangeSet}
     * @return a {@link DbChangeSet}
    */
    public static DbChangeSet invert(final DbChangeSet c) {
        return new DbChangeSet(
                c.creates.stream().map(DbCreateOperation::invert).collect(Collectors.toSet()),
                c.deletes.stream().map(DbDeleteOperation::invert).collect(Collectors.toSet())
                );
    }
}
