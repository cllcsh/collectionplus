/**
 * @author luoj
 * @create 2009-3-30
 * @file UserSessionAware.java
 * @since v0.1
 * 
 */
package com.osource.base.web.interceptor;

import com.osource.base.web.UserSession;

/**
 *
 */
public interface UserSessionAware {
	public void setUserSession(UserSession userSession);
}
