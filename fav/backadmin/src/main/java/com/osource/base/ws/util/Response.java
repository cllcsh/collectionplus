package com.osource.base.ws.util;

public class Response
{
	private String ReturnStatus = ErrorCodes.RETURN_COMMON_SUCCESS; //返回码
	
	private String Summary = "操作成功"; //描述信息

	/**   
	 * returnStatus   
	 *   
	 * @return  the returnStatus   
	 * @since   CodingExample Ver(编码范例查看) 1.0   
	 */
	
	public String getReturnStatus()
	{
		return ReturnStatus;
	}

	/**   
	 * @param returnStatus the returnStatus to set   
	 */
	public void setReturnStatus(String returnStatus)
	{
		ReturnStatus = returnStatus;
	}

	/**   
	 * summary   
	 *   
	 * @return  the summary   
	 * @since   CodingExample Ver(编码范例查看) 1.0   
	 */
	
	public String getSummary()
	{
		return Summary;
	}

	/**   
	 * @param summary the summary to set   
	 */
	public void setSummary(String summary)
	{
		Summary = summary;
	}
	
	
}
