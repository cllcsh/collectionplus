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

import com.osource.module.admin.model.LoginLogBean;
import com.osource.module.fav.model.TaskPointsConfigInfo;
import com.osource.module.fav.service.TaskPointsConfigService;
import com.osource.module.fav.web.form.TaskPointsConfigForm;
import com.osource.module.system.model.UserInfo;
import com.osource.base.Constants;
import com.osource.base.web.UserSession;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class TaskPointsConfigAction extends BaseAction {
	
	@Autowired
	private TaskPointsConfigService taskPointsConfigService;
	
	private TaskPointsConfigForm taskPointsConfigForm;
	
	private static Map<String, String> typeMap = CommonCache.getEnumInfos(EnumTypeCons.TASK_POINTS_CONFIG_TYPE);
	
	/** action methods **/
	
	public TaskPointsConfigAction(){
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
	 * @throws IctException 
	 */
	public String query() throws IctException{
		Map<String, Object> condition = new HashMap<String, Object>();
		putConditonMap(condition, "taskName", taskPointsConfigForm.getTaskName(), true);
		if (0 != taskPointsConfigForm.getPoints()){
			condition.put("points", taskPointsConfigForm.getPoints());
		}
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(taskPointsConfigService.findByCondition(condition, pages));
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加任务积分设置信息页面
	 */
	public String add() {
		this.setActionName("taskPointsConfig_save");
		return RESULT_SET;
	}

	/**
	 *  添加任务积分设置信息信息
	 */
	public String save() {
		TaskPointsConfigInfo taskPointsConfigInfo = new TaskPointsConfigInfo();
	
		try {
			if(taskPointsConfigForm != null)
				IctUtil.copyProperties(taskPointsConfigInfo, taskPointsConfigForm);
			taskPointsConfigInfo.setInsertId(getUserSession().getUserId());
			taskPointsConfigService.save(taskPointsConfigInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加任务积分设置信息成功");
			logger.debug("添加任务积分设置信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加任务积分设置信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑任务积分设置信息页面
	 */
	public String edit() throws IctException {
		try {
			TaskPointsConfigInfo taskPointsConfigInfo;
			taskPointsConfigInfo = taskPointsConfigService.findById(this.getId());
			
			TaskPointsConfigForm taskPointsConfigForm = new TaskPointsConfigForm(); 
			IctUtil.copyProperties(taskPointsConfigForm, taskPointsConfigInfo);
			
			this.setTaskPointsConfigForm(taskPointsConfigForm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看任务积分设置信息页面
	 */
	public String view() throws IctException {
		TaskPointsConfigInfo taskPointsConfigInfo;
		taskPointsConfigInfo = taskPointsConfigService.findById(Integer.valueOf(this.getId()));
		
		TaskPointsConfigForm taskPointsConfigForm = new TaskPointsConfigForm(); 
		IctUtil.copyProperties(taskPointsConfigForm, taskPointsConfigInfo);
		
		this.setTaskPointsConfigForm(taskPointsConfigForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改任务积分设置信息信息
	 */
	public String update() {
		TaskPointsConfigInfo taskPointsConfigInfo = new TaskPointsConfigInfo();
		
		try {
			IctUtil.copyProperties(taskPointsConfigInfo, taskPointsConfigForm);
			taskPointsConfigInfo.setUpdateId(getUserSession().getUserId());
			
			taskPointsConfigService.update(taskPointsConfigInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改任务积分设置信息成功");
			logger.debug("修改任务积分设置信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改任务积分设置信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除任务积分设置信息信息
	 */
	public String deletes() {
		try {
			taskPointsConfigService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public TaskPointsConfigService getTaskPointsConfigService() {
		return taskPointsConfigService;
	}

	public void setTaskPointsConfigService(TaskPointsConfigService taskPointsConfigService) {
		this.taskPointsConfigService = taskPointsConfigService;
	}

	public TaskPointsConfigForm getTaskPointsConfigForm() {
		return taskPointsConfigForm;
	}

	public void setTaskPointsConfigForm(TaskPointsConfigForm taskPointsConfigForm) {
		this.taskPointsConfigForm = taskPointsConfigForm;
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
	}
}