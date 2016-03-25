package com.osource.base.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.util.Entry;
import com.osource.core.BeanProvider;
import com.osource.module.system.dao.AreaDao;
import com.osource.util.StringUtil;

public class AreaManager implements Serializable{
	private static final Log logger = LogFactory.getLog(AreaManager.class);
	private static AreaManager instance;
	public static synchronized AreaManager getInstance() {
		if (instance==null){
			instance = new AreaManager();
			instance.setAreaDao((AreaDao)BeanProvider.getBean("areaDaoImpl"));
			instance.init();
		}
		return instance;
	}
	
	private AreaDao areaDao;
	
	private Map<String, String> province = new HashMap();
	private Map<String, String> city = new HashMap();
	private Map<String, String> area = new HashMap();
	private Map<String, List<Entry<String, String>>> provinceCity = new HashMap();
	private Map<String, List<Entry<String, String>>> cityArea = new HashMap();
	
	public String getProvinceName(String code){
		String result = province.get(code);
		return StringUtil.isEmpty(result) ? "" : result;
	}
	public String getCityName(String code){
		String result = city.get(code);
		return StringUtil.isEmpty(result) ? "" : result;
	}
	public String getAreaName(String code){
		String result = area.get(code);
		return StringUtil.isEmpty(result) ? "" : result;
	}
	public List<Entry<String, String>> getCityByProvinceCode(String code){
		return provinceCity.get(code);
	}
	public List<Entry<String, String>> getAreaByAreaCode(String code){
		return cityArea.get(code);
	}
	
	public void reload(){
		province.clear();
		city.clear();
		area.clear();
		provinceCity.clear();
		cityArea.clear();
		logger.info("清理业务字典成功！");
		init();
	}
	
	private void init(){
		List<Entry<String, String>> plist = areaDao.getPrivonceSelectList("");
		for(Entry<String, String> entry :plist){
			province.put(entry.getKey(), entry.getValue());
			provinceCity.put(entry.getKey(), areaDao.getCitySelectList(entry.getKey()));
		}
		List<Entry<String, String>> clist = areaDao.getAllCity();
		for(Entry<String, String> entry : clist){
			city.put(entry.getKey(), entry.getValue());
			cityArea.put(entry.getKey(), areaDao.getAreaSelectList(entry.getKey()));
		}
		List<Entry<String, String>> alist = areaDao.getAllArea();
		for(Entry<String, String> entry : alist){
			area.put(entry.getKey(), entry.getValue());
		}
	}
	
	public AreaDao getAreaDao() {
		return areaDao;
	}
	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	
}
