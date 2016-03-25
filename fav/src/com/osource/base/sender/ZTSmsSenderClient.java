/**
 * 
 */
package com.osource.base.sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 助通科技短信发送
 * 
 * @author yangsen
 * 
 */
public class ZTSmsSenderClient {
    private static Logger logger = LoggerFactory.getLogger(ZTSmsSenderClient.class);

    // 发送传递的参数
    // http://www.ztsms.cn:8800/sendXSms.do?username=用户名&password=密码&mobile=手机号码&content=内容&dstime=&productid=产品ID&xh=留空
    private static String username = "gxfache";
    private static String password = "83vxTfcz";
    private static Integer productid;
    private static String xh = "";
    private static String sendurl = "http://www.ztsms.cn:8800/";

    /**
     * 创建一个新的实例 SMSSender. 助通科技提供的用户名与地址
     */
    /****
     * username 用户名（必填） password 密码（必填） mobile 手机号，多个手机号为用半角 , 分开，如13899999999,13688888888(最多100个，必填) content 发送内容（必填）
     * dstime 定时时间，为空时表示立即发送（选填） productid 产品id(必填) xh 扩展号,留空 *
     **/

    public ZTSmsSenderClient() {
        username = "gxfache"; // 您的用户名
        password = "83vxTfcz"; // 密码
        sendurl = "http://www.ztsms.cn:8800/"; // 助通提供的发送地址
        productid = 95533; // 产品ID号
        xh = ""; // 扩展号留空
    }

    /**
     * 
     * @param mobile
     * @param content
     * @param type
     *            发送类型，1：验证码；2：通知
     * @return
     */
    public static String sendSms(String mobile, String content, String type) {
        if (StringUtils.equals("1", type)) {
            productid = 95533;
        } else if (StringUtils.equals("2", type)) {
            productid = 333333;
        }
        String sendUrl = null;
        try {// 否则发到手机乱码
            sendUrl = sendurl + "sendXSms.do?username=" + username + "&password=" + password + "&mobile=" + mobile
                    + "&content=" + URLEncoder.encode(content, "UTF-8") + "&productid=" + productid + "&xh=" + xh;
        } catch (UnsupportedEncodingException uee) {
            logger.error(uee.toString());
        }

        logger.info("短信内容为------------->:" + content);
        return getUrl(sendUrl);
    }

    /**
     * @Title: getUrl
     * @Description: 获取地址
     * @param urlString
     * @return
     */

    public static String getUrl(String urlString) {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setReadTimeout(15000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            for (String line = null; (line = reader.readLine()) != null;) {
                sb.append(line + "\n");
            }
            reader.close();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        String result = "";
        try {
            result = URLDecoder.decode(sb.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }
}