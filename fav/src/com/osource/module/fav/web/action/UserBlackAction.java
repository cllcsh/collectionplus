package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.UserBlackInfo;
import com.osource.module.fav.service.UserBlackService;
import com.osource.module.fav.web.form.UserBlackForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class UserBlackAction extends BaseAction {
	
	@Autowired
	private UserBlackService userBlackService;
	
	private UserBlackForm userBlackForm;

	/** action methods **/
	
	public UserBlackAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
	
		return RESULT_INIT;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
		putConditonMap(condition, "userName", userBlackForm.getUserName(), false);
		putConditonMap(condition, "blacklistUserName", userBlackForm.getBlacklistUserName(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(userBlackService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加黑名单信息页面
	 */
	public String add() {
		this.setActionName("userBlack_save");
		return RESULT_SET;
	}

	/**
	 *  添加黑名单信息信息
	 */
	public String save() {
		UserBlackInfo userBlackInfo = new UserBlackInfo();
	
		try {
			if(userBlackForm != null)
				IctUtil.copyProperties(userBlackInfo, userBlackForm);
			userBlackInfo.setInsertId(getUserSession().getUserId());
			userBlackService.save(userBlackInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加黑名单信息成功");
			logger.debug("添加黑名单信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加黑名单信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑黑名单信息页面
	 */
	public String edit() throws IctException {
		UserBlackInfo userBlackInfo;
		userBlackInfo = userBlackService.findById(this.getId());
		
		UserBlackForm userBlackForm = new UserBlackForm(); 
		IctUtil.copyProperties(userBlackForm, userBlackInfo);
		
		this.setUserBlackForm(userBlackForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看黑名单信息页面
	 */
	public String view() throws IctException {
		UserBlackInfo userBlackInfo;
		userBlackInfo = userBlackService.findById(Integer.valueOf(this.getId()));
		
		UserBlackForm userBlackForm = new UserBlackForm(); 
		IctUtil.copyProperties(userBlackForm, userBlackInfo);
		
		this.setUserBlackForm(userBlackForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改黑名单信息信息
	 */
	public String update() {
		UserBlackInfo userBlackInfo = new UserBlackInfo();
		
		try {
			IctUtil.copyProperties(userBlackInfo, userBlackForm);
			userBlackInfo.setUpdateId(getUserSession().getUserId());
			
			userBlackService.update(userBlackInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改黑名单信息成功");
			logger.debug("修改黑名单信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改黑名单信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除黑名单信息信息
	 */
	public String deletes() {
		try {
			userBlackService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public UserBlackService getUserBlackService() {
		return userBlackService;
	}

	public void setUserBlackService(UserBlackService userBlackService) {
		this.userBlackService = userBlackService;
	}

	public UserBlackForm getUserBlackForm() {
		return userBlackForm;
	}

	public void setUserBlackForm(UserBlackForm userBlackForm) {
		this.userBlackForm = userBlackForm;
	}

}