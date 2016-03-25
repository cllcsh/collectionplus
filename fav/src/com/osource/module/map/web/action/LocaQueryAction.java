package com.osource.module.map.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;

import com.osource.base.common.CodeBookManager;
import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.module.map.model.CenterPointBean;
import com.osource.module.map.model.LocaQueryBean;
import com.osource.module.map.service.CenterPointService;
import com.osource.module.map.service.LocaQueryService;
import com.osource.module.map.service.LocationService;
import com.osource.module.map.task.LocationTask;
import com.osource.module.map.web.form.LocaQueryForm;
import com.osource.module.map.web.form.MapForm;
import com.osource.module.map.web.session.CenterPointUserSession;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class LocaQueryAction extends BaseAction {

	@Autowired
	private LocaQueryService locaQueryService;
	@Autowired
	@Qualifier("locationService")
	private LocationService locationService;
	@Autowired
	private TaskExecutor threadPoolTaskExecutor;
	private LocaQueryForm locaQueryForm;

	private String strHtml;

	private String locationIds;

	private Integer deptId;
	
	private String mapType;//地图类型
	
	private MapForm	mapForm;
	
	@Autowired
	private CenterPointService centerPointService;

	/** action methods * */

	public String frame() {
		if(getUserSession().match(CenterPointUserSession.class)){
			CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession().getUserSession(CenterPointUserSession.class);
			this.pushValueStack("centerPointUserSession", centerPointUserSession);
		}
		return "frame";
	}

	/**
	 * 功能初始页面跳转
	 */
	public String init() {
		mapType = request.getParameter("mapType");
		
		if(mapType == null){//初始显示默认地图
			return "init";
		}else{
			if(("google").equals(mapType)){//使用google地图
				return "googleInit";
			}
		}
		
		return RESULT_INIT;
	}

	/**
	 * 地图初始化
	 */
	public String map() {
		if(getUserSession().match(CenterPointUserSession.class)){
			CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession().getUserSession(CenterPointUserSession.class);
			this.pushValueStack("centerPointUserSession", centerPointUserSession);
		}
		return RESULT_MAP;
	}

	/**
	 * 根据查询条件进行查询
	 */
	@SuppressWarnings("unchecked")
	public String query() {
		if (locaQueryForm.getDeptId() == null) {
			locaQueryForm.setDeptId(this.getUserSession().getDeptId());
		}
		Map map = new HashMap();
		map.put("objType", locaQueryForm.getObjType());
		map.put("deptId", locaQueryForm.getDeptId());
		String contextPath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
		try {
			strHtml = locaQueryService.generateTree(contextPath, map, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_LIST;
	}

	/**
	 * 跳转到查看定位查询页面
	 */
	public String view() throws IctException {
		if (locaQueryForm.getDeptId() == null) {
			locaQueryForm.setDeptId(this.getUserSession().getDeptId());
		}
		String[] ss = null;
		List<String> list = null;
		List<LocaQueryBean> list1 = null;

		try {
			ss = locationIds.split(",");
			list = new ArrayList<String>();
			for (int i = 0; i < ss.length; i++) {
				if (!"".equals(ss[i])) {
					list.add(ss[i]);
				}
			}
			if (locaQueryForm.getObjType() == 0) {
				list1 = locaQueryService.queryByCondition("postition_criminal_findByCondition", list);
			} else if (locaQueryForm.getObjType() == 1) {
				list1 = locaQueryService.queryByCondition("postition_user_findByCondition", list);
			} else {
				list1 = new ArrayList<LocaQueryBean>();
			}

			//session.put("userLocList", list1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return RESULT_VIEW;
		return returnJsonString(list1);
	}

	/**
	 * 发送定位请求
	 */
	public String location() {
		List<Integer> terminalIds = new ArrayList<Integer>();
		if ((locationIds != null)) {
			String[] terminals = locationIds.split(",");
			for (String terminal : terminals) {
				if ((terminal != null) && !terminal.trim().equals("")) {
					terminalIds.add(Integer.valueOf(terminal));
				}
			}
		}
		
		try {
			List<Integer> locaIds = locationService.saveLocations(terminalIds); 
			threadPoolTaskExecutor.execute(new LocationTask(locaIds));
			locationIds = "";
			for (int i = 0; i < locaIds.size(); i++) {
				if (i == locaIds.size() - 1) {
					locationIds = locationIds + locaIds.get(i);
				} else {
					locationIds = locationIds + locaIds.get(i) + ",";
				}
			}
			if(locaIds == null || locaIds.size() == 0){
				this.getAjaxMessagesJson().setMessage("TERMINIAL_SAVE_FAILED", "定位时发生系统错误，请稍后重试！");
			}else{
				this.getAjaxMessagesJson().setMessage("0", locationIds);
			}
		} catch (IctException e) {
			this.getAjaxMessagesJson().setMessage("TERMINIAL_SAVE_FAILED", "定位时发生系统错误，请稍后重试！");
			e.printStackTrace();
		}
		return RESULT_AJAXJSON;
	}

	/**
	 * 获取定位数据
	 */
	public String getData() {
		String returnStr = "";
		String[] arrLocaId = null;
		String outNum = "";
		String tempId = "";
		try {
			arrLocaId = locationIds.split(",");
			locationIds = "";
			for (int i = 0; i < arrLocaId.length; i++) {
				if ((arrLocaId[i] != null) && (!arrLocaId[i].equals(""))) {
					tempId = arrLocaId[i];
				} else {
					continue;
				}
				locaQueryForm.setId(Integer.valueOf(tempId));
				LocaQueryBean locaQueryBean = locaQueryService.findLocationInfoByCondition("map_findLocationInfoById", locaQueryForm);
				if ((locaQueryBean != null) && (locaQueryBean.getLocCode() != null) && (!"".equals(locaQueryBean.getLocCode()))) {
					if (i == 0) {
						returnStr = locaQueryBean.getName() + "**" + locaQueryBean.getLocNum() + "**" + locaQueryBean.getLocDate() + "**" + locaQueryBean.getRectifyLong() + "**" + locaQueryBean.getRectifyLat() + "**" + locaQueryBean.getLocCode() + "**" + locaQueryBean.getPlaceName() + "**" + CodeBookManager.getInstance().getDictValue("tb_location_loc_code", locaQueryBean.getLocCode())
							+ "**" +locaQueryBean.getLocId()+ "**" +locaQueryBean.getRadius()+ "**" +locaQueryBean.getPosour()+ "**" +locaQueryBean.getPic_path()+ "**" +locaQueryBean.getAreaId()+ "**" +locaQueryBean.getAreaName() + "**" + locaQueryBean.getStaffId() + "**" + locaQueryBean.getStaffName()+"**" + locaQueryBean.getId();;
					} else {
						returnStr = returnStr + "$$" + locaQueryBean.getName() + "**" + locaQueryBean.getLocNum() + "**" + locaQueryBean.getLocDate() + "**" + locaQueryBean.getRectifyLong() + "**" + locaQueryBean.getRectifyLat() + "**" + locaQueryBean.getLocCode() + "**" + locaQueryBean.getPlaceName() + "**" + CodeBookManager.getInstance().getDictValue("tb_location_loc_code", locaQueryBean.getLocCode())
							+ "**" +locaQueryBean.getLocId()+ "**" +locaQueryBean.getRadius()+ "**" +locaQueryBean.getPosour()+ "**" +locaQueryBean.getPic_path()+ "**" +locaQueryBean.getAreaId()+ "**" +locaQueryBean.getAreaName() + "**" + locaQueryBean.getStaffId() + "**" + locaQueryBean.getStaffName() +"**" + locaQueryBean.getId();;
					}
				} else {
					if (i == 0) {
						locationIds = arrLocaId[i];
						outNum = locaQueryBean.getLocNum();
					} else {
						locationIds = locationIds + "," + arrLocaId[i];
						outNum = outNum + "," + locaQueryBean.getLocNum();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ajaxMessagesJson.setMessage(outNum, returnStr, locationIds);
		return RESULT_AJAXJSON;
	}

	/**
	 * 显示最后一次成功定位信息
	 */
	public String getLastPoint() {
		LocaQueryBean locaQueryBean = null;
		LocaQueryBean locaQueryBean2 = null;
		try {
			locaQueryBean = locaQueryService.findLocationInfoByCondition("map_findLastLocationInfoByNum", locaQueryForm);
			locaQueryBean2 = locaQueryService.findLocationInfoByCondition("map_findLatestLocationInfoByNum", locaQueryForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ((locaQueryBean != null) && (locaQueryBean.getId() != null)) {
			String returnStr = locaQueryBean.getName() + "**" + locaQueryBean.getLocNum() + "**" + locaQueryBean.getLocDate() + "**" + locaQueryBean.getRectifyLong() + "**" + locaQueryBean.getRectifyLat() + "**" + locaQueryBean.getLocCode() + "**" + locaQueryBean.getPlaceName() 
				+ "**" + locaQueryBean.getLocId()+ "**" +locaQueryBean.getRadius()+ "**" +locaQueryBean.getPosour()+ "**" +locaQueryBean.getPic_path()+ "**" +locaQueryBean.getAreaId()+ "**" +locaQueryBean.getAreaName() + "**" + locaQueryBean.getName()+ "**" + locaQueryBean.getStaffId() + "**" + locaQueryBean.getStaffName() +  "**" + locaQueryBean.getId();
			
			if(locaQueryBean.getLocDate().equals(locaQueryBean2.getLocDate()))
				this.ajaxMessagesJson.setMessage("0", returnStr);
			else
				this.ajaxMessagesJson.setMessage("1", returnStr);
				
		} else {
			this.ajaxMessagesJson.setMessage("MAP_FINDLOCATION_FAILD", "没有查到相关位置信息");
		}
		return RESULT_AJAXJSON;
	}
	
	public String mapView()
	{
//		try {
//			String locationId = request.getParameter("locationId");
//			
//			mapForm = new MapForm();
//			
//			mapForm.setDisplayWay((String)request.getParameter("displayWay"));//显示方式：1-显示 围栏 ,0-不显示围栏
//			
//			LocaQueryBean locaQueryBean = new LocaQueryBean();
//			if( locationId != null && !locationId.equals(""))
//				locaQueryBean = locaQueryService.findLocationInfoById(Integer.valueOf(locationId));
//			
//			if(locaQueryBean != null ){
//				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				
//				mapForm.setHolder(locaQueryBean.getHolder()) ;
//				mapForm.setPlaceName(locaQueryBean.getPlaceName()); 
//				
//				mapForm.setLocationId(locaQueryBean.getId());
//				mapForm.setRectifyLong(locaQueryBean.getRectifyLong());
//				mapForm.setRectifyLat(locaQueryBean.getRectifyLat());
//				mapForm.setRailingsId(locaQueryBean.getRailingsId());
//				mapForm.setLocationNum(locaQueryBean.getLocNum());
//				mapForm.setLocDate(locaQueryBean.getLocDate());
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
		String terminalIds = request.getParameter("terminalIds");
		mapForm = new MapForm();
		mapForm.setTerminalIds(terminalIds);
		CenterPointBean cpi = new CenterPointBean();
		try {
			cpi = centerPointService.findCenterPointInfoByUserId(getUserSession().getUserId());
		} catch (IctException e) {
			e.printStackTrace();
		}
		mapForm.setLongitude(cpi.getLongitude());
		mapForm.setLatitude(cpi.getLatitude());
		mapForm.setZoomLevel(cpi.getZoomLevel());
		return "view";
	}

	/** getter and setter methods * */

	public LocaQueryService getLocaQueryService() {
		return locaQueryService;
	}

	public void setLocaQueryService(LocaQueryService locaQueryService) {
		this.locaQueryService = locaQueryService;
	}

	public LocaQueryForm getLocaQueryForm() {
		return locaQueryForm;
	}

	public void setLocaQueryForm(LocaQueryForm locaQueryForm) {
		this.locaQueryForm = locaQueryForm;
	}

	public String getStrHtml() {
		return strHtml;
	}

	public void setStrHtml(String strHtml) {
		this.strHtml = strHtml;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	public MapForm getMapForm() {
		return mapForm;
	}

	public void setMapForm(MapForm mapForm) {
		this.mapForm = mapForm;
	}
}