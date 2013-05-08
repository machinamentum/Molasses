/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class ShortTag extends NBTTag<Short> {

	public ShortTag(String name) {
		super(name);
		data = 0;
	}
	
	public void setShort(short data) {
		this.data = data;
	}
	
	public short getShort() {
		return data;
	}

	
	@Override
	public TagType getType() {
		return TagType.SHORT;
	}

}
