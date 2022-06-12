package uk.gov.gchq.magmacore.service;

import java.util.function.Function;

import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.magmacore.exception.DbTransformationException;

/**
 * Class representing an invertible operation to delete a predicate.
 *
 * */
public class DbDeleteOperation implements Function<MagmaCoreService, MagmaCoreService> {
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
    public DbDeleteOperation(final IRI subject, final IRI predicate, final String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
    }

    /**
     * Apply the operation to a {@link MagmaCoreService}.
     *
     * @param mcService {@link MagmaCoreService}
     * */
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        final var thing = mcService.get(subject);

        if (thing != null && thing.hasThisValue(predicate.getIri(), object)) {
            thing.removeValue(predicate.getIri(), object);
            mcService.update(thing);
            return mcService;
        }

        throw new DbTransformationException(
                String.format("Triple not found for delete: %s, %s, %s", subject, predicate, object)
                );
    }

    /**
     * Invert a {@link DbDeleteOperation}.
     *
     * @param d the {@link DbDeleteOperation}
     * @return {@link DbCreateOperation}
    */
    public static DbCreateOperation invert(final DbDeleteOperation d) {
        return new DbCreateOperation(d.subject, d.predicate, d.object);
    }
}
