package com.front.home.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.front.db.bean.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.express.portal.Mapping;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.home.service.HomeService;
import com.front.shou.service.ShouService;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.common.base.GenericPageAction;
import com.front.web.util.IdCreaterTool;
import com.front.web.util.JsonUtil;

public class HomeAction extends GenericPageAction{

	private final static Logger logger = LoggerFactory.getLogger(HomeAction.class);
	
	
	@Mapping("/home")
	public Renderer goHome() throws Exception
	{ 
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
		HomeService.refreshHomeBean();
		int unreadMsgTotal = HomeService.queryUnreadMessagesTotal(user.getId());
		out.put("unreadMsgTotal",  unreadMsgTotal);
		List<JSONObject> recommendCollectionList = HomeService.queryRecommendCollections(user.getId());
		out.put("recommendCollectionList", recommendCollectionList);
		List<FavUserBean> topCollectorsList = HomeService.queryTopCollectors(user.getId());
		out.put("topCollectorsList", topCollectorsList);
		out.put("adImagesList",  SysConfigCache.getHomeAdImages());
        return new TemplateRenderer("/html/home/index.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/changeTopCollectors")
	public Renderer changeTopCollectors() throws Exception
	{ 
		FavUserBean user = getSessionUser();
		List<FavUserBean> topCollectorsList = HomeService.queryTopCollectors(user.getId());
		JSONArray jsonList = JsonUtil.listToJSONArray(topCollectorsList);
		writerObjToPage(jsonList);
        return null;
	}
	
	@Mapping("/changeRecommendCollections")
	public Renderer changeRecommendCollections() throws Exception
	{ 
		FavUserBean user = getSessionUser();
		List<JSONObject> recommendCollectionList = HomeService.queryRecommendCollections(user.getId());
		JSONArray jsonList = new JSONArray();
		for(JSONObject json : recommendCollectionList)
		{
			jsonList.add(json);
		}
		writerObjToPage(jsonList);
        return null;
	}
	
	@Mapping("/queryUnreadMessagesTotal")
	public Renderer queryUnreadMessagesTotal() throws Exception
	{ 
		FavUserBean user = getSessionUser();
		int unreadMsgTotal = HomeService.queryUnreadMessagesTotal(user.getId());
		JSONObject json = new JSONObject();
		json.put("unreadMsgTotal", unreadMsgTotal);
		writerObjToPage(json);
        return null;
	}
	
	@Mapping("/queryRecommendCollections")
	public Renderer queryRecommendCollections() throws Exception
	{ 
		FavUserBean user = getSessionUser();
		List<JSONObject> rcList = HomeService.queryRecommendCollections(user.getId());
		JSONArray recommendJsonArray = new JSONArray();
		for(JSONObject json : rcList)
		{
			recommendJsonArray.add(json);
		}
		writerObjToPage(recommendJsonArray);
        return null;
	}
	
	@Mapping("/message")
	public Renderer goMessage() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
		List<JSONObject> messageList = HomeService.queryUserAllMessages(user.getId(), 0, 9999);
		out.put("messageList", messageList);
        return new TemplateRenderer("/html/home/message.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/showMessage")
	public Renderer showMessage() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String sender = getRequest().getParameter("id");
		FavUserBean user = getSessionUser();
		HomeService.updateMessagesReadStatus(user.getId(), new BigDecimal(sender));
		List<JSONObject> messageList = HomeService.queryMessages(user.getId(), new BigDecimal(sender));
		out.put("messageList", messageList);
		FavUserBean senderUser = UserService.queryUserById(new BigDecimal(sender));
		out.put("sender", senderUser);
        return new TemplateRenderer("/html/home/message-show.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/replyMessage")
	public Renderer replyMessage() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String receiver = getRequest().getParameter("id");
		FavUserBean receiverUser = UserService.queryUserById(new BigDecimal(receiver));
		out.put("receiver", receiverUser);
        return new TemplateRenderer("/html/home/message-reply.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/sendMessage")
	public Renderer sendMessage() throws Exception
	{
		String receiver = getRequest().getParameter("id");
		String content = getRequest().getParameter("content");
		FavUserBean user = getSessionUser();
		JSONObject json = new JSONObject();
		boolean result = true;
		
		//收件人是否设置私信
	    FavUserBean fuBean = UserService.queryUserById(new BigDecimal(receiver));
	    if (null != fuBean)
	    {
	    	if (Constant.PERSONAL_MSG_SET_REJECT.equalsIgnoreCase(fuBean.getPersonal_msg_set()))
	    	{
	    		result = false;
				json.put("msg", "此用户不接收您的私信。");
	    	}
	    	else if (Constant.PERSONAL_MSG_SET_ATTENTION.equalsIgnoreCase(fuBean.getPersonal_msg_set()))
	    	{
	    		if (!ShouService.isUserFans(user.getId(), new BigDecimal(receiver)))
	    		{
	    			result = false;
					json.put("msg", "此用户不接收您的私信，请加关注后再试。");
	    		}
	    	}
	    }
		
	    if (result)
	    {
	    	//检查此用户是否设置不接受私信
	    	FavUserSetBean favUserSetBean = ShouService.queryFavUserSetBean(user.getId(), new BigDecimal(receiver));
	    	if(null != favUserSetBean)
	    	{
	    		if ("1".equals(favUserSetBean.getBlock_msg()))
	    		{
	    			result = false;
	    			json.put("msg", "此用户屏蔽您的私信。");
	    		}
	    	}	    	
	    }
		if (result)
		{
			result = HomeService.addMessage(new BigDecimal(receiver), user.getId(), content);
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	@Mapping("/todayAppreciation")
	public Renderer goTodayAppreciation() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		List<TodayAppreciationBean> taBeanList =  HomeService.queryTodayAppreciation();
		out.put("taBeanList", taBeanList);
        return new TemplateRenderer("/html/home/jianshang.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/dailyPolemic")
	public Renderer goDailyPolemic() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		DailyPolemicBean dpBean = HomeService.queryTodayDailyPolemic();
		List<String> imageList = new ArrayList<String>();
		if (dpBean != null)
		{
			String images = dpBean.getImages();
			if (StringUtils.isNotBlank(images))
			{
				imageList = Arrays.asList(images.split(","));
				dpBean.setImages(imageList.get(0));
			}
			int total = dpBean.getSupport_a_viewpoint() + dpBean.getSupport_b_viewpoint();
			if (total == 0)
			{
				out.put("support", "50%");
			}
			else
			{
				out.put("support", dpBean.getSupport_a_viewpoint()*100/total  + "%");
			}
			
		}
		else
		{
			dpBean = new DailyPolemicBean();
			out.put("support", "50%");
		}
		out.put("dpBean", dpBean);
		out.put("imageList", imageList);
        return new TemplateRenderer("/html/home/lunzhan.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
    @Mapping("/polemicVote")
	public Renderer polemicVote() throws Exception
	{
		String dailyPolemicId = getRequest().getParameter("id");
		String type = getRequest().getParameter("type");
		FavUserBean user = getSessionUser();
		String day = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		DailyPolemicVoteBean dpvBean = HomeService.queryDailyPolemicVote(user.getId(), new BigDecimal(dailyPolemicId), day);
		boolean result = false;
		JSONObject json = new JSONObject();
		if (null == dpvBean)
		{
			dpvBean = new DailyPolemicVoteBean();
			dpvBean.setId(IdCreaterTool.getPolemicVoteId());
			dpvBean.setDaily_polemic_id(new BigDecimal(dailyPolemicId));
			dpvBean.setUser_id(user.getId());
			dpvBean.setDay(day);
			dpvBean.setType(type);
			dpvBean.setInsert_id(user.getId());
			dpvBean.setUpdate_id(user.getId());
			result = HomeService.addDailyPolemicVote(dpvBean);
			if(!result)
			{
		        json.put("msg", "投票失败，请再次投票");
			}
		}
		else
		{
			json.put("msg", "今日已投票");
		}
		
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
}
