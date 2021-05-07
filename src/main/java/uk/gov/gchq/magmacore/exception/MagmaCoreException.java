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

package uk.gov.gchq.magmacore.exception;

/**
 * Superclass of exceptions arising from MagmaCore.
 */
public class MagmaCoreException extends Exception {

    /**
     *
     */
    public MagmaCoreException() {
        super();
    }

    /**
     *
     * @param message
     */
    public MagmaCoreException(final String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public MagmaCoreException(final Throwable cause) {
        super(cause);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public MagmaCoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
