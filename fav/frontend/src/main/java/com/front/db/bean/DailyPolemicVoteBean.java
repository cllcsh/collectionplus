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
@DBTable(name="tb_daily_polemic_vote")
public class DailyPolemicVoteBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 天天论战id
    @DBField(name="daily_polemic_id")
    private BigDecimal daily_polemic_id;
    // 投票人
    @DBField(name="user_id")
    private BigDecimal user_id;
    // 1:反对;0:赞成
    @DBField(name="type")
    private String type;
    // 投票日期 yyyy-MM-dd
    @DBField(name="day")
    private String day;
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
  
    public BigDecimal getDaily_polemic_id() {
		return daily_polemic_id;
	}
	public void setDaily_polemic_id(BigDecimal dailyPolemicId) {
		daily_polemic_id = dailyPolemicId;
	}
	public BigDecimal getUser_id() {
		return user_id;
	}
	public void setUser_id(BigDecimal userId) {
		user_id = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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