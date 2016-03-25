/**   
 * 文件名：DeptSelectUI.java   

 */
package com.osource.module.system.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.Constants;
import com.osource.base.dao.IctListUIDao;
import com.osource.base.util.Entry;
import com.osource.base.web.UserSession;
import com.osource.module.system.dao.DeptDao;
import com.osource.module.system.model.DeptInfo;


/**   
 *    
 * 项目名称：osource   
 * <p>类名称：DeptSelectUI   
 * <p>类描述：   
 * <p>创建人：luoj   
 * <p>创建时间：2009-11-11 上午10:42:17   
 * <p>修改人：luoj   
 * <p>修改时间：2009-11-11 上午10:42:17   
 * <p>修改备注：   
 * @version    
 *    
 */
@Component("deptSelect")
public class DeptSelectUI implements IctListUIDao {
	
	@Autowired
	private DeptDao deptDao;
	/**
	 * getDeptSelectList(根据当前登录用户取得该机构及其下级机构树形列表)
	 * <p>在jsp页面用ict标签使用
	 * <p><code>&lt;ict:select beanContextId="deptSelect"/&gt;</code>
	 * <p>显示列表形如
	 * <p><b>江苏</b>
	 * <p><b>--南京</b>
	 * <p><b>----鼓楼区</b>
	 * <p><b>--苏州</b>
	 * @param pid 父机构id    
	 * @return <b>Entry<机构id, 机构名><b>    
	 */
	public List<Entry<String, String>> getIctList(HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute(Constants.USER_SESSION_KEY);
		if(userSession == null) {
			return new ArrayList();
//			throw new new java.lang.NullPointerException("userSession is null");
		}
		List<Entry<String, String>> result = deptDao.getDeptSelectList(userSession.getDeptId());
		return result;
	}

	/* (non-Javadoc)   
	 * @see com.osource.base.dao.IctListUIDao#getIctValue(java.lang.String)   
	 */
	public String getIctValue(String key) {
		DeptInfo deptInfo = deptDao.findDeptInfoById(Integer.parseInt(key));
		if (null != deptInfo)
		{
			return deptInfo.getName();
		}
		
		return null;
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

}
