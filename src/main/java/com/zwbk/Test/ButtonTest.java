package com.zwbk.Test;

import org.junit.Test;

import com.zwbk.button.Button;
import com.zwbk.button.ClickButton;
import com.zwbk.button.MediaIdButton;
import com.zwbk.button.PicPhotoOrAlbum;
import com.zwbk.button.SubButton;
import com.zwbk.button.ViewButton;
import com.zwbk.button.ViewLimitedButton;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class ButtonTest {
	
	@Test
	public void buttonTest() {
//		Button button = new Button();
//		ClickButton c = new ClickButton("zwbk","2");
//		ViewButton v1 = new ViewButton("baidu","www.baidu.com");
//		ViewButton v2 = new ViewButton("google","www.google.com");
//		SubButton sub = new SubButton("view");
//		
//		sub.getSub_button().add(v1);
//		sub.getSub_button().add(v2);
//		button.getButton().add(c);
//		button.getButton().add(sub);
		
//		Button b = new Button();
//		MediaIdButton m = new MediaIdButton("media", "media_id");
//		ViewLimitedButton vb = new ViewLimitedButton("viewlimit", "viewlimitid");
//		SubButton su = new SubButton("su");
//		su.getSub_button().add(vb);
//		b.getButton().add(m);
//		b.getButton().add(su);
		
		Button b = new Button();
		ClickButton cb = new ClickButton("植物百科","zwbk");
		ViewButton vb1 = new ViewButton("网易新闻","https://news.163.com/");
		ViewButton vb2 = new ViewButton("我要看番","https://www.bilibili.com/");
		ViewButton vb3 = new ViewButton("我还要看番","https://www.bilibili.com/");
		PicPhotoOrAlbum ppoa = new PicPhotoOrAlbum("上传照片","pic");
		SubButton sb = new SubButton("点开看看");
		
		sb.getSub_button().add(vb1);
		sb.getSub_button().add(vb2);
		sb.getSub_button().add(vb3);
		
		b.getButton().add(cb);
		b.getButton().add(ppoa);
		b.getButton().add(sb);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(b,jsonConfig);
		System.out.println(json.toString());
	}
}
