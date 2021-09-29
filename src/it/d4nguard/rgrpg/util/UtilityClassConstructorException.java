/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
 *
 * This file is part of RG-RPG.
 *
 * RG-RPG is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RG-RPG is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RG-RPG.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package it.d4nguard.rgrpg.util;

public class UtilityClassConstructorException extends RuntimeException {
	/**
	 * Constructs a new runtime exception with {@code null} as its
	 * detail message.  The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public UtilityClassConstructorException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * @param message
	 * 		the detail message. The detail message is saved for
	 * 		later retrieval by the {@link #getMessage()} method.
	 */
	public UtilityClassConstructorException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message and
	 * cause.  <p>Note that the detail message associated with
	 * {@code cause} is <i>not</i> automatically incorporated in
	 * this runtime exception's detail message.
	 *
	 * @param message
	 * 		the detail message (which is saved for later retrieval
	 * 		by the {@link #getMessage()} method).
	 * @param cause
	 * 		the cause (which is saved for later retrieval by the
	 *        {@link #getCause()} method).  (A {@code null} value is
	 * 		permitted, and indicates that the cause is nonexistent or
	 * 		unknown.)
	 *
	 * @since 1.4
	 */
	public UtilityClassConstructorException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a
	 * detail message of {@code (cause==null ? null : cause.toString())}
	 * (which typically contains the class and detail message of
	 * {@code cause}).  This constructor is useful for runtime exceptions
	 * that are little more than wrappers for other throwables.
	 *
	 * @param cause
	 * 		the cause (which is saved for later retrieval by the
	 *        {@link #getCause()} method).  (A {@code null} value is
	 * 		permitted, and indicates that the cause is nonexistent or
	 * 		unknown.)
	 *
	 * @since 1.4
	 */
	public UtilityClassConstructorException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new runtime exception with the specified detail
	 * message, cause, suppression enabled or disabled, and writable
	 * stack trace enabled or disabled.
	 *
	 * @param message
	 * 		the detail message.
	 * @param cause
	 * 		the cause.  (A {@code null} value is permitted,
	 * 		and indicates that the cause is nonexistent or unknown.)
	 * @param enableSuppression
	 * 		whether or not suppression is enabled
	 * 		or disabled
	 * @param writableStackTrace
	 * 		whether or not the stack trace should
	 * 		be writable
	 *
	 * @since 1.7
	 */
	public UtilityClassConstructorException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
