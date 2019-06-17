package com.zwbk.Entity;

import java.util.HashMap;
import java.util.Map;

public class Message {
	private static String msg;
	private static int state;
	private Map<String,Object> map  = new HashMap<String,Object>();
	
	public Message success() {
		msg = "success";
		state = 100;
		Message message = new Message();
		message.setMsg(msg);
		message.setState(state);
		return message;
	}
	
	public static Message faile() {
		msg = "faile";
		state = 200;
		Message message = new Message();
		message.setMsg(msg);
		message.setState(state);
		return message;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Message add(String key ,Object value) {
		Message message = new Message();
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put(key, value);
		message.setMap(map);
		return message;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getState() {
		return state;
	}


	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setState(int state) {
		this.state = state;
	}

}
