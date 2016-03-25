/**
 * 文件名：FavUserSetInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-22
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：FavUserSetInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-22
 * @version 2.0
 */
@SuppressWarnings("serial")
public class FavUserSetInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_fav_user_set";
	}
	
    // 用户id
    private int userId;
    private String userName;
    // 好友用户id
    private int friendId;
    private String friendName;
    // 是否屏蔽私信  1:屏蔽;0:不屏蔽
    private String blockMsg;
    private String blockMsgDesc;
    // 是否屏蔽话题  1:屏蔽;0:不屏蔽
    private String blockDynamic;
    private String blockDynamicDesc;
    // 是否屏蔽回复  1:屏蔽;0:不屏蔽
    private String blockReply;
    private String blockReplyDesc;
    // 是否屏蔽评论  1:屏蔽;0:不屏蔽
    private String blockComment;
    private String blockCommentDesc;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getFriendId() {
        return friendId;
    }
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
    public String getBlockMsg() {
        return blockMsg;
    }
    public void setBlockMsg(String blockMsg) {
        this.blockMsg = blockMsg;
    }
    public String getBlockDynamic() {
        return blockDynamic;
    }
    public void setBlockDynamic(String blockDynamic) {
        this.blockDynamic = blockDynamic;
    }
    public String getBlockReply() {
        return blockReply;
    }
    public void setBlockReply(String blockReply) {
        this.blockReply = blockReply;
    }
    public String getBlockComment() {
        return blockComment;
    }
    public void setBlockComment(String blockComment) {
        this.blockComment = blockComment;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public String getBlockMsgDesc() {
		return blockMsgDesc;
	}
	public void setBlockMsgDesc(String blockMsgDesc) {
		this.blockMsgDesc = blockMsgDesc;
	}
	public String getBlockDynamicDesc() {
		return blockDynamicDesc;
	}
	public void setBlockDynamicDesc(String blockDynamicDesc) {
		this.blockDynamicDesc = blockDynamicDesc;
	}
	public String getBlockReplyDesc() {
		return blockReplyDesc;
	}
	public void setBlockReplyDesc(String blockReplyDesc) {
		this.blockReplyDesc = blockReplyDesc;
	}
	public String getBlockCommentDesc() {
		return blockCommentDesc;
	}
	public void setBlockCommentDesc(String blockCommentDesc) {
		this.blockCommentDesc = blockCommentDesc;
	}
}
