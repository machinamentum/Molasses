package com.joshuahuelsman.molasses.vector;

public abstract strictfp class Vector {
	
	public Vector negateLocal() {
		scale(-1);
		return this;
	}
	
	public abstract Vector negate();
	
	public Vector normalise() {
		float length = length();

		if (length == 0) {
			return this;
		}

		scale(1.0f / length);
		return this;
	}
	
	public float length() {
		return (float) Math.sqrt(lengthSquared());
	}
	
	public abstract float lengthSquared();
	
	public abstract Vector scale(float s);

}
