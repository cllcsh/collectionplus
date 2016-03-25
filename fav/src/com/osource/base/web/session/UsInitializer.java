/**   
 * 文件名：SessionInitializer.java   
 *   
 */
package com.osource.base.web.session;

import com.osource.core.AbstractUserSession;



/**   
 *    
 * 项目名称：osource   
 * <p>类名称：UsInitializer   
 * <p>类描述：用户登录后初始化userSession
 * <p>创建人：luoj   
 * <p>创建时间：2009-11-4 下午06:10:29   
 * @version    
 *    
 */
public interface UsInitializer {
	/**
	 * 
	 * initialize 初始化userSession   
	 * @param   userSession userSession对象     
	 * @Exception 异常对象
	 */
	public void initialize(AbstractUserSession userSession);
}
