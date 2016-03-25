package com.osource.base.web;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.osource.core.AbstractUserSession;

public class UserActionContext extends ActionContext {
	
	public static final String USER_SESSION_KEY = "user_session";
	public UserActionContext(Map context) {
		super(context);
	}

	public static AbstractUserSession getUserSession() {
        return (AbstractUserSession) ActionContext.getContext().getSession().get(USER_SESSION_KEY);
    }
	
	public static AbstractUserSession getUserSession(Class clazz) {
		Map session = ActionContext.getContext().getSession();
		if(!session.containsKey(USER_SESSION_KEY)){
			return null;
		}
		AbstractUserSession userSession = (UserSession) session.get(USER_SESSION_KEY);
        return userSession.getUserSession(clazz);
    }
	
	public static void setUserSession(UserSession userSession) {
        ActionContext.getContext().getSession().put(USER_SESSION_KEY, userSession);
    }
}
