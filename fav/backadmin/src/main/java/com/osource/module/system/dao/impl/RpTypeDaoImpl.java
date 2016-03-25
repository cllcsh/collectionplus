/**   
 * 文件名：RpTypeDaoImpl.java   

 *   
 */
package com.osource.module.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.RpTypeDao;
import com.osource.module.system.model.RpTypeBean;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;

/**   
 *    
 * 项目名称：osource   
 * 类名称：RpTypeDaoImpl   
 * 类描述：   
 * 创建人：zhangyan   
 * 创建时间：Nov 6, 2009 2:39:28 PM   
 * 修改人：zhangyan   
 * 修改时间：Nov 6, 2009 2:39:28 PM   
 * 修改备注：   
 * @version    
 *    
 */

@SuppressWarnings("unchecked")
@Repository
public class RpTypeDaoImpl extends BaseDaoImpl implements RpTypeDao {
	
	public void insertRpType(RpTypeBean ri) throws IctException {
		insert("system_rpType_insert", ri);   
	}

	public void deletesRpType(String id) throws IctException {

		if(!StringUtil.isEmpty(id)){
			if(id.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(id,0);
				if(ids != null)
				update("system_rpType_deleteByIds", ids);
			} else {
				update("system_rpType_deleteById", id);
			}
		}
	}
	
	public boolean updateRpType(RpTypeBean ri) throws IctException {
		
		 
		int i = update("system_rpType_update", ri);
		if(i > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public RpTypeBean findRpTypeBean(String id) throws IctException {
		return (RpTypeBean) queryForObject("system_rpType_findById", id);
	}
	
	public List<RpTypeBean> queryRpTypeList(RpTypeBean rpTypeBean, int start, int limit) throws IctException{
		return queryForList("system_rpType_findList", rpTypeBean, start, limit);
	}
	
	public Integer getRpTypeCount(RpTypeBean rpTypeBean) throws IctException {
		return (Integer) queryForObject("system_rpType_getCount",rpTypeBean);
	}
	
}
