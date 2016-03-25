package com.osource.module.fav.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.fav.model.HomeInfo;
import com.osource.module.fav.service.HomeService;
import com.osource.module.fav.web.form.HomeForm;
import com.osource.util.Cons;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class HomeAction extends BaseAction {
	
	@Autowired
	private HomeService homeService;
	
	private HomeForm homeForm;
	
	private List<String> imgs;
	
	private List<String> imgPaths;

	/** action methods **/
	
	public HomeAction(){
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
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<HomeInfo> list = homeService.findByCondition(condition, pages);
		if (null != list){
			String img = Cons.DEFAULT_IMG;
			for (HomeInfo homeInfo : list) {
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages1())){
					homeInfo.setAdImages1(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages2())){
					homeInfo.setAdImages2(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages3())){
					homeInfo.setAdImages3(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages4())){
					homeInfo.setAdImages4(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages5())){
					homeInfo.setAdImages5(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages6())){
					homeInfo.setAdImages6(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages7())){
					homeInfo.setAdImages7(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages8())){
					homeInfo.setAdImages8(img);
				}
				if (StringUtil.isEmptyOrNull(homeInfo.getAdImages9())){
					homeInfo.setAdImages9(img);
				}
			}
		}
		this.setPageList(list);
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加首页信息页面
	 */
	public String add() {
		this.setActionName("home_save");
		return RESULT_SET;
	}

	/**
	 *  添加首页信息信息
	 */
	public String save() {
		HomeInfo homeInfo = new HomeInfo();
	
		try {
			if(homeForm != null)
				IctUtil.copyProperties(homeInfo, homeForm);
			homeInfo.setInsertId(getUserSession().getUserId());
			if (null != imgs && imgs.size() != 0){
				int i = 1;
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						if (i == 1){
							homeInfo.setAdImages1(img);
							homeInfo.setAdPath1(imgPaths.get(i-1));
							i ++;
						}else if (i == 2){
							homeInfo.setAdImages2(img);
							homeInfo.setAdPath2(imgPaths.get(i-1));
							i ++;
						}else if (i == 3){
							homeInfo.setAdImages3(img);
							homeInfo.setAdPath3(imgPaths.get(i-1));
							i ++;
						}else if (i == 4){
							homeInfo.setAdImages4(img);
							homeInfo.setAdPath4(imgPaths.get(i-1));
							i ++;
						}else if (i == 5){
							homeInfo.setAdImages5(img);
							homeInfo.setAdPath5(imgPaths.get(i-1));
							i ++;
						}else if (i == 6){
							homeInfo.setAdImages6(img);
							homeInfo.setAdPath6(imgPaths.get(i-1));
							i ++;
						}else if (i == 7){
							homeInfo.setAdImages7(img);
							homeInfo.setAdPath7(imgPaths.get(i-1));
							i ++;
						}else if (i == 8){
							homeInfo.setAdImages8(img);
							homeInfo.setAdPath8(imgPaths.get(i-1));
							i ++;
						}else if (i == 9){
							homeInfo.setAdImages9(img);
							homeInfo.setAdPath9(imgPaths.get(i-1));
							i ++;
						}
					}
				}
			}
			homeService.save(homeInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加首页信息成功");
			logger.debug("添加首页信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加首页信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑首页信息页面
	 */
	public String edit() throws IctException {
		HomeInfo homeInfo;
		homeInfo = homeService.findById(this.getId());
		HomeForm homeForm = new HomeForm(); 
		IctUtil.copyProperties(homeForm, homeInfo);
		imgs = new ArrayList<String>();
		imgPaths = new ArrayList<String>();
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages1())){
			imgs.add(homeForm.getAdImages1());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath1()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages2())){
			imgs.add(homeForm.getAdImages2());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath2()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages3())){
			imgs.add(homeForm.getAdImages3());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath3()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages4())){
			imgs.add(homeForm.getAdImages4());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath4()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages5())){
			imgs.add(homeForm.getAdImages5());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath5()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages6())){
			imgs.add(homeForm.getAdImages6());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath6()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages7())){
			imgs.add(homeForm.getAdImages7());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath7()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages8())){
			imgs.add(homeForm.getAdImages8());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath8()));
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages9())){
			imgs.add(homeForm.getAdImages9());
			imgPaths.add(StringUtil.trimPage(homeForm.getAdPath9()));
		}
		if (!imgs.isEmpty()){
			homeForm.setImages("xxx");
		}
		this.setHomeForm(homeForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看首页信息页面
	 */
	public String view() throws IctException {
		HomeInfo homeInfo;
		homeInfo = homeService.findById(Integer.valueOf(this.getId()));
		
		HomeForm homeForm = new HomeForm(); 
		IctUtil.copyProperties(homeForm, homeInfo);
		imgs = new ArrayList<String>();
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages1())){
			imgs.add(homeForm.getAdImages1());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages2())){
			imgs.add(homeForm.getAdImages2());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages3())){
			imgs.add(homeForm.getAdImages3());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages4())){
			imgs.add(homeForm.getAdImages4());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages5())){
			imgs.add(homeForm.getAdImages5());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages6())){
			imgs.add(homeForm.getAdImages6());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages7())){
			imgs.add(homeForm.getAdImages7());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages8())){
			imgs.add(homeForm.getAdImages8());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdImages9())){
			imgs.add(homeForm.getAdImages9());
		}
		imgPaths = new ArrayList<String>();
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath1())){
			imgPaths.add(homeForm.getAdPath1());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath2())){
			imgPaths.add(homeForm.getAdPath2());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath3())){
			imgPaths.add(homeForm.getAdPath3());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath4())){
			imgPaths.add(homeForm.getAdPath4());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath5())){
			imgPaths.add(homeForm.getAdPath5());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath6())){
			imgPaths.add(homeForm.getAdPath6());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath7())){
			imgPaths.add(homeForm.getAdPath7());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath8())){
			imgPaths.add(homeForm.getAdPath8());
		}
		if (StringUtil.isNotEmptyAndNotNull(homeForm.getAdPath9())){
			imgPaths.add(homeForm.getAdPath9());
		}
		this.setHomeForm(homeForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改首页信息信息
	 */
	public String update() {
		HomeInfo homeInfo = new HomeInfo();
		
		try {
			IctUtil.copyProperties(homeInfo, homeForm);
			homeInfo.setUpdateId(getUserSession().getUserId());
			int i = 1;
			for (String img : imgs) {
				if (StringUtil.isNotEmptyAndNotNull(img)){
					if (i == 1){
						homeInfo.setAdImages1(img);
						homeInfo.setAdPath1(imgPaths.get(i-1));
						i ++;
					}else if (i == 2){
						homeInfo.setAdImages2(img);
						homeInfo.setAdPath2(imgPaths.get(i-1));
						i ++;
					}else if (i == 3){
						homeInfo.setAdImages3(img);
						homeInfo.setAdPath3(imgPaths.get(i-1));
						i ++;
					}else if (i == 4){
						homeInfo.setAdImages4(img);
						homeInfo.setAdPath4(imgPaths.get(i-1));
						i ++;
					}else if (i == 5){
						homeInfo.setAdImages5(img);
						homeInfo.setAdPath5(imgPaths.get(i-1));
						i ++;
					}else if (i == 6){
						homeInfo.setAdImages6(img);
						homeInfo.setAdPath6(imgPaths.get(i-1));
						i ++;
					}else if (i == 7){
						homeInfo.setAdImages7(img);
						homeInfo.setAdPath7(imgPaths.get(i-1));
						i ++;
					}else if (i == 8){
						homeInfo.setAdImages8(img);
						homeInfo.setAdPath8(imgPaths.get(i-1));
						i ++;
					}else if (i == 9){
						homeInfo.setAdImages9(img);
						homeInfo.setAdPath9(imgPaths.get(i-1));
						i ++;
					}
				}
			}
			i ++;
			while (i < 10) {
				if (i == 1){
					homeInfo.setAdImages1("");
					homeInfo.setAdPath1("");
					i ++;
				}else if (i == 2){
					homeInfo.setAdImages2("");
					homeInfo.setAdPath2("");
					i ++;
				}else if (i == 3){
					homeInfo.setAdImages3("");
					homeInfo.setAdPath3("");
					i ++;
				}else if (i == 4){
					homeInfo.setAdImages4("");
					homeInfo.setAdPath4("");
					i ++;
				}else if (i == 5){
					homeInfo.setAdImages5("");
					homeInfo.setAdPath5("");
					i ++;
				}else if (i == 6){
					homeInfo.setAdImages6("");
					homeInfo.setAdPath6("");
					i ++;
				}else if (i == 7){
					homeInfo.setAdImages7("");
					homeInfo.setAdPath7("");
					i ++;
				}else if (i == 8){
					homeInfo.setAdImages8("");
					homeInfo.setAdPath8("");
					i ++;
				}else if (i == 9){
					homeInfo.setAdImages9("");
					homeInfo.setAdPath9("");
					i ++;
				}
			}
			homeService.update(homeInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改首页信息成功");
			logger.debug("修改首页信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改首页信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除首页信息信息
	 */
	public String deletes() {
		try {
			homeService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public HomeService getHomeService() {
		return homeService;
	}

	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}

	public HomeForm getHomeForm() {
		return homeForm;
	}

	public void setHomeForm(HomeForm homeForm) {
		this.homeForm = homeForm;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public List<String> getImgPaths() {
		return imgPaths;
	}

	public void setImgPaths(List<String> imgPaths) {
		this.imgPaths = imgPaths;
	}
}