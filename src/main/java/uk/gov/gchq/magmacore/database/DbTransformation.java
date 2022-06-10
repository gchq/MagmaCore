package uk.gov.gchq.magmacore.database;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class representing an invertible ordered sequence of change sets.
 *
 * */
public class DbTransformation implements Function<MagmaCoreDatabase, MagmaCoreDatabase> {
    private List<DbChangeSet> transformations;

    /**
     * Constructor.
     *
     * @param transformations a {@link List} of {@link DbChangeSet}
    */
    public DbTransformation(final List<DbChangeSet> transformations) {
        this.transformations = transformations;
    }

    /**
     * Constructor.
     *
    */
    public DbTransformation() {
        this(List.of());
    }

    /**
     * Apply this {@link DbTransformation} to a {@link MagmaCoreDatabase}.
     *
     * */
    @Override
    public MagmaCoreDatabase apply(final MagmaCoreDatabase db) {
        final var transformation = transformations
            .stream()
            .map(x -> (Function<MagmaCoreDatabase, MagmaCoreDatabase>) x)
            .reduce(Function::andThen)
            .orElse(Function.identity());

        return transformation.apply(db);
    }

    /**
     * Invert this {@link DbTransformation}.
     *
     * @return a {@link DbTransformation}
     */
    public DbTransformation invert() {
        final var list = transformations
            .stream()
            .map(DbChangeSet::invert)
            .collect(Collectors.toList());

        Collections.reverse(list);

        return new DbTransformation(list);
    }

    /**
     * Add a DbChangeSet to this transformation.
     *
     * @param changeSet {@link DbChangeSet}
    */
    public void add(final DbChangeSet changeSet) {
        this.transformations.add(changeSet);
    }
}
