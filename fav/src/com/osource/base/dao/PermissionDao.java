/**
 * @author luoj
 * @create 2009-6-24
 * @file PermissionDao.java
 * @since v0.1
 * 
 */
package com.osource.base.dao;

import java.util.List;

import com.osource.base.common.menu.FuncNode;
import com.osource.base.common.menu.MenuNode;

/**
 * @author luoj
 *
 */
public interface PermissionDao {
	/**
	 * 获取所有功能点
	 * @return
	 */
	public List getAllFunc();
	
	/**
	 * 根据用户名获取有权限的功能列表
	 * @param userId
	 * @return
	 */
	public List<FuncNode> getFuncs(Integer userId);
	/**
	 * 根据用户名获取有权限的功能列表
	 * @param userId
	 * @return
	 */
	public List<FuncNode> getFuncsWhiteList();
	/**
	 * 根据用户名获取有权限的功能列表
	 * @param userId
	 * @return
	 */
	public List<FuncNode> getFuncsBinding();
	/**
	 * 获取所有模块导航菜单
	 * @return
	 */
	public List<MenuNode> getAllMenus();
	/**
	 * 获取模块导航菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus();
	/**
	 * 获取模块导航菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus(Integer userId);
	/**
	 * 获取子导航菜单
	 * @param parentId 父模块或父菜单ID
	 * @return
	 */
	public List<MenuNode> getSubMenus(Integer parentId);
	/**
	 * 获取子导航菜单
	 * @param parentId 父模块或父菜单ID
	 * @return
	 */
	public List<MenuNode> getSubMenus(Integer userId, Integer parentId);
	
}
