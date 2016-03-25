package com.osource.module.system.service;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.model.NoticeInfo;

@SuppressWarnings("unchecked")
public interface NoticeInfoService{
	/**
	 * 新增角色
	 * @param departmentInfo
	 * @return
	 * @throws IctException
	 */
	public NoticeInfo saveNoticeInfo(NoticeInfo noticeInfo)throws IctException;
	
	/**
	 * 更新角色
	 * @param RoleInfo
	 * @return
	 * @throws IctException
	 */
	public NoticeInfo updateNoticeInfo(NoticeInfo noticeInfo) throws IctException;
	
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
		
	*/	public void deleteNoticeInfoByIds(String id) throws IctException;
	
	/**
	 * 根据角色Id返回角色信息
	 * @param id
	 * @return
	 */
	public NoticeInfo findNoticeInfoById(String id);
	
	public List<NoticeInfo> findNoticeInfoList(Map condition);
	
	/**
	 * 返回所有有效公告数量
	 * @return
	 */
	public long getAllNoticeNum();
	
	/**
	 * 返回所有公告信息
	 * @param orderby
	 * @param ascOrDesc
	 * @param pages
	 * @return
	 */
	public PageList findNoticeInfoList(String orderby, int ascOrDesc, Pages pages);
	/**
	 * 根据条件返回公告数量
	 * @param condition
	 * @return
	 */
	public long getNoticeNumByCondition(Map condition);
	/**
	 * 根据条件返回公告信息
	 * @param condition
	 * @param pages
	 * @return
	 */
	public PageList findNoticeInfoListByCondition(Map condition,Pages pages);
	
	/**
	 * 根据条件返回公告信息
	 * @param condition
	 * @param pages
	 * @return
	 */
		public PageList findNoticeInfoForPageList(Map condition,Pages pages);
		
		public long getNoticeNumForPageList(Map condition);
		
		public List<NoticeInfo> findByCondition(String sqlMap, Map condition);
	}