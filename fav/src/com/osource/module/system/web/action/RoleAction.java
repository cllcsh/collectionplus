/**   
 *    
 * 项目名称：osource   
 * 类名称：RoleAction   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 4, 2009 2:57:38 PM   
 * 修改人：Administrator   
 * 修改时间：Nov 4, 2009 2:57:38 PM   
 * 修改备注：   
 * @version    
 *    
 */
package com.osource.module.system.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.util.Entry;
import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.exception.IctRuntimeException;
import com.osource.core.page.Pages;
import com.osource.core.tree.ItemNode;
import com.osource.core.tree.TreeNode;
import com.osource.module.system.model.FunctionInfo;
import com.osource.module.system.model.RoleBean;
import com.osource.module.system.model.RoleFunction;
import com.osource.module.system.service.DeptService;
import com.osource.module.system.service.FunctionInfoService;
import com.osource.module.system.service.RoleInfoService;
import com.osource.module.system.web.form.RoleInfoForm;
import com.thoughtworks.xstream.XStream;

@Scope("prototype")
@Controller
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = -1385737429651528948L;

	@Autowired
	private RoleInfoService roleInfoService;
	private RoleInfoForm roleInfoForm;
	@Autowired
	private FunctionInfoService functionInfoService;
	@Autowired
	private DeptService deptService;

	private String roleId;
	private List<Entry<String, String>> departmentList;// 机构名下拉列表
	private List<FunctionInfo> functionList;// edit by weiwu
	private String functionValue;// edit by weiwu
	private String departmentName;
	private String tempDepartmentId;

	private String disable = "0";

	public RoleAction() {
		super();
	}

	/**
	 * 初始化页面
	 * 
	 * @return
	 * @throws IctException
	 */
	public String init() throws IctException {
		// DepartmentInfoService departmentInfoService = new
		// DepartmentInfoService();
		// departmentList =
		// departmentInfoService.getDepartmentListByDeptId(getUserSession().getDeptId());
		// DepartmentInfo info = new DepartmentInfo();
		// info.setDepartmentId("0");
		// info.setDepartmentName("");
		// departmentList.add(0,info);
		// this.setDepartmentList(departmentList);
		return RESULT_INIT;
	}

	public String query() {
		Pages pages = new Pages(this.getPage(), this.getLimit());
		this.setPageList(roleInfoService.findRoleInfoListByCondition(roleInfoForm, pages, this.getUserSession()));
		return RESULT_LIST;
	}

	/**
	 * 列出所有角色
	 * 
	 * @return
	 * 
	 *         public String list(){ Pages pages = new
	 *         Pages(this.getPage(),this.getLimit());
	 *         this.setPageList(roleInfoService.findRoleInfoList("id",
	 *         Constants.ORDER_ASC, pages)); return RESULT_LIST; }
	 */
	/**
	 * 初始化增加角色页面
	 * 
	 * @return
	 * @throws IctException
	 */
	public String add() throws IctException {
		RoleInfoForm roleInfoForm = new RoleInfoForm();
		// DepartmentInfoService departmentInfoService = new
		// DepartmentInfoService();
		// roleInfoForm.setDepartmentName(departmentInfoService.findDepartmentInfoById(String.valueOf(getUserSession().getDeptId())).getDepartmentName());
		this.setRoleInfoForm(roleInfoForm);

		// departmentList =
		// departmentInfoService.getDepartmentListByDeptId(getUserSession().getDeptId());
		// this.setDepartmentList(departmentList);
		departmentList = deptService.getDeptSelectList(getUserSession()
				.getDeptId());

		this.setActionName("role_save");
		return RESULT_SET;
	}

	/**
	 * 新增角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save(){
		try {
			if(roleInfoForm.getDepartmentId() == null){
				roleInfoForm.setDepartmentId(this.getTempDepartmentId());
			}
			roleInfoService.saveRoleAndFunction(roleInfoForm, this.getUserSession());
			this.getAjaxMessagesJson().setMessage("0", "添加成功");
		} catch (Exception ex1) {
			this.getAjaxMessagesJson().setMessage("1", "添加失败");
		}
		return RESULT_AJAXJSON;
	}

	/**
	 * 初始化角色编辑页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {

		departmentList = deptService.getDeptSelectList(getUserSession().getDeptId());

		RoleBean info = roleInfoService.findRoleInfoById(this.getRoleId());
		RoleInfoForm roleInfoForm = new RoleInfoForm();
		PropertyUtils.copyProperties(roleInfoForm, info);
		this.setRoleInfoForm(roleInfoForm);

		// this.setDepartmentName(roleInfoForm.getDepartmentName());
		// departmentList =
		// departmentInfoService.getDepartmentListByDeptId(getUserSession().getDeptId());
		// this.setDepartmentList(departmentList);
		this.setActionName("role_update");
		return RESULT_SET;
	}

	/**
	 * 修改角色信息
	 * 
	 * @return
	 * @throws IctException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String update() throws Exception {
		try {
			if(roleInfoForm.getDepartmentId() == null){
				roleInfoForm.setDepartmentId(this.getTempDepartmentId());
			}
			roleInfoService.updateRoleAndFunction(roleInfoForm, this.getUserSession());
			this.getAjaxMessagesJson().setMessage("0", "修改成功");
		} catch (Exception ex1) {
			this.getAjaxMessagesJson().setMessage("1", "修改失败");
		}
		return RESULT_AJAXJSON;
	}

	/**
	 * 查看角色信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		RoleBean info = roleInfoService.findRoleInfoById(this.getRoleId());
		RoleInfoForm roleInfoForm = new RoleInfoForm();
		PropertyUtils.copyProperties(roleInfoForm, info);
		this.setRoleInfoForm(roleInfoForm);
		return RESULT_VIEW;
	}

	/**
	 * 根据角色ID删除角色
	 * 
	 * @return
	 * @throws IctException
	 */
	public String delete() throws IctException {
		try {
			roleInfoService.deleteRoleFunctionById(this.getIds());
			roleInfoService.deleteRoleInfoByIds(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
		} catch (IctException ex1) {
			this.getAjaxMessagesJson().setMessage("1", "删除失败");
		}
		return RESULT_AJAXJSON;
	}

	/* getter and setter */

	public RoleInfoService getRoleInfoService() {
		return roleInfoService;
	}

	public void setRoleInfoService(RoleInfoService RoleInfoService) {
		this.roleInfoService = RoleInfoService;
	}

	public RoleInfoForm getRoleInfoForm() {
		return roleInfoForm;
	}

	public void setRoleInfoForm(RoleInfoForm RoleInfoForm) {
		this.roleInfoForm = RoleInfoForm;
	}


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<FunctionInfo> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<FunctionInfo> functionList) {
		this.functionList = functionList;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}



	public String toTree() throws IctException {
		return "tree";
	}

	public String treeNodes() 
	{
		TreeNode root = new TreeNode("0");
		ItemNode func = new ItemNode("1", "所有功能");

		root.addChild(func);
		this.setFunctionList(functionInfoService.getFunctionInfoList(getUserSession().getUserId()));

		if ((functionList != null) && (functionList.size() > 0)) 
		{
			if (roleId.equals("")) 
			{
				List<RoleFunction> temp = null;
				addNode(0, func, functionList, temp);
			} 
			else 
			{
				List<RoleFunction> temp = roleInfoService.findRoleFunctionList(Integer.valueOf(roleId));
				addNode(0, func, functionList, temp);
			}
		}
		
		XStream xs = new XStream();
		xs.autodetectAnnotations(true);
		
		try 
		{
			ServletOutputStream out = response.getOutputStream();
			String encoding = System.getProperty("sun.jnu.encoding");
			response.setContentType("text/xml;charset=" + encoding);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			//response.setCharacterEncoding("UTF-8");
			out.print("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
			xs.toXML(root, out);
			out.close();
		} 
		catch (IOException e) 
		{
			throw new IctRuntimeException(e);
		}

		return null;
	}

	private void addNode(Integer frontFunctionId, ItemNode node, List<FunctionInfo> list, List<RoleFunction> temp) 
	{
		List<FunctionInfo> fList = new ArrayList<FunctionInfo>();
		for (int i = 0; i < list.size(); i++) 
		{
			// 找到根节点和一级子节点，递归调用中找到子树的根节点和一级子节点
			if ((list.get(i).getFrontFucntionId() == null) || (frontFunctionId.equals(list.get(i).getFrontFucntionId()))) 
			{
				fList.add(list.get(i));
				list.remove(i);
				i--;
			}
		}
		if (fList.isEmpty()) 
		{
			return;
		} 
		else 
		{
			for (FunctionInfo fi : fList) 
			{
				ItemNode item = new ItemNode(String.valueOf(fi.getId()), fi.getFunctionName());

				if (temp != null) 
				{
					for (int i = 0; i < temp.size(); i++) 
					{
						if (fi.getId().equals(temp.get(i).getFunctionId()) && item.getChild() == null) 
						{
							item.setChecked("checked");
							break;
						}
					}
				}

				node.addChild(item);
				node.setChecked(null);

				addNode(fi.getId(), item, list, temp);
			}
		}
	}

	public FunctionInfoService getFunctionInfoService() {
		return functionInfoService;
	}

	public void setFunctionInfoService(FunctionInfoService functionInfoService) {
		this.functionInfoService = functionInfoService;
	}

	public String getFunctionValue() {
		return functionValue;
	}

	public void setFunctionValue(String functionValue) {
		this.functionValue = functionValue;
	}

	public String getTempDepartmentId() {
		return tempDepartmentId;
	}

	public void setTempDepartmentId(String tempDepartmentId) {
		this.tempDepartmentId = tempDepartmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Entry<String, String>> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Entry<String, String>> departmentList) {
		this.departmentList = departmentList;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	// //////////////////////////////////////////////////////////////////////////
	public String data() {

		TreeNode root = new TreeNode("0");
		ItemNode item1 = new ItemNode("0" + "a1", "item1");
		ItemNode item2 = new ItemNode("0" + "d2", "item2");
		ItemNode item3 = new ItemNode("0" + "s3", "item3");
		item3.setTooltip("我爱你");
		ItemNode item4 = new ItemNode("0" + "4d", "item4");
		item4.setChild("2");
		// item4.setRadio("1");
		ItemNode item5 = new ItemNode("0" + "5s", "item5");

		item3.addChild(item4);
		item3.addChild(item5);
		item2.addChild(item3);
		root.addChild(item1);
		root.addChild(item2);

		XStream xs = new XStream();
		xs.autodetectAnnotations(true);

		try {
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.print("<?xml version=\"1.0\" encoding=\"GBK\" ?>");
			xs.toXML(root, out);
			out.close();
		} catch (IOException e) {
			throw new IctRuntimeException(e);
		}

		return null;
	}
}
