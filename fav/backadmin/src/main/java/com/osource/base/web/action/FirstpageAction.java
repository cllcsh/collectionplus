package com.osource.base.web.action;


public class FirstpageAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	public String init(){
		return RESULT_INIT;
	}
	

	public String list(){
		return RESULT_LIST;
	}
	
	public String index(){
		return "index";
	}
	
	public String navi(){
		return "navi";
	}
}
