package com.osource.module.system.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.util.Entry;
import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.system.model.RoleBean;
import com.osource.module.system.model.UserPermissionInfo;
import com.osource.module.system.model.UserRole;
import com.osource.module.system.service.DeptService;
import com.osource.module.system.service.RoleInfoService;
import com.osource.module.system.service.UserPermissionService;
import com.osource.module.system.web.form.UserPermissionForm;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class UserPermissionAction extends BaseAction {
	
	@Autowired
	private UserPermissionService userPermissionService;
	
	private UserPermissionForm userPermissionForm;
	

	//private DepartmentInfoService departmentInfoService = new DepartmentInfoService();
	
	private String userId;
	
	private List<Entry<String, String>> departmentList;//机构名下拉列表
	//private List<DepartmentInfo> departmentList;//机构名下拉列表
	
	private List<RoleBean> roleList;//角色列表（用于生成角色名复选框列表）
	private List<UserRole> userRoleList;//用户角色列表
	private List<Integer> roleIdList = new ArrayList();//用户角色Id列表（在修改用户角色时初始化显示）
	
	private String deptId; 
	
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleInfoService roleInfoService;
	

	/** action methods **/
	
	public UserPermissionAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
	
		departmentList = deptService.getDeptSelectList(getUserSession().getDeptId());
		return RESULT_INIT;
	}
	
	/**
	 * 根据所选的机构的id，来获得角色
	 * @author zhoubo
	 * @param deptId
	 * @return
	 */
	public String getRolesByDeptId(){
		Map condition = new HashMap();
		condition.put("deptNode", this.getDeptId());//该DeptId为经过补0成8位的deptId

		roleList = roleInfoService.findRolesByDepartmentId(condition);
		
		//roleList = interModuleMethods.getRoleInfoList(condition);
		return returnJsonString(roleList);
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
		
		condition.put("loginName", userPermissionForm.getLoginName().trim());
		condition.put("userName", userPermissionForm.getUserName().trim());
		if(userPermissionForm.getDeptId() == null)
			condition.put("deptId", getUserSession().getDeptId());
		else
			condition.put("deptId", userPermissionForm.getDeptId());
		
		//condition.put("deptNode",getUserSession().getDeptNode());
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(userPermissionService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加用户权限信息页面
	 */
	public String add() {
		Map condition = new HashMap();
		
		condition.put("deptNode", getUserSession().getDeptNode());
		
		try {
			departmentList = deptService.getDeptSelectList(getUserSession().getDeptId());
			roleList = roleInfoService.findRoleInfoList(condition);
			userRoleList = userPermissionService.findUserRoleList(getUserSession().getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setActionName("userPermission_save");
		return RESULT_SET;
	}

	/**
	 *  添加用户权限信息信息
	 */
	public String save() {
		UserPermissionInfo userPermissionInfo = new UserPermissionInfo();
		
		try {
			if(userPermissionForm != null)
				IctUtil.copyProperties(userPermissionInfo, userPermissionForm);
						
			//userPermissionService.save(userPermissionInfo);
			userPermissionService.saveUserPermissionInfo(userPermissionInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加用户权限信息成功");
			logger.debug("添加用户权限信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加用户权限信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑用户权限信息页面
	 */
	public String edit() throws IctException {
		
		UserPermissionInfo userPermissionInfo;
		userPermissionInfo = userPermissionService.findById(this.getId());//注意：这里用Id替换UserId
		
		UserPermissionForm userPermissionForm = new UserPermissionForm(); 
		IctUtil.copyProperties(userPermissionForm, userPermissionInfo);
		
		Map condition = new HashMap();
		
		try {
			
			departmentList = deptService.getDeptSelectList(getUserSession().getDeptId());
			
			String deptIdNode = String.valueOf(userPermissionForm.getDeptId());
			int length = deptIdNode.length();
			if(length<8){
				for(int i=0; i<(8-length); i++)
					deptIdNode = "0"+deptIdNode;
			}
			condition.put("deptNode", deptIdNode);//该DeptId为经过补0成8位的deptId
			roleList = roleInfoService.findRolesByDepartmentId(condition);
			
			userRoleList = userPermissionService.findUserRoleList(Integer.valueOf(this.getId()));//注意：这里用Id替换UserId
			if(userRoleList!=null && !userRoleList.isEmpty()){
				for(int i=0;i<userRoleList.size();i++){
					roleIdList.add(userRoleList.get(i).getRoleId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userPermissionForm.setUserRoles(userRoleList);
		
		this.setUserPermissionForm(userPermissionForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看用户权限信息页面
	 */
	public String view() throws IctException {
	
		UserPermissionInfo userPermissionInfo;
		userPermissionInfo = userPermissionService.findById(Integer.valueOf(this.getId()));//注意：这里用Id替换UserId
		userRoleList = userPermissionService.findUserRoleList(Integer.valueOf(this.getId()));//注意：这里用Id替换UserId
		
		UserPermissionForm userPermissionForm = new UserPermissionForm(); 
		IctUtil.copyProperties(userPermissionForm, userPermissionInfo);
		
		userPermissionForm.setUserRoles(userRoleList);
		this.setUserPermissionForm(userPermissionForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改用户权限信息信息
	 */
	public String update() {
		
		UserPermissionInfo userPermissionInfo = new UserPermissionInfo();
		
		try {
			IctUtil.copyProperties(userPermissionInfo, userPermissionForm);
			
			//userPermissionService.update(userPermissionInfo);
			userPermissionService.updateUserPermissionInfo(userPermissionInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改用户权限信息成功");
			logger.debug("修改用户权限信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改用户权限信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除用户权限信息信息
	 */
	public String deletes() {
		try {
			userPermissionService.deleteById(this.getIds());//注意：这里用Id替换UserId
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public UserPermissionService getUserPermissionService() {
		return userPermissionService;
	}

	public void setUserPermissionService(UserPermissionService userPermissionService) {
		this.userPermissionService = userPermissionService;
	}

	public UserPermissionForm getUserPermissionForm() {
		return userPermissionForm;
	}

	public void setUserPermissionForm(UserPermissionForm userPermissionForm) {
		this.userPermissionForm = userPermissionForm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Entry<String, String>> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Entry<String, String>> departmentList) {
		this.departmentList = departmentList;
	}

	public List<RoleBean> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public List<Integer> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public RoleInfoService getRoleInfoService() {
		return roleInfoService;
	}

	public void setRoleInfoService(RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
	}

}