package com.zwbk.WxService;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.zwbk.Entity.AccessToken;
import com.zwbk.Entity.Token;
import com.zwbk.Wxutil.MessageUtil;
import com.zwbk.Wxutil.Util;
import com.zwbk.Wxutil.WxUtil;

import net.sf.json.JSONObject;

public class WxService {
	//获得accessToken的URL
	private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 微信公众号
	private static final String APPID = "wx6a4c3a30ade075b4";
	private static final String APPSECRET = "8f76fda56cb6fc700d1608dbab180c0f";
	// 用于存储accessToken
	private static AccessToken at;
	//获取accessToken
	private static void getToken() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String tokenStr = WxUtil.getAccessToken(url);
		JSONObject jsonObject = JSONObject.fromObject(tokenStr);
		if(jsonObject.getString("access_token") != null) {
			String token = jsonObject.getString("access_token");
			String expireIn = jsonObject.getString("expires_in");
			// 创建token对象,并存起来。
			at = new AccessToken(token,expireIn);
		}else {
			System.out.println("accessToken 获取错误  返回码 ："+jsonObject.getString("errorcode"));
		}

	}
	//accessToken获取机制
	public static String getAccessToken() {
		if(at==null||at.isExpired()) {			
			getToken();
		}
		return at.getAccessToken();
	}
	
	//检查token
	public static boolean checkToken(Token token , PrintWriter out) {
		System.out.println("开始验证token");
		
		String signature = token.getSignature();
		String timestamp = token.getTimestamp();
		String nonce = token.getNonce();
		String echostr = token.getEchostr();

		if(WxUtil.check(timestamp,nonce,signature)) {
			System.out.println("验证成功");
			out.print(echostr);
			out.flush();
			out.close();
			return true;
		}else {
			System.out.println("验证失败");
			return false;
		}
	}
	
	//messageHandler
	public static void messageHandler(InputStream in,PrintWriter out) {
		Map<String,String> requestMap = MessageUtil.parseRequest(in);
		System.out.println(requestMap);
		String responseXml = MessageUtil.getRespose(requestMap);
		out.print(responseXml);
		out.flush();
		out.close();
	}
	
	//get openid-Access_token(这里的Access_token和之前的不一样，用于获取用户信息)
	public static HashMap<String,String> getOpenidAndAccessToken(String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		url = url.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
		String result = Util.get(url);
		System.out.println("获取OpenidAndAccessToken 结果:"+result);
		JSONObject json = JSONObject.fromObject(result);
		HashMap<String,String> hm = new HashMap<>();
		hm.put("openid", json.getString("openid"));
		hm.put("access_token", json.getString("access_token"));
		return hm;
	}
	
	public static String getUserInfo(String openid , String access_token) {
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		url = url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
		String result = Util.get(url);
		JSONObject json = JSONObject.fromObject(result);
		System.out.println("获取用户信息结果："+result);
		return json.getString("city");
	}
}
