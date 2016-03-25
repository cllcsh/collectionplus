package com.osource.base.web.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;


public class AjaxMessagesJson implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6349469713219615240L;

	private static final Log logger = LogFactory.getLog(AjaxMessagesJson.class);

	private JSONObject json = new JSONObject();

	public void setMessage(String codeid, String message) {
		try {
			this.json.put("codeid", codeid);
			this.json.put("message", message);
			this.json.put("text", "");
		} catch (JSONException e) {
			logger.error(e);
		}
	}

	public void setMessage(String codeid, String message, String text) {
		try {
			this.json.put("codeid", codeid);
			this.json.put("message", message);
			this.json.put("text", text);
		} catch (JSONException e) {
			logger.error(e);
		}
	}

	public String getJsonString() {
		return this.json.toString();
	}
}
