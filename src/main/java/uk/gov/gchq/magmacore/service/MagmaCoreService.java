package uk.gov.gchq.magmacore.service;

import uk.gov.gchq.hqdm.iri.HQDM;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.magmacore.database.MagmaCoreDatabase;

/**
 * Services exported by the MagmaCore module.
 *
 * */
public class MagmaCoreService {
    
    // The service operates on a database.
    private final MagmaCoreDatabase db;

    /**
     * Constructor that requires a {@link MagmaCoreDatabase}.
     *
     * @param db {@link MagmaCoreDatabase}
    */
    public MagmaCoreService(final MagmaCoreDatabase db) {
        this.db = db;
    }

    /**
     * Find an object by its ENTITY_NAME.
     *
     * @param name the name {@link String} to seaerch for.
     * @return the {@link Thing}that was found.
     * @throws RuntimeException if no or multiple results found.
     */
    public <T> T findByEntityName(final String name) {
        final var searchResult = db.findByPredicateIriAndStringValue(HQDM.ENTITY_NAME, name);
        if (searchResult.size() == 1) {
            return (T) searchResult.get(0);
        } else if (searchResult.isEmpty()) {
            throw new RuntimeException("No entity found with name: " + name);
        } else {
            throw new RuntimeException("Multiple entities found with name: " + name);
        }
    }

    /**
     * Create a new Thing.
     *
     * @param thing {@link Thing}
    */
    public void create(final Thing thing) {
        db.create(thing);
    }

    /**
     * Update an existing {@link Thing}.
     *
     * @param thing {@link Thing}
    */
    public void update(final Thing thing) {
        db.update(thing);
    }

    /**
     * Get a {@link Thing} with the given {@link IRI}.
     *
     * @param iri {@link IRI}
     * @return {@link Thing}
    */
    public Thing get(final IRI iri) {
        return db.get(iri);
    }

}
