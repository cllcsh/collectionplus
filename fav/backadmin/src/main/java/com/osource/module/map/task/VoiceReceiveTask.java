package com.osource.module.map.task;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.core.BeanProvider;
import com.osource.core.PropertiesManager;
import com.osource.module.map.service.VoiceCheckService;

public class VoiceReceiveTask extends TimerTask{
	private static Log logger = LogFactory.getLog(TimerTask.class);
	@Override
	public void run() {
		VoiceCheckService voiceCheckService=BeanProvider.getBean("voiceCheckServiceImpl");
		voiceCheckService.voiceReceiveTask(PropertiesManager.getInteger("common.properties", "delayHours", 3));
		logger.info("执行   声纹识别调用接口长时间没有返回值的定时任务！");
	}

}
