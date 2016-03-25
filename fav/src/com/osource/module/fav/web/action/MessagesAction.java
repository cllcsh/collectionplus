package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.MessagesInfo;
import com.osource.module.fav.service.MessagesService;
import com.osource.module.fav.web.form.MessagesForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class MessagesAction extends BaseAction {
	
	@Autowired
	private MessagesService messagesService;
	
	private MessagesForm messagesForm;
	
	private Map<String, String> readMap = CommonCache.getEnumInfos(EnumTypeCons.MESSAGES_IS_READ_TYPE);
	
	private String startDate;
	private String endDate;
	
	/** action methods **/
	
	public MessagesAction(){
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
		putConditonMap(condition, "startDate", startDate, false);
        putConditonMap(condition, "endDate", endDate, false);
        putConditonMap(condition, "senderName", messagesForm.getSenderName(), false);
        putConditonMap(condition, "receiverName", messagesForm.getReceiverName(), false);
        putConditonMap(condition, "content", messagesForm.getContent(), false);
        putConditonMap(condition, "isRead", messagesForm.getIsRead(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(messagesService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加消息信息页面
	 */
	public String add() {
		this.setActionName("messages_save");
		return RESULT_SET;
	}

	/**
	 *  添加消息信息信息
	 */
	public String save() {
		MessagesInfo messagesInfo = new MessagesInfo();
	
		try {
			if(messagesForm != null)
				IctUtil.copyProperties(messagesInfo, messagesForm);
			messagesInfo.setInsertId(getUserSession().getUserId());
			messagesService.save(messagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加消息信息成功");
			logger.debug("添加消息信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加消息信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑消息信息页面
	 */
	public String edit() throws IctException {
		MessagesInfo messagesInfo;
		messagesInfo = messagesService.findById(this.getId());
		
		MessagesForm messagesForm = new MessagesForm(); 
		IctUtil.copyProperties(messagesForm, messagesInfo);
		
		this.setMessagesForm(messagesForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看消息信息页面
	 */
	public String view() throws IctException {
		MessagesInfo messagesInfo;
		messagesInfo = messagesService.findById(Integer.valueOf(this.getId()));
		
		MessagesForm messagesForm = new MessagesForm(); 
		IctUtil.copyProperties(messagesForm, messagesInfo);
		
		this.setMessagesForm(messagesForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改消息信息信息
	 */
	public String update() {
		MessagesInfo messagesInfo = new MessagesInfo();
		
		try {
			IctUtil.copyProperties(messagesInfo, messagesForm);
			messagesInfo.setUpdateId(getUserSession().getUserId());
			
			messagesService.update(messagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改消息信息成功");
			logger.debug("修改消息信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改消息信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除消息信息信息
	 */
	public String deletes() {
		try {
			messagesService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public MessagesService getMessagesService() {
		return messagesService;
	}

	public void setMessagesService(MessagesService messagesService) {
		this.messagesService = messagesService;
	}

	public MessagesForm getMessagesForm() {
		return messagesForm;
	}

	public void setMessagesForm(MessagesForm messagesForm) {
		this.messagesForm = messagesForm;
	}

	public Map<String, String> getReadMap() {
		return readMap;
	}

	public void setReadMap(Map<String, String> readMap) {
		this.readMap = readMap;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}