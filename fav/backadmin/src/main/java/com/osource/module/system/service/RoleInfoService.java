package com.osource.module.system.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.osource.base.web.UserSession;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.model.RoleBean;
import com.osource.module.system.model.RoleFunction;
import com.osource.module.system.web.form.RoleInfoForm;



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
public interface RoleInfoService{
	/**
	 * 根据角色Id删除信息
	 * @param id
	 * @throws IctException
	 */
	public void deleteRoleInfoById(String id) throws IctException;
	
	public void deleteRoleInfoByIds(String id) throws IctException;
	
	/**
	 * 根据角色Id返回角色信息
	 * @param id
	 * @return
	 */
	public RoleBean findRoleInfoById(String id);
	
	public List<RoleBean> findRoleInfoList(Map condition);
	
	/**
	 * 根据机构Id返回角色信息,added by lifa,2010-2-26
	 * @param condition
	 * @return
	 */
	public List<RoleBean> findRolesByDepartmentId(Map condition);

	/**
	 * 返回所有有效角色数量
	 * @return
	 */
	public long getAllRoleNum();
	
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
	public long getRoleNumByCondition(Map condition);
	
	/**
	 * 根据条件返回角色信息
	 * @param condition
	 * @param pages
	 * @return
	 */
	public PageList findRoleInfoListByCondition(RoleInfoForm roleInfoForm,Pages pages, UserSession userSession);
	
	/**
	 * 更改角色功能信息
	 * @param condition
	 * @param pages
	 * @return
	 * edit by weiwu
	 * @throws IctException 
	 * @throws SQLException 
	 */
	public void updateRoleFunctionById(String roleId, List<RoleFunction> roleFunctionList) throws IctException, SQLException;
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
	public List<RoleFunction> findRoleFunctionList(Integer roleId);
	/**
	 * 删除角色功能信息
	 * @param condition
	 * @param pages
	 * @return
	 * edit by weiwu
	 * @throws IctException 
	 * @throws IctException 
	 */
	public void deleteRoleFunctionById(String roleId) throws IctException;
	
//	public List<RoleFunction> getRoleFunctionList(RoleBean roleInfoForm, UserSession userSession) throws IctException;
	
	public void saveRoleAndFunction(RoleInfoForm roleInfoForm, UserSession userSession) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IctException, SQLException;	
	
	public void updateRoleAndFunction(RoleInfoForm roleInfoForm, UserSession userSession) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IctException, SQLException;
}
