/**   
 * 文件名：AttachmentDao.java   
 *   
 * 版本信息： 2.0    
 *   
 */
package com.osource.base.dao;

import java.util.List;

import com.osource.base.model.Attachment;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;


/**   
 *    
 * 项目名称：osource   
 * 类名称：AttachmentDao   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 6, 2009 2:27:49 PM   
 * 修改人：Administrator   
 * 修改时间：Nov 6, 2009 2:27:49 PM   
 * 修改备注：   
 * @version    
 *    
 */
public interface AttachmentDao {
	
	public Integer addAttachment(Attachment attachment)throws IctException;
	public void updateAttachment(Attachment attachment) throws IctException;
	public void deleteAttachmentById(Integer id)throws IctException;
	public Attachment findAttachmentyId(Integer id)throws IctException;
	public List<Attachment> findAttachmentByReleId(Integer id,Integer type) throws IctException;
	public PageList<Attachment> findAttachmentByCondition(Object condition, Pages pages) throws IctException;

}
