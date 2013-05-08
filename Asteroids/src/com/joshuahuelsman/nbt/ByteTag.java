/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class ByteTag extends NBTTag<Byte> {

	public ByteTag(String name) {
		super(name);
		data = 0;
	}
	
	public void setByte(byte data) {
		this.data = data;
	}

	public byte getByte() {
		return data;
	}

	
	@Override
	public TagType getType() {
		return TagType.BYTE;
	}

}
