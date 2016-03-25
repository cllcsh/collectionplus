/**
 * @author luoj
 * @create 2009-7-2
 * @file FuncInitializer.java
 * @since v0.1
 * 
 */
package com.osource.base.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osource.base.common.FuncManager;
import com.osource.core.init.ServletInitializer;

/**
 * @author luoj
 *
 */
@Component("funcInitializer")
public class FuncInitializer implements ServletInitializer {
	@Autowired
	private FuncManager funcManager;
	/* (non-Javadoc)
	 * @see com.osource.base.web.init.ServletInitializer#initialize(javax.servlet.ServletContextEvent)
	 */
	public void initialize(ServletContextEvent sce) throws ServletException {
//		FuncManager.getInstance();
	}
	public FuncManager getFuncManager() {
		return funcManager;
	}
	public void setFuncManager(FuncManager funcManager) {
		this.funcManager = funcManager;
	}
}
