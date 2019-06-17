package com.zwbk.Message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
	@XStreamAlias("MediaId")
	private String mediaId;
	@XStreamAlias("PicUrl")
	private String PicUrl;
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public ImageMessage(Map<String, String> requestMap,String PicUrl ,String mediaId) {
		super(requestMap);
		this.PicUrl = PicUrl;
		this.setMsgType("image");
		this.mediaId=mediaId;
	}
	
}
