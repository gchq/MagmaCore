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

package uk.gov.gchq.magmacore.hqdm.model;

/**
 * An {@link OrganizationComponent} that is also a {@link StateOfPosition} that may be held by a
 * {@link Person}.
 *
 * <p>
 * Note: Normally a {@link Position} is held by one {@link Person} at a time, but this does not have
 * to be the case.
 * </P>
 */
public interface Position extends OrganizationComponent, StateOfPosition {
}
