package com.osource.module.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.osource.module.admin.dao.LoginLogDao;
import com.osource.module.admin.model.LoginLogBean;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;


/**   
 *    
 * 项目名称：osource   
 * <p>类名称：LoginLogDaoImpl   
 * <p>类描述：   
 * <p>创建人：zhangyan   
 * <p>创建时间：Nov 4, 2009 4:45:20 PM   
 * @version    
 *    
 */
@Repository
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLogBean> implements LoginLogDao {

	public String getEntityName() {
		return "admin_loginLog";
	}
	
	public void deleteLoginLogById(String id) {
		if(!StringUtil.isEmpty(id)){
			if(id.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(id,0);
				if(ids != null)
					getSqlMapClientTemplate().update("admin_loginLog_deletes", ids);
			} else {
				getSqlMapClientTemplate().update("admin_loginLog_delete", id);
			}
		}
	}

	public List findLoginLogsList(LoginLogBean condition, int start, int limit) {
		return getSqlMapClientTemplate().queryForList("admin_loginLog_query", condition, start, limit);
	}

	public Integer getLoginLogCount(LoginLogBean condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("admin_loginLog_getCount",condition);
	}
}
