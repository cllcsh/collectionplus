package com.osource.base.web.action;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.osource.base.service.UploadService;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.util.IctUtil;

/**
 * 项目名称：zjsfjz   
 * 类名称：UploadsAction   
 * 类描述：   
 * 创建人：xiaoxubing   
 * 创建时间：2010-1-11 下午06:13:41   
 * 修改人：Administrator   
 * 修改时间：2010-1-11 下午06:13:41   
 * 修改备注：   
 * @version    
 *    
 */
public class UploadsAction extends BaseAction {
	private static final long serialVersionUID = 246574617931868284L;
	
	@Autowired
	private UploadService uploadService;
	private String finalPath;
	private int finalPaths[];
	
	private String title;
	private String path;
	// 用File数组来封装多个上传文件域对象    
    private File[] upload;    
    // 用String数组来封装多个上传文件名    
    private String[] uploadFileName;    
    // 用String数组来封装多个上传文件类型    
    private String[] uploadContentType;  
    
    private Integer relevanceId;
    
    private String type;

	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(UploadsAction.class);
	// 接受依赖注入的属性
	private String savePath;

	// 接受依赖注入的方法
	public void setSavePath(String value) {
		this.savePath = value;
	}

	@SuppressWarnings("deprecation")
	private String getSavePath() throws Exception {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getTitle() {
		return (this.title);
	}

	
	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public UploadService getUploadService() {
		return uploadService;
	}

	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	public int[] getFinalPaths() {
		return finalPaths;
	}

	public void setFinalPaths(int[] finalPaths) {
		this.finalPaths = finalPaths;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String fileUpload() throws Exception {
		int[] finPaths = new int[upload.length];
		for(int i = 0;i<upload.length;i++)
		{
			String formerName = getUpload()[i].getName();
			this.setSavePath(PropertiesManager.getProperty("common.properties", "UPLOAD_PATH"));
			uploadFileName[i] = IctUtil.getCurrTime()+i+ uploadFileName[i].substring(uploadFileName[i].lastIndexOf("."));
			
			int id = uploadService.upload1(formerName, getSavePath(), uploadFileName[i], getUpload()[i], getUploadContentType()[i], this.getPath(), relevanceId, type);
			finPaths[i] = id;
		}
				
		//uploadService.saveAttachment(formerName, getUploadFileName(), getSavePath()+"\\"+getUploadFileName(), getUpload().length());
		this.setFinalPaths(finPaths);
		return SUCCESS;
	}

	
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

	public Integer getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(Integer relevanceId) {
		this.relevanceId = relevanceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}