/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import com.joshuahuelsman.asteroids.ImageLoader;
import com.joshuahuelsman.asteroids.entity.component.AnimatedImageRenderComponent;
import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.geom.Rectangle;

public class BulletEntity extends Entity {
	
	private Entity owner = null;
	
	public BulletEntity(Entity owner) {
		this.owner = owner;
		addComponent(new AnimatedImageRenderComponent(ImageLoader.load("bullet.png"), 6, 10));
	}
	
	
	@Override
	public void update(GameContainer gc, int deltaMS) {
		super.update(gc, deltaMS);
		if(position.y + getCollider().height < 0 || position.y > gc.getHeight()) {
			remove = true;
		}
	}

	@Override
	public Rectangle getCollider() {
		return new Rectangle(position.x, position.y, 6, 10);
	}
	
	
	@Override
	public void onCollide(Entity other) {
		if(HealthEntity.class.isInstance(other) && other != owner) {
			HealthEntity entity = (HealthEntity)other;
			entity.damage(this);
			remove = true;
		}
	}

}
