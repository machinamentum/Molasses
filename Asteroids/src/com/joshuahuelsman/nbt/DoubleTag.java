/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public class DoubleTag extends NBTTag<Double> {

	public DoubleTag(String name) {
		super(name);
		data = 0D;
	}
	

	public void setDouble(double data) {
		this.data = data;
	}
	
	public double getDouble() {
		return data;
	}


	@Override
	public TagType getType() {
		return TagType.DOUBLE;
	}

}
