package com.zwbk.button;

import java.util.ArrayList;
import java.util.List;

public class Button {
	public List<AbstractButton> Button =  new ArrayList<>();
	
	public List<AbstractButton> getButton() {
		return Button;
	}

	public void setButton(List<AbstractButton> button) {
		Button = button;
	}

}
