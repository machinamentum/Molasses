/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.graphics;

public class Color {
	
	public static final Color black = new Color(0.001f, 0.001f, 0.001f, 1f);
	public static final Color cyan = new Color(0f, 1f, 1f, 1f);
	public static final Color magenta = new Color(1f, 0f, 1f, 1f);
	public static final Color yellow = new Color(1f, 1f, 0f, 1f);
	public static final Color orange = new Color(1f, 0.6f, 0f, 1f);
	public static final Color green = new Color(0f, 1f, 0f, 1f);
	public static final Color white = new Color(1f, 1f, 1f, 1f);
	public static final Color red = new Color(1f, 0f, 0f, 1f);
	public static final Color blue = new Color(0f, 0f, 1f, 1f);
	
	public float r;
	public float g;
	public float b;
	public float a;
	
	public Color() {
		r = 0;
		g = 0;
		b = 0;
		a = 1;
	}
	
	public Color(Color c) {
		r = c.r;
		g = c.g;
		b = c.b;
		a = c.a;
	}
	
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color(float r, float g, float b) {
		this(r, g, b, 1f);
	}
	
	public Color(int color) {
		int red = (color & 0x00ff0000) >> 16;
		int green = (color & 0x0000ff00) >> 8;
		int blue = (color & 0x000000ff);
		int alpha = (color & 0xff000000) >> 24;
		r = red / 255f;
		g = green / 255f;
		b = blue / 255f;
		a = alpha / 255f;
	}
	
	public int getRGB() {
		int alpha = ((int)(a * 255f) << 24) & 0xff000000;
		int red = ((int)(r * 255f) << 16) & 0x00ff0000;
		int green = ((int)(g * 255f) << 8) & 0x0000ff00;
		int blue = (int)(b * 255f) & 0x000000ff;
		int color = alpha | red | green | blue;
		return color;
	}

}
