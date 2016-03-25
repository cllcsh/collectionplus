/**   
 * 文件名：ServiceAware.java   
 *   
 * <p>版本信息：2.0  
 *   
 */
package com.osource.base.web.interceptor;

import com.osource.core.exception.IctException;

/**   
 *    
 * 项目名称：osource   
 * <p>类名称：ServiceAware   
 * <p>类描述：   
 * <p>创建人：luoj   
 * <p>创建时间：2009-11-19 上午09:23:01   
 * <p>修改人：luoj   
 * <p>修改时间：2009-11-19 上午09:23:01   
 * <p>修改备注：   
 * @version    
 *    
 */
public interface ServiceAware {
	public void injectUserSession() throws IctException;
}
