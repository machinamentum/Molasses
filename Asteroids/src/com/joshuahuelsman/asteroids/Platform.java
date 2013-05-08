/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids;

import java.io.File;

public class Platform {
	
	public static final String OS = System.getProperty("os.name");
	
	static {
		getAppDir().mkdirs();
	}
	
	
	public static File getAppDir() {
		if(OS.toLowerCase().contains("win")) {
			return new File(System.getenv("APPDATA"), ".asteroids");
		}
		
		return new File(System.getProperty("user.home"), ".asteroids");
	}

}
