/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.input;

public interface InputProviderListener {
	
	/**
	 * Called when a key assigned to a previously
	 * bound Command, is pressed.
	 * @param command the Command
	 */
	public void controlPressed(Command command);
	
	/**
	 * Called when a key assigned to a previously
	 * bound Command, is released.
	 * @param command the Command
	 */
	public void controlReleased(Command command);

}
