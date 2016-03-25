package com.osource.base.form.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.form.ModuleGenerator;
import com.osource.base.form.model.FormTable;
import com.osource.base.form.service.FormTableService;
import com.osource.base.form.web.form.FormTableForm;
import com.osource.base.web.action.BaseAction;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class FormTableAction extends BaseAction {
	
	@Autowired
	private FormTableService formTableService;
	
	private FormTableForm formTableForm;
	
	private static String packName = "com.osource.form";//properties文件所在包名
	private static String basePagePath = "osource";//jsp文件存放根路径，所在工程下开始，即工程路径下的basePagePath
	
	private static String fileName = "config_template.properties";

	/** action methods **/
	
	public FormTableAction(){
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
		
		if(formTableForm == null){
			formTableForm = new FormTableForm();
		}

//		if(formTableForm.getCriminalId() != null){
//			condition.put("criminalId", FormTableForm.getCriminalId());
//		}
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(formTableService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	public String add() {
		this.setActionName("formTable_save");
		return RESULT_SET;
	}

	public String save() {
		FormTable formTable = new FormTable();
	
		try {
			if(formTableForm != null)
				IctUtil.copyProperties(formTable, formTableForm);
//			if(formTable.getDeptId() == null){
//				formTable.setDeptId(getUserSession().getDeptId());
//			}
			formTable.setId(IDgenerator.gettNextID("tb_table_info"));
			formTableService.save(formTable);
			this.getAjaxMessagesJson().setMessage("0", "创建功能模块成功");
			logger.debug("创建功能模块成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "创建功能模块失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	public String edit() throws IctException {
		FormTable formTable;
		formTable = formTableService.findById(this.getId());
		
		FormTableForm formTableForm = new FormTableForm(); 
		IctUtil.copyProperties(formTableForm, formTable);
		
		this.setFormTableForm(formTableForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看页面
	 */
	public String view() throws IctException {
		FormTable formTable;
		formTable = formTableService.findById(Integer.valueOf(this.getId()));
		
		FormTableForm formTableForm = new FormTableForm(); 
		IctUtil.copyProperties(formTableForm, formTable);
		
		this.setFormTableForm(formTableForm);
		
		return RESULT_VIEW;
	}
	
	public String update() {
		FormTable formTable = new FormTable();
		
		try {
			IctUtil.copyProperties(formTable, formTableForm);
			//formTable.setUpdateId(getUserSession().getUserId());
			
			formTableService.update(formTable);
			this.getAjaxMessagesJson().setMessage("0", "修改模块信息成功");
			logger.debug("修改模块信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改模块信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除核实取证信息采集信息
	 */
	public String deletes() {
		try {
			formTableService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 自动根据模板生成代码
	 * @return
	 */
	public String genCode(){
		FormTable formTable;
		formTable = formTableService.findById(this.getId());
		new ModuleGenerator(packName, fileName, basePagePath, formTable).generate();
		return null;
	}

	public FormTableService getFormTableService() {
		return formTableService;
	}

	public void setFormTableService(FormTableService formTableService) {
		this.formTableService = formTableService;
	}

	public FormTableForm getFormTableForm() {
		return formTableForm;
	}

	public void setFormTableForm(FormTableForm formTableForm) {
		this.formTableForm = formTableForm;
	}
	
	/** getter and setter methods **/

}