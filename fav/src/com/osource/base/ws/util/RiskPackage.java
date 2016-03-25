package com.osource.base.ws.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.osource.base.ws.model.RiskInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class RiskPackage extends HeaderPackage
{
	public RiskInfo xmlToObj(String xml) {
		Digester digester = new Digester();
		
		digester.addObjectCreate("Package", RiskInfo.class);
		super.xmlToHeader(digester);
		digester.addBeanPropertySetter("Package/SessionBody/Id", "id");
		digester.addBeanPropertySetter("Package/SessionBody/OpFlag", "opFlag");
		digester.addBeanPropertySetter("Package/SessionBody/Kind", "kind");
		digester.addBeanPropertySetter("Package/SessionBody/Name", "name");
		digester.addBeanPropertySetter("Package/SessionBody/DeptId", "deptId");
		digester.addBeanPropertySetter("Package/SessionBody/SourceId", "sourceId");
		digester.addBeanPropertySetter("Package/SessionBody/Description", "description");
		digester.addBeanPropertySetter("Package/SessionBody/SrcIp", "srcIp");
		digester.addBeanPropertySetter("Package/SessionBody/DstIp", "dstIp");
		digester.addBeanPropertySetter("Package/SessionBody/LevelHigh", "levelHigh");
		digester.addBeanPropertySetter("Package/SessionBody/LevelMid", "levelMid");
		digester.addBeanPropertySetter("Package/SessionBody/LevelLow", "levelLow");
		digester.addBeanPropertySetter("Package/SessionBody/Cent1", "cent1");
		digester.addBeanPropertySetter("Package/SessionBody/Cent2", "cent2");
		digester.addBeanPropertySetter("Package/SessionBody/Cent3", "cent3");
		digester.addBeanPropertySetter("Package/SessionBody/Cent4", "cent4");
		digester.addBeanPropertySetter("Package/SessionBody/Cent5", "cent5");
		digester.addBeanPropertySetter("Package/SessionBody/Num", "num");
		
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		RiskInfo p = null;
		try {
			p = (RiskInfo) digester.parse(is);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	//参数校验
	public Response verifyPackageData(RiskInfo cr){
		return super.verifyPackageData(cr.getHeader());
	}
	
	private static String objToXML(Object obj)
	{
		
        XStream sm = new XStream(new DomDriver());   
        sm.alias("Package", obj.getClass());
        String str = sm.toXML(obj);
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
		RiskPackage riskPackage = new RiskPackage();
    	RiskInfo riskInfo = riskPackage.xmlToObj(reqXml);
    	
    	System.out.println(objToXML(riskInfo));
	}
}
