/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public final class RenderingContext {

	private int[] pixels;
	
	private BufferedImage image;
	
	protected static RenderingContext instance;
	
	protected RenderingContext(DisplayMode mode) {
		image = new BufferedImage(mode.getWidth(), mode.getHeight(), mode.getBufferType());
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
	
	public Graphics2D getGraphics() {
		return (Graphics2D) image.getGraphics();
	}
	
	
	public int[] getPixels() {
		return pixels;
	}
	
	public static RenderingContext getInstance() {
		return instance;
	}

}
