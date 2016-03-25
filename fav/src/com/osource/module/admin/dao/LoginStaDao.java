package com.osource.module.admin.dao;

import java.util.List;
import java.util.Map;

import com.osource.module.admin.model.LoginStaBean;
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
public interface LoginStaDao extends BaseDao<LoginStaBean> { 
	
	public List<LoginStaBean> findLoginStasList(LoginStaBean condition, int start, int limit);
	
	public Integer getLoginStaCount(LoginStaBean condition);
	
	public List<LoginStaBean> exportQuery(Map condition);
    
	public void deleteLoginStaById(String id);

}
