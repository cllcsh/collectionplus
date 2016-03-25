package com.osource.base.form.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.form.model.FormTableField;
import com.osource.base.form.service.FormTableFieldService;
import com.osource.base.form.web.form.FormTableFieldForm;
import com.osource.base.web.action.BaseAction;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class FormTableFieldAction extends BaseAction {
	
	@Autowired
	private FormTableFieldService formTableFieldService;
	
	private FormTableFieldForm formTableFieldForm;
	
	private static String tableId;

	/** action methods **/
	
	public FormTableFieldAction(){
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
		
		if(formTableFieldForm == null){
			formTableFieldForm = new FormTableFieldForm();
		}

//		if(formTableForm.getCriminalId() != null){
//			condition.put("criminalId", FormTableForm.getCriminalId());
//		}
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(formTableFieldService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	public String add() {
		this.setActionName("formTableField_save");
		return RESULT_SET;
	}

	public String save() {
		FormTableField formTableField = new FormTableField();
	
		try {
			if(formTableFieldForm != null)
				IctUtil.copyProperties(formTableField, formTableFieldForm);
//			if(formTable.getDeptId() == null){
//				formTable.setDeptId(getUserSession().getDeptId());
//			}
			formTableField.setId(IDgenerator.gettNextID("tb_table_field"));
			formTableFieldService.save(formTableField);
			this.getAjaxMessagesJson().setMessage("0", "创建字段信息成功");
			logger.debug("创建字段信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "创建字段信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	public String edit() throws IctException {
		FormTableField formTableField;
		formTableField = formTableFieldService.findById(this.getId());
		
		FormTableFieldForm formTableFieldForm = new FormTableFieldForm(); 
		IctUtil.copyProperties(formTableFieldForm, formTableField);
		
		this.setFormTableFieldForm(formTableFieldForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看页面
	 */
	public String view() throws IctException {
		FormTableField formTableField;
		formTableField = formTableFieldService.findById(Integer.valueOf(this.getId()));
		
		FormTableFieldForm formTableFieldForm = new FormTableFieldForm(); 
		IctUtil.copyProperties(formTableFieldForm, formTableField);
		
		this.setFormTableFieldForm(formTableFieldForm);
		
		return RESULT_VIEW;
	}
	
	public String update() {
		FormTableField formTableField = new FormTableField();
		
		try {
			IctUtil.copyProperties(formTableField, formTableFieldForm);
			//formTable.setUpdateId(getUserSession().getUserId());
			
			formTableFieldService.update(formTableField);
			this.getAjaxMessagesJson().setMessage("0", "修改字段信息成功");
			logger.debug("修改字段信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改字段信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除核实取证信息采集信息
	 */
	public String deletes() {
		try {
			formTableFieldService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}

	public FormTableFieldService getFormTableFieldService() {
		return formTableFieldService;
	}

	public void setFormTableFieldService(FormTableFieldService formTableFieldService) {
		this.formTableFieldService = formTableFieldService;
	}

	public FormTableFieldForm getFormTableFieldForm() {
		return formTableFieldForm;
	}

	public void setFormTableFieldForm(FormTableFieldForm formTableFieldForm) {
		this.formTableFieldForm = formTableFieldForm;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	
	/** getter and setter methods **/

}