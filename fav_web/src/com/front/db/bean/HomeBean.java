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
@DBTable(name="tb_home")
public class HomeBean {
    @DBField(name="id")
    private BigDecimal id;
    // 广告位图片
    @DBField(name="ad_images1")
    private String ad_images1;
    // 广告位图片
    @DBField(name="ad_images2")
    private String ad_images2;
    // 广告位图片
    @DBField(name="ad_images3")
    private String ad_images3;
    // 广告位图片
    @DBField(name="ad_images4")
    private String ad_images4;
    // 广告位图片
    @DBField(name="ad_images5")
    private String ad_images5;
    // 广告位图片
    @DBField(name="ad_images6")
    private String ad_images6;
    // 广告位图片
    @DBField(name="ad_images7")
    private String ad_images7;
    // 广告位图片
    @DBField(name="ad_images8")
    private String ad_images8;
    // 广告位图片
    @DBField(name="ad_images9")
    private String ad_images9;
    // 广告位地址
    @DBField(name="ad_path1")
    private String ad_path1;
    // 广告位地址
    @DBField(name="ad_path2")
    private String ad_path2;
    // 广告位地址
    @DBField(name="ad_path3")
    private String ad_path3;
    // 广告位地址
    @DBField(name="ad_path4")
    private String ad_path4;
    // 广告位地址
    @DBField(name="ad_path5")
    private String ad_path5;
    // 广告位地址
    @DBField(name="ad_path6")
    private String ad_path6;
    // 广告位地址
    @DBField(name="ad_path7")
    private String ad_path7;
    // 广告位地址
    @DBField(name="ad_path8")
    private String ad_path8;
    // 广告位地址
    @DBField(name="ad_path9")
    private String ad_path9;
    
    // 推荐藏品展示个数
    @DBField(name="recommend_collection_show_num")
    private int recommend_collection_show_num;
    // 热门藏家展示个数
    @DBField(name="top_collectors_show_num")
    private int top_collectors_show_num;
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
    public String getAd_images1() {
        return ad_images1;
    }
    public void setAd_images1(String ad_images1) {
        this.ad_images1 = ad_images1;
    }
    public String getAd_images2() {
        return ad_images2;
    }
    public void setAd_images2(String ad_images2) {
        this.ad_images2 = ad_images2;
    }
    public String getAd_images3() {
        return ad_images3;
    }
    public void setAd_images3(String ad_images3) {
        this.ad_images3 = ad_images3;
    }
    public String getAd_images4() {
        return ad_images4;
    }
    public void setAd_images4(String ad_images4) {
        this.ad_images4 = ad_images4;
    }
    public String getAd_images5() {
        return ad_images5;
    }
    public void setAd_images5(String ad_images5) {
        this.ad_images5 = ad_images5;
    }
    public String getAd_images6() {
        return ad_images6;
    }
    public void setAd_images6(String ad_images6) {
        this.ad_images6 = ad_images6;
    }
    public String getAd_images7() {
        return ad_images7;
    }
    public void setAd_images7(String ad_images7) {
        this.ad_images7 = ad_images7;
    }
    public String getAd_images8() {
        return ad_images8;
    }
    public void setAd_images8(String ad_images8) {
        this.ad_images8 = ad_images8;
    }
    public String getAd_images9() {
        return ad_images9;
    }
    public void setAd_images9(String ad_images9) {
        this.ad_images9 = ad_images9;
    }
    public int getRecommend_collection_show_num() {
        return recommend_collection_show_num;
    }
    public void setRecommend_collection_show_num(int recommend_collection_show_num) {
        this.recommend_collection_show_num = recommend_collection_show_num;
    }
    public int getTop_collectors_show_num() {
        return top_collectors_show_num;
    }
    public void setTop_collectors_show_num(int top_collectors_show_num) {
        this.top_collectors_show_num = top_collectors_show_num;
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
    public String getAd_path1() {
		return ad_path1;
	}
	public void setAd_path1(String adPath1) {
		ad_path1 = adPath1;
	}
	public String getAd_path2() {
		return ad_path2;
	}
	public void setAd_path2(String adPath2) {
		ad_path2 = adPath2;
	}
	public String getAd_path3() {
		return ad_path3;
	}
	public void setAd_path3(String adPath3) {
		ad_path3 = adPath3;
	}
	public String getAd_path4() {
		return ad_path4;
	}
	public void setAd_path4(String adPath4) {
		ad_path4 = adPath4;
	}
	public String getAd_path5() {
		return ad_path5;
	}
	public void setAd_path5(String adPath5) {
		ad_path5 = adPath5;
	}
	public String getAd_path6() {
		return ad_path6;
	}
	public void setAd_path6(String adPath6) {
		ad_path6 = adPath6;
	}
	public String getAd_path7() {
		return ad_path7;
	}
	public void setAd_path7(String adPath7) {
		ad_path7 = adPath7;
	}
	public String getAd_path8() {
		return ad_path8;
	}
	public void setAd_path8(String adPath8) {
		ad_path8 = adPath8;
	}
	public String getAd_path9() {
		return ad_path9;
	}
	public void setAd_path9(String adPath9) {
		ad_path9 = adPath9;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}