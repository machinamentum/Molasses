/*
 * This file was written by Joshua Huelsman. Do whatever you want
 * with this stuff. If you think it's worth it, and we someday
 * meet, you can buy me an energy drink.
 */
package com.joshuahuelsman.molasses.input;

import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.event.MouseInputListener;


public class Mouse implements MouseInputListener {
	
	private static boolean grab = false;
	
	private static int lastY;
	
	private static int lastX;
	
	private static int x;
	
	private static int y;
	
	private static HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	
	public static void setGrabbed(boolean grab) {
		Mouse.grab = grab;
	}
	
	public static boolean isGrabbed() {
		return grab;
	}
	
	public static int getDX() {
		return x - lastX;
	}
	
	public static int getDY() {
		return y - lastY;
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	public static void update() {
		lastX = x;
		lastY = y;
	}
	
	public static boolean isButtonDown(int button) {
		try {
		return map.get(button);
		} catch (NullPointerException e) {
			//there was no mapping, so the button was never pressed.
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		map.put(e.getButton(), true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		map.put(e.getButton(), false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}
