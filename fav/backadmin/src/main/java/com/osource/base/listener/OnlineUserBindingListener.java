/**   
 * 文件名：OnlineUserBindingListener.java   
 *   
 * 版本信息：v2.0  
 *   
 */
package com.osource.base.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.TaskExecutor;

import com.opensymphony.xwork2.ActionContext;
import com.osource.base.Constants;
import com.osource.base.common.OnlineUserManager;
import com.osource.base.service.LoginService;
import com.osource.core.exception.IctException;

/**
 * 
 * 项目名称：osource 类名称：OnlineUserBindingListener 类描述： 创建人：luoj 创建时间：2010-1-5 下午03:59:19
 * 
 * @version
 * 
 */
public class OnlineUserBindingListener implements HttpSessionBindingListener, java.io.Serializable {
    protected final Log logger = LogFactory.getLog(getClass());
    private Integer userId;
    private String sessionId;
    private Date startDate;
    private TaskExecutor threadPoolTaskExecutor;
    private LoginService loginService;
    private Integer loginId;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public OnlineUserBindingListener(Integer userId) {
        super();
        this.startDate = new Date();
        this.userId = userId;
    }

    public OnlineUserBindingListener(Integer userId, TaskExecutor threadPoolTaskExecutor, LoginService loginService,
            Integer loginId) {
        super();
        this.startDate = new Date();
        this.userId = userId;
        this.loginService = loginService;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.loginId = loginId;
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        this.sessionId = session.getId();
        ServletContext application = session.getServletContext();// 把用户名放入在线列表
        OnlineUserManager onlineUserManager = (OnlineUserManager) ActionContext.getContext().getApplication()
                .get(Constants.ONLINE_USERS_MANAGER_KEY);// 第一次使用前，需要初始化
        if (onlineUserManager == null) {
            onlineUserManager = new OnlineUserManager();
            ActionContext.getContext().getApplication().put(Constants.ONLINE_USERS_MANAGER_KEY, onlineUserManager);
            application.setAttribute(Constants.ONLINE_USERS_MANAGER_KEY, onlineUserManager);
        }
        onlineUserManager.addUser(sessionId, this.userId);

    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        OnlineUserManager onlineUserManager1 = (OnlineUserManager) application
                .getAttribute(Constants.ONLINE_USERS_MANAGER_KEY);
        // ActionContext tempcontext = ActionContext.getContext();
        // Map<String, Object> tempapplication = tempcontext.getApplication();
        // OnlineUserManager onlineUserManager = (OnlineUserManager)
        // ActionContext.getContext().getApplication().get(Constants.ONLINE_USERS_MANAGER_KEY);
        onlineUserManager1.delUser(sessionId, userId);
        final Long onLineTimes = (new Date().getTime() - startDate.getTime()) / 60000;
        final Long onLineTimesSec = (new Date().getTime() - startDate.getTime()) / 1000;
        int hour = onLineTimesSec.intValue() / 3600;
        int min = (onLineTimesSec.intValue() % 3600) / 60;
        int sec = (onLineTimesSec.intValue() % 3600) % 60;
        String temp = "";

        if (hour > 0 && min > 0 && sec > 0)
            temp = hour + "小时" + min + "分钟" + sec + "秒";
        else if (min > 0 && sec > 0)
            temp = min + "分钟" + sec + "秒";
        else if (sec > 0)
            temp = sec + "秒";
        else if (min > 0)
            temp = min + "分钟";
        else
            temp = "< 1秒";

        final String onlineTime = temp;

        if (loginService != null) {
            if (threadPoolTaskExecutor != null) {
                threadPoolTaskExecutor.execute(new Runnable() {
                    public void run() {
                        try {
                            // loginService.updateLoginTimes(userId, onLineTimes.intValue());
                            loginService.updateLoginLogTimes(loginId, onlineTime);
                        } catch (IctException e) {
                            logger.debug("保存在线时长失败![userId=" + userId + "]");
                        }
                    }
                });
            } else {
                try {
                    // loginService.updateLoginTimes(userId, onLineTimes.intValue());
                    loginService.updateLoginLogTimes(loginId, onlineTime);
                } catch (IctException e) {
                    logger.debug("保存在线时长失败![userId=" + userId + "]");
                }
            }
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OnlineUserBindingListener other = (OnlineUserBindingListener) obj;
        if (sessionId == null) {
            if (other.sessionId != null)
                return false;
        } else if (!sessionId.equals(other.sessionId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
