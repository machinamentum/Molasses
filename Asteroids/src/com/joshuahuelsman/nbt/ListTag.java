/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

import java.util.ArrayList;

public class ListTag extends NBTTag<ArrayList<NBTTag<?>>> {
	
	private TagType listType = TagType.END;
	
	public ListTag(String name) {
		super(name);
		data = new ArrayList<NBTTag<?>>();
	}
	
	public void setList(ArrayList<NBTTag<?>> data) {
		this.data = new ArrayList<NBTTag<?>>(data);
	}
	
	public ArrayList<NBTTag<?>> getList() {
		return data;
	}
	
	public TagType getListType() {
		return listType;
	}
	
	public void setListType(TagType type) {
		this.listType = type;
	}

	
	@Override
	public TagType getType() {
		return TagType.LIST;
	}

}
