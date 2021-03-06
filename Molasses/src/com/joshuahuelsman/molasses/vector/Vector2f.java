/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.vector;

public strictfp class Vector2f extends Vector {
	
	public float x;
	
	public float y;
	
	public Vector2f() {
		this(0, 0);
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(Vector2f vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	/**
	 * Adds the given Vector2 component-
	 * wise to this Vector2.
	 * @param vec the Vector2 to add to
	 * this Vector2.
	 * @return this Vector2. Useful for
	 * chaining operations.
	 */
	public Vector2f add(Vector2f vec) {
		this.x += vec.x;
		this.y += vec.y;
		return this;
	}
	
	/**
	 * @return a copy of this Vector2 negated.
	 */
	public Vector2f negate() {
		return new Vector2f(-x, -y);
	}
	
	public Vector2f scale(float s) {
		x *= s;
		y *= s;
		return this;
	}

	@Override
	public float lengthSquared() {
		return (x * x) + (y * y);
	}

}
