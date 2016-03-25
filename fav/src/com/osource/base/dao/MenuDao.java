/**
 * @author luoj
 * @create 2009-6-22
 * @file MenuDao.java
 * @since v0.1
 * 
 */
package com.osource.base.dao;

import java.util.List;

import com.osource.base.common.menu.MenuNode;
/**
 * @author luoj
 *
 */
public interface MenuDao {
	
	/**
	 * 获取模块功能菜单
	 * @return
	 */
	public List<MenuNode> getAllMenus();
	
	/**
	 * 获取模块功能菜单
	 * @return
	 */
	public List<MenuNode> getModuleMenus();
	/**
	 * 获取子菜单
	 * @param parentId 父模块或父菜单ID
	 * @return
	 */
	public List<MenuNode> getSubMenus(Integer parentId);
}
