/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-26
 */
public class CallExeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CallExeException(String msg) {
		super(msg);
	}
	
	public CallExeException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

