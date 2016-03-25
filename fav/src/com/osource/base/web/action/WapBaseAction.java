/**
 * 
 */
package com.osource.base.web.action;

import com.osource.base.Constants;
import com.osource.base.web.UserSession;

/**
 * @author luoj
 *
 */
public class WapBaseAction extends BaseAction {
	@Override
	public UserSession getUserSession(){
		return (UserSession)session.get(Constants.WAP_USER_SESSION_KEY);
	}
}
