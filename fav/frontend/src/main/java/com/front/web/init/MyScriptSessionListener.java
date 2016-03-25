package com.front.web.init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.express.util.Convert;
import org.express.util.StringUtil;

/**
 * 构建自己的ScriptSession监听器
 * @author Dengb
 *
 */
public class MyScriptSessionListener implements ScriptSessionListener {
	//存放创建的scriptsession
	public static final Map<String, ScriptSession> scriptSessionMap = new ConcurrentHashMap<String, ScriptSession>();

	@Override
	public void sessionCreated(ScriptSessionEvent event) {
		WebContext webContext = WebContextFactory.get();
        HttpSession session = webContext.getSession();
        ScriptSession scriptSession = event.getSession();
        //通过设置一个sessionId，后续再推送消息的时候，实现定向推送
        String sessionId = Convert.toString(session.getAttribute("ss_sessionId"));
        scriptSession.setAttribute("sessionId", sessionId);        
        scriptSessionMap.put(session.getId(), scriptSession);     //添加scriptSession
        System. out.println( "session: " + session.getId() + " scriptSession: " + scriptSession.getId() + "is created!");
	}

	@Override
	public void sessionDestroyed(ScriptSessionEvent event) {
		WebContext webContext = WebContextFactory.get();
		//加一个容错处理，我也有点不太明白为撒子这个webcontext有时候获取不到了
		if (webContext != null) {
			HttpSession session = webContext.getSession();
	        ScriptSession scriptSession = scriptSessionMap.remove(session.getId());  //移除scriptSession
	        System. out.println( "session: " + session.getId() + " scriptSession: " + scriptSession.getId() + "is destroyed!");
		}
	}
	
	/**
	 * 获取所有的ScriptSession
	 * @return
	 */
	public static Collection<ScriptSession> getScriptSessions(){
	        return scriptSessionMap.values();
	 }
	
	/**
	 * 获取固定的scriptSession
	 * @param sessionId
	 * @return
	 */
	public static List<ScriptSession> getScriptSession(String sessionId) {
		if (StringUtil.isNullorEmpty(sessionId)) return null;
		
		List<ScriptSession> list = new ArrayList<ScriptSession>();
		Collection<ScriptSession> collection = getScriptSessions();
		for (ScriptSession scriptSession : collection) {
			if (sessionId.equals(scriptSession.getAttribute("sessionId"))) {
				list.add(scriptSession);
			}
		}
		return list;
	}
}
