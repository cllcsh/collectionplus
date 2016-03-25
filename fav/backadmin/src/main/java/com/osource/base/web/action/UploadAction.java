package com.osource.base.web.action;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.osource.base.service.UploadService;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.util.IctUtil;

/**
 * 项目名称：osource 类名称：UploadAction 类描述： 创建人：weiwu 创建时间：Nov 6, 2009 9:56:28 AM 修改人：Administrator 修改时间：Nov 6, 2009 9:56:28
 * AM 修改备注：
 */
public class UploadAction extends BaseAction {
    private static final long serialVersionUID = 246574617931868284L;

    @Autowired
    private UploadService uploadService;

    private String finalPath;

    private String attachmentId;

    private String name;

    private String title;

    private String path;

    private File upload;

    private String uploadContentType;

    private String uploadFileName;

    // 接受依赖注入的属性
    private String savePath;

    // 接受依赖注入的方法
    public void setSavePath(String value) {
        this.savePath = value;
    }

    @SuppressWarnings("deprecation")
    private String getSavePath() throws Exception {
        if (StringUtils.isNotEmpty(savePath)) {
            return ServletActionContext.getRequest().getRealPath(savePath);
        }
        return "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getTitle() {
        return (this.title);
    }

    public File getUpload() {
        return (this.upload);
    }

    public String getUploadContentType() {
        return (this.uploadContentType);
    }

    public String getUploadFileName() {
        return (this.uploadFileName);
    }

    @Override
    public String execute() throws Exception {
        // 判读图片大小

        // Image srcPic =javax.imageio.ImageIO.read(getUpload()); // 构造Image对象
        // int wideth = srcPic.getWidth(null); // 得到源图宽
        // int height = srcPic.getHeight(null); // 得到源图长
        //
        // if(wideth!=378&&height!=57)
        // {
        // logger.error("图片长宽不符合要求");
        //
        // ActionContext.getContext().put("errorCode", "3");
        // return "uploadError";
        // }
        // System.out.println("开始上传单个文件-----------------------");
        // 以服务器的文件保存地址和原文件名建立上传文件输出流
        // 设置图片保存地址
        String formerName = this.getUploadFileName();
//        this.setSavePath(PropertiesManager.getProperty("common.properties", "UPLOAD_PATH"));
        // this.setSavePath("/upload");
        this.setUploadFileName(IctUtil.getCurrTime()
                + getUploadFileName().substring(getUploadFileName().lastIndexOf(".")));

        String fiPath = uploadService.upload(formerName, getSavePath(), getUploadFileName(), getUpload(),
                getUploadContentType(), this.getPath());
        // uploadService.saveAttachment(formerName, getUploadFileName(),
        // getSavePath()+"\\"+getUploadFileName(), getUpload().length());
        if (fiPath.indexOf("|") != -1) {
            String[] res = fiPath.split("\\|");
            attachmentId = res[0];
            name = res[1];
            finalPath = res[2];
        }
        String type = getRequest().getParameter("gxfcType");
        if (StringUtils.isBlank(type)) {
            return SUCCESS;
        } else {
            this.getAjaxMessagesJson().setMessage("0", finalPath);
            return RESULT_AJAXJSON;
        }
    }

    /*
     * public InputStream getTarget() { String title1 = null; try { title1 = new String(title.getBytes("ISO-8859-1"),
     * "UTF-8"); } catch (UnsupportedEncodingException e) { e.printStackTrace(); } String fileName = "/" + savePath +
     * "/" + title1; return ServletActionContext.getServletContext().getResourceAsStream( fileName); }
     */
    public String download() {
        return SUCCESS;
    }

    /**
     * 上传图片窗口初始化
     *
     * @return
     * @throws IctException
     */
    public String init() throws IctException {
        return RESULT_FILESET;
    }

    /**
     * added by lifa,2009-12-3.
     *
     * @return
     * @throws IctException
     */
    public String uploadInit() throws IctException {
        return "uploadInit";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFinalPath() {
        return finalPath;
    }

    public void setFinalPath(String finalPath) {
        this.finalPath = finalPath;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}