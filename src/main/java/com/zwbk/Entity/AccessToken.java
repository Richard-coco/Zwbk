package com.zwbk.Entity;

public class AccessToken {
	private String accessToken;
	//过期时间
	private long expireTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
	
	//expireIn 有效时间
	public AccessToken(String accessToken, String expireIn) {
		super();
		this.accessToken = accessToken;
		expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
	}
	//判断是否过期
	public boolean isExpired() {
		return System.currentTimeMillis()>expireTime;
	}
}
