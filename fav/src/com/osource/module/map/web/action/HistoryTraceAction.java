package com.osource.module.map.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.common.tree.Node;
import com.osource.base.common.tree.NormalNode;
import com.osource.base.web.action.BaseAction;
import com.osource.base.web.ajax.AjaxMessagesJson;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.map.service.LocaQueryService;
import com.osource.module.map.web.form.HistoryTraceForm;
import com.osource.module.map.web.session.CenterPointUserSession;
import com.osource.module.system.model.DeptInfo;
import com.osource.module.system.service.DeptService;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
/**
 * HistoryTraceAction
 * @author zhouhao	
 * @create 2010-3-8
 * @file TabsAction.java
 * @since v0.1
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class HistoryTraceAction extends BaseAction {
	@Autowired
	private DeptService deptService;
	@Autowired
	private LocaQueryService locaQueryService;
    private String showType;
    private Integer depId;
	private String startTime;
	private String endTime;
	private static SimpleDateFormat format1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	private HistoryTraceForm form;

	/**
	 * 历史轨迹初始化
	 * @return String
	 * 
	 */
	public String init() {
		if(this.getForm() == null) {
			this.setForm(new HistoryTraceForm());
		}
			
		this.getForm().setDeptList(deptService.getDeptSelectList(getUserSession().getDeptId()));
		return "init";
	}

	public String frame() {
		if(getUserSession().match(CenterPointUserSession.class)){
			CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession().getUserSession(CenterPointUserSession.class);
			this.pushValueStack("centerPointUserSession", centerPointUserSession);
		}
		return "frame";
	}
	
	public String map() {
		if(getUserSession().match(CenterPointUserSession.class)){
			CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession().getUserSession(CenterPointUserSession.class);
			this.pushValueStack("centerPointUserSession", centerPointUserSession);
		}
		return RESULT_MAP;
	}
	
	/**
	 * 历史轨迹树页面
	 * @return String
	 * 
	 */
	public String treeNodes() {
		if(showType == null) {
			showType = "0";
		}
		return "getTree";
	}
	
	/**
	 * 历史轨迹树结构
	 * @return String
	 * 
	 */
	public String getTree() {
		String strHtml = "";
		String contextPath = "";
		if(showType == null) {
			showType = "0";
		}
		if(depId == null) {
			depId = getUserSession().getDeptId();
		}
		
		if(form == null){
			form = new HistoryTraceForm();
			form.setDeptId(depId);
			form.setObjType(showType);
		}
		try {
		contextPath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", form.getDeptId());
		map.put("objType", form.getObjType());
		
		
			strHtml= locaQueryService.generateTree(contextPath, map , false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("strHtml", strHtml.toString());

		return "tree";
	}
	
	/**
	 * 历史轨迹列表
	 * @return String
	 * 
	 */
	public String getTraceList() throws NumberFormatException, IctException, java.text.ParseException{
		Pages pages = new Pages(this.getPage(),this.getLimit());
		String terminalId = this.getId().toString();
		String startTime = this.getStartTime().replace("@", " ");
		String endTime = this.getEndTime().replace("@", " ");
		//this.setPageList(locaQueryService.findLocationInfoInDate(terminalId, startTime, endTime, pages));
		PageList result = locaQueryService.findLocationInfoInDate(terminalId, startTime, endTime, pages);
		Map map = new HashMap();
		map.put("total", result.size());
		map.put("rows", result);
		return returnJsonString(map);
	}
	
	public static java.util.Date stringToDateTime(String str) throws java.text.ParseException{ //字符串转换成日期(包括年月日时分秒)
		java.util.Date date = null;    
		try {    
			date = format1.parse(str); 
		} catch (ParseException e) {    
		    e.printStackTrace();    
		}    
		return date;
	}
	
	/**
	 * 历史轨迹多级分组
	 * @param upperGroupId
	 * @param node
	 * @param list
	 * 
	 */
	@SuppressWarnings("unused")
	private void addNode(Integer upperGroupId, Node node, List<DeptInfo> list) {
		List<DeptInfo> gList = new ArrayList<DeptInfo>();
		for(int i=0; i<list.size(); i++) {
			if((list.get(i).getUpperDept() == null) || (list.get(i).getUpperDept().equals(0)) || (upperGroupId.equals(list.get(i).getUpperDept()))) {
				gList.add(list.get(i));
				list.remove(i);
				i--;
			}
		}
		
		for(DeptInfo gi : gList) {
			NormalNode cbNode = new NormalNode(gi.getName());
			cbNode.setIcon("/ictmap/resource/eXtree/js/images/foldericon.png");
			cbNode.setOpenIcon("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/resource/eXtree/js/images/foldericon.png");
			addNode(gi.getId(), cbNode, list);
			node.addChild(cbNode);
		}
	}
	
	
	/**
	 * 多级分组
	 * @param cropName 法人名称
	 * @param cropId 法人id
	 * @param giList 组列表
	 * @param tiList 无组的终端列表
	 * @return String
	 * 
	 
	@SuppressWarnings("unused")
	private StringBuffer multiLevel(String deptName, Integer deptId, List<DeptInfo> giList) {
		int totalCount = 0;
		StringBuffer htmlStr = new StringBuffer("");
		htmlStr.append("<table id=\"totalTab\" width=\"100%\" cellspacing=\"0\" cellpadding=\"2\">");
		htmlStr.append("<tr bgcolor=\"#f7fafe\"><td width=\"14\">");
		htmlStr.append("<img id=\"img_group-99999" + deptId + "\" src=\"http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/module/map/images/openfoldericon.png\" border=\"0\" style=\"cursor:hand\" onClick=\"display('group-99999" + deptId + "')\">");
		htmlStr.append("</td><td colspan=\"2\" class=\"font_696969\">");
		htmlStr.append("<div align=\"left\">");
		//htmlStr += "<input id=\"cb_group" + deptId + "\" type=\"checkbox\" name=\"cb_group\" value=\"" + deptId + "\" onClick=\"return selectNode(this);\">";
		htmlStr.append("&nbsp;<font style=\"cursor:default;font-size=12px;\">" + deptName + "(" + totalCount + ")</font></div></td></tr>");
		htmlStr.append("<tr bgcolor=\"#f7fafe\" id=\"tr_group-99999" + deptId + "\"><td>　</td><td colspan=\"2\">");
		htmlStr.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"2\" id=\"tb_group-99999" + deptId + "\">");
		htmlStr = locaQueryService.genLevel(giList.get(0).getUpper_dept(), htmlStr, giList, request.getServerName(),  request.getServerPort(), request.getContextPath());
		htmlStr.append("</table></td></tr></table>");
		return htmlStr;
	}*/

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public DeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public HistoryTraceForm getForm() {
		return form;
	}

	public void setForm(HistoryTraceForm form) {
		this.form = form;
	}

	public LocaQueryService getLocaQueryService() {
		return locaQueryService;
	}

	public void setLocaQueryService(LocaQueryService locaQueryService) {
		this.locaQueryService = locaQueryService;
	}
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

}
