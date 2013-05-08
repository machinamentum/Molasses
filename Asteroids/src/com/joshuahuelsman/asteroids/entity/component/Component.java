/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import com.joshuahuelsman.asteroids.entity.Entity;
import com.joshuahuelsman.molasses.GameContainer;

public abstract class Component {
	
	protected Entity owner;
	
	public abstract void update(GameContainer gc, int deltaMS);
	
	public void setOwner(Entity owner) {
		this.owner = owner;
	}

}
