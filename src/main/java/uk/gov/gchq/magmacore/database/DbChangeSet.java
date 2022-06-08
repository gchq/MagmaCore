package uk.gov.gchq.magmacore.database;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class representing an invertible set of deletes and creates.
 *
 * */
public class DbChangeSet implements Function<MagmaCoreDatabase, MagmaCoreDatabase> {
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
     * Apply this {@link DbChangeSet} to a {@link MagmaCoreDatabase}.
     *
     * */
    @Override
    public MagmaCoreDatabase apply(final MagmaCoreDatabase db) {
        final var deleteFunction = deletes
            .stream()
            .map(x -> (Function<MagmaCoreDatabase, MagmaCoreDatabase>) x)
            .reduce(Function::andThen)
            .orElse(Function.identity());

        final var createFunction = creates
            .stream()
            .map(x -> (Function<MagmaCoreDatabase, MagmaCoreDatabase>) x)
            .reduce(Function::andThen)
            .orElse(Function.identity());

        return deleteFunction.andThen(createFunction).apply(db);
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
