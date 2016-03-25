package com.osource.module.system.dao;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.SpecialistInfo;


/**   
 *    
 * 项目名称：osource   
 * 类名称：SpecialistDao   
 * 类描述：   
 * 创建人：Fun   
 * 创建时间：2009-11-5 上午10:12:20   
 * 修改人：Fun   
 * 修改时间：2009-11-5 上午10:12:20   
 * 修改备注：   
 * @version    
 *    
 */
@SuppressWarnings("unchecked")
public interface SpecialistDao {

	
	public SpecialistInfo findSpecialistInfoById(Integer id);
	
	
	public long getAllSpecialistNumByCondition(Map condition);
	
	
	public List findSpecialistInfosByCondition(Map condition, int start, int limit);
	

	public SpecialistInfo saveSpecialistInfo(SpecialistInfo specialistInfo) throws IctException;

	
	public SpecialistInfo updateSpecialistInfo(SpecialistInfo specialistInfo) throws IctException;
	
	
	public void deleteSpecialistInfoById(String id) throws IctException;
}
