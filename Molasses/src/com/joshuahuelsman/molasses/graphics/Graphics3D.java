/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.graphics;

import com.joshuahuelsman.molasses.vector.Matrix4f;
import com.joshuahuelsman.molasses.vector.Vector3f;

/**
 * This is an experimental 3D software renderer.
 * It in neither stable nor usable.
 * @author joshuahuelsman
 *
 */
public class Graphics3D extends Graphics {
	
	Matrix4f proj = Matrix4f.perspective(0, 680, 0, 480, 0.25f, 1000);
	
	Matrix4f f = Matrix4f.scale(-1, -1, 1);
	
	public void drawLine(float x1, float y1, float z1, float x2, float y2, float z2) {
		Vector3f v1 = new Vector3f(x1, y1, z1);
		Vector3f v2 = new Vector3f(x2, y2, z2);
		
		v1 = proj.mul(v1);
		v2 = proj.mul(v2);
		
		v1 = f.mul(v1);
		v2 = f.mul(v2);
		
		System.out.println("");
		System.out.println(v1.x);
		System.out.println(v1.y);
		System.out.println(v2.x);
		System.out.println(v2.y);
		super.drawLine(v1.x, v1.y, v2.x, v2.y);
	}
	

}
