/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

import java.util.HashMap;

public class CompoundMap extends HashMap<String, NBTTag<?>> {

	public CompoundMap() {
		
	}
	
	public CompoundMap(CompoundMap data) {
		super(data);
	}

	private static final long serialVersionUID = -5680495365261069361L;

}
