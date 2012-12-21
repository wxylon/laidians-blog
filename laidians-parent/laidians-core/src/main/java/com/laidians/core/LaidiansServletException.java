/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.core;

import javax.servlet.ServletException;

import org.springframework.core.NestedExceptionUtils;

/**
 * Subclass of {@link ServletException} that properly handles a root cause in terms
 * of message and stacktrace, just like LaidiansCheckedException/LaidiansRuntimeException does.
 *
 * <p>Note that the plain ServletException doesn't expose its root cause at all,
 * neither in the exception message nor in printed stack traces! While this might
 * be fixed in later Servlet API variants (which even differ per vendor for the
 * same API version), it is not reliably available on Servlet 2.4 (the minimum
 * version required by Spring 3.x), which is why we need to do it ourselves.
 *
 * <p>The similarity between this class and the LaidiansCheckedException/LaidiansRuntimeException
 * class is unavoidable, as this class needs to derive from ServletException.
 *
 * @author Juergen Hoeller
 * @since 1.2.5
 * @see #getMessage
 * @see #printStackTrace
 * @see LaidiansRuntimeException
 * @see LaidiansCheckedException
 */
public class LaidiansServletException extends ServletException {

	private static final long serialVersionUID = 6738006923242805736L;


	static {
		NestedExceptionUtils.class.getName();
	}


	/**
	 * Construct a <code>LaidiansServletException</code> with the specified detail message.
	 * @param msg the detail message
	 */
	public LaidiansServletException(String msg) {
		super(msg);
	}

	/**
	 * Construct a <code>LaidiansServletException</code> with the specified detail message
	 * and nested exception.
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public LaidiansServletException(String msg, Throwable cause) {
		super(msg, cause);
		// Set JDK 1.4 exception chain cause if not done by ServletException class already
		// (this differs between Servlet API versions).
		if (getCause() == null && cause!=null) {
			initCause(cause);
		}
	}


	/**
	 * Return the detail message, including the message from the nested exception
	 * if there is one.
	 */
	@Override
	public String getMessage() {
		return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
	}

}
