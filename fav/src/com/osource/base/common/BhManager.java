package com.osource.base.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("unused")
public class BhManager {
	private static final Log logger = LogFactory.getLog(BhManager.class);
	private static BhManager instance;
	public static synchronized BhManager getInstance() {
		if (instance==null){
			instance = new BhManager();
//			instance.init();
		}
		return instance;
	}
	
	private Map<String, String> bh = new HashMap();
}
