/**
 * @author luoj
 * @create 2009-9-21
 * @file VersionService.java
 * @since v0.1
 * 
 */
package com.osource.base.service.impl;

import org.springframework.stereotype.Service;

import com.osource.base.service.VersionService;
import com.osource.core.PropertiesManager;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * @author luoj
 *
 */
@Service
public class VersionServiceImpl extends BaseServiceImpl implements VersionService {
	private static final String RESOURCE_FILE = "version.properties";
	
	public String getCurrentVersion(){
		return PropertiesManager.getProperty(RESOURCE_FILE, "current", "1.0");
	}
}
