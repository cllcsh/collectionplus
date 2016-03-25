/**   
 * 文件名：OnlineAction.java   
 *   
 */
package com.osource.module.system.web.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.Constants;
import com.osource.base.common.OnlineUserManager;
import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.module.system.service.OnlineService;

/**   
 *    
 * 项目名称：osource   
 * <p>类名称：OnlineAction   
 * <p>类描述：   
 * <p>创建人：luoj   
 * <p>创建时间：2010-1-7 上午09:01:02   
 * @version    
 *    
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class OnlineAction extends BaseAction {
	
	@Autowired
	private OnlineService onlineService;
	
	/**
	 *  查看在线用户列表
	 */
	@SuppressWarnings("unchecked")
	public String view() throws IctException {
		List<Integer> onlineUsers = new ArrayList();
		OnlineUserManager onlineUserManager = (OnlineUserManager)request.getSession().getServletContext().getAttribute(Constants.ONLINE_USERS_MANAGER_KEY);
		if(onlineUserManager != null){
			onlineUsers.addAll(onlineUserManager.getOnlineUsers());
			if(onlineUsers.isEmpty())
				onlineUsers.add(-1);
			this.setPageList(onlineService.findByCondition(onlineUsers));
		} else {
			this.setPageList(new PageList());
		}
		return RESULT_LIST;
	}
	
	
	/** getter and setter methods **/
	
	public OnlineService getOnlineService() {
		return onlineService;
	}

	public void setOnlineService(OnlineService onlineService) {
		this.onlineService = onlineService;
	}

}