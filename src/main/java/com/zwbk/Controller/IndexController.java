package com.zwbk.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwbk.Entity.Message;
import com.zwbk.Entity.Token;
import com.zwbk.Entity.User;
import com.zwbk.Entity.Zw;
import com.zwbk.Service.UserService;
import com.zwbk.Service.ZwService;
import com.zwbk.WxService.WxService;
import com.zwbk.Wxutil.JudgeLocal;
import com.zwbk.Wxutil.MessageUtil;

import net.sf.json.JSONObject;

@Controller
public class IndexController {
	
	@Autowired
	UserService us;
	@Autowired
	ZwService zs;
	
	HashMap<String ,String> hm = new HashMap<>();
	
	@RequestMapping(value = "togo" ,method = {RequestMethod.GET})
	public void togo(Token token, HttpServletRequest  request ,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		WxService.checkToken(token, out);
	
	}
	
	@RequestMapping(value = "togo" ,method = {RequestMethod.POST})
	public void messageHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		WxService.messageHandler(request.getInputStream(), response.getWriter());

	}
	
	@RequestMapping("tuijian")
	public String tuiJian(String code,Model model){
		//hm.put("code", code);
		
		//开消息队列来处理
		while(!MessageUtil.l.isEmpty()) {
			User u = MessageUtil.l.poll();
			us.insertAddress(u);
			System.out.println("用户信息录入成功 信息链表长度为："+MessageUtil.l.size());
		}
		
		HashMap<String,String> h  = WxService.getOpenidAndAccessToken(code);
		User u = us.selectByPrimaryKey(h.get("openid"));
		String local = JudgeLocal.judge(u.getLongitude(), u.getLatitude());
		model.addAttribute("local", local);
		model.addAttribute("longitude",u.getLongitude());
		model.addAttribute("latitude",u.getLatitude());
		return "wel";
	}
	
	@ResponseBody
	@RequestMapping("result")
	public Message test(String local){
		System.out.println("success into result");
		PageHelper.startPage(1,12);
		List<Zw> l ;
		l = zs.selectByLocal(local);
		PageInfo<Zw> page =  new PageInfo<>(l);
		return new Message().success().add("ResultList",page);
	}
}
