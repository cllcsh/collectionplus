package com.osource.module.map.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.util.Entry;
import com.osource.core.BeanProvider;
import com.osource.module.map.dao.VoiceCheckDao;
import com.osource.module.map.dao.impl.VoiceCheckDaoImpl;
import com.osource.module.map.model.VoiceCheckBean;
import com.osource.module.map.service.VoiceCheckService;
import com.osource.module.map.web.form.VoiceCheckForm;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class VoiceCheckServiceImpl extends BaseServiceImpl<VoiceCheckBean> implements VoiceCheckService {

	/** setter and getter methods **/
	
	protected VoiceCheckDao getDao() {
		return (VoiceCheckDao)super.getDao();
	}

	@Autowired
	public void setDao(VoiceCheckDao voiceCheckDao) {
		super.setDao(voiceCheckDao);
	}

	public List<Entry<String, String>> findCriminal(
			VoiceCheckForm registerForm, Integer deptId) {
		return getDao().findCriminal(registerForm,deptId);
	}

	public String voiceReceiveTask(Integer delayHours) {
		// TODO Auto-generated method stub
		return ((VoiceCheckDaoImpl)BeanProvider.getBean("voiceCheckDaoImpl")).voiceReceiveTask(delayHours);
	}
}
