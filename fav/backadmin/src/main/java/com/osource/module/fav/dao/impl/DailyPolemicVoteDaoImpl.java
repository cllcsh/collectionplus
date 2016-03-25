package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DailyPolemicVoteInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DailyPolemicVoteDao;

@Repository
public class DailyPolemicVoteDaoImpl extends BaseDaoImpl<DailyPolemicVoteInfo> implements DailyPolemicVoteDao {
	@Override
	public String getEntityName() {
		return "fav_dailyPolemicVote";
	}
	
}