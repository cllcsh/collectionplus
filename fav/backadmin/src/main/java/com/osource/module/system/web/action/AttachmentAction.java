package com.osource.module.system.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.util.Entry;
import com.osource.base.web.action.BaseAction;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.system.model.AttachmentBean;
import com.osource.module.system.service.AttachmentService;
import com.osource.module.system.service.DeptService;
import com.osource.module.system.web.form.AttachmentForm;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AttachmentAction extends BaseAction {
    private static final int BUFFER_SIZE = 16 * 1024;
    // 上传文件域对象
    private String upload;
    // 上传文件名
    private String uploadFileName;
    // 上传文件类型
    private String uploadContentType;
    // 保存文件的目录路径(通过依赖注入)
    private String savePath;

    private String fileName;
    private String finPath;
    private String fileSize;
    private List<Entry<String, String>> departmentList;// 机构名下拉列表
    public InputStream inputStream;

    @Autowired
    private AttachmentService attachmentService;

    private AttachmentForm attachmentForm;

    @Autowired
    private DeptService deptService;

    /** action methods **/

    public AttachmentAction() {
        super();
    }

    /**
     * 功能初始页面跳转
     */
    public String init() {

        return RESULT_INIT;
    }

    /**
     * 根据查询条件进行查询
     */
    public String query() {
        Map condition = new HashMap();

        condition.put("name", attachmentForm.getName());
        condition.put("deptId", getUserSession().getDeptId());

        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(attachmentService.findByCondition(condition, pages));

        return RESULT_LIST;
    }

    /**
     * 跳转到添加通知下载页面
     */
    public String add() {
        departmentList = deptService.getDeptSelectList(getUserSession().getDeptId());
        this.setActionName("attachment_save");
        return RESULT_SET;
    }

    /**
     * 添加通知下载信息
     */
    public String save() {
        AttachmentBean bean = new AttachmentBean();

        bean.setName(attachmentForm.getName());
        bean.setDescription(attachmentForm.getDescription());
        // bean.setDeptId(Integer.valueOf(attachmentForm.getDepartmentId()));
        bean.setInsertId(getUserSession().getUserId());
        bean.setFileName(this.fileName);

        try {
            attachmentService.save(bean);
            this.getAjaxMessagesJson().setMessage("0", "添加通知下载成功");
            logger.debug("添加通知下载成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加通知下载失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 跳转到编辑通知下载页面
     */
    public String edit() throws IctException {
        AttachmentBean attachmentBean;
        attachmentBean = attachmentService.findById(this.getId());

        departmentList = deptService.getDeptSelectList(getUserSession().getDeptId());

        AttachmentForm attachmentForm = new AttachmentForm();
        IctUtil.copyProperties(attachmentForm, attachmentBean);

        // attachmentForm.setDepartmentId(String.valueOf(attachmentBean.getDeptId()));

        this.setAttachmentForm(attachmentForm);

        return RESULT_SET;
    }

    /**
     * 跳转到查看通知下载页面
     */
    public String view() throws IctException {
        AttachmentBean attachmentBean;
        attachmentBean = attachmentService.findById(Integer.valueOf(this.getId()));

        AttachmentForm attachmentForm = new AttachmentForm();
        IctUtil.copyProperties(attachmentForm, attachmentBean);

        this.setAttachmentForm(attachmentForm);

        return RESULT_VIEW;
    }

    /**
     * 修改通知下载信息
     */
    public String update() {
        AttachmentBean attachmentBean = new AttachmentBean();

        try {
            IctUtil.copyProperties(attachmentBean, attachmentForm);
            attachmentBean.setUpdateId(getUserSession().getUserId());
            // attachmentBean.setDeptId(Integer.valueOf(attachmentForm.getDepartmentId()));
            attachmentBean.setFileName(this.fileName);

            attachmentService.update(attachmentBean);
            this.getAjaxMessagesJson().setMessage("0", "修改通知下载成功");
            logger.debug("修改通知下载成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改通知下载失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 删除通知下载信息
     */
    public String deletes() {
        try {
            attachmentService.deleteById(this.getIds());
            this.getAjaxMessagesJson().setMessage("0", "删除成功");
            logger.debug("删除成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
            logger.debug(e);
        }
        return RESULT_AJAXJSON;
    }

    /**
     * 获取下载数据流
     */
    public String download() {
        AttachmentBean attachmentBean;
        attachmentBean = attachmentService.findById(this.getId());

        // 文件下载路径

        // String downloadFile =
        // ServletActionContext.getServletContext().getRealPath("uplod/"+attachmentBean.getFilePath());
        this.setFinPath(attachmentBean.getFilePath());
        String ext = attachmentBean.getFilePath().substring(attachmentBean.getFilePath().lastIndexOf("."));
        this.setFileName(attachmentBean.getName() + ext);

        return null;
    }

    public InputStream getInputStream() throws Exception {

        // 通过 ServletContext，也就是application 来读取数据
        String realPath = ServletActionContext.getServletContext()
                .getRealPath(PropertiesManager.getProperty("common.properties", "UPLOAD_PATH"))
                + File.separator + this.getFinPath();
        InputStream temp = new java.io.FileInputStream(realPath);

        return temp;

    }

    public String getDownloadFileName() {
        String downFileName = null;
        try {
            downFileName = new String(this.getFileName().getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return downFileName;
    }

    public String upload() {
        String dirPath = PropertiesManager.getProperty("common.properties", "UPLOAD_PATH") + "\\attachment";
        String fileName = IctUtil.getCurrTime() + getUploadFileName().substring(getUploadFileName().lastIndexOf("."));
        String dstPath = dirPath + "\\" + fileName;

        System.out.println("上传的文件的类型：" + this.getUploadContentType());

        File dir = new File(ServletActionContext.getServletContext().getRealPath(dirPath));
        if (!dir.exists())
            dir.mkdir();

        File dstFile = new File(ServletActionContext.getServletContext().getRealPath(dstPath));
        File src = new File(this.upload);
        copy(src, dstFile);

        this.setFileName(fileName);
        this.setFinPath(ServletActionContext.getServletContext().getRealPath(dstPath));
        this.setFileSize(String.valueOf(src.length()));

        return "attsucc";
    }

    // 自己封装的一个把源文件对象复制成目标文件对象
    private static void copy(File src, File dst) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** getter and setter methods **/

    public AttachmentService getAttachmentService() {
        return attachmentService;
    }

    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    public AttachmentForm getAttachmentForm() {
        return attachmentForm;
    }

    public void setAttachmentForm(AttachmentForm attachmentForm) {
        this.attachmentForm = attachmentForm;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = ServletActionContext.getServletContext().getRealPath(this.savePath);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFinPath() {
        return finPath;
    }

    public void setFinPath(String finPath) {
        this.finPath = finPath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public List<Entry<String, String>> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Entry<String, String>> departmentList) {
        this.departmentList = departmentList;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}