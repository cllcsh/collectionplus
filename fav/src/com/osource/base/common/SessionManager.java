/**
 * @author luoj
 * @create 2009-7-30
 * @file SessionContext.java
 * @since v0.1
 * 
 */
package com.osource.base.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author luoj
 *
 */
public class SessionManager {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(SessionManager.class);
	
	private Map sessionMap = new HashMap();
	
	private static SessionManager instance;
	public static synchronized SessionManager getInstance() {
		if (instance==null){
			instance = new SessionManager();
		}
		return instance;
	}
	
	public synchronized void addSession(HttpSession session) {
		if (session != null) {
			sessionMap.put(session.getId(), session);
			//logger.debug(MessageFormat.format("添加session id={0}", session.getId()));
		}
	}

	public synchronized void delSession(HttpSession session) {
		if (session != null) {
			sessionMap.remove(session.getId());
//			logger.debug(MessageFormat.format("移除session id={0}", session.getId()));
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null)
			return null;
		return (HttpSession) sessionMap.get(session_id);
	}
}
