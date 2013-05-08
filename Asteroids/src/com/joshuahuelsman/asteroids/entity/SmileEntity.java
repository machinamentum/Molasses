/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import com.joshuahuelsman.asteroids.ImageLoader;
import com.joshuahuelsman.asteroids.entity.component.BasicControlComponent;
import com.joshuahuelsman.asteroids.entity.component.ImageRenderComponent;
import com.joshuahuelsman.molasses.geom.Rectangle;

public class SmileEntity extends Entity {
	
	public SmileEntity() {
		super();
		addComponent(new ImageRenderComponent(ImageLoader.load("smile.png")));
		addComponent(new BasicControlComponent());
	}

	@Override
	public Rectangle getCollider() {
		//TODO width, height
		return new Rectangle(position.x, position.y, 32, 32);
	}

}
