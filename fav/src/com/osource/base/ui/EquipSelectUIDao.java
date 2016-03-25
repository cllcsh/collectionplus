/**
 * 
 */
package com.osource.base.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.osource.base.dao.SelectUIDao;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.Entry;

/**
 * @author Fun
 *
 */
public class EquipSelectUIDao extends BaseDaoImpl implements SelectUIDao{
	
	public List<Entry> getSelectList(Map condition) {
		List<Entry> result = new ArrayList();
		try
		{
			result = getSqlMapClientTemplate().queryForList("SelectUI_findEquipListByCondition", condition);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

}
