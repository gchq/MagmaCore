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
 * A {@link SpatioTemporalExtent} that is not a proper {@code temporal_part_of} any other
 * {@link Individual} of the same kind.
 *
 * <p>
 * Note: In standard mereology a {@link SpatioTemporalExtent} is a part of itself. Parts of an
 * {@link Individual} excluding itself are called proper parts.
 * </p>
 */
public interface Individual extends State {
}
