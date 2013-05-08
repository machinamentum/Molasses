/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids.entity.component;

import java.awt.Graphics;

import com.joshuahuelsman.molasses.GameContainer;

public abstract class RenderComponent extends Component {
	
	public abstract void render(GameContainer gc, Graphics g);

}
