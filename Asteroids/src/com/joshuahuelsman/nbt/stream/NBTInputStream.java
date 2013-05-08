/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.nbt.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import com.joshuahuelsman.nbt.ByteArrayTag;
import com.joshuahuelsman.nbt.ByteTag;
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

public class NBTInputStream {

	private DataInputStream in;

	public NBTInputStream(InputStream stream) throws IOException {
		this(stream, true);
	}

	public NBTInputStream(InputStream stream, boolean compressed)
			throws IOException {
		in = (compressed ? new DataInputStream(new GZIPInputStream(stream))
				: new DataInputStream(stream));
	}

	public NBTTag<?> readTag() throws IOException {
		return readTag(in.readByte(), true);
	}

	private NBTTag<?> readTag(byte type, boolean named) throws IOException {
		NBTTag<?> tag = null;

		if (type == 0) {
			return null;
		}

		String name = "";
		if (named) {
			short length = in.readShort();
			byte[] bname = new byte[length];
			in.readFully(bname);
			name = new String(bname, Charset.forName("UTF-8"));
		}

		TagType tagType = TagType.getTagType(type);
		switch (tagType) {
		case BYTE:
			tag = new ByteTag(name);
			((ByteTag) tag).setByte(in.readByte());
			break;
		case SHORT:
			tag = new ShortTag(name);
			((ShortTag) tag).setShort(in.readShort());
			break;
		case INT:
			tag = new IntTag(name);
			((IntTag) tag).setInt(in.readInt());
			break;
		case LONG:
			tag = new LongTag(name);
			((LongTag) tag).setLong(in.readLong());
			break;
		case FLOAT:
			tag = new FloatTag(name);
			((FloatTag) tag).setFloat(in.readFloat());
			break;
		case DOUBLE:
			tag = new DoubleTag(name);
			((DoubleTag) tag).setDouble(in.readDouble());
			break;
		case BYTE_ARRAY:
			tag = new ByteArrayTag(name);
			int size = in.readInt();
			byte[] bytes = new byte[size];
			in.readFully(bytes);
			ArrayList<Byte> list = new ArrayList<Byte>();
			for (byte b : bytes) {
				list.add(b);
			}
			((ByteArrayTag) tag).setByteArray(list);
			break;
		case STRING:
			tag = new StringTag(name);
			short length = in.readShort();
			byte[] bstr = new byte[length];
			in.readFully(bstr);
			String str = new String(bstr, Charset.forName("UTF-8"));
			((StringTag) tag).setString(str);
			break;
		case LIST:
			tag = new ListTag(name);
			byte tType = in.readByte();
			((ListTag) tag).setListType(TagType.getTagType(tType));
			int amount = in.readInt();
			for (int i = 0; i < amount; i++) {
				((ListTag) tag).getList().add(readTag(tType, false));
			}
			break;
		case COMPOUND:
			tag = new CompoundTag(name);
			boolean stop = false;
			while (!stop) {
				NBTTag<?> t = readTag();
				if (t == null) {
					stop = true;
				} else {
					((CompoundTag) tag).put(t.getName(), t);
				}
			}
			break;
		default:
			break;
		}

		return tag;
	}

	public void close() throws IOException {
		in.close();
	}
}
