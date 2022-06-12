package uk.gov.gchq.magmacore.demo;

import java.util.List;

import uk.gov.gchq.magmacore.service.DbTransformation;
import uk.gov.gchq.magmacore.service.MagmaCoreService;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 *
 * */
public class ExampleDataObjects {

    /**
     * A function that populates a database.
     *
     * @param mcService a {@link MagmaCoreService}
     * @return {@link DbTransformation}
     */
    public static DbTransformation populateExampleData(final MagmaCoreService mcService) {

        // svc is updated by each DbChangeSet and used to create the next DbChangeSet since
        // they are sequentially dependent.
        var svc = mcService;
        
        // Apply the transformation to the database. There are dependencies between these change sets 
        // since they both depend on RDL being present, but also the occupancies depend on the 
        // individuals being present, so each change set needs to be applied before the next one 
        // can be created.
        final var rdlChangeSet = ExampleRdl.createRefDataObjects();

        // Apply the DbChangeSet
        svc = rdlChangeSet.apply(svc);

        // svc now contains the RDL needed for the next DbChangeSet
        final var individualsChangeSet = ExampleIndividuals.addWholeLifeIndividuals(svc);

        // Apply the DbChangeSet
        svc = individualsChangeSet.apply(svc);

        // svc now contains the individuals needed for creating the next DbChangeSet
        final var occupanciesChangeSet = ExampleAssociations.addHouseOccupancies(svc);

        // Apply the DbChangeSet
        svc = occupanciesChangeSet.apply(svc);

        // Combine the DbChangeSets into a DbTransformation and return it as a record of the changes.
        return new DbTransformation(List.of(rdlChangeSet, individualsChangeSet, occupanciesChangeSet));
    }

}
