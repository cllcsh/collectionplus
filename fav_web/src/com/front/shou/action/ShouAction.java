package com.front.shou.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.express.portal.Mapping;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.db.bean.CollectionBean;
import com.front.db.bean.CollectionCategoryBean;
import com.front.db.bean.CollectionPeriodBean;
import com.front.db.bean.CommentBean;
import com.front.db.bean.CommentLikeBean;
import com.front.db.bean.CommentTopBean;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.FavUserSetBean;
import com.front.db.bean.FavoritesBean;
import com.front.db.bean.UserFansBean;
import com.front.home.service.HomeService;
import com.front.shou.service.ShouService;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.common.RuleConfig;
import com.front.web.common.base.GenericPageAction;
import com.front.web.util.FavRuntimeException;
import com.front.web.util.IdCreaterTool;
import com.front.web.util.JsonUtil;

public class ShouAction extends GenericPageAction{

	private final static Logger logger = LoggerFactory.getLogger(ShouAction.class);

	@Mapping("/collectionCircle")
	public Renderer goCollectionCircle() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
		List<JSONObject> collectionList = ShouService.queryDayHotCollections(user.getId());
		if (CollectionUtils.isEmpty(collectionList))
		{
			collectionList = ShouService.queryHotCollections(user.getId());
		}
		else
		{
			//今日最热不足100个藏品时，用最热藏品来填充
			if (collectionList.size() < 100)
			{
				List<JSONObject> hotList = ShouService.queryHotCollections(user.getId());
				if (CollectionUtils.isNotEmpty(hotList))
				{
					int size = collectionList.size();
					for(JSONObject collection : hotList)
					{
						if (!collectionList.contains(collection))
						{
							if (size >=100)break;
							
							collectionList.add(collection);
							size++;
						}
					}
				}
			}
		}
		if (CollectionUtils.isEmpty(collectionList))
		{
			collectionList = new ArrayList<JSONObject>();
		}
		out.put("collectionList", collectionList);
		out.put("total", collectionList.size());
        return new TemplateRenderer("/html/shou/list.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/searchCollection")
	public Renderer searchCollection() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String title = getRequest().getParameter("title");
		List<JSONObject> collectionList = ShouService.queryCollectionsByTitle(title);
		out.put("collectionList", collectionList);
		//out.put("total", collectionList.size());
		out.put("title", title);
        return new TemplateRenderer("/html/shou/search.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/collectionDetail")
	public Renderer collectionDetail() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject collectionDetail = ShouService.queryCollectionDetail(new BigDecimal(id));
		out.put("collectionDetail", collectionDetail);
		FavUserBean favUserBean = getSessionUser();
		//从分享的地址进入时，如没有session则不展示收藏按钮
		out.put("isShowFav", false);
		if (favUserBean != null)
		{
			out.put("isShowFav", true);
			out.put("isFaved", false);
			FavoritesBean fbean = ShouService.queryFavoritesBean(favUserBean.getId(), new BigDecimal(id));
			if (fbean != null)
			{
				out.put("isFaved", true);
			}
		}
		
        return new TemplateRenderer("/html/shou/show.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/addFavorites")
	public Renderer addFavorites()
	{
		String id = getRequest().getParameter("id");
		FavUserBean user = getSessionUser();
		String msg = null;
		boolean isAdd = false;
		try {
			isAdd = ShouService.addFavorites(user.getId(), new BigDecimal(id));
			if (isAdd)
			{
				msg = "添加收藏成功";
			}
			else
			{
				msg = "添加收藏失败";
			}
		} 
		catch (FavRuntimeException fe){
			msg = fe.getMessage();
		}
		catch (Exception e) {
			msg = "添加收藏失败";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", isAdd);
		resultJson.put("msg", msg);
		writerObjToPage(resultJson);
        return null;
	}
	
	@Mapping("/deleteFavorites")
	public Renderer deleteFavorites()
	{
		String collectionId = getRequest().getParameter("id");
		String msg = null;
		boolean isDel = false;
		try {
			isDel = ShouService.deleteFavoritesBean(getSessionUser().getId(), new BigDecimal(collectionId));
			if (isDel)
			{
				msg = "删除收藏成功";
			}
			else
			{
				msg = "删除收藏失败";
			}
		} 
		catch (FavRuntimeException fe){
			msg = fe.getMessage();
		}
		catch (Exception e) {
			msg = "删除收藏失败";
		}
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", isDel);
		resultJson.put("msg", msg);
		writerObjToPage(resultJson);
        return null;
	}
	
	@Mapping("/allCollection")
	public Renderer allCollection() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		List<JSONObject> collectionList = ShouService.queryAllCollections();
		out.put("collectionList", collectionList);
        return new TemplateRenderer("/html/shou/cate.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/categoryList")
	public Renderer categoryList() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String periodId = getRequest().getParameter("periodId");
		String categoryId = getRequest().getParameter("categoryId");
		/*int start = 0;
		String pageIndex = getRequest().getParameter("pageIndex");
		if (StringUtils.isNotBlank(pageIndex))
		{
			try
			{
				start = Integer.parseInt(pageIndex);
			}
			catch (NumberFormatException e) {
			}
		}
		int pageSize = RuleConfig.getInstance().getPageSize(Constant.PAGE_SIZE);
		start = start * pageSize;
		int end = start + pageSize;*/
		int start = 0;
		int end = Constant.MAX_PAGE_SIZE;
		List<JSONObject> collectionList = ShouService.queryCollections(new BigDecimal(categoryId), new BigDecimal(periodId), start, end);
		if (CollectionUtils.isEmpty(collectionList))
		{
			collectionList = new ArrayList<JSONObject>();
		}
		out.put("category", SysConfigCache.getCollectionCategory(new BigDecimal(categoryId)));
		out.put("period", SysConfigCache.getCollectionPeriod(new BigDecimal(periodId)));
		out.put("collectionList", collectionList);
		//out.put("last_page", collectionList.size() >= pageSize ? true : false);
        return new TemplateRenderer("/html/shou/cate-list.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/friendDynamic")
	public Renderer friendDynamic() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		int pageSize = RuleConfig.getInstance().getPageSize(Constant.PAGE_SIZE);
		//List<JSONObject> dynamicList = ShouService.queryDynamicsList(0, pageSize);
		List<JSONObject> dynamicList = ShouService.queryFriendDynamicsList(getSessionUser().getId(), 0, pageSize);
		out.put("user_name", getSessionUser().getUser_name());
		out.put("user_id", getSessionUser().getId());
		out.put("source_type", Constant.COMMENT_SOURCE_TYPE_DYNAMIC);
		if (dynamicList != null)
		{
			out.put("last_page", dynamicList.size() < pageSize ? true : false);
		}
		else
		{
			out.put("last_page",true);
		}
		
		//如果没有好友动态，则展示自己的个人动态
		if (CollectionUtils.isEmpty(dynamicList))
		{
			dynamicList = ShouService.queryUserDynamicsList(getSessionUser().getId());
		}
		out.put("dynamicList", dynamicList);
        return new TemplateRenderer("/html/shou/dynamic.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/queryFriendDynamic")
	public Renderer queryFriendDynamic() throws Exception
	{
		JSONObject result = new JSONObject();
		String pageIndex = getRequest().getParameter("pageIndex");
		int pIndex = Integer.parseInt(pageIndex);
		int pageSize = RuleConfig.getInstance().getPageSize(Constant.PAGE_SIZE);
		//List<JSONObject> dynamicList = ShouService.queryDynamicsList((pIndex-1) * pageSize, pIndex * pageSize);
		List<JSONObject> dynamicList = ShouService.queryFriendDynamicsList(getSessionUser().getId(), (pIndex-1) * pageSize, pIndex * pageSize);
		JSONArray jsonArray = new JSONArray();
		if (dynamicList != null)
		{
			result.put("last_page", dynamicList.size() < pageSize ? true : false);
			for(int i = 0; i < dynamicList.size(); i++)
			{
				jsonArray.add(dynamicList.get(i));
			}
		}
		else
		{
			result.put("last_page",true);
		}
		result.put("dynamicList", jsonArray);
		writerObjToPage(result);
        return null;
	}
	
	@Mapping("/userInfo")
	public Renderer userInfo() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject userJson = getUserInfo(id);
		List<UserFansBean> ufBeanList = ShouService.queryUserFansBean(new BigDecimal(id), getSessionUser().getId());
		if(CollectionUtils.isNotEmpty(ufBeanList))
		{
			out.put("isAdded", true);
		}
		else
		{
			out.put("isAdded", false);
		}
		if (getSessionUser().getId().equals(new BigDecimal(id)))
		{
			out.put("isOwen", true);
		}
		out.put("user", userJson);
        return new TemplateRenderer("/html/shou/user-info.html" , Constant.DEFAULT_MODULE_STRING , out);
	}

	private JSONObject getUserInfo(String id) throws Exception {
		FavUserBean favUserBean = UserService.queryUserById(new BigDecimal(id));
		JSONObject userJson = JsonUtil.objToJSONObject(favUserBean);
		int fans = UserService.queryUserFansTotal(new BigDecimal(id));
		int attentions = UserService.queryUserAttentionTotal(new BigDecimal(id));
		int collections = ShouService.queryUserCollectionTotal(new BigDecimal(id));
		int dynamics = ShouService.queryUserDynamicTotal(new BigDecimal(id));
		int comments = ShouService.queryUserCommentTotal(new BigDecimal(id));
		userJson.put("fans", fans);
		userJson.put("attentions", attentions);
		userJson.put("collections", collections);
		userJson.put("dynamics", dynamics);
		userJson.put("comments", comments);
		List<String> titles = SysConfigCache.getUserTitle(favUserBean.getUser_title());
		userJson.put("titles", titles);
		return userJson;
	}
	
	@Mapping("/shareUserInfo")
	public Renderer shareUserInfo() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject userJson = getUserInfo(id);
		out.put("isAdded", true);
		out.put("isOwen", true);
		out.put("user", userJson);
        return new TemplateRenderer("/html/shou/user-info.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/userFans")
	public Renderer userFans() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		FavUserBean user = UserService.queryUserById(new BigDecimal(id));
		List<FavUserBean> favUserList = ShouService.queryUserFans(new BigDecimal(id));
		out.put("favUserList", favUserList);
		out.put("name", user.getUser_name());
		out.put("type", "fan");
        return new TemplateRenderer("/html/shou/user-fans.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/userAttentions")
	public Renderer userAttentions() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		FavUserBean user = UserService.queryUserById(new BigDecimal(id));
		List<FavUserBean> favUserList = ShouService.queryUserAttentions(new BigDecimal(id));
		out.put("favUserList", favUserList);
		out.put("name", user.getUser_name());
		out.put("type", "attention");
        return new TemplateRenderer("/html/shou/user-fans.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/searchFriend")
	public Renderer searchFriend() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		String userName = getRequest().getParameter("userName");
		List<FavUserBean> favUserList = ShouService.queryUserAttentions(getSessionUser().getId(), userName);
		out.put("favUserList", favUserList);
		out.put("collectionId", id);
        return new TemplateRenderer("/html/home/search.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/queryFriend")
	public Renderer queryFriend() throws Exception
	{
		String userName = getRequest().getParameter("userName");
		List<FavUserBean> favUserList = ShouService.queryUserAttentions(getSessionUser().getId(), userName);
		JSONArray userList = new JSONArray();
		JSONObject jsonObj = null;
		for(FavUserBean fub : favUserList)
		{
			jsonObj = new JSONObject();
			jsonObj.put("id", fub.getId());
			jsonObj.put("avatar", fub.getAvatar());
			jsonObj.put("user_name", fub.getUser_name());
			jsonObj.put("signature", fub.getSignature() == null ? "" : fub.getSignature());
			userList.add(jsonObj);
		}
		writerObjToPage(userList);
        return null;
	}
	
	
	@Mapping("/addAttention")
	public Renderer addAttention()
	{
		JSONObject result = new JSONObject();
		String msg = "加关注失败";
		boolean optResult = false;
		String id = getRequest().getParameter("id");
		FavUserBean user = getSessionUser();
		try {
			List<UserFansBean> ufBeanList = ShouService.queryUserFansBean(new BigDecimal(id), user.getId());
			if (CollectionUtils.isEmpty(ufBeanList))
			{
				UserFansBean userFansBean = new UserFansBean();
				userFansBean.setId(IdCreaterTool.getUserFansId());
				userFansBean.setUser_id(new BigDecimal(id));
				userFansBean.setFan_id(user.getId());
				userFansBean.setConcern_time(new Date());
				userFansBean.setInsert_id(user.getId());
				userFansBean.setUpdate_id(user.getId());
				optResult = ShouService.addUserFan(userFansBean);
				if (optResult)
				{
					msg = "加关注成功";
				}
			}
			else
			{
				msg = "已关注此用户";
			}
		} catch (Exception e) {
			logger.error("addAttention user_id[" + id + "], fan_id["
					+ user.getId() + "] error", e);
		}
		result.put("result", optResult);
		result.put("msg", msg);
		writerObjToPage(result);
        return null;
	}
	
	@Mapping("/deleteAttention")
	public Renderer deleteAttention()
	{
		JSONObject result = new JSONObject();
		String msg = "取消关注成功";
		boolean optResult = true;
		String id = getRequest().getParameter("id");
		FavUserBean user = getSessionUser();
		try {
			ShouService.deleteUserFansBean(new BigDecimal(id), user.getId());
		} catch (Exception e) {
			msg = "取消关注失败";
			optResult = false;
			logger.error("deleteAttention user_id[" + id + "], fan_id["
					+ user.getId() + "] error", e);
		}
		result.put("result", optResult);
		result.put("msg", msg);
		writerObjToPage(result);
        return null;
	}
	
	@Mapping("/userCollectionList")
	public Renderer userCollectionList() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		FavUserBean user = UserService.queryUserById(new BigDecimal(id));
		List<JSONObject> collectionList = ShouService.queryUserCollections(new BigDecimal(id));
		if (CollectionUtils.isEmpty(collectionList))
		{
			collectionList = new ArrayList<JSONObject>();
		}
		out.put("collectionList", collectionList);
		out.put("name", user.getUser_name());
        return new TemplateRenderer("/html/shou/user-list.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/ViewImage")
	public Renderer ViewImage() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject collectionDetail = ShouService.queryCollectionDetail(new BigDecimal(id));
		out.put("collectionDetail", collectionDetail);
		return new TemplateRenderer("html/shou/view_image.html", Constant.DEFAULT_MODULE_STRING, out);
	}
	
	@Mapping("/toComment")
	public Renderer toComment() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		String type = getRequest().getParameter("source_type");
		List<JSONObject> commentList = ShouService.queryComments(new BigDecimal(id), getSessionUser().getId());
		out.put("commentList", commentList);
		out.put("source_type", type);
		out.put("id", id);
        return new TemplateRenderer("/html/shou/comment.html" , Constant.DEFAULT_MODULE_STRING , out);
	}

	@Mapping("/toCommentReply")
	public Renderer toCommentReply() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String commentId = getRequest().getParameter("id");
		String sourceId = getRequest().getParameter("sourceId");
		String userId = getRequest().getParameter("userId");
		String type = getRequest().getParameter("source_type");
		List<JSONObject> commentList = ShouService.queryCommentReplys(new BigDecimal(commentId));
		out.put("commentList", commentList);
		out.put("source_type", type);
		out.put("id", sourceId);
		out.put("reply_id", commentId);
		out.put("reply_user_id", userId);
        return new TemplateRenderer("/html/shou/comment.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/addComment")
	public Renderer addComment() throws Exception
	{
		String id = getRequest().getParameter("id");
		String sourceType = getRequest().getParameter("source_type");
		String content = getRequest().getParameter("content");
		String reply_id = getRequest().getParameter("reply_id");
		String reply_user_id = getRequest().getParameter("reply_user_id");
		CommentBean ccBean = new CommentBean();
		boolean result = false;
		JSONObject rltJson = new JSONObject();
		if (Constant.COMMENT_SOURCE_TYPE_COLLECTION.equalsIgnoreCase(sourceType)
				|| Constant.COMMENT_SOURCE_TYPE_DYNAMIC.equalsIgnoreCase(sourceType))
		{
			ccBean.setId(IdCreaterTool.getCommentId());
			ccBean.setSource_id(new BigDecimal(id));
			ccBean.setComment_content(content);
			ccBean.setFriend_id(getSessionUser().getId());
			ccBean.setSource_type(sourceType);
			ccBean.setComment_time(new Date());
			ccBean.setInsert_id(getSessionUser().getId());
			ccBean.setUpdate_id(getSessionUser().getId());
			if (StringUtils.isBlank(reply_id))
			{
				ccBean.setType(Constant.COMMENT_TYPE_COMMENT);
			}
			else
			{
				ccBean.setType(Constant.COMMENT_TYPE_REPLY);
				ccBean.setReply_id(new BigDecimal(reply_id));
				if (StringUtils.isNotBlank(reply_user_id))
				{
					ccBean.setFriend_id(new BigDecimal(reply_user_id));
				}
			}
			result = ShouService.addComment(ccBean);
			rltJson.put("comment_id", ccBean.getId());
		}
		rltJson.put("result", result);
		
		writerObjToPage(rltJson);
        return null;
	}
	
	@Mapping("/replyDetail")
	public Renderer replyDetail() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		String sourceType = getRequest().getParameter("source_type");
		JSONObject comment = ShouService.queryCommentDetail(new BigDecimal(id));
		out.put("comment", comment);
		JSONObject like = ShouService.queryCommentLike(new BigDecimal(id), getSessionUser().getId());
		if (like == null)
		{
			out.put("isVote", false);
		}
		else
		{
			out.put("isVote", true);
		}
		int commentTotal  = ShouService.queryCommentReplyTotal(new BigDecimal(id));
		out.put("like", like);
		out.put("source_type", sourceType);
		out.put("commentTotal", commentTotal);
        return new TemplateRenderer("/html/shou/reply.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/toAllComments")
	public Renderer toComments() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		String type = getRequest().getParameter("source_type");
		List<JSONObject> commentList = ShouService.queryComments(new BigDecimal(id), getSessionUser().getId());
		out.put("commentList", commentList);
		out.put("source_type", type);
		out.put("id", id);
        return new TemplateRenderer("/html/shou/show-reply.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/commentLike")
	public Renderer addCommentLike() throws Exception
	{
		String commentsId = getRequest().getParameter("id");
		String sourceType = getRequest().getParameter("source_type");
		String type = getRequest().getParameter("type");
		CommentLikeBean clBean = new CommentLikeBean();
		boolean result = false;
		JSONObject rltJson = new JSONObject();
		if (Constant.COMMENT_SOURCE_TYPE_COLLECTION.equalsIgnoreCase(sourceType)
				|| Constant.COMMENT_SOURCE_TYPE_DYNAMIC.equalsIgnoreCase(sourceType))
		{
			clBean.setId(IdCreaterTool.getCommentLikeId());
			clBean.setComments_id(new BigDecimal(commentsId));
			clBean.setFriend_id(getSessionUser().getId());
			clBean.setType(type);
			clBean.setSource_type(sourceType);
			clBean.setInsert_id(getSessionUser().getId());
			clBean.setUpdate_id(getSessionUser().getId());
			result = ShouService.addCommentLike(clBean);
		}
		rltJson.put("result", result);
		
		writerObjToPage(rltJson);
        return null;
	}
	
	@Mapping("/addDynamicLike")
	public Renderer addDynamicLike() throws Exception
	{
		String dynamicId = getRequest().getParameter("id");
		boolean result = false;
		JSONObject rltJson = new JSONObject();
		ShouService.addDynamicLike(new BigDecimal(dynamicId), getSessionUser().getId());
		rltJson.put("result", result);
		writerObjToPage(rltJson);
        return null;
	}

	@Mapping("/showUserReply")
	public Renderer showUserReply() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		String userName = "";
		List<JSONObject> replyList = ShouService.queryUserReply(new BigDecimal(id));
		if (CollectionUtils.isNotEmpty(replyList))
		{
			userName = replyList.get(0).getString("user_name");
		}
		else
		{
			FavUserBean favUserBean = UserService.queryUserById(new BigDecimal(id));
			if (favUserBean != null)
			{
				userName = favUserBean.getUser_name();
			}
		}
		out.put("replyList", replyList);
		out.put("id", id);
		out.put("user_name", userName);
        return new TemplateRenderer("/html/shou/user-reply.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/friendSet")
	public Renderer friendSet() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String friendId = getRequest().getParameter("id");
		FavUserSetBean favUserSetBean =  ShouService.queryFavUserSetBean(getSessionUser().getId(), new BigDecimal(friendId));
		JSONObject setJson = new JSONObject();
		if (favUserSetBean != null)
		{
			setJson = JsonUtil.objToJSONObject(favUserSetBean);
		}
		out.put("id", friendId);
		out.put("set", setJson);
        return new TemplateRenderer("/html/shou/setting.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/userDynamic")
	public Renderer userDynamic() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		String userId = getRequest().getParameter("id");
		FavUserBean fuBean = UserService.queryUserById(new BigDecimal(userId));
		List<JSONObject> dynamicList = ShouService.queryUserDynamicsList(new BigDecimal(userId));
		out.put("dynamicList", dynamicList);
		out.put("name", fuBean.getUser_name());
		out.put("user_name", getSessionUser().getUser_name());
		out.put("user_id", getSessionUser().getId());
		out.put("source_type", Constant.COMMENT_SOURCE_TYPE_DYNAMIC);
        return new TemplateRenderer("/html/shou/user-dynamic.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/addBlacklist")
	public Renderer addBlacklist()
	{
		String friendId = getRequest().getParameter("id");
		boolean result = false;
		JSONObject rltJson = new JSONObject();
		try {
			result = ShouService.addBlacklist(getSessionUser().getId(), new BigDecimal(friendId));
			if(!result)
			{
				rltJson.put("msg", "添加黑名单失败");
			}
		} catch (FavRuntimeException e) {
			result = false;
			rltJson.put("msg", e.getMessage());
		} catch (Exception e) {
			logger.error("addBlacklist error.", e);
			result = false;
			rltJson.put("msg", "添加黑名单失败");
		}
		rltJson.put("result", result);
		writerObjToPage(rltJson);
        return null;
	}
	
	@Mapping("/modifyFavUserSet")
	public Renderer modifyFavUserSet() throws Exception
	{
		boolean reulst = true;
		String friendId = getRequest().getParameter("friendId");
		String blockMsg = getRequest().getParameter("block_msg");
		String blockDynamic = getRequest().getParameter("block_dynamic");
		String blockReply = getRequest().getParameter("block_reply");
		String blockComment = getRequest().getParameter("block_comment");
		FavUserSetBean favUserSetBean = ShouService.queryFavUserSetBean(getSessionUser().getId(), new BigDecimal(friendId));
		if (null == favUserSetBean)
		{
			//新增
			favUserSetBean = new FavUserSetBean();
			favUserSetBean.setId(IdCreaterTool.getFavUserSetId());
			favUserSetBean.setUser_id(getSessionUser().getId());
			favUserSetBean.setFriend_id(new BigDecimal(friendId));
			favUserSetBean.setBlock_comment(blockComment);
			favUserSetBean.setBlock_dynamic(blockDynamic);
			favUserSetBean.setBlock_msg(blockMsg);
			favUserSetBean.setBlock_reply(blockReply);
			favUserSetBean.setInsert_id(getSessionUser().getId());
			favUserSetBean.setUpdate_id(getSessionUser().getId());
			reulst = ShouService.addFavUserSetBean(favUserSetBean);
		}
		else
		{
			if (!blockMsg.equals(favUserSetBean.getBlock_msg()) 
					|| !blockDynamic.equals(favUserSetBean.getBlock_dynamic())
					|| !blockReply.equals(favUserSetBean.getBlock_reply())
					|| !blockComment.equals(favUserSetBean.getBlock_comment()))
			{
				//修改
				favUserSetBean = new FavUserSetBean();
				favUserSetBean.setUser_id(getSessionUser().getId());
				favUserSetBean.setFriend_id(new BigDecimal(friendId));
				favUserSetBean.setBlock_comment(blockComment);
				favUserSetBean.setBlock_dynamic(blockDynamic);
				favUserSetBean.setBlock_msg(blockMsg);
				favUserSetBean.setBlock_reply(blockReply);
				favUserSetBean.setUpdate_id(getSessionUser().getId());
				favUserSetBean.setUpdate_date(new Date());
				reulst = ShouService.updateFavUserSetBean(favUserSetBean);
			}
		}
		JSONObject rltJson = new JSONObject();
		rltJson.put("result", reulst);
		writerObjToPage(rltJson);
        return null;
	}
	
	@Mapping("/topComment")
	public Renderer topComment()
	{
		String commentId = getRequest().getParameter("id");
		String sourceType = getRequest().getParameter("sourceType");
		String sourceId = getRequest().getParameter("sourceId");
		String friendId = getRequest().getParameter("friendId");
		boolean reulst = false;
		try
		{
			CommentTopBean commentTopBean = new CommentTopBean();
			commentTopBean.setComment_id(new BigDecimal(commentId));
			commentTopBean.setFriend_id(new BigDecimal(friendId));
			commentTopBean.setId(IdCreaterTool.getCommentTopId());
			commentTopBean.setInsert_id(getSessionUser().getId());
			commentTopBean.setSource_id(new BigDecimal(sourceId));
			commentTopBean.setSource_type(sourceType);
			commentTopBean.setTop_time(new Date());
			commentTopBean.setUpdate_id(getSessionUser().getId());
			reulst = ShouService.addCommentTopBean(commentTopBean);
		}
		catch (Exception e) {
			logger.error("topComment error", e);
		}
		JSONObject rltJson = new JSONObject();
		rltJson.put("result", reulst);
		writerObjToPage(rltJson);
        return null;
	}
	
	@Mapping("/inviteComment")
	public Renderer inviteComment()
	{
		JSONObject rltJson = new JSONObject();
		rltJson.put("result", false);
		String collectionId = getRequest().getParameter("collectionId");
		String friendId = getRequest().getParameter("friendId");
		String title = "收藏品";
		FavUserBean user = getSessionUser();
		try {
			CollectionBean collection = ShouService.queryCollection(new BigDecimal(collectionId));
			if (collection != null)
			{
				title = collection.getTitle();
				String content = user.getUser_name() + "邀请您对【<a href='collectionDetail?id=" + collectionId + "' style='color: #8A0001;'>"
				    + title + "</a>】发表回复，点击上述标题即可跳转。";
				boolean result = HomeService.addMessage(new BigDecimal(friendId), user.getId(), content);
				rltJson.put("result", result);
			}
			else
			{
				rltJson.put("msg", "收藏品[" + collectionId + "]不存在");
			}
			
		} catch (Exception e) {
			logger.error("inviteComment error", e);
		}
		writerObjToPage(rltJson);
		return null;
	}
}
