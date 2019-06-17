package com.zwbk.Wxutil;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WxUtil {
	private static String token = "int";
//token验证
	public static boolean check(String timestamp, String nonce, String signature) {
		// 将token、timeStamp、nonce三个参数进行字典序排序
		String[] strs = new String[] { token, timestamp, nonce };
		Arrays.sort(strs);
		// 将三个参数字符串拼接成一个字符串
		String str = strs[0] + strs[1] + strs[2];
		// 进行sha1加密
		String jiami = sha1(str);

		// 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return jiami.equals(signature);
	}

	private static String sha1(String str) {
		try {
			// 获取一个加密对象
			MessageDigest md = MessageDigest.getInstance("sha1");
			// 加密
			byte[] digest = md.digest(str.getBytes());
			char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			StringBuffer sb = new StringBuffer();
			// 处理加密结果
			for (byte b : digest) {
				sb.append(chars[(b >> 4) & 15]);
				sb.append(chars[b & 15]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//获取accessToken
	public static String getAccessToken(String url) {
		try {
			URL urlObj = new URL(url);
			// 开连接
			URLConnection connection = urlObj.openConnection();
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024];
			int len = -1;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}
			is.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
