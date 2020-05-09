package org.neeraj.restwebservice.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9161142996640921988L;
	
	public DataNotFoundException(String message)
	{
		super(message);
	}
}
