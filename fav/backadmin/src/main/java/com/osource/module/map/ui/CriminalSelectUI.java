package com.osource.module.map.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.Constants;
import com.osource.base.dao.IctListUIDao;
import com.osource.base.util.Entry;
import com.osource.base.web.UserSession;
import com.osource.module.map.dao.CheckDao;

@Component("criminalSelect")
public class CriminalSelectUI implements IctListUIDao {
	@Autowired
	private CheckDao checkDao; 
	public List<Entry<String, String>> getIctList(HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute(Constants.USER_SESSION_KEY);
		if(userSession == null) {
			return new ArrayList();
		}
		return checkDao.getCriminalSelectList(userSession.getUserId());
	}

	public String getIctValue(String key) {
		return null;
	}

	public CheckDao getCheckDao() {
		return checkDao;
	}

	public void setCheckDao(CheckDao checkDao) {
		this.checkDao = checkDao;
	}

}
