package com.osource.module.fav.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CollectionCommentsInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.model.DynamicCommentTopInfo;
import com.osource.module.fav.service.CollectionCommentsService;
import com.osource.module.fav.service.DynamicCommentTopService;
import com.osource.module.fav.service.DynamicCommentsLikeService;
import com.osource.module.fav.web.form.CollectionCommentsForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CollectionCommentsAction extends BaseAction {
	
	@Autowired
	private CollectionCommentsService collectionCommentsService;
	
	private CollectionCommentsForm collectionCommentsForm;
	
	// 评论来源类型集合
	private static Map<String, String> sourceTypeMap = new LinkedHashMap<String, String>();
	
	// 评论类型集合
	private static Map<String, String> typeMap = CommonCache.getEnumInfos(EnumTypeCons.COMMENTS_TYPE);
	static{
		sourceTypeMap.put("0", "藏品");
		sourceTypeMap.put("1", "动态");
	}
	private String startDate;
    private String endDate;
    private String searchName;

	/** action methods **/
	
	public CollectionCommentsAction(){
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
        putConditonMap(condition, "sourceName", collectionCommentsForm.getSourceName(), false);
        putConditonMap(condition, "sourceType", collectionCommentsForm.getSourceType(), true);
        putConditonMap(condition, "content", collectionCommentsForm.getContent(), false);
        putConditonMap(condition, "userName", collectionCommentsForm.getUserName(), false);
        putConditonMap(condition, "type", collectionCommentsForm.getType(), true);
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<CollectionCommentsInfo> list = (PageList)collectionCommentsService.findByCondition(condition, pages);
		if (null != list) {
			for (CollectionCommentsInfo bean : list) {
				bean.setTypeDesc(StringUtil.trim(typeMap.get(bean.getType())));
			}
		}
		this.setPageList(list);
		return RESULT_LIST;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String queryForBrowse(){
		HttpServletRequest request = getRequest();
		String sourceType = request.getParameter("sourceType");
		String sourceId = request.getParameter("sourceId");
		Map condition = new HashMap();
		putConditonMap(condition, "content", searchName, false);
		putConditonMap(condition, "sourceId", sourceId, false);
		putConditonMap(condition, "sourceType", sourceType, false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<CollectionCommentsInfo> list = (PageList)collectionCommentsService.findByCondition(condition, pages);
		JSONArray jsonArray = new JSONArray();
		if (null != list) {
			for (CollectionCommentsInfo bean : list) {
				JSONObject object = new JSONObject();
				object.put("id", bean.getId());
				object.put("name", bean.getContent());
				jsonArray.add(object);
			}
		}
		JSONObject result = new JSONObject();
		result.put("list", jsonArray);
		JSONObject pageJsonObject = new JSONObject();
		pageJsonObject.put("count", pages.getTotal());
		pageJsonObject.put("currentPage", pages.getPage());
		pageJsonObject.put("pageSize", pages.getLimit());
		pageJsonObject.put("countPage", pages.getAllPage());
		result.put("page", pageJsonObject);
		HttpServletResponse response = (HttpServletResponse) ServletActionContext.getResponse();
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *  跳转到添加藏品评论信息页面
	 */
	public String add() {
		this.setActionName("collectionComments_save");
		return RESULT_SET;
	}

	/**
	 *  添加藏品评论信息信息
	 */
	public String save() {
		CollectionCommentsInfo collectionCommentsInfo = new CollectionCommentsInfo();
		try {
			if(collectionCommentsForm != null)
				IctUtil.copyProperties(collectionCommentsInfo, collectionCommentsForm);
			collectionCommentsInfo.setInsertId(getUserSession().getUserId());
			collectionCommentsService.save(collectionCommentsInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加评论信息成功");
			logger.debug("添加评论信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加评论信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑藏品评论信息页面
	 */
	public String edit() throws IctException {
		CollectionCommentsInfo collectionCommentsInfo;
		collectionCommentsInfo = collectionCommentsService.findById(this.getId());
		
		CollectionCommentsForm collectionCommentsForm = new CollectionCommentsForm(); 
		IctUtil.copyProperties(collectionCommentsForm, collectionCommentsInfo);
		
		collectionCommentsForm.setTypeDesc(StringUtil.trim(typeMap.get(collectionCommentsForm.getType())));
		if (collectionCommentsInfo.getReplyId() != 0){
			CollectionCommentsInfo bean = collectionCommentsService.findById(collectionCommentsInfo.getReplyId());
			if (null != bean){
				collectionCommentsForm.setReplyContent(StringUtil.trimPage(bean.getContent()));
				collectionCommentsForm.setReplyUserName(StringUtil.trimPage(bean.getUserName()));
			}else {
				collectionCommentsForm.setReplyContent("");
				collectionCommentsForm.setReplyUserName("");
			}
		}
		this.setCollectionCommentsForm(collectionCommentsForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看藏品评论信息页面
	 */
	public String view() throws IctException {
		CollectionCommentsInfo collectionCommentsInfo;
		collectionCommentsInfo = collectionCommentsService.findById(Integer.valueOf(this.getId()));
		
		CollectionCommentsForm collectionCommentsForm = new CollectionCommentsForm(); 
		IctUtil.copyProperties(collectionCommentsForm, collectionCommentsInfo);
		collectionCommentsForm.setTypeDesc(StringUtil.trim(typeMap.get(collectionCommentsForm.getType())));
		if (collectionCommentsInfo.getReplyId() != 0){
			CollectionCommentsInfo bean = collectionCommentsService.findById(collectionCommentsInfo.getReplyId());
			if (null != bean){
				collectionCommentsForm.setReplyContent(StringUtil.trimPage(bean.getContent()));
				collectionCommentsForm.setReplyUserName(StringUtil.trimPage(bean.getUserName()));
			}else {
				collectionCommentsForm.setReplyContent("");
				collectionCommentsForm.setReplyUserName("");
			}
		}
		this.setCollectionCommentsForm(collectionCommentsForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改藏品评论信息信息
	 */
	public String update() {
		CollectionCommentsInfo collectionCommentsInfo = new CollectionCommentsInfo();
		
		try {
			IctUtil.copyProperties(collectionCommentsInfo, collectionCommentsForm);
			collectionCommentsInfo.setUpdateId(getUserSession().getUserId());
			collectionCommentsService.updateInfo(collectionCommentsInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改评论信息成功");
			logger.debug("修改评论信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改评论信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除藏品评论信息信息
	 */
	public String deletes() {
		try {
			collectionCommentsService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public CollectionCommentsService getCollectionCommentsService() {
		return collectionCommentsService;
	}

	public void setCollectionCommentsService(CollectionCommentsService collectionCommentsService) {
		this.collectionCommentsService = collectionCommentsService;
	}

	public CollectionCommentsForm getCollectionCommentsForm() {
		return collectionCommentsForm;
	}

	public void setCollectionCommentsForm(CollectionCommentsForm collectionCommentsForm) {
		this.collectionCommentsForm = collectionCommentsForm;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}