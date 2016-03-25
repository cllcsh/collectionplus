package com.osource.base.form.util;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class Struts2Util
{
  private static final String ENCODING_PREFIX = "encoding:";
  private static final String NOCACHE_PREFIX = "no-cache:";
  private static final String ENCODING_DEFAULT = "UTF-8";
  private static final boolean NOCACHE_DEFAULT = true;
  private static Log logger = LogFactory.getLog(Struts2Util.class);

  public static HttpSession getSession()
  {
    return ServletActionContext.getRequest().getSession();
  }

  public static ServletContext getServletContext()
  {
    return ServletActionContext.getServletContext();
  }

  public static HttpServletRequest getRequest()
  {
    return ServletActionContext.getRequest();
  }

  public static HttpServletResponse getResponse()
  {
    return ServletActionContext.getResponse();
  }

  public static String getParameter(String name)
  {
    return getRequest().getParameter(name);
  }

  public static String[] getParameters(String name)
  {
    return getRequest().getParameterValues(name);
  }

  public static void render(String contentType, String content, String[] headers)
  {
    try
    {
      String encoding = "UTF-8";
      boolean noCache = true;
      for (String header : headers) {
        String headerName = StringUtils.substringBefore(header, ":");
        String headerValue = StringUtils.substringAfter(header, ":");

        if (StringUtils.equalsIgnoreCase(headerName, "encoding:"))
          encoding = headerValue;
        else if (StringUtils.equalsIgnoreCase(headerName, "no-cache:"))
          noCache = Boolean.parseBoolean(headerValue);
        else {
          throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
        }
      }
      HttpServletResponse response = ServletActionContext.getResponse();

      String fullContentType = contentType + ";charset=" + encoding;
      response.setContentType(fullContentType);
      if (noCache) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
      }

      response.getWriter().write(content);
    }
    catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }

  public static void renderText(String text, String[] headers)
  {
    render("text/plain", text, headers);
  }

  public static void renderHtml(String html, String[] headers)
  {
    render("text/html", html, headers);
  }

  public static void renderXml(String xml, String[] headers)
  {
    render("text/xml", xml, headers);
  }

  public static void renderJson(String string, String[] headers)
  {
    render("application/json", string, headers);
  }
}