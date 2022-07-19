package uk.gov.gchq.magmacore.service.dto;

import java.util.Set;

import uk.gov.gchq.magmacore.hqdm.model.Association;
import uk.gov.gchq.magmacore.hqdm.model.Participant;
import uk.gov.gchq.magmacore.hqdm.model.Role;

/**
 * Details about an {@link Association}.
 *
 * <p>
 * This class has public final fields to emulate Java `record` types. When the MagmaCore Java
 * language version is updated this should be converted to a proper `record` type.
 * </p>
 */
public class AssociationDetails {
    public final Association association;
    public final Set<ParticipantDetails> participants;

    /**
     * Constructor.
     *
     * @param association  an {@link Association}
     * @param participants a {@link Set} of {@link ParticipantDetails}
     */
    public AssociationDetails(final Association association, final Set<ParticipantDetails> participants) {
        this.association = association;
        this.participants = participants;
    }

    /**
     * Details of a {@link Participant} and its Set of {@link Role}.
     *
     */
    public static class ParticipantDetails {
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
}
