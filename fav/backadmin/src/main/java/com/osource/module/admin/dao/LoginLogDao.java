package com.osource.module.admin.dao;

import java.util.List;

import com.osource.module.admin.model.LoginLogBean;
import com.osource.orm.ibatis.BaseDao;

/**   
 *    
 * 项目名称：osource   
 * 类名称：LoginLogDao   
 * 类描述：   
 * 创建人：zhangyan   
 * 创建时间：Nov 4, 2009 4:47:30 PM   
 * @version    
 *    
 */
public interface LoginLogDao extends BaseDao<LoginLogBean> { 
	
	public List<LoginLogBean> findLoginLogsList(LoginLogBean condition, int start, int limit);
	
	public Integer getLoginLogCount(LoginLogBean condition);
    
	public void deleteLoginLogById(String id);

}
