package com.osource.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.common.MenuManager;
import com.osource.base.common.menu.MenuNode;
import com.osource.base.service.MenuService;

@Service
@Scope("prototype")
@Transactional
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuManager menuManager;
	
	/**
	 * 获取模块菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus(Integer userId, String userType){
		
		List<MenuNode> menus = menuManager.getModuleMenus(userId, userType);
		
		return menus;
	}
	
	public String getFirstModuleLink(Integer userId, String userType){
		List<MenuNode> modules = menuManager.getModuleMenus(userId, userType);
		if(modules.isEmpty())
			return "";
		else return modules.get(0).getLink() + "?moduleId=" + modules.get(0).getId();
	}
	
	/**
	 * 获取子菜单
	 * @param parentId 父模块或父菜单ID
	 * @return
	 */
	public List<MenuNode> getSubMenus(Integer userId, Integer parentId, String userType){
		List<MenuNode> menus = menuManager.getSubMenus(userId, parentId, userType);
		return menus;
	}
	
	public String getFirstFunLink(Integer userId, Integer parentId, String userType){
		if(menuManager.getSubMenus(userId, parentId, userType).isEmpty())
			return "";
		else return menuManager.getSubMenus(userId, parentId, userType).get(0).getLink();
	}

	public MenuManager getMenuManager() {
		return menuManager;
	}

	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}
}
