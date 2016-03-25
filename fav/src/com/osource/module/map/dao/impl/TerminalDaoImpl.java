package com.osource.module.map.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.osource.base.common.location.ITerminal;
import com.osource.base.dao.TerminalDao;
import com.osource.base.model.Location;
import com.osource.base.model.Terminal;
import com.osource.base.model.TerminalExt;
import com.osource.core.exception.IctException;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * @author : FengJingzhun
 * @version : 1.0
 * @date : 2009-6-26 14:24:23
 */
@Component
public class TerminalDaoImpl extends BaseDaoImpl implements TerminalDao
{

    public TerminalExt select(Integer id) {
        return (TerminalExt) queryForObject("Terminal_select", id);
    }

    public Terminal selectByLocNum(String locationNum) {
        return (Terminal) queryForObject("Terminal_selectByLocNum", locationNum);
    }
    public List<Terminal> findByIds(List<Integer> ids) {
    	 return queryForList("Terminal_findByIds", ids);
    }

	public List<Terminal> findByImsi(String imsi) {
		return null;
	}

	public List<ITerminal> queryByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateLastLocationInfo(Location arg0) throws IctException {
		// TODO Auto-generated method stub
		
	}
}
