/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import java.util.Random;

import com.joshuahuelsman.asteroids.AsteroidsGame;
import com.joshuahuelsman.asteroids.ImageLoader;
import com.joshuahuelsman.asteroids.entity.component.BasicMoveComponent;
import com.joshuahuelsman.asteroids.entity.component.ImageRenderComponent;
import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.geom.Rectangle;
import com.joshuahuelsman.molasses.vector.Vector2f;

public class AsteroidMediumEntity extends HealthEntity {

	public AsteroidMediumEntity(GameContainer gc) {
		addComponent(new ImageRenderComponent(
				ImageLoader.load("asteroid_medium.png")));
		addComponent(new BasicMoveComponent(new Vector2f(0, 1)));
		position.x = new Random()
				.nextInt((int) (gc.getHeight() - getCollider().width));
		position.y = -getCollider().height;
		maxHealth = 10;
		health = maxHealth;
	}

	@Override
	public void update(GameContainer gc, int deltaMS) {
		rotation += deltaMS * 0.0025f;
		super.update(gc, deltaMS);
		if (position.y > gc.getHeight()) {
			remove = true;
		}
	}

	@Override
	public Rectangle getCollider() {
		return new Rectangle(position.x, position.y, 64, 64);
	}
	
	@Override
	public void onCollide(Entity other) {
		if(PlayerEntity.class.isInstance(other)) {
			((PlayerEntity)other).damage(this);
		}
	}

	@Override
	public void onEntityRemoved(GameContainer gc) {
		super.onEntityRemoved(gc);
		if (health == 0) {
			AsteroidsGame game = (AsteroidsGame) gc.getGame();
			game.score += 25;
		}
	}

}
