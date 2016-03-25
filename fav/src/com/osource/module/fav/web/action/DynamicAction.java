package com.osource.module.fav.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.model.DynamicImagesInfo;
import com.osource.module.fav.model.DynamicInfo;
import com.osource.module.fav.service.DynamicImagesService;
import com.osource.module.fav.service.DynamicService;
import com.osource.module.fav.web.form.DynamicForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicAction extends BaseAction {
	
	@Autowired
	private DynamicService dynamicService;
	@Autowired
	private DynamicImagesService dynamicImagesService;
	private DynamicForm dynamicForm;
	private String startDate;
    private String endDate;
	/** action methods **/
 // 藏品图片
 	private List<String> imgs;
 	
 	private String searchName;
 	
	public DynamicAction(){
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
	    putConditonMap(condition, "releaseName", dynamicForm.getReleaseName(), false);
	    if (StringUtil.isNotEmptyAndNotNull(dynamicForm.getCommentNumberDesc())){
	    	putConditonMap(condition, "commentNumber", Integer.valueOf(dynamicForm.getCommentNumberDesc()), false);
	    }
	    if (StringUtil.isNotEmptyAndNotNull(dynamicForm.getLikeNumberDesc())){
	    	putConditonMap(condition, "likeNumber", Integer.valueOf(dynamicForm.getLikeNumberDesc()), false);
	    }
	    putConditonMap(condition, "dynamicContent", dynamicForm.getDynamicContent(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dynamicService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String queryForBrowse(){
		Map condition = new HashMap();
	    putConditonMap(condition, "dynamicContent", searchName, false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<DynamicInfo> list = (PageList)dynamicService.findByCondition(condition, pages);
		JSONArray jsonArray = new JSONArray();
		if (null != list) {
			for (DynamicInfo bean : list) {
				JSONObject object = new JSONObject();
				object.put("id", bean.getId());
				object.put("name", bean.getDynamicContent());
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
	 *  跳转到添加动态信息页面
	 */
	public String add() {
		this.setActionName("dynamic_save");
		return RESULT_SET;
	}

	/**
	 *  添加动态信息信息
	 */
	public String save() {
		DynamicInfo dynamicInfo = new DynamicInfo();
	
		try {
			if(dynamicForm != null)
				IctUtil.copyProperties(dynamicInfo, dynamicForm);
			dynamicInfo.setInsertId(getUserSession().getUserId());
			dynamicService.save(dynamicInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加动态信息成功");
			logger.debug("添加动态信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加动态信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑动态信息页面
	 */
	public String edit() throws IctException {
		DynamicInfo dynamicInfo;
		dynamicInfo = dynamicService.findById(this.getId());
		
		DynamicForm dynamicForm = new DynamicForm(); 
		IctUtil.copyProperties(dynamicForm, dynamicInfo);
		
		this.setDynamicForm(dynamicForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看动态信息页面
	 */
	public String view() throws IctException {
		DynamicInfo dynamicInfo;
		dynamicInfo = dynamicService.findById(Integer.valueOf(this.getId()));
		
		DynamicForm dynamicForm = new DynamicForm(); 
		IctUtil.copyProperties(dynamicForm, dynamicInfo);
		
		this.setDynamicForm(dynamicForm);
		
		Map condition = new HashMap();
		putConditonMap(condition, "dynamicId", dynamicForm.getId(), false);
		List<DynamicImagesInfo> dynamicImagesInfos = dynamicImagesService.findByCondition(condition);
		if (null != dynamicImagesInfos){
			imgs = new ArrayList<String>();
			for (DynamicImagesInfo info : dynamicImagesInfos) {
				imgs.add(info.getImage());
			}
		}
		return RESULT_VIEW;
	}
	
	/**
	 *  修改动态信息信息
	 */
	public String update() {
		DynamicInfo dynamicInfo = new DynamicInfo();
		
		try {
			IctUtil.copyProperties(dynamicInfo, dynamicForm);
			dynamicInfo.setUpdateId(getUserSession().getUserId());
			
			dynamicService.update(dynamicInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改动态信息成功");
			logger.debug("修改动态信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改动态信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除动态信息信息
	 */
	public String deletes() {
		try {
			dynamicService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicService getDynamicService() {
		return dynamicService;
	}

	public void setDynamicService(DynamicService dynamicService) {
		this.dynamicService = dynamicService;
	}

	public DynamicForm getDynamicForm() {
		return dynamicForm;
	}

	public void setDynamicForm(DynamicForm dynamicForm) {
		this.dynamicForm = dynamicForm;
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

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}