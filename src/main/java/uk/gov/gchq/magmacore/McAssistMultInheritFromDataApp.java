package uk.gov.gchq.magmacore;

import static uk.gov.gchq.magmacore.util.ClassGenUtils.drop;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.dumpClass;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.dumpDatabase;
import static uk.gov.gchq.magmacore.util.ClassGenUtils.initJenaDatabase;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Participant;
import uk.gov.gchq.hqdm.model.StateOfOrganization;
import uk.gov.gchq.hqdm.model.impl.HqdmObject;
import uk.gov.gchq.hqdm.model.impl.ThingImpl;
import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.demo.BasicHqdmInherit;
import uk.gov.gchq.magmacore.generators.MultipleInheritGenerateFromThing;

/**
 * Demonstrate how to create a new class dynamically.
 */
@Log4j2
@UtilityClass
public class McAssistMultInheritFromDataApp {

    public static final String PARTICIPANT_TYPE_NAME = "PARTICIPANT_TYPE";
    public static final String STATE_OF_ORGANIZATION_TYPE_NAME = "STATE_OF_ORGANIZATION_TYPE";
    public static final String NEW_ENTITY_NAME = "PARTICIPANT_TYPE_AND_STATE_OF_ORGANIZATION";
    public static final String ORG_SUPER_TYPE = "StateOfOrganization";
    public static final String PARTICIPANT_SUPER_TYPE = "Participant";
    public static final String TTL_FILE_NAME = "objectsWithNewTypeIncluded.ttl";

    private static final Function1<? super MagmaCoreJenaDatabase, Participant> getParticipantType = jenaDatabase -> {
        jenaDatabase.begin();
        final var queryResults =
                jenaDatabase.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, PARTICIPANT_TYPE_NAME);
        jenaDatabase.abort();

        queryResults.forEach(log::info);

        return (Participant) queryResults.get(0);
    };

    private static final Function1<? super MagmaCoreJenaDatabase, StateOfOrganization> getStateOfOrgType =
            jenaDatabase -> {
                jenaDatabase.begin();
                final var queryResults =
                        jenaDatabase.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME,
                                STATE_OF_ORGANIZATION_TYPE_NAME);
                jenaDatabase.abort();

                queryResults.forEach(log::info);

                return (StateOfOrganization) queryResults.get(0);
            };

    private static Function1<MagmaCoreJenaDatabase, MagmaCoreJenaDatabase> createNewObject(
            @NonNull final Class<?> newHqdmClass) {
        return jenaDatabase -> {
            try {
                final var newObj = newHqdmClass
                        .getDeclaredConstructor(IRI.class)
                        .newInstance(new IRI(uk.gov.gchq.magmacore.util.DataObjectUtils.USER_BASE, uid()));

                // Add name and commit to Jena database
                final var newTypeObj = (HqdmObject) newObj;
                newTypeObj.addStringValue(HQDM.ENTITY_NAME, NEW_ENTITY_NAME);
                newTypeObj.addStringValue(HQDM.HAS_SUPERTYPE, ORG_SUPER_TYPE);
                newTypeObj.addStringValue(HQDM.HAS_SUPERTYPE, PARTICIPANT_SUPER_TYPE);

                jenaDatabase.begin();
                jenaDatabase.create(newTypeObj);
                jenaDatabase.commit();
                return jenaDatabase;
            } catch (final Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Example program for creating classes dynamically.
     *
     * @param args an array of Strings.
     */
    public static void main(final String[] args) {

        log.info(
                "Starting the Magma Core Assist app, multiple inheritance new HQDM class generation from HQDM data "
                        + "objects.\n");

        final var jenaDatabase = initJenaDatabase(BasicHqdmInherit.createDataObjects())
                .get();
        final var participantType = jenaDatabase.map(getParticipantType)
                .getOrElseThrow(RuntimeException::new);
        final var stateOfOrganizationType = jenaDatabase.map(getStateOfOrgType)
                .getOrElseThrow(RuntimeException::new);
        final var newHqdmClass = jenaDatabase.map(createNewType(participantType, stateOfOrganizationType))
                .getOrElseThrow(RuntimeException::new);

        log.info(
                "New Java Class Generated for the new Hqdm Type.\nNow create a record of it in the database, "
                        + "using the new Java Class: " + newHqdmClass.getName());

        dumpClass(newHqdmClass);

        jenaDatabase
                .map(createNewObject(newHqdmClass))
                .map(dumpDatabase(TTL_FILE_NAME))
                .map(drop);

        log.info("Ending the Magma Core Assist app.");
    }

    private static Function1<MagmaCoreJenaDatabase, Class<?>> createNewType(
            @NonNull final Participant participantType, @NonNull final StateOfOrganization stateOfOrganizationType) {
        return jenaDatabase -> {
            // Generate Hqdm Objects that specify the multiple inheritance using the **NEW** has_supertype relationship
            // Specify a new Thing that has_supertype as the two types that it inherits from
            final var dataSpecForNewType = (ThingImpl) new ThingImpl.Builder(new IRI(REF_BASE, uid()))
                    .has_supertype(participantType)
                    .has_supertype(stateOfOrganizationType)
                    .build();
            //Now parse this object to generate the new Java interfaces and Classes
            return MultipleInheritGenerateFromThing.generateNewTypeClasses(
                    dataSpecForNewType,
                    jenaDatabase);
        };
    }

}
