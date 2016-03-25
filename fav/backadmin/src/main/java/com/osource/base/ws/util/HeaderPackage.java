package com.osource.base.ws.util;

import org.apache.commons.digester.Digester;

import com.osource.base.ws.model.Header;


public class HeaderPackage {
	/**
	 * 把xml转换成相应对象
	 * @param xml
	 * @return
	 */
	public void xmlToHeader(Digester digester ) {
		
		digester.setValidating(false);
		digester.addObjectCreate("Package/SessionHeader", Header.class);
		 
		digester.addBeanPropertySetter("Package/SessionHeader/UserName", "userName");
		digester.addBeanPropertySetter("Package/SessionHeader/Password", "password");

		
		digester.addSetNext("Package/SessionHeader", "setHeader");
	}
	
	/**
	 * 校验数据包内容
	 * @param cr
	 * @return
	 */
	public Response verifyPackageData(Header cr){
	
		Response status = new Response();
		if(!"eagleeye".equals(cr.getUserName()) || !"123456".equals(cr.getPassword())) 
		{	
			status.setReturnStatus(ErrorCodes.RETURN_COMMON_NPARAM);
		}else{
			status.setReturnStatus(ErrorCodes.RETURN_COMMON_SUCCESS);
		}
		return status;
	}
	
}
