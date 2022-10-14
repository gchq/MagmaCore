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
 * A {@link ClassOfFunctionalObject}, that is also a {@link KindOfPhysicalObject}, and a
 * {@link KindOfIntentionallyConstructedObject} where each {@code member_of} a
 * {@link KindOfFunctionalObject} is of the same kind.
 */
public interface KindOfFunctionalObject
        extends ClassOfFunctionalObject, KindOfPhysicalObject, KindOfIntentionallyConstructedObject {
}
