package com.gunnampalli.exceptions;

@SuppressWarnings("serial")
public class FileNotFoundExceptions extends FrameworkExceptions {

	public FileNotFoundExceptions()
	{
		
	}
	
	public FileNotFoundExceptions(String message)
	{
		super(message);
	}
	
	public FileNotFoundExceptions(String message,Throwable cause)
	{
		super(message,cause);
	}
}
