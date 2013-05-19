/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.vector;

public class Vector3f extends Vector {
	
	public float x;

	public float y;

	public float z;
	
	public Vector3f() {
		this(0, 0, 0);
	}

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(Vector3f vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	/**
	 * Adds the given Vector3 component- wise to this Vector3.
	 * 
	 * @param vec
	 *            the Vector3 to add to this Vector3.
	 * @return this Vector3. Useful for chaining operations.
	 */
	public Vector3f add(Vector3f vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}

	/**
	 * @return a copy of this Vector3 negated.
	 */
	@Override
	public Vector3f negate() {
		return new Vector3f(-x, -y, -z);
	}


	public Vector3f scale(float s) {
		x *= s;
		y *= s;
		z *= s;
		return this;
	}

	@Override
	public float lengthSquared() {
		return (x * x) + (y * y) + (z * z);
	}
}
