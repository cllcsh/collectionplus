package org.express.portal;

import java.io.File;
import java.net.URL;
import java.util.Properties;

public class PathUtil
{
	private static File appPath;
	private static File serverPath;
	private static File webContextPath;
	
	private static File cfgPath;
	private static File dataPath;
	private static File uploadPath;
	private static String uploadImgPath;
	private static String uploadImgFileName;
	private static String commonFileWebContextPath;
	private static String imgWebContextPath;
	private static Properties pathProperties;
	
	private static PathUtil instance = new PathUtil();
	
	public static PathUtil getInstance() {
		return instance;
	}
	
	public static Properties getPathProperties() {
		return pathProperties;
	}

	public static void setPathProperties(Properties pathProperties) {
		PathUtil.pathProperties = pathProperties;
	}

	static
	{
		try
		{
			pathProperties = new Properties();
			pathProperties.load(PathUtil.class.getResourceAsStream("/config/path.properties"));
			Properties cp_props = new Properties();
			for (Object key : pathProperties.keySet())
			{
				String skey = (String) key;
				if (skey.startsWith("path."))
				{
					String name = skey.substring(5);
					cp_props.put(name, pathProperties.getProperty(skey));
				}
			}
			
			File path = new File(cp_props.getProperty("serverPath")).getAbsoluteFile();
			serverPath = path;
			if( path.getPath().endsWith("tomcat") || path.getPath().indexOf("tomcat")!=-1)
			{
				appPath = path.getParentFile();
			}
			else
			{
				appPath = path;
			}
			
			path = new File(cp_props.getProperty("commonFilePath")).getAbsoluteFile();
			cfgPath = new File(path, "config/");
			dataPath = new File(path, "data/");
			uploadPath = new File(path, "upload/");
			uploadImgPath = cp_props.getProperty("uploadImgPath");
			uploadImgFileName = cp_props.getProperty("uploadImgFileName");
			commonFileWebContextPath = cp_props.getProperty("commonFileWebContextPath");
			imgWebContextPath = cp_props.getProperty("imgWebContextPath");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 取应用根目录,亦即安装目录
	 * @return String
	 */
	public static File getAppPath()
	{
		return appPath;
	}
	
	/**
	 * 取tomcat目录
	 * @return String
	 */
	public static File getServerPath()
	{
		return serverPath;
	}
	
	/**
	 * path配置文件公共filepath下的data目录
	 * @return
	 */
	public static File getDataPath()
	{
		return dataPath;
	}
	
	/**
	 * path配置文件公共filepath下的config目录
	 * @return
	 */
	public static File getConfigPath()
	{
		return cfgPath;
	}
	
	/**
	 * path配置文件公共filepath下的upload目录
	 * @return
	 */
	public static File getUploadPath()
	{
		return uploadPath;
	}
	
	/**
	 * path下的上传图片目录路径
	 * @return String
	 */
	public static String getUploadImgPath()
	{
		return uploadImgPath;
	}
	
	/**
	 * path下的上传图片文件名
	 * @return String
	 */
	public static String getUploadImgFileName()
	{
		return uploadImgFileName;
	}
	
	/**
	 * 取web应用目录
	 * @return String
	 */
	public static File getWebContextPath()
	{
		if(webContextPath == null)
		{
			URL url = Thread.currentThread().getContextClassLoader().getResource("");
			File file = new File(url.getFile());
			webContextPath = file.getParentFile().getParentFile();
		}
		return webContextPath;
	}

	/**
	 * 取公共文件请求用url地址
	 * @return
	 */
	public static String getCommonFileWebContextPath() {
		return commonFileWebContextPath;
	}
	
	/**
     * 取图片服务器请求用url地址
     * @return
     */
    public static String getImgWebContextPath() {
        return imgWebContextPath;
    }
}
