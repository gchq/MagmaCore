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
 * A {@link ClassOfState} where each member of the set is a member of a {@link PhysicalProperty}
 * within the range.
 *
 * <p>
 * Note: The {@link PhysicalPropertyRange} is a supertype of each {@link PhysicalProperty} in the
 * range.
 * </p>
 */
public interface PhysicalPropertyRange extends ClassOfState {
}
