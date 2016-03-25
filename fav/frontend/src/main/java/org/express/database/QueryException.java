package org.express.database;

public class QueryException  extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public QueryException(String error)
	{
		super(error);
	}
	
	public QueryException(Throwable cause)
	{
		super(cause);
	}
}
