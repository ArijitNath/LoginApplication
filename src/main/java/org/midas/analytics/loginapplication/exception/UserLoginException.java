package org.midas.analytics.loginapplication.exception;

public class UserLoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1962221811056410979L;
	
	public UserLoginException( String message  ) {
		super(message);
	}
	
	public UserLoginException( String message, Throwable t ) {
		super(message, t);
	}
}
