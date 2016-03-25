package org.express.portal;

/**
 * If any configuration is incorrect.
 * 
 */
public class ConfigException extends IllegalArgumentException
{
	private static final long serialVersionUID = 1L;

	public ConfigException()
	{
	}

	public ConfigException(String message)
	{
		super(message);
	}

	public ConfigException(Throwable cause)
	{
		super(cause);
	}

	public ConfigException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
