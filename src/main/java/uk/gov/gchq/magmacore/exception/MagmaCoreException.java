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
 * Superclass of exceptions arising from Magma Core code.
 */
public class MagmaCoreException extends Exception {

    /**
     * Constructs a new MagmaCoreException with null as its detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause(Throwable)}.
     */
    public MagmaCoreException() {
        super();
    }

    /**
     * Constructs a new MagmaCoreException with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause(Throwable)}.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the
     *        {@link #getMessage()} method.
     */
    public MagmaCoreException(final String message) {
        super(message);
    }

    /**
     * Constructs a new MagmaCoreException with the specified cause and a detail message of
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail
     * message of cause).
     *
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()}
     *        method). (A {@code null} value is permitted, and indicates that the cause is
     *        nonexistent or unknown.)
     */
    public MagmaCoreException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new MagmaCoreException with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the
     *        {@link #getMessage()} method).
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()}
     *        method). (A {@code null} value is permitted, and indicates that the cause is
     *        nonexistent or unknown.)
     */
    public MagmaCoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
