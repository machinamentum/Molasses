/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;


public class CompoundTag extends NBTTag<CompoundMap> {

	public CompoundTag(String name) {
		super(name);
		data = new CompoundMap();
	}
	
	public CompoundMap getMap() {
		return data;
	}
	
	public void setCompoundMap(CompoundMap data) {
		this.data = new CompoundMap(data);
	}
	
	public void put(String name, NBTTag<?> tag) {
		data.put(name, tag);
	}
	
	public void put(NBTTag<?> tag) {
		put(tag.getName(), tag);
	}
	
	public void remove(String name) {
		data.remove(name);
	}

	
	@Override
	public TagType getType() {
		return TagType.COMPOUND;
	}

}
