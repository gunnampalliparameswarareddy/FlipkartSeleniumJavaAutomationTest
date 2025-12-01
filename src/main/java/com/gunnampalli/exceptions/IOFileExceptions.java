package com.gunnampalli.exceptions;

public class IOFileExceptions extends RuntimeException {
	public IOFileExceptions()
	{
		
	}
	public IOFileExceptions(String message)
	{
		super(message);
	}
	public IOFileExceptions(String message,Throwable cause)
	{
		super(message,cause);
	}
}
