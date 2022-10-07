package uk.gov.gchq.magmacore.service.dto;

import java.util.Set;

import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * Details of a {@link Participant} and its Set of {@link Role}.
 *
 */
public class ParticipantDetails {

    /**
     * Convert an instance to a human-readable {@link String}.
     *
     * @return {@link String}.
     */
    @Override
    public String toString() {
        return "ParticipantDetails [participant=" + participant + ", roles=" + roles + "]";
    }

    /** TODO: Comment. */
    public final Participant participant;

    /** TODO: Comment. */
    public final Set<Role> roles;

    /**
     * Constructor.
     *
     * @param participant A {@link Participant}.
     * @param roles       A {@link Set} of {@link Role}.
     */
    public ParticipantDetails(final Participant participant, final Set<Role> roles) {
        this.participant = participant;
        this.roles = roles;
    }
}
