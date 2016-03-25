/**
 * 文件名：MessagesInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：MessagesInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class MessagesInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_messages";
	}
	// 发送 人（发送人的用户id）
    private int sender;
    // 发送 人（发送人的用户id）
    private String senderName;
    // 接收人（接收人的用户id）
    private int receiver;
    // 接收人（接收人的用户id）
    private String receiverName;
    // 发送时间
    private Date sendTime;
    // 消息内容
    private String content;
    // 是否已读，Y:已读，N：未读
    private String isRead;
    // 是否已读，Y:已读，N：未读
    private String isReadDesc;
    // 是否删除，Y:已删除，N：未删除
    private String isDelete;
    public int getSender() {
        return sender;
    }
    public void setSender(int sender) {
        this.sender = sender;
    }
    public int getReceiver() {
        return receiver;
    }
    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getIsRead() {
        return isRead;
    }
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
    public String getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getIsReadDesc() {
		return isReadDesc;
	}
	public void setIsReadDesc(String isReadDesc) {
		this.isReadDesc = isReadDesc;
	}
}