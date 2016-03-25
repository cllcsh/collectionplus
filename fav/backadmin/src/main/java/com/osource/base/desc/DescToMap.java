package com.osource.base.desc;

import java.util.HashMap;
import java.util.Map;


public class DescToMap {
	
	public static Map<String, String> getPostPara(String content) {
		Map<String, String> paras = new HashMap<String, String>();
		try {
			// 解密
		    content = SimpleDes.decrypt(content, DataDirection.FROM_MOBILE);
			if (null != content && !"".equals(content)) {
				if (content.indexOf(";") != -1) {
					String[] rs = content.split(";");
					for (int i = 0; i < rs.length; i++) {
						if (rs[i].indexOf("=") == -1) {
							continue;
						}
						String[] temp = rs[i].split("=");
						if (temp.length > 1) {
							//modify by wangbing
							 //增加替换等于号和分号=(%3d);(%3b)
							String str=temp[1].replaceAll("%3d", "=");
							str=str.replaceAll("%3b", ";");
							paras.put(temp[0],str);
						}
					}
				} else {
					if (content.indexOf("=") != -1) {
						String[] temp = content.split("=");
						if (temp.length > 1) {
							String str=temp[1].replaceAll("%3d", "=");
							str=str.replaceAll("%3b", ";");
							paras.put(temp[0],str);
						}
					}
				}
			}
		} catch (Exception e) {
			//logger.error(e.getMessage());
		}
		return paras;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//String content = "userId=0;deptId=0;phoneType=XT800;systemVersion=android4.0;actionName=getTraceList;locationNum=15312620311;flag=0;startDate=2013-05-01;endDate=2013-05-14;page=2;limit=10;viewType=2;";
			//String content = "userId=0;deptId=0;phoneType=XT800;systemVersion=android4.0;actionName=getTraceList;locationNum=15312620311;flag=0;startDate=2013-06-05;endDate=2013-06-05;viewType=1;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			//String content = "userId=0;deptId=0;phoneType=XT800;systemVersion=android4.0;actionName=findAlarmInfo;page=3;limit=20;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			//String content = "userId=0;deptId=0;phoneType=XT800;systemVersion=android4.0;actionName=setAlarmStatus;id=1120846;status=2;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			String content = "userId=0;deptId=0;phoneType=;systemVersion=;actionName=terminalQuery;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=;param1=;param2=;param3=;page=1;limit=10;";
			//String content = "userId=28136;deptId=27871;phoneType=XT820;systemVersion=android4.2;actionName=findUserInfo;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			//String content = "userId=28136;deptId=27871;phoneType=XT820;systemVersion=android4.2;actionName=updateUser;email=1@189.cn;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			//String content = "userId=0;deptId=0;phoneType=XT800;systemVersion=android4.0;actionName=adviceSave;feedbackContent=建议内容建议内容建议内容建议内容;contactWay=18900000000;";
			//String content = "userId=0;deptId=0;phoneType=XT800;systemVersion=android4.0;actionName=NoticeQuery;page=1;limit=10;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			//String content = "userId=0;deptId=0;phoneType=;systemVersion=;actionName=mobileLocation;locationNum=18912005511;flag=0;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			//String content = "userId=0;deptId=0;phoneType=;systemVersion=;actionName=modifyPassword;oldPass=321321;newPass=123213;apkVersion=1.0.0;imsi=460036521123717;meid=1223;other=other;param1=1;param2=2;param3=3";
			String decontent ="";
			decontent = SimpleDes.encrypt(content, DataDirection.TO_SERVER);
			System.out.println(decontent);
			Map<String, String> map = getPostPara(decontent);
			String name=map.get("msgText");
			System.out.println(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
