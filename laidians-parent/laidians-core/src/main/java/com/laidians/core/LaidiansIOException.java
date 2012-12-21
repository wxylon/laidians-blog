/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.core;

import java.io.IOException;

/**
 * Subclass of {@link IOException} that properly handles a root cause,
 * exposing the root cause just like LaidiansChecked/LaidiansRuntimeException does.
 *
 * <p>Proper root cause handling has not been added to standard IOException before
 * Java 6, which is why we need to do it ourselves for Java 5 compatibility purposes.
 *
 * <p>The similarity between this class and the LaidiansChecked/LaidiansRuntimeException
 * class is unavoidable, as this class needs to derive from IOException. 
 * @author wxylon@gmail.com
 * @see #getMessage
 * @see #printStackTrace
 * @see com.laidians.core.LaidiansCheckedException
 * @see com.laidians.core.LaidiansRuntimeException
 * @date 2012-12-21
 */
public class LaidiansIOException extends IOException {
	
	private static final long serialVersionUID = -4493060080625943250L;

	static {
		LaidiansExceptionUtils.class.getName();
	}

	public LaidiansIOException(String msg) {
		super(msg);
	}

	public LaidiansIOException(String msg, Throwable cause) {
		super(msg);
		initCause(cause);
	}

	@Override
	public String getMessage() {
		return LaidiansExceptionUtils.buildMessage(super.getMessage(), getCause());
	}

}
