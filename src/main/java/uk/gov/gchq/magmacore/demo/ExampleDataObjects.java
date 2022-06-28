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

package uk.gov.gchq.magmacore.demo;

import java.util.List;

import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.transformation.DbTransformation;

/**
 * Functions for creating systems using MagmaCore and HQDM.
 */
public class ExampleDataObjects {

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
        final var rdlChangeSet = ExampleRdl.createRefDataObjects();

        // Apply the DbChangeSet
        rdlChangeSet.apply(mcService);

        // mcService now contains the RDL needed for the next DbChangeSet
        final var individualsChangeSet = ExampleIndividuals.addWholeLifeIndividuals(mcService);

        // Apply the DbChangeSet
        individualsChangeSet.apply(mcService);

        // mcService now contains the individuals needed for creating the next DbChangeSet
        final var occupanciesChangeSet = ExampleAssociations.addHouseOccupancies(mcService);

        // Apply the DbChangeSet
        occupanciesChangeSet.apply(mcService);

        // Combine the DbChangeSets into a DbTransformation and return it as a record of the changes.
        return new DbTransformation(List.of(rdlChangeSet, individualsChangeSet, occupanciesChangeSet));
    }

}
