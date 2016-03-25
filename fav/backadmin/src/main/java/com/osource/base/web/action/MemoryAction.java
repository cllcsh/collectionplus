package com.osource.base.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.common.CodeBookManager;
import com.osource.base.common.FuncManager;
import com.osource.base.common.MenuManager;
import com.osource.core.PropertiesManager;

/**
 * @author weiw
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class MemoryAction extends BaseAction {
	@Autowired
	private MenuManager menuManager;
	@Autowired
	private FuncManager funcManager;
	
	private String buttonName;

	public String init()
	{
		return RESULT_INIT;
	}
	
	public String clearMemory()
	{
		if(buttonName.equals("codebook"))
		{
			String result = clearCodeBook();
			return result;
		}
		else if(buttonName.equals("func"))
		{
			String result = clearFunc();
			return result;
		}
		else if(buttonName.equals("menu"))
		{
			String result = clearMenu();
			return result;
		}
		else if(buttonName.equals("properties"))
		{
			String result = clearProperties();
			return result;
		}
		else
		{
			String result = clearSysParams();
			return result;
		}
	}
	
	private String clearCodeBook()
	{

		CodeBookManager.getInstance().clearData();
		this.getAjaxMessagesJson().setMessage("0", "清除成功");
		return RESULT_AJAXJSON;
	}
	
	private String clearFunc()
	{
		funcManager.reloadData();
		this.getAjaxMessagesJson().setMessage("0", "清除成功");
		return RESULT_AJAXJSON;
	}
	
	private String clearMenu()
	{
		menuManager.reloadData();
		this.getAjaxMessagesJson().setMessage("0", "清除成功");
		return RESULT_AJAXJSON;
	}
	
	private String clearProperties()
	{
		PropertiesManager.clear();
		this.getAjaxMessagesJson().setMessage("0", "清除成功");
		return RESULT_AJAXJSON;
	}
	
	private String clearSysParams()
	{
//		SystemParamsReader.reload();
		this.getAjaxMessagesJson().setMessage("0", "清除成功");
		return RESULT_AJAXJSON;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public MenuManager getMenuManager() {
		return menuManager;
	}

	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}

	public FuncManager getFuncManager() {
		return funcManager;
	}

	public void setFuncManager(FuncManager funcManager) {
		this.funcManager = funcManager;
	}
}
