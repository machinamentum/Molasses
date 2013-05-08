/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses;

import java.awt.Graphics;

public interface Game {
	
	public void init(GameContainer gc);
	
	public void update(GameContainer gc, int deltaMS);
	
	public void render(GameContainer gc, Graphics g);
	
	public String getTitle();
	
	public boolean closeRequested();
	
}
