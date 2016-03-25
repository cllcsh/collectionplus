/**  
 * @Title: QiniuUtil.java 
 * @Package com.warmdoctor.ows.qiniu 
 * @author bruce  
 * @date 2015年5月9日 下午8:30:16 
 * @version V1.0  
 */
package org.express.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang.StringUtils;
import org.express.common.cache.CommonConfigCash;

/**
 * 7牛工具类
 * 
 * @ClassName: QiniuUtil
 * @author bruce
 * @date 2015年5月9日 下午8:30:16
 */
public class QiniuUtil {

	/**
	 * @Fields QINIU_ACCESS_KEY : 签名
	 */
	private static final String QINIU_ACCESS_KEY = "qiniu_access_key";

	/**
	 * @Fields QINIU_SECRET_KEY : 验证
	 */
	private static final String QINIU_SECRET_KEY = "qiniu_secret_key";

	/**
	 * @Fields QINIU_BUCKETNAME : 普通上传
	 */
	private static final String QINIU_BUCKETNAME = "qiniu_bucket";

	private static final long DEFAULT_QINIU_TIME = 3600 * 40;

	private static String token = "";

	private static long atime;

	private static UploadManager uploadManager = new UploadManager();

	/**
	 * 获取token信息
	 * 
	 * @author :bruce
	 * @return String
	 * @create 2015年5月9日下午8:31:01
	 */
	public static final String getToken() {
		if (!StringUtils.isEmpty(token)) {
			long currentTime = System.currentTimeMillis();
			if ((currentTime - atime) < DEFAULT_QINIU_TIME) {// 过期了 |
				return token;
			}
		}
		Auth auth = Auth.create(CommonConfigCash.getValue(QINIU_ACCESS_KEY),
				CommonConfigCash.getValue(QINIU_SECRET_KEY));
		token = auth.uploadToken(CommonConfigCash.getValue(QINIU_BUCKETNAME), null, DEFAULT_QINIU_TIME, null);
		atime = System.currentTimeMillis();
		return token;
	}

	public static final Response upload(byte[] files, String fileName){
		for (int i = 0; i < 3; i++) {
			try {
				Response response = uploadManager.put(files, fileName, getToken());
				if(response != null){
					if(response.isOK()){
						return response;
					}
				}
				return null;
			} catch (QiniuException e) {
			}
		}
		return null;
	}

}
