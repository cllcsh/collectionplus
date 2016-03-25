/**
 * @author luoj
 * @create 2009-5-11
 * @file MenuService.java
 * @since v0.1
 * 
 */
package com.osource.base.service;

import java.util.List;

import com.osource.base.common.menu.MenuNode;

/**
 * @author luoj
 *
 */
public interface MenuService {
	
	/**
	 * 获取模块菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus(Integer userId, String userType);
	
	public String getFirstModuleLink(Integer userId, String userType);
	
	/**
	 * 获取子菜单
	 * @param parentId 父模块或父菜单ID
	 * @return
	 */
	public List<MenuNode> getSubMenus(Integer userId, Integer parentId, String userType);
	
	public String getFirstFunLink(Integer userId, Integer parentId, String userType);
	
}
