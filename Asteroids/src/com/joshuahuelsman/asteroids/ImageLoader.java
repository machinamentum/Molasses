/*
 * Copyright 2013 Joshua Huelsman
 */
package com.joshuahuelsman.asteroids;

import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	//Store references to all loaded images in a map.
	//This helps save on memory usage and load times.
	private static HashMap<String, Image> map = null;
	
	public static Image load(String ref) {
		if(map == null) {
			map = new HashMap<String, Image>();
		}
		if(map.containsKey(ref)) {
			return map.get(ref);
		}
		Image image = null;
		try {
			image = ImageIO.read(ClassLoader.getSystemResourceAsStream(ref));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(image != null) {
			map.put(ref, image);
		}
		return image;
	}

}
