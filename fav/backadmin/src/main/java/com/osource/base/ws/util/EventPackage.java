package com.osource.base.ws.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.osource.base.ws.model.EventInfo;
import com.osource.base.ws.model.NodeEventInfo;

public class EventPackage extends HeaderPackage
{
	public EventInfo xmlToObj(String xml) {
		Digester digester = new Digester();
		
		digester.addObjectCreate("Package", EventInfo.class);
		super.xmlToHeader(digester);
		digester.addBeanPropertySetter("Package/SessionBody/OpFlag", "opFlag");
		digester.addBeanPropertySetter("Package/SessionBody/DeptId", "deptId");
		digester.addBeanPropertySetter("Package/SessionBody/Id", "id");
		digester.addBeanPropertySetter("Package/SessionBody/Name", "name");
		digester.addBeanPropertySetter("Package/SessionBody/SrcIp", "srcIp");
		digester.addBeanPropertySetter("Package/SessionBody/DstIp", "dstIp");
		digester.addBeanPropertySetter("Package/SessionBody/EffectIp", "effectIp");	
		digester.addBeanPropertySetter("Package/SessionBody/TypeId", "typeId");
		
		digester.addObjectCreate("Package/SessionBody/NodeEvent", NodeEventInfo.class);
		digester.addBeanPropertySetter("Package/SessionBody/NodeEvent/NodID", "nodID");
		digester.addBeanPropertySetter("Package/SessionBody/NodeEvent/EffectClassify", "effectClassify");
		digester.addSetNext("Package/SessionBody/NodeEvent", "addNodeEventInfo");
		
		InputStream is = new ByteArrayInputStream(xml.getBytes());
		EventInfo p = null;
		try {
			p = (EventInfo) digester.parse(is);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	//参数校验
	public Response verifyPackageData(EventInfo cr){
		return super.verifyPackageData(cr.getHeader());
	}
}
