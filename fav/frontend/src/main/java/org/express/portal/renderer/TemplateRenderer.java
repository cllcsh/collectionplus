package org.express.portal.renderer;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.express.portal.PathUtil;
import org.express.portal.template.TemplateFactory;

import com.front.web.common.Constant;
import com.front.web.common.RuleConfig;

/**
 * Render output using template engine.
 */
public class TemplateRenderer extends Renderer
{
	private String path;
	private Map<String, Object> model;

	public TemplateRenderer(String path)
	{
		this.path = path;
		this.model = new HashMap<String, Object>();
	}

	public TemplateRenderer(String path, Map<String, Object> model)
	{
		this.path = path;
		this.model = model;
	}

	public TemplateRenderer(String path, String modelKey, Object modelValue)
	{
		this.path = path;
		this.model = new HashMap<String, Object>();
		this.model.put(modelKey, modelValue);
	}

	@Override
	public void render(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		model.put("request", request);
		model.put("pathUtil", PathUtil.getInstance());
		model.put("pageSize", RuleConfig.getInstance().getPageSize(Constant.PAGE_SIZE));
		TemplateFactory.getTemplateFactory().loadTemplate(path).render(request, response, model);
	}
}
