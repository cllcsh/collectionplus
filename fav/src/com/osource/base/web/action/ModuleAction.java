/**
 * @author luoj
 * @create 2009-6-23
 * @file ModuleAction.java
 * @since v0.1
 * 
 */
package com.osource.base.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.common.MenuManager;
import com.osource.base.common.menu.MenuNode;
import com.osource.base.service.MenuService;
import com.osource.base.service.VersionService;

/**
 * @author luoj
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class ModuleAction extends BaseAction {

	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuManager menuManager;
	@Autowired
	private VersionService versionService;
	
	private Integer moduleId;
	
	private String mainUrl;
	
	private String subModuleId;
	
	private List<MenuNode> menus;
	
	public String center(){
		if(mainUrl != null)
			this.setMainUrl(mainUrl);
		else
		  this.setMainUrl(menuService.getFirstFunLink(getUserSession().getUserId(), moduleId, getUserSession().getUserType()));
		return RESULT_CONTENT;
	}
	
	public String menu(){
		pushValueStack("version", versionService.getCurrentVersion());
		pushValueStack("moduleName", menuManager.getMenuName(moduleId));
		this.setMenus(menuService.getSubMenus(getUserSession().getUserId(), moduleId, getUserSession().getUserType()));
		return RESULT_MENU;
	}
	
	

	public String getSubModuleId() {
		return subModuleId;
	}

	public void setSubModuleId(String subModuleId) {
		this.subModuleId = subModuleId;
	}

	public List<MenuNode> getMenus() {
		return menus;
	}
	
	public void setMenus(List<MenuNode> menus) {
		this.menus = menus;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public MenuManager getMenuManager() {
		return menuManager;
	}

	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}

	public VersionService getVersionService() {
		return versionService;
	}

	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}
	
}
