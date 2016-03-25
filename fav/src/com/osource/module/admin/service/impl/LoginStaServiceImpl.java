/**   
 * 文件名：LoginLogServiceImpl.java   
 *   
 *   
 */
package com.osource.module.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.admin.dao.LoginStaDao;
import com.osource.module.admin.model.LoginStaBean;
import com.osource.module.admin.service.LoginStaService;
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
public class LoginStaServiceImpl extends BaseServiceImpl<LoginStaBean> implements LoginStaService {
	
	private LoginStaDao loginStaDao;

	  /**
	   * 根据用户名删除UserInfo对象
	   * @param userId String
	   * @return UserInfo
	   */
	public void deleteLoginStaByUserId(String userId) {
		if (!StringUtil.isEmpty(userId)) {
			loginStaDao.deleteLoginStaById(userId);
			logger.debug("根据用户名删除UserInfo对象失败");
		}
	}
	
	public PageList findLoginStaListByCondition( LoginStaBean condition,Pages pages) {
		PageList result = new PageList();
		result.setPages(pages);
		//pages.setTotal(loginLogDao.getAllLoginLogNumList(condition));  revised by zhangyan;
		pages.setTotal(loginStaDao.getLoginStaCount(condition));
		pages.executeCount();
		result.addAll(loginStaDao.findLoginStasList(condition, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	public LoginStaBean save(LoginStaBean loginLogBean) throws IctException
	{
		return loginStaDao.save(loginLogBean);
	}


	public LoginStaDao getLoginLogDao() {
		return loginStaDao;
	}

	@Autowired
	public void setLoginLogDao(LoginStaDao loginStaDao) {
		this.loginStaDao = loginStaDao;
	}

	public List<LoginStaBean> exportQuery(Map condition) {
		// TODO Auto-generated method stub
		return loginStaDao.exportQuery(condition);
	}

}
