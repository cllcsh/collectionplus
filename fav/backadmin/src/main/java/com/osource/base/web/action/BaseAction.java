/**
 * @author luoj
 * @create 2009-3-23
 * @file BaseAction.java
 * @since v0.1
 * 
 */
package com.osource.base.web.action;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osource.base.common.tools.QiniuUtil;
import com.osource.base.util.StringUtil;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.osource.base.Constants;
import com.osource.base.desc.DescToMap;
import com.osource.base.web.UserSession;
import com.osource.base.web.ajax.AjaxMessagesJson;
import com.osource.base.web.form.BaseForm;
import com.osource.base.web.interceptor.ServiceAware;
import com.osource.base.web.report.ExportExcel;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.core.json.IgnoreNullFieldPropertyFilter;
import com.osource.core.json.JsonUtils;
import com.osource.core.model.BaseModel;
import com.osource.core.page.PageList;
import com.osource.orm.ibatis.BaseService;

/**
 * action基类
 */
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,
        ServiceAware {
    private static final long serialVersionUID = 1734324659146476385L;

    protected final Log logger = LogFactory.getLog(getClass());
	public static final int SELECT_ALL = -1;
	public static final String SELECT_ALL_STR = "-1";
    public static final String RESULT_ERROR = "error";
    public static final String RESULT_DEFAULT = "default";
    public static final String RESULT_AJAXJSON = "ajaxjson";
    public static final String RESULT_JSONSTRING = "jsonstring";
    public static final String RESULT_MAP = "map";
    public static final String RESULT_REDIRECT = "redirect";

    public static final String RESULT_CONTENT = "content";
    public static final String RESULT_CENTER = "center";
    public static final String RESULT_LEFT = "left";
    public static final String RESULT_MENU = "menu";
    public static final String RESULT_RIGHT = "right";

    public static final String RESULT_VIEW = "view";
    public static final String RESULT_EDIT = "edit";
    public static final String RESULT_INIT = "init";
    public static final String RESULT_LIST = "list";
    public static final String RESULT_SET = "set";
    public static final String RESULT_FILESET = "fileSet";

    public static final String RESULT_CATALOGINIT = "catalogInit";

    private String jsp_base = PropertiesManager.getProperty("common.properties", "JSP_BASE", "");
    private String login_style = PropertiesManager.getProperty("common.properties", "LOGIN_STYLE", "default");
    private String jsp_head_title = PropertiesManager.getProperty("common.properties", "JSP_HEAD_TITLE", "");
    protected Map<String, Object> session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected String actionName;
    protected String jsonToString;
    protected AjaxMessagesJson ajaxMessagesJson = new AjaxMessagesJson();
    private BaseForm actionForm;
    protected Map jsonResult = new HashMap();

    protected PageList pageList;
    protected int page = 1; // 要查看页码数
    protected int limit; // 每页显示条数

    protected Integer id;
    protected String ids;

    private List<BaseModel> items;

    private int force;

    private String para; // 定位终端传来的参数

    /**
     * 图片链接地址
     */
    protected String qnImageUrl = QiniuUtil.getQiniuUrl();

    public BaseForm getActionForm() {
        return actionForm;
    }

    public void setActionForm(BaseForm actionForm) {
        this.actionForm = actionForm;
    }

    /**
     * 返回json格式数据串
     * 
     * @param obj
     * @return
     */
    public String returnJsonString(Object obj) {
        JSON json = JSONSerializer.toJSON(obj);
        this.setJsonToString(json.toString());
        return RESULT_JSONSTRING;
    }

    /**
     * 
     * 将Json对象中的日期转换成字符串，并将值为空的字段过滤掉
     */
    public String returnJsonStringDes(Object obj, String[] excludes) {
        List<PropertyFilter> filters = new ArrayList();
        filters.add(new IgnoreNullFieldPropertyFilter());
        JSON json = JSONSerializer.toJSON(obj, JsonUtils.configJson(excludes, filters, "yyyy-MM-dd HH:mm:ss"));

        // try {
        // this.setJsonToString(SimpleDes.encrypt(json.toString(), DataDirection.TO_MOBILE));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        this.setJsonToString(json.toString().replaceAll("\\(", "（").replaceAll("\\)", "）"));
        logger.debug("返回Json字符串：" + this.getJsonToString());

        return RESULT_JSONSTRING;
    }

    /**
     * 
     * 将Json对象中的日期转换成字符串
     */
    public String returnJsonStringDateStr(Object obj, String[] excludes) {
        List<PropertyFilter> filters = new ArrayList();
        JSON json = JSONSerializer.toJSON(obj, JsonUtils.configJson(excludes, filters, "yyyy-MM-dd HH:mm:ss"));

        // try {
        // this.setJsonToString(SimpleDes.encrypt(json.toString(), DataDirection.TO_MOBILE));
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        this.setJsonToString(json.toString().replaceAll("\\(", "（").replaceAll("\\)", "）"));
        logger.debug("返回Json字符串：" + this.getJsonToString());

        return RESULT_JSONSTRING;
    }

    /**
     * 根据手机终端传过来的参数，进行解码
     */
    public Map getParaMap() {
        Map<String, String> map = new HashMap<String, String>();
        logger.debug("para: " + para);
        if (para != null && !"".equals(para)) {
            map = DescToMap.getPostPara(para);
            return map;
        } else {
            return null;
        }
    }

    /**
     * 取得当前用户信息
     * 
     * @return UserSession
     */
    public UserSession getUserSession() {
        return (UserSession) session.get(Constants.USER_SESSION_KEY);
    }

    public String exportExcel(String filename, Map dataMap, String model) throws IctException {
        ExportExcel exportExcel = new ExportExcel();
        exportExcel.export(filename, dataMap, model, response);
        return null;
    }

    public void pushValueStack(String key, Object value) {
        ActionContext.getContext().put(key, value);
    }

    public void injectUserSession() throws IctException {
        Field[] fds = this.getClass().getDeclaredFields();
        for (Field fd : fds) {
            try {
                fd.setAccessible(true);
                if (fd.get(this) instanceof BaseService) {
                    BaseService s = (BaseService) fd.get(this);
                    s.setUserSession(getUserSession());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.debug(this.getClass() + " => field " + fd.getName() + " IllegalAccess\r\n" + e);
                throw new IctException(e);
            }
        }
    }

    /**
     * 设置cookie
     * 
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     */
    public void addCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/;HTTPOnly");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        } else {
            cookie.setMaxAge(60 * 60 * 24 * 7);// 默认一周
        }
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     * 
     * @param name
     *            cookie名字
     * @return
     */
    public String getCookieByName(String name) {
        Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
    
    public void putConditonMap(Map condition, String key, Object id, boolean select){
    	if (null == id){
    		return;
    	}
    	if (!select){
    		if (id instanceof String){
    			if ("".equals(((String) id).trim())){
    				return;
    			}
    		}
    		condition.put(key, id);
    	}else {
    		if (id instanceof Integer){
    			if (SELECT_ALL != (Integer)id){
    				condition.put(key, id);
    			}
    		}else {
    			if (!SELECT_ALL_STR.equals(id)){
    				condition.put(key, id);
    			}
    		}
		}
    }

    /* getter and setter */

    public String getJsp_base() {
        return jsp_base;
    }

    public void setJsp_base(String jspBase) {
        jsp_base = jspBase;
    }

    public String getLogin_style() {
        return login_style;
    }

    public void setLogin_style(String loginStyle) {
        login_style = loginStyle;
    }

    public String getJsp_head_title() {
        return jsp_head_title;
    }

    public void setJsp_head_title(String jspHeadTitle) {
        jsp_head_title = jspHeadTitle;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getJsonToString() {
        return jsonToString;
    }

    public void setJsonToString(String jsonToString) {
        this.jsonToString = jsonToString;
    }

    public AjaxMessagesJson getAjaxMessagesJson() {
        return ajaxMessagesJson;
    }

    public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
        this.ajaxMessagesJson = ajaxMessagesJson;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public PageList getPageList() {
        return pageList;
    }

    public void setPageList(PageList pageList) {
        this.pageList = pageList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<BaseModel> getItems() {
        return items;
    }

    public void setItems(List<BaseModel> items) {
        this.items = items;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getQnImageUrl() {
        return qnImageUrl;
    }

    public void setQnImageUrl(String qnImageUrl) {
        this.qnImageUrl = qnImageUrl;
    }
}
