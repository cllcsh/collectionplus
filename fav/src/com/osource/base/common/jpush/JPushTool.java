/**
 * 
 */
package com.osource.base.common.jpush;

import java.util.Map;

import cn.jpush.api.DeviceEnum;
import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;



/**
 * @author Fun
 *
 */
public class JPushTool {

	private static final String appKey ="43bf57e127632e93d67576cb";	//必填，每个应用都对应一个appKey

	private static final String masterSecret = "fe54ab6e7d0a62ca90ab850b";//必填，每个应用都对应一个masterSecret

	private static JPushClient jpush = null;
	
	//private int sendNo = 0;

	/**
	 * 保存离线的时长。秒为单位。最多支持10天（864000秒）。
	 * 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
	 * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒)。
	 */
	private static long timeToLive =  60 * 60 * 24;  
	
	public JPushTool(){
		jpush = new JPushClient(masterSecret, appKey, timeToLive);
	}
	
	public JPushTool(DeviceEnum device){
		 jpush = new JPushClient(masterSecret, appKey, timeToLive, device);
	}
	
	public void printResult(MessageResult msgResult){
		if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {        
			System.out.println("发送成功， sendNo=" + msgResult.getSendno());    
		} else {        
			System.out.println("发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息=" + msgResult.getErrmsg());    
		}
	}
	
	//发送带AppKey的自定义通知
	public int sendNotificationWithAppKey(String msgTitle, String msgContent){
		int sendNo = getRandomSendNo();
		MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	//发送带AppKey的自定义通知
	public int sendNotificationWithAppKey(String msgTitle, String msgContent,Map map){
		int sendNo = getRandomSendNo();
		MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent, 0, map);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	//发送带AppKey的自定义通知
	public int sendNotificationWithAppKey(int sendNo, String msgTitle, String msgContent){
		MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	//发送带ALIAS的通知
	public int sendNotificationWithAlias(String alias,String  msgTitle, String msgContent){
		int sendNo = getRandomSendNo();
		MessageResult msgResult = jpush.sendNotificationWithAlias(sendNo, alias, msgTitle, msgContent);
		
		System.out.println("sendNo="+msgResult.getSendno()+" alias:"+alias);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	//发送带tag的通知
	public int sendNotificationWithTag(String tag,String  msgTitle, String msgContent){
		int sendNo = getRandomSendNo();
		MessageResult msgResult = jpush.sendNotificationWithTag(sendNo, tag, msgTitle, msgContent);
		
		System.out.println("sendNo="+msgResult.getSendno()+" tag:"+tag);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	//发送带AppKey的自定义消息
	public int sendCustomMessageWithAppKey(String msgTitle, String msgContent){
		int sendNo = getRandomSendNo();
		MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo, msgTitle, msgContent);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	//发送带AppKey的自定义消息
	public int sendCustomMessageWithAppKey(int sendNo, String msgTitle, String msgContent){
		MessageResult msgResult = jpush.sendCustomMessageWithAppKey(sendNo, msgTitle, msgContent);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}

	
	//发送带Alias的自定义消息
	public int sendCustomMessageWithAlias(String alias,String  msgTitle, String msgContent){
		int sendNo = getRandomSendNo();
		MessageResult msgResult = jpush.sendCustomMessageWithAlias(sendNo, alias, msgTitle, msgContent);
		
		System.out.println("sendNo="+msgResult.getSendno()+" alias:"+alias);
		printResult(msgResult);
		
		return msgResult.getErrcode();
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JPushTool  jpush = new JPushTool();
		
		// 在实际业务中，建议 sendNo 是一个你自己的业务可以处理的一个自增数字。
		// 除非需要覆盖，请确保不要重复使用。详情请参考 API 文档相关说明。
		//int sendNo = getRandomSendNo();
		String imei = "";
		String msgTitle = "通知：平台端jpush通知推送";
		String msgContent = "平台端jpush通知推送测试"; 

		//jpush.sendNotificationWithAppKey(msgTitle, msgContent);
		//jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent);
		jpush.sendNotificationWithAlias("18963603569",msgTitle, msgContent);
		jpush.sendNotificationWithAlias("18951603084",msgTitle, msgContent);
		
		
		msgTitle = "消息：平台端jpush消息推送";
		msgContent = "平台端jpush消息推送测试";
		//jpush.sendCustomMessageWithAppKey(msgTitle, msgContent);
		//jpush.sendCustomMessageWithAppKey(sendNo,msgTitle, msgContent);
		
		//jpush.sendCustomMessageWithAlias("18963603569", msgTitle, msgContent);
		

	}
	
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = MAX/2; 
	
	/** * 保持 sendNo 的唯一性是有必要的 
	 * * It is very important to keep sendNo unique. 
	 * * @return sendNo 
	 * */
	public static int getRandomSendNo() {    
		return (int) (MIN + Math.random() * (MAX - MIN));
	}

}
