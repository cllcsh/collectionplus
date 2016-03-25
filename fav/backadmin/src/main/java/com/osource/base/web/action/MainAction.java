/**
 * 
 */
package com.osource.base.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.common.menu.MenuNode;
import com.osource.base.service.MenuService;
import com.osource.util.DBTime;

/**
 * @author Admin
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class MainAction extends BaseAction {
	@Autowired
	private MenuService menuService;
	
	private String topUrl = "main!top.do";
	private String mainUrl;
	private String bottomUrl = "main!down.do";
	
	private List<MenuNode> moduleMenus;
	
	//added by lifa,2009-11-30.用于导航条菜单显示
	private List<MenuNode> menus; 
	
	private String headingPicUrl;
	
	public String execute() {
		
		String url = "/firstpage_init.do";
		
		if(url != ""){
			this.setMainUrl(url);
		}else{
			this.setMainUrl("/jsp/main/error.jsp");
		}
		
		return "html";
	}
	public String top(){
		this.setModuleMenus(menuService.getModuleMenus(getUserSession().getUserId(), getUserSession().getUserType()));
		return "top";
	}

	
	public String getTime(){
		return returnJsonString(DBTime.getTime());
	}
	
	public String menu(){
		moduleMenus = menuService.getModuleMenus(getUserSession().getUserId(), getUserSession().getUserType());
		
		Map resultMap = new HashMap();
		resultMap.put("status", "success");
		resultMap.put("menu", moduleMenus);
		String[] exclude = {"children","idMap"};
		String returnJson = returnJsonStringDes(resultMap,exclude);
		System.out.println(this.getJsonToString());
		return returnJson;
	}
	
	public String left(){
		return "left";
	}
	public String center(){
		return "center";
	}
	public String down(){
		return "down";
	}
	
	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public List<MenuNode> getModuleMenus() {
		moduleMenus = menuService.getModuleMenus(getUserSession().getUserId(), getUserSession().getUserType());
		return moduleMenus;
	}
	public void setModuleMenus(List<MenuNode> moduleMenus) {
		this.moduleMenus = moduleMenus;
	}
	public String getTopUrl() {
		return topUrl;
	}
	public void setTopUrl(String topUrl) {
		this.topUrl = topUrl;
	}
	public String getMainUrl() {
		return mainUrl;
	}
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	public String getBottomUrl() {
		return bottomUrl;
	}
	public void setBottomUrl(String bottomUrl) {
		this.bottomUrl = bottomUrl;
	}
	public List<MenuNode> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuNode> menus) {
		this.menus = menus;
	}
	public String getHeadingPicUrl() {
		return headingPicUrl;
	}
	public void setHeadingPicUrl(String headingPicUrl) {
		this.headingPicUrl = headingPicUrl;
	}
}
