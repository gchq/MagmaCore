package uk.gov.gchq.magmacore.examples.plans;

/**
 * An entity does not match HQDM or is otherwise incorrectly specified.
 */
public class InvalidEntityectException extends RuntimeException {

    public InvalidEntityectException(final String s) {
        super(s);
    }

}

