package com.zwbk.button;

public class ViewLimitedButton extends AbstractButton{
	public String type = "view_limited";
	public String view_limited;
	public ViewLimitedButton(String name,String view_limited) {
		super(name);
		this.view_limited = view_limited;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getView_limited() {
		return view_limited;
	}
	public void setView_limited(String view_limited) {
		this.view_limited = view_limited;
	}


}
