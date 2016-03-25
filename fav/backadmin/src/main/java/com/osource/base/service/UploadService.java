package com.osource.base.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.osource.core.exception.IctException;

/**   
 *    
 * 项目名称：osource   
 * 类名称：UploadService   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 6, 2009 11:27:33 AM   
 * 修改人：Administrator   
 * 修改时间：Nov 6, 2009 11:27:33 AM   
 * 修改备注：   
 * @version    
 *    
 */
public interface UploadService {
	
	public Integer upload1(String formerName, String savePath, String uploadFileName, File upload, String uploadContentType, String path, Integer relevanceId, String type) throws IOException, IctException, SQLException;
	
	public String upload(String formerName, String savePath, String uploadFileName, File upload, String uploadContentType, String path) throws IOException, IctException, SQLException;
	
	
	public Integer saveAttachment(String formerName, String fileName, String filePath, Long fileSize, Integer id, Integer relevanceId, String type) throws IctException;
	

//	public AttachmentDao getAttachmentDao() {
//		return attachmentDao;
//	}
//
//	public void setAttachmentDao(AttachmentDao attachmentDao) {
//		this.attachmentDao = attachmentDao;
//	}

}
