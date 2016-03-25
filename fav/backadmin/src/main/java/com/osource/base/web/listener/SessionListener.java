/**
 * @author luoj
 * @create 2009-7-30
 * @file SessionListener.java
 * @since v0.1
 * 
 */
package com.osource.base.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.osource.base.common.SessionManager;

/**
 * @author luoj
 *
 */
public class SessionListener implements HttpSessionListener {
	private SessionManager sessionManager = SessionManager.getInstance();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		sessionManager.addSession(se.getSession());

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		sessionManager.delSession(session);

	}

}
