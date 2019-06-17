package com.zwbk.button;

public abstract class AbstractButton {
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractButton(String name) {
		super();
		this.name = name;
	}
}
