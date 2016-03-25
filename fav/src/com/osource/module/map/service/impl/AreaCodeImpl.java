package com.osource.module.map.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.osource.core.PropertiesManager;
import com.osource.module.map.model.AreaCodeBean;
import com.osource.module.map.service.AreaCode;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * @author : zhou hao
 * @version : 1.0
 * @date : 2009-7-13 9:56:53
 */
@Service
@Scope("prototype")
@Transactional
public class AreaCodeImpl extends BaseServiceImpl implements AreaCode
{
    private static final String NAME_URL_STRING = PropertiesManager.getProperty("common.properties", "AREACODE_URL_STRING");//"http://202.102.112.25:9600/EFService.asmx";
    private static final String CONTENT_TYPE = PropertiesManager.getProperty("common.properties", "CONTENT_TYPE");//"application/soap+xml; charset=utf-8";

    public List<AreaCodeBean> getAreaCode()
    {
    	List<AreaCodeBean> acList = new ArrayList<AreaCodeBean>();

        Document doc = getSoapInputStream();
        if (doc == null)
        {
            return null;
        }
        Element root = doc.getDocumentElement();
        NodeList nl = root.getElementsByTagName("GetAreaCodeResult");
        
        if (nl.getLength() > 0)
        {
            Element e = (Element) ((Element) ((Element) nl.item(0)).getChildNodes().item(1)).getChildNodes().item(0);
            NodeList nList = e.getChildNodes();
            
            for(int i=0; i<nList.getLength(); i++) {
//            	Element el = (Element) nList.item(i);
//            	NodeList eNList = el.getChildNodes();
            	
        		AreaCodeBean ac = new AreaCodeBean();
        	//	ac.setAreaCode(((Element) eNList.item(0)).getTextContent());
        	//	ac.setAreaName(((Element) eNList.item(1)).getTextContent());
        	//	ac.setParentCode(((Element) eNList.item(2)).getTextContent());
        		if(!"640101".equals(ac.getAreaCode())){
        			if(!"640201".equals(ac.getAreaCode())){
        				if(!"430401".equals(ac.getAreaCode())){
        					acList.add(ac);
        				}
        			}
        		}
            }
            AreaCodeBean ac0 = new AreaCodeBean();
    		ac0.setAreaCode("640104");
    		ac0.setAreaName("兴庆区");
    		ac0.setParentCode("640100");
    		 AreaCodeBean ac1 = new AreaCodeBean();
     		ac1.setAreaCode("640105");
     		ac1.setAreaName("西夏区");
     		ac1.setParentCode("640100");
     		 AreaCodeBean ac2 = new AreaCodeBean();
     		ac2.setAreaCode("640106");
     		ac2.setAreaName("金凤区");
     		ac2.setParentCode("640100");
     		
    		 AreaCodeBean ac3 = new AreaCodeBean();
      		ac3.setAreaCode("640202");
      		ac3.setAreaName("大武口区");
      		ac3.setParentCode("640200");
    		 AreaCodeBean ac4 = new AreaCodeBean();
      		ac4.setAreaCode("640205");
      		ac4.setAreaName("惠农区");
      		ac4.setParentCode("640200");
      		
	   		 AreaCodeBean ac5 = new AreaCodeBean();
	   		ac5.setAreaCode("430405");
	   		ac5.setAreaName("珠晖区");
	   		ac5.setParentCode("430400");      		
	   		 AreaCodeBean ac6 = new AreaCodeBean();
		   	ac6.setAreaCode("430406");
		   	ac6.setAreaName("雁峰区");
		   	ac6.setParentCode("430400");   
	   		 AreaCodeBean ac7 = new AreaCodeBean();		   	
		   	ac7.setAreaCode("430407");
		   	ac7.setAreaName("石鼓区");
		   	ac7.setParentCode("430400"); 
	   		 AreaCodeBean ac8 = new AreaCodeBean();			   	
		   	ac8.setAreaCode("430408");
		   	ac8.setAreaName("蒸湘区");
		   	ac8.setParentCode("430400"); 
	   		 AreaCodeBean ac9 = new AreaCodeBean();		   	
		   	ac9.setAreaCode("430408");
		   	ac9.setAreaName("南岳区");
		   	ac9.setParentCode("430400"); 		   	
		   	
     		acList.add(ac0);
     		acList.add(ac1);
     		acList.add(ac2);
     		acList.add(ac3);
     		acList.add(ac4);
     		acList.add(ac5);
     		acList.add(ac6);
     		acList.add(ac7);
     		acList.add(ac8);
     		acList.add(ac9);
            return acList;
        }
        return null;

    }

    /*
     * 返回InputStream是因为w3c DOM中Document的parse方法可
     * 以接受InputStream类型的参数，方便在下一步对XML的解释
     */
    public Document getSoapInputStream()
    {
        InputStream is = null;
        HttpURLConnection httpConn = null;
        try
        {
            String soap = getSoapRequest();
            if (soap == null)
            {
                return null;
            }
            else
            {
                URL url = new URL(NAME_URL_STRING);
                httpConn = (HttpURLConnection) url.openConnection();


                httpConn.setRequestProperty("Content-Length", String.valueOf(soap.length()));
                httpConn.setRequestProperty("Content-Type", CONTENT_TYPE);
                httpConn.setRequestMethod("POST");
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);

                OutputStream out = httpConn.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
                osw.write(soap);
                osw.flush();
                osw.close();
                out.close();
                is = httpConn.getInputStream();
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                DocumentBuilder db = dbf.newDocumentBuilder();
                return db.parse(is);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
                if (httpConn != null)
                {
                    httpConn.disconnect();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getSoapRequest()
    {
        InputStream is = null;
        try
        {
            String filePath = this.getClass().getClassLoader().getResource("").getPath() + "AreaCodeService.xml";
            String decodedFilePath = URLDecoder.decode(filePath, System.getProperties().getProperty("file.encoding"));
            is = new FileInputStream(decodedFilePath);
            InputStreamReader isr = new InputStreamReader(is);// 读取存在weathersoap的SOAP信息
            BufferedReader reader = new BufferedReader(isr);
            String soap = "";
            String tmp;
            while ((tmp = reader.readLine()) != null)
            {
                soap += tmp;
            }
            reader.close();
            isr.close();
            return soap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                if (is != null)
                {
                    is.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
