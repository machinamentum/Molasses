/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt;

public enum TagType {
	
	END(0), BYTE(1), SHORT(2), INT(3), LONG(4), FLOAT(5), DOUBLE(6), BYTE_ARRAY(7), STRING(8), LIST(9), COMPOUND(10);

	private byte type;
	
	TagType(int type) {
		this.type = (byte)type;
	}
	
	public byte asByte() {
		return type;
	}
	
	public static TagType getTagType(byte type) {
		for(TagType t : TagType.values()) {
			if(t.asByte() == type) {
				return t;
			}
		}
		return null;
	}
	
}
