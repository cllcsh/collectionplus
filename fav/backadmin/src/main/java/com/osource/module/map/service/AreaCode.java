package com.osource.module.map.service;

import java.util.List;

import org.w3c.dom.Document;

import com.osource.module.map.model.AreaCodeBean;

/**
 * @author : zhou hao
 * @version : 1.0
 * @date : 2009-7-13 9:56:53
 */
public interface AreaCode
{

    public List<AreaCodeBean> getAreaCode();

    /*
     * 返回InputStream是因为w3c DOM中Document的parse方法可
     * 以接受InputStream类型的参数，方便在下一步对XML的解释
     */
    public Document getSoapInputStream();
   
    public String getSoapRequest();

//    public static void main(String[] args)
//    {
//    	AreaCode placename = new AreaCode();
//    	List<AreaCodeBean> acList = placename.getAreaCode();
//    	System.out.println(acList.size());
//    	for(AreaCodeBean acb : acList) {
//    		System.out.println(acb.getAreaCode());
//    		System.out.println(acb.getAreaName());
//    		System.out.println(acb.getParentCode());
//    		System.out.println("-----");
//    	}
//    }
}
