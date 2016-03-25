/**
 * 文件名：UserPermissionForm.java

 */
package com.osource.module.system.web.form;

import java.util.List;

import com.osource.base.web.form.BaseForm;
import com.osource.module.system.model.UserRole;

/**
 * 项目名称：osource
 * <p>
 * 类名称：UserPermissionForm
 * <p>
 * 类描述：用户权限管理（在镇江司法E通中叫“用户管理”）
 * <p>
 * 和“ 执法人员和专职人员登记”共用数据库中的tb_user表，为与之区分，改名为用户权限管理。
 * <p>
 * 创建人：lif
 * <p>
 * 创建时间：2010-02-26
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class UserPermissionForm extends BaseForm {

    private String loginName;
    private String userName;
    private String deptName; // 机构名字
    private List<UserRole> userRoles;

    @Override
    public void setId(Integer id) {
        super.setId(id);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                userRole.setUserId(id);
            }
        }
    }

    @Override
    public void setDeptId(Integer deptId) {
        super.setDeptId(deptId);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                // userRole.setDeptId(deptId);
            }
        }
    }

    @Override
    public void setInsertId(Integer insertId) {
        super.setInsertId(insertId);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                userRole.setInsertId(insertId);
            }
        }
    }

    @Override
    public void setUpdateId(Integer updateId) {
        super.setUpdateId(updateId);
        if (userRoles != null && !userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                userRole.setUpdateId(updateId);
            }
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}