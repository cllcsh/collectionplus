package com.osource.base.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osource.base.dao.AttachmentDao;
import com.osource.base.model.Attachment;
import com.osource.base.service.UploadService;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * 
 * 项目名称：osource 类名称：UploadService 类描述： 创建人：weiwu 创建时间：Nov 6, 2009 11:27:33 AM 修改人：Administrator 修改时间：Nov 6, 2009
 * 11:27:33 AM 修改备注：
 * 
 * @version
 * 
 */
@Service
public class UploadServiceImpl extends BaseServiceImpl implements UploadService {
    private static final Log log = LogFactory.getLog(UploadServiceImpl.class);

    @Autowired
    private AttachmentDao attachmentDao;

    public Integer upload1(String formerName, String savePath, String uploadFileName, File upload,
                           String uploadContentType, String path, Integer relevanceId, String type) throws IOException,
            IctException, SQLException {
        String realSavePath;
        if (path != null) {
            realSavePath = savePath + "/" + path;
        } else {
            realSavePath = savePath;
        }
        File file = new File(realSavePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                log.error("创建上传路径失败");
            }
        }
        FileOutputStream fos = new FileOutputStream(realSavePath + "//" + uploadFileName);
        FileInputStream fis = new FileInputStream(upload);
        byte[] buffer = new byte[1024];
        int len = 0;
        Integer id = IDgenerator.gettNextID("tb_attachment");
        // sqlMapClient.startTransaction();
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        saveAttachment(formerName, uploadFileName, "upload/" + path + "/" + uploadFileName, upload.length(), id,
                       relevanceId, type);
        // sqlMapClient.commitTransaction();
        // sqlMapClient.endTransaction();

        log.info("上传文件" + realSavePath + uploadContentType + uploadFileName);
        return id;
    }

    public String upload(String formerName, String savePath, String uploadFileName, File upload,
                         String uploadContentType, String path) throws IOException, IctException, SQLException {
        String realSavePath;
        if (path != null) {
            realSavePath = savePath + "/" + path;
        } else
            realSavePath = savePath;
        File file = new File(realSavePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                log.error("创建上传路径失败");
            }
        }
        FileOutputStream fos = new FileOutputStream(realSavePath + "/" + uploadFileName);
        FileInputStream fis = new FileInputStream(upload);
        byte[] buffer = new byte[1024];
        int len = 0;
        Integer id = IDgenerator.gettNextID("tb_attachment");
        // sqlMapClient.startTransaction();
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        Integer relevanceId = 0;
        String type = "";
        saveAttachment(formerName, uploadFileName, "upload/" + path + "/" + uploadFileName, upload.length(), id,
                       relevanceId, type);
        // sqlMapClient.commitTransaction();
        // sqlMapClient.endTransaction();

        log.info("上传文件" + realSavePath + uploadContentType + uploadFileName);
        return id + "|" + formerName + "|" + path + "/" + uploadFileName;
    }

    public Integer saveAttachment(String formerName, String fileName, String filePath, Long fileSize, Integer id,
                                  Integer relevanceId, String type) throws IctException {
        Attachment attachment = new Attachment();
        attachment.setName(formerName);
        attachment.setFileName(fileName);
        attachment.setFilePath(filePath);
        attachment.setFileSize(fileSize);
        attachment.setId(id);
        attachment.setDeptId(0);
        attachment.setInsertId(1);

        attachment.setRelevanceId(relevanceId);
        attachment.setType(type);

        return attachmentDao.addAttachment(attachment);
    }

    public AttachmentDao getAttachmentDao() {
        return attachmentDao;
    }

    public void setAttachmentDao(AttachmentDao attachmentDao) {
        this.attachmentDao = attachmentDao;
    }

}
