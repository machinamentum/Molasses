/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.vector.Vector2f;

public class BasicMoveComponent extends Component {
	
	
	private Vector2f direction;
	
	private float speed = 0.2f;
	
	public BasicMoveComponent(Vector2f dir) {
		direction = new Vector2f(dir).normalise();
	}

	@Override
	public void update(GameContainer gc, int deltaMS) {
		owner.setVelocity(direction);
		owner.getVelocity().scale(deltaMS * speed);
	}
	
	public BasicMoveComponent setSpeed(float speed) {
		this.speed = speed;
		return this;
	}

}
