package com.osource.module.system.dao;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.CircleUserModel;

@SuppressWarnings("unchecked")
public interface CircleUserDao {
	public void saveUser(CircleUserModel circleUserModel) throws IctException;

	public void updateUser(CircleUserModel circleUserModel) throws IctException;
    
    public void modifyPassword(CircleUserModel circleUserModel) throws IctException;
    
    public CircleUserModel findCircleUserModelByLoginId(String loginId);
    
    public void deleteUser(String id) throws IctException;
}
