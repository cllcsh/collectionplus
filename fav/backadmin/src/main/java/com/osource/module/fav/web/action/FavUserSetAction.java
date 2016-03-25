package com.osource.module.fav.web.action;

import java.util.HashMap;
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

import com.osource.module.fav.model.FavUserSetInfo;
import com.osource.module.fav.service.FavUserSetService;
import com.osource.module.fav.web.form.FavUserSetForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class FavUserSetAction extends BaseAction {
	
	@Autowired
	private FavUserSetService favUserSetService;
	
	private FavUserSetForm favUserSetForm;

	private static Map<String, String> blockTypeMap = CommonCache.getEnumInfos(EnumTypeCons.FAV_USER_SET_BLOCK_TYPE);
	
	/** action methods **/
	
	public FavUserSetAction(){
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
		putConditonMap(condition, "userName", favUserSetForm.getUserName(), false);
		putConditonMap(condition, "friendName", favUserSetForm.getFriendName(), false);
		putConditonMap(condition, "blockComment", favUserSetForm.getBlockComment(), true);
		putConditonMap(condition, "blockDynamic", favUserSetForm.getBlockDynamic(), true);
		putConditonMap(condition, "blockMsg", favUserSetForm.getBlockMsg(), true);
		putConditonMap(condition, "blockReply", favUserSetForm.getBlockReply(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<FavUserSetInfo> list = favUserSetService.findByCondition(condition, pages);
		if (null != list){
			for (FavUserSetInfo info : list) {
				deal(info);
			}
		}
		this.setPageList(list);
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加用户设置信息页面
	 */
	public String add() {
		this.setActionName("favUserSet_save");
		return RESULT_SET;
	}

	/**
	 *  添加用户设置信息信息
	 */
	public String save() {
		FavUserSetInfo favUserSetInfo = new FavUserSetInfo();
	
		try {
			if(favUserSetForm != null)
				IctUtil.copyProperties(favUserSetInfo, favUserSetForm);
			favUserSetInfo.setInsertId(getUserSession().getUserId());
			favUserSetService.save(favUserSetInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加用户设置信息成功");
			logger.debug("添加用户设置信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加用户设置信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑用户设置信息页面
	 */
	public String edit() throws IctException {
		FavUserSetInfo favUserSetInfo;
		favUserSetInfo = favUserSetService.findById(this.getId());
		
		FavUserSetForm favUserSetForm = new FavUserSetForm(); 
		IctUtil.copyProperties(favUserSetForm, favUserSetInfo);
		
		this.setFavUserSetForm(favUserSetForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看用户设置信息页面
	 */
	public String view() throws IctException {
		FavUserSetInfo favUserSetInfo;
		favUserSetInfo = favUserSetService.findById(Integer.valueOf(this.getId()));
		
		FavUserSetForm favUserSetForm = new FavUserSetForm(); 
		IctUtil.copyProperties(favUserSetForm, favUserSetInfo);
		
		this.setFavUserSetForm(favUserSetForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改用户设置信息信息
	 */
	public String update() {
		FavUserSetInfo favUserSetInfo = new FavUserSetInfo();
		
		try {
			IctUtil.copyProperties(favUserSetInfo, favUserSetForm);
			favUserSetInfo.setUpdateId(getUserSession().getUserId());
			
			favUserSetService.update(favUserSetInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改用户设置信息成功");
			logger.debug("修改用户设置信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改用户设置信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除用户设置信息信息
	 */
	public String deletes() {
		try {
			favUserSetService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	private void deal(FavUserSetInfo info){
		info.setBlockCommentDesc(StringUtil.trimPage(blockTypeMap.get(info.getBlockComment())));
		info.setBlockDynamicDesc(StringUtil.trimPage(blockTypeMap.get(info.getBlockDynamic())));
		info.setBlockMsgDesc(StringUtil.trimPage(blockTypeMap.get(info.getBlockMsg())));
		info.setBlockReplyDesc(StringUtil.trimPage(blockTypeMap.get(info.getBlockReply())));
	}
	
	public FavUserSetService getFavUserSetService() {
		return favUserSetService;
	}

	public void setFavUserSetService(FavUserSetService favUserSetService) {
		this.favUserSetService = favUserSetService;
	}

	public FavUserSetForm getFavUserSetForm() {
		return favUserSetForm;
	}

	public void setFavUserSetForm(FavUserSetForm favUserSetForm) {
		this.favUserSetForm = favUserSetForm;
	}

	public Map<String, String> getBlockTypeMap() {
		return blockTypeMap;
	}

	public void setBlockTypeMap(Map<String, String> blockTypeMap) {
		this.blockTypeMap = blockTypeMap;
	}
}