/*
 * Copyright 2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package uk.gov.gchq.magmacore.hqdm.rdfbuilders;

import uk.gov.gchq.magmacore.hqdm.exception.HqdmException;
import uk.gov.gchq.magmacore.hqdm.model.ParticipantInActivityOrAssociation;
import uk.gov.gchq.magmacore.hqdm.rdf.iri.IRI;
import uk.gov.gchq.magmacore.hqdm.services.SpatioTemporalExtentServices;

/**
 * Builder for constructing instances of ParticipantInActivityOrAssociation.
 */
public class ParticipantInActivityOrAssociationBuilder {

    private final ParticipantInActivityOrAssociation participantInActivityOrAssociation;

    /**
     * Constructs a Builder for a new ParticipantInActivityOrAssociation.
     *
     * @param iri IRI of the ParticipantInActivityOrAssociation.
     */
    public ParticipantInActivityOrAssociationBuilder(final IRI iri) {
        participantInActivityOrAssociation = SpatioTemporalExtentServices
                .createParticipantInActivityOrAssociation(iri);
    }

    /**
     * Returns an instance of ParticipantInActivityOrAssociation created from the properties set on this
     * builder.
     *
     * @return The built ParticipantInActivityOrAssociation.
     * @throws HqdmException If the ParticipantInActivityOrAssociation is missing any mandatory
     *                       properties.
     */
    public ParticipantInActivityOrAssociation build() throws HqdmException {
        return participantInActivityOrAssociation;
    }
}
