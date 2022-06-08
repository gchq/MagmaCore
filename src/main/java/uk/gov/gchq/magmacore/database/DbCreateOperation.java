package uk.gov.gchq.magmacore.database;

import java.util.function.Function;

import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;

/**
 * Class representing an invertible operation to create a predicate.
 *
 * */
public class DbCreateOperation implements Function<MagmaCoreDatabase, MagmaCoreDatabase> {
    private IRI subject;
    private IRI predicate;
    private String object;

    /**
     * Constructor.
     *
     * @param subject {@link IRI}
     * @param predicate {@link IRI}
     * @param object {@link String}
    */
    public DbCreateOperation(final IRI subject, final IRI predicate, final String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    /**
     * {@inheritDoc}
     *
     * */
    @Override
    public MagmaCoreDatabase apply(final MagmaCoreDatabase db) {
        final var thing = db.get(subject);
        if (thing == null) {
            final  var newThing = SpatioTemporalExtentServices.createSpatioTemporalExtent(subject.getIri());
            newThing.addStringValue(predicate.getIri(), object);
            db.create(newThing);
        } else {
            if (!thing.hasThisValue(predicate.getIri(), object)) {
                thing.addValue(predicate.getIri(), object);
                db.update(thing);
            } else {
                throw new RuntimeException(
                    String.format("Triple already exists: %s, %s, %s", subject, predicate, object)
                );
            }
        }

        return db;
    }

    /**
     * Invert an operation.
     *
     * @param c {@link DbCreateOperation}
     * @return {@link DbDeleteOperation}
    */
    public static DbDeleteOperation invert(final DbCreateOperation c) {
        return new DbDeleteOperation(c.subject, c.predicate, c.object);
    }

}

