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
@DBTable(name="tb_user")
public class UserBean {
    @DBField(name="id")
    private BigDecimal id;
    // 手机号
    @DBField(name="login_name")
    private String login_name;
    @DBField(name="password")
    private String password;
    @DBField(name="former_password")
    private String former_password;
    // 真实姓名汉字
    @DBField(name="name")
    private String name;
    @DBField(name="id_card")
    private String id_card;
    // 电子邮箱
    @DBField(name="e_mail")
    private String e_mail;
    @DBField(name="province")
    private String province;
    // 市
    @DBField(name="city")
    private String city;
    // 区
    @DBField(name="area")
    private String area;
    // 详细地址
    @DBField(name="address")
    private String address;
    // 公司名称
    @DBField(name="company_name")
    private String company_name;
    // 职务名称
    @DBField(name="duty")
    private String duty;
    @DBField(name="reg_number")
    private String reg_number;
    // 身份证反面
    @DBField(name="idcardb_picPath")
    private String idcardb_picPath;
    // 身份证正面
    @DBField(name="idcardf_picPath")
    private String idcardf_picPath;
    // 营业执照照片
    @DBField(name="business_picPath")
    private String business_picPath;
    // 品牌
    @DBField(name="brand")
    private String brand;
    // VIP查询密码
    @DBField(name="vip_pass")
    private String vip_pass;
    @DBField(name="approve_flag")
    private String approve_flag;
    @DBField(name="reason")
    private String reason;
    // 用户类型
    @DBField(name="user_type")
    private String user_type;
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
    @DBField(name="img_path")
    private String img_path;
    // 银行卡号
    @DBField(name="card_no")
    private String card_no;
    // 开户银行
    @DBField(name="bank_name")
    private String bank_name;
    // 开户人
    @DBField(name="account_holder")
    private String account_holder;
    // 登录次数
    @DBField(name="login_count")
    private int login_count;
    // 专属总经理
    @DBField(name="manager")
    private String manager;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFormer_password() {
        return former_password;
    }
    public void setFormer_password(String former_password) {
        this.former_password = former_password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId_card() {
        return id_card;
    }
    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
    public String getE_mail() {
        return e_mail;
    }
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    public String getDuty() {
        return duty;
    }
    public void setDuty(String duty) {
        this.duty = duty;
    }
    public String getReg_number() {
        return reg_number;
    }
    public void setReg_number(String reg_number) {
        this.reg_number = reg_number;
    }
    public String getIdcardb_picPath() {
        return idcardb_picPath;
    }
    public void setIdcardb_picPath(String idcardb_picPath) {
        this.idcardb_picPath = idcardb_picPath;
    }
    public String getIdcardf_picPath() {
        return idcardf_picPath;
    }
    public void setIdcardf_picPath(String idcardf_picPath) {
        this.idcardf_picPath = idcardf_picPath;
    }
    public String getBusiness_picPath() {
        return business_picPath;
    }
    public void setBusiness_picPath(String business_picPath) {
        this.business_picPath = business_picPath;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getVip_pass() {
        return vip_pass;
    }
    public void setVip_pass(String vip_pass) {
        this.vip_pass = vip_pass;
    }
    public String getApprove_flag() {
        return approve_flag;
    }
    public void setApprove_flag(String approve_flag) {
        this.approve_flag = approve_flag;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getUser_type() {
        return user_type;
    }
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    public BigDecimal getUse_flag() {
        return use_flag;
    }
    public void setUse_flag(BigDecimal use_flag) {
        this.use_flag = use_flag;
    }
    public Date getInsert_date() {
        return insert_date;
    }
    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }
    public BigDecimal getInsert_id() {
        return insert_id;
    }
    public void setInsert_id(BigDecimal insert_id) {
        this.insert_id = insert_id;
    }
    public Date getUpdate_date() {
        return update_date;
    }
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
    public BigDecimal getUpdate_id() {
        return update_id;
    }
    public void setUpdate_id(BigDecimal update_id) {
        this.update_id = update_id;
    }
    public String getImg_path() {
        return img_path;
    }
    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
    public String getCard_no() {
        return card_no;
    }
    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }
    public String getBank_name() {
        return bank_name;
    }
    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
    public String getAccount_holder() {
        return account_holder;
    }
    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }
    public int getLogin_count() {
        return login_count;
    }
    public void setLogin_count(int login_count) {
        this.login_count = login_count;
    }
    public String getManager() {
        return manager;
    }
    public void setManager(String manager) {
        this.manager = manager;
    }
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}