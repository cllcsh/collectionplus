/**
 * 
 */
package com.osource.base.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author yangsen
 * 
 */
public class SmsSenderClient {
    private static final Log logger = LogFactory.getLog(SmsSenderClient.class);
    private static final String url = "http://api.cnsms.cn";
    private static final String userName = "108262";
    private static final String password = "748b35b0fe6e52f0d2d960f2450096e6";

    /**
     * 获取url
     * 
     * @param type
     *            请求类型：0，发送短信；1，接收回复短信；2，取剩余短信条数
     * @return
     */
    private static String getUrlType(int type) {
        StringBuffer sb = new StringBuffer();
        if (type == 0) {
            sb.append("ac=send");
        } else if (type == 1) {
            sb.append("ac=gr");
        } else if (type == 2) {
            sb.append("ac=gc");
        } else {
            sb.append("ac=gp");
        }
        sb.append("&uid=");
        sb.append(userName);
        sb.append("&pwd=");
        sb.append(password);

        return sb.toString();
    }

    /**
     * 短信发送
     * 
     * @return
     */
    public static String sendMessage(String mobile, String content) {
        StringBuffer sb = new StringBuffer(getUrlType(0));
        try {
            sb.append("&mobile=");
            sb.append(mobile);
            sb.append("&content=");
            sb.append(URLEncoder.encode(content, "GBK"));
        } catch (Exception e) {
            logger.info("内容编码出现异常，短信发送失败。");
        }
        logger.info("发送短信：" + mobile + "---->" + content);
        return sendPost(sb.toString());
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private static String sendPost(String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.info("出现异常，短信发送失败。");
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String result = SmsSenderClient.sendMessage("18951603281", "恭喜发车短信测试");
        System.out.println(result);
    }
}
