package com.osource.base.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.base.Constants;
import com.osource.base.dao.SequenceDao;
import com.osource.core.IDCache;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * @author : FengJingzhun
 * @version : 1.0
 * @date : 2009-5-14 14:49:29
 */
@Repository
public class SequenceDaoIbatisImpl extends BaseDaoImpl implements SequenceDao {

    private static final Integer ID_INCREMENT = Integer.valueOf(PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "ID_INCREMENT", "5"));
    
    public Integer getCurValueByName(String name) throws IctException {
    	return (Integer) queryForObject("Sequence_getCurValueByName", name);
    }

    public Integer getMaxValueByName(String name) throws IctException {
    	return (Integer) queryForObject("Sequence_getMaxValueByName", name);
    }

    public void iniCurValueByName(String name, Integer currentValue) throws IctException {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", name);
		m.put("currentValue", currentValue);
		insert("Sequence_iniCurValueByName", m);
    }

    public void updateCurValueByName(String name, Integer currentValue) throws IctException {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("name", name);
            m.put("currentValue", currentValue);
            update("Sequence_updateCurValueByName", m);
    }

	public IDCache loadTableIds(String tableName) throws IctException {
			Integer currentValue = getCurValueByName(tableName);
			Integer nextValue;
			if (currentValue == null) {
				currentValue = getMaxValueByName(tableName);
				nextValue = currentValue + ID_INCREMENT;
				iniCurValueByName(tableName, nextValue);
			} else {
				nextValue = currentValue + ID_INCREMENT;
				updateCurValueByName(tableName, nextValue);
			}
			logger.info(tableName + "======>now the currentValue is " + currentValue);
			logger.info(tableName + "======>now the nextValue is " + nextValue);
			return new IDCache(currentValue, nextValue);
	}
}
