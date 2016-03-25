package com.osource.base.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.osource.base.dao.AttachmentDao;
import com.osource.base.model.Attachment;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.orm.ibatis.BaseDaoImpl;


/**   
 *    
 * 项目名称：osource   
 * 类名称：AttachmentDaoIbatisImpl   
 * 类描述：   
 * 创建人：weiwu   
 * 创建时间：Nov 6, 2009 3:12:46 PM   
 * 修改人：Administrator   
 * 修改时间：Nov 6, 2009 3:12:46 PM   
 * 修改备注：   
 * @version    
 *    
 */
public class AttachmentDaoIbatisImpl extends BaseDaoImpl implements AttachmentDao{

	public String getEntityName() {
		return "attachment";
	}
	public Integer addAttachment(Attachment attachment) throws IctException {
		insert("attachment_addAttachment", attachment);
		return attachment.getId();
	}
	
	public void updateAttachment(Attachment attachment) throws IctException {
		update("attachment_updateAttachment", attachment);
	}

	public void deleteAttachmentById(Integer id) throws IctException {
		delete("attachment_deleteAttachmentById", id);
	}

	public Attachment findAttachmentyId(Integer id) throws IctException {
		return (Attachment) this.queryForObject("attachment_findAttachmentById", id);
	}

	public List<Attachment> findAttachmentByReleId(Integer id,Integer type) throws IctException {
		HashMap map = new HashMap();
		map.put("relevanceId", id);
		map.put("type", type);
		return  this.queryForList("attachment_findAttachmentByReleId", map);
	}
	
	public PageList<Attachment> findAttachmentByCondition(Object condition, Pages pages) throws IctException {
		PageList<Attachment> result = new PageList();
		if(pages == null){
			result.addAll(getSqlMapClientTemplate().queryForList("attachment_findByCondition", condition));
			result.getPages().setTotal(result.size());
			return result;
		}
		pages.setTotal((Long)getSqlMapClientTemplate().queryForObject("attachment_countByCondition",condition));
		pages.executeCount();
		result.setPages(pages);
		result.addAll(getSqlMapClientTemplate().queryForList( "attachment_findByCondition", condition, pages.getStart(), pages.getLimit()));
		return result;
	}
}
