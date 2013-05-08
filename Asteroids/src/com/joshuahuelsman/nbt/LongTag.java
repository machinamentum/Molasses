/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class LongTag extends NBTTag<Long> {

	public LongTag(String name) {
		super(name);
		data = 0L;
	}
	
	public void setLong(long data) {
		this.data = data;
	}
	
	public long getLong() {
		return data;
	}

	
	@Override
	public TagType getType() {
		return TagType.LONG;
	}

}
