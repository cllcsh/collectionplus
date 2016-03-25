/**   
 * 文件名：ManagerListAction.java   
 *   
 * 版本信息： 2.0  
 *   
 */
package com.osource.base.web.action;

//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import com.osource.core.BeanProvider;
//import com.osource.base.web.ajax.AjaxMessagesJson;
//import com.osource.module.system.model.UserEntity;
//import com.osource.module.system.service.UserService;

/**   
 *    
 * 项目名称：
 * 类名称：ManagerListAction   
 * 类描述：根据给出的机构编号，返回管理人员列表
 * 创建人：  
 * 创建时间：2009-11-3 下午04:16:23   
 * 修改人：  
 * 修改时间：2009-11-3 下午04:16:23   
 * 修改备注：   
 * @version    
 *    
 */
public class ManagerListAction extends BaseAction {
//	private static final long serialVersionUID = -6719190908742748625L;
//	private static final Logger logger = Logger.getLogger(ManagerListAction.class);
//	
//	private AjaxMessagesJson ajaxMessagesJson = new AjaxMessagesJson();
//	private UserService userService = BeanProvider.getBean("userService");
//	private Integer deptId;
//
//	public AjaxMessagesJson getAjaxMessagesJson() {
//		return ajaxMessagesJson;
//	}
//
//	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
//		this.ajaxMessagesJson = ajaxMessagesJson;
//	}
//
//	public Integer getDeptId() {
//		return deptId;
//	}
//
//	public void setDeptId(Integer deptId) {
//		this.deptId = deptId;
//	}
//
//	public UserService getUserService() {
//		return userService;
//	}
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//
//	public String execute() throws Exception {
//		try {
//			StringBuffer sb = new StringBuffer();
//			if(deptId == null) {
//				deptId = this.getUserSession().getDeptId();
//			}
//			List<UserEntity> userList = userService.queryUserListByDeptId(deptId);
//			
//			/**
//			 * 将返回的管理员拼装成下拉框
//			 */
//			sb.append( "<select id=\"managerList").append("\" name=\"managerList").append("\"");
//			sb.append(">");
//			sb.append("<option value=\"\"> </option>");
//			for(UserEntity userEntity : userList) {
//				sb.append( "<option value=\"").append(userEntity.getId()).append("\"");
//				sb.append(">").append(userEntity.getName()).append("</option>");
//			}
//			sb.append("</select>");
//			
//			this.ajaxMessagesJson.setMessage("0", sb.toString());
//		} catch(Exception e) {
//			logger.error(e);
//			this.ajaxMessagesJson.setMessage("error", "");
//		}
//		return RESULT_AJAXJSON;
//	}

}
