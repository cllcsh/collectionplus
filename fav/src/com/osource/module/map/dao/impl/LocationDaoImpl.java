package com.osource.module.map.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.base.dao.LocationDao;
import com.osource.base.model.Location;
import com.osource.base.model.ManRectifyBean;
import com.osource.core.exception.IctException;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class LocationDaoImpl extends BaseDaoImpl<Location> implements LocationDao {
	public String getEntityName(){
		return "Location";
	}
	
	public List<Location> findInIds(List<Integer> ids) {
		return queryForList(getEntityName() + "_findInIds", ids);
	}
	
    public void insert(Location location) throws IctException {
        insert("Location_insert", location);
    }

    public Location queryNewly(String locImsi, String agoMin) {
        Map para = new HashMap();
        para.put("locImsi", locImsi);
        para.put("agoMin", agoMin);
        return (Location) queryForObject("Location_queryNewly", para);
    }

	public void inserts(List<Location> locations) throws IctException {
		for(Location location : locations){
			insert("Location_insert", location);
		}
		
	}

	public List<Location> findAllNullPlaceName(Map arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(ManRectifyBean arg0) throws IctException {
		// TODO Auto-generated method stub
		
	}
}
