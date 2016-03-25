package com.osource.module.system.dao;

import java.util.List;

import com.osource.base.util.Entry;

public interface AreaDao {
	public List<Entry<String, String>> getPrivonceSelectList(String code);
	public List<Entry<String, String>> getAllCity();
	public List<Entry<String, String>> getAllArea();
	public List<Entry<String, String>> getCitySelectList(String code);
	public List<Entry<String, String>> getAreaSelectList(String code);
}
