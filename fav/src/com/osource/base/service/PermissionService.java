package com.osource.base.service;

import java.util.Map;

public interface PermissionService {
	public Map getPermissions(Integer userId, String userType);
	public Map getPermissions(Integer userId);
}
