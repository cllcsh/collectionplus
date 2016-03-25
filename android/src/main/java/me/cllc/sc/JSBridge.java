package me.cllc.sc;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.QZoneShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * Created by zhangyang on 16/1/17.
 */
public class JSBridge {

    public Context ctx;

    private final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    public JSBridge(Context ctx) {
        this.ctx = ctx;
    }

    @JavascriptInterface
    public void share(String title, String body, String img, String url) {
//        Toast.makeText(this.ctx, "share@JSBridge", Toast.LENGTH_LONG).show();
        // 微信配置
        String appID = "wx37e4d31bf0789573";
        String appSecret = "63c9518ec886c523b92d821313789a66";
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this.ctx, appID, appSecret);
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(this.ctx,appID,appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler((Activity) this.ctx, "1105121716", "9JwI0F3OVMUb6pPW");
        qqSsoHandler.addToSocialSDK();
        //参数1为当前Activity，参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler((Activity) this.ctx, "1105121716", "9JwI0F3OVMUb6pPW");
        qZoneSsoHandler.addToSocialSDK();

        mController.getConfig().removePlatform(SHARE_MEDIA.TENCENT, SHARE_MEDIA.SINA);

        mController.setShareContent(title + body);
        mController.setShareImage(new UMImage(this.ctx, img));

        WeiXinShareContent weixinContent = new WeiXinShareContent();
        weixinContent.setTitle(title);
        weixinContent.setShareContent(body);
        weixinContent.setShareImage(new UMImage(this.ctx, img));
        weixinContent.setTargetUrl(url);
        mController.setShareMedia(weixinContent);

        CircleShareContent circleContent = new CircleShareContent();
        circleContent.setShareContent(body);
        circleContent.setTitle(title);
        circleContent.setTargetUrl(url);
        circleContent.setShareImage(new UMImage(this.ctx, img));
        mController.setShareMedia(circleContent);

        QQShareContent qqShareContent = new QQShareContent();
        qqShareContent.setTitle(title);
        qqShareContent.setShareContent(body);
        qqShareContent.setTargetUrl(url);
        qqShareContent.setShareImage(new UMImage(this.ctx, R.mipmap.ic_launcher));
        mController.setShareMedia(qqShareContent);

        QZoneShareContent qzoneContent = new QZoneShareContent();
        qzoneContent.setTitle(title);
        qzoneContent.setTargetUrl(url);
        qzoneContent.setShareContent(body);
        qzoneContent.setShareImage(new UMImage(this.ctx, img));
        mController.setShareMedia(qqShareContent);

        mController.openShare((Activity) this.ctx, false);
    }
}
