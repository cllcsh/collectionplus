package com.osource.module.fav.service;

import com.osource.orm.ibatis.BaseService;
import com.osource.module.fav.model.FavUserInfo;

public interface FavUserService extends BaseService<FavUserInfo> {
	boolean isExist(String account);
}