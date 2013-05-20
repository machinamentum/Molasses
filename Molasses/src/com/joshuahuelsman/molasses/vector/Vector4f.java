/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.vector;

public strictfp class Vector4f extends Vector {

	public float x;
	
	public float y;
	
	public float z;
	
	public float w;
	
	public Vector4f() {
		this(0, 0, 0, 0);
	}
	
	public Vector4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vector4f add(Vector4f vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		this.w += vec.w;
		return this;
	}
	
	public Vector4f(Vector3f vec, float w) {
		this(vec.x, vec.y, vec.z, w);
	}

	@Override
	public Vector4f negate() {
		return new Vector4f(-x, -y, -z, -w);
	}
	
	@Override
	public float lengthSquared() {
		return (x * x) + (y * y) + (z * z) + (w * w);
	}

	@Override
	public Vector4f scale(float s) {
		x *= s;
		y *= s;
		z *= s;
		w *= s;
		return this;
	}

}
