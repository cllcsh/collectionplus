/**
 * @author luoj
 * @create 2009-6-22
 * @file MenuDaoImpl.java
 * @since v0.1
 * 
 */
package com.osource.base.dao.impl;

import java.util.List;

import com.osource.base.common.menu.MenuNode;
import com.osource.base.dao.MenuDao;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * @author luoj
 *
 */
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao{

	public List<MenuNode> getAllMenus() {
		return queryForList("menu_findAllMenuNodes");
	}

	public List<MenuNode> getModuleMenus() {
		return null;
	}

	public List<MenuNode> getSubMenus(Integer parentId) {
		return null;
	}

}
