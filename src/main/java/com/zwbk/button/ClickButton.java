package com.zwbk.button;

public class ClickButton extends AbstractButton {
	public String type = "click";
	public String key ;

	public ClickButton(String name,String key) {
		super(name);
		this.key = key;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
