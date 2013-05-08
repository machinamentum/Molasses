/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses;

import com.joshuahuelsman.molasses.input.InputProviderListener;

public abstract class BasicGame implements Game, InputProviderListener {
	
	protected String title;
	
	public BasicGame(String title) {
		this.title = new String(title);
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean closeRequested() {
		return true;
	}
}
