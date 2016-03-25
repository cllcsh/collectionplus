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
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.DynamicCommentTopInfo;
import com.osource.module.fav.model.DynamicCommentsLikeInfo;
import com.osource.module.fav.service.DynamicCommentsLikeService;
import com.osource.module.fav.web.form.DynamicCommentsLikeForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicCommentsLikeAction extends BaseAction {
	
	@Autowired
	private DynamicCommentsLikeService dynamicCommentsLikeService;
	
	private DynamicCommentsLikeForm dynamicCommentsLikeForm;
	
	// 评论来源类型集合
	private static Map<String, String> sourceTypeMap = new LinkedHashMap<String, String>();
	
	private static Map<String, String> typeMap = CommonCache.getEnumInfos(EnumTypeCons.COMMENT_LIKE_TYPE);
	// 评论类型集合
	static{
		sourceTypeMap.put("0", "藏品");
		sourceTypeMap.put("1", "动态");
	}
	private String startDate;
    private String endDate;
	/** action methods **/
	
	public DynamicCommentsLikeAction(){
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
        putConditonMap(condition, "sourceName", dynamicCommentsLikeForm.getSourceName(), false);
        putConditonMap(condition, "sourceType", dynamicCommentsLikeForm.getSourceType(), true);
        putConditonMap(condition, "commentContent", dynamicCommentsLikeForm.getCommentContent(), false);
        putConditonMap(condition, "userName", dynamicCommentsLikeForm.getUserName(), false);
        putConditonMap(condition, "type", dynamicCommentsLikeForm.getType(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dynamicCommentsLikeService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加动态评论赞同反对信息页面
	 */
	public String add() {
		this.setActionName("dynamicCommentsLike_save");
		return RESULT_SET;
	}

	/**
	 *  添加动态评论赞同反对信息信息
	 */
	public String save() {
		DynamicCommentsLikeInfo dynamicCommentsLikeInfo = new DynamicCommentsLikeInfo();
	
		try {
			if(dynamicCommentsLikeForm != null)
				IctUtil.copyProperties(dynamicCommentsLikeInfo, dynamicCommentsLikeForm);
			dynamicCommentsLikeInfo.setInsertId(getUserSession().getUserId());
			dynamicCommentsLikeService.save(dynamicCommentsLikeInfo);
			// 更新对应的评论的顶值+1
			int likeSize = 0;
			int opposeSize = 0;
			// 0：赞成 1：反对
			if ("0".equals(dynamicCommentsLikeInfo.getType())){
				likeSize ++;
			}else {
				opposeSize ++;
			}
			dynamicCommentsLikeService.updateCommentLikeAndOpposeSize(
					dynamicCommentsLikeInfo.getCommentId(), likeSize, opposeSize);
			this.getAjaxMessagesJson().setMessage("0", "添加动态评论赞同反对信息成功");
			logger.debug("添加动态评论赞同反对信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加动态评论赞同反对信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑动态评论赞同反对信息页面
	 */
	public String edit() throws IctException {
		DynamicCommentsLikeInfo dynamicCommentsLikeInfo;
		dynamicCommentsLikeInfo = dynamicCommentsLikeService.findById(this.getId());
		
		DynamicCommentsLikeForm dynamicCommentsLikeForm = new DynamicCommentsLikeForm(); 
		IctUtil.copyProperties(dynamicCommentsLikeForm, dynamicCommentsLikeInfo);
		
		this.setDynamicCommentsLikeForm(dynamicCommentsLikeForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看动态评论赞同反对信息页面
	 */
	public String view() throws IctException {
		DynamicCommentsLikeInfo dynamicCommentsLikeInfo;
		dynamicCommentsLikeInfo = dynamicCommentsLikeService.findById(Integer.valueOf(this.getId()));
		
		DynamicCommentsLikeForm dynamicCommentsLikeForm = new DynamicCommentsLikeForm(); 
		IctUtil.copyProperties(dynamicCommentsLikeForm, dynamicCommentsLikeInfo);
		
		this.setDynamicCommentsLikeForm(dynamicCommentsLikeForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改动态评论赞同反对信息信息
	 */
	public String update() {
		DynamicCommentsLikeInfo dynamicCommentsLikeInfo = new DynamicCommentsLikeInfo();
		
		try {
			IctUtil.copyProperties(dynamicCommentsLikeInfo, dynamicCommentsLikeForm);
			dynamicCommentsLikeInfo.setUpdateId(getUserSession().getUserId());
			
			DynamicCommentsLikeInfo qInfo;
			qInfo = dynamicCommentsLikeService.findById(dynamicCommentsLikeInfo.getId());
			
			dynamicCommentsLikeService.update(dynamicCommentsLikeInfo);
			if (qInfo.getCommentId() != dynamicCommentsLikeInfo.getCommentId() || !qInfo.getType().equals(dynamicCommentsLikeInfo.getType())){
				// 更新对应的评论的顶值+1
				int likeSize = 0;
				int opposeSize = 0;
				// 0：赞成 1：反对
				if ("0".equals(dynamicCommentsLikeInfo.getType())){
					likeSize ++;
				}else {
					opposeSize ++;
				}
				
				// 更新对应的评论的顶值+1
				int qLikeSize = 0;
				int qOpposeSize = 0;
				// 0：赞成 1：反对
				if ("0".equals(qInfo.getType())){
					qLikeSize ++;
				}else {
					qOpposeSize ++;
				}
				dynamicCommentsLikeService.updateCommentLikeAndOpposeSize(
						qInfo.getCommentId(), -qLikeSize, -qOpposeSize);
				dynamicCommentsLikeService.updateCommentLikeAndOpposeSize(
						dynamicCommentsLikeInfo.getCommentId(), likeSize, opposeSize);
			}
			this.getAjaxMessagesJson().setMessage("0", "修改动态评论赞同反对信息成功");
			logger.debug("修改动态评论赞同反对信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改动态评论赞同反对信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除动态评论赞同反对信息信息
	 */
	public String deletes() {
		try {
			dynamicCommentsLikeService.deleteById(this.getIds());
			for (String id : this.getIds().split(",")) {
				DynamicCommentsLikeInfo qInfo;
				qInfo = dynamicCommentsLikeService.findById(Integer.valueOf(id));
				if (null != qInfo){
					int likeSize = 0;
					int opposeSize = 0;
					// 0：赞成 1：反对
					if ("0".equals(qInfo.getType())){
						likeSize ++;
					}else {
						opposeSize ++;
					}
					dynamicCommentsLikeService.updateCommentLikeAndOpposeSize(qInfo.getCommentId(), -likeSize, -opposeSize);
				}
			}
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicCommentsLikeService getDynamicCommentsLikeService() {
		return dynamicCommentsLikeService;
	}

	public void setDynamicCommentsLikeService(DynamicCommentsLikeService dynamicCommentsLikeService) {
		this.dynamicCommentsLikeService = dynamicCommentsLikeService;
	}

	public DynamicCommentsLikeForm getDynamicCommentsLikeForm() {
		return dynamicCommentsLikeForm;
	}

	public void setDynamicCommentsLikeForm(DynamicCommentsLikeForm dynamicCommentsLikeForm) {
		this.dynamicCommentsLikeForm = dynamicCommentsLikeForm;
	}

	public Map<String, String> getSourceTypeMap() {
		return sourceTypeMap;
	}

	public void setSourceTypeMap(Map<String, String> sourceTypeMap) {
		this.sourceTypeMap = sourceTypeMap;
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
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