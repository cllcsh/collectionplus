package org.express.portal;

import java.security.MessageDigest;
import java.util.UUID;

import javax.servlet.ServletException;

import org.express.portal.container.ContainerFactory;
import org.express.portal.template.TemplateFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils for create ContainerFactory, TemplateFactory, etc.
 */
public class Utils
{
	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	public static ContainerFactory createContainerFactory(String name) throws ServletException
	{
		ContainerFactory cf = tryInitContainerFactory(name);
		if (cf == null)
			cf = tryInitContainerFactory(ContainerFactory.class.getPackage().getName() + "." + name + ContainerFactory.class.getSimpleName());
		if (cf == null)
			throw new ConfigException("Cannot create container factory by name '" + name + "'.");
		return cf;
	}

	static ContainerFactory tryInitContainerFactory(String clazz)
	{
		try
		{
			Object obj = Class.forName(clazz).newInstance();
			if (obj instanceof ContainerFactory)
				return (ContainerFactory) obj;
		}
		catch (Exception e)
		{
		}
		return null;
	}

	public static TemplateFactory createTemplateFactory(String name)
	{
		TemplateFactory tf = tryInitTemplateFactory(name);
		if (tf == null)
			tf = tryInitTemplateFactory(TemplateFactory.class.getPackage().getName() + "." + name + TemplateFactory.class.getSimpleName());
		if (tf == null)
		{
			log.warn("Cannot init template factory '" + name + "'.");
			throw new ConfigException("Cannot init template factory '" + name + "'.");
		}
		return tf;
	}

	static TemplateFactory tryInitTemplateFactory(String clazz)
	{
		try
		{
			Object obj = Class.forName(clazz).newInstance();
			if (obj instanceof TemplateFactory)
				return (TemplateFactory) obj;
		}
		catch (Exception e)
		{
		}
		return null;
	}
	
	public static String getMd5Sum(String text)
	{
		if (text == null)
			return "";

		StringBuffer hexString = new StringBuffer();

		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());

			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++)
			{
				text = Integer.toHexString(0xFF & digest[i]);
				if (text.length() < 2)
				{
					text = "0" + text;
				}
				hexString.append(text);
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hexString.toString();
	}
	
	public static String generateToken()
	{
		String token = UUID.randomUUID().toString();
		token = token.substring(token.lastIndexOf("-")+1);
		return token;
	}
}
