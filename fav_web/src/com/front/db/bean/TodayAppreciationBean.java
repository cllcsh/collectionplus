package com.front.db.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.front.web.framework.database.annotation.DBField;
import com.front.web.framework.database.annotation.DBTable;

/**
 * @author gaoxiang
 * @date 2015-11-05
 */
@DBTable(name="tb_today_appreciation")
public class TodayAppreciationBean {
    // Id
    @DBField(name="id")
    private BigDecimal id;
    // 标题
    @DBField(name="title")
    private String title;
    // 内容
    @DBField(name="content")
    private String content;
    // 发布时间
    @DBField(name="release_time")
    private Date release_time;
    // 上传图片
    @DBField(name="image")
    private String image;
    
    //显示顺序
    @DBField(name="display_order")
    private Integer display_order;
    
    //是否显示（Y:是,N:否）
    @DBField(name="is_show")
    private String is_show;
    
    @DBField(name="use_flag")
    private BigDecimal use_flag;
    @DBField(name="insert_date")
    private Date insert_date;
    @DBField(name="insert_id")
    private BigDecimal insert_id;
    @DBField(name="update_date")
    private Date update_date;
    @DBField(name="update_id")
    private BigDecimal update_id;
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getRelease_time() {
        return release_time;
    }
    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }
   
    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(Integer displayOrder) {
		display_order = displayOrder;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String isShow) {
		is_show = isShow;
	}
	public BigDecimal getUse_flag() {
		return use_flag;
	}
	public void setUse_flag(BigDecimal useFlag) {
		use_flag = useFlag;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insertDate) {
		insert_date = insertDate;
	}
	public BigDecimal getInsert_id() {
		return insert_id;
	}
	public void setInsert_id(BigDecimal insertId) {
		insert_id = insertId;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}
	public BigDecimal getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(BigDecimal updateId) {
		update_id = updateId;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}