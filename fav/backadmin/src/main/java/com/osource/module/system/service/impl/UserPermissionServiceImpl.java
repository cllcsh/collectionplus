package com.osource.module.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.module.system.dao.UserPermissionDao;
import com.osource.module.system.model.UserPermissionInfo;
import com.osource.module.system.model.UserRole;
import com.osource.module.system.service.UserPermissionService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class UserPermissionServiceImpl extends BaseServiceImpl<UserPermissionInfo> implements UserPermissionService {

    public List<UserRole> findUserRoleList(Integer userId) {
        return getDao().findUserRoleList(userId);
    }

    public UserPermissionInfo saveUserPermissionInfo(UserPermissionInfo userPermissionInfo) throws IctException {
        userPermissionInfo.setInsertId(getUserSession().getUserId());
        try {
            List<UserRole> userRoles = userPermissionInfo.getUserRoles();

            if (userRoles != null && !userRoles.isEmpty()) {
                for (UserRole userRole : userRoles) {
                    userRole.setId(IDgenerator.gettNextID("tb_user_role"));
                    userRole.setUserId(userPermissionInfo.getId());
                    // userRole.setDeptId(userPermissionInfo.getDeptId());
                    userRole.setInsertId(userPermissionInfo.getInsertId());
                }

                getDao().save(userPermissionInfo);

                for (UserRole userRole : userRoles) {
                    getDao().saveUserRole(userRole);
                }

            } else {
                getDao().save(userPermissionInfo);
            }

        } catch (Exception e) {
            logger.error(e);
            throw new IctException(e);
        }

        return userPermissionInfo;
    }

    public UserPermissionInfo saveUserRoleInfo(UserPermissionInfo userPermissionInfo) throws IctException {
        userPermissionInfo.setInsertId(getUserSession().getUserId());
        try {
            List<UserRole> userRoles = userPermissionInfo.getUserRoles();

            if (userRoles != null && !userRoles.isEmpty()) {

                getDao().deleteUserRoleByUserId(String.valueOf(userPermissionInfo.getId()));

                for (UserRole userRole : userRoles) {
                    userRole.setId(IDgenerator.gettNextID("tb_user_role"));
                    userRole.setUserId(userPermissionInfo.getId());
                    // userRole.setDeptId(userPermissionInfo.getDeptId());
                    userRole.setInsertId(userPermissionInfo.getInsertId());

                    getDao().saveUserRole(userRole);
                }
            } else {
                getDao().deleteUserRoleByUserId(String.valueOf(userPermissionInfo.getId()));
            }
        } catch (Exception e) {
            logger.error(e);
            throw new IctException(e);
        }

        return userPermissionInfo;
    }

    public UserPermissionInfo updateUserPermissionInfo(UserPermissionInfo userPermissionInfo) throws IctException {
        userPermissionInfo.setUpdateId(getUserSession().getUserId());
        try {
            List<UserRole> userRoles = userPermissionInfo.getUserRoles();
            if (userRoles != null && !userRoles.isEmpty()) {
                for (UserRole userRole : userRoles) {
                    userRole.setId(IDgenerator.gettNextID("tb_user_role"));
                    userRole.setUserId(userPermissionInfo.getId());
                    // userRole.setDeptId(userPermissionInfo.getDeptId());
                    userRole.setInsertId(userPermissionInfo.getUpdateId());
                }

                getDao().update(userPermissionInfo);
                getDao().deleteUserRoleByUserId(String.valueOf(userPermissionInfo.getId()));

                for (UserRole userRole : userRoles) {
                    getDao().saveUserRole(userRole);
                }

            } else {

                getDao().update(userPermissionInfo);
                getDao().deleteUserRoleByUserId(String.valueOf(userPermissionInfo.getId()));
            }

        } catch (Exception e) {
            logger.error(e);
            throw new IctException(e);
        }

        return userPermissionInfo;
    }

    /** setter and getter methods **/

    protected UserPermissionDao getDao() {
        return (UserPermissionDao) super.getDao();
    }

    @Autowired
    public void setDao(UserPermissionDao userPermissionDao) {
        super.setDao(userPermissionDao);
    }
}
