package com.zwbk.Interface;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;

//识别测试
public class Sample {
	public static final String APP_ID = "16127389";
	public static final String API_KEY = "pPoziHNlATeQcPhA3ljR8uHu";
	public static final String SECRET_KEY = "BABV8vbiCpkOxFzZWQ61v3QgFXg9pdUX";

	@Test
	public void testSample() {
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
//
//        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		// 调用接口
		String path = "C:\\Users\\Administrator\\Desktop\\8.png";
		JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
		System.out.println(res.toString(2));
	}

	@Test
	public void test() {


		// 初始化一个AipImageClassify
		AipImageClassify client = new AipImageClassify("16125382", "RTospIA7c9NeaV3FmjqdSCbv", "N7uHz10mk0yXoq34qLA5b7sD5MoQf3Ua");

//	        // 可选：设置网络连接参数
//	        client.setConnectionTimeoutInMillis(2000);
//	        client.setSocketTimeoutInMillis(60000);
//
//	        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//	        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//	        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

		// 调用接口
		String path = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=485718565,1013450070&fm=27&gp=0.jpg";
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		String localPath = "E:\\zwbk-pic\\"+time+".png";
		System.out.println(localPath);
		DownloadPicFromURL.downloadPicture(path,localPath);
		
		JSONObject res = client.plantDetect(localPath, new HashMap<String, String>());
		System.out.println(res.toString(2));

	}
	
	@Test
	public void TEst() {
		Double d = 0.1231651654;
		DecimalFormat df2 = new DecimalFormat("%00.00");
		String s = df2.format(d);
		System.out.println(s);
	}
}
