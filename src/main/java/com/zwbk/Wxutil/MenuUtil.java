package com.zwbk.Wxutil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MenuUtil {
	
	//创建菜单
	public static String getMenu(String url, String data) {
		try {
			// 发送菜单JSON
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(data.getBytes());
			os.close();

			// 获取返回值
			InputStream is = connection.getInputStream();
			byte[] b = new byte[1024 * 8];
			int len = -1;
			StringBuffer sb = new StringBuffer();
			while ((len = is.read(b)) != -1) {
				sb.append(new String(b, 0, len));
			}

			return sb.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MenuUtil.getMenu fail";
	}
	
	//删除菜单
	public static String deleteMenu(String url) {
		try {
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			
			InputStream in = connection.getInputStream();
			int len = -1;
			byte[] b = new byte[1024*8];
			StringBuffer sb = new StringBuffer();
			while((len = in.read(b)) != -1) {
				sb.append(new String(b,0,len));
			}
			in.close();
			return sb.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MenuUtil.deleteMenu fail";
		
	}
}
