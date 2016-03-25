/**
 * 文件名：NoticeInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-08-15
 * 
 */
package com.osource.module.system.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：NoticeInfo
 * <p>类描述：
 * <p>创建人：pachong
 * <p>创建时间：2015-08-15
 * @version 2.0
 */
@SuppressWarnings("serial")
public class NoticeInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_notice";
	}
	
	 private String title;
	 private String type;
	 private String content;
	 
	 public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
}