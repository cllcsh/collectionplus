package com.osource.module.map.web.session;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.osource.base.web.session.UsInitializer;
import com.osource.core.AbstractUserSession;
import com.osource.module.map.dao.CenterPointDao;

/**
 * @author zhouhao
 * 
 */
@Repository("centerPointUsInitializer")
public class CenterPointUsInitializer implements UsInitializer {
    @Autowired
    private CenterPointDao centerPointDao;
    protected Map<String, Object> session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public CenterPointDao getCenterPointDao() {
        return centerPointDao;
    }

    public void setCenterPointDao(CenterPointDao centerPointDao) {
        this.centerPointDao = centerPointDao;
    }

    /*
     * (non-Javadoc)
     * @see com.osource.base.web.session.UsInitializer#initialize(com.osource.base.web.UserSession)
     */
    public void initialize(AbstractUserSession userSession) {
        /**
         * CenterPointBean cpi = centerPointDao.findCenterPointByUserId(userSession.getUserId()); if(cpi == null){ cpi =
         * new CenterPointBean(); cpi.setLatitude(Double.parseDouble(PropertiesManager.getProperty("common.properties",
         * "CENTER_LATITUDES"))); cpi.setLongitude(Double.parseDouble(PropertiesManager.getProperty("common.properties",
         * "CENTER_LONGITUDES")));
         * cpi.setZoomLevel(Double.parseDouble(PropertiesManager.getProperty("common.properties", "CENTER_ZOOMLEVEL")));
         * } CenterPointUserSession centerPointUserSession; if(userSession.match(CenterPointUserSession.class)){
         * centerPointUserSession = (CenterPointUserSession) userSession.getUserSession(CenterPointUserSession.class); }
         * else { centerPointUserSession = new CenterPointUserSession(userSession.getUserSession());
         * userSession.setUserSession(centerPointUserSession); } centerPointUserSession.setCenterPointInfo(cpi);
         **/
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
