package com.zwbk.Interface;

import java.net.URLEncoder;

import org.junit.Test;

public class ZW {
	@Test
	public static String advancedGeneral() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
		try {
			// 本地文件路径
			String filePath = "[本地文件路径]";
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "[调用鉴权接口获取的token]";

			String result = HttpUtil.post(url, accessToken, param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
