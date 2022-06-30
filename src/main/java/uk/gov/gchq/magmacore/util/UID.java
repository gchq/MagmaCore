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

package uk.gov.gchq.magmacore.util;

import java.util.UUID;

/**
 * Wrapper for UUID to generate a random UUID {@link String}.
 */
public final class UID {

    private UID() {
    }

    /**
     * Create a new random UUID to assign to an object.
     *
     * @return A Random UUID {@link String}.
     */
    public static String uid() {
        return UUID.randomUUID().toString();
    }
}
