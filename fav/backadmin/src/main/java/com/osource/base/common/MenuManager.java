/**
 * @author luoj
 * @create 2009-6-22
 * @file MenuManager.java
 * @since v0.1
 * 
 */
package com.osource.base.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.common.menu.MenuNode;
import com.osource.base.dao.PermissionDao;

/**
 * @author luoj
 *
 */
@SuppressWarnings("serial")
@Component
public class MenuManager implements Serializable{
	private static final Log logger = LogFactory.getLog(MenuManager.class);
	@Autowired
	private PermissionDao permissionDao;
	
	private List<MenuNode> moduleMenu = new ArrayList();
	private Map<Integer, MenuNode> menuMap = new LinkedHashMap();
	@PostConstruct
	public boolean init(){
		List<MenuNode> menus = permissionDao.getAllMenus();
		for(MenuNode menu : menus) {
			menuMap.put(menu.getId(), menu);
		}
		Iterator<MenuNode> it = menuMap.values().iterator();
		while(it.hasNext()){
			MenuNode menuNode = it.next();
			if(menuNode.getUpperId() == null || menuNode.getUpperId() <= 0){
				moduleMenu.add(menuNode);
			} else {
				menuNode.setParent(menuMap.get(menuNode.getUpperId()));
			}
		}
		return true;
	}
	
	/**
	 * 获取模块菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus(){
		return moduleMenu;
	}

	/**
	 * 获取模块菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus(Integer userId, String userType){
		List<MenuNode> result = new ArrayList();
		if(userType != null && (userType.equalsIgnoreCase("1") || userType.equalsIgnoreCase("2"))){
			for(MenuNode menuNode : moduleMenu){
				if(!getSubMenus(userId, menuNode.getId(), userType).isEmpty()){
					menuNode.setChildren(getSubMenus(userId, menuNode.getId(), userType));
					result.add(menuNode);
				}
			}
		} else {
			result = permissionDao.getModuleMenus(userId);
		}
		return result;
	}
	
	/**
	 * 获取子菜单
	 * @param parentId 父模块或父菜单ID
	 * @return
	 */	
	public List<MenuNode> getSubMenus(Integer userId, Integer parentId, String userType){
		List<MenuNode> result = new ArrayList();
		if(userType.equalsIgnoreCase("1") || userType.equalsIgnoreCase("2")){
			for(MenuNode menuNode : menuMap.get(parentId).getChildren()){
				if(Integer.valueOf(menuNode.getUserType()) >= Integer.valueOf(userType)){
					result.add(menuNode);
				}
			}
		} else {
			result = permissionDao.getSubMenus(userId, parentId);
		}
		return result;
	}
	
	public String getMenuName(Integer navId){
		return menuMap.get(navId).getName();
	}
	
	public void reloadData(){
		menuMap.clear();
		moduleMenu.clear();
		init();
		logger.info("清理菜单成功！");
	}

	public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
}
