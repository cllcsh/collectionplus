package com.osource.module.admin.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**   
 *    
 * 项目名称：osource   
 * 类名称：LoginLogForm   
 * 类描述：   
 * 创建人：zhangyan   
 * 创建时间：Nov 4, 2009 6:07:37 PM   
 * 修改人：Administrator   
 * 修改时间：Nov 4, 2009 6:07:37 PM   
 * 修改备注：   
 * @version    
 *    
 */
public class LoginLogForm extends BaseForm {
	
	private String loginName;
	private Date  loginDate;
	private String loginIp;
	private String loginResult;
	private String loginAddr;
	private Date  loginFirDate;
	private Date  loginEndDate;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginAddr() {
		return loginAddr;
	}
	public void setLoginAddr(String loginAddr) {
		this.loginAddr = loginAddr;
	}
	public Date getLoginFirDate() {
		return loginFirDate;
	}
	public void setLoginFirDate(Date loginFirDate) {
		this.loginFirDate = loginFirDate;
	}
	public Date getLoginEndDate() {
		return loginEndDate;
	}
	public void setLoginEndDate(Date loginEndDate) {
		this.loginEndDate = loginEndDate;
	}

}
