/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class StringTag extends NBTTag<String> {
	
	
	public StringTag(String name) {
		super(name);
		data = "";
	}

	public void setString(String data) {
		this.data = new String(data);
	}
	
	public String getString() {
		return data;
	}

	
	@Override
	public TagType getType() {
		return TagType.STRING;
	}

}
