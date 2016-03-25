/**
 * 
 */
package com.osource.base.service.impl;

import java.util.List;
import java.util.Map;

import com.osource.base.dao.SelectUIDao;
import com.osource.base.service.UIService;
import com.osource.core.BeanProvider;
import com.osource.util.Entry;

/**
 * 该Service用来访问ui中的一些公共接口，如关联下拉框列表
 * @author Fun,2010-3-10
 *
 */
public class UIServiceImpl implements UIService{
	
	/**
	 * 该方法用来生成下拉列表
	 * 
	 * @param beanId:Spring中配置的beanId<P>
	 * 	其值为：<BR>
	 * rectifySelectDao-矫正对象下拉列表；
	 * managerSelectDao-矫正工作者下拉列表；
	 * resurviverSelectDao-刑释解教人员下拉列表
	 * <BR>
	 * @param condition：查询条件，包括参数deptId
	 * @return
	 */
	public List<Entry> getSelectList(String beanId, Map condition){
		
		SelectUIDao selectUIDao = BeanProvider.getBean(beanId);
		return (List<Entry>)selectUIDao.getSelectList(condition);
	}
	
}
