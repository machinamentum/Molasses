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

public class AsteroidSmallEntity extends HealthEntity {

	public AsteroidSmallEntity(GameContainer gc) {
		addComponent(new ImageRenderComponent(
				ImageLoader.load("asteroid_small.png")));
		addComponent(new BasicMoveComponent(new Vector2f(0, 1)));
		position.x = new Random()
				.nextInt((int) (gc.getHeight() - getCollider().width));
		position.y = -getCollider().height;
		maxHealth = 5;
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
		return new Rectangle(position.x, position.y, 32, 32);
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
			game.score += 5;
		}
	}

}
