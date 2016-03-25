package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.TaskPointsConfigInfo;
import com.osource.module.fav.model.UserPointsRecordInfo;
import com.osource.module.fav.service.TaskPointsConfigService;
import com.osource.module.fav.service.UserPointsRecordService;
import com.osource.module.fav.web.form.UserPointsRecordForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class UserPointsRecordAction extends BaseAction {
	
	@Autowired
	private UserPointsRecordService userPointsRecordService;
	@Autowired
	private TaskPointsConfigService taskPointsConfigService;
	
	private UserPointsRecordForm userPointsRecordForm;
	
	private Map<Integer, String> taskMap = new LinkedHashMap<Integer, String>();
	private String startDate;
    private String endDate;
    private String startPoint;
    private String endPoint;
    
    
	/** action methods **/
	
	public UserPointsRecordAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
		List<TaskPointsConfigInfo> list = taskPointsConfigService.findByCondition(new HashMap());
		if (null != list){
			for (TaskPointsConfigInfo taskPointsConfigInfo : list) {
				taskMap.put(taskPointsConfigInfo.getId(), taskPointsConfigInfo.getTaskNameDesc());
			}
		}
		return RESULT_INIT;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
		putConditonMap(condition, "startDate", startDate, false);
        putConditonMap(condition, "endDate", endDate, false);	
        if (0 != userPointsRecordForm.getTaskid()){
        	putConditonMap(condition, "taskid", userPointsRecordForm.getTaskid(), true);
        }
        putConditonMap(condition, "userName", userPointsRecordForm.getUserName(), false);
        if (0 != userPointsRecordForm.getPoints()){
        	putConditonMap(condition, "points", userPointsRecordForm.getPoints(), false);	
        }
        if (StringUtil.isNotEmptyAndNotNull(startPoint)){
        	putConditonMap(condition, "startPoint", Integer.valueOf(startPoint), false);
        }
        if (StringUtil.isNotEmptyAndNotNull(endPoint)){
        	putConditonMap(condition, "endPoint", Integer.valueOf(endPoint), false);
        }
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(userPointsRecordService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加用户积分记录信息页面
	 */
	public String add() {
		this.setActionName("userPointsRecord_save");
		return RESULT_SET;
	}

	/**
	 *  添加用户积分记录信息信息
	 */
	public String save() {
		UserPointsRecordInfo userPointsRecordInfo = new UserPointsRecordInfo();
	
		try {
			if(userPointsRecordForm != null)
				IctUtil.copyProperties(userPointsRecordInfo, userPointsRecordForm);
			userPointsRecordInfo.setInsertId(getUserSession().getUserId());
			userPointsRecordService.save(userPointsRecordInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加用户积分记录信息成功");
			logger.debug("添加用户积分记录信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加用户积分记录信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑用户积分记录信息页面
	 */
	public String edit() throws IctException {
		UserPointsRecordInfo userPointsRecordInfo;
		userPointsRecordInfo = userPointsRecordService.findById(this.getId());
		
		UserPointsRecordForm userPointsRecordForm = new UserPointsRecordForm(); 
		IctUtil.copyProperties(userPointsRecordForm, userPointsRecordInfo);
		
		this.setUserPointsRecordForm(userPointsRecordForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看用户积分记录信息页面
	 */
	public String view() throws IctException {
		UserPointsRecordInfo userPointsRecordInfo;
		userPointsRecordInfo = userPointsRecordService.findById(Integer.valueOf(this.getId()));
		
		UserPointsRecordForm userPointsRecordForm = new UserPointsRecordForm(); 
		IctUtil.copyProperties(userPointsRecordForm, userPointsRecordInfo);
		
		this.setUserPointsRecordForm(userPointsRecordForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改用户积分记录信息信息
	 */
	public String update() {
		UserPointsRecordInfo userPointsRecordInfo = new UserPointsRecordInfo();
		
		try {
			IctUtil.copyProperties(userPointsRecordInfo, userPointsRecordForm);
			userPointsRecordInfo.setUpdateId(getUserSession().getUserId());
			
			userPointsRecordService.update(userPointsRecordInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改用户积分记录信息成功");
			logger.debug("修改用户积分记录信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改用户积分记录信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除用户积分记录信息信息
	 */
	public String deletes() {
		try {
			userPointsRecordService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public UserPointsRecordService getUserPointsRecordService() {
		return userPointsRecordService;
	}

	public void setUserPointsRecordService(UserPointsRecordService userPointsRecordService) {
		this.userPointsRecordService = userPointsRecordService;
	}

	public UserPointsRecordForm getUserPointsRecordForm() {
		return userPointsRecordForm;
	}

	public void setUserPointsRecordForm(UserPointsRecordForm userPointsRecordForm) {
		this.userPointsRecordForm = userPointsRecordForm;
	}

	public Map<Integer, String> getTaskMap() {
		return taskMap;
	}

	public void setTaskMap(Map<Integer, String> taskMap) {
		this.taskMap = taskMap;
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

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
}