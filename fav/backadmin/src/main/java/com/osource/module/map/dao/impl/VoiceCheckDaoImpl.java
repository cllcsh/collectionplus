package com.osource.module.map.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.base.util.Entry;
import com.osource.module.map.dao.VoiceCheckDao;
import com.osource.module.map.model.VoiceCheckBean;
import com.osource.module.map.web.form.VoiceCheckForm;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class VoiceCheckDaoImpl extends BaseDaoImpl<VoiceCheckBean> implements VoiceCheckDao {
	@Override
	public String getEntityName() {
		return "map_voiceCheck";
	}

	public List<Entry<String, String>> findCriminal(
			VoiceCheckForm registerForm, Integer deptId) {
		Map map=new HashMap();
		map.put("name", registerForm.getCriminalName().trim());
		map.put("deptId", registerForm.getDeptId().toString());
		map.put("allCriminalId", registerForm.getAllCriminalId());
		List<Entry<String, String>> list=queryForList("map_voiceCheck_findCriminal",map);
		return list;
	}

	public String voiceReceiveTask(Integer delayHours) {
		Map map=new HashMap();
		map.put("old", "3");
		map.put("new", "5");
		map.put("delayHours", delayHours);
		getSqlMapClientTemplate().update("map_voice_voiceReceiveTask", map);
		map.put("old", "6");
		map.put("new", "8");
		getSqlMapClientTemplate().update("map_voice_voiceReceiveTask", map);
		map.put("old", "9");
		map.put("new", "91");
		getSqlMapClientTemplate().update("map_voice_voiceReceiveTask",map);
		map.put("old", "6");
		map.put("new", "7");
		getSqlMapClientTemplate().update("map_voiceCheck_voiceReceiveTask",map);
		return null;
	}
	
}