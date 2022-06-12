package uk.gov.gchq.magmacore.demo;

import uk.gov.gchq.magmacore.service.DbTransformation;
import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.util.ComposableBiFunction;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleDataObjects {

    // A BiFunction to create the RDL
    private static ComposableBiFunction<DbTransformation, MagmaCoreService, MagmaCoreService> createRefDataObjects = (t, s) -> {
        final var changeSet = ExampleRdl.createRefDataObjects();
        t.add(changeSet);
        return changeSet.apply(s);
    };

    // A BiFunction to add the individuals
    private static ComposableBiFunction<DbTransformation, MagmaCoreService, MagmaCoreService> addWholeLifeIndividuals = (t, s) -> {
        final var changeSet = ExampleIndividuals.addWholeLifeIndividuals(s);
        t.add(changeSet);
        return changeSet.apply(s);
    };

    // A BiFunction to create the occupancy associations
    private static ComposableBiFunction<DbTransformation, MagmaCoreService, MagmaCoreService> addHouseOccupancies = (t, s) -> {
        final var changeSet = ExampleAssociations.addHouseOccupancies(s);
        t.add(changeSet);
        return changeSet.apply(s);
    };

    /**
     * A function that populates a database.
     *
     * @param mcService a {@link MagmaCoreService}
     * @return {@link DbTransformation}
     */
    public static DbTransformation populateExampleData(final MagmaCoreService mcService) {

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
        transform.apply(result, mcService);

        // Return the DbTransformation that took place.
        return result;
    }

}
