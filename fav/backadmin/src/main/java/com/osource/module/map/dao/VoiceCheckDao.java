package com.osource.module.map.dao;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.module.map.model.VoiceCheckBean;
import com.osource.module.map.web.form.VoiceCheckForm;
import com.osource.orm.ibatis.BaseDao;

public interface VoiceCheckDao extends BaseDao<VoiceCheckBean> {
	public  List<Entry<String, String>> findCriminal(VoiceCheckForm registerForm,Integer deptId);
	public String voiceReceiveTask(Integer delayHours);
}