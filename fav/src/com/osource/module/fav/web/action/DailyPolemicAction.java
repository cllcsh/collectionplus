package com.osource.module.fav.web.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.BooleanUtil;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.DailyPolemicInfo;
import com.osource.module.fav.service.DailyPolemicService;
import com.osource.module.fav.web.form.DailyPolemicForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DailyPolemicAction extends BaseAction {
	
	@Autowired
	private DailyPolemicService dailyPolemicService;
	
	private DailyPolemicForm dailyPolemicForm;

	private List<String> imgs;
	
	/** action methods **/
	
	public DailyPolemicAction(){
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
		putConditonMap(condition, "title", dailyPolemicForm.getTitle(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dailyPolemicService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加天天论战信息页面
	 */
	public String add() {
		this.setActionName("dailyPolemic_save");
		return RESULT_SET;
	}

	/**
	 *  添加天天论战信息信息
	 */
	public String save() {
		DailyPolemicInfo dailyPolemicInfo = new DailyPolemicInfo();
	
		try {
			if(dailyPolemicForm != null)
				IctUtil.copyProperties(dailyPolemicInfo, dailyPolemicForm);
			dailyPolemicInfo.setInsertId(getUserSession().getUserId());
			if (null != imgs && imgs.size() != 0){
				String images = "";
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						images += img + ",";
					}
				}
				if (images.length() > 1){
					dailyPolemicInfo.setImages(images.substring(0, images.length() - 1));
				}
			}
			dailyPolemicService.save(dailyPolemicInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加天天论战信息成功");
			logger.debug("添加天天论战信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加天天论战信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑天天论战信息页面
	 */
	public String edit() throws IctException {
		DailyPolemicInfo dailyPolemicInfo;
		dailyPolemicInfo = dailyPolemicService.findById(this.getId());
		
		DailyPolemicForm dailyPolemicForm = new DailyPolemicForm(); 
		IctUtil.copyProperties(dailyPolemicForm, dailyPolemicInfo);
		String images = dailyPolemicForm.getImages();
		if (StringUtil.isNotEmptyAndNotNull(images)){
			imgs = Arrays.asList(images.split(","));
		}
		this.setDailyPolemicForm(dailyPolemicForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看天天论战信息页面
	 */
	public String view() throws IctException {
		DailyPolemicInfo dailyPolemicInfo;
		dailyPolemicInfo = dailyPolemicService.findById(Integer.valueOf(this.getId()));
		
		DailyPolemicForm dailyPolemicForm = new DailyPolemicForm(); 
		IctUtil.copyProperties(dailyPolemicForm, dailyPolemicInfo);
		String images = dailyPolemicForm.getImages();
		if (StringUtil.isNotEmptyAndNotNull(images)){
			imgs = Arrays.asList(images.split(","));
		}
		this.setDailyPolemicForm(dailyPolemicForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改天天论战信息信息
	 */
	public String update() {
		DailyPolemicInfo dailyPolemicInfo = new DailyPolemicInfo();
		
		try {
			IctUtil.copyProperties(dailyPolemicInfo, dailyPolemicForm);
			dailyPolemicInfo.setUpdateId(getUserSession().getUserId());
			if (null != imgs && imgs.size() != 0){
				String images = "";
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						images += img + ",";
					}
				}
				if (images.length() > 1){
					dailyPolemicInfo.setImages(images.substring(0, images.length() - 1));
				}
			}
			dailyPolemicService.update(dailyPolemicInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改天天论战信息成功");
			logger.debug("修改天天论战信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改天天论战信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除天天论战信息信息
	 */
	public String deletes() {
		try {
			dailyPolemicService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DailyPolemicService getDailyPolemicService() {
		return dailyPolemicService;
	}

	public void setDailyPolemicService(DailyPolemicService dailyPolemicService) {
		this.dailyPolemicService = dailyPolemicService;
	}

	public DailyPolemicForm getDailyPolemicForm() {
		return dailyPolemicForm;
	}

	public void setDailyPolemicForm(DailyPolemicForm dailyPolemicForm) {
		this.dailyPolemicForm = dailyPolemicForm;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
}