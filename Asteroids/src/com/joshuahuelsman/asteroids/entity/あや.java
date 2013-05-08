/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity;

import com.joshuahuelsman.asteroids.AsteroidsGame;
import com.joshuahuelsman.asteroids.ImageLoader;
import com.joshuahuelsman.asteroids.entity.component.BasicMoveComponent;
import com.joshuahuelsman.asteroids.entity.component.ImageRenderComponent;
import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.geom.Rectangle;
import com.joshuahuelsman.molasses.vector.Vector2f;

public class あや extends HealthEntity {

	public あや() {
		addComponent(new ImageRenderComponent(ImageLoader.load("あや.png")));
		addComponent(new BasicMoveComponent(new Vector2f(0, 1)).setSpeed(0.025f));
	}
	
	@Override
	public void update(GameContainer gc, int deltaMS) {
		super.update(gc, deltaMS);
		
		if(position.y > gc.getHeight()) {	
			remove = true;
		}
	}
	
	
	@Override
	public Rectangle getCollider() {
		return new Rectangle(position.x, position.y, 16, 16);
	}
	
	@Override
	public void onEntityRemoved(GameContainer gc) {
		super.onEntityRemoved(gc);
		if(health <= 0) {
			AsteroidsGame game = (AsteroidsGame)gc.getGame();
			game.score = -4182013;
			game.glitch = true;
		}
	}


}
