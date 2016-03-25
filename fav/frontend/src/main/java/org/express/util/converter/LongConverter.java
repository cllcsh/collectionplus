package org.express.util.converter;

/**
 * Convert String to Long.
 */
public class LongConverter implements Converter<Long>
{
	public Long convert(String s)
	{
		return Long.parseLong(s);
	}
}
