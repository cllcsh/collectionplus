package com.osource.module.map.task;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class VoiceReceiveListener implements ServletContextListener{
	private Timer timer = null;
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer = new Timer(true);
	    //设置任务计划，启动和间隔时间
	    timer.schedule(new VoiceReceiveTask(),1000*60  , 1000 *60 *60);
		
	}

}
