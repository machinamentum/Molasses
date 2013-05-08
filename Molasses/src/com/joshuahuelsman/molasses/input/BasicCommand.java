/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.input;

public class BasicCommand extends Command {
	
	private String name;
	
	public BasicCommand(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
