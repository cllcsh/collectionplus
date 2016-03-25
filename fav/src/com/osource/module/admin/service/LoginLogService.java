package com.osource.module.admin.service;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.admin.model.LoginLogBean;
import com.osource.orm.ibatis.BaseService;

/**   
 *    
 * 项目名称：osource   
 * 类名称：LoginLogService   
 * 类描述：   
 * 创建人：zhangyan   
 * 创建时间：Nov 4, 2009 5:12:03 PM   
 * @version    
 *    
 */
public interface LoginLogService extends BaseService<LoginLogBean> {
	
	  /**
	   * 根据用户名删除UserInfo对象
	   * @param userId String
	   * @return UserInfo
	   */
	public void deleteLoginLogByUserId(String userId);
	
	public PageList<LoginLogBean> findLoginLogListByCondition( LoginLogBean condition,Pages pages);
	
	public LoginLogBean save(LoginLogBean loginLogBean)throws IctException;
	
}
