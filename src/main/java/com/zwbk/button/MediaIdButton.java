package com.zwbk.button;

public class MediaIdButton extends AbstractButton {

	public String type = "media_id";
	public String media_id;

	public MediaIdButton(String name,String media_id) {
		super(name);
		this.media_id = media_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

}
