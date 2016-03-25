package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.model.FamousHomeInfo;
import com.osource.module.fav.service.FamousHomeService;
import com.osource.module.fav.web.form.FamousHomeForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class FamousHomeAction extends BaseAction {
	
	@Autowired
	private FamousHomeService famousHomeService;
	
	private FamousHomeForm famousHomeForm;
	
	/**
	 * 专项
	 */
	private static Map<Integer, String> specialMap = CommonCache.getSpecials();

	// 名人类型
	private static Map<String, String> famousHomeTypeMap = CommonCache.getEnumInfos(EnumTypeCons.FAMOUS_HOME_TYPE_TYPE);
	
	// 是否入驻
	private static Map<String, String> famousHomeStatusTypeMap = CommonCache.getEnumInfos(EnumTypeCons.FAMOUS_HOME_STATUS_TYPE);
	/** action methods **/
	
	private List<String> special;
	
	public FamousHomeAction(){
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
		putConditonMap(condition, "status", famousHomeForm.getStatus(), true);
		putConditonMap(condition, "type", famousHomeForm.getType(), true);
		putConditonMap(condition, "name", famousHomeForm.getName(), false);
		if (StringUtil.isNotEmptyAndNotNull(famousHomeForm.getSpecialids()) && !BaseAction.SELECT_ALL_STR.equals(famousHomeForm.getSpecialids())) {
			putConditonMap(condition, "specialids", "," + famousHomeForm.getSpecialids() + ",", false);
		}
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<FamousHomeInfo> list = (PageList)famousHomeService.findByCondition(condition, pages);
		if (null != list) {
			for (FamousHomeInfo bean : list) {
				dealFamousHomeInfo(bean);
			}
		}
		this.setPageList(list);
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加名人堂信息页面
	 */
	public String add() {
		this.setActionName("famousHome_save");
		return RESULT_SET;
	}

	/**
	 *  添加名人堂信息信息
	 */
	public String save() {
		FamousHomeInfo famousHomeInfo = new FamousHomeInfo();
	
		try {
			if(famousHomeForm != null)
				IctUtil.copyProperties(famousHomeInfo, famousHomeForm);
			famousHomeInfo.setInsertId(getUserSession().getUserId());
			String specialStr = ",";
			if (null != special){
				for (String str : special) {
					specialStr += str + ",";
				}
			}
			if (!",".equals(specialStr)){
				famousHomeInfo.setSpecialids(specialStr);
			}
			famousHomeService.save(famousHomeInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加名人堂信息成功");
			logger.debug("添加名人堂信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加名人堂信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑名人堂信息页面
	 */
	public String edit() throws IctException {
		FamousHomeInfo famousHomeInfo;
		famousHomeInfo = famousHomeService.findById(this.getId());
		dealFamousHomeInfo(famousHomeInfo);
		FamousHomeForm famousHomeForm = new FamousHomeForm(); 
		IctUtil.copyProperties(famousHomeForm, famousHomeInfo);
		
		this.setFamousHomeForm(famousHomeForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看名人堂信息页面
	 */
	public String view() throws IctException {
		FamousHomeInfo famousHomeInfo;
		famousHomeInfo = famousHomeService.findById(Integer.valueOf(this.getId()));
		dealFamousHomeInfo(famousHomeInfo);
		
		FamousHomeForm famousHomeForm = new FamousHomeForm(); 
		IctUtil.copyProperties(famousHomeForm, famousHomeInfo);
		this.setFamousHomeForm(famousHomeForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改名人堂信息信息
	 */
	public String update() {
		FamousHomeInfo famousHomeInfo = new FamousHomeInfo();
		
		try {
			IctUtil.copyProperties(famousHomeInfo, famousHomeForm);
			famousHomeInfo.setUpdateId(getUserSession().getUserId());
			String specialStr = ",";
			if (null != special){
				for (String str : special) {
					specialStr += str + ",";
				}
			}
			if (!",".equals(specialStr)){
				famousHomeInfo.setSpecialids(specialStr);
			}
			famousHomeService.update(famousHomeInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改名人堂信息成功");
			logger.debug("修改名人堂信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改名人堂信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除名人堂信息信息
	 */
	public String deletes() {
		try {
			famousHomeService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	private void dealFamousHomeInfo(FamousHomeInfo bean){
		if (null == bean){
			return;
		}
		bean.setStatusDesc(StringUtil.trim(famousHomeStatusTypeMap.get(bean.getStatus())));
		bean.setTypeDesc(StringUtil.trim(famousHomeTypeMap.get(bean.getType())));
		
		String specialidsDesc = "";
		if (StringUtil.isNotEmptyAndNotNull(bean.getSpecialids())){
			String[] specialids = bean.getSpecialids().split(",");
			String value = "";
			if (null != specialids){
				for (String str : specialids) {
					if (StringUtil.isNotEmptyAndNotNull(str)){
						value = specialMap.get(Integer.valueOf(str));
					}
					if (StringUtil.isNotEmptyAndNotNull(value)){
						specialidsDesc += value + ",";
					}
				}
			}
		}
		if (StringUtil.isNotEmptyAndNotNull(specialidsDesc)){
			bean.setSpecialidsDesc(specialidsDesc.substring(0, specialidsDesc.length() - 1));
		}
	}
	
	/** getter and setter methods **/
	
	public FamousHomeService getFamousHomeService() {
		return famousHomeService;
	}

	public void setFamousHomeService(FamousHomeService famousHomeService) {
		this.famousHomeService = famousHomeService;
	}

	public FamousHomeForm getFamousHomeForm() {
		return famousHomeForm;
	}

	public void setFamousHomeForm(FamousHomeForm famousHomeForm) {
		this.famousHomeForm = famousHomeForm;
	}

	public Map<Integer, String> getSpecialMap() {
		return specialMap;
	}

	public void setSpecialMap(Map<Integer, String> specialMap) {
		this.specialMap = specialMap;
	}

	public Map<String, String> getFamousHomeTypeMap() {
		return famousHomeTypeMap;
	}

	public void setFamousHomeTypeMap(Map<String, String> famousHomeTypeMap) {
		this.famousHomeTypeMap = famousHomeTypeMap;
	}

	public Map<String, String> getFamousHomeStatusTypeMap() {
		return famousHomeStatusTypeMap;
	}

	public void setFamousHomeStatusTypeMap(
			Map<String, String> famousHomeStatusTypeMap) {
		this.famousHomeStatusTypeMap = famousHomeStatusTypeMap;
	}

	public List<String> getSpecial() {
		return special;
	}

	public void setSpecial(List<String> special) {
		this.special = special;
	}
}