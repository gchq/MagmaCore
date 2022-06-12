package uk.gov.gchq.magmacore.service;

import java.util.function.Function;

import uk.gov.gchq.hqdm.exception.HqdmException;
import uk.gov.gchq.hqdm.iri.IRI;
import uk.gov.gchq.hqdm.model.Thing;
import uk.gov.gchq.hqdm.services.SpatioTemporalExtentServices;
import uk.gov.gchq.magmacore.exception.DbTransformationException;

/**
 * Class representing an invertible operation to create a predicate.
 *
 * */
public class DbCreateOperation implements Function<MagmaCoreService, MagmaCoreService> {

    // The IRI of the Thing we're referring to.
    private IRI subject;

    // The IRI of the property we're referring to.
    private IRI predicate;

    // The value of the property we're referring to.
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
    public MagmaCoreService apply(final MagmaCoreService mcService) {
        Thing thing = null;
        try {
            thing = mcService.get(subject);
        } catch (final HqdmException e) {
            // The object does not exist.
        }

        if (thing == null) {
            final  var newThing = SpatioTemporalExtentServices.createSpatioTemporalExtent(subject.getIri());
            newThing.addStringValue(predicate.getIri(), object);
            mcService.create(newThing);
        } else {
            if (!thing.hasThisValue(predicate.getIri(), object)) {
                thing.addValue(predicate.getIri(), object);
                mcService.update(thing);
            } else {
                throw new DbTransformationException(
                    String.format("Triple already exists: %s, %s, %s", subject, predicate, object)
                );
            }
        }

        return mcService;
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

