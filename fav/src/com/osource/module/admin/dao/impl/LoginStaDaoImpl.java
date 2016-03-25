package com.osource.module.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.module.admin.dao.LoginStaDao;
import com.osource.module.admin.model.LoginStaBean;
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
public class LoginStaDaoImpl extends BaseDaoImpl<LoginStaBean> implements LoginStaDao {

	public String getEntityName() {
		return "admin_loginLog";
	}
	
	public void deleteLoginStaById(String id) {
		if(!StringUtil.isEmpty(id)){
			if(id.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(id,0);
				if(ids != null)
					getSqlMapClientTemplate().update("admin_loginSta_deletes", ids);
			} else {
				getSqlMapClientTemplate().update("admin_loginSta_delete", id);
			}
		}
	}

	public List findLoginStasList(LoginStaBean condition, int start, int limit) {
		return getSqlMapClientTemplate().queryForList("admin_loginSta_query", condition, start, limit);
	}

	public Integer getLoginStaCount(LoginStaBean condition) {
		return (Integer) getSqlMapClientTemplate().queryForObject("admin_loginSta_getCount",condition);
	}

	public List<LoginStaBean> exportQuery(Map condition) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("admin_loginSta_exportQuery", condition);
	}
}
