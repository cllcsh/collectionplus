package org.express.util.converter;

/**
 * Convert String to Boolean.
 */
public class BooleanConverter implements Converter<Boolean>
{
	public Boolean convert(String s)
	{
		return Boolean.parseBoolean(s);
	}
}
