/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class FloatTag extends NBTTag<Float> {

	public FloatTag(String name) {
		super(name);
		data = 0f;
	}

	public void setFloat(float data) {
		this.data = data;
	}
	
	public float getFloat() {
		return data;
	}

	
	@Override
	public TagType getType() {
		return TagType.FLOAT;
	}

}
