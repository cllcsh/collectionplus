/**
 * @author luoj
 * @create 2009-3-23
 * @file UserInfoForm.java
 * @since v0.1
 * 
 */
package com.osource.module.system.web.form;

import com.osource.base.web.form.BaseForm;

/**
 *
 */
public class ConfigInfoForm extends BaseForm {
	
	/** 配置编号*/
	private String configCode;
	
	/** 配置键*/
	private String configKey;
	
	/** 配置类型*/
	private String configType;

	/** 配置值*/
	private String configValue;
	
	/** 描述*/
	private String configDesc;
	
	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
}
