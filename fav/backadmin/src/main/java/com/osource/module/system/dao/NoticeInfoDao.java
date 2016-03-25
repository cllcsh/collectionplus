package com.osource.module.system.dao;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.NoticeInfo;

@SuppressWarnings("unchecked")
public interface NoticeInfoDao {
	public NoticeInfo findNoticeInfoById(String id);

	public List<NoticeInfo> findNoticeInfoList(Map condition);

	public List<NoticeInfo> findNoticeInfoForPageList(Map condition, int start,
			int limit);

	public long getNoticeNumForPageListByCondition(Map condition);

	public NoticeInfo saveNoticeInfo(NoticeInfo noticeInfo) throws IctException;

	public NoticeInfo updateNoticeInfo(NoticeInfo noticeInfo)
			throws IctException;

	public long getAllNoticeNum();

	public List<NoticeInfo> findNoticeInfoList(String orderby, int ascOrDesc,
			int start, int limit);

	public long getNoticeNumByCondition(Map condition);

	public List<NoticeInfo> findNoticeInfoListByCondition(Map condition,
			int start, int limit);

	public void deleteNoticeInfoById(String id) throws IctException;

	public void deleteNoticeInfoByIds(String id) throws IctException;
	
	public List<NoticeInfo> findByCondition(String sqlMap, Map condition);

}
