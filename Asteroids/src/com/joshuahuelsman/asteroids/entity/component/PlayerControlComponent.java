/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import com.joshuahuelsman.asteroids.AsteroidsGame;
import com.joshuahuelsman.asteroids.entity.BulletEntity;
import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.SoundSystem;
import com.joshuahuelsman.molasses.input.BasicCommand;
import com.joshuahuelsman.molasses.input.Command;
import com.joshuahuelsman.molasses.vector.Vector2f;

public class PlayerControlComponent extends LeftRightControlComponent {

	protected boolean shoot = false;

	private boolean left = true;

	@Override
	public void update(GameContainer gc, int deltaMS) {
		super.update(gc, deltaMS);

		if (shoot) {
			shoot = false;
			SoundSystem.play("shoot.wav");
			AsteroidsGame game = (AsteroidsGame) gc.getGame();
			BulletEntity bullet = new BulletEntity(owner);
			BasicMoveComponent move = new BasicMoveComponent(
					new Vector2f(0, -1));
			bullet.addComponent(move);
			if (left) {
				bullet.setPosition(new Vector2f(owner.getPosition()));
			} else {
				bullet.setPosition(new Vector2f(owner.getPosition())
						.add(new Vector2f(owner.getCollider().width
								- bullet.getCollider().width, 0)));
			}
			left = !left;
			game.addEntity(bullet);
		}
	}

	@Override
	public void controlPressed(Command command) {
		String s = ((BasicCommand) command).getName();
		if (s.equals("shoot")) {
			shoot = true;
		}
		super.controlPressed(command);
	}

}
