package com.osource.module.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.NoticeInfoDao;
import com.osource.module.system.model.NoticeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class NoticeInfoDaoImpl extends BaseDaoImpl implements NoticeInfoDao{
	
	public NoticeInfo findNoticeInfoById(String id){
		return (NoticeInfo) queryForObject("NoticeInfo_findNoticeInfoById", id);
	}
	
	/*
	 * Map中可传入参数
	 * corpNode：法人结点
	 * status：发布状态（1-未发布，2-已发布）
	 * displayPosition：公告显示位置（1-主页面，2-登录页面）
	 */
	public List<NoticeInfo> findNoticeInfoList(Map condition){
		return (List<NoticeInfo>) queryForList("NoticeInfo_findNoticeInfo", condition);
	}
	
	public NoticeInfo saveNoticeInfo(NoticeInfo noticeInfo) throws IctException{
		insert("NoticeInfo_saveNoticeInfo", noticeInfo);
		return noticeInfo;
	}
	
	public NoticeInfo updateNoticeInfo(NoticeInfo noticeInfo) throws IctException{
		update("NoticeInfo_updateNoticeInfo", noticeInfo);
		return noticeInfo;
	}
	
	public long getAllNoticeNum(){
		int num = 0;
		num = (Integer) queryForObject("NoticeInfo_getAllNoticeNum");
		return num;
	}
	
	public List<NoticeInfo> findNoticeInfoList(String orderby, int ascOrDesc, int start, int limit){
		List<NoticeInfo> list = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (orderby != null) {
				map.put("orderby", orderby);
				map.put("ascOrDesc", ascOrDesc);
			}
			list = (List<NoticeInfo>) queryForList("NoticeInfo_findNoticeInfoList", map, start, limit);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public long getNoticeNumByCondition(Map condition){
		int noticeNum = 0;
		try {
			noticeNum = (Integer) queryForObject("NoticeInfo_getAllNoticeNumByCondition", condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticeNum;
	}
	
	/*
	 * Map中可传入参数
	 * corpNode：法人结点
	 * noticeTitle:公告标题
	 * noticeType：公告类型
	 * status：发布状态（1-未发布，2-已发布）
	 * displayPosition：公告显示位置（1-主页面，2-登录页面）
	 * startDate:有效期开始时间
	 * endDate:有效期结束时间
	 * 
	 */
	public List<NoticeInfo> findNoticeInfoListByCondition(Map condition, int start, int limit){
		return queryForList("NoticeInfo_findNoticeInfoByCondition", condition, start, limit);
	}
	
	public List<NoticeInfo> findNoticeInfoForPageList(Map condition, int start, int limit){
		return (List<NoticeInfo>) queryForList("NoticeInfo_findNoticeInfo", condition, start, limit);
	}
	
	public long getNoticeNumForPageListByCondition(Map condition){
		int noticeNum = 0;
		try {
			noticeNum = (Integer) queryForObject("NoticeInfo_getNoticeNumForPageListByCondition", condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noticeNum;
	}
	
	public void deleteNoticeInfoById(String id) throws IctException{
		try{
			if(!StringUtil.isEmpty(id)){
				if(id.indexOf(",") > -1){
					String ids = StringUtil.toSqlInStr(id,0);
					if(ids != null)
						update("NoticeInfo_deleteNoticeInfoInIds", ids);
					} 
				else{
					update("NoticeInfo_deleteNoticeInfoById", id);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteNoticeInfoByIds(String id) throws IctException{
		deleteNoticeInfoById(id);
	}

	public List<NoticeInfo> findByCondition(String sqlMap, Map condition){
		return (List<NoticeInfo>) queryForList(sqlMap, condition);
	}
}
