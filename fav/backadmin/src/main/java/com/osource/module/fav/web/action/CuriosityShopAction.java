package com.osource.module.fav.web.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CuriosityShopInfo;
import com.osource.module.fav.service.CuriosityShopService;
import com.osource.module.fav.web.form.CuriosityShopForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CuriosityShopAction extends BaseAction {
	
	@Autowired
	private CuriosityShopService curiosityShopService;
	
	private CuriosityShopForm curiosityShopForm;

	private List<String> imgs;
	
	/** action methods **/
	
	public CuriosityShopAction(){
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
		putConditonMap(condition, "name", curiosityShopForm.getName(), false);
		putConditonMap(condition, "address", curiosityShopForm.getAddress(), false);
		putConditonMap(condition, "phone", curiosityShopForm.getPhone(), false);
		putConditonMap(condition, "introduction", curiosityShopForm.getIntroduction(), false);
		if (!"省份".equals(curiosityShopForm.getProvince())){
			putConditonMap(condition, "province", curiosityShopForm.getProvince(), false);
		}
		if (!"市".equals(curiosityShopForm.getCity())){
			putConditonMap(condition, "city", curiosityShopForm.getCity(), false);
		}
		if (!"市/县".equals(curiosityShopForm.getCounty())){
			putConditonMap(condition, "county", curiosityShopForm.getCounty(), false);
		}
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(curiosityShopService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加古玩店信息页面
	 */
	public String add() {
		this.setActionName("curiosityShop_save");
		return RESULT_SET;
	}

	/**
	 *  添加古玩店信息信息
	 */
	public String save() {
		CuriosityShopInfo curiosityShopInfo = new CuriosityShopInfo();
	
		try {
			if(curiosityShopForm != null)
				IctUtil.copyProperties(curiosityShopInfo, curiosityShopForm);
			curiosityShopInfo.setInsertId(getUserSession().getUserId());
			int size = imgs.size();
			if (null != imgs && size != 0){
				String images = "";
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						images += img + ",";
					}
				}
				if (images.length() > 1){
					curiosityShopInfo.setImages(images.substring(0, images.length() - 1));
				}
			}
			curiosityShopService.save(curiosityShopInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加古玩店信息成功");
			logger.debug("添加古玩店信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加古玩店信息失败");
			logger.debug(e);
			e.printStackTrace();
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑古玩店信息页面
	 */
	public String edit() throws IctException {
		CuriosityShopInfo curiosityShopInfo;
		curiosityShopInfo = curiosityShopService.findById(this.getId());
		
		CuriosityShopForm curiosityShopForm = new CuriosityShopForm(); 
		IctUtil.copyProperties(curiosityShopForm, curiosityShopInfo);
		String images = curiosityShopForm.getImages();
		if (StringUtil.isNotEmptyAndNotNull(images)){
			imgs = Arrays.asList(images.split(","));
		}
		this.setCuriosityShopForm(curiosityShopForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看古玩店信息页面
	 */
	public String view() throws IctException {
		CuriosityShopInfo curiosityShopInfo;
		curiosityShopInfo = curiosityShopService.findById(Integer.valueOf(this.getId()));
		
		CuriosityShopForm curiosityShopForm = new CuriosityShopForm(); 
		IctUtil.copyProperties(curiosityShopForm, curiosityShopInfo);
		String images = curiosityShopForm.getImages();
		if (StringUtil.isNotEmptyAndNotNull(images)){
			imgs = Arrays.asList(images.split(","));
		}
		this.setCuriosityShopForm(curiosityShopForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改古玩店信息信息
	 */
	public String update() {
		CuriosityShopInfo curiosityShopInfo = new CuriosityShopInfo();
		
		try {
			IctUtil.copyProperties(curiosityShopInfo, curiosityShopForm);
			curiosityShopInfo.setUpdateId(getUserSession().getUserId());
			if (null != imgs && imgs.size() != 0){
				String images = "";
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						images += img + ",";
					}
				}
				if (images.length() > 1){
					curiosityShopInfo.setImages(images.substring(0, images.length() - 1));
				}
			}
			curiosityShopService.update(curiosityShopInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改古玩店信息成功");
			logger.debug("修改古玩店信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改古玩店信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除古玩店信息信息
	 */
	public String deletes() {
		try {
			curiosityShopService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public CuriosityShopService getCuriosityShopService() {
		return curiosityShopService;
	}

	public void setCuriosityShopService(CuriosityShopService curiosityShopService) {
		this.curiosityShopService = curiosityShopService;
	}

	public CuriosityShopForm getCuriosityShopForm() {
		return curiosityShopForm;
	}

	public void setCuriosityShopForm(CuriosityShopForm curiosityShopForm) {
		this.curiosityShopForm = curiosityShopForm;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
}