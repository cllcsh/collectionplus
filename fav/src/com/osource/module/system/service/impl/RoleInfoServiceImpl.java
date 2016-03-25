package com.osource.module.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.common.FuncManager;
import com.osource.base.web.UserSession;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.RoleInfoDao;
import com.osource.module.system.model.RoleBean;
import com.osource.module.system.model.RoleFunction;
import com.osource.module.system.service.RoleInfoService;
import com.osource.module.system.web.form.RoleInfoForm;
import com.osource.orm.ibatis.BaseServiceImpl;



/**   
 *    
 * 项目名称：osource   
 * 类名称：RoleInfoService   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 5, 2009 7:02:49 PM   
 * 修改人：weiwu   
 * 修改时间：Nov 5, 2009 7:02:49 PM   
 * 修改备注：   
 * @version    
 *    
 */
@SuppressWarnings("unchecked")
@Service
@Scope("prototype")
@Transactional
public class RoleInfoServiceImpl extends BaseServiceImpl implements RoleInfoService{
	
	@Autowired
	private RoleInfoDao roleInfoDao;
	@Autowired
	private FuncManager funcManager;
	/**
	 * 根据角色Id删除信息
	 * @param id
	 * @throws IctException
	 */
	public void deleteRoleInfoById(String id) throws IctException {
			roleInfoDao.deleteRoleInfoById(id);
	}
	
	public void deleteRoleInfoByIds(String id) throws IctException {
			roleInfoDao.deleteRoleInfoByIds(id);
	}
	
	/**
	 * 根据角色Id返回角色信息
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public RoleBean findRoleInfoById(String id){
		return roleInfoDao.findRoleInfoById(id);
	}
	@Transactional(readOnly = true)
	public List<RoleBean> findRoleInfoList(Map condition){
		return roleInfoDao.findRoleInfoList(condition);
	}
	
	/**
	 * 根据机构Id返回角色信息,added by lifa,2010-2-26
	 * @param condition
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<RoleBean> findRolesByDepartmentId(Map condition){
		return roleInfoDao.findRolesByDepartmentId(condition);
	}

	/**
	 * 返回所有有效角色数量
	 * @return
	 */
	@Transactional(readOnly = true)
	public long getAllRoleNum(){
		return roleInfoDao.getAllRoleNum();
	}
	
	/**
	 * 返回所有角色信息
	 * @param orderby
	 * @param ascOrDesc
	 * @param pages
	 * @return
	
	public PageList findRoleInfoList(String orderby, int ascOrDesc, Pages pages){
		PageList result = new PageList();
		pages.setTotal(getAllRoleNum());
		pages.executeCount();
		result.setPages(pages);
		result.setResults(roleInfoDao.findRoleInfoList(orderby, ascOrDesc, pages.getStart(), pages.getLimit()));
		return result;
	} */
	/**
	 * 根据条件返回角色数量
	 * @param condition
	 * @return
	 */
	@Transactional(readOnly = true)
	public long getRoleNumByCondition(Map condition){
		return roleInfoDao.getRoleNumByCondition(condition);
	}
	
	/**
	 * 根据条件返回角色信息
	 * @param condition
	 * @param pages
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageList findRoleInfoListByCondition(RoleInfoForm roleInfoForm,Pages pages, UserSession userSession){
		Map condition = new HashMap();
		condition.put("roleName", roleInfoForm.getRoleName().trim());
		
		if(roleInfoForm.getDepartmentId() == null || "".equals(roleInfoForm.getDepartmentId()))
			roleInfoForm.setDepartmentId(String.valueOf(userSession.getDeptId()));
		condition.put("departmentId", roleInfoForm.getDepartmentId());

		PageList result = new PageList();
		pages.setTotal(getRoleNumByCondition(condition));
		pages.executeCount();
		result.setPages(pages);
		result.addAll(roleInfoDao.findRoleInfoListByCondition(condition, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	/**
	 * 更改角色功能信息
	 * @param condition
	 * @param pages
	 * @return
	 * edit by weiwu
	 * @throws IctException 
	 * @throws SQLException 
	 */
	public void updateRoleFunctionById(String roleId, List<RoleFunction> roleFunctionList) throws IctException, SQLException
	{
		if(roleFunctionList == null || roleFunctionList.isEmpty() == true){
			roleInfoDao.deleteRoleFunctionFormById(roleId);
		}else{
			roleInfoDao.deleteRoleFunctionFormById(roleId);
			for(RoleFunction rf : roleFunctionList)
			{
				roleInfoDao.saveRoleFunction(rf);
			}
		}		
	}
	/**
	 * 添加角色功能信息
	 * @param condition
	 * @param pages
	 * @return
	 * edit by weiwu
	 * @throws IctException 
	 * @throws SQLException 
	 
	public void saveRoleFunction(List<RoleFunction> roleFunctionList) throws IctException, SQLException
	{
		if(roleFunctionList != null){
			sqlMapClient.startTransaction(); 
			for(RoleFunction rf : roleFunctionList)
			{
				roleInfoDao.saveRoleFunction(rf);
			}
			sqlMapClient.commitTransaction();
			sqlMapClient.endTransaction();
		}
	}*/
	/**
	 * 查找角色功能信息
	 * @param condition
	 * @param pages
	 * @return
	 * edit by weiwu
	 * @throws IctException 
	 */
	@Transactional(readOnly = true)
	public List<RoleFunction> findRoleFunctionList(Integer roleId) {
		return roleInfoDao.findRoleFunctionById(roleId);
	}
	/**
	 * 删除角色功能信息
	 * @param condition
	 * @param pages
	 * @return
	 * edit by weiwu
	 * @throws IctException 
	 * @throws IctException 
	 */
	public void deleteRoleFunctionById(String roleId) throws IctException {
		roleInfoDao.deleteRoleFunctionFormById(roleId);
	}
	
	private List<RoleFunction> getRoleFunctionList(RoleBean roleInfoForm, UserSession userSession) throws IctException{		
		String str = roleInfoForm.getFunctionValue();
		if(str.length() != 0){
			String[] strs = str.split(",");
			List<String> tempList = Arrays.asList(strs);
			List<Integer> funcList = funcManager.getFuncsAndParents(tempList);
			List<RoleFunction> roleFunctionList = new ArrayList();
			for( Integer funcId : funcList)
			{
				Integer funId = funcId;
				RoleFunction rf = new RoleFunction(funId,Integer.valueOf(roleInfoForm.getRoleId()));
				rf.setId(IDgenerator.gettNextID("tb_role_func"));
				rf.setInsertId(userSession.getUserId());
				roleFunctionList.add(rf);
			}
			return roleFunctionList;
		} else {
			return null;
		}
	}
	
	public void saveRoleAndFunction(RoleInfoForm roleInfoForm, UserSession userSession) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IctException, SQLException
	{
		RoleBean roleInfo = new RoleBean();
		PropertyUtils.copyProperties(roleInfo, roleInfoForm);
		roleInfo.setRoleId(String.valueOf(IDgenerator.gettNextID("tb_role")));
		roleInfo.setInsertId(userSession.getUserId());
		List <RoleFunction> funcList  = getRoleFunctionList(roleInfo, userSession);
		if(funcList != null) {
			roleInfoDao.saveRoleInfo(roleInfo);
			for(RoleFunction rf : funcList) {
				roleInfoDao.saveRoleFunction(rf);
			}
		} else {
			roleInfoDao.saveRoleInfo(roleInfo);
		}
	}
	
	public void updateRoleAndFunction(RoleInfoForm roleInfoForm, UserSession userSession) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IctException, SQLException
	{
		RoleBean roleInfo = new RoleBean();
		PropertyUtils.copyProperties(roleInfo, roleInfoForm);
		roleInfo.setUpdateId(userSession.getUserId());
		List <RoleFunction> funcList = getRoleFunctionList(roleInfo, userSession);
		roleInfoDao.updateRoleInfo(roleInfo);
		updateRoleFunctionById(roleInfoForm.getRoleId(), funcList);
	}
	
	
	/*getter and setter*/	
	
	public RoleInfoDao getRoleInfoDao() {
		return roleInfoDao;
	}

	public void setRoleInfoDao(RoleInfoDao RoleInfoDao) {
		this.roleInfoDao = RoleInfoDao;
	}

	public FuncManager getFuncManager() {
		return funcManager;
	}

	public void setFuncManager(FuncManager funcManager) {
		this.funcManager = funcManager;
	}
}
