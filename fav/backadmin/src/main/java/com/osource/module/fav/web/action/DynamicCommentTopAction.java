package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CollectionCommentsInfo;
import com.osource.module.fav.model.DynamicCommentTopInfo;
import com.osource.module.fav.service.CollectionCommentsService;
import com.osource.module.fav.service.DynamicCommentTopService;
import com.osource.module.fav.web.form.DynamicCommentTopForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicCommentTopAction extends BaseAction {
	
	@Autowired
	private DynamicCommentTopService dynamicCommentTopService;
	
	@Autowired
	private CollectionCommentsService collectionCommentsService;
	
	private DynamicCommentTopForm dynamicCommentTopForm;
	
	// 评论来源类型集合
	private static Map<String, String> sourceTypeMap = new LinkedHashMap<String, String>();
	
	// 评论类型集合
	static{
		sourceTypeMap.put("0", "藏品");
		sourceTypeMap.put("1", "动态");
	}
	private String startDate;
    private String endDate;
	/** action methods **/
	
	public DynamicCommentTopAction(){
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
        putConditonMap(condition, "sourceName", dynamicCommentTopForm.getSourceName(), false);
        putConditonMap(condition, "sourceType", dynamicCommentTopForm.getSourceType(), true);
        putConditonMap(condition, "commentContent", dynamicCommentTopForm.getCommentContent(), false);
        putConditonMap(condition, "userName", dynamicCommentTopForm.getUserName(), false);
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dynamicCommentTopService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加动态评论顶信息页面
	 */
	public String add() {
		this.setActionName("dynamicCommentTop_save");
		return RESULT_SET;
	}

	/**
	 *  添加动态评论顶信息信息
	 */
	public String save() {
		DynamicCommentTopInfo dynamicCommentTopInfo = new DynamicCommentTopInfo();
	
		try {
			if(dynamicCommentTopForm != null)
				IctUtil.copyProperties(dynamicCommentTopInfo, dynamicCommentTopForm);
			dynamicCommentTopInfo.setInsertId(getUserSession().getUserId());
			dynamicCommentTopService.save(dynamicCommentTopInfo);
			// 更新对应的评论的顶值+1
			dynamicCommentTopService.updateCommentTopSize(1, dynamicCommentTopInfo.getCommentId());
			this.getAjaxMessagesJson().setMessage("0", "添加评论顶信息成功");
			logger.debug("添加动态评论顶信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加评论顶信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑动态评论顶信息页面
	 */
	public String edit() throws IctException {
		DynamicCommentTopInfo dynamicCommentTopInfo;
		dynamicCommentTopInfo = dynamicCommentTopService.findById(this.getId());
		
		DynamicCommentTopForm dynamicCommentTopForm = new DynamicCommentTopForm(); 
		IctUtil.copyProperties(dynamicCommentTopForm, dynamicCommentTopInfo);
		
		this.setDynamicCommentTopForm(dynamicCommentTopForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看动态评论顶信息页面
	 */
	public String view() throws IctException {
		DynamicCommentTopInfo dynamicCommentTopInfo;
		dynamicCommentTopInfo = dynamicCommentTopService.findById(Integer.valueOf(this.getId()));
		
		DynamicCommentTopForm dynamicCommentTopForm = new DynamicCommentTopForm(); 
		IctUtil.copyProperties(dynamicCommentTopForm, dynamicCommentTopInfo);
		
		this.setDynamicCommentTopForm(dynamicCommentTopForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改动态评论顶信息信息
	 */
	public String update() {
		DynamicCommentTopInfo dynamicCommentTopInfo = new DynamicCommentTopInfo();
		
		try {
			IctUtil.copyProperties(dynamicCommentTopInfo, dynamicCommentTopForm);
			dynamicCommentTopInfo.setUpdateId(getUserSession().getUserId());
			
			DynamicCommentTopInfo qInfo = dynamicCommentTopService.findById(dynamicCommentTopInfo.getId());
			dynamicCommentTopService.update(dynamicCommentTopInfo);
			if (qInfo.getCommentId() != dynamicCommentTopInfo.getCommentId()){
				// 更新对应的评论的顶值+1
				dynamicCommentTopService.updateCommentTopSize(-1, qInfo.getCommentId());
				dynamicCommentTopService.updateCommentTopSize(1, dynamicCommentTopInfo.getCommentId());
			}
			this.getAjaxMessagesJson().setMessage("0", "修改评论顶信息成功");
			logger.debug("修改动态评论顶信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改评论顶信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除动态评论顶信息信息
	 */
	public String deletes() {
		try {
			dynamicCommentTopService.deleteById(this.getIds());
			for (String id : this.getIds().split(",")) {
				DynamicCommentTopInfo dynamicCommentTopInfo = dynamicCommentTopService.findById(Integer.valueOf(id));
				if (null != dynamicCommentTopInfo){
					dynamicCommentTopService.updateCommentTopSize(-1, dynamicCommentTopInfo.getCommentId());
				}
			}
			// 更新对应的评论的顶值+1
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicCommentTopService getDynamicCommentTopService() {
		return dynamicCommentTopService;
	}

	public void setDynamicCommentTopService(DynamicCommentTopService dynamicCommentTopService) {
		this.dynamicCommentTopService = dynamicCommentTopService;
	}

	public DynamicCommentTopForm getDynamicCommentTopForm() {
		return dynamicCommentTopForm;
	}

	public void setDynamicCommentTopForm(DynamicCommentTopForm dynamicCommentTopForm) {
		this.dynamicCommentTopForm = dynamicCommentTopForm;
	}

	public Map<String, String> getSourceTypeMap() {
		return sourceTypeMap;
	}

	public void setSourceTypeMap(Map<String, String> sourceTypeMap) {
		this.sourceTypeMap = sourceTypeMap;
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