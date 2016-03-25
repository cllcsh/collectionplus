/**
 * 
 */
package com.osource.base.sender;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import com.osource.module.babystory.model.PhoneMessageInfo;
//import com.osource.module.babystory.service.PhoneMessageService;
import com.osource.sms.send.Sender;

/**
 * 企信通短信发送
 * 
 * @author yangsen
 * 
 */
public class SmsSender implements Sender {
    private static final Log logger = LogFactory.getLog(SmsSender.class);
    @Autowired
//    private PhoneMessageService phoneMessageService;

    /*
     * (non-Javadoc)
     * @see com.osource.sms.send.Sender#send(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
     */
    public String send(String tel, String message, String senderName, Integer smsId) {
        String result = SmsSenderClient.sendMessage(tel, message);
//        PhoneMessageInfo phoneMessageInfo = new PhoneMessageInfo();
//        phoneMessageInfo.setContent(message);
        /**
         * phoneMessageInfo.setMessageDate(new Date()); phoneMessageInfo.setMessageModel(0);
         * phoneMessageInfo.setMessageType(smsId); phoneMessageInfo.setMobile(tel);
         * phoneMessageInfo.setResultType(result);
         **/
        try {
//            phoneMessageService.save(phoneMessageInfo);
        } catch (Exception e) {
            logger.info("保存数据库失败");
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.osource.sms.send.Sender#send(java.util.List, java.lang.String, java.lang.String, java.lang.Integer)
     */
    public String send(List<String> tel, String message, String senderName, Integer smsId) {
        String mobile = StringUtils.join(tel, ",");
        String result = SmsSenderClient.sendMessage(mobile, message);
//        PhoneMessageInfo phoneMessageInfo = new PhoneMessageInfo();
//        phoneMessageInfo.setContent(message);
        /**
         * phoneMessageInfo.setMessageDate(new Date()); phoneMessageInfo.setMessageModel(0);
         * phoneMessageInfo.setMessageType(smsId); phoneMessageInfo.setMobile(mobile);
         * phoneMessageInfo.setResultType(result);
         **/
        try {
//            phoneMessageService.save(phoneMessageInfo);
        } catch (Exception e) {
            logger.info("保存数据库失败");
        }
        return result;
    }

    /**
     * @return the phoneMessageService
     */
//    public PhoneMessageService getPhoneMessageService() {
//        return phoneMessageService;
//    }

    /**
     * @param phoneMessageService
     *            the phoneMessageService to set
     */
//    public void setPhoneMessageService(PhoneMessageService phoneMessageService) {
//        this.phoneMessageService = phoneMessageService;
//    }
}
