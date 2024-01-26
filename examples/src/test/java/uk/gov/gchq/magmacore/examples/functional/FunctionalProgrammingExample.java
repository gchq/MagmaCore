package uk.gov.gchq.magmacore.examples.functional;

import java.util.function.Function;

import org.junit.Test;

import uk.gov.gchq.magmacore.service.MagmaCoreService;
import uk.gov.gchq.magmacore.service.MagmaCoreServiceFactory;

/**
 * A Functional Programming example for using MagmaCore.
 */
public class FunctionalProgrammingExample {

    private Function<Database, Database> beginWriteTransaction = db -> {
        db.service.beginWrite();
        return db;
    };

    private Function<Database, Database> commitTransaction = db -> {
        db.service.commit();
        return db;
    };

    @Test
    public void test() {

        final String ttl = 
            new DatabaseCreator()
            .andThen(beginWriteTransaction)
            .andThen(new RefDataFinder())
            .andThen(createPerson)
            .andThen(createResearchActivity)
            .andThen(addPersonAsParticipantInActivity)
            .andThen(exportTtl)
            .andThen(commitTransaction)
            .apply(Void.instance);

    }

    private static class Void {
        public static Void instance = new Void();

        private Void() {}
    }

    private static class DatabaseCreator implements Function<Void, Database> {

        @Override
        public Database apply(final Void v) {
            return new Database(MagmaCoreServiceFactory.createWithJenaDatabase());
        }
    }

    private static class Database {

        private MagmaCoreService service;

        public MagmaCoreService getService() {
            return service;
        }

        public Database(final MagmaCoreService service) {
            this.service = service;
        }
    }

    private static class RefDataFinder implements Function<Database, RefData> {

        @Override
        public RefData apply(final Database database) {
            return new RefData(database);
        }

    }

    private static class RefData {
        private final Database database;

        public Database getDatabase() {
            return database;
        }

        public RefData(final Database database) {
            this.database = database;
        }
    }
}

