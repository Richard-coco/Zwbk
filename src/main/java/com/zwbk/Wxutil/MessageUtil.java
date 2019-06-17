package com.zwbk.Wxutil;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import com.thoughtworks.xstream.XStream;
import com.zwbk.Entity.User;
import com.zwbk.Interface.DownloadPicFromURL;
import com.zwbk.Message.Article;
import com.zwbk.Message.BaseMessage;
import com.zwbk.Message.ImageMessage;
import com.zwbk.Message.MusicMessage;
import com.zwbk.Message.NewsMessage;
import com.zwbk.Message.SuccessMessage;
import com.zwbk.Message.TextMessage;
import com.zwbk.Message.VideoMessage;
import com.zwbk.Message.VoiceMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageUtil {
	
	//保存了用户信息（username 经纬度）
	public static LinkedList<User> l = new LinkedList<>();
	
	//读取文字
	public static final String APP_ID = "16125382";
	public static final String API_KEY = "RTospIA7c9NeaV3FmjqdSCbv";
	public static final String SECRET_KEY = "N7uHz10mk0yXoq34qLA5b7sD5MoQf3Ua";
	//识别植物
	public static final String APP_IDC = "16127389";
	public static final String API_KEYC = "pPoziHNlATeQcPhA3ljR8uHu";
	public static final String SECRET_KEYC = "BABV8vbiCpkOxFzZWQ61v3QgFXg9pdUX";

	// 将服务器发送过来的XML解析 用MAP集合保存数据
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		try {
			// 获得输入流
			Document document = reader.read(is);
			// 获得根节点
			Element root = document.getRootElement();
			// get elements 将数据存入map集合
			List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getStringValue());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	private static String beanToXml(SuccessMessage msg) {
		XStream stream = new XStream();
		// 注入转换器
		stream.processAnnotations(TextMessage.class);
		stream.processAnnotations(ImageMessage.class);
		stream.processAnnotations(MusicMessage.class);
		stream.processAnnotations(NewsMessage.class);
		stream.processAnnotations(VideoMessage.class);
		stream.processAnnotations(VoiceMessage.class);
		stream.processAnnotations(SuccessMessage.class);
		String xml = stream.toXML(msg);
		return xml;
	}

	// 将消息map传入 自动回复
	public static String getRespose(Map<String, String> requestMap) {
		SuccessMessage msg = null;
		String msgType = requestMap.get("MsgType");
		switch (msgType) {
		case "text":
			msg = dealTextMessage(requestMap);
			break;
		case "image":
			msg = dealImage(requestMap);
			break;
		case "voice":

			break;
		case "video":

			break;
		case "shortvideo":

			break;
		case "location":

			break;
		case "link":

			break;
		case "event":
			msg = dealEvent(requestMap);
			break;
		default:
			break;
		}
		// 转换成XML格式的字符串
		if (msg != null) {
			return beanToXml(msg);
		}
		return null;

	}

	// 读取图片植物
	private static BaseMessage dealImage(Map<String, String> requestMap) {
		// 初始化一个AipOcr
		AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
		// AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		// 调用接口
		String path = requestMap.get("PicUrl");

		// 下载图片到E:\\zwbk-pic\\
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");// 设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		String localPath = "E:\\zwbk-pic\\" + time+".png";
		DownloadPicFromURL.downloadPicture(path, localPath);

		// org.json.JSONObject
		org.json.JSONObject res = client.plantDetect(localPath, new HashMap<String, String>());
		// org.json.JSONObject res = client.generalUrl(path, new HashMap<String,String>());

		String json = res.toString();
		System.out.println(json);
		net.sf.json.JSONObject js = net.sf.json.JSONObject.fromObject(json);
		// JSONArray jsa = js.getJSONArray("words_result");
		JSONArray jsa = js.getJSONArray("result");
		StringBuilder sb = new StringBuilder();
		Iterator<JSONObject> it = jsa.iterator();
		DecimalFormat df2 = new DecimalFormat("00.00%");

		while (it.hasNext()) {
			JSONObject j = it.next();
			Double score = Double.parseDouble(j.get("score").toString());
			sb.append(df2.format(score) + ":   " + j.getString("name") + "\n");
		}

		return new TextMessage(requestMap, sb.toString());

	}

	// 读取图片消息中的文字
	private static BaseMessage dealImageC(Map<String, String> requestMap) {
		// 初始化一个AipOcr
		 AipOcr client = new AipOcr(APP_IDC, API_KEYC, SECRET_KEYC);
		// 调用接口
		String path = requestMap.get("PicUrl");

		// org.json.JSONObject
		org.json.JSONObject res = client.generalUrl(path, new HashMap<String,String>());

		String json = res.toString();
		System.out.println(json);
		net.sf.json.JSONObject js = net.sf.json.JSONObject.fromObject(json);
		 JSONArray jsa = js.getJSONArray("words_result");
		StringBuilder sb = new StringBuilder();
		Iterator<JSONObject> it = jsa.iterator();

		while (it.hasNext()) {
			JSONObject j = it.next();
			sb.append( j.getString("words"));
		}

		return new TextMessage(requestMap, sb.toString());

	}

	// 处理文本消息
	private static SuccessMessage dealTextMessage(Map<String, String> requestMap) {
		// 获得请求中的content
		String content = requestMap.get("Content");
		// String resp = chat(msg);
		String picUrl = "http://mmbiz.qpic.cn/mmbiz_jpg/qmfwV4ySiaMJiaerSI6459sB1UTILla4aJfJHGqTLw6aF23KTmNOBMhicDibeaXhbLdmbzIkVEHxOF1ksibzfibcpc1w/0";
		if (content.equals("图文")) {
			List<Article> articles = new ArrayList<>();
			Article e = new Article("标题", "描述", picUrl, "www.baidu.com");
			articles.add(e);
			NewsMessage nm = new NewsMessage(requestMap, articles);
			return nm;
		} else {
			return new TextMessage(requestMap,"我还没学会说话，能等我一会吗？");
		}
	}

	// 处理Event事件
	private static BaseMessage dealEvent(Map<String, String> requestMap) {
		if ("CLICK".equals(requestMap.get("Event"))) {
			return new TextMessage(requestMap, "你好");
		} else if("LOCATION".equals(requestMap.get("Event"))){
			//纬度
			String longitude = requestMap.get("Longitude");
			//精度
			String latitude = requestMap.get("Latitude");
			String openid = requestMap.get("FromUserName");
			User u = new User();
			u.setLatitude(latitude);
			u.setLongitude(longitude);
			u.setOpenid(openid);
			l.add(u);
			return new TextMessage(requestMap,"");
		}else {
			return new TextMessage(requestMap, "");
		}

	}

}
