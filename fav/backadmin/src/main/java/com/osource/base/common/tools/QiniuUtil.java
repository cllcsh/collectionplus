/**
 * @Title: QiniuUtil.java
 * @Package com.warmdoctor.ows.qiniu
 * @author bruce
 * @date 2015年5月9日 下午8:30:16
 * @version V1.0
 */
package com.osource.base.common.tools;

import com.osource.base.common.cash.CommonCash;
import com.osource.base.util.StringUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * 7牛工具类
 *
 * @author bruce
 * @ClassName: QiniuUtil
 * @date 2015年5月9日 下午8:30:16
 */
public class QiniuUtil {

    /**
     * @Fields QINIU_ACCESS_KEY : 签名
     */
    private static final String QINIU_ACCESS_KEY = "qiniu.access.key";

    /**
     * @Fields QINIU_SECRET_KEY : 验证
     */
    private static final String QINIU_SECRET_KEY = "qiniu.secret.key";

    /**
     * @Fields QINIU_BUCKETNAME : 普通上传
     */
    private static final String QINIU_BUCKETNAME = "qiniu.bucket";

    /**
     * 图片服务的名称
     */
    public static final String QINIU_URL = "path.imgWebContextPath";

    private static final long DEFAULT_QINIU_TIME = 3600 * 40;

    private static String token = "";

    private static long atime;

    private static final String DEFAULT_QINIU_URL = CommonCash.getValue(QINIU_URL);

    private static UploadManager uploadManager = new UploadManager();

    /**
     * 获取token信息
     *
     * @return String
     * @author :bruce
     * @create 2015年5月9日下午8:31:01
     */
    public static final String getToken() {
        if (!StringUtils.isEmpty(token)) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - atime) < DEFAULT_QINIU_TIME) {// 过期了 |
                return token;
            }
        }
        Auth auth = Auth.create(CommonCash.getValue(QINIU_ACCESS_KEY),
                CommonCash.getValue(QINIU_SECRET_KEY));
        token = auth.uploadToken(CommonCash.getValue(QINIU_BUCKETNAME), null, DEFAULT_QINIU_TIME, null);
        atime = System.currentTimeMillis();
        return token;
    }

    public static final Response upload(byte[] files, String fileName) {
        for (int i = 0; i < 3; i++) {
            try {
                Response response = uploadManager.put(files, fileName, getToken());
                if (response != null) {
                    if (response.isOK()) {
                        return response;
                    }
                }
                return null;
            } catch (QiniuException e) {
            }
        }
        return null;
    }

    public static final Response upload(File file,String fileName){
        for (int i = 0; i < 3; i++) {
            try {
                Response response = uploadManager.put(file, fileName, getToken());
                if (response != null) {
                    if (response.isOK()) {
                        return response;
                    }
                }
                return null;
            } catch (QiniuException e) {
            }
        }
        return null;
    }

    public static final String getQiniuUrl(String url) {
        String _url = url;
        if (StringUtils.isNotEmpty(url)) {
            if (StringUtil.isNotEmpty(DEFAULT_QINIU_URL)) {
                if ("/".equals(url.substring(0, 1))) {
                    _url = DEFAULT_QINIU_URL + url;
                } else {
                    _url = DEFAULT_QINIU_URL + '/' + url;
                }
            }
        }
        return _url;
    }

    public static final String getQiniuUrl(){
        return DEFAULT_QINIU_URL;
    }

}
