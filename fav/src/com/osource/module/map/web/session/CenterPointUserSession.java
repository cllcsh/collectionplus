package com.osource.module.map.web.session;

import com.osource.core.AbstractUserSession;
import com.osource.module.map.model.CenterPointBean;

public class CenterPointUserSession extends AbstractUserSession {
	private CenterPointBean centerPointInfo;//中心点信息
	
	public CenterPointUserSession(AbstractUserSession UserSession) {
		super(UserSession);
	}

	public CenterPointBean getCenterPointInfo() {
		return centerPointInfo;
	}

	public void setCenterPointInfo(CenterPointBean centerPointInfo) {
		this.centerPointInfo = centerPointInfo;
	}
}
