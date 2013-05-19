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
	
	public Vector3f mul(Vector3f vec) {
		Vector3f ret = new Vector3f();
		ret.x = m00 * vec.x + m01 * vec.y + m02 * vec.z;
		ret.y = m10 * vec.x + m11 * vec.y + m12 * vec.z;
		ret.z = m20 * vec.x + m21 * vec.y + m22 * vec.z;;
		return ret;
	}

	public static Matrix3f mul(Matrix3f l, Matrix3f r) {
		Matrix3f m = new Matrix3f();
		m.m00 = l.m00 * r.m00 + l.m01 * r.m10 + l.m02 * r.m20;
		m.m01 = l.m00 * r.m01 + l.m01 * r.m11 + l.m02 * r.m21;
		m.m02 = l.m00 * r.m02 + l.m01 * r.m12 + l.m02 * r.m22;
		
		m.m10 = l.m10 * r.m00 + l.m11 * r.m10 + l.m12 * r.m20;
		m.m11 = l.m10 * r.m01 + l.m11 * r.m11 + l.m12 * r.m21;
		m.m12 = l.m10 * r.m02 + l.m11 * r.m12 + l.m12 * r.m22;
		
		m.m20 = l.m20 * r.m00 + l.m21 * r.m10 + l.m22 * r.m20;
		m.m21 = l.m20 * r.m01 + l.m21 * r.m11 + l.m22 * r.m21;
		m.m22 = l.m20 * r.m02 + l.m21 * r.m12 + l.m22 * r.m22;
		return m;
	}
	
	public static Matrix3f scale(float x, float y) {
		Matrix3f m = setIdentity(new Matrix3f());
		m.m00 = x;
		m.m11 = y;
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
