package com.osource.module.admin.service;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.admin.model.LoginStaBean;
import com.osource.orm.ibatis.BaseService;

/**   
 *    
 * 项目名称：osource   
 * 类名称：LoginLogService   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 4, 2009 5:12:03 PM   
 * @version    
 *    
 */
public interface LoginStaService extends BaseService<LoginStaBean> {
	
	  /**
	   * 根据用户名删除UserInfo对象
	   * @param userId String
	   * @return UserInfo
	   */
	public void deleteLoginStaByUserId(String userId);
	
	public PageList<LoginStaBean> findLoginStaListByCondition( LoginStaBean condition,Pages pages);
	
	public List<LoginStaBean> exportQuery(Map condition);
	
	public LoginStaBean save(LoginStaBean loginStaBean)throws IctException;
	
}
