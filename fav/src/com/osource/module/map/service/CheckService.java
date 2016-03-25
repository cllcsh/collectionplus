package com.osource.module.map.service;

import com.osource.module.map.model.CheckBean;
import com.osource.orm.ibatis.BaseService;

public interface CheckService extends BaseService<CheckBean> {
	public String findPhone(String criminalId);
}