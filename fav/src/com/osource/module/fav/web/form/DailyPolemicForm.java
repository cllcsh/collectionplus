/**
 * 文件名：DailyPolemicForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：DailyPolemicForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DailyPolemicForm extends BaseForm {
	 // 标题
    private String title;
    // 内容
    private String content;
    // 藏品图片，多个用,分隔
    private String images;
    // 甲方观点
    private String aViewpoint;
    // 乙方观点
    private String bViewpoint;
    // 支持甲方观点票数
    private int supportAViewpoint;
    // 支持乙方观点票数
    private int supportBViewpoint;
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
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public String getAViewpoint() {
        return aViewpoint;
    }
    public void setAViewpoint(String aViewpoint) {
        this.aViewpoint = aViewpoint;
    }
    public String getBViewpoint() {
        return bViewpoint;
    }
    public void setBViewpoint(String bViewpoint) {
        this.bViewpoint = bViewpoint;
    }
    public int getSupportAViewpoint() {
        return supportAViewpoint;
    }
    public void setSupportAViewpoint(int supportAViewpoint) {
        this.supportAViewpoint = supportAViewpoint;
    }
    public int getSupportBViewpoint() {
        return supportBViewpoint;
    }
    public void setSupportBViewpoint(int supportBViewpoint) {
        this.supportBViewpoint = supportBViewpoint;
    }
}