/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class IntTag extends NBTTag<Integer> {

	public IntTag(String name) {
		super(name);
		data = 0;
	}
	
	public void setInt(int data) {
		this.data = data;
	}
	
	public int getInt() {
		return data;
	}

	
	@Override
	public TagType getType() {
		return TagType.INT;
	}

}
