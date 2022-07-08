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

package uk.gov.gchq.hqdm.model;

/**
 * An {@link Individual} that {@code consists_of} the {@link Participant}s that are associated, and
 * where the {@link Participant}s are {@code part_of} the same {@link PeriodOfTime}.
 */
public interface Association extends
        Individual,
        StateOfAssociation,
        ParticipantInActivityOrAssociation {
}
