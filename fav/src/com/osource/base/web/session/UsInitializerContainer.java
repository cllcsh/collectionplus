/**   
 * 文件名：UsInitializerContainer.java   
 *   
 *   
 */
package com.osource.base.web.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.core.AbstractUserSession;


/**   
 *    
 * 项目名称：osource   
 * 类名称：UsInitializerContainer   
 * 类描述：   
 * 创建人：luoj   
 * 创建时间：2009-11-4 下午06:49:01   
 * @version    
 *    
 */
@Component("usInitializerContainer")
public class UsInitializerContainer implements UsInitializer {
	@Autowired
	private List<UsInitializer> initializerList = new ArrayList<UsInitializer>();
	/* (non-Javadoc)   
	 * @see com.osource.base.web.session.UsInitializer#initialize(com.osource.base.web.UserSession)   
	 */
	public void initialize(AbstractUserSession userSession) {
		for(UsInitializer initializer : initializerList) {
			initializer.initialize(userSession);
		}

	}
	public List<UsInitializer> getInitializerList() {
		return initializerList;
	}
	public void setInitializerList(List<UsInitializer> initializerList) {
		this.initializerList = initializerList;
	}

}
