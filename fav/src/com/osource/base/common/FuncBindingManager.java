/**
 * @author luoj
 * @create 2009-7-2
 * @file FuncBindingManager.java
 * @since v0.1
 * 
 */
package com.osource.base.common;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.common.menu.FuncNode;

/**
 * @author luoj
 *
 */
public class FuncBindingManager {
	
	private static final Log logger = LogFactory.getLog(FuncBindingManager.class);
	
	private Map<String, String> funcBindingMap = new HashMap();
	
	public void regedit(String sub, String primary){
		funcBindingMap.put(sub, primary);
		logger.debug(MessageFormat.format("绑定 {0} 到 {1}",sub,primary));
	}
	
	public void regedit(FuncNode primaryNode){
		List<FuncNode> list = primaryNode.getChildren();
		for(FuncNode funcNode : list){
			regedit(funcNode.getLink(), primaryNode.getLink());
		}
	}
	
	
	public String getBinding(String funcUrl){
		return funcBindingMap.get(funcUrl);
	} 

}
