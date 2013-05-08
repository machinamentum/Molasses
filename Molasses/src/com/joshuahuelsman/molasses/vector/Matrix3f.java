/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.vector;

public class Matrix3f {
	
	public float m00, m01, m02;
	public float m10, m11, m12;
	public float m20, m21, m22;
	
	public void transform(Vector2f v) {
		v.x = m00 * v.x + m01 * v.y;
		v.y = m10 * v.x + m11 * v.y;
	}
	
	public static Matrix3f createScale(float s) {
		Matrix3f m = new Matrix3f();
		setIdentity(m);
		m.m00 = s;
		m.m11 = s;
		return m;
	}
	
	public static Matrix3f setIdentity(Matrix3f m) {
		m.clear(0);
		m.m00 = 1;
		m.m11 = 1;
		m.m22 = 1;
		return m;
	}
	
	private void clear(float val) {
		m00 = val;
		m01 = val;
		m02 = val;
		m10 = val;
		m11 = val;
		m12 = val;
		m20 = val;
		m21 = val;
		m22 = val;
	}
	
	
	
}
