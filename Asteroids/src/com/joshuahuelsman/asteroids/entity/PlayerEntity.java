/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import com.joshuahuelsman.asteroids.ImageLoader;
import com.joshuahuelsman.asteroids.entity.component.ImageRenderComponent;
import com.joshuahuelsman.asteroids.entity.component.PlayerControlComponent;
import com.joshuahuelsman.molasses.display.Display;
import com.joshuahuelsman.molasses.geom.Rectangle;

public class PlayerEntity extends HealthEntity {

	public PlayerEntity() {
		addComponent(new PlayerControlComponent());
		addComponent(new ImageRenderComponent(ImageLoader.load("player.png")));
		position.y = (9f/10f) * Display.getDisplayMode().getHeight();
	}
	
	
	@Override
	public Rectangle getCollider() {
		return new Rectangle((int)position.x, (int)position.y, 32, 32);
	}

}
