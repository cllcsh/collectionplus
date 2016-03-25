package com.osource.module.map.service;

import java.util.List;

import com.osource.base.model.Location;
import com.osource.core.exception.IctException;
import com.osource.orm.ibatis.BaseService;

public interface LocationService extends BaseService<Location> {
	public List<Integer> saveLocations(List<Integer> terminalIds) throws IctException;
	
	public List<Integer> saveLocationByMobile(String locNum) throws IctException;

}
