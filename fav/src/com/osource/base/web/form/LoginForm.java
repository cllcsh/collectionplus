/**
 * 
 */
package com.osource.base.web.form;

import java.io.Serializable;

/**
 * @author Admin
 *
 */
public class LoginForm implements Serializable{
	private String loginname;
	private String password;
	private String authcode;//验证码
	private String smpassword; //短信密码
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	/**   
	 * smpassword   
	 *   
	 * @return  the smpassword   
	 * @since   CodingExample Ver(编码范例查看) 1.0   
	 */
	
	public String getSmpassword()
	{
		return smpassword;
	}
	/**   
	 * @param smpassword the smpassword to set   
	 */
	public void setSmpassword(String smpassword)
	{
		this.smpassword = smpassword;
	}
	
}
