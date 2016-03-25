package com.osource.base.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.common.AreaManager;
import com.osource.base.util.Entry;
import com.osource.module.system.dao.AreaDao;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AreaAction extends BaseAction {
	@Autowired
	private AreaDao areaDao;
	public String getCityList(){
//		List<Entry<String, String>> result = areaDao.getCitySelectList(this.ids);
		List<Entry<String, String>> result = AreaManager.getInstance().getCityByProvinceCode(this.ids);
		return returnJsonString(result);
	}
	
	public String getAreaList(){
//		List<Entry<String, String>> result = areaDao.getAreaSelectList(this.ids);
		List<Entry<String, String>> result = AreaManager.getInstance().getAreaByAreaCode(this.ids);
		return returnJsonString(result);
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
}
