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

import com.osource.module.fav.model.HeatInfo;
import com.osource.module.fav.service.HeatService;
import com.osource.module.fav.web.form.HeatForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class HeatAction extends BaseAction {
	
	@Autowired
	private HeatService heatService;
	
	private HeatForm heatForm;

	private static Map<String, String> nameMap = CommonCache.getEnumInfos(EnumTypeCons.HEAT_NAME_TYPE);
 	
	/** action methods **/
	
	public HeatAction(){
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
		if (StringUtil.isNotEmptyAndNotNull(heatForm.getValueDesc())){
			putConditonMap(condition, "value", Integer.valueOf(heatForm.getValueDesc()), false);
		}
		putConditonMap(condition, "name", heatForm.getName(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(heatService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加热度信息页面
	 */
	public String add() {
		this.setActionName("heat_save");
		return RESULT_SET;
	}

	/**
	 *  添加热度信息信息
	 */
	public String save() {
		HeatInfo heatInfo = new HeatInfo();
	
		try {
			if(heatForm != null)
				IctUtil.copyProperties(heatInfo, heatForm);
			heatInfo.setInsertId(getUserSession().getUserId());
			heatService.save(heatInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加热度信息成功");
			logger.debug("添加热度信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加热度信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑热度信息页面
	 */
	public String edit() throws IctException {
		HeatInfo heatInfo;
		heatInfo = heatService.findById(this.getId());
		
		HeatForm heatForm = new HeatForm(); 
		IctUtil.copyProperties(heatForm, heatInfo);
		
		this.setHeatForm(heatForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看热度信息页面
	 */
	public String view() throws IctException {
		HeatInfo heatInfo;
		heatInfo = heatService.findById(Integer.valueOf(this.getId()));
		
		HeatForm heatForm = new HeatForm(); 
		IctUtil.copyProperties(heatForm, heatInfo);
		
		this.setHeatForm(heatForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改热度信息信息
	 */
	public String update() {
		HeatInfo heatInfo = new HeatInfo();
		
		try {
			IctUtil.copyProperties(heatInfo, heatForm);
			heatInfo.setUpdateId(getUserSession().getUserId());
			
			heatService.update(heatInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改热度信息成功");
			logger.debug("修改热度信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改热度信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除热度信息信息
	 */
	public String deletes() {
		try {
			heatService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public HeatService getHeatService() {
		return heatService;
	}

	public void setHeatService(HeatService heatService) {
		this.heatService = heatService;
	}

	public HeatForm getHeatForm() {
		return heatForm;
	}

	public void setHeatForm(HeatForm heatForm) {
		this.heatForm = heatForm;
	}

	public Map<String, String> getNameMap() {
		return nameMap;
	}

	public void setNameMap(Map<String, String> nameMap) {
		this.nameMap = nameMap;
	}
}