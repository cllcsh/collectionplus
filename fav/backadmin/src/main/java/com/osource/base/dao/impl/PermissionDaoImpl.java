/**
 * @author luoj
 * @create 2009-6-24
 * @file PermissionDaoImpl.java
 * @since v0.1
 * 
 */
package com.osource.base.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.base.common.menu.FuncNode;
import com.osource.base.common.menu.MenuNode;
import com.osource.base.dao.PermissionDao;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * @author luoj
 *
 */
@Repository
public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {

	/* (non-Javadoc)
	 * @see com.osource.base.dao.PermissionDao#getAllFunc()
	 */
	public List<FuncNode> getAllFunc() {
		return queryForList("permission_findAllFuncNodes");
	}

	public List<FuncNode> getFuncsBinding() {
		return queryForList("permission_findFuncNodesHasBinding");
	}

	public List<FuncNode> getFuncsWhiteList() {
		return queryForList("permission_findFuncNodesWhileList");
	}

	/* (non-Javadoc)
	 * @see com.osource.base.dao.PermissionDao#getAllMenus()
	 */
	public List<MenuNode> getAllMenus() {
		return queryForList("permission_findAllMenuNodes");
	}

	/* (non-Javadoc)
	 * @see com.osource.base.dao.PermissionDao#getModuleMenus()
	 */
	public List<MenuNode> getModuleMenus() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.osource.base.dao.PermissionDao#getSubMenus(java.lang.Integer)
	 */
	public List<MenuNode> getSubMenus(Integer parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FuncNode> getFuncs(Integer userId) {
		return (List<FuncNode>) queryForList("permission_findFuncNodesByUserId", userId);
	}

	public List<MenuNode> getModuleMenus(Integer userId) {
		return (List<MenuNode>) queryForList("permission_findModuleMenuNodesByUserId", userId);
	}

	public List<MenuNode> getSubMenus(Integer userId, Integer parentId) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("upperId", parentId);
		return (List<MenuNode>) queryForList("permission_findSubNodesByUserId", map);
	}

}
