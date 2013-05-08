/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.display;

import java.awt.image.BufferedImage;

public final class DisplayMode {

	private int width, height;
	
	private int bufferType;
	
	public DisplayMode(int width, int height) {
		this(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	public DisplayMode(int width, int height, int type) {
		this.width = width;
		this.height = height;
		this.bufferType = type;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getBufferType() {
		return bufferType;
	}

}
