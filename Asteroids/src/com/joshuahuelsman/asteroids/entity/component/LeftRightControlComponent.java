/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import com.joshuahuelsman.molasses.GameContainer;
import com.joshuahuelsman.molasses.display.Display;
import com.joshuahuelsman.molasses.vector.Vector2f;

public class LeftRightControlComponent extends BasicControlComponent {
	
	
	@Override
	public void update(GameContainer gc, int deltaMS) {
		if(init) {
			init = false;
			gc.getInputProvider().addListener(this);
		}
		owner.setVelocity(new Vector2f(0, 0));
		if(left) {
			owner.getVelocity().x = -1;
		}
		if(right) {
			owner.getVelocity().x = 1;
		}
		
		owner.getVelocity().scale(deltaMS * speed);
		
		if(owner.getPosition().x < 0)
			owner.getPosition().x = 0;
		if(owner.getPosition().x + owner.getCollider().width >= Display.getDisplayMode().getWidth())
			owner.getPosition().x = Display.getDisplayMode().getWidth() - owner.getCollider().width;
		
		
	}
	
	

}
