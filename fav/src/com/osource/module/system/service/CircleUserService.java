package com.osource.module.system.service;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.CircleUserModel;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:54:11
 */
@SuppressWarnings("unchecked")
public interface CircleUserService
{
    public void saveUser(CircleUserModel circleUserModel) throws IctException;
    
    public void updateUser(CircleUserModel circleUserModel) throws IctException;
    
    public void modifyPassword(CircleUserModel circleUserModel) throws IctException;
    
    public CircleUserModel findCircleUserModelByLoginId(String loginId);
    
    public void deleteUser(String id) throws IctException;
}
