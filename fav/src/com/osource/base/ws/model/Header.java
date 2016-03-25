package com.osource.base.ws.model;

public class Header
{
	private String UserName;  //用户名
	
	private String  Password; //密码

	/**   
	 * userName   
	 *   
	 * @return  the userName   
	 * @since   CodingExample Ver(编码范例查看) 1.0   
	 */
	
	public String getUserName()
	{
		return UserName;
	}

	/**   
	 * @param userName the userName to set   
	 */
	public void setUserName(String userName)
	{
		UserName = userName;
	}

	/**   
	 * password   
	 *   
	 * @return  the password   
	 * @since   CodingExample Ver(编码范例查看) 1.0   
	 */
	
	public String getPassword()
	{
		return Password;
	}

	/**   
	 * @param password the password to set   
	 */
	public void setPassword(String password)
	{
		Password = password;
	}
	
	
}
