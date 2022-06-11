package uk.gov.gchq.magmacore.demo;

import uk.gov.gchq.magmacore.database.DbTransformation;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;
import uk.gov.gchq.magmacore.util.ComposableBiFunction;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleDataObjects {

    // A BiFunction to create the RDL
    private static ComposableBiFunction<DbTransformation, MagmaCoreDatabase, MagmaCoreDatabase> createRefDataObjects = (t, d) -> {
        final var changeSet = ExampleRdl.createRefDataObjects();
        t.add(changeSet);
        return changeSet.apply(d);
    };

    // A BiFunction to add the individuals
    private static ComposableBiFunction<DbTransformation, MagmaCoreDatabase, MagmaCoreDatabase> addWholeLifeIndividuals = (t, d) -> {
        final var changeSet = ExampleIndividuals.addWholeLifeIndividuals(d);
        t.add(changeSet);
        return changeSet.apply(d);
    };

    // A BiFunction to create the occupancy associations
    private static ComposableBiFunction<DbTransformation, MagmaCoreDatabase, MagmaCoreDatabase> addHouseOccupancies = (t, d) -> {
        final var changeSet = ExampleAssociations.addHouseOccupancies(d);
        t.add(changeSet);
        return changeSet.apply(d);
    };

    /**
     * A function that populates a database.
     *
     * @param db a {@link MagmaCoreDatabase}
     * @return {@link DbTransformation}
     */
    public static DbTransformation populateExampleData(final MagmaCoreDatabase db) {

        // Apply the transformation to the database. There are dependencies between these change sets 
        // since they both depend on RDL being present, but also the occupancies depend on the 
        // individuals being present, so each change set needs to be applied before the next one 
        // can be created.
        final var transform = 
            createRefDataObjects
            .andThen(addWholeLifeIndividuals)
            .andThen(addHouseOccupancies);

        // This will be updated to record the transformation that took place.
        final var result = new DbTransformation();

        // Apply the transform to the database
        transform.apply(result, db);

        // Return the DbTransformation that took place.
        return result;
    }

}
