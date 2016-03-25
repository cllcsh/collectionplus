/**   
 * 文件名：PermissionUsInitializer.java   
 *   
 *   
 */
package com.osource.base.web.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.service.PermissionService;
import com.osource.base.web.UserSession;
import com.osource.core.AbstractUserSession;


/**   
 *    
 * 项目名称：osource   
 * 类名称：PermissionUsInitializer   
 * 类描述：   
 * 创建人：luoj   
 * 创建时间：2009-11-4 下午06:35:54   
 * @version    
 *    
 */
@Component
public class PermissionUsInitializer implements UsInitializer {
	@Autowired
	private PermissionService permissionService;
	/* (non-Javadoc)   
	 * @see com.osource.base.web.session.UsInitializer#initialize(com.osource.base.web.UserSession)   
	 */
	public void initialize(AbstractUserSession userSession) {
		UserSession userUserSession;
		if(userSession.match(UserSession.class)){
			userUserSession = (UserSession) userSession.getUserSession(UserSession.class);
		} else {
			userUserSession = new UserSession(userSession.getUserSession());
			userSession.setUserSession(userUserSession);
		}
		userUserSession.setUserPermissions(permissionService.getPermissions(userSession.getUserId()));

	}
	public PermissionService getPermissionService() {
		return permissionService;
	}
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
