package com.front.user.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.express.portal.Mapping;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.db.bean.CollectionCategoryBean;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.SmsBean;
import com.front.user.bean.VerifyCodeBean;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.common.base.GenericPageAction;
import com.front.web.util.IdCreaterTool;
import com.front.web.util.MD5;
import com.front.web.util.ZTSmsSenderClient;

public class UserAction extends GenericPageAction{

	private final static Logger logger = LoggerFactory.getLogger(UserAction.class);
	
	
	@Mapping("/login")
	public Renderer gotoLogin()
	{
		 Map<String, Object> out = getOutputMap();
         return new TemplateRenderer("/html/user/login.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/forgetPassword")
	public Renderer gotoForgetPassword()
	{
		Map<String, Object> out = getOutputMap();
        return new TemplateRenderer("/html/user/forget-password-phone.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/forgetPwdTwo")
	public Renderer forgetPwdTwo()
	{
		String phone = getRequest().getParameter("phone");
		Map<String, Object> out = getOutputMap();
		out.put("phone", phone);
        return new TemplateRenderer("/html/user/forget-password-valid.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/checkIsExistAccount")
	public Renderer checkIsExistAccount()
	{
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", false);
		String phone = getRequest().getParameter("phone");
		if (StringUtils.isNotBlank(phone))
		{
			try {
				FavUserBean favUserBean = UserService.queryUserByAccount(phone);
				if (favUserBean != null && Constant.USE_FLAG.compareTo(favUserBean.getUse_flag()) == 0)
				{
					resultJson.put("result", true);
					resultJson.put("msg", "帐号名称已存在");
				}
				else
				{
					resultJson.put("msg", "帐号名称不存在，请检查帐号名称是否有误");
				}
			} catch (Exception e) {
				logger.error("checkIsExistAccount error." ,e);
				resultJson.put("msg", "帐号名称不存在，请检查帐号名称是否有误");
			}
		}
		else
		{
			resultJson.put("msg", "帐号名称不能为空");
		}
		writerObjToPage(resultJson);
        return null;
	}
	
	@Mapping("/checkAccountSendCode")
	public Renderer checkAccountSendCode()
	{
		return getResetVerifyCode();
	}
	
	@Mapping("/gotoPasswordReset")
	public Renderer gotoPasswordReset()
	{
		String phone = getRequest().getParameter("phone");
		String verifyCode = getRequest().getParameter("verifyCode");
		Map<String, Object> out = getOutputMap();
		out.put("phone", phone);
		out.put("verifyCode", verifyCode);
        return new TemplateRenderer("/html/user/forget-password-reset.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/resetPassword")
	public Renderer resetPassword()
	{
		String phone = getRequest().getParameter("phone");
		String verifyCode = getRequest().getParameter("verifyCode");
		String password = getRequest().getParameter("password");
		String confrimPassword = getRequest().getParameter("confrimPassword");
		JSONObject resultJson = new JSONObject();
		boolean isReset = true;
		if (StringUtils.isBlank(password)|| StringUtils.isBlank(confrimPassword))
		{
			isReset = false;
			resultJson.put("msg", "密码、确认密码不能为空");
		}
		
		if (isReset && !password.equals(confrimPassword))
		{
			isReset = false;
			resultJson.put("msg", "密码与确认密码不一致");
		}
		
		if (isReset && StringUtils.isBlank(phone))
		{
			isReset = false;
			resultJson.put("msg", "帐户名称已丢失，请重新输入帐户名称");
		}
		
		if (isReset && StringUtils.isBlank(verifyCode))
		{
			isReset = false;
			resultJson.put("msg", "验证码已丢失，请重新获取验证码");
		}

		if (isReset)
		{
			VerifyCodeBean vcBean = (VerifyCodeBean)getSession().getAttribute(Constant.SMS_VERIFY_CODE);
			
			if (vcBean != null)
			{
				if (!phone.equals(vcBean.getPhone()) || !verifyCode.equals(vcBean.getVerifyCode()))
				{
					isReset = false;
					resultJson.put("msg", "验证码和已帐户名称不正确，请重新输入帐户名称重置密码");
				}
			}
			else
			{
				isReset = false;
				resultJson.put("msg", "请重新获取验证码");
			}
		}
		
		if (isReset)
		{
			try {
				FavUserBean favUserBean = UserService.queryUserByAccount(phone);
				if (favUserBean != null && Constant.USE_FLAG.compareTo(favUserBean.getUse_flag()) == 0)
				{
					boolean isUpdate = UserService.updateUserPassword(MD5.GetMD5Code(password), phone);
					if (isUpdate)
					{
						resultJson.put("msg", "密码重置成功");
					}
					else
					{
						isReset = false;
						resultJson.put("msg", "密码重置失败");
					}
				}
				else
				{
					isReset = false;
					resultJson.put("msg", "帐号不存在，请检查手机号是否正确");
				}
			} catch (Exception e) {
				isReset = false;
				resultJson.put("msg", "密码重置失败");
			}
		}
		resultJson.put("result", isReset);
		writerObjToPage(resultJson);
        return null;
	}
	
	@Mapping("/checkVerifyCode")
	public Renderer checkVerifyCode()
	{
		JSONObject resultJson = new JSONObject();
		String phone = getRequest().getParameter("phone");
		String verifyCode = getRequest().getParameter("verifyCode");
		boolean isPass = true;
		if (isPass && StringUtils.isBlank(verifyCode))
		{
			isPass = false;
			resultJson.put("msg", "验证码不能为空");
		}
		if (isPass && StringUtils.isBlank(phone))
		{
			isPass = false;
			resultJson.put("msg", "手机号不能为空");
		}
		if (isPass)
		{
			VerifyCodeBean vcBean = (VerifyCodeBean)getSession().getAttribute(Constant.SMS_VERIFY_CODE);
			
			if (vcBean != null)
			{
				if (System.currentTimeMillis() - vcBean.getCreateTime() <= vcBean.getTimeInterval())
				{
					if (!verifyCode.equals(vcBean.getVerifyCode()))
					{
						isPass = false;
						resultJson.put("msg", "验证码不正确");
					}
				}
				else
				{
					isPass = false;
					resultJson.put("msg", "验证码已失效，请重新获取验证码");
				}
			}
			else
			{
				isPass = false;
				resultJson.put("msg", "请获取验证码");
			}
		}
		resultJson.put("result", isPass);
	    writerObjToPage(resultJson);
        return null;
	}
	

	/**
	 * 获取注册验证码
	 * @return
	 */
	@Mapping("/getVerifyCode")
	public Renderer getVerifyCode()
	{
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", false);
		String phone = getRequest().getParameter("phone");
		if (StringUtils.isNotBlank(phone))
		{
			try {
				//下发短信验证码
				String verifyCode = UserService.getRandomCode();
				String sendContent = "您的验证码为" + verifyCode + "，验证码有效期为10分钟";
				ZTSmsSenderClient.sendSms(phone, sendContent, "1");
				UserService.addSms(sendContent, phone);
				VerifyCodeBean vcBean = new VerifyCodeBean();
				vcBean.setCreateTime(System.currentTimeMillis());
				vcBean.setPhone(phone);
				vcBean.setVerifyCode(verifyCode);
				//短信验证码设置到session中
				getSession().setAttribute(Constant.SMS_VERIFY_CODE, vcBean);
				resultJson.put("result", true);
				resultJson.put("msg", "验证码已下发，请注意查收");
			} catch (Exception e) {
				logger.error("getVerifyCode error." ,e);
				resultJson.put("msg", "下发验证码失败，请重新获取");
			}
		}
		else
		{
			resultJson.put("msg", "手机号不能为空，请检查手机号");
		}
		writerObjToPage(resultJson);
        return null;
	}
	
	/**
	 * 获取重置密码的验证码
	 * @return
	 */
	@Mapping("/getResetVerifyCode")
	public Renderer getResetVerifyCode()
	{
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", false);
		String phone = getRequest().getParameter("phone");
		if (StringUtils.isNotBlank(phone))
		{
			try {
				FavUserBean favUserBean = UserService.queryUserByAccount(phone);
				if (favUserBean != null && Constant.USE_FLAG.compareTo(favUserBean.getUse_flag()) == 0)
				{
					//下发短信验证码
					String verifyCode =  UserService.getRandomCode();
					String sendContent = "您的验证码为" + verifyCode + "，验证码有效期为10分钟";
					ZTSmsSenderClient.sendSms(phone, sendContent, "1");
					UserService.addSms(sendContent, phone);
					VerifyCodeBean vcBean = new VerifyCodeBean();
					vcBean.setCreateTime(System.currentTimeMillis());
					vcBean.setPhone(phone);
					vcBean.setVerifyCode(verifyCode);
					//短信验证码设置到session中
					getSession().setAttribute(Constant.SMS_VERIFY_CODE, vcBean);
					resultJson.put("result", true);
					resultJson.put("msg", "验证码已下发，请注意查收");
				}
				else
				{
					resultJson.put("msg", "帐号不存在，请检查手机号是否有误");
				}
			} catch (Exception e) {
				logger.error("getVerifyCode error." ,e);
				resultJson.put("msg", "下发验证码失败，请重新获取");
			}
		}
		else
		{
			resultJson.put("msg", "手机号不能为空，请检查手机号");
		}
		writerObjToPage(resultJson);
        return null;
	}
	
	@Mapping("/goRegister")
	public Renderer gotoRegister()
	{
		Map<String, Object> out = getOutputMap();
		List<CollectionCategoryBean> ccbList = SysConfigCache.getCollectionCategorys();
		out.put("categoryList", ccbList);
		JSONObject categoryJson = new JSONObject();
		for (CollectionCategoryBean ccb : ccbList)
		{
			categoryJson.put(ccb.getId().toString(), ccb.getCategory_name());
		}
		out.put("categoryJson", categoryJson);
        return new TemplateRenderer("/html/user/register.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/register")
	public Renderer addFavUser() throws Exception
	{
		String account = getRequest().getParameter("account");
		String verifyCode = getRequest().getParameter("verifyCode");
		String password = getRequest().getParameter("password");
		String confirmPassword = getRequest().getParameter("confirmPassword");
		String categoryIds = getRequest().getParameter("categoryIds");
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", false);
		boolean isAdd = true;
		if (isAdd && StringUtils.isBlank(account))
		{
			isAdd = false; 
			resultJson.put("msg", "账户名称不能为空");
		}
		
		if (isAdd && StringUtils.isBlank(verifyCode))
		{
			isAdd = false; 
			resultJson.put("msg", "验证码不能为空");
		}
		
		if (isAdd && StringUtils.isBlank(password))
		{
			isAdd = false; 
			resultJson.put("msg", "密码不能为空");
		}
		
		if (isAdd && StringUtils.isBlank(confirmPassword))
		{
			isAdd = false; 
			resultJson.put("msg", "确认密码不能为空");
		}
		
		if (isAdd && !password.equals(confirmPassword))
		{
			isAdd = false; 
			resultJson.put("msg", "密码与确认密码不一致");
		}
		
		/*if (isAdd && StringUtils.isBlank(categoryIds))
		{
			isAdd = false; 
			resultJson.put("msg", "请选择最少一个兴趣");
		}*/
		
		if (isAdd)
		{
			//session中短信验证码
			VerifyCodeBean vcBean = (VerifyCodeBean)getSession().getAttribute(Constant.SMS_VERIFY_CODE);
			logger.info("addFavUser SMS_VERIFY_CODE : " + vcBean);
			if (vcBean != null)
			{
				if (System.currentTimeMillis() - vcBean.getCreateTime() <= vcBean.getTimeInterval())
				{
					if (!verifyCode.equals(vcBean.getVerifyCode()))
					{
						isAdd = false; 
						resultJson.put("msg", "验证码不正确");
					}
				}
				else
				{
					isAdd = false; 
					resultJson.put("msg", "验证码已失效，请重新获取验证码");
				}
			}
			else
			{
				SmsBean smsBean = UserService.querySms(account);
				if (smsBean != null)
				{
					vcBean = new VerifyCodeBean();
					if (System.currentTimeMillis() - smsBean.getInsert_date().getTime() <= vcBean.getTimeInterval())
					{
						if (!smsBean.getContent().contains(verifyCode))
						{
							isAdd = false; 
							resultJson.put("msg", "验证码不正确");
						}
					}
					else
					{
						isAdd = false; 
						resultJson.put("msg", "验证码已失效，请重新获取验证码");
					}
				}
				else
				{
					isAdd = false; 
					resultJson.put("msg", "验证码已失效，请重新获取验证码");					
				}
			}
		}
		
		//校验帐号是否唯一
		if (isAdd)
		{
			FavUserBean favUserBean = UserService.queryUserByAccount(account);
			if (favUserBean != null && Constant.USE_FLAG.compareTo(favUserBean.getUse_flag()) == 0)
			{
				isAdd = false; 
				resultJson.put("msg", "账户名称已存在");
			}
			else
			{
				//已暂停的用户，直接恢复他的帐号
				if (favUserBean != null)
				{
					logger.info("addFavUser restore user account ["+ account +"] ...");
					FavUserBean update = new FavUserBean();
					update.setId(favUserBean.getId());
					update.setPassword(MD5.GetMD5Code(password));
					update.setUse_flag(Constant.USE_FLAG);
					update.setUpdate_date(new Date());
					update.setUpdate_id(favUserBean.getId());
					try {
						boolean result = UserService.updateFavUser(update);
						resultJson.put("result", result);
						if (result)
						{
							getSession().setAttribute(Constant.SESSION_USER_INFO, favUserBean);
						}
						else
						{
							resultJson.put("msg", "注册用户失败");
						}
						
					} catch (Exception e) {
						resultJson.put("msg", "注册用户失败");
						writerObjToPage(resultJson);
						logger.error("addFavUser account ["+ account +"] error", e);
						throw e;
					}
				}
				else
				{
					if (isAdd)
					{
						favUserBean = new FavUserBean();
						BigDecimal userId = IdCreaterTool.getFavUserId();
						favUserBean.setId(userId);
						favUserBean.setAccount(account);
						favUserBean.setPhone(account);
						favUserBean.setAvatar(Constant.DEFAULT_PROFLE_PHOTO);
						favUserBean.setUser_name(account);
						//新注册用户默认等级为1级
						favUserBean.setUser_level("1");
						favUserBean.setPassword(MD5.GetMD5Code(password));
						favUserBean.setInsert_id(userId);
						favUserBean.setUpdate_id(userId);
						try {
							//注册用户
							boolean result = UserService.addFavUser(favUserBean, categoryIds);
							resultJson.put("result", result);
							if (result)
							{
								getSession().setAttribute(Constant.SESSION_USER_INFO, favUserBean);
							}
							else
							{
								resultJson.put("msg", "注册用户失败");
							}
							//增加成功后删除session验证码
							//getSession().removeAttribute(Constant.SMS_VERIFY_CODE);
						} catch (Exception e) {
							resultJson.put("msg", "注册用户失败");
							writerObjToPage(resultJson);
							logger.error("addFavUser account ["+ account +"] error", e);
							throw e;
						}
					}
				}
			}
		}
		
		writerObjToPage(resultJson);
		logger.info("addFavUser account ["+ account +"] result: " + resultJson.getBoolean("result") + ", msg : " + resultJson.getString("msg"));
        return null;
	}
	
	@Mapping("/goUserInfoSet")
	public Renderer userInfoSet()
	{
		Map<String, Object> out = getOutputMap();
		List<CollectionCategoryBean> ccbList = SysConfigCache.getCollectionCategorys();
		out.put("categoryList", ccbList);
		JSONObject categoryJson = new JSONObject();
		for (CollectionCategoryBean ccb : ccbList)
		{
			categoryJson.put(ccb.getId().toString(), ccb.getCategory_name());
		}
		out.put("categoryJson", categoryJson);
        return new TemplateRenderer("/html/user/userinfo-set.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/updateUserInfoSet")
	public Renderer updateUserInfoSet()
	{
		String userName = getRequest().getParameter("username");
		String categoryIds = getRequest().getParameter("categoryIds");
		JSONObject resultJson = new JSONObject();
		try {
			UserService.updateUserInfo(getSessionUser().getId(), userName, categoryIds);
			resultJson.put("result", true);
		} catch (Exception e) {
			logger.error("setUserInfo error", e);
			resultJson.put("result", false);
        	resultJson.put("msg", "完善个人信息失败");
		}
		writerObjToPage(resultJson);
		return null;
	}
	@Mapping("/loginVerify")
	public Renderer loginVerify()
	{
		String account = getRequest().getParameter("account");
		String password = getRequest().getParameter("password");
		JSONObject resultJson = new JSONObject();
		resultJson.put("result", false);
        if (StringUtils.isBlank(account) || StringUtils.isBlank(account))
        {
        	resultJson.put("msg", "用户名和密码不能为空");
        	writerObjToPage(resultJson);
        	return null;
        }
        
		try {
			FavUserBean user = UserService.queryUser(account, MD5.GetMD5Code(password));
			if (user != null)
			{
				getSession().setAttribute(Constant.SESSION_USER_INFO, user);
				resultJson.put("result", true);
			}
			else
			{
	        	resultJson.put("msg", "用户名和密码不正确");
			}
			
		} catch (Exception e) {
			logger.error("login account["+account+"] error", e);
			resultJson.put("result", false);
        	resultJson.put("msg", "用户名和密码不正确");
		}
		writerObjToPage(resultJson);
        return null;
	}
}
