package com.osource.module.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.RoleInfoDao;
import com.osource.module.system.model.RoleBean;
import com.osource.module.system.model.RoleFunction;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;


/**   
 *    
 * 项目名称：osource   
 * 类名称：RoleInfoDaoImpl   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 5, 2009 10:31:33 AM   
 * 修改人：weiwu   
 * 修改时间：Nov 5, 2009 10:31:33 AM   
 * 修改备注：   
 * @version    
 *    
 */

@SuppressWarnings("unchecked")
@Repository
public class RoleInfoDaoImpl extends BaseDaoImpl implements RoleInfoDao{

	public void deleteRoleInfoById(String id) throws IctException {
		update("system_role_deleteRoleById", id);
	}
	public void deleteRoleInfoByIds(String id) throws IctException {
		if(!StringUtil.isEmpty(id)){
			if(id.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(id,0);
				if(ids != null)
				update("system_role_deleteRoleInIds", ids);
			} else {
				update("system_role_deleteRoleById", id);
			}
		}
	}

	public RoleBean findRoleInfoById(String id) {
		return (RoleBean) queryForObject("system_role_findRoleById", id);
	}
	
	public List<RoleBean> findRoleInfoList(Map condition) {
		return (List<RoleBean>) queryForList("system_role_findRoleInfoList", condition);
	}
	
	//added by lifa,2010-2-26
	public List<RoleBean> findRolesByDepartmentId(Map condition){
		return queryForList("system_role_findRolesByDepartmentId", condition);
	}

	public List<RoleBean> findRoleInfoList(String orderby, int ascOrDesc, int start, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (orderby != null) {
			map.put("orderby", orderby);
			map.put("ascOrDesc", ascOrDesc);
		}
		List<RoleBean> list = (List<RoleBean>) queryForList("system_role_findRoleByCondition", map, start, limit);
		return list;
	}

	public List<RoleBean> findRoleInfoListByCondition(Map condition, int start,	int limit) {
		List<RoleBean> list = (List<RoleBean>) queryForList("system_role_findRoleByCondition", condition, start, limit);
		return list;
	}

	public long getAllRoleNum() {
		return  (Integer) queryForObject("system_role_getAllRoleNum", null);
	}

	public long getRoleNumByCondition(Map condition) {
		return  (Integer) queryForObject("system_role_getAllRoleNumByCondition", condition);
	}

	public RoleBean saveRoleInfo(RoleBean roleInfo) throws IctException {
		this.insert("system_role_saveRole", roleInfo);
		return roleInfo;
	}

	public RoleBean updateRoleInfo(RoleBean roleInfo) throws IctException {
		update("system_role_updateRole", roleInfo);
		return roleInfo;
	}

	public RoleFunction saveRoleFunction(
			RoleFunction roleFunction) throws IctException {
		this.insert("system_role_saveRoleFunction", roleFunction);
		return null;
	}
	
	public void deleteRoleFunctionFormById(String id) throws IctException {	
/*		if(!StringUtil.isEmpty(id)){
			if(id.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(id,0);
				if(ids != null)
					delete("system_role_deleteRoleFunctionByIds", ids);	
			} else {*/
				delete("system_role_deleteRoleFunctionById", id);	
/*			}
		}*/
	}
	public List<RoleFunction> findRoleFunctionById(Integer roleId) {
		return (List<RoleFunction>) queryForList("system_role_findRoleFunctionList", roleId);
	}

}
