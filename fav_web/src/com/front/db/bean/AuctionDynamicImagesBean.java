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
@DBTable(name="tb_auction_dynamic_images")
public class AuctionDynamicImagesBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 动态id
    @DBField(name="anction_dynamic_id")
    private BigDecimal anction_dynamic_id;
    // 内容
    @DBField(name="content")
    private String content;
    // 上传图片
    @DBField(name="images1")
    private String images1;
    // 上传图片
    @DBField(name="images2")
    private String images2;
    // 上传图片
    @DBField(name="images3")
    private String images3;
    // 上传图片
    @DBField(name="images4")
    private String images4;
    // 上传图片
    @DBField(name="images5")
    private String images5;
    // 上传图片
    @DBField(name="images6")
    private String images6;
    // 上传图片
    @DBField(name="images7")
    private String images7;
    // 上传图片
    @DBField(name="images8")
    private String images8;
    // 上传图片
    @DBField(name="images9")
    private String images9;
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
    public BigDecimal getAnction_dynamic_id() {
        return anction_dynamic_id;
    }
    public void setAnction_dynamic_id(BigDecimal anction_dynamic_id) {
        this.anction_dynamic_id = anction_dynamic_id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImages1() {
        return images1;
    }
    public void setImages1(String images1) {
        this.images1 = images1;
    }
    public String getImages2() {
        return images2;
    }
    public void setImages2(String images2) {
        this.images2 = images2;
    }
    public String getImages3() {
        return images3;
    }
    public void setImages3(String images3) {
        this.images3 = images3;
    }
    public String getImages4() {
        return images4;
    }
    public void setImages4(String images4) {
        this.images4 = images4;
    }
    public String getImages5() {
        return images5;
    }
    public void setImages5(String images5) {
        this.images5 = images5;
    }
    public String getImages6() {
        return images6;
    }
    public void setImages6(String images6) {
        this.images6 = images6;
    }
    public String getImages7() {
        return images7;
    }
    public void setImages7(String images7) {
        this.images7 = images7;
    }
    public String getImages8() {
        return images8;
    }
    public void setImages8(String images8) {
        this.images8 = images8;
    }
    public String getImages9() {
        return images9;
    }
    public void setImages9(String images9) {
        this.images9 = images9;
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
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}