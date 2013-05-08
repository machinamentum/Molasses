/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses;

import java.awt.Graphics;

import com.joshuahuelsman.molasses.display.Display;
import com.joshuahuelsman.molasses.display.DisplayMode;
import com.joshuahuelsman.molasses.display.RenderingContext;

public class AppGameContainer extends GameContainer {

	protected Graphics graphics;

	public AppGameContainer(Game game) {
		this(game, 640, 480);
	}

	public AppGameContainer(Game game, int width, int height) {
		super(game);
		Display.setDisplayMode(new DisplayMode(width, height));
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public void run() {
		Display.create();
		Display.setTitle(getGame().getTitle());
		graphics = RenderingContext.getInstance().getGraphics();
		getGame().init(this);
		super.run();
	}

}
