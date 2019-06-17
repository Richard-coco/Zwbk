package com.zwbk.button;

public class ViewButton extends AbstractButton {
	
	public String type = "view";
	public String url;
	
	public ViewButton(String name , String url) {
		super(name);
		this.url = url;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
