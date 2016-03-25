package com.front.web.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.express.portal.PathUtil;


public class Constant 
{
	public final static String DEFAULT_MODULE_STRING = "model";
	
	public final static String SESSION_USER_INFO = "user_info";
	
	public final static String SMS_VERIFY_CODE = "verifyCode";
	
	
	public final static String DEFAULT_SESSION_VALID_CODE = "validphone";
	public final static String DEFAULT_SESSION_DISABLE_TIME = "disabletime";
	
	public final static String DEFAULT_LOGINURL = "/login";
	public final static String DEFAULT_ERRORURL = "/error";
	public final static String HOME_URL = "/home";
	public final static String DEFAULT_PAGE_BEAN = "pages";
	
	public final static String TRANSACTIONMETHOD = "transactionMethod";
	
	public final static String NOT_CHECK_URL = "not_check_url";
	
	public final static String PAGE_SIZE = "pageSize";
	
	public final static String DEFAULT_PROFLE_PHOTO = PathUtil.getUploadImgFileName() + "/profle.png";
	
	public final static int COLLECTION_DETAIL_COMMENTS_SHOW = 1;
	//评论来源类型 0：藏品 1：动态
	public final static String COMMENT_SOURCE_TYPE_COLLECTION = "0";
	//评论来源类型 0：藏品 1：动态
	public final static String COMMENT_SOURCE_TYPE_DYNAMIC = "1";
	//评论类型 0：评论 1：回复
	public final static String COMMENT_TYPE_COMMENT = "0";
	//评论类型 0：评论 1：回复
	public final static String COMMENT_TYPE_REPLY = "1";
	//该用户的粉丝数量系数
	public final static String HEAT_USER_FANS = "user_fans";
	//该用户获得的赞同值系数
	public final static String HEAT_USER_COMMENT_LIKE = "user_comment_like";
	//该条藏品话题下的赞同值系数
	public final static String HEAT_COLLECTION_LIKE = "collection_like";
	//该条藏品话题下的反对值系数
	public final static String HEAT_COLLECTION_OPPOSE = "collection_oppose";
	//热度+
	public final static int HEAT_POSITIVE = 1;
	//热度-
	public final static int HEAT_NEGATIVE = -1;
	
	//评论类型 0：赞成 
	public final static String COMMENT_LIKE = "0";
	//评论类型 1：反对
	public final static String COMMENT_OPPOSE = "1";
	
	//天天论战投票 0：赞成 
	public final static String DAILY_POLEMIC_VOTE_LIKE = "0";
	//天天论战投票 1：反对
	public final static String DAILY_POLEMIC_VOTE_OPPOSE = "1";
	
	//任务类型：发藏品：release_collection，发个人动态：release_dynamic，获赞：get_like，更换头像：change_avatar
	public final static String TASK_TYPE_COLLECTION = "release_collection";
	public final static String TASK_TYPE_DYNAMIC = "release_dynamic";
	public final static String TASK_TYPE_LIKE = "get_like";
	public final static String TASK_TYPE_AVATAR = "change_avatar";
	
	//personal_msg_set` 个人私信设置  全部接收:received_msg_all  只接收我关注的用户 received_msg_attention 拒绝所有私信 reject_msg_all
	public final static String PERSONAL_MSG_SET_ALL = "received_msg_all";
	public final static String PERSONAL_MSG_SET_ATTENTION = "received_msg_attention";
	public final static String PERSONAL_MSG_SET_REJECT = "reject_msg_all";
	
	//第一颗星代码的热度值，最多有5个星
	public final static int HEAT_LEVEL = 5;
	public final static int MAX_STAR = 5;
	
	public final static BigDecimal USE_FLAG = new BigDecimal(1);
	
	//famous_home_type_type 0：专家组 1：收藏大咖
	public final static String FAMOUS_TYPE_EXPERT_GROUP = "0";
	//famous_home_type_type 0：专家组 1：收藏大咖
	public final static String FAMOUS_TYPE_COLLECT = "1";
	
	//拍品征集
	public final static String AUCTION_DYNAMICS_TYPE_CALL = "1";
	//拍卖预展
	public final static String AUCTION_DYNAMICS_TYPE_PREVIEW = "2";
	//文字直播
	public final static String AUCTION_DYNAMICS_TYPE_LIVE = "3";
	//成交记录
	public final static String AUCTION_DYNAMICS_TYPE_TRANS_RECORD = "4";
	
	//价钱单位
	public final static String ENUM_MONEY_TYPE = "money_type";
	
	//性别昵称
	public final static String ENUM_SEX_NICK_TYPE = "sex_nick_type";
	//送拍
	public final static String COLLECTION_STATUS_SEND_RACKET = "collection_status_send_racket";
	//页中最大数量
	public final static int MAX_PAGE_SIZE = 3000;
	public static Map<String, String> APPLY_STATUS = new HashMap<String, String>();
	static{
		APPLY_STATUS.put("collection_status_wait_apply", "等待审核");
		APPLY_STATUS.put("collection_status_applying", "审核中");
		APPLY_STATUS.put("collection_status_pass_apply", "审核通过");
		APPLY_STATUS.put("collection_status_fail_apply", "审核不通过");
		APPLY_STATUS.put("collection_status_appraisal", "估价");
		APPLY_STATUS.put("collection_status_auction", "拍卖中");
		APPLY_STATUS.put("collection_status_solded", "已卖");
		APPLY_STATUS.put("collection_status_invalid", "流拍");
	}
}
