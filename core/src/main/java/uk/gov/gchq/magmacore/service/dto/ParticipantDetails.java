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
     * Convert an instance to a human-readble {@link String}.
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "ParticipantDetails [participant=" + participant + ", roles=" + roles + "]";
    }

    public final Participant participant;
    public final Set<Role> roles;

    /**
     * Constructor.
     *
     * @param participant a {@link Participant}.
     * @param roles       a {@link Set} of {@link Role}
     */
    public ParticipantDetails(final Participant participant, final Set<Role> roles) {
        this.participant = participant;
        this.roles = roles;
    }
}
