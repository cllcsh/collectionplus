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
 * @author Fun
 *
 */
public class CriminalSelectUIDao extends BaseDaoImpl implements SelectUIDao{
	
	public List<Entry> getSelectList(Map condition) {
		List<Entry> result = getSqlMapClientTemplate().queryForList("SelectUI_findCriminalListByCondition", condition);
		return result;
	}

}
