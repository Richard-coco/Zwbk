package com.zwbk.button;

import java.util.List;

public class PicPhotoOrAlbum extends AbstractButton{
	public String type = "pic_photo_or_album";
	public String key;
	public List<AbstractButton> sub_button;
	public PicPhotoOrAlbum(String name,String key) {
		super(name);
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<AbstractButton> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<AbstractButton> sub_button) {
		this.sub_button = sub_button;
	}
	
}
