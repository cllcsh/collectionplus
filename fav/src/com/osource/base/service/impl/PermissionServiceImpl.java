package com.osource.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osource.base.common.FuncManager;
import com.osource.base.common.menu.FuncNode;
import com.osource.base.dao.PermissionDao;
import com.osource.base.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private FuncManager funcManager;
	@Autowired
	private PermissionDao permissionDao;
	public Map getPermissions(Integer userId, String userType){
		Map permissions = new HashMap();
		if(userType.equalsIgnoreCase("1") || userType.equalsIgnoreCase("2")){		//根据用户类型从内存取用户功能
			List<FuncNode> mfuncs = funcManager.getFuncs(userType);
			for(FuncNode funcNode : mfuncs){
				permissions.put(funcNode.getLink(), 1);
			}
		} else {			//根据用户id去数据库取用户功能
			List<FuncNode> funcs = permissionDao.getFuncs(userId);
			for(FuncNode funcNode : funcs){
				permissions.put(funcNode.getLink(), 1);
			}
		}
		return permissions;
	}
	public Map getPermissions(Integer userId){
		Map permissions = new HashMap();
		List<FuncNode> funcs = permissionDao.getFuncs(userId);
		for(FuncNode funcNode : funcs){
			permissions.put(funcNode.getLink(), 1);
		}
		return permissions;
	}
	public FuncManager getFuncManager() {
		return funcManager;
	}
	public void setFuncManager(FuncManager funcManager) {
		this.funcManager = funcManager;
	}
	public PermissionDao getPermissionDao() {
		return permissionDao;
	}
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}
	
}
