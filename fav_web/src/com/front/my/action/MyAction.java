package com.front.my.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.express.portal.Mapping;
import org.express.portal.MultipartHttpServletRequest;
import org.express.portal.PathUtil;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.TaskPointsConfigBean;
import com.front.my.service.MyService;
import com.front.shou.service.ShouService;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.common.base.GenericPageAction;
import com.front.web.util.JsonUtil;
import com.front.web.util.MD5;

public class MyAction extends GenericPageAction{
	private final static Logger logger = LoggerFactory.getLogger(MyAction.class);

	@Mapping("/myIndex")
	public Renderer goMyIndex() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
		FavUserBean favUserBean = UserService.queryUserById(user.getId());
		JSONObject userJson = JsonUtil.objToJSONObject(favUserBean);
		int fans = UserService.queryUserFansTotal(user.getId());
		int attentions = UserService.queryUserAttentionTotal(user.getId());
		int collections = ShouService.queryUserCollectionTotal(user.getId());
		List<String> titles = SysConfigCache.getUserTitle(favUserBean.getUser_title());
		userJson.put("titles", titles);
		userJson.put("fans", fans);
		userJson.put("attentions", attentions);
		userJson.put("collections", collections);
		out.put("user", userJson);
        return new TemplateRenderer("/html/my/index.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/mySet")
	public Renderer mySet() throws Exception
	{
		Map<String, Object> out = getOutputMap();
        return new TemplateRenderer("/html/my/setting.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/logout")
	public Renderer logout() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		getSession().invalidate();
		out.put("logout", "Y");
        return new TemplateRenderer("/html/user/login.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/toChangePassword")
	public Renderer toChangePassword() throws Exception
	{
		Map<String, Object> out = getOutputMap();
        return new TemplateRenderer("/html/my/password.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/changePassword")
	public Renderer changePassword() throws Exception
	{
		JSONObject json = new JSONObject();
		String oldPwd = getRequest().getParameter("oldPwd");
		String newPwd = getRequest().getParameter("newPwd");
		String confrimPwd = getRequest().getParameter("confrimPwd");
		boolean result = true;
		if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd)
				|| StringUtils.isBlank(confrimPwd)) {
			json.put("msg", "密码不能为空");
			result = false;
		}
		if (result)
		{
			if (newPwd.equals(confrimPwd))
			{
				FavUserBean favUserBean = MyService.queryUserByPwd(oldPwd, getSessionUser().getId());
				if (favUserBean == null)
				{
					json.put("msg", "原密码不正确");
					result = false;
				}
				else
				{
					result = UserService.updateUserPassword(MD5.GetMD5Code(newPwd), getSessionUser().getId());
					if (!result)
					{
						json.put("msg", "修改密码失败");
					}
				}
			}
			else
			{
				json.put("msg", "新密码与确认密码不一致");
				result = false;
			}
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	@Mapping("/myReply")
	public Renderer myReply() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		List<JSONObject> replyList = ShouService.queryUserReply(getSessionUser().getId());
		out.put("replyList", replyList);
        return new TemplateRenderer("/html/my/reply.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/seeTask")
	public Renderer seeTask() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		Map<String, TaskPointsConfigBean> taskConfigMap = SysConfigCache.getTaskPointsConfigMap();
		List<JSONObject> taskList = MyService.queryUserDayTask(getSessionUser().getId());
		JSONObject collectionTask = new JSONObject();
		JSONObject dynamicTask = new JSONObject();
		JSONObject likeTask = new JSONObject();
		JSONObject avatarTask = new JSONObject();
		Map<String, String> completedTaskMap = new HashMap<String, String>();
		if(CollectionUtils.isNotEmpty(taskList))
		{
			for(JSONObject obj : taskList)
			{
				completedTaskMap.put(obj.getString("task_name"),"");
				if (Constant.TASK_TYPE_COLLECTION.equals(obj.getString("task_name")))
				{
					collectionTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_COLLECTION).getPoints());
					collectionTask.put("isCompleted", true);
				}
				else if(Constant.TASK_TYPE_DYNAMIC.equals(obj.getString("task_name")))
				{
					dynamicTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_DYNAMIC).getPoints());
					dynamicTask.put("isCompleted",  true);
				}
				else if(Constant.TASK_TYPE_LIKE.equals(obj.getString("task_name")))
				{
					likeTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_LIKE).getPoints());
					likeTask.put("isCompleted",  true);
				}
				else if(Constant.TASK_TYPE_AVATAR.equals(obj.getString("task_name")))
				{
					avatarTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_AVATAR).getPoints());
					avatarTask.put("isCompleted",  true);
				}
			}
			
		}
		Iterator<String> iterator = taskConfigMap.keySet().iterator();
		while(iterator.hasNext())
		{
			String taskName = iterator.next();
			if (!completedTaskMap.containsKey(taskName))
			{
				if (Constant.TASK_TYPE_COLLECTION.equals(taskName))
				{
					collectionTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_COLLECTION).getPoints());
					collectionTask.put("isCompleted", false);
				}
				else if(Constant.TASK_TYPE_DYNAMIC.equals(taskName))
				{
					dynamicTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_DYNAMIC).getPoints());
					dynamicTask.put("isCompleted", false);
				}
				else if(Constant.TASK_TYPE_LIKE.equals(taskName))
				{
					likeTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_LIKE).getPoints());
					likeTask.put("isCompleted", false);
				}
				else if(Constant.TASK_TYPE_AVATAR.equals(taskName))
				{
					avatarTask.put("point", taskConfigMap.get(Constant.TASK_TYPE_AVATAR).getPoints());
					avatarTask.put("isCompleted", false);
				}
			}
		}
		out.put("taskCollection", collectionTask);
		out.put("dynamicTask", dynamicTask);
		out.put("likeTask", likeTask);
		out.put("avatarTask", avatarTask);
        return new TemplateRenderer("/html/my/task.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/myMessageSet")
	public Renderer myMessageSet() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
		FavUserBean favUserBean = UserService.queryUserById(user.getId());
		String msgSet = Constant.PERSONAL_MSG_SET_ALL;
		if (favUserBean != null)
		{
			msgSet = favUserBean.getPersonal_msg_set();
		}
		out.put("msg_set", msgSet);
        return new TemplateRenderer("/html/my/message.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/updateUserMsgSet")
	public Renderer updateUserMsgSet()
	{
		JSONObject json = new JSONObject();
		String msgSet = getRequest().getParameter("msgSet");
		boolean result = false;
		try {
			result = MyService.updateUserMsgSet(getSessionUser().getId(), msgSet);
		} catch (Exception e) {
			logger.error("updateUserMsgSet error", e);
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	@Mapping("/myBlacklist")
	public Renderer myBlacklist() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
		List<JSONObject> userBlacklists = MyService.queryUserBlacklist(user.getId());
		out.put("userBlacklists", userBlacklists);
        return new TemplateRenderer("/html/my/blacklist.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/deleteBlacklist")
	public Renderer deleteBlacklist()
	{
		JSONObject json = new JSONObject();
		String id = getRequest().getParameter("id");
		boolean result = false;
		try {
			result = MyService.deleteBlacklist(new BigDecimal(id));
		} catch (Exception e) {
			logger.error("deleteBlacklist error", e);
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	@Mapping("/privateCollection")
	public Renderer privateCollection() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		List<JSONObject> collectionList = MyService.queryCollectionsByUserId(getSessionUser().getId());
		out.put("collectionList", collectionList);
        return new TemplateRenderer("/html/my/private.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/myFavorites")
	public Renderer myFavorites() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		List<JSONObject> collectionList = MyService.queryUserFavorites(getSessionUser().getId());
		out.put("collectionList", collectionList);
        return new TemplateRenderer("/html/my/favorites.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/applyRecord")
	public Renderer applyRecord() throws Exception
	{
		Map<String, Object> out = getOutputMap();
		List<JSONObject> applyList = MyService.queryUserApplyRecord(getSessionUser().getId());
		out.put("applyList", applyList);
        return new TemplateRenderer("/html/my/apply.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/modifySignature")
	public Renderer modifySignature()
	{
		JSONObject json = new JSONObject();
		String signature = getRequest().getParameter("signature");
		boolean result = false;
		try {
			result = MyService.modifySignature(getSessionUser().getId(), signature);
		} catch (Exception e) {
			logger.error("modifySignature error", e);
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	@Mapping("/modifyUserName")
	public Renderer modifyUserName()
	{
		JSONObject json = new JSONObject();
		String name = getRequest().getParameter("name");
		boolean result = false;
		try {
			FavUserBean favUserBean = new FavUserBean();
			favUserBean.setId(getSessionUser().getId());
			favUserBean.setUser_name(name);
			favUserBean.setUpdate_date(new Date());
			favUserBean.setUpdate_id(getSessionUser().getId());
			result = UserService.updateFavUser(favUserBean);
		} catch (Exception e) {
			logger.error("modifyUserName error", e);
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	
	
	@Mapping("/changeAvatar")
	public Renderer changeAvatar()
	{
		JSONObject json = new JSONObject();
		boolean result = false;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) getRequest();
		//上传图片集合
		List<FileItem> fileItems = multipartRequest.getUpFileData();
		for (FileItem fileItem : fileItems)
		{
			String fileName = fileItem.getName();
			if (StringUtils.isBlank(fileName))
			{
				continue;
			}
			String uuid = "atr_" + UUID.randomUUID().toString(); // 重命名文件
			File file = new File(PathUtil.getUploadImgPath()).getAbsoluteFile();
			if (!file.exists()) {
				file.mkdirs();
			}
			int pos = fileName.lastIndexOf(".");// 取图片文件格式
			if (pos > 0) {
				fileName = fileName.substring(pos);
			}
			String filepathString = file.getPath() + "/" + uuid  + fileName;
			String imgUrl = PathUtil.getUploadImgFileName() + "/" + uuid  + fileName;
			try {
				BigDecimal userId = getSessionUser().getId();
				FavUserBean fuBean = UserService.queryUserById(userId);
				// 写到磁盘
				fileItem.write(new File(filepathString));
				FavUserBean favUserBean = new FavUserBean();
				favUserBean.setId(userId);
				favUserBean.setAvatar(imgUrl);
				favUserBean.setUpdate_date(new Date());
				favUserBean.setUpdate_id(userId);
				boolean optResult = UserService.updateFavUser(favUserBean);
				if (optResult)
				{
					//更新头像的积分暂时屏蔽
					/**if (!UserService.isExistUserPointsRecord(userId, Constant.TASK_TYPE_AVATAR))
					{
						UserService.updateUserPoints(userId, Constant.TASK_TYPE_AVATAR);
						UserService.addUserPointsRecord(userId, Constant.TASK_TYPE_AVATAR);
					}*/
					if (fuBean != null)
					{
						//如果是默认头像则不需要删除用户头像
						if (!Constant.DEFAULT_PROFLE_PHOTO.endsWith(fuBean.getAvatar()) && StringUtils.isNotBlank(fuBean.getAvatar()))
						{
							String tAvatar = fuBean.getAvatar();
							tAvatar = tAvatar.replace(PathUtil.getUploadImgFileName(), "");
							File oldImg = new File(file.getPath() + tAvatar);
							if (oldImg.exists())
							{
								oldImg.delete();
							}
						}						
					}
				}
			} catch (Exception e) {
				logger.error("changeAvatar error", e);
			}
		}
		json.put("result", result);
		writerObjToPage(json);
        return null;
	}
	
	
}
