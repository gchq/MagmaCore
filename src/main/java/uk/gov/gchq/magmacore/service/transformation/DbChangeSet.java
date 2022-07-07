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
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * An invertible set of delete and create operations.
 */
public class DbChangeSet implements Function<MagmaCoreService, MagmaCoreService> {
    private List<DbDeleteOperation> deletes;
    private List<DbCreateOperation> creates;

    /**
     * Constructs a DbChangeSet with a list of delete and create operations to perform.
     *
     * @param deletes A {@link List} of {@link DbDeleteOperation}.
     * @param creates A {@link List} of {@link DbCreateOperation}.
     */
    public DbChangeSet(final List<DbDeleteOperation> deletes, final List<DbCreateOperation> creates) {
        this.deletes = deletes;
        this.creates = creates;
    }

    /**
     * Apply the change set to a {@link MagmaCoreService}.
     */
    @Override
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        final Function<MagmaCoreService, MagmaCoreService> deleteFunction = deletes
                .stream()
                .map(d -> (Function<MagmaCoreService, MagmaCoreService>) d)
                .reduce(Function::andThen)
                .orElse(Function.identity());

        final Function<MagmaCoreService, MagmaCoreService> createFunction = creates
                .stream()
                .map(c -> (Function<MagmaCoreService, MagmaCoreService>) c)
                .reduce(Function::andThen)
                .orElse(Function.identity());

        mcService.runInTransaction(deleteFunction.andThen(createFunction));
        return mcService;
    }

    /**
     * Invert a {@link DbChangeSet}.
     *
     * @param changeSet A {@link DbChangeSet} to invert.
     * @return The inverted {@link DbChangeSet}.
     */
    public static DbChangeSet invert(final DbChangeSet changeSet) {
        final List<DbDeleteOperation> newDeletes = changeSet.creates
                .stream()
                .map(DbCreateOperation::invert)
                .collect(Collectors.toList());

        final List<DbCreateOperation> newCreates = changeSet.deletes
                .stream()
                .map(DbDeleteOperation::invert)
                .collect(Collectors.toList());

        Collections.reverse(newDeletes);
        Collections.reverse(newCreates);
        return new DbChangeSet(newDeletes, newCreates);
    }
}
