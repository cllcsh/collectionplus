package com.front.web.init;

import org.directwebremoting.impl.DefaultScriptSessionManager;

/**
 * 实现自己的scriptsession管理器
 * @author Dengb
 *
 */
public class MyScriptSessionManager extends DefaultScriptSessionManager {
	public MyScriptSessionManager() {
		this.addScriptSessionListener(new MyScriptSessionListener());
		//我们是无刷新的，只有把这个设置长点，默认5分钟，只有刷新的时候才会重新创建一个scriptsession，如果不刷新就一直是5分钟
		this.setScriptSessionTimeout(24 * 60 * 60 *1000);
		System. out.println( "bind MyScriptSessionListener");
	}
}
