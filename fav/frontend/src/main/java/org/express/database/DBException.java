package org.express.database;

public class DBException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public DBException(String e)
	{
		super(e);
		System.out.println(e);
	}

	public DBException(Exception e)
	{
		super(e);
		e.printStackTrace();
	}
}
