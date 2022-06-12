package uk.gov.gchq.magmacore.service;

import uk.gov.gchq.magmacore.database.MagmaCoreJenaDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreObjectDatabase;
import uk.gov.gchq.magmacore.database.MagmaCoreRemoteSparqlDatabase;

/**
 * Factory for creating MagmaCoreService instances. This 
 * removes the need to expose MagmaCoreDatabase interface to
 * clients of the library.
 *
 * */
public class MagmaCoreServiceFactory {

    /**
     * Create an in-memory object database.
     *
     * @return {@link MagmaCoreService}
     * */
    public static MagmaCoreService createWithObjectDatabase() {
        return new MagmaCoreService(new MagmaCoreObjectDatabase());
    }

    /**
     * Create a Jena database.
     *
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService createWithJenaDatabase() {
        return new MagmaCoreService(new MagmaCoreJenaDatabase());
    }

    /**
     * Create a Jena database.
     *
     * @param name a database name String
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService createWithJenaDatabase(final String name) {
        return new MagmaCoreService(new MagmaCoreJenaDatabase(name));
    }

    /**
     * Create a Jena database.
     *
     * @param db a {@link MagmaCoreJenaDatabase}
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService createWithJenaDatabase(final MagmaCoreJenaDatabase db) {
        return new MagmaCoreService(db);
    }

    /**
     * Attach to a remote SPARQL Endpoint.
     *
     * @param url the url {@link String}
     * @return {@link MagmaCoreService}
    */
    public static MagmaCoreService attachRemoteSparqlEndpoint(final String url) {
        return new MagmaCoreService(new MagmaCoreRemoteSparqlDatabase(url));
    }
}
