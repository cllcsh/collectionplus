/**   
 * 文件名：SpecialistDaoIbatisImpl.java   

 *   
 */
package com.osource.module.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.SpecialistDao;
import com.osource.module.system.model.SpecialistInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;



/**   
 *    
 * 项目名称：osource   
 * 类名称：SpecialistDaoIbatisImpl   
 * 类描述：   
 * 创建人：Fun   
 * 创建时间：2009-11-5 上午10:11:23   
 * 修改人：Fun   
 * 修改时间：2009-11-5 上午10:11:23   
 * 修改备注：   
 * @version    
 *    
 */
@SuppressWarnings("unchecked")
@Repository
public class SpecialistDaoIbatisImpl extends BaseDaoImpl implements SpecialistDao{
	

	public SpecialistInfo findSpecialistInfoById(Integer id) {
		try {
			return (SpecialistInfo) getSqlMapClientTemplate().queryForObject("system_specialist_findSpecialistInfoById", id);
		} catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public long getAllSpecialistNumByCondition(Map condition) {
		int specialistNum = 0;
		try {
			specialistNum = (Integer) getSqlMapClientTemplate().queryForObject("system_specialist_getAllSpecialistNumByCondition", condition);
		} catch (Exception e) {
			logger.error(e);
		}
		return specialistNum;
	}
	
	public List<SpecialistInfo> findSpecialistInfosByCondition(Map condition, int start, int limit) {
		try {
			return (List<SpecialistInfo>) getSqlMapClientTemplate().queryForList("system_specialist_findSpecialistInfosByCondition", condition, start, limit);
		} catch(Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public SpecialistInfo saveSpecialistInfo(SpecialistInfo specialistInfo) throws IctException {
		try {
			getSqlMapClientTemplate().insert("system_specialist_saveSpecialistInfo", specialistInfo);
			return specialistInfo;
		} catch(Exception e) {
			logger.error(e);
			throw new IctException(e);
		}
	}

	
	public SpecialistInfo updateSpecialistInfo(SpecialistInfo specialistInfo) throws IctException {
		try {
			getSqlMapClientTemplate().update("system_specialist_updateSpecialistInfo", specialistInfo);
			return specialistInfo;
		} catch(Exception e) {
			logger.error(e);
			throw new IctException(e);
		}
	}
	
	public void deleteSpecialistInfoById(String id) throws IctException {
		  try{
			if(!StringUtil.isEmpty(id)){
				if(id.indexOf(",") > -1){
					String ids = StringUtil.toSqlInStr(id,0);
					if(ids != null)
						getSqlMapClientTemplate().update("system_specialist_deleteSpecialistInfoInIds", ids);
				} else {
					getSqlMapClientTemplate().update("system_specialist_deleteSpecialistInfoById", id);
				}
			}
		  }catch(Exception e){
			 e.printStackTrace();
			 logger.error(e);
		  }
		}

}
