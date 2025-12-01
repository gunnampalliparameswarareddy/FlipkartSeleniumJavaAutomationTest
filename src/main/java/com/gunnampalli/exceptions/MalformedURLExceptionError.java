package com.gunnampalli.exceptions;

@SuppressWarnings("serial")
public class MalformedURLExceptionError  extends FrameworkExceptions{
	public MalformedURLExceptionError()
	{
		
	}
	public MalformedURLExceptionError(String message)
	{
		super(message);
	}
	public MalformedURLExceptionError(String message,Throwable cause)
	{
		super(message,cause);
	}
}
