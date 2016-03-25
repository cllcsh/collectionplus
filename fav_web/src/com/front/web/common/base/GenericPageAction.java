package com.front.web.common.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.express.base.ShareBaseAction;
import org.express.portal.ActionContext;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;

import com.front.db.bean.FavUserBean;
import com.front.web.common.Constant;


public class GenericPageAction extends ShareBaseAction 
{
	public void gotoHomePage()
	{
		try
		{
			getResponse().sendRedirect(getContext().getContextPath() + Constant.HOME_URL);
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}
	
	public void gotoPage(String url)
	{
		try
		{
			getResponse().sendRedirect(getContext().getContextPath() + url);
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}

	public void gotoLoginPage()
	{
		try
		{
			getResponse().sendRedirect(getContext().getContextPath() + Constant.DEFAULT_LOGINURL);
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}
	
	public Map<String, Object> getInputmap() {
		Map<String, Object> inputmap = new HashMap<String,Object>();
		return inputmap;
	}
	
	/***
	 * 默认封装Map
	 * @return
	 */
	public Map<String, Object> getOutputMap() 
	{
		Map<String, Object> map = new HashMap<String,Object>();
		return map;
	}
	
	/**
	 * 转到错误页面
	 * @param msg
	 * @return
	 */
	public Renderer error(String msg) {	
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("error" , msg);
		return new TemplateRenderer("/page/404.html", Constant.DEFAULT_MODULE_STRING, outMap);
	}
	
	public FavUserBean getSessionUser()
	{
		return (FavUserBean)getSession().getAttribute(Constant.SESSION_USER_INFO);
	}
	/**
     * 把对象输出到页面
     * @param obj 对象（如String,Json等）
     */
    public void writerObjToPage(Object obj)
    {
        HttpServletResponse response = ActionContext.getActionContext().getHttpServletResponse();
        response.reset();
        response.setContentType("text/json;charset=UTF-8"); 
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(obj);  
            out.flush();  
        } 
        catch (IOException e) {
        }
        finally
        {
            if (out != null)
            {
                out.close();     
            }
        }
    }
    
    /**
     * 获取请求的Ip地址
     * @return String
     */
    public String getIpAddress()
    {
        HttpServletRequest request = ActionContext.getActionContext().getHttpServletRequest();
        String ip = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for") != null) {
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }
}
