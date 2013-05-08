/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.input.BasicCommand;
import com.joshuahuelsman.molasses.input.Command;
import com.joshuahuelsman.molasses.input.InputProviderListener;
import com.joshuahuelsman.molasses.vector.Vector2f;

public class BasicControlComponent extends Component implements InputProviderListener {
	
	boolean init = true;
	
	boolean up, down, left, right;
	
	float speed = 0.5f;

	@Override
	public void update(GameContainer gc, int deltaMS) {
		if(init) {
			init = false;
			gc.getInputProvider().addListener(this);
		}
		owner.setVelocity(new Vector2f(0, 0));
		if(up) {
			owner.getVelocity().y = -deltaMS * speed;
		}
		if(down) {
			owner.getVelocity().y = deltaMS * speed;
		}
		if(left) {
			owner.getVelocity().x = -deltaMS * speed;
		}
		if(right) {
			owner.getVelocity().x = deltaMS * speed;
		}
		
		owner.getVelocity().normalise();
	}

	@Override
	public void controlPressed(Command command) {
		String str = ((BasicCommand)command).getName();
		if(str.equals("up")) {
			up = true;
		}else if(str.equals("down")) {
			down = true;
		}else if(str.equals("left")) {
			left = true;
		}else if(str.equals("right")) {
			right = true;
		}
	}

	@Override
	public void controlReleased(Command command) {
		String str = ((BasicCommand)command).getName();
		if(str.equals("up")) {
			up = false;
		}else if(str.equals("down")) {
			down = false;
		}else if(str.equals("left")) {
			left = false;
		}else if(str.equals("right")) {
			right = false;
		}
	}

}
