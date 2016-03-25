package org.express.portal.renderer;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;

public class JSONRenderer extends Renderer 
{
	private String characterEncoding = "UTF-8";
	private Object obj;
	
	public JSONRenderer()
	{
		
	}
	
	public JSONRenderer(Object obj)
	{
		this.obj = obj;
	}
	
	public JSONRenderer(Object obj , String code)
	{
		this.obj = obj;
		this.characterEncoding = code;
	}
	
	public String getCharacterEncoding()
	{
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding)
	{
		this.characterEncoding = characterEncoding;
	}

	public Object getObject()
	{
		return obj;
	}

	public void setObject(Object obj)
	{
		this.obj = obj;
	}

	@Override
	public void render(ServletContext context , HttpServletRequest request , HttpServletResponse response) throws Exception 
	{
		response.setCharacterEncoding(characterEncoding);
		PrintWriter pw = response.getWriter();
		pw.write(JSONObject.toJSONString(obj));
		pw.flush();
	}
}
