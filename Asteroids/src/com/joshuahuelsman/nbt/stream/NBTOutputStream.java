/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

import com.joshuahuelsman.nbt.ByteArrayTag;
import com.joshuahuelsman.nbt.ByteTag;
import com.joshuahuelsman.nbt.CompoundMap;
import com.joshuahuelsman.nbt.CompoundTag;
import com.joshuahuelsman.nbt.DoubleTag;
import com.joshuahuelsman.nbt.FloatTag;
import com.joshuahuelsman.nbt.IntTag;
import com.joshuahuelsman.nbt.ListTag;
import com.joshuahuelsman.nbt.LongTag;
import com.joshuahuelsman.nbt.NBTTag;
import com.joshuahuelsman.nbt.ShortTag;
import com.joshuahuelsman.nbt.StringTag;
import com.joshuahuelsman.nbt.TagType;

public class NBTOutputStream {
	
	protected DataOutputStream out;
	
	public NBTOutputStream(OutputStream stream) throws IOException {
		this(stream, true);
	}
	
	public NBTOutputStream(OutputStream stream, boolean compressed) throws IOException {
		out = (compressed ? new DataOutputStream(new GZIPOutputStream(stream)) : new DataOutputStream(stream));
	}
	
	public void writeTag(NBTTag<?> tag, boolean named) throws IOException {
		
		if(named) {
			out.writeByte(tag.getType().asByte());
			out.writeShort(tag.getName().length());
			out.write(tag.getName().getBytes(Charset.forName("UTF-8")));
		}
		
		switch(tag.getType()) {
		case BYTE:
			out.writeByte(((ByteTag)tag).getByte());
			break;
		case SHORT:
			out.writeShort(((ShortTag)tag).getShort());
			break;
		case INT:
			out.writeInt(((IntTag)tag).getInt());
			break;
		case LONG:
			out.writeLong(((LongTag)tag).getLong());
			break;
		case FLOAT:
			out.writeFloat(((FloatTag)tag).getFloat());
			break;
		case DOUBLE:
			out.writeDouble(((DoubleTag)tag).getDouble());
			break;
		case BYTE_ARRAY:
			ArrayList<Byte> bytes = ((ByteArrayTag)tag).getByteArray();
			out.writeInt(bytes.size());
			for(int i = 0; i < bytes.size(); i++) {
				out.writeByte(bytes.get(i));
			}
			break;
		case STRING:
			out.writeShort(((StringTag)tag).getString().length());
			out.write(((StringTag)tag).getString().getBytes(Charset.forName("UTF-8")));
			break;
		case LIST:
			out.writeByte(((ListTag)tag).getListType().asByte());
			out.writeInt(((ListTag)tag).getList().size());
			ArrayList<NBTTag<?>> tags = ((ListTag)tag).getList();
			for(int i = 0; i < tags.size(); i++) {
				writeTag(tags.get(i), false);
			}
			break;
		case COMPOUND:
			CompoundMap map = ((CompoundTag)tag).getMap();
			for(NBTTag<?> t : map.values()) {
				writeTag(t);
			}
			out.writeByte(TagType.END.asByte());
			break;
		default:
			break;
		
		}
	}
	
	public void writeTag(NBTTag<?> tag) throws IOException {
		writeTag(tag, true);
	}

	public void close() throws IOException {
		out.close();
	}
}
