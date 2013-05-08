/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

import java.util.ArrayList;

public class ByteArrayTag extends NBTTag<ArrayList<Byte>> {

	public ByteArrayTag(String name) {
		super(name);
		data = new ArrayList<Byte>();
	}
	

	public void setByteArray(ArrayList<Byte> data) {
		this.data = new ArrayList<Byte>(data);
	}
	
	public ArrayList<Byte> getByteArray() {
		return data;
	}


	
	@Override
	public TagType getType() {
		return TagType.BYTE_ARRAY;
	}

}
