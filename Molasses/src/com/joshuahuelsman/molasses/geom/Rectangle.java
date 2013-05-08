/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.geom;

public class Rectangle {

	public float x;

	public float y;

	public float width;

	public float height;
	
	public Rectangle() {
		this(0,0,0,0);
	}

	public Rectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean intersects(Rectangle other) {
		if (this.x > other.x + other.width 
				|| this.x + this.width < other.x
				|| this.y > other.y + other.height
				|| this.y + this.height < other.y) {
			return false;
		}
		return true;
	}

}
