/**
 * 
 */
package com.osource.base.ui;

import java.util.List;
import java.util.Map;

import com.osource.base.dao.SelectUIDao;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.Entry;

/**
 * 获得矫正工作者下拉列表
 * @author Fun
 *
 */
public class ManagerSelectUIDao extends BaseDaoImpl implements SelectUIDao{
	
	public List<Entry> getSelectList(Map condition) {
		List<Entry> result = getSqlMapClientTemplate().queryForList("SelectUI_findManagerListByCondition", condition);
		return result;
	}
}
