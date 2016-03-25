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
@DBTable(name="ts_login_log")
public class LoginLogBean {
    @DBField(name="id")
    private BigDecimal id;
    @DBField(name="login_name")
    private String login_name;
    @DBField(name="login_date")
    private Date login_date;
    @DBField(name="login_ip")
    private String login_ip;
    @DBField(name="login_addr")
    private String login_addr;
    // 1：成功；2：用户名不存在；3 ：密码错误；4：验证码错误 
    @DBField(name="login_result")
    private String login_result;
    @DBField(name="dept_id")
    private BigDecimal dept_id;
    @DBField(name="use_flag")
    private BigDecimal use_flag;
    // 在线时长
    @DBField(name="online_time")
    private String online_time;
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getLogin_name() {
        return login_name;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    public Date getLogin_date() {
        return login_date;
    }
    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }
    public String getLogin_ip() {
        return login_ip;
    }
    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }
    public String getLogin_addr() {
        return login_addr;
    }
    public void setLogin_addr(String login_addr) {
        this.login_addr = login_addr;
    }
    public String getLogin_result() {
        return login_result;
    }
    public void setLogin_result(String login_result) {
        this.login_result = login_result;
    }
    public BigDecimal getDept_id() {
        return dept_id;
    }
    public void setDept_id(BigDecimal dept_id) {
        this.dept_id = dept_id;
    }
    public BigDecimal getUse_flag() {
        return use_flag;
    }
    public void setUse_flag(BigDecimal use_flag) {
        this.use_flag = use_flag;
    }
    public String getOnline_time() {
        return online_time;
    }
    public void setOnline_time(String online_time) {
        this.online_time = online_time;
    }
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}