/**
 * DataSynImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.osource.base.ws;

import java.rmi.RemoteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.ws.model.EventInfo;
import com.osource.base.ws.model.RiskInfo;
import com.osource.base.ws.util.ErrorCodes;
import com.osource.base.ws.util.EventPackage;
import com.osource.base.ws.util.Response;
import com.osource.base.ws.util.RiskPackage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class DataSynImpl implements com.osource.base.ws.DataSynServicePortType{
	private static final Log logger = LogFactory.getLog(DataSynImpl.class);
	
    public java.lang.String synEvent(java.lang.String in0) throws java.rmi.RemoteException {
    	logger.debug("Enter func::synEvent,parater info is " + in0);
    	Response response = new Response();
    	try
    	{
	    	EventPackage eventPackage = new EventPackage();
	    	EventInfo eventInfo = eventPackage.xmlToObj(in0);
	    	//参数校验
	    	response = eventPackage.verifyPackageData(eventInfo);
	    	
	    	if (!ErrorCodes.RETURN_COMMON_SUCCESS.equals(response.getReturnStatus()))
	    	{
	    		return objToXML(response);
	    	}
	  
	    	//保存单位信息
	    	//response = EventInfoManager.getInstance().synEvent(eventInfo);
    	}
	    catch(Exception e)
	    {
	    	response.setReturnStatus(ErrorCodes.RETURN_COMMON_FAIL);
	    	response.setSummary(e.toString());
	    }
    	return objToXML(response);
    }

    public java.lang.String synRisk(java.lang.String reqXml) throws java.rmi.RemoteException {
    	logger.debug("开始同步风险数据(synRisk)，请求参数为：" + reqXml);
    	Response response = new Response();
    	try
    	{
	    	RiskPackage riskPackage = new RiskPackage();
	    	RiskInfo riskInfo = riskPackage.xmlToObj(reqXml);
	    	//参数校验
	    	response = riskPackage.verifyPackageData(riskInfo);
	    	
	    	if (!ErrorCodes.RETURN_COMMON_SUCCESS.equals(response.getReturnStatus()))
	    	{
	    		return objToXML(response);
	    	}
	  
	    	//同步风险信息
	    	//response = RiskInfoManager.getInstance().sysRisk(riskInfo);
    	}
	    catch(Exception e)
	    {
	    	response.setReturnStatus(ErrorCodes.RETURN_COMMON_FAIL);
	    	response.setSummary(e.toString());
	    	e.printStackTrace();
	    }
    	return objToXML(response);
    }

	private static String objToXML(Object obj)
	{
		
        XStream sm = new XStream(new DomDriver());   
        sm.alias("Package", obj.getClass());
        String str = sm.toXML(obj);
        logger.debug("返回结果内容包:\t" + str);
        return str;
	}
	
	public static void main(String[] args) {
		//DataSynImpl dataSyn = new DataSynImpl();
		
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Package><SessionHeader><UserName>eagleeye</UserName><Password>123456</Password></SessionHeader>";
		String reqXml = header 
						+"<SessionBody>" 
							+"<OpFlag>01</OpFlag>"
							+"<Kind>10</Kind>"
							+"<Id></Id>"
							+"<Name>操作系统漏洞 </Name>"
							+"<DeptId>1</DeptId>"
							+"<SourceId>57</SourceId>"
							+"<Description>影响操作系统安全</Description>"
							+"<SrcIp>202.102.11.101</SrcIp>"
							+"<DstIp>202.102.12.105</DstIp>"
							+"<LevelHigh></LevelHigh>"
							+"<LevelMid>12</LevelMid>"
							+"<LevelLow>5</LevelLow>"
							+"<Cent1></Cent1>"
							+"<Cent2></Cent2>"
							+"<Cent3></Cent3>"
							+"<Cent4></Cent4>"
							+"<Cent5></Cent5>"
							+"<Num></Num>"
						+"</SessionBody></Package>";
		
		System.out.println("请求XML："+reqXml);
		
		/*RiskPackage riskPackage = new RiskPackage();
    	RiskInfo riskInfo = riskPackage.xmlToObj(reqXml);
    	
    	System.out.println(objToXML(riskInfo));*/
    	
    	DataSynImpl datasyn = new DataSynImpl();
    	try {
			datasyn.synRisk(reqXml);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
	
}
