/**   
 * 文件名：RpTypeIctListUIDaoImpl.java   

 *   
 */
package com.osource.module.system.dao.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.osource.base.Constants;
import com.osource.base.dao.IctListUIDao;
import com.osource.base.util.Entry;
import com.osource.base.web.UserSession;
import com.osource.orm.ibatis.BaseDaoImpl;


/**   
 *    
 * 项目名称：osource   
 * 类名称：RpTypeIctListUIDaoImpl   
 * 类描述：   
 * 创建人：zhangyan   
 * 创建时间：Nov 17, 2009 9:51:47 AM   
 * 修改人：zhangyan   
 * 修改时间：Nov 17, 2009 9:51:47 AM   
 * 修改备注：   
 * @version    
 *    
 */
@SuppressWarnings("unchecked")
@Repository("tb_rp_type")
public class RpTypeIctListUIDaoImpl  extends BaseDaoImpl implements IctListUIDao {
	
	public List<Entry<String, String>> getIctList(HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute(Constants.USER_SESSION_KEY);
		Integer deptId = 1;
		if(userSession != null) {
			deptId = userSession.getDeptId();
		}
		return queryForList("system_rpType_selectTagList", deptId);
	}

	/* (non-Javadoc)
	 * @see com.osource.base.dao.IctListUIDao#getIctValue(java.lang.String)   
	 */	
	public String getIctValue(String key) {
		return (String) queryForObject("system_rpType_selectTagValue", key);
	}

}
