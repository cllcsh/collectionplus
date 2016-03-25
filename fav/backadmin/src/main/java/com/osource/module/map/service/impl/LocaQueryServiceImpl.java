package com.osource.module.map.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.common.Point;
import com.osource.base.common.PointManager;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.map.dao.LocaQueryDao;
import com.osource.module.map.model.LocaQueryBean;
import com.osource.module.map.service.LocaQueryService;
import com.osource.module.map.web.form.LocaQueryForm;
import com.osource.module.system.dao.DeptDao;
import com.osource.module.system.model.DeptInfo;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class LocaQueryServiceImpl extends BaseServiceImpl<LocaQueryBean> implements LocaQueryService {

	@Autowired
	private DeptDao deptDao;

	public List<LocaQueryBean> queryByCondition(String sqlMap, List condition) {
		return this.getDao().queryByCondition(sqlMap, condition);
	}

	private StringBuffer genLevel(Integer upperGroupId, StringBuffer str, List<DeptInfo> list, String contextPath, boolean modelFlag) {
		StringBuffer tempStr = str;
		List<DeptInfo> gList = new ArrayList<DeptInfo>();
		for (int i = 0; i < list.size(); i++) {
			if ((list.get(i).getUpperDept() == null) || list.get(i).getUpperDept().equals(0) || (upperGroupId.equals(list.get(i).getUpperDept()))) {
				gList.add(list.get(i));
				list.remove(i);
				i--;
			}
		}
		for (DeptInfo gi : gList) {
			boolean flag = false;
			if ((list != null) && (list.size() > 0)) {
				for (int i = 0; i < list.size(); i++) {
					if (gi.getId().equals(list.get(i).getUpperDept())) {
						flag = true;
						break;
					}
				}
			}
			int count = 0;
			tempStr.append("<tr bgcolor=\"#f7fafe\"><td width=\"14\">");
			tempStr.append("<img id=\"img_group" + gi.getId() + "\" src=\"" + contextPath + "/module/map/images/foldericon.png\" border=\"0\" style=\"cursor:hand\" onClick=\"display('group" + gi.getId() + "')\">");
			tempStr.append("</td><td colspan=\"2\" class=\"font_696969\">");
			if (modelFlag) {
				tempStr.append("<input id=\"cb_group" + gi.getId() + "\" type=\"checkbox\" name=\"cb_group\" value=\"" + gi.getId() + "\" onClick=\"return selectNode(this);\">");
			}
			tempStr.append("&nbsp;<font style=\"cursor:default;font-size=12px;\">" + gi.getName() + "(" + count + ")</font></td></tr>");
			if (flag) {
				tempStr.append("<tr bgcolor=\"#f7fafe\" style=\"display: none;\" id=\"tr_group" + gi.getId() + "\"><td>　</td><td colspan=\"2\">");
				tempStr.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"2\" id=\"tb_group" + gi.getId() + "\">");
			}
			tempStr = genLevel(gi.getId(), tempStr, list, contextPath, modelFlag);
			if (flag) {
				tempStr.append("</table></td></tr>");
			}
		}
		return tempStr;
	}

	@Transactional(readOnly = true)
	public StringBuffer buildTree(String showType, String contextPath, boolean modelFlag) {
		Integer id = getUserSession().getDeptId();
		// 判断showType
		List<DeptInfo> deList = deptDao.findDeptInfoListById(id);
		DeptInfo di = null;
		if ((deList != null) && (deList.size() > 0)) {
			for (DeptInfo deptInfo : deList) {
				if (id == deptInfo.getId()) {
					di = deptInfo;
					break;
				}
			}
			if (di == null) {
				return null;
			}
			if ((di.getUserInDept() != null) && (di.getUserInDept().size() > 0)) {
				DeptInfo giInfo = new DeptInfo();
				giInfo.setId(-9999);
				giInfo.setName("当前机构下用户");
				giInfo.setUserInDept(di.getUserInDept());
				deList.add(giInfo);
			}

			int totalCount = 0;
			for (int i = 0; i < deList.size(); i++) {
				if (deList.get(i).getUserInDept() != null) {
					totalCount += deList.get(i).getUserInDept().size();
				}
			}

			StringBuffer strHtml = new StringBuffer("");
			strHtml.append("<table id=\"totalTab\" width=\"100%\" cellspacing=\"0\" cellpadding=\"2\">");
			strHtml.append("<tr bgcolor=\"#f7fafe\"><td width=\"14\">");
			strHtml.append("<img id=\"img_group-99999" + di.getId() + "\" src=\"" + contextPath + "/module/map/images/openfoldericon.png\" border=\"0\" style=\"cursor:hand\" onClick=\"display('group-99999" + di.getId() + "')\">");
			strHtml.append("</td><td colspan=\"2\" class=\"font_696969\">");
			strHtml.append("<div align=\"left\">");
			if (modelFlag) {
				strHtml.append("<input id=\"cb_group-99999" + id + "\" type=\"checkbox\" name=\"cb_group\" value=\"-99999" + id + "\" onClick=\"return selectNode(this);\">");
			}
			strHtml.append("&nbsp;<font style=\"cursor:default;font-size=12px;\">" + di.getName() + "(" + totalCount + ")</font></div></td></tr>");
			strHtml.append("<tr bgcolor=\"#f7fafe\" id=\"tr_group-99999" + di.getId() + "\"><td>　</td><td colspan=\"2\">");
			strHtml.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"2\" id=\"tb_group-99999" + di.getId() + "\">");

			if ((deList != null) && (deList.size() > 0)) {
				// 多级分组
				if (showType.equals("0")) {
					strHtml = genLevel(id, strHtml, deList, contextPath, modelFlag);
				}
			}
			strHtml.append("</table></td></tr></table>");
			return strHtml;
		} else {
			return null;
		}
	}

	/*
	 * 生成树形结构
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String generateTree(String contextPath, Map condition, boolean mutiSelected) {
		int total = 0;
		int _ID = 0;
		Integer parentId = null;
		DeptInfo deptInfo = null;
		List<DeptInfo> list = null;
		List<LocaQueryBean> userList = null;
		StringBuffer str = new StringBuffer("");
		list = deptDao.findDeptListById("system_dept_findDeptListById", (Integer) condition.get("deptId"));
		_ID = ((Integer) condition.get("deptId")).intValue();
		for (int i = 0; i < list.size(); i++) {
			deptInfo = (DeptInfo) list.get(i);
			if (deptInfo.getId().intValue() == ((Integer) condition.get("deptId")).intValue()) {
				parentId = deptInfo.getUpperDept();
				break;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			deptInfo = (DeptInfo) list.get(i);
			condition.put("deptId", deptInfo.getId());
			total = (int) this.getDao().countByCondition("module_locaQuery_countByCondition", condition);
			deptInfo.setListCount(total);
			userList = this.getDao().findByCondition("module_locaQuery_findByCondition", condition);
			deptInfo.setList(userList);
		}

		StringBuffer strScript = new StringBuffer();
		strScript.append("<script type=\"text/javascript\">");
		strScript.append("display(" + "\"group" + _ID + "\");");// 展开本级机构
		strScript.append("</script>");

		str.append("<table id=\"totalTab\" width=\"100%\" cellspacing=\"0\" cellpadding=\"2\">");
		str = generateTree(parentId, contextPath, str, list, mutiSelected);
		str.append(strScript);
		str.append("</table>");
		return str.toString();
	}

	/**
	 * 生成树形结构
	 * @param mutiSelected 是否支持多选
	 */
	public StringBuffer generateTree(Integer parentId, String contextPath, StringBuffer str, List<DeptInfo> list, boolean mutiSelected) {
		List<DeptInfo> tmpList = new ArrayList<DeptInfo>();
		DeptInfo dept = null;
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			dept = (DeptInfo) list.get(i);
			if (parentId == null)
				parentId = 0;
			if ((dept.getUpperDept() == null) || (dept.getUpperDept().intValue() == parentId.intValue())) {
				tmpList.add(dept);
				list.remove(i);
				i--;
			}
		}
		for (int j = 0; j < tmpList.size(); j++) {
			boolean flag = false;
			dept = (DeptInfo) tmpList.get(j);
			count = dept.getListCount();
			for (int k = 0; k < list.size(); k++) {
				DeptInfo dept1 = (DeptInfo) list.get(k);
				if (dept1.getUpperDept().intValue() == dept.getId().intValue()) {
					flag = true;
					break;
				}
			}
			str.append("<tr bgcolor=\"#f7fafe\"><td width=\"14\">");
			str.append("<img id=\"img_group" + dept.getId() + "\" src=\"" + contextPath + "/module/map/images/foldericon.png\" border=\"0\" style=\"cursor:hand\" onClick=\"display('group" + dept.getId() + "')\">");
			str.append("</td><td colspan=\"2\" class=\"font_696969\">");
			if (mutiSelected) {
				str.append("<input id=\"cb_group" + dept.getId() + "\" type=\"checkbox\" name=\"cb_group\" value=\"" + dept.getId() + "\" onClick=\"return selectNode(this);\">");
			}
			str.append("&nbsp;<font style=\"cursor:default;font-size=12px;\">" + dept.getName() + "(" + count + ")</font></td></tr>");

			// 显示机构下的用户
			List<LocaQueryBean> userList = dept.getList();
			LocaQueryBean user = null;
			if ((!flag) && ((userList == null) || (userList.size() == 0))) {
				continue;
			}
			str.append("<tr bgcolor=\"#f7fafe\"  style=\"display: none;\" id=\"tr_group" + dept.getId() + "\"><td>　</td><td colspan=\"2\">");
			str.append("<table width=\"100%\" cellspacing=\"0\" cellpadding=\"2\" id=\"tb_group" + dept.getId() + "\">");
			if (flag) {
				str.append("<tr bgcolor=\"#f7fafe\" style=\"display: none;\" id=\"tr_group" + dept.getId() + "\"><td>　</td><td colspan=\"2\">");
			}
			str = generateTree(dept.getId(), contextPath, str, list, mutiSelected);
			if (flag) {
				str.append("</td></tr>");
			}
			if ((userList != null) && (userList.size() > 0)) {
				for (int n = 0; n < dept.getList().size(); n++) {
					user = (LocaQueryBean) userList.get(n);
					// str.append("<tr bgcolor=\"#f7fafe\"><td width=\"14\">");
					// str.append("</td><td colspan=\"2\" class=\"font_696969\">");
					// str.append("<input id=\"cb_group" + dept.getId() + "_" + user.getId() + "\" type=\"checkbox\" name=\"cb_tu\" value=\"" + user.getId() + "\" onClick=\"return selectNode(this);\">");
					// str.append("&nbsp;<font style=\"cursor:default;font-size=12px;\" onDblClick=\"showLocaQuery('"+user.getId()+"');\">" + user.getName() + "</font></td></tr>");
					str.append("<tr bgcolor=\"#f7fafe\">");
					str.append("<td width=\"14\" class=\"font_696969\"><input id=\"cb_tu" + user.getId() + "\" type=\"checkbox\" name=\"cb_tu\" value= \"" + user.getId() + "\" onClick=\"return selectNode(this);\"></td>");
					str.append("<td class=\"font_696969\">&nbsp;<font style=\"font-size=12px;cursor:hand;\" title=\"手机号：" + user.getLocNum() + "\" id= \"" + user.getId() + "\" onDblClick=\"getLastPoint('" + user.getLocNum() + "');\" >" + user.getName() + "</font>");
					str.append("<input type=\"hidden\" id=\"hd_tu" + user.getId() + "\" value=\""+user.getLocNum()+"\"/></td>");
					str.append("<td style=\"font-size: 12px;\" class=\"font_696969\" id=\"td_tu" + user.getId() + "\"></td>");
					str.append("</tr>");
				}
			}			
			str.append("</table></td></tr>");
		}
		return str;
	}

	/**
	 * 查找某个定位号码一段时间内的定位信息
	 * 
	 * @param locNum
	 * @param startDate
	 * @param endDate
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public PageList findLocationInfoInDate(String terminalId, String startDate, String endDate, Pages pages) throws IctException {
		LocaQueryDao lqd = getDao();
		try {
			PageList result = new PageList();
			pages.setTotal(lqd.getLocationInfoCountInDate(terminalId, startDate, endDate));
			result.setPages(pages);
			List<LocaQueryBean> liInfoList = lqd.findLocationInfoInDate(terminalId, startDate, endDate, pages.getStart(), pages.getLimit());
			if ((liInfoList != null) && (liInfoList.size() > 0)) {
				for (int i = 0; i < liInfoList.size(); i++) {
					LocaQueryBean locInfo = liInfoList.get(i);
					locInfo.setNum(i+1);
					boolean flag = true;
					if ((locInfo.getRectifyLong() != null) && (locInfo.getRectifyLat() != null)) {
						for (int j = 0; j < i; j++) {
							LocaQueryBean li = liInfoList.get(j);
							if ((locInfo.getRectifyLong().equals(li.getRectifyLong())) && (locInfo.getRectifyLat().equals(li.getRectifyLat()))) {
								flag = false;
								break;
							}
						}
					}
					if (flag) {
						locInfo.setChecked("checked");
						liInfoList.remove(i);
						liInfoList.add(i, locInfo);
					}
				}
				
				//设置参数字符串,added by lifa,2010-6-11
//				for(int k =0; k < liInfoList.size(); k++) {
//					LocaQueryBean locInfo = liInfoList.get(k);
//					String params = locInfo.getId()+","+locInfo.getHolder()+","+locInfo.getLocNum()+","+locInfo.getRectifyLong()+","+locInfo.getRectifyLat()+","+locInfo.getLocDate()+","+locInfo.getPlaceName();
//					liInfoList.get(k).setParamStr(params);
//				}
			}
			
			result.addAll(liInfoList);
			return result;
		} catch (IctException e) {
			logger.error("查找定位信息失败！");
			logger.error(e);
			throw e;
		}
	}
	
	@Transactional(readOnly = true)
	public LocaQueryBean findLocationInfoByCondition(String sqlMap, LocaQueryForm condition){
		return this.getDao().findLocationInfoByCondition(sqlMap, condition);
	}
	
	/** setter and getter methods * */

	protected LocaQueryDao getDao() {
		return (LocaQueryDao) super.getDao();
	}

	@Autowired
	public void setDao(LocaQueryDao locaQueryDao) {
		super.setDao(locaQueryDao);
	}

	public DeptDao getDeptDao() {
		return deptDao;
	}

	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public PageList findLocationInfoInDateNew(String log, String lat,String startDate, String endDate, Pages pages) throws IctException {
		LocaQueryDao lqd = getDao();
		try {
			PageList result = new PageList();
			pages.setTotal(lqd.getLocationInfoCountInDate("", startDate, endDate));
			result.setPages(pages);
			List<LocaQueryBean> liInfoList = lqd.findLocationInfoInDate("", startDate, endDate, pages.getStart(), pages.getLimit());
			if ((liInfoList != null) && (liInfoList.size() > 0)) {
				for (int i = 0; i < liInfoList.size(); i++) {
					LocaQueryBean locInfo = liInfoList.get(i);
					if(!getPositionStatus(locInfo.getRectifyLong(),locInfo.getRectifyLat(),log,lat)){
						liInfoList.remove(i);
						i--;
					}
				}
			}
			
			result.addAll(liInfoList);
			return result;
		} catch (IctException e) {
			logger.error("查找定位信息失败！");
			logger.error(e);
			throw e;
		}
	}
	
	private boolean getPositionStatus(double lng, double lat, String new_lng,String new_lat) {
		Point point = new Point();
		point.setX(lng);
		point.setY(lat);
		String[] newlng=new_lng.split(",",0);
		String[] newlat=new_lat.split(",",0);
		List<Point> pointList = new ArrayList<Point>();
		for (int i=0;i<newlng.length;i++) {
			Point p = new Point();
			p.setX(Double.parseDouble(newlng[i]));
			p.setY(Double.parseDouble(newlat[i]));
			pointList.add(p);
		}
		return PointManager.isPointInPolygon(point, pointList);
	}

	public LocaQueryBean findLocationInfoById(Integer locationId) {
		return this.getDao().findLocationInfoById(locationId);
	}
}