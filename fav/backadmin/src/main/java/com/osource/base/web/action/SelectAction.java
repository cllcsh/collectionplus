/**
 * 
 */
package com.osource.base.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osource.base.dao.SelectUIDao;
import com.osource.core.BeanProvider;
import com.osource.util.Entry;

/**
 * @author Fun
 * 该Action用来处理下拉列表的生成
 */
public class SelectAction extends BaseAction{
	
	private Integer parentId;
	private String beanContextId;
	//其值为“正常”状态的值，在矫正对象列表中，用于区分查全部还是查除“正常”以外的矫正对象
	private Integer flag;
	
	/**
	 * 根据条件获得矫正对象列表
	 * @return
	 */
	public String getIctList(){
		Map condition = new HashMap();
		condition.put("deptId", this.getParentId());
		condition.put("flag", this.getFlag());
		
		SelectUIDao selectUIDao = BeanProvider.getBean(beanContextId);
		List<Entry> result = selectUIDao.getSelectList(condition);
		
		return returnJsonString(result);
	}
	
	/*************************************/

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getBeanContextId() {
		return beanContextId;
	}

	public void setBeanContextId(String beanContextId) {
		this.beanContextId = beanContextId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
