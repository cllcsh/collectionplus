/**   
 * 文件名：LoginLogServiceImpl.java   
 *   
 *   
 */
package com.osource.module.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.admin.dao.LoginLogDao;
import com.osource.module.admin.model.LoginLogBean;
import com.osource.module.admin.service.LoginLogService;
import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.util.StringUtil;


/**   
 *    
 * 项目名称：osource   
 * <p>类名称：LoginLogServiceImpl   
 * <p>类描述：   
 * <p>创建人：luoj   
 * <p>创建时间：2010-1-9 下午01:03:10   
 * @version    
 *    
 */
@Service
@Scope("prototype")
@Transactional
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogBean> implements LoginLogService {
	
	private LoginLogDao loginLogDao;

	  /**
	   * 根据用户名删除UserInfo对象
	   * @param userId String
	   * @return UserInfo
	   */
	public void deleteLoginLogByUserId(String userId) {
		if (!StringUtil.isEmpty(userId)) {
			loginLogDao.deleteLoginLogById(userId);
			logger.debug("根据用户名删除UserInfo对象失败");
		}
	}
	
	public PageList findLoginLogListByCondition( LoginLogBean condition,Pages pages) {
		PageList result = new PageList();
		result.setPages(pages);
		//pages.setTotal(loginLogDao.getAllLoginLogNumList(condition));  revised by zhangyan;
		pages.setTotal(loginLogDao.getLoginLogCount(condition));
		pages.executeCount();
		result.addAll(loginLogDao.findLoginLogsList(condition, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	public LoginLogBean save(LoginLogBean loginLogBean) throws IctException
	{
		return loginLogDao.save(loginLogBean);
	}


	public LoginLogDao getLoginLogDao() {
		return loginLogDao;
	}

	@Autowired
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

}
