package com.zwbk.button;

import java.util.ArrayList;
import java.util.List;

public class SubButton extends AbstractButton {
	
	public List<AbstractButton> sub_button = new ArrayList<>();

	public SubButton(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public List<AbstractButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<AbstractButton> sub_button) {
		this.sub_button = sub_button;
	}

}
