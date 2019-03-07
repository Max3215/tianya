package com.ynyes.tianya.util;

import java.net.URLEncoder;

import com.emay.channel.httpclient.SDKHttpClient;

/**
 * 短信发送工具类
 * @author liuxinbing
 *
 */

public class SmsUtils {
	//私家定制、汽车包租、长租、约租、代驾前端订购以后，后台短信通知工作人员。汽车租赁杨鼎科13996265070；私人定制张曼娜18623159900
	public final static String RENT_PHONE = "13996265070";//汽车租赁杨鼎科13996265070
	public final static String PRIVATE_PHONE = "18623159900";//私人定制张曼娜18623159900

	public static void smsSend(String mobile,String message)
	{
		String sn = "8SDK-EMY-6699-RFTQT";// 软件序列号,请通过亿美销售人员获取
		String password = "148954";// 密码,请通过亿美销售人员获取
		String key = "123456";// 序列号首次激活时自己设定
		String baseUrl = "http://219.239.91.114:8080/sdkproxy/";
		
		try {
			message = "【天涯国旅】"+message;
			message = URLEncoder.encode(message, "UTF-8");
			if(message.contains("回复TD退订"))
			{
				sn = "8SDK-EMY-6699-RFTRL";// 软件序列号,请通过亿美销售人员获取
				password = "559770";// 密码,请通过亿美销售人员获取
			}
			
			String code = "888";
			long seqId = System.currentTimeMillis();
			String param = "cdkey=" + sn + "&password=" 
						+ password + "&phone=" + mobile + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
			String url = baseUrl + "sendsms.action";
			String sms = SDKHttpClient.sendSMS(url, param);
			System.err.println(sms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
