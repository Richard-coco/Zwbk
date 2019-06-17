package com.zwbk.WxService;

import org.junit.Test;

import com.zwbk.Wxutil.MenuUtil;
import com.zwbk.button.Button;
import com.zwbk.button.ClickButton;
import com.zwbk.button.PicPhotoOrAlbum;
import com.zwbk.button.SubButton;
import com.zwbk.button.ViewButton;

import net.sf.json.JSONObject;

public class Menu {
	//创建菜单URL
	public  final String URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//删除菜单
	public final String URLDELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	@Test
	public void createMenu() {
		String url_tuijian = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
		url_tuijian = url_tuijian.replace("APPID", "wx6a4c3a30ade075b4").replace("REDIRECT_URI", "http://24tmj4.natappfree.cc/zwbk/tuijian").replace("SCOPE", "snsapi_userinfo");
		Button b = new Button();
		PicPhotoOrAlbum cb = new PicPhotoOrAlbum("植物识别","zwbk");
		ViewButton vb1 = new ViewButton("种植技术","http://www.nongfen.com/zhongzhi/");
		ViewButton vb2 = new ViewButton("栽培技术","http://www.nongfen.com/zaipei/");
		ViewButton vb3 = new ViewButton("防治技术","http://www.nongfen.com/bingchonghai/");
		ViewButton vb4 = new  ViewButton("施肥技术","http://www.nongfen.com/huafei/");
		ViewButton vb5 = new  ViewButton("联系专家","https://www.haodf.com/keshi/10007000/zaixian_shanghai.htm");
		ViewButton ppoa = new ViewButton("作物推荐",url_tuijian);
		SubButton sb = new SubButton("技术指导");
		
		sb.getSub_button().add(vb1);
		sb.getSub_button().add(vb2);
		sb.getSub_button().add(vb3);
		sb.getSub_button().add(vb4);
		sb.getSub_button().add(vb5);
		
		b.getButton().add(cb);
		b.getButton().add(ppoa);
		b.getButton().add(sb);
		
		JSONObject dataJson = JSONObject.fromObject(b);
		String data = dataJson.toString();
		String accessToken = WxService.getAccessToken();
		String url = URL.replace("ACCESS_TOKEN",accessToken);
		String result = MenuUtil.getMenu(url,data);
		System.out.println(result);
	}
	
	@Test
	public void deleteMenu() {
		String accessToken = WxService.getAccessToken();
		String url = URLDELETE.replace("ACCESS_TOKEN",accessToken);	
		String result = MenuUtil.deleteMenu(url);
		System.out.println(result);
	}

}
