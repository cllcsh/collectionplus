package com.osource.module.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.NoticeInfoDao;
import com.osource.module.system.model.NoticeInfo;
import com.osource.module.system.service.NoticeInfoService;
import com.osource.orm.ibatis.BaseServiceImpl;

@SuppressWarnings("unchecked")
@Service
@Scope("prototype")
@Transactional
public class NoticeInfoServiceImpl extends BaseServiceImpl implements NoticeInfoService{
	private static final Logger logger = Logger.getLogger(NoticeInfoServiceImpl.class);
	@Autowired
	private NoticeInfoDao noticeInfoDao;
	public NoticeInfoDao getNoticeInfoDao() {
		return noticeInfoDao;
	}
	
	public void setNoticeInfoDao(NoticeInfoDao noticeInfoDao) {
		this.noticeInfoDao = noticeInfoDao;
	}
	/**
	 * 新增角色
	 * @param departmentInfo
	 * @return
	 * @throws IctException
	 */
	public NoticeInfo saveNoticeInfo(NoticeInfo noticeInfo)throws IctException{
		noticeInfoDao.saveNoticeInfo(noticeInfo);
		return noticeInfo;
	}
	
	/**
	 * 更新角色
	 * @param RoleInfo
	 * @return
	 * @throws IctException
	 */
	public NoticeInfo updateNoticeInfo(NoticeInfo noticeInfo) throws IctException {
		try {
			noticeInfo = noticeInfoDao.updateNoticeInfo(noticeInfo);
			return noticeInfo;
		} catch (Exception e) {
			logger.error(e);
			throw new IctException(e);
		}
	}
	
	/**
	 * 根据角色Id删除信息
	 * @param id
	 * @throws IctException
	 */
	/*	public void deleteRoleInfoById(String id) throws IctException {
			try {
				roleInfoDao.deleteRoleInfoById(id);
			} catch (Exception e) {
				logger.error(e);
				throw new IctException(e);
			}
		}
		
	*/	public void deleteNoticeInfoByIds(String id) throws IctException {
		try {
			noticeInfoDao.deleteNoticeInfoByIds(id);
		} catch (Exception e) {
			logger.error(e);
			throw new IctException(e);
		}
	}
	
	/**
	 * 根据角色Id返回角色信息
	 * @param id
	 * @return
	 */
	public NoticeInfo findNoticeInfoById(String id){
		return noticeInfoDao.findNoticeInfoById(id);
	}
	
	public List<NoticeInfo> findNoticeInfoList(Map condition){
		return noticeInfoDao.findNoticeInfoList(condition);
	}
	
	/**
	 * 返回所有有效公告数量
	 * @return
	 */
	public long getAllNoticeNum(){
		return noticeInfoDao.getAllNoticeNum();
	}
	
	/**
	 * 返回所有公告信息
	 * @param orderby
	 * @param ascOrDesc
	 * @param pages
	 * @return
	 */
	public PageList findNoticeInfoList(String orderby, int ascOrDesc, Pages pages){
		PageList result = new PageList();
		pages.setTotal(getAllNoticeNum());
		pages.executeCount();
		result.setPages(pages);
		result.addAll(noticeInfoDao.findNoticeInfoList(orderby, ascOrDesc, pages.getStart(), pages.getLimit()));
		return result;
	}
	/**
	 * 根据条件返回公告数量
	 * @param condition
	 * @return
	 */
	public long getNoticeNumByCondition(Map condition){
		return noticeInfoDao.getNoticeNumByCondition(condition);
	}
	
	/**
	 * 根据条件返回公告信息
	 * @param condition
	 * @param pages
	 * @return
	 */
	public PageList findNoticeInfoListByCondition(Map condition,Pages pages){
		PageList result = new PageList();
		pages.setTotal(getNoticeNumByCondition(condition));
		pages.executeCount();
		result.setPages(pages);
		result.addAll(noticeInfoDao.findNoticeInfoListByCondition(condition, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	/**
	 * 根据条件返回公告信息
	 * @param condition
	 * @param pages
	 * @return
	 */
		public PageList findNoticeInfoForPageList(Map condition,Pages pages){
			PageList result = new PageList();
			pages.setTotal(getNoticeNumForPageList(condition));
			pages.executeCount();
			result.setPages(pages);
			result.addAll(noticeInfoDao.findNoticeInfoForPageList(condition, pages.getStart(), pages.getLimit()));
			return result;
		}
		
		public long getNoticeNumForPageList(Map condition){
			return noticeInfoDao.getNoticeNumForPageListByCondition(condition);
		}
		
		public List<NoticeInfo> findByCondition(String sqlMap, Map condition){
			return noticeInfoDao.findByCondition(sqlMap, condition);
		}
	}