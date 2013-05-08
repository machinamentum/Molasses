/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.vector;


public strictfp class Matrix4f {
	
	public float m00, m01, m02, m03;
	public float m10, m11, m12, m13;
	public float m20, m21, m22, m23;
	public float m30, m31, m32, m33;

	
	/**
	 * @param vec the vector to multiply this matrix by.
	 * @return a new Vector3f that is the product of the
	 * input vector and this matrix;
	 */
	public Vector3f mul(Vector3f vec) {
		Vector3f ret = new Vector3f();
		ret.x = m00 * vec.x + m01 * vec.y + m02 * vec.z + m03 * 1;
		ret.y = m10 * vec.x + m11 * vec.y + m12 * vec.z + m13 * 1;
		ret.z = m20 * vec.x + m21 * vec.y + m22 * vec.z + m23 * 1;
		float w = m30 * vec.x + m31 * vec.y + m32 * vec.z + m33 * 1;
		ret.x /= w;
		ret.y /= w;
		ret.z /= w;
		return ret;
	}
	
	
	public static Matrix4f mul(Matrix4f l, Matrix4f r) {
		Matrix4f m = new Matrix4f();
		m.m00 = l.m00 * r.m00 + l.m01 * r.m10 + l.m02 * r.m20 + l.m03 * r.m30;
		m.m01 = l.m00 * r.m01 + l.m01 * r.m11 + l.m02 * r.m21 + l.m03 * r.m31;
		m.m02 = l.m00 * r.m02 + l.m01 * r.m12 + l.m02 * r.m22 + l.m03 * r.m32;
		m.m03 = l.m00 * r.m03 + l.m01 * r.m13 + l.m02 * r.m23 + l.m03 * r.m33;
		
		m.m10 = l.m10 * r.m00 + l.m11 * r.m10 + l.m12 * r.m20 + l.m13 * r.m30;
		m.m11 = l.m10 * r.m01 + l.m11 * r.m11 + l.m12 * r.m21 + l.m13 * r.m31;
		m.m12 = l.m10 * r.m02 + l.m11 * r.m12 + l.m12 * r.m22 + l.m13 * r.m32;
		m.m13 = l.m10 * r.m03 + l.m11 * r.m13 + l.m12 * r.m23 + l.m13 * r.m33;
		
		m.m20 = l.m20 * r.m00 + l.m21 * r.m10 + l.m22 * r.m20 + l.m23 * r.m30;
		m.m21 = l.m20 * r.m01 + l.m21 * r.m11 + l.m22 * r.m21 + l.m23 * r.m31;
		m.m22 = l.m20 * r.m02 + l.m21 * r.m12 + l.m22 * r.m22 + l.m23 * r.m32;
		m.m23 = l.m20 * r.m03 + l.m21 * r.m13 + l.m22 * r.m23 + l.m23 * r.m33;
		
		m.m30 = l.m30 * r.m00 + l.m31 * r.m10 + l.m32 * r.m20 + l.m33 * r.m30;
		m.m31 = l.m30 * r.m01 + l.m31 * r.m11 + l.m32 * r.m21 + l.m33 * r.m31;
		m.m32 = l.m30 * r.m02 + l.m31 * r.m12 + l.m32 * r.m22 + l.m33 * r.m32;
		m.m33 = l.m30 * r.m03 + l.m31 * r.m13 + l.m32 * r.m23 + l.m33 * r.m33;
		return m;
	}
	
	public static Matrix4f scale(float x, float y, float z) {
		Matrix4f m = setIdentity(new Matrix4f());
		m.m00 = x;
		m.m11 = y;
		m.m22 = z;
		return m;
	}
	
	public static Matrix4f translate(float x, float y, float z) {
		Matrix4f m = setIdentity(new Matrix4f());
		m.m03 = x;
		m.m13 = y;
		m.m23 = z;
		return m;
	}
	
	public static Matrix4f rotateX(double theta) {
		Matrix4f m = setIdentity(new Matrix4f());
		m.m11 = (float) Math.cos(theta);
		m.m21 = (float) Math.sin(theta);
		m.m12 = (float) -Math.sin(theta);
		m.m22 = (float) Math.cos(theta);
		return m;
	}
	
	public static Matrix4f perspective(float l, float r, float b, float t, float n, float f) {
		Matrix4f m = new Matrix4f();
		m.clear(0);
		m.m00 = (2 * n) / (r - l);
		m.m02 = - (r + l) / (r - l);
		m.m11 = (2 * n) / (t - b);
		m.m12 = - (t + b) / (t - b);
		m.m22 = (f + n) / (f - n);
		m.m23 = - (2 * f * n) / (f - n);
		m.m32 = 1;
		return m;
	}
	
	public static Matrix4f setIdentity(Matrix4f m) {
		m.clear(0);
		m.m00 = 1;
		m.m11 = 1;
		m.m22 = 1;
		m.m33 = 1;
		return m;
	}
	
	private void clear(float val) {
		m00 = val;
		m01 = val;
		m02 = val;
		m03 = val;
		
		m10 = val;
		m11 = val;
		m12 = val;
		m13 = val;
		
		m20 = val;
		m21 = val;
		m22 = val;
		m23 = val;
		
		m30 = val;
		m31 = val;
		m32 = val;
		m33 = val;
	}

}
