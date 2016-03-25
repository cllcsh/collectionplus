package com.osource.module.fav.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;
import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.fav.model.FavUserInfo;
import com.osource.module.fav.service.FavUserService;
import com.osource.module.fav.web.form.FavUserForm;
import com.osource.util.BooleanUtil;
import com.osource.util.IctUtil;
import com.osource.util.MD5;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class FavUserAction extends BaseAction {
	
	@Autowired
	private FavUserService favUserService;
	
	private FavUserForm favUserForm;
	
	private static Map<String, String> levelMap = new LinkedHashMap<String, String>();
	
	private static Map<String, String> personalMsgSetMap = CommonCache.getEnumInfos(EnumTypeCons.FAV_USER_PERSONAL_MSG_SET_TYPE);
	
	private static Map<Integer, String> userTitleMap = CommonCache.getUserTitles();
	private static Map<Integer, String> userTitleImgMap = CommonCache.getUserTitleImgs();
	private String searchName;
	
	private List<String> userTitle;
	private String startDate;
    private String endDate;
	static{
		levelMap.put("1", "一级");
		levelMap.put("2", "二级");
		levelMap.put("3", "三级");
		levelMap.put("4", "四级");
		levelMap.put("5", "五级");
		levelMap.put("6", "六级");
		levelMap.put("7", "七级");
		levelMap.put("8", "八级");
		levelMap.put("9", "九级");
		levelMap.put("10", "十级");
		levelMap.put("11", "十一级");
		levelMap.put("12", "十二级");
		levelMap.put("13", "十三级");
		levelMap.put("14", "十四级");
		levelMap.put("15", "十五级");
	}
	/** action methods **/
	
	public FavUserAction(){
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
		putConditonMap(condition, "userName", favUserForm.getUserName(), false);
		putConditonMap(condition, "account", favUserForm.getAccount(), false);
		putConditonMap(condition, "phone", favUserForm.getPhone(), false);
		if (StringUtil.isNotEmptyAndNotNull(favUserForm.getHeatDesc())){
			favUserForm.setHeat(Integer.parseInt(favUserForm.getHeatDesc()));
			putConditonMap(condition, "heat", favUserForm.getHeat(), false);
		}
		putConditonMap(condition, "userLevel", favUserForm.getUserLevel(), true);
		putConditonMap(condition, "userTitle", favUserForm.getUserTitle(), true);
		if (StringUtil.isNotEmptyAndNotNull(favUserForm.getUserPointsDesc())){
			favUserForm.setUserPoints(Integer.parseInt(favUserForm.getUserPointsDesc()));
			putConditonMap(condition, "userPoints", favUserForm.getUserPoints(), false);
		}
		if (StringUtil.isNotEmptyAndNotNull(favUserForm.getUserAllPointsDesc())){
			favUserForm.setUserAllPoints(Integer.parseInt(favUserForm.getUserAllPointsDesc()));
			putConditonMap(condition, "userAllPoints", favUserForm.getUserAllPoints(), false);
		}
		if (StringUtil.isNotEmptyAndNotNull(favUserForm.getExperienceDesc())){
			favUserForm.setExperience(Integer.parseInt(favUserForm.getExperienceDesc()));
			putConditonMap(condition, "experience", favUserForm.getExperience(), false);
		}
		putConditonMap(condition, "startDate", startDate, false);
        putConditonMap(condition, "endDate", endDate, false);
        putConditonMap(condition, "personalMsgSet", favUserForm.getPersonalMsgSet(), true);
        
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<FavUserInfo> list = favUserService.findByCondition(condition, pages);
		if (null != list){
			for (FavUserInfo bean : list) {
				dealFavUserInfo(bean);
			}
		}
		this.setPageList(list);
		return RESULT_LIST;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String queryForBrowse(){
		Map condition = new HashMap();
		putConditonMap(condition, "userName", searchName, false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<FavUserInfo> list = favUserService.findByCondition(condition, pages);
		JSONArray jsonArray = new JSONArray();
		if (null != list){
			for (FavUserInfo bean : list) {
				JSONObject object = new JSONObject();
				object.put("id", bean.getId());
				object.put("name", bean.getUserName());
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
	 *  跳转到添加用户信息页面
	 */
	public String add() {
		this.setActionName("favUser_save");
		return RESULT_SET;
	}

	/**
	 *  添加用户信息信息
	 */
	public String save() {
		FavUserInfo favUserInfo = new FavUserInfo();
	
		try {
			if(favUserForm != null)
				IctUtil.copyProperties(favUserInfo, favUserForm);
			favUserInfo.setInsertId(getUserSession().getUserId());
			favUserInfo.setPassword(MD5.GetMD5Code(favUserInfo.getPassword()));
			if (BooleanUtil.isEmpty(favUserInfo.getAvatar())){
				favUserInfo.setAvatar("/upload/file/profle.png");
			}
			String userTitleStr = "";
			if (null != userTitle){
				for (String str : userTitle) {
					userTitleStr += str + ",";
				}
			}
			if (!"".equals(userTitleStr)){
				favUserInfo.setUserTitle(userTitleStr.substring(0, userTitleStr.length()-1));
			}
			if (BooleanUtil.isEmpty(favUserInfo.getUpateAvatarTime())){
				favUserInfo.setUpateAvatarTime(new Date());
			}
			favUserService.save(favUserInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加用户信息成功");
			logger.debug("添加用户信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加用户信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑用户信息页面
	 */
	public String edit() throws IctException {
		FavUserInfo favUserInfo;
		favUserInfo = favUserService.findById(this.getId());
		
		FavUserForm favUserForm = new FavUserForm(); 
		IctUtil.copyProperties(favUserForm, favUserInfo);
		
		this.setFavUserForm(favUserForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看用户信息页面
	 */
	public String view() throws IctException {
		FavUserInfo favUserInfo;
		favUserInfo = favUserService.findById(Integer.valueOf(this.getId()));
		dealFavUserInfo(favUserInfo);
		FavUserForm favUserForm = new FavUserForm(); 
		IctUtil.copyProperties(favUserForm, favUserInfo);
		
		this.setFavUserForm(favUserForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改用户信息信息
	 */
	public String update() {
		FavUserInfo favUserInfo = new FavUserInfo();
		
		try {
			IctUtil.copyProperties(favUserInfo, favUserForm);
			favUserInfo.setUpdateId(getUserSession().getUserId());
			String userTitleStr = "";
			if (null != userTitle){
				for (String str : userTitle) {
					userTitleStr += str + ",";
				}
			}
			if (!"".equals(userTitleStr)){
				favUserInfo.setUserTitle(userTitleStr.substring(0, userTitleStr.length()-1));
			}
			if (BooleanUtil.isEmpty(favUserInfo.getAvatar())){
				favUserInfo.setAvatar("/upload/file/profle.png");
			}
			if (BooleanUtil.isEmpty(favUserInfo.getUpateAvatarTime())){
				favUserInfo.setUpateAvatarTime(new Date());
			}
			favUserService.update(favUserInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改用户信息成功");
			logger.debug("修改用户信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改用户信息失败");
			logger.debug(e);
			e.printStackTrace();
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除用户信息信息
	 */
	public String deletes() {
		try {
			favUserService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public String reset(){
		try {
			FavUserInfo info = favUserService.findById(getId());
			if (null != info){
				info.setPassword(MD5.GetMD5Code("123456"));
				favUserService.update(info);
			}
			logger.debug("重置成功，初始密码是123456");
			this.getAjaxMessagesJson().setMessage("0", "重置成功，初始密码是123456");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "重置密码失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	public String checkAccount(){
		String account = request.getParameter("account");
		String id = request.getParameter("id");
		if (BooleanUtil.isEmpty(id)){
			if (favUserService.isExist(account)){
				this.getAjaxMessagesJson().setMessage("0", "false");
			}else {
				this.getAjaxMessagesJson().setMessage("0", "true");
			}
		}else {
			FavUserInfo favUserInfo = favUserService.findById(Integer.valueOf(id));
			if (account.equals(favUserInfo.getAccount())){
				this.getAjaxMessagesJson().setMessage("0", "true");
			}else {
				if (favUserService.isExist(account)){
					this.getAjaxMessagesJson().setMessage("0", "false");
				}else {
					this.getAjaxMessagesJson().setMessage("0", "true");
				}
			}
		}
		return RESULT_AJAXJSON;
	}
	
	private void dealFavUserInfo(FavUserInfo bean){
		if (null == bean){
			return;
		}
		List<String> userTitleImgs = new ArrayList<String>();
		if (StringUtil.isNotEmptyAndNotNull(bean.getUserTitle())){
			String value = "";
			for (String key : bean.getUserTitle().split(",")) {
				value = userTitleImgMap.get(Integer.valueOf(key));
				if (StringUtil.isNotEmptyAndNotNull(value)){
					userTitleImgs.add(value);
				}
			}
			bean.setUserTitleImgs(userTitleImgs);
		}
		bean.setUserLevelDesc(StringUtil.trim(levelMap.get(bean.getUserLevel())));
	}
	/** getter and setter methods **/
	
	public FavUserService getFavUserService() {
		return favUserService;
	}

	public void setFavUserService(FavUserService favUserService) {
		this.favUserService = favUserService;
	}

	public FavUserForm getFavUserForm() {
		return favUserForm;
	}

	public void setFavUserForm(FavUserForm favUserForm) {
		this.favUserForm = favUserForm;
	}

	public Map<String, String> getLevelMap() {
		return levelMap;
	}

	public void setLevelMap(Map<String, String> levelMap) {
		this.levelMap = levelMap;
	}

	public Map<Integer, String> getUserTitleMap() {
		return userTitleMap;
	}

	public void setUserTitleMap(Map<Integer, String> userTitleMap) {
		this.userTitleMap = userTitleMap;
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

	public List<String> getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(List<String> userTitle) {
		this.userTitle = userTitle;
	}

	public Map<String, String> getPersonalMsgSetMap() {
		return personalMsgSetMap;
	}

	public void setPersonalMsgSetMap(Map<String, String> personalMsgSetMap) {
		this.personalMsgSetMap = personalMsgSetMap;
	}

	public Map<Integer, String> getUserTitleImgMap() {
		return userTitleImgMap;
	}

	public void setUserTitleImgMap(Map<Integer, String> userTitleImgMap) {
		this.userTitleImgMap = userTitleImgMap;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}