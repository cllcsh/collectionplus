package com.osource.module.system.ui;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.common.AreaManager;
import com.osource.base.dao.IctListUIDao;
import com.osource.base.util.Entry;
import com.osource.module.system.dao.AreaDao;
import com.osource.util.StringUtil;

@Component("provinceSelect")
public class ProvinceSelectUI implements IctListUIDao {
	@Autowired
	private AreaDao areaDao;
	
	public List<Entry<String, String>> getIctList(HttpSession session) {
		return areaDao.getPrivonceSelectList("");
	}

	public String getIctValue(String key) {
		String provinceName = AreaManager.getInstance().getProvinceName(key);
		String cityName = AreaManager.getInstance().getCityName(key);
		String areaName = AreaManager.getInstance().getAreaName(key);
		String resule = StringUtil.isEmpty(areaName) ? (StringUtil.isEmpty(cityName) ? provinceName : cityName) : areaName;
		return resule;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

}
