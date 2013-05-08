/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public abstract class NBTTag<T> {
	
	protected T data;
	
	protected String name;
	
	protected boolean named;
	
	public NBTTag(String name) {
		this.name = new String(name);
	}
	
	public NBTTag() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract TagType getType();

}
